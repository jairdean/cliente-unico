package com.equivida.ws.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import com.equivida.dto.PersonaDto;
import com.equivida.dto.RcsRespuestaDto;
import com.equivida.modelo.PersonaNatural;
import com.equivida.modelo.Rcs;
import com.equivida.modelo.TipoIdentificacion;
import com.equivida.servicio.RcsServicio;
import com.equivida.servicio.TipoIdentificacionServicio;
import com.equivida.ws.ClienteUnicoWs;
import com.equivida.ws.RcsWs;

@Stateless(name = "RcsWs")
@WebService(endpointInterface = "com.equivida.ws.RcsWs")
@Remote(ClienteUnicoWs.class)
public class RcsWsImpl implements RcsWs {
	private static final Logger LOG = Logger.getLogger(RcsWsImpl.class
			.getName());

	@EJB
	private RcsServicio rcsServicio;

	@EJB
	private TipoIdentificacionServicio tipoIdentificacionServicio;

	@Override
	@WebMethod
	public List<RcsRespuestaDto> buscarRcs(List<PersonaDto> personas,
			String aplicacion) {

		// transforma a persona natural para reusar servicio

		String ip = "";

		List<PersonaNatural> pnLista = new ArrayList<PersonaNatural>();

		int fila = 1;

		List<RcsRespuestaDto> respLista = new ArrayList<RcsRespuestaDto>();

		for (PersonaDto personaDto : personas) {

			PersonaNatural personaNatural = new PersonaNatural();

			if (personaDto.getTipoIndentificacion().length() != 1) {
				RcsRespuestaDto resp = new RcsRespuestaDto();
				resp.setError("Ingrese tipo de identificacion valido. Registro Posicion No.  "
						+ fila);
				respLista.add(resp);
				fila++;
				continue;
			}

			Character tipoId = personaDto.getTipoIndentificacion().charAt(0);

			TipoIdentificacion tp = tipoIdentificacionServicio.findByPk(tipoId);

			if (tp == null) {
				RcsRespuestaDto resp = new RcsRespuestaDto();
				resp.setError("No existe el tipo de identificacion enviado. Registro Posicion No.  "
						+ fila);
				respLista.add(resp);
				fila++;
				continue;
			}

			// valida caracter interrogacion ?
			if (personaDto.getApellidoMaterno() != null
					&& personaDto.getApellidoMaterno().trim().length() > 0
					&& personaDto.getApellidoMaterno().contains("?")) {
				RcsRespuestaDto resp = new RcsRespuestaDto();
				resp.setError("Caracter invalido en apellido materno. Registro Posicion No.  "
						+ fila);
				respLista.add(resp);
				fila++;
				continue;
			}
			if (personaDto.getApellidoPaterno().contains("?")) {
				RcsRespuestaDto resp = new RcsRespuestaDto();
				resp.setError("Caracter invalido en apellido paterno. Registro Posicion No.  "
						+ fila);
				respLista.add(resp);
				fila++;
				continue;
			}
			if (personaDto.getPrimerNombre().contains("?")) {
				RcsRespuestaDto resp = new RcsRespuestaDto();
				resp.setError("Caracter invalido en primer nombre. Registro Posicion No.  "
						+ fila);
				respLista.add(resp);
				fila++;
				continue;
			}
			if (personaDto.getSegundoNombre() != null
					&& personaDto.getSegundoNombre().trim().length() > 0
					&& personaDto.getSegundoNombre().contains("?")) {
				RcsRespuestaDto resp = new RcsRespuestaDto();
				resp.setError("Caracter invalido en segundo nombre. Registro Posicion No.  "
						+ fila);
				respLista.add(resp);
				fila++;
				continue;
			}
			if (personaDto.getIdentificacion().contains("?")) {
				RcsRespuestaDto resp = new RcsRespuestaDto();
				resp.setError("Caracter invalido en identificacion. Registro Posicion No.  "
						+ fila);
				respLista.add(resp);
				fila++;
				continue;
			}

			personaNatural.setIdentificacion(personaDto.getIdentificacion());
			personaNatural
					.setTipoIdentificacion(new TipoIdentificacion(tipoId));
			personaNatural.setPrimerNombre(personaDto.getPrimerNombre());
			personaNatural.setSegundoNombre(personaDto.getSegundoNombre());
			personaNatural.setApellidoPaterno(personaDto.getApellidoPaterno());
			personaNatural.setApellidoMaterno(personaDto.getApellidoMaterno());

			pnLista.add(personaNatural);

			fila++;

		}

		// si tiene datos hubo entonces error y devuelve errores
		if (respLista.size() == 0) {

			LOG.info("Llamada al metodo");
			List<Rcs> rcsLista = rcsServicio
					.obtenerRcs(pnLista, aplicacion, ip);

			for (Rcs rcs : rcsLista) {
				RcsRespuestaDto dto = new RcsRespuestaDto();
				dto.setIndentificacion(rcs.getIdentificacion());
				dto.setContenidoXml(rcs.getContenidoXml());
				respLista.add(dto);
			}
		}

		return respLista;
	}

	public TipoIdentificacionServicio getTipoIdentificacionServicio() {
		return tipoIdentificacionServicio;
	}

	public void setTipoIdentificacionServicio(
			TipoIdentificacionServicio tipoIdentificacionServicio) {
		this.tipoIdentificacionServicio = tipoIdentificacionServicio;
	}

}
