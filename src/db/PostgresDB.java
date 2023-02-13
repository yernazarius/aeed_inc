package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDB implements i_db{
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        //String connection = "jdbc:postgresql://localhost:5433/canteendb";
        String connection = "jdbc:postgresql://localhost:5432/CanteenDB";
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(connection, "postgres", "12345");
    }
}
