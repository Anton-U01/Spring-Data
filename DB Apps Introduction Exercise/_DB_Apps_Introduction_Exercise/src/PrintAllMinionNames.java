import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class PrintAllMinionNames {
    private final static String GET_MINIONS = "select name from minions";
    public static void main(String[] args) throws SQLException {
        Connection connection = Utils.getSQLConnection();
        PreparedStatement countStatement = connection.prepareStatement(GET_MINIONS);
        ResultSet resultSet = countStatement.executeQuery();
        Deque<String> deque = new LinkedList<>();
        while (resultSet.next()){
            String name = resultSet.getString("name");
            deque.add(name);
        }
        while (!deque.isEmpty()){
            System.out.println(deque.pollFirst());
            System.out.println(deque.pollLast());
        }
    }
}
