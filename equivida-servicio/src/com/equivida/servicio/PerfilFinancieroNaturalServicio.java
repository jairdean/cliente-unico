package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.PerfilFinancieroNatural;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface PerfilFinancieroNaturalServicio extends
		GenericService<PerfilFinancieroNatural, Integer> {

	PerfilFinancieroNatural buscarPorSecPersonaNatural(Integer secPersonaNatural);

	/**
	 * Se persiste PerfilFinancieroNatural.
	 * 
	 * @param perfilFinancieroNatural
	 */
	void persistir(PerfilFinancieroNatural perfilFinancieroNatural);
}
