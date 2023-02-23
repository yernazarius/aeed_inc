package db;

import java.sql.Connection;
import java.sql.SQLException;

public interface i_db {
    Connection getConnection() throws ClassNotFoundException, SQLException;

}
