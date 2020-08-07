/**
 *PersonaNaturalDaoEjb.java
 *
 *Tue Jan 26 12:42:07 ECT 2016
 */
package com.equivida.smartdata.dao.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.smartdata.dao.InformacionAdicionalSdDao;
import com.equivida.smartdata.model.InformacionAdicionalSd;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

@Stateless(name = "InformacionAdicionalSdDao")
public class InformacionAdicionalSdDaoEjb extends GenericDaoEjb<InformacionAdicionalSd, Integer> implements InformacionAdicionalSdDao {

	public InformacionAdicionalSdDaoEjb() {
		super(InformacionAdicionalSd.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.dao.InformacionAdicionalSdDao#obtenerInformacionAdicionalBySecPersonaNatural(Integer secPersonaNatural)
	 */
	@Override
	public InformacionAdicionalSd obtenerInformacionAdicionalBySecPersonaNatural(Integer secPersonaNatural) {
		StringBuffer sql = new StringBuffer(200);
		sql.append("select d from InformacionAdicionalSd d where ");
		sql.append("d.secPersonaNatural.secPersonaNatural = :secPersonaNatural ");

		Query query = em.createQuery(sql.toString());
		query.setParameter("secPersonaNatural", secPersonaNatural);

		List<InformacionAdicionalSd> InformacionAdicionalList = query.getResultList();

		if (InformacionAdicionalList != null && !InformacionAdicionalList.isEmpty())
			return InformacionAdicionalList.get(0);

		return null;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.dao.InformacionAdicionalSdDao#crearInformacionAdicional
	 * (InformacionAdicionalSd)
	 */
	/*
	@Override
	public boolean crearInformacionAdicional(InformacionAdicionalSd inf) {

		String hql = "INSERT INTO INFORMACION_ADICIONAL("
				+ "SEC_PERSONA_NATURAL, COD_TIPO_IDENTIFICACION, IDENTIFICACION, RAZON_SOCIAL, NOMBRE_COMERCIAL,"
				+ "FCH_INSCRIPCION, FCH_INICIO_ACTIVIDADES, FCH_CANCELACION, FCH_SUSPENSION, FCH_REINICIO,"
				+ "PRINCIPAL, NUMERO, SECUNDARIA, REFERENCIA, TELEFONO,"
				+ "E_MAIL, COD_ACTIVIDAD_ECONOMICA, SEC_PROVINCIA, SEC_CANTON, SEC_PARROQUIA,"
				+ "SEC_CANAL, USR_CREACION,TS_CREACION,USR_MODIFICACION) VALUES ("
				+ inf.getSecPersonaNatural().getSecPersona().getSecPersona() +",''"+inf.getCodTipoIdentificacion().getCodTipoIdentificacion() +"','"+inf.getIdentificacion()+"','"+inf.getRazonSocial()+"','"+inf.getNombreComercial()+"',"
				+ "'"+inf.getFchInscripcion()+"','"+inf.getFchInicioActividades()+"','"+inf.getFchCancelacion()+"','"+inf.getFchSuspension()+"','"+ inf.getFchReinicio()+"',"
				+ "'"+inf.getPrincipal()+"','"+inf.getNumero()+"','"+inf.getSecundaria()+"','"+ inf.getReferencia()+"','"+ inf.getTelefono()+"',"
				+ "'"+inf.getEMail()+"',"+inf.getCodActividadEconomica().getCodActividadEconomica()+","+inf.getSecProvincia().getSecProvincia()+","+inf.getSecCanton().getSecCanton() +","+inf.getSecParroquia().getSecParroquia()+","
				+ ""+inf.getSecCanal().getSecCanal()+",'"+inf.getUsrCreacion()+"','"+inf.getTsCreacion()+"','"+inf.getUsrModificacion()+"'"
				+")";

		Query insert = em.createNativeQuery(hql);
		insert.executeUpdate();

		return true;
	}*/
}