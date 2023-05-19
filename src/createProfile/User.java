package createProfile;

public class User {

    private static String name;
    private static String password;
    private static String user_email;
    private static String user_role;
    public User() {
        super();
    }

    public User(String name, String password, String email, String type) {
        setPassword(password);
        setName(name);
        setUser_email(email);
        setUser_role(type);
    }
    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        User.password = password;
    }


    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        User.name = name;
    }

    public static String getUser_email() {
        return user_email;
    }

    public static void setUser_email(String user_email) {
        User.user_email = user_email;
    }

    public static String getUser_role() {
        return user_role;
    }

    public static void setUser_role(String user_role) {
        User.user_role = user_role;
    }

}