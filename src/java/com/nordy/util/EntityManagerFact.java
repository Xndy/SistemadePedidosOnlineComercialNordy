/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nordy.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author PC-4
 */
 public class EntityManagerFact {
    
    private static EntityManagerFactory emf;
   
    
    public static EntityManager getEntityManager(){
        return Persistence.createEntityManagerFactory("ComercialNordyPU").createEntityManager();
    }
    
    
}
