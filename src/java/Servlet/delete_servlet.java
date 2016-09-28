/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import static Servlet.crear_servlet.permisoAutorizado;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.Cliente;
import modelos.Usuario;

/**
 *
 * @author usuario
 */
@WebServlet(name = "delete_servlet", urlPatterns = {"/delete"})
public class delete_servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response,Integer id)
            throws ServletException, IOException, SQLException, ClassNotFoundException, ParseException {
         response.setContentType("text/html;charset=UTF-8");
        Cliente.eliminar(Cliente.buscar(id),false);
            
        HttpSession session = request.getSession();
        session.setAttribute("Exito","Se eliminó el registro con exito");
           response.sendRedirect(request.getContextPath()+"/index");
           
       
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    

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
            ////////////////////////
             HttpSession session= (HttpSession) request.getSession();
            Usuario usser = (Usuario) session.getAttribute("usuario_registrado");
            if(usser==null){
                 request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
            }else{
                boolean valido=permisoAutorizado(usser.getRol().getPermiso());
                if(valido){
                     String get=request.getParameter("id");
                    Integer id = Integer.parseInt(get);
                    request.setAttribute("id", id);
                    processRequest(request, response,id);
                }else{
                    session.setAttribute("noEncontrado", "No tiene permiso para realizar esta acción");
                     response.sendRedirect(request.getContextPath()+"/index");
                }
            }
            ///////////////////
            
            
            
        } catch (SQLException | ClassNotFoundException | ParseException ex) {
            Logger.getLogger(delete_servlet.class.getName()).log(Level.SEVERE, null, ex);
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
