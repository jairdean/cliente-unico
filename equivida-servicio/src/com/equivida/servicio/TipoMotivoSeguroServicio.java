package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.TipoMotivoSeguro;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Daniel Cardenas
 * 
 */
@Local
public interface TipoMotivoSeguroServicio extends
		GenericService<TipoMotivoSeguro, Short> {

}
