package com.equivida.servicio;

import java.util.List;

import javax.ejb.Local;

import com.equivida.constant.EstadoEnum;
import com.equivida.modelo.Relacion;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * @author Daniel Cardenas
 * 
 */
@Local
public interface RelacionServicio extends GenericService<Relacion, Integer> {

	List<Relacion> obtener(Integer secPersonaPrincipal, Integer secPersonaRelacion);

	List<Relacion> buscarPorTipoPersonaPrincipal(short idTipo, Integer secPersona, EstadoEnum estado);

	List<Relacion> buscarPorTipoPersonaPrincipal(short idTipo[], Integer secPersona, EstadoEnum estado);
}
