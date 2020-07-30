package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.CantonTMunicipioDao;
import com.equivida.modelo.CantonTMunicipio;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Daniel Cardenas
 * 
 */
@Stateless(name = "CantonTMunicipioDao")
public class CantonTMunicipioDaoEjb extends
		GenericDaoEjb<CantonTMunicipio, Short> implements CantonTMunicipioDao {

	public CantonTMunicipioDaoEjb() {
		super(CantonTMunicipio.class);
	}

}
