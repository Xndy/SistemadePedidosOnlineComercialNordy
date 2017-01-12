
package com.nordy.util.validaciones;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import com.nordy.util.testVal;

@FacesValidator
public class Numero_Min_Max implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String cadena = (String)value;
        if(testVal.validarNumerosLongitud(cadena, 7, 10)){
            if(testVal.validarNumeroCedula(cadena)){
                throw new ValidatorException(new FacesMessage("Confirme que su dato ingresado sea correcto"));
            }
            
        }
    }
    
}
