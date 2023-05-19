package database;

import logger.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL implements IDatabase {
    Logger logger;
    Connection connectionDataBase = null;

    private static MySQL MySql = null;
    private String localDataBaseURL = "jdbc:mysql://localhost:3306/sys";
    private String user = "root";
    private String password = "aya123";

    public Connection getConnection()
    {

        if(MySql==null)
        {
            MySql =  new MySQL();

            try {
                connectionDataBase = DriverManager.getConnection(localDataBaseURL, user, password);
                logger.logInfo("Create Connection to DataBase");

            }
            catch (SQLException e) {
                logger.logError(e.getMessage());

            }

        }

        return connectionDataBase;
    }

}
