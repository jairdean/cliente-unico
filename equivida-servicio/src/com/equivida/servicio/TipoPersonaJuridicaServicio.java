package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.TipoPersonaJuridica;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface TipoPersonaJuridicaServicio extends
		GenericService<TipoPersonaJuridica, Short> {

}
