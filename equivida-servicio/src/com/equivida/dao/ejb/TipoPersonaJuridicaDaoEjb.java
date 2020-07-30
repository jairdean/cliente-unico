package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.TipoPersonaJuridicaDao;
import com.equivida.modelo.TipoPersonaJuridica;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "TipoPersonaJuridicaDao")
public class TipoPersonaJuridicaDaoEjb extends
		GenericDaoEjb<TipoPersonaJuridica, Short> implements
		TipoPersonaJuridicaDao {

	public TipoPersonaJuridicaDaoEjb() {
		super(TipoPersonaJuridica.class);
	}

}
