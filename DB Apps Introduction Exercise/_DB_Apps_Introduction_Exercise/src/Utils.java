import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

enum Utils {
    ;
    static final Connection getSQLConnection() throws SQLException {
        final Properties properties = new Properties();
        properties.setProperty(Constants.USER_KEY,Constants.USER_VALUE);
        properties.setProperty(Constants.PASSWORD_KEY,Constants.PASSWORD_VALUE);

        Connection connection = DriverManager
                .getConnection(Constants.JDBC_URL,properties);
        return connection;
    }
}
