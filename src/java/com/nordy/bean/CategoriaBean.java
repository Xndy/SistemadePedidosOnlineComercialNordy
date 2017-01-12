/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nordy.bean;

import com.nordy.dao.CategoriaDAO;
import com.nordy.interfaces.ICategoriaDAO;
import com.nordy.interfaces.IProductoDAO;
import com.nordy.dao.ProductoDAO;
import com.nordy.dominio.Categoria;
import com.nordy.dominio.Productos;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author PC-4
 */
@ManagedBean(name = "categoriaBean")
@ApplicationScoped
public class CategoriaBean {

    private Categoria categoria;
    private List<Categoria> categoriaList;
    private ICategoriaDAO iCategoriaDAO;
    private int idcategoria;
    
    private List<Productos> productosList;
    private IProductoDAO  productosDAO;
    
    private String filtro = "";
    
    private List<Categoria> listaCategoria = new ArrayList<>();
    ArrayList<SelectItem> cbCategoria = new ArrayList<>();
    
    private String des;

    public ArrayList<SelectItem> getCbCategoria() {
        cbCategoria.clear();
            for(Categoria tmp:iCategoriaDAO.getListCategoria()){
                SelectItem matItem = new SelectItem(tmp.getIdcategoria(), tmp.getDescripcion());
                cbCategoria.add(matItem);
            }
        return cbCategoria;
    }
    
    
    
    public List<Categoria> getCategoriaList(){
        categoriaList = new ArrayList<Categoria>();
        categoriaList = iCategoriaDAO.getListCategoria();
        return categoriaList;
    }
    
    public void cargarProductosParaCarrito(AjaxBehaviorEvent evento ){
        Categoria categoria = new Categoria();
        categoria.setIdcategoria(idcategoria);
        productosList = new ArrayList<Productos>();
        this.productosList = productosDAO.getListProductos(categoria);
 
    }
    
    public List<Categoria> getListaCategoria() {
        if(this.filtro.equals("")){
            listaCategoria = iCategoriaDAO.getListCategoria();
        }else{
            listaCategoria = iCategoriaDAO.buscar(filtro);
        }
        return listaCategoria;
    }

    public void doGrabar(){
        iCategoriaDAO.ingreso(categoria);
    }
    
    public void actualizar(RowEditEvent event){
        Categoria a_categoria = ((Categoria)event.getObject());
        a_categoria.setDescripcion(des);
        iCategoriaDAO.actualizar(a_categoria);
        
        
         FacesMessage msg = new FacesMessage("Registro Actualizado:", des);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        des = "";
    }
    public void cancelar(RowEditEvent event){
        FacesContext.getCurrentInstance().addMessage("Accion Cancelado", new FacesMessage());
    }
    
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public void setProductosList(List<Productos> productosList) {
        this.productosList = productosList;
    }

    public List<Productos> getProductosList() {
        return productosList;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }
    
    public void setListaCategoria(List<Categoria> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
    
    
    /**
     * Creates a new instance of CategoriaBean
     */
    public CategoriaBean() {
        if (iCategoriaDAO==null && categoria == null && productosDAO == null) {
            iCategoriaDAO= new  CategoriaDAO();
            productosDAO = new ProductoDAO();
            categoria=new Categoria();
        }
        
    }
    
}
