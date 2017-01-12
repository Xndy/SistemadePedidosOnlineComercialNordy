/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nordy.interfaces;

import com.nordy.dominio.DetalleCompra;
import com.nordy.dominio.Proforma;
import java.util.List;

/**
 *
 * @author XndyPxndx
 */
public interface IDetallePedidoDAO {
    
    public void addDetallePedido(List<DetalleCompra> detallePedidoList, Proforma proforma);
    public List<DetalleCompra> getDetalleCompraList();
    public List<DetalleCompra> getDetalleCompraListByProforma(Proforma proforma);
    public void deleteDetalle(DetalleCompra detalleCompra);
    public void updateIncrementDetalle(DetalleCompra detalleCompra);
    public void updateDeacreseDetalle(DetalleCompra detalleCompra);
    
}
