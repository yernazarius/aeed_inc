package Products;

import db.PostgresDB;
import db.i_db;
import user.User;

import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;


public class MenuOutput implements IMenu {
     private final i_db db = new PostgresDB();
    @Override
    public void displayMenu(String option) {
        String sql = "SELECT id, name, price FROM "+ option;


        try(Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                System.out.println(rs.getInt("id")+" "+rs.getString("name") + " " + rs.getInt("price"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } ;

    }
    @Override
    public Integer getPriceOfOrder(int index, String option) {
        String sql = "SELECT id, name, price FROM " + option + " WHERE id = ?";
        //i_db db = new PostgresDB();
        try(Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, index);
            ResultSet rs = ps.executeQuery();
            rs.next();

            return rs.getInt("price");


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void outputChosenOrder(int index, String option, ArrayList<String> items) {

        String sql = "SELECT id, name, price FROM " + option + " WHERE id = ?";
       // i_db db = new PostgresDB();
        try(Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, index);
            ResultSet rs = ps.executeQuery();
            rs.next();
            items.add(rs.getString("name") + " " + rs.getInt("price"));

        }catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }






    }

