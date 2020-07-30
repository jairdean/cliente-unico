/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.equivida.modelo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author saviasoft4
 */
@Entity
@Table(name = "TIPO_OTRO_SEGURO")
@NamedQueries({
    @NamedQuery(name = "TipoOtroSeguro.findAll", query = "SELECT t FROM TipoOtroSeguro t"),
    @NamedQuery(name = "TipoOtroSeguro.findBySecTipoAdicional", query = "SELECT t FROM TipoOtroSeguro t WHERE t.secTipoAdicional = :secTipoAdicional"),
    @NamedQuery(name = "TipoOtroSeguro.findByTipoAdicional", query = "SELECT t FROM TipoOtroSeguro t WHERE t.tipoAdicional = :tipoAdicional")})
public class TipoOtroSeguro implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8146757915192018579L;
	@Id
    @Basic(optional = false)
    @Column(name = "SEC_TIPO_ADICIONAL")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Short secTipoAdicional;
    @Basic(optional = false)
    @Column(name = "TIPO_ADICIONAL")
    private String tipoAdicional;

    public TipoOtroSeguro() {
    }

    public TipoOtroSeguro(Short secTipoAdicional) {
        this.secTipoAdicional = secTipoAdicional;
    }

    public TipoOtroSeguro(Short secTipoAdicional, String tipoAdicional) {
        this.secTipoAdicional = secTipoAdicional;
        this.tipoAdicional = tipoAdicional;
    }

    public Short getSecTipoAdicional() {
        return secTipoAdicional;
    }

    public void setSecTipoAdicional(Short secTipoAdicional) {
        this.secTipoAdicional = secTipoAdicional;
    }

    public String getTipoAdicional() {
        return tipoAdicional;
    }

    public void setTipoAdicional(String tipoAdicional) {
        this.tipoAdicional = tipoAdicional;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (secTipoAdicional != null ? secTipoAdicional.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoOtroSeguro)) {
            return false;
        }
        TipoOtroSeguro other = (TipoOtroSeguro) object;
        if ((this.secTipoAdicional == null && other.secTipoAdicional != null) || (this.secTipoAdicional != null && !this.secTipoAdicional.equals(other.secTipoAdicional))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.equivida.modelo.TipoOtroSeguro[secTipoAdicional=" + secTipoAdicional + "]";
    }

}
