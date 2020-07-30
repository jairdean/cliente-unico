package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.TipoDireccionDao;
import com.equivida.modelo.TipoDireccion;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "TipoDireccionDao")
public class TipoDireccionDaoEjb extends GenericDaoEjb<TipoDireccion, Short>
		implements TipoDireccionDao {

	public TipoDireccionDaoEjb() {
		super(TipoDireccion.class);
	}

}