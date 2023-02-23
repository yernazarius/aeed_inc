package user;

public interface i_User {
    boolean create_user(User user);
//    void login_user(String phone_number, String password, User user, String message);

    String login_user(User user);
}
