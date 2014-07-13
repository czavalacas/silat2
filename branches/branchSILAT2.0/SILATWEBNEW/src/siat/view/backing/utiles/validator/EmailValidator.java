package siat.view.backing.utiles.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Clase validadora de formato de correos electrónicos
 * @author dfloresgonz
 */
public class EmailValidator implements Validator {
    
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object object) {
        String dato = object.toString();
        if (!dato.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            throw new ValidatorException(new FacesMessage("El e-mail tiene formato Incorrecto."));
        }
    }
}