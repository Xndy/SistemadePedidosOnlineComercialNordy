
package com.nordy.bean.dialog;

import com.nordy.dao.ProductoDAO;
import com.nordy.dominio.Categoria;
import com.nordy.dominio.Productos;
import com.nordy.interfaces.IProductoDAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
@ManagedBean
public class ProductoDialogMB {
    private Productos producto;
    IProductoDAO productoDAO;
    private List<Productos>  listaProductos;
    private String idcategoria ;
   
   public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public List<Productos> getListaProductos() {
        listaProductos = productoDAO.lista_producto();
        return listaProductos;
    }

    public void setListaProductos(List<Productos> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public String getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(String idcategoria) {
        this.idcategoria = idcategoria;
    }


    public ProductoDialogMB() {
        if(producto == null && productoDAO == null && listaProductos == null){
            producto = new Productos();
            productoDAO = new ProductoDAO();
            listaProductos = new ArrayList<>();
        }
    }
    
    public void doGrabar(){
        Categoria cat= new Categoria(Integer.parseInt(idcategoria));
        producto.setCodcategoria(cat);
        productoDAO.ingreso(producto);
        
    }
    
    public void productoDialog(){
         System.err.println("EY x aqui Ando. . . SI ENTRE");
        Map<String, Object> opcions = new HashMap<String, Object>();
        opcions.put("modal", true);
        opcions.put("draggable", true);
        opcions.put("resizable", false);
        opcions.put("contentHeigh", 350);
        RequestContext.getCurrentInstance().openDialog("mntProductoDialog.xhtml", opcions, null);
    }
     
    public void cargarProducto(SelectEvent event){
        producto = (Productos)event.getObject();
    }
    public void productoSeleccinado(Productos p){
        RequestContext.getCurrentInstance().closeDialog(p);
    }
}
