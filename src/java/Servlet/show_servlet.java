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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.Cliente;
import modelos.Pais;

/**
 *
 * @author usuario
 */
@WebServlet(name = "show_servlet", urlPatterns = {"/show"})
public class show_servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response,Integer id)
            throws ServletException, IOException, SQLException, ClassNotFoundException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        
        Cliente buscado=Cliente.buscar(id);
        if(buscado==null){
            
             HttpSession session = request.getSession();
             session.setAttribute("noEncontrado","El cliente que intenta buscar no se encuentra disponible");
            response.sendRedirect(request.getContextPath()+"/index");
         
        }else{
            request.setAttribute("cliente", buscado);
            ArrayList<Pais> paises = Pais.listado();
          request.setAttribute("readonly", true);
        
                request.setAttribute("paises", paises);
            request.getRequestDispatcher("WEB-INF/jsp/show.jsp").forward(request, response);
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
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            String get=request.getParameter("id");
            Integer id = Integer.parseInt(get);
            request.setAttribute("id", id);
            processRequest(request, response,id);
            
        } catch (SQLException ex) {
            Logger.getLogger(show_servlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(show_servlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(show_servlet.class.getName()).log(Level.SEVERE, null, ex);
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
