package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.DeportePractica;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Daniel Cardenas
 * 
 */
@Local
public interface DeportePracticaServicio extends
		GenericService<DeportePractica, Integer> {

}
