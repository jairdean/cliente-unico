package com.equivida.sise.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "contratante", namespace = "http://ws.sise.equivida.com/")
public class ContratanteDto implements Serializable {
	@XmlElement(name = "txt_apellido1", required = true)
	private String txt_apellido1;

	@XmlElement(name = "txt_apellido2", required = false)
	private String txt_apellido2;

	@XmlElement(name = "txt_nombre", required = false)
	private String txt_nombre;

	@XmlElement(name = "cod_tipo_doc", required = true)
	private BigDecimal cod_tipo_doc;

	@XmlElement(name = "nro_doc", required = false)
	private String nro_doc;

	@XmlElement(name = "cod_tipo_iva", required = false)
	private BigDecimal cod_tipo_iva;

	@XmlElement(name = "nro_nit", required = false)
	private String nro_nit;

	@XmlElement(name = "fec_nac", required = false)
	private String fec_nac;

	@XmlElement(name = "txt_lugar_nac", required = false)
	private String txt_lugar_nac;

	@XmlElement(name = "txt_sexo", required = false)
	private String txt_sexo;

	@XmlElement(name = "cod_est_civil", required = false)
	private BigDecimal cod_est_civil;

	@XmlElement(name = "cod_tipo_persona", required = false)
	private String cod_tipo_persona;

	@XmlElement(name = "cod_tipo_soc", required = false)
	private BigDecimal cod_tipo_soc;

	@XmlElement(name = "cod_sector_mercado", required = false)
	private BigDecimal cod_sector_mercado;

	@XmlElement(name = "txt_nombre_contacto", required = false)
	private String txt_nombre_contacto;

	@XmlElement(name = "fec_contitucion", required = false)
	private String fec_contitucion;

	@XmlElement(name = "txt_cuidad_const", required = false)
	private String txt_cuidad_const;

	@XmlElement(name = "cnt_duracion_empresa", required = false)
	private BigDecimal cnt_duracion_empresa;

	@XmlElement(name = "imp_capital_social", required = false)
	private String imp_capital_social;

	@XmlElement(name = "cod_tipo_empresa", required = false)
	private BigDecimal cod_tipo_empresa;

	@XmlElement(name = "txt_nombre_gerente", required = false)
	private String txt_nombre_gerente;

	@XmlElement(name = "cnt_tiempo_mercado", required = false)
	private BigDecimal cnt_tiempo_mercado;

	@XmlElement(name = "txt_nombres_rep_legal", required = false)
	private String txt_nombres_rep_legal;

	@XmlElement(name = "txt_apellidos_rep_legal", required = false)
	private String txt_apellidos_rep_legal;

	@XmlElement(name = "cod_tipo_doc_rep_legal", required = false)
	private BigDecimal cod_tipo_doc_rep_legal;

	@XmlElement(name = "nro_doc_rep_legal", required = false)
	private String nro_doc_rep_legal;

	@XmlElement(name = "ingresos_anuales", required = false)
	private BigDecimal ingresos_anuales;

	@XmlElement(name = "cod_moneda_ing", required = false)
	private BigDecimal cod_moneda_ing;

	@XmlElement(name = "fec_corte_ing", required = false)
	private String fec_corte_ing;

	@XmlElement(name = "imp_total_activos", required = false)
	private BigDecimal imp_total_activos;

	@XmlElement(name = "imp_total_pasivos", required = false)
	private BigDecimal imp_total_pasivos;

	@XmlElement(name = "sn_parteRel", required = false)
	private String sn_parteRel;

	@XmlElement(name = "txt_apellido_casada", required = false)
	private String txt_apellido_casada;

	@XmlElement(name = "cod_clase_vivienda", required = false)
	private BigDecimal cod_clase_vivienda;

	@XmlElement(name = "cod_tipo_vivienda", required = false)
	private BigDecimal cod_tipo_vivienda;

	@XmlElement(name = "sn_vehiculo", required = false)
	private String sn_vehiculo;

	@XmlElement(name = "txt_desc_vehiculo", required = false)
	private String txt_desc_vehiculo;

	@XmlElement(name = "sn_seguro_anterior", required = false)
	private String sn_seguro_anterior;

	@XmlElement(name = "cod_cia_seguro_anterior", required = false)
	private BigDecimal cod_cia_seguro_anterior;

	@XmlElement(name = "sn_asiste_club", required = false)
	private String sn_asiste_club;

	@XmlElement(name = "txt_desc_club", required = false)
	private String txt_desc_club;

	@XmlElement(name = "txt_nombres_conyugue", required = false)
	private String txt_nombres_conyugue;

	@XmlElement(name = "txt_apellidos_conyugue", required = false)
	private String txt_apellidos_conyugue;

	@XmlElement(name = "cod_tipo_doc_conyugue", required = false)
	private BigDecimal cod_tipo_doc_conyugue;

	@XmlElement(name = "nro_doc_conyugue", required = false)
	private String nro_doc_conyugue;

	@XmlElement(name = "sn_relacionl", required = false)
	private String sn_relacionl;

	@XmlElement(name = "txt_edificio", required = false)
	private String txt_edificio;

	@XmlElement(name = "txt_urbanizacion", required = false)
	private String txt_urbanizacion;

	@XmlElement(name = "txt_sector", required = false)
	private String txt_sector;

	@XmlElement(name = "imp_prom_ing_mensual", required = false)
	private String imp_prom_ing_mensual;

	@XmlElement(name = "sn_pep", required = false)
	private String sn_pep;

	@XmlElement(name = "sn_relacion_laboral_pep", required = false)
	private String sn_relacion_laboral_pep;

	@XmlElement(name = "txt_nombre_institucion_pep", required = false)
	private String txt_nombre_institucion_pep;

	@XmlElement(name = "cod_actividad", required = false)
	private String cod_actividad;

	@XmlElement(name = "cod_ingreso_pm", required = false)
	private BigDecimal cod_ingreso_pm;

	public ContratanteDto() {
	}

	public ContratanteDto(String txt_apellido1, String txt_apellido2, String txt_nombre, BigDecimal cod_tipo_doc,
			String nro_doc, BigDecimal cod_tipo_iva, String nro_nit, String fec_nac, String txt_lugar_nac,
			String txt_sexo, BigDecimal cod_est_civil, String cod_tipo_persona, BigDecimal cod_tipo_soc,
			BigDecimal cod_sector_mercado, String txt_nombre_contacto, String fec_contitucion, String txt_cuidad_const,
			BigDecimal cnt_duracion_empresa, String imp_capital_social, BigDecimal cod_tipo_empresa,
			String txt_nombre_gerente, BigDecimal cnt_tiempo_mercado, String txt_nombres_rep_legal,
			String txt_apellidos_rep_legal, BigDecimal cod_tipo_doc_rep_legal, String nro_doc_rep_legal,
			BigDecimal ingresos_anuales, BigDecimal cod_moneda_ing, String fec_corte_ing, BigDecimal imp_total_activos,
			BigDecimal imp_total_pasivos, String sn_parteRel, String txt_apellido_casada, BigDecimal cod_clase_vivienda,
			BigDecimal cod_tipo_vivienda, String sn_vehiculo, String txt_desc_vehiculo, String sn_seguro_anterior,
			BigDecimal cod_cia_seguro_anterior, String sn_asiste_club, String txt_desc_club,
			String txt_nombres_conyugue, String txt_apellidos_conyugue, BigDecimal cod_tipo_doc_conyugue,
			String nro_doc_conyugue, String sn_relacionl, String txt_edificio, String txt_urbanizacion,
			String txt_sector, String imp_prom_ing_mensual, String sn_pep, String sn_relacion_laboral_pep,
			String txt_nombre_institucion_pep, String cod_actividad, BigDecimal cod_ingreso_pm) {
		super();
		this.txt_apellido1 = txt_apellido1;
		this.txt_apellido2 = txt_apellido2;
		this.txt_nombre = txt_nombre;
		this.cod_tipo_doc = cod_tipo_doc;
		this.nro_doc = nro_doc;
		this.cod_tipo_iva = cod_tipo_iva;
		this.nro_nit = nro_nit;
		this.fec_nac = fec_nac;
		this.txt_lugar_nac = txt_lugar_nac;
		this.txt_sexo = txt_sexo;
		this.cod_est_civil = cod_est_civil;
		this.cod_tipo_persona = cod_tipo_persona;
		this.cod_tipo_soc = cod_tipo_soc;
		this.cod_sector_mercado = cod_sector_mercado;
		this.txt_nombre_contacto = txt_nombre_contacto;
		this.fec_contitucion = fec_contitucion;
		this.txt_cuidad_const = txt_cuidad_const;
		this.cnt_duracion_empresa = cnt_duracion_empresa;
		this.imp_capital_social = imp_capital_social;
		this.cod_tipo_empresa = cod_tipo_empresa;
		this.txt_nombre_gerente = txt_nombre_gerente;
		this.cnt_tiempo_mercado = cnt_tiempo_mercado;
		this.txt_nombres_rep_legal = txt_nombres_rep_legal;
		this.txt_apellidos_rep_legal = txt_apellidos_rep_legal;
		this.cod_tipo_doc_rep_legal = cod_tipo_doc_rep_legal;
		this.nro_doc_rep_legal = nro_doc_rep_legal;
		this.ingresos_anuales = ingresos_anuales;
		this.cod_moneda_ing = cod_moneda_ing;
		this.fec_corte_ing = fec_corte_ing;
		this.imp_total_activos = imp_total_activos;
		this.imp_total_pasivos = imp_total_pasivos;
		this.sn_parteRel = sn_parteRel;
		this.txt_apellido_casada = txt_apellido_casada;
		this.cod_clase_vivienda = cod_clase_vivienda;
		this.cod_tipo_vivienda = cod_tipo_vivienda;
		this.sn_vehiculo = sn_vehiculo;
		this.txt_desc_vehiculo = txt_desc_vehiculo;
		this.sn_seguro_anterior = sn_seguro_anterior;
		this.cod_cia_seguro_anterior = cod_cia_seguro_anterior;
		this.sn_asiste_club = sn_asiste_club;
		this.txt_desc_club = txt_desc_club;
		this.txt_nombres_conyugue = txt_nombres_conyugue;
		this.txt_apellidos_conyugue = txt_apellidos_conyugue;
		this.cod_tipo_doc_conyugue = cod_tipo_doc_conyugue;
		this.nro_doc_conyugue = nro_doc_conyugue;
		this.sn_relacionl = sn_relacionl;
		this.txt_edificio = txt_edificio;
		this.txt_urbanizacion = txt_urbanizacion;
		this.txt_sector = txt_sector;
		this.imp_prom_ing_mensual = imp_prom_ing_mensual;
		this.sn_pep = sn_pep;
		this.sn_relacion_laboral_pep = sn_relacion_laboral_pep;
		this.txt_nombre_institucion_pep = txt_nombre_institucion_pep;
		this.cod_actividad = cod_actividad;
		this.cod_ingreso_pm = cod_ingreso_pm;
	}

	/**
	 * @return the txt_apellido1
	 */
	public String getTxt_apellido1() {
		return txt_apellido1;
	}

	/**
	 * @param txt_apellido1 the txt_apellido1 to set
	 */
	public void setTxt_apellido1(String txt_apellido1) {
		this.txt_apellido1 = txt_apellido1;
	}

	/**
	 * @return the txt_apellido2
	 */
	public String getTxt_apellido2() {
		return txt_apellido2;
	}

	/**
	 * @param txt_apellido2 the txt_apellido2 to set
	 */
	public void setTxt_apellido2(String txt_apellido2) {
		this.txt_apellido2 = txt_apellido2;
	}

	/**
	 * @return the txt_nombre
	 */
	public String getTxt_nombre() {
		return txt_nombre;
	}

	/**
	 * @param txt_nombre the txt_nombre to set
	 */
	public void setTxt_nombre(String txt_nombre) {
		this.txt_nombre = txt_nombre;
	}

	/**
	 * @return the cod_tipo_doc
	 */
	public BigDecimal getCod_tipo_doc() {
		return cod_tipo_doc;
	}

	/**
	 * @param cod_tipo_doc the cod_tipo_doc to set
	 */
	public void setCod_tipo_doc(BigDecimal cod_tipo_doc) {
		this.cod_tipo_doc = cod_tipo_doc;
	}

	/**
	 * @return the nro_doc
	 */
	public String getNro_doc() {
		return nro_doc;
	}

	/**
	 * @param nro_doc the nro_doc to set
	 */
	public void setNro_doc(String nro_doc) {
		this.nro_doc = nro_doc;
	}

	/**
	 * @return the cod_tipo_iva
	 */
	public BigDecimal getCod_tipo_iva() {
		return cod_tipo_iva;
	}

	/**
	 * @param cod_tipo_iva the cod_tipo_iva to set
	 */
	public void setCod_tipo_iva(BigDecimal cod_tipo_iva) {
		this.cod_tipo_iva = cod_tipo_iva;
	}

	/**
	 * @return the nro_nit
	 */
	public String getNro_nit() {
		return nro_nit;
	}

	/**
	 * @param nro_nit the nro_nit to set
	 */
	public void setNro_nit(String nro_nit) {
		this.nro_nit = nro_nit;
	}

	/**
	 * @return the fec_nac
	 */
	public String getFec_nac() {
		return fec_nac;
	}

	/**
	 * @param fec_nac the fec_nac to set
	 */
	public void setFec_nac(String fec_nac) {
		this.fec_nac = fec_nac;
	}

	/**
	 * @return the txt_lugar_nac
	 */
	public String getTxt_lugar_nac() {
		return txt_lugar_nac;
	}

	/**
	 * @param txt_lugar_nac the txt_lugar_nac to set
	 */
	public void setTxt_lugar_nac(String txt_lugar_nac) {
		this.txt_lugar_nac = txt_lugar_nac;
	}

	/**
	 * @return the txt_sexo
	 */
	public String getTxt_sexo() {
		return txt_sexo;
	}

	/**
	 * @param txt_sexo the txt_sexo to set
	 */
	public void setTxt_sexo(String txt_sexo) {
		this.txt_sexo = txt_sexo;
	}

	/**
	 * @return the cod_est_civil
	 */
	public BigDecimal getCod_est_civil() {
		return cod_est_civil;
	}

	/**
	 * @param cod_est_civil the cod_est_civil to set
	 */
	public void setCod_est_civil(BigDecimal cod_est_civil) {
		this.cod_est_civil = cod_est_civil;
	}

	/**
	 * @return the cod_tipo_persona
	 */
	public String getCod_tipo_persona() {
		return cod_tipo_persona;
	}

	/**
	 * @param cod_tipo_persona the cod_tipo_persona to set
	 */
	public void setCod_tipo_persona(String cod_tipo_persona) {
		this.cod_tipo_persona = cod_tipo_persona;
	}

	/**
	 * @return the cod_tipo_soc
	 */
	public BigDecimal getCod_tipo_soc() {
		return cod_tipo_soc;
	}

	/**
	 * @param cod_tipo_soc the cod_tipo_soc to set
	 */
	public void setCod_tipo_soc(BigDecimal cod_tipo_soc) {
		this.cod_tipo_soc = cod_tipo_soc;
	}

	/**
	 * @return the cod_sector_mercado
	 */
	public BigDecimal getCod_sector_mercado() {
		return cod_sector_mercado;
	}

	/**
	 * @param cod_sector_mercado the cod_sector_mercado to set
	 */
	public void setCod_sector_mercado(BigDecimal cod_sector_mercado) {
		this.cod_sector_mercado = cod_sector_mercado;
	}

	/**
	 * @return the txt_nombre_contacto
	 */
	public String getTxt_nombre_contacto() {
		return txt_nombre_contacto;
	}

	/**
	 * @param txt_nombre_contacto the txt_nombre_contacto to set
	 */
	public void setTxt_nombre_contacto(String txt_nombre_contacto) {
		this.txt_nombre_contacto = txt_nombre_contacto;
	}

	/**
	 * @return the fec_contitucion
	 */
	public String getFec_contitucion() {
		return fec_contitucion;
	}

	/**
	 * @param fec_contitucion the fec_contitucion to set
	 */
	public void setFec_contitucion(String fec_contitucion) {
		this.fec_contitucion = fec_contitucion;
	}

	/**
	 * @return the txt_cuidad_const
	 */
	public String getTxt_cuidad_const() {
		return txt_cuidad_const;
	}

	/**
	 * @param txt_cuidad_const the txt_cuidad_const to set
	 */
	public void setTxt_cuidad_const(String txt_cuidad_const) {
		this.txt_cuidad_const = txt_cuidad_const;
	}

	/**
	 * @return the cnt_duracion_empresa
	 */
	public BigDecimal getCnt_duracion_empresa() {
		return cnt_duracion_empresa;
	}

	/**
	 * @param cnt_duracion_empresa the cnt_duracion_empresa to set
	 */
	public void setCnt_duracion_empresa(BigDecimal cnt_duracion_empresa) {
		this.cnt_duracion_empresa = cnt_duracion_empresa;
	}

	/**
	 * @return the imp_capital_social
	 */
	public String getImp_capital_social() {
		return imp_capital_social;
	}

	/**
	 * @param imp_capital_social the imp_capital_social to set
	 */
	public void setImp_capital_social(String imp_capital_social) {
		this.imp_capital_social = imp_capital_social;
	}

	/**
	 * @return the cod_tipo_empresa
	 */
	public BigDecimal getCod_tipo_empresa() {
		return cod_tipo_empresa;
	}

	/**
	 * @param cod_tipo_empresa the cod_tipo_empresa to set
	 */
	public void setCod_tipo_empresa(BigDecimal cod_tipo_empresa) {
		this.cod_tipo_empresa = cod_tipo_empresa;
	}

	/**
	 * @return the txt_nombre_gerente
	 */
	public String getTxt_nombre_gerente() {
		return txt_nombre_gerente;
	}

	/**
	 * @param txt_nombre_gerente the txt_nombre_gerente to set
	 */
	public void setTxt_nombre_gerente(String txt_nombre_gerente) {
		this.txt_nombre_gerente = txt_nombre_gerente;
	}

	/**
	 * @return the cnt_tiempo_mercado
	 */
	public BigDecimal getCnt_tiempo_mercado() {
		return cnt_tiempo_mercado;
	}

	/**
	 * @param cnt_tiempo_mercado the cnt_tiempo_mercado to set
	 */
	public void setCnt_tiempo_mercado(BigDecimal cnt_tiempo_mercado) {
		this.cnt_tiempo_mercado = cnt_tiempo_mercado;
	}

	/**
	 * @return the txt_nombres_rep_legal
	 */
	public String getTxt_nombres_rep_legal() {
		return txt_nombres_rep_legal;
	}

	/**
	 * @param txt_nombres_rep_legal the txt_nombres_rep_legal to set
	 */
	public void setTxt_nombres_rep_legal(String txt_nombres_rep_legal) {
		this.txt_nombres_rep_legal = txt_nombres_rep_legal;
	}

	/**
	 * @return the txt_apellidos_rep_legal
	 */
	public String getTxt_apellidos_rep_legal() {
		return txt_apellidos_rep_legal;
	}

	/**
	 * @param txt_apellidos_rep_legal the txt_apellidos_rep_legal to set
	 */
	public void setTxt_apellidos_rep_legal(String txt_apellidos_rep_legal) {
		this.txt_apellidos_rep_legal = txt_apellidos_rep_legal;
	}

	/**
	 * @return the cod_tipo_doc_rep_legal
	 */
	public BigDecimal getCod_tipo_doc_rep_legal() {
		return cod_tipo_doc_rep_legal;
	}

	/**
	 * @param cod_tipo_doc_rep_legal the cod_tipo_doc_rep_legal to set
	 */
	public void setCod_tipo_doc_rep_legal(BigDecimal cod_tipo_doc_rep_legal) {
		this.cod_tipo_doc_rep_legal = cod_tipo_doc_rep_legal;
	}

	/**
	 * @return the nro_doc_rep_legal
	 */
	public String getNro_doc_rep_legal() {
		return nro_doc_rep_legal;
	}

	/**
	 * @param nro_doc_rep_legal the nro_doc_rep_legal to set
	 */
	public void setNro_doc_rep_legal(String nro_doc_rep_legal) {
		this.nro_doc_rep_legal = nro_doc_rep_legal;
	}

	/**
	 * @return the ingresos_anuales
	 */
	public BigDecimal getIngresos_anuales() {
		return ingresos_anuales;
	}

	/**
	 * @param ingresos_anuales the ingresos_anuales to set
	 */
	public void setIngresos_anuales(BigDecimal ingresos_anuales) {
		this.ingresos_anuales = ingresos_anuales;
	}

	/**
	 * @return the cod_moneda_ing
	 */
	public BigDecimal getCod_moneda_ing() {
		return cod_moneda_ing;
	}

	/**
	 * @param cod_moneda_ing the cod_moneda_ing to set
	 */
	public void setCod_moneda_ing(BigDecimal cod_moneda_ing) {
		this.cod_moneda_ing = cod_moneda_ing;
	}

	/**
	 * @return the fec_corte_ing
	 */
	public String getFec_corte_ing() {
		return fec_corte_ing;
	}

	/**
	 * @param fec_corte_ing the fec_corte_ing to set
	 */
	public void setFec_corte_ing(String fec_corte_ing) {
		this.fec_corte_ing = fec_corte_ing;
	}

	/**
	 * @return the imp_total_activos
	 */
	public BigDecimal getImp_total_activos() {
		return imp_total_activos;
	}

	/**
	 * @param imp_total_activos the imp_total_activos to set
	 */
	public void setImp_total_activos(BigDecimal imp_total_activos) {
		this.imp_total_activos = imp_total_activos;
	}

	/**
	 * @return the imp_total_pasivos
	 */
	public BigDecimal getImp_total_pasivos() {
		return imp_total_pasivos;
	}

	/**
	 * @param imp_total_pasivos the imp_total_pasivos to set
	 */
	public void setImp_total_pasivos(BigDecimal imp_total_pasivos) {
		this.imp_total_pasivos = imp_total_pasivos;
	}

	/**
	 * @return the sn_parteRel
	 */
	public String getSn_parteRel() {
		return sn_parteRel;
	}

	/**
	 * @param sn_parteRel the sn_parteRel to set
	 */
	public void setSn_parteRel(String sn_parteRel) {
		this.sn_parteRel = sn_parteRel;
	}

	/**
	 * @return the txt_apellido_casada
	 */
	public String getTxt_apellido_casada() {
		return txt_apellido_casada;
	}

	/**
	 * @param txt_apellido_casada the txt_apellido_casada to set
	 */
	public void setTxt_apellido_casada(String txt_apellido_casada) {
		this.txt_apellido_casada = txt_apellido_casada;
	}

	/**
	 * @return the cod_clase_vivienda
	 */
	public BigDecimal getCod_clase_vivienda() {
		return cod_clase_vivienda;
	}

	/**
	 * @param cod_clase_vivienda the cod_clase_vivienda to set
	 */
	public void setCod_clase_vivienda(BigDecimal cod_clase_vivienda) {
		this.cod_clase_vivienda = cod_clase_vivienda;
	}

	/**
	 * @return the cod_tipo_vivienda
	 */
	public BigDecimal getCod_tipo_vivienda() {
		return cod_tipo_vivienda;
	}

	/**
	 * @param cod_tipo_vivienda the cod_tipo_vivienda to set
	 */
	public void setCod_tipo_vivienda(BigDecimal cod_tipo_vivienda) {
		this.cod_tipo_vivienda = cod_tipo_vivienda;
	}

	/**
	 * @return the sn_vehiculo
	 */
	public String getSn_vehiculo() {
		return sn_vehiculo;
	}

	/**
	 * @param sn_vehiculo the sn_vehiculo to set
	 */
	public void setSn_vehiculo(String sn_vehiculo) {
		this.sn_vehiculo = sn_vehiculo;
	}

	/**
	 * @return the txt_desc_vehiculo
	 */
	public String getTxt_desc_vehiculo() {
		return txt_desc_vehiculo;
	}

	/**
	 * @param txt_desc_vehiculo the txt_desc_vehiculo to set
	 */
	public void setTxt_desc_vehiculo(String txt_desc_vehiculo) {
		this.txt_desc_vehiculo = txt_desc_vehiculo;
	}

	/**
	 * @return the sn_seguro_anterior
	 */
	public String getSn_seguro_anterior() {
		return sn_seguro_anterior;
	}

	/**
	 * @param sn_seguro_anterior the sn_seguro_anterior to set
	 */
	public void setSn_seguro_anterior(String sn_seguro_anterior) {
		this.sn_seguro_anterior = sn_seguro_anterior;
	}

	/**
	 * @return the cod_cia_seguro_anterior
	 */
	public BigDecimal getCod_cia_seguro_anterior() {
		return cod_cia_seguro_anterior;
	}

	/**
	 * @param cod_cia_seguro_anterior the cod_cia_seguro_anterior to set
	 */
	public void setCod_cia_seguro_anterior(BigDecimal cod_cia_seguro_anterior) {
		this.cod_cia_seguro_anterior = cod_cia_seguro_anterior;
	}

	/**
	 * @return the sn_asiste_club
	 */
	public String getSn_asiste_club() {
		return sn_asiste_club;
	}

	/**
	 * @param sn_asiste_club the sn_asiste_club to set
	 */
	public void setSn_asiste_club(String sn_asiste_club) {
		this.sn_asiste_club = sn_asiste_club;
	}

	/**
	 * @return the txt_desc_club
	 */
	public String getTxt_desc_club() {
		return txt_desc_club;
	}

	/**
	 * @param txt_desc_club the txt_desc_club to set
	 */
	public void setTxt_desc_club(String txt_desc_club) {
		this.txt_desc_club = txt_desc_club;
	}

	/**
	 * @return the txt_nombres_conyugue
	 */
	public String getTxt_nombres_conyugue() {
		return txt_nombres_conyugue;
	}

	/**
	 * @param txt_nombres_conyugue the txt_nombres_conyugue to set
	 */
	public void setTxt_nombres_conyugue(String txt_nombres_conyugue) {
		this.txt_nombres_conyugue = txt_nombres_conyugue;
	}

	/**
	 * @return the txt_apellidos_conyugue
	 */
	public String getTxt_apellidos_conyugue() {
		return txt_apellidos_conyugue;
	}

	/**
	 * @param txt_apellidos_conyugue the txt_apellidos_conyugue to set
	 */
	public void setTxt_apellidos_conyugue(String txt_apellidos_conyugue) {
		this.txt_apellidos_conyugue = txt_apellidos_conyugue;
	}

	/**
	 * @return the cod_tipo_doc_conyugue
	 */
	public BigDecimal getCod_tipo_doc_conyugue() {
		return cod_tipo_doc_conyugue;
	}

	/**
	 * @param cod_tipo_doc_conyugue the cod_tipo_doc_conyugue to set
	 */
	public void setCod_tipo_doc_conyugue(BigDecimal cod_tipo_doc_conyugue) {
		this.cod_tipo_doc_conyugue = cod_tipo_doc_conyugue;
	}

	/**
	 * @return the nro_doc_conyugue
	 */
	public String getNro_doc_conyugue() {
		return nro_doc_conyugue;
	}

	/**
	 * @param nro_doc_conyugue the nro_doc_conyugue to set
	 */
	public void setNro_doc_conyugue(String nro_doc_conyugue) {
		this.nro_doc_conyugue = nro_doc_conyugue;
	}

	/**
	 * @return the sn_relacionl
	 */
	public String getSn_relacionl() {
		return sn_relacionl;
	}

	/**
	 * @param sn_relacionl the sn_relacionl to set
	 */
	public void setSn_relacionl(String sn_relacionl) {
		this.sn_relacionl = sn_relacionl;
	}

	/**
	 * @return the txt_edificio
	 */
	public String getTxt_edificio() {
		return txt_edificio;
	}

	/**
	 * @param txt_edificio the txt_edificio to set
	 */
	public void setTxt_edificio(String txt_edificio) {
		this.txt_edificio = txt_edificio;
	}

	/**
	 * @return the txt_urbanizacion
	 */
	public String getTxt_urbanizacion() {
		return txt_urbanizacion;
	}

	/**
	 * @param txt_urbanizacion the txt_urbanizacion to set
	 */
	public void setTxt_urbanizacion(String txt_urbanizacion) {
		this.txt_urbanizacion = txt_urbanizacion;
	}

	/**
	 * @return the txt_sector
	 */
	public String getTxt_sector() {
		return txt_sector;
	}

	/**
	 * @param txt_sector the txt_sector to set
	 */
	public void setTxt_sector(String txt_sector) {
		this.txt_sector = txt_sector;
	}

	/**
	 * @return the imp_prom_ing_mensual
	 */
	public String getImp_prom_ing_mensual() {
		return imp_prom_ing_mensual;
	}

	/**
	 * @param imp_prom_ing_mensual the imp_prom_ing_mensual to set
	 */
	public void setImp_prom_ing_mensual(String imp_prom_ing_mensual) {
		this.imp_prom_ing_mensual = imp_prom_ing_mensual;
	}

	/**
	 * @return the sn_pep
	 */
	public String getSn_pep() {
		return sn_pep;
	}

	/**
	 * @param sn_pep the sn_pep to set
	 */
	public void setSn_pep(String sn_pep) {
		this.sn_pep = sn_pep;
	}

	/**
	 * @return the sn_relacion_laboral_pep
	 */
	public String getSn_relacion_laboral_pep() {
		return sn_relacion_laboral_pep;
	}

	/**
	 * @param sn_relacion_laboral_pep the sn_relacion_laboral_pep to set
	 */
	public void setSn_relacion_laboral_pep(String sn_relacion_laboral_pep) {
		this.sn_relacion_laboral_pep = sn_relacion_laboral_pep;
	}

	/**
	 * @return the txt_nombre_institucion_pep
	 */
	public String getTxt_nombre_institucion_pep() {
		return txt_nombre_institucion_pep;
	}

	/**
	 * @param txt_nombre_institucion_pep the txt_nombre_institucion_pep to set
	 */
	public void setTxt_nombre_institucion_pep(String txt_nombre_institucion_pep) {
		this.txt_nombre_institucion_pep = txt_nombre_institucion_pep;
	}

	/**
	 * @return the cod_actividad
	 */
	public String getCod_actividad() {
		return cod_actividad;
	}

	/**
	 * @param cod_actividad the cod_actividad to set
	 */
	public void setCod_actividad(String cod_actividad) {
		this.cod_actividad = cod_actividad;
	}

	/**
	 * @return the cod_ingreso_pm
	 */
	public BigDecimal getCod_ingreso_pm() {
		return cod_ingreso_pm;
	}

	/**
	 * @param cod_ingreso_pm the cod_ingreso_pm to set
	 */
	public void setCod_ingreso_pm(BigDecimal cod_ingreso_pm) {
		this.cod_ingreso_pm = cod_ingreso_pm;
	}

	@Override
	public String toString() {
		return "ContratanteDto [txt_apellido1=" + txt_apellido1 + ", txt_apellido2=" + txt_apellido2 + ", txt_nombre="
				+ txt_nombre + ", cod_tipo_doc=" + cod_tipo_doc + ", nro_doc=" + nro_doc + ", cod_tipo_iva="
				+ cod_tipo_iva + ", nro_nit=" + nro_nit + ", fec_nac=" + fec_nac + ", txt_lugar_nac=" + txt_lugar_nac
				+ ", txt_sexo=" + txt_sexo + ", cod_est_civil=" + cod_est_civil + ", cod_tipo_persona="
				+ cod_tipo_persona + ", cod_tipo_soc=" + cod_tipo_soc + ", cod_sector_mercado=" + cod_sector_mercado
				+ ", txt_nombre_contacto=" + txt_nombre_contacto + ", fec_contitucion=" + fec_contitucion
				+ ", txt_cuidad_const=" + txt_cuidad_const + ", cnt_duracion_empresa=" + cnt_duracion_empresa
				+ ", imp_capital_social=" + imp_capital_social + ", cod_tipo_empresa=" + cod_tipo_empresa
				+ ", txt_nombre_gerente=" + txt_nombre_gerente + ", cnt_tiempo_mercado=" + cnt_tiempo_mercado
				+ ", txt_nombres_rep_legal=" + txt_nombres_rep_legal + ", txt_apellidos_rep_legal="
				+ txt_apellidos_rep_legal + ", cod_tipo_doc_rep_legal=" + cod_tipo_doc_rep_legal
				+ ", nro_doc_rep_legal=" + nro_doc_rep_legal + ", ingresos_anuales=" + ingresos_anuales
				+ ", cod_moneda_ing=" + cod_moneda_ing + ", fec_corte_ing=" + fec_corte_ing + ", imp_total_activos="
				+ imp_total_activos + ", imp_total_pasivos=" + imp_total_pasivos + ", sn_parteRel=" + sn_parteRel
				+ ", txt_apellido_casada=" + txt_apellido_casada + ", cod_clase_vivienda=" + cod_clase_vivienda
				+ ", cod_tipo_vivienda=" + cod_tipo_vivienda + ", sn_vehiculo=" + sn_vehiculo + ", txt_desc_vehiculo="
				+ txt_desc_vehiculo + ", sn_seguro_anterior=" + sn_seguro_anterior + ", cod_cia_seguro_anterior="
				+ cod_cia_seguro_anterior + ", sn_asiste_club=" + sn_asiste_club + ", txt_desc_club=" + txt_desc_club
				+ ", txt_nombres_conyugue=" + txt_nombres_conyugue + ", txt_apellidos_conyugue="
				+ txt_apellidos_conyugue + ", cod_tipo_doc_conyugue=" + cod_tipo_doc_conyugue + ", nro_doc_conyugue="
				+ nro_doc_conyugue + ", sn_relacionl=" + sn_relacionl + ", txt_edificio=" + txt_edificio
				+ ", txt_urbanizacion=" + txt_urbanizacion + ", txt_sector=" + txt_sector + ", imp_prom_ing_mensual="
				+ imp_prom_ing_mensual + ", sn_pep=" + sn_pep + ", sn_relacion_laboral_pep=" + sn_relacion_laboral_pep
				+ ", txt_nombre_institucion_pep=" + txt_nombre_institucion_pep + ", cod_actividad=" + cod_actividad
				+ ", cod_ingreso_pm=" + cod_ingreso_pm + "]";
	}
}
