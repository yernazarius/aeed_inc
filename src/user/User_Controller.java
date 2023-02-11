package user;


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
    public boolean isExist(String phone_number) {
        return repo.phone_number_exists(phone_number);
    }

    public boolean accountExists(String password) {
        return repo.password_exists(password);
    }

//    public boolean LOGIN_USER(String phone_number, String password) {
//        return repo.login_user(phone_number, password);
//    }

}
