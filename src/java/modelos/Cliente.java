/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import com.mysql.jdbc.PreparedStatement;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import servicios.MySQL;
import static servicios.MySQL.conectar;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.management.Query.lt;

/**
 *
 * @author usuario
 */
public class Cliente {

    private static final String tabla = "clientes";

    private Integer id;
    private Integer edad;

    private String nombre;
    private String apellido;
    private String fecha_nacimiento;
    private Boolean activo;
    private String nacionalidad;

    public Cliente(Integer id, String nombre, String apellido, String fecha_nacimiento, Boolean activo, String nacionalidad) throws ParseException {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.edad = calcularEdad(fecha_nacimiento);
        this.activo = activo;
        this.nacionalidad = nacionalidad;

        //this.setEdad(fecha_nacimiento);
    }

    public Cliente() {
        this.id = null;
        this.nombre = null;
        this.apellido = null;
        this.fecha_nacimiento = null;
        this.activo = null;
        this.nacionalidad = null;
        this.edad = null;
    }
    ////////////

    public Integer getId() {
        return id;
    }

    private Integer calcularEdad(String fecha) throws ParseException {

        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        

        Date fecha1 = null;
        fecha1 = formatoDelTexto.parse(fecha);
        String FBD=formatoDelTexto.format(fecha1);
        
        
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String fecha2 = formatter.format(fecha1);
        
        
        Date fecha_nac = null;
        fecha_nac=formatter.parse(fecha2);
        
        String fecha_na = formatter.format(fecha_nac);

        Date fechaNac = null;
        try {
            /**
             * Se puede cambiar la mascara por el formato de la fecha que se
             * quiera recibir, por ejemplo año mes día "yyyy-MM-dd" en este caso
             * es día mes año
             */

            //System.out.println(fecha);
            fechaNac = new SimpleDateFormat("dd/MM/yyyy").parse(fecha_na);
            
        } catch (Exception ex) {
            System.out.println("Error:" + ex);
        }
        Calendar fechaNacimiento = Calendar.getInstance();
        //Se crea un objeto con la fecha actual
        Calendar fechaActual = Calendar.getInstance();
        //Se asigna la fecha recibida a la fecha de nacimiento.
        fechaNacimiento.setTime(fechaNac);

        //Se restan la fecha actual y la fecha de nacimiento
        int año = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
        int mes = fechaActual.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);
        int dia = fechaActual.get(Calendar.DATE) - fechaNacimiento.get(Calendar.DATE);
        //Se ajusta el año dependiendo el mes y el día
        if (mes < 0 || (mes == 0 && dia < 0)) {
            año--;
        }
        //Regresa la edad en base a la fecha de nacimiento
        return año;

    }

    public Integer getEdad() throws ParseException {
        return edad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getFecha_nacimiento() throws ParseException {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");

        Date fecha = null;
        fecha = formatoDelTexto.parse(fecha_nacimiento);

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String fecha2 = formatter.format(fecha);

        return fecha2;
    }

    public String getFecha_nacimientoBD() throws ParseException {

        return fecha_nacimiento;
    }

    public String getActivo() {
        if (activo == true) {
            return "Si";

        } else {
            return "No";
        }

    }

    public Boolean getActivoBD() {
        return activo;

    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) throws ParseException {

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = null;
        fecha = formatter.parse(fecha_nacimiento);

        // System.out.println("set fecha que llega "+fecha);
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");

        String fecha2 = formatoDelTexto.format(fecha);

        System.out.println("fecha que se guarda" + fecha2);

        this.fecha_nacimiento = fecha2;
    }

    ////////////////
    public static ArrayList<Cliente> listado() throws SQLException, ClassNotFoundException, ParseException {

        ArrayList<Cliente> clientes = new ArrayList<>();

        Connection db = MySQL.conectar();
        try {

            String Query = "SELECT c.id,c.nombre, c.apellido, c.fecha_nacimiento, n.nombre as nacionalidad,c.activo,SPACE(30) AS edad "
                    + "FROM `" + tabla + "` c,`nacionalidades` n "
                    + "WHERE n.id=c.nacionalidad_id;";

            Statement st = db.createStatement();
            //java.sql.ResultSet resultSet;
            ResultSet resultado = st.executeQuery(Query);
            while (resultado.next()) {
                clientes.add(new Cliente(resultado.getInt("id"),
                        resultado.getString("nombre"),
                        resultado.getString("apellido"),
                        resultado.getString("fecha_nacimiento"),
                        resultado.getBoolean("activo"),
                        resultado.getString("nacionalidad")
                ));
            }

        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Error en la adquisición de datos");
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientes;
    }

    public static void guardar(Cliente cliente) throws SQLException, ClassNotFoundException, ParseException {
        try {
            String Query = "INSERT INTO `" + tabla + "` (nombre,apellido,fecha_nacimiento,nacionalidad_id,activo) VALUES(?, ?,?,?,?);";
            Connection db = MySQL.conectar();
            java.sql.PreparedStatement consulta;
            //PreparedStatement ;
            if (cliente.getId() == null) {
                consulta = db.prepareStatement(Query);
                consulta.setString(1, cliente.getNombre());
                consulta.setString(2, cliente.getApellido());
                consulta.setString(3, cliente.getFecha_nacimientoBD());
                consulta.setString(4, cliente.getNacionalidad());
                consulta.setBoolean(5, cliente.getActivoBD());

            } else {

                consulta = db.prepareStatement("UPDATE `" + tabla + "` SET nombre= ?, apellido= ?,fecha_nacimiento= ?, nacionalidad_id= ?,activo= ? WHERE id = ?;");
                consulta.setString(1, cliente.getNombre());
                consulta.setString(2, cliente.getApellido());
                consulta.setString(3, cliente.getFecha_nacimientoBD());
                consulta.setString(4, cliente.getNacionalidad());
                consulta.setBoolean(5, cliente.getActivoBD());
                consulta.setInt(6, cliente.getId());
            }
            consulta.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }

    public static Cliente buscar(int id) throws SQLException, ClassNotFoundException, ParseException {
        Cliente cliente = null;
        Connection db = MySQL.conectar();
        java.sql.PreparedStatement consulta;

        try {
            consulta = db.prepareStatement("SELECT c.nacionalidad_id,c.id,c.nombre, c.apellido, c.fecha_nacimiento, n.nombre as nacionalidad,c.activo,SPACE(30) AS edad FROM `" + tabla + "` c,`nacionalidades` n WHERE n.id=c.nacionalidad_id AND c.id=?;");
            consulta.setInt(1, id);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                cliente = new Cliente(id, resultado.getString("nombre"), resultado.getString("apellido"), resultado.getString("fecha_nacimiento"), resultado.getBoolean("activo"), resultado.getString("nacionalidad"));
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return cliente;
    }

    public static void eliminar(Cliente cliente, boolean estado) throws SQLException, ClassNotFoundException {
        Connection db = MySQL.conectar();
        java.sql.PreparedStatement consulta;
        try {
             consulta = db.prepareStatement("UPDATE `" + tabla + "` SET activo= ? WHERE id = ?;");
                
               
            consulta.setBoolean(1, estado);
            consulta.setInt(2, cliente.getId());
            consulta.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public String toString() {
        return "Cliente{" + "nombre=" + nombre +this.edad +this.getNacionalidad()+this.getActivo()+'}';
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException {
        Cliente a=new Cliente();
        //Cliente b = new Cliente(18, "nahir", "argañaraz", "1989-03-04", true, "4");
        
        a=buscar(3);
       System.out.println(a);
        eliminar(a, false);
         System.out.println(a);
//        
       // Cliente.eliminar(new Cliente(null,"sonia","cardenas",fecha,true,"18"));
        //b.setFecha_nacimiento("04/03/1989");
        //b.setEdad(b.getFecha_nacimiento());
//        Iterator<Cliente> it = Cliente.listado().iterator();
//            while(it.hasNext())
//                

        //System.out.println(b.getEdad());
    }

}
