/**
 * 
 */
package com.equivida.intranet.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.equivida.util.EmailUtils;

/**
 * @author Daniel Cardenas
 *
 */
@FacesValidator("emailValidator")
public class EmailValidator implements Validator {

	@Override
	public void validate(FacesContext facesContext, UIComponent uiComponent, Object object) throws ValidatorException {

		if (object != null) {
			String email = object.toString();
			if (email.length() > 1) {
				boolean valido = EmailUtils.validar(email);
				if (!valido) {
					String m = "Correo no v\u00E1lido";
					FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, m, m);
					throw new ValidatorException(fm);
				}
			}
		}

	}

}
