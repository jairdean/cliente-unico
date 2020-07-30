package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.PerfilFinancieroJuridicaDao;
import com.equivida.modelo.PerfilFinancieroJuridica;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "PerfilFinancieroJuridicaDao")
public class PerfilFinancieroJuridicaDaoEjb extends
		GenericDaoEjb<PerfilFinancieroJuridica, Integer> implements
		PerfilFinancieroJuridicaDao {

	public PerfilFinancieroJuridicaDaoEjb() {
		super(PerfilFinancieroJuridica.class);
	}

}
