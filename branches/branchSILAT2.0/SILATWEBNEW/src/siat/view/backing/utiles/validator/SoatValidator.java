package siat.view.backing.utiles.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class SoatValidator implements Validator {
    
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object object) {
        String cantidad = object.toString();
        Long iCantidad = 0L;
        try{
            iCantidad = Long.parseLong(cantidad);  
        }catch(Exception e){
            FacesMessage fm = new FacesMessage("Inserte solo números.");
            throw new ValidatorException(fm);
        }
        if (iCantidad < 1) {
            FacesMessage fm = new FacesMessage("Inserte un número entero mayor a 0.");
            throw new ValidatorException(fm);
        }
    }
}