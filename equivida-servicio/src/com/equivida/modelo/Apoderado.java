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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author saviasoft4
 */
@Entity
@Table(name = "APODERADO")
@NamedQueries({
    @NamedQuery(name = "Apoderado.findAll", query = "SELECT a FROM Apoderado a"),
    @NamedQuery(name = "Apoderado.findBySecApoderado", query = "SELECT a FROM Apoderado a WHERE a.secApoderado = :secApoderado")})
public class Apoderado implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 394063732147798287L;
	@Id
    @Basic(optional = false)
    @Column(name = "SEC_APODERADO")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer secApoderado;
    @JoinColumn(name = "SEC_PERSONA", referencedColumnName = "SEC_PERSONA")
    @ManyToOne(optional = false)
    private Persona persona;
    @JoinColumn(name = "SEC_BENEFICIARIO_POLIZA", referencedColumnName = "SEC_BENEFICIARIO_POLIZA")
    @ManyToOne(optional = false)
    private BeneficiarioPoliza beneficiarioPoliza;

    public Apoderado() {
    }

    public Apoderado(Integer secApoderado) {
        this.secApoderado = secApoderado;
    }

    public Integer getSecApoderado() {
        return secApoderado;
    }

    public void setSecApoderado(Integer secApoderado) {
        this.secApoderado = secApoderado;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public BeneficiarioPoliza getBeneficiarioPoliza() {
        return beneficiarioPoliza;
    }

    public void setBeneficiarioPoliza(BeneficiarioPoliza beneficiarioPoliza) {
        this.beneficiarioPoliza = beneficiarioPoliza;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (secApoderado != null ? secApoderado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Apoderado)) {
            return false;
        }
        Apoderado other = (Apoderado) object;
        if ((this.secApoderado == null && other.secApoderado != null) || (this.secApoderado != null && !this.secApoderado.equals(other.secApoderado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.equivida.modelo.Apoderado[secApoderado=" + secApoderado + "]";
    }

}
