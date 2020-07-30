package com.equivida.servicio;

import java.util.Collection;

import javax.ejb.Local;

import com.equivida.modelo.PreguntaHabitoEnfermedad;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * @author Daniel Cardenas
 * 
 */
@Local
public interface PreguntaHabitoEnfermedadServicio extends GenericService<PreguntaHabitoEnfermedad, Integer> {

	Collection<PreguntaHabitoEnfermedad> buscarPorCategoria(short codTipoHabito, String[] categorias);

}
