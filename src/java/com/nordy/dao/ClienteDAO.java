/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nordy.dao;

import com.nordy.interfaces.IClienteDAO;
import com.nordy.dominio.Cliente;
import com.nordy.util.EntityManagerFact;import com.nordy.util.Generar_ID;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

import javax.persistence.EntityManager;

/**
 *
 * @author Dario 0985907468
 */
public class ClienteDAO implements IClienteDAO
{
    private EntityManager em;
    
    @Override
    public Cliente getClienteByNombre(String nombre) {
        em = EntityManagerFact.getEntityManager();
        em.getTransaction().begin();
        
        Query hql = em.createQuery("SELECT c FROM Cliente c WHERE c.nombre = :nombre ");
        hql.setParameter("nombre", nombre);   
        Cliente clienteSelected = (Cliente) hql.getSingleResult();
        
        return clienteSelected;
    }

    @Override
    public boolean loginControl(Cliente cliente) {
        em = EntityManagerFact.getEntityManager();
        em.getTransaction().begin();
        
        try {
            
                Query hql = em.createQuery("SELECT c FROM Cliente c WHERE c.nombre = :nombre AND c.contrasenia = :contrasenia");
                hql.setParameter("nombre",cliente.getNombre());
                hql.setParameter("contrasenia", cliente.getContrasenia());
                
                Cliente cli = (Cliente) hql.getSingleResult();
                
                if( cli!=null){
                    return true;
                }
                return false;
                
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void ingreso(Cliente c) {
        EntityManager emg = EntityManagerFact.getEntityManager();
        try{
            emg.getTransaction().begin();
            
            Cliente _cliente = buscarCliente(c.getCedula());
            System.out.println(_cliente + " " + _cliente.getCedula() );
            
            if(_cliente.getCedula()==null){
                c.setIdcliente(Generar_ID.idSerial("Cliente", "idcliente"));
                System.out.println(c.getEstado());
                
                emg.persist(c);
            }else{
                emg.merge(c);
            }
            
            emg.getTransaction().commit();
        }finally{
            emg.close();
        }
    }

   
    

    @Override
    public List<Cliente> ListaCliennte() {
        EntityManager emg = EntityManagerFact.getEntityManager();
        List<Cliente> listaC = new ArrayList<>();
        listaC = emg.createQuery("FROM Cliente c").getResultList();
        emg.close();
        return listaC;
    }

    @Override
    public List<Cliente> buscar(String c) {
        EntityManager emg = EntityManagerFact.getEntityManager();
        List<Cliente> clienteList = new ArrayList<>();
        try{
           Query hql = emg.createQuery("select c from Cliente c where c.nombre like :_nombre"); 
           hql.setParameter("_nombre", c+'%');
           clienteList = hql.getResultList();
        }finally{
            emg.close();
            return clienteList;
        }
    }

    @Override
    public Cliente buscarCliente(String cedula) {
       EntityManager emg = EntityManagerFact.getEntityManager();
        Cliente cli = new Cliente();
        try{
           Query val = emg.createQuery("Select c from Cliente c where c.cedula = :_ced"); 
           val.setParameter("_ced", cedula);
           cli = (Cliente) val.getSingleResult();
        }finally{
            emg.close();
            return cli;
        }
    }    
 
}
