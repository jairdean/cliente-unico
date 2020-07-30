package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.HabitoEnfermedad;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface HabitoEnfermedadServicio extends
		GenericService<HabitoEnfermedad, Integer> {
}
