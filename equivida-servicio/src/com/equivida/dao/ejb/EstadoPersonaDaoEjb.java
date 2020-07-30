package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.EstadoPersonaDao;
import com.equivida.modelo.EstadoPersona;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Daniel Cardenas
 * 
 */
@Stateless(name = "EstadoPersonaDao")
public class EstadoPersonaDaoEjb extends GenericDaoEjb<EstadoPersona, Integer>
		implements EstadoPersonaDao {

	public EstadoPersonaDaoEjb() {
		super(EstadoPersona.class);
	}

}
