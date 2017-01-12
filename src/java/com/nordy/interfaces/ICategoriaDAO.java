/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nordy.interfaces;

import com.nordy.dominio.Categoria;
import java.util.List;

/**
 *
 * @author PC-4
 */
public interface ICategoriaDAO {
    
    public List<Categoria> getListCategoria();
    public void ingreso(Categoria cat); 
    public void actualizar(Categoria cat);
    public List<Categoria> buscar(String des);
}
