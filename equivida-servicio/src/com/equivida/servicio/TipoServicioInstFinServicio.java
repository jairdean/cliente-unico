package com.equivida.servicio;

import java.util.List;

import javax.ejb.Local;

import com.equivida.modelo.TipoServicioInstFin;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Daniel Cardenas
 * 
 */
@Local
public interface TipoServicioInstFinServicio extends
		GenericService<TipoServicioInstFin, Short> {

	List<TipoServicioInstFin> obtenerPorTipoFinanciera(Short secTipoFinanciera);

}