package com.equivida.servicio;

import java.util.List;

import javax.ejb.Local;

import com.equivida.modelo.OtraOcupacion;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Daniel Cardenas
 * 
 */
@Local
public interface OtraOcupacionServicio extends
		GenericService<OtraOcupacion, Integer> {

	List<OtraOcupacion> obtenerPorPersonaNatural(Integer secPersonaNatural);
}
