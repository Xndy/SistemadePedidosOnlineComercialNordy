/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nordy.interfaces;

import com.nordy.dominio.Categoria;
import com.nordy.dominio.Productos;
import java.util.List;

/**
 *
 * @author PC-4
 */
public interface IProductoDAO {
    
    public List<Productos> getListProductos();
    public List<Productos> getListProductos(Categoria categoria);
    public void ingreso(Productos p); 
    public void actualizar(Productos p);
    public List<Productos> lista_producto();
    public Productos buscar(int id);
    public List<Productos> buscarLike(String p);
    
}
