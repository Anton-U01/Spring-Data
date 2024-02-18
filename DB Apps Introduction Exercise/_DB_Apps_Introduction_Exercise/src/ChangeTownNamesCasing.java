import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangeTownNamesCasing {
    private static final String UPDATE_TOWNS_BY_COUNTRY = "UPDATE towns " +
            "SET name = UPPER(name) " +
            "WHERE country = ?;";
    private static final String GET_TOWNS_BY_COUNTRY = "SELECT name FROM towns " +
            "WHERE country = ?";
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String country = scanner.nextLine();

        Connection connection = Utils.getSQLConnection();

        PreparedStatement updateTownsStatement = connection.prepareStatement(UPDATE_TOWNS_BY_COUNTRY);
        updateTownsStatement.setString(1,country);
        int affectedRows = updateTownsStatement.executeUpdate();
        if(affectedRows == 0){
            System.out.println("No town names were affected.");
        } else {
            System.out.printf("%d town names were affected.\n",affectedRows);
            PreparedStatement getTownsStatement = connection.prepareStatement(GET_TOWNS_BY_COUNTRY);
            getTownsStatement.setString(1,country);
            ResultSet resultSet = getTownsStatement.executeQuery();
            List<String> towns = new ArrayList<>();
            while (resultSet.next()){
                towns.add(resultSet.getString("name"));
            }
            System.out.printf("[" + String.join(", ",towns) + "]");
        }
        connection.close();
    }
}
