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
@Table(name = "TIPO_PERSONA_JURIDICA")
@NamedQueries({
    @NamedQuery(name = "TipoPersonaJuridica.findAll", query = "SELECT t FROM TipoPersonaJuridica t"),
    @NamedQuery(name = "TipoPersonaJuridica.findByCodTipoPersonaJuridica", query = "SELECT t FROM TipoPersonaJuridica t WHERE t.codTipoPersonaJuridica = :codTipoPersonaJuridica"),
    @NamedQuery(name = "TipoPersonaJuridica.findByTipoPersonaJuridica", query = "SELECT t FROM TipoPersonaJuridica t WHERE t.tipoPersonaJuridica = :tipoPersonaJuridica")})
public class TipoPersonaJuridica implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2608473671716663871L;
	@Id
    @Basic(optional = false)
    @Column(name = "COD_TIPO_PERSONA_JURIDICA")
    private Short codTipoPersonaJuridica;
    @Basic(optional = false)
    @Column(name = "TIPO_PERSONA_JURIDICA")
    private String tipoPersonaJuridica;

    public TipoPersonaJuridica() {
    }

    public TipoPersonaJuridica(Short codTipoPersonaJuridica) {
        this.codTipoPersonaJuridica = codTipoPersonaJuridica;
    }

    public TipoPersonaJuridica(Short codTipoPersonaJuridica, String tipoPersonaJuridica) {
        this.codTipoPersonaJuridica = codTipoPersonaJuridica;
        this.tipoPersonaJuridica = tipoPersonaJuridica;
    }

    public Short getCodTipoPersonaJuridica() {
        return codTipoPersonaJuridica;
    }

    public void setCodTipoPersonaJuridica(Short codTipoPersonaJuridica) {
        this.codTipoPersonaJuridica = codTipoPersonaJuridica;
    }

    public String getTipoPersonaJuridica() {
        return tipoPersonaJuridica;
    }

    public void setTipoPersonaJuridica(String tipoPersonaJuridica) {
        this.tipoPersonaJuridica = tipoPersonaJuridica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codTipoPersonaJuridica != null ? codTipoPersonaJuridica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPersonaJuridica)) {
            return false;
        }
        TipoPersonaJuridica other = (TipoPersonaJuridica) object;
        if ((this.codTipoPersonaJuridica == null && other.codTipoPersonaJuridica != null) || (this.codTipoPersonaJuridica != null && !this.codTipoPersonaJuridica.equals(other.codTipoPersonaJuridica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.equivida.modelo.TipoPersonaJuridica[codTipoPersonaJuridica=" + codTipoPersonaJuridica + "]";
    }

}
