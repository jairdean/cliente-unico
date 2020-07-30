package com.equivida.dao;

import java.util.List;

import com.equivida.constant.EstadoEnum;
import com.equivida.modelo.Relacion;
import com.saviasoft.persistence.util.dao.GenericDao;

/**
 * 
 * @author Gerardo Tuquerrez
 * 
 */
public interface RelacionDao extends GenericDao<Relacion, Integer> {

	List<Relacion> buscarPorTipoPersonaPrincipal(short idTipo, Integer secPersona, EstadoEnum estado);
	
	List<Relacion> buscarPorTipoPersonaPrincipal(short idTipo[], Integer secPersona, EstadoEnum estado);

}
