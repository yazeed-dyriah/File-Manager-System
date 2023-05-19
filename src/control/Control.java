package control;

import createProfile.Login;
import createProfile.SignUp;
import database.MySQL;
import features.IManagingFile;
import features.ManagerFileFactory;
import logger.Logger;

import versionControl.Factory;
import versionControl.IVersionControl;
import database.IDatabase;
import java.sql.Connection;
import java.util.Scanner;

public class Control {

    static Logger log;
    static IDatabase mySQL = new MySQL();

    public static int Login(Connection Database)
    {
        log.logInfo("Login in Profile");
        return Login.loginApplication(Database);
    }
    public static void  SignUp(Connection Database) {
        log.logInfo("Create Profile");
        SignUp.insert(Database);
    }
    public static void TypeUser(int type)
    {
        if(type==1 || type==2 || type==3)
        {
            Connection DataBase= mySQL.getConnection();
            if(DataBase!=null) {
                switch(type)
                {
                    case 1:
                        Admin(DataBase);
                        break;
                    case 2:
                        //Staff(DataBase);
                        break;
                    case 3:
                        //Reader(DataBase);
                        break;
                    default:

                }
            }
        }
    }
    private static void Admin(Connection DataBase) {
        ManagerFileFactory  managerFileFactory = new ManagerFileFactory();

        boolean  exit =true;
        do {


            try {

                System.out.println("1-Import");
                System.out.println("2-Export");
                System.out.println("3-Delete");
                System.out.println("4-Files list");
                System.out.println("5-Version Control");
                System.out.println("6-Exit");

                System.out.println("Enter The choice :");
                Scanner keyboard = new Scanner (System.in);
                int choice = keyboard.nextInt();

                switch (choice) {
                    case 1:
                        IManagingFile imp = managerFileFactory.getOperationType("IMPORT");
                        imp.FileFeature(DataBase);

                        break;
                    case 2:
                        IManagingFile export = managerFileFactory.getOperationType("EXPORT");
                        export.FileFeature(DataBase);
                        break;
                    case 3:
                        IManagingFile delete = managerFileFactory.getOperationType("DELETE");
                        delete.FileFeature(DataBase);

                        break;
                    case 4:
                        IManagingFile lis = managerFileFactory.getOperationType("FILE_LIST");
                        lis.FileFeature(DataBase);
                        break;
                    case 5:
                        VersionControl(DataBase);
                    case 6:
                        exit=false;
                        break;
                    default:
                }
            }
            catch (Exception e){
                System.out.println("Invalid Input, please enter a number ");
                log.logError("Invalid input " + e.getMessage());
            }
        }while (exit);
    }
    private static void Staff(Connection DataBase) {

        ManagerFileFactory  managerFileFactory = new ManagerFileFactory();

        boolean  exit =true;
        do {


            try {
                Scanner keyboard = new Scanner (System.in);
                System.out.println("Enter The choice :");
                System.out.println("1-Import ");
                System.out.println("2-Export");
                System.out.println("3-Files list");
                System.out.println("4-Exit");
                int choice = keyboard.nextInt();
                switch (choice) {
                    case 1:
                        IManagingFile imp = managerFileFactory.getOperationType("IMPORT");
                        imp.FileFeature(DataBase);
                        break;
                    case 2:
                        IManagingFile export = managerFileFactory.getOperationType("EXPORT");
                        export.FileFeature(DataBase);
                        break;
                    case 3:
                        IManagingFile lis = managerFileFactory.getOperationType("FILE_LIST");
                        lis.FileFeature(DataBase);
                        break;
                    case 4:
                        exit=false;
                        break;
                    default:
                }
            } catch (Exception e){
                System.out.println("Invalid Input, please enter a number ");
                log.logError("Invalid input " + e.getMessage());
            }
        }while (exit);
    }
    private static void Reader(Connection DataBase) {

        ManagerFileFactory  managerFileFactory = new ManagerFileFactory();
        boolean  exit =true;
        do {



            try {

                System.out.println("Enter The choice :");
                System.out.println("1-Export :");
                System.out.println("2-Files list");
                System.out.println("3-Exit");

                Scanner keyboard = new Scanner (System.in);
                int choice = keyboard.nextInt();

                switch (choice) {

                    case 1:
                        IManagingFile export = managerFileFactory.getOperationType("EXPORT");
                        export.FileFeature(DataBase);
                        break;
                    case 2:
                        IManagingFile lis = managerFileFactory.getOperationType("FILE_LIST");
                        lis.FileFeature(DataBase);
                        break;
                    case 3:
                        exit = false;
                        break;
                    default:
                }
            } catch (Exception e){
                System.out.println("Invalid Input, please enter a number ");
                log.logError("Invalid input " + e.getMessage());
            }
        }while (exit);

    }
    private static void VersionControl(Connection DataBase) {

        try {


            System.out.println("Import SnapShot Now '1'");
            System.out.println("Take SnapShot Now  '2'");

            Scanner keyboard = new Scanner (System.in);
            int input = keyboard.nextInt();

            switch(input) {
                case 1:
                    IVersionControl version = Factory.GetSnapShot("VERSION");
                    version.Snap(DataBase);
                    break;
                case 2:
                    IVersionControl Export = Factory.GetSnapShot("EXPORT");
                    Export.Snap(DataBase);
                    break;
                default:
            }
        }
        catch (Exception e){
            log.logError(e.getMessage());
            System.out.println("Invalid input");
        }


    }
    public static void main(String args[]) {
        int type = 0;
        Logger.logInfo("Welcome Application");
        boolean bool = true;
        MySQL mySQL = new MySQL();
        Connection mysql = mySQL.getConnection();
        boolean  exit =true;

        do {

            try {
                System.out.println("1-Sign Up");
                System.out.println("2-Sign In");
                System.out.println("3-Exit");
                Scanner keyboard = new Scanner(System.in);
                int input = keyboard.nextInt();

                switch (input) {
                    case 1:
                        SignUp(mysql);
                        break;
                    case 2:

                        type = Login(mysql);
                        System.out.println(type);
                        TypeUser(type);
                        if (type == 1) {
                            Admin(mysql);
                        }
                       else if (type == 2) {
                            Staff(mysql);
                        } else if (type == 3) {
                            Reader(mysql);
                        }
                        break;
                    case 3:
                        exit = false;
                        break;
                    default:
                }

            }
            catch(Exception e){
                System.out.println("Invalid Input, please enter a number ");
                log.logError("Invalid input " + e.getMessage());
            }
        }
        while (exit);
    }
}