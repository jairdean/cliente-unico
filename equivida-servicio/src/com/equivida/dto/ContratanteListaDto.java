package com.equivida.dto;

import java.io.Serializable;

import com.equivida.constant.TipoPersonaEnum;

/**
 * Dto para mostrar la información de un contratante sea: natural o juridica en
 * la pantalla de búsqueda.
 * 
 * @author juan
 *
 */
public class ContratanteListaDto implements Serializable {
	private static final long serialVersionUID = 3071992480316413337L;

	private Integer id;
	private TipoPersonaEnum tipo;
	private String identificacion;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String primerNombre;
	private String segundoNombre;
	private String razonSocial; // PAra el caso de persona juridica.

	public String getApellidosTra() {
		StringBuilder resp = new StringBuilder(300);

		if (apellidoPaterno != null && apellidoPaterno.trim().length() > 0) {
			resp.append(apellidoPaterno);
		}
		if (apellidoMaterno != null && apellidoMaterno.trim().length() > 0) {
			resp.append(" ").append(apellidoMaterno);
		}

		return resp.toString();
	}

	public String getNombresTra() {
		StringBuilder resp = new StringBuilder(300);

		if (primerNombre != null && primerNombre.trim().length() > 0) {
			resp.append(primerNombre);
		}
		if (segundoNombre != null && segundoNombre.trim().length() > 0) {
			resp.append(" ").append(segundoNombre);
		}

		return resp.toString();
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the tipo
	 */
	public TipoPersonaEnum getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(TipoPersonaEnum tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the identificacion
	 */
	public String getIdentificacion() {
		return identificacion;
	}

	/**
	 * @param identificacion the identificacion to set
	 */
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	/**
	 * @return the apellidoPaterno
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	/**
	 * @param apellidoPaterno the apellidoPaterno to set
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	/**
	 * @return the apellidoMaterno
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	/**
	 * @param apellidoMaterno the apellidoMaterno to set
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	/**
	 * @return the primerNombre
	 */
	public String getPrimerNombre() {
		return primerNombre;
	}

	/**
	 * @param primerNombre the primerNombre to set
	 */
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	/**
	 * @return the segundoNombre
	 */
	public String getSegundoNombre() {
		return segundoNombre;
	}

	/**
	 * @param segundoNombre the segundoNombre to set
	 */
	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	/**
	 * @return the razonSocial
	 */
	public String getRazonSocial() {
		return razonSocial;
	}

	/**
	 * @param razonSocial the razonSocial to set
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

}
