package com.equivida.dao.ejb;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.dao.IngresoMensualDao;
import com.equivida.modelo.IngresoMensual;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "IngresoMensualDao")
public class IngresoMensualDaoEjb extends GenericDaoEjb<IngresoMensual, Integer> implements IngresoMensualDao {

	public IngresoMensualDaoEjb() {
		super(IngresoMensual.class);
	}

	@Override
	public void insertarJdbc(IngresoMensual ingresoMensual) {

		StringBuilder sql = new StringBuilder();
		sql.append(
				"insert into EQUIVIDA.INGRESO_MENSUAL (SEC_INGRESO_PERSONA, MNT_EGRESO_MENSUAL, MNT_INGRESO_MENSUAL, SEC_PERSONA_NATURAL)");
		sql.append("values (default, :egreso, :ingreso, :secpersonanatural) ");

		Query q = em.createNativeQuery(sql.toString());

		q.setParameter("egreso", ingresoMensual.getMntEgresoMensual());
		q.setParameter("ingreso", ingresoMensual.getMntIngresoMensual());
		q.setParameter("secpersonanatural", ingresoMensual.getPersonaNatural().getSecPersonaNatural());
		
		q.executeUpdate();
	}

}