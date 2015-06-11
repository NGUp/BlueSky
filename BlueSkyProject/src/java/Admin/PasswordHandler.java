package Admin;

import DataAccessLayer.EmployeeHandler;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
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
public class PasswordHandler extends HttpServlet {

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
            out.println("<title>Servlet PasswordHandler</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PasswordHandler at " + request.getContextPath() + "</h1>");
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
        String employee = request.getParameter("txtID");
        String oldPassword = request.getParameter("txtPasswordOld");
        String newPassword = request.getParameter("txtPasswordNew");
        String confirmPassword = request.getParameter("txtPasswordConfirm");
        
        if (employee.isEmpty()) {
            response.sendRedirect("/admin/info.jsp");
            return;
        }
        
        if (oldPassword.isEmpty()) {
            response.sendRedirect("/admin/info.jsp");
            return;
        }
        
        if (newPassword.isEmpty()) {
            response.sendRedirect("/admin/info.jsp");
            return;
        }
        
        if (confirmPassword.isEmpty()) {
            response.sendRedirect("/admin/info.jsp");
            return;
        }
        
        if (confirmPassword == newPassword) {
            response.sendRedirect("/admin/info.jsp");
            return;
        }
        
        EmployeeHandler handler = new EmployeeHandler();
        
        try {
            if (handler.updatePassword(employee, oldPassword, newPassword)) {
                response.sendRedirect("/admin/index.jsp");
            } else {
                response.sendRedirect("/admin/info.jsp");
            }
            
        } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException ex) {
            Logger.getLogger(App.PasswordHandler.class.getName()).log(Level.SEVERE, null, ex);
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
