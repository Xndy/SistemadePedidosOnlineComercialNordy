/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nordy.interfaces;

import com.nordy.dominio.Proforma;
import java.util.List;

/**
 *
 * @author Dario 0985907468
 */
public interface IPedidoDAO {
    
    public void addPedido(Proforma proforma);
    public List<Proforma> getProformaList();
    public Proforma getProformaID();
    public void updatePedido(Proforma proforma); 
    public Proforma findProforma(Proforma proforma);
    
    
}
