package com.nordy.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;





/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Freddycito Salas
 */
public class testVal {

    
    public static boolean validarLetra(String cadena){
        
        for (int i = 0; i <cadena.length(); i++) {
            if((cadena.charAt(i) < 'a' || cadena.charAt(i) > 'z' ) && (cadena.charAt(i) < 'A' || cadena.charAt(i) > 'Z' ) 
                    &&(cadena.charAt(i) != 32)
                    &&(cadena.charAt(i) != 'ñ')
                    &&(cadena.charAt(i) != 'Ñ')) {
                return true;
            }
        }  
        return false;
    }
        
    public static boolean validarNumeroCedula(String cadena){
        
        if(!validarNumerosLongitud(cadena, 10, 10)){
           for (int i = 0; i <cadena.length(); i++) {
                if((cadena.charAt(i) < '0' || cadena.charAt(i) > '9' ) ) {

                    return true;
                }
            } 
        }
          
        return false;
    }
    
    
    
    public static boolean validarNumerosLongitud(String cadena,int max, int min){
        
        if((cadena.length() >= max)||(cadena.length() <= min)){
            return true;
        }     
        return false;
    }
    
    public static boolean validarNumeros(String cadena){
        
        for (int i = 0; i <cadena.length(); i++) {
                if((cadena.charAt(i) < '0' || cadena.charAt(i) > '9' ) ) {

                    return true;
                }
            }     
        return false;
    }
    
    public static boolean correo(String cadena){
        
        
        
        int contArroba = 0;
        
        for (int i = 0 ; i<cadena.length() ; i++){
            if(cadena.charAt(i)== '@' ){contArroba++; }
            
            if((cadena.charAt(i) < 'a' || cadena.charAt(i) > 'z' )
                    && (cadena.charAt(i) < '0' || cadena.charAt(i) > '9' )
                    && cadena.charAt(i) != 64
                    && cadena.charAt(i) != 46
                    && cadena.charAt(i) != 45
                    && cadena.charAt(i) != 95) {
                return true;
            }
        }
        if(contArroba != 1 )return true;
        
        
        
        int countPuntos = 0;
        String[] datos = cadena.split("@");
        for(int x = 0 ; x<datos[1].length() ; x++){
            if(datos[1].charAt(x) == '.'){countPuntos++;}
            if((datos[1].charAt(x) < 'a' || datos[1].charAt(x) > 'z' ) && datos[1].charAt(x) != 46) return true;
        }
        if (countPuntos < 1 || countPuntos > 2 ) return true;
        
        return false;
        
     }
    
}
