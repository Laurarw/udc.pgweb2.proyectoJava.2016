/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author usuario
 */
public class Validacion {
    
    public static String soloLetras(String valor){
       
        boolean algunDigito = false;
        boolean algunaLetra = false;
        String mje=null;
        
        if(!vacio(valor)){
            mje= "Debe ingresar un valor";
        }
        
        for (int i = 0; i < valor.length(); i++) {
            if (Character.isDigit(valor.charAt(i))) {
                //es un digito
                algunDigito = true;
            } else {
                algunaLetra = true;
                //no es un digito
            }
        }
        
        if(algunDigito){
            mje= "No se permiten digitos";
            
        }
        return mje;
    }
    
    public static String validarFecha(String fecha){
        String mje=null;
        if(vacio(fecha)==false){
            mje="El Campo es obligatorio";
            return mje;
        }
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            mje="La fecha no es valida. Revisar";
        }
        
        return mje;
        
    }
    public static Boolean vacio(String valor){
        if(valor.length()<=0){
            return false;
        }
        return true;
    }
    
    public static String esMayorEdad(String fecha) throws ParseException{
        String mje=null;
        if(vacio(fecha)==false){
            return "Debe ingresar una fecha";
        }
         DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
         Date fecha_nac = null;
        fecha_nac=formatter.parse(fecha);
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
       if(año<18){
           mje="Es menor de edad";
       }
       return mje;
    }
    public static void main(String[] args) throws ParseException {
       if(Validacion.validarFecha("12/02989").equals("")){
          System.out.println( Validacion.esMayorEdad("12/02/2012"));
       }
        
    }
}
