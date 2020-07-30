package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.Actividad;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface ActividadServicio extends GenericService<Actividad, Integer> {

}
