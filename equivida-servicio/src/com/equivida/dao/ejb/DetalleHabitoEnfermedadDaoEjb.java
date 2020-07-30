package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.DetalleHabitoEnfermedadDao;
import com.equivida.modelo.DetalleHabitoEnfermedad;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "DetalleHabitoEnfermedadDao")
public class DetalleHabitoEnfermedadDaoEjb extends
		GenericDaoEjb<DetalleHabitoEnfermedad, Integer> implements
		DetalleHabitoEnfermedadDao {

	public DetalleHabitoEnfermedadDaoEjb() {
		super(DetalleHabitoEnfermedad.class);
	}

}
