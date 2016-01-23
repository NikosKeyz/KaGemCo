/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import database.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.ManagerRequest;

public class GetManagerRequests extends HttpServlet {

    /**
     * Returns a JSON file containing an array of manager requests. If no
	 * parameters are set, all manager requests are returned. Otherwise,
	 * parameters named after the ManagerRequest object fields can be passed
	 * as search criteria.
	 * 
	 * If an error occurs, an object containing a field named "error" is
	 * returned instead of an array in JSON format, that contains the error
	 * description.
	 * 
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		ArrayList<ManagerRequest> managerRequests;
		
		// Error message to be sent as JSON to the client
		String errorMessage = null;
		
		
		response.setContentType("application/json;charset=UTF-8");
        
		PrintWriter out = response.getWriter();
		
		// Attach jsonWriter to out PrintWriter
		JsonWriter jsonWriter = Json.createWriter(out);
		
		// Get search criteria as parameters from the URL //
		
		long requestID = -1;
		String requestIDParam = request.getParameter("requestID");
		
		if (requestIDParam != null) {
			try {
				requestID = Long.valueOf(requestIDParam);
			}
			// If a non number value was passed as a parameter to requestID,
			// return an error object containing the error message.
			catch (NumberFormatException e) {
				errorMessage = "request ID must be an integer number";

				// JSON error object creation
				JsonObjectBuilder errorObjBuilder = Json.createObjectBuilder();
				JsonObject errorObj = errorObjBuilder.add("error", errorMessage).build();

				// Write json contents to web page
				jsonWriter.writeObject(errorObj);

				return;
			}
		}
		
		
		
		String salesmanUsername = request.getParameter("salesmanUsername");
		String managerUsername = request.getParameter("managerUsername");
		String status = request.getParameter("status");
		String description = request.getParameter("description");
		String strictParam = request.getParameter("strict");
		boolean strict =true; // by default the strict mode is true
		
		// If strict parameter is defined, then parse it
		if (strictParam != null) {
			strict = Boolean.valueOf(request.getParameter("strict"));
		}
		
		// If no parameters are passed, then get all manager requests
		try {
			managerRequests = Database.searchManagerRequests(salesmanUsername, managerUsername, status, description, strict);
		} catch (IllegalArgumentException e) {
			managerRequests = Database.getManagerRequests();
		}
        
       
		JsonArrayBuilder rootArray = Json.createArrayBuilder();

		for (ManagerRequest managerRequest : managerRequests) {

			// Object that holds one manager request's information
			JsonObjectBuilder mrequestObj = Json.createObjectBuilder();

			// Fill object with manager request data
			mrequestObj
				.add("requestID", managerRequest.getRequestID())
				.add("salesmanUsername", managerRequest.getSalesmanUsername())
				.add("managerUsername", managerRequest.getManagerUsername())
				.add("status", managerRequest.getStatus())
				.add("description", managerRequest.getDescription());

			// Add manager request json object to root array
			rootArray.add(mrequestObj);
		}

		JsonArray jsonArray = rootArray.build();

		// Write json contents to web page
		jsonWriter.writeArray(jsonArray);
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
