/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package App;

import DataAccessLayer.CustomerHandler;
import DataTransferObject.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author namvh
 */
public class RegisterHandler extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet RegisterHandler</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet RegisterHandler at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }

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
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet RegisterHandler</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet RegisterHandler at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.io.UnsupportedEncodingException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, UnsupportedEncodingException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet RegisterHandler</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet RegisterHandler at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
        String fullName = request.getParameter("txtFullName");
        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        
        if (password == null || confirm == null) {
            response.sendRedirect("/BlueSkyWeb/register.jsp");
        }
        
        if (password.length() != 0 && confirm.length() != 0) {
            response.sendRedirect("/BlueSkyWeb/register.jsp");
        }
        
        if (!(password.equals(confirm))) {
            response.sendRedirect("/BlueSkyWeb/register.jsp");
        }
        
        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setName(fullName);
        customer.setPassword(password);
        
        CustomerHandler handler = new CustomerHandler();
        
        try {
//            if (handler.register(customer)) {
//                response.sendRedirect("/BlueSkyWeb/login.jsp");
//            }
            handler.register(customer);
            //response.sendRedirect("/BlueSkyWeb/login.jsp");
        } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException ex) {
            Logger.getLogger(RegisterHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return;
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
