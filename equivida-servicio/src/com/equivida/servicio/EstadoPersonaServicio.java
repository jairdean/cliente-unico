package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.EstadoPersona;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Daniel Cardenas
 * 
 */
@Local
public interface EstadoPersonaServicio extends
		GenericService<EstadoPersona, Integer> {

}
