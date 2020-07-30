package com.equivida.servicio.impl;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.DireccionTelefonoDao;
import com.equivida.modelo.DireccionTelefono;
import com.equivida.servicio.DireccionTelefonoServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Gerardo Tuquerrez
 */
@Stateless(name = "DireccionTelefonoServicio")
public class DireccionTelefonoServicioImpl extends GenericServiceImpl<DireccionTelefono, Integer>
		implements DireccionTelefonoServicio {

	@EJB
	private DireccionTelefonoDao direccionTelefonoDao;

	public GenericDao<DireccionTelefono, Integer> getDao() {
		return direccionTelefonoDao;
	}

	@Override
	public void guardarLista(Collection<DireccionTelefono> lista) {
		System.out.println("guardar lista:" + lista.size());
		if (lista != null && !lista.isEmpty()) {
			for (DireccionTelefono direccionTelefono : lista) {
				System.out.println("DT:" + direccionTelefono.toString());
				System.out.println("T:" + direccionTelefono.getTelefono().toString());

				System.out.println("Persona:" + direccionTelefono.getPersona());
				System.out.println("Persona sec:" + direccionTelefono.getPersona().getSecPersona());
				System.out.println("getDireccion:" + direccionTelefono.getDireccion());
				System.out.println("getDireccion sec:" + direccionTelefono.getDireccion().getSecDireccion());
				System.out.println("telefono :" + direccionTelefono.getTelefono());
				System.out.println("telefono sec:" + direccionTelefono.getTelefono().getSecTelefono());
				
				if (direccionTelefono.getPersona() != null && direccionTelefono.getPersona().getSecPersona() != null
						&& direccionTelefono.getDireccion() != null
						&& direccionTelefono.getDireccion().getSecDireccion() != null
						&& direccionTelefono.getTelefono() != null
						&& direccionTelefono.getTelefono().getSecTelefono() != null) {
					if (direccionTelefono.getSecDireccionTelefono() == null) {
						System.out.println("insert dt");
						getDao().save(direccionTelefono);
					} else {
						// solo si no es el mismo objecto
						if (!direccionTelefono.equals(direccionTelefono.getOriginal())) {
							System.out.println("update dt");
							direccionTelefonoDao.update(direccionTelefono);
							// direccionTelefonoDao.updateLigth(direccionTelefono);
						}
					}
				}
			}
		}
	}
}
