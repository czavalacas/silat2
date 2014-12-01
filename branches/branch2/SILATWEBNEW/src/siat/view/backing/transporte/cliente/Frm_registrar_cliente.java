package siat.view.backing.transporte.cliente;

//import java.awt.event.ActionEvent;

//import java.awt.event.ActionEvent;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import javax.naming.Context;
import javax.naming.InitialContext;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.RichSubform;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectItem;
import oracle.adf.view.rich.component.rich.input.RichSelectManyCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichDecorativeBox;
import oracle.adf.view.rich.component.rich.layout.RichGridCell;
import oracle.adf.view.rich.component.rich.layout.RichGridRow;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGridLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichSeparator;
import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import siat.view.backing.utiles.ADFUtil;

import siat.view.backing.utiles.Utils;

import silat.servicios_negocio.BDLSF.IR.BDL_C_SFUtilsRemote;
import silat.servicios_negocio.Beans.BeanChofer;
import silat.servicios_negocio.Beans.BeanADUbigeo;
import silat.servicios_negocio.Beans.BeanDireccion;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanFlota;
import silat.servicios_negocio.Beans.BeanTRItem;
import silat.servicios_negocio.LNSF.IR.LN_C_SFEmpresasRemote;
import silat.servicios_negocio.entidades.admin.ADUbigeo;

public class Frm_registrar_cliente {
    
    private RichPanelFormLayout panelRegistroFlota;
    private RichPanelGridLayout pglBotonesFlota;
    private RichGridRow rowBotonesFlota;
    private RichGridCell celbtn1Flota;
    private RichGridCell celbtn2Flota;
    private RichGridCell celbtn3Flota;
    private RichCommandButton btnNuevaFlota;
    private RichCommandButton btnEditarFlota;
    private RichCommandButton btnBorrarFlota;
    private RichPopup popuNuevaFlota;
    private RichSubform sfNwFlo;
    private RichPanelFormLayout panelPopupRegFlota;
    private RichCommandButton btnRegistrarFlota;
    private RichInputText txtMarcaVehiculo;
    private RichInputText txtPlacaVehiculo;
    private RichInputText txtSotaVehiculo;
    private RichInputText txtConfigVehiculo;
    private RichInputText txtDescVehiculo;
    private String marcaVehiculo;
    private String placaVehiculo;
    private String soatVehiculo;
    private String configVehiculo;
    private String descripVehiculo;
    private RichTable tabFlot;
    private RichOutputText outNumDire;
    private RichSelectOneChoice choiceConfVehicular;
    private RichSelectItem si17;
    private RichSelectItem si8;
    private RichSelectItem si9;
    private RichSelectItem si10;
    private RichSelectItem si11;
    private RichSelectItem si12;
    private RichSelectItem si13;
    private RichSelectItem si14;
    private RichSelectItem si15;
    private RichSelectItem si16;
    private RichDecorativeBox db1;
    private RichPanelGroupLayout pgl1;
    private RichPanelGroupLayout pgl2;
    private RichOutputText ot1;
    private RichPanelFormLayout pfl1;
    private RichInputText it1;
    private RichInputText it2;
    private RichInputText it3;
    private RichInputText it4;
    private RichInputText it5;
    private RichInputText it6;
    private RichCommandButton cb1;
    private RichSelectManyCheckbox smc1;
    private UISelectItems si1;
    private RichCommandButton cb2;
    private RichPopup p1;
    private RichInputText it7;
    private RichPanelFormLayout pfl2;
    private RichSelectOneChoice soc1;
    private UISelectItems si2;
    private RichSelectOneChoice soc2;
    private UISelectItems si3;
    private RichSelectOneChoice soc3;
    private UISelectItems si4;
    private RichCommandButton cb3;
    private RichTable tbDirecs;
    private RichSubform s1;
    ////////////////////////////////////////////
    private RichPanelGridLayout pglBotonesChofer;
    private RichGridRow rowBotonesChofer;
    private RichGridCell celbtn1Chofer;
    private RichGridCell celbtn2Chofer;
    private RichGridCell celbtn3Chofer;
    private RichCommandButton btnNuevoChofer;
    private RichCommandButton btnEditarChofer;
    private RichCommandButton btnBorrarChofer;
    private RichPanelFormLayout panelRegistroChofer;
    private RichPopup popupNuevoChofer;
    private RichSubform sfNwCho;
    private RichPanelFormLayout panelPopupRegChofer;
    private RichCommandButton btnRegistrarChofer;
    private RichInputText txtNombreYApellido;
    private RichInputText txtLicenciaChofer;
    //////////////////////////////////direccion
    private RichPanelGridLayout pglBotonesDireccion;
    private RichGridRow rowBotonesDirec;
    private RichGridCell celdabtn1Dire;
    private RichGridCell celdabtn2Dire;
    private RichGridCell celdabtn3Dire;
    private RichCommandButton btnEditarDireccion;
    private RichCommandButton btnBorrarDireccion;
    private RichInputText labelCertficado;
    private RichSeparator s2;
    private RichSelectItem si5;
    private RichSelectItem si6;
    private RichSelectItem si7;
    private RichTable tabChof;
    //mis variables
    private BDL_C_SFUtilsRemote bdL_C_SFUtilsRemote;
    private final static String LOOKUP_NAME_SFUtils_REMOTO = "mapBDL_C_SFUtils";//#silat.servicios_negocio.BDLSF.IR.BDL_C_SFUtilsRemote
    private List Departamentos;
    private List Provincias;
    private List Distritos;
    private BeanError beanError = new BeanError();
    private List<SelectItem> relaList;
    private SessionScopedBeanRegistrarCliente beanSessionRegistrarCliente;
    private String nombreChofer;
    private String licenciaChofer;
    private String certificadoInscripcionFlota;
    private BeanChofer beanChofer = new BeanChofer();
    private BeanDireccion beanDire2 = new BeanDireccion();
    private BeanFlota beanFlota = new BeanFlota();
    FacesContext ctx = FacesContext.getCurrentInstance();
    private LN_C_SFEmpresasRemote ln_C_SFEmpresasRemote;
    private final static String LOOKUP_NAME_SFEmpresa_REMOTO = "mapLN_C_SFEmpresas";
    private RichInputText inputCerInsFlota; //#silat.servicios_negocio.LNSF.IR.LN_C_SFEmpresasRemote
    
    

    public Frm_registrar_cliente() {
        try {
            final Context ctx;
            ctx = new InitialContext();
            bdL_C_SFUtilsRemote = (BDL_C_SFUtilsRemote)ctx.lookup(LOOKUP_NAME_SFUtils_REMOTO);
            ln_C_SFEmpresasRemote = (LN_C_SFEmpresasRemote)ctx.lookup(LOOKUP_NAME_SFEmpresa_REMOTO);
            this.setDepartamentos(this.FillList(1, null, null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetearDatoChofer() {
        txtLicenciaChofer.resetValue();
        txtNombreYApellido.resetValue();
    }

    public void resetearDatoFlota() {
        if(txtPlacaVehiculo != null){
            txtPlacaVehiculo.resetValue();
            txtMarcaVehiculo.resetValue();
            txtDescVehiculo.resetValue();
            choiceConfVehicular.resetValue();
            inputCerInsFlota.resetValue();
        }
    }

    public void resetearDatoDireccion() {
        if(it7 != null){
            it7.resetValue();
            soc1.resetValue();
            soc2.resetValue();
            soc3.resetValue();
        }
    }

    public void seleccionarChofer(SelectionEvent selectionEvent) {
        controlarBotones(false);
        RichTable t = (RichTable)selectionEvent.getSource();
        Object _selectedRowData = t.getSelectedRowData();
        beanChofer = (BeanChofer)_selectedRowData;
        getBeanSessionRegistrarCliente().setBeanChofer(beanChofer);
    }

    public void seleccionarFlota(SelectionEvent selectionEvent) {
        controlarBotonesFlota(false);
        RichTable t = (RichTable)selectionEvent.getSource();
        Object _selectedRowData = t.getSelectedRowData();
        beanFlota = (BeanFlota)_selectedRowData;
        getBeanSessionRegistrarCliente().setBeanFlota(beanFlota);
    }

    public void seleccionarDireccion(SelectionEvent selectionEvent) {
        controlarBotonesDireccion(false);
        RichTable t = (RichTable)selectionEvent.getSource();
        Object _selectedRowData = t.getSelectedRowData();
        beanDire2 = (BeanDireccion)_selectedRowData;
        getBeanSessionRegistrarCliente().setBeanDirec(beanDire2);
        beanDire2 = new BeanDireccion();
        beanDire2 = getBeanSessionRegistrarCliente().getBeanDirec();
        it7.setValue(beanDire2.getCDireccion());
        soc1.setValue(beanDire2.getDepartamento());
        ValueChangeEvent vce = new ValueChangeEvent(soc1, "0", beanDire2.getDepartamento());
        vce.queue();
        getBeanSessionRegistrarCliente().setIsDepartamentoSeteado(true);
        ValueChangeEvent vce2 = new ValueChangeEvent(soc2, "0", beanDire2.getProvicia());
        vce2.queue();
        getBeanSessionRegistrarCliente().setIsProvinciaSeteado(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(it7);
        AdfFacesContext.getCurrentInstance().addPartialTarget(soc1);
        getBeanSessionRegistrarCliente().setAccion(2);
    }

    public void borrarDireccion(ActionEvent actionEvent) {
        beanDire2 = new BeanDireccion();
        beanDire2 = getBeanSessionRegistrarCliente().getBeanDirec();
        getBeanSessionRegistrarCliente().getListDirec().remove(beanDire2.getNidDireccion().intValue());
        getBeanSessionRegistrarCliente().setAccion(1);
        resetearDatoDireccion();
        controlarBotonesDireccion(true);
        getBeanSessionRegistrarCliente().setNumDirec(beanSessionRegistrarCliente.getListDirec().size());
        Utils.unselectFilas(tbDirecs);
    }

    public void borrarChofer(ActionEvent actionEvent) {
        beanChofer = new BeanChofer();
        beanChofer = getBeanSessionRegistrarCliente().getBeanChofer();
        getBeanSessionRegistrarCliente().getListChofer().remove(beanChofer.getNidChofer().intValue());
        controlarBotones(true);
        Utils.unselectFilas(tabChof);
    }

    public void borrarFlota(ActionEvent actionEvent) {
        beanFlota = new BeanFlota();
        beanFlota = getBeanSessionRegistrarCliente().getBeanFlota();
        getBeanSessionRegistrarCliente().getListFlota().remove(beanFlota.getNidFlota().intValue());
        controlarBotonesFlota(true);
        Utils.unselectFilas(tabFlot);
    }
   
    public void controlarTablas(boolean estado) {
        panelRegistroFlota.setVisible(estado);
        panelRegistroChofer.setVisible(estado);
        beanSessionRegistrarCliente.setPanelRegistroChofer(estado);
        beanSessionRegistrarCliente.setPanelRegistroFlota(estado);
        AdfFacesContext.getCurrentInstance().addPartialTarget(panelRegistroChofer);
        AdfFacesContext.getCurrentInstance().addPartialTarget(panelRegistroFlota);
    }

    public void controlarBotones(boolean estado) {
        btnEditarChofer.setDisabled(estado);
        btnBorrarChofer.setDisabled(estado);
        AdfFacesContext.getCurrentInstance().addPartialTarget(btnEditarChofer);
        AdfFacesContext.getCurrentInstance().addPartialTarget(btnBorrarChofer);
    }

    public void controlarBotonesFlota(boolean estado) {
        btnEditarFlota.setDisabled(estado);
        btnBorrarFlota.setDisabled(estado);
        AdfFacesContext.getCurrentInstance().addPartialTarget(btnEditarFlota);
        AdfFacesContext.getCurrentInstance().addPartialTarget(btnBorrarFlota);
    }

    public void controlarBotonesDireccion(boolean estado) {
        btnBorrarDireccion.setDisabled(estado);
        AdfFacesContext.getCurrentInstance().addPartialTarget(btnBorrarDireccion);
    }

    public void openPopUpNuevoChofer(ActionEvent actionEvent) {
        getBeanSessionRegistrarCliente().setAccion(1); //Grabar
        Utils.showPopUpMIDDLE(popupNuevoChofer);
        if (txtNombreYApellido != null) {
            resetearDatoChofer();
        }
    }

    public void openPopUpNuevaFlota(ActionEvent actionEvent) {
        getBeanSessionRegistrarCliente().setAccion(1); //Grabar
        Utils.showPopUpMIDDLE(popuNuevaFlota);
        if (txtMarcaVehiculo != null) {
            resetearDatoFlota();
        }
    }

    public void registrarFlota(ActionEvent actionEvent) {
        int repetido=0;
        int accion = getBeanSessionRegistrarCliente().getAccion();
        resetearDatoFlota();
            int lstSize =
                getBeanSessionRegistrarCliente().getListFlota() == null ? 0 : getBeanSessionRegistrarCliente().getListFlota().size();
            int index = 0;
            if (accion == 1) {
                index = (lstSize == 0) ? 0 : (lstSize == 1) ? lstSize : lstSize - 1;
            }
            if (accion == 2) {
                index = getBeanSessionRegistrarCliente().getBeanFlota().getNidFlota().intValue();
            }
        if (!getBeanSessionRegistrarCliente().contieneFlota(beanFlota)) {
            if(beanSessionRegistrarCliente.getBeanFlota()!=null){
            for(BeanFlota flota : beanSessionRegistrarCliente.getListFlota()){
                if(flota.getCPlaca().equals(placaVehiculo.toUpperCase())){
                    repetido=1;
                        popuNuevaFlota.hide();
                        Utils.throwError_Aux(ctx, "El numero de placa ya Se Encuentra Registrado. ",1);
                    }
                }            
            }
        if(repetido==0){
        if(!getBeanSessionRegistrarCliente().getListFlota().isEmpty() && accion == 2){
            getBeanSessionRegistrarCliente().getListFlota().remove(index);
        }
            beanFlota.setNidFlota(index);
            beanFlota.setCMarvehi(marcaVehiculo.toUpperCase());
            beanFlota.setCPlaca(placaVehiculo.toUpperCase());
            beanFlota.setCConfveh(configVehiculo.toString());            
            if (descripVehiculo != null) {
                beanFlota.setCDescFlota(descripVehiculo.toUpperCase());
            }
            if (descripVehiculo == null) {
                beanFlota.setCDescFlota("");
            }
            beanFlota.setCCerins(certificadoInscripcionFlota.toUpperCase());
            getBeanSessionRegistrarCliente().setBeanFlota(beanFlota);
            getBeanSessionRegistrarCliente().getListFlota().add(index, beanFlota);
            Utils.unselectFilas(tabFlot);
            popuNuevaFlota.hide();}
        } else {
            Utils.throwError_Aux(ctx, "Ya existe el Chofer ingresado.",1);
        
        
        }
        }
    public void registrarChofer(ActionEvent actionEvent) {
        int repetido=0;
        int accion = getBeanSessionRegistrarCliente().getAccion();
        resetearDatoChofer();     
            int lstSize =
                getBeanSessionRegistrarCliente().getListChofer() == null ? 0 : getBeanSessionRegistrarCliente().getListChofer().size();
            int index = 0;
            if (accion == 1) {
                index = (lstSize == 0) ? 0 : (lstSize == 1) ? lstSize : lstSize - 1;
            }
            if (accion == 2) {
                index = getBeanSessionRegistrarCliente().getBeanChofer().getNidChofer().intValue();
            }
        if (!getBeanSessionRegistrarCliente().contiene(beanChofer)) {
            if(beanSessionRegistrarCliente.getListChofer()!=null){
            for(BeanChofer chofer : beanSessionRegistrarCliente.getListChofer()){
                if(chofer.getLicencia().equals(licenciaChofer.toUpperCase())){
                    repetido=1;
                        popupNuevoChofer.hide();
                        Utils.throwError_Aux(ctx, "El numero de licencia ya Se Encuentra Registrado. ",1);
                    }
            }            
            }
            if(repetido==0){
                if(!getBeanSessionRegistrarCliente().getListChofer().isEmpty() && accion ==2){
                getBeanSessionRegistrarCliente().getListChofer().remove(index);
            }
            beanChofer.setNombres(nombreChofer.toUpperCase());
            beanChofer.setLicencia(licenciaChofer.toUpperCase());
            beanChofer.setNidChofer(index);
            getBeanSessionRegistrarCliente().setBeanChofer(beanChofer);
            getBeanSessionRegistrarCliente().getListChofer().add(index, beanChofer);
            Utils.unselectFilas(tabChof);
            popupNuevoChofer.hide();}
        } else {
            Utils.throwError_Aux(ctx, "Ya existe el Chofer ingresado.",1);
        }

        }
    ///grabardireccion

    public void addCliente(ActionEvent actionEvent) {
        int accion = getBeanSessionRegistrarCliente().getAccion();
        if (!getBeanSessionRegistrarCliente().contieneDireccion(beanDire2)) {
            int lstSize =
                getBeanSessionRegistrarCliente().getListDirec() == null ? 0 : getBeanSessionRegistrarCliente().getListDirec().size();
            int index = 0;
            if (accion == 1) {
                index = (lstSize == 0) ? 0 : (lstSize == 1) ? lstSize : lstSize - 1;
            }
            if (accion == 2) {
                index = getBeanSessionRegistrarCliente().getBeanDirec().getNidDireccion().intValue();
                getBeanSessionRegistrarCliente().getListDirec().remove(index);
            }
            beanDire2.setNidDireccion(index);
            beanDire2.setCidUbigeo(soc3.getValue().toString());
            beanDire2.setCDireccion(it7.getValue().toString());
            beanDire2.setDepartamento(soc1.getValue().toString());
            beanDire2.setDescDepartamento(bdL_C_SFUtilsRemote.getUbigeoByCid(soc1.getValue().toString()).getCDescUbigeo());
            beanDire2.setDescProvincia(bdL_C_SFUtilsRemote.getUbigeoByCid(soc2.getValue().toString()).getCDescUbigeo());
            beanDire2.setProvicia(soc2.getValue().toString());
            beanDire2.setDescDistrito(bdL_C_SFUtilsRemote.getUbigeoByCid(soc3.getValue().toString()).getCDescUbigeo());
            beanDire2.setDistrito(soc3.getValue().toString());
            getBeanSessionRegistrarCliente().setBeanDirec(beanDire2);
            getBeanSessionRegistrarCliente().getListDirec().add(index, beanDire2);
            getBeanSessionRegistrarCliente().setNumDirec(beanSessionRegistrarCliente.getListDirec().size());
            getBeanSessionRegistrarCliente().setAccion(1);
            soc3.resetValue();
            it7.resetValue();
            soc2.resetValue();
            soc1.resetValue();
            Utils.unselectFilas(tbDirecs);
        } else {
            Utils.throwError_Aux(ctx, "Ya existe el Chofer ingresado.", 4);
        }
    }

    public void editarChofer(ActionEvent actionEvent) {
        resetearDatoChofer();
        beanChofer = new BeanChofer();
        beanChofer = getBeanSessionRegistrarCliente().getBeanChofer();
        txtNombreYApellido.setValue(beanChofer.getNombres());
        txtLicenciaChofer.setValue(beanChofer.getLicencia());
        getBeanSessionRegistrarCliente().setAccion(2); //Modificar
    }

    public void editarFlota(ActionEvent actionEvent) {
        resetearDatoFlota();
        beanFlota = new BeanFlota();
        beanFlota = getBeanSessionRegistrarCliente().getBeanFlota();
        if(txtPlacaVehiculo != null){
            txtPlacaVehiculo.setValue(beanFlota.getCPlaca());
            txtMarcaVehiculo.setValue(beanFlota.getCMarvehi());
            txtDescVehiculo.setValue(beanFlota.getCDescFlota());
            choiceConfVehicular.setValue(beanFlota.getCConfveh());
            inputCerInsFlota.setValue(beanFlota.getCCerins());
            getBeanSessionRegistrarCliente().setAccion(2); //Modificar   
        }
    }

    public String removeCliente() {
        if (this.getBeanSessionRegistrarCliente().getListDirec().isEmpty()) {
            return null;
        }
        return null;
    }

    public void reset() {
        it1.resetValue();
        it2.resetValue();
        it3.resetValue();
        it4.resetValue();
        it6.resetValue();
    }

    public void insertarCliente(ActionEvent actionEvent) {
        insertarCliente();       
    }
    
    public void reset2(){
        Utils.removeSession("beanSessionRegistrarCliente");        
        reset();        
        Utils._redireccionar(ctx, "/WEB-INF/consultar_cliente.xml#consultar_cliente");
        controlarTablas(false);
    }


    public BeanError insertarCliente() {
        try {
            ADFUtil.setEL("#{pageFlowScope.cCerins}", beanSessionRegistrarCliente.getCertificadoInscripcion());
            Utils.mandarParametro("cCerins", "#{pageFlowScope.cCerins}", "crearEmpresa");                                 
            Integer tire[] = new Integer[this.getBeanSessionRegistrarCliente().getRelaValue().size()];
            for (int i = 0; i < this.getBeanSessionRegistrarCliente().getRelaValue().size(); i++) {
                tire[i] = Integer.parseInt(this.getBeanSessionRegistrarCliente().getRelaValue().get(i) + "");
            } //recorre la lita de tipodeRelacion i las setea a tire            
            ADFUtil.setEL("#{pageFlowScope.nidTire}", tire);
            Utils.mandarParametro("nidTire", "#{pageFlowScope.nidTire}", "crearEmpresa");
            ///SETEAR DIRECCION
            String cidUbigeo[] = new String[this.getBeanSessionRegistrarCliente().getListDirec().size()];
            String direccion[] = new String[this.getBeanSessionRegistrarCliente().getListDirec().size()];
            Iterator iter = this.getBeanSessionRegistrarCliente().getListDirec().iterator();
            int j = 0;
            while (iter.hasNext()) {
                BeanDireccion beanDire = (BeanDireccion)iter.next();
                cidUbigeo[j] = beanDire.getCidUbigeo();
                direccion[j] = beanDire.getCDireccion();
                j++;
            }
            ADFUtil.setEL("#{pageFlowScope.cDireccion}", direccion);
            Utils.mandarParametro("cDireccion", "#{pageFlowScope.cDireccion}", "crearEmpresa");
            ADFUtil.setEL("#{pageFlowScope.cidUbigeo}", cidUbigeo);
            Utils.mandarParametro("cidUbigeo", "#{pageFlowScope.cidUbigeo}", "crearEmpresa");
            ////SETEAR CHOFER
            String nombreChofer[] = new String[this.getBeanSessionRegistrarCliente().getListChofer().size()];
            String licenciaChofer[] = new String[this.getBeanSessionRegistrarCliente().getListChofer().size()];
            Iterator iter2 = this.getBeanSessionRegistrarCliente().getListChofer().iterator();
            int k = 0;
            while (iter2.hasNext()) {
                BeanChofer beanChofe = (BeanChofer)iter2.next();
                nombreChofer[k] = beanChofe.getNombres();
                licenciaChofer[k] = beanChofe.getLicencia();
                k++;
            }
            ADFUtil.setEL("#{pageFlowScope.cNombChofer}", nombreChofer);
            Utils.mandarParametro("cNombChofer", "#{pageFlowScope.cNombChofer}", "crearEmpresa");
            ADFUtil.setEL("#{pageFlowScope.cLicenciaChofer}", licenciaChofer);
            Utils.mandarParametro("cLicenciaChofer", "#{pageFlowScope.cLicenciaChofer}", "crearEmpresa");
            ////SETEAR FLOTA
            String marcaFlota[] = new String[this.getBeanSessionRegistrarCliente().getListFlota().size()];
            String placaFlota[] = new String[this.getBeanSessionRegistrarCliente().getListFlota().size()];
            String configFlota[] = new String[this.getBeanSessionRegistrarCliente().getListFlota().size()];
            String descripFlota[] = new String[this.getBeanSessionRegistrarCliente().getListFlota().size()];
            String certiInscripFlota[] = new String[this.getBeanSessionRegistrarCliente().getListFlota().size()];
            Iterator iter3 = this.getBeanSessionRegistrarCliente().getListFlota().iterator();
            int m = 0;
            while (iter3.hasNext()) {
                BeanFlota beanFlote = (BeanFlota)iter3.next();
                marcaFlota[m] = beanFlote.getCMarvehi();
                placaFlota[m] = beanFlote.getCPlaca();
                configFlota[m] = beanFlote.getCConfveh();
                descripFlota[m] = beanFlote.getCDescFlota();    
                certiInscripFlota[m] = beanFlote.getCCerins();
                m++;
            }
            ADFUtil.setEL("#{pageFlowScope.cMarcaFlota}", marcaFlota);
            Utils.mandarParametro("cMarcaFlota", "#{pageFlowScope.cMarcaFlota}", "crearEmpresa");
            ADFUtil.setEL("#{pageFlowScope.cPlacaFlota}", placaFlota);
            Utils.mandarParametro("cPlacaFlota", "#{pageFlowScope.cPlacaFlota}", "crearEmpresa");
            ADFUtil.setEL("#{pageFlowScope.cConfFlota}", configFlota);
            Utils.mandarParametro("cConfFlota", "#{pageFlowScope.cConfFlota}", "crearEmpresa");
            ADFUtil.setEL("#{pageFlowScope.cDescripcionFlota}", descripFlota);
            Utils.mandarParametro("cDescripcionFlota", "#{pageFlowScope.cDescripcionFlota}", "crearEmpresa");
            ADFUtil.setEL("#{pageFlowScope.cCerInsCripFlota}", certiInscripFlota);
            Utils.mandarParametro("cCerInsCripFlota", "#{pageFlowScope.cCerInsCripFlota}", "crearEmpresa");
            if(beanSessionRegistrarCliente.getListDirec()!=null){
                if(beanSessionRegistrarCliente.getListDirec().size() > 0){
                    beanError = (BeanError)ADFUtil.invokeEL("#{bindings.crearEmpresa.execute}"); 
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
                    List<BeanDireccion> beanDire = new ArrayList<BeanDireccion>();
                    this.getBeanSessionRegistrarCliente().setListDirec(beanDire); 
                    reset2();
                }else if(beanSessionRegistrarCliente.getListDirec().size()==0){
                    Utils.throwError_Aux(ctx, "Registre almenos 1 dirección", 4);
                    return null;
                }
            }else{
                Utils.throwError_Aux(ctx, "Registre almenos 1 dirección", 4);
                return null;
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return beanError;
    }

    public void mostrarProvincias(ValueChangeEvent vcl) {
        vcl.getComponent().processUpdates(FacesContext.getCurrentInstance());
        String newValue = vcl.getNewValue().toString();
        this.setProvincias(this.FillList(2, newValue, null));
        AdfFacesContext.getCurrentInstance().addPartialTarget(soc2);
        if (getBeanSessionRegistrarCliente().isIsDepartamentoSeteado() == true) {
            soc2.setValue(beanDire2.getProvicia());
        }
        if(this.getDistritos() != null){
            this.getDistritos().removeAll(this.getDistritos());
        }
        Utils.addTargetMany(soc2,soc3);
    }

    public void mostrarDistritos(ValueChangeEvent vcl) {
        vcl.getComponent().processUpdates(FacesContext.getCurrentInstance());
        String newValue = vcl.getNewValue().toString();
        this.setDistritos(this.FillList(3, null, newValue));
        Utils.addTarget(soc3);
        if (getBeanSessionRegistrarCliente().isIsProvinciaSeteado() == true) {
            soc3.setValue(beanDire2.getDistrito());
            Utils.addTarget(soc3);
        }
    }

    public ArrayList FillList(int tipUbigeo, String dep, String prov) {
        ArrayList List = new ArrayList();
        List<BeanADUbigeo> entitylist = bdL_C_SFUtilsRemote.getUbigeos(tipUbigeo, dep, prov);
        for (BeanADUbigeo ubigeo : entitylist) {
            List.add(new SelectItem(ubigeo.getCidUbigeo().toString(), ubigeo.getCDescUbigeo().toString()));
        }
        return List;
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


    public void setDb1(RichDecorativeBox db1) {
        this.db1 = db1;
    }

    public RichDecorativeBox getDb1() {
        return db1;
    }

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }


    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }

    public void setOt1(RichOutputText ot1) {
        this.ot1 = ot1;
    }

    public RichOutputText getOt1() {
        return ot1;
    }

    public void setRelaList(List<SelectItem> relaList) {
        this.relaList = relaList;
    }

    public List<SelectItem> getRelaList() {
        return relaList;
    }


    /*
    public void setRelaValue(List<SelectItem> relaValue) {
        this.relaValue = relaValue;
        if (!(relaList.get(0)==relaValue.get(0))) {
        controlarTablas(true);
        } else {
            controlarTablas(false);
        }

    }

    public List<SelectItem> getRelaValue() {
        return relaValue;
    }
*/

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

    public void setCb1(RichCommandButton cb1) {
        this.cb1 = cb1;
    }

    public RichCommandButton getCb1() {
        return cb1;
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

    public void setCb2(RichCommandButton cb2) {
        this.cb2 = cb2;
    }

    public RichCommandButton getCb2() {
        return cb2;
    }

    public void setP1(RichPopup p1) {
        this.p1 = p1;
    }

    public RichPopup getP1() {
        return p1;
    }

    public void setIt7(RichInputText it7) {
        this.it7 = it7;
    }

    public RichInputText getIt7() {
        return it7;
    }

    public void setPfl2(RichPanelFormLayout pfl2) {
        this.pfl2 = pfl2;
    }

    public RichPanelFormLayout getPfl2() {
        return pfl2;
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


    public void setBeanSessionRegistrarCliente(SessionScopedBeanRegistrarCliente beanSessionRegistrarCliente) {
        this.beanSessionRegistrarCliente = beanSessionRegistrarCliente;
    }

    public SessionScopedBeanRegistrarCliente getBeanSessionRegistrarCliente() {
        return beanSessionRegistrarCliente;
    }

    public void setTbDirecs(RichTable t1) {
        this.tbDirecs = t1;
    }

    public RichTable getTbDirecs() {
        return tbDirecs;
    }

    public void setS1(RichSubform s1) {
        this.s1 = s1;
    }

    public RichSubform getS1() {
        return s1;
    }


    public void setPglBotonesChofer(RichPanelGridLayout pglBotonesChofer) {
        this.pglBotonesChofer = pglBotonesChofer;
    }

    public RichPanelGridLayout getPglBotonesChofer() {
        return pglBotonesChofer;
    }

    public void setRowBotonesChofer(RichGridRow rowBotonesChofer) {
        this.rowBotonesChofer = rowBotonesChofer;
    }

    public RichGridRow getRowBotonesChofer() {
        return rowBotonesChofer;
    }

    public void setCelbtn1Chofer(RichGridCell celbtn1Chofer) {
        this.celbtn1Chofer = celbtn1Chofer;
    }

    public RichGridCell getCelbtn1Chofer() {
        return celbtn1Chofer;
    }

    public void setCelbtn2Chofer(RichGridCell celbtn2Chofer) {
        this.celbtn2Chofer = celbtn2Chofer;
    }

    public RichGridCell getCelbtn2Chofer() {
        return celbtn2Chofer;
    }

    public void setCelbtn3Chofer(RichGridCell celbtn3Chofer) {
        this.celbtn3Chofer = celbtn3Chofer;
    }

    public RichGridCell getCelbtn3Chofer() {
        return celbtn3Chofer;
    }


    public void setBtnNuevoChofer(RichCommandButton btnNuevoChofer) {
        this.btnNuevoChofer = btnNuevoChofer;
    }

    public RichCommandButton getBtnNuevoChofer() {
        return btnNuevoChofer;
    }

    public void setBtnEditarChofer(RichCommandButton btnEditarChofer) {
        this.btnEditarChofer = btnEditarChofer;
    }

    public RichCommandButton getBtnEditarChofer() {
        return btnEditarChofer;
    }

    public void setBtnBorrarChofer(RichCommandButton btnBorrarChofer) {
        this.btnBorrarChofer = btnBorrarChofer;
    }

    public RichCommandButton getBtnBorrarChofer() {
        return btnBorrarChofer;
    }


    public void setPanelRegistroChofer(RichPanelFormLayout panelRegistroChofer) {
        this.panelRegistroChofer = panelRegistroChofer;
    }

    public RichPanelFormLayout getPanelRegistroChofer() {
        return panelRegistroChofer;
    }


    public void setPopupNuevoChofer(RichPopup popupNuevoChofer) {
        this.popupNuevoChofer = popupNuevoChofer;
    }

    public RichPopup getPopupNuevoChofer() {
        return popupNuevoChofer;
    }


    public void setSfNwCho(RichSubform sfNwCho) {
        this.sfNwCho = sfNwCho;
    }

    public RichSubform getSfNwCho() {
        return sfNwCho;
    }


    public void setBtnRegistrarChofer(RichCommandButton btnRegistrarChofer) {
        this.btnRegistrarChofer = btnRegistrarChofer;
    }

    public RichCommandButton getBtnRegistrarChofer() {
        return btnRegistrarChofer;
    }

    public void setPanelPopupRegChofer(RichPanelFormLayout panelPopupRegChofer) {
        this.panelPopupRegChofer = panelPopupRegChofer;
    }

    public RichPanelFormLayout getPanelPopupRegChofer() {
        return panelPopupRegChofer;
    }

    public void setTxtNombreYApellido(RichInputText txtNombreYApellido) {
        this.txtNombreYApellido = txtNombreYApellido;
    }

    public RichInputText getTxtNombreYApellido() {
        return txtNombreYApellido;
    }

    public void setNombreChofer(String nombreChofer) {
        this.nombreChofer = nombreChofer;
    }

    public String getNombreChofer() {
        return nombreChofer;
    }


    public void setTxtLicenciaChofer(RichInputText txtLicenciaChofer) {
        this.txtLicenciaChofer = txtLicenciaChofer;
    }

    public RichInputText getTxtLicenciaChofer() {
        return txtLicenciaChofer;
    }

    public void setLicenciaChofer(String licenciaChofer) {
        this.licenciaChofer = licenciaChofer;
    }

    public String getLicenciaChofer() {
        return licenciaChofer;
    }


    public void setBeanChofer(BeanChofer beanChofer) {
        this.beanChofer = beanChofer;
    }

    public BeanChofer getBeanChofer() {
        return beanChofer;
    }

    public void setTabChof(RichTable tabChof) {
        this.tabChof = tabChof;
    }

    public RichTable getTabChof() {
        return tabChof;
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

    public void setSi7(RichSelectItem si7) {
        this.si7 = si7;
    }

    public RichSelectItem getSi7() {
        return si7;
    }

    public void mostrarTablaChofer(ValueChangeEvent vce) {
        getBeanSessionRegistrarCliente().setRelaValue((List<String>)vce.getNewValue());
        List<String> objArr = (List<String>) vce.getNewValue();
        int num = 0;
        if (objArr != null) { 
            for (int x = 0; x < objArr.size(); x++) {
                String obj = objArr.get(x);
                if (obj.equals("1")) { //Proveedor
                    num++;
                }
            }
            if (num != 0) {
           /*     labelCertficado.setDisabled(false);
                labelCertficado.setVisible(true);
                labelCertficado.setRequired(true);
                beanSessionRegistrarCliente.setIsDisa(false);
                beanSessionRegistrarCliente.setIsReq_Vis(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(labelCertficado);*/
                controlarTablas(true);  
            }
            if (num == 0) {
             /*   labelCertficado.setDisabled(true);
                labelCertficado.setVisible(false);
                labelCertficado.setRequired(false);
                beanSessionRegistrarCliente.setIsDisa(true);
                beanSessionRegistrarCliente.setIsReq_Vis(false);              
                AdfFacesContext.getCurrentInstance().addPartialTarget(labelCertficado);*/
                controlarTablas(false);
                List milista = new ArrayList<String>();
                List milista2 = new ArrayList<String>();
                getBeanSessionRegistrarCliente().setListChofer(milista); //LIMPIA LA LISTA
                getBeanSessionRegistrarCliente().setListFlota(milista2);
            }
        }
        if (objArr == null) {
            controlarTablas(false);
            labelCertficado.setRequired(false);
            labelCertficado.setDisabled(true);
            labelCertficado.setVisible(false);
            AdfFacesContext.getCurrentInstance().addPartialTarget(labelCertficado);
            List milista = new ArrayList<String>();
            List milista2 = new ArrayList<String>();
            getBeanSessionRegistrarCliente().setListChofer(milista); //NULEA LA LISTA Y PRODUCE UN NULLEXCEPTION
            getBeanSessionRegistrarCliente().setListFlota(milista2);
        }
    }

    public void setPanelRegistroFlota(RichPanelFormLayout panelRegistroFlota) {
        this.panelRegistroFlota = panelRegistroFlota;
    }

    public RichPanelFormLayout getPanelRegistroFlota() {
        return panelRegistroFlota;
    }


    public void setPglBotonesFlota(RichPanelGridLayout pglBotonesFlota) {
        this.pglBotonesFlota = pglBotonesFlota;
    }

    public RichPanelGridLayout getPglBotonesFlota() {
        return pglBotonesFlota;
    }

    public void setRowBotonesFlota(RichGridRow rowBotonesFlota) {
        this.rowBotonesFlota = rowBotonesFlota;
    }

    public RichGridRow getRowBotonesFlota() {
        return rowBotonesFlota;
    }

    public void setCelbtn1Flota(RichGridCell celbtn1Flota) {
        this.celbtn1Flota = celbtn1Flota;
    }

    public RichGridCell getCelbtn1Flota() {
        return celbtn1Flota;
    }

    public void setCelbtn2Flota(RichGridCell celbtn2Flota) {
        this.celbtn2Flota = celbtn2Flota;
    }

    public RichGridCell getCelbtn2Flota() {
        return celbtn2Flota;
    }

    public void setCelbtn3Flota(RichGridCell celbtn3Flota) {
        this.celbtn3Flota = celbtn3Flota;
    }

    public RichGridCell getCelbtn3Flota() {
        return celbtn3Flota;
    }

    public void setBtnNuevaFlota(RichCommandButton btnNuevaFlota) {
        this.btnNuevaFlota = btnNuevaFlota;
    }

    public RichCommandButton getBtnNuevaFlota() {
        return btnNuevaFlota;
    }

    public void setBtnEditarFlota(RichCommandButton btnEditarFlota) {
        this.btnEditarFlota = btnEditarFlota;
    }

    public RichCommandButton getBtnEditarFlota() {
        return btnEditarFlota;
    }

    public void setBtnBorrarFlota(RichCommandButton btnBorrarFlota) {
        this.btnBorrarFlota = btnBorrarFlota;
    }

    public RichCommandButton getBtnBorrarFlota() {
        return btnBorrarFlota;
    }


    public void setPopuNuevaFlota(RichPopup popuNuevaFlota) {
        this.popuNuevaFlota = popuNuevaFlota;
    }

    public RichPopup getPopuNuevaFlota() {
        return popuNuevaFlota;
    }


    public void setSfNwFlo(RichSubform sfNwFlo) {
        this.sfNwFlo = sfNwFlo;
    }

    public RichSubform getSfNwFlo() {
        return sfNwFlo;
    }

    public void setPanelPopupRegFlota(RichPanelFormLayout panelPopupRegFlota) {
        this.panelPopupRegFlota = panelPopupRegFlota;
    }

    public RichPanelFormLayout getPanelPopupRegFlota() {
        return panelPopupRegFlota;
    }


    public void setBtnRegistrarFlota(RichCommandButton btnRegistrarFlota) {
        this.btnRegistrarFlota = btnRegistrarFlota;
    }

    public RichCommandButton getBtnRegistrarFlota() {
        return btnRegistrarFlota;
    }

    public void setTxtMarcaVehiculo(RichInputText txtMarcaVehiculo) {
        this.txtMarcaVehiculo = txtMarcaVehiculo;
    }

    public RichInputText getTxtMarcaVehiculo() {
        return txtMarcaVehiculo;
    }

    public void setTxtPlacaVehiculo(RichInputText txtPlacaVehiculo) {
        this.txtPlacaVehiculo = txtPlacaVehiculo;
    }

    public RichInputText getTxtPlacaVehiculo() {
        return txtPlacaVehiculo;
    }

    public void setTxtSotaVehiculo(RichInputText txtSotaVehiculo) {
        this.txtSotaVehiculo = txtSotaVehiculo;
    }

    public RichInputText getTxtSotaVehiculo() {
        return txtSotaVehiculo;
    }

    public void setTxtConfigVehiculo(RichInputText txtConfigVehiculo) {
        this.txtConfigVehiculo = txtConfigVehiculo;
    }

    public RichInputText getTxtConfigVehiculo() {
        return txtConfigVehiculo;
    }

    public void setTxtDescVehiculo(RichInputText txtDescVehiculo) {
        this.txtDescVehiculo = txtDescVehiculo;
    }

    public RichInputText getTxtDescVehiculo() {
        return txtDescVehiculo;
    }

    public void setMarcaVehiculo(String marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }

    public String getMarcaVehiculo() {
        return marcaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setSoatVehiculo(String soatVehiculo) {
        this.soatVehiculo = soatVehiculo;
    }

    public String getSoatVehiculo() {
        return soatVehiculo;
    }

    public void setConfigVehiculo(String configVehiculo) {
        this.configVehiculo = configVehiculo;
    }

    public String getConfigVehiculo() {
        return configVehiculo;
    }

    public void setDescripVehiculo(String descripVehiculo) {
        this.descripVehiculo = descripVehiculo;
    }

    public String getDescripVehiculo() {
        return descripVehiculo;
    }

    public void setBeanFlota(BeanFlota beanFlota) {
        this.beanFlota = beanFlota;
    }

    public BeanFlota getBeanFlota() {
        return beanFlota;
    }

    public void setTabFlot(RichTable tabFlot) {
        this.tabFlot = tabFlot;
    }

    public RichTable getTabFlot() {
        return tabFlot;
    }


    public void setOutNumDire(RichOutputText outNumDire) {
        this.outNumDire = outNumDire;
    }

    public RichOutputText getOutNumDire() {
        return outNumDire;
    }


    public void setSi17(RichSelectItem si17) {
        this.si17 = si17;
    }

    public RichSelectItem getSi17() {
        return si17;
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

    public void setSi14(RichSelectItem si14) {
        this.si14 = si14;
    }

    public RichSelectItem getSi14() {
        return si14;
    }

    public void setSi15(RichSelectItem si15) {
        this.si15 = si15;
    }

    public RichSelectItem getSi15() {
        return si15;
    }

    public void setSi16(RichSelectItem si16) {
        this.si16 = si16;
    }

    public RichSelectItem getSi16() {
        return si16;
    }

    public void setChoiceConfVehicular(RichSelectOneChoice choiceConfVehicular) {
        this.choiceConfVehicular = choiceConfVehicular;
    }

    public RichSelectOneChoice getChoiceConfVehicular() {
        return choiceConfVehicular;
    }


    public void setPglBotonesDireccion(RichPanelGridLayout pglBotonesDireccion) {
        this.pglBotonesDireccion = pglBotonesDireccion;
    }

    public RichPanelGridLayout getPglBotonesDireccion() {
        return pglBotonesDireccion;
    }

    public void setRowBotonesDirec(RichGridRow rowBotonesDirec) {
        this.rowBotonesDirec = rowBotonesDirec;
    }

    public RichGridRow getRowBotonesDirec() {
        return rowBotonesDirec;
    }

    public void setCeldabtn1Dire(RichGridCell celdabtn1Dire) {
        this.celdabtn1Dire = celdabtn1Dire;
    }

    public RichGridCell getCeldabtn1Dire() {
        return celdabtn1Dire;
    }

    public void setCeldabtn2Dire(RichGridCell celdabtn2Dire) {
        this.celdabtn2Dire = celdabtn2Dire;
    }

    public RichGridCell getCeldabtn2Dire() {
        return celdabtn2Dire;
    }

    public void setCeldabtn3Dire(RichGridCell celdabtn3Dire) {
        this.celdabtn3Dire = celdabtn3Dire;
    }

    public RichGridCell getCeldabtn3Dire() {
        return celdabtn3Dire;
    }

    public void setBtnEditarDireccion(RichCommandButton btnEditarDireccion) {
        this.btnEditarDireccion = btnEditarDireccion;
    }

    public RichCommandButton getBtnEditarDireccion() {
        return btnEditarDireccion;
    }

    public void setBtnBorrarDireccion(RichCommandButton btnBorrarDireccion) {
        this.btnBorrarDireccion = btnBorrarDireccion;
    }

    public RichCommandButton getBtnBorrarDireccion() {
        return btnBorrarDireccion;
    }

    public void setBeanDire2(BeanDireccion beanDire2) {
        this.beanDire2 = beanDire2;
    }

    public BeanDireccion getBeanDire2() {
        return beanDire2;
    }

    public void setLabelCertficado(RichInputText it8) {
        this.labelCertficado = it8;
    }

    public RichInputText getLabelCertficado() {
        return labelCertficado;
    }

    public void setS2(RichSeparator s2) {
        this.s2 = s2;
    }

    public RichSeparator getS2() {
        return s2;
    }

    public void setCertificadoInscripcionFlota(String certificadoInscripcionFlota) {
        this.certificadoInscripcionFlota = certificadoInscripcionFlota;
    }

    public String getCertificadoInscripcionFlota() {
        return certificadoInscripcionFlota;
    }

    public void setInputCerInsFlota(RichInputText inputCerInsFlota) {
        this.inputCerInsFlota = inputCerInsFlota;
    }

    public RichInputText getInputCerInsFlota() {
        return inputCerInsFlota;
    }
}
