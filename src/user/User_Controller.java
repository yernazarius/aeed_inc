package user;


import java.util.Objects;

public class User_Controller {
    private final i_User repo;

    public User_Controller(i_User repo) {
        this.repo = repo;
    }

    public String ADD_USER(String name, String surname, String email, String phone_number, String password) {
        User user = new User(name, surname, email, phone_number, password);
        boolean created = repo.create_user(user);
        return (created ? "User was created!" : "This phone number or email has been already used!");
    }

    public String LOGIN_USER(User user) {
        String response = repo.login_user(user);
        if (Objects.equals(response, "naverno_potomu_chto")) {
            return response;} else
        if (Objects.equals(response, "wrong phone number"))
            return "Sorry, this telephone number doesn't exist :_(\\nPlease try again, enter your telephone number and password.\\n\"";
        else
        if (Objects.equals(response, "wrong password")) return "Wrong password! Please try again.";
        return "01";
    }

    public User getter(User user) {
        return repo.hetuser(user);
    }

}
