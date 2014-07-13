package siat.view.backing.utiles.validator;

import java.math.BigDecimal;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import javax.naming.Context;
import javax.naming.InitialContext;

import siat.view.backing.administrativo.usuarios.SessionScopeBeanModificarUsuario;
import siat.view.backing.utiles.Utils;

import silat.servicios_negocio.Beans.BeanUsuarioAutenticado;
import silat.servicios_negocio.LNSF.IR.LN_C_SFClaveRemoto;

public class ValidatorRepetirContraseña implements Validator {
    private SessionScopeBeanModificarUsuario beanSessionScopeModificarUsuario;
    private BeanUsuarioAutenticado beanUsuario = (BeanUsuarioAutenticado) Utils.getSession("USER");
    
    public ValidatorRepetirContraseña(){
        try{
            final Context ctx;
            ctx = new InitialContext();           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object object) {
        String dato = object.toString();
        String nuevaClave = (String) Utils.getSession("CONTRASEÑA_NUEVA");
        if (beanUsuario != null) {
            if (Utils.getSession("CONTRASEÑA_NUEVA") != null) {
                if (!dato.equals(nuevaClave)) {
                    FacesMessage fm = new FacesMessage("Vuelva a Escribir la Contraseña.");
                    throw new ValidatorException(fm);
                }
            }
        }
    }

    public void setBeanSessionScopeModificarUsuario(SessionScopeBeanModificarUsuario beanSessionScopeModificarUsuario) {
        this.beanSessionScopeModificarUsuario = beanSessionScopeModificarUsuario;
    }

    public SessionScopeBeanModificarUsuario getBeanSessionScopeModificarUsuario() {
        return beanSessionScopeModificarUsuario;
    }
}
