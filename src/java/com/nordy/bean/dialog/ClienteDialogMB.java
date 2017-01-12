/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nordy.bean.dialog;


import com.nordy.dominio.Cliente;
import com.nordy.dao.ClienteDAO;
import com.nordy.interfaces.IClienteDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author xxxxx
 */
@ManagedBean(name = "clienteDialogMB")
@ViewScoped
public class ClienteDialogMB  implements Serializable{
    
    private Cliente cliente;
    private IClienteDAO iClienteDAO;
    private List<Cliente> clienteList;

    public List<Cliente> getClienteList() {
       clienteList = iClienteDAO.ListaCliennte();     
        return clienteList;
    }
    
    
    public void clientesDialog() {
        System.out.println("hgfdsgfdsddfdfdffndgtfrdtrdfgh");
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentHeigh", 350);
        RequestContext.getCurrentInstance().openDialog("mntclienteDialog.xhtml?faces-redirect=true", options, null);
    }
    
    
     public void clienteSeleccionado(Cliente c) {
        
        RequestContext.getCurrentInstance().closeDialog(c);
        System.out.println(c.getEstado());
       
    }
     
      public void cargarCliente(SelectEvent event) {
        cliente = (Cliente) event.getObject();
      }
    
    
    public Cliente getCliente() {
        return cliente;
    }
    

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    
    
    public ClienteDialogMB() {
        if(cliente == null && iClienteDAO==null && clienteList== null){
            cliente = new Cliente();
            iClienteDAO= new ClienteDAO();
            clienteList= new ArrayList<Cliente>();
        }
    }
    
       public void doGrabar(){
        iClienteDAO.ingreso(cliente);
        //FacesContext.getCurrentInstance().addMessage("Cliente ingresado", new FacesMessage());
        cliente = null;
      }
}
