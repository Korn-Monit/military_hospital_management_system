package main.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.control.MyApiServlet;

public class Stock {
    //retrieve data from table stock
    public String retrieveDataFromDatabase(Connection connection) throws SQLException {
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
    
    //add data to table stock
    public void addDataToStock(String test1, String test2, String test3, String test4, String test5, String test6, String test7, String test8, String test9, String test10, String test11, String test12, String test13, String test14, String test15, String test16) throws SQLException {
        String insertUserSQL = "INSERT INTO stock (affectataire, module, nominal, secteur, natur_des_colis, n_du_colis, designation_generique_du_colis, precision_arti, dimens_long_large_haut, volume, poids, valeur, iata, projection, observation, champ_concataine) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = MyApiServlet.establishConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertUserSQL)) {

            // Set the values for the placeholders in the SQL query
            preparedStatement.setString(1, test1);
            preparedStatement.setString(2, test2);
            preparedStatement.setString(3, test3);
            preparedStatement.setString(4, test4);
            preparedStatement.setString(5, test5);
            preparedStatement.setInt(6, Integer.parseInt(test6));
//            System.out.print(false);
            preparedStatement.setString(7, test7);
            preparedStatement.setString(8, test8);
            preparedStatement.setString(9, test9);
            preparedStatement.setFloat(10, Float.parseFloat(test10));
            preparedStatement.setFloat(11, Float.parseFloat(test11));
            preparedStatement.setInt(12, Integer.parseInt(test12));
            preparedStatement.setFloat(13, Float.parseFloat(test13));
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
    public boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public boolean isFloat(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
//  update stock
   public void updateDataInDatabase(int id, String module) {
       String sql = "UPDATE stock SET module = ? WHERE id = ?";

       try (Connection conn = MyApiServlet.establishConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

           pstmt.setString(1, module);
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
   public void deleteStockFromDatabase(int id) {
       String sql = "DELETE FROM stock WHERE id = ?";

       try (Connection conn = MyApiServlet.establishConnection();
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
