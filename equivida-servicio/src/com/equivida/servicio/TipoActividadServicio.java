package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.TipoActividad;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface TipoActividadServicio extends
		GenericService<TipoActividad, Short> {

}
