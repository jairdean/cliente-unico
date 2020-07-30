/**
 * 
 */
package com.equivida.intranet.ctrl;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

import com.equivida.intranet.util.BaseCtrl;
import com.equivida.modelo.PersonaJuridica;
import com.equivida.servicio.PersonaJuridicaServicio;
import com.equivida.util.FileReceptor;

/**
 * @author daniel
 * 
 */
@ManagedBean(name = "personaJuridicaColectivoCtrl")
@ViewScoped
public class PersonaJuridicaColectivoCtrl extends BaseCtrl {

	private static final long serialVersionUID = -6875246582259759823L;

	@EJB(mappedName = "PersonaJuridicaServicio/local")
	private PersonaJuridicaServicio personaJuridicaServicio;

	private PersonaJuridica personaJuridica;

	private FileReceptor archivo;

	public PersonaJuridica getPersonaJuridica() {
		if (personaJuridica == null) {
			String paramSecPersonaString = FacesContext.getCurrentInstance()
					.getExternalContext().getRequestParameterMap().get("p");

			personaJuridica = personaJuridicaServicio.findByPk(Integer
					.parseInt(paramSecPersonaString));
		}
		return personaJuridica;
	}

	/**
	 * Carga el archivo a la propiedad file de este controlador.
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void fileListener(FileUploadEvent event) throws Exception {
		UploadedFile item = event.getUploadedFile();
		if (item != null) {
			archivo = new FileReceptor();
			archivo.setLength(new Long(item.getSize()));
			archivo.setName(item.getName());
			archivo.setMime(item.getContentType());
			archivo.setData(item.getData());
		}
	}

	public void setPersonaJuridica(PersonaJuridica personaJuridica) {
		this.personaJuridica = personaJuridica;
	}

}