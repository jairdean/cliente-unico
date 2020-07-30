/**
 * 
 */
package com.equivida.servicio;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import com.equivida.constant.TipoIdentificacionEnum;
import com.equivida.dto.ContratanteListaDto;
import com.equivida.exception.ContratanteException;
import com.equivida.exception.RcsException;
import com.equivida.modelo.PersonaJuridica;
import com.equivida.modelo.PersonaNatural;

/**
 * Interfaz que define los métodos para la lógica de negocio de contratantes.
 * 
 * @author juan
 * @author Daniel Cardenas
 *
 */
@Local
public interface ContratanteServicio {

	/**
	 * Se busca datos de referencia mediante la identificación cuando en el
	 * formuario es Persona Natural:
	 * 
	 * 1. Se busca en tabla de personas juridicas. 2. Se busca con los 10 primeros
	 * digitos en personaNatural. 3. Si no existe, en personaNatural, se busca en
	 * beneficiarios.
	 * 
	 * @param identificacion (Simpre es RUC)
	 * @return
	 */
	PersonaNatural verificarExistenciaPN(String identificacion);

	/**
	 * Se busca datos de referencia mediante la pk cuando en el formuario es Persona
	 * Natural:
	 * 
	 * 1. Se busca en tabla de personas juridicas. 2. Se busca con los 10 primeros
	 * digitos en personaNatural. 3. Si no existe, en personaNatural, se busca en
	 * beneficiarios.
	 * 
	 * @param identificacion (Simpre es RUC)
	 * @return
	 */
	PersonaNatural verificarExistenciaPN(Integer idPersonaNatural, String ruc);

	/**
	 * Se busca datos de referencia mediante la identificación cuando en el
	 * formuario es Persona Natural:
	 * 
	 * 1. Se busca en tabla de personas juridicas. 2. Se busca en Servivicio aun no
	 * disponible
	 * 
	 * @param identificacion (Simpre es RUC)
	 * @return
	 */
	PersonaJuridica verificarExistenciaPJ(String identificacion);

	/**
	 * Persiste contratante de tipo PersonaNatural..
	 * 
	 * Devuelve id en sistema SISE
	 * 
	 * @param personaNatural
	 */
	BigDecimal persistirPN(PersonaNatural personaNatural) throws ContratanteException, RcsException;

	/**
	 * Servicio apra buscar contratantes de manera paginada.
	 * 
	 * @param numeroDocumento
	 * @param apellidoPaterno
	 * @param apellidoMaterno
	 * @param primerNombre
	 * @param segundoNombre
	 * @param firstRows
	 * @param totalRows
	 * @return
	 */
	List<ContratanteListaDto> obtenerListadoPaginado(String numeroDocumento, String apellidoPaterno,
			String apellidoMaterno, String primerNombre, String segundoNombre, int firstRows, int totalRows);

	/**
	 * Cuenta registros para paginador.
	 * 
	 * @param numeroDocumento
	 * @param apellidoPaterno
	 * @param apellidoMaterno
	 * @param primerNombre
	 * @param segundoNombre
	 * @return
	 */
	Long obtenerTotalListadoPaginado(String numeroDocumento, String apellidoPaterno, String apellidoMaterno,
			String primerNombre, String segundoNombre);

	/**
	 * Se busca datos de referencia mediante la identificación para representante
	 * legales de persona juridica
	 * 
	 * 1. Se busca en tabla de personas naturales. 2. Se busca con los 10 primeros
	 * digitos en personaNatural. 3. Si no existe, en personaNatural, se busca en
	 * beneficiarios.
	 * 
	 * @param identificacion (Puede ser ruc o cedula)
	 * @return
	 */
	PersonaNatural verificarExistenciaRepresentanteLegal(String identificacion,
			TipoIdentificacionEnum tipoIdentificacionEnum);

	/**
	 * Persiste contratante de tipo PersonaJuridica
	 * 
	 * Devuelve id en sistema SISE
	 * 
	 * @param personaNatural
	 */
	BigDecimal persistirPJ(PersonaJuridica personaJuridica) throws ContratanteException, RcsException;

	void crearPJ(PersonaJuridica personaJuridica);

	void crearPN(PersonaNatural personaNatural);
}
