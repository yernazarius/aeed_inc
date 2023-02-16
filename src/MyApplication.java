import Products.MenuOutput;
import user.User;
import user.User_Controller;
import user.User_DB;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
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
                 } else if (input.equalsIgnoreCase("no")) {
                     user = create_account(user);
                 }
            else while(true){
                    input = scanner.next();
                    if (input.equalsIgnoreCase("yes")) {
                        user = login_user();
                        break;
                    } else if (input.equalsIgnoreCase("no")) {
                        user = create_account(user);
                        break;
                    }
            input = scanner.next();}
            System.out.println("Welcome " + user.getName() + " " + user.getSurname()+"!");
            System.out.println("What do you want?");
            outputMenu();



    }

    public User create_account(User user) {
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
        user = new User(name, surname, email, phone_number, password);
        return user;

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
        String sql   = "SELECT name, surname, email, phonenumber, password FROM users WHERE phonenumber = ?";
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
                        rs.getString("phonenumber"), rs.getString("password"));
            }
            return user_2;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public void printOptions() {
        System.out.println("Choose the menu option:");
        System.out.println("1 - Dishes");
        System.out.println("2 - Desserts");
        System.out.println("3 - Salads");
        System.out.println("4 - Cakes");
        System.out.println("5 - End ordering");
    }

    public void outputMenu(){
        int num = 0;
        //String [] chosenItems = new String[500];
        String[] options ={"dishes", "desserts", "salads", "cakes"};
        while(true) {
            printOptions();
            int option = scanner.nextInt();
            if (option == 5) break;
            while(option<1 || option>5){
                System.out.println("Incorrect input! Please try again)");
                option = scanner.nextInt();
            }

            String type = options[option-1];
            menu.displayMenu(type);

            int chosen_id = scanner.nextInt();
            boolean isCorrectId = true;
            try {
                sum += menu.getPriceOfOrder(chosen_id, type);
            } catch (Exception e){
                isCorrectId = false;
            }
            if(!isCorrectId){
                System.out.println("There is an error. Please enter again:");
                chosen_id = scanner.nextInt();
                sum += menu.getPriceOfOrder(chosen_id, type);
            }
            menu.outputChosenOrder(chosen_id,type,chosenItems,num);
            num++;

            System.out.println("What else do you want?");
        }
        System.out.println("Thank you! ");

        printList(chosenItems);

        System.out.println("Your order is: " + sum+ " tenge. Do u wanna edit it? Yes or no?" );
        String ans = scanner.next();
        if (ans.equalsIgnoreCase("yes")) {
            editTheList(chosenItems);
            if (isEdited) return;
            printList(chosenItems);
        } else {
            System.out.println("As bolsyn!");
            return;
        }
    }
}

