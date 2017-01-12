/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nordy.dao;

import com.nordy.dominio.Categoria;
import com.nordy.interfaces.IProductoDAO;
import com.nordy.dominio.Productos;
import com.nordy.util.EntityManagerFact;
import com.nordy.util.Generar_ID;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author PC-4
 */
public class ProductoDAO implements IProductoDAO{

     
    
    @Override
    public void ingreso(Productos p) {
        EntityManager em = EntityManagerFact.getEntityManager();
        try {
            em.getTransaction().begin();
            p.setIdproductos(Generar_ID.idSerial("Productos", "idproductos"));
            em.persist(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void actualizar(Productos p) {
        EntityManager em = EntityManagerFact.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Productos> lista_producto() {
        List<Productos> listaP = new ArrayList<>();
        EntityManager em = EntityManagerFact.getEntityManager();
        try{
            Query hql = em.createQuery("from Productos p");
            listaP =hql.getResultList();
        }finally{
            em.close();
            return listaP;
        }
    }

    @Override
    public Productos buscar(int id) {
        EntityManager em = EntityManagerFact.getEntityManager();
        Productos productos = null;
        try{
            Query hql = em.createQuery("SELECT P FROM Productos p where p.idproductos =:_id");
            hql.setParameter("_id", id);
            productos = (Productos) hql.getSingleResult();
        }finally{
            em.close();
            return productos;
        }
    }

    @Override
    public List<Productos> buscarLike(String p) {
        EntityManager em = EntityManagerFact.getEntityManager();
        List<Productos> productosList = new ArrayList<>();
        try{
          Query hql = em.createQuery("select p from Productos p where p.categoria like :_categoria");
          productosList = (hql.setParameter("_categoria", p+'%')).getResultList();
          em.close();
          return productosList;
        }finally{
        }
    }

    @Override
    public List<Productos> getListProductos(Categoria categoria) {
        EntityManager em = EntityManagerFact.getEntityManager();
        em.getTransaction().begin();
        
        Query hql = em.createQuery("SELECT p FROM Productos p WHERE p.codcategoria = :codcategoria");
        hql.setParameter("codcategoria", categoria);
        List<Productos> productoList = hql.getResultList();
        return productoList;
    }

    @Override
    public List<Productos> getListProductos() {
        EntityManager em = EntityManagerFact.getEntityManager();
        em.getTransaction().begin();
        Query hql = em.createQuery("SELECT p FROM Productos p");
        List<Productos> productoList = hql.getResultList();
        return productoList;
    }
    
}
