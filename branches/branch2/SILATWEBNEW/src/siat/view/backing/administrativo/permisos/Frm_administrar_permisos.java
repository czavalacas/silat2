package siat.view.backing.administrativo.permisos;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import javax.naming.Context;
import javax.naming.InitialContext;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.data.RichTreeTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.layout.RichGridCell;
import oracle.adf.view.rich.component.rich.layout.RichGridRow;
import oracle.adf.view.rich.component.rich.layout.RichPanelGridLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.adf.view.rich.event.DialogEvent;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;

import siat.view.backing.utiles.Utils;

import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanPermisos;
import silat.servicios_negocio.Beans.BeanUsuarioAutenticado;
import silat.servicios_negocio.LNSF.IR.LN_C_SFPermisosBeanRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFUsuarioRemote;
import silat.servicios_negocio.LNSF.IR.LN_T_SFPermisosRemoto;
import silat.servicios_negocio.LNSF.SFBean.LN_C_SFPermisosBeanBean;

public class Frm_administrar_permisos {
    private RichOutputText ouputTitle;
    private RichTreeTable treePer;
    private RichCommandButton btnVerUsuarios;
    private ChildPropertyTreeModel permisosTree;
    private RichPanelGridLayout pgl1;
    private RichGridRow gr1;
    private RichGridCell gc1;
    private RichGridCell gc2;
    private RichGridCell gc3;
    private RichCommandButton btnResetPerm;
    private RichCommandButton btnGrabarPerm;
    private RichPopup popupUsuarios;
    private RichDialog dialogUsuarios;
    private RichTable t1;
    private RichOutputText outUsuario;
    private RichOutputText outUsuario2;
    private RichInputText inputPrueba;
    private RichOutputText ouputPrueba;
    private RichCommandButton btnConfirmar;
    private LN_C_SFPermisosBeanRemote ln_C_SFPermisosBeanRemote;
    private final static String LOOKUP_NAME_SFPERMISOS_REMOTO = "mapLNPermisos";//#silat.servicios_negocio.LNSF.IR.LN_C_SFPermisosBeanRemote
    private LN_C_SFUsuarioRemote ln_C_SFUsuarioRemote;
    private final static String LOOKUP_NAME_SFUSUARIO_REMOTO = "mapLNSFUsuario";//#silat.servicios_negocio.LNSF.IR.LN_C_SFUsuarioRemote
    private LN_T_SFPermisosRemoto ln_T_SFPermisosRemoto;
    private final static String LOOKUP_NAME_SFPERMISOSREMOTO = "LUBAL_SIAT_APP-SILATNEGOCIO-LN_T_SFPermisos";//#silat.servicios_negocio.LNSF.IR.LN_T_SFPermisosRemoto
    FacesContext ctx = FacesContext.getCurrentInstance();
    private BeanUsuarioAutenticado beanUsuarioAutenticado = new BeanUsuarioAutenticado();
    private SessionScopeBeanAdministrarPermisos beanSessionScopedAdministrarPermisos;
    private RichPopup popConf;
    private RichDialog diaConf;
    private RichOutputText grabPerm;


    public Frm_administrar_permisos() {
        try {
            final Context ctx;
            ctx = new InitialContext();
            ln_C_SFPermisosBeanRemote = (LN_C_SFPermisosBeanRemote)ctx.lookup(LOOKUP_NAME_SFPERMISOS_REMOTO);
            ln_C_SFUsuarioRemote = (LN_C_SFUsuarioRemote)ctx.lookup(LOOKUP_NAME_SFUSUARIO_REMOTO);
            ln_T_SFPermisosRemoto = (LN_T_SFPermisosRemoto)ctx.lookup(LOOKUP_NAME_SFPERMISOSREMOTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void seleccionarUsuario(SelectionEvent selectionEvent) {
        RichTable t = (RichTable)selectionEvent.getSource();
        Object _selectedRowData = t.getSelectedRowData();
        beanUsuarioAutenticado = (BeanUsuarioAutenticado)_selectedRowData;
        beanSessionScopedAdministrarPermisos.setBeanUsuarioAutenticado(beanUsuarioAutenticado);
        beanSessionScopedAdministrarPermisos.setCUsuario(beanUsuarioAutenticado.getCUsuario());
        beanSessionScopedAdministrarPermisos.setNidRol(beanUsuarioAutenticado.getNidRol());
        beanSessionScopedAdministrarPermisos.setNidUsuario(beanUsuarioAutenticado.getNidUsuario());
        mostrarListaPermisos();
        outUsuario.setValue("Permisos De: " +
                            beanSessionScopedAdministrarPermisos.getBeanUsuarioAutenticado().getCApellidos() + " " +
                            beanSessionScopedAdministrarPermisos.getBeanUsuarioAutenticado().getCNombres());
        outUsuario2.setValue("Usuario: " + beanSessionScopedAdministrarPermisos.getCUsuario().toUpperCase() +
                             "             Rol: " +
                             beanSessionScopedAdministrarPermisos.getBeanUsuarioAutenticado().getCCargo());
        treePer.setValue(beanSessionScopedAdministrarPermisos.getPermisosTree());
        btnResetPerm.setDisabled(false);
        btnGrabarPerm.setDisabled(false);
        Utils.addTargetMany(outUsuario,outUsuario2,treePer,btnResetPerm,btnGrabarPerm);
        popupUsuarios.hide();
    }


    public String showPopUp(RichPopup p) {
        try {
            RichPopup.PopupHints ph = new RichPopup.PopupHints();
            ph.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN, RichPopup.PopupHints.AlignTypes.ALIGN_AFTER_END);
            ph.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN_ID, btnVerUsuarios);
            p.show(ph);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String mostrarUsuarios() {
        beanSessionScopedAdministrarPermisos.setListUsuarios(ln_C_SFUsuarioRemote.getUsuarios());
        showPopUp(popupUsuarios);
        return null;
    }

    public String mostrarListaPermisos() {
        List<BeanPermisos> lstPermisos = ln_C_SFPermisosBeanRemote.getCrearArbolNuevoAdmPermisos(beanSessionScopedAdministrarPermisos.getNidRol(),
                                                                                                 beanSessionScopedAdministrarPermisos.getCUsuario());
        getBeanSessionScopedAdministrarPermisos().setListPermisos(lstPermisos);
        if (lstPermisos != null) {
            if (lstPermisos.size() > 0) {
                Set<BigDecimal> setPermisos = new HashSet<BigDecimal>(lstPermisos.get(0).getLstPermisos());
                List<BigDecimal> listFinal = new ArrayList<BigDecimal>(setPermisos);
                beanSessionScopedAdministrarPermisos.setPermisosUser(listFinal);
            }
        }
        beanSessionScopedAdministrarPermisos.setListPermisosAll(ln_C_SFPermisosBeanRemote.getCrearArbolNuevoAllPermisos(beanSessionScopedAdministrarPermisos.getPermisosUser()));
        permisosTree = new ChildPropertyTreeModel(ln_C_SFPermisosBeanRemote.getCrearArbolNuevoAllPermisos(beanSessionScopedAdministrarPermisos.getPermisosUser()),"listaHijos");
        beanSessionScopedAdministrarPermisos.setPermisosTree(permisosTree);
        return null;
    }

    public void dialogPermisosListener(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
            actualizarPermisos();
        }
    }
    
    public String openPopConfirmGrabarPermisos(){
        Utils.showPopUp(popConf,btnGrabarPerm);
        return null;
    }
    
    public String actualizarPermisos() {
        try {
            List<BigDecimal> permisosUsuario = beanSessionScopedAdministrarPermisos.getPermisosUser();
            if (permisosUsuario != null) {
                ///REMUEVE LOS PERMISOS DE USPE
                ln_T_SFPermisosRemoto.removerPermisos(beanSessionScopedAdministrarPermisos.getNidUsuario());
                boolean isOk = false;
                BeanError error = new  BeanError();
                for (int i = 0; i < permisosUsuario.size(); i++) {
                    ///INSERTA PERMISOS EN USPE Y ROPE(SI EXISTE YA NO INSERTA ROPE)
                    BeanPermisos bPermisos = ln_T_SFPermisosRemoto.actualizarPermisos(beanSessionScopedAdministrarPermisos.getNidUsuario(),
                                                                                      beanSessionScopedAdministrarPermisos.getNidRol(),
                                                                                      permisosUsuario.get(i));
                    if (bPermisos.getBeanError() != null) {
                        error = bPermisos.getBeanError();
                        if (error.getCidError().equals("000")) {
                            isOk = true;
                        }
                    }
                }
                if(isOk){
                    Utils.depurar("GRABO Gasto");
                    Utils.throwError_Aux(ctx,error.getCDescripcionError(),3);
                }else{
                    Utils.throwError_Aux(ctx,error.getCDescripcionError(),1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Utils.throwError_Aux(ctx, "Error Inesperado", 1);
        }
        return null;
    }

    public String resetearPermisos() {
        //Utils.removeSession("beanSessionScopedAdministrarPermisos");
        beanSessionScopedAdministrarPermisos.setListPermisos(null);
        permisosTree = null;
        beanSessionScopedAdministrarPermisos.setPermisosUser(null);
        mostrarListaPermisos();
        treePer.setValue(beanSessionScopedAdministrarPermisos.getPermisosTree());
        Utils.addTarget(treePer);
        return null;
    }


    public void permisoCheckedVCE(ValueChangeEvent vce) {
        try {
            vce.getComponent().processUpdates(FacesContext.getCurrentInstance());
            Object val = vce.getNewValue(); //Valor nuevo false: quito el check, true: puso un check
            RichSelectBooleanCheckbox ckBox = (RichSelectBooleanCheckbox)vce.getComponent();
            beanSessionScopedAdministrarPermisos.setNidPermVCE(new BigDecimal(ckBox.getShortDesc()));
            //Utils.depurar(oldVal);
            int decision = 0;
            BeanPermisos perm = new BeanPermisos();
            BeanPermisos permHijo = new BeanPermisos();
            List<BeanPermisos> todos = getBeanSessionScopedAdministrarPermisos().getPermisosTodos();
            if (val.toString().equals("true")) {
                decision = 1;
            } else { //quita el check
                decision = 0;
            }
            for (int i = 0; i < todos.size(); i++) {
                perm = todos.get(i);
                if (perm.getNidPermiso().intValue() == beanSessionScopedAdministrarPermisos.getNidPermVCE().intValue()) {
                    if (perm.getCIndFld().equals("S")) { //Es padre, folder osea tiene nodos dentro
                        checkearHijos(todos, permHijo, perm, decision);
                    } else { //Es N, osea nodo (no es padre) es un link (tiene url)
                        int quitar = 0; //si es cero quitara, despues al llamar mas veces subira el quitar y ya no quitara si es que es padre y tiene otros hijos
                        checkearPadres(todos, permHijo, perm, decision, quitar);//pone checks a los padres del nodo seleccionado
                    }
                }
            }
            permisosTree = new ChildPropertyTreeModel(ln_C_SFPermisosBeanRemote.getCrearArbolNuevoAllPermisos(beanSessionScopedAdministrarPermisos.getPermisosUser()),"listaHijos");
            beanSessionScopedAdministrarPermisos.setPermisosTree(permisosTree);
            treePer.setValue(beanSessionScopedAdministrarPermisos.getPermisosTree());
            Utils.addTarget(treePer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkearPadres(List<BeanPermisos> todos, BeanPermisos permPapa, BeanPermisos perm, int decision, int quitar) {
        if (todos.size() > 0) {
            for (int j = 0; j < todos.size(); j++) {
                permPapa = todos.get(j);
                if (permPapa.getNidPermiso() != null) {
                    boolean papaSiTieneOtrosHijos = false;
                    if (permPapa.getNidPermiso().intValue() == perm.getNMenuPadre().intValue()) { //Es hijo
                        if (decision == 1) { //TRUE
                            if (!beanSessionScopedAdministrarPermisos.getPermisosUser().contains(permPapa.getNidPermiso())) {
                                beanSessionScopedAdministrarPermisos.getPermisosUser().add(permPapa.getNidPermiso());
                            }
                            if (!beanSessionScopedAdministrarPermisos.getPermisosUser().contains(perm.getNidPermiso())) {
                                beanSessionScopedAdministrarPermisos.getPermisosUser().add(perm.getNidPermiso());
                            }
                        } else { //FALSE
                            if (beanSessionScopedAdministrarPermisos.getPermisosUser().contains(permPapa.getNidPermiso()) && !tieneHermanos(permPapa,perm) ) {Utils.depurar("tieneHermanos(permPapa,perm): " +tieneHermanos(permPapa,perm));
                                beanSessionScopedAdministrarPermisos.getPermisosUser().remove(permPapa.getNidPermiso());Utils.depurar("254: "+permPapa.getNidPermiso());
                                if (beanSessionScopedAdministrarPermisos.getPermisosUser().contains(perm.getNidPermiso())) {Utils.depurar("perm: "+perm.getNidPermiso());
                                    beanSessionScopedAdministrarPermisos.getPermisosUser().remove(perm.getNidPermiso());
                                }
                            }else{
                                papaSiTieneOtrosHijos = true;
                            }
                            if(quitar == 0){Utils.depurar("quitar: "+quitar);
                                if (beanSessionScopedAdministrarPermisos.getPermisosUser().contains(perm.getNidPermiso())) {Utils.depurar("quit o aa r: "+perm.getNidPermiso());
                                    beanSessionScopedAdministrarPermisos.getPermisosUser().remove(perm.getNidPermiso());
                                }
                            }
                        }
                        if(!papaSiTieneOtrosHijos){
                            quitar++;
                            checkearPadres(todos,permPapa,permPapa,decision,quitar);
                        }           
                    }
                }
            }
        }
    }

    public void checkearHijos(List<BeanPermisos> todos, BeanPermisos permHijo, BeanPermisos perm, int decision) {
        if(todos != null){
            if (todos.size() > 0) {
                for (int j = 0; j < todos.size(); j++) {
                    permHijo = todos.get(j);
                    if (permHijo.getNMenuPadre() != null) {
                        if (permHijo.getNMenuPadre().intValue() == perm.getNidPermiso().intValue()) { //Es hijo
                            if (decision == 1) { //CHECK TRUE
                                if (!beanSessionScopedAdministrarPermisos.getPermisosUser().contains(permHijo.getNidPermiso())) {
                                    beanSessionScopedAdministrarPermisos.getPermisosUser().add(permHijo.getNidPermiso());
                                }
                                if (!beanSessionScopedAdministrarPermisos.getPermisosUser().contains(perm.getNidPermiso())) {
                                    beanSessionScopedAdministrarPermisos.getPermisosUser().add(perm.getNidPermiso());
                                }
                            } else {
                                if (beanSessionScopedAdministrarPermisos.getPermisosUser().contains(permHijo.getNidPermiso()) ) {
                                    beanSessionScopedAdministrarPermisos.getPermisosUser().remove(permHijo.getNidPermiso());
                                }
                                if (beanSessionScopedAdministrarPermisos.getPermisosUser().contains(perm.getNidPermiso())) {
                                    beanSessionScopedAdministrarPermisos.getPermisosUser().remove(perm.getNidPermiso());
                                }
                            }
                            checkearHijos(permHijo.getListaHijos(),permHijo,permHijo,decision);
                        }
                    }
                }
            }
        }
    }

    public boolean tieneHermanos(BeanPermisos permPapa, BeanPermisos nodoSelect) {
        if(permPapa.getListaHijos() != null){
            if(permPapa.getListaHijos().size() > 0){
                Iterator it = permPapa.getListaHijos().iterator();
                while(it.hasNext()){
                    BeanPermisos hijoDelPapa = (BeanPermisos) it.next();Utils.depurar("hijoDelPapa "+hijoDelPapa.getCDescPermiso());
                    Utils.depurar("hijoDelPapa.getNidPermiso() "+hijoDelPapa.getNidPermiso()+" nodoSelect.getNidPermiso(): "+nodoSelect.getNidPermiso());
                    Utils.depurar("contiene: "+beanSessionScopedAdministrarPermisos.getPermisosUser().contains(hijoDelPapa.getNidPermiso()));
                    Utils.depurar("compareTo "+ hijoDelPapa.getNidPermiso().compareTo(nodoSelect.getNidPermiso()));
                    if(beanSessionScopedAdministrarPermisos.getPermisosUser().contains(hijoDelPapa.getNidPermiso()) && 
                       hijoDelPapa.getNidPermiso().compareTo(nodoSelect.getNidPermiso()) != 0){Utils.depurar("tiene hermano ");
                        return true;
                    }
                }
            }
        }Utils.depurar("NO tiene hermano ");
        return false;
    }

    public void setBeanSessionScopedAdministrarPermisos(SessionScopeBeanAdministrarPermisos beanSessionScopedAdministrarPermisos) {
        this.beanSessionScopedAdministrarPermisos = beanSessionScopedAdministrarPermisos;
    }

    public SessionScopeBeanAdministrarPermisos getBeanSessionScopedAdministrarPermisos() {
        return beanSessionScopedAdministrarPermisos;
    }

    public void setOuputTitle(RichOutputText ot1) {
        this.ouputTitle = ot1;
    }

    public RichOutputText getOuputTitle() {
        return ouputTitle;
    }

    public void setTreePer(RichTreeTable tt1) {
        this.treePer = tt1;
    }

    public RichTreeTable getTreePer() {
        return treePer;
    }

    public void setBtnVerUsuarios(RichCommandButton cb1) {
        this.btnVerUsuarios = cb1;
    }

    public RichCommandButton getBtnVerUsuarios() {
        return btnVerUsuarios;
    }

    public ChildPropertyTreeModel getPermisosTree() {
        return permisosTree;
    }


    public void setPgl1(RichPanelGridLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGridLayout getPgl1() {
        return pgl1;
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

    public void setGc3(RichGridCell gc3) {
        this.gc3 = gc3;
    }

    public RichGridCell getGc3() {
        return gc3;
    }

    public void setBtnResetPerm(RichCommandButton cb2) {
        this.btnResetPerm = cb2;
    }

    public RichCommandButton getBtnResetPerm() {
        return btnResetPerm;
    }

    public void setBtnGrabarPerm(RichCommandButton cb3) {
        this.btnGrabarPerm = cb3;
    }

    public RichCommandButton getBtnGrabarPerm() {
        return btnGrabarPerm;
    }

    public void setPopupUsuarios(RichPopup p1) {
        this.popupUsuarios = p1;
    }

    public RichPopup getPopupUsuarios() {
        return popupUsuarios;
    }

    public void setDialogUsuarios(RichDialog d1) {
        this.dialogUsuarios = d1;
    }

    public RichDialog getDialogUsuarios() {
        return dialogUsuarios;
    }

    public void setT1(RichTable t1) {
        this.t1 = t1;
    }

    public RichTable getT1() {
        return t1;
    }

    public void setOutUsuario(RichOutputText ot6) {
        this.outUsuario = ot6;
    }

    public RichOutputText getOutUsuario() {
        return outUsuario;
    }

    public void setOutUsuario2(RichOutputText ot6) {
        this.outUsuario2 = ot6;
    }

    public RichOutputText getOutUsuario2() {
        return outUsuario2;
    }

    public void setInputPrueba(RichInputText it1) {
        this.inputPrueba = it1;
    }

    public RichInputText getInputPrueba() {
        return inputPrueba;
    }

    public void setOuputPrueba(RichOutputText ot6) {
        this.ouputPrueba = ot6;
    }

    public RichOutputText getOuputPrueba() {
        return ouputPrueba;
    }

    public void setBtnConfirmar(RichCommandButton cb1) {
        this.btnConfirmar = cb1;
    }

    public RichCommandButton getBtnConfirmar() {
        return btnConfirmar;
    }

    public void setPopConf(RichPopup p1) {
        this.popConf = p1;
    }

    public RichPopup getPopConf() {
        return popConf;
    }

    public void setDiaConf(RichDialog d1) {
        this.diaConf = d1;
    }

    public RichDialog getDiaConf() {
        return diaConf;
    }

    public void setGrabPerm(RichOutputText grabPerm) {
        this.grabPerm = grabPerm;
    }

    public RichOutputText getGrabPerm() {
        return grabPerm;
    }
}
