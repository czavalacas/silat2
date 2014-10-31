package siat.view.backing.transporte.guia;

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

public class Frm_registrar_guia{//NUEVO CODIGO
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
    private SessionScopedBeanRegistrarGuia beanSessionRegistrarGuia;
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
    /*private RichPopup popupSeleccionarManifiesto;
    private RichPanelFormLayout pfl12;
    private RichCommandButton btnAsignarManifiesto;*/
    //  private RichCommandButton btnAddUndMed;
    //private BeanUsuarioAutenticado beanUsuario = (BeanUsuarioAutenticado) Utils.getSession("USER");
    private Date fecha_Minima;
    private Date fecha_Maxima;
    
    
    public Frm_registrar_guia(){
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
            this.setFecha_Minima(FechaUtiles.fechaPast7());
            this.setFecha_Maxima(FechaUtiles.fechaFoward7());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @PostConstruct 
    public void methodOnPostConstruct(){
        if(getBeanSessionRegistrarGuia().getExec() == 0){
            getBeanSessionRegistrarGuia().setExec(1);
            if(Utils.getSession("GUIA") != null){//Modificar
                getBeanSessionRegistrarGuia().setTituloWindow("Modificar Guia de Remision");
                getBeanSessionRegistrarGuia().setUpdate("2");
                BeanTRGuia beanGuia = (BeanTRGuia) Utils.getSession("GUIA");
                Utils.removeSession("GUIA");
                if(beanGuia.getCConformidad().equals("1") || beanGuia.getIsDisableWhenOk_Anulado() == 1){//OK o anulado
                    getBeanSessionRegistrarGuia().setDisableWhenOK(true); 
                }else{//PENDIENTE
                    getBeanSessionRegistrarGuia().setDisableWhenOK(false);
                }
                getBeanSessionRegistrarGuia().setCidGuia(beanGuia.getCidGuia());
                getBeanSessionRegistrarGuia().setLstConf(this.llenarConfCombo());
                getBeanSessionRegistrarGuia().setVisibleSocConf(true);
                getBeanSessionRegistrarGuia().setFechaEmision(beanGuia.getFechaGuia());
                getBeanSessionRegistrarGuia().setFechaDespacho(beanGuia.getFechaDespacho());
                getBeanSessionRegistrarGuia().setCidGuia(beanGuia.getCidGuia().substring(beanGuia.getCidGuia().indexOf("-")+1,beanGuia.getCidGuia().length()));
                getBeanSessionRegistrarGuia().setCodUN(beanGuia.getCidGuia().substring(0,beanGuia.getCidGuia().indexOf("-")));
                getBeanSessionRegistrarGuia().setNombOS(beanGuia.getOrdenServicio().getCDetalle());
                getBeanSessionRegistrarGuia().setNidOs(beanGuia.getOrdenServicio().getNidOrdnServ());
                //Aqui se cae
                getBeanSessionRegistrarGuia().setRazSocCliente(beanGuia.getOrdenServicio().getAdEmpresa().getCRazonSocial());
                
                getBeanSessionRegistrarGuia().setRucCliente(beanGuia.getOrdenServicio().getAdEmpresa().getCRuc());
                getBeanSessionRegistrarGuia().setRazSocRemitente(beanGuia.getAdEmpresa().getCRazonSocial());
                getBeanSessionRegistrarGuia().setRucRemitente(beanGuia.getAdEmpresa().getCRuc());
                getBeanSessionRegistrarGuia().setNidRemitente((beanGuia.getAdEmpresa().getNidParty().intValue()));
                getBeanSessionRegistrarGuia().setCidDirecRemitente(beanGuia.getNidDireccionRemitente().toString());
                getBeanSessionRegistrarGuia().setCidDirecDestino(beanGuia.getNidDireccionDestino().toString());
                getBeanSessionRegistrarGuia().setLstDirecsRemi(this.llenarDireccionCombo(null,beanGuia.getAdEmpresa().getNidParty().intValue(),null));
                getBeanSessionRegistrarGuia().setLstDirecs(this.llenarDireccionCombo(null,beanGuia.getOrdenServicio().getAdEmpresa().getNidParty().intValue(),null));
                getBeanSessionRegistrarGuia().setLstItems(beanGuia.getItemsLista());
                getBeanSessionRegistrarGuia().setNumPaquetes(beanGuia.getNumPaquetes());
                getBeanSessionRegistrarGuia().setTxtObservGuia(beanGuia.getCObservaciones());
                getBeanSessionRegistrarGuia().setVisibOSAux(true);
                getBeanSessionRegistrarGuia().setVisibOS(false);
                getBeanSessionRegistrarGuia().setDisableComponents(true);
                getBeanSessionRegistrarGuia().setImgGuiaProv(beanGuia.getImgGuiaProv());
                getBeanSessionRegistrarGuia().setNombBtnImg("Subir Imagen");//dfloresgonz 26.04.2014 se cambia por necesidad de adjuntar imagenes aun este OK
                getBeanSessionRegistrarGuia().setDisabInputFile(false);//dfloresgonz 26.04.2014 se cambia por necesidad de adjuntar imagenes aun este OK
                getBeanSessionRegistrarGuia().setDisableComboManifiesto(false);//czavalacas 02.10.2014 campo del choice elejir existente manifiesto cambiado
               
                //Manifiesto
                String tipoComboManif = "";
                if(beanGuia.getTrManifiesto() != null){
                System.out.println("TIENE MANIFIESTO");
                    BeanManifiesto manif = beanGuia.getTrManifiesto();
                    tipoComboManif = "2";//Manif Exist
                    getBeanSessionRegistrarGuia().setPboxManifiestoVis(true);
                    getBeanSessionRegistrarGuia().setPboxDatosEmpVis(true);//czavalacas 02.10.2014 valor original true, cambiado a false para que se pueda agregar manifiesto a una guia
                    getBeanSessionRegistrarGuia().setEstadoFormManif(true);
                    getBeanSessionRegistrarGuia().setNidManifiesto(manif.getNidManifiesto());
                    getBeanSessionRegistrarGuia().setValorComboManif(Integer.parseInt(tipoComboManif));
                    getBeanSessionRegistrarGuia().setFecManif(manif.getFechaManifiesto());
                    getBeanSessionRegistrarGuia().setFletePactado(manif.getNFletePactado());
                    getBeanSessionRegistrarGuia().setAdelanto(manif.getNAdelanto());
                    getBeanSessionRegistrarGuia().setIgv(manif.getIgv());
                    getBeanSessionRegistrarGuia().setMontoFinalVal(manif.getDetraccion());
                    getBeanSessionRegistrarGuia().setDetraccionVal(manif.getDetraccionReal());
                    getBeanSessionRegistrarGuia().setSaldo(manif.getDetraccion() - manif.getNAdelanto());
                    getBeanSessionRegistrarGuia().setTipDoc(manif.getCTipoDoc());
                    getBeanSessionRegistrarGuia().setObserv(manif.getCObservaciones());
                    getBeanSessionRegistrarGuia().setTxtFleteDisable(true);
                    beanSessionRegistrarGuia.setTxtAdelaDisable(true);
                    beanSessionRegistrarGuia.setTxtObsvDisable(true);
                    beanSessionRegistrarGuia.setDisabRegisManif(true);
                    //@TODO darle solo permiso de editar el manifiesto al admin
                    getBeanSessionRegistrarGuia().setEmpresaSC(manif.getTrManifiesto().getCRazonSocial());
                    getBeanSessionRegistrarGuia().setRucEmpSC(manif.getTrManifiesto().getCRuc());
                    getBeanSessionRegistrarGuia().setLstGuiasManif(this.llenarGuiasManif(manif.getNidManifiesto()));
                    getBeanSessionRegistrarGuia().setVisibTxtRazSocProvTransBehav(false);
                    getBeanSessionRegistrarGuia().setVisibTxtRazSocProvTrans2(true);//
                    getBeanSessionRegistrarGuia().setDisabSocFlota(true);
                    getBeanSessionRegistrarGuia().setVisibRucEmpTrans(true);
                    getBeanSessionRegistrarGuia().setDisabSocChofer(true);
                    getBeanSessionRegistrarGuia().setPboxDatosEmpTitulo("Datos de Empresa Proveedora de Transporte");
                    getBeanSessionRegistrarGuia().setLstFlotas(this.llenarFlotasCombo(manif.getTrManifiesto().getNidParty().intValue(),null));
                    getBeanSessionRegistrarGuia().setLstChofers(this.llenarChofersCombo(manif.getTrManifiesto().getNidParty().intValue(),null));
                    getBeanSessionRegistrarGuia().setCidFlota(manif.getNidFlota().toString());
                    getBeanSessionRegistrarGuia().setCidChofer(manif.getNidChof().toString());
                    getBeanSessionRegistrarGuia().setVisibSocFlota(true);
                    getBeanSessionRegistrarGuia().setVisibSocChofer(true);
                    getBeanSessionRegistrarGuia().setPboxDatosTransVis(true);//czavalacas 02.10.2014 valor original true, cambiado a false para que se pueda agregar manifiesto a una guia
                    List<BeanFlota> lstBeanFlota = ln_C_SFFlotaRemote.findFlotasByAttr_LN(null,beanGuia.getNidFlota());
                    if(lstBeanFlota != null){
                        if(lstBeanFlota.size() > 0){
                            BeanFlota beanFlota = lstBeanFlota.get(0);
                            getBeanSessionRegistrarGuia().setMarcaVehi(beanFlota.getCMarvehi());
                            getBeanSessionRegistrarGuia().setPlaca(beanFlota.getCPlaca());
                            getBeanSessionRegistrarGuia().setConfigVehi(beanFlota.getCConfveh());
                            getBeanSessionRegistrarGuia().setCertiInsc(beanFlota.getCertificadoInscripcionEmpresa());
                        } 
                    }
                    List<BeanChofer> lstBeanChofer = ln_C_SFChoferRemote.findChofersByAttr_LN(null,beanGuia.getNidChof());
                    if(lstBeanChofer != null){
                        if(lstBeanChofer.size() > 0){
                            BeanChofer beanChofer = lstBeanChofer.get(0);
                            getBeanSessionRegistrarGuia().setLicen(beanChofer.getLicencia());
                            getBeanSessionRegistrarGuia().setConductor(beanChofer.getNombres());
                        }
                    }
                }else{
                    System.out.println("NO TIENE MANIFIESTO");
                    tipoComboManif = "3";//Transp Propio
                    getBeanSessionRegistrarGuia().setPboxDatosEmpVis(false); //czavalacas 02.10.2014 valor original true, cambiado a false para que se pueda agregar manifiesto a una guia
                    getBeanSessionRegistrarGuia().setPboxDatosTransVis(false);//czavalacas 02.10.2014 valor original true, cambiado a false para que se pueda agregar manifiesto a una guia
                    getBeanSessionRegistrarGuia().setVisibTxtRazSocProvTrans2(true);
                    getBeanSessionRegistrarGuia().setVisibRucEmpTrans(true);
                    getBeanSessionRegistrarGuia().setVisibSocFlota(true);
                    getBeanSessionRegistrarGuia().setVisibSocChofer(true);
                    BeanEmpresa empresa = ln_C_SFEmpresasRemote.selectedEmpresa(new BigDecimal(5));
                    getBeanSessionRegistrarGuia().setEmpresaSC(empresa.getCRazonSocial());
                    getBeanSessionRegistrarGuia().setRucEmpSC(empresa.getCRuc());
                    getBeanSessionRegistrarGuia().setPboxDatosEmpTitulo("Transporte Lubal");
                    getBeanSessionRegistrarGuia().setLstFlotas(this.llenarFlotasCombo(5,null));
                    getBeanSessionRegistrarGuia().setLstChofers(this.llenarChofersCombo(5,null));
                    List<BeanFlota> lstBeanFlota = ln_C_SFFlotaRemote.findFlotasByAttr_LN(null,beanGuia.getNidFlota());
                    getBeanSessionRegistrarGuia().setCidFlota(beanGuia.getNidFlota().toString());
                    getBeanSessionRegistrarGuia().setCidChofer(beanGuia.getNidChof().toString());
                    if(lstBeanFlota != null){
                        if(lstBeanFlota.size() > 0){
                            BeanFlota beanFlota = lstBeanFlota.get(0);
                            getBeanSessionRegistrarGuia().setMarcaVehi(beanFlota.getCMarvehi());
                            getBeanSessionRegistrarGuia().setPlaca(beanFlota.getCPlaca());
                            getBeanSessionRegistrarGuia().setConfigVehi(beanFlota.getCConfveh());
                            getBeanSessionRegistrarGuia().setCertiInsc(beanFlota.getCertificadoInscripcionEmpresa());
                        }
                    }
                    List<BeanChofer> lstBeanChofer = ln_C_SFChoferRemote.findChofersByAttr_LN(null,beanGuia.getNidChof());
                    if(lstBeanChofer != null){
                        if(lstBeanChofer.size() > 0){
                            BeanChofer beanChofer = lstBeanChofer.get(0);
                            getBeanSessionRegistrarGuia().setLicen(beanChofer.getLicencia());
                            getBeanSessionRegistrarGuia().setConductor(beanChofer.getNombres());
                        }
                    }
                }
                getBeanSessionRegistrarGuia().setValorComboManif(Integer.parseInt(tipoComboManif));
                getBeanSessionRegistrarGuia().setComboTipoManif(tipoComboManif);
                getBeanSessionRegistrarGuia().setConfo(beanGuia.getCConformidad());
            }else{
                getBeanSessionRegistrarGuia().setVisibleSocConf(false);
                getBeanSessionRegistrarGuia().setDisabInputFile(false);//Habilitar el INPUTFILE
                getBeanSessionRegistrarGuia().setNombBtnImg("Subir Imagen");
                getBeanSessionRegistrarGuia().setTituloWindow("Registrar Guia de Remision");
                getCidGuia();
                getBeanSessionRegistrarGuia().setConfo("2");
                getBeanSessionRegistrarGuia().setUpdate("1"); 
                getBeanSessionRegistrarGuia().setListaUNs(this.llenarUNCombo());
            }
        }else{  
            mostrarRemitentes();
            mostrarManif();
            mostrarProveedores();
            mostrarOS();
            beanSessionRegistrarGuia.setLstUndMedida(this.llenarUndMedidaCombo());
        }
    }
    
    public void abrirConfEstados(ActionEvent actionEvent) {
        if(beanSessionRegistrarGuia.getUpdate().equalsIgnoreCase("1")){//Al grabar le aparecera el popup
            beanSessionRegistrarGuia.setTextChBoxEstOS("Cerrar la Orden de Servicio "+beanSessionRegistrarGuia.getNidOs());
            if(beanSessionRegistrarGuia.getNidManifiesto() != 0){
                beanSessionRegistrarGuia.setTextChBoxEstManif("Cambiar estado del Manifiesto "+beanSessionRegistrarGuia.getNidManifiesto()+" a 'EN TRANSITO' ");
            }
            Utils.showPopUp(popEst,btnGrabarGuia);
        }else{//al modificar invocara al LN de frente
            validarGuia();
        }
    }
    
    public void validarGuia(){
        boolean isOk = true;
        isOk = getBeanSessionRegistrarGuia().isLstItems_OK();
        if(getBeanSessionRegistrarGuia().getNidOs() == 0){
            Utils.throwError_Aux(ctx, "Debe seleccionar una Orden de Servicio.",4);
            isOk = false;
        }
        if(getBeanSessionRegistrarGuia().getNidRemitente() == 0){
            Utils.throwError_Aux(ctx, "Debe seleccionar una empresa Remitente.",4);
            isOk = false;
        }
       /* if(getBeanSessionRegistrarGuia().getValorComboManif() == 0){
            Utils.throwError_Aux(ctx, "Debe seleccionar/Registrar el Manifiesto.",4);
            isOk = false;
        }
        if(getBeanSessionRegistrarGuia().getValorComboManif() == 2 || getBeanSessionRegistrarGuia().getValorComboManif() == 1){//Selecciono un manifiesto
            //Revisar que se haya seleccionado uno.
            if(getBeanSessionRegistrarGuia().getNidManifiesto() == 0){
                Utils.throwError_Aux(ctx, "Debe seleccionar un Manifiesto.",4);
                isOk = false;
            }
        }*/
        if(beanSessionRegistrarGuia.getNumPaquetes() == 0){
            Utils.throwError_Aux(ctx, "Debe poner el numero de paquetes y/o bultos diferente a 0.",4);
            isOk = false;
        }
        if(isOk){
            grabar();
        }
    }
    
    public void grabar(){
        try{
            String cidGuia = getBeanSessionRegistrarGuia().getCidGuia();
            int numPaquetes = getBeanSessionRegistrarGuia().getNumPaquetes();
            String obs = getBeanSessionRegistrarGuia().getTxtObservGuia();
            if(obs != null){
                if(obs.length() >= 300){
                    obs = obs.substring(0,300);
                }
            }
            String conf = "2";//Pendiente
            if(getBeanSessionRegistrarGuia().getUpdate().equals("2")){
                conf = getBeanSessionRegistrarGuia().getConfo();
            }
            String estGuia = "1";
            Date fecEmis = getBeanSessionRegistrarGuia().getFechaEmision();
            Date fecDesp = getBeanSessionRegistrarGuia().getFechaDespacho();
            int nidRemitente = getBeanSessionRegistrarGuia().getNidRemitente();
            int nidOS = getBeanSessionRegistrarGuia().getNidOs();
            int nidManif = getBeanSessionRegistrarGuia().getNidManifiesto();
            int nidFlota = 0;
            if(getBeanSessionRegistrarGuia().getCidFlota()!=null){
               nidFlota = Integer.parseInt(getBeanSessionRegistrarGuia().getCidFlota());   
            }
            int nidChofer = 0;
            if(getBeanSessionRegistrarGuia().getCidChofer()!=null){
               nidChofer = Integer.parseInt(getBeanSessionRegistrarGuia().getCidChofer()); 
            }
            int nidDirecRemi = Integer.parseInt(getBeanSessionRegistrarGuia().getCidDirecRemitente());
            int nidDirecDest = Integer.parseInt(getBeanSessionRegistrarGuia().getCidDirecDestino());
            String codUn = getBeanSessionRegistrarGuia().getCodUN();
            int opc = Integer.parseInt(getBeanSessionRegistrarGuia().getUpdate());//1 grabar, 2 actualizar
            List<BeanTRItem> lstItems = getBeanSessionRegistrarGuia().getLstItems();
            String estadoManif = getBeanSessionRegistrarGuia().getEstManif();
            String imgGuiaProv = getBeanSessionRegistrarGuia().getImgGuiaProv();
            boolean cerrarOS = beanSessionRegistrarGuia.isEstCambioOS();
            boolean enTransitoManif = beanSessionRegistrarGuia.isEstCambioManif();
            BeanTRGuia bGuia = ln_T_SFGuiaRemote.registrarGuia_LN(cidGuia, 
                                                                  numPaquetes, 
                                                                  obs, 
                                                                  conf, 
                                                                  estGuia, 
                                                                  fecEmis, 
                                                                  fecDesp, 
                                                                  nidRemitente, 
                                                                  nidOS, 
                                                                  nidManif, 
                                                                  nidFlota, 
                                                                  nidChofer, 
                                                                  nidDirecRemi, 
                                                                  nidDirecDest, 
                                                                  opc, 
                                                                  lstItems,
                                                                  codUn,
                                                                  estadoManif,
                                                                  imgGuiaProv,
                                                                  cerrarOS,
                                                                  enTransitoManif);
            if(bGuia.getBeanError() != null){
                BeanError error = bGuia.getBeanError();
                int severidad = 0;
                if(error.getCidError().equals("000")){
                    severidad = 3;
                    Utils.depurar("Grabo la Guia");
                    Utils._redireccionar(ctx,"WEB-INF/consultar_guia.xml#consultar_guia");
                }else{
                    severidad = 1;
                }
                Utils.throwError_Aux(ctx,error.getCDescripcionError(), severidad);
            }else{
                Utils.throwError_Aux(ctx,"Error Inesperado", 1);
            }
        }catch(Exception e){
            e.printStackTrace();
            Utils.throwError_Aux(ctx,"Error Inesperado", 1);
        }
    }
    
    public void validarManifiesto(ActionEvent actionEvent) {
        if(beanSessionRegistrarGuia.getValorComboManif() == 1){//Nuevo Manif
            if(beanSessionRegistrarGuia.getNidEmpProvTrans() == 0){
                Utils.throwError_Aux(ctx, "Debe seleccionar Empresa proveedora de transporte.",4);
            }else{
                registrarManifiesto();
            }
        }
    }
    
    public void registrarGuiaAnulada(DialogEvent dialogEvent) {
        try {
            if (beanSessionRegistrarGuia.getCodUN() == null) {
                Utils.throwError_Aux(ctx, "Seleccione un codigo de serie.", 4);
                return;
            }
            if (beanSessionRegistrarGuia.getCidGuia() == null) {
                Utils.throwError_Aux(ctx, "Espere a que se genere el codigo de Guia.", 4);
                return;
            }
            BeanTRGuia bGuia = ln_T_SFGuiaRemote.registrarGuiaAnulada(beanSessionRegistrarGuia.getCidGuia(),
                                                                      beanSessionRegistrarGuia.getCodUN(),
                                                                      beanSessionRegistrarGuia.getFechaEmision(),
                                                                      beanSessionRegistrarGuia.getFechaDespacho());
            if (bGuia.getBeanError() != null) {
                BeanError error = bGuia.getBeanError();
                int severidad = 0;
                if (error.getCidError().equals("000")) {
                    severidad = 3;
                    Utils.depurar("Grabo la Guia");
                    Utils._redireccionar(ctx, "WEB-INF/consultar_guia.xml#consultar_guia");
                } else {
                    severidad = 1;
                }
                Utils.throwError_Aux(ctx, error.getCDescripcionError(), severidad);
            } else {
                Utils.throwError_Aux(ctx, "Error Inesperado", 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Utils.throwError_Aux(ctx,"Error Inesperado", 1);
        }
    }
    
    public void anularGuia(ActionEvent actionEvent) {
        if (beanSessionRegistrarGuia.getCodUN() == null) {
            Utils.throwError_Aux(ctx, "Seleccione un codigo de serie.", 4);
            return;
        }
        if (beanSessionRegistrarGuia.getCidGuia() == null) {
            Utils.throwError_Aux(ctx, "Esperee a que se genere el codigo de Guia.", 4);
            return;
        }
        Utils.showPopUpMIDDLE(popAnul);
    }
    
    public String registrarManifiesto(){
        try{
            String obsv = beanSessionRegistrarGuia.getObserv();
            if(obsv != null && obsv.length() >= 300){
                obsv = obsv.substring(0,300);
            }
            BeanManifiesto bManifiesto = ln_T_SFManifiestoRemote.registrarManifiesto_LN(getBeanSessionRegistrarGuia().getNidEmpProvTrans(),
                                                                                        getBeanSessionRegistrarGuia().getFecManif(), 
                                                                                        getBeanSessionRegistrarGuia().getFletePactado(),
                                                                                        getBeanSessionRegistrarGuia().getAdelanto(),
                                                                                        getBeanSessionRegistrarGuia().getTipDoc(),
                                                                                        obsv,
                                                                                        Integer.parseInt(getBeanSessionRegistrarGuia().getCidFlota()),
                                                                                        Integer.parseInt(getBeanSessionRegistrarGuia().getCidChofer()),
                                                                                        0,1,"3");//Guias x asignar
            if(bManifiesto.getBeanError() != null){
                BeanError error = bManifiesto.getBeanError();
                int severidad = 0;
                if("000".equals(error.getCidError()) ){
                    severidad = 3;
                    this.tipoManf.setValue("2");
                    ValueChangeEvent vce = new ValueChangeEvent(tipoManf,"1","2");
                    vce.queue();
                    txtFecManif.setValue(bManifiesto.getFechaManifiesto());
                    itCodManif.setValue(bManifiesto.getNidManifiesto());
                    txtAdelanto.setValue(bManifiesto.getNAdelanto());
                    txtFletePact.setValue(bManifiesto.getNFletePactado());
                    tipoDocManif.setValue(bManifiesto.getCTipoDoc());
                    txtObsManif.setValue(bManifiesto.getCObservaciones());
                    itMontoFin.setValue(bManifiesto.getDetraccion());
                    itDetraccion.setValue(bManifiesto.getDetraccionReal());
                    Double adela = 0.0;
                    if(bManifiesto.getNAdelanto() != null){
                        adela = bManifiesto.getNAdelanto();
                    }
                    itSaldo.setValue(bManifiesto.getDetraccion() - adela);
                    beanSessionRegistrarGuia.setFletePactado(bManifiesto.getNFletePactado());
                    beanSessionRegistrarGuia.setAdelanto(bManifiesto.getNAdelanto());
                    beanSessionRegistrarGuia.setMontoFinalVal(bManifiesto.getDetraccion());
                    beanSessionRegistrarGuia.setDetraccionVal(bManifiesto.getDetraccionReal());
                    beanSessionRegistrarGuia.setSaldo(bManifiesto.getDetraccion() - adela);
                    beanSessionRegistrarGuia.setEmpresaSC(bManifiesto.getTrManifiesto().getCRazonSocial());
                    beanSessionRegistrarGuia.setRucEmpSC(bManifiesto.getTrManifiesto().getCRuc());                   
                    beanSessionRegistrarGuia.setManifIngresado(bManifiesto);
                    beanSessionRegistrarGuia.setLlenarCombosAfterInsertManif(true);
                    Utils.addTargetMany(txtFecManif,itCodManif,txtAdelanto,txtFletePact,tipoDocManif,txtObsManif,txtRazSocEmpTransp2,rucEmpTransp,tipoManf,itSaldo);
                }else{
                    severidad = 1;
                    resetNewManif();
                }
                Utils.throwError_Aux(ctx,error.getCDescripcionError(), severidad);
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
    
    public void seleccionarOS(SelectionEvent selectionEvent) {
        RichTable t = (RichTable)selectionEvent.getSource();
        BeanOrdenServicio beanOS = (BeanOrdenServicio) t.getSelectedRowData();
        int nidOrdnServ = beanOS.getNidOrdnServ();
        getBeanSessionRegistrarGuia().setNidOs(nidOrdnServ);
        Object cDetalle = beanOS.getCDetalle();
        String strDetalle = String.valueOf(cDetalle);
        getBeanSessionRegistrarGuia().setLstDirecs(this.llenarDireccionCombo(null,beanOS.getAdEmpresa().getNidParty().intValue(), null));
        Utils.addTarget(socDirecs);
        try {
            itOServicio.setValue(cDetalle);
            beanSessionRegistrarGuia.setTxtObservGuia(strDetalle);
            txtObs.setValue(cDetalle);
            razSocClie.setValue(beanOS.getAdEmpresa().getCRazonSocial());
            rucClie.setValue(beanOS.getAdEmpresa().getCRuc());
            Utils.unselectFilas(tbOS);
            popOS.hide();
            Utils.addTargetMany(txtObs,tbOS,itOServicio,razSocClie,rucClie);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }
    
    public void abrirPopEmp(ActionEvent ae) {
        RichPopup.PopupHints ph = new RichPopup.PopupHints();
        ph.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN,RichPopup.PopupHints.AlignTypes.ALIGN_AFTER_END);
        ph.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN_ID,otTitulo);
        mostrarRemitentes();
        popRemit.show(ph);
    }
    
    public String mostrarRemitentes(){
        String razRemi = getBeanSessionRegistrarGuia().getRazSocRemitente();
        String ruc = getBeanSessionRegistrarGuia().getRucRemi();
        getBeanSessionRegistrarGuia().setLstRemitentes(this.ln_C_SFRelacionEmpresaRemote.getEmpresaProveedoresCliente((razRemi == null ? "" : razRemi.toUpperCase()),
                                                                                                                      (ruc == null ? "" : ruc.toUpperCase())));
        Utils.unselectFilas(tbRemit);
        return null;
    }
    
    public String mostrarProveedores(){
        if(beanSessionRegistrarGuia.isTransportePropio() == false){
            String razProv = getBeanSessionRegistrarGuia().getRazSocProv();
            getBeanSessionRegistrarGuia().setLstProveedores(this.ln_C_SFRelacionEmpresaRemote.getEmpresaProveedores((razProv == null ? "" : razProv.toUpperCase())));
            Utils.unselectFilas(tbProv);
        }
        return null;
    }
    
    public String mostrarManif(){
        Date fecMin = getBeanSessionRegistrarGuia().getManifFecMin();
        Date fecMax = getBeanSessionRegistrarGuia().getManifFecMax();
        int nidManif = getBeanSessionRegistrarGuia().getManifId();
        String razonSocial = getBeanSessionRegistrarGuia().getManifRazSoc();
        getBeanSessionRegistrarGuia().setLstManif(this.ln_C_SFManifiestoRemote.findManifiestosByAttr_LN(fecMin, fecMax, nidManif, null, razonSocial,"3"));
        Utils.unselectFilas(tabMani);
        return null;
    }
    
    public String mostrarOS(){
        String detalle = getBeanSessionRegistrarGuia().getDetallOS();
        String razonSocial = getBeanSessionRegistrarGuia().getRazSocOS(); 
        BeanOrdenServicio beanOS = new BeanOrdenServicio();
        beanOS.setCDetalle(detalle == null ? "" : detalle.toUpperCase());
        beanOS.setCRazonSocial(razonSocial == null ? "" : razonSocial.toUpperCase());
        getBeanSessionRegistrarGuia().setLstOS(this.ln_C_SFOrdenServicioRemote.findOrdenServicioByAttributesAux_Para_Guia(beanOS));
        Utils.unselectFilas(tbOS);
        return null;
    }
    
    public void seleccionarRemitente(SelectionEvent selectionEvent){
        RichTable t = (RichTable)selectionEvent.getSource();
        Object _selectedRowData = t.getSelectedRowData();
        BeanADRelacionEmpresa rela = (BeanADRelacionEmpresa) _selectedRowData;
        getBeanSessionRegistrarGuia().setNidRemitente(rela.getAdEmpresa1().getNidParty().intValue());
        getBeanSessionRegistrarGuia().setLstDirecsRemi(this.llenarDireccionCombo(null,rela.getAdEmpresa1().getNidParty().intValue(), null));
        Utils.addTarget(socDirecRemi);
        try {
            razonSocProve.setValue(rela.getAdEmpresa1().getCRazonSocial());
            rucProve.setValue(rela.getAdEmpresa1().getCRuc());
            Utils.addTargetMany(razonSocProve,rucProve);
            popRemit.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }
    
    public void seleccionarProveedor(SelectionEvent selectionEvent){
        if(beanSessionRegistrarGuia.isTransportePropio() == false){
            socFlota.resetValue();
            socChofer.resetValue();
            txtMarcVehi.resetValue();
            txtPlaca.resetValue();
            txtConfigVehi.resetValue();
            txtCertInsc.resetValue();
            txtLicCondu.resetValue();
            txtConductor.resetValue();
            beanSessionRegistrarGuia.setCidFlota(null);
            beanSessionRegistrarGuia.setCidChofer(null);
            beanSessionRegistrarGuia.setMarcaVehi(null);
            beanSessionRegistrarGuia.setPlaca(null);
            beanSessionRegistrarGuia.setConfigVehi(null);
            beanSessionRegistrarGuia.setCertiInsc(null);
            beanSessionRegistrarGuia.setLicen(null);
            beanSessionRegistrarGuia.setConductor(null);
            Utils.addTargetMany(txtLicCondu,txtConductor,txtMarcVehi,txtPlaca,txtConfigVehi,txtCertInsc,socFlota,socChofer);
            try {
                RichTable t = (RichTable)selectionEvent.getSource();
                Object _selectedRowData = t.getSelectedRowData();
                BeanADRelacionEmpresa beanEmpresaSubCont = (BeanADRelacionEmpresa) _selectedRowData;
                beanSessionRegistrarGuia.setNidEmpProvTrans(beanEmpresaSubCont.getAdEmpresa1().getNidParty().intValue());
                txtRazSocEmpTransp.setValue(beanEmpresaSubCont.getAdEmpresa1().getCRazonSocial());
                rucEmpTransp.setValue(beanEmpresaSubCont.getAdEmpresa1().getCRuc());
                beanSessionRegistrarGuia.setLstFlotas(this.llenarFlotasCombo(beanEmpresaSubCont.getAdEmpresa1().getNidParty().intValue(),null));
                beanSessionRegistrarGuia.setLstChofers(this.llenarChofersCombo(beanEmpresaSubCont.getAdEmpresa1().getNidParty().intValue(),null));
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
    
    public ArrayList llenarGuiasManif(int nidManif){
        ArrayList guias = new ArrayList(); 
        List<BeanTRGuia> guiasManifs = ln_C_SFGuiaRemote.findGuiasByAttr_LN(null,null, null, null, null, 
                                                                            null, null, null, null, nidManif,
                                                                            null,null,"1",0,null,
                                                                            null,null,0,new BigDecimal(0),null);
        if(guiasManifs != null){
            if(guiasManifs.size() > 0){ 
                for (BeanTRGuia d : guiasManifs) {
                    guias.add(new SelectItem(d.getCidGuia().toString(),d.getCidGuia()));
                }
            }
        }
        return guias;
    }
    
    public ArrayList llenarDireccionCombo(Integer nidDireccion,
                                          Integer nidParty,
                                          String cDireccion){
        ArrayList direcsItems = new ArrayList();
        List<BeanDireccion> direcs = LN_C_SFDireccionRemote.getDireccionByProp_LN(nidDireccion,nidParty,cDireccion);
        if(direcs != null){
            if(direcs.size() > 0){
                for (BeanDireccion d : direcs) {
                    direcsItems.add(new SelectItem(d.getNidDireccion().toString(), 
                                                   d.getCDireccion()));
                }
            }
        }
        return direcsItems;
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

    public void registrarItem(ActionEvent actionEvent) {
        int accion = beanSessionRegistrarGuia.getAccion();
        resetearItems();
        BeanTRItem beanItm = new BeanTRItem();
        if(accion == 2){
            beanItm = beanSessionRegistrarGuia.getBeanIteam();
        }else{
            int orden = 1;
            if (beanSessionRegistrarGuia.getLstItems() != null) {
                if (beanSessionRegistrarGuia.getLstItems().size() >= 1) {
                    orden = beanSessionRegistrarGuia.getLstItems().size() + 1;
                }
            }
            beanItm.setOrden(orden);
        }
        beanItm.setCCidGuiaRemitente((cCidGuiaRemitente != null ? cCidGuiaRemitente.toUpperCase() : cCidGuiaRemitente));
        beanItm.setCDescItem(cDescItem.toUpperCase());
        beanItm.setCUndMedida(cUndMedida);
        beanItm.setDPeso(dPeso);
        beanItm.setNCantidad(nCantidad);
        if(accion == 1){
            BeanTRItem itmClon = beanItm.clonar();
            beanSessionRegistrarGuia.getLstItems().add(itmClon);
        }
       popItems.hide();
       btnBorrarItem.setDisabled(true);
       btnEditarItem.setDisabled(true);
       beanSessionRegistrarGuia.setAccion(1);
       Utils.unselectFilas(tblItms);
       Utils.addTargetMany(btnBorrarItem,btnEditarItem);
    }

    public void editarItem(ActionEvent actionEvent) {
        resetearItems();
        beanIteam = new BeanTRItem();
        beanIteam = beanSessionRegistrarGuia.getBeanIteam();
        txtCantidad.setValue(beanIteam.getNCantidad());
        socUndMed.setValue(beanIteam.getCUndMedida());
        txtPeso.setValue(beanIteam.getDPeso());
        txtGuiRem.setValue(beanIteam.getCCidGuiaRemitente());
        txtDescBien.setValue(beanIteam.getCDescItem());
        beanSessionRegistrarGuia.setAccion(2); //Modificar
    }

    public void seleccionarItem(SelectionEvent selectionEvent) {
        if(getBeanSessionRegistrarGuia().isDisableComponents() == false){
            controlarBotones(false);
            RichTable t = (RichTable)selectionEvent.getSource();
            Object _selectedRowData = t.getSelectedRowData();
            beanIteam = (BeanTRItem) _selectedRowData;
            beanSessionRegistrarGuia.setBeanIteam(beanIteam);  
        }
    }
    
    public void borrarItem(ActionEvent actionEvent) {
        beanIteam = new BeanTRItem();
        beanIteam = getBeanSessionRegistrarGuia().getBeanIteam();
        int ordenBorrar = beanIteam.getOrden();
        int size = beanSessionRegistrarGuia.getLstItems().size();
        if(ordenBorrar < size){
            if(ordenBorrar > 1){
                for(BeanTRItem itm :beanSessionRegistrarGuia.getLstItems()){
                    if(itm.getOrden() > 1){
                        itm.setOrden(itm.getOrden() - 1);    
                    }
                }
            }else{
                for(BeanTRItem itm : beanSessionRegistrarGuia.getLstItems()){
                    itm.setOrden(itm.getOrden() - 1);
                }
            }
        }
        beanSessionRegistrarGuia.getLstItems().remove(beanIteam);
        controlarBotones(true);
        Utils.unselectFilas(tblItms);
    }

    public void openPopUp(ActionEvent actionEvent){
        this.setCCidGuiaRemitente("Segun Guia Remitente "+ (razonSocProve.getValue() == null ? "" : razonSocProve.getValue()) +" #: ");    
        beanSessionRegistrarGuia.setAccion(1);//Grabar
        RichPopup.PopupHints ph = new RichPopup.PopupHints();
        ph.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN,RichPopup.PopupHints.AlignTypes.ALIGN_AFTER_END);
        ph.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN_ID,btnNewItem);
        popItems.show(ph);
        if(txtCantidad != null){
            resetearItems();
        }
    }
    
    public void resetearItems(){
        txtCantidad.resetValue();
        txtPeso.resetValue();
        txtGuiRem.resetValue();
        txtDescBien.resetValue();
        socUndMed.resetValue();
    }
    
    public void controlarBotones(boolean estado){
        btnEditarItem.setDisabled(estado);
        btnBorrarItem.setDisabled(estado);
        Utils.addTargetMany(btnEditarItem,btnBorrarItem);
    }
    
    public void cambioUndMedida(ValueChangeEvent vce) {
        try{
            BeanUnidadMedida beanUM = (BeanUnidadMedida)vce.getNewValue();
            setCUndMedida(beanUM.getSigla());
        }catch(Exception e){
            txtPeso.setDisabled(false);
            txtPeso.setVisible(true);
            txtPeso.setRequired(true);
        }
        Utils.addTarget(txtPeso);
    }
    
    public void selectManif(SelectionEvent selectionEvent) {
        RichTable t = (RichTable)selectionEvent.getSource();
        BeanManifiesto beanManifiesto = (BeanManifiesto)  t.getSelectedRowData();
        beanSessionRegistrarGuia.setLstGuiasManif(this.llenarGuiasManif(beanManifiesto.getNidManifiesto()));
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
        beanSessionRegistrarGuia.setLstFlotas(this.llenarFlotasCombo(null,beanManifiesto.getNidFlota()));
        socFlota.setValue(beanManifiesto.getNidFlota().toString());
        beanSessionRegistrarGuia.setCidFlota(beanManifiesto.getNidFlota().toString());
        beanSessionRegistrarGuia.setCidChofer(beanManifiesto.getNidChof().toString());
        beanSessionRegistrarGuia.setLstChofers(this.llenarChofersCombo(null,beanManifiesto.getNidChof()));
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
        beanSessionRegistrarGuia.setManifIngresado(beanManifiesto);
        beanSessionRegistrarGuia.setNidManifiesto(beanManifiesto.getNidManifiesto());
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
    
    /* MANIFIESTO */
    public void cambioManif(ValueChangeEvent valueChangeEvent) {//1 nuevo, 2 existente, 3 propio
     //   valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        try{
            String valorCombo = valueChangeEvent.getNewValue().toString();
            int opc = 0;
            opc = Integer.parseInt(valorCombo);
            beanSessionRegistrarGuia.setValorComboManif(opc);
            beanSessionRegistrarGuia.setNidEmpProvTrans(0);
            ckbTraPro.setRendered(false);
            ckbTraPro.setSelected(false);
            switch(opc){
                case 1 : nuevoManifiesto();break;
                case 2 : existenteManifiesto();break;
                case 3 : transportePropio();break;
            }
            Utils.addTarget(ckbTraPro);
        }catch(Exception e){
            Utils.throwError(ctx,"Elija una opcion","Elija una opcion");
            beanSessionRegistrarGuia.setValorComboManif(0);e.printStackTrace();
        }
    }
    
    /* Metodo Para Seleccionar Manifiesto Existente = 2*/
  /*  public void asignarManifiesto(ActionEvent actionEvent) {
        
        int opc = 2;
       // opc = Integer.parseInt(valorCombo);
        beanSessionRegistrarGuia.setValorComboManif(opc);
        beanSessionRegistrarGuia.setNidEmpProvTrans(0);
        ckbTraPro.setRendered(false);
        ckbTraPro.setSelected(false);
        switch(opc){
            case 1 : nuevoManifiesto();break;
            case 2 : existenteManifiesto();break;
            case 3 : transportePropio();break;
        }
        Utils.showPopUpMIDDLE(popupSeleccionarManifiesto);
       // Utils.addTarget(ckbTraPro);
    }*/
    
    
    public void cambioCheckTransPropio(ValueChangeEvent vce) {
        try{
            Boolean isPropio = (Boolean) vce.getNewValue();
            beanSessionRegistrarGuia.setTransportePropio(isPropio);
            txtAdelanto.resetValue();
            txtAdelanto.resetValue();
            beanSessionRegistrarGuia.setFletePactado(0.0);
            beanSessionRegistrarGuia.setAdelanto(0.0);
            itIGV.resetValue();
            beanSessionRegistrarGuia.setIgv(0.0);
            itDetraccion.resetValue();
            beanSessionRegistrarGuia.setDetraccionVal(0.0);
            itMontoFin.resetValue();
            beanSessionRegistrarGuia.setMontoFinalVal(0.0);
            itSaldo.resetValue();
            beanSessionRegistrarGuia.setSaldo(0.0);
            beanSessionRegistrarGuia.setLstGuiasManif(null);
            socFlota.resetValue();
            socChofer.resetValue();
            txtMarcVehi.resetValue();
            txtPlaca.resetValue();
            txtConfigVehi.resetValue();
            txtCertInsc.resetValue();
            txtLicCondu.resetValue();
            txtConductor.resetValue();
            beanSessionRegistrarGuia.setCidFlota(null);
            beanSessionRegistrarGuia.setCidChofer(null);
            beanSessionRegistrarGuia.setMarcaVehi(null);
            beanSessionRegistrarGuia.setPlaca(null);
            beanSessionRegistrarGuia.setConfigVehi(null);
            beanSessionRegistrarGuia.setCertiInsc(null);
            beanSessionRegistrarGuia.setLicen(null);
            beanSessionRegistrarGuia.setConductor(null);
            Utils.addTargetMany(txtLicCondu,txtConductor,txtMarcVehi,txtPlaca,txtConfigVehi,txtCertInsc,socFlota,socChofer);
            if(isPropio){//disabled = true
                txtFletePact.setDisabled(true);
                txtAdelanto.setDisabled(true);
                beanSessionRegistrarGuia.setTipDoc("2");
                beanSessionRegistrarGuia.setTxtTipDocuDisable(true);
                
                beanSessionRegistrarGuia.setCidChofer("0");
                beanSessionRegistrarGuia.setCidFlota("0");
                beanSessionRegistrarGuia.setPboxDatosEmpTitulo("Transporte Lubal");
                pboxDatosEmp.setText("Transporte Lubal");
                beanSessionRegistrarGuia.setLstFlotas(this.llenarFlotasCombo(5,null));
                beanSessionRegistrarGuia.setLstChofers(this.llenarChofersCombo(5,null));
                
                BeanEmpresa empresa = ln_C_SFEmpresasRemote.selectedEmpresa(new BigDecimal(5));
                beanSessionRegistrarGuia.setNidEmpProvTrans(empresa.getAdParty().getNidParty().intValue());
                beanSessionRegistrarGuia.setEmpresaSC(empresa.getCRazonSocial());
                beanSessionRegistrarGuia.setRucEmpSC(empresa.getCRuc());
                txtRazSocEmpTransp2.setRendered(true);
                txtRazSocEmpTransp2.setValue(empresa.getCRazonSocial());
                beanSessionRegistrarGuia.setVisibTxtRazSocProvTrans2(true);
                txtRazSocEmpTransp.setRendered(false);
                txtRazSocEmpTransp.resetValue();
                txtRazSocEmpTransp.setVisible(false);
                beanSessionRegistrarGuia.setVisibTxtRazSocProvTransBehav(false);
                rucEmpTransp.setValue(empresa.getCRuc());
                rucEmpTransp.setRendered(true);
                Utils.addTargetMany(itSaldo,itMontoFin,itDetraccion,itIGV,tipoDocManif,txtFletePact,txtAdelanto,pboxDatosEmp,txtRazSocEmpTransp2,txtRazSocEmpTransp,rucEmpTransp);
            }else{
                beanSessionRegistrarGuia.setNidEmpProvTrans(0);
                txtFletePact.setDisabled(false);
                txtAdelanto.setDisabled(false);
                beanSessionRegistrarGuia.setTipDoc("1");
                beanSessionRegistrarGuia.setTxtTipDocuDisable(true);
                beanSessionRegistrarGuia.setVisibTxtRazSocProvTrans2(false);
                beanSessionRegistrarGuia.setEstadoFormManif(true);
                beanSessionRegistrarGuia.setVisibTxtRazSocProvTransBehav(true);
                txtRazSocEmpTransp2.setRendered(false);
                txtRazSocEmpTransp2.resetValue();
                txtRazSocEmpTransp.setVisible(true);
                txtRazSocEmpTransp.setRendered(true);
                txtRazSocEmpTransp.resetValue();
                beanSessionRegistrarGuia.setEmpresaSC(null);
                beanSessionRegistrarGuia.setRucEmpSC(null);
                rucEmpTransp.resetValue();
                beanSessionRegistrarGuia.setPboxDatosEmpTitulo("Datos de Empresa Proveedora de Transporte");
                pboxDatosEmp.setText("Datos de Empresa Proveedora de Transporte");
                beanSessionRegistrarGuia.setLstFlotas(null);
                beanSessionRegistrarGuia.setLstChofers(null);
                Utils.addTargetMany(itSaldo,itMontoFin,itDetraccion,itIGV,tipoDocManif,txtFletePact,rucEmpTransp,txtAdelanto,txtRazSocEmpTransp2,txtRazSocEmpTransp,pboxDatosEmp);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void nuevoManifiesto(){
        beanSessionRegistrarGuia.setNidManifiesto(ln_C_SFUtilsRemote.traerSiguienteValorSequence("trmanifiesto.nid_manifiesto"));
        ckbTraPro.setRendered(true);
        panelNuevoManif(true);
        txtFletePact.setDisabled(false);
        txtAdelanto.setDisabled(false);
        tipoDocManif.setDisabled(false);
        txtObsManif.setDisabled(false);
        beanSessionRegistrarGuia.setVisibTxtRazSocProvTransBehav(true);
        socFlota.setDisabled(false);
        socChofer.setDisabled(false);
        itSaldo.resetValue();
        beanSessionRegistrarGuia.setRendBuscManif(false);
        itIGV.resetValue();
        itDetraccion.resetValue();
        itMontoFin.resetValue();
        getBeanSessionRegistrarGuia().setSaldo(0.0);
        txtAdelanto.resetValue();
        txtFletePact.resetValue();
        beanSessionRegistrarGuia.setLstChofers(null);
        beanSessionRegistrarGuia.setLstFlotas(null);
        socFlota.resetValue();
        socChofer.resetValue();
        txtRazSocEmpTransp.resetValue();
        txtRazSocEmpTransp2.setRendered(false);
        txtRazSocEmpTransp.setRendered(true);
        txtRazSocEmpTransp.resetValue();
        txtRazSocEmpTransp.setVisible(true);
        beanSessionRegistrarGuia.setDisabRegisManif(false);
        btnRegManif.setDisabled(false);
        beanSessionRegistrarGuia.setVisibTxtRazSocProvTransBehav(true);
        rucEmpTransp.resetValue();
        //itBuscarManif.setRendered(false);
        socGuiasManif.setRendered(false);
        rucEmpTransp.setRendered(true);
        beanSessionRegistrarGuia.setVisibRucEmpTrans(true);
        beanSessionRegistrarGuia.setVisibSocChofer(true);
        beanSessionRegistrarGuia.setVisibSocFlota(true);
        socFlota.setRendered(true);
        socChofer.setRendered(true);
        txtMarcVehi.setRendered(true);
        txtPlaca.setRendered(true);
        txtConfigVehi.setRendered(true);
        txtCertInsc.setRendered(true);
        socGuiasManif.setRendered(false);
        txtLicCondu.setRendered(true);
        txtConductor.setRendered(true);
        resetNewManif();
        beanSessionRegistrarGuia.setFecManif(FechaUtiles.fechaActual());
        beanSessionRegistrarGuia.setFletePactado(null);
        beanSessionRegistrarGuia.setAdelanto(null);
        beanSessionRegistrarGuia.setTipDoc(null);
        beanSessionRegistrarGuia.setObserv(null);
        beanSessionRegistrarGuia.setRucEmpSC(null);
        beanSessionRegistrarGuia.setEmpresaSC(null);
        txtMarcVehi.resetValue();
        txtPlaca.resetValue();
        txtConfigVehi.resetValue();
        txtCertInsc.resetValue();
        txtLicCondu.resetValue();
        txtConductor.resetValue();
        pboxDatosEmp.setText("Datos de Empresa Proveedora de Transporte");
        Utils.addTargetMany(txtMarcVehi,txtPlaca,txtConfigVehi,txtCertInsc,txtLicCondu,txtConductor,socFlota,socChofer,
                            itSaldo,txtFletePact,itIGV,itMontoFin,txtAdelanto,socGuiasManif,itDetraccion,btnRegManif);
    }
    
    public void existenteManifiesto(){
        resetNewManif();
        beanSessionRegistrarGuia.setNidManifiesto(0);
        beanSessionRegistrarGuia.setTransportePropio(false);
        beanSessionRegistrarGuia.setLstGuiasManif(new ArrayList<BeanTRGuia>());
        beanSessionRegistrarGuia.setPboxDatosTransVis(true);
        beanSessionRegistrarGuia.setVisibTxtRazSocProvTrans2(true);
        panelNuevoManif(true);
        txtFecManif.setRendered(true);
        txtFecManif.resetValue();
        itCodManif.setRendered(true);
        itCodManif.resetValue();
        txtFletePact.setRendered(true);
        itSaldo.setRendered(true);
        itSaldo.resetValue();
        itIGV.setRendered(true);
        itIGV.resetValue();
        itDetraccion.setRendered(true);
        itDetraccion.resetValue();
        itMontoFin.resetValue();
        itMontoFin.setRendered(true);
        socGuiasManif.setRendered(true);
        itMontoFin.resetValue();
        beanSessionRegistrarGuia.setDisabRegisManif(true);
        btnRegManif.setDisabled(true);
        beanSessionRegistrarGuia.setTxtTipDocuDisable(true);
        beanSessionRegistrarGuia.setSaldo(0.0);
        beanSessionRegistrarGuia.setFletePactado(0.0);
        beanSessionRegistrarGuia.setAdelanto(0.0);
        beanSessionRegistrarGuia.setMontoFinalVal(0.0);
        beanSessionRegistrarGuia.setVisibRucEmpTrans(true);
        beanSessionRegistrarGuia.setVisibSocChofer(true);
        beanSessionRegistrarGuia.setDisabRegisManif(true);
        beanSessionRegistrarGuia.setVisibSocFlota(true);
        txtFletePact.resetValue();
        txtAdelanto.setRendered(true);
        txtAdelanto.resetValue();
        tipoDocManif.setRendered(true);
        txtObsManif.setRendered(true);
        txtFletePact.setDisabled(true);
        txtAdelanto.setDisabled(true);
        tipoDocManif.setDisabled(true);
        txtObsManif.setDisabled(true);
    //    itBuscarManif.setRendered(true);
        txtRazSocEmpTransp2.setRendered(true);
        rucEmpTransp.setRendered(true);
        txtRazSocEmpTransp2.resetValue();
        rucEmpTransp.resetValue();
        txtRazSocEmpTransp.setRendered(false);
        txtRazSocEmpTransp.resetValue();
        txtRazSocEmpTransp.setVisible(false);
        beanSessionRegistrarGuia.setVisibTxtRazSocProvTransBehav(false);
        socFlota.setRendered(true);
        socFlota.setDisabled(true);
        socChofer.setDisabled(true);
        socFlota.resetValue();
        socChofer.resetValue();
        socChofer.setRendered(true);
        beanSessionRegistrarGuia.setRendBuscManif(true);
        beanSessionRegistrarGuia.setTxtFleteDisable(true);
        beanSessionRegistrarGuia.setTxtAdelaDisable(true);
        beanSessionRegistrarGuia.setTxtObsvDisable(true);
        beanSessionRegistrarGuia.setLstChofers(null);
        beanSessionRegistrarGuia.setLstFlotas(null);
        beanSessionRegistrarGuia.setPboxDatosEmpTitulo("Datos de Empresa Proveedora de Transporte");
        pboxDatosEmp.setText("Datos de Empresa Proveedora de Transporte");
        txtMarcVehi.setRendered(true);
        txtPlaca.setRendered(true);
        txtConfigVehi.setRendered(true);
        txtCertInsc.setRendered(true);
        txtLicCondu.setRendered(true);
        txtConductor.setRendered(true);
        txtMarcVehi.resetValue();
        txtPlaca.resetValue();
        txtConfigVehi.resetValue();
        txtCertInsc.resetValue();
        txtLicCondu.resetValue();
        txtConductor.resetValue();
        if(beanSessionRegistrarGuia.isLlenarCombosAfterInsertManif() == true){
            BeanManifiesto bManifiesto = getBeanSessionRegistrarGuia().getManifIngresado();
            //this.setGuiasManif(this.llenarGuiasManif(bManifiesto.getNidManifiesto()));
            beanSessionRegistrarGuia.setLstFlotas(this.llenarFlotasCombo(null,bManifiesto.getNidFlota()));
            beanSessionRegistrarGuia.setCidFlota(bManifiesto.getNidFlota().toString());
            beanSessionRegistrarGuia.setLstChofers(this.llenarChofersCombo(null,bManifiesto.getNidChof()));
            beanSessionRegistrarGuia.setCidChofer(bManifiesto.getNidChof().toString());
            beanSessionRegistrarGuia.setNidManifiesto(bManifiesto.getNidManifiesto());
            txtFecManif.setValue(bManifiesto.getFechaManifiesto());
            itCodManif.setValue(bManifiesto.getNidManifiesto());
            txtAdelanto.setValue(bManifiesto.getNAdelanto());
            txtFletePact.setValue(bManifiesto.getNFletePactado());
            txtObsManif.setValue(bManifiesto.getCObservaciones());
            itSaldo.setValue(bManifiesto.getNFletePactado() - bManifiesto.getNAdelanto());
            txtRazSocEmpTransp2.setValue(beanSessionRegistrarGuia.getEmpresaSC());
            rucEmpTransp.setValue(beanSessionRegistrarGuia.getRucEmpSC());
            if(beanSessionRegistrarGuia.getFecManif() == null){
                beanSessionRegistrarGuia.setFecManif(bManifiesto.getFechaManifiesto());
                beanSessionRegistrarGuia.setFletePactado(bManifiesto.getNFletePactado());
                beanSessionRegistrarGuia.setAdelanto(bManifiesto.getNAdelanto());
                beanSessionRegistrarGuia.setTipDoc(bManifiesto.getCTipoDoc());
                beanSessionRegistrarGuia.setObserv(bManifiesto.getCObservaciones());
                beanSessionRegistrarGuia.setRucEmpSC(bManifiesto.getTrManifiesto().getCRuc());
                beanSessionRegistrarGuia.setEmpresaSC(bManifiesto.getTrManifiesto().getCRazonSocial());
            }
        }else{
            beanSessionRegistrarGuia.setEmpresaSC(null);
            beanSessionRegistrarGuia.setRucEmpSC(null);
        }
        Utils.addTargetMany(txtConductor,txtMarcVehi,txtPlaca,txtConfigVehi,txtCertInsc,pboxDatosEmp,pboxManifiesto,txtLicCondu,
                            txtFecManif,itCodManif,txtObsManif,txtRazSocEmpTransp,itIGV,txtRazSocEmpTransp2,itMontoFin,socFlota,
                            itSaldo,socGuiasManif,txtFletePact,txtAdelanto,rucEmpTransp,socChofer,itDetraccion);
    }
    
    public void transportePropio(){
        panelNuevoManif(false);
        beanSessionRegistrarGuia.setNidManifiesto(0);
        beanSessionRegistrarGuia.setCidChofer("0");
        beanSessionRegistrarGuia.setCidFlota("0");
       // itBuscarManif.setRendered(false);
        beanSessionRegistrarGuia.setVisibTxtRazSocProvTrans2(true);
        beanSessionRegistrarGuia.setVisibSocFlota(true);
        beanSessionRegistrarGuia.setVisibSocChofer(true);
        resetNewManif();
        beanSessionRegistrarGuia.setPboxDatosEmpVis(true);
        beanSessionRegistrarGuia.setVisibRucEmpTrans(true);
        beanSessionRegistrarGuia.setPboxDatosTransVis(true);
        beanSessionRegistrarGuia.setDisabSocFlota(false);
        beanSessionRegistrarGuia.setDisabSocChofer(false);
        BeanEmpresa empresa = ln_C_SFEmpresasRemote.selectedEmpresa(new BigDecimal(5));
        beanSessionRegistrarGuia.setEmpresaSC(empresa.getCRazonSocial());
        beanSessionRegistrarGuia.setRucEmpSC(empresa.getCRuc());
        txtRazSocEmpTransp2.setRendered(true);
        txtRazSocEmpTransp2.setValue(empresa.getCRazonSocial());
        txtRazSocEmpTransp.setRendered(false);
        txtRazSocEmpTransp.resetValue();
        txtRazSocEmpTransp.setVisible(false);
        beanSessionRegistrarGuia.setVisibTxtRazSocProvTransBehav(false);
        rucEmpTransp.setValue(empresa.getCRuc());
        rucEmpTransp.setRendered(true);
        pboxDatosEmp.setVisible(true);
        beanSessionRegistrarGuia.setPboxDatosEmpTitulo("Transporte Lubal");
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
        beanSessionRegistrarGuia.setLstFlotas(this.llenarFlotasCombo(5,null));
        beanSessionRegistrarGuia.setLstChofers(this.llenarChofersCombo(5,null));
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
        getBeanSessionRegistrarGuia().setEstadoFormManif(estado);
        pboxDatosEmp.setVisible(estado);
        pboxDatosTrans.setVisible(estado);
        pboxManifiesto.setVisible(estado);
        getBeanSessionRegistrarGuia().setPboxDatosEmpVis(estado);
        getBeanSessionRegistrarGuia().setPboxDatosTransVis(estado);
        getBeanSessionRegistrarGuia().setPboxManifiestoVis(estado);
        Utils.addTargetMany(pboxDatosEmp,pboxDatosTrans,pflNewManif,pboxManifiesto);
    }

    public void changeDireccRemi(ValueChangeEvent vce) {
        try{
            String val = vce.getNewValue().toString();
            if(getBeanSessionRegistrarGuia().getCidDirecDestino() != null){
                if(getBeanSessionRegistrarGuia().getCidDirecDestino().equals(val)){
                    getBeanSessionRegistrarGuia().setCidDirecRemitente("0");
                    socDirecRemi.setValue("0");
                    Utils.throwError_Aux(ctx,"Debe seleccionar una direccion distinta a la de destino.",4);
                }else{
                    getBeanSessionRegistrarGuia().setCidDirecRemitente(val);
                }
            }else{
                getBeanSessionRegistrarGuia().setCidDirecRemitente(val);
            }
        }catch(Exception e){
            getBeanSessionRegistrarGuia().setCidDirecRemitente("0");
            socDirecRemi.setValue("0");
            Utils.throwError_Aux(ctx,"Debe seleccionar una direccion",4);
        }
        Utils.addTarget(socDirecRemi);
    }

    public void changeDirecDest(ValueChangeEvent vce) {
        try{
            String val = vce.getNewValue().toString();
            if(getBeanSessionRegistrarGuia().getCidDirecRemitente() != null){
                if(getBeanSessionRegistrarGuia().getCidDirecRemitente().equals(val)){
                    getBeanSessionRegistrarGuia().setCidDirecDestino("0");
                    socDirecs.setValue("0");
                    Utils.throwError_Aux(ctx,"Debe seleccionar una direccion distinta a la remitente.",4);
                }else{
                    getBeanSessionRegistrarGuia().setCidDirecDestino(val);
                }
            }else{
                getBeanSessionRegistrarGuia().setCidDirecDestino(val);
            }
        }catch(Exception e){
            getBeanSessionRegistrarGuia().setCidDirecDestino("0");
            socDirecs.setValue("0");
            Utils.throwError_Aux(ctx,"Debe seleccionar una direccion",4);
        }
        Utils.addTarget(socDirecs);
    }
    
    public ArrayList llenarUNCombo() {
        ArrayList unItems = new ArrayList(); 
        List<BeanUnidadNegocio> roles = ln_C_SFUnidadNegocioRemote.getUnidadesNegocio_LN();
        for (BeanUnidadNegocio r : roles) {
            unItems.add(new SelectItem(r.getCidUnidadNegocio().toString(), 
                                       r.getCidUnidadNegocio().toString()));
        }
        return unItems;
    }
    
    public void cambiarFlete(ValueChangeEvent vce) {
        try{
            Object flete = vce.getNewValue();
            Double dFlete = Double.parseDouble(flete.toString());
            Double adela = 0.0;
            Double detrac = 0.0;
            Double montoFinal = 0.0;
            if(getBeanSessionRegistrarGuia().getAdelanto() != null){
                adela = getBeanSessionRegistrarGuia().getAdelanto();
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
                getBeanSessionRegistrarGuia().setIgvVal(igv);
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
            getBeanSessionRegistrarGuia().setFletePactado(dFlete);
            getBeanSessionRegistrarGuia().setSaldo(saldo);
            getBeanSessionRegistrarGuia().setDetrac(detrac);
            getBeanSessionRegistrarGuia().setMontoFinalVal(montoFinal);
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
            if(getBeanSessionRegistrarGuia().getMontoFinalVal() != null){
                montoFinal = getBeanSessionRegistrarGuia().getMontoFinalVal();
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
            getBeanSessionRegistrarGuia().setAdelanto(dAdela);
            getBeanSessionRegistrarGuia().setSaldo(saldo);
            Utils.addTargetMany(txtAdelanto,itSaldo);
        }catch(Exception e){
            Utils.throwError_Aux(ctx,"Ingrese un valor numerico positivo mayor a 0",4);
        }
    }
    
    public void cambioFecEmision(ValueChangeEvent vce) {
       /* try{
            Object oFecha = vce.getNewValue();
            Date fec = (Date) oFecha;
            String error = ln_T_SFGuiaRemote.validarFechasEmiDesp(fec,getBeanSessionRegistrarGuia().getFechaDespacho());
            if(!error.equals("000")){
                if(error.equals("LUB-0014")){
                    Utils.throwError_Aux(ctx,"La fecha de emision no puede ser despues que la de despacho",4);
                }
                if(error.equals("LUB-0015")){
                    Utils.throwError_Aux(ctx,"La fecha de emision no puede ser menos de 8 dias que la fecha de despacho",4);
                }
                getBeanSessionRegistrarGuia().setFechaEmision(FechaUtiles.fechaActual());
                fecEmision.resetValue();
                Utils.addTarget(fecEmision);
            }
        }catch(Exception e){
            Utils.throwError_Aux(ctx,"Error al seleccionar fecha",4);
        }*/
    }
    
    public void checkConformidad(ValueChangeEvent vce) {
        try{
            Object oConf = vce.getNewValue().toString();
            if(oConf.equals("1")){//OK
                int nidManif = getBeanSessionRegistrarGuia().getNidManifiesto();
                if(nidManif != 0){
                    String codUN = getBeanSessionRegistrarGuia().getCodUN();
                    String cidGuia = getBeanSessionRegistrarGuia().getCidGuia();
                    BeanError beanError = ln_C_SFManifiestoRemote.guiasOK(nidManif, codUN, cidGuia,0);
                    if(beanError != null){
                        if(!beanError.getCidError().equals("000")){
                            String msjTitulo = beanError.getCDescripcionError().replaceAll(":codUN",codUN);
                            msjTitulo = msjTitulo.replaceAll(":cidGuia",cidGuia);
                            msjTitulo = msjTitulo.replaceAll(":nidManif",nidManif+"");
                            getBeanSessionRegistrarGuia().setNombreDialog(msjTitulo);
                            Utils.showPopUpMIDDLE(popGuiaOK);
                        }
                    }
                }
            }else{
                getBeanSessionRegistrarGuia().setEstManif(null);
            }
        }catch(Exception e){
           // Utils.throwError_Aux(ctx,"Error al seleccionar Conformidad",4);
        }
    }
    
    public void dialogGuiasOKListener(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
          // MANDA EL ESTADO DEL MANIFIESTO EN SESSION DEL COMPONENTE estadoManif
        }
    }
    
    public void onDialogCancel(ClientEvent ce) {
        getBeanSessionRegistrarGuia().setConfo("2");//Pendiente
        socConf.setValue("2");
        Utils.addTarget(socConf);
        popGuiaOK.hide();
        FacesContext fctx = FacesContext.getCurrentInstance();
        fctx.renderResponse();
    }
    
    public void onDialogCambioCancel(ClientEvent ce) {
        beanSessionRegistrarGuia.setEstCambioManif(false);
        beanSessionRegistrarGuia.setEstCambioOS(false);
        popEst.hide();
        FacesContext fctx = FacesContext.getCurrentInstance();
        fctx.renderResponse();
    }

    public void abrirPopImg(ActionEvent ae) {
        ///imageservlet?cidguia=#{sessionScope.beanSessionRegistrarGuia.cidGuia}&#38;cidunin=#{sessionScope.beanSessionRegistrarGuia.codUN}
        String rutaImg = "/imageservlet?cidguia="+beanSessionRegistrarGuia.getCidGuia()+"&cidunin="+beanSessionRegistrarGuia.getCodUN();
        beanSessionRegistrarGuia.setRutaImgGuia(rutaImg);
        if(imgGuia != null){
            imgGuia.setSource(rutaImg);
            Utils.addTarget(imgGuia);
        }
        Utils.showPopUpMIDDLE(popImg);
    }

    public void dialogImgok(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
            if(getBeanSessionRegistrarGuia().getImgGuiaProvAux() != null){
                getBeanSessionRegistrarGuia().setImgGuiaProv(getBeanSessionRegistrarGuia().getImgGuiaProvAux());
            }
        }
    }
    
    public void cambiarEstadosOSManifDialog(DialogEvent de) {
        if (de.getOutcome() == DialogEvent.Outcome.ok) {
            validarGuia();   
        }
    }
    
    public void onDialogImagenCancel(ClientEvent ce) {
        getBeanSessionRegistrarGuia().setImgGuiaProv(null);
        getBeanSessionRegistrarGuia().setImgGuiaProvAux(null);
        imgGuia.setSource(getBeanSessionRegistrarGuia().getImgGuiaProv());
        Utils.addTarget(imgGuia);
        FacesContext fctx = FacesContext.getCurrentInstance();
        fctx.renderResponse();
        popImg.hide();
    }
    
    public void uploadFileValueChangeEvent(ValueChangeEvent valueChangeEvent) {
        try{
            UploadedFile file = (UploadedFile)valueChangeEvent.getNewValue();
            long fileSize = file.getLength() / (1024 * 1024);//megabytes
            Utils.depurar("size en bytes: "+file.getLength()+ " size en kilos: "+(file.getLength() / 1024)+ " size en megas: "+fileSize);
            if(file.getLength() > 1602864){
                Utils.throwError_Aux(ctx,"El archivo no puede ser de mas de 1.5 MB.",4);
                return;
            }
            if(Utils.validarExtensionImg(file.getFilename())){
                String ext = file.getFilename().substring(file.getFilename().lastIndexOf(".") + 1, file.getFilename().length());
                String cidGuiaFull = getBeanSessionRegistrarGuia().getCodUN()+"-"+getBeanSessionRegistrarGuia().getCidGuia();
                String timePath = GregorianCalendar.getInstance().getTimeInMillis()+"";
                String rutaLocal = "";
                if(File.separator.equals("/")){
                    rutaLocal = File.separator+"recursos" + File.separator + "img" + File.separator + "guias" + File.separator + cidGuiaFull + timePath + "." + ext;   
                }else{
                    rutaLocal = "recursos" + File.separator + "img" + File.separator + "guias" + File.separator + cidGuiaFull + timePath + "." + ext;   
                }
               // String rutaLocal = "/recursos/img/guias/"+cidGuiaFull+timePath+ "."+ext;    
                ServletContext servletCtx = (ServletContext)ctx.getExternalContext().getContext();
                String imageDirPath = servletCtx.getRealPath("/");
                InputStream inputStream = file.getInputStream();
                BufferedImage input = ImageIO.read(inputStream);
               
                String rutaImg = imageDirPath + rutaLocal;
                Utils.depurar("rutaImg: "+rutaImg);
                File outputFile = new File(rutaImg);
                getBeanSessionRegistrarGuia().setImgGuiaProvAux(rutaImg);
                imgGuia.setSource(rutaLocal);
                Utils.addTarget(imgGuia);
                ImageIO.write(input,ext,outputFile);
            }else{
                Utils.throwError_Aux(ctx,"El archivo no es de tipo imagen suba un jpg/png",4);
            }
        }catch(Exception e){
            Utils.throwError_Aux(ctx,"Hubo un error a subir la imagen ingrese nuevamente",4);
        }
    }
    
    public void getCidGuia(){
        String cidGuia = "";
        if(beanSessionRegistrarGuia.getCodUN() != null){
            cidGuia  = ln_C_SFUtilsRemote.generarCorrelativoLN("TRGuia","G",6,beanSessionRegistrarGuia.getCodUN()); //(String) ADFUtil.evaluateEL("#{bindings.generarCorrelativoLN_Return}");
            getBeanSessionRegistrarGuia().setCidGuia(cidGuia);
        }
    }
    
    public void cambioUN(ValueChangeEvent vce) {
        try{
            String val = (String) vce.getNewValue();
            beanSessionRegistrarGuia.setCodUN(val);
            getCidGuia();
            Utils.addTarget(txtCorrelativo);
        }catch(Exception e){
            
        }
    }
    
    public void agregarUM(ActionEvent ae) {
        try {
            if(beanSessionRegistrarGuia.getDescUM() != null && beanSessionRegistrarGuia.getValUM() != null){
                BeanUnidadMedida bUM = ln_T_SFUnidadMedidaRemote.registrarUnidadMedida(beanSessionRegistrarGuia.getDescUM(),beanSessionRegistrarGuia.getValUM());
                if (bUM != null) {
                    int severidad = 4;
                    BeanError bError = bUM.getBeanError();
                    if (bError.getCidError().equals("000")) {
                        severidad = 3;
                    }
                    Utils.throwError_Aux(ctx, bError.getCDescripcionError(),severidad);
                    if(bError.getCidError().equals("000")){
                        popUM.hide();
                        beanSessionRegistrarGuia.setDescUM(null);
                        beanSessionRegistrarGuia.setValUM(null);
                    }
                } else {
                    Utils.throwError_Aux(ctx, "Error Inesperado", 4);
                }
            }else{
                Utils.throwError_Aux(ctx, "Llene los campos", 4);
            }
        } catch (Exception e) {
            Utils.throwError_Aux(ctx, "Error Inesperado", 4);
            e.printStackTrace();
        }
    }
    
    public void setOtTitulo(RichOutputText ot1) {
        this.otTitulo = ot1;
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

    public void setRowBotones(RichGridRow gr2) {
        this.rowBotones = gr2;
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

    public void setCelBtn1(RichGridCell gc4) {
        this.celBtn1 = gc4;
    }

    public RichGridCell getCelBtn1() {
        return celBtn1;
    }


    public void setItOServicio(RichInputText it1) {
        this.itOServicio = it1;
    }

    public RichInputText getItOServicio() {
        return itOServicio;
    }


    public void setPopOS(RichPopup p1) {
        this.popOS = p1;
    }

    public RichPopup getPopOS() {
        return popOS;
    }

    public void setOlTituloPopUp(RichOutputLabel ol1) {
        this.olTituloPopUp = ol1;
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

    public void setTbOS(RichTable t1) {
        this.tbOS = t1;
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

    public void setItDetraccion(RichInputText it3) {
        this.itDetraccion = it3;
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


    public void setPopRemit(RichPopup p2) {
        this.popRemit = p2;
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

    public void setTbRemit(RichTable t2) {
        this.tbRemit = t2;
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

    public void setRazonSocProve(RichInputText it6) {
        this.razonSocProve = it6;
    }

    public RichInputText getRazonSocProve() {
        return razonSocProve;
    }

    public void setRucProve(RichInputText it7) {
        this.rucProve = it7;
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

    public void setPglBotones(RichPanelGridLayout pgl4) {
        this.pglBotones = pgl4;
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
        this.gc7 = celBtn1;
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

    public void setRazSocClie(RichInputText it6) {
        this.razSocClie = it6;
    }

    public RichInputText getRazSocClie() {
        return razSocClie;
    }

    public void setRucClie(RichInputText it7) {
        this.rucClie = it7;
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

    public void setTxtCorrelativo(RichInputText it6) {
        this.txtCorrelativo = it6;
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

    public void setTblItms(RichTable t3) {
        this.tblItms = t3;
    }

    public RichTable getTblItms() {
        return tblItms;
    }

    public void setBtnNewItem(RichCommandButton cb2) {
        this.btnNewItem = cb2;
    }

    public RichCommandButton getBtnNewItem() {
        return btnNewItem;
    }

    public void setPopItems(RichPopup p3) {
        this.popItems = p3;
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


    public void setTxtGuiRem(RichInputText it9) {
        this.txtGuiRem = it9;
    }

    public RichInputText getTxtGuiRem() {
        return txtGuiRem;
    }

    public void setTxtDescBien(RichInputText it10) {
        this.txtDescBien = it10;
    }

    public RichInputText getTxtDescBien() {
        return txtDescBien;
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

    public void setBtnRegistrarItem(RichCommandButton cb2) {
        this.btnRegistrarItem = cb2;
    }

    public RichCommandButton getBtnRegistrarItem() {
        return btnRegistrarItem;
    }


    public void setSocUndMed(RichSelectOneChoice soc1) {
        this.socUndMed = soc1;
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

    public void setBtnEditarItem(RichCommandButton cb2) {
        this.btnEditarItem = cb2;
    }

    public RichCommandButton getBtnEditarItem() {
        return btnEditarItem;
    }

    public void setBeanSessionRegistrarGuia(SessionScopedBeanRegistrarGuia beanSessionRegistrarGuia) {
        this.beanSessionRegistrarGuia = beanSessionRegistrarGuia;
    }

    public SessionScopedBeanRegistrarGuia getBeanSessionRegistrarGuia() {
        return beanSessionRegistrarGuia;
    }

    public void setBtnBorrarItem(RichCommandButton cb2) {
        this.btnBorrarItem = cb2;
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


    public void setTxtObs(RichInputText it6) {
        this.txtObs = it6;
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

    public void setTxtRazSocEmpTransp(RichInputText it6) {
        this.txtRazSocEmpTransp = it6;
    }

    public RichInputText getTxtRazSocEmpTransp() {
        return txtRazSocEmpTransp;
    }

    public void setRucEmpTransp(RichInputText it6) {
        this.rucEmpTransp = it6;
    }

    public RichInputText getRucEmpTransp() {
        return rucEmpTransp;
    }

    public void setPanFormTrans(RichPanelFormLayout pfl9) {
        this.panFormTrans = pfl9;
    }

    public RichPanelFormLayout getPanFormTrans() {
        return panFormTrans;
    }

    public void setTxtMarcVehi(RichInputText it6) {
        this.txtMarcVehi = it6;
    }

    public RichInputText getTxtMarcVehi() {
        return txtMarcVehi;
    }

    public void setTxtPlaca(RichInputText it7) {
        this.txtPlaca = it7;
    }

    public RichInputText getTxtPlaca() {
        return txtPlaca;
    }

    public void setTxtConfigVehi(RichInputText it8) {
        this.txtConfigVehi = it8;
    }

    public RichInputText getTxtConfigVehi() {
        return txtConfigVehi;
    }

    public void setTxtCertInsc(RichInputText it9) {
        this.txtCertInsc = it9;
    }

    public RichInputText getTxtCertInsc() {
        return txtCertInsc;
    }

    public void setTxtLicCondu(RichInputText it10) {
        this.txtLicCondu = it10;
    }

    public RichInputText getTxtLicCondu() {
        return txtLicCondu;
    }

    public void setTxtConductor(RichInputText it11) {
        this.txtConductor = it11;
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

    public void setPopProv(RichPopup p3) {
        this.popProv = p3;
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

    public void setTbProv(RichTable t4) {
        this.tbProv = t4;
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

    public void setGc4(RichGridCell gc4) {
        this.celBtn1 = gc4;
    }

    public RichGridCell getGc4() {
        return celBtn1;
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

    public void setTipoManf(RichSelectOneChoice soc1) {
        this.tipoManf = soc1;
    }

    public RichSelectOneChoice getTipoManf() {
        return tipoManf;
    }

    public void setItemManif1(RichSelectItem si4) {
        this.itemManif1 = si4;
    }

    public RichSelectItem getItemManif1() {
        return itemManif1;
    }

    public void setItemManif2(RichSelectItem si5) {
        this.itemManif2 = si5;
    }

    public RichSelectItem getItemManif2() {
        return itemManif2;
    }

    public void setPflNewManif(RichPanelFormLayout pfl10) {
        this.pflNewManif = pfl10;
    }

    public RichPanelFormLayout getPflNewManif() {
        return pflNewManif;
    }

    public void setTxtFecManif(RichInputDate id2) {
        this.txtFecManif = id2;
    }

    public RichInputDate getTxtFecManif() {
        return txtFecManif;
    }

    public void setTxtFletePact(RichInputText it7) {
        this.txtFletePact = it7;
    }

    public RichInputText getTxtFletePact() {
        return txtFletePact;
    }

    public void setTxtAdelanto(RichInputText it7) {
        this.txtAdelanto = it7;
    }

    public RichInputText getTxtAdelanto() {
        return txtAdelanto;
    }

    public void setTxtObsManif(RichInputText it7) {
        this.txtObsManif = it7;
    }

    public RichInputText getTxtObsManif() {
        return txtObsManif;
    }

    public void setTipoDocManif(RichSelectOneChoice soc2) {
        this.tipoDocManif = soc2;
    }

    public RichSelectOneChoice getTipoDocManif() {
        return tipoDocManif;
    }

    public void setTipoDoc1(RichSelectItem si6) {
        this.tipoDoc1 = si6;
    }

    public RichSelectItem getTipoDoc1() {
        return tipoDoc1;
    }

    public void setTipoDoc2(RichSelectItem si7) {
        this.tipoDoc2 = si7;
    }

    public RichSelectItem getTipoDoc2() {
        return tipoDoc2;
    }

    public void setTipoDoc3(RichSelectItem si8) {
        this.tipoDoc3 = si8;
    }

    public RichSelectItem getTipoDoc3() {
        return tipoDoc3;
    }

    public void setTipoDoc4(RichSelectItem si9) {
        this.tipoDoc4 = si9;
    }

    public RichSelectItem getTipoDoc4() {
        return tipoDoc4;
    }

    public void setTipoDoc5(RichSelectItem si10) {
        this.tipoDoc5 = si10;
    }

    public RichSelectItem getTipoDoc5() {
        return tipoDoc5;
    }

    public void setBtnRegManif(RichCommandButton cb4) {
        this.btnRegManif = cb4;
    }

    public RichCommandButton getBtnRegManif() {
        return btnRegManif;
    }

    public void setItemManif3(RichSelectItem si6) {
        this.itemManif3 = si6;
    }

    public RichSelectItem getItemManif3() {
        return itemManif3;
    }

    public void setPboxDatosEmp(RichPanelBox pb1) {
        this.pboxDatosEmp = pb1;
    }

    public RichPanelBox getPboxDatosEmp() {
        return pboxDatosEmp;
    }

    public void setPboxDatosTrans(RichPanelBox pb1) {
        this.pboxDatosTrans = pb1;
    }

    public RichPanelBox getPboxDatosTrans() {
        return pboxDatosTrans;
    }

    public void setPboxManifiesto(RichPanelBox pb1) {
        this.pboxManifiesto = pb1;
    }

    public RichPanelBox getPboxManifiesto() {
        return pboxManifiesto;
    }


    public void setSocFlota(RichSelectOneChoice soc1) {
        this.socFlota = soc1;
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

    public void setSocChofer(RichSelectOneChoice soc1) {
        this.socChofer = soc1;
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


    public void setItBuscarManif(RichInputText it7) {
        this.itBuscarManif = it7;
    }

    public RichInputText getItBuscarManif() {
        return itBuscarManif;
    }

    public void setPopManif(RichPopup p4) {
        this.popManif = p4;
    }

    public RichPopup getPopManif() {
        return popManif;
    }

    public void setTabMani(RichTable t5) {
        this.tabMani = t5;
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

    public void setBtnBuscarManif(RichCommandButton cb4) {
        this.btnBuscarManif = cb4;
    }

    public RichCommandButton getBtnBuscarManif() {
        return btnBuscarManif;
    }

    public void setTxtRazSocEmpTransp2(RichInputText it15) {
        this.txtRazSocEmpTransp2 = it15;
    }

    public RichInputText getTxtRazSocEmpTransp2() {
        return txtRazSocEmpTransp2;
    }

    public void setSocDirecs(RichSelectOneChoice soc1) {
        this.socDirecs = soc1;
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

    public void setSocDirecRemi(RichSelectOneChoice soc1) {
        this.socDirecRemi = soc1;
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

    public void setBtnGrabarGuia(RichCommandButton cb4) {
        this.btnGrabarGuia = cb4;
    }

    public RichCommandButton getBtnGrabarGuia() {
        return btnGrabarGuia;
    }

    public void setFecEmision(RichInputDate id6) {
        this.fecEmision = id6;
    }

    public RichInputDate getFecEmision() {
        return fecEmision;
    }

    public void setFecDespacho(RichInputDate id6) {
        this.fecDespacho = id6;
    }

    public RichInputDate getFecDespacho() {
        return fecDespacho;
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

    public void setCtx(FacesContext ctx) {
        this.ctx = ctx;
    }

    public FacesContext getCtx() {
        return ctx;
    }

    public void setSocCodigoUN(RichSelectOneChoice soc1) {
        this.socCodigoUN = soc1;
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

    public void setItSaldo(RichInputText it1) {
        this.itSaldo = it1;
    }

    public RichInputText getItSaldo() {
        return itSaldo;
    }


    public void setSocGuiasManif(RichSelectOneChoice soc2) {
        this.socGuiasManif = soc2;
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

    public void setLn_C_SFGuiaRemote(LN_C_SFGuiaRemote ln_C_SFGuiaRemote) {
        this.ln_C_SFGuiaRemote = ln_C_SFGuiaRemote;
    }

    public LN_C_SFGuiaRemote getLn_C_SFGuiaRemote() {
        return ln_C_SFGuiaRemote;
    }

    public void setGuiasManif(List guiasManif) {
        this.guiasManif = guiasManif;
    }

    public List getGuiasManif() {
        return guiasManif;
    }

    public void setD2(RichDocument d2) {
        this.diaGuiaOK = d2;
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

    public void setSocConf(RichSelectOneChoice soc2) {
        this.socConf = soc2;
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

    public void setItOServAux(RichInputText it1) {
        this.itOServAux = it1;
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

    public void setItIGV(RichInputText it15) {
        this.itIGV = it15;
    }

    public RichInputText getItIGV() {
        return itIGV;
    }

    public void setItMontoFin(RichInputText it16) {
        this.itMontoFin = it16;
    }

    public RichInputText getItMontoFin() {
        return itMontoFin;
    }

    public void setPopGuiaOK(RichPopup p4) {
        this.popGuiaOK = p4;
    }

    public RichPopup getPopGuiaOK() {
        return popGuiaOK;
    }

    public void setSocEstManif(RichSelectOneChoice soc1) {
        this.socEstManif = soc1;
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

    public void setD2(RichDialog d2) {
        this.d2 = d2;
    }

    public RichDialog getD2() {
        return d2;
    }
    
    public void setPb1(RichPanelBox pb1) {
        this.pb1 = pb1;
    }

    public RichPanelBox getPb1() {
        return pb1;
    }

    public void setPb2(RichPanelBox pb2) {
        this.pb2 = pb2;
    }

    public RichPanelBox getPb2() {
        return pb2;
    }

    public void setPopImg(RichPopup p1) {
        this.popImg = p1;
    }

    public RichPopup getPopImg() {
        return popImg;
    }

    public void setGl1(RichGoLink gl1) {
        this.gl1 = gl1;
    }

    public RichGoLink getGl1() {
        return gl1;
    }

    public void setImgGuia(RichImage i1) {
        this.imgGuia = i1;
    }

    public RichImage getImgGuia() {
        return imgGuia;
    }

    public void setFileImg(RichInputFile if1) {
        this.fileImg = if1;
    }

    public RichInputFile getFileImg() {
        return fileImg;
    }

    public void setDiagImg(RichDialog diagImg) {
        this.diagImg = diagImg;
    }

    public RichDialog getDiagImg() {
        return diagImg;
    }

    public void setBtnImg(RichCommandButton cb4) {
        this.btnImg = cb4;
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

    public void setBtnAddUndMed(RichCommandButton cb5) {
        this.btnAddUndMed = cb5;
    }

    public RichCommandButton getBtnAddUndMed() {
        return btnAddUndMed;
    }

    public void setIt3(RichInputText it3) {
        this.it3 = it3;
    }

    public RichInputText getIt3() {
        return it3;
    }

    public void setItCodManif(RichInputText it15) {
        this.itCodManif = it15;
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

    public void setSpiPaq(RichInputNumberSpinbox ins1) {
        this.spiPaq = ins1;
    }

    public RichInputNumberSpinbox getSpiPaq() {
        return spiPaq;
    }

    public void setTxtPeso(RichInputNumberSpinbox ins1) {
        this.txtPeso = ins1;
    }

    public RichInputNumberSpinbox getTxtPeso() {
        return txtPeso;
    }


    public void setPopEst(RichPopup p1) {
        this.popEst = p1;
    }

    public RichPopup getPopEst() {
        return popEst;
    }

    public void setDiaEst(RichDialog d3) {
        this.diaEst = d3;
    }

    public RichDialog getDiaEst() {
        return diaEst;
    }

    public void setSbcEstOS(RichSelectBooleanCheckbox sbc1) {
        this.sbcEstOS = sbc1;
    }

    public RichSelectBooleanCheckbox getSbcEstOS() {
        return sbcEstOS;
    }

    public void setSbcEstManif(RichSelectBooleanCheckbox sbc1) {
        this.sbcEstManif = sbc1;
    }

    public RichSelectBooleanCheckbox getSbcEstManif() {
        return sbcEstManif;
    }

    public void setBtnNewUM(RichCommandButton cb5) {
        this.btnNewUM = cb5;
    }

    public RichCommandButton getBtnNewUM() {
        return btnNewUM;
    }

    public void setItDesc(RichInputText it15) {
        this.itDesc = it15;
    }

    public RichInputText getItDesc() {
        return itDesc;
    }

    public void setItUniMed(RichInputText it15) {
        this.itUniMed = it15;
    }

    public RichInputText getItUniMed() {
        return itUniMed;
    }

    public void setBtnAnular(RichCommandButton cb5) {
        this.btnAnular = cb5;
    }

    public RichCommandButton getBtnAnular() {
        return btnAnular;
    }

    public void setPopUM(RichPopup popUM) {
        this.popUM = popUM;
    }

    public RichPopup getPopUM() {
        return popUM;
    }

    public void setBtnAsignarManifiesto(RichCommandButton cb5) {
        this.btnAnular = cb5;
    }

    public RichCommandButton getBtnAsignarManifiesto() {
        return btnAnular;
    }

    public void setPopAnul(RichPopup p1) {
        this.popAnul = p1;
    }

    public RichPopup getPopAnul() {
        return popAnul;
    }

    public void setTxtCantidad(RichInputText it15) {
        this.txtCantidad = it15;
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

    public void setCkbTraPro(RichSelectBooleanCheckbox sbc1) {
        this.ckbTraPro = sbc1;
    }

    public RichSelectBooleanCheckbox getCkbTraPro() {
        return ckbTraPro;
    }


    public void setFecha_Minima(Date fecha_Minima) {
        this.fecha_Minima = fecha_Minima;
    }

    public Date getFecha_Minima() {
        return fecha_Minima;
    }

    public void setFecha_Maxima(Date fecha_Maxima) {
        this.fecha_Maxima = fecha_Maxima;
    }

    public Date getFecha_Maxima() {
        return fecha_Maxima;
    }


  /*  public void setPopupSeleccionarManifiesto(RichPopup p1) {
        this.popupSeleccionarManifiesto = p1;
    }

    public RichPopup getPopupSeleccionarManifiesto() {
        return popupSeleccionarManifiesto;
    }

    public void setPfl12(RichPanelFormLayout pfl12) {
        this.pfl12 = pfl12;
    }

    public RichPanelFormLayout getPfl12() {
        return pfl12;
    }

    public void setCb5(RichCommandButton cb5) {
        this.btnAsignarManifiesto = cb5;
    }

    public RichCommandButton getCb5() {
        return btnAsignarManifiesto;
    }*/

  

}
