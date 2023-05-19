package versionControl;

import logger.Logger;
import java.sql.*;
import java.util.Scanner;

public class DatabaseVersionControl implements IVersionControl {

    Logger log;

    int version;

    public void createSnapshot(Connection connection) {
        // Create a new table for the snapshot
        try {
            System.out.println("Enter your version: ");
            Scanner scanner = new Scanner(System.in);
            version = scanner.nextInt();
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE snapshot_" + version + " AS SELECT * FROM files ");


            log.logInfo("Snapshot is under progress");
        }
        catch (SQLException e) {
            log.logError(e.getMessage());
            e.printStackTrace();
        }
    }
    @Override
    public void Snap(Connection database) {

        createSnapshot(database);
    }
}