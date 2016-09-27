/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.Usuario;
import servicios.Validacion;

/**
 *
 * @author usuario
 */
@WebServlet(name = "login_servlet", urlPatterns = {"/login"})
public class login_servlet extends HttpServlet {

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
            out.println("<title>Servlet login_servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet login_servlet at " + request.getContextPath() + "</h1>");
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
       //HttpSession session = request.getSession(true);	
       //HttpSession session= (HttpSession) request.getSession();
//        Usuario uId = (Usuario) session.getAttribute("usuario_registrado");

        
      request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
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
        try {
            String usuario=request.getParameter("usuario");
            String pass=request.getParameter("pass");
            
            HashMap<String, Object> errores=new HashMap();
            
            Usuario usser           =Validacion.loginValid(usuario,pass);
            HttpSession session = request.getSession(true);	
           
            if(usser==null){
                errores.put("login", "Datos incorrectos. Porfavor intente de nuevo.");
                request.setAttribute("errores", errores);
                session.setAttribute("noEcontrado","Usuario Invalido"); 
                request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
            }else{
                    
          session.setAttribute("usuario_registrado",usser); 
          session.setAttribute("Exito","Usuario Valido."); 
          request.getRequestDispatcher("WEB-INF/jsp/loginValid.jsp").forward(request, response);
                
            }
           
            
            
            
        } catch (SQLException | ClassNotFoundException | ParseException ex) {
            Logger.getLogger(login_servlet.class.getName()).log(Level.SEVERE, null, ex);
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
