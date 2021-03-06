package controllers;

import database.Authentication;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckLoginCredentials extends HttpServlet {

    private String username;
    private String password;
    private String role;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        username = request.getParameter("username");
        password = request.getParameter("password");   
        
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        
        role = Authentication.getRole(username, password);
        
        if ( role.equals("error") )
            response.getWriter().write("error");
        else
            setCookies( response );
    }

    private void setCookies( HttpServletResponse response ) {
        
        Cookie usernameCookie = new Cookie("username",username);
        usernameCookie.setMaxAge(-1);
        response.addCookie(usernameCookie);
        
        Cookie roleCookie = new Cookie("role",role);
        roleCookie.setMaxAge(-1);
        response.addCookie(roleCookie);    

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
