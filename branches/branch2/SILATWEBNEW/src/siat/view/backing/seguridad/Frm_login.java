package siat.view.backing.seguridad;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import weblogic.security.SimpleCallbackHandler;
import weblogic.security.services.Authentication;
import weblogic.servlet.security.ServletAuthentication;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import oracle.adf.controller.ControllerContext;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichDecorativeBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichMessages;
import siat.view.backing.utiles.ADFUtil;
import siat.view.backing.utiles.Utils;

import silat.servicios_negocio.Beans.BeanRol;
import silat.servicios_negocio.Beans.BeanUsuario;
import silat.servicios_negocio.Beans.BeanUsuarioAutenticado;
import silat.servicios_negocio.LNSF.IR.LN_C_SFRolRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFUsuarioRemote;

public class Frm_login {
    private RichPanelStretchLayout psl1;
    private RichForm f1;
    private RichDocument d1;
    private RichDecorativeBox db1;
    private RichPanelFormLayout pfl1;
    private RichInputText it1;
    private RichInputText it2;
    private RichCommandButton cb1;
    private RichMessages m1;
    private RichSelectOneChoice soc1;
    private UISelectItems si1;
    private RichPanelGroupLayout pgl2;
    private RichPanelFormLayout pfl2;
    private RichInputText it3;
    private RichSelectOneChoice soc2;
    private UISelectItems si2;
    private RichCommandButton cb2;
    private RichPanelGroupLayout pgl1;
    private RichActiveOutputText otError;
    /*Mis Variables*/
    private List listaRoles;
    private List ListRazones;
    private LN_C_SFRolRemote ln_C_SFRolRemote = null;
    private final static String LOOKUP_NAME_SFROL_REMOTO = "mapLN_C_SFRol";//#silat.servicios_negocio.LNSF.IR.LN_C_SFRolRemote
   
    private LN_C_SFUsuarioRemote ln_C_SFUsuarioRemote;
    private final static String LOOKUP_NAME_SFUSUA_REMOTO = "mapLNSFUsuario";//#silat.servicios_negocio.LNSF.IR.LN_C_SFRolRemote
    private String red = "";
    FacesContext contx = FacesContext.getCurrentInstance();
    ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
    private BeanUsuarioAutenticado beanUsuario = new BeanUsuarioAutenticado();
    private String usua;
    private String pwd;
    private String valorRol;
    private String errorTxt;
    private RichImage i1;
    private RichImage i2;
    private String redireccionar = "";

    public Frm_login(){
        try {
            final Context ctx;
            ctx = new InitialContext();
            ln_C_SFRolRemote = (LN_C_SFRolRemote) ctx.lookup(LOOKUP_NAME_SFROL_REMOTO);
            ln_C_SFUsuarioRemote = (LN_C_SFUsuarioRemote) ctx.lookup(LOOKUP_NAME_SFUSUA_REMOTO);
            this.setListaRoles(this.llenarRolesCombo());
            BeanUsuarioAutenticado beanUser = (BeanUsuarioAutenticado) Utils.getSession("USER");
            if(beanUser != null){
                logoutTarget();
            }
          //  Utils.depurar("locale ADF "+contx.getViewRoot().getLocale());
          //  Utils.depurar("222locale ADF "+Locale.getDefault());
            Locale.setDefault(contx.getViewRoot().getLocale());
          //  Utils.depurar("3333 locale ADF "+contx.getViewRoot().getLocale());
          //  Utils.depurar("44444 locale ADF "+Locale.getDefault());
           /* Utils.depurar("Client Address: "+getClientIpAddress());
            test();*/
        } catch (Exception e) {
           // e.printStackTrace();
        }
    }
    
   /* public BeanUsuarioAutenticado autenticarUsuario(){
        try{
            ADFUtil.setEL("#{pageFlowScope.usua}", getUsua());
            ADFUtil.setEL("#{pageFlowScope.pwd}", getPwd());
        //    ADFUtil.setEL("#{pageFlowScope.rolo}", getValorRol());
            Utils.mandarParametro("username", "#{pageFlowScope.usua}", "autenticarUsuario");
            Utils.mandarParametro("clave", "#{pageFlowScope.pwd}", "autenticarUsuario");
          //  Utils.mandarParametro("rol", "#{pageFlowScope.rolo}", "autenticarUsuario");
            beanUsuario = (BeanUsuarioAutenticado) ADFUtil.invokeEL("#{bindings.autenticarUsuario.execute}"); 
            String error = beanUsuario.getOutput();
            if (!error.equals("000")) {
                setErrorTxt(error);
                Utils.addTarget(otError);
                //Utils.throwError(contx, error, error);
            }else{
                Utils.putSession("USER",beanUsuario);           
                setRed(error);
            }
        }catch(Exception e){
            //e.printStackTrace();
        }
        return beanUsuario;
    }*/
    
    
   // public BeanUsuarioAutenticado autenticarUsuario() 
    public void autenticarUsuario(ActionEvent actionEvent){
        try {
            if (getUsua() == null || getPwd() == null) {
                setErrorTxt("Ingrese su usuario y clave");
                Utils.addTarget(otError);
               return;
            }
            beanUsuario = ln_C_SFUsuarioRemote.autenticarUsuario(getUsua(), getPwd());
            String error = beanUsuario.getOutput();
            String tipoRol = beanUsuario.getRol();
            if (error != null) {
                if ("000".equals(error)) {
                
                if("CLIENTE".equals(tipoRol)){
                    FacesContext fCtx = FacesContext.getCurrentInstance();
                    ExternalContext eCtx = fCtx.getExternalContext();
                    String activityUrl = ControllerContext.getInstance().getViewActivityViewID("LUBAL_SIAT_APP-SILATWEBMOVIL-context-root/faces/Prueba.xhtml"); 
                    try {
                        eCtx.redirect(activityUrl);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }  
                }else{
                    //    beanUsuario.setNidLog(ln_T_SFLogLocal.grabarLogLogInWeb_LN(vecData, beanUsuario.getNidUsuario()));
                        Utils.putSession("USER", beanUsuario);
                        setRedireccionar("000"); 
                }

                } else {
                    setErrorTxt(error);
                    Utils.addTarget(otError);
                    it2.resetValue();
                    setPwd(null);
                    Utils.addTarget(it2);
                }
            } else {
                setErrorTxt(error);
                Utils.addTarget(otError);
                it2.resetValue();
                setPwd(null);
                Utils.addTarget(it2);
            }
        } catch (Exception e) {
     //       ln_T_SFLoggerLocal.registrarLogErroresSistema(beanUsuario.getNidLog(),"BAC",CLASE,"autenticarUsuario(ActionEvent actionEvent)",
      //                                                    "Error Inesperado Logear al usuario",
            e.printStackTrace();
        }        
    }

    /*public void autorizarUsuario(ActionEvent actionEvent) {//ActionListener
        autenticarUsuario();
     
    }*/
    
    public String logoutTarget() {
        HttpSession session = (HttpSession)ectx.getSession(false);
        session.invalidate();
        Utils.depurar("usuario cerro sesion");
        return null;
    }
    
    public String autorizarUsuario() {//Action
        return getRed();
    }
    
    public String getClientIpAddress() {
        String clientIpAddress = ((HttpServletRequest) ectx.getRequest()).getRemoteAddr();
        return clientIpAddress;
    }
    
    public void test(){
        String a = ((HttpServletRequest) ectx.getRequest()).getLocalAddr();
        String b = ((HttpServletRequest) ectx.getRequest()).getLocalName();
        int c = ((HttpServletRequest) ectx.getRequest()).getLocalPort();
        String d = ((HttpServletRequest) ectx.getRequest()).getRemoteHost();
        String e = ((HttpServletRequest) ectx.getRequest()).getRemoteUser();
        int f = ((HttpServletRequest) ectx.getRequest()).getRemotePort();
        String headername = "";
        for(Enumeration en = ((HttpServletRequest) ectx.getRequest()).getHeaderNames(); en.hasMoreElements();){
                headername = (String)en.nextElement();
            Utils.depurar("headername  "+headername+" : " +((HttpServletRequest) ectx.getRequest()).getHeader(headername));
        }
        Utils.depurar("a: "+a);
        Utils.depurar("b: "+b);
        Utils.depurar("c: "+c);
        Utils.depurar("d: "+d);
        Utils.depurar("e: "+e);
        Utils.depurar("f: "+f);
    }
    
    public ArrayList llenarRolesCombo() {
        ArrayList rolesItems = new ArrayList();
        List<BeanRol> roles = ln_C_SFRolRemote.getRolesActivosLN();
        for (BeanRol r : roles) {
            rolesItems.add(new SelectItem(r.getNidRole().toString(), 
                                          r.getCDescRole().toString()));
        }
        return rolesItems;
    }
    
    public List getListRazones() {
        return ListRazones;
    }

    public void setListRazones(List ListRazones) {
        this.ListRazones = ListRazones;
    }
    
    public void setPsl1(RichPanelStretchLayout psl1) {
        this.psl1 = psl1;
    }

    public RichPanelStretchLayout getPsl1() {
        return psl1;
    }

    public void setF1(RichForm f1) {
        this.f1 = f1;
    }

    public RichForm getF1() {
        return f1;
    }

    public void setD1(RichDocument d1) {
        this.d1 = d1;
    }

    public RichDocument getD1() {
        return d1;
    }


    public void setDb1(RichDecorativeBox db1) {
        this.db1 = db1;
    }

    public RichDecorativeBox getDb1() {
        return db1;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setIt1(RichInputText it1) {
        this.it1 = it1;
    }

    public RichInputText getIt1() {
        return it1;
    }

    public void setIt2(RichInputText it2) {
        this.it2 = it2;
    }

    public RichInputText getIt2() {
        return it2;
    }


    public void setCb1(RichCommandButton cb1) {
        this.cb1 = cb1;
    }

    public RichCommandButton getCb1() {
        return cb1;
    }

    public void setM1(RichMessages m1) {
        this.m1 = m1;
    }

    public RichMessages getM1() {
        return m1;
    }


    public void setSoc1(RichSelectOneChoice soc1) {
        this.soc1 = soc1;
    }

    public RichSelectOneChoice getSoc1() {
        return soc1;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public UISelectItems getSi1() {
        return si1;
    }


    public void setListaRoles(List listaRoles) {
        this.listaRoles = listaRoles;
    }

    public List getListaRoles() {
        return listaRoles;
    }

    public void setRed(String red) {
        this.red = red;
    }

    public String getRed() {
        return red;
    }

    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }

    public void setPfl2(RichPanelFormLayout pfl2) {
        this.pfl2 = pfl2;
    }

    public RichPanelFormLayout getPfl2() {
        return pfl2;
    }

    public void setIt3(RichInputText it3) {
        this.it3 = it3;
    }

    public RichInputText getIt3() {
        return it3;
    }

    public void setSoc2(RichSelectOneChoice soc2) {
        this.soc2 = soc2;
    }

    public RichSelectOneChoice getSoc2() {
        return soc2;
    }

    public void setSi2(UISelectItems si2) {
        this.si2 = si2;
    }

    public UISelectItems getSi2() {
        return si2;
    }

    public void setCb2(RichCommandButton cb2) {
        this.cb2 = cb2;
    }

    public RichCommandButton getCb2() {
        return cb2;
    }

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }

    public void setUsua(String usua) {
        this.usua = usua;
    }

    public String getUsua() {
        return usua;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPwd() {
        return pwd;
    }

    public void setValorRol(String valorRol) {
        this.valorRol = valorRol;
    }

    public String getValorRol() {
        return valorRol;
    }

    public void setOtError(RichActiveOutputText aot1) {
        this.otError = aot1;
    }

    public RichActiveOutputText getOtError() {
        return otError;
    }

    public void setErrorTxt(String errorTxt) {
        this.errorTxt = errorTxt;
    }

    public String getErrorTxt() {
        return errorTxt;
    }

    public void setI1(RichImage i1) {
        this.i1 = i1;
    }

    public RichImage getI1() {
        return i1;
    }

    public void setI2(RichImage i2) {
        this.i2 = i2;
    }

    public RichImage getI2() {
        return i2;
    }

    public void setRedireccionar(String redireccionar) {
        this.redireccionar = redireccionar;
    }

    public String getRedireccionar() {
        return redireccionar;
    }
}
