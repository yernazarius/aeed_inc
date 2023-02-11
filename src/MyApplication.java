import user.User;
import user.User_Controller;
import user.User_Controller.*;
import db.i_db;
import user.User_DB;
import user.i_User;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class MyApplication {
    Scanner scanner = new Scanner(System.in);
    User_Controller controller = null;


    public MyApplication(User_Controller controller) {
        this.controller = controller;
    }


    public void start() {
            System.out.println("Hello! Welcome to online-canteen AEED-INC!");
            System.out.println("Do you have account?");
            System.out.println("Please enter `Yes` or `No`");
            String input = scanner.next();
            User user = new User();
            if (input.equalsIgnoreCase("yes")) {
                user = login_user();
                System.out.println(user.getName() + " " + user.getSurname());
            } else if (input.equalsIgnoreCase("no")) {
                create_account();
            }

    }

    public void create_account() {
        System.out.println("Please enter name");
        String name = scanner.next();
        System.out.println("Please enter surname");
        String surname = scanner.next();
        System.out.println("Please enter email");
        String email = scanner.next();
        System.out.println("Please enter phone number");
        String phone_number = scanner.next();
        System.out.println("Please enter password");
        String password = scanner.next();

        System.out.println(controller.ADD_USER(name, surname, email, phone_number, password));

    }


//    public void signIn() {
//        System.out.println("Please enter phone number");
//        String phone_number = scanner.next();
//        System.out.println("Please enter password");
//        String password = scanner.next();
//        if (controller.LOGIN_USER(phone_number, password)) {
//            System.out.println("You are in System. Welcome ");
//            System.exit(0);
//        } else
//            System.out.println("Please check your email or password");
//    }
    public User login_user() {
        System.out.println("Please enter phone number");
        String phone_number = scanner.next();
        System.out.println("Please enter password");
        String password = scanner.next();
        User user_2 = new User();
        String sql   = "SELECT name, surname, email, phone_number, password FROM users WHERE phone_number = ?";
        try (Connection connection = User_DB.db.getConnection(); PreparedStatement ps = connection.prepareStatement(sql);){
            ps.setString(1, phone_number);
            ResultSet rs = ps.executeQuery();
            while(!rs.isBeforeFirst()){
                System.out.println("Sorry, this telephone number doesn't exist :_(\nPlease try again, enter your telephone number and password\n");
                ps.setString(1, scanner.next());
                password = scanner.next();
                rs = ps.executeQuery();
            }
            while (rs.next()) {
                while (!rs.getString("password").equals(password)) {
                    System.out.println("Wrong password! Please try again");
                    password = scanner.next();
                }
                user_2 = new User(rs.getString("name"), rs.getString("surname"), rs.getString("email"),
                        rs.getString("phone_number"), rs.getString("password"));
            }
            return user_2;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
