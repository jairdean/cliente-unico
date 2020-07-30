package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.HistoriaMedicaFamiliar;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface HistoriaMedicaFamiliarServicio extends
		GenericService<HistoriaMedicaFamiliar, Integer> {

}
