/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nordy.bean;

import com.nordy.dao.ClienteDAO;
import com.nordy.dao.DetallePedidoDAO;
import com.nordy.interfaces.IDetallePedidoDAO;
import com.nordy.interfaces.IPedidoDAO;
import com.nordy.dao.PedidoDAO;
import com.nordy.dominio.Cliente;
import com.nordy.dominio.DetalleCompra;
import com.nordy.dominio.Productos;
import com.nordy.dominio.Proforma;
import com.nordy.interfaces.IClienteDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author xxxxx
 */
@ManagedBean(name = "pedidoBean")
@ApplicationScoped
public class PedidoBean {
    //Variables de PedidoCabecera:
    private Date fecha;
    private Proforma proforma;
    private Cliente cliente;
    private IPedidoDAO iPedidoDAO;
    private IClienteDAO iClienteDAO; 
    
    //busqueda del IDDELCLIENTE por medio del Nombre
    private String nombre;
    //Variables de Detalle del Producto:
    private DetalleCompra detalleCompra;
    private Productos producto;
    private IDetallePedidoDAO iDetallePedidoDAO;
    private List<DetalleCompra> detalleComprasList = new ArrayList<DetalleCompra>();
    private Double precioTotalDetalleProducto;
    private Double precioTotalFactura;
 
     
    private Double iva = 0.14;

    public Proforma getProforma() {
        this.proforma = iPedidoDAO.getProformaID();
        return proforma;
    }

    public void savePedido(){
        proforma.setFechaentrega(fecha);
        proforma.setCodcliente(getCliente());
        proforma.setSubtotal(calculoTotalFactura());
        proforma.setDescuento(0.0);
        proforma.setIva(iva);
        proforma.setEstado(false);
        iPedidoDAO.addPedido(proforma);
        //Retorna el ID de la Proforma Guardada :)
        Proforma proformaID = iPedidoDAO.getProformaID();
        //Guarda el detalle de la proforma :S
        iDetallePedidoDAO.addDetallePedido(detalleComprasList, proformaID);
        detalleComprasList.clear();
    }
   
    
    //Va cargando el detalle en memoria :)
    public void almacenandoDetalle(Productos p){
        DetalleCompra temp = new DetalleCompra();
        precioTotalDetalleProducto = calculoPrecioTotal(p, detalleCompra);
        temp.setPrecioTotal(precioTotalDetalleProducto);
        temp.setPrecioUnitario(p.getPrecio());
        temp.setCantidad(detalleCompra.getCantidad());
        temp.setCodproductos(p);
        this.detalleComprasList.add(temp);
    }
    
    //Calcula el precio total del producto
    private Double calculoPrecioTotal(Productos p, DetalleCompra dc){
        //esta variable se quedara en memoria 
        Double tempPrecioTotal = 0.0;
        tempPrecioTotal = p.getPrecio() * dc.getCantidad();
        return tempPrecioTotal;
    }

    //calcula el total de la factura
    private Double calculoTotalFactura(){
        Double totalFacturaTemp = 0.0;
        for( DetalleCompra dc: detalleComprasList){
            totalFacturaTemp += dc.getPrecioTotal();
        }
        totalFacturaTemp = totalFacturaTemp + (iva * totalFacturaTemp);
        return totalFacturaTemp;
    }
    
    
    //Actualiza el Total despues de Elimiaar, Incrementar o Restar una cantidad en el valor del detalle
    public void updaTotalPedido(){
        //Obtine el iddelultimo pedido para traer todas los detalles
        // relacionasdos a ese id y asi los sumara para finalmente actualizar su total 
        Double nuevoTotal = 0.0; 
        Proforma p = iPedidoDAO.getProformaID();
        List<DetalleCompra> detalleCompraList = iDetallePedidoDAO.getDetalleCompraListByProforma(p);
        for(DetalleCompra dc : detalleCompraList){
            nuevoTotal = nuevoTotal + dc.getPrecioTotal();
        }
        nuevoTotal = nuevoTotal + ( nuevoTotal * iva);
        p.setSubtotal(nuevoTotal);
        iPedidoDAO.updatePedido(p);
    }
    
 
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        UsuarioLogeadoMB usMB = new UsuarioLogeadoMB();
        cliente = iClienteDAO.getClienteByNombre(usMB.getNombrelogeado());
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public DetalleCompra getDetalleCompra() {
        return detalleCompra;
    }

    public void setDetalleCompra(DetalleCompra detalleCompra) {
        this.detalleCompra = detalleCompra;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public List<DetalleCompra> getDetalleComprasList() {
        return detalleComprasList;
    }

    public void setDetalleComprasList(List<DetalleCompra> detalleComprasList) {
        this.detalleComprasList = detalleComprasList;
    }

    public void setProforma(Proforma proforma) {
        this.proforma = proforma;
    }
    

    /**
     * Creates a new instance of PedidoBean
     */
    public PedidoBean() {
        if(cliente == null && proforma == null && iPedidoDAO == null && fecha == null &&
          detalleCompra == null && producto == null&& iDetallePedidoDAO == null && iClienteDAO == null){
            
            detalleCompra = new DetalleCompra();
            producto = new Productos();
            iDetallePedidoDAO = new DetallePedidoDAO();
            cliente = new Cliente();
            proforma = new Proforma();
            iPedidoDAO = new PedidoDAO();
            fecha = new Date();
            iClienteDAO = new ClienteDAO();
            
        }
    }
    
}
