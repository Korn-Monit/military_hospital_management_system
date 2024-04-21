package main.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.model.Medicin;

@WebServlet(name = "MyMedicinApi", urlPatterns = {"/api/getMedicin", "/api/addMedicin", "/api/updateMedicin", "/api/deleteMedicin"})
public class MyMedicinApi extends HttpServlet{
	public Medicin medicin = new Medicin();
	
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
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
    	//get data from medicin
        try {
        	Connection connection;
			connection = MyApiServlet.establishConnection();

           String data1 = medicin.retrieveDataFromMedecin(connection);
           response.getWriter().append(data1);
            
            request.setAttribute("data1",data1);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index1.jsp");
            dispatcher.forward(request, response);
           
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	//add to medecin
	      try {
	    	String produit = request.getParameter("produit");
	        String dci = request.getParameter("dci");
	        String forme_dosage = request.getParameter("forme_dosage");
	        String dlu = request.getParameter("dlu");
	        String qtte = request.getParameter("qtte");
	        String lot = request.getParameter("lot");
	        String classe_therapeutique = request.getParameter("classe_therapeutique");
	        String n_caisse = request.getParameter("n_caisse");
	        String caisse = request.getParameter("caisse");
	        String donation = request.getParameter("donation");
	        
	        medicin.addDataToMedicin(produit, dci, forme_dosage, dlu, qtte,lot, classe_therapeutique, n_caisse, caisse, donation);
	        response.getWriter().write("Data added successfully!");
	    } catch (SQLException e) {
	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        response.getWriter().write("Error adding data to the database: " + e.getMessage());
	    }
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
    	//update medicin
	    String idString = request.getParameter("id");
	    int id = Integer.parseInt(idString);
	    String produitUpdate = request.getParameter("produit");
	    medicin.updateDataInMedicin(id, produitUpdate);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	//delete medicin
	    String idString = request.getParameter("id");
	    int id = Integer.parseInt(idString);
    	medicin.deleteMedicinFromDatabase(id);
	}
}
