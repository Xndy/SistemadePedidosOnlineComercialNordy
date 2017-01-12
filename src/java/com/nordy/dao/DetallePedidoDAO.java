/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nordy.dao;

import com.nordy.interfaces.IDetallePedidoDAO;
import com.nordy.dominio.DetalleCompra;
import com.nordy.dominio.Proforma;
import com.nordy.util.EntityManagerFact;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author XndyPxndx
 */
public class DetallePedidoDAO implements IDetallePedidoDAO{

    private EntityManager em;
    
    
    @Override
    public void addDetallePedido(List<DetalleCompra> detallePedidoList, Proforma proforma) {
        em = EntityManagerFact.getEntityManager();
        em.getTransaction().begin();
        for(DetalleCompra dc : detallePedidoList){
            dc.setIdproforma(proforma);
            System.out.println(dc);
            em.persist(dc);
        }
        em.getTransaction().commit();
    }

    @Override
    public List<DetalleCompra> getDetalleCompraList() {
        em = EntityManagerFact.getEntityManager();
        em.getTransaction().begin();
        Query hql = em.createQuery("SELECT dc FROM DetalleCompra dc");
        List<DetalleCompra> detalleComprasList = hql.getResultList();
        em.getTransaction().commit();
        return detalleComprasList;
    }

    @Override
    public List<DetalleCompra> getDetalleCompraListByProforma(Proforma proforma) {
        em = EntityManagerFact.getEntityManager();
        em.getTransaction().begin();
        Query hql = em.createQuery("SELECT dc FROM DetalleCompra dc WHERE dc.idproforma = :idproforma");
        hql.setParameter("idproforma", proforma );
        List<DetalleCompra> detalleComprasList = hql.getResultList();
        em.getTransaction().commit();
        return detalleComprasList;
    }

    @Override
    public void deleteDetalle(DetalleCompra detalleCompra) {
        DetalleCompra detCompra = em.find(DetalleCompra.class, detalleCompra.getIddetalle());
        em.getTransaction().begin();
        em.remove(detCompra);
        em.getTransaction().commit();
    }

    @Override
    public void updateIncrementDetalle(DetalleCompra detalleCompra) {
        DetalleCompra detCompra = em.find(DetalleCompra.class, detalleCompra.getIddetalle());
        em.getTransaction().begin();
        em.merge(detCompra);
        em.getTransaction().commit();
    
    }
    
    @Override
    public void updateDeacreseDetalle(DetalleCompra detalleCompra) {
        DetalleCompra detCompra = em.find(DetalleCompra.class, detalleCompra.getIddetalle());
        em.getTransaction().begin();
        em.merge(detCompra);
        em.getTransaction().commit();
    }
    
    
    
}
