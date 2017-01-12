/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nordy.bean;

import com.nordy.dao.DetallePedidoDAO;
import com.nordy.interfaces.IDetallePedidoDAO;
import com.nordy.interfaces.IPedidoDAO;
import com.nordy.dao.PedidoDAO;
import com.nordy.dominio.DetalleCompra;
import com.nordy.dominio.Productos;
import com.nordy.dominio.Proforma;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author xxxxx
 */
@ManagedBean(name = "detallePedidoBean")
@ApplicationScoped
public class DetallePedidoBean {

    private DetalleCompra detalleCompra;
    private IDetallePedidoDAO iDetallePedidoDAO;
    private List<DetalleCompra> detalleComprasList;
    private PedidoBean pedidoBean = new PedidoBean();
    private IPedidoDAO iPedidoDAO;
    
    private Double precioTotal;
    private int cantidadtemp;
    
    public List<DetalleCompra> getDetalleCompraList(){
        detalleComprasList = new ArrayList<DetalleCompra>();
        Proforma proformaID = iPedidoDAO.getProformaID();
        detalleComprasList = iDetallePedidoDAO.getDetalleCompraListByProforma(proformaID);
        return detalleComprasList;
    }

    public void deleteDetalle(DetalleCompra detalle){
        iDetallePedidoDAO.deleteDetalle(detalle);
    }
    
    public String updateIncrementCantidad(DetalleCompra detalle){
        cantidadtemp = detalle.getCantidad()+1;
        precioTotal = calculoPrecioTotal(detalle, cantidadtemp);
        detalle.setPrecioTotal(precioTotal);
        detalle.setCantidad(detalle.getCantidad()+1);
        iDetallePedidoDAO.updateIncrementDetalle(detalle);
         pedidoBean.updaTotalPedido();
       return "templateProformaCliente.xhtml?faces-redirect=true";
    }
    
    public String updateDeacreaseCantidad(DetalleCompra detalle){
        cantidadtemp = detalle.getCantidad()-1;
        precioTotal = calculoPrecioTotal(detalle, cantidadtemp);
        detalle.setPrecioTotal(precioTotal);
        detalle.setCantidad(detalle.getCantidad()-1);
        iDetallePedidoDAO.updateDeacreseDetalle(detalle);
        
        pedidoBean.updaTotalPedido();
         return "templateProformaCliente.xhtml?faces-redirect=true";
    }
    
    
    
    public Double calculoPrecioTotal(DetalleCompra dc, int cantidadtemp){
        //esta variable se quedara en memoria 
        Double tempPrecioTotal = 0.0;
        tempPrecioTotal = cantidadtemp * dc.getPrecioUnitario();
        return tempPrecioTotal;
    }
    
    public DetalleCompra getDetalleCompra() {
        return detalleCompra;
    }

    public void setDetalleCompra(DetalleCompra detalleCompra) {
        this.detalleCompra = detalleCompra;
    }
    
    
    
    /**
     * Creates a new instance of DetallePedidoBean
     */
    public DetallePedidoBean() {
        if(detalleCompra == null && iDetallePedidoDAO == null && iPedidoDAO == null ){
            detalleCompra = new DetalleCompra();
            iDetallePedidoDAO = new DetallePedidoDAO();
            iPedidoDAO = new PedidoDAO();
        }
    }
    
}
