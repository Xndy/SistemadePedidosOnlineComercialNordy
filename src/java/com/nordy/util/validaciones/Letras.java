
package com.nordy.util.validaciones;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import com.nordy.util.testVal;

@FacesValidator
public class Letras implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String cadena = (String) value;
        if(testVal.validarLetra(cadena)){
            throw new ValidatorException(new FacesMessage("Dato incorrecto"));
        }
        if(cadena.length()<1){
            throw new ValidatorException(new FacesMessage("Revise q no existan campos vacios"));
        }
    }
}
