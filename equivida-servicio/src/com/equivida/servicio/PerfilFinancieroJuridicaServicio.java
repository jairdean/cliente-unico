package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.PerfilFinancieroJuridica;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * @author Daniel Cardenas
 * 
 */
@Local
public interface PerfilFinancieroJuridicaServicio extends GenericService<PerfilFinancieroJuridica, Integer> {

	PerfilFinancieroJuridica buscarPorSecPersonaJuridica(Integer secPersonaJuridica);

	void persistir(PerfilFinancieroJuridica perfilFinancieroJuridica);

}
