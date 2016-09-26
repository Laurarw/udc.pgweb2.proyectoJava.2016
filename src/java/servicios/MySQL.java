/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

//import com.mysql.jdbc.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;







import javax.swing.JOptionPane;

/**
 *
 * @author usuario
 */
public class MySQL {
    
    private static Connection Conexion=null;
    
    public static Connection conectar() throws SQLException, ClassNotFoundException {
      if (Conexion == null) {
         try {
            Class.forName("com.mysql.jdbc.Driver");
            Conexion = DriverManager.getConnection("jdbc:mysql://localhost/BD_2", "root", "123456");
         } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
      return Conexion;
   }
    
//    public void MySQLConnection(String user, String pass, String db_name) throws Exception {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db_name, user, pass);
//            JOptionPane.showMessageDialog(null, "Se ha iniciado la conexión con el servidor de forma exitosa");
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public static void cerrar() throws SQLException {
      if (Conexion != null) {
         Conexion.close();
      }
   }
    
//    public void closeConnection() {
//        try {
//            Conexion.close();
//            JOptionPane.showMessageDialog(null, "Se ha finalizado la conexión con el servidor");
//        } catch (SQLException ex) {
//            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public void getValues(String table_name) {
        try {
            String Query = "SELECT * FROM " + table_name;
            Statement st = Conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
 
            while (resultSet.next()) {
                System.out.println("Nombre: " + resultSet.getString("nombre") + " " + resultSet.getString("apellido"));
            }
 
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la adquisición de datos");
        }
    }
     public static void main(String[] args) {
        //MySQL db = new MySQL();
        try {
            Connection db=  MySQL.conectar();
            //db.getValues("clientes");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

