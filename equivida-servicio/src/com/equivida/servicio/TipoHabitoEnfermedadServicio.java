package com.equivida.servicio;

import java.util.Collection;

import javax.ejb.Local;

import com.equivida.modelo.TipoHabitoEnfermedad;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * @author Daniel Cardenas
 * 
 */
@Local
public interface TipoHabitoEnfermedadServicio extends GenericService<TipoHabitoEnfermedad, Short> {

	Collection<TipoHabitoEnfermedad> buscarPorCategoria(String[] categorias);

}
