package siat.view.backing.administrativo.usuarios;

import java.math.BigDecimal;

import javax.faces.context.FacesContext;

import javax.naming.Context;
import javax.naming.InitialContext;

import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import siat.view.backing.utiles.Utils;
import siat.view.backing.administrativo.permisos.Frm_administrar_permisos;

import silat.servicios_negocio.Beans.BeanClave;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanUsuarioAutenticado;
import silat.servicios_negocio.LNSF.IR.LN_C_SFClaveRemoto;
import silat.servicios_negocio.LNSF.IR.LN_C_SFPermisosBeanRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFUsuarioRemote;
import silat.servicios_negocio.LNSF.IR.LN_T_SFClaveRemoto;
import silat.servicios_negocio.LNSF.IR.LN_T_SFPermisosRemoto;

public class Frm_modificar_usuario {
    private RichOutputText outTittle;
    private RichInputText inputClavActual;
    private RichInputText inputClavNueva;
    private RichInputText inputRepetirClav;
    private RichCommandButton cb1;
    private LN_T_SFClaveRemoto ln_T_SFClaveRemoto = null;
    private final static String LOOKUP_NAME_SFCLAVE_REMOTO =
        "LUBAL_SIAT_APP-SILATNEGOCIO-LN_T_SFClave";//#silat.servicios_negocio.LNSF.IR.LN_T_SFClaveRemoto
    private LN_C_SFClaveRemoto ln_C_SFClaveRemoto;
    private final static String LOOKUP_NAME_SFCLAVEC_REMOTO =
        "LUBAL_SIAT_APP-SILATNEGOCIO-LN_C_SFClave";//#silat.servicios_negocio.LNSF.IR.LN_C_SFClaveRemoto
    private BeanUsuarioAutenticado beanUsuario = (BeanUsuarioAutenticado) Utils.getSession("USER");
    FacesContext ctx = FacesContext.getCurrentInstance();
    private SessionScopeBeanModificarUsuario beanSessionScopeModificarUsuario;

    public Frm_modificar_usuario() {
        try {
            final Context ctx;
            ctx = new InitialContext();
            ln_T_SFClaveRemoto = (LN_T_SFClaveRemoto)ctx.lookup(LOOKUP_NAME_SFCLAVE_REMOTO);
            ln_C_SFClaveRemoto = (LN_C_SFClaveRemoto)ctx.lookup(LOOKUP_NAME_SFCLAVEC_REMOTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void resetarCamposClave(){
        beanSessionScopeModificarUsuario.setContraselaNueva("");
        beanSessionScopeModificarUsuario.setContraseñaActual("");
        beanSessionScopeModificarUsuario.setRepetirContraseñaNueva("");
        inputClavActual.resetValue();
        inputClavNueva.resetValue();
        inputRepetirClav.resetValue();
        Utils.addTargetMany(inputClavActual,inputClavNueva,inputRepetirClav);
    }
    
    public String actualizarContraseña() {
        try {
            //String cUsuario = beanUsuario.getCUsuario();
            String cClave = beanSessionScopeModificarUsuario.getContraselaNueva();
           // BigDecimal nidClave;
           // nidClave = ln_C_SFClaveRemoto.claveDeUsuarioEnSesion(beanUsuario.getNidUsuario());
            BeanClave bClave = ln_T_SFClaveRemoto.grabarNuevaContraseña(null, cClave, beanUsuario.getNidUsuario());
            resetarCamposClave();
            if (bClave.getBeanError() != null) {
                BeanError error = bClave.getBeanError();    
                int severidad = 0;
                if ("000".equals(error.getCidError())) {   
                    severidad = 3;
                    Utils.depurar("CONTRASEÑA CAMBIADA");
                }else {
                    severidad = 1;
                }
                Utils.throwError_Aux(ctx, error.getCDescripcionError(), severidad);
            } else {
                Utils.throwError_Aux(ctx, "Error Inesperado", 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Utils.throwError_Aux(ctx, "Error Inesperado", 1);
        }
        return null;
    }

    public void setBeanSessionScopeModificarUsuario(SessionScopeBeanModificarUsuario beanSessionScopeModificarUsuario) {
        this.beanSessionScopeModificarUsuario = beanSessionScopeModificarUsuario;
    }

    public SessionScopeBeanModificarUsuario getBeanSessionScopeModificarUsuario() {
        return beanSessionScopeModificarUsuario;
    }

    public void setOutTittle(RichOutputText ot1) {
        this.outTittle = ot1;
    }

    public RichOutputText getOutTittle() {
        return outTittle;
    }

    public void setInputClavActual(RichInputText it1) {
        this.inputClavActual = it1;
    }

    public RichInputText getInputClavActual() {
        return inputClavActual;
    }

    public void setInputClavNueva(RichInputText it2) {
        this.inputClavNueva = it2;
    }

    public RichInputText getInputClavNueva() {
        return inputClavNueva;
    }

    public void setInputRepetirClav(RichInputText it3) {
        this.inputRepetirClav = it3;
    }

    public RichInputText getInputRepetirClav() {
        return inputRepetirClav;
    }

    public void setCb1(RichCommandButton cb1) {
        this.cb1 = cb1;
    }

    public RichCommandButton getCb1() {
        return cb1;
    }
}