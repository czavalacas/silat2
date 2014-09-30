package siat.view.backing.transporte.manifiesto;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;

import javax.faces.context.FacesContext;

import javax.naming.Context;
import javax.naming.InitialContext;

import oracle.adf.view.rich.component.rich.output.RichOutputLabel;

import siat.view.backing.utiles.Utils;

import silat.servicios_negocio.Beans.BeanUsuarioAutenticado;
import silat.servicios_negocio.LNSF.IL.LN_T_SFManifiestoRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFGuiaRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFManifiestoRemote;

public class Frm_registrar_manifiesto {
    
    private RichOutputLabel tituloPage;
    
    
    
    /*--------Mis Variables------------*/
    private final static String LOOKUP_NAME_SFCMANIF_REMOTO     = "mapLN_C_SFManifiesto";
    private final static String LOOKUP_NAME_SFCGUIA_REMOTO      = "mapLN_C_SFGuia";
    private final static String LOOKUP_NAME_SFMANIFIESTO_REMOTO = "mapLN_T_SFManifiesto";
    private LN_C_SFManifiestoRemote ln_C_SFManifiestoRemote;
    private LN_C_SFGuiaRemote ln_C_SFGuiaRemote;
    private LN_T_SFManifiestoRemote ln_T_SFManifiestoRemote;
    FacesContext ctx = FacesContext.getCurrentInstance();
    private SessionScopedBeanRegistrarManifiesto beanSessionRegistrarManifiesto;
    private BeanUsuarioAutenticado beanUsuario = new BeanUsuarioAutenticado();
    

    public Frm_registrar_manifiesto(){
        try{
            final Context ctx;
            ctx = new InitialContext();
            ln_C_SFManifiestoRemote = (LN_C_SFManifiestoRemote) ctx.lookup(LOOKUP_NAME_SFCMANIF_REMOTO);
            ln_C_SFGuiaRemote = (LN_C_SFGuiaRemote) ctx.lookup(LOOKUP_NAME_SFCGUIA_REMOTO);
            ln_T_SFManifiestoRemote = (LN_T_SFManifiestoRemote) ctx.lookup(LOOKUP_NAME_SFMANIFIESTO_REMOTO);         
            beanUsuario = (BeanUsuarioAutenticado) Utils.getSession("USER");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @PostConstruct
    public void methodInvokeOncedOnPageLoad(){
        if(beanSessionRegistrarManifiesto.getExec() == 0){
            Utils.depurar("Primera Vez postContructor");
            beanSessionRegistrarManifiesto.setExec(1);         
          
        }else{
         
            Utils.depurar("POST CONSTRUCT otras veces");
        }
    }


    public void setBeanSessionRegistrarManifiesto(SessionScopedBeanRegistrarManifiesto beanSessionRegistrarManifiesto) {
        this.beanSessionRegistrarManifiesto = beanSessionRegistrarManifiesto;
    }

    public SessionScopedBeanRegistrarManifiesto getBeanSessionRegistrarManifiesto() {
        return beanSessionRegistrarManifiesto;
    }

    public void setTituloPage(RichOutputLabel tituloPage) {
        this.tituloPage = tituloPage;
    }

    public RichOutputLabel getTituloPage() {
        return tituloPage;
    }
}
