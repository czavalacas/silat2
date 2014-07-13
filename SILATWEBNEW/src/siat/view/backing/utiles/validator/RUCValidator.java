package siat.view.backing.utiles.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class RUCValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object object) {
        String cantidad = object.toString();
        Long iCantidad=0L;
        try{
            iCantidad = Long.parseLong(cantidad);            
        }catch(Exception e){
            FacesMessage fm = new FacesMessage("Inserte solo números.");
            throw new ValidatorException(fm);
        }
        if (cantidad.length() != 11) {
            FacesMessage fm = new FacesMessage("El Número de RUC debe tener exactamente 11 caracteres.");
            throw new ValidatorException(fm);
        }        
    }
}