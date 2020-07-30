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
@Table(name = "TIPO_CONTACTO_PREFERIDO")
@NamedQueries({
    @NamedQuery(name = "TipoContactoPreferido.findAll", query = "SELECT t FROM TipoContactoPreferido t"),
    @NamedQuery(name = "TipoContactoPreferido.findByCodTipoContactoPreferido", query = "SELECT t FROM TipoContactoPreferido t WHERE t.codTipoContactoPreferido = :codTipoContactoPreferido"),
    @NamedQuery(name = "TipoContactoPreferido.findByTipoContactoPreferido", query = "SELECT t FROM TipoContactoPreferido t WHERE t.tipoContactoPreferido = :tipoContactoPreferido")})
public class TipoContactoPreferido implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -177893262792963807L;
	@Id
    @Basic(optional = false)
    @Column(name = "COD_TIPO_CONTACTO_PREFERIDO")
    private Short codTipoContactoPreferido;
    @Basic(optional = false)
    @Column(name = "TIPO_CONTACTO_PREFERIDO")
    private String tipoContactoPreferido;

    public TipoContactoPreferido() {
    }

    public TipoContactoPreferido(Short codTipoContactoPreferido) {
        this.codTipoContactoPreferido = codTipoContactoPreferido;
    }

    public TipoContactoPreferido(Short codTipoContactoPreferido, String tipoContactoPreferido) {
        this.codTipoContactoPreferido = codTipoContactoPreferido;
        this.tipoContactoPreferido = tipoContactoPreferido;
    }

    public Short getCodTipoContactoPreferido() {
        return codTipoContactoPreferido;
    }

    public void setCodTipoContactoPreferido(Short codTipoContactoPreferido) {
        this.codTipoContactoPreferido = codTipoContactoPreferido;
    }

    public String getTipoContactoPreferido() {
        return tipoContactoPreferido;
    }

    public void setTipoContactoPreferido(String tipoContactoPreferido) {
        this.tipoContactoPreferido = tipoContactoPreferido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codTipoContactoPreferido != null ? codTipoContactoPreferido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoContactoPreferido)) {
            return false;
        }
        TipoContactoPreferido other = (TipoContactoPreferido) object;
        if ((this.codTipoContactoPreferido == null && other.codTipoContactoPreferido != null) || (this.codTipoContactoPreferido != null && !this.codTipoContactoPreferido.equals(other.codTipoContactoPreferido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.equivida.modelo.TipoContactoPreferido[codTipoContactoPreferido=" + codTipoContactoPreferido + "]";
    }

}
