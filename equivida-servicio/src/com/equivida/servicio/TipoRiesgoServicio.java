package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.TipoRiesgo;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Daniel Cardenas
 * 
 */
@Local
public interface TipoRiesgoServicio extends
		GenericService<TipoRiesgo, Short> {

}
