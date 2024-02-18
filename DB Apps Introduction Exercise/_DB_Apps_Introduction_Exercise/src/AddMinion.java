import java.sql.*;
import java.util.Scanner;

public class AddMinion {
    private static final String GET_TOWN_BY_NAME = "SELECT id FROM towns " +
            "WHERE name = ?;";
    private static final String INSERT_INTO_TOWNS = "INSERT INTO towns (name) values (?);";
    private static final String GET_VILLAIN_BY_NAME = "SELECT id FROM villains " +
            "WHERE name = ?;";
    private static final String INSERT_INTO_VILLAINS = "INSERT INTO villains (name,evilness_factor) values (?,?);";
    private static final String INSERT_INTO_MINIONS = "INSERT INTO minions (name,age,town_id) values(?,?,?)";
    private static final String GET_LAST_MINION = "SELECT id FROM minions AS m ORDER BY id DESC LIMIT 1";
    private static final String INSERT_INTO_MINIONS_VILLAINS = "INSERT INTO minions_villains "+
            "(minion_id,villain_id) values (?,?)";
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String[] minionsInfo = scanner.nextLine().split(" ");
        String minionName = minionsInfo[1];
        int age = Integer.parseInt(minionsInfo[2]);
        String town = minionsInfo[3];

        Connection connection = Utils.getSQLConnection();
        PreparedStatement townStatement = connection.prepareStatement(GET_TOWN_BY_NAME);
        townStatement.setString(1,town);
        ResultSet townSet = townStatement.executeQuery();
        if(!townSet.next()){
            PreparedStatement insertTownStatement = connection.prepareStatement(INSERT_INTO_TOWNS);
            insertTownStatement.setString(1,town);
            insertTownStatement.executeUpdate();
            System.out.printf("Town %s was added to the database.\n",town);
        }

        ResultSet setForTownId = townStatement.executeQuery();
        int townId = -1;
        if(setForTownId.next()) {
            townId = setForTownId.getInt("id");
        }

        String villainName = scanner.nextLine().split(" ")[1];
        PreparedStatement villainStatement = connection.prepareStatement(GET_VILLAIN_BY_NAME);
        villainStatement.setString(1,villainName);
        ResultSet villainSet = villainStatement.executeQuery();
        if(!villainSet.next()){
            PreparedStatement insertVillainStatement = connection.prepareStatement(INSERT_INTO_VILLAINS);
            insertVillainStatement.setString(1,villainName);
            insertVillainStatement.setString(2,"evil");
            insertVillainStatement.executeUpdate();
            System.out.printf("Villain %s was added to the database.\n",villainName);
        }

        ResultSet setForVillainId = villainStatement.executeQuery();
        int villainId = -1;
        if(setForVillainId.next()){
            villainId = setForVillainId.getInt("id");
        }

       PreparedStatement addMinionStatement = connection.prepareStatement(INSERT_INTO_MINIONS);
       addMinionStatement.setString(1,minionName);
       addMinionStatement.setInt(2,age);
       addMinionStatement.setInt(3,townId);
       addMinionStatement.executeUpdate();

       PreparedStatement getLastMinionStatement = connection.prepareStatement(GET_LAST_MINION);
       ResultSet lastMinionSet = getLastMinionStatement.executeQuery();
       int lastMinionId = -1;
       if(lastMinionSet.next()){
           lastMinionId = lastMinionSet.getInt("id");
       }

       PreparedStatement insertMinionToVillainStatement = connection.prepareStatement(INSERT_INTO_MINIONS_VILLAINS);
       insertMinionToVillainStatement.setInt(1,lastMinionId);
       insertMinionToVillainStatement.setInt(2,villainId);
       insertMinionToVillainStatement.executeUpdate();
       System.out.printf("Successfully added %s to be minion of %s.\n",minionName,villainName);

       connection.close();
    }
}
