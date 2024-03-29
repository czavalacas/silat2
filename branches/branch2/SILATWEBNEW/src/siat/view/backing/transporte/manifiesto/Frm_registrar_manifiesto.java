package siat.view.backing.transporte.manifiesto;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.InputStream;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.component.UIOutput;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import javax.imageio.ImageIO;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.servlet.ServletContext;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.RichSubform;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputNumberSpinbox;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectItem;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichDecorativeBox;
import oracle.adf.view.rich.component.rich.layout.RichGridCell;
import oracle.adf.view.rich.component.rich.layout.RichGridRow;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGridLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelTabbed;
import oracle.adf.view.rich.component.rich.layout.RichShowDetailItem;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichGoLink;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;

import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichSeparator;

import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.render.ClientEvent;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.UploadedFile;

import siat.view.backing.transporte.guia.SessionScopedBeanRegistrarGuia;
import siat.view.backing.utiles.Utils;

import siat.view.backing.utiles.fecha.FechaUtiles;

import silat.servicios_negocio.Beans.BeanADRelacionEmpresa;
import silat.servicios_negocio.Beans.BeanChofer;
import silat.servicios_negocio.Beans.BeanConstraint;
import silat.servicios_negocio.Beans.BeanDireccion;
import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanFlota;
import silat.servicios_negocio.Beans.BeanManifiesto;
import silat.servicios_negocio.Beans.BeanOrdenServicio;
import silat.servicios_negocio.Beans.BeanTRGuia;
import silat.servicios_negocio.Beans.BeanTRItem;
import silat.servicios_negocio.Beans.BeanUnidadMedida;
import silat.servicios_negocio.Beans.BeanUnidadNegocio;
import silat.servicios_negocio.Beans.BeanUsuarioAutenticado;
import silat.servicios_negocio.LNSF.IL.LN_T_SFManifiestoRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFChoferRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFDireccionRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFEmpresasRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFFlotaRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFGuiaRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFManifiestoRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFOrdenServicioRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFRelacionEmpresaRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFUnidadMedidaRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFUnidadNegocioRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFUtilsRemote;
import silat.servicios_negocio.LNSF.IR.LN_T_SFGuiaRemote;
import silat.servicios_negocio.LNSF.IR.LN_T_SFUnidadMedidaRemote;

public class Frm_registrar_manifiesto {
    
    private RichOutputText otTitulo;
    private RichPanelGridLayout pgl1;
    private RichGridRow gr1;
    private RichGridCell gc1;
    private RichGridCell gc2;
    private RichGridRow rowBotones;
    private RichGridCell gc3;
    private RichGridCell celBtn1;
    private RichInputText itOServicio;
    private RichPopup popOS;
    private RichOutputLabel olTituloPopUp;
    private RichDecorativeBox db1;
    private RichPanelGroupLayout pgl2;
    private RichTable tbOS;
    private RichInputDate id1;
    private RichPanelGroupLayout pgl3;
    private RichPanelFormLayout pfl1;
    private RichInputText it2;
    private RichInputText it3;
    private RichInputText itDetraccion;
    private RichCommandButton cb1;
    private RichDecorativeBox db2;
    private RichPopup popRemit;
    private RichPanelFormLayout pfl2;
    private RichInputText it4;
    private RichInputText it5;
    private RichCommandButton cb3;
    private RichTable tbRemit;
    private RichSubform s1;
    private RichSubform s2;
    private RichInputText razonSocProve;
    private RichInputText rucProve;
    private RichPanelFormLayout pfl_main;
    private RichPanelFormLayout pfl3;
    private RichPanelGridLayout pglBotones;
    private RichGridRow gr3;
    private RichGridCell gc5;
    private RichGridCell gc6;
    private RichPanelGridLayout pgl5;
    private RichGridRow gr4;
    private RichGridCell gc7;
    private RichGridCell ddd;
    private RichInputText dd;
    private RichPanelFormLayout pfl4;
    private RichInputText razSocClie;
    private RichInputText rucClie;
    private RichPanelStretchLayout psl1;
    private RichInputText txtCorrelativo;
    private RichPanelFormLayout pfl5;
    private RichTable tblItms;
    private RichCommandButton btnNewItem;
    private RichPopup popItems;
    private RichSubform s3;
    private RichPanelFormLayout pfl6;
    private RichInputText txtGuiRem;
    private RichInputText txtDescBien;
    private RichCommandButton btnRegistrarItem;
    private RichSelectOneChoice socUndMed;
    private RichSelectItem si1;
    private RichSelectItem si2;
    private RichSelectItem si3;
    private RichCommandButton btnEditarItem;
    private RichCommandButton btnBorrarItem;
    private RichSeparator s4;
    private RichInputText txtObs;
    private RichPanelFormLayout pfl7;
    private RichPanelFormLayout pfl8;
    private RichInputText txtRazSocEmpTransp;
    private RichInputText rucEmpTransp;
    private RichPanelFormLayout panFormTrans;
    private RichInputText txtMarcVehi;
    private RichInputText txtPlaca;
    private RichInputText txtConfigVehi;
    private RichInputText txtCertInsc;
    private RichInputText txtLicCondu;
    private RichInputText txtConductor;
    private RichSeparator s5;
    private UIOutput os1;
    private RichDocument d1;
    private RichPopup popProv;
    private RichSubform s6;
    private RichDecorativeBox db3;
    private RichPanelFormLayout pfl9;
    private RichInputText it6;
    private RichCommandButton cb2;
    private RichTable tbProv;
    private RichPanelTabbed pt1;
    private RichShowDetailItem sdi1;
    private RichShowDetailItem sdi2;
    private RichGridCell celBtn2;
    private RichGridCell celBtn3;
    private RichSelectOneChoice tipoManf;
    private RichSelectItem itemManif1;
    private RichSelectItem itemManif2;
    private RichPanelFormLayout pflNewManif;
    private RichInputDate txtFecManif;
    private RichInputText txtFletePact;
    private RichInputText txtAdelanto;
    private RichInputText txtObsManif;
    private RichSelectOneChoice tipoDocManif;
    private RichSelectItem tipoDoc1;
    private RichSelectItem tipoDoc2;
    private RichSelectItem tipoDoc3;
    private RichSelectItem tipoDoc4;
    private RichSelectItem tipoDoc5;
    private RichCommandButton btnRegManif;
    private RichSelectItem itemManif3;
    private RichPanelBox pboxDatosEmp;
    private RichPanelBox pboxDatosTrans;
    private RichPanelBox pboxManifiesto;
    private RichSelectOneChoice socFlota;
    private UISelectItems si4;
    private RichSelectOneChoice socChofer;
    private UISelectItems si5;
    private RichInputText itBuscarManif;
    private RichPopup popManif;
    private RichTable tabMani;
    private RichInputDate id3;
    private RichInputText it13;
    private RichPanelFormLayout pfl10;
    private RichInputDate id5;
    private RichInputDate id4;
    private RichInputText it14;
    private RichCommandButton btnBuscarManif;
    private RichInputText txtRazSocEmpTransp2;
    private RichSelectOneChoice socDirecs;
    private UISelectItems si6;
    private RichSelectOneChoice socDirecRemi;
    private UISelectItems si7;
    private RichCommandButton btnGrabarGuia;
    private RichInputDate fecEmision;
    private RichInputDate fecDespacho;
    private RichSelectOneChoice socCodigoUN;
    private UISelectItems si8;
    private RichInputText itSaldo;
    private RichSelectOneChoice socGuiasManif;
    private UISelectItems si9;
    private RichDocument diaGuiaOK;
    private RichForm f1;
    private RichSelectOneChoice socConf;
    private UISelectItems si10;
    private RichInputText itOServAux;
    private RichInputText it1;
    private RichInputText itIGV;
    private RichInputText itMontoFin;
    private RichPopup popGuiaOK;
    private RichDialog d2;
    private RichSelectOneChoice socEstManif;
    private RichSelectItem si11;
    private RichSelectItem si12;
    private RichSelectItem si13;
    private RichPanelBox pb1;
    private RichPopup popImg;
    private RichPopup popUM;
    private RichDialog diagImg;
    private RichGoLink gl1;
    private RichImage imgGuia;
    private RichInputFile fileImg;
    private RichCommandButton btnImg;
    private RichCommandButton cb4;
    private RichCommandButton btnAddUndMed;
    private RichPanelBox pb2;
    private RichInputText itCodManif;
    private RichSelectItem si14;
    private RichSelectItem si15;
    private RichCommandButton btnBOS;
    private RichInputNumberSpinbox spiPaq;
    private RichInputNumberSpinbox txtPeso;
    private RichPopup popEst;
    private RichDialog diaEst;
    private RichSelectBooleanCheckbox sbcEstOS;
    private RichSelectBooleanCheckbox sbcEstManif;
    private RichInputText itDesc;
    private RichInputText itUniMed;
    private RichCommandButton btnNewUM;
    private RichCommandButton btnAnular;
    private RichPopup popAnul;
    private RichInputText txtCantidad;
    private RichSubform s8;
    private RichDialog d3;
    private RichPanelFormLayout pfl11;
    /*--------Mis Variables------------*/
    private final static String LOOKUP_NAME_SFFLOTA_REMOTO        = "mapLN_C_SFFlota";
    private final static String LOOKUP_NAME_SFCHOFER_REMOTO       = "mapLN_C_SFChofer";
    private final static String LOOKUP_NAME_SFEMPRESA_REMOTO      = "mapLN_C_SFEmpresas";
    private final static String LOOKUP_NAME_SFDIRECCION_REMOTO    = "LUBAL_SIAT_APP-SILATNEGOCIO-LN_C_SFDireccion";
    private final static String LOOKUP_NAME_SFMANIFIESTO_REMOTO   = "mapLN_T_SFManifiesto";
    private final static String LOOKUP_NAME_SFGUIA_REMOTO         = "mapLN_T_SFGuia";
    private final static String LOOKUP_NAME_SFUN_REMOTO           = "mapLN_C_SFUnidadNegocio";
    private final static String LOOKUP_NAME_SFCGUIA_REMOTO        = "mapLN_C_SFGuia";
    private final static String LOOKUP_NAME_SFCMANIF_REMOTO       = "mapLN_C_SFManifiesto";
    private final static String LOOKUP_NAME_SFC_RELA_REMOTO       = "mapLN_C_SFRelacionEmpresa";
    private final static String LOOKUP_NAME_SFC_OS_REMOTO         = "mapLN_C_SFOrdenServicio";
    private final static String LOOKUP_NAME_SFC_UTL_REMOTO        = "mapLN_C_SFUtils";
    private final static String LOOKUP_NAME_SFC_UND_MEDIDA_REMOTO = "mapLN_C_SFUnidadMedida";
    private final static String LOOKUP_NAME_SFT_UND_MEDIDA_REMOTO = "mapLN_T_SFUnidadMedida";
    private LN_C_SFManifiestoRemote ln_C_SFManifiestoRemote;
    private LN_C_SFRelacionEmpresaRemote ln_C_SFRelacionEmpresaRemote;
    private LN_C_SFGuiaRemote ln_C_SFGuiaRemote;
    private LN_C_SFFlotaRemote ln_C_SFFlotaRemote;
    private LN_C_SFChoferRemote ln_C_SFChoferRemote;
    private LN_C_SFEmpresasRemote ln_C_SFEmpresasRemote;
    private LN_C_SFDireccionRemote LN_C_SFDireccionRemote;
    private LN_T_SFManifiestoRemote ln_T_SFManifiestoRemote;
    private LN_T_SFGuiaRemote ln_T_SFGuiaRemote;
    private LN_C_SFUnidadNegocioRemote ln_C_SFUnidadNegocioRemote;
    private LN_C_SFOrdenServicioRemote ln_C_SFOrdenServicioRemote;
    private LN_C_SFUtilsRemote ln_C_SFUtilsRemote;
    private LN_C_SFUnidadMedidaRemote ln_C_SFUnidadMedidaRemote;
    private LN_T_SFUnidadMedidaRemote ln_T_SFUnidadMedidaRemote;
    private String correlativo = "";
    private BeanTRItem beanIteam = new BeanTRItem();
    private List<BeanTRItem> lstItems = new ArrayList<BeanTRItem>();
    private List guiasManif;
    private String cCidGuiaRemitente;
    private String cDescItem;
    private String cUndMedida;
    private int dPeso;
    private Double nCantidad;
    FacesContext ctx = FacesContext.getCurrentInstance();
    private RichSelectBooleanCheckbox ckbTraPro;
    private SessionScopedBeanRegistrarManifiesto beanSessionRegistrarManifiesto;
    private BeanUsuarioAutenticado beanUsuario = new BeanUsuarioAutenticado();  

    public Frm_registrar_manifiesto(){
        try{
            final Context ctx;
            ctx = new InitialContext();
            ln_C_SFFlotaRemote = (LN_C_SFFlotaRemote)                       ctx.lookup(LOOKUP_NAME_SFFLOTA_REMOTO);
            ln_C_SFChoferRemote = (LN_C_SFChoferRemote)                     ctx.lookup(LOOKUP_NAME_SFCHOFER_REMOTO);
            ln_C_SFEmpresasRemote = (LN_C_SFEmpresasRemote)                 ctx.lookup(LOOKUP_NAME_SFEMPRESA_REMOTO);
            LN_C_SFDireccionRemote = (LN_C_SFDireccionRemote)               ctx.lookup(LOOKUP_NAME_SFDIRECCION_REMOTO);
            ln_T_SFManifiestoRemote = (LN_T_SFManifiestoRemote)             ctx.lookup(LOOKUP_NAME_SFMANIFIESTO_REMOTO);
            ln_T_SFGuiaRemote = (LN_T_SFGuiaRemote)                         ctx.lookup(LOOKUP_NAME_SFGUIA_REMOTO);
            ln_C_SFGuiaRemote = (LN_C_SFGuiaRemote)                         ctx.lookup(LOOKUP_NAME_SFCGUIA_REMOTO);
            ln_C_SFUnidadNegocioRemote = (LN_C_SFUnidadNegocioRemote)       ctx.lookup(LOOKUP_NAME_SFUN_REMOTO);
            ln_C_SFManifiestoRemote = (LN_C_SFManifiestoRemote)             ctx.lookup(LOOKUP_NAME_SFCMANIF_REMOTO);
            ln_C_SFRelacionEmpresaRemote = (LN_C_SFRelacionEmpresaRemote)   ctx.lookup(LOOKUP_NAME_SFC_RELA_REMOTO);
            ln_C_SFOrdenServicioRemote = (LN_C_SFOrdenServicioRemote)       ctx.lookup(LOOKUP_NAME_SFC_OS_REMOTO);
            ln_C_SFUtilsRemote = (LN_C_SFUtilsRemote)                       ctx.lookup(LOOKUP_NAME_SFC_UTL_REMOTO);
            ln_C_SFUnidadMedidaRemote = (LN_C_SFUnidadMedidaRemote)         ctx.lookup(LOOKUP_NAME_SFC_UND_MEDIDA_REMOTO);
            ln_T_SFUnidadMedidaRemote = (LN_T_SFUnidadMedidaRemote)         ctx.lookup(LOOKUP_NAME_SFT_UND_MEDIDA_REMOTO);
            } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @PostConstruct
    public void methodInvokeOncedOnPageLoad(){
        if(getBeanSessionRegistrarManifiesto().getExec() == 0){
            mostrarProveedores();
            beanSessionRegistrarManifiesto.setValorComboManif(1);
            beanSessionRegistrarManifiesto.setTransportePropio(false);    
            nuevoManifiesto();     
            beanSessionRegistrarManifiesto.setTipDoc("1");
            beanSessionRegistrarManifiesto.setTxtTipDocuDisable(true);
            getBeanSessionRegistrarManifiesto().setExec(1);          
            }
    }   
    
    
    public void validarManifiesto(ActionEvent actionEvent) {
        if(beanSessionRegistrarManifiesto.getValorComboManif() == 1){//Nuevo Manif      
            if(beanSessionRegistrarManifiesto.getNidEmpProvTrans() == 0){
                Utils.throwError_Aux(ctx, "Debe seleccionar Empresa proveedora de transporte.",4);
            }else{   
                registrarManifiesto();
            }
        }
    }
    

    public String registrarManifiesto(){      
        try{
            String obsv = beanSessionRegistrarManifiesto.getObserv();
            if(obsv != null && obsv.length() >= 300){
                obsv = obsv.substring(0,300);
            }
            BeanManifiesto bManifiesto = ln_T_SFManifiestoRemote.registrarManifiesto_LN(getBeanSessionRegistrarManifiesto().getNidEmpProvTrans(),
                                                                                        getBeanSessionRegistrarManifiesto().getFecManif(), 
                                                                                        getBeanSessionRegistrarManifiesto().getFletePactado(),
                                                                                        getBeanSessionRegistrarManifiesto().getAdelanto(),
                                                                                        getBeanSessionRegistrarManifiesto().getTipDoc(),
                                                                                        obsv,
                                                                                        Integer.parseInt(getBeanSessionRegistrarManifiesto().getCidFlota()),
                                                                                        Integer.parseInt(getBeanSessionRegistrarManifiesto().getCidChofer()),
                                                                                        0,1,"3");//Guias x asignar
        
            if(bManifiesto.getBeanError() != null){
            BeanError error = bManifiesto.getBeanError();
                int severidad = 0;
                if ("000".equals(error.getCidError())) {
                    severidad = 3;
                    Utils.depurar("Grabo Manifiesto");
                    Utils._redireccionar(ctx, "WEB-INF/consultar_manifiesto.xml#consultar_manifiesto");
                } else {
                    severidad = 1;
                }
                Utils.throwError_Aux(ctx, error.getCDescripcionError(), severidad);
            }else{
                Utils.throwError_Aux(ctx,"Error Inesperado", 1);
                resetNewManif();
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            Utils.throwError_Aux(ctx,"Error Inesperado", 1);
            resetNewManif();
            return null;
        }
    }
    
    public void abrirPopEmp(ActionEvent ae) {
        RichPopup.PopupHints ph = new RichPopup.PopupHints();
        ph.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN,RichPopup.PopupHints.AlignTypes.ALIGN_AFTER_END);
        ph.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN_ID,otTitulo);
        mostrarRemitentes();
        popRemit.show(ph);
    }
    
    public String mostrarRemitentes(){
        String razRemi = getBeanSessionRegistrarManifiesto().getRazSocRemitente();
        String ruc = getBeanSessionRegistrarManifiesto().getRucRemi();
        getBeanSessionRegistrarManifiesto().setLstRemitentes(this.ln_C_SFRelacionEmpresaRemote.getEmpresaProveedoresCliente((razRemi == null ? "" : razRemi.toUpperCase()),
                                                                                                                      (ruc == null ? "" : ruc.toUpperCase())));
        Utils.unselectFilas(tbRemit);
        return null;
    }
    
    public String mostrarProveedores(){
        if(beanSessionRegistrarManifiesto.isTransportePropio() == false){
            String razProv = getBeanSessionRegistrarManifiesto().getRazSocProv();
            getBeanSessionRegistrarManifiesto().setLstProveedores(this.ln_C_SFRelacionEmpresaRemote.getEmpresaProveedores((razProv == null ? "" : razProv.toUpperCase())));
            Utils.unselectFilas(tbProv);
        }
        return null;
    }
  
  
    public void seleccionarProveedor(SelectionEvent selectionEvent){
     //   beanSessionRegistrarManifiesto.setExecuteCheck(1);
        if(beanSessionRegistrarManifiesto.isTransportePropio() == false){
            socFlota.resetValue();
            socChofer.resetValue();
            txtMarcVehi.resetValue();
            txtPlaca.resetValue();
            txtConfigVehi.resetValue();
            txtCertInsc.resetValue();
            txtLicCondu.resetValue();
            txtConductor.resetValue();
            beanSessionRegistrarManifiesto.setCidFlota(null);
            beanSessionRegistrarManifiesto.setCidChofer(null);
            beanSessionRegistrarManifiesto.setMarcaVehi(null);
            beanSessionRegistrarManifiesto.setPlaca(null);
            beanSessionRegistrarManifiesto.setConfigVehi(null);
            beanSessionRegistrarManifiesto.setCertiInsc(null);
            beanSessionRegistrarManifiesto.setLicen(null);
            beanSessionRegistrarManifiesto.setConductor(null);
            Utils.addTargetMany(txtLicCondu,txtConductor,txtMarcVehi,txtPlaca,txtConfigVehi,txtCertInsc,socFlota,socChofer);
            try {
                RichTable t = (RichTable)selectionEvent.getSource();
                Object _selectedRowData = t.getSelectedRowData();
                BeanADRelacionEmpresa beanEmpresaSubCont = (BeanADRelacionEmpresa) _selectedRowData;
                beanSessionRegistrarManifiesto.setNidEmpProvTrans(beanEmpresaSubCont.getAdEmpresa1().getNidParty().intValue());
                txtRazSocEmpTransp.setValue(beanEmpresaSubCont.getAdEmpresa1().getCRazonSocial());
                rucEmpTransp.setValue(beanEmpresaSubCont.getAdEmpresa1().getCRuc());
                beanSessionRegistrarManifiesto.setLstFlotas(this.llenarFlotasCombo(beanEmpresaSubCont.getAdEmpresa1().getNidParty().intValue(),null));
                beanSessionRegistrarManifiesto.setLstChofers(this.llenarChofersCombo(beanEmpresaSubCont.getAdEmpresa1().getNidParty().intValue(),null));
                Utils.unselectFilas(tbProv);
                popProv.hide();
                Utils.addTargetMany(tbProv,txtRazSocEmpTransp,rucEmpTransp,socFlota,socChofer);
             } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            popProv.hide();
        }
        return;
    }

    public ArrayList llenarFlotasCombo(Integer nidParty, Integer nidID) {
        ArrayList flotasItems = new ArrayList();
        flotasItems = llenarCombo(nidParty, nidID, flotasItems, 0);
        return flotasItems;
    }
    
    public ArrayList llenarChofersCombo(Integer nidParty,Integer nidID) {
        ArrayList chofersItems = new ArrayList();
        chofersItems = llenarCombo(nidParty,nidID,chofersItems,1);
        return chofersItems;
    }   
    
  
    public ArrayList llenarCombo(Integer nidParty,Integer nidID,ArrayList items, int opc){
        //opc 0 = flotas, opc = 1 chofer
        if(opc == 0){
            List<BeanFlota> flotas = ln_C_SFFlotaRemote.findFlotasByAttr_LN(nidParty,nidID);
            for (BeanFlota r : flotas) {
                items.add(new SelectItem(r.getNidFlota().toString(), 
                                         r.getCPlaca()+" | "+r.getCDescFlota().toString()));
            }
        }
        if(opc == 1){
            List<BeanChofer> chofers = ln_C_SFChoferRemote.findChofersByAttr_LN(nidParty,nidID);
            for (BeanChofer r : chofers) {
                items.add(new SelectItem(r.getNidChofer().toString(), 
                                         r.getLicencia()+" | "+r.getNombres().toString()));
            }
        }
        return items;
    }
    
    public ArrayList llenarConfCombo() {
        ArrayList confItems = new ArrayList();
        List<BeanConstraint> consts = ln_C_SFGuiaRemote.getListConformidad();
        for (BeanConstraint r : consts) {
            confItems.add(new SelectItem(r.getCValorCampo().toString(), 
                                         r.getCDescrip().toString()));
        }
        return confItems;
    }
    
    public ArrayList llenarUndMedidaCombo() {
        ArrayList umItems = new ArrayList();
        List<BeanUnidadMedida> consts = ln_C_SFUnidadMedidaRemote.getUnidadesMedida_LN();
        for (BeanUnidadMedida r : consts) {
            umItems.add(new SelectItem(r.getSigla().toString(), 
                                       r.getDescUnidadMedida().toString()));
        }
        return umItems;
    }
    
    public void selectFlota(ValueChangeEvent vce) {
         vce.getComponent().processUpdates(FacesContext.getCurrentInstance());
        if(vce.getNewValue() != null){
            String cidFlota = vce.getNewValue().toString(); 
            int nidFlota = Integer.parseInt(cidFlota);
            List<BeanFlota> lstBeanFlota = ln_C_SFFlotaRemote.findFlotasByAttr_LN(null,nidFlota);
            if(lstBeanFlota != null){
                BeanFlota beanFlota = lstBeanFlota.get(0);
                txtMarcVehi.setValue(beanFlota.getCMarvehi());
                txtPlaca.setValue(beanFlota.getCPlaca());
                txtConfigVehi.setValue(beanFlota.getCConfveh());
                txtCertInsc.setValue(beanFlota.getCertificadoInscripcionEmpresa());
            }
        }else{
            txtMarcVehi.resetValue();
            txtPlaca.resetValue();
            txtConfigVehi.resetValue();
            txtCertInsc.resetValue();
        }
         Utils.addTargetMany(txtMarcVehi,txtPlaca,txtConfigVehi,txtCertInsc);
    }
    
    public void selectChofer(ValueChangeEvent vce) {
        vce.getComponent().processUpdates(FacesContext.getCurrentInstance());
        if(vce.getNewValue() != null){
            String cidChofer = vce.getNewValue().toString();
            int nidChofer = Integer.parseInt(cidChofer);
            List<BeanChofer> lstBeanChofer = ln_C_SFChoferRemote.findChofersByAttr_LN(null,nidChofer);
            if(lstBeanChofer != null){
                BeanChofer beanChofer = lstBeanChofer.get(0);
                txtLicCondu.setValue(beanChofer.getLicencia());
                txtConductor.setValue(beanChofer.getNombres());
            }
        }else{
            txtLicCondu.resetValue();
            txtConductor.resetValue();
        }
        Utils.addTargetMany(txtLicCondu,txtConductor);
     }  

     
  
    public void selectManif(SelectionEvent selectionEvent) {
        RichTable t = (RichTable)selectionEvent.getSource();
        BeanManifiesto beanManifiesto = (BeanManifiesto)  t.getSelectedRowData();
         txtFecManif.setValue(beanManifiesto.getFechaManifiesto());
        itCodManif.setValue(beanManifiesto.getNidManifiesto());
        txtAdelanto.setValue(beanManifiesto.getNAdelanto());
        txtFletePact.setValue(beanManifiesto.getNFletePactado());
        Double igv = beanManifiesto.getIgv();
        itSaldo.setValue(beanManifiesto.getDetraccion() - beanManifiesto.getNAdelanto());
        itIGV.setValue(igv);
        itDetraccion.setValue(beanManifiesto.getDetraccionReal());
        itMontoFin.setValue(beanManifiesto.getDetraccion());
        tipoDocManif.setValue(beanManifiesto.getCTipoDoc());
        txtObsManif.setValue(beanManifiesto.getCObservaciones());
        txtRazSocEmpTransp2.setValue(beanManifiesto.getTrManifiesto().getCRazonSocial());
        rucEmpTransp.setValue(beanManifiesto.getTrManifiesto().getCRuc());
        beanSessionRegistrarManifiesto.setLstFlotas(this.llenarFlotasCombo(null,beanManifiesto.getNidFlota()));
        socFlota.setValue(beanManifiesto.getNidFlota().toString());
        beanSessionRegistrarManifiesto.setCidFlota(beanManifiesto.getNidFlota().toString());
        beanSessionRegistrarManifiesto.setCidChofer(beanManifiesto.getNidChof().toString());
        beanSessionRegistrarManifiesto.setLstChofers(this.llenarChofersCombo(null,beanManifiesto.getNidChof()));
        List<BeanFlota> lstBeanFlota = ln_C_SFFlotaRemote.findFlotasByAttr_LN(null,beanManifiesto.getNidFlota());
        if(lstBeanFlota != null){
            if(lstBeanFlota.size() > 0){
                BeanFlota beanFlota = lstBeanFlota.get(0);
                txtMarcVehi.setValue(beanFlota.getCMarvehi());
                txtPlaca.setValue(beanFlota.getCPlaca());
                txtConfigVehi.setValue(beanFlota.getCConfveh());
                txtCertInsc.setValue(beanFlota.getCertificadoInscripcionEmpresa());
            }
        }
        socChofer.setValue(beanManifiesto.getNidChof().toString());
        beanSessionRegistrarManifiesto.setManifIngresado(beanManifiesto);
        beanSessionRegistrarManifiesto.setNidManifiesto(beanManifiesto.getNidManifiesto());
        List<BeanChofer> lstBeanChofer = ln_C_SFChoferRemote.findChofersByAttr_LN(null,beanManifiesto.getNidChof());
        if(lstBeanChofer != null){
            if(lstBeanChofer.size() > 0){
                BeanChofer beanChofer = lstBeanChofer.get(0);
                txtLicCondu.setValue(beanChofer.getLicencia());
                txtConductor.setValue(beanChofer.getNombres());
            }
        }
        popManif.hide();
        Utils.addTargetMany(socChofer,itSaldo,socGuiasManif,txtFecManif,itCodManif,txtAdelanto,txtAdelanto,txtFletePact,tipoDocManif,
                            txtObsManif,txtRazSocEmpTransp2,rucEmpTransp,txtMarcVehi,txtPlaca,txtConfigVehi,txtCertInsc,
                            txtLicCondu,txtConductor,socFlota,itMontoFin,itIGV,itDetraccion);
    }
    
    public void checkBoxTransportePropio(ValueChangeEvent vc) {
        Boolean select = (Boolean) vc.getNewValue();
        Boolean selectOld = (Boolean) vc.getOldValue();
        if( (select == true && selectOld == null) || (select == true && selectOld != null)
           || (select == false && selectOld != null) ){
              camposManifiesto(select);
        }
    }

    public String camposManifiesto(Boolean isPropio){
        try{
            beanSessionRegistrarManifiesto.setTransportePropio(isPropio);
            txtAdelanto.resetValue();
            txtAdelanto.resetValue();
            beanSessionRegistrarManifiesto.setFletePactado(0.0);
            beanSessionRegistrarManifiesto.setAdelanto(0.0);
            itIGV.resetValue();
            beanSessionRegistrarManifiesto.setIgv(0.0);
            itDetraccion.resetValue();
            beanSessionRegistrarManifiesto.setDetraccionVal(0.0);
            itMontoFin.resetValue();
            beanSessionRegistrarManifiesto.setMontoFinalVal(0.0);
            itSaldo.resetValue();
            beanSessionRegistrarManifiesto.setSaldo(0.0);
            beanSessionRegistrarManifiesto.setLstGuiasManif(null);
            socFlota.resetValue();
            socChofer.resetValue();
            txtMarcVehi.resetValue();
            txtPlaca.resetValue();
            txtConfigVehi.resetValue();
            txtCertInsc.resetValue();
            txtLicCondu.resetValue();
            txtConductor.resetValue();
            beanSessionRegistrarManifiesto.setCidFlota(null);
            beanSessionRegistrarManifiesto.setCidChofer(null);
            beanSessionRegistrarManifiesto.setMarcaVehi(null);
            beanSessionRegistrarManifiesto.setPlaca(null);
            beanSessionRegistrarManifiesto.setConfigVehi(null);
            beanSessionRegistrarManifiesto.setCertiInsc(null);
            beanSessionRegistrarManifiesto.setLicen(null);
            beanSessionRegistrarManifiesto.setConductor(null);
            Utils.addTargetMany(txtLicCondu,txtConductor,txtMarcVehi,txtPlaca,txtConfigVehi,txtCertInsc,socFlota,socChofer);
            if(isPropio){//disabled = true
                txtFletePact.setDisabled(true);
                txtAdelanto.setDisabled(true);
                beanSessionRegistrarManifiesto.setTipDoc("2");
                beanSessionRegistrarManifiesto.setTxtTipDocuDisable(true);
                
                beanSessionRegistrarManifiesto.setCidChofer("0");
                beanSessionRegistrarManifiesto.setCidFlota("0");
                beanSessionRegistrarManifiesto.setPboxDatosEmpTitulo("Transporte Lubal");
                pboxDatosEmp.setText("Transporte Lubal");
                beanSessionRegistrarManifiesto.setLstFlotas(this.llenarFlotasCombo(5,null));
                beanSessionRegistrarManifiesto.setLstChofers(this.llenarChofersCombo(5,null));
                
                BeanEmpresa empresa = ln_C_SFEmpresasRemote.selectedEmpresa(new BigDecimal(5));
                beanSessionRegistrarManifiesto.setNidEmpProvTrans(empresa.getAdParty().getNidParty().intValue());
                beanSessionRegistrarManifiesto.setEmpresaSC(empresa.getCRazonSocial());
                beanSessionRegistrarManifiesto.setRucEmpSC(empresa.getCRuc());
                txtRazSocEmpTransp2.setRendered(true);
                txtRazSocEmpTransp2.setValue(empresa.getCRazonSocial());
                beanSessionRegistrarManifiesto.setVisibTxtRazSocProvTrans2(true);
                txtRazSocEmpTransp.setRendered(false);
                txtRazSocEmpTransp.resetValue();
                txtRazSocEmpTransp.setVisible(false);
                beanSessionRegistrarManifiesto.setVisibTxtRazSocProvTransBehav(false);
                rucEmpTransp.setValue(empresa.getCRuc());
                rucEmpTransp.setRendered(true);
                Utils.addTargetMany(itSaldo,itMontoFin,itDetraccion,itIGV,tipoDocManif,txtFletePact,txtAdelanto,pboxDatosEmp,txtRazSocEmpTransp2,txtRazSocEmpTransp,rucEmpTransp);
            }else{
                beanSessionRegistrarManifiesto.setNidEmpProvTrans(0);
                txtFletePact.setDisabled(false);
                txtAdelanto.setDisabled(false);
                beanSessionRegistrarManifiesto.setTipDoc("1");
                beanSessionRegistrarManifiesto.setTxtTipDocuDisable(true);
                beanSessionRegistrarManifiesto.setVisibTxtRazSocProvTrans2(false);
                beanSessionRegistrarManifiesto.setEstadoFormManif(true);
                beanSessionRegistrarManifiesto.setVisibTxtRazSocProvTransBehav(true);
                txtRazSocEmpTransp2.setRendered(false);
                txtRazSocEmpTransp2.resetValue();
                txtRazSocEmpTransp.setVisible(true);
                txtRazSocEmpTransp.setRendered(true);
                txtRazSocEmpTransp.resetValue();
                beanSessionRegistrarManifiesto.setEmpresaSC(null);
                beanSessionRegistrarManifiesto.setRucEmpSC(null);
                rucEmpTransp.resetValue();
                beanSessionRegistrarManifiesto.setPboxDatosEmpTitulo("Datos de Empresa Proveedora de Transporte");
                pboxDatosEmp.setText("Datos de Empresa Proveedora de Transporte");
                beanSessionRegistrarManifiesto.setLstFlotas(null);
                beanSessionRegistrarManifiesto.setLstChofers(null);
                Utils.addTargetMany(itSaldo,itMontoFin,itDetraccion,itIGV,tipoDocManif,txtFletePact,rucEmpTransp,txtAdelanto,txtRazSocEmpTransp2,txtRazSocEmpTransp,pboxDatosEmp);
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public void nuevoManifiesto(){
        beanSessionRegistrarManifiesto.setNidManifiesto(ln_C_SFUtilsRemote.traerSiguienteValorSequence("trmanifiesto.nid_manifiesto"));
        panelNuevoManif(true);
        beanSessionRegistrarManifiesto.setTxtFleteDisable(false);
        beanSessionRegistrarManifiesto.setTxtAdelaDisable(false);        
        beanSessionRegistrarManifiesto.setTxtTipDocuDisable(false);
        beanSessionRegistrarManifiesto.setTxtObsvDisable(false);
        beanSessionRegistrarManifiesto.setVisibTxtRazSocProvTransBehav(true);
        beanSessionRegistrarManifiesto.setDisabSocFlota(false);
        beanSessionRegistrarManifiesto.setDisabSocChofer(false);
        beanSessionRegistrarManifiesto.setRendBuscManif(false);
        getBeanSessionRegistrarManifiesto().setSaldo(0.0);
        beanSessionRegistrarManifiesto.setLstChofers(null);
        beanSessionRegistrarManifiesto.setLstFlotas(null);
        beanSessionRegistrarManifiesto.setVisibTxtRazSocProvTrans2(false);
        beanSessionRegistrarManifiesto.setEstadoFormManif(true);
        beanSessionRegistrarManifiesto.setVisibTxtRazSocProvTransBehav(true);  
        beanSessionRegistrarManifiesto.setDisabRegisManif(false);  
        beanSessionRegistrarManifiesto.setVisibRucEmpTrans(true);
        beanSessionRegistrarManifiesto.setVisibSocFlota(true);
        beanSessionRegistrarManifiesto.setVisibSocChofer(true);
        beanSessionRegistrarManifiesto.setPboxDatosTransVis(true);
        beanSessionRegistrarManifiesto.setFecManif(FechaUtiles.fechaActual());
        beanSessionRegistrarManifiesto.setFletePactado(null);
        beanSessionRegistrarManifiesto.setAdelanto(null);
        beanSessionRegistrarManifiesto.setTipDoc(null);
        beanSessionRegistrarManifiesto.setObserv(null);
        beanSessionRegistrarManifiesto.setRucEmpSC(null);
        beanSessionRegistrarManifiesto.setEmpresaSC(null);
        beanSessionRegistrarManifiesto.setPboxDatosEmpTitulo("Datos de Empresa Proveedora de Transporte");
      }
    
       
    public void transportePropio(){
        panelNuevoManif(false);
        beanSessionRegistrarManifiesto.setNidManifiesto(0);
        beanSessionRegistrarManifiesto.setCidChofer("0");
        beanSessionRegistrarManifiesto.setCidFlota("0");
        beanSessionRegistrarManifiesto.setVisibTxtRazSocProvTrans2(true);
        beanSessionRegistrarManifiesto.setVisibSocFlota(true);
        beanSessionRegistrarManifiesto.setVisibSocChofer(true);
        resetNewManif();
        beanSessionRegistrarManifiesto.setPboxDatosEmpVis(true);
        beanSessionRegistrarManifiesto.setVisibRucEmpTrans(true);
        beanSessionRegistrarManifiesto.setPboxDatosTransVis(true);
        beanSessionRegistrarManifiesto.setDisabSocFlota(false);
        beanSessionRegistrarManifiesto.setDisabSocChofer(false);
        BeanEmpresa empresa = ln_C_SFEmpresasRemote.selectedEmpresa(new BigDecimal(5));
        beanSessionRegistrarManifiesto.setEmpresaSC(empresa.getCRazonSocial());
        beanSessionRegistrarManifiesto.setRucEmpSC(empresa.getCRuc());
        txtRazSocEmpTransp2.setRendered(true);
        txtRazSocEmpTransp2.setValue(empresa.getCRazonSocial());
        txtRazSocEmpTransp.setRendered(false);
        txtRazSocEmpTransp.resetValue();
        txtRazSocEmpTransp.setVisible(false);
        beanSessionRegistrarManifiesto.setVisibTxtRazSocProvTransBehav(false);
        rucEmpTransp.setValue(empresa.getCRuc());
        rucEmpTransp.setRendered(true);
        pboxDatosEmp.setVisible(true);
        beanSessionRegistrarManifiesto.setPboxDatosEmpTitulo("Transporte Lubal");
        pboxDatosEmp.setText("Transporte Lubal");
        pboxDatosTrans.setVisible(true);
        socFlota.setDisabled(false);
        socChofer.setDisabled(false);
        socFlota.setRendered(true);
        socChofer.setRendered(true);
        txtMarcVehi.resetValue();
        txtPlaca.resetValue();
        txtConfigVehi.resetValue();
        txtCertInsc.resetValue();
        txtLicCondu.resetValue();
        txtConductor.resetValue();
        txtMarcVehi.setRendered(true);
        txtPlaca.setRendered(true);
        txtConfigVehi.setRendered(true);
        txtCertInsc.setRendered(true);
        socGuiasManif.setRendered(false);
        txtLicCondu.setRendered(true);
        txtConductor.setRendered(true);
        beanSessionRegistrarManifiesto.setLstFlotas(this.llenarFlotasCombo(5,null));
        beanSessionRegistrarManifiesto.setLstChofers(this.llenarChofersCombo(5,null));
        Utils.addTargetMany(txtRazSocEmpTransp2,rucEmpTransp,socFlota,pboxDatosTrans,socChofer,pboxDatosEmp,txtMarcVehi,
                            socGuiasManif,txtPlaca,txtConfigVehi,txtCertInsc,txtLicCondu,txtConductor,txtRazSocEmpTransp);
    }
    
    public void resetNewManif(){
        txtFecManif.resetValue();
        itCodManif.resetValue();
        txtAdelanto.resetValue();
        txtFletePact.resetValue();
        itSaldo.resetValue();
        itIGV.resetValue();
        itDetraccion.resetValue();
        itMontoFin.resetValue();
        tipoDocManif.resetValue();
        txtObsManif.resetValue();
        Utils.addTargetMany(txtFecManif,itCodManif,tipoDocManif,txtFletePact,txtAdelanto,txtObsManif,itDetraccion);  
    }
    
    public void panelNuevoManif(boolean estado){
        getBeanSessionRegistrarManifiesto().setEstadoFormManif(estado);
        getBeanSessionRegistrarManifiesto().setPboxDatosEmpVis(estado);
        getBeanSessionRegistrarManifiesto().setPboxDatosTransVis(estado);
        getBeanSessionRegistrarManifiesto().setPboxManifiestoVis(estado);
    }

 
    public void cambiarFlete(ValueChangeEvent vce) {
        try{
            Object flete = vce.getNewValue();
            Double dFlete = Double.parseDouble(flete.toString());
            Double adela = 0.0;
            Double detrac = 0.0;
            Double montoFinal = 0.0;
            if(getBeanSessionRegistrarManifiesto().getAdelanto() != null){
                adela = getBeanSessionRegistrarManifiesto().getAdelanto();
            }
            Double saldo = 0.0;//dFlete - adela;
            if(saldo < 0.0){
                Utils.throwError_Aux(ctx,"El flete no puede ser menor al adelanto",4);
                dFlete = 0.0;
                saldo = 0.0;
                txtFletePact.resetValue();
                itIGV.resetValue();
                itDetraccion.resetValue();
                itMontoFin.resetValue();
            }else{
                Double igv = dFlete + (dFlete * .18);
                getBeanSessionRegistrarManifiesto().setIgvVal(igv);
                itIGV.setValue(igv);
                if(igv >= 400.0){
                    montoFinal = igv - (igv * .04);
                    detrac = igv * .04;
                }else{
                    montoFinal = igv;
                }
                itMontoFin.setValue(montoFinal);
                itDetraccion.setValue(detrac);
                saldo = montoFinal - adela;
                itSaldo.setValue(saldo);
            }
            getBeanSessionRegistrarManifiesto().setFletePactado(dFlete);
            getBeanSessionRegistrarManifiesto().setSaldo(saldo);
            getBeanSessionRegistrarManifiesto().setDetrac(detrac);
            getBeanSessionRegistrarManifiesto().setMontoFinalVal(montoFinal);
            Utils.addTargetMany(txtFletePact,itIGV,itMontoFin,itSaldo,itDetraccion);
        }catch(Exception e){
            Utils.throwError_Aux(ctx,"Ingrese un valor numerico positivo mayor a 0",4);
        }
    }

    public void cambiarAdelanto(ValueChangeEvent vce) {
        try{
            Object adela = vce.getNewValue();
            Double dAdela = Double.parseDouble(adela.toString());
            Double montoFinal = 0.0;
            if(getBeanSessionRegistrarManifiesto().getMontoFinalVal() != null){
                montoFinal = getBeanSessionRegistrarManifiesto().getMontoFinalVal();
            }
            Double saldo = montoFinal - dAdela;
            if(saldo < 0){
                Utils.throwError_Aux(ctx,"El adelanto no puede ser mayor a 0",4);
                dAdela = 0.0;
                txtAdelanto.resetValue();
                saldo = 0.0;
            }else{
                itSaldo.setValue(saldo);
            }
            getBeanSessionRegistrarManifiesto().setAdelanto(dAdela);
            getBeanSessionRegistrarManifiesto().setSaldo(saldo);
            Utils.addTargetMany(txtAdelanto,itSaldo);
        }catch(Exception e){
            Utils.throwError_Aux(ctx,"Ingrese un valor numerico positivo mayor a 0",4);
        }
    }
    
   
    
    public void checkConformidad(ValueChangeEvent vce) {
        try{
            Object oConf = vce.getNewValue().toString();
            if(oConf.equals("1")){//OK
                int nidManif = getBeanSessionRegistrarManifiesto().getNidManifiesto();
                if(nidManif != 0){
                    String codUN = getBeanSessionRegistrarManifiesto().getCodUN();
                    String cidGuia = getBeanSessionRegistrarManifiesto().getCidGuia();
                    BeanError beanError = ln_C_SFManifiestoRemote.guiasOK(nidManif, codUN, cidGuia,0);
                    if(beanError != null){
                        if(!beanError.getCidError().equals("000")){
                            String msjTitulo = beanError.getCDescripcionError().replaceAll(":codUN",codUN);
                            msjTitulo = msjTitulo.replaceAll(":cidGuia",cidGuia);
                            msjTitulo = msjTitulo.replaceAll(":nidManif",nidManif+"");
                            getBeanSessionRegistrarManifiesto().setNombreDialog(msjTitulo);
                            Utils.showPopUpMIDDLE(popGuiaOK);
                        }
                    }
                }
            }else{
                getBeanSessionRegistrarManifiesto().setEstManif(null);
            }
        }catch(Exception e){
           // Utils.throwError_Aux(ctx,"Error al seleccionar Conformidad",4);
        }
    }
    
   
    public void onDialogCancel(ClientEvent ce) {
        getBeanSessionRegistrarManifiesto().setConfo("2");//Pendiente
        socConf.setValue("2");
        Utils.addTarget(socConf);
        popGuiaOK.hide();
        FacesContext fctx = FacesContext.getCurrentInstance();
        fctx.renderResponse();
    }
    
    public void onDialogCambioCancel(ClientEvent ce) {
        beanSessionRegistrarManifiesto.setEstCambioManif(false);
        beanSessionRegistrarManifiesto.setEstCambioOS(false);
        popEst.hide();
        FacesContext fctx = FacesContext.getCurrentInstance();
        fctx.renderResponse();
    }


   
    public void setBeanSessionRegistrarManifiesto(SessionScopedBeanRegistrarManifiesto beanSessionRegistrarManifiesto) {
        this.beanSessionRegistrarManifiesto = beanSessionRegistrarManifiesto;
    }

    public SessionScopedBeanRegistrarManifiesto getBeanSessionRegistrarManifiesto() {
        return beanSessionRegistrarManifiesto;
    }


    public void setOtTitulo(RichOutputText otTitulo) {
        this.otTitulo = otTitulo;
    }

    public RichOutputText getOtTitulo() {
        return otTitulo;
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

    public void setRowBotones(RichGridRow rowBotones) {
        this.rowBotones = rowBotones;
    }

    public RichGridRow getRowBotones() {
        return rowBotones;
    }

    public void setGc3(RichGridCell gc3) {
        this.gc3 = gc3;
    }

    public RichGridCell getGc3() {
        return gc3;
    }

    public void setCelBtn1(RichGridCell celBtn1) {
        this.celBtn1 = celBtn1;
    }

    public RichGridCell getCelBtn1() {
        return celBtn1;
    }

    public void setItOServicio(RichInputText itOServicio) {
        this.itOServicio = itOServicio;
    }

    public RichInputText getItOServicio() {
        return itOServicio;
    }

    public void setPopOS(RichPopup popOS) {
        this.popOS = popOS;
    }

    public RichPopup getPopOS() {
        return popOS;
    }

    public void setOlTituloPopUp(RichOutputLabel olTituloPopUp) {
        this.olTituloPopUp = olTituloPopUp;
    }

    public RichOutputLabel getOlTituloPopUp() {
        return olTituloPopUp;
    }

    public void setDb1(RichDecorativeBox db1) {
        this.db1 = db1;
    }

    public RichDecorativeBox getDb1() {
        return db1;
    }

    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }

    public void setTbOS(RichTable tbOS) {
        this.tbOS = tbOS;
    }

    public RichTable getTbOS() {
        return tbOS;
    }

    public void setId1(RichInputDate id1) {
        this.id1 = id1;
    }

    public RichInputDate getId1() {
        return id1;
    }

    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
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

    public void setItDetraccion(RichInputText itDetraccion) {
        this.itDetraccion = itDetraccion;
    }

    public RichInputText getItDetraccion() {
        return itDetraccion;
    }

    public void setCb1(RichCommandButton cb1) {
        this.cb1 = cb1;
    }

    public RichCommandButton getCb1() {
        return cb1;
    }

    public void setDb2(RichDecorativeBox db2) {
        this.db2 = db2;
    }

    public RichDecorativeBox getDb2() {
        return db2;
    }

    public void setPopRemit(RichPopup popRemit) {
        this.popRemit = popRemit;
    }

    public RichPopup getPopRemit() {
        return popRemit;
    }

    public void setPfl2(RichPanelFormLayout pfl2) {
        this.pfl2 = pfl2;
    }

    public RichPanelFormLayout getPfl2() {
        return pfl2;
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

    public void setCb3(RichCommandButton cb3) {
        this.cb3 = cb3;
    }

    public RichCommandButton getCb3() {
        return cb3;
    }

    public void setTbRemit(RichTable tbRemit) {
        this.tbRemit = tbRemit;
    }

    public RichTable getTbRemit() {
        return tbRemit;
    }

    public void setS1(RichSubform s1) {
        this.s1 = s1;
    }

    public RichSubform getS1() {
        return s1;
    }

    public void setS2(RichSubform s2) {
        this.s2 = s2;
    }

    public RichSubform getS2() {
        return s2;
    }

    public void setRazonSocProve(RichInputText razonSocProve) {
        this.razonSocProve = razonSocProve;
    }

    public RichInputText getRazonSocProve() {
        return razonSocProve;
    }

    public void setRucProve(RichInputText rucProve) {
        this.rucProve = rucProve;
    }

    public RichInputText getRucProve() {
        return rucProve;
    }

    public void setPfl_main(RichPanelFormLayout pfl_main) {
        this.pfl_main = pfl_main;
    }

    public RichPanelFormLayout getPfl_main() {
        return pfl_main;
    }

    public void setPfl3(RichPanelFormLayout pfl3) {
        this.pfl3 = pfl3;
    }

    public RichPanelFormLayout getPfl3() {
        return pfl3;
    }

    public void setPglBotones(RichPanelGridLayout pglBotones) {
        this.pglBotones = pglBotones;
    }

    public RichPanelGridLayout getPglBotones() {
        return pglBotones;
    }

    public void setGr3(RichGridRow gr3) {
        this.gr3 = gr3;
    }

    public RichGridRow getGr3() {
        return gr3;
    }

    public void setGc5(RichGridCell gc5) {
        this.gc5 = gc5;
    }

    public RichGridCell getGc5() {
        return gc5;
    }

    public void setGc6(RichGridCell gc6) {
        this.gc6 = gc6;
    }

    public RichGridCell getGc6() {
        return gc6;
    }

    public void setPgl5(RichPanelGridLayout pgl5) {
        this.pgl5 = pgl5;
    }

    public RichPanelGridLayout getPgl5() {
        return pgl5;
    }

    public void setGr4(RichGridRow gr4) {
        this.gr4 = gr4;
    }

    public RichGridRow getGr4() {
        return gr4;
    }

    public void setGc7(RichGridCell gc7) {
        this.gc7 = gc7;
    }

    public RichGridCell getGc7() {
        return gc7;
    }

    public void setDdd(RichGridCell ddd) {
        this.ddd = ddd;
    }

    public RichGridCell getDdd() {
        return ddd;
    }

    public void setDd(RichInputText dd) {
        this.dd = dd;
    }

    public RichInputText getDd() {
        return dd;
    }

    public void setPfl4(RichPanelFormLayout pfl4) {
        this.pfl4 = pfl4;
    }

    public RichPanelFormLayout getPfl4() {
        return pfl4;
    }

    public void setRazSocClie(RichInputText razSocClie) {
        this.razSocClie = razSocClie;
    }

    public RichInputText getRazSocClie() {
        return razSocClie;
    }

    public void setRucClie(RichInputText rucClie) {
        this.rucClie = rucClie;
    }

    public RichInputText getRucClie() {
        return rucClie;
    }

    public void setPsl1(RichPanelStretchLayout psl1) {
        this.psl1 = psl1;
    }

    public RichPanelStretchLayout getPsl1() {
        return psl1;
    }

    public void setTxtCorrelativo(RichInputText txtCorrelativo) {
        this.txtCorrelativo = txtCorrelativo;
    }

    public RichInputText getTxtCorrelativo() {
        return txtCorrelativo;
    }

    public void setPfl5(RichPanelFormLayout pfl5) {
        this.pfl5 = pfl5;
    }

    public RichPanelFormLayout getPfl5() {
        return pfl5;
    }

    public void setTblItms(RichTable tblItms) {
        this.tblItms = tblItms;
    }

    public RichTable getTblItms() {
        return tblItms;
    }

    public void setBtnNewItem(RichCommandButton btnNewItem) {
        this.btnNewItem = btnNewItem;
    }

    public RichCommandButton getBtnNewItem() {
        return btnNewItem;
    }

    public void setPopItems(RichPopup popItems) {
        this.popItems = popItems;
    }

    public RichPopup getPopItems() {
        return popItems;
    }

    public void setS3(RichSubform s3) {
        this.s3 = s3;
    }

    public RichSubform getS3() {
        return s3;
    }

    public void setPfl6(RichPanelFormLayout pfl6) {
        this.pfl6 = pfl6;
    }

    public RichPanelFormLayout getPfl6() {
        return pfl6;
    }

    public void setTxtGuiRem(RichInputText txtGuiRem) {
        this.txtGuiRem = txtGuiRem;
    }

    public RichInputText getTxtGuiRem() {
        return txtGuiRem;
    }

    public void setTxtDescBien(RichInputText txtDescBien) {
        this.txtDescBien = txtDescBien;
    }

    public RichInputText getTxtDescBien() {
        return txtDescBien;
    }

    public void setBtnRegistrarItem(RichCommandButton btnRegistrarItem) {
        this.btnRegistrarItem = btnRegistrarItem;
    }

    public RichCommandButton getBtnRegistrarItem() {
        return btnRegistrarItem;
    }

    public void setSocUndMed(RichSelectOneChoice socUndMed) {
        this.socUndMed = socUndMed;
    }

    public RichSelectOneChoice getSocUndMed() {
        return socUndMed;
    }

    public void setSi1(RichSelectItem si1) {
        this.si1 = si1;
    }

    public RichSelectItem getSi1() {
        return si1;
    }

    public void setSi2(RichSelectItem si2) {
        this.si2 = si2;
    }

    public RichSelectItem getSi2() {
        return si2;
    }

    public void setSi3(RichSelectItem si3) {
        this.si3 = si3;
    }

    public RichSelectItem getSi3() {
        return si3;
    }

    public void setBtnEditarItem(RichCommandButton btnEditarItem) {
        this.btnEditarItem = btnEditarItem;
    }

    public RichCommandButton getBtnEditarItem() {
        return btnEditarItem;
    }

    public void setBtnBorrarItem(RichCommandButton btnBorrarItem) {
        this.btnBorrarItem = btnBorrarItem;
    }

    public RichCommandButton getBtnBorrarItem() {
        return btnBorrarItem;
    }

    public void setS4(RichSeparator s4) {
        this.s4 = s4;
    }

    public RichSeparator getS4() {
        return s4;
    }

    public void setTxtObs(RichInputText txtObs) {
        this.txtObs = txtObs;
    }

    public RichInputText getTxtObs() {
        return txtObs;
    }

    public void setPfl7(RichPanelFormLayout pfl7) {
        this.pfl7 = pfl7;
    }

    public RichPanelFormLayout getPfl7() {
        return pfl7;
    }

    public void setPfl8(RichPanelFormLayout pfl8) {
        this.pfl8 = pfl8;
    }

    public RichPanelFormLayout getPfl8() {
        return pfl8;
    }

    public void setTxtRazSocEmpTransp(RichInputText txtRazSocEmpTransp) {
        this.txtRazSocEmpTransp = txtRazSocEmpTransp;
    }

    public RichInputText getTxtRazSocEmpTransp() {
        return txtRazSocEmpTransp;
    }

    public void setRucEmpTransp(RichInputText rucEmpTransp) {
        this.rucEmpTransp = rucEmpTransp;
    }

    public RichInputText getRucEmpTransp() {
        return rucEmpTransp;
    }

    public void setPanFormTrans(RichPanelFormLayout panFormTrans) {
        this.panFormTrans = panFormTrans;
    }

    public RichPanelFormLayout getPanFormTrans() {
        return panFormTrans;
    }

    public void setTxtMarcVehi(RichInputText txtMarcVehi) {
        this.txtMarcVehi = txtMarcVehi;
    }

    public RichInputText getTxtMarcVehi() {
        return txtMarcVehi;
    }

    public void setTxtPlaca(RichInputText txtPlaca) {
        this.txtPlaca = txtPlaca;
    }

    public RichInputText getTxtPlaca() {
        return txtPlaca;
    }

    public void setTxtConfigVehi(RichInputText txtConfigVehi) {
        this.txtConfigVehi = txtConfigVehi;
    }

    public RichInputText getTxtConfigVehi() {
        return txtConfigVehi;
    }

    public void setTxtCertInsc(RichInputText txtCertInsc) {
        this.txtCertInsc = txtCertInsc;
    }

    public RichInputText getTxtCertInsc() {
        return txtCertInsc;
    }

    public void setTxtLicCondu(RichInputText txtLicCondu) {
        this.txtLicCondu = txtLicCondu;
    }

    public RichInputText getTxtLicCondu() {
        return txtLicCondu;
    }

    public void setTxtConductor(RichInputText txtConductor) {
        this.txtConductor = txtConductor;
    }

    public RichInputText getTxtConductor() {
        return txtConductor;
    }

    public void setS5(RichSeparator s5) {
        this.s5 = s5;
    }

    public RichSeparator getS5() {
        return s5;
    }

    public void setOs1(UIOutput os1) {
        this.os1 = os1;
    }

    public UIOutput getOs1() {
        return os1;
    }

    public void setD1(RichDocument d1) {
        this.d1 = d1;
    }

    public RichDocument getD1() {
        return d1;
    }

    public void setPopProv(RichPopup popProv) {
        this.popProv = popProv;
    }

    public RichPopup getPopProv() {
        return popProv;
    }

    public void setS6(RichSubform s6) {
        this.s6 = s6;
    }

    public RichSubform getS6() {
        return s6;
    }

    public void setDb3(RichDecorativeBox db3) {
        this.db3 = db3;
    }

    public RichDecorativeBox getDb3() {
        return db3;
    }

    public void setPfl9(RichPanelFormLayout pfl9) {
        this.pfl9 = pfl9;
    }

    public RichPanelFormLayout getPfl9() {
        return pfl9;
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

    public void setTbProv(RichTable tbProv) {
        this.tbProv = tbProv;
    }

    public RichTable getTbProv() {
        return tbProv;
    }

    public void setPt1(RichPanelTabbed pt1) {
        this.pt1 = pt1;
    }

    public RichPanelTabbed getPt1() {
        return pt1;
    }

    public void setSdi1(RichShowDetailItem sdi1) {
        this.sdi1 = sdi1;
    }

    public RichShowDetailItem getSdi1() {
        return sdi1;
    }

    public void setSdi2(RichShowDetailItem sdi2) {
        this.sdi2 = sdi2;
    }

    public RichShowDetailItem getSdi2() {
        return sdi2;
    }

    public void setCelBtn2(RichGridCell celBtn2) {
        this.celBtn2 = celBtn2;
    }

    public RichGridCell getCelBtn2() {
        return celBtn2;
    }

    public void setCelBtn3(RichGridCell celBtn3) {
        this.celBtn3 = celBtn3;
    }

    public RichGridCell getCelBtn3() {
        return celBtn3;
    }

    public void setTipoManf(RichSelectOneChoice tipoManf) {
        this.tipoManf = tipoManf;
    }

    public RichSelectOneChoice getTipoManf() {
        return tipoManf;
    }

    public void setItemManif1(RichSelectItem itemManif1) {
        this.itemManif1 = itemManif1;
    }

    public RichSelectItem getItemManif1() {
        return itemManif1;
    }

    public void setItemManif2(RichSelectItem itemManif2) {
        this.itemManif2 = itemManif2;
    }

    public RichSelectItem getItemManif2() {
        return itemManif2;
    }

    public void setPflNewManif(RichPanelFormLayout pflNewManif) {
        this.pflNewManif = pflNewManif;
    }

    public RichPanelFormLayout getPflNewManif() {
        return pflNewManif;
    }

    public void setTxtFecManif(RichInputDate txtFecManif) {
        this.txtFecManif = txtFecManif;
    }

    public RichInputDate getTxtFecManif() {
        return txtFecManif;
    }

    public void setTxtFletePact(RichInputText txtFletePact) {
        this.txtFletePact = txtFletePact;
    }

    public RichInputText getTxtFletePact() {
        return txtFletePact;
    }

    public void setTxtAdelanto(RichInputText txtAdelanto) {
        this.txtAdelanto = txtAdelanto;
    }

    public RichInputText getTxtAdelanto() {
        return txtAdelanto;
    }

    public void setTxtObsManif(RichInputText txtObsManif) {
        this.txtObsManif = txtObsManif;
    }

    public RichInputText getTxtObsManif() {
        return txtObsManif;
    }

    public void setTipoDocManif(RichSelectOneChoice tipoDocManif) {
        this.tipoDocManif = tipoDocManif;
    }

    public RichSelectOneChoice getTipoDocManif() {
        return tipoDocManif;
    }

    public void setTipoDoc1(RichSelectItem tipoDoc1) {
        this.tipoDoc1 = tipoDoc1;
    }

    public RichSelectItem getTipoDoc1() {
        return tipoDoc1;
    }

    public void setTipoDoc2(RichSelectItem tipoDoc2) {
        this.tipoDoc2 = tipoDoc2;
    }

    public RichSelectItem getTipoDoc2() {
        return tipoDoc2;
    }

    public void setTipoDoc3(RichSelectItem tipoDoc3) {
        this.tipoDoc3 = tipoDoc3;
    }

    public RichSelectItem getTipoDoc3() {
        return tipoDoc3;
    }

    public void setTipoDoc4(RichSelectItem tipoDoc4) {
        this.tipoDoc4 = tipoDoc4;
    }

    public RichSelectItem getTipoDoc4() {
        return tipoDoc4;
    }

    public void setTipoDoc5(RichSelectItem tipoDoc5) {
        this.tipoDoc5 = tipoDoc5;
    }

    public RichSelectItem getTipoDoc5() {
        return tipoDoc5;
    }

    public void setBtnRegManif(RichCommandButton btnRegManif) {
        this.btnRegManif = btnRegManif;
    }

    public RichCommandButton getBtnRegManif() {
        return btnRegManif;
    }

    public void setItemManif3(RichSelectItem itemManif3) {
        this.itemManif3 = itemManif3;
    }

    public RichSelectItem getItemManif3() {
        return itemManif3;
    }

    public void setPboxDatosEmp(RichPanelBox pboxDatosEmp) {
        this.pboxDatosEmp = pboxDatosEmp;
    }

    public RichPanelBox getPboxDatosEmp() {
        return pboxDatosEmp;
    }

    public void setPboxDatosTrans(RichPanelBox pboxDatosTrans) {
        this.pboxDatosTrans = pboxDatosTrans;
    }

    public RichPanelBox getPboxDatosTrans() {
        return pboxDatosTrans;
    }

    public void setPboxManifiesto(RichPanelBox pboxManifiesto) {
        this.pboxManifiesto = pboxManifiesto;
    }

    public RichPanelBox getPboxManifiesto() {
        return pboxManifiesto;
    }

    public void setSocFlota(RichSelectOneChoice socFlota) {
        this.socFlota = socFlota;
    }

    public RichSelectOneChoice getSocFlota() {
        return socFlota;
    }

    public void setSi4(UISelectItems si4) {
        this.si4 = si4;
    }

    public UISelectItems getSi4() {
        return si4;
    }

    public void setSocChofer(RichSelectOneChoice socChofer) {
        this.socChofer = socChofer;
    }

    public RichSelectOneChoice getSocChofer() {
        return socChofer;
    }

    public void setSi5(UISelectItems si5) {
        this.si5 = si5;
    }

    public UISelectItems getSi5() {
        return si5;
    }

    public void setItBuscarManif(RichInputText itBuscarManif) {
        this.itBuscarManif = itBuscarManif;
    }

    public RichInputText getItBuscarManif() {
        return itBuscarManif;
    }

    public void setPopManif(RichPopup popManif) {
        this.popManif = popManif;
    }

    public RichPopup getPopManif() {
        return popManif;
    }

    public void setTabMani(RichTable tabMani) {
        this.tabMani = tabMani;
    }

    public RichTable getTabMani() {
        return tabMani;
    }

    public void setId3(RichInputDate id3) {
        this.id3 = id3;
    }

    public RichInputDate getId3() {
        return id3;
    }

    public void setIt13(RichInputText it13) {
        this.it13 = it13;
    }

    public RichInputText getIt13() {
        return it13;
    }

    public void setPfl10(RichPanelFormLayout pfl10) {
        this.pfl10 = pfl10;
    }

    public RichPanelFormLayout getPfl10() {
        return pfl10;
    }

    public void setId5(RichInputDate id5) {
        this.id5 = id5;
    }

    public RichInputDate getId5() {
        return id5;
    }

    public void setId4(RichInputDate id4) {
        this.id4 = id4;
    }

    public RichInputDate getId4() {
        return id4;
    }

    public void setIt14(RichInputText it14) {
        this.it14 = it14;
    }

    public RichInputText getIt14() {
        return it14;
    }

    public void setBtnBuscarManif(RichCommandButton btnBuscarManif) {
        this.btnBuscarManif = btnBuscarManif;
    }

    public RichCommandButton getBtnBuscarManif() {
        return btnBuscarManif;
    }

    public void setTxtRazSocEmpTransp2(RichInputText txtRazSocEmpTransp2) {
        this.txtRazSocEmpTransp2 = txtRazSocEmpTransp2;
    }

    public RichInputText getTxtRazSocEmpTransp2() {
        return txtRazSocEmpTransp2;
    }

    public void setSocDirecs(RichSelectOneChoice socDirecs) {
        this.socDirecs = socDirecs;
    }

    public RichSelectOneChoice getSocDirecs() {
        return socDirecs;
    }

    public void setSi6(UISelectItems si6) {
        this.si6 = si6;
    }

    public UISelectItems getSi6() {
        return si6;
    }

    public void setSocDirecRemi(RichSelectOneChoice socDirecRemi) {
        this.socDirecRemi = socDirecRemi;
    }

    public RichSelectOneChoice getSocDirecRemi() {
        return socDirecRemi;
    }

    public void setSi7(UISelectItems si7) {
        this.si7 = si7;
    }

    public UISelectItems getSi7() {
        return si7;
    }

    public void setBtnGrabarGuia(RichCommandButton btnGrabarGuia) {
        this.btnGrabarGuia = btnGrabarGuia;
    }

    public RichCommandButton getBtnGrabarGuia() {
        return btnGrabarGuia;
    }

    public void setFecEmision(RichInputDate fecEmision) {
        this.fecEmision = fecEmision;
    }

    public RichInputDate getFecEmision() {
        return fecEmision;
    }

    public void setFecDespacho(RichInputDate fecDespacho) {
        this.fecDespacho = fecDespacho;
    }

    public RichInputDate getFecDespacho() {
        return fecDespacho;
    }

    public void setSocCodigoUN(RichSelectOneChoice socCodigoUN) {
        this.socCodigoUN = socCodigoUN;
    }

    public RichSelectOneChoice getSocCodigoUN() {
        return socCodigoUN;
    }

    public void setSi8(UISelectItems si8) {
        this.si8 = si8;
    }

    public UISelectItems getSi8() {
        return si8;
    }

    public void setItSaldo(RichInputText itSaldo) {
        this.itSaldo = itSaldo;
    }

    public RichInputText getItSaldo() {
        return itSaldo;
    }

    public void setSocGuiasManif(RichSelectOneChoice socGuiasManif) {
        this.socGuiasManif = socGuiasManif;
    }

    public RichSelectOneChoice getSocGuiasManif() {
        return socGuiasManif;
    }

    public void setSi9(UISelectItems si9) {
        this.si9 = si9;
    }

    public UISelectItems getSi9() {
        return si9;
    }

    public void setDiaGuiaOK(RichDocument diaGuiaOK) {
        this.diaGuiaOK = diaGuiaOK;
    }

    public RichDocument getDiaGuiaOK() {
        return diaGuiaOK;
    }

    public void setF1(RichForm f1) {
        this.f1 = f1;
    }

    public RichForm getF1() {
        return f1;
    }

    public void setSocConf(RichSelectOneChoice socConf) {
        this.socConf = socConf;
    }

    public RichSelectOneChoice getSocConf() {
        return socConf;
    }

    public void setSi10(UISelectItems si10) {
        this.si10 = si10;
    }

    public UISelectItems getSi10() {
        return si10;
    }

    public void setItOServAux(RichInputText itOServAux) {
        this.itOServAux = itOServAux;
    }

    public RichInputText getItOServAux() {
        return itOServAux;
    }

    public void setIt1(RichInputText it1) {
        this.it1 = it1;
    }

    public RichInputText getIt1() {
        return it1;
    }

    public void setItIGV(RichInputText itIGV) {
        this.itIGV = itIGV;
    }

    public RichInputText getItIGV() {
        return itIGV;
    }

    public void setItMontoFin(RichInputText itMontoFin) {
        this.itMontoFin = itMontoFin;
    }

    public RichInputText getItMontoFin() {
        return itMontoFin;
    }

    public void setPopGuiaOK(RichPopup popGuiaOK) {
        this.popGuiaOK = popGuiaOK;
    }

    public RichPopup getPopGuiaOK() {
        return popGuiaOK;
    }

    public void setD2(RichDialog d2) {
        this.d2 = d2;
    }

    public RichDialog getD2() {
        return d2;
    }

    public void setSocEstManif(RichSelectOneChoice socEstManif) {
        this.socEstManif = socEstManif;
    }

    public RichSelectOneChoice getSocEstManif() {
        return socEstManif;
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

    public void setPb1(RichPanelBox pb1) {
        this.pb1 = pb1;
    }

    public RichPanelBox getPb1() {
        return pb1;
    }

    public void setPopImg(RichPopup popImg) {
        this.popImg = popImg;
    }

    public RichPopup getPopImg() {
        return popImg;
    }

    public void setPopUM(RichPopup popUM) {
        this.popUM = popUM;
    }

    public RichPopup getPopUM() {
        return popUM;
    }

    public void setDiagImg(RichDialog diagImg) {
        this.diagImg = diagImg;
    }

    public RichDialog getDiagImg() {
        return diagImg;
    }

    public void setGl1(RichGoLink gl1) {
        this.gl1 = gl1;
    }

    public RichGoLink getGl1() {
        return gl1;
    }

    public void setImgGuia(RichImage imgGuia) {
        this.imgGuia = imgGuia;
    }

    public RichImage getImgGuia() {
        return imgGuia;
    }

    public void setFileImg(RichInputFile fileImg) {
        this.fileImg = fileImg;
    }

    public RichInputFile getFileImg() {
        return fileImg;
    }

    public void setBtnImg(RichCommandButton btnImg) {
        this.btnImg = btnImg;
    }

    public RichCommandButton getBtnImg() {
        return btnImg;
    }

    public void setCb4(RichCommandButton cb4) {
        this.cb4 = cb4;
    }

    public RichCommandButton getCb4() {
        return cb4;
    }

    public void setBtnAddUndMed(RichCommandButton btnAddUndMed) {
        this.btnAddUndMed = btnAddUndMed;
    }

    public RichCommandButton getBtnAddUndMed() {
        return btnAddUndMed;
    }

    public void setPb2(RichPanelBox pb2) {
        this.pb2 = pb2;
    }

    public RichPanelBox getPb2() {
        return pb2;
    }

    public void setItCodManif(RichInputText itCodManif) {
        this.itCodManif = itCodManif;
    }

    public RichInputText getItCodManif() {
        return itCodManif;
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

    public void setBtnBOS(RichCommandButton btnBOS) {
        this.btnBOS = btnBOS;
    }

    public RichCommandButton getBtnBOS() {
        return btnBOS;
    }

    public void setSpiPaq(RichInputNumberSpinbox spiPaq) {
        this.spiPaq = spiPaq;
    }

    public RichInputNumberSpinbox getSpiPaq() {
        return spiPaq;
    }

    public void setTxtPeso(RichInputNumberSpinbox txtPeso) {
        this.txtPeso = txtPeso;
    }

    public RichInputNumberSpinbox getTxtPeso() {
        return txtPeso;
    }

    public void setPopEst(RichPopup popEst) {
        this.popEst = popEst;
    }

    public RichPopup getPopEst() {
        return popEst;
    }

    public void setDiaEst(RichDialog diaEst) {
        this.diaEst = diaEst;
    }

    public RichDialog getDiaEst() {
        return diaEst;
    }

    public void setSbcEstOS(RichSelectBooleanCheckbox sbcEstOS) {
        this.sbcEstOS = sbcEstOS;
    }

    public RichSelectBooleanCheckbox getSbcEstOS() {
        return sbcEstOS;
    }

    public void setSbcEstManif(RichSelectBooleanCheckbox sbcEstManif) {
        this.sbcEstManif = sbcEstManif;
    }

    public RichSelectBooleanCheckbox getSbcEstManif() {
        return sbcEstManif;
    }

    public void setItDesc(RichInputText itDesc) {
        this.itDesc = itDesc;
    }

    public RichInputText getItDesc() {
        return itDesc;
    }

    public void setItUniMed(RichInputText itUniMed) {
        this.itUniMed = itUniMed;
    }

    public RichInputText getItUniMed() {
        return itUniMed;
    }

    public void setBtnNewUM(RichCommandButton btnNewUM) {
        this.btnNewUM = btnNewUM;
    }

    public RichCommandButton getBtnNewUM() {
        return btnNewUM;
    }

    public void setBtnAnular(RichCommandButton btnAnular) {
        this.btnAnular = btnAnular;
    }

    public RichCommandButton getBtnAnular() {
        return btnAnular;
    }

    public void setPopAnul(RichPopup popAnul) {
        this.popAnul = popAnul;
    }

    public RichPopup getPopAnul() {
        return popAnul;
    }

    public void setTxtCantidad(RichInputText txtCantidad) {
        this.txtCantidad = txtCantidad;
    }

    public RichInputText getTxtCantidad() {
        return txtCantidad;
    }

    public void setS8(RichSubform s8) {
        this.s8 = s8;
    }

    public RichSubform getS8() {
        return s8;
    }

    public void setD3(RichDialog d3) {
        this.d3 = d3;
    }

    public RichDialog getD3() {
        return d3;
    }

    public void setPfl11(RichPanelFormLayout pfl11) {
        this.pfl11 = pfl11;
    }

    public RichPanelFormLayout getPfl11() {
        return pfl11;
    }

    public void setLn_C_SFManifiestoRemote(LN_C_SFManifiestoRemote ln_C_SFManifiestoRemote) {
        this.ln_C_SFManifiestoRemote = ln_C_SFManifiestoRemote;
    }

    public LN_C_SFManifiestoRemote getLn_C_SFManifiestoRemote() {
        return ln_C_SFManifiestoRemote;
    }

    public void setLn_C_SFRelacionEmpresaRemote(LN_C_SFRelacionEmpresaRemote ln_C_SFRelacionEmpresaRemote) {
        this.ln_C_SFRelacionEmpresaRemote = ln_C_SFRelacionEmpresaRemote;
    }

    public LN_C_SFRelacionEmpresaRemote getLn_C_SFRelacionEmpresaRemote() {
        return ln_C_SFRelacionEmpresaRemote;
    }

    public void setLn_C_SFGuiaRemote(LN_C_SFGuiaRemote ln_C_SFGuiaRemote) {
        this.ln_C_SFGuiaRemote = ln_C_SFGuiaRemote;
    }

    public LN_C_SFGuiaRemote getLn_C_SFGuiaRemote() {
        return ln_C_SFGuiaRemote;
    }

    public void setLn_C_SFFlotaRemote(LN_C_SFFlotaRemote ln_C_SFFlotaRemote) {
        this.ln_C_SFFlotaRemote = ln_C_SFFlotaRemote;
    }

    public LN_C_SFFlotaRemote getLn_C_SFFlotaRemote() {
        return ln_C_SFFlotaRemote;
    }

    public void setLn_C_SFChoferRemote(LN_C_SFChoferRemote ln_C_SFChoferRemote) {
        this.ln_C_SFChoferRemote = ln_C_SFChoferRemote;
    }

    public LN_C_SFChoferRemote getLn_C_SFChoferRemote() {
        return ln_C_SFChoferRemote;
    }

    public void setLn_C_SFEmpresasRemote(LN_C_SFEmpresasRemote ln_C_SFEmpresasRemote) {
        this.ln_C_SFEmpresasRemote = ln_C_SFEmpresasRemote;
    }

    public LN_C_SFEmpresasRemote getLn_C_SFEmpresasRemote() {
        return ln_C_SFEmpresasRemote;
    }

    public void setLN_C_SFDireccionRemote(LN_C_SFDireccionRemote LN_C_SFDireccionRemote) {
        this.LN_C_SFDireccionRemote = LN_C_SFDireccionRemote;
    }

    public LN_C_SFDireccionRemote getLN_C_SFDireccionRemote() {
        return LN_C_SFDireccionRemote;
    }

    public void setLn_T_SFManifiestoRemote(LN_T_SFManifiestoRemote ln_T_SFManifiestoRemote) {
        this.ln_T_SFManifiestoRemote = ln_T_SFManifiestoRemote;
    }

    public LN_T_SFManifiestoRemote getLn_T_SFManifiestoRemote() {
        return ln_T_SFManifiestoRemote;
    }

    public void setLn_T_SFGuiaRemote(LN_T_SFGuiaRemote ln_T_SFGuiaRemote) {
        this.ln_T_SFGuiaRemote = ln_T_SFGuiaRemote;
    }

    public LN_T_SFGuiaRemote getLn_T_SFGuiaRemote() {
        return ln_T_SFGuiaRemote;
    }

    public void setLn_C_SFUnidadNegocioRemote(LN_C_SFUnidadNegocioRemote ln_C_SFUnidadNegocioRemote) {
        this.ln_C_SFUnidadNegocioRemote = ln_C_SFUnidadNegocioRemote;
    }

    public LN_C_SFUnidadNegocioRemote getLn_C_SFUnidadNegocioRemote() {
        return ln_C_SFUnidadNegocioRemote;
    }

    public void setLn_C_SFOrdenServicioRemote(LN_C_SFOrdenServicioRemote ln_C_SFOrdenServicioRemote) {
        this.ln_C_SFOrdenServicioRemote = ln_C_SFOrdenServicioRemote;
    }

    public LN_C_SFOrdenServicioRemote getLn_C_SFOrdenServicioRemote() {
        return ln_C_SFOrdenServicioRemote;
    }

    public void setLn_C_SFUtilsRemote(LN_C_SFUtilsRemote ln_C_SFUtilsRemote) {
        this.ln_C_SFUtilsRemote = ln_C_SFUtilsRemote;
    }

    public LN_C_SFUtilsRemote getLn_C_SFUtilsRemote() {
        return ln_C_SFUtilsRemote;
    }

    public void setLn_C_SFUnidadMedidaRemote(LN_C_SFUnidadMedidaRemote ln_C_SFUnidadMedidaRemote) {
        this.ln_C_SFUnidadMedidaRemote = ln_C_SFUnidadMedidaRemote;
    }

    public LN_C_SFUnidadMedidaRemote getLn_C_SFUnidadMedidaRemote() {
        return ln_C_SFUnidadMedidaRemote;
    }

    public void setLn_T_SFUnidadMedidaRemote(LN_T_SFUnidadMedidaRemote ln_T_SFUnidadMedidaRemote) {
        this.ln_T_SFUnidadMedidaRemote = ln_T_SFUnidadMedidaRemote;
    }

    public LN_T_SFUnidadMedidaRemote getLn_T_SFUnidadMedidaRemote() {
        return ln_T_SFUnidadMedidaRemote;
    }

    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }

    public String getCorrelativo() {
        return correlativo;
    }

    public void setBeanIteam(BeanTRItem beanIteam) {
        this.beanIteam = beanIteam;
    }

    public BeanTRItem getBeanIteam() {
        return beanIteam;
    }

    public void setLstItems(List<BeanTRItem> lstItems) {
        this.lstItems = lstItems;
    }

    public List<BeanTRItem> getLstItems() {
        return lstItems;
    }

    public void setGuiasManif(List guiasManif) {
        this.guiasManif = guiasManif;
    }

    public List getGuiasManif() {
        return guiasManif;
    }

    public void setCCidGuiaRemitente(String cCidGuiaRemitente) {
        this.cCidGuiaRemitente = cCidGuiaRemitente;
    }

    public String getCCidGuiaRemitente() {
        return cCidGuiaRemitente;
    }

    public void setCDescItem(String cDescItem) {
        this.cDescItem = cDescItem;
    }

    public String getCDescItem() {
        return cDescItem;
    }

    public void setCUndMedida(String cUndMedida) {
        this.cUndMedida = cUndMedida;
    }

    public String getCUndMedida() {
        return cUndMedida;
    }

    public void setDPeso(int dPeso) {
        this.dPeso = dPeso;
    }

    public int getDPeso() {
        return dPeso;
    }

    public void setNCantidad(Double nCantidad) {
        this.nCantidad = nCantidad;
    }

    public Double getNCantidad() {
        return nCantidad;
    }

    public void setCtx(FacesContext ctx) {
        this.ctx = ctx;
    }

    public FacesContext getCtx() {
        return ctx;
    }

    public void setCkbTraPro(RichSelectBooleanCheckbox ckbTraPro) {
        this.ckbTraPro = ckbTraPro;
    }

    public RichSelectBooleanCheckbox getCkbTraPro() {
        return ckbTraPro;
    }

    public void setBeanUsuario(BeanUsuarioAutenticado beanUsuario) {
        this.beanUsuario = beanUsuario;
    }

    public BeanUsuarioAutenticado getBeanUsuario() {
        return beanUsuario;
    }


   
}
