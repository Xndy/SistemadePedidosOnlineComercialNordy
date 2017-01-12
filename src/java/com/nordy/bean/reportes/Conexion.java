/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nordy.bean.reportes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author xxxxx
 */
public class Conexion {
	
	 public static Connection getConnection(){
	        Connection cn=null;
	        
	        try {
	            Class.forName("org.postgresql.Driver");
	        } catch (ClassNotFoundException e) {
	                System.out.println("Driver no encontrado");
	                System.out.println(e.getMessage());
	        }
	        try {
	            cn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/ComercialNordy"
	                    ,"postgres","1234");
	        } catch (SQLException e) {
	            System.out.println("Error de conexion !!");
	            System.out.println(e.getMessage());
	            
	        }
	        
	        return cn;
	        
	    }
	    public static void probarConexion(){
	        Connection cn=Conexion.getConnection();
	        if (cn !=null){
	            System.out.println("Conexion ok");
	        }
	        else{
	            System.out.println("Conexion no establecida");
	        }
	    }
}
