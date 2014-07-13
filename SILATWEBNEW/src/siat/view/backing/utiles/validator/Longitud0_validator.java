package siat.view.backing.utiles.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import siat.view.backing.utiles.Utils;

/**
 * Validator de longitud mayor a 0.
 * @author dfloresgonz
 */
public class Longitud0_validator implements Validator {
    
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object object) {
        String cantidad = object.toString();
        int iCantidad = 0;
        try{
            iCantidad = Integer.parseInt(cantidad);
        }catch(Exception e){
            FacesMessage fm = new FacesMessage("Inserte solo numeros.");
            throw new ValidatorException(fm);
        }
        if (iCantidad < 1) {
            FacesMessage fm = new FacesMessage("Inserte un numero entero mayor a 0.");
            throw new ValidatorException(fm);
        }
    }
}