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
@Table(name = "CATEGORIA_PPE")
@NamedQueries({
    @NamedQuery(name = "CategoriaPpe.findAll", query = "SELECT c FROM CategoriaPpe c"),
    @NamedQuery(name = "CategoriaPpe.findByCodCategoriaPpe", query = "SELECT c FROM CategoriaPpe c WHERE c.codCategoriaPpe = :codCategoriaPpe"),
    @NamedQuery(name = "CategoriaPpe.findByCategoriaPpe", query = "SELECT c FROM CategoriaPpe c WHERE c.categoriaPpe = :categoriaPpe")})
public class CategoriaPpe implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4379619606814787133L;
	@Id
    @Basic(optional = false)
    @Column(name = "COD_CATEGORIA_PPE")
    private Short codCategoriaPpe;
    @Basic(optional = false)
    @Column(name = "CATEGORIA_PPE")
    private String categoriaPpe;

    public CategoriaPpe() {
    }

    public CategoriaPpe(Short codCategoriaPpe) {
        this.codCategoriaPpe = codCategoriaPpe;
    }

    public CategoriaPpe(Short codCategoriaPpe, String categoriaPpe) {
        this.codCategoriaPpe = codCategoriaPpe;
        this.categoriaPpe = categoriaPpe;
    }

    public Short getCodCategoriaPpe() {
        return codCategoriaPpe;
    }

    public void setCodCategoriaPpe(Short codCategoriaPpe) {
        this.codCategoriaPpe = codCategoriaPpe;
    }

    public String getCategoriaPpe() {
        return categoriaPpe;
    }

    public void setCategoriaPpe(String categoriaPpe) {
        this.categoriaPpe = categoriaPpe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codCategoriaPpe != null ? codCategoriaPpe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriaPpe)) {
            return false;
        }
        CategoriaPpe other = (CategoriaPpe) object;
        if ((this.codCategoriaPpe == null && other.codCategoriaPpe != null) || (this.codCategoriaPpe != null && !this.codCategoriaPpe.equals(other.codCategoriaPpe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.equivida.modelo.CategoriaPpe[codCategoriaPpe=" + codCategoriaPpe + "]";
    }

}
