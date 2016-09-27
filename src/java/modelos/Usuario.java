/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;


import static com.sun.org.apache.xalan.internal.lib.ExsltStrings.split;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import servicios.MySQL;


/**
 *
 * @author usuario
 */
public class Usuario {
    
    private static final String tabla = "usuarios";

    private Integer id;
    private String nombre;
    private String pass;    
    private Boolean estado;
    private Rol rol;
    private String app="Biorgia";

    public Usuario(Integer id, String nombre, String pass, Boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.pass = pass;
        this.estado = estado;
        //this.app = app;
    }

    public Usuario() {
         this.id = null;
        this.nombre = null;
        this.pass = null;
        this.estado = null;
      
    }
    
    public static Usuario buscar(String nombre) throws SQLException, ClassNotFoundException, ParseException {
        Usuario usser = null;
        Connection db = MySQL.conectar();
        java.sql.PreparedStatement consulta;
        java.sql.PreparedStatement consultas;

        try {
            consulta = db.prepareStatement("SELECT u.id,u.nombre, u.contraseña, u.estado FROM "+tabla+" u where u.nombre= ?");
            consulta.setString(1, nombre);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                usser = new Usuario(resultado.getInt("id"), nombre,resultado.getString("contraseña"), resultado.getBoolean("estado"));
            }
            ////////se buscan los roles
            if(usser!=null){
            Rol rol=  buscarRol(usser.getNombre());
                
            usser.setRol(rol);
            System.out.println(usser.getRol());
            
            }
            
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
      
        
        return usser;
    }
    
    public static Rol buscarRol(String nombreUsuario) throws SQLException, ClassNotFoundException{
        
        Rol rol = null;
        String[] permisos = null ;
        int id=0;
        String nombrerol=null;
        
        Connection db = MySQL.conectar();
        java.sql.PreparedStatement consultas;
        
        consultas = db.prepareStatement("SELECT r.id, r.nombre as rol,GROUP_CONCAT(p.nombre SEPARATOR ',') as permisos FROM `roles` r INNER JOIN usuarios_roles_aplicaciones ura ON r.id=ura.roles_id INNER JOIN aplicaciones_web a ON a.id=ura.aplicaciones_web_id INNER JOIN usuarios u ON ura.usuarios_id=u.id INNER JOIN gestion_roles_permisos grp ON ura.roles_id=grp.roles_id INNER JOIN permisos p ON grp.permisos_id=grp.permisos_id where a.titulo='Biorgia' and u.nombre= ? group by r.nombre;");
            consultas.setString(1, nombreUsuario);
            
            ResultSet resultados = consultas.executeQuery();
            
            while (resultados.next()){
                id= resultados.getInt("id");
                nombrerol= resultados.getString("rol");
               
               permisos=resultados.getString("permisos").split(",");
              
            }
            
            rol = new Rol(nombrerol,id,permisos);
             
        
        return rol;
    }
    
    
    
    
    public String getEstado() {
        if (estado == true) {
            return "Activo";

        } else {
            return "Inactivo";
        }

    }

    public Boolean getEstadoBD() {
        return estado;

    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPass() {
        return pass;
    }

    public Rol getRol() {
        return rol;
    }

    public String getApp() {
        return app;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", pass=" + pass + ", estado=" + estado + ", rol=" + rol + ", app=" + app + '}';
    }
    
    public static void main(String[] args) throws SQLException{
        //Usuario a=new Usuario(1, "Dixon", "123456", null, null);
        try {
            Usuario buscado=Usuario.buscar("Dixon");
            System.out.println("usuario: "+buscado);
                    } catch (ClassNotFoundException | ParseException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
