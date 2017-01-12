
package com.nordy.util.validaciones;

import com.nordy.util.testVal;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator
public class ValidadorCorreo implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String cadena=(String) value;
        if(testVal.correo(cadena)){
            throw  new ValidatorException(new FacesMessage("Correo Invalido"));
        }
        if(cadena.length()<5 ){
           throw  new ValidatorException(new FacesMessage("Correo Incorrecto")); 
        }
        
    }
    
}
