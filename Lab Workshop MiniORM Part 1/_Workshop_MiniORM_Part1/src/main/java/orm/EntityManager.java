package orm;

import orm.annotations.Column;
import orm.annotations.Entity;
import orm.annotations.Id;

import javax.xml.crypto.Data;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EntityManager <E> implements DbContext<E>{
    private static final String insertTemplate = "INSERT INTO %s (%s) VALUES (%s)";
    private static final String updateTemplate = "UPDATE %s SET %s WHERE %s";
    private static final String selectTemplateWithWhere = "SELECT %s FROM %s %s %s";
    private Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean persist(E entity) throws SQLException, IllegalAccessException {
        Field idColumn = getIdColumn(entity);
        idColumn.setAccessible(true);
        Object idValue = idColumn.get(entity);
        if(idValue == null || (long) idValue == 0){
            return doInsert(entity, insertTemplate);
        }

        return doUpdate(entity, idColumn,idValue);
    }




    private boolean doInsert(E entity, String insertTemplate) throws IllegalAccessException, SQLException {
        String tableName = getTableName(entity);
        List<String> columnsList = getColumnsWithoutID(entity);
        List<String> valuesList = getValues(entity);

        insertTemplate = String.format(insertTemplate,tableName,String.join(",",columnsList),String.join(",",valuesList));

        PreparedStatement statement = connection.prepareStatement(insertTemplate);
        int changedRows = statement.executeUpdate();

        return changedRows == 1;
    }


    @Override
    public Iterable<E> find(Class<E> table) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return find(table,null);
    }

    @Override
    public Iterable<E> find(Class<E> table, String where) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return baseFind(table, where,null);
    }



    @Override
    public E findFirst(Class<E> table) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return findFirst(table,null);
    }

    @Override
    public E findFirst(Class<E> table, String where) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        E firstEntity = baseFind(table,where,1).get(0);
        return firstEntity;
    }

    private List<E> baseFind(Class<E> table, String where,Integer limit) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String fieldList = "*";
        String tableName = getTableName(table);
        String whereClause = where == null ? "" : "WHERE " + where;
        String limitClause = limit == null ? "" : " LIMIT " + limit;

        String selectStatement = String.format(selectTemplateWithWhere, fieldList, tableName, whereClause,limitClause);
        PreparedStatement preparedStatement = connection.prepareStatement(selectStatement);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<E> list = new ArrayList<>();
        while (resultSet.next()){
            E entity = generateEntity(table,resultSet);

            list.add(entity);
        }
        return list;
    }

    private String getTableName(E entity) {
        Entity annotation = entity.getClass().getAnnotation(Entity.class);
        if(annotation == null){
            throw new RuntimeException("No Entity annotation");
        }

        return annotation.name();
    }

    private String getTableName(Class<E> clazz) {
        Entity annotation = clazz.getAnnotation(Entity.class);
        if(annotation == null){
            throw new RuntimeException("No Entity annotation");
        }

        return annotation.name();
    }

    private List<String> getValues(E entity) throws IllegalAccessException {
        List<String> valuesList = new ArrayList<>();
        Field[] declaredFields = entity.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if(field.isAnnotationPresent(Id.class)){
                continue;
            }
            Column annotation = field.getAnnotation(Column.class);
            if(annotation == null){
                continue;
            }
            field.setAccessible(true);
            Object fieldValue = field.get(entity);

            valuesList.add("'" + fieldValue.toString() + "'");
        }
        return valuesList;
    }

    private List<String> getColumnsWithoutID(E entity) {
        Field[] declaredFields = entity.getClass().getDeclaredFields();
        List<String> columns = new ArrayList<>();
        for (Field field : declaredFields) {
            Column annotation = field.getAnnotation(Column.class);
            if(annotation == null){
                continue;
            }
            if(field.isAnnotationPresent(Id.class)){
                continue;
            }
            columns.add(annotation.name());
        }
        return columns;
    }

    private Field getIdColumn(E entity) {
        Field[] declaredFields = entity.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if(field.isAnnotationPresent(Id.class)){
                return field;
            }
        }
        throw new RuntimeException("Entity has no Id column");
    }

    private boolean doUpdate(E entity, Field idColumn, Object idValue) throws IllegalAccessException, SQLException {
        String tableName = getTableName(entity);
        List<String> columns = getColumnsWithoutID(entity);
        List<String> values = getValues(entity);
        List<String> columnsWithValues = new ArrayList<>();
        for (int i = 0; i < columns.size(); i++) {
            String result = columns.get(i) + "=" + values.get(i);
            columnsWithValues.add(result);
        }
        String idCondition = String.format("%s=%s",idColumn.getName(),idValue.toString());
        String updateQuery = String.format(updateTemplate, tableName, String.join(",", columnsWithValues), idCondition);

        PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
        int updateCount = preparedStatement.executeUpdate();
        return updateCount == 1;

    }

    private E generateEntity(Class<E> table, ResultSet resultSet) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        //create
        E entity = table.getDeclaredConstructor().newInstance();
        //fill
        Field[] fields = table.getDeclaredFields();
        for (Field field : fields) {
            fillData(entity,field,resultSet);
        }

        return entity;
    }

    private void fillData(E entity, Field field, ResultSet resultSet) throws SQLException, IllegalAccessException {
        String dbFieldName = field.getAnnotation(Column.class).name();
        Class<?> javaType = field.getType();
        field.setAccessible(true);

        if(javaType == int.class || javaType == Integer.class){
            int value = resultSet.getInt(dbFieldName);
            field.setInt(entity,value);
        } else if (javaType == long.class || javaType == Long.class){
            long value = resultSet.getLong(dbFieldName);
            field.setLong(entity,value);
        } else if (javaType == LocalDate.class){
            LocalDate value = resultSet.getObject(dbFieldName,LocalDate.class);
            field.set(entity,value);
        } else if (javaType == String.class){
            String value = resultSet.getString(dbFieldName);
            field.set(entity,value);
        } else {
            throw new RuntimeException("Unsupported type " + javaType);
        }
    }

}
