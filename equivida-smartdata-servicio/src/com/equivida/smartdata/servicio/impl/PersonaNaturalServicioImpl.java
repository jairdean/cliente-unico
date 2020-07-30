/**
 *ActividadEconomicaservicioImpl.java
 *
 *Tue Jan 26 12:18:50 ECT 2016
 */
package com.equivida.smartdata.servicio.impl;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.Stateless;
import javax.ejb.EJB;

import java.util.List;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.equivida.smartdata.model.PersonaNaturalSd;
import com.equivida.smartdata.servicio.PersonaNaturalServicio;



@Stateless(name = "PersonaNaturalServicioImpl")
public class PersonaNaturalServicioImpl implements PersonaNaturalServicio  {


	@Override
	public PersonaNaturalSd consumirServicio(String noDocumento) {
		PersonaNaturalSd persona = new PersonaNaturalSd();
		persona.setIdentificacion("1719130476");
		return persona;
	}
	
}
