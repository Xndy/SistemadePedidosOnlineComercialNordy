/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nordy.bean.dialog;

import com.nordy.bean.mail.ContactenosBean;
import com.nordy.dao.ClienteDAO;
import com.nordy.dao.DetallePedidoDAO;
import com.nordy.dao.PedidoDAO;
import com.nordy.dominio.Cliente;
import com.nordy.dominio.DetalleCompra;
import com.nordy.dominio.Proforma;
import com.nordy.interfaces.IClienteDAO;
import com.nordy.interfaces.IDetallePedidoDAO;
import com.nordy.interfaces.IPedidoDAO;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Dario 0985907468
 */
@ManagedBean(name = "pedidoDialogMB")
@SessionScoped
public class PedidoDialogMB implements Serializable {

    
    private List<Proforma> proformaList;
    private Proforma proforma;
    private IPedidoDAO iPedidoDAO;
    
    private List<DetalleCompra> detalleProformaListbyProforma;
    private IDetallePedidoDAO iDetallePedidoDAO;
    
    private Cliente cliente;
    private IClienteDAO iClienteDAO; 
    
    private ContactenosBean contactenosBean;
    
    public void pedidoDialog(Proforma p) {
        
        this.proforma = getProformaByCliente(p);
        
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        RequestContext.getCurrentInstance().openDialog("mntproformaporcliente.xhtml", options, null);
        
        
    }
    
     public List<Proforma> getProformaList(){
        proformaList = new ArrayList<>();
        proformaList = iPedidoDAO.getProformaList();
        return proformaList;
    }
    
    public Proforma getProformaByCliente(Proforma p){
        Proforma pr = iPedidoDAO.findProforma(p);
        this.detalleProformaListbyProforma = getDetalleProformaByProforma(p);
        return pr;
    }
     
    public List<DetalleCompra> getDetalleProformaByProforma(Proforma p){
        List<DetalleCompra> detalleProforma = new ArrayList<DetalleCompra>();
        detalleProforma = iDetallePedidoDAO.getDetalleCompraListByProforma(p);
        return detalleProforma;
    }
    
    public void pedidoSeleccionado(Cliente c) {
        RequestContext.getCurrentInstance().closeDialog(c);
    }

    public String updateAprobado(Proforma p){
        p.setEstado(true);
        iPedidoDAO.updatePedido(p);
        
        String nombrecliente = p.getCodcliente().getNombre();
        Cliente c = iClienteDAO.getClienteByNombre(nombrecliente);
        contactenosBean.sendEmailUpdatePedido(c.getCorreo());
        
        RequestContext.getCurrentInstance().update("grow1");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aprobado", "El pedido fue aprobado satisfactoriamente"));
        
        
        
        return "/templateProformasAdmin.xhtml?faces-redirect=true";
    }
    
    public Proforma getProforma() {
        return proforma;
    }

    public void setProforma(Proforma proforma) {
        this.proforma = proforma;
    }

    public List<DetalleCompra> getDetalleProformaListbyProforma() {
        return detalleProformaListbyProforma;
    }

    

    public void setDetalleProformaListbyProforma(List<DetalleCompra> detalleProformaListbyProforma) {
        this.detalleProformaListbyProforma = detalleProformaListbyProforma;
    }
    
    
    
    /**
     * Creates a new instance of PedidoDialogMB
     */
    public PedidoDialogMB() {
        if(contactenosBean == null && iClienteDAO == null && cliente == null && detalleProformaListbyProforma == null && proforma == null && iPedidoDAO == null&& iDetallePedidoDAO == null){
            proforma = new Proforma();
            iPedidoDAO = new PedidoDAO();
            iDetallePedidoDAO = new  DetallePedidoDAO();
            detalleProformaListbyProforma =  new ArrayList<DetalleCompra>();
            cliente = new Cliente();
            iClienteDAO = new ClienteDAO(); 
            contactenosBean = new ContactenosBean();
            
        }
    }
    
}
