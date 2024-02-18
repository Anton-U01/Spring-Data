import java.sql.*;
import java.util.Properties;

public class GetVillainsNames {
        private static final String GET_VILLAINS_NAMES = "SELECT name,COUNT(DISTINCT mv.minion_id) AS minions_number FROM villains AS v " +
            "JOIN minions_villains AS mv ON mv.villain_id = v.id " +
            "GROUP BY name " +
            "HAVING minions_number > 15 " +
            "ORDER BY minions_number DESC;";

    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getSQLConnection();
        final PreparedStatement statement = connection.prepareStatement(GET_VILLAINS_NAMES);

        final ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            String villainName = resultSet.getString("name");
            int minionsNumber = resultSet.getInt("minions_number");
            System.out.printf("%s %d",villainName,minionsNumber);
        }
        connection.close();
    }
}
