package siat.view.backing.utiles.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class Decimal_CeroValidator implements Validator {
    
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object object) {
        String cantidad = object.toString();
        double iCantidad = 0.0;
        try{
            iCantidad = Double.parseDouble(cantidad);
        }catch(Exception e){
            FacesMessage fm = new FacesMessage("Inserte solo números.");
            throw new ValidatorException(fm);
        }
        if (iCantidad < 0) {
            FacesMessage fm = new FacesMessage("Inserte un número entero positivo.");
            throw new ValidatorException(fm);
        }
    }
}