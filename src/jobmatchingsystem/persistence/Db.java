package jobmatchingsystem.persistence;
import java.sql.*;




public class Db {
    private static final String URL = "jdbc:mysql://localhost:3306/jobmatch";
    private static final String USER = "root";
    private static final String PASS = "Houston2024^";

    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Explicit load
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}

