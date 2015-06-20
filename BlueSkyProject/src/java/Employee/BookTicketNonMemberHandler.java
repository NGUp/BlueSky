/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Employee;

import Core.Cryptography;
import DataAccessLayer.BillHandler;
import DataAccessLayer.CustomerHandler;
import DataAccessLayer.TicketHandler;
import DataAccessLayer.TicketPriceHandler;
import DataTransferObject.Bill;
import DataTransferObject.Customer;
import DataTransferObject.Ticket;
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
public class BookTicketNonMemberHandler extends HttpServlet {

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
            out.println("<title>Servlet BookTicketNonMemberHandler</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookTicketNonMemberHandler at " + request.getContextPath() + "</h1>");
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
        if (request.getParameter("txtFlightID") == null ||
                request.getParameter("txtCabinID") == null ||
                request.getParameter("txtSeat") == null ||
                request.getParameter("txtName") == null ||
                request.getParameter("txtEmail") == null) {
            String referer = request.getHeader("Referer");
            response.sendRedirect(referer);
            return;
        }
        
        String flightID = request.getParameter("txtFlightID");
        String cabinID = request.getParameter("txtCabinID");
        String seat = request.getParameter("txtSeat");
        String email = request.getParameter("txtEmail");
        String name = request.getParameter("txtName");
        
        if ("".equals(flightID) || "".equals(cabinID) || "".equals(seat) || "".equals(email) || "".equals(name)) {
            String referer = request.getHeader("Referer");
            response.sendRedirect(referer);
            return;
        }
        
        CustomerHandler customerHandler = new CustomerHandler();
        TicketPriceHandler ticketPriceHandler = new TicketPriceHandler();
        BillHandler billHandler = new BillHandler();
        TicketHandler ticketHandler = new TicketHandler();
        
        try {
            Customer customer = new Customer();
            Cryptography crypto = new Cryptography();
            
            customer.setID(crypto.encode((new Date()).toString()));
            customer.setEmail(email);
            customer.setName(name);
            customer.setPassword("123456789");
            
            if (customerHandler.register(customer)) {
                String billID = crypto.encode((new Date()).toString());
            
                Bill bill = new Bill();
                bill.setID(billID);
                bill.setTime(new Date());
                bill.setCustomer(customer.getID());
                bill.setTotal(ticketPriceHandler.getPriceByFlightCabin(flightID, cabinID));

                if (billHandler.insert(bill)) {
                    Ticket ticket = new Ticket();
                    ticket.setID(crypto.encode((new Date()).toString()));
                    ticket.setBill(billID);
                    ticket.setCabin(cabinID);
                    ticket.setCustomer(customer.getID());
                    ticket.setFlight(flightID);
                    ticket.setPrice(ticketPriceHandler.getIDByFlightCabin(flightID, cabinID));
                    ticket.setSeat(seat);
                    ticket.setTime(bill.getTime());

                    if (ticketHandler.insert(ticket)) {
                        response.sendRedirect("/employee/search.jsp");
                        return;
                    }
                }
            }
            
            String referer = request.getHeader("Referer");
            response.sendRedirect(referer);
            
        } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException ex) {
            Logger.getLogger(BookTicketMemberHandler.class.getName()).log(Level.SEVERE, null, ex);
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
