/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Admin;

import Core.Cryptography;
import DataAccessLayer.EmployeeHandler;
import DataTransferObject.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author namvh
 */
public class CreateEmployeeHandler extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateEmployeeHandler</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateEmployeeHandler at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        //processRequest(request, response);
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
            throws ServletException, IOException, UnsupportedEncodingException {
        String fullName = request.getParameter("txtFullName");
        String email = request.getParameter("txtEmail");
        String userName = request.getParameter("txtUserName");
        String birthday = request.getParameter("txtBirthday");
        String ID = request.getParameter("txtID");
        String address = request.getParameter("txtAddress");
        String phone = request.getParameter("txtPhone");
        String gender = request.getParameter("cbxGender");
        
        if (fullName == null || email == null || userName == null || ID == null) {
            response.sendRedirect("/admin/employee.jsp");
        }
        
        if ("".equals(fullName) || "".equals(email) || "".equals(userName) || "".equals(ID)) {
            response.sendRedirect("/admin/employee.jsp");
        }
        
        Employee employee = new Employee();
        employee.setGender(gender);
        employee.setEmail(email);
        employee.setName(fullName);
        employee.setIdentityCard(ID);
        employee.setAddress(address);
        employee.setPhone(phone);
        employee.setBirthday(new Date(birthday));
        employee.setUsername(userName);
        
        Cryptography crypto = new Cryptography();
        
        try {
            employee.setID(crypto.encode((new Date()).toString()));
            employee.setPassword(crypto.encode("123456789"));
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CreateEmployeeHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        EmployeeHandler handler = new EmployeeHandler();
        
        try {
            if (handler.create(employee)) {
                response.sendRedirect("/admin/index.jsp");
                return;
            }
            
            response.sendRedirect("/admin/employee.jsp");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CreateEmployeeHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
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
