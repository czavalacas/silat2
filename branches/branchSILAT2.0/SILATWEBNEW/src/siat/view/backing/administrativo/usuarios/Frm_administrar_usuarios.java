package siat.view.backing.administrativo.usuarios;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItem;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import javax.naming.Context;
import javax.naming.InitialContext;
import oracle.adf.view.rich.component.rich.RichSubform;
import oracle.adf.view.rich.component.rich.data.RichColumn;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectItem;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;

import org.apache.myfaces.trinidad.component.core.input.CoreSelectItem;
import org.apache.myfaces.trinidad.component.core.input.CoreSelectOneChoice;
import org.apache.myfaces.trinidad.event.SelectionEvent;

import siat.view.backing.utiles.Utils;

import silat.servicios_negocio.BDLSF.IR.BDL_C_SFRolRemote;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanParty;
import silat.servicios_negocio.Beans.BeanPersona;
import silat.servicios_negocio.Beans.BeanRol;
import silat.servicios_negocio.Beans.BeanUnidadMedida;
import silat.servicios_negocio.Beans.BeanUsuario;
import silat.servicios_negocio.LNSF.IR.LN_C_SFRolRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFUsuarioRemote;
import silat.servicios_negocio.LNSF.IR.LN_T_SFUsuarioRemote;

public class Frm_administrar_usuarios {
    private RichOutputLabel olTitulo;
    private RichSubform sfUsr;
    private RichPanelFormLayout pflUsr;
    private RichInputText itUsu;
    private RichSelectOneChoice socRol;
    private UISelectItems siRols;
    private RichInputText itNomb;
    private RichInputText itApe;
    private RichInputText itCorreo;
    private RichInputText itTelf;
    private RichInputDate idFecNac;
    private RichPanelGroupLayout pglUsr;
    private RichCommandButton btnRegistrar;
    private RichCommandButton btnRefrescar;
    private RichCommandButton btnDesact;
    private RichTable tbUsrs;
    private LN_C_SFUsuarioRemote ln_C_SFUsuarioRemote;
    private LN_T_SFUsuarioRemote ln_T_SFUsuarioRemote;
    private LN_C_SFRolRemote ln_C_SFRolRemote;
    private final static String LOOKUP_NAME_SFUSUARIO_REMOTO = "mapLNSFUsuario";//#silat.servicios_negocio.LNSF.IR.LN_C_SFUsuarioRemote
    private final static String LOOKUP_NAME_SF_T_USUARIO_REMOTO = "mapLN_T_SFUsuario";//#silat.servicios_negocio.LNSF.IR.LN_T_SFUsuarioRemote
    private final static String LOOKUP_NAME_SFROL_REMOTO = "mapLN_C_SFRol";//#silat.servicios_negocio.LNSF.IR.LN_C_SFRolRemote
    private SessionScopedBeanAdminUsuarios beanSessionAdminUsuarios;
    FacesContext ctx = FacesContext.getCurrentInstance();
    private String user;
    private String rol;
    private String nombres;
    private String apellidos;
    private String correo;
    private String telf;
    private Date fecNac;
    private RichColumn c4;

    public Frm_administrar_usuarios(){
        try {
            final Context ctx;
            ctx = new InitialContext();
            ln_C_SFUsuarioRemote = (LN_C_SFUsuarioRemote)ctx.lookup(LOOKUP_NAME_SFUSUARIO_REMOTO);
            ln_T_SFUsuarioRemote = (LN_T_SFUsuarioRemote)ctx.lookup(LOOKUP_NAME_SF_T_USUARIO_REMOTO);
            ln_C_SFRolRemote = (LN_C_SFRolRemote) ctx.lookup(LOOKUP_NAME_SFROL_REMOTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @PostConstruct 
    public void methodOnPostConstruct(){
        if(beanSessionAdminUsuarios.getExec() == 0){
            beanSessionAdminUsuarios.setExec(1);
            this.mostrarUsers();
            beanSessionAdminUsuarios.setListaRoles(this.llenarRolesCombo());
            beanSessionAdminUsuarios.setNombreBtn("Desactivar Usuario");
            beanSessionAdminUsuarios.setNombreBtnReg("Registrar Usuario");
        }else{
            this.mostrarUsers();
            beanSessionAdminUsuarios.setListaRoles(this.llenarRolesCombo());
        }
    }
    
    public String mostrarUsers(){
        beanSessionAdminUsuarios.setLstUsuariosNoAdmin(ln_C_SFUsuarioRemote.lstUsuariosNoAdmin());
        if(tbUsrs != null){
            tbUsrs.setValue(beanSessionAdminUsuarios.getLstUsuariosNoAdmin());
            Utils.addTarget(tbUsrs);
        }
        return null;
    }
    
    public void refrescar(ActionEvent actionEvent) {
        _refrescar();
    }
    
    public List<BeanUsuario> _refrescar(){
          beanSessionAdminUsuarios.setLstUsuariosNoAdmin(ln_C_SFUsuarioRemote.lstUsuariosNoAdmin());
          if(tbUsrs != null){
              tbUsrs.setValue(beanSessionAdminUsuarios.getLstUsuariosNoAdmin());
              Utils.addTarget(tbUsrs);
          }
          Utils.unselectFilas(tbUsrs);
          return beanSessionAdminUsuarios.getLstUsuariosNoAdmin();
    }
    
    public void registrarUsuario(ActionEvent ae) {
        if(isOk()){
            BeanUsuario bUser = new BeanUsuario();
            if(beanSessionAdminUsuarios.isBoolModificarUser()){
                bUser = beanSessionAdminUsuarios.getBeanUsuarioSelected();
                bUser.getAdPersona().setCApellidos(getApellidos());
                bUser.getAdPersona().setCNombres(getNombres());
                bUser.getAdPersona().setFecNac(getFecNac());
                bUser.getAdPersona().getAdParty().setCEmail(getCorreo());
                bUser.getAdPersona().getAdParty().setCTelf(getTelf());
                bUser = ln_T_SFUsuarioRemote.actualizarUsuario(bUser);
            }else{
                BeanPersona bPers = new BeanPersona();
                BeanParty bParty = new BeanParty();
                bParty.setCEmail(getCorreo());
                bParty.setCTelf(getTelf());
                bParty.setCTipoParty("P");
                bPers.setCApellidos(getApellidos());
                bPers.setCNombres(getNombres());
                bPers.setFecNac(getFecNac());
                bPers.setCCargo(beanSessionAdminUsuarios.getDescRol());
                bPers.setAdParty(bParty);
                bUser.setCUsuario(beanSessionAdminUsuarios.getUser());
                bUser.setNEstadoUsuario(new BigDecimal(1));
                bUser.setNTipoUsuario(new BigDecimal(beanSessionAdminUsuarios.getRol()));
                bUser.setAdPersona(bPers);
                bUser = ln_T_SFUsuarioRemote.registrarUsuario(bUser);
            }  
            if(bUser.getBeanError() != null){
                BeanError error = bUser.getBeanError();
                int severidad = 0;
                if(error.getCidError().equals("000")){
                    severidad = 3;
                    Utils.depurar("Grabo/Modifico el usuario");
                    _refrescar();
                    itUsu.resetValue();
                    socRol.resetValue();
                    itNomb.resetValue();
                    itApe.resetValue();
                    itCorreo.resetValue();
                    itTelf.resetValue();
                    idFecNac.resetValue();
                    beanSessionAdminUsuarios.setUser(null);
                    beanSessionAdminUsuarios.setRol(null);
                    setNombres(null);
                    setApellidos(null);
                    setCorreo(null);
                    setTelf(null);
                    setFecNac(null);
                    beanSessionAdminUsuarios.setNombreBtnReg("Registrar Usuario");
                    beanSessionAdminUsuarios.setBoolModificarUser(false);
                    itUsu.setDisabled(false);
                    socRol.setDisabled(false);
                    Utils.addTargetMany(btnRegistrar,itUsu,socRol,itNomb,itApe,itTelf,itCorreo,idFecNac);
                }else{
                    severidad = 1;
                }
                Utils.throwError_Aux(ctx,error.getCDescripcionError(), severidad);
            }else{
                Utils.throwError_Aux(ctx,"Error Inesperado", 1);
            }
            btnDesact.setDisabled(true);
            Utils.addTarget(btnDesact);
            beanSessionAdminUsuarios.setBeanUsuarioSelected(null);
        }
    }
    
    public boolean isOk(){
        int severidad = 4;
        if(beanSessionAdminUsuarios.getUser() == null){
            Utils.throwError_Aux(ctx,"Escriba un nombre de usuario.", severidad);
            return false;
        }
        if(beanSessionAdminUsuarios.getRol() == null){
            Utils.throwError_Aux(ctx,"Seleccione un Rol.", severidad);
            return false;
        }
        if(getNombres() == null){
            Utils.throwError_Aux(ctx,"Escriba el/los nombre(s) del usuario.", severidad);
            return false;
        }
        if(getApellidos() == null){
            Utils.throwError_Aux(ctx,"Escriba el/los apellido(s) del usuario.", severidad);
            return false;
        }
        if(getCorreo() == null){
            Utils.throwError_Aux(ctx,"Escriba el correo del usuario.", severidad);
            return false;
        }
        return true;
    }
    
    public void selectUsuario(SelectionEvent selectionEvent){
        RichTable t = (RichTable)selectionEvent.getSource();
        BeanUsuario beanUsuario = (BeanUsuario) t.getSelectedRowData();
        beanSessionAdminUsuarios.setBeanUsuarioSelected(beanUsuario);
        btnDesact.setDisabled(false);
        beanSessionAdminUsuarios.setModificarUser(true);
        beanSessionAdminUsuarios.setRol(beanUsuario.getNTipoUsuario()+"");
        beanSessionAdminUsuarios.setUser(beanUsuario.getCUsuario());
        setNombres(beanUsuario.getAdPersona().getCNombres());
        setApellidos(beanUsuario.getAdPersona().getCApellidos());
        setCorreo(beanUsuario.getAdPersona().getAdParty().getCEmail());
        setTelf(beanUsuario.getAdPersona().getAdParty().getCTelf());
        setFecNac(beanUsuario.getAdPersona().getFecNac());
        if(beanUsuario.getNEstadoUsuario().intValue() == 1){
            beanSessionAdminUsuarios.setNombreBtn("Desactivar Usuario.");
            beanSessionAdminUsuarios.setFlgMod(0);
        }else{//0
            beanSessionAdminUsuarios.setNombreBtn("Reactivar Usuario.");
            beanSessionAdminUsuarios.setFlgMod(1);
        }
        beanSessionAdminUsuarios.setNombreBtnReg("Actualizar Usuario");
        beanSessionAdminUsuarios.setBoolModificarUser(true);
        itUsu.setDisabled(true);
        socRol.setDisabled(true);
        Utils.addTargetMany(btnDesact,btnDesact,itUsu,socRol,itNomb,itApe,itCorreo,itTelf,idFecNac,btnRegistrar);
    }
    
    public void desactivarUsuario(ActionEvent ae) {
        if (beanSessionAdminUsuarios.getBeanUsuarioSelected() != null) {
            if (beanSessionAdminUsuarios.getBeanUsuarioSelected().getNidUsuario() != null) {
                int flgMod = beanSessionAdminUsuarios.getFlgMod();
                BeanUsuario bUser = ln_T_SFUsuarioRemote.desactivarUsuario(beanSessionAdminUsuarios.getBeanUsuarioSelected().getNidUsuario(),flgMod);
                if (bUser.getBeanError() != null) {
                    BeanError error = bUser.getBeanError();
                    int severidad = 0;
                    if (error.getCidError().equals("000")) {
                        severidad = 3;
                        Utils.depurar("Grabo el usuario");
                        _refrescar();
                    } else {
                        severidad = 1;
                    }
                    Utils.throwError_Aux(ctx, error.getCDescripcionError(), severidad);
                } else {
                    Utils.throwError_Aux(ctx, "Error Inesperado", 1);
                }
            }
        } else {
            Utils.throwError_Aux(ctx, "Seleccione un usuario para anular.", 4);
            return;
        }
    }

    public ArrayList llenarRolesCombo() {
        ArrayList rolesItems = new ArrayList();
        List<BeanRol> roles = ln_C_SFRolRemote.getRolesActivosNoAdminLN();
        for (BeanRol r : roles) {
            rolesItems.add(new SelectItem(r.getNidRole().toString(), 
                                          r.getCDescRole().toString()));
        }
        return rolesItems;
    }

    public void cambioRol(ValueChangeEvent vce) {
        try { 
           RichSelectOneChoice csoc = (RichSelectOneChoice)vce.getComponent();
            String lblRol = "";
            UISelectItems itms = (UISelectItems)csoc.getChildren().get(0);
            List listaRoles = (List) itms.getValue();
            for(int i = 0; i < listaRoles.size(); i++){
                SelectItem selItm = (SelectItem) listaRoles.get(i);
                if (((String)selItm.getValue()).equals((String)vce.getNewValue())) {
                    lblRol = selItm.getLabel();
                    beanSessionAdminUsuarios.setDescRol(lblRol);
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setBeanSessionAdminUsuarios(SessionScopedBeanAdminUsuarios beanSessionAdminUsuarios) {
        this.beanSessionAdminUsuarios = beanSessionAdminUsuarios;
    }

    public SessionScopedBeanAdminUsuarios getBeanSessionAdminUsuarios() {
        return beanSessionAdminUsuarios;
    }

    public void setOlTitulo(RichOutputLabel ol1) {
        this.olTitulo = ol1;
    }

    public RichOutputLabel getOlTitulo() {
        return olTitulo;
    }

    public void setSfUsr(RichSubform s1) {
        this.sfUsr = s1;
    }

    public RichSubform getSfUsr() {
        return sfUsr;
    }

    public void setPflUsr(RichPanelFormLayout pfl1) {
        this.pflUsr = pfl1;
    }

    public RichPanelFormLayout getPflUsr() {
        return pflUsr;
    }

    public void setItUsu(RichInputText it1) {
        this.itUsu = it1;
    }

    public RichInputText getItUsu() {
        return itUsu;
    }

    public void setSocRol(RichSelectOneChoice soc1) {
        this.socRol = soc1;
    }

    public RichSelectOneChoice getSocRol() {
        return socRol;
    }

    public void setSiRols(UISelectItems si1) {
        this.siRols = si1;
    }

    public UISelectItems getSiRols() {
        return siRols;
    }

    public void setItNomb(RichInputText it2) {
        this.itNomb = it2;
    }

    public RichInputText getItNomb() {
        return itNomb;
    }

    public void setItApe(RichInputText it3) {
        this.itApe = it3;
    }

    public RichInputText getItApe() {
        return itApe;
    }

    public void setItCorreo(RichInputText it4) {
        this.itCorreo = it4;
    }

    public RichInputText getItCorreo() {
        return itCorreo;
    }

    public void setItTelf(RichInputText it5) {
        this.itTelf = it5;
    }

    public RichInputText getItTelf() {
        return itTelf;
    }

    public void setIdFecNac(RichInputDate id1) {
        this.idFecNac = id1;
    }

    public RichInputDate getIdFecNac() {
        return idFecNac;
    }

    public void setLn_C_SFUsuarioRemote(LN_C_SFUsuarioRemote ln_C_SFUsuarioRemote) {
        this.ln_C_SFUsuarioRemote = ln_C_SFUsuarioRemote;
    }

    public LN_C_SFUsuarioRemote getLn_C_SFUsuarioRemote() {
        return ln_C_SFUsuarioRemote;
    }

    public void setCtx(FacesContext ctx) {
        this.ctx = ctx;
    }

    public FacesContext getCtx() {
        return ctx;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNombres() {
        return nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setTelf(String telf) {
        this.telf = telf;
    }

    public String getTelf() {
        return telf;
    }

    public void setFecNac(Date fecNac) {
        this.fecNac = fecNac;
    }

    public Date getFecNac() {
        return fecNac;
    }

    public void setPglUsr(RichPanelGroupLayout pgl1) {
        this.pglUsr = pgl1;
    }

    public RichPanelGroupLayout getPglUsr() {
        return pglUsr;
    }

    public void setBtnRegistrar(RichCommandButton cb1) {
        this.btnRegistrar = cb1;
    }

    public RichCommandButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public void setBtnRefrescar(RichCommandButton cb1) {
        this.btnRefrescar = cb1;
    }

    public RichCommandButton getBtnRefrescar() {
        return btnRefrescar;
    }

    public void setBtnDesact(RichCommandButton cb1) {
        this.btnDesact = cb1;
    }

    public RichCommandButton getBtnDesact() {
        return btnDesact;
    }

    public void setTbUsrs(RichTable t1) {
        this.tbUsrs = t1;
    }

    public RichTable getTbUsrs() {
        return tbUsrs;
    }

    public void setC4(RichColumn c4) {
        this.c4 = c4;
    }

    public RichColumn getC4() {
        return c4;
    }
}
