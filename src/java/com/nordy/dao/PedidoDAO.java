/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nordy.dao;

import com.nordy.interfaces.IPedidoDAO;
import com.nordy.dominio.Proforma;
import com.nordy.util.EntityManagerFact;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Dario 0985907468
 */
public class PedidoDAO implements IPedidoDAO{

    private EntityManager em;
    
    
    @Override
    public void addPedido(Proforma proforma) {
        em = EntityManagerFact.getEntityManager();
        em.getTransaction().begin();
        em.persist(proforma);
        em.getTransaction().commit();
        em.close();
        
    }

    @Override
    public List<Proforma> getProformaList() {
        em = EntityManagerFact.getEntityManager();
        em.getTransaction().begin();
        
        Query hql = em.createQuery("SELECT p from Proforma p ORDER BY p.idproforma DESC");
        List<Proforma> proformasList = hql.getResultList();
        
        return proformasList;
    
    }

    @Override
    public Proforma getProformaID() {
        em = EntityManagerFact.getEntityManager();
        em.getTransaction().begin();
        
        Query hql = em.createQuery("SELECT p from Proforma p ORDER BY p.idproforma DESC");
        hql.setMaxResults(1);
        Proforma proforma =  (Proforma) hql.getSingleResult();
        
        return proforma;
    }
    
    @Override
    public void updatePedido(Proforma proforma){
        
        em = EntityManagerFact.getEntityManager();
        Proforma p = em.find(Proforma.class, proforma.getIdproforma());
        em.getTransaction().begin();
        em.merge(proforma);
        em.getTransaction().commit();
        
    } 

    @Override
    public Proforma findProforma(Proforma proforma) {
        em = EntityManagerFact.getEntityManager();
        em.getTransaction().begin();
        Query hql = em.createQuery("SELECT p FROM Proforma p WHERE p.idproforma = :idproforma");
        hql.setParameter("idproforma", proforma.getIdproforma());
        Proforma p =  (Proforma) hql.getSingleResult();
        return p;
    }
    
}
