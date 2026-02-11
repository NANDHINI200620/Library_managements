package com.wipro.book.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBUtil {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE"; 
    private static final String USER = "system";
    private static final String PASSWORD = "ORACLE";

    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver"); 
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getDBConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
