package dk.easv.mrs.DAL.db;

// SQL server imports
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

// Project imports
import dk.easv.mrs.util.MRSException;

// Java imports
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class DBConnector {

    private static final String PROP_FILE = "config/config.settings";
    private SQLServerDataSource dataSource;

    public DBConnector() throws MRSException {
        try {
            Properties databaseProperties = new Properties();
            databaseProperties.load(new FileInputStream(new File(PROP_FILE)));

            dataSource = new SQLServerDataSource();
            dataSource.setServerName(databaseProperties.getProperty("Server"));
            dataSource.setDatabaseName(databaseProperties.getProperty("Database"));
            dataSource.setUser(databaseProperties.getProperty("User"));
            dataSource.setPassword(databaseProperties.getProperty("Password"));
            dataSource.setPortNumber(1433);
            dataSource.setTrustServerCertificate(true);
        }
        catch (IOException err) {
            // log
            throw new MRSException("Config file could not be loaded.", err);
        }
    }

    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }

    /**
     * Only for testing purposes
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        DBConnector databaseConnector = new DBConnector();

        try (Connection connection = databaseConnector.getConnection()) {
            System.out.println("Is it open? " + !connection.isClosed());
        } //Connection gets closed here
    }
}

