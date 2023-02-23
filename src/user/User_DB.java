package user;

import db.i_db;

import java.sql.*;
import java.util.Objects;

public class User_DB implements i_User {
    public static  i_db db = null;

    User userGGG = new User();
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
    public String login_user(User user) {
        String phone_number = user.getPhoneNumber();
        String password = user.getPassword();
        String sql   = "SELECT name, surname, email, phonenumber, password FROM users WHERE phonenumber = ?";
        try (Connection connection = User_DB.db.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, phone_number);
            ResultSet rs = ps.executeQuery();
            if(!rs.isBeforeFirst()){
                return "wrong phone number";
            }
            while (rs.next()) {
                if (!rs.getString("password").equals(password)) {
                    return "wrong password";
                }
                userGGG = new User(rs.getString("name"), rs.getString("surname"), rs.getString("email"),
                        rs.getString("phonenumber"), rs.getString("password"));
            }
            return "naverno_potomu_chto";
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User hetuser(User user) {
        return userGGG;
    }
}
