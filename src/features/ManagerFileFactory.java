package features;

public class ManagerFileFactory {
    public IManagingFile getOperationType(String operationType){

        switch(operationType) {
            case "IMPORT":
                return (IManagingFile) new ImportFile();
            case "EXPORT":
                return (IManagingFile) new ExportFile();
            case "DELETE":
                return (IManagingFile) new DeleteFile();
            case "FILE_LIST":
                return (IManagingFile) new ListOfFiles();
        }
        return null;
    }
}