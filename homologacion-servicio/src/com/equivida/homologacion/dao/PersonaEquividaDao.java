package com.equivida.homologacion.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.equivida.homologacion.modelo.PersonaEquivida;
import com.saviasoft.persistence.util.dao.GenericDao;

@Local
public interface PersonaEquividaDao extends
		GenericDao<PersonaEquivida, Integer> {
	
	Map<Long, Long> obtenerSecuencialPersona(List<Long> idssSise);

}