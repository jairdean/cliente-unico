package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.TipoServicioFinanciero;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Daniel Cardenas
 * 
 */
@Local
public interface TipoServicioFinancieroServicio extends
		GenericService<TipoServicioFinanciero, Short> {
}