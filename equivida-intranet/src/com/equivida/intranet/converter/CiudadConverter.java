package com.equivida.intranet.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.equivida.constant.Constantes;
import com.equivida.intranet.ctrl.ContratantePJCtrl;
import com.equivida.intranet.ctrl.PersonaNaturalCtrl;
import com.equivida.modelo.Ciudad;
import com.equivida.modelo.Pais;

@FacesConverter(value = "ciudadConverter")
public class CiudadConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent arg1, String ciudad) {

		String idComponent = arg1.getId();

		PersonaNaturalCtrl personaNaturalCtrl = (PersonaNaturalCtrl) facesContext.getApplication()
				.evaluateExpressionGet(facesContext, "#{personaNaturalCtrl}", PersonaNaturalCtrl.class);

		// Para el formulario de contratante juridica
		ContratantePJCtrl contratantePJCtrl = (ContratantePJCtrl) facesContext.getApplication()
				.evaluateExpressionGet(facesContext, "#{contratantePJCtrl}", ContratantePJCtrl.class);

		Ciudad cm = null;
		if (personaNaturalCtrl.getCiudadesMap() != null) {
			cm = personaNaturalCtrl.getCiudadesMap().get(ciudad);
			if (cm == null) {
				cm = verificarSiNoEncuentraEnConMapa(idComponent, personaNaturalCtrl);
			}
		} else {
			cm = verificarSiNoEncuentraEnConMapa(idComponent, personaNaturalCtrl);
		}

		if (cm == null) {
			if (contratantePJCtrl.getCiudadesMap() != null) {
				cm = contratantePJCtrl.getCiudadesMap().get(ciudad);
				if (cm == null) {
					cm = verificarSiNoEncuentraEnConMapa(idComponent, contratantePJCtrl);
				}
			} else {
				cm = verificarSiNoEncuentraEnConMapa(idComponent, contratantePJCtrl);
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
		if (cm.getPais() != null) {
			c.setPais(new Pais(cm.getPais().getCodPais(), cm.getPais().getPais()));
		} else {
			c.setPais(new Pais(Constantes.PAIS_ID_NO_DISPONIBLE, "No Disponible"));
		}

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

	private Ciudad verificarSiNoEncuentraEnConMapa(String idComponent, Object ctrl) {
		Ciudad cm = null;
		if (ctrl instanceof PersonaNaturalCtrl) {
			PersonaNaturalCtrl personaNaturalCtrl = (PersonaNaturalCtrl) ctrl;

			if (idComponent.equals("autoCiudadFN")) {
				if (personaNaturalCtrl.getPersonaNatural().getCiudadNacimiento() != null) {
					cm = personaNaturalCtrl.getPersonaNatural().getCiudadNacimiento();
				}
			}

		} else if (ctrl instanceof ContratantePJCtrl) {
			ContratantePJCtrl contratantePJCtrl = (ContratantePJCtrl) ctrl;

			if (idComponent.equals("autoCiudadFNRl")) {

				if (contratantePJCtrl.getPersonaJuridica().getRepresentante().getPersonaNaturalTransient()
						.getCiudadNacimiento() != null) {
					cm = contratantePJCtrl.getPersonaJuridica().getRepresentante().getPersonaNaturalTransient()
							.getCiudadNacimiento();
				}
			}

		}

		return cm;
	}
}
