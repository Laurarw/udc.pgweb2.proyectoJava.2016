/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
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
import modelos.Usuario;
import servicios.Validacion;

/**
 *
 * @author usuario
 */
@WebServlet(name = "crear_servlet", urlPatterns = {"/crear"})
public class crear_servlet extends HttpServlet {
    
    private static final String permiso="crear_cliente";
    
    public static Boolean permisoAutorizado(String [] permisosUsuario){
        boolean a= Arrays.asList(permisosUsuario).contains(permiso);
        return a;
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        ///////////
        
         
        
        ////////
        ArrayList<Pais> paises = Pais.listado();
        
        request.setAttribute("paises", paises);
        request.getRequestDispatcher("WEB-INF/jsp/crear.jsp").forward(request, response);
        
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
        try {
         
             HttpSession session= (HttpSession) request.getSession();
            Usuario usser = (Usuario) session.getAttribute("usuario_registrado");
            if(usser==null){
                 request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
            }else{
                boolean valido=permisoAutorizado(usser.getRol().getPermiso());
                if(valido){
                     processRequest(request, response);
                }else{
                    session.setAttribute("noEncontrado", "No tiene permiso para realizar esta acción");
                     //response.sendRedirect(request.getContextPath()+"/denegado.jsp");
                     response.sendRedirect(request.getContextPath()+"/index");
                }
            }
           
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(crear_servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            Boolean activo=false;
            HashMap<String, String> cliente = new HashMap<>();
            
            cliente.put("nombre", request.getParameter("nombre"));
            cliente.put("apellido", request.getParameter("apellido"));
            cliente.put("fecha_nacimiento", request.getParameter("fecha_nac"));
           
            cliente.put("check", request.getParameter("activo"));
            cliente.put("nacionalidad", request.getParameter("lugar_nac"));
            
         
            
            if(cliente.get("check")!=null){
                
                activo=true;
            }
            
             
            HashMap<String, Object> errores=new HashMap();
            
            String nombre           =Validacion.soloLetras(cliente.get("nombre"));
            String apellido         =Validacion.soloLetras(cliente.get("apellido"));
            String fecha_nacimiento =Validacion.validarFecha(cliente.get("fecha_nacimiento"));
            Boolean nacionalidad    =Validacion.vacio(cliente.get("nacionalidad"));
            String edad             =Validacion.esMayorEdad(cliente.get("fecha_nacimiento"));
            
             
          
            
            if(nombre!=null){
                errores.put("nombre", nombre);
            }
            if(apellido!=null){
                errores.put("apellido", apellido);
            }
            if(fecha_nacimiento!=null){
                errores.put("fecha_nacimiento", fecha_nacimiento);
            }else if(edad!=null){
                errores.put("fecha_nacimiento", edad);
            }
            if(nacionalidad==false){
                errores.put("nacionalidad", "Debe ingresar el campo");
            }
          
            ////////verifica si hay errores
            if(!errores.isEmpty()){
                request.setAttribute("cliente", cliente);
                request.setAttribute("errores", errores);
                ArrayList<Pais> paises = Pais.listado();
        
                request.setAttribute("paises", paises);
                request.getRequestDispatcher("WEB-INF/jsp/crear.jsp").forward(request, response);
                
            }else{
                processRequestPOST(request, response,cliente, activo);
            }
        } catch (ParseException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(crear_servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
           
       
    }
    
protected void processRequestPOST(HttpServletRequest request, HttpServletResponse response,HashMap<String, String> cliente,Boolean activo)
            throws ServletException, IOException, SQLException, ClassNotFoundException, ParseException {
    
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha1 = null;
        fecha1 = formatoDelTexto.parse(cliente.get("fecha_nacimiento"));
  
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String fecha2 = formatter.format(fecha1);
          

        Cliente.guardar(new Cliente(null,cliente.get("nombre"),cliente.get("apellido"),fecha2,activo,cliente.get("nacionalidad")));
        
        response.sendRedirect(request.getContextPath()+"/index");
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
