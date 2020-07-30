/**
 * 
 */
package com.equivida.smartdata.helper;

import java.util.ArrayList;
import java.util.List;

import com.equivida.smartdata.model.DireccionSd;
import com.equivida.smartdata.model.DireccionTelefonoSd;
import com.equivida.smartdata.model.TelefonoSd;

/**
 * @author juan
 *
 */
public class SmartDataHelper {

	/**
	 * Se cargan los telefonos a las direcciones.
	 * 
	 * @param telefonoList
	 * @param direccionList
	 */
	public static void cargarDireccionTelefonoAdireccion(List<TelefonoSd> telefonoList,
			List<DireccionSd> direccionList) {
		if (direccionList != null && !direccionList.isEmpty() && telefonoList != null && !telefonoList.isEmpty()) {
			for (DireccionSd d : direccionList) {

				d.setDireccionTelefonoList(new ArrayList<DireccionTelefonoSd>());

				for (TelefonoSd t : telefonoList) {
					if (t.getDireccionTelefonoList() != null && !t.getDireccionTelefonoList().isEmpty()) {
						for (DireccionTelefonoSd dt : t.getDireccionTelefonoList()) {
							if (dt.getDireccion().getSecDireccion().equals(d.getSecDireccion())) {
								d.getDireccionTelefonoList().add(dt);
							}
						}
					}
				}
			}
		}
	}
}
