package com.equivida.homologacion.servicio;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.equivida.homologacion.modelo.PersonaEquivida;
import com.saviasoft.persistence.util.service.GenericService;

@Local
public interface PersonaEquividaServicio extends GenericService<PersonaEquivida, Integer> {

	Long obtenerIdPersonaDestinoPN(Integer secPersona, Integer secPersonaNatural, Short sistemaOid,
			Short codTipoPersona);

	boolean existe(Integer secPersona, Integer secPersonaNatural, Long idPersonaSise, Short sistemaOid,
			Short codTipoPersona);

	boolean existeJuridica(Integer secPersona, Integer secPersonaJuridica, Long idPersonaSise, Short sistemaOid,
			Short codTipoPersona);

	Long obtenerIdSisePersonaNatural(Integer secPersonaNatural);

	Integer obtenerSecuencialPersonaNatural(Long idSise);

	List<Long> obtenerIdSisePersona(Integer secPersona);

	Integer obtenerSecuencialPersona(Long idSise);

	Map<Long, Long> obtenerSecuencialPersona(List<Long> idsSise);

	/**
	 * Crea una persona en una nueva transaccion.
	 * 
	 * @param personaEquivida
	 */
	void createNoTx(PersonaEquivida personaEquivida);
	
	boolean existeNoTx(Integer secPersona, Integer secPersonaNatural, Long idPersonaSise, Short sistemaOid,
			Short codTipoPersona);
	
	boolean existeJuridicaNoTx(Integer secPersona, Integer secPersonaJuridica, Long idPersonaSise, Short sistemaOid,
			Short codTipoPersona);
}