package com.equivida.intranet.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.equivida.intranet.ctrl.AutocompleteCtrl;
import com.equivida.intranet.ctrl.ContratantePJCtrl;
import com.equivida.intranet.ctrl.ContratantePNCtrl;
import com.equivida.intranet.ctrl.PersonaNaturalCtrl;
import com.equivida.modelo.Pais;

@FacesConverter(value = "paisConverter")
public class PaisConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent arg1, String pais) {

		String idComponent = arg1.getId();

		AutocompleteCtrl autocompleteCtrl = (AutocompleteCtrl) facesContext.getApplication()
				.evaluateExpressionGet(facesContext, "#{autocompleteCtrl}", AutocompleteCtrl.class);

		PersonaNaturalCtrl personaNaturalCtrl = (PersonaNaturalCtrl) facesContext.getApplication()
				.evaluateExpressionGet(facesContext, "#{personaNaturalCtrl}", PersonaNaturalCtrl.class);

		// Para el formulario de contratante
		ContratantePNCtrl contratantePNCtrl = (ContratantePNCtrl) facesContext.getApplication()
				.evaluateExpressionGet(facesContext, "#{contratantePNCtrl}", ContratantePNCtrl.class);

		// Para el formulario de contratante juridica
		ContratantePJCtrl contratantePJCtrl = (ContratantePJCtrl) facesContext.getApplication()
				.evaluateExpressionGet(facesContext, "#{contratantePJCtrl}", ContratantePJCtrl.class);

		Pais pm = null;
		if (autocompleteCtrl.getPaisesMap() != null) {
			pm = autocompleteCtrl.getPaisesMap().get(pais);
			if (pm == null) {
				pm = verificarSiNoEncuentraEnConMapa(idComponent, personaNaturalCtrl);
			}
		} else {
			pm = verificarSiNoEncuentraEnConMapa(idComponent, personaNaturalCtrl);
		}

		if (pm == null) {
			pm = verificarSiNoEncuentraEnConMapa(idComponent, contratantePNCtrl);
		}
		
		if (pm == null) {
			pm = verificarSiNoEncuentraEnConMapa(idComponent, contratantePJCtrl);
		}

		if (pm == null) {
			// p = new ArrayList<Pais>(autocompleteCtrl.getPaisesMap().values())
			// .get(0);
			FacesMessage fm = new FacesMessage("No existe " + pais, "No existe " + pais);
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			// personaNaturalCtrl.getPersonaNatural().getCiudadNacimiento().getPais().setCodPais(null);
			throw new ConverterException(fm);
		}

		Pais p = new Pais();
		p.setCodPais(pm.getCodPais());
		p.setPais(pm.getPais());

		// inicia valores que dependan de otros
		return p;
	}

	private Pais verificarSiNoEncuentraEnConMapa(String idComponent, Object ctrl) {
		Pais pm = null;
		if (ctrl instanceof PersonaNaturalCtrl) {
			PersonaNaturalCtrl personaNaturalCtrl = (PersonaNaturalCtrl) ctrl;

			if (idComponent.equals("autoPaisNAC")) {
				if (personaNaturalCtrl.getPersonaNatural().getPaisNacionalidad() != null) {
					pm = personaNaturalCtrl.getPersonaNatural().getPaisNacionalidad();
				}
			}

			if (idComponent.equals("autoPaisFN")) {
				if (personaNaturalCtrl.getPersonaNatural().getCiudadNacimiento().getPais() != null) {
					pm = personaNaturalCtrl.getPersonaNatural().getCiudadNacimiento().getPais();
				}
			}
		} else if (ctrl instanceof ContratantePNCtrl) {
			ContratantePNCtrl contratantePNCtrl = (ContratantePNCtrl) ctrl;

			if (idComponent.equals("autoPaisPN")) {
				if (contratantePNCtrl.getPersonaNatural().getCiudadNacimiento().getPais() != null) {
					pm = contratantePNCtrl.getPersonaNatural().getCiudadNacimiento().getPais();
				}
			}

			if (idComponent.equals("autoPaisNACPN")) {
				if (contratantePNCtrl.getPersonaNatural().getPaisNacionalidad() != null) {
					pm = contratantePNCtrl.getPersonaNatural().getPaisNacionalidad();
				}
			}
		} else if (ctrl instanceof ContratantePJCtrl) {
			ContratantePJCtrl contratantePJCtrl = (ContratantePJCtrl) ctrl;

			if (idComponent.equals("autoPaisFN")) {

				if (contratantePJCtrl.getPersonaJuridica().getRepresentante().getPersonaNaturalTransient()
						.getCiudadNacimiento().getPais() != null) {
					pm = contratantePJCtrl.getPersonaJuridica().getRepresentante().getPersonaNaturalTransient()
							.getCiudadNacimiento().getPais();
				}
			}

			if (idComponent.equals("autoPaisNAC")) {
				if (contratantePJCtrl.getPersonaJuridica().getRepresentante().getPersonaNaturalTransient()
						.getPaisNacionalidad() != null) {
					pm = contratantePJCtrl.getPersonaJuridica().getRepresentante().getPersonaNaturalTransient()
							.getPaisNacionalidad();
				}
			}
			
			if (idComponent.equals("autoPaisOrigenEmp")) {
				if (contratantePJCtrl.getPersonaJuridica().getRepresentante().getPersonaNaturalTransient()
						.getPaisNacionalidad() != null) {
					pm = contratantePJCtrl.getPersonaJuridica().getRepresentante().getPersonaNaturalTransient()
							.getPaisNacionalidad();
				}
			}
			
			
		}

		return pm;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if (obj == null) {
			return null;
		}
		Pais p = (Pais) obj;
		return p.getPais();
	}
}
