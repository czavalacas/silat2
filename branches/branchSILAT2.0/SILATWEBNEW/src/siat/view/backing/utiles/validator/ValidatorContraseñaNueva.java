package siat.view.backing.utiles.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import javax.naming.Context;
import javax.naming.InitialContext;

import siat.view.backing.utiles.Utils;

import silat.servicios_negocio.Beans.BeanUsuarioAutenticado;

public class ValidatorContraseñaNueva implements Validator {
    
    public ValidatorContraseñaNueva(){
        try{
            final Context ctx;
            ctx = new InitialContext();           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object object) {
        String dato = object.toString();  System.out.println("dato:"+dato);
        if (dato.length() < 6) {
            FacesMessage fm = new FacesMessage("Contraseñana debe tener como minimo 6 caracteres.");
            throw new ValidatorException(fm);
        }else{
            Utils.putSession("CONTRASEÑA_NUEVA", dato); 
        }      
    }
}
