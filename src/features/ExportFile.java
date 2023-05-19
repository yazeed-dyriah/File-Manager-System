package features;

import logger.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class ExportFile implements IManagingFile {

    @Override
    public void FileFeature(Connection database) {

        ExportFile(database);
    }
    static Logger log;
    public void ExportFile(Connection conn)  {
        Statement stmt = null;
        ResultSet rs = null;
        BufferedWriter writer = null;
        System.out.println("Enter file ID: ");
        Scanner scanner = new Scanner(System.in);
        int fileID_input = scanner.nextInt();

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM files");
            while (rs.next()) {

                int fileId = rs.getInt("idfiles");
                if(fileID_input == fileId){

                    String fileName = rs.getString("filename");
                    String data = rs.getString("file");
                    writer = new BufferedWriter(new FileWriter(fileName));
                    writer.write(data);
                    writer.newLine();
                    System.out.println("File exported successfully");
                    log.logInfo("File" + fileId + "exported" + fileName);
                }
            }

        } catch (SQLException e) {
            log.logError(e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.logError(e.getMessage());
            throw new RuntimeException(e);
        }
            finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException e) {
                        log.logError(e.getMessage());
                        throw new RuntimeException(e);
                    }
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException e) {
                        log.logError(e.getMessage());
                        throw new RuntimeException(e);

                    }
                }
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        log.logError(e.getMessage());
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
