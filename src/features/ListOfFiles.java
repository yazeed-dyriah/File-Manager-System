package features;

import logger.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ListOfFiles implements IManagingFile {
    @Override
    public void FileFeature(Connection database) {
        list(database);
    }
    Logger log;
    public void list(Connection database){

        try {
            Statement stmt = database.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM files");

            while (rs.next()) {
                int id = rs.getInt("idfiles");
                String name = rs.getString("filename");
                System.out.println("File ID: " + id + " File name:"+ name);

            }
            log.logInfo("All Files listed");
            rs.close();
            stmt.close();
        } catch (Exception e) {
            log.logError(e.getMessage());

        }
    }
}
