/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nordy.bean;

import com.nordy.dao.ClienteDAO;
import com.nordy.dominio.Cliente;
import com.nordy.interfaces.IClienteDAO;
import com.nordy.util.UtilHttp;
import java.util.List;
import java.util.ArrayList;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Dario 0985907468
 */
@ManagedBean(name = "clienteBean")
public class ClienteBean {

    private Cliente cliente;
    private IClienteDAO iClienteDAO;
    private List<Cliente> listaCliente =  new ArrayList<>();
    
    private Cliente clienteTmp;
    
    private String filtro = "";         
    
    public String logincontrol(){
        
        if(iClienteDAO.loginControl(cliente) == true){
            HttpSession session = UtilHttp.getSession();
            session.setAttribute("username", cliente.getNombre());
            
            Cliente c = new Cliente();
            c = iClienteDAO.getClienteByNombre(cliente.getNombre());
            UsuarioLogeadoMB.nombre = cliente.getNombre();
            UsuarioLogeadoMB.tipo = c.getTipo();
            
            return "/template.xhtml?cliente="+ cliente.getNombre() + "&tipo="+ c.getTipo() + ""+"faces-redirect=true";
            
        }
        RequestContext.getCurrentInstance().update("grow1");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", "Contraseña o Usuario Incorrectos"));
        return "/templateInicioSesion.xhtml";
    }

    
 
    public String logout() {
        
      HttpSession session = UtilHttp.getSession();
      session.invalidate();
      UsuarioLogeadoMB.nombre = "";
      UsuarioLogeadoMB.tipo = "";
        
      return "/templateInicioSesion.xhtml?faces-redirect=true";
   }
    
    
    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }
    

    public Cliente getClienteTmp() {
        return clienteTmp;
    }

    public void setClienteTmp(Cliente clienteTmp) {
        this.clienteTmp = clienteTmp;
    }
    
    public List<Cliente> getListaCliente() {
        if(this.filtro.equals("")){
            listaCliente = iClienteDAO.ListaCliennte();
        }else{
            listaCliente = iClienteDAO.buscar(filtro);
        }
        return listaCliente;
    }

    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

   

    public void doGrabar(){
        iClienteDAO.ingreso(cliente);
    }
    
    public void actualizar(RowEditEvent event){
        Cliente clienteModificar = (Cliente) event.getObject();
        clienteModificar.setCedula(clienteTmp.getCedula());
        clienteModificar.setNombre(clienteTmp.getNombre());
        clienteModificar.setCelular(clienteTmp.getCelular());
        clienteModificar.setContrasenia(clienteTmp.getContrasenia());
        clienteModificar.setCorreo(clienteTmp.getCorreo());
        clienteModificar.setDireccion(clienteTmp.getDireccion());
        clienteModificar.setEstado(clienteTmp.getEstado());
        clienteModificar.setTipo(clienteTmp.getTipo());
        FacesMessage msg = new FacesMessage("Registro Actualizado:");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void cancelar(RowEditEvent event){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Accion Cancelado"));
    }

    
    /**
     * Creates a new instance of ClienteBean
     */
    public ClienteBean() {
        if(cliente == null && iClienteDAO == null){
            cliente = new  Cliente();
            iClienteDAO = new ClienteDAO();
        }
    }
    
}
