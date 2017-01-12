/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nordy.util;

import javax.persistence.EntityManager;

/**
 *
 * @author Freddycito Salas
 */
public class Generar_ID {

 public static int idSerial(String clase,String id) {
        EntityManager em = EntityManagerFact.getEntityManager();
        int _id = 0;
        
        Object obj = em.createQuery("SELECT MAX(c."+id+") FROM "+ clase +" c").getSingleResult();
        if (obj == null){
            _id = 0;
        }else{
            _id = Integer.parseInt(obj+"") ;
        }
            return _id + 1 ;
     
    }
    
}
