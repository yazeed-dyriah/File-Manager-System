package createProfile;


import logger.Logger;
import exception.LoginException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Login {
    static Logger log;
    private static final int EMAIL_KEY = 1;
    private static final int TYPE_PROFILE_WARNING = 2;
    private static final int PASSWORD_KEY = 3;


    private static final int FAILED_LOGIN = 4;


    public static int loginApplication(Connection connection)
        
    {
        String exception = null;


        Scanner keyboard = new Scanner (System.in);
        System.out.println("Enter your Email : ");
        String inputEmail = keyboard.nextLine();
        System.out.println("Enter your Password : ");
        String inputPassword = keyboard.nextLine();

        try {
            log.logInfo("Login to Application");
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM users");

            while(resultSet.next())
            {

                if(resultSet.getString(EMAIL_KEY).equals(inputEmail)&&resultSet.getString(PASSWORD_KEY).equals(inputPassword)) {
                    return resultSet.getInt(TYPE_PROFILE_WARNING);

                }
            }

            throw new LoginException(exception);

        }
        catch (Exception e) {
            log.logInfo(e.getMessage());

        }

        return FAILED_LOGIN;

    }

}
