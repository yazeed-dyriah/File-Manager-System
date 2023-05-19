package features;

import logger.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ImportFile implements IManagingFile {
    @Override
    public void FileFeature(Connection database) {
        Add(database);
    }
    Logger log;
    public void Add(Connection database) {
        System.out.println("Enter file path: ");
        Scanner scanner = new Scanner(System.in);
        String filename_input = scanner.nextLine();

        File file = new File(filename_input);
        String filename = file.getName();
        String file_type = filename.substring(filename.lastIndexOf(".") + 1);
        long file_size = file.length();

        String sql = "INSERT INTO files (filename,file_type,file_size,file) VALUES (?,?,?,?)";
        PreparedStatement statement = null;
        try {

            statement = database.prepareStatement(sql);
            InputStream inputStream = null;
            inputStream = new FileInputStream(file);

            statement.setString(1,filename);
            statement.setString(2,file_type);
            statement.setFloat(3,file_size);
            statement.setBinaryStream(4, inputStream);
            statement.executeUpdate();
            System.out.println("File added successfully");
            log.logInfo("File" + filename + "Imported" + file_size);

        } catch (SQLException e) {
            log.logError(e.getMessage());
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            log.logError(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}