package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.TipoEstado;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Daniel Cardenas
 * 
 */
@Local
public interface TipoEstadoServicio extends GenericService<TipoEstado, Short> {

}
