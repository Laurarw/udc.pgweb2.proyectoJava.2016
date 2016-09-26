/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import servicios.MySQL;

/**
 *
 * @author usuario
 */
public class Pais {
        private static final String tabla = "nacionalidades";
    private Integer id;
    private String nombre;

    public Pais(Integer id,String nombre) {
        this.id=id;
        this.nombre=nombre;
    }
    
    public Pais() {
        this.id=null;
        this.nombre=null;
    }
    
    public static ArrayList<Pais> listado() throws SQLException, ClassNotFoundException {

        ArrayList<Pais>  nacionalidades = new ArrayList<>();

        Connection db = MySQL.conectar();
        try {

            String Query = "SELECT n.id,n.nombre FROM `" + tabla + "` n";

            Statement st = db.createStatement();
            //java.sql.ResultSet resultSet;
            ResultSet resultado = st.executeQuery(Query);
            while (resultado.next()) {
                nacionalidades.add(new Pais(resultado.getInt("id"),
                        resultado.getString("nombre")
                ));
            }

        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Error en la adquisición de datos");
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nacionalidades;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
        Pais.listado();
        Iterator<Pais> it = Pais.listado().iterator();
            while(it.hasNext())
                System.out.println(it.next().getId());
           
    }
    
}
