/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import static control.LoginHandling.modelUser;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UserBean;

public class SigninInputHandling extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SigninInputHandling</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SigninInputHandling at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        
        try 
        {
            List<UserBean> list = modelUser.doRetrieveByUsername(username);
            String messaggio = "{\"esito\":\"#\"}";
            messaggio = messaggio.replace("#", null != list && list.isEmpty() ? "true" : username);
            
            response.setContentType("application/json");
            response.getWriter().write(messaggio);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(SigninInputHandling.class.getName()).log(Level.SEVERE, "CRUD op failed: {0}", ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
