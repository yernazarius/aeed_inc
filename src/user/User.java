package user;


public class User extends Human{
    private String phone_number;
    private String email;
    private String password;
    private  int bonus = 0;


    public User(){}
    public User(int id, String firstName, String secondName, String telephoneNumber, String password){
        super(id,firstName,secondName);
        this.phone_number = telephoneNumber;
        this.password = password;
    }

    public User(String name, String surname, String email, String phoneNumber, String password) {
        super(name, surname);
        setEmail(email); setPhoneNumber(phoneNumber); setPassword(password);
    }
    public User(String phone_number, String password) {
        setPhoneNumber(phone_number); setPassword(password);
    }
    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus += bonus;
    }
}