package main.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.control.MyApiServlet;

public class Equipement {

	public List<EquipementObject> retrieveDataFromEquipement(Connection connection) throws SQLException {
		List<EquipementObject> equipements = new ArrayList<>();
        try (
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM eqipement")) 
        {   	
	        while (resultSet.next()) {
	        	EquipementObject equipement = new EquipementObject();
	        	equipement.setId(resultSet.getInt("id"));
	        	equipement.setDesignation(resultSet.getString("equipement"));
	        	equipement.setQuantite(resultSet.getInt("quantite"));
	        	equipement.setDlu(resultSet.getString("dlu"));
	        	equipements.add(equipement);
	        }
        }
        return equipements;
    }
    
    //add data to table medicin
    public void addDataToEquipement(String test1, String test2, String test3) throws SQLException {
        String insertUserSQL = "INSERT INTO equipement (designation, quantite, dlu) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = MyApiServlet.establishConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertUserSQL)) {

            // Set the values for the placeholders in the SQL query
            preparedStatement.setString(1, test1);
            preparedStatement.setInt(2, Integer.parseInt(test2));
            preparedStatement.setString(3, test3);


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
    
   public void updateDataInEquipement(int id, String designation) {
       String sql = "UPDATE equipement SET designation = ? WHERE id = ?";

       try (Connection conn = MyApiServlet.establishConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

           pstmt.setString(1, designation);
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
   
   public void deleteEquipementFromDatabase(int id) {
       String sql = "DELETE FROM equipement WHERE id = ?";

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
