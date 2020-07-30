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
@Table(name = "TIPO_RIESGO")
@NamedQueries({
    @NamedQuery(name = "TipoRiesgo.findAll", query = "SELECT t FROM TipoRiesgo t"),
    @NamedQuery(name = "TipoRiesgo.findByCodTipoRiesgo", query = "SELECT t FROM TipoRiesgo t WHERE t.codTipoRiesgo = :codTipoRiesgo"),
    @NamedQuery(name = "TipoRiesgo.findByTipoRiesgo", query = "SELECT t FROM TipoRiesgo t WHERE t.tipoRiesgo = :tipoRiesgo")})
public class TipoRiesgo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COD_TIPO_RIESGO")
    private Short codTipoRiesgo;
    @Basic(optional = false)
    @Column(name = "TIPO_RIESGO")
    private char tipoRiesgo;

    public TipoRiesgo() {
    }

    public TipoRiesgo(Short codTipoRiesgo) {
        this.codTipoRiesgo = codTipoRiesgo;
    }

    public TipoRiesgo(Short codTipoRiesgo, char tipoRiesgo) {
        this.codTipoRiesgo = codTipoRiesgo;
        this.tipoRiesgo = tipoRiesgo;
    }

    public Short getCodTipoRiesgo() {
        return codTipoRiesgo;
    }

    public void setCodTipoRiesgo(Short codTipoRiesgo) {
        this.codTipoRiesgo = codTipoRiesgo;
    }

    public char getTipoRiesgo() {
        return tipoRiesgo;
    }

    public void setTipoRiesgo(char tipoRiesgo) {
        this.tipoRiesgo = tipoRiesgo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codTipoRiesgo != null ? codTipoRiesgo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoRiesgo)) {
            return false;
        }
        TipoRiesgo other = (TipoRiesgo) object;
        if ((this.codTipoRiesgo == null && other.codTipoRiesgo != null) || (this.codTipoRiesgo != null && !this.codTipoRiesgo.equals(other.codTipoRiesgo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.equivida.modelo.TipoRiesgo[codTipoRiesgo=" + codTipoRiesgo + "]";
    }

}
