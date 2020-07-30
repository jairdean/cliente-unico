package com.equivida.servicio;

import java.util.List;

import javax.ejb.Local;

import com.equivida.modelo.Hijo;
import com.equivida.modelo.Persona;
import com.equivida.modelo.PersonaNatural;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Duval
 * 
 */
@Local
public interface HijoServicio extends GenericService<Hijo, Integer> {

	List<Hijo> obtenerPorPadre(Integer idPadre);

	/**
	 * Guarda , actualiza o elimina informacion
	 * 
	 * @param persona
	 */
	void guardarActualizarEliminarHijos(Persona persona, PersonaNatural personaNatural);

	void guardarhijo(Hijo hijo);

	void eliminar(Hijo hijo);

}
