//package main.control;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import main.model.DatabaseUtil;
//
//public class UpdateServlet {
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//	        throws ServletException, IOException {
//	        try {
//	            String id = request.getParameter("id");
//	            String name = request.getParameter("name");
//
//	            // Validate input
//	            if (id == null || id.isEmpty() || name == null || name.isEmpty()) {
//	                response.getWriter().append("Invalid input.");
//	                return;
//	            }
//
//	            updateRecord(id, name);
//	            response.getWriter().append("Record updated successfully.");
//	        } catch (SQLException e) {
//	            // Log the exception
//	            System.err.println("Database error: " + e.getMessage());
//	            e.printStackTrace();
//	            throw new ServletException("Database error", e);
//	        }
//	    }
//	    
//	    private void updateRecord(String id, String name) throws SQLException {
//	        String updateQuery = "UPDATE test SET NAME = ? WHERE id = ?";
//	        try (Connection connection = DatabaseUtil.establishConnection();
//	            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
//
//	            preparedStatement.setString(1, name);
//	            preparedStatement.setString(2, id);
//	            preparedStatement.executeUpdate();
//	        }
//	    }
//}
