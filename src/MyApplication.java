import Products.MenuOutput;
import user.User;
import user.User_Controller;

import java.util.Objects;

import java.util.ArrayList;
import java.util.Scanner;

public class MyApplication {
    Scanner scanner = new Scanner(System.in);
    User_Controller controller = null;


    public MyApplication(User_Controller controller) {
        this.controller = controller;
    }
    private ArrayList<String> chosenItems = new ArrayList<>();
    private int sum = 0;
    private boolean isEdited = false;
    private ArrayList<Integer> prices= new ArrayList<>();
    MenuOutput menu = new MenuOutput();
    User user = new User();
    public ArrayList<Integer> getPrices() {
        return prices;
    }

    public void setPrices(ArrayList<Integer> prices) {
        this.prices = prices;
    }

    public void start() {
            System.out.println("Hello! Welcome to online-canteen AEED-INC!");
            System.out.println("Do you have account?");
            System.out.println("Please enter `Yes` or `No`");
            String input = scanner.next();;
            User user = new User();
            while(true){
                if (input.equalsIgnoreCase("yes")) {
                    user = login_user();

                    break;
                    } else if (input.equalsIgnoreCase("no")) {
                        user = create_account(user);
                        break;
                    }
                    else {
                        System.out.println("Input is incorrect!. Please enter again: ");
                        input = scanner.next();
                    }
            }
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


    public User login_user() {
        System.out.println("Please enter phone number");
        String phone_number = scanner.next();
        System.out.println("Please enter password");
        String password = scanner.next();
        User user_ = new User(phone_number, password);
        String result = "";
        while (!Objects.equals(result, "Good job!")) {
            result = controller.LOGIN_USER(user_);
            if (result == "naverno_potomu_chto") {
                user_ = controller.getter(user);
                return user_;
            } else {
                System.out.println(result);
                System.out.println("Please enter phone number");
                phone_number = scanner.next();
                System.out.println("Please enter password");
                password = scanner.next();
                user_ = new User(phone_number, password);
            }
        }
        return new User();

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
        String[] options ={"dishes_", "dessert_", "salads_", "cakes_"};
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
                prices.add(menu.getPriceOfOrder(chosen_id, type));
                //sum += menu.getPriceOfOrder(chosen_id, type);
            } catch (Exception e){
                isCorrectId = false;
            }
            if(!isCorrectId){
                System.out.println("There is an error. Please enter again:");
                chosen_id = scanner.nextInt();

            }
            menu.outputChosenOrder(chosen_id,type,chosenItems);

            System.out.println("What else do you want?");
        }
        System.out.println("Thank you! ");

        printList(chosenItems);
        sum = calculateSum(prices);
        System.out.println("Your order is: " + sum + " tenge. Do u wanna edit it? Yes or no?" );
        String ans = scanner.next();
        while (!ans.equalsIgnoreCase("yes") && !ans.equalsIgnoreCase("no")) {
            System.out.println("Incorrect input. try again");
            ans = scanner.next();
        }
        if (ans.equalsIgnoreCase("yes")) {
            editTheList(chosenItems);

            if (isEdited) return;
            printList(chosenItems);
        } else {
            System.out.println("As bolsyn!");

            return;
        }

    }
    public void editTheList(ArrayList<String> list) {
        isEdited = true;
        while(true) {
            System.out.println("Do u want to delete(enter `1` or `delete`) or add(enter `2`)? Enter stop or 0 to end.");
            String ans = scanner.next();
            if (ans.equalsIgnoreCase("1") || ans.equalsIgnoreCase("delete")) {
                System.out.println("Which one?");
                int n = scanner.nextInt();
                list.remove(n - 1);
                sum -= prices.get(n - 1);
                if (list.size() == 0) {
                    System.out.println("You have deleted everything!!!!!!!!!");
                    return;
                }
            } else if (ans.equalsIgnoreCase("add") || ans.equalsIgnoreCase("2")) {
                outputMenu();
                return;
            }
            else if (ans.equalsIgnoreCase("stop") || ans.equalsIgnoreCase("0")){
                printList(list);
                System.out.println("Your order is: " + sum);
                break;
            }
            printList(list);
        }
    }

    public int calculateSum(ArrayList<Integer> p){
        int sum = 0;
        for (Integer integer : p) {
            sum += integer;
        }
        return sum;
    }

    public void printList(ArrayList<String> list) {
        for(int j = 0; j < list.size(); j++){
            int temp = j + 1;
            System.out.println(temp + ". "+ list.get(j)+" tenge");
        }
    }

}

