package siat.view.backing.utiles.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class ComboValidator implements Validator{

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object object) {
        String dato = object.toString();
        if (dato.matches("-1")) {
            throw new ValidatorException(new FacesMessage("(*) Debe seleccionar una opción."));
        }
    }
}