/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nordy.bean;

import com.nordy.dao.ClienteDAO;
import com.nordy.interfaces.IProductoDAO;
import com.nordy.dao.ProductoDAO;
import com.nordy.dominio.Categoria;
import com.nordy.dominio.Cliente;
import com.nordy.dominio.Productos;
import com.nordy.interfaces.IClienteDAO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import org.primefaces.component.menu.Menu;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;


/**
 *
 * @author PC-4
 */
@ManagedBean(name = "productoBean")
@ApplicationScoped
public class ProductoBean {

    
    private Productos  productos;
    private List<Productos> productosList;
    private IProductoDAO  productosDAO;
    private CategoriaBean categoriaBean;
    private MenuModel model;
    private int idcategoria;
    private String detallecategoria;
    
    private List<Productos>  listaProductos;
    private Categoria idCategoria;
    private Productos productoTmp;
    private String filtro = "";

    private Cliente cliente;
    private IClienteDAO iClienteDAO;
    
    private UsuarioLogeadoMB usMB = new UsuarioLogeadoMB();
    
    
    /**
     * Creates a new instance of ProdutoBean
     */
    
    public MenuModel getMenu(){
    model = new DefaultMenuModel();
        DefaultMenuItem item;
        
        //Menu 0
        DefaultSubMenu subMenu0 = new DefaultSubMenu("Inicio");
        item = new DefaultMenuItem("Quienes Somos");
        item.setOutcome("/templateQuienesSomos.xhtml");
        subMenu0.addElement(item);
        item = new DefaultMenuItem("Contactenos");
        item.setOutcome("/templateContactos.xhtml"); 
        subMenu0.addElement(item);
        model.addElement(subMenu0);        
        
        DefaultSubMenu subMenu1 = new DefaultSubMenu("Nuestros Productos");
        
         for (Categoria c : categoriaBean.getCategoriaList()){
            item = new DefaultMenuItem(c.getDescripcion());
            item.setParam("categoria", c.getIdcategoria());
            item.setParam("detalle", c.getDescripcion());
            item.setOutcome("/templateProductos.xhtml?faces-redirect=true");
            subMenu1.addElement(item);
 
        }
        model.addElement(subMenu1);
        
        DefaultSubMenu subMenu2 = new DefaultSubMenu("Carrito de Compras");
        item = new DefaultMenuItem("Solicite su Pedido !Ahora!");
        item.setOutcome("/templateCarrito.xhtml?faces-redirect=true"); 
        subMenu2.addElement(item);
   
        
        System.out.println("Tipo1 " + UsuarioLogeadoMB.tipo + " tipo2 " + usMB.getTipologeado() );
       // if (UsuarioLogeadoMB.tipo != "Administrador"){
        if (UsuarioLogeadoMB.tipo.equals( "Administrador")){
        
        item = new DefaultMenuItem("Ver su Factura Listado de Facturas");
        item.setOutcome("/templateProformasAdmin.xhtml?faces-redirect=true"); 
        subMenu2.addElement(item);  
        
         item = new DefaultMenuItem("Nuevos Registros ");
        item.setOutcome("/registros.xhtml?faces-redirect=true"); 
        subMenu2.addElement(item);
        }

        model.addElement(subMenu2);
        
        
        
        return model;
    }
  
      
    public List<Productos> getListProductos(){
        
        Categoria categoria = new Categoria();
        categoria.setIdcategoria(idcategoria);
        productosList = new ArrayList<Productos>();
        productosDAO = new ProductoDAO();
        this.productosList = productosDAO.getListProductos(categoria);
        
        return productosList;
    }
    
    public List<Productos> getListaProductos() {
        listaProductos  = new ArrayList<Productos>();
        if(this.filtro.equals("")){
            listaProductos = productosDAO.lista_producto();
        }else{
            listaProductos = productosDAO.buscarLike(filtro);
        }
        return listaProductos;
    }

    public void setListaProductos(List<Productos> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Categoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdcategoria(Categoria idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Productos getProductoTmp() {
        return productoTmp;
    }

    public void setProductoTmp(Productos productoTmp) {
        this.productoTmp = productoTmp;
    }
    
    

    public void modificar(RowEditEvent event){
        Productos productoModificar = (Productos) event.getObject();
        
        productoModificar.setTalla(productoTmp.getTalla());
        productoModificar.setColor(productoTmp.getColor());
        productoModificar.setCantidad(productoTmp.getCantidad());
        productoModificar.setDetalle(productoTmp.getDetalle());
        productoModificar.setPrecio(productoTmp.getPrecio());
        productosDAO.actualizar(productoModificar);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(" Registro Actualizado"));
    } 
    public void cancelar(RowEditEvent event){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Accion Cancelado"));
    }
    public void doGrabar(){
        productos.setCodcategoria(idCategoria);
        productosDAO.ingreso(productos);
    }


    
    
    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }
    
    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getDetallecategoria() {
        return detallecategoria;
    }

    public void setDetallecategoria(String detallecategoria) {
        this.detallecategoria = detallecategoria;
    }

    public ProductoBean() { 
        if (cliente == null && iClienteDAO == null&& productosDAO == null && productoTmp == null && productos == null && categoriaBean == null && idCategoria == null){
            productosDAO = new ProductoDAO();
            productos = new Productos();
            idCategoria = new Categoria();
            productoTmp = new Productos();
            categoriaBean = new CategoriaBean();

           }
    }
    
    
    

}
