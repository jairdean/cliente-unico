package com.equivida.dao;

import java.util.Collection;

import com.equivida.modelo.PreguntaHabitoEnfermedad;
import com.saviasoft.persistence.util.dao.GenericDao;

/**
 * 
 * @author Gerardo Tuquerrez
 * @author Daniel Cardenas
 * 
 */
public interface PreguntaHabitoEnfermedadDao extends GenericDao<PreguntaHabitoEnfermedad, Integer> {

	Collection<PreguntaHabitoEnfermedad> buscarPorCategoria(short codTipoHabito, String[] categorias);

}
