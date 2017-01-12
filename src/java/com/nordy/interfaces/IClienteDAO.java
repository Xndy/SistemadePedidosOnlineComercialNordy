/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nordy.interfaces;

import com.nordy.dominio.Cliente;
import java.util.List;

/**
 *
 * @author Dario 0985907468
 */
public interface IClienteDAO {
    

    public boolean  loginControl (Cliente cliente);
    public Cliente getClienteByNombre(String nombre);
    public void ingreso(Cliente c); 
    public Cliente buscarCliente(String cedula);
    public List<Cliente> ListaCliennte();
    public List<Cliente> buscar(String cliente);

}
