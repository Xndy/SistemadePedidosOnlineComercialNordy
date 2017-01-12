/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nordy.dao;

import com.nordy.interfaces.ICategoriaDAO;
import com.nordy.dominio.Categoria;
import com.nordy.util.EntityManagerFact;
import com.nordy.util.Generar_ID;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author PC-4
 */
public class CategoriaDAO implements ICategoriaDAO{

@Override
    public void ingreso(Categoria cat) {
        EntityManager em = EntityManagerFact.getEntityManager();
        try{
            em.getTransaction().begin();
            cat.setIdcategoria(Generar_ID.idSerial("Categoria", "idcategoria"));
            em.persist(cat);
            em.getTransaction().commit();
        }finally{
            em.close();
       }
    }

    @Override
    public void actualizar(Categoria cat) {
        EntityManager em = EntityManagerFact.getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(cat);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
    }

    @Override
    public List<Categoria> buscar(String des) {
        EntityManager em = EntityManagerFact.getEntityManager();
        List<Categoria> categoriasList = new ArrayList<>();
        try{
            Query hql = em.createQuery("select cat from Categoria cat where cat.descripcion like :_des");
            hql.setParameter("_des", des + "%");
            categoriasList = hql.getResultList();
        }finally{
            em.close();
            return categoriasList; 
        }
    }
    
    
   

    @Override
    public List<Categoria> getListCategoria() {
       EntityManager em = EntityManagerFact.getEntityManager();
        em.getTransaction().begin();
        
        Query hql = em.createQuery("SELECT c FROM Categoria c");
        List<Categoria> categoriaList = hql.getResultList();
        return categoriaList;
    }
    
}
