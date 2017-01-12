/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nordy.bean.reportes;


import com.nordy.bean.mail.ContactenosBean;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.primefaces.context.RequestContext;


/**
 *
 * @author xxxxx
 */
@ManagedBean(name = "pedidoReportBean")
public class PedidoReportBean {

    private ContactenosBean contactenosBean = new ContactenosBean();
    
    public void exportarPDF() throws JRException, IOException, SQLException{
        contactenosBean.sendEmail();
        
        Connection cnn = Conexion.getConnection();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/pedidoNordy.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),null,cnn);
        HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition","attachment; filename=solicitud_de_pedidoNordy.pdf");
        ServletOutputStream stream =  response.getOutputStream();//permite que el browser reconozca el pdf para poderdescargar
         
         //convierte el jasper a pdf y se envia el jasperPrint que es el jasper llenado
         JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            
        
        stream.flush();
        stream.close();
   //     FacesContext.getCurrentInstance().responseComplete();
        
        RequestContext.getCurrentInstance().update("grow1");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Información", "Pedido Registrado Correctamente"));
        
        
    } 

  
    
    /**
     * Creates a new instance of PedidoReportBean
     */
    public PedidoReportBean() {
        
    }
    
    
}
