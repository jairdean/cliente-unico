package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.DetalleHabitoEnfermedad;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface DetalleHabitoEnfermedadServicio extends
		GenericService<DetalleHabitoEnfermedad, Integer> {

}
