package user;

public interface i_User {
    boolean create_user(User user);
//    void login_user(String phone_number, String password, User user, String message);
    boolean phone_number_exists(String phone_number);

    boolean password_exists(String password);
//    boolean login_user(String phone_number, String password);
}
