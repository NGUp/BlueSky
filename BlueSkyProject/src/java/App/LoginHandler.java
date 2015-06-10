package App;

import DataAccessLayer.CustomerHandler;
import DataTransferObject.Customer;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author namvh
 */
public class LoginHandler extends HttpServlet {

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
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
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
        String email = request.getParameter("txtEmail");
        String passWord = request.getParameter("txtPassword");
        
        if (email == null || passWord == null) {
            response.sendRedirect("/login.jsp");
        }
        
        if (email.length() == 0 || passWord.length() == 0) {
            response.sendRedirect("/login.jsp");
        }
  
        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setPassword(passWord);

        CustomerHandler handler = new CustomerHandler();
        String ID = "";
        
        try {
            ID = handler.login(customer);

            if (ID != null && !"".equals(ID)) {
                HttpSession session = request.getSession(true);
                session.setAttribute("userID", ID);
                session.setAttribute("userName", handler.getName(ID));
            } else {
                response.sendRedirect("/login.jsp");
                return;
            }

            response.sendRedirect("/index.jsp");
        } catch (NoSuchAlgorithmException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(LoginHandler.class.getName()).log(Level.SEVERE, null, ex);
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
