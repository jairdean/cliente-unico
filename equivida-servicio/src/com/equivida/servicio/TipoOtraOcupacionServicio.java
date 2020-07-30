package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.TipoOtraOcupacion;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Daniel Cardenas
 * 
 */
@Local
public interface TipoOtraOcupacionServicio extends
		GenericService<TipoOtraOcupacion, Short> {

}
