package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.RucPersonaNaturalDao;
import com.equivida.modelo.RucPersonaNatural;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Daniel Cardenas
 * 
 */
@Stateless(name = "RucPersonaNaturalDao")
public class RucPersonaNaturalDaoEjb extends GenericDaoEjb<RucPersonaNatural, Integer> implements RucPersonaNaturalDao {

	public RucPersonaNaturalDaoEjb() {
		super(RucPersonaNatural.class);
	}
}