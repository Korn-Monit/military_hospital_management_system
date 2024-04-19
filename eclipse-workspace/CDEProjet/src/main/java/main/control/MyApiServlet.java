package main.control;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.model.DatabaseUtil;

public class MyApiServlet extends HttpServlet {
//    public void init() throws ServletException {
//        super.init();
//        try {
//            Connection connection = DatabaseUtil.establishConnection();
////            DatabaseUtil.createDatabaseAndTableIfNotExists(connection);
//            connection.close(); // Remember to close the connection
//        } catch (SQLException e) {
//            throw new ServletException("Failed to initialize database", e);
//        }
//    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        try {

        	Connection connection = DatabaseUtil.establishConnection();
//        	Statement statement = connection.createStatement();
//        	.createDatabaseAndTableIfNotExists(connection);
        	DatabaseUtil.createDatabaseAndTableIfNotExists(connection);
//        	String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS " + "cde";
//            statement.executeUpdate(createDatabaseQuery);
            
            // Use the database
//            String useDatabaseQuery = "USE " + "cde";
//            statement.executeUpdate(useDatabaseQuery);       
        
//            // Create the table if it does not exist
//            String createTableQuery = "CREATE TABLE IF NOT EXISTS stock ("
//            	    + "id INT AUTO_INCREMENT PRIMARY KEY,"
//            	    + "affectataire VARCHAR(255),"
//            	    + "module VARCHAR(255),"
//            	    + "nominal VARCHAR(255),"
//            	    + "secteur VARCHAR(255),"
//            	    + "natur_des_colis VARCHAR(255),"
//            	    + "n_du_colis INT,"
//            	    + "designation_generique_du_colis VARCHAR(255),"
//            	    + "precision_arti VARCHAR(255),"
//            	    + "dimens_long_large_haut VARCHAR(255),"
//            	    + "volume FLOAT(2),"
//            	    + "poids FLOAT(2),"
//            	    + "valeur INT,"
//            	    + "iata FLOAT(2),"
//            	    + "projection VARCHAR(255),"
//            	    + "observation VARCHAR(255),"
//            	    + "champ_concataine VARCHAR(255)"
//            	    + ")";
//            statement.executeUpdate(createTableQuery);
            String data = retrieveDataFromDatabase(connection);
//           response.getWriter().append(data);
            
            System.out.print(data);

            request.setAttribute("data",data);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
            
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
                sb.append(resultSet.getString("name")).append("\n");
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
