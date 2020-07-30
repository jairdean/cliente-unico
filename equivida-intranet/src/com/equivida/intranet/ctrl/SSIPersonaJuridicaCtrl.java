package com.equivida.intranet.ctrl;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.equivida.constant.Constantes;
import com.equivida.intranet.util.BasePersonaCtrl;
import com.equivida.modelo.ActividadEconomica;
import com.equivida.modelo.Pais;
import com.equivida.modelo.PerfilFinancieroJuridica;
import com.equivida.modelo.PersonaJuridica;
import com.equivida.modelo.TipoIdentificacion;
import com.equivida.modelo.TipoPersonaJuridica;
import com.equivida.servicio.PaisServicio;
import com.equivida.servicio.PersonaJuridicaServicio;
import com.equivida.servicio.SeguroIndividualServicio;

@ManagedBean(name = "SSIPersonaJuridicaCtrl")
@ViewScoped
public class SSIPersonaJuridicaCtrl extends BasePersonaCtrl {

	private static final long serialVersionUID = 8599657567289786101L;

	@EJB(mappedName = "PaisServicio/local")
	private PaisServicio paisServicio;

	@EJB(mappedName = "SeguroIndividualServicio/local")
	private SeguroIndividualServicio seguroIndividualServicio;

	@EJB(mappedName = "PersonaJuridicaServicio/local")
	private PersonaJuridicaServicio personaJuridicaServicio;

	private PersonaJuridica personaJuridica;

	private Integer paramSecPersona;// para edicion

	public String guardar() {

		// Collection<DireccionTelefono> tmtDireccionTelefono = persona
		// .getDireccionTelefonoCollection();

		// for (DireccionTelefono direccionTelefono : tmtDireccionTelefono) {
		// if (direccionTelefono.getTelefono().getPrincipal()) {
		// preferido = direccionTelefono.getTelefono();
		// break;
		// }
		// }

		// Collection<Telefono> tmpTelefonoAdicionales = persona
		// .getTelefonoSinDireccionCollection();

		// for (Telefono telefono : tmpTelefonoAdicionales) {
		// if (telefono.getPrincipal()) {
		// preferido = telefono;
		// break;
		// }
		// }

		// a telf q es contacto preferido (principal)
		// persona.getContactoPreferido().setTipoContactoPreferido(
		// tipoContactoPreferido);
		// persona.getContactoPreferido().setPersona(persona);
		// persona.getContactoPreferido().setTelefono(preferido);

		seguroIndividualServicio.crearSolicitudPersonaJuridica(personaJuridica);

		String mensaje = getBundleMensajes("registro.guardado.correctamente",
				new Object[] { personaJuridica.getRazonSocial() });
		addInfoMessage(null, mensaje, mensaje);

		return null;
	}

	public void seleccionarPaisNAC() {
		String lnn = getPersonaJuridica().getPais().getPais();
		String[] arr = lnn.split("\\|");
		getPersonaJuridica().getPais().setCodPais(Short.parseShort(arr[0]));
		getPersonaJuridica().getPais().setPais(arr[1]);

	}

	public PersonaJuridica getPersonaJuridica() {
		if (personaJuridica == null) {
			// verifica si se manda parametro -> edicion
			String paramSecPersonaString = FacesContext.getCurrentInstance().getExternalContext()
					.getRequestParameterMap().get("p");

			System.out.println("paramSecPersonaString:" + paramSecPersonaString);

			// si es nuevo
			if (paramSecPersonaString == null || paramSecPersonaString.equals("")) {
				personaJuridica = new PersonaJuridica();

				TipoPersonaJuridica tipoPersonaJuridica = new TipoPersonaJuridica();
				personaJuridica.setTipoPersonaJuridica(tipoPersonaJuridica);

				// perfil financiero
				PerfilFinancieroJuridica perfilFinancieroJuridica = new PerfilFinancieroJuridica();
				perfilFinancieroJuridica.setPersonaJuridica(personaJuridica);
				personaJuridica.setPerfilFinancieroJuridicaTransient(perfilFinancieroJuridica);

				TipoIdentificacion tipoIdentificacion = new TipoIdentificacion(Constantes.TIPO_IDENTIFICACION_RUC);

				personaJuridica.setTipoIdentificacion(tipoIdentificacion);

				Pais pais = paisServicio.findByPk(Constantes.PAIS_ID_ECUADOR);
				personaJuridica.setPais(pais);

				personaJuridica.setActividadEconomica(new ActividadEconomica());

				personaJuridica.setEmailContacto(new String());

				initPersonaParaFormulario(tipoIdentificacion);
				// personaJuridica.setPersona(persona);
			} else {
				// cuando es edicion, es decir hay parametro 'paramSecPersona'
				paramSecPersona = Integer.parseInt(paramSecPersonaString);
				personaJuridica = personaJuridicaServicio.findByPk(paramSecPersona, true, true, true, true, true);
			}

		}
		return personaJuridica;
	}

	public void setPersonaJuridica(PersonaJuridica personaJuridica) {
		this.personaJuridica = personaJuridica;
	}

	public Integer getParamSecPersona() {
		return paramSecPersona;
	}

	public void setParamSecPersona(Integer paramSecPersona) {
		this.paramSecPersona = paramSecPersona;
	}
}