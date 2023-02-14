package user;

import db.i_db;

import java.sql.*;

public class User_DB implements i_User {
    public static  i_db db = null;

    public User_DB(i_db db) {
        this.db = db;
    }
    @Override
    public boolean create_user(User user) {
        String sql = "INSERT INTO users(name, surname, email, phonenumber, password) VALUES(?, ?, ?, ?, ?)";
        String sql_2 = "SELECT id, name, surname, email, phonenumber, password FROM users WHERE phonenumber = ?";
        try (Connection connection = db.getConnection();  PreparedStatement ps = connection.prepareStatement(sql); ){
            PreparedStatement ps_2= connection.prepareStatement(sql_2);
            ps_2.setString(1, user.getPhoneNumber());
            ResultSet rs = ps_2.executeQuery();
            if (rs.isBeforeFirst()) return false;

            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPhoneNumber());
            ps.setString(5, user.getPassword());
            ps.execute();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean phone_number_exists(String phone_number) {
        String sql = "SELECT phonenumber FROM users WHERE phonenumber = ?";

        try (Connection connection = db.getConnection();  PreparedStatement ps = connection.prepareStatement(sql); ){

            ps.setString(1, phone_number);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean password_exists(String password) {
        String sql = "SELECT password FROM users WHERE password = ?";
        try (Connection connection = db.getConnection(); PreparedStatement ps = connection.prepareStatement(sql);){
            ps.setString(1, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


//     @Override
//    public void login_user(String phone_number, String password, User user, String message) {
//         String sql = "SELECT id, name, surname, phone_number, password FROM users WHERE phone_number = ?";
//         try (Connection connection = db.getConnection();  PreparedStatement ps = connection.prepareStatement(sql); ){
//             ps.setString(1, phone_number);
//             ResultSet rs = ps.executeQuery();
//             if(rs.isBeforeFirst()) {
//                 message = "Sorry, this telephone number doesn't exist((( \nPlease try again, enter your telephone number and password\n";
//                 return;
//             }
//
//             while (rs.next()) {
//                 if (!rs.getString("password").equals(password)) {message = "Wrong password! Please try again";
//                     return;
//                 }
//                 user = new User(rs.getString("name"), rs.getString("surname"), rs.getString("email"),
//                         rs.getString("phone_bumber"), rs.getString("password"));
//                 message = "Good, job!";
//                 return;
//             }
//         } catch (SQLException | ClassNotFoundException e) {
//             throw new RuntimeException(e);
//         }
//
//     }


}
