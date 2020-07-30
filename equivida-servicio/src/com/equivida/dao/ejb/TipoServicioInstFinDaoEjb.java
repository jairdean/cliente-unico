package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.TipoServicioInstFinDao;
import com.equivida.modelo.TipoServicioInstFin;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Daniel Cardenas
 * 
 */
@Stateless(name = "TipoServicioInstFinDao")
public class TipoServicioInstFinDaoEjb extends
		GenericDaoEjb<TipoServicioInstFin, Short> implements
		TipoServicioInstFinDao {

	public TipoServicioInstFinDaoEjb() {
		super(TipoServicioInstFin.class);
	}
}