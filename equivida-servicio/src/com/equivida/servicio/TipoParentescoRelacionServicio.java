package com.equivida.servicio;

import java.util.List;

import javax.ejb.Local;

import com.equivida.modelo.TipoParentescoRelacion;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Daniel Cardenas
 * 
 */
@Local
public interface TipoParentescoRelacionServicio extends GenericService<TipoParentescoRelacion, Short> {

	List<TipoParentescoRelacion> obtenerPorIds(short[] ids);

}
