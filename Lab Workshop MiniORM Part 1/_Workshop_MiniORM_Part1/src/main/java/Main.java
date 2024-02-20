import Entities.User;
import orm.Connector;
import orm.EntityManager;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Connector.createConnection("root","1234","soft_uni");
        Connection connection = Connector.getConnection();

        EntityManager<User> userEntityManager = new EntityManager<>(connection);
        User user1 = new User("Gosho",25, LocalDate.now());
        user1.setId(1);
        userEntityManager.persist(user1);

        Iterable<User> users = userEntityManager.find(User.class,"age > 15");
        User firstUser = userEntityManager.findFirst(User.class,"age > 5");
        System.out.println(firstUser);
        System.out.println(users.iterator().next());
    }
}
