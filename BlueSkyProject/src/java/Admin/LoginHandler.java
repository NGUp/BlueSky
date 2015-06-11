package Admin;

import DataAccessLayer.EmployeeHandler;
import DataTransferObject.Employee;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
                    
        String username = request.getParameter("txtUsername");
        String passWord = request.getParameter("txtPassword");
        
        if (username == null || passWord == null) {
            response.sendRedirect("/admin/login.jsp");
        }
        
        if (username.length() == 0 || passWord.length() == 0) {
            response.sendRedirect("/admin/login.jsp");
        }
          
        Employee employee = new Employee();
        employee.setUsername(username);
        employee.setPassword(passWord);

        EmployeeHandler handler = new EmployeeHandler();
        String ID = "";
        
        try {
            ID = handler.login(employee);

            if (ID != null && !"".equals(ID)) {
                HttpSession session = request.getSession(true);
                session.setAttribute("userID", ID);
                session.setAttribute("userPermission", "USER");
                session.setAttribute("userName", handler.getName(ID));
            } else {
                response.sendRedirect("/admin/login.jsp");
                return;
            }

            response.sendRedirect("/admin/index.jsp");
        } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException ex) {
            Logger.getLogger(App.LoginHandler.class.getName()).log(Level.SEVERE, null, ex);
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
