package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.SectorMercadoDao;
import com.equivida.modelo.SectorMercado;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Duval
 * 
 */
@Stateless(name = "SectorMercadoDaoDao")
public class SectorMercadoDaoEjb extends GenericDaoEjb<SectorMercado, Integer>
		implements SectorMercadoDao {

	public SectorMercadoDaoEjb() {
		super(SectorMercado.class);
	}

}
