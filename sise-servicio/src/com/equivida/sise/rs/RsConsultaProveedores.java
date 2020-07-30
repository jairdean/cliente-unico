package com.equivida.sise.rs;

import java.io.Serializable;
import java.math.BigDecimal;

public class RsConsultaProveedores implements Serializable {

	private static final long serialVersionUID = -8128461532670938583L;

	private BigDecimal idPersona;
	private String numNit;
	private String txtApellido1;
	private String txtApellido2;
	private String txtNombre;
	private BigDecimal codTipoDoc;
	private String nroDoc;
	private BigDecimal codTipoIva;
	private String txtCiaTra;
	private String txtDptoTra;
	private String txtPuestoTra;
	private String txtAsistenteTra;
	private String fecNac;
	private String txtLugarNac;
	private String txtSexo;
	private String codEstCivil;
	private String txtNotas;
	private String codTipoPersona;
	private String txtOrigen;
	private BigDecimal codPaisOrigen;
	private BigDecimal codGrupoAsoc;
	private String fatCrm;
	
	public BigDecimal getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(BigDecimal idPersona) {
		this.idPersona = idPersona;
	}
	public String getNumNit() {
		return numNit;
	}
	public void setNumNit(String numNit) {
		this.numNit = numNit;
	}
	public String getTxtApellido1() {
		return txtApellido1;
	}
	public void setTxtApellido1(String txtApellido1) {
		this.txtApellido1 = txtApellido1;
	}
	public String getTxtApellido2() {
		return txtApellido2;
	}
	public void setTxtApellido2(String txtApellido2) {
		this.txtApellido2 = txtApellido2;
	}
	public String getTxtNombre() {
		return txtNombre;
	}
	public void setTxtNombre(String txtNombre) {
		this.txtNombre = txtNombre;
	}
	public BigDecimal getCodTipoDoc() {
		return codTipoDoc;
	}
	public void setCodTipoDoc(BigDecimal codTipoDoc) {
		this.codTipoDoc = codTipoDoc;
	}
	public String getNroDoc() {
		return nroDoc;
	}
	public void setNroDoc(String nroDoc) {
		this.nroDoc = nroDoc;
	}
	public BigDecimal getCodTipoIva() {
		return codTipoIva;
	}
	public void setCodTipoIva(BigDecimal codTipoIva) {
		this.codTipoIva = codTipoIva;
	}
	public String getTxtCiaTra() {
		return txtCiaTra;
	}
	public void setTxtCiaTra(String txtCiaTra) {
		this.txtCiaTra = txtCiaTra;
	}
	public String getTxtDptoTra() {
		return txtDptoTra;
	}
	public void setTxtDptoTra(String txtDptoTra) {
		this.txtDptoTra = txtDptoTra;
	}
	public String getTxtPuestoTra() {
		return txtPuestoTra;
	}
	public void setTxtPuestoTra(String txtPuestoTra) {
		this.txtPuestoTra = txtPuestoTra;
	}
	public String getTxtAsistenteTra() {
		return txtAsistenteTra;
	}
	public void setTxtAsistenteTra(String txtAsistenteTra) {
		this.txtAsistenteTra = txtAsistenteTra;
	}
	public String getFecNac() {
		return fecNac;
	}
	public void setFecNac(String fecNac) {
		this.fecNac = fecNac;
	}
	public String getTxtLugarNac() {
		return txtLugarNac;
	}
	public void setTxtLugarNac(String txtLugarNac) {
		this.txtLugarNac = txtLugarNac;
	}
	public String getTxtSexo() {
		return txtSexo;
	}
	public void setTxtSexo(String txtSexo) {
		this.txtSexo = txtSexo;
	}
	public String getCodEstCivil() {
		return codEstCivil;
	}
	public void setCodEstCivil(String codEstCivil) {
		this.codEstCivil = codEstCivil;
	}
	public String getTxtNotas() {
		return txtNotas;
	}
	public void setTxtNotas(String txtNotas) {
		this.txtNotas = txtNotas;
	}
	public String getCodTipoPersona() {
		return codTipoPersona;
	}
	public void setCodTipoPersona(String codTipoPersona) {
		this.codTipoPersona = codTipoPersona;
	}
	public String getTxtOrigen() {
		return txtOrigen;
	}
	public void setTxtOrigen(String txtOrigen) {
		this.txtOrigen = txtOrigen;
	}
	public BigDecimal getCodPaisOrigen() {
		return codPaisOrigen;
	}
	public void setCodPaisOrigen(BigDecimal codPaisOrigen) {
		this.codPaisOrigen = codPaisOrigen;
	}
	public BigDecimal getCodGrupoAsoc() {
		return codGrupoAsoc;
	}
	public void setCodGrupoAsoc(BigDecimal codGrupoAsoc) {
		this.codGrupoAsoc = codGrupoAsoc;
	}
	public String getFatCrm() {
		return fatCrm;
	}
	public void setFatCrm(String fatCrm) {
		this.fatCrm = fatCrm;
	}

}