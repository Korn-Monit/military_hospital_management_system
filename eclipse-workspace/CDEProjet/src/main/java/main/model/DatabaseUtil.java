package main.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/cde";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "";

    // Database name and table name
    private static final String DATABASE_NAME = "cde";
    private static final String TABLE_NAME = "stock";

    static {
        try {
            // Register the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection establishConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");
    }

    public static void createDatabaseAndTableIfNotExists(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            // Create the database if it does not exist
            String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME;
            statement.executeUpdate(createDatabaseQuery);

            // Use the database
            String useDatabaseQuery = "USE " + DATABASE_NAME;
            statement.executeUpdate(useDatabaseQuery);

            // Create the table if it does not exist
            String createTableQuery1 = "CREATE TABLE IF NOT EXISTS stock ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "affectataire VARCHAR(255),"
                    + "module VARCHAR(255),"
                    + "nominal VARCHAR(255),"
                    + "secteur VARCHAR(255),"
                    + "natur_des_colis VARCHAR(255),"
                    + "n_du_colis INT,"
                    + "designation_generique_du_colis VARCHAR(255),"
                    + "precision_arti VARCHAR(255),"
                    + "dimens_long_large_haut VARCHAR(255),"
                    + "volume DECIMAL(5, 2)," // Changed to DECIMAL
                    + "poids DECIMAL(5, 2)," // Changed to DECIMAL
                    + "valeur INT,"
                    + "iata DECIMAL(5, 2)," // Changed to DECIMAL
                    + "projection VARCHAR(255),"
                    + "observation VARCHAR(255),"
                    + "champ_concataine VARCHAR(255)"
                    + ")";
            statement.executeUpdate(createTableQuery1);

            String createTableQuery2 = "CREATE TABLE IF NOT EXISTS medicin ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "produit VARCHAR(255),"
                    + "dci VARCHAR(255),"
                    + "forme_dosage VARCHAR(255)," // Added column type
                    + "dlu DATE," // Removed the comma after DATE
                    + "qtte INT," // Added column type
                    + "lot VARCHAR(255),"
                    + "classe_therapeutique VARCHAR(255),"
                    + "n_caisse INT,"
                    + "caisse VARCHAR(255),"
                    + "donation INT"
                    + ")";
            statement.executeUpdate(createTableQuery2);

            String createTableQuery3 = "CREATE TABLE IF NOT EXISTS equipement ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "designation VARCHAR(255),"
                    + "quantite INT,"
                    + "dlu DATE" // Removed the comma after DATE
                    + ")";
            statement.executeUpdate(createTableQuery3);

            System.out.println("Database and table created successfully.");
        } catch (SQLException e) {
            System.err.println("Error creating database and tables: " + e.getMessage());
            throw e; // Rethrow the exception if you want the caller to handle it
        }
    }

//
//    public static Connection getConnection() throws SQLException {
//        Connection connection = establishConnection();
//        createDatabaseAndTableIfNotExists(connection);
//        return connection;
//    }
}
