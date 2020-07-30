package com.equivida.servicio;

import java.util.List;

import javax.ejb.Local;

import com.equivida.modelo.PersonaNatural;
import com.equivida.modelo.RucPersonaNatural;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Daniel Cardenas
 * 
 */
@Local
public interface RucPersonaNaturalServicio extends GenericService<RucPersonaNatural, Integer> {

	List<RucPersonaNatural> buscarPoIdentificacion(String identificacion, Integer secPersonaNatural);
	
	void verificarRuc(PersonaNatural personaNatural);
	
}
