/**
 * 
 */
package com.equivida.intranet.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * @author Daniel Cardenas
 *
 */
@FacesValidator("telefonoValidator")
public class TelefonoValidator implements Validator {

	private static final String PATTERN = "[0-9]+";

	@Override
	public void validate(FacesContext facesContext, UIComponent uiComponent, Object object) throws ValidatorException {

		if (object != null) {

			String telefono = object.toString();

			if (telefono.trim().equals("")) {
				// no valida
				return;
			}

			boolean valido = validar(telefono);
			if (!valido) {
				String m = "Tel\u00E9fono no v\u00E1lido";
				FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, m, m);
				throw new ValidatorException(fm);
			}

			// solicitado por email del 23-ago-2019 //pruebas de contrantante
			if (uiComponent.getId().equals("txtCelularPJ")) {
				if (telefono.length() != 8) {
					String m = "Tel\u00E9fono no v\u00E1lido (debe ser 8 d\u00EDgitos)";
					FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, m, m);
					throw new ValidatorException(fm);
				}
			}

			// solicitado por email del 23-ago-2019 //pruebas de contrantante
			if (uiComponent.getId().equals("txtCelularPN")) {
				if (telefono.length() != 8) {
					String m = "Tel\u00E9fono no v\u00E1lido (debe ser 8 d\u00EDgitos)";
					FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, m, m);
					throw new ValidatorException(fm);
				}
			}
			if (uiComponent.getId().equals("txtTelefonoDireccionPN")) {
				if (telefono.length() != 7) {
					String m = "Tel\u00E9fono no v\u00E1lido (debe ser 7 d\u00EDgitos)";
					FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, m, m);
					throw new ValidatorException(fm);
				}
			}

		}

	}

	/**
	 * Validate given email with regular expression.
	 * 
	 * @param email email for validation
	 * @return true valid email, otherwise false
	 */
	public static boolean validar(String email) {

		// Compiles the given regular expression into a pattern.
		Pattern pattern = Pattern.compile(PATTERN);

		// Match the given input against this pattern
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();

	}
}
