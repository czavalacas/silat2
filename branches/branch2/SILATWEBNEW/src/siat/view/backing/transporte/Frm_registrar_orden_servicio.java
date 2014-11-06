package siat.view.backing.transporte;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.InputStream;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

import javax.imageio.ImageIO;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.servlet.ServletContext;

import oracle.adf.model.bean.DCDataRow;
import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.RichSubform;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputNumberSpinbox;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichDecorativeBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.event.DialogEvent;

import oracle.adf.view.rich.render.ClientEvent;

import oracle.jbo.Row;

import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import org.apache.myfaces.trinidad.model.UploadedFile;

import siat.view.backing.utiles.ADFUtil;
import siat.view.backing.utiles.Utils;

import siat.view.backing.utiles.fecha.FechaUtiles;

import silat.servicios_negocio.BDLSF.IR.BDL_C_SFEmpresasRemote;
import silat.servicios_negocio.Beans.BeanADRelacionEmpresa;
import silat.servicios_negocio.Beans.BeanDireccion;
import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanOrdenServicio;
import silat.servicios_negocio.Beans.BeanTRItem;
import silat.servicios_negocio.Beans.BeanUnidadMedida;
import silat.servicios_negocio.Beans.BeanUnidadNegocio;
import silat.servicios_negocio.Beans.BeanUsuarioAutenticado;
import silat.servicios_negocio.LNSF.IR.LN_C_SFDireccionRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFFlotaRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFGuiaRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFOrdenServicioRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFRelacionEmpresaRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFUnidadMedidaRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFUnidadNegocioRemote;
import silat.servicios_negocio.LNSF.IR.LN_T_SFUnidadMedidaRemote;


public class Frm_registrar_orden_servicio {

    private RichDecorativeBox db1;
    private RichPanelGroupLayout pgl1;
    private RichOutputText ot1;
    private RichPanelFormLayout pfl1;
    private RichInputText it1;
    private RichInputDate id1;
    private RichCommandButton btnGrabar;
    private RichCommandButton cb2;
    private RichPopup popClientes;
    private RichPanelFormLayout pflROS;
    private RichInputText itNomEp;
    private RichCommandButton btnBuscarEmp;
    private RichTable tbCli;
    private RichInputText it4;
    private RichInputText it2;
    private RichSubform sbROS;
    private RichDialog drOS;
    /*------------------------Mis Variables ------------------*/
    private final static String LOOKUP_NAME_SFC_EMPR_REMOTO = "mapBDL_C_SFEmpresas";
    private final static String LOOKUP_NAME_SFORDSERV_REMOTO  = "mapLN_C_SFOrdenServicio";
    private final static String LOOKUP_NAME_SFT_UND_MEDIDA_REMOTO = "mapLN_T_SFUnidadMedida";
    private final static String LOOKUP_NAME_SFC_RELA_REMOTO       = "mapLN_C_SFRelacionEmpresa";
    private final static String LOOKUP_NAME_SFDIRECCION_REMOTO    = "LUBAL_SIAT_APP-SILATNEGOCIO-LN_C_SFDireccion";
    private final static String LOOKUP_NAME_SFUN_REMOTO           = "mapLN_C_SFUnidadNegocio";
    private final static String LOOKUP_NAME_SFC_UND_MEDIDA_REMOTO = "mapLN_C_SFUnidadMedida";    
    private BDL_C_SFEmpresasRemote bdL_C_SFEmpresasRemote;
    private LN_C_SFOrdenServicioRemote ln_C_SFOrdenServicioRemote;
    private LN_T_SFUnidadMedidaRemote ln_T_SFUnidadMedidaRemote;
    private LN_C_SFRelacionEmpresaRemote ln_C_SFRelacionEmpresaRemote;
    private LN_C_SFDireccionRemote ln_C_SFDireccionRemote;
    private LN_C_SFUnidadNegocioRemote ln_C_SFUnidadNegocioRemote;
    private LN_C_SFUnidadMedidaRemote ln_C_SFUnidadMedidaRemote;    
    private String nombreEmpresa;
    private BigDecimal nidPartyEmpresa;
    private BeanOrdenServicio beanOrdenServicio = new BeanOrdenServicio();
    private BeanEmpresa beanEmpresa = new BeanEmpresa();
    private SessionScopeRegistrarOS beanSessionScopedRegistrarOS;
    private String error = "";
    private String cidError = "";
    private Date fechaHoy = new Date();
    private String cDescItem;
    private String cUndMedida;
    private int dPeso;
    private Double nCantidad;
    private String cCidGuiaRemitente;
    private BeanTRItem beanIteam = new BeanTRItem();
    
    FacesContext ctx = FacesContext.getCurrentInstance();
    private RichInputText itNidOS;

    private Date fecha_Minima;
    private Date fecha_Maxima;

    private RichCommandButton btnNewItem;
    private RichCommandButton btnEditarItem;
    private RichCommandButton btnBorrarItem;
    private RichCommandButton btnImg;
    private RichCommandButton btnAddUndMed;
    private RichTable tblItms;
    private RichPopup popItems;
    private RichInputText txtCantidad;
    private RichSelectOneChoice socUndMed;
    private RichInputNumberSpinbox txtPeso;
    private RichInputText txtDescBien;
    private RichInputText txtGuiRem;
    private RichCommandButton btnRegistrarItem;
    private RichPopup popUM;
    private RichInputText itDesc;
    private RichInputText itUniMed;
    private RichCommandButton btnNewUM;
    private RichInputText razonSocProve;
    private RichInputText rucProve;
    private RichSelectOneChoice socDirecRemi;
    private RichInputText razSocClie;
    private RichInputText rucClie;
    private RichSelectOneChoice socDirecs;
    private RichPopup popRemit;
    private RichTable tbRemit;
    private RichPopup popImg;
    private RichDialog diagImg;
    private RichInputFile fileImg;
    private RichImage imgGuia;


    public Frm_registrar_orden_servicio(){
        try{
            final Context ctx;
            ctx = new InitialContext();
            bdL_C_SFEmpresasRemote = (BDL_C_SFEmpresasRemote)               ctx.lookup(LOOKUP_NAME_SFC_EMPR_REMOTO);
            ln_C_SFOrdenServicioRemote = (LN_C_SFOrdenServicioRemote)       ctx.lookup(LOOKUP_NAME_SFORDSERV_REMOTO);
            ln_C_SFUnidadNegocioRemote = (LN_C_SFUnidadNegocioRemote)       ctx.lookup(LOOKUP_NAME_SFUN_REMOTO);
            this.setFecha_Minima(FechaUtiles.fechaPast7());
            this.setFecha_Maxima(FechaUtiles.fechaFoward7());
            ln_T_SFUnidadMedidaRemote = (LN_T_SFUnidadMedidaRemote)         ctx.lookup(LOOKUP_NAME_SFT_UND_MEDIDA_REMOTO);
            ln_C_SFRelacionEmpresaRemote = (LN_C_SFRelacionEmpresaRemote)   ctx.lookup(LOOKUP_NAME_SFC_RELA_REMOTO);
            ln_C_SFDireccionRemote = (LN_C_SFDireccionRemote)               ctx.lookup(LOOKUP_NAME_SFDIRECCION_REMOTO);
            ln_C_SFUnidadMedidaRemote = (LN_C_SFUnidadMedidaRemote)         ctx.lookup(LOOKUP_NAME_SFC_UND_MEDIDA_REMOTO);
        }catch(Exception e){
            e.printStackTrace();
            Utils.redireccionar("/silat/faces/frm_login");
        }
    }
    
    @PostConstruct
    public void methodInvokeOncedOnPageLoad(){
        if(beanSessionScopedRegistrarOS.getExec() == 0){
            mostrarRemitentes();
            beanSessionScopedRegistrarOS.setExec(1);
            beanSessionScopedRegistrarOS.setNidOSGenerado(ln_C_SFOrdenServicioRemote.traerSiguienteValorSequence());
        }else{
            beanSessionScopedRegistrarOS.setNidOSGenerado(ln_C_SFOrdenServicioRemote.traerSiguienteValorSequence());
        }
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
    
    public ArrayList llenarUndMedidaCombo() {
        ArrayList umItems = new ArrayList();
        List<BeanUnidadMedida> consts = ln_C_SFUnidadMedidaRemote.getUnidadesMedida_LN();
        for (BeanUnidadMedida r : consts) {
            umItems.add(new SelectItem(r.getSigla().toString(), 
                                       r.getDescUnidadMedida().toString()));
        }
        return umItems;
    }
    
    public String buscarEmpresasCliente(){
        getBeanSessionScopedRegistrarOS().setLstEmpresasCliente(this.bdL_C_SFEmpresasRemote.getADEmpresabyName(getBeanSessionScopedRegistrarOS().getRazSocial()));
        return null;
    }
    
    public Object seleccionar(SelectionEvent selectionEvent){
        RichTable t = (RichTable)selectionEvent.getSource();
        Object _selectedRowData = t.getSelectedRowData();
        BeanEmpresa empresa = (BeanEmpresa) _selectedRowData;
      //  FacesCtrlHierNodeBinding rowData = (FacesCtrlHierNodeBinding)t.getSelectedRowData();
        Object nidPar = empresa.getNidParty();//rowData.getAttribute("nidParty");
        Object razSol = empresa.getCRazonSocial();//rowData.getAttribute("CRazonSocial");
        setNidPartyEmpresa(new BigDecimal(nidPar+""));
        
       // ADFUtil.setEL("#{pageFlowScope.nidParty}", nidPar);
        
        beanSessionScopedRegistrarOS.setNidPartyOS(empresa.getNidParty());
        getBeanSessionScopedRegistrarOS().setLstDirecs(this.llenarDireccionCombo(null,empresa.getNidParty().intValue(), null));
        Utils.addTarget(socDirecs);
        try{
            it1.setValue(razSol);
            it4.setValue(new BigDecimal(nidPar+""));
            btnGrabar.setDisabled(false);
            razSocClie.setValue(empresa.getCRazonSocial());
            rucClie.setValue(empresa.getCRuc());
            Utils.addTargetMany(it4,it1,btnGrabar,razSocClie,rucClie);
            cerrarPopUp();
        }catch(Exception e){
            System.out.println("\n\n\n\n\n\n\nError inesperado al seleccionar empresa\n\n\n\n");
        }
        return razSol;
    }
    
    public void abrirPopEmp(ActionEvent ae) {
        buscarEmpresasCliente();
        Utils.showPopUpMIDDLE(popClientes);
    }
    
    public void registrarOS_ActionListener(ActionEvent actionEvent) {//action
        try{
            
            beanOrdenServicio= ln_C_SFOrdenServicioRemote.grabarOrdenServicio(beanSessionScopedRegistrarOS.getNidPartyOS(), 
                                                                              beanSessionScopedRegistrarOS.getCDetalleOS(), 
                                                                              beanSessionScopedRegistrarOS.getFechaHoy(),
                                                                              beanSessionScopedRegistrarOS.getCidDirecDestino(),
                                                                              beanSessionScopedRegistrarOS.getCidDirecRemitente(),
                                                                              beanSessionScopedRegistrarOS.getNidRemitente(),
                                                                              beanSessionScopedRegistrarOS.getLstItems());
            
            
           /* ADFUtil.setEL("#{pageFlowScope.fecha}",beanSessionScopedRegistrarOS.getFechaHoy());
            Utils.mandarParametro("fecha", "#{pageFlowScope.fecha}", "grabarOrdenServicio");
            Utils.mandarParametro("nidParty", "#{pageFlowScope.nidParty}", "grabarOrdenServicio");
            beanOrdenServicio = (BeanOrdenServicio)ADFUtil.invokeEL("#{bindings.grabarOrdenServicio.execute}");*/
            setError(beanOrdenServicio.getOutput());
            setCidError(beanOrdenServicio.getCidError());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public String registrarAction(){//listener (2)
        if(getCidError().equals("000")){
            Utils.throwError_Aux(ctx,getError(), 3);
            Utils._redireccionar(ctx,"/WEB-INF/frm_actualizar_orden_servicio.xml#frm_actualizar_orden_servicio");
        }else{
            Utils.throwError_Aux(ctx,getError(), 1);
        }
        return null;
    }
    
    public String cerrarPopUp() {      
         popClientes.hide();  
         return null;
    }

    public void seleccionEmpresa(ActionEvent e){
        it1.setValue(beanEmpresa.getCRazonSocial());
        it4.setValue(beanEmpresa.getNidParty());
        Utils.addTargetMany(it4,it1);
    }
    
    public void editarItem(ActionEvent actionEvent) {
        resetearItems();
        beanIteam = new BeanTRItem();
        beanIteam = beanSessionScopedRegistrarOS.getBeanIteam();
        txtCantidad.setValue(beanIteam.getNCantidad());
        socUndMed.setValue(beanIteam.getCUndMedida());
        txtPeso.setValue(beanIteam.getDPeso());
        txtGuiRem.setValue(beanIteam.getCCidGuiaRemitente());
        txtDescBien.setValue(beanIteam.getCDescItem());
        beanSessionScopedRegistrarOS.setAccion(2); //Modificar
    }
    
    public void borrarItem(ActionEvent actionEvent) {
        beanIteam = new BeanTRItem();
        beanIteam = getBeanSessionScopedRegistrarOS().getBeanIteam();
        int ordenBorrar = beanIteam.getOrden();
        int size = beanSessionScopedRegistrarOS.getLstItems().size();
        if(ordenBorrar < size){
            if(ordenBorrar > 1){
                for(BeanTRItem itm :beanSessionScopedRegistrarOS.getLstItems()){
                    if(itm.getOrden() > 1){
                        itm.setOrden(itm.getOrden() - 1);    
                    }
                }
            }else{
                for(BeanTRItem itm : beanSessionScopedRegistrarOS.getLstItems()){
                    itm.setOrden(itm.getOrden() - 1);
                }
            }
        }
        beanSessionScopedRegistrarOS.getLstItems().remove(beanIteam);
        controlarBotones(true);
        Utils.unselectFilas(tblItms);
    }
    
    public void controlarBotones(boolean estado){
        btnEditarItem.setDisabled(estado);
        btnBorrarItem.setDisabled(estado);
        Utils.addTargetMany(btnEditarItem,btnBorrarItem);
    }
    
    public void dialogImgok(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
            if(getBeanSessionScopedRegistrarOS().getImgGuiaProvAux() != null){
                getBeanSessionScopedRegistrarOS().setImgGuiaProv(getBeanSessionScopedRegistrarOS().getImgGuiaProvAux());
            }
        }
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
                String cidGuiaFull = getBeanSessionScopedRegistrarOS().getCodUN()+"-"+getBeanSessionScopedRegistrarOS().getCidGuia();
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
                getBeanSessionScopedRegistrarOS().setImgGuiaProvAux(rutaImg);
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
    
    public void onDialogImagenCancel(ClientEvent ce) {
        getBeanSessionScopedRegistrarOS().setImgGuiaProv(null);
        getBeanSessionScopedRegistrarOS().setImgGuiaProvAux(null);
        imgGuia.setSource(getBeanSessionScopedRegistrarOS().getImgGuiaProv());
        Utils.addTarget(imgGuia);
        FacesContext fctx = FacesContext.getCurrentInstance();
        fctx.renderResponse();
        popImg.hide();
    }
    
    public void abrirPopImg(ActionEvent ae) {
        ///imageservlet?cidguia=#{sessionScope.beanSessionRegistrarGuia.cidGuia}&#38;cidunin=#{sessionScope.beanSessionRegistrarGuia.codUN}
        String rutaImg = "/imageservlet?cidguia="+beanSessionScopedRegistrarOS.getCidGuia()+"&cidunin="+beanSessionScopedRegistrarOS.getCodUN();
        beanSessionScopedRegistrarOS.setRutaImgGuia(rutaImg);
        if(imgGuia != null){
            imgGuia.setSource(rutaImg);
            Utils.addTarget(imgGuia);
        }
        Utils.showPopUpMIDDLE(popImg);
    }
    
    public void openPopUp(ActionEvent actionEvent){
        beanSessionScopedRegistrarOS.setLstUndMedida(this.llenarUndMedidaCombo());
        this.setCCidGuiaRemitente("Segun Guia Remitente "+ (razonSocProve.getValue() == null ? "" : razonSocProve.getValue()) +" #: ");    
        beanSessionScopedRegistrarOS.setAccion(1);//Grabar
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
    
    public void changeDirecDest(ValueChangeEvent vce) {
        try{
            String val = vce.getNewValue().toString();
            if(getBeanSessionScopedRegistrarOS().getCidDirecRemitente() != null){
                if(getBeanSessionScopedRegistrarOS().getCidDirecRemitente().equals(val)){
                    getBeanSessionScopedRegistrarOS().setCidDirecDestino("0");
                    socDirecs.setValue("0");
                    Utils.throwError_Aux(ctx,"Debe seleccionar una direccion distinta a la remitente.",4);
                }else{
                    getBeanSessionScopedRegistrarOS().setCidDirecDestino(val);
                }
            }else{
                getBeanSessionScopedRegistrarOS().setCidDirecDestino(val);
            }
        }catch(Exception e){
            getBeanSessionScopedRegistrarOS().setCidDirecDestino("0");
            socDirecs.setValue("0");
            Utils.throwError_Aux(ctx,"Debe seleccionar una direccion",4);
        }
        Utils.addTarget(socDirecs);
    }
    
    public void changeDireccRemi(ValueChangeEvent vce) {
        try{
            String val = vce.getNewValue().toString();
            if(getBeanSessionScopedRegistrarOS().getCidDirecDestino() != null){
                if(getBeanSessionScopedRegistrarOS().getCidDirecDestino().equals(val)){
                    getBeanSessionScopedRegistrarOS().setCidDirecRemitente("0");
                    socDirecRemi.setValue("0");
                    Utils.throwError_Aux(ctx,"Debe seleccionar una direccion distinta a la de destino.",4);
                }else{
                    getBeanSessionScopedRegistrarOS().setCidDirecRemitente(val);
                }
            }else{
                getBeanSessionScopedRegistrarOS().setCidDirecRemitente(val);
            }
        }catch(Exception e){
            getBeanSessionScopedRegistrarOS().setCidDirecRemitente("0");
            socDirecRemi.setValue("0");
            Utils.throwError_Aux(ctx,"Debe seleccionar una direccion",4);
        }
        Utils.addTarget(socDirecRemi);
    }
    
    public void seleccionarItem(SelectionEvent selectionEvent) {
        if(getBeanSessionScopedRegistrarOS().isDisableComponents() == false){
            controlarBotones(false);
            RichTable t = (RichTable)selectionEvent.getSource();
            Object _selectedRowData = t.getSelectedRowData();
            beanIteam = (BeanTRItem) _selectedRowData;
            beanSessionScopedRegistrarOS.setBeanIteam(beanIteam);  
        }
    }
    
    public void registrarItem(ActionEvent actionEvent) {
        int accion = beanSessionScopedRegistrarOS.getAccion();
        resetearItems();
        BeanTRItem beanItm = new BeanTRItem();
        if(accion == 2){
            beanItm = beanSessionScopedRegistrarOS.getBeanIteam();
        }else{
            int orden = 1;
            if (beanSessionScopedRegistrarOS.getLstItems() != null) {
                if (beanSessionScopedRegistrarOS.getLstItems().size() >= 1) {
                    orden = beanSessionScopedRegistrarOS.getLstItems().size() + 1;
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
            beanSessionScopedRegistrarOS.getLstItems().add(itmClon);
        }
       popItems.hide();
       btnBorrarItem.setDisabled(true);
       btnEditarItem.setDisabled(true);
       beanSessionScopedRegistrarOS.setAccion(1);
       Utils.unselectFilas(tblItms);
       Utils.addTargetMany(btnBorrarItem,btnEditarItem);
    }
    
    public void agregarUM(ActionEvent ae) {
        try {
            if(beanSessionScopedRegistrarOS.getDescUM() != null && beanSessionScopedRegistrarOS.getValUM() != null){
                BeanUnidadMedida bUM = ln_T_SFUnidadMedidaRemote.registrarUnidadMedida(beanSessionScopedRegistrarOS.getDescUM(),beanSessionScopedRegistrarOS.getValUM());
                if (bUM != null) {
                    int severidad = 4;
                    BeanError bError = bUM.getBeanError();
                    if (bError.getCidError().equals("000")) {
                        severidad = 3;
                    }
                    Utils.throwError_Aux(ctx, bError.getCDescripcionError(),severidad);
                    if(bError.getCidError().equals("000")){
                        popUM.hide();
                        beanSessionScopedRegistrarOS.setDescUM(null);
                        beanSessionScopedRegistrarOS.setValUM(null);
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
    
    public String mostrarRemitentes(){
        String razRemi = getBeanSessionScopedRegistrarOS().getRazSocRemitente();
        String ruc = getBeanSessionScopedRegistrarOS().getRucRemi();
        getBeanSessionScopedRegistrarOS().setLstRemitentes(this.ln_C_SFRelacionEmpresaRemote.getEmpresaProveedoresCliente((razRemi == null ? "" : razRemi.toUpperCase()),
                                                                                                                          (ruc == null ? "" : ruc.toUpperCase())));
        Utils.unselectFilas(tbRemit);
        return null;
    }
    
    public void seleccionarRemitente(SelectionEvent selectionEvent){
        RichTable t = (RichTable)selectionEvent.getSource();
        Object _selectedRowData = t.getSelectedRowData();
        BeanADRelacionEmpresa rela = (BeanADRelacionEmpresa) _selectedRowData;
        getBeanSessionScopedRegistrarOS().setNidRemitente(rela.getAdEmpresa1().getNidParty().intValue());
        getBeanSessionScopedRegistrarOS().setLstDirecsRemi(this.llenarDireccionCombo(null,rela.getAdEmpresa1().getNidParty().intValue(), null));
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
    
    public ArrayList llenarDireccionCombo(Integer nidDireccion,
                                          Integer nidParty,
                                          String cDireccion){
        ArrayList direcsItems = new ArrayList();
        List<BeanDireccion> direcs = ln_C_SFDireccionRemote.getDireccionByProp_LN(nidDireccion,nidParty,cDireccion);
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
    
    
    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }

    public void setOt1(RichOutputText ot1) {
        this.ot1 = ot1;
    }
    
    public void setDb1(RichDecorativeBox db1) {
        this.db1 = db1;
    }

    public RichDecorativeBox getDb1() {
        return db1;
    }
    
    public RichOutputText getOt1() {
        return ot1;
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


    public void setId1(RichInputDate id1) {
        this.id1 = id1;
    }

    public RichInputDate getId1() {
        return id1;
    }

    public void setBtnGrabar(RichCommandButton cb1) {
        this.btnGrabar = cb1;
    }

    public RichCommandButton getBtnGrabar() {
        return btnGrabar;
    }


    public void setCb2(RichCommandButton cb2) {
        this.cb2 = cb2;
    }

    public RichCommandButton getCb2() {
        return cb2;
    }

    public void setPopClientes(RichPopup p1) {
        this.popClientes = p1;
    }

    public RichPopup getPopClientes() {
        return popClientes;
    }

    public void setPflROS(RichPanelFormLayout pfl2) {
        this.pflROS = pfl2;
    }

    public RichPanelFormLayout getPflROS() {
        return pflROS;
    }

    public void setBtnBuscarEmp(RichCommandButton cb3) {
        this.btnBuscarEmp = cb3;
    }

    public RichCommandButton getBtnBuscarEmp() {
        return btnBuscarEmp;
    }

    public void setTbCli(RichTable t1) {
        this.tbCli = t1;
    }

    public RichTable getTbCli() {
        return tbCli;
    }


    public void setIt4(RichInputText it4) {
        this.it4 = it4;
    }

    public RichInputText getIt4() {
        return it4;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNidPartyEmpresa(BigDecimal nidPartyEmpresa) {
        this.nidPartyEmpresa = nidPartyEmpresa;
    }

    public BigDecimal getNidPartyEmpresa() {
        return nidPartyEmpresa;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setCidError(String cidError) {
        this.cidError = cidError;
    }

    public String getCidError() {
        return cidError;
    }

    public void setIt2(RichInputText it2) {
        this.it2 = it2;
    }

    public RichInputText getIt2() {
        return it2;
    }

    public void setBeanOrdenServicio(BeanOrdenServicio beanOrdenServicio) {
        this.beanOrdenServicio = beanOrdenServicio;
    }

    public BeanOrdenServicio getBeanOrdenServicio() {
        return beanOrdenServicio;
    }

    public void setBeanEmpresa(BeanEmpresa beanEmpresa) {
        this.beanEmpresa = beanEmpresa;
    }

    public BeanEmpresa getBeanEmpresa() {
        return beanEmpresa;
    }

    public void setFechaHoy(Date fechaHoy) {
        this.fechaHoy = fechaHoy;
    }

    public Date getFechaHoy() {
        return fechaHoy;
    }

    public void setSbROS(RichSubform s1) {
        this.sbROS = s1;
    }

    public RichSubform getSbROS() {
        return sbROS;
    }

    public void setDrOS(RichDialog d1) {
        this.drOS = d1;
    }

    public RichDialog getDrOS() {
        return drOS;
    }

    public void setBeanSessionScopedRegistrarOS(SessionScopeRegistrarOS beanSessionScopedRegistrarOS) {
        this.beanSessionScopedRegistrarOS = beanSessionScopedRegistrarOS;
    }

    public SessionScopeRegistrarOS getBeanSessionScopedRegistrarOS() {
        return beanSessionScopedRegistrarOS;
    }

    public void setItNomEp(RichInputText itNomEmp) {
        this.itNomEp = itNomEmp;
    }

    public RichInputText getItNomEp() {
        return itNomEp;
    }

    public void setItNidOS(RichInputText it3) {
        this.itNidOS = it3;
    }

    public RichInputText getItNidOS() {
        return itNidOS;
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


    public void setBtnNewItem(RichCommandButton btnNewItem) {
        this.btnNewItem = btnNewItem;
    }

    public RichCommandButton getBtnNewItem() {
        return btnNewItem;
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

    public void setBtnImg(RichCommandButton btnImg) {
        this.btnImg = btnImg;
    }

    public RichCommandButton getBtnImg() {
        return btnImg;
    }

    public void setBtnAddUndMed(RichCommandButton btnAddUndMed) {
        this.btnAddUndMed = btnAddUndMed;
    }

    public RichCommandButton getBtnAddUndMed() {
        return btnAddUndMed;
    }

    public void setTblItms(RichTable tblItms) {
        this.tblItms = tblItms;
    }

    public RichTable getTblItms() {
        return tblItms;
    }

    public void setPopItems(RichPopup popItems) {
        this.popItems = popItems;
    }

    public RichPopup getPopItems() {
        return popItems;
    }

    public void setTxtCantidad(RichInputText txtCantidad) {
        this.txtCantidad = txtCantidad;
    }

    public RichInputText getTxtCantidad() {
        return txtCantidad;
    }

    public void setSocUndMed(RichSelectOneChoice socUndMed) {
        this.socUndMed = socUndMed;
    }

    public RichSelectOneChoice getSocUndMed() {
        return socUndMed;
    }

    public void setTxtPeso(RichInputNumberSpinbox txtPeso) {
        this.txtPeso = txtPeso;
    }

    public RichInputNumberSpinbox getTxtPeso() {
        return txtPeso;
    }

    public void setTxtDescBien(RichInputText txtDescBien) {
        this.txtDescBien = txtDescBien;
    }

    public RichInputText getTxtDescBien() {
        return txtDescBien;
    }

    public void setTxtGuiRem(RichInputText txtGuiRem) {
        this.txtGuiRem = txtGuiRem;
    }

    public RichInputText getTxtGuiRem() {
        return txtGuiRem;
    }

    public void setBtnRegistrarItem(RichCommandButton btnRegistrarItem) {
        this.btnRegistrarItem = btnRegistrarItem;
    }

    public RichCommandButton getBtnRegistrarItem() {
        return btnRegistrarItem;
    }

    public void setPopUM(RichPopup popUM) {
        this.popUM = popUM;
    }

    public RichPopup getPopUM() {
        return popUM;
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

    public void setSocDirecRemi(RichSelectOneChoice socDirecRemi) {
        this.socDirecRemi = socDirecRemi;
    }

    public RichSelectOneChoice getSocDirecRemi() {
        return socDirecRemi;
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

    public void setSocDirecs(RichSelectOneChoice socDirecs) {
        this.socDirecs = socDirecs;
    }

    public RichSelectOneChoice getSocDirecs() {
        return socDirecs;
    }

    public void setPopRemit(RichPopup popRemit) {
        this.popRemit = popRemit;
    }

    public RichPopup getPopRemit() {
        return popRemit;
    }

    public void setTbRemit(RichTable tbRemit) {
        this.tbRemit = tbRemit;
    }

    public RichTable getTbRemit() {
        return tbRemit;
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

    public void setCCidGuiaRemitente(String cCidGuiaRemitente) {
        this.cCidGuiaRemitente = cCidGuiaRemitente;
    }

    public String getCCidGuiaRemitente() {
        return cCidGuiaRemitente;
    }

    public void setPopImg(RichPopup popImg) {
        this.popImg = popImg;
    }

    public RichPopup getPopImg() {
        return popImg;
    }

    public void setDiagImg(RichDialog diagImg) {
        this.diagImg = diagImg;
    }

    public RichDialog getDiagImg() {
        return diagImg;
    }

    public void setFileImg(RichInputFile fileImg) {
        this.fileImg = fileImg;
    }

    public RichInputFile getFileImg() {
        return fileImg;
    }

    public void setImgGuia(RichImage imgGuia) {
        this.imgGuia = imgGuia;
    }

    public RichImage getImgGuia() {
        return imgGuia;
    }

}
