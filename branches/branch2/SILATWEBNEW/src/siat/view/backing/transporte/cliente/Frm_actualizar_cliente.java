package siat.view.backing.transporte.cliente;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import javax.naming.Context;
import javax.naming.InitialContext;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.RichSubform;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectItem;
import oracle.adf.view.rich.component.rich.input.RichSelectManyCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichDecorativeBox;

import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.event.DialogEvent;

import oracle.adf.view.rich.render.ClientEvent;

import oracle.adfinternal.view.faces.model.binding.FacesCtrlHierNodeBinding;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import siat.view.backing.utiles.ADFUtil;
import siat.view.backing.utiles.Utils;

import silat.servicios_negocio.BDLSF.IR.BDL_C_SFUtilsRemote;
import silat.servicios_negocio.Beans.BeanADRelacionEmpresa;
import silat.servicios_negocio.Beans.BeanADTipoRelacion;
import silat.servicios_negocio.Beans.BeanADUbigeo;
import silat.servicios_negocio.Beans.BeanChofer;
import silat.servicios_negocio.Beans.BeanDireccion;
import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanFlota;
import silat.servicios_negocio.Beans.BeanGasto;
import silat.servicios_negocio.Beans.BeanUsuarioAutenticado;
import silat.servicios_negocio.LNSF.IR.LN_C_SFChoferRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFDireccionRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFEmpresasRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFFlotaRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFGastosRemoto;
import silat.servicios_negocio.LNSF.IR.LN_C_SFGuiaRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFManifiestoRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFRelacionEmpresaRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFUtilsRemote;
import silat.servicios_negocio.LNSF.IR.LN_T_SFEmpresaRemote;
import silat.servicios_negocio.LNSF.IR.LN_T_SFFlotaRemote;

public class Frm_actualizar_cliente {
    
    private RichDecorativeBox db1;
    private RichPanelGroupLayout pgl1;
    private RichOutputText ot1;
    private RichPanelGroupLayout pgl2;
    private RichPanelGroupLayout pgl3;
    private RichTable t1;
    private RichCommandButton cb1;
    private RichPopup p1;
    private RichDialog d1;
    private RichPanelFormLayout pfl1;
    private RichInputText it1;
    private RichInputText it2;
    private RichInputText it3;
    private RichInputText it4;
    private RichInputText it5;
    private RichInputText it6;
    private RichCommandButton cb2;
    private RichSelectManyCheckbox smc1;
    private UISelectItems si1;
    private RichPopup p2;
    private RichInputText it7;
    private RichSelectOneChoice soc1;
    private UISelectItems si2;
    private RichSelectOneChoice soc2;
    private UISelectItems si3;
    private RichSelectOneChoice soc3;
    private UISelectItems si4;
    private RichCommandButton cb3;
    private RichPanelFormLayout pfl2;
    private RichTable t2;
    private RichCommandButton cb4;

    private RichSelectItem itemCliente;
    private RichSelectItem si7;
    private RichSelectItem itemProv;
    private RichSpacer s1;
    private RichCommandButton btnEditDirec;
    private RichSpacer s2;
    private RichCommandButton btnEditFlotas;
    private RichSpacer s3;
    private RichCommandButton btnEditChofe;
    private RichPopup popupEditDirec;
    private RichDialog d2;
    private RichTable tbDirec;
    private RichCommandButton btnNewDire;
    private RichCommandButton btnEditDire;
    private RichCommandButton btnDeleteDire;
    private RichPopup popupChofer;
    private RichDialog d3;
    private RichTable tbChof;
    private RichCommandButton btnNewChof;
    private RichCommandButton btnEditChof;
    private RichCommandButton btnDeleteChof;
    private RichSpacer s4;
    private RichSpacer s5;
    private RichSpacer s6;
    private RichSpacer s7;
    private RichPopup popupEditFlota;
    private RichDialog d4;
    private RichTable tbFlota;
    private RichCommandButton btnNewFlota;
    private RichCommandButton btnEditFltota;
    private RichCommandButton btnDeleteFlota;
    private RichSpacer s8;
    private RichSpacer s9;
    private RichPopup popupADDChof;
    private RichSubform s10;
    private RichPanelFormLayout panelADDChof;
    private RichInputText txtNombreYApellido;
    private RichInputText txtLicencia;
    private RichCommandButton btnADDChof;
    private RichPopup popupNuevaFlota;
    private RichSubform s11;
    private RichPanelFormLayout formNuevaFlota;
    private RichInputText txtMarca;
    private RichInputText txtPlaca;
    private RichInputText txtDescripcion;
    private RichSelectOneChoice choiceConfig;    
    private RichSelectItem si5;
    private RichSelectItem si6;
    private RichSelectItem si8;
    private RichSelectItem si9;
    private RichSelectItem si10;
    private RichSelectItem si11;
    private RichSelectItem si12;
    private RichSelectItem si13;
    private RichCommandButton btnADDFlota;
    private RichPopup popupNuevaDireccion;
    private RichSubform s12;
    private RichPanelFormLayout formNuevaDireccion;
    private RichInputText txtDireccion;
    private RichSelectOneChoice choiceDepartamento;
    private UISelectItems si14;
    private RichSelectOneChoice choiceProvincia;
    private UISelectItems si15;
    private RichSelectOneChoice choiceDistrito;
    private UISelectItems si16;
    private RichCommandButton btnRegistrarDireccion;
    private RichInputText inputCertificadoInscripcion;
    private RichSubform s13;
    private RichInputText it8;
    private RichInputText it9;
    private RichSelectOneChoice soc4;
    private UISelectItems si17;
    private RichCommandButton btnBuscar;
    private RichCommandButton btnLimpiar;
    private RichCommandButton btnBorrar;
    private RichPopup popConf;
    private RichDialog diaConf;
    private RichOutputText otMsjBorrar;
    // Mis Variables
    private SessionScopeBeanActualizarCliente beanSessionActualizarCliente;
    private final static String LOOKUP_NAME_SFDireccion_REMOTO       = "LUBAL_SIAT_APP-SILATNEGOCIO-LN_C_SFDireccion";
    private final static String LOOKUP_NAME_SFRelacionEmpresa_REMOTO = "mapLN_C_SFRelacionEmpresa";
    private final static String LOOKUP_NAME_SFUtils_REMOTO           = "mapBDL_C_SFUtils";
    private final static String LOOKUP_NAME_SFEmpresas_REMOTO        = "mapLN_C_SFEmpresas";
    private final static String LOOKUP_NAME_SFChofer_REMOTO          = "mapLN_C_SFChofer";
    private final static String LOOKUP_NAME_SFFlota_REMOTO           = "mapLN_C_SFFlota";
    private final static String LOOKUP_NAME_SFTFlota_REMOTO          = "LUBAL_SIAT_APP-SILATNEGOCIO-LN_T_SFFlota";
    private final static String LOOKUP_NAME_SFCMANIF_REMOTO          = "mapLN_C_SFManifiesto";
    private final static String LOOKUP_NAME_SFUTILS_REMOTO           = "mapLN_C_SFUtils";
    private final static String LOOKUP_NAME_SFUT_EMP_REMOTO          = "mapLN_T_SFEmpresa";
    private final static String LOOKUP_NAME_SFCGUIA_REMOTO           = "mapLN_C_SFGuia";
    private final static String LOOKUP_NAME_SFGASTOSCONSULTA_REMOTO  = "LUBAL_SIAT_APP-SILATNEGOCIO-LN_C_SFGastos";
    private LN_T_SFEmpresaRemote         ln_T_SFEmpresaRemote;
    private LN_C_SFGuiaRemote            ln_C_SFGuiaRemote;
    private LN_C_SFManifiestoRemote      ln_C_SFManifiestoRemote;
    private LN_T_SFFlotaRemote           ln_T_SFFlotaRemote;
    private LN_C_SFUtilsRemote           ln_C_SFUtilsRemote;
    private LN_C_SFFlotaRemote           ln_C_SFFlotaRemote;
    private LN_C_SFChoferRemote          ln_C_SFChoferRemote;
    private LN_C_SFEmpresasRemote        ln_C_SFEmpresasRemote;
    private BDL_C_SFUtilsRemote          bdL_C_SFUtilsRemote;
    private LN_C_SFRelacionEmpresaRemote ln_C_SFRelacionEmpresaRemote;
    private LN_C_SFDireccionRemote       ln_C_SFDireccionRemote;
    private LN_C_SFGastosRemoto          ln_C_SFGastosRemoto;
    private List listRelacion;
    private List Departamentos;
    private List Provincias;
    private List Distritos;
    private BeanError beanError = new BeanError();
    private List<SelectItem> relaList;
    private List<SelectItem> relaValue;
    private BeanChofer beanChofer = new BeanChofer();
    private BeanFlota beanFlota=new BeanFlota();
    private BeanDireccion beanDireccion=new BeanDireccion();
    private BeanEmpresa beanEmpresa=new BeanEmpresa();
    private BeanUsuarioAutenticado beanUsuario= new BeanUsuarioAutenticado();
    FacesContext ctx = FacesContext.getCurrentInstance();
    private RichInputText inputCerInsFlota;

    public Frm_actualizar_cliente() {
        try {
            final Context ctx;      
            ctx = new InitialContext();
            ln_C_SFUtilsRemote = (LN_C_SFUtilsRemote)                    ctx.lookup(LOOKUP_NAME_SFUTILS_REMOTO);
            ln_C_SFDireccionRemote = (LN_C_SFDireccionRemote)            ctx.lookup(LOOKUP_NAME_SFDireccion_REMOTO);
            ln_C_SFRelacionEmpresaRemote = (LN_C_SFRelacionEmpresaRemote)ctx.lookup(LOOKUP_NAME_SFRelacionEmpresa_REMOTO);
            bdL_C_SFUtilsRemote = (BDL_C_SFUtilsRemote)                  ctx.lookup(LOOKUP_NAME_SFUtils_REMOTO);
            ln_C_SFEmpresasRemote = (LN_C_SFEmpresasRemote)              ctx.lookup(LOOKUP_NAME_SFEmpresas_REMOTO);
            ln_C_SFChoferRemote = (LN_C_SFChoferRemote)                  ctx.lookup(LOOKUP_NAME_SFChofer_REMOTO);
            ln_C_SFFlotaRemote = (LN_C_SFFlotaRemote)                    ctx.lookup(LOOKUP_NAME_SFFlota_REMOTO);
            ln_T_SFFlotaRemote = (LN_T_SFFlotaRemote)                    ctx.lookup(LOOKUP_NAME_SFTFlota_REMOTO);
            ln_T_SFEmpresaRemote = (LN_T_SFEmpresaRemote)                ctx.lookup(LOOKUP_NAME_SFUT_EMP_REMOTO);
            ln_C_SFGuiaRemote = (LN_C_SFGuiaRemote)                      ctx.lookup(LOOKUP_NAME_SFCGUIA_REMOTO);
            ln_C_SFManifiestoRemote = (LN_C_SFManifiestoRemote)          ctx.lookup(LOOKUP_NAME_SFCMANIF_REMOTO);
            ln_C_SFGastosRemoto = (LN_C_SFGastosRemoto)                  ctx.lookup(LOOKUP_NAME_SFGASTOSCONSULTA_REMOTO);
            this.setDepartamentos(this.FillList(1, null, null));       
            this.setListRelacion(Utils.llenarBeanCombos(ln_C_SFUtilsRemote.getListaParaCombo_LN("ADTipoRelacion","descripcionTipoRelacion","nidTipoRelacion")));
            beanUsuario = (BeanUsuarioAutenticado) Utils.getSession("USER");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @PostConstruct
    public void postConstructor(){
        if(beanSessionActualizarCliente.getExec() == 0){
            beanSessionActualizarCliente.setExec(1);
            filtrarEmpresas();           
        }else{
            //Utils.depurar("POST CONSTRUCT otras veces");
        }
         
    }
    public String filtrarEmpresas() {
         beanSessionActualizarCliente.setBeanListEmpresas(null);
         BeanEmpresa beanEmpresa = new BeanEmpresa();       
         beanEmpresa.setCRazonSocial(beanSessionActualizarCliente.getRazonSocial());
         beanEmpresa.setCRuc(beanSessionActualizarCliente.getRuc()); 
         if(beanSessionActualizarCliente.getRelacionEmpresa()!=null){
             BeanADTipoRelacion tipoRela= new BeanADTipoRelacion();
             tipoRela.setNidTipoRelacion(Integer.parseInt(beanSessionActualizarCliente.getRelacionEmpresa()));
             BeanADRelacionEmpresa relaEmpresa=new BeanADRelacionEmpresa();
             relaEmpresa.setAdTipoRelacion(tipoRela);
             beanEmpresa.setRelaEmpresa(relaEmpresa);
         }
         beanSessionActualizarCliente.setBeanListEmpresas(ln_C_SFEmpresasRemote.findEmpresasByAttributes(beanEmpresa));
         if(t1 != null){
            t1.setValue(beanSessionActualizarCliente.getBeanListEmpresas());
            Utils.addTarget(t1);
        }
         return null;
     }
    public void limpiar(ActionEvent actionEvent){
        clearLista();
    }

    public List<BeanEmpresa> clearLista() {
        resetearCamposConsulta();
        BeanEmpresa beanEmpresa = new BeanEmpresa();
        beanSessionActualizarCliente.setBeanListEmpresas(ln_C_SFEmpresasRemote.findEmpresasByAttributes(beanEmpresa));
        if (t1 != null) {
            t1.setValue(beanSessionActualizarCliente.getBeanListEmpresas());
            Utils.addTarget(t1);
        }
        Utils.unselectFilas(t1);
        return beanSessionActualizarCliente.getBeanListEmpresas();
    }
    
    public void resetearCamposConsulta() {       
        beanSessionActualizarCliente.setRazonSocial(null);
        beanSessionActualizarCliente.setRelacionEmpresa(null);
        beanSessionActualizarCliente.setRuc(null);
        beanSessionActualizarCliente.setBeanListEmpresas(null);        
    }

    public void botonesProveedor() {
        int num = 0;
        List<BeanADRelacionEmpresa> objArr = ln_C_SFRelacionEmpresaRemote.getRelacion(beanSessionActualizarCliente.getNidParty());
        if (objArr != null) { //objArr.size() > 0
            for (int x = 0; x < objArr.size(); x++) {
                String obj = objArr.get(x).getDescRela();
                if (obj.equals("Proveedor")) { //Proveedor
                    num++;
                }
            }
            if (num != 0) {
                btnEditChofe.setDisabled(false);
                btnEditFlotas.setDisabled(false);
         /*       if (inputCertificadoInscripcion != null) {
                    inputCertificadoInscripcion.setVisible(true);
                    inputCertificadoInscripcion.setRequired(true);
                }
                beanSessionActualizarCliente.setEstadoRequieredInputText(true);
                beanSessionActualizarCliente.setEstadoVisibleInputText(true);*/
                AdfFacesContext.getCurrentInstance().addPartialTarget(btnEditChofe);
                AdfFacesContext.getCurrentInstance().addPartialTarget(btnEditFlotas);
            }
            if (num == 0) {
                btnEditChofe.setDisabled(true);
                btnEditFlotas.setDisabled(true);
       /*         beanSessionActualizarCliente.setEstadoRequieredInputText(false);
                beanSessionActualizarCliente.setEstadoVisibleInputText(false);
                if (inputCertificadoInscripcion != null) {
                    inputCertificadoInscripcion.setVisible(false);
                    inputCertificadoInscripcion.setRequired(false);
                }*/
                AdfFacesContext.getCurrentInstance().addPartialTarget(btnEditChofe);
                AdfFacesContext.getCurrentInstance().addPartialTarget(btnEditFlotas);
            }
        }
    }

    public void mostrarInputCertificado(ValueChangeEvent vce) {
        List<String> objArr = (List<String>)vce.getNewValue();
        int num = 0;
        if (objArr != null) { //objArr.size() > 0
            for (int x = 0; x < objArr.size(); x++) {
                String obj = objArr.get(x);
                if (obj.equals("1")) { //Proveedor
                    num++;
                }
            }
           /* if (num != 0) {
                beanSessionActualizarCliente.setEstadoRequieredInputText(true);
                beanSessionActualizarCliente.setEstadoVisibleInputText(true);
                inputCertificadoInscripcion.setVisible(true);
                inputCertificadoInscripcion.setRequired(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(inputCertificadoInscripcion);
            }
            if (num == 0) {
                beanSessionActualizarCliente.setEstadoRequieredInputText(false);
                beanSessionActualizarCliente.setEstadoVisibleInputText(false);
                inputCertificadoInscripcion.setVisible(false);
                inputCertificadoInscripcion.setRequired(false);
                AdfFacesContext.getCurrentInstance().addPartialTarget(inputCertificadoInscripcion);
            }*/
        }
    }
    
    public String setDireccion() {
        beanSessionActualizarCliente.setBeanListDire(ln_C_SFDireccionRemote.getRelacion(beanSessionActualizarCliente.getNidParty()));
        Utils.unselectFilas(tbDirec);
        Utils.showPopUpMIDDLE(popupEditDirec);
        return null;
    }

    public String setChoferes() {
        beanSessionActualizarCliente.setBeanListChof(ln_C_SFChoferRemote.traerChoferesPorEmpresa(beanSessionActualizarCliente.getNidParty().intValue()));
        Utils.unselectFilas(tbChof);
        Utils.showPopUpMIDDLE(popupChofer);
        return null;
    }

    public String setFlotas() {
        beanSessionActualizarCliente.setBeanListFlota(ln_C_SFFlotaRemote.getFlotasPorEmpresa(beanSessionActualizarCliente.getNidParty().intValue()));
        Utils.unselectFilas(tbFlota);
        Utils.showPopUpMIDDLE(popupEditFlota);
        return null;
    }

    public void seleccionarChofer(SelectionEvent selectionEvent) {
        controlarBotonesChofer(false);
        RichTable t = (RichTable)selectionEvent.getSource();
        Object _selectedRowData = t.getSelectedRowData();
        beanChofer = (BeanChofer)_selectedRowData;
        beanSessionActualizarCliente.setBeanChofer(beanChofer);
    } 
    
    public void seleccionarFlota(SelectionEvent selectionEvent) {
        controlarBotonesFlota(false);
        RichTable t = (RichTable)selectionEvent.getSource();
        Object _selectedRowData = t.getSelectedRowData();
        beanFlota = (BeanFlota)_selectedRowData;
        beanSessionActualizarCliente.setBeanFlota(beanFlota);
    }
    public void seleccionarDireccion(SelectionEvent selectionEvent) {
        controlarBotonesDireccion(false);
        RichTable t = (RichTable)selectionEvent.getSource();
        Object _selectedRowData = t.getSelectedRowData();
        beanDireccion = (BeanDireccion)_selectedRowData;
        beanSessionActualizarCliente.setBeanDireccion(beanDireccion);
        beanSessionActualizarCliente.setDireccionEmpresa(beanDireccion.getCDireccion());
        beanSessionActualizarCliente.setDepartamentoEmpresa(beanDireccion.getDepartamento());
        beanSessionActualizarCliente.setProvinciaEmpresa(beanDireccion.getProvicia());
        beanSessionActualizarCliente.setDistritoEmpresa(beanDireccion.getDistrito());
    }
 
    public void editarChofer(ActionEvent actionEvent) {
        beanSessionActualizarCliente.setAccion(2); //Modificar
        beanChofer = new BeanChofer();
        beanChofer = beanSessionActualizarCliente.getBeanChofer();
        beanSessionActualizarCliente.setNombreChofer(beanChofer.getNombres());
        beanSessionActualizarCliente.setLicenciaChofer(beanChofer.getLicencia());
        Utils.showPopUpMIDDLE(popupADDChof);
    }
    
    public void editarFlota(ActionEvent actionEvent) {
        beanSessionActualizarCliente.setAccion(2); //Modificar
        beanFlota = new BeanFlota();
        beanFlota = beanSessionActualizarCliente.getBeanFlota();
        beanSessionActualizarCliente.setMarcaVehiculo(beanFlota.getCMarvehi());
        Utils.putSession("OBJ_FLOTA", beanFlota.getCPlaca());
        beanSessionActualizarCliente.setPlacaVehiculo(beanFlota.getCPlaca());
        beanSessionActualizarCliente.setConfVehicular(beanFlota.getCConfveh());
        beanSessionActualizarCliente.setDescripcionVehiculo(beanFlota.getCDescFlota());
        beanSessionActualizarCliente.setCCerInsFlota(beanFlota.getCCerins());
        Utils.showPopUpMIDDLE(popupNuevaFlota);
    }
    
    public void editarDireccion(ActionEvent actionEvent) {
        beanSessionActualizarCliente.setAccion(2); //Modificar
        beanDireccion = new BeanDireccion();
        beanDireccion = beanSessionActualizarCliente.getBeanDireccion();
        beanSessionActualizarCliente.setDireccionEmpresa(beanDireccion.getCDireccion());
        beanSessionActualizarCliente.setDepartamentoEmpresa(beanDireccion.getDescDepartamento());
        this.setProvincias(this.FillList(2, beanDireccion.getDescDepartamento(), null));
        beanSessionActualizarCliente.setProvinciaEmpresa(beanDireccion.getDescProvincia());
        this.setDistritos(this.FillList(3, null, beanDireccion.getDescDistrito()));
        beanSessionActualizarCliente.setDistritoEmpresa(beanDireccion.getDescDistrito());
        Utils.showPopUpMIDDLE(popupNuevaDireccion);
    }
    
    public void controlarBotonesChofer(boolean estado) {
        btnEditChof.setDisabled(estado);
        btnDeleteChof.setDisabled(estado);
        Utils.addTargetMany(btnEditChof,btnDeleteChof);
    }
    
    public void controlarBotonesDireccion(boolean estado) {
        btnEditDire.setDisabled(estado);
        btnDeleteDire.setDisabled(estado);
        Utils.addTargetMany(btnEditDire,btnDeleteDire);
    }
    
    public void controlarBotonesFlota(boolean estado) {
        btnEditFltota.setDisabled(estado);
        btnDeleteFlota.setDisabled(estado);
        Utils.addTargetMany(btnDeleteFlota,btnEditFltota);
    }
    
    public void openPopUpNuevaFlota(ActionEvent actionEvent) {
        Utils.removeSession("OBJ_FLOTA");
        if(txtMarca!=null){            
            resetearDatosFlota();
        }
        beanSessionActualizarCliente.setAccion(1); //Grabar
        controlarBotonesFlota(true);
        Utils.showPopUpMIDDLE(popupNuevaFlota);//////////////////
    }

    public void openPopUpNuevoChofer(ActionEvent actionEvent) {
        if(txtNombreYApellido!=null){            
            resetearDatosChofer();
        }
        beanSessionActualizarCliente.setAccion(1); //Grabar
        controlarBotonesChofer(true);
        Utils.showPopUpMIDDLE(popupADDChof);
    }
    
    public void openPopUpNuevaDireccion(ActionEvent actionEvent) {
        if(txtDireccion != null){            
            resetearDatosDireccion();
        }
        beanSessionActualizarCliente.setAccion(1); //Grabar
        controlarBotonesDireccion(true);
        Utils.showPopUpMIDDLE(popupNuevaDireccion);
    }

    public void mostrarProvincias(ValueChangeEvent vcl) {
        vcl.getComponent().processUpdates(FacesContext.getCurrentInstance());
        String newValue = vcl.getNewValue().toString();
        this.setProvincias(this.FillList(2, newValue, null));
        if(this.getDistritos() != null){
            this.getDistritos().removeAll(this.getDistritos());
        }
        Utils.addTargetMany(choiceProvincia,choiceDistrito);
    }

    public void mostrarDistritos(ValueChangeEvent vcl) {
        vcl.getComponent().processUpdates(FacesContext.getCurrentInstance());
        String newValue = vcl.getNewValue().toString();
        this.setDistritos(this.FillList(3, null, newValue));
        AdfFacesContext.getCurrentInstance().addPartialTarget(choiceDistrito);
    }

    public ArrayList FillList(int tipUbigeo, String dep, String prov) {
        ArrayList List = new ArrayList();
        List<BeanADUbigeo> entitylist = bdL_C_SFUtilsRemote.getUbigeos(tipUbigeo, dep, prov);
        for (BeanADUbigeo ubigeo : entitylist) {
            List.add(new SelectItem(ubigeo.getCidUbigeo().toString(), ubigeo.getCDescUbigeo().toString()));
        }
        return List;
    }

    public void ActualizarCliente(ActionEvent actionEvent) {
        ActualizarCliente();
    }

    public void resetearDatosChofer() {
        beanSessionActualizarCliente.setNombreChofer("");
        beanSessionActualizarCliente.setLicenciaChofer("");    
    }
    
    public void resetearDatosFlota() {
        beanSessionActualizarCliente.setMarcaVehiculo("");
        beanSessionActualizarCliente.setPlacaVehiculo(""); 
        beanSessionActualizarCliente.setConfVehicular("");
        beanSessionActualizarCliente.setDescripcionVehiculo("");
        beanSessionActualizarCliente.setCCerInsFlota("");
        txtMarca.resetValue();
        txtPlaca.resetValue();
        txtDescripcion.resetValue();
        choiceConfig.resetValue();
        inputCerInsFlota.resetValue();
        Utils.addTargetMany(txtMarca,txtPlaca,txtDescripcion,choiceConfig,inputCerInsFlota);
    }
    public void resetearDatosDireccion() {
        txtDireccion.resetValue();
        beanSessionActualizarCliente.setDireccionEmpresa(null);
        beanSessionActualizarCliente.setDepartamentoEmpresa("0");
        choiceDepartamento.resetValue();
        choiceProvincia.resetValue();
        choiceDistrito.resetValue();
        Utils.addTargetMany(txtDireccion,choiceDepartamento,choiceProvincia,choiceDistrito);
    }

    public void ActualizarChofer(ActionEvent actionEvent) {        
        ActualizarChofer();
        btnEditChof.setDisabled(true);
        btnDeleteChof.setDisabled(true);
        Utils.unselectFilas(tbChof);
        Utils.addTargetMany(btnEditChof,btnDeleteChof);
    }
    public void ActualizarFlota(ActionEvent actionEvent){
        ActualizarFlota();
        btnEditFltota.setDisabled(true);
        btnDeleteFlota.setDisabled(true);
        Utils.unselectFilas(tbFlota);
        Utils.addTargetMany(btnEditFltota,btnDeleteFlota);
    }
    
    public void ActualizarDireccion(ActionEvent actionEvent){
        ActualizarDireccion();
        beanSessionActualizarCliente.setDireccionEmpresa("");
        beanSessionActualizarCliente.setDepartamentoEmpresa("");
        controlarBotonesDireccion(true);
        Utils.unselectFilas(tbDirec);
    }
    
    public String AnularFlota(){   
        if(Utils.hasPermiso(beanUsuario.getLstPermisos(),new BigDecimal("18"))){
            String estado = "0";
            BeanFlota bFlota=new BeanFlota();
            bFlota=ln_T_SFFlotaRemote.anularFlota(beanSessionActualizarCliente.getBeanFlota().getNidFlota(),estado);
            beanSessionActualizarCliente.setBeanListFlota(ln_C_SFFlotaRemote.getFlotasPorEmpresa(beanSessionActualizarCliente.getNidParty().intValue()));
             popupEditFlota.hide();
             if (bFlota.getBeanError() != null) {
                BeanError error = bFlota.getBeanError();
                int severidad = 0;
                if (error.getCidError().equals("000") ) {
                    severidad = 3;
                    Utils.depurar("GASTO ANULADO");
                } else {
                    severidad = 1;
                }
                Utils.throwError_Aux(ctx, error.getCDescripcionError(), severidad);
            }else {
                Utils.throwError_Aux(ctx, "Error Inesperado", 1);
            }
        }else{
            Utils.throwError_Aux(ctx, "Ud. no tiene el permiso para realizar esta ", 1);
        } return null;}
    
    public BeanError ActualizarFlota() {
        try {
            if (beanSessionActualizarCliente.getAccion() == 1) {
                ADFUtil.setEL("#{pageFlowScope.nidParty}", beanSessionActualizarCliente.getNidParty());
                Utils.mandarParametro("nidParty", "#{pageFlowScope.nidParty}", "registrarFlota");
                Utils.mandarParametro("marca",  "#{backingBeanScope.backing_transporte_cliente_frm_actualizar_cliente.txtMarca.value}", "registrarFlota");
                Utils.mandarParametro("placa", "#{backingBeanScope.backing_transporte_cliente_frm_actualizar_cliente.txtPlaca.value}", "registrarFlota");
                Utils.mandarParametro("configuracion",  "#{backingBeanScope.backing_transporte_cliente_frm_actualizar_cliente.choiceConfig.value}", "registrarFlota");
                Utils.mandarParametro("descripcion", "#{backingBeanScope.backing_transporte_cliente_frm_actualizar_cliente.txtDescripcion.value}", "registrarFlota");
                ADFUtil.setEL("#{pageFlowScope.cCerIns}", beanSessionActualizarCliente.getCCerInsFlota());
                Utils.mandarParametro("cCerIns", "#{pageFlowScope.cCerIns}", "registrarFlota");
                ADFUtil.invokeEL("#{bindings.registrarFlota.execute}");
                beanSessionActualizarCliente.setBeanListFlota(ln_C_SFFlotaRemote.getFlotasPorEmpresa(beanSessionActualizarCliente.getNidParty().intValue()));
                resetearDatosFlota();
                popupNuevaFlota.hide();
               // refresh();
            }
            if(beanSessionActualizarCliente.getAccion()==2){
                ADFUtil.setEL("#{pageFlowScope.nidParty}", beanSessionActualizarCliente.getNidParty());
                Utils.mandarParametro("nidParty", "#{pageFlowScope.nidParty}", "actualizarFlota");
                ADFUtil.setEL("#{pageFlowScope.nidFlota}", beanSessionActualizarCliente.getBeanFlota().getNidFlota());
                Utils.mandarParametro("nidFlota", "#{pageFlowScope.nidFlota}", "actualizarFlota");
                Utils.mandarParametro("marca",  "#{backingBeanScope.backing_transporte_cliente_frm_actualizar_cliente.txtMarca.value}", "actualizarFlota");
                Utils.mandarParametro("placa", "#{backingBeanScope.backing_transporte_cliente_frm_actualizar_cliente.txtPlaca.value}", "actualizarFlota");
                Utils.mandarParametro("configuracion",  "#{backingBeanScope.backing_transporte_cliente_frm_actualizar_cliente.choiceConfig.value}", "actualizarFlota");
                Utils.mandarParametro("descripcion", "#{backingBeanScope.backing_transporte_cliente_frm_actualizar_cliente.txtDescripcion.value}", "actualizarFlota");
                ADFUtil.setEL("#{pageFlowScope.cCeInsFlota}", beanSessionActualizarCliente.getCCerInsFlota());
                Utils.mandarParametro("cCeInsFlota", "#{pageFlowScope.cCeInsFlota}", "actualizarFlota");
                BeanFlota bFlota = (BeanFlota) ADFUtil.invokeEL("#{bindings.actualizarFlota.execute}");
                if(bFlota != null){
                    if(bFlota.getBeanError() != null){
                        beanError = bFlota.getBeanError();
                        if (beanError != null) {
                            if(!beanError.getCidError().equals("000")){
                                Utils.throwError_Aux(ctx, beanError.getCDescripcionError(),1);
                                return null;
                            }else{
                                Utils.throwError_Aux(ctx, beanError.getCDescripcionError(),3);
                            }
                        } else {
                            Utils.throwError_Aux(ctx, "ERROR",1);
                            return null;
                        }
                    }
                }else{
                    Utils.throwError_Aux(ctx,"Error inesperado.",3);
                }
                beanSessionActualizarCliente.setBeanListFlota(ln_C_SFFlotaRemote.getFlotasPorEmpresa(beanSessionActualizarCliente.getNidParty().intValue()));
                resetearDatosFlota();
                popupNuevaFlota.hide();
                clearLista();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beanError;
    }

    public void borrarChofer(ActionEvent actionEvent) {
        if(beanSessionActualizarCliente.getBeanListChof() != null){
            if(beanSessionActualizarCliente.getBeanListChof().size() > 1){
                int cantChoferEnGuias = ln_C_SFGuiaRemote.cantGuiasByChofer(beanSessionActualizarCliente.getBeanChofer().getNidChofer());
                int cantChoferEnManif = ln_C_SFManifiestoRemote.cantManifiestosByChofer(beanSessionActualizarCliente.getBeanChofer().getNidChofer());
                int cantTotal = cantChoferEnGuias + cantChoferEnManif;
                if(cantTotal == 0){
                    ADFUtil.setEL("#{pageFlowScope.nidChofer}", beanSessionActualizarCliente.getBeanChofer().getNidChofer());
                    Utils.mandarParametro("nidChofer", "#{pageFlowScope.nidChofer}", "removerChofer");
                    ADFUtil.invokeEL("#{bindings.removerChofer.execute}");
                    beanSessionActualizarCliente.setBeanListChof(ln_C_SFChoferRemote.traerChoferesPorEmpresa(beanSessionActualizarCliente.getNidParty().intValue()));
                }else{
                    Utils.throwError_Aux(ctx,"El chofer esta siendo usada en una Guia/Manifiesto, no puede borrar.",4);
                }
            }
        }
    }
    
    public void borrarFlota(ActionEvent actionEvent) {
        if(beanSessionActualizarCliente.getBeanListFlota() != null){
            if(beanSessionActualizarCliente.getBeanListFlota().size() > 1){
                int cantFlotaEnGastos = ln_C_SFGastosRemoto.cantGastosByFlota(beanSessionActualizarCliente.getBeanFlota().getNidFlota());
                int cantFlotaEnGuias  = ln_C_SFGuiaRemote.cantGuiasByFlota(beanSessionActualizarCliente.getBeanFlota().getNidFlota());
                int cantFlotaEnManif  = ln_C_SFManifiestoRemote.cantManifiestosByFlota(beanSessionActualizarCliente.getBeanFlota().getNidFlota());
                int total = cantFlotaEnGastos + cantFlotaEnGuias + cantFlotaEnManif;
                if(total == 0){
                    ADFUtil.setEL("#{pageFlowScope.nidFlota}", beanSessionActualizarCliente.getBeanFlota().getNidFlota());
                    Utils.mandarParametro("nidFlota", "#{pageFlowScope.nidFlota}", "removerFlota");
                    ADFUtil.invokeEL("#{bindings.removerFlota.execute}");
                    beanSessionActualizarCliente.setBeanListFlota(ln_C_SFFlotaRemote.getFlotasPorEmpresa(beanSessionActualizarCliente.getNidParty().intValue()));     
                }else{
                    Utils.throwError_Aux(ctx,"La flota esta siendo usada en un Gasto/Guia/Manifiesto, no puede borrar.",4);
                }
            }
        }
    }
    
    public void borrarDireccion(ActionEvent actionEvent) {
        if(beanSessionActualizarCliente.getBeanListDire() != null){
            if(beanSessionActualizarCliente.getBeanListDire().size() > 1){ 
                int cantDireccionEnGuias = ln_C_SFGuiaRemote.cantGuiasByDireccion(beanSessionActualizarCliente.getBeanDireccion().getNidDireccion());
                if(cantDireccionEnGuias == 0){
                    ADFUtil.setEL("#{pageFlowScope.nidDireccion}", beanSessionActualizarCliente.getBeanDireccion().getNidDireccion());
                    Utils.mandarParametro("nidDireccion", "#{pageFlowScope.nidDireccion}", "borrarDireccion");
                    ADFUtil.invokeEL("#{bindings.borrarDireccion.execute}");
                    beanSessionActualizarCliente.setBeanListDire(ln_C_SFDireccionRemote.getRelacion(beanSessionActualizarCliente.getNidParty()));   
                }else{
                    Utils.throwError_Aux(ctx,"Esta direccion esta siendo usada en una Guia, no puede borrarla.",4);
                }
            }
        }
    }

    public BeanError ActualizarDireccion() {
        try {
            if (beanSessionActualizarCliente.getAccion() == 1) {
                ADFUtil.setEL("#{pageFlowScope.nidParty}", beanSessionActualizarCliente.getNidParty());
                Utils.mandarParametro("nidParty", "#{pageFlowScope.nidParty}", "insertarDireccion");
                Utils.mandarParametro("cidUbigeo",  "#{backingBeanScope.backing_transporte_cliente_frm_actualizar_cliente.choiceDistrito.value}", "insertarDireccion");
                Utils.mandarParametro("cDireccion", "#{backingBeanScope.backing_transporte_cliente_frm_actualizar_cliente.txtDireccion.value}", "insertarDireccion");
                ADFUtil.invokeEL("#{bindings.insertarDireccion.execute}");
                beanSessionActualizarCliente.setBeanListDire(ln_C_SFDireccionRemote.getRelacion(beanSessionActualizarCliente.getNidParty()));
                resetearDatosDireccion();
                popupNuevaDireccion.hide();
                clearLista();
            }
            if(beanSessionActualizarCliente.getAccion() == 2){
                ADFUtil.setEL("#{pageFlowScope.nidParty}", beanSessionActualizarCliente.getNidParty());
                Utils.mandarParametro("nidParty", "#{pageFlowScope.nidParty}", "modificarDireccion");
                ADFUtil.setEL("#{pageFlowScope.nidDireccion}", beanSessionActualizarCliente.getBeanDireccion().getNidDireccion());
                Utils.mandarParametro("nidDireccion", "#{pageFlowScope.nidDireccion}", "modificarDireccion");
                Utils.mandarParametro("cidUbigeo",  "#{backingBeanScope.backing_transporte_cliente_frm_actualizar_cliente.choiceDistrito.value}", "modificarDireccion");
                Utils.mandarParametro("cDireccion", "#{backingBeanScope.backing_transporte_cliente_frm_actualizar_cliente.txtDireccion.value}", "modificarDireccion");
                ADFUtil.invokeEL("#{bindings.modificarDireccion.execute}");
                beanSessionActualizarCliente.setBeanListDire(ln_C_SFDireccionRemote.getRelacion(beanSessionActualizarCliente.getNidParty()));
                resetearDatosDireccion();
                popupNuevaDireccion.hide();
                clearLista();              
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beanError;

    }
    
    public BeanError ActualizarChofer() {

        try {
            if (beanSessionActualizarCliente.getAccion() == 1) {
                ADFUtil.setEL("#{pageFlowScope.nidParty}", beanSessionActualizarCliente.getNidParty());
                Utils.mandarParametro("nidParty", "#{pageFlowScope.nidParty}", "guardarChofer");
                Utils.mandarParametro("nombres",  "#{backingBeanScope.backing_transporte_cliente_frm_actualizar_cliente.txtNombreYApellido.value}", "guardarChofer");
                Utils.mandarParametro("licencia", "#{backingBeanScope.backing_transporte_cliente_frm_actualizar_cliente.txtLicencia.value}", "guardarChofer");
                ADFUtil.invokeEL("#{bindings.guardarChofer.execute}");
                beanSessionActualizarCliente.setBeanListChof(ln_C_SFChoferRemote.traerChoferesPorEmpresa(beanSessionActualizarCliente.getNidParty().intValue()));
                resetearDatosChofer();
                popupADDChof.hide();
                clearLista();
            }
            if(beanSessionActualizarCliente.getAccion()==2){
                ADFUtil.setEL("#{pageFlowScope.nidParty}", beanSessionActualizarCliente.getNidParty());
                Utils.mandarParametro("nidParty", "#{pageFlowScope.nidParty}", "actualizarChofer");
                ADFUtil.setEL("#{pageFlowScope.nidChofer}", beanSessionActualizarCliente.getBeanChofer().getNidChofer());
                Utils.mandarParametro("nidChofer", "#{pageFlowScope.nidChofer}", "actualizarChofer");
                Utils.mandarParametro("nombres",  "#{backingBeanScope.backing_transporte_cliente_frm_actualizar_cliente.txtNombreYApellido.value}", "actualizarChofer");
                Utils.mandarParametro("licencia", "#{backingBeanScope.backing_transporte_cliente_frm_actualizar_cliente.txtLicencia.value}", "actualizarChofer");
                ADFUtil.invokeEL("#{bindings.actualizarChofer.execute}");
                beanSessionActualizarCliente.setBeanListChof(ln_C_SFChoferRemote.traerChoferesPorEmpresa(beanSessionActualizarCliente.getNidParty().intValue()));
                resetearDatosChofer();
                popupADDChof.hide();
                clearLista();              
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beanError;

    }

    public BeanError ActualizarCliente() {
        try {
            Integer tire[] = new Integer[this.getBeanSessionActualizarCliente().getRelaValue().size()];
            for (int i = 0; i < this.getBeanSessionActualizarCliente().getRelaValue().size(); i++) {
                tire[i] = Integer.parseInt(this.getBeanSessionActualizarCliente().getRelaValue().get(i) + "");
            }
            ADFUtil.setEL("#{pageFlowScope.nidTire}", tire);
            Utils.mandarParametro("nidTire", "#{pageFlowScope.nidTire}", "modificarEmpresa");
            ADFUtil.setEL("#{pageFlowScope.nidParty}", beanSessionActualizarCliente.getNidParty());
            Utils.mandarParametro("nidParty", "#{pageFlowScope.nidParty}", "modificarEmpresa");
            Utils.mandarParametro("cCerins", "#{backingBeanScope.backing_transporte_cliente_frm_actualizar_cliente.inputCertificadoInscripcion.value}", "modificarEmpresa");
            Utils.mandarParametro("cRuc", "#{backingBeanScope.backing_transporte_cliente_frm_actualizar_cliente.it1.value}", "modificarEmpresa");
            Utils.mandarParametro("cRazonSocial",   "#{backingBeanScope.backing_transporte_cliente_frm_actualizar_cliente.it2.value}",  "modificarEmpresa");
            Utils.mandarParametro("cPagWeb",  "#{backingBeanScope.backing_transporte_cliente_frm_actualizar_cliente.it3.value}", "modificarEmpresa");
            Utils.mandarParametro("cDetalle",    "#{backingBeanScope.backing_transporte_cliente_frm_actualizar_cliente.it4.value}",  "modificarEmpresa");
            Utils.mandarParametro("cEmail",     "#{backingBeanScope.backing_transporte_cliente_frm_actualizar_cliente.it5.value}", "modificarEmpresa");
            Utils.mandarParametro("cTelf",    "#{backingBeanScope.backing_transporte_cliente_frm_actualizar_cliente.it6.value}",  "modificarEmpresa");
            beanError = (BeanError)ADFUtil.invokeEL("#{bindings.modificarEmpresa.execute}");
            if (beanError != null) {
                if(!beanError.getCidError().equals("000")){
                    Utils.throwError_Aux(ctx, beanError.getCDescripcionError(),1);
                    return null;
                }else{
                    Utils.throwError_Aux(ctx, beanError.getCDescripcionError(),3);
                }
            } else {
                Utils.throwError_Aux(ctx, "ERROR",1);
                return null;
            }
            p1.hide();
            clearLista();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beanError;
    }

    public void controlarItemProveedor(boolean estado) {
        itemProv.setDisabled(estado);
        AdfFacesContext.getCurrentInstance().addPartialTarget(itemProv);
    }

    public void seleccionar(SelectionEvent selectionEvent) {
        cb1.setDisabled(false);
        btnEditDirec.setDisabled(false);
        Utils.addTargetMany(cb1,btnEditDirec,btnBorrar);
        RichTable t = (RichTable)selectionEvent.getSource();
        Object _selectedRowData = t.getSelectedRowData();
        beanEmpresa = (BeanEmpresa)_selectedRowData;
        if(beanEmpresa.getNidParty().compareTo(new BigDecimal(5)) == 0){
            btnBorrar.setDisabled(true);
        }else{
            btnBorrar.setDisabled(false);
        }
        beanSessionActualizarCliente.setBeanEmpresa(beanEmpresa);                
        beanSessionActualizarCliente.setNidParty(beanEmpresa.getNidParty());
        beanSessionActualizarCliente.setNombreEmpresa(beanEmpresa.getCRazonSocial());   
        botonesProveedor();       
    }

    public String addCliente() {
        BeanDireccion beanDire2 = new BeanDireccion();
        beanDire2.setCidUbigeo(soc3.getValue().toString());
        beanDire2.setCDireccion(it7.getValue().toString());
        beanDire2.setDepartamento(bdL_C_SFUtilsRemote.getUbigeoByCid(soc1.getValue().toString()).getCDescUbigeo());
        beanDire2.setProvicia(bdL_C_SFUtilsRemote.getUbigeoByCid(soc2.getValue().toString()).getCDescUbigeo());
        beanDire2.setDistrito(bdL_C_SFUtilsRemote.getUbigeoByCid(soc3.getValue().toString()).getCDescUbigeo());
        this.getBeanSessionActualizarCliente().getBeanListDire().add(beanDire2);
        soc3.resetValue();
        it7.resetValue();
        soc2.resetValue();
        soc1.resetValue();
        return null;
    }

    public String getEmpresas() {
        BeanEmpresa beanemp = ln_C_SFEmpresasRemote.selectedEmpresa(beanSessionActualizarCliente.getNidParty());      
        beanSessionActualizarCliente.setCDetalle(beanemp.getAdParty().getCDetalle());
        beanSessionActualizarCliente.setCRazonSocial(beanemp.getCRazonSocial());
        beanSessionActualizarCliente.setCRuc(beanemp.getCRuc());
        beanSessionActualizarCliente.setCTelf(beanemp.getAdParty().getCTelf());
        beanSessionActualizarCliente.setCEmail(beanemp.getAdParty().getCEmail());
        beanSessionActualizarCliente.setCPagWeb(beanemp.getCPagWeb());
        beanSessionActualizarCliente.setCertificadoInscripcion(beanemp.getCCerins());
        beanSessionActualizarCliente.setBeanListDire(ln_C_SFDireccionRemote.getRelacion(beanSessionActualizarCliente.getNidParty()));
        beanSessionActualizarCliente.setBeanListRela(ln_C_SFRelacionEmpresaRemote.getRelacion(beanSessionActualizarCliente.getNidParty()));
        Iterator iter = beanSessionActualizarCliente.getBeanListRela().iterator();
        int aux[] = new int[3];
        int i = 0;
        while (iter.hasNext()) {
            BeanADRelacionEmpresa beanRela = (BeanADRelacionEmpresa)iter.next();
            aux[i] = beanRela.getNidTipoRelacion();
            i++;
        }
        List bool = new ArrayList();
        for (int j = 0; j < aux.length; j++) {
            bool.add(aux[j] + "");
        }
        beanSessionActualizarCliente.setRelaValue(bool);
        Utils.showPopUpMIDDLE(p1);
        if (getBeanSessionActualizarCliente().getRelaValue().contains("1")) {
            beanSessionActualizarCliente.setEstadoItem(false);
        }
        return null;
    }

    public String btnBorrar_action() {
        if(beanSessionActualizarCliente.getBeanEmpresa() != null){
            BigDecimal nidEmpresa = beanSessionActualizarCliente.getBeanEmpresa().getNidParty();
            beanError = ln_T_SFEmpresaRemote.borrarEmpresa(nidEmpresa);
            if (beanError != null) {
                if(!beanError.getCidError().equals("000")){
                    Utils.throwError_Aux(ctx, beanError.getCDescripcionError(),1);
                    return null;
                }else{
                    Utils.throwError_Aux(ctx, beanError.getCDescripcionError(),3);
                    clearLista();
                }
            } else {
                Utils.throwError_Aux(ctx, "ERROR",1);
                return null;
            }
        }else{
            Utils.throwError_Aux(ctx,"Seleccione una Empresa para borrar.",4);
        }
        return null;
    }
    
    public void dialogConfBorrarEmpresa(DialogEvent de) {
        if (de.getOutcome() == DialogEvent.Outcome.ok){
            btnBorrar_action();
        }
    }
    
    public void onDialogCancel(ClientEvent ce) {
        btnEditDire.setDisabled(true);
        btnDeleteDire.setDisabled(true);
        beanSessionActualizarCliente.setDireccionEmpresa(null);
        if(txtDireccion != null){
            txtDireccion.resetValue();
            choiceDepartamento.resetValue();
            choiceProvincia.resetValue();
            choiceDistrito.resetValue();
        }
        popupNuevaDireccion.hide();
        Utils.unselectFilas(tbDirec);
        Utils.addTargetMany(btnEditDire,btnDeleteDire,txtDireccion,choiceDepartamento,choiceProvincia,choiceDistrito);
    }

    public void onDialogCancel_NewFlota(ClientEvent ce) {
        btnEditFltota.setDisabled(true);
        btnDeleteFlota.setDisabled(true);
        beanSessionActualizarCliente.setMarcaVehiculo(null);
        beanSessionActualizarCliente.setPlacaVehiculo(null);
        beanSessionActualizarCliente.setConfVehicular(null);
        beanSessionActualizarCliente.setDescripcionVehiculo(null);
        if(txtMarca != null){
            txtMarca.resetValue();
            txtPlaca.resetValue();
            choiceConfig.resetValue();
            txtDescripcion.resetValue();
        }
        popupNuevaFlota.hide();
        Utils.unselectFilas(tbFlota);
        Utils.addTargetMany(btnEditFltota,btnDeleteFlota,txtMarca,txtPlaca,choiceConfig,txtDescripcion);
    }
    
    public void onDialogCancel_NewChofer(ClientEvent ce) {
        btnEditChof.setDisabled(true);
        btnDeleteChof.setDisabled(true);
        beanSessionActualizarCliente.setBeanChofer(new BeanChofer());
        beanSessionActualizarCliente.setNombreChofer(null);
        beanSessionActualizarCliente.setLicenciaChofer(null);
        if(txtNombreYApellido != null){
            txtNombreYApellido.resetValue();
            txtLicencia.resetValue();
        }
        popupADDChof.hide();
        Utils.unselectFilas(tbChof);
        Utils.addTargetMany(btnEditChof,btnDeleteChof,txtNombreYApellido,txtLicencia);
    }
    
    public void confirBorrar(ActionEvent ae) {
        Utils.showPopUpMIDDLE(popConf);
    }
    
    public void limpiarTbDirec(ActionEvent actionEvent) {
        beanSessionActualizarCliente.setBeanDireccion(new BeanDireccion());
        btnDeleteDire.setDisabled(true);
        Utils.unselectFilas(tbDirec);
    }
    
    public void limpiarTbFlota(ActionEvent actionEvent) {
        beanSessionActualizarCliente.setBeanFlota(new BeanFlota());
        btnDeleteFlota.setDisabled(true);
        Utils.unselectFilas(tbFlota);
    }
    
    public void limpiarTbChof(ActionEvent actionEvent) {
        beanSessionActualizarCliente.setBeanChofer(new BeanChofer());
        btnDeleteChof.setDisabled(true);
        Utils.unselectFilas(tbChof);
    }
    
    public void setBeanSessionActualizarCliente(SessionScopeBeanActualizarCliente beanSessionActualizarCliente) {
        this.beanSessionActualizarCliente = beanSessionActualizarCliente;
    }

    public SessionScopeBeanActualizarCliente getBeanSessionActualizarCliente() {
        return beanSessionActualizarCliente;
    }

    public void setDb1(RichDecorativeBox db1) {
        this.db1 = db1;
    }

    public RichDecorativeBox getDb1() {
        return db1;
    }


    public void setDepartamentos(List Departamentos) {
        this.Departamentos = Departamentos;
    }

    public List getDepartamentos() {
        return Departamentos;
    }

    public void setProvincias(List Provincias) {
        this.Provincias = Provincias;
    }

    public List getProvincias() {
        return Provincias;
    }

    public void setDistritos(List Distritos) {
        this.Distritos = Distritos;
    }

    public List getDistritos() {
        return Distritos;
    }

    public void setRelaList(List<SelectItem> relaList) {
        this.relaList = relaList;
    }

    public List<SelectItem> getRelaList() {
        return relaList;
    }

    public void setRelaValue(List<SelectItem> relaValue) {
        this.relaValue = relaValue;
    }

    public List<SelectItem> getRelaValue() {
        return relaValue;
    }

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }

    public void setOt1(RichOutputText ot1) {
        this.ot1 = ot1;
    }

    public RichOutputText getOt1() {
        return ot1;
    }

    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }


    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
    }

    public void setT1(RichTable t1) {
        this.t1 = t1;
    }

    public RichTable getT1() {
        return t1;
    }

    public void setCb1(RichCommandButton cb1) {
        this.cb1 = cb1;
    }

    public RichCommandButton getCb1() {
        return cb1;
    }

    public void setP1(RichPopup p1) {
        this.p1 = p1;
    }

    public RichPopup getP1() {
        return p1;
    }

    public void setD1(RichDialog d1) {
        this.d1 = d1;
    }

    public RichDialog getD1() {
        return d1;
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

    public void setIt3(RichInputText it3) {
        this.it3 = it3;
    }

    public RichInputText getIt3() {
        return it3;
    }

    public void setIt4(RichInputText it4) {
        this.it4 = it4;
    }

    public RichInputText getIt4() {
        return it4;
    }

    public void setIt5(RichInputText it5) {
        this.it5 = it5;
    }

    public RichInputText getIt5() {
        return it5;
    }

    public void setIt6(RichInputText it6) {
        this.it6 = it6;
    }

    public RichInputText getIt6() {
        return it6;
    }

    public void setCb2(RichCommandButton cb2) {
        this.cb2 = cb2;
    }

    public RichCommandButton getCb2() {
        return cb2;
    }

    public void setSmc1(RichSelectManyCheckbox smc1) {
        this.smc1 = smc1;
    }

    public RichSelectManyCheckbox getSmc1() {
        return smc1;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public UISelectItems getSi1() {
        return si1;
    }

    public void setP2(RichPopup p2) {
        this.p2 = p2;
    }

    public RichPopup getP2() {
        return p2;
    }

    public void setIt7(RichInputText it7) {
        this.it7 = it7;
    }

    public RichInputText getIt7() {
        return it7;
    }

    public void setSoc1(RichSelectOneChoice soc1) {
        this.soc1 = soc1;
    }

    public RichSelectOneChoice getSoc1() {
        return soc1;
    }

    public void setSi2(UISelectItems si2) {
        this.si2 = si2;
    }

    public UISelectItems getSi2() {
        return si2;
    }

    public void setSoc2(RichSelectOneChoice soc2) {
        this.soc2 = soc2;
    }

    public RichSelectOneChoice getSoc2() {
        return soc2;
    }

    public void setSi3(UISelectItems si3) {
        this.si3 = si3;
    }

    public UISelectItems getSi3() {
        return si3;
    }

    public void setSoc3(RichSelectOneChoice soc3) {
        this.soc3 = soc3;
    }

    public RichSelectOneChoice getSoc3() {
        return soc3;
    }

    public void setSi4(UISelectItems si4) {
        this.si4 = si4;
    }

    public UISelectItems getSi4() {
        return si4;
    }

    public void setCb3(RichCommandButton cb3) {
        this.cb3 = cb3;
    }

    public RichCommandButton getCb3() {
        return cb3;
    }

    public void setPfl2(RichPanelFormLayout pfl2) {
        this.pfl2 = pfl2;
    }

    public RichPanelFormLayout getPfl2() {
        return pfl2;
    }

    public void setT2(RichTable t2) {
        this.t2 = t2;
    }

    public RichTable getT2() {
        return t2;
    }

    public void setCb4(RichCommandButton cb4) {
        this.cb4 = cb4;
    }

    public RichCommandButton getCb4() {
        return cb4;
    }


    public void setItemCliente(RichSelectItem si6) {
        this.itemCliente = si6;
    }

    public RichSelectItem getItemCliente() {
        return itemCliente;
    }

    public void setSi7(RichSelectItem si7) {
        this.si7 = si7;
    }

    public RichSelectItem getSi7() {
        return si7;
    }


    public void setItemProv(RichSelectItem si5) {
        this.itemProv = si5;
    }

    public RichSelectItem getItemProv() {
        return itemProv;
    }

    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }

    public void setBtnEditDirec(RichCommandButton cb5) {
        this.btnEditDirec = cb5;
    }

    public RichCommandButton getBtnEditDirec() {
        return btnEditDirec;
    }

    public void setS2(RichSpacer s2) {
        this.s2 = s2;
    }

    public RichSpacer getS2() {
        return s2;
    }

    public void setBtnEditFlotas(RichCommandButton cb6) {
        this.btnEditFlotas = cb6;
    }

    public RichCommandButton getBtnEditFlotas() {
        return btnEditFlotas;
    }

    public void setS3(RichSpacer s3) {
        this.s3 = s3;
    }

    public RichSpacer getS3() {
        return s3;
    }

    public void setBtnEditChofe(RichCommandButton cb7) {
        this.btnEditChofe = cb7;
    }

    public RichCommandButton getBtnEditChofe() {
        return btnEditChofe;
    }

    public void setPopupEditDirec(RichPopup p3) {
        this.popupEditDirec = p3;
    }

    public RichPopup getPopupEditDirec() {
        return popupEditDirec;
    }

    public void setD2(RichDialog d2) {
        this.d2 = d2;
    }

    public RichDialog getD2() {
        return d2;
    }

    public void setTbDirec(RichTable t3) {
        this.tbDirec = t3;
    }

    public RichTable getTbDirec() {
        return tbDirec;
    }

    public void setBtnNewDire(RichCommandButton cb5) {
        this.btnNewDire = cb5;
    }

    public RichCommandButton getBtnNewDire() {
        return btnNewDire;
    }

    public void setBtnEditDire(RichCommandButton cb6) {
        this.btnEditDire = cb6;
    }

    public RichCommandButton getBtnEditDire() {
        return btnEditDire;
    }

    public void setBtnDeleteDire(RichCommandButton cb7) {
        this.btnDeleteDire = cb7;
    }

    public RichCommandButton getBtnDeleteDire() {
        return btnDeleteDire;
    }

    public void setPopupChofer(RichPopup p3) {
        this.popupChofer = p3;
    }

    public RichPopup getPopupChofer() {
        return popupChofer;
    }

    public void setD3(RichDialog d3) {
        this.d3 = d3;
    }

    public RichDialog getD3() {
        return d3;
    }

    public void setTbChof(RichTable t3) {
        this.tbChof = t3;
    }

    public RichTable getTbChof() {
        return tbChof;
    }

    public void setBtnNewChof(RichCommandButton cb5) {
        this.btnNewChof = cb5;
    }

    public RichCommandButton getBtnNewChof() {
        return btnNewChof;
    }

    public void setBtnEditChof(RichCommandButton cb6) {
        this.btnEditChof = cb6;
    }

    public RichCommandButton getBtnEditChof() {
        return btnEditChof;
    }

    public void setBtnDeleteChof(RichCommandButton cb7) {
        this.btnDeleteChof = cb7;
    }

    public RichCommandButton getBtnDeleteChof() {
        return btnDeleteChof;
    }

    public void setS4(RichSpacer s4) {
        this.s4 = s4;
    }

    public RichSpacer getS4() {
        return s4;
    }

    public void setS5(RichSpacer s5) {
        this.s5 = s5;
    }

    public RichSpacer getS5() {
        return s5;
    }

    public void setS6(RichSpacer s6) {
        this.s6 = s6;
    }

    public RichSpacer getS6() {
        return s6;
    }

    public void setS7(RichSpacer s7) {
        this.s7 = s7;
    }

    public RichSpacer getS7() {
        return s7;
    }

    public void setPopupEditFlota(RichPopup p3) {
        this.popupEditFlota = p3;
    }

    public RichPopup getPopupEditFlota() {
        return popupEditFlota;
    }

    public void setD4(RichDialog d4) {
        this.d4 = d4;
    }

    public RichDialog getD4() {
        return d4;
    }

    public void setTbFlota(RichTable t3) {
        this.tbFlota = t3;
    }

    public RichTable getTbFlota() {
        return tbFlota;
    }

    public void setBtnNewFlota(RichCommandButton cb5) {
        this.btnNewFlota = cb5;
    }

    public RichCommandButton getBtnNewFlota() {
        return btnNewFlota;
    }

    public void setBtnEditFltota(RichCommandButton cb6) {
        this.btnEditFltota = cb6;
    }

    public RichCommandButton getBtnEditFltota() {
        return btnEditFltota;
    }

    public void setBtnDeleteFlota(RichCommandButton cb7) {
        this.btnDeleteFlota = cb7;
    }

    public RichCommandButton getBtnDeleteFlota() {
        return btnDeleteFlota;
    }

    public void setS8(RichSpacer s8) {
        this.s8 = s8;
    }

    public RichSpacer getS8() {
        return s8;
    }

    public void setS9(RichSpacer s9) {
        this.s9 = s9;
    }

    public RichSpacer getS9() {
        return s9;
    }

    public void setPopupADDChof(RichPopup p3) {
        this.popupADDChof = p3;
    }

    public RichPopup getPopupADDChof() {
        return popupADDChof;
    }

    public void setS10(RichSubform s10) {
        this.s10 = s10;
    }

    public RichSubform getS10() {
        return s10;
    }

    public void setPanelADDChof(RichPanelFormLayout pfl3) {
        this.panelADDChof = pfl3;
    }

    public RichPanelFormLayout getPanelADDChof() {
        return panelADDChof;
    }

    public void setTxtNombreYApellido(RichInputText it8) {
        this.txtNombreYApellido = it8;
    }

    public RichInputText getTxtNombreYApellido() {
        return txtNombreYApellido;
    }

    public void setTxtLicencia(RichInputText it9) {
        this.txtLicencia = it9;
    }

    public RichInputText getTxtLicencia() {
        return txtLicencia;
    }

    public void setBtnADDChof(RichCommandButton cb5) {
        this.btnADDChof = cb5;
    }

    public RichCommandButton getBtnADDChof() {
        return btnADDChof;
    }

    public void setPopupNuevaFlota(RichPopup p3) {
        this.popupNuevaFlota = p3;
    }

    public RichPopup getPopupNuevaFlota() {
        return popupNuevaFlota;
    }

    public void setS11(RichSubform s11) {
        this.s11 = s11;
    }

    public RichSubform getS11() {
        return s11;
    }

    public void setFormNuevaFlota(RichPanelFormLayout pfl3) {
        this.formNuevaFlota = pfl3;
    }

    public RichPanelFormLayout getFormNuevaFlota() {
        return formNuevaFlota;
    }

    public void setTxtMarca(RichInputText it8) {
        this.txtMarca = it8;
    }

    public RichInputText getTxtMarca() {
        return txtMarca;
    }

    public void setTxtPlaca(RichInputText it9) {
        this.txtPlaca = it9;
    }

    public RichInputText getTxtPlaca() {
        return txtPlaca;
    }

    public void setTxtDescripcion(RichInputText it10) {
        this.txtDescripcion = it10;
    }

    public RichInputText getTxtDescripcion() {
        return txtDescripcion;
    }

    public void setChoiceConfig(RichSelectOneChoice soc4) {
        this.choiceConfig = soc4;
    }

    public RichSelectOneChoice getChoiceConfig() {
        return choiceConfig;
    }
    
    public void setSi5(RichSelectItem si5) {
        this.si5 = si5;
    }

    public RichSelectItem getSi5() {
        return si5;
    }

    public void setSi6(RichSelectItem si6) {
        this.si6 = si6;
    }

    public RichSelectItem getSi6() {
        return si6;
    }

    public void setSi8(RichSelectItem si8) {
        this.si8 = si8;
    }

    public RichSelectItem getSi8() {
        return si8;
    }

    public void setSi9(RichSelectItem si9) {
        this.si9 = si9;
    }

    public RichSelectItem getSi9() {
        return si9;
    }

    public void setSi10(RichSelectItem si10) {
        this.si10 = si10;
    }

    public RichSelectItem getSi10() {
        return si10;
    }

    public void setSi11(RichSelectItem si11) {
        this.si11 = si11;
    }

    public RichSelectItem getSi11() {
        return si11;
    }

    public void setSi12(RichSelectItem si12) {
        this.si12 = si12;
    }

    public RichSelectItem getSi12() {
        return si12;
    }

    public void setSi13(RichSelectItem si13) {
        this.si13 = si13;
    }

    public RichSelectItem getSi13() {
        return si13;
    }

    public void setBtnADDFlota(RichCommandButton cb5) {
        this.btnADDFlota = cb5;
    }

    public RichCommandButton getBtnADDFlota() {
        return btnADDFlota;
    }

    public void setPopupNuevaDireccion(RichPopup p3) {
        this.popupNuevaDireccion = p3;
    }

    public RichPopup getPopupNuevaDireccion() {
        return popupNuevaDireccion;
    }

    public void setS12(RichSubform s12) {
        this.s12 = s12;
    }

    public RichSubform getS12() {
        return s12;
    }

    public void setFormNuevaDireccion(RichPanelFormLayout pfl3) {
        this.formNuevaDireccion = pfl3;
    }

    public RichPanelFormLayout getFormNuevaDireccion() {
        return formNuevaDireccion;
    }

    public void setTxtDireccion(RichInputText it8) {
        this.txtDireccion = it8;
    }

    public RichInputText getTxtDireccion() {
        return txtDireccion;
    }

    public void setChoiceDepartamento(RichSelectOneChoice soc4) {
        this.choiceDepartamento = soc4;
    }

    public RichSelectOneChoice getChoiceDepartamento() {
        return choiceDepartamento;
    }

    public void setSi14(UISelectItems si14) {
        this.si14 = si14;
    }

    public UISelectItems getSi14() {
        return si14;
    }

    public void setChoiceProvincia(RichSelectOneChoice soc5) {
        this.choiceProvincia = soc5;
    }

    public RichSelectOneChoice getChoiceProvincia() {
        return choiceProvincia;
    }

    public void setSi15(UISelectItems si15) {
        this.si15 = si15;
    }

    public UISelectItems getSi15() {
        return si15;
    }

    public void setChoiceDistrito(RichSelectOneChoice soc6) {
        this.choiceDistrito = soc6;
    }

    public RichSelectOneChoice getChoiceDistrito() {
        return choiceDistrito;
    }

    public void setSi16(UISelectItems si16) {
        this.si16 = si16;
    }

    public UISelectItems getSi16() {
        return si16;
    }

    public void setBtnRegistrarDireccion(RichCommandButton cb5) {
        this.btnRegistrarDireccion = cb5;
    }

    public RichCommandButton getBtnRegistrarDireccion() {
        return btnRegistrarDireccion;
    }

    public void setInputCertificadoInscripcion(RichInputText it8) {
        this.inputCertificadoInscripcion = it8;
    }

    public RichInputText getInputCertificadoInscripcion() {
        return inputCertificadoInscripcion;
    }

    public void setS13(RichSubform s13) {
        this.s13 = s13;
    }

    public RichSubform getS13() {
        return s13;
    }

    public void setIt8(RichInputText it8) {
        this.it8 = it8;
    }

    public RichInputText getIt8() {
        return it8;
    }

    public void setIt9(RichInputText it9) {
        this.it9 = it9;
    }

    public RichInputText getIt9() {
        return it9;
    }

    public void setSoc4(RichSelectOneChoice soc4) {
        this.soc4 = soc4;
    }

    public RichSelectOneChoice getSoc4() {
        return soc4;
    }

    public void setSi17(UISelectItems si17) {
        this.si17 = si17;
    }

    public UISelectItems getSi17() {
        return si17;
    }

    public void setBtnBuscar(RichCommandButton cb5) {
        this.btnBuscar = cb5;
    }

    public RichCommandButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnLimpiar(RichCommandButton cb6) {
        this.btnLimpiar = cb6;
    }

    public RichCommandButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public void setListRelacion(List listRelacion) {
        this.listRelacion = listRelacion;
    }

    public List getListRelacion() {
        return listRelacion;
    }

    public void setBtnBorrar(RichCommandButton cb5) {
        this.btnBorrar = cb5;
    }

    public RichCommandButton getBtnBorrar() {
        return btnBorrar;
    }

    public void setPopConf(RichPopup p3) {
        this.popConf = p3;
    }

    public RichPopup getPopConf() {
        return popConf;
    }

    public void setDiaConf(RichDialog d5) {
        this.diaConf = d5;
    }

    public RichDialog getDiaConf() {
        return diaConf;
    }

    public void setOtMsjBorrar(RichOutputText ot25) {
        this.otMsjBorrar = ot25;
    }

    public RichOutputText getOtMsjBorrar() {
        return otMsjBorrar;
    }

    public void setInputCerInsFlota(RichInputText inputCerInsFlota) {
        this.inputCerInsFlota = inputCerInsFlota;
    }

    public RichInputText getInputCerInsFlota() {
        return inputCerInsFlota;
    }
}
