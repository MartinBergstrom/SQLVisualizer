package model;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Martin on 2017-04-11.
 */
public class DBConnector {
    private static final String connectionURL =
            "jdbc:sqlserver://PAJBULLEN;integratedSecurity=true;";


    public static Connection getConnection() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Connection connect = null;

        Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

        connect = DriverManager.getConnection(connectionURL);
        System.out.println("Connected to database");

        return connect;
    }
}
