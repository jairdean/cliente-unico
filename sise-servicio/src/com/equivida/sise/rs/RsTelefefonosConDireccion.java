/**
 * 
 */
package com.equivida.sise.rs;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author saviasoft5
 * 
 */
public class RsTelefefonosConDireccion implements Serializable {

	private static final long serialVersionUID = -8812308218077779984L;

	private BigDecimal idTelefono;
	private Integer codTipoTelefono;
	private Integer codPais;
	private Integer codArea;
	private String numero;
	private String extension;
	private String tipoTelefono;
	private BigDecimal idDireccion;

	public BigDecimal getIdTelefono() {
		return idTelefono;
	}

	public void setIdTelefono(BigDecimal idTelefono) {
		this.idTelefono = idTelefono;
	}

	public Integer getCodTipoTelefono() {
		return codTipoTelefono;
	}

	public void setCodTipoTelefono(Integer codTipoTelefono) {
		this.codTipoTelefono = codTipoTelefono;
	}

	public Integer getCodPais() {
		return codPais;
	}

	public void setCodPais(Integer codPais) {
		this.codPais = codPais;
	}

	public Integer getCodArea() {
		return codArea;
	}

	public void setCodArea(Integer codArea) {
		this.codArea = codArea;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getTipoTelefono() {
		return tipoTelefono;
	}

	public void setTipoTelefono(String tipoTelefono) {
		this.tipoTelefono = tipoTelefono;
	}

	public BigDecimal getIdDireccion() {
		return idDireccion;
	}

	public void setIdDireccion(BigDecimal idDireccion) {
		this.idDireccion = idDireccion;
	}

}
