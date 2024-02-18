import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class IncreaseAgeStoredProcedure {
    private final static String CALL_PROCEDURE = "CALL usp_get_older(?)";
    private final static String GET_MINION_INFO = "SELECT name,age FROM minions " +
            "WHERE id = ?;";
    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);
        int minionId = Integer.parseInt(scan.nextLine());
        Connection connection = Utils.getSQLConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(CALL_PROCEDURE);
        preparedStatement.setInt(1,minionId);
        preparedStatement.executeUpdate();

        PreparedStatement getInfoStatement = connection.prepareStatement(GET_MINION_INFO);
        getInfoStatement.setInt(1,minionId);
        ResultSet resultSet = getInfoStatement.executeQuery();
        if(resultSet.next()){
            System.out.printf("%s %d",resultSet.getString("name"),resultSet.getInt("age"));
        }

        connection.close();
    }
}
