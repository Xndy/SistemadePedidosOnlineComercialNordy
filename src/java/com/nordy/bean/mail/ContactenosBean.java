/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nordy.bean.mail;

import java.util.Properties;
import javax.mail.*;
import javax.faces.bean.ManagedBean;
import javax.mail.internet.*;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
/**
 *
 * @author XndyPxndx
 */
@ManagedBean(name = "contactenosBean")
public class ContactenosBean {
    
    private int port = 465;
    private String host = "smtp.gmail.com";
    private String from = "comercialnordy@gmail.com";
    private boolean auth = true;
    private String username = "comercialnordy@gmail.com";
    private String password = "nordy1234";
    private Protocol protocol = Protocol.SMTPS;
    private boolean debug = true;

    
    private String nombre;
    private String asunto;
    private String correo;
    private String mensaje;
    
    
    public void sendEmail() {
        
        if ( asunto == null && mensaje == null && correo == null){
            correo = "andy.sanchezg@ug.edu.ec";
            asunto = "Tienes Una Nueva Notificacion";
            mensaje = "Tiene un nuevo pedido, no te olvides de a probarlo";
        }
        
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        switch (protocol) {
            case SMTPS:
                props.put("mail.smtp.ssl.enable", true);
                break;
            case TLS:
                props.put("mail.smtp.starttls.enable", true);
                break;
        }

        Authenticator authenticator = null;
        if (auth) {
            props.put("mail.smtp.auth", true);
            authenticator = new Authenticator() {
                private PasswordAuthentication pa = new PasswordAuthentication(username, password);
                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    return pa;
                }
            };
        }

        Session session = Session.getInstance(props, authenticator);
        session.setDebug(debug);

        MimeMessage message = new MimeMessage(session);
        try {
            
            message.setFrom(new InternetAddress(correo));
            InternetAddress[] address = {new InternetAddress(from)};
            message.setRecipients(Message.RecipientType.TO, address);
            message.setSubject(asunto);
            message.setSentDate(new Date());
            message.setText(" El correo electronico es: \n\n\n" + correo + " \n \n\n\n El mensaje es el siguiente: \n\n\n\n" +asunto);
            Transport.send(message);
            
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Exito", "Su su solicitud fue enviada correctamente. Pronto nos contactaremos contigo"));

            
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public void sendEmailUpdatePedido(String fromm) {
        
        if ( asunto == null && mensaje == null && correo == null){
            correo = "andy.sanchezg@ug.edu.ec";
            asunto = "Pedido de Comercial Nordy!";
            mensaje = "Reciba un cordial Saludos de todos los que conformamos comercial Nordy el motivo de este correo es para confirma que su pedido fue APROBADO CON EXITO  ";
        }
        
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        switch (protocol) {
            case SMTPS:
                props.put("mail.smtp.ssl.enable", true);
                break;
            case TLS:
                props.put("mail.smtp.starttls.enable", true);
                break;
        }

        Authenticator authenticator = null;
        if (auth) {
            props.put("mail.smtp.auth", true);
            authenticator = new Authenticator() {
                private PasswordAuthentication pa = new PasswordAuthentication(username, password);
                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    return pa;
                }
            };
        }

        Session session = Session.getInstance(props, authenticator);
        session.setDebug(debug);

        MimeMessage message = new MimeMessage(session);
        try {
            
            message.setFrom(new InternetAddress(correo));
            InternetAddress[] address = {new InternetAddress(fromm)};
            message.setRecipients(Message.RecipientType.TO, address);
            message.setSubject(asunto);
            message.setSentDate(new Date());
            message.setText(mensaje);
            Transport.send(message);
            
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
    
}
