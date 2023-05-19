package versionControl;

import logger.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Export implements IVersionControl {
    @Override
    public void Snap(Connection database) {

        rollback(database);
    }
    Logger log;
    int version;
    private ArrayList<String> tables;
    private String databaseName;

    public void rollback( Connection connection) {
        try {
            System.out.println("Enter your version: ");
            Scanner scanner = new Scanner(System.in);
            version = scanner.nextInt();
            Statement stmt = connection.createStatement();

            stmt.executeUpdate("DROP TABLE files");

        } catch (SQLException e) {
            log.logError(e.getMessage());
            e.printStackTrace();
        }

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE files" + " AS SELECT * FROM snapshot_" + version);

        } catch (SQLException e) {
            log.logError(e.getMessage());
            e.printStackTrace();
        }
    }
}
