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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MyApiServlet", urlPatterns = {"/api/getStock", "/api/addStock", "/api/updateStock", "/api/deleteStock"})
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
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cde", "root", "");
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
            String createTableQuery1 = "CREATE TABLE IF NOT EXISTS "+ TABLE1_NAME+" ("
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
                    + ");";
            statement.executeUpdate(createTableQuery1);

            String createTableQuery2 = "CREATE TABLE IF NOT EXISTS " + TABLE2_NAME+ " ("
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
                    + ");";
            statement.executeUpdate(createTableQuery2);

            String createTableQuery3 = "CREATE TABLE IF NOT EXISTS " +TABLE3_NAME+ " ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "designation VARCHAR(255),"
                    + "quantite INT,"
                    + "dlu DATE" // Removed the comma after DATE
                    + ");";
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

           String data = retrieveDataFromDatabase(connection);
           response.getWriter().append(data);
            
            request.setAttribute("data",data);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
           
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//add data to stock
        try {
        	String affectataire = request.getParameter("affectataire");
            String module = request.getParameter("module");
            String nominal = request.getParameter("nominal");
            String secteur = request.getParameter("secteur");
            String natur_des_colis = request.getParameter("natur_des_colis");
            String n_du_colis = request.getParameter("n_du_colis");
            String designation_generique_du_colis = request.getParameter("designation_generique_du_colis");
            String precision_arti = request.getParameter("precision_arti");
            String dimens_long_large_haut = request.getParameter("dimens_long_large_haut");
            String volume = request.getParameter("volume");
            String poids = request.getParameter("poids");
            String valeur = request.getParameter("valeur");
            String iata = request.getParameter("iata");
            String projection = request.getParameter("projection");
            String observation = request.getParameter("observation");
            String champ_concataine = request.getParameter("champ_concataine");

            
            addDataToStock(affectataire, module, nominal, secteur, natur_des_colis, n_du_colis, designation_generique_du_colis, precision_arti, dimens_long_large_haut,volume,poids,valeur,iata,projection,observation,champ_concataine);
            response.getWriter().write("Data added successfully!");
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error adding data to the database: " + e.getMessage());
        }
        
        //update stock 
    	
//        String idString = request.getParameter("id");
//        int id = Integer.parseInt(idString);
//        String moduleUpdate = request.getParameter("module");
//        updateDataInDatabase(id, moduleUpdate);
        
        //delete stock
//        String idString2 = request.getParameter("id");
//        int id2 = Integer.parseInt(idString2);
//        deleteStockFromDatabase(id2);
//        response.getWriter().write("Row with ID " + id2 + " deleted successfully.");
    }


    //function to retrieve data from database
    private String retrieveDataFromDatabase(Connection connection) throws SQLException {
//    	System.out.print("haha");
        try (
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
    
    // Function to add data to the database
    public void addDataToStock(String test1, String test2, String test3, String test4, String test5, String test6, String test7, String test8, String test9, String test10, String test11, String test12, String test13, String test14, String test15, String test16) throws SQLException {
        String insertUserSQL = "INSERT INTO stock (affectataire, module, nominal, secteur, natur_des_colis, n_du_colis, designation_generique_du_colis, precision_arti, dimens_long_large_haut, volume, poids, valeur, iata, projection, observation, champ_concataine) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = establishConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertUserSQL)) {

            // Set the values for the placeholders in the SQL query
            preparedStatement.setString(1, test1);
            preparedStatement.setString(2, test2);
            preparedStatement.setString(3, test3);
            preparedStatement.setString(4, test4);
            preparedStatement.setString(5, test5);
            preparedStatement.setString(6, test6);
            preparedStatement.setString(7, test7);
            preparedStatement.setString(8, test8);
            preparedStatement.setString(9, test9);
            preparedStatement.setString(10, test10);
            preparedStatement.setString(11, test11);
            preparedStatement.setString(12, test12);
            preparedStatement.setString(13, test13);
            preparedStatement.setString(14, test14);
            preparedStatement.setString(15, test15);
            preparedStatement.setString(16, test16);

            // Execute the SQL statement
            preparedStatement.executeUpdate();

            System.out.println("Added successfully!");
        } catch (SQLException e) {
            System.err.println("Error adding user to the database: " + e.getMessage());
            throw e; // Rethrow the exception if you want the caller to handle it
        }
    }
    
//   update stock
    protected void updateDataInDatabase(int id, String nominal) {
        String sql = "UPDATE stock SET nominal = ? WHERE id = ?";

        try (Connection conn = establishConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nominal);
            pstmt.setInt(2, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data updated successfully.");
            } else {
                System.out.println("No data was updated.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating data in the database: " + e.getMessage());
            e.printStackTrace();
        }
    }
//	delete stock
    protected void deleteStockFromDatabase(int id) {
        String sql = "DELETE FROM stock WHERE id = ?";

        try (Connection conn = establishConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data deleted successfully.");
            } else {
                System.out.println("No data was deleted.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting data from the database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    
}
