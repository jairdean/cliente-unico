package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.TipoServicioFinancieroDao;
import com.equivida.modelo.TipoServicioFinanciero;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "TipoServicioFinancieroDao")
public class TipoServicioFinancieroDaoEjb extends
		GenericDaoEjb<TipoServicioFinanciero, Short> implements
		TipoServicioFinancieroDao {

	public TipoServicioFinancieroDaoEjb() {
		super(TipoServicioFinanciero.class);
	}
}