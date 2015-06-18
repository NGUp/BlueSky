/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Employee;

import DataAccessLayer.FlightHandler;
import DataTransferObject.Flight;
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
public class CreateFlightHandler extends HttpServlet {

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
            out.println("<title>Servlet CreateFlightHandler</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateFlightHandler at " + request.getContextPath() + "</h1>");
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
                request.getParameter("txtDateDeparture") == null ||
                request.getParameter("txtTimeDeparture") == null ||
                request.getParameter("txtDateArrival") == null ||
                request.getParameter("txtTimeArrival") == null ||
                request.getParameter("cbxTrip") == null ||
                request.getParameter("cbxPlane") == null ||
                request.getParameter("cbxMainPilot") == null ||
                request.getParameter("cbxVicePilot") == null ||
                request.getParameter("cbxMainStewardess") == null) {
            response.sendRedirect("/employee/flight/create.jsp");
            return;
        }
        
        String flightID = request.getParameter("txtID");
        String dateDeparture = request.getParameter("txtDateDeparture");
        String timeDeparture = request.getParameter("txtTimeDeparture");
        String dateArrival = request.getParameter("txtDateArrival");
        String timeArrival = request.getParameter("txtTimeArrival");
        String trip = request.getParameter("cbxTrip");
        String plane = request.getParameter("cbxPlane");
        String mainPilot = request.getParameter("cbxMainPilot");
        String vicePilot = request.getParameter("cbxVicePilot");
        String mainStewardess = request.getParameter("cbxMainStewardess");
        
        if (mainPilot.equals(vicePilot)) {
            response.sendRedirect("/employee/flight/create.jsp");
            return;
        }
        
        String[] departureTime = timeDeparture.split(":");
        String[] arrivalTime = timeArrival.split(":");
        
        Date departure = new Date(dateDeparture);
        departure.setHours(Integer.parseInt(departureTime[0]));
        departure.setMinutes(Integer.parseInt(departureTime[1]));
        
        Date arrival = new Date(dateArrival);
        arrival.setHours(Integer.parseInt(arrivalTime[0]));
        arrival.setMinutes(Integer.parseInt(arrivalTime[1]));
        
        Flight flight = new Flight();
        flight.setID(flightID);
        flight.setArrival(arrival);
        flight.setDeparture(departure);
        flight.setPlane(plane);
        flight.setTrip(trip);
        flight.setMainPilot(mainPilot);
        flight.setVicePilot(vicePilot);
        flight.setMainStewardess(mainStewardess);
        
        FlightHandler handler = new FlightHandler();
        
        try {
            if (handler.insert(flight)) {
                response.sendRedirect("/employee/flight.jsp");
                return;
            }
            
            response.sendRedirect("/employee/flight/create.jsp");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CreateFlightHandler.class.getName()).log(Level.SEVERE, null, ex);
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
