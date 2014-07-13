package siat.view.backing.utiles.validator;

import java.math.BigDecimal;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import javax.naming.Context;
import javax.naming.InitialContext;

import siat.view.backing.utiles.Utils;

import silat.servicios_negocio.BDLSF.IR.BDL_C_SFFlotaRemote;
import silat.servicios_negocio.Beans.BeanUsuarioAutenticado;
import silat.servicios_negocio.LNSF.IR.LN_C_SFClaveRemoto;
import silat.servicios_negocio.LNSF.IR.LN_C_SFUsuarioRemote;

public class ValidatorContraseñaActual implements Validator {
    private final static String LOOKUP_NAME_SFCLAVE_REMOTO = "LUBAL_SIAT_APP-SILATNEGOCIO-LN_C_SFClave";//#silat.servicios_negocio.LNSF.IR.LN_C_SFClaveRemoto
    private LN_C_SFClaveRemoto ln_C_SFClaveRemoto;
    private LN_C_SFUsuarioRemote ln_C_SFUsuarioRemote;
    private final static String LOOKUP_NAME_SFUSUARIO_REMOTO = "mapLNSFUsuario";//#silat.servicios_negocio.LNSF.IR.LN_C_SFUsuarioRemote
    private BeanUsuarioAutenticado beanUsuario_Sess = (BeanUsuarioAutenticado)Utils.getSession("USER");

    public ValidatorContraseñaActual() {
        try {
            final Context ctx;
            ctx = new InitialContext();
            ln_C_SFUsuarioRemote = (LN_C_SFUsuarioRemote)ctx.lookup(LOOKUP_NAME_SFUSUARIO_REMOTO);
            ln_C_SFClaveRemoto = (LN_C_SFClaveRemoto)ctx.lookup(LOOKUP_NAME_SFCLAVE_REMOTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object object) {
        String dato = object.toString();
        String cUsuario = beanUsuario_Sess.getCUsuario();
        BeanUsuarioAutenticado beanUsuario = new BeanUsuarioAutenticado();
        beanUsuario = ln_C_SFUsuarioRemote.autenticarUsuario(cUsuario, dato/*, nidRol*/);//TODO usar un metodo menos pesado
        String error = beanUsuario.getOutput();
        if (beanUsuario_Sess != null) {
            if (!error.equals("000")) {
                FacesMessage fm = new FacesMessage("Contraseña Actual Incorrecta.");
                throw new ValidatorException(fm);
            }
        }
    }

}
