package siat.view.backing.administrativo.usuarios;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.naming.Context;
import javax.naming.InitialContext;

import oracle.adf.view.rich.component.rich.RichSubform;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import siat.view.backing.utiles.Utils;

import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanRol;
import silat.servicios_negocio.LNSF.IR.LN_C_SFRolRemote;
import silat.servicios_negocio.LNSF.IR.LN_T_SFRolRemote;

public class Frm_administrar_roles {
    private RichOutputLabel otTitulo;
    private RichSubform sfRol;
    private RichPanelFormLayout pfl1;
    private RichInputText itDescRol;
    private RichCommandButton btnReg;
    private RichTable tbRol;
    /*Mis variables*/
    private SessionScopeBeanAdminRoles beanSessionAdminRoles;
    private final static String LOOKUP_NAME_SFROL_REMOTO = "mapLN_C_SFRol";//#silat.servicios_negocio.LNSF.IR.LN_C_SFRolRemote
    private final static String LOOKUP_NAME_SF_T_ROL_REMOTO = "mapLN_T_SFRol";//#silat.servicios_negocio.LNSF.IR.LN_T_SFRolRemote
    private LN_C_SFRolRemote ln_C_SFRolRemote;
    private  LN_T_SFRolRemote ln_T_SFRolRemote;
    private FacesContext ctx = FacesContext.getCurrentInstance();
    private String descRol;

    public Frm_administrar_roles(){
        try {
            final Context ctx;
            ctx = new InitialContext();
            ln_C_SFRolRemote = (LN_C_SFRolRemote) ctx.lookup(LOOKUP_NAME_SFROL_REMOTO);
            ln_T_SFRolRemote = (LN_T_SFRolRemote) ctx.lookup(LOOKUP_NAME_SF_T_ROL_REMOTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @PostConstruct 
    public void methodOnPostConstruct(){
        if(beanSessionAdminRoles.getExec() == 0){
            beanSessionAdminRoles.setExec(1);
            this.mostrarRoles();
            beanSessionAdminRoles.setIsNuevo(true);
            beanSessionAdminRoles.setNombBoton("Registrar Rol");
        }else{
           
        }
    }
    
    public String mostrarRoles(){
        beanSessionAdminRoles.setRoles(ln_C_SFRolRemote.getRolesActivosNoAdminLN());
        if(tbRol != null){
            tbRol.setValue(beanSessionAdminRoles.getRoles());
            Utils.addTarget(tbRol);
        }
        return null;
    }
    
    public void registrarRol(ActionEvent actionEvent) {
        String descRol = getDescRol();
        int tipEvento = beanSessionAdminRoles.isIsNuevo() == true ? 1 : 0;
        BigDecimal nidRol = new BigDecimal(0);
        if(beanSessionAdminRoles.getRolSelected() != null){
            if(beanSessionAdminRoles.getRolSelected().getNidRole() != null){
                nidRol = beanSessionAdminRoles.getRolSelected().getNidRole();
            }
        }
        BeanRol bRol = ln_T_SFRolRemote.registrarRol(descRol, tipEvento, nidRol);
        if(bRol.getBeanError() != null){
            BeanError error = bRol.getBeanError();
            int severidad = 0;
            if(error.getCidError().equals("000")){
                severidad = 3;
                Utils.depurar("Grabo/Modifico el Rol");
                itDescRol.resetValue();
                setDescRol(null);
                beanSessionAdminRoles.setNombBoton("Registrar Rol");
                beanSessionAdminRoles.setIsNuevo(true);
                Utils.addTargetMany(itDescRol,btnReg);
                Utils.unselectFilas(tbRol);
                this.mostrarRoles();
            }else{
                severidad = 1;
            }
            Utils.throwError_Aux(ctx,error.getCDescripcionError(), severidad);
        }else{
            Utils.throwError_Aux(ctx,"Error Inesperado", 1);
        }
        beanSessionAdminRoles.setRolSelected(null);
    }
    
    public void selectRol(SelectionEvent se) {
        RichTable t = (RichTable) se.getSource();
        BeanRol beanRol = (BeanRol) t.getSelectedRowData();
        beanSessionAdminRoles.setRolSelected(beanRol);
        beanSessionAdminRoles.setIsNuevo(false);
        beanSessionAdminRoles.setNombBoton("Editar Rol");
        itDescRol.setValue(beanRol.getCDescRole());
        setDescRol(beanRol.getCDescRole());
        Utils.addTargetMany(btnReg,itDescRol);
    }
    
    public void setBeanSessionAdminRoles(SessionScopeBeanAdminRoles beanSessionAdminRoles) {
        this.beanSessionAdminRoles = beanSessionAdminRoles;
    }

    public SessionScopeBeanAdminRoles getBeanSessionAdminRoles() {
        return beanSessionAdminRoles;
    }

    public void setOtTitulo(RichOutputLabel ol1) {
        this.otTitulo = ol1;
    }

    public RichOutputLabel getOtTitulo() {
        return otTitulo;
    }

    public void setSfRol(RichSubform s1) {
        this.sfRol = s1;
    }

    public RichSubform getSfRol() {
        return sfRol;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setItDescRol(RichInputText it1) {
        this.itDescRol = it1;
    }

    public RichInputText getItDescRol() {
        return itDescRol;
    }

    public void setBtnReg(RichCommandButton cb1) {
        this.btnReg = cb1;
    }

    public RichCommandButton getBtnReg() {
        return btnReg;
    }

    public void setTbRol(RichTable t1) {
        this.tbRol = t1;
    }

    public RichTable getTbRol() {
        return tbRol;
    }

    public void setDescRol(String descRol) {
        this.descRol = descRol;
    }

    public String getDescRol() {
        return descRol;
    }
}
