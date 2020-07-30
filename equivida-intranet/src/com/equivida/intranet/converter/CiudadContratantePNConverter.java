package com.equivida.intranet.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.equivida.intranet.ctrl.ContratantePNCtrl;
import com.equivida.modelo.Ciudad;
import com.equivida.modelo.Pais;

@FacesConverter(value = "ciudadContratantePNConverter")
public class CiudadContratantePNConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent arg1, String ciudad) {

		ContratantePNCtrl contratantePNCtrl = (ContratantePNCtrl) facesContext.getApplication()
				.evaluateExpressionGet(facesContext, "#{contratantePNCtrl}", ContratantePNCtrl.class);

		Ciudad cm = null;
		if (contratantePNCtrl.getCiudadesMap() != null) {
			cm = contratantePNCtrl.getCiudadesMap().get(ciudad);
		} else {
			if (contratantePNCtrl.getPersonaNatural().getCiudadNacimiento() != null) {
				cm = contratantePNCtrl.getPersonaNatural().getCiudadNacimiento();
			} else {
				cm = null;
			}
		}

		if (cm == null) {
			FacesMessage fm = new FacesMessage("No existe " + ciudad, "No existe " + ciudad);
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ConverterException(fm);
		}

		Ciudad c = new Ciudad();
		c.setSecCiudad(cm.getSecCiudad());
		c.setCiudad(cm.getCiudad());
		c.setPais(new Pais(cm.getPais().getCodPais(), cm.getPais().getPais()));

		return c;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		String resp = "";

		Ciudad p = (Ciudad) obj;
		if (p != null && p.getCiudad() != null) {
			resp = p.getCiudad();
		}

		return resp;
	}
}
