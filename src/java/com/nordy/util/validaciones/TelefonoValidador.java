/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nordy.util.validaciones;

import com.nordy.util.testVal;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator
public class TelefonoValidador implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String cadena = (String)value; 
        if(testVal.validarNumeros(cadena)||(cadena.length()<7)){
            throw  new ValidatorException(new FacesMessage("Verifique numero de telefono")); 
        }
        
    }
    
}
