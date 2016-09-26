/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

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
    private Aplicacion app;
    
    
}
