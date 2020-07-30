package com.equivida.servicio;

import java.util.List;

import javax.ejb.Local;

import com.equivida.modelo.Referencia;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface ReferenciaServicio extends GenericService<Referencia, Integer> {

	List<Referencia> obtenerPorPersonaNatural(Integer secPersonaNatural);
}
