package main.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.control.MyApiServlet;

public class Medicin {

    public String retrieveDataFromMedecin(Connection connection) throws SQLException {

        try (
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM medicin")) 
        {   	
            StringBuilder sb = new StringBuilder();
            while (resultSet.next()) {
                sb.append(resultSet.getString("produit")).append("\n");
            }
            return sb.toString();
        }
    }
    
    //add data to table medicin
    public void addDataToMedicin(String test1, String test2, String test3, String test4, String test5, String test6, String test7, String test8, String test9, String test10) throws SQLException {
        String insertUserSQL = "INSERT INTO medicin (produit, dci, forme_dosage, dlu, qtte, lot, classe_therapeutique, n_caisse, caisse, donation) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = MyApiServlet.establishConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertUserSQL)) {

            // Set the values for the placeholders in the SQL query
            preparedStatement.setString(1, test1);
            preparedStatement.setString(2, test2);
            preparedStatement.setString(3, test3);
            preparedStatement.setString(4, test4);
            preparedStatement.setInt(5, Integer.parseInt(test5));
            preparedStatement.setString(6, test6);
            preparedStatement.setString(7, test7);
            preparedStatement.setInt(8, Integer.parseInt(test8));
            preparedStatement.setString(9, test9);
            preparedStatement.setInt(10, Integer.parseInt(test10));

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
    
   public void updateDataInMedicin(int id, String produit) {
       String sql = "UPDATE medicin SET produit = ? WHERE id = ?";

       try (Connection conn = MyApiServlet.establishConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

           pstmt.setString(1, produit);
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
   
   public void deleteMedicinFromDatabase(int id) {
       String sql = "DELETE FROM medicin WHERE id = ?";

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
