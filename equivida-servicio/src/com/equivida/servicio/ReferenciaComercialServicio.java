package com.equivida.servicio;

import java.util.List;

import javax.ejb.Local;

import com.equivida.modelo.ReferenciaComercial;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Daniel Cardenas
 * 
 */
@Local
public interface ReferenciaComercialServicio extends
		GenericService<ReferenciaComercial, Integer> {

	List<ReferenciaComercial> obtenerPorPersonaNatural(Integer secPersonaNatural);
}