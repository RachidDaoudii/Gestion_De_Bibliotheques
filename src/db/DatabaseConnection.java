package db;

import java.sql.*;

public class DatabaseConnection {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/laravel";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private static Connection connection;

    public DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Charger le pilote JDBC
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }


    /** public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connexion à la base de données fermée.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }**/
}
