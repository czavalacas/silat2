package siat.view.backing.bienvenida;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPoll;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.RichSubform;
import oracle.adf.view.rich.component.rich.data.RichTree;
import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichGridCell;
import oracle.adf.view.rich.component.rich.layout.RichGridRow;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichMessages;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.event.DialogEvent;
import org.apache.myfaces.trinidad.event.PollEvent;
import siat.view.backing.utiles.ADFUtil;
import siat.view.backing.utiles.Utils;
import silat.servicios_negocio.Beans.BeanPermisos;
import silat.servicios_negocio.Beans.BeanUsuarioAutenticado;
import silat.servicios_negocio.LNSF.IR.LN_C_SFOrdenServicioRemote;
import silat.servicios_negocio.LNSF.IR.LN_T_SFOrdenServicioRemote;

public class Plantilla_main {
    private RichPanelGroupLayout pgl1;
    private RichPanelSplitter ps1;
    private RichPanelSplitter ps2;
    private RichPanelStretchLayout psl1;
    private RichForm f1;
    private RichDocument d1;
    private RichMessages m1;
    private RichGridRow gr1;
    private RichGridCell gc1;
    private RichGridCell gc2;
    private RichRegion region1;
    private RichTree t1;
    private RichImage i1;
    private RichImage imgCerrar;
    private RichCommandLink cl1;
    private RichPanelFormLayout pfl1;
    private RichInputText it1;
    private RichInputText it2;
    private RichSubform s1;
    private RichSubform sfrmReg;
    private RichCommandButton cb1;
    private RichPoll pollOS;
    private RichPopup popOS;
    private RichDialog diaOS;
    private RichOutputText otMsjOs;
    /*-------------------MIS VARIABLES-------------------------*/
    private final static String LOOKUP_NAME_SFORDSERV_REMOTO = "mapLN_C_SFOrdenServicio";//#silat.servicios_negocio.LNSF.IR.LN_C_SFOrdenServicioRemote
    private final static String LOOKUP_NAME_SF_T_ORDSERV_REMOTO = "mapLN_T_SFOrdenServicio";//#silat.servicios_negocio.LNSF.IR.LN_T_SFOrdenServicioRemote
    private String usuario;
    private String rol;
    private BeanUsuarioAutenticado beanUsuario = (BeanUsuarioAutenticado) Utils.getSession("USER");
    private SessionScopedBeanMain beanSessionScopedBeanMain;
    private LN_C_SFOrdenServicioRemote ln_C_SFOrdenServicioRemote;
    private LN_T_SFOrdenServicioRemote ln_T_SFOrdenServicioRemote;
    FacesContext ctx = FacesContext.getCurrentInstance();

    public Plantilla_main(){
        try{
            final Context ctx;
            ctx = new InitialContext();
            ln_C_SFOrdenServicioRemote = (LN_C_SFOrdenServicioRemote) ctx.lookup(LOOKUP_NAME_SFORDSERV_REMOTO);
            ln_T_SFOrdenServicioRemote = (LN_T_SFOrdenServicioRemote) ctx.lookup(LOOKUP_NAME_SF_T_ORDSERV_REMOTO);
            if(beanUsuario != null){
                usuario = beanUsuario.getCUsuario() + " - " + beanUsuario.getCNombres()+" "+beanUsuario.getCApellidos();
                rol     = beanUsuario.getRol();
                mandarParametro(null);
            }else{
                logoutTarget("/faces/frm_login");
            }
        }catch(Exception e){//@TODO log
            e.printStackTrace();
            logoutTarget("/faces/frm_login");
        }
    }
    
    public String getUrl(ActionEvent e) {
        if(Utils.getSession("override") == null){
            String url = cl1.getShortDesc();
            Utils.putSession("url",url);
        }
        return null;
    }
    
    @PostConstruct
    public void methodInvokeOncedOnPageLoad(){
        if(getBeanSessionScopedBeanMain().getExec() == 0){
            getBeanSessionScopedBeanMain().setExec(1);
            mostrarTodo();
        }else{
          //  mostrarTodo();
        }
    }
    
    public void mandarParametro(ValueChangeEvent vce){//LN_C_SFPermisosBeanBean
        if(ADFUtil.evaluateEL("#{pageFlowScope.nomUsu}") == null){
            ADFUtil.setEL("#{pageFlowScope.nomUsu}", beanUsuario.getCUsuario());
        }
        ADFUtil.setEL("#{pageFlowScope.parID}", beanUsuario.getNidRol());
        Utils.mandarParametro("nidRol","#{pageFlowScope.parID}","getCrearArbolNuevo");
        Utils.mandarParametro("nombUser","#{pageFlowScope.nomUsu}","getCrearArbolNuevo");
    }

    public String logoutTarget(String aTarget) {
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletResponse response = (HttpServletResponse)ectx.getResponse();
        String url = ectx.getRequestContextPath() + aTarget;
        HttpSession session = (HttpSession)ectx.getSession(false);
        //close session
        session.invalidate();
        try {//@TODO log
            Utils.depurar("usuario cerro sesion");
            response.sendRedirect(url);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void dialogLogoutListener(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
         //   ADFUtil.invokeEL("#{chatBean.logout}");
            logoutTarget("/faces/frm_login");
        }
    }

    public void checkNuevasOrdenServPoll(PollEvent pe) {
        try {
            buscarOrdenesServicio();
        } catch (Exception e) {
            RichPoll poll = (RichPoll) pe.getComponent();
            poll.setTimeout(1000);
            Utils.depurar("timeout!!");
            e.printStackTrace();
            return;
        }
    }
    
    public void buscarOrdenesServicio(){
        int cantNuevasOS = ln_C_SFOrdenServicioRemote.countNuevasOS_LN();
        if (cantNuevasOS > 0) {
            beanSessionScopedBeanMain.setNombMsj("Hay "+cantNuevasOS+" Orden(es) de Servicio nueva(s). Presiona OK para revisarlas.");
            if(beanSessionScopedBeanMain.isIsMostrarNotif()){
                beanSessionScopedBeanMain.setIsMostrarNotif(false);
                Utils.showPopUpMIDDLE(popOS);
                //Utils.depurar("cantNuevasOS: " + cantNuevasOS);
            }
        }
    }
    
    public void dialogOK_OS_Listener(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
            ln_T_SFOrdenServicioRemote.flgVistoNuevasOrdenServicio_LN();
            beanSessionScopedBeanMain.setIsMostrarNotif(true);
            Utils._redireccionar(ctx,"/WEB-INF/frm_actualizar_orden_servicio.xml#frm_actualizar_orden_servicio");
        }
    }
    
    public void mostrarTodo(){
        if(beanUsuario != null){
            List<BeanPermisos> lstPermisos = (List<BeanPermisos>) ADFUtil.invokeEL("#{bindings.getCrearArbolNuevo.execute}");
            if(lstPermisos != null){
                if(lstPermisos.size() > 0){
                    List<BigDecimal> lstBPerm = lstPermisos.get(0).getLstPermisos();
                    if(lstBPerm != null){
                        if(lstBPerm.size() > 0){
                            beanUsuario.setLstPermisos(lstBPerm);//Utils.depurar("per: "+beanUsuario.getLstPermisos());
                            boolean hasPermiso = Utils.hasPermiso(beanUsuario.getLstPermisos(),new BigDecimal(49));
                            if (hasPermiso){
                                beanSessionScopedBeanMain.setRenderPoll(true);                    
                            }else{
                                beanSessionScopedBeanMain.setRenderPoll(false);                    
                            }
                            Utils.putSession("USER",beanUsuario);                             
                        }
                    }
                }
            }
        }
    }
    
    public void cambiarClave(ActionEvent actionEvent) {
        Utils._redireccionar(ctx,"/WEB-INF/modificar_usuario.xml#modificar_usuario");
    }
    
    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }

    public void setPs1(RichPanelSplitter ps1) {
        this.ps1 = ps1;
    }

    public RichPanelSplitter getPs1() {
        return ps1;
    }

    public void setPs2(RichPanelSplitter ps2) {
        this.ps2 = ps2;
    }

    public RichPanelSplitter getPs2() {
        return ps2;
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

    public void setM1(RichMessages m1) {
        this.m1 = m1;
    }

    public RichMessages getM1() {
        return m1;
    }


    public void setGr1(RichGridRow gr1) {
        this.gr1 = gr1;
    }

    public RichGridRow getGr1() {
        return gr1;
    }

    public void setGc1(RichGridCell gc1) {
        this.gc1 = gc1;
    }

    public RichGridCell getGc1() {
        return gc1;
    }

    public void setGc2(RichGridCell gc2) {
        this.gc2 = gc2;
    }

    public RichGridCell getGc2() {
        return gc2;
    }

    public void setRegion1(RichRegion r1) {
        this.region1 = r1;
    }

    public RichRegion getRegion1() {
        return region1;
    }

    public void setT1(RichTree t1) {
        this.t1 = t1;
    }

    public RichTree getT1() {
        return t1;
    }


    public void setI1(RichImage i1) {
        this.i1 = i1;
    }

    public RichImage getI1() {
        return i1;
    }

    public void setCl1(RichCommandLink cl1) {
        this.cl1 = cl1;
    }

    public RichCommandLink getCl1() {
        return cl1;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getRol() {
        return rol;
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

    public void setS1(RichSubform s1) {
        this.s1 = s1;
    }

    public RichSubform getS1() {
        return s1;
    }

    public void setSfrmReg(RichSubform s2) {
        this.sfrmReg = s2;
    }

    public RichSubform getSfrmReg() {
        return sfrmReg;
    }

    public void setBeanUsuario(BeanUsuarioAutenticado beanUsuario) {
        this.beanUsuario = beanUsuario;
    }

    public BeanUsuarioAutenticado getBeanUsuario() {
        return beanUsuario;
    }

    public void setBeanSessionScopedBeanMain(SessionScopedBeanMain beanSessionScopedBeanMain) {
        this.beanSessionScopedBeanMain = beanSessionScopedBeanMain;
    }

    public SessionScopedBeanMain getBeanSessionScopedBeanMain() {
        return beanSessionScopedBeanMain;
    }

    public void setCb1(RichCommandButton cb1) {
        this.cb1 = cb1;
    }

    public RichCommandButton getCb1() {
        return cb1;
    }


    public void setPollOS(RichPoll p2) {
        this.pollOS = p2;
    }

    public RichPoll getPollOS() {
        return pollOS;
    }

    public void setPopOS(RichPopup p2) {
        this.popOS = p2;
    }

    public RichPopup getPopOS() {
        return popOS;
    }

    public void setDiaOS(RichDialog d3) {
        this.diaOS = d3;
    }

    public RichDialog getDiaOS() {
        return diaOS;
    }

    public void setOtMsjOs(RichOutputText ot1) {
        this.otMsjOs = ot1;
    }

    public RichOutputText getOtMsjOs() {
        return otMsjOs;
    }


    public void setImgCerrar(RichImage imgCerrar) {
        this.imgCerrar = imgCerrar;
    }

    public RichImage getImgCerrar() {
        return imgCerrar;
    }
}
