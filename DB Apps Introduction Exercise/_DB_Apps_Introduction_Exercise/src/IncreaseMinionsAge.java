import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class IncreaseMinionsAge {
    private final static String UPDATE_MINION_BY_NAME = "UPDATE minions " +
            "SET age = age + 1, name = LOWER(name) " +
            "WHERE id = ?;";
    private final static String GET_MINION_INFO = "SELECT name,age FROM minions;";
    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);
        int[] ids = Arrays.stream(scan.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        Connection connection = Utils.getSQLConnection();

            PreparedStatement statement = connection.prepareStatement(UPDATE_MINION_BY_NAME);
            for (int i = 0; i < ids.length; i++) {
                statement.setInt(1,ids[i]);
                statement.executeUpdate();
            }


        PreparedStatement printStatement = connection.prepareStatement(GET_MINION_INFO);
        ResultSet resultSet = printStatement.executeQuery();
        while (resultSet.next()){
            System.out.printf("%s %d\n",resultSet.getString("name"),resultSet.getInt("age"));
        }
        connection.close();
    }
}
