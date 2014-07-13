package siat.view.backing.utiles.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class LicenciaValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object object) {
        String cantidad = object.toString();          
        if (cantidad.length() != 9) {
            FacesMessage fm = new FacesMessage("El Número de Licencia debe tener exactamente 9 caracteres.");
            throw new ValidatorException(fm);
        } 
            if (!cantidad.matches("[a-zA-Z][0-9]{8,}")) {
            //     ("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
                throw new ValidatorException(new FacesMessage("La Licencia tiene formato Incorrecto. Tipo de formato Valido X12345678"));
            }
        }
}
