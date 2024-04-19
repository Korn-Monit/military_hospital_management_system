package main.control;
//import net.sf.ehcache.CacheManager;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MyApiServlet extends HttpServlet {
    // Declare a static variable to hold the database connection
    private static Connection connection;
    private static final String DATABASE_NAME = "cde";
    private static final String TABLE1_NAME = "stock";
    private static final String TABLE2_NAME = "medicin";
    private static final String TABLE3_NAME = "equipement";

    @Override
    public void init(ServletConfig config) throws ServletException {
        // Call the function to establish the database connection
        try {
            connection = establishConnection();
            // Log a message or perform any other setup tasks
            createDatabaseAndTableIfNotExists(connection);
            System.out.println("Database connection established.");
        } catch (SQLException e) {
            throw new ServletException("Failed to establish database connection.", e);
        }
    }

    // Function to establish a database connection
    public static Connection establishConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/cde", "root", "");
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
            String createTableQuery1 = "CREATE TABLE IF NOT EXISTS"+TABLE1_NAME+" ("
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

            String createTableQuery2 = "CREATE TABLE IF NOT EXISTS" + TABLE2_NAME+ "("
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

            String createTableQuery3 = "CREATE TABLE IF NOT EXISTS" +TABLE3_NAME+ "("
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        try {

        	Connection connection;
			connection = establishConnection();

//        	Statement statement = connection.createStatement();

               
        

           String data = retrieveDataFromDatabase(connection);
           response.getWriter().append(data);
            
//            System.out.print(data);
//
            request.setAttribute("data",data);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
//            
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
    
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Extract data from request parameters
//        String name = request.getParameter("name");
//
//        try {
//            addDataToDatabase(name);
//            response.getWriter().println("Data added successfully!");
//        } catch (SQLException e) {
//            throw new ServletException("Error adding data to the database", e);
//        }
//    }

    //function to retrieve data from database
    private String retrieveDataFromDatabase(Connection connection) throws SQLException {
//    	System.out.print("haha");
        try (
            	            // Create the database if it does not exist
        		//        	Connection connection = DatabaseUtil.establishConnection();
            Statement statement = connection.createStatement();
        	
            ResultSet resultSet = statement.executeQuery("SELECT * FROM stock")) 
        {
        	
        	
        	
            StringBuilder sb = new StringBuilder();
            while (resultSet.next()) {
                sb.append(resultSet.getString("module")).append("\n");
            }
            return sb.toString();
        }
    }
    
//    private void addDataToDatabase(String name) throws SQLException {
//        try (Connection connection = DatabaseUtil.getConnection();
//             PreparedStatement statement = connection.prepareStatement("INSERT INTO your_table (name) VALUES (?)")) {
//            
//            statement.setString(1, name);
//            
//            statement.executeUpdate();
//        }
//    }
}
