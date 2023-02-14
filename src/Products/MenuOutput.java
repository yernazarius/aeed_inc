package Products;

import db.PostgresDB;
import db.i_db;
import user.User;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class MenuOutput {
    public void displayMenu(String option){
        String sql = (new StringBuilder()).append("SELECT id, name, price FROM ").append(option).toString();
        i_db db = new PostgresDB();
        try(Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();){
            while (rs.next()){
                System.out.println(rs.getString("id")+" "+rs.getString("name") + " " + rs.getString("price"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer orderFood(int index, String option) {
        String sql = "SELECT id, name, price FROM " + option + " WHERE id = ?";
        i_db db = new PostgresDB();
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
    public void outputChosenOrder(int index, String option, String [] items, int i ){

        String sql = "SELECT id, name, price FROM " + option + " WHERE id = ?";
        i_db db = new PostgresDB();
        try(Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, index);
            ResultSet rs = ps.executeQuery();
            rs.next();
            items[i]=rs.getString("name")+" "+ rs.getInt("price");

        }catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        }
    }

