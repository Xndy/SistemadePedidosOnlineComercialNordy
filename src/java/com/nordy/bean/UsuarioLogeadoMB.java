/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nordy.bean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Freddycito Salas
 */
@ManagedBean(name = "usuarioLogeadoMB")
@ApplicationScoped
public class UsuarioLogeadoMB {

    public static String nombre;
    public static String tipo = "";
    
    private String nombrelogeado;
    private String tipologeado;

    public String getTipologeado() {
        tipologeado = tipo;
        return tipologeado;
    }

    public void setTipologeado(String tipologeado) {
        this.tipologeado = tipologeado;
    }
    
   

    public String getNombrelogeado() {
        nombrelogeado = nombre;
        return nombrelogeado;
    }

    public void setNombrelogeado(String nombrelogeado) {
        this.nombrelogeado = nombrelogeado;
    }
    
    
    /**
     * Creates a new instance of UsuarioLogeadoMB
     */
    public UsuarioLogeadoMB() {
        
        
        
    }
    
}
