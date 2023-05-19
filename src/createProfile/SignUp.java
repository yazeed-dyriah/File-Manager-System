package createProfile;
import logger.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class SignUp {

    static Logger logger;



    private static final int EMAIL_KEY = 1;
    private static final int TYPE_PROFILE_WARNING = 2;
    private static final int PASSWORD_KEY = 4;

    private static final int NAME_KEY = 3;


    public static void signup() {

        Scanner keyboard = new Scanner (System.in);
        System.out.println("Enter Name : ");
        String name = keyboard.nextLine();
        System.out.println("Enter Email : ");
        String email = keyboard.nextLine();
        System.out.println("Enter Password : ");
        String password = keyboard.nextLine();
        System.out.println("Enter Number Type of Profile( Admin 1 Staff 2 Reader 3 : ) ");
        String role  = keyboard.nextLine();



        User member = new User(password,name, email,role);

    }
    public static void insert(Connection conmysql)
    {
        signup();
        try {

            String sql = "insert into users (user_email,user_role,password,name) VALUES (?, ?,? ,?)";

            PreparedStatement ps  = conmysql.prepareStatement(sql);
            ps.setString(EMAIL_KEY, User.getUser_email());
            ps.setString(PASSWORD_KEY, User.getPassword());
            ps.setString(NAME_KEY, User.getName());
            ps.setString(TYPE_PROFILE_WARNING, User.getUser_role());

            int row = ps.executeUpdate();
            logger.logInfo("Create Profile");


        } catch (SQLException e) {
            e.printStackTrace();
            logger.logError("Can't create Profile : "+e.getMessage());
        }



    }

}
