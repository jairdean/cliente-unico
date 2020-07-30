package com.equivida.servicio;

import java.util.Collection;

import javax.ejb.Local;

import com.equivida.modelo.DireccionTelefono;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface DireccionTelefonoServicio extends
		GenericService<DireccionTelefono, Integer> {

	void guardarLista(Collection<DireccionTelefono> lista);
	
}
