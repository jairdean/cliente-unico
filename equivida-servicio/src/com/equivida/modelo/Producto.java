/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.equivida.modelo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author saviasoft4
 */
@Entity
@Table(name = "PRODUCTO")
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findByCodProducto", query = "SELECT p FROM Producto p WHERE p.codProducto = :codProducto"),
    @NamedQuery(name = "Producto.findByProducto", query = "SELECT p FROM Producto p WHERE p.producto = :producto")})
public class Producto implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5440594934805498134L;
	@Id
    @Basic(optional = false)
    @Column(name = "COD_PRODUCTO")
    private Short codProducto;
    @Basic(optional = false)
    @Column(name = "PRODUCTO")
    private String producto;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
    //private Collection<Interviniente> intervinienteCollection;

    public Producto() {
    }

    public Producto(Short codProducto) {
        this.codProducto = codProducto;
    }

    public Producto(Short codProducto, String producto) {
        this.codProducto = codProducto;
        this.producto = producto;
    }

    public Short getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(Short codProducto) {
        this.codProducto = codProducto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codProducto != null ? codProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.codProducto == null && other.codProducto != null) || (this.codProducto != null && !this.codProducto.equals(other.codProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.equivida.modelo.Producto[codProducto=" + codProducto + "]";
    }
}
