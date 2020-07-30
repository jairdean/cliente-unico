/**
 * 
 */
package com.equivida.intranet.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.DoubleRangeValidator;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

/**
 * @author daniel
 * 
 */
@FacesValidator("bindableDoubleRangeValidator")
public class NumeroValidator extends DoubleRangeValidator {

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		setMinimum((Double) component.getAttributes().get("minimum"));
		setMaximum((Double) component.getAttributes().get("maximum"));
		super.validate(context, component, value);
	}

}
