/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nordy.dominio;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author xxxxx
 */
@Entity
@Table(name = "proforma")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proforma.findAll", query = "SELECT p FROM Proforma p"),
    @NamedQuery(name = "Proforma.findByIdproforma", query = "SELECT p FROM Proforma p WHERE p.idproforma = :idproforma"),
    @NamedQuery(name = "Proforma.findByFechaentrega", query = "SELECT p FROM Proforma p WHERE p.fechaentrega = :fechaentrega"),
    @NamedQuery(name = "Proforma.findBySubtotal", query = "SELECT p FROM Proforma p WHERE p.subtotal = :subtotal"),
    @NamedQuery(name = "Proforma.findByIva", query = "SELECT p FROM Proforma p WHERE p.iva = :iva"),
    @NamedQuery(name = "Proforma.findByDescuento", query = "SELECT p FROM Proforma p WHERE p.descuento = :descuento"),
    @NamedQuery(name = "Proforma.findByEstado", query = "SELECT p FROM Proforma p WHERE p.estado = :estado")})
public class Proforma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproforma")
    private Integer idproforma;
    @Column(name = "fechaentrega")
    @Temporal(TemporalType.DATE)
    private Date fechaentrega;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "subtotal")
    private Double subtotal;
    @Column(name = "iva")
    private Double iva;
    @Column(name = "descuento")
    private Double descuento;
    @Column(name = "estado")
    private Boolean estado;
    @JoinColumn(name = "codcliente", referencedColumnName = "idcliente")
    @ManyToOne
    private Cliente codcliente;

    public Proforma() {
    }

    public Proforma(Integer idproforma) {
        this.idproforma = idproforma;
    }

    public Integer getIdproforma() {
        return idproforma;
    }

    public void setIdproforma(Integer idproforma) {
        this.idproforma = idproforma;
    }

    public Date getFechaentrega() {
        return fechaentrega;
    }

    public void setFechaentrega(Date fechaentrega) {
        this.fechaentrega = fechaentrega;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Cliente getCodcliente() {
        return codcliente;
    }

    public void setCodcliente(Cliente codcliente) {
        this.codcliente = codcliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproforma != null ? idproforma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proforma)) {
            return false;
        }
        Proforma other = (Proforma) object;
        if ((this.idproforma == null && other.idproforma != null) || (this.idproforma != null && !this.idproforma.equals(other.idproforma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nordy.dominio.Proforma[ idproforma=" + idproforma + " ]";
    }
    
}
