/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import servicios.MySQL;

/**
 *
 * @author usuario
 */
public class Rol {
    
    private static final String tabla = "roles";
    
    private String nombre;
    private Integer id;
    private String[] permiso;

    public Rol(String nombre, Integer id, String[] permiso) {
        this.nombre = nombre;
        this.id = id;
        this.permiso = permiso;
    }

    public Rol() {
        this.nombre = null;
        this.id = null;
        this.permiso = null;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getId() {
        return id;
    }

    public  String[] getPermiso() {
        return permiso;
    }

    @Override
    public String toString() {
        return "Rol{" + "nombre=" + nombre + ", id=" + id + ", permiso=" + Arrays.toString(permiso) + '}';
    }
    
    public static Rol buscar(int id) throws SQLException, ClassNotFoundException, ParseException {
        Rol cliente = null;
        Connection db = MySQL.conectar();
        java.sql.PreparedStatement consulta;

        try {
            consulta = db.prepareStatement("SELECT r.id,r.nombre FROM `" + tabla + " r WHERE r.id= ?;");
            consulta.setInt(1, id);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
               // cliente = new Cliente(id, resultado.getString("nombre"), resultado.getString("apellido"), resultado.getString("fecha_nacimiento"), resultado.getBoolean("activo"), resultado.getString("nacionalidad"));
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return cliente;
    }
    
    
}
