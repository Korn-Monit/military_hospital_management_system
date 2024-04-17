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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
//        throws ServletException, IOException {
        try {
//        	List<String> arrayList = new ArrayList<>();
            String data = retrieveDataFromDatabase();
//           response.getWriter().append(data);
            
            System.out.print(data);
//           System.out.print("test");
            request.setAttribute("data",data);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
            
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
//    }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract data from request parameters
        String name = request.getParameter("name");
//        String email = request.getParameter("email");

        // Validate and sanitize the data (optional)

        // Add data to the database
        try {
            addDataToDatabase(name);
            response.getWriter().println("Data added successfully!");
        } catch (SQLException e) {
            throw new ServletException("Error adding data to the database", e);
        }
    }

    //function to retrieve data from database
    private String retrieveDataFromDatabase() throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM test")) {
        	
        	
        	
            StringBuilder sb = new StringBuilder();
            while (resultSet.next()) {
                sb.append(resultSet.getString("name")).append("\n");
            }
            return sb.toString();
        }
    }
    
    private void addDataToDatabase(String name) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO your_table (name) VALUES (?)")) {
            
            statement.setString(1, name);
//            statement.setString(2, email);
            
            statement.executeUpdate();
        }
    }
}
