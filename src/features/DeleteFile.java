package features;

import java.sql.Connection;
import logger.Logger;

import java.sql.PreparedStatement;
import java.util.Scanner;
public class DeleteFile implements IManagingFile {
    @Override
    public void FileFeature(Connection database) {
        delete(database);
    }

    Logger log;

    public void delete(Connection database){
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter file ID to delete: ");
            int idfiles = scanner.nextInt() ;
            PreparedStatement stmt = database.prepareStatement("DELETE FROM files WHERE idfiles = ?");
            stmt.setInt(1, idfiles);
            stmt.executeUpdate();
            log.logInfo("File deleted by ID: " + idfiles);
            stmt.close();
        } catch (Exception e) {
            log.logError(e.getMessage());


        }
    }
}