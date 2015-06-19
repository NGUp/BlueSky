/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Employee;

import Core.Cryptography;
import DataAccessLayer.TicketPriceHandler;
import DataTransferObject.TicketPrice;
import java.io.IOException;
import java.io.PrintWriter;
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
public class UpdateTicketPriceHandler extends HttpServlet {

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
            out.println("<title>Servlet UpdateTicketPriceHandler</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateTicketPriceHandler at " + request.getContextPath() + "</h1>");
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
        
        if (request.getParameter("txtID") == null ||
                request.getParameter("txtStartTime") == null ||
                request.getParameter("txtEndTime") == null ||
                request.getParameter("txtPrice") == null ||
                request.getParameter("cbxFlight") == null ||
                request.getParameter("cbxCabin") == null) {
            response.sendRedirect("/employee/ticket/update.jsp");
            return;
        }
        
        String ID = request.getParameter("txtID");
        String starTime = request.getParameter("txtStartTime");
        String endTime = request.getParameter("txtEndTime");
        float price = Float.parseFloat(request.getParameter("txtPrice"));
        String flightID = request.getParameter("cbxFlight");
        String cabinID = request.getParameter("cbxCabin");
        
        if ("".equals(ID) || "".equals(starTime) || "".equals(endTime) || price == 0 || "".equals(flightID) || "".equals(cabinID)) {
            response.sendRedirect("/employee/ticket/update.jsp");
            return;
        }
        
        TicketPrice ticketPrice = new TicketPrice();
        ticketPrice.setID(ID);
        ticketPrice.setCabin(cabinID);
        ticketPrice.setFlight(flightID);
        ticketPrice.setPrice(price);
        ticketPrice.setStartTime(new Date(starTime));
        ticketPrice.setEndTime(new Date(endTime));
        
        if (ticketPrice.getStartTime().after(ticketPrice.getEndTime())) {
            response.sendRedirect("/employee/ticket/update.jsp");
            return;
        }
        
        TicketPriceHandler handler = new TicketPriceHandler();
        
        try {
            if (handler.update(ticketPrice)) {
                response.sendRedirect("/employee/ticket.jsp");
                return;
            }
            
            response.sendRedirect("/employee/ticket/update.jsp");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreateTicketPriceHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateTicketPriceHandler.class.getName()).log(Level.SEVERE, null, ex);
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
