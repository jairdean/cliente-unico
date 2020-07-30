package com.equivida.servicio;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.equivida.constant.PersonaRechazoListaReservadaEnum;
import com.equivida.dto.MensajeRcsDto;
import com.equivida.dto.ResultadoWebserviceListaReservada;
import com.equivida.modelo.PersonaJuridica;
import com.equivida.modelo.PersonaNatural;
import com.equivida.modelo.Rcs;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * @author Daniel Cardenas
 * 
 */
@Local
public interface RcsServicio extends GenericService<Rcs, Integer> {

	List<Rcs> obtenerPorFechaUsuarioId(Date fechaDesde, Date fechaHasta, String idUsuario);

	List<String> obtenerDistintosUsuarios();

	List<Rcs> obtenerPorFechaUsuarioIdPaginado(Date fechaDesde, Date fechaHasta, String idUsuario,
			Character codEstadoRcs, boolean contratante, int ini, int total);

	Long obtenerPorFechaUsuarioIdPaginadoTotal(Date fechaDesde, Date fechaHasta, String idUsuario,
			Character codEstadoRcs, boolean contratante);

	/**
	 * Consulta el ultimo registro con la cedula dada
	 * 
	 * @param identificacion
	 * @return
	 */
	Rcs obtenerMasReciente(String identificacion);

	/**
	 * Verifica si puede continuar el proceso de ingreso/actualizacion en el
	 * formulario de cliente unico, dependiendo de Rcs
	 * 
	 * @param personaNatural
	 * @param usuario
	 * @param ip
	 * @param nuevo
	 * @return
	 */
	MensajeRcsDto verificarBloqueo(PersonaNatural personaNatural, String usuario, String ip, boolean nuevo);

	/**
	 * 
	 * @param persona
	 * @param usuario
	 * @param ip
	 * @param nuevo
	 * @return
	 */
	ResultadoWebserviceListaReservada consultarRegistrarServicioWebRcs(PersonaNatural personaNatural, String usuario,
			String ip, boolean nuevo);

	/**
	 * @param personaJuridica
	 * @param usuario
	 * @param ip
	 * @param nuevo
	 * @param controlRcsEnum
	 * @return
	 */
	ResultadoWebserviceListaReservada consultarRegistrarServicioWebRcsPJ(PersonaJuridica personaJuridica,
			String usuario, String ip, boolean nuevo, PersonaRechazoListaReservadaEnum rcsEnum);

	/**
	 * 
	 * @param persona
	 * @param usuario
	 * @param ip
	 * @param nuevo
	 * @return
	 */
	ResultadoWebserviceListaReservada consultarRegistrarServicioWebRcsConyuge(PersonaNatural personaNatural,
			String usuario, String ip, boolean nuevo);

	/**
	 * Aprueba el cliente
	 * 
	 * @param rcs
	 * @param usuarioAprueba
	 */
	void aprobar(Rcs rcs, String usuarioAprueba);

	/**
	 * Rechaza el cliente
	 * 
	 * @param rcs
	 * @param usuarioRechaza
	 */
	void rechazar(Rcs rcs, String usuarioRechaza);

	/**
	 * Verifica si puede continuar el proceso de ingreso/actualizacion en el
	 * formulario de cliente unico, dependiendo de Rcs, para conyuge
	 * 
	 * @param personaNatural
	 * @param usuario
	 * @param ip
	 * @param nuevo
	 * @return
	 */
	MensajeRcsDto verificarBloqueoConyuge(PersonaNatural personaNatural, String usuario, String ip, boolean nuevo);

	List<String> obtenerDistintosUsuariosCreacion();

	/**
	 * Metodo que consulta en la tabla RCS si no encuentra devuelve
	 * 
	 * @param personaLista
	 * @param usuario
	 * @param ip
	 * @return
	 */
	List<Rcs> obtenerRcs(List<PersonaNatural> personaLista, String usuario, String ip);

	/**
	 * Verifica si puede continuar el proceso de ingreso/actualizacion en el
	 * formulario de contratante PJ, dependiendo de Rcs
	 * 
	 * @param personaJuridica
	 * @param usuario
	 * @param ip
	 * @param nuevo
	 * @param controlRcsEnum
	 * @return
	 */
	MensajeRcsDto verificarBloqueoPJ(PersonaJuridica personaJuridica, String usuario, String ip, boolean nuevo,
			PersonaRechazoListaReservadaEnum rcsEnum);
}
