package com.equivida.servicio;

import java.util.List;

import javax.ejb.Local;

import com.equivida.modelo.MotivoSeguro;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Daniel Cardenas
 * 
 */
@Local
public interface MotivoSeguroServicio extends
		GenericService<MotivoSeguro, Integer> {
	
	List<MotivoSeguro> obtenerPorPersona(Integer secPersona);

}
