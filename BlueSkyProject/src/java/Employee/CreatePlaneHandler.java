/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Employee;

import DataAccessLayer.PlaneHandler;
import DataTransferObject.Plane;
import java.io.IOException;
import java.io.PrintWriter;
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
public class CreatePlaneHandler extends HttpServlet {

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
            out.println("<title>Servlet CreatePlaneHandler</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreatePlaneHandler at " + request.getContextPath() + "</h1>");
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
            throws ServletException, IOException {
        if (request.getParameter("txtID") == null
                || request.getParameter("txtName") == null
                || request.getParameter("txtManufacturer") == null
                || request.getParameter("txtStart") == null) {
            response.sendRedirect("/employee/plane/create.jsp");
        }
        
        String ID = request.getParameter("txtID");
        String name = request.getParameter("txtName");
        String start = request.getParameter("txtStart");
        String manufacturer = request.getParameter("txtManufacturer");
        
        if ("".equals(ID) || "".equals(name) || "".equals(start) || "".equals(manufacturer)) {
            response.sendRedirect("/employee/plane/create.jsp");
        }
        
        PlaneHandler handler = new PlaneHandler();
        
        Plane plane = new Plane();
        plane.setID(ID);
        plane.setName(name);
        plane.setManufacturer(manufacturer);
        plane.setStart(new Date(start));
        
        try {
            if (handler.insert(plane)) {
                response.sendRedirect("/employee/plane.jsp");
                return;
            }
            
            response.sendRedirect("/employee/plane/create.jsp");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CreatePlaneHandler.class.getName()).log(Level.SEVERE, null, ex);
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
