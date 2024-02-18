import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GetMinionNames {
    private final static String GET_MINIONS_NAMES_AGE_BY_VILLAIN_ID =
            "SELECT name,age FROM minions AS m " +
                    "JOIN minions_villains AS mv ON mv.minion_id = m.id " +
                    "WHERE mv.villain_id = ?;";
    private final static String GET_VILLAIN_NAME_BY_ID =
            "SELECT name FROM villains " +
                    "WHERE id = ?;";

    public static void main(String[] args) throws SQLException {
        Connection connection = Utils.getSQLConnection();

        PreparedStatement statementVillainName = connection.prepareStatement(GET_VILLAIN_NAME_BY_ID);

        Scanner scanner = new Scanner(System.in);
        int villaindID = scanner.nextInt();
        statementVillainName.setInt(1,villaindID);
        ResultSet villainSet = statementVillainName.executeQuery();

        if(!villainSet.next()){
            System.out.printf("No villain with ID %d exists in the database.",villaindID);
        }
        String villainName = villainSet.getString("name");
        System.out.printf("Villain: %s\n",villainName);

        PreparedStatement statement = connection.prepareStatement(GET_MINIONS_NAMES_AGE_BY_VILLAIN_ID);
        statement.setInt(1,villaindID);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            String minionName = resultSet.getString("name");
            int minionAge = resultSet.getInt("age");
            int counter = 1;
            System.out.printf("%d. %s %d\n",counter,minionName,minionAge);
            counter++;
        }
        connection.close();
    }
}
