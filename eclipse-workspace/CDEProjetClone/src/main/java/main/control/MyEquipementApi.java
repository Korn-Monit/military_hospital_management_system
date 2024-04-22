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

import main.model.Equipement;
import main.model.EquipementObject;
//import main.model.Medicin;
//import main.model.Stock;

@WebServlet(name = "MyApiServlet", urlPatterns = {"/api/getEquipement", "/api/addEquipement", "/api/updateEquipement", "/api/deleteEquipement"})
public class MyEquipementApi extends HttpServlet {
    // Declare a static variable to hold the database connection
    private static Connection connection;
    private static final String DATABASE_NAME = "cde";
    private static final String TABLE1_NAME = "stock";
    private static final String TABLE2_NAME = "medicin";
    private static final String TABLE3_NAME = "equipement";
    public Equipement equipement = new Equipement();
//    public Medicin medicin = new Medicin();
//    public Equipement equipement = new Equipement();
//    public Stock 

  @Override
  public void init(ServletConfig config) throws ServletException {
      // Call the function to establish the database connection
      try {
      	Connection connection = MyApiServlet.establishConnection();            // Log a message or perform any other setup tasks
          MyApiServlet.createDatabaseAndTableIfNotExists(connection);
          System.out.println("Database connection established.");
      } catch (SQLException e) {
          throw new ServletException("Failed to establish database connection.", e);
      }
  }

    // Function to establish a database connection

    


    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{

    	//get data from equipement
        try {
        	Connection connection;
			connection = MyApiServlet.establishConnection();

           List<EquipementObject> data3 = equipement.retrieveDataFromEquipement(connection);
            
            request.setAttribute("data3",data3);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index2.jsp");
            dispatcher.forward(request, response);
           
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
  	//add to equipement
      try {
    	String designation = request.getParameter("designation");
        String quantite = request.getParameter("quantite");
        String dlu = request.getParameter("dlu");

        
        equipement.addDataToEquipement(designation, quantite, dlu);
        response.getWriter().write("Data added successfully!");
    } catch (SQLException e) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.getWriter().write("Error adding data to the database: " + e.getMessage());
    }
    	
}
    
    
    protected void doPut(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{

    	//update equipement
	    String idString = request.getParameter("id");
	    int id = Integer.parseInt(idString);
	    String designationUpdate = request.getParameter("designation");
	    equipement.updateDataInEquipement(id, designationUpdate);
    }
    
    
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	//delete medicin
	    String idString = request.getParameter("id");
	    int id = Integer.parseInt(idString);
    	equipement.deleteEquipementFromDatabase(id);
    }

    



    

    



    
}
