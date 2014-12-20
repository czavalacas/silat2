package backingWebMovilEmp;

import java.io.IOException;

import java.math.BigDecimal;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.faces.component.UIOutput;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import javax.naming.Context;
import javax.naming.InitialContext;

import silat.servicios_negocio.Beans.BeanADRelacionEmpresa;
import silat.servicios_negocio.Beans.BeanChofer;
import silat.servicios_negocio.Beans.BeanDireccion;
import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.Beans.BeanFlota;
import silat.servicios_negocio.Beans.BeanManifiesto;
import silat.servicios_negocio.Beans.BeanOrdenServicio;
import silat.servicios_negocio.Beans.BeanTRGuia;
import silat.servicios_negocio.Beans.BeanTRItem;
import silat.servicios_negocio.Beans.BeanTRItemsWebMovil;
import silat.servicios_negocio.Beans.BeanTrItemXOrds;
import silat.servicios_negocio.Beans.BeanUnidadMedida;
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
import silat.servicios_negocio.LNSF.IR.LN_C_SFUsuarioRemote;

import silat.servicios_negocio.LNSF.IR.LN_C_SFUtilsRemote;
import silat.servicios_negocio.LNSF.IR.LN_T_SFGuiaRemote;
import silat.servicios_negocio.LNSF.IR.LN_T_SFItemxOrdsRemota;
import silat.servicios_negocio.LNSF.SFBean.LN_C_SFFlotaBean;


import silat.servicios_negocio.LNSF.SFBean.LN_T_SFItemxOrdsBean;

import utils.system;


@ManagedBean
@ViewScoped
public class Frm_WebMovilEmp {
    
    //CONEXCIONES
    private LN_C_SFOrdenServicioRemote lN_C_SFOrdenServicioRemote;
    private final static String LOOKUP_NAME_SFORDENSERVICIO_REMOTO = "mapLN_C_SFOrdenServicio";
    private LN_C_SFManifiestoRemote ln_C_SFManifiestoRemote;
    private final static String LOOKUP_NAME_SFCMANIF_REMOTO = "mapLN_C_SFManifiesto";
    private LN_C_SFRelacionEmpresaRemote ln_C_SFRelacionEmpresaRemote;
    private final static String LOOKUP_NAME_SFC_RELA_REMOTO = "mapLN_C_SFRelacionEmpresa";
    private LN_C_SFChoferRemote ln_C_SFChoferRemote;
    private final static String LOOKUP_NAME_SFCHOFER_REMOTO = "mapLN_C_SFChofer";
    private LN_C_SFFlotaRemote ln_C_SFFlotaRemote;
    private final static String LOOKUP_NAME_SFFLOTA_REMOTO = "mapLN_C_SFFlota";
    private LN_T_SFManifiestoRemote ln_T_SFManifiestoRemote;
    private final static String LOOKUP_NAME_SFMANIFIESTO_REMOTO   = "mapLN_T_SFManifiesto";
    private LN_C_SFEmpresasRemote ln_C_SFEmpresasRemote;
    private final static String LOOKUP_NAME_SFEMPRESA_REMOTO      = "mapLN_C_SFEmpresas";
    private LN_C_SFDireccionRemote LN_C_SFDireccionRemote;
    private final static String LOOKUP_NAME_SFDIRECCION_REMOTO    = "LUBAL_SIAT_APP-SILATNEGOCIO-LN_C_SFDireccion";
    private LN_T_SFGuiaRemote ln_T_SFGuiaRemote;
    private final static String LOOKUP_NAME_SFGUIA_REMOTO         = "mapLN_T_SFGuia";
    private LN_C_SFUtilsRemote ln_C_SFUtilsRemote;
    private final static String LOOKUP_NAME_SFC_UTL_REMOTO        = "mapLN_C_SFUtils";
    
    private LN_T_SFItemxOrdsRemota lN_T_SFItemxOrdsRemota;
    private final static String LOOKUP_NAME_SFITEMORDS_REMOTO        = "map-LN_T_SFItemxOrds";
    private LN_C_SFGuiaRemote ln_C_SFGuiaRemote;
    private final static String LOOKUP_NAME_SFCGUIA_REMOTO = "mapLN_C_SFGuia";
    private LN_C_SFUnidadMedidaRemote ln_C_SFUnidadMedidaRemote; 
    private final static String LOOKUP_NAME_SFC_UND_MEDIDA_REMOTO = "mapLN_C_SFUnidadMedida";
    
    //VARIABLES LISTAS
    private List<BeanOrdenServicio> listaOrdenServicio = new ArrayList<BeanOrdenServicio>();
    private List<BeanManifiesto> listaManifiesto = new ArrayList<BeanManifiesto>();
    private List<BeanManifiesto> listaManifiestoElegir = new ArrayList<BeanManifiesto>();
    private List<BeanADRelacionEmpresa> listaProov = new ArrayList<BeanADRelacionEmpresa>();
    private List<BeanFlota> lstFlotas = new ArrayList<BeanFlota>();
    private List<BeanChofer> lstChofers = new ArrayList<BeanChofer>();
    
    private List<BeanTRGuia> lstGuiasdeManifiesto = new ArrayList<BeanTRGuia>();
    
    private List<BeanTrItemXOrds> lstItemsOrds = new ArrayList<BeanTrItemXOrds>();
    private List<BeanTrItemXOrds> lstItemsOrdsRespal = new ArrayList<BeanTrItemXOrds>();
    
    List<BeanADRelacionEmpresa> lstRemitentes = new ArrayList<BeanADRelacionEmpresa>();
    
    List<BeanDireccion> direcsRemin = new ArrayList<BeanDireccion>();
    
    List<BeanDireccion> direcsDest = new ArrayList<BeanDireccion>();
    
    List<BeanUnidadMedida> lstUndMedida = new ArrayList<BeanUnidadMedida>();
    
    private List<String> mensajeManif = new ArrayList<String>();
    
    private List<String> mensajeGuia = new ArrayList<String>();
    
    //OTRAS VARIABLES
    private String bienvenida="Bienvenido Usuario";
    private int exec = 0;
    public String action;
    private String RazonSocialEmpresaElegida;
    private String rucEmpresaElegida;
    private BigDecimal nidPartyEmpresaElegida;
    private String fechaRegManifiesto;
    private String fletePactado;
    private String adelanto;
    private String comentario;
    private boolean checkBox;
    private boolean checkBox1;
    private boolean checkBox2;
    private boolean cerrarOS;
    private int varia = 0;
    private int varia1 = 0;
    private int varia2 = 0;
    private boolean manifTransito;
    private int tipoDoc = 1;
    private String flotaElegida;
    private String choferElegido;
    private String busquedaRemitente;
    private String busquedaProveedor;
    
    private String nombreChofer;
    private String nombreFlota;
    
    private String ordenServElegida;
    private String nombreEmpresaOrds;
    private String dateOrds;
    private String comentarioOrds;
    private String nidOrds;
    
    private String nidRemitenteElegido;
    private String nombreRemitente;
    private String RucRemitente;
    
    private String nidDireccionRemitente;
    private String nombreDireccionRemitente;
    
    private String nidDestinoElegido;
    private String nidDireccionDestino;
    private String nombreDireccionDestino;
    
    private String nidManifiestoEleg;
    
    private String comentarioGuia;
    private String nPaquetes;
    
    private String nidFlotaEleg;
    private String nidChofEleg;
    
    private Date fechaTrans = new Date();
    private Date fechaEmis = new Date();
    
    private String nidRemitenteGuardar;
    
    private String nidItem;
    
    private String styleCreaGuia = "display:none";
    private String styleLastForm ="display:none"; 
    
    private String styleItemsDetalleOrdenServicio = "overflow-y: scroll;height:150px";
    private String styleItemsCrearGuia = "overflow-y: scroll;height:280px;margin-top:10px;margin-bottom:10px";
    
    private String ManifiestoElegido;
    
    private String fechaMnifiestoInfo;
    private String fletePactadoInfo;
    private String adelantoInfo;
    private String observacionesInfo;
    
    private String nidUmedida;
    private String cantidadNewItem;
    private String descripcionNewItem;
    
    //STYLES
    private String StylerazonSocialManifiesto = "background-color: #DDDDDD;color:#000000;";
    
    private String mensajeManifiesto = "";
    


    public Frm_WebMovilEmp() {
        try {
        final Context ctx;
        ctx = new InitialContext();
        lN_C_SFOrdenServicioRemote = (LN_C_SFOrdenServicioRemote)ctx.lookup(LOOKUP_NAME_SFORDENSERVICIO_REMOTO);
        ln_C_SFManifiestoRemote = (LN_C_SFManifiestoRemote) ctx.lookup(LOOKUP_NAME_SFCMANIF_REMOTO);
        ln_C_SFRelacionEmpresaRemote = (LN_C_SFRelacionEmpresaRemote)ctx.lookup(LOOKUP_NAME_SFC_RELA_REMOTO);
        ln_C_SFChoferRemote = (LN_C_SFChoferRemote)ctx.lookup(LOOKUP_NAME_SFCHOFER_REMOTO); 
        ln_C_SFFlotaRemote = (LN_C_SFFlotaRemote)ctx.lookup(LOOKUP_NAME_SFFLOTA_REMOTO);
        ln_C_SFEmpresasRemote = (LN_C_SFEmpresasRemote)ctx.lookup(LOOKUP_NAME_SFEMPRESA_REMOTO);
        ln_T_SFManifiestoRemote = (LN_T_SFManifiestoRemote)ctx.lookup(LOOKUP_NAME_SFMANIFIESTO_REMOTO);
        LN_C_SFDireccionRemote = (LN_C_SFDireccionRemote)               ctx.lookup(LOOKUP_NAME_SFDIRECCION_REMOTO);
        ln_T_SFManifiestoRemote = (LN_T_SFManifiestoRemote)             ctx.lookup(LOOKUP_NAME_SFMANIFIESTO_REMOTO);
        ln_C_SFUtilsRemote = (LN_C_SFUtilsRemote)                       ctx.lookup(LOOKUP_NAME_SFC_UTL_REMOTO);
        ln_T_SFGuiaRemote = (LN_T_SFGuiaRemote)                         ctx.lookup(LOOKUP_NAME_SFGUIA_REMOTO);
        lN_T_SFItemxOrdsRemota = (LN_T_SFItemxOrdsRemota)       ctx.lookup(LOOKUP_NAME_SFITEMORDS_REMOTO);
        ln_C_SFGuiaRemote = (LN_C_SFGuiaRemote) ctx.lookup(LOOKUP_NAME_SFCGUIA_REMOTO);
        ln_C_SFUnidadMedidaRemote = (LN_C_SFUnidadMedidaRemote)         ctx.lookup(LOOKUP_NAME_SFC_UND_MEDIDA_REMOTO);
        
        }catch(Exception e){}
    }
    @PostConstruct
    public void methodInvokeOncedOnPageLoad() {
        if (getExec() == 0) {
            setExec(1);
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            setFechaRegManifiesto(sdf.format(date));
            setListaOrdenServicio(lN_C_SFOrdenServicioRemote.ordenServicioPendiente());
            setListaManifiesto(ln_C_SFManifiestoRemote.getListaManifsSinAsignar());
            setListaProov(ln_C_SFRelacionEmpresaRemote.getEmpresaProveedores(""));
            setLstRemitentes(this.ln_C_SFRelacionEmpresaRemote.getEmpresaProveedoresCliente("",""));
            setLstUndMedida(this.llenarUndMedidaCombo());
        } else {
        }
    }
    
    public String añardirnuevoItem(){
        try{
            String g = nidUmedida;
            BeanTrItemXOrds bean = new BeanTrItemXOrds();
            String cidGuia  = ln_C_SFUtilsRemote.generarCorrelativoLN("TRGuia","G",6,"001"); 
            bean.setCCidGuiaRemitente(cidGuia);
            bean.setCDescItem(getDescripcionNewItem());
            bean.setCUndMedida(getNidUmedida());
            double cantidad = Double.parseDouble(getCantidadNewItem());
            bean.setNCantidad(cantidad);
            //vista
            bean.setDetalleWebmovilCantidad(cantidad);
            bean.setDetalleWebmovilDescripcion(getDescripcionNewItem());
            bean.setDetalleWebmovilUmedida(getNidUmedida());            
            getLstItemsOrdsRespal().add(bean);
            getLstItemsOrds().add(bean);
            
            if(getLstItemsOrdsRespal().size() == 5){
                setStyleItemsCrearGuia("overflow-y: scroll;height:390px;margin-top:10px;margin-bottom:10px");
            }
            if(getLstItemsOrdsRespal().size() == 4){
                setStyleItemsCrearGuia("overflow-y: scroll;height:320px;margin-top:10px;margin-bottom:10px");
            }
            if(getLstItemsOrdsRespal().size() == 3){
                setStyleItemsCrearGuia("overflow-y: scroll;height:250px;margin-top:10px;margin-bottom:10px");
            }
            if(getLstItemsOrdsRespal().size() == 2){
                setStyleItemsCrearGuia("overflow-y: scroll;height:160px;margin-top:10px;margin-bottom:10px");
            }
            if(getLstItemsOrdsRespal().size() == 1){
                setStyleItemsCrearGuia("overflow-y: scroll;height:100px;margin-top:10px;margin-bottom:10px");
            }
            if(getLstItemsOrdsRespal().size() == 0){
                setStyleItemsCrearGuia("overflow-y: scroll;height:0px;margin-top:10px;margin-bottom:10px");
            }
            
            setDescripcionNewItem("");
            setCantidadNewItem("");
            
        }catch(Exception e){
                setDescripcionNewItem("");
                setCantidadNewItem("");
            }
        return "";
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
    
    public String traeInfoManifiessto(){
        String  h = ManifiestoElegido;
        BeanManifiesto bean = new BeanManifiesto();
        int g = Integer.parseInt(h);
        bean.setNidManifiesto(g);
        List<BeanManifiesto> manif= ln_C_SFManifiestoRemote._findManifiestosByAttr_LN(null, null, g, null, null, null, null, null, null, null, null, null);
        
        setFechaMnifiestoInfo(manif.get(0).getFechaManifiesto().getDate()+"/"+manif.get(0).getFechaManifiesto().getMonth()+"/"+(manif.get(0).getFechaManifiesto().getYear()-100));
        setFletePactadoInfo(manif.get(0).getNFletePactado()+"");
        setAdelantoInfo(manif.get(0).getNAdelanto()+"");
        setObservacionesInfo(manif.get(0).getCObservaciones());
        
        setLstGuiasdeManifiesto(ln_C_SFGuiaRemote.getGuiasByManifiesto_LN(g));
        
        return "";
    }
    
    public String restaurarItems(){
        setLstItemsOrdsRespal(lN_C_SFOrdenServicioRemote.ItemsbyOrdenServicio(ordenServElegida));
        return "";
    }
    
    public String eliminarItem(){
        String h = nidItem;
        int index = Integer.parseInt(h);
        getLstItemsOrdsRespal().remove(index);
        setStyleCreaGuia("display:block");
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("creaGuia");
        return "";
    }
    public String registrarGuia(){
        System.out.println("IMPRIME MIERDA!!");
        boolean entro = true;
        getMensajeGuia().clear();
        if(nidDireccionDestino == null){
            getMensajeGuia().add("Elegir Direccion Destino");
            entro = false;
        }
        if(nidRemitenteElegido == null){
            getMensajeGuia().add("Elegir Remitente");
            entro = false;
        }
        if(nidDireccionRemitente == null){
            getMensajeGuia().add("Elegir Direccion Remitente");
            entro = false;
        }
        if(nidManifiestoEleg == null){
            getMensajeGuia().add("Elegir Manifiesto");
            entro = false;
        }
        if (!getComentarioGuia().equals("")) {
        } else {
            getMensajeGuia().add("Insertar Comentario");
            entro = false;
        }
        if (!getNPaquetes().equals("")) {
        } else {
            getMensajeGuia().add("Insertar n°Paquetes");
            entro = false;
        }
        System.out.println("FECHA EMIS : "+getFechaEmis());
    /*    if(getFechaEmis().before(new Date())){
            getMensajeGuia().add("Elegir una Fecha de Emision Mayor");
            entro = false;
        }
        System.out.println("FECHA TRANS : "+getFechaTrans());
        if(getFechaTrans().before(new Date())){
            getMensajeGuia().add("Elegir una Fecha de Translado Mayor");
            entro = false;
        }*/
        ln_C_SFUtilsRemote.SystemOutPrint1nWebMovil(getFechaEmis(), getFechaTrans());
        if(getFechaEmis().after(getFechaTrans())){
            getMensajeGuia().add("Elegir una Fecha de Translado mayor a Fecha de Emision");
            entro = false;
        }
        if(entro == true){
        try{
        int npaq = Integer.parseInt(getNPaquetes());
        String cidGuia = "";
        cidGuia  = ln_C_SFUtilsRemote.generarCorrelativoLN("TRGuia","G",6,"001"); 
        String conf = "2";
        String estGuia = "1";
        Date fechaEmision = getFechaEmis();
        Date fechaDespacho = getFechaTrans();
            
        Date fechaActual = new Date();
            if(fechaEmision.before(fechaActual)){
                String g = "";
                int i = Integer.parseInt(g);
            }
            if(fechaDespacho.before(fechaActual)){
                String g = "";
                int i = Integer.parseInt(g);
            }
            if(fechaEmision.after(fechaDespacho)){
                String g = "";
                int i = Integer.parseInt(g);
            }
            if(getComentarioGuia().trim().equals("")){
                String g = "";
                int i = Integer.parseInt(g);
            }
            if(getComentarioGuia().trim().equals("")){
                String g = "";
                int i = Integer.parseInt(g);
            }
            
        int nidRemitente = Integer.parseInt(nidRemitenteElegido);
        int nidOS = Integer.parseInt(ordenServElegida);
        int nidManif = Integer.parseInt(nidManifiestoEleg);
        
        String h = nidManifiestoEleg;
        int g = Integer.parseInt(h);
        List<BeanManifiesto> manif= ln_C_SFManifiestoRemote._findManifiestosByAttr_LN(null, null, g, null, null, null, null, null, null, null, null, null);
        int nidFlota = Integer.parseInt(""+manif.get(0).getNidFlota());
        int nidChofer = Integer.parseInt(manif.get(0).getNidChof()+"");
        int nidDirecRemi = Integer.parseInt(nidDireccionRemitente);
        int nidDirecDest = Integer.parseInt(nidDireccionDestino);
        int opc = Integer.parseInt("1");
        String codUn = "001";
        String estadoManif = "1";        
        List<BeanTRItem> lstItems = new ArrayList<BeanTRItem>();   
        int num=1;
        for(BeanTrItemXOrds bean : getLstItemsOrdsRespal()){
            BeanTRGuia guia = new BeanTRGuia();
            BeanTRItem bean1 = new BeanTRItem();
            bean1.setNidItem(bean.getNidItem());
            bean1.setCDescItem(bean.getDetalleWebmovilDescripcion());
            bean1.setCUndMedida(bean.getDetalleWebmovilUmedida());
            bean1.setNCantidad(bean.getDetalleWebmovilCantidad());
            bean1.setDPeso(bean.getDPeso());   
            bean1.setOrden(num);
            guia.setCidGuia(cidGuia);
            bean1.setTrGuia(guia);
            bean1.setCidGuia(cidGuia);
            lstItems.add(bean1);     
            System.out.println("webmovil_EMP 1 "+num);
            num=num+1;
        }
            System.out.println("webmovil_EMP 2");
        BeanTRGuia bGuia = ln_T_SFGuiaRemote.registrarGuia_LN(cidGuia, 
                                                              npaq, 
                                                              getComentarioGuia(), 
                                                              conf, 
                                                              estGuia, 
                                                              fechaEmision, 
                                                              fechaDespacho, 
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
                                                              null,
                                                              isCerrarOS(),
                                                              isManifTransito());
            FacesContext ctx = FacesContext.getCurrentInstance();
            ExternalContext extContext = ctx.getExternalContext();
            String url = extContext.encodeActionURL(ctx.getApplication().getViewHandler().getActionURL(ctx, "/faces/Prueba.xhtml"));
            try {
                extContext.redirect(url);
            } catch (IOException ioe) {
                throw new FacesException(ioe);
            }
        }catch(Exception e){
            
        }
        }else{
            setStyleLastForm("display:block;");
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("lastForm");
        }
        return "";
    }
    
    public String traeInfoManifiesto(){
        String h = nidManifiestoEleg;
        int g = Integer.parseInt(h);
        List<BeanManifiesto> manif= ln_C_SFManifiestoRemote._findManifiestosByAttr_LN(null, null, g, null, null, null, null, null, null, null, null, null);
        
        setStyleCreaGuia("display:block");
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("creaGuia");
        return "";
    }
    
    public String traeInfoDireccionDestino(){
        String h = nidDireccionDestino;
        String g = nidDestinoElegido;
        int nidDire = Integer.parseInt(h);
        int nidEmp = Integer.parseInt(g);
        List<BeanDireccion> direccion = LN_C_SFDireccionRemote.getDireccionByProp_LN(nidDire,nidEmp,null);
        setNombreDireccionDestino(direccion.get(0).getCDireccion());
        
        setStyleCreaGuia("display:block");
        
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("creaGuia");
        return "";
    
    
    
    }
    public String traeInfoDireccionRemitente(){
        String h = nidDireccionRemitente;
        String g = nidRemitenteElegido;
        int nidDire = Integer.parseInt(h);
        int nidEmp = Integer.parseInt(g);
        List<BeanDireccion> direccion = LN_C_SFDireccionRemote.getDireccionByProp_LN(nidDire,nidEmp,null);
        setNombreDireccionRemitente(direccion.get(0).getCDireccion());
        
        setStyleCreaGuia("display:block");
        
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("creaGuia");
        return "";
    }
    
    public String traeInfoRemitente(){
        setNombreDireccionRemitente("");
        setNombreDireccionRemitente("");
        setNidManifiestoEleg("");
        setNombreDireccionRemitente("");
        setNombreRemitente("");
        setFechaEmis(new Date());
        setFechaTrans(new Date());
        
        String h = nidRemitenteElegido;
        BeanEmpresa empresa = ln_C_SFEmpresasRemote.selectedEmpresa(new BigDecimal(h));
        setNombreRemitente(empresa.getCRazonSocial());
        setStyleCreaGuia("display:block");
        
        setNombreDireccionRemitente("");
        
        setNidRemitenteGuardar(h);
        
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("creaGuia");
        
        int nid = Integer.parseInt(h);
        setDirecsRemin(LN_C_SFDireccionRemote.getDireccionByProp_LN(null,nid,null));
        
        return "";
    }
    
    public String traeInfoOrdenServicio(){
        setNidDireccionDestino(null);
        setNidRemitenteElegido(null);
        setNidDireccionRemitente(null);
        setNidManifiestoEleg(null);
        setNombreDireccionDestino(null);
        setNombreDireccionRemitente(null);
        setNombreRemitente(null);
        String  h = ordenServElegida;
        BeanOrdenServicio bean = new BeanOrdenServicio();
        int g = Integer.parseInt(h);
        bean.setNidOrdnServ(g);
        List <BeanOrdenServicio> lstBeanOrd = lN_C_SFOrdenServicioRemote.findOrdenServicioByAttributesAux(bean);
        setNombreEmpresaOrds(lstBeanOrd.get(0).getDetalleWebmovilEmpresa());
        setComentarioOrds(lstBeanOrd.get(0).getDetalleWebmovilComentario());
        
           SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss");
           String folderName = formatter.format(lstBeanOrd.get(0).getFecOrdnServ());
        setDateOrds(folderName);
        setNidOrds(lstBeanOrd.get(0).getNidOrdnServ()+"");
        
        
        setNidDestinoElegido(lstBeanOrd.get(0).getDetalleWebmovilNIDEmpresa());
        int t = Integer.parseInt(lstBeanOrd.get(0).getDetalleWebmovilNIDEmpresa());
        
        setDirecsDest(LN_C_SFDireccionRemote.getDireccionByProp_LN(null,t,null));
        
        getDirecsRemin().clear();

        
        setStyleCreaGuia("display:none");
        
        
        
        setLstItemsOrds(lN_C_SFOrdenServicioRemote.ItemsbyOrdenServicio(ordenServElegida));
        if(getLstItemsOrds().size() == 5){
            setStyleItemsDetalleOrdenServicio("overflow-y: scroll;height:390px");
            setStyleItemsCrearGuia("overflow-y: scroll;height:390px;margin-top:10px;margin-bottom:10px");
        }
        if(getLstItemsOrds().size() == 4){
            setStyleItemsDetalleOrdenServicio("overflow-y: scroll;height:320px");
            setStyleItemsCrearGuia("overflow-y: scroll;height:320px;margin-top:10px;margin-bottom:10px");
        }
        if(getLstItemsOrds().size() == 3){
            setStyleItemsDetalleOrdenServicio("overflow-y: scroll;height:250px");
            setStyleItemsCrearGuia("overflow-y: scroll;height:250px;margin-top:10px;margin-bottom:10px");
        }
        if(getLstItemsOrds().size() == 2){
            setStyleItemsDetalleOrdenServicio("overflow-y: scroll;height:160px");
            setStyleItemsCrearGuia("overflow-y: scroll;height:160px;margin-top:10px;margin-bottom:10px");
        }
        if(getLstItemsOrds().size() == 1){
            setStyleItemsDetalleOrdenServicio("overflow-y: scroll;height:100px");
            setStyleItemsCrearGuia("overflow-y: scroll;height:100px;margin-top:10px;margin-bottom:10px");
        }
        if(getLstItemsOrds().size() == 0){
            setStyleItemsDetalleOrdenServicio("overflow-y: scroll;height:0px");
            setStyleItemsCrearGuia("overflow-y: scroll;height:0px;margin-top:10px;margin-bottom:10px");
        }
        setLstItemsOrdsRespal(lN_C_SFOrdenServicioRemote.ItemsbyOrdenServicio(ordenServElegida));
        setListaManifiestoElegir(ln_C_SFManifiestoRemote.getListaManifsSinAsignar());
        //HCO
        String h1 = "";
        if (lstBeanOrd.get(0).getNidRemitente() == null) {
        setNombreRemitente("No tiene");
        setNidRemitenteElegido(null);
        } else {
        h1 = lstBeanOrd.get(0).getNidRemitente()+"";
        BeanEmpresa empresa = ln_C_SFEmpresasRemote.selectedEmpresa(new BigDecimal(h1));
        setNombreRemitente(empresa.getCRazonSocial());
        setNidRemitenteElegido(h1);
        }
        if(lstBeanOrd.get(0).getNidRemitente() == null){
            
        }else{
            int nid = Integer.parseInt(h1);
            setDirecsRemin(LN_C_SFDireccionRemote.getDireccionByProp_LN(null,nid,null)); 
        }
        if(lstBeanOrd.get(0).getNidRemitente() == null || lstBeanOrd.get(0).getNidDirecProv()==null){
        
        }
        else{
        String h3 = lstBeanOrd.get(0).getNidDirecProv();
        int nidDire = Integer.parseInt(h3);
        int nidEmp = Integer.parseInt(h1);
        List<BeanDireccion> direccion = LN_C_SFDireccionRemote.getDireccionByProp_LN(nidDire,nidEmp,null);
        setNombreDireccionRemitente(direccion.get(0).getCDireccion());
        setNidDireccionDestino(direccion.get(0).getNidDireccion()+"");
        }
        
        getMensajeGuia().clear();
        String h4 = "";
        String g4 = "";
        if(lstBeanOrd.get(0).getNidDirecCli() == null || getNidDestinoElegido() == null){

        }else{
            h4 = lstBeanOrd.get(0).getNidDirecCli();
            g4 = getNidDestinoElegido();
            int nidDirec = Integer.parseInt(h4);
            int nidEmpc = Integer.parseInt(g4);
            List<BeanDireccion> direccion1 = LN_C_SFDireccionRemote.getDireccionByProp_LN(nidDirec,nidEmpc,null);
            setNombreDireccionDestino(direccion1.get(0).getCDireccion());
            setNidDireccionRemitente(direccion1.get(0).getNidDireccion()+"");
        }
        
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("detOrden");
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("creaGuia");
        
        return "";
    }

    public String traeinfoFlota() {
        String h = flotaElegida;
        int nidParty = Integer.parseInt(h);
        for(BeanFlota bean : getLstFlotas()){
            if(bean.getNidFlota().equals(nidParty)){
                setNombreFlota(""+bean.getNidFlota());
            }
        }
        return "";
    }
    
    public String traeinfoChofer() {
        if(varia == 1){
        String h = choferElegido;
        int nidParty = Integer.parseInt(h);
        List<BeanChofer> lista = ln_C_SFChoferRemote.findChofersByAttr_LN(5, nidParty);
        setNombreChofer(lista.get(0).getNombres());
        }else{
        String h = choferElegido;
        int nidParty = Integer.parseInt(h);
        int nidPartyEmpresa = Integer.parseInt(getNidPartyEmpresaElegida()+"");
        List<BeanChofer> lista = ln_C_SFChoferRemote.findChofersByAttr_LN(nidPartyEmpresa, nidParty);
        setNombreChofer(lista.get(0).getNombres());
        }
        return "";
    }
    
    public String editAction() {
                String h = action;
                BeanEmpresa empresa = ln_C_SFEmpresasRemote.selectedEmpresa(new BigDecimal(h));
                setRazonSocialEmpresaElegida(empresa.getCRazonSocial());
                setRucEmpresaElegida(empresa.getCRuc());
                setNidPartyEmpresaElegida(empresa.getNidParty());
                int nid = Integer.parseInt(getNidPartyEmpresaElegida()+"");
                setLstFlotas(ln_C_SFFlotaRemote.getFlotasPorEmpresa(nid));
                setLstChofers(ln_C_SFChoferRemote.traerChoferesPorEmpresa(nid));
                setFlotaElegida("");
                setChoferElegido("");
                setNombreChofer("");
                setNombreFlota("");
                setComentarioGuia("");
                setNPaquetes("");
                setFechaEmis(new Date());
                setFechaTrans(new Date());
                setFlotaElegida(null);
                setChoferElegido(null);
                FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("regManifiesto1");
                FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("regManifiesto2");
               return "";
            }
    
    public boolean onDelete(AjaxBehaviorEvent event){
        if(varia == 0){
            setLstFlotas(ln_C_SFFlotaRemote.getFlotasPorEmpresa(5));
            setLstChofers(ln_C_SFChoferRemote.traerChoferesPorEmpresa(5));
            
            BeanEmpresa empresa = ln_C_SFEmpresasRemote.selectedEmpresa(new BigDecimal(5));
            setRazonSocialEmpresaElegida(empresa.getCRazonSocial());
            setRucEmpresaElegida(empresa.getCRuc());
            setNidPartyEmpresaElegida(empresa.getNidParty());
            System.out.println("Entroo1");
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("regManifiesto");
            setTipoDoc(1);
            setFlotaElegida("");
            setChoferElegido("");
            setNombreChofer("");
            setNombreFlota("");
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("regManifiesto1");
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("regManifiesto2");
            varia = 1;
        }else{
            setRazonSocialEmpresaElegida("");
            setRucEmpresaElegida("");
            BigDecimal nn = null;
            setNidPartyEmpresaElegida(nn);
            
            getLstFlotas().clear();
            getLstChofers().clear();
            
            setFlotaElegida("");
            setChoferElegido("");
            setNombreChofer("");
            setNombreFlota("");
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("regManifiesto1");
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("regManifiesto2");
            
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("regManifiesto");
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("modChof");
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("modFlot");
            setTipoDoc(2);
            varia = 0;
        }
        return true;
    }
    
    public boolean cerrarOS(AjaxBehaviorEvent event){
        if(varia1 == 0){
            setCerrarOS(true);
            varia1 = 1;
        }else{
            setCerrarOS(false);
            varia1 = 0;
        }
        
        
        return true;
        
    }
    
    public boolean manifiestoEnTransito(AjaxBehaviorEvent event){
        if(varia2 == 0){
            setManifTransito(true);
            varia2 = 1;
        }else{
            setManifTransito(false);
            varia2 = 0;
        }
        
        
        return true;
        
    }
    
    public String buscarRmitentes(){
        String razRemi = getBusquedaRemitente();
        setLstRemitentes(this.ln_C_SFRelacionEmpresaRemote.getEmpresaProveedoresCliente((razRemi == null ? "" : razRemi.toUpperCase()),""));
        setStyleCreaGuia("display:block");
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("creaGuia");
        return "";
    }
    
    public String buscarProovedor(){
        String razRemi = getBusquedaProveedor();
        setListaProov(ln_C_SFRelacionEmpresaRemote.getEmpresaProveedores(razRemi == null ? "" : razRemi.toUpperCase()));
        return "";
    }
    

    public String registrarManifiesto(){
        getMensajeManif().clear();
        getMensajeManif().add("FALTA:");
        String msm = "";
        boolean entro = true;
        if(getNidPartyEmpresaElegida() == null){
            entro = false;
            msm = "Elegir Empresa";
            getMensajeManif().add(msm);
        }
        if(getFlotaElegida() == null){
            entro = false;
            msm = "Elegir Flota";
            getMensajeManif().add(msm);
        }
        if(getChoferElegido() == null){
            entro = false;
            msm = "Elegir Chofer";
            getMensajeManif().add(msm);
        }
        if (!getAdelanto().equals("")) {
        } else {
            entro = false;
            msm = "Insertar Adelanto";
            getMensajeManif().add(msm);
        }
        if (!getFletePactado().equals("")) {
        } else {
            entro = false;
            msm = "Insertar flete pactado";
            getMensajeManif().add(msm);
        }
        if (!getComentario().equals("")) {
        } else {
            entro = false;
            msm += "Insertar Comentario";
            getMensajeManif().add(msm);
        }
        if(entro == true){
        try{
            Date date  =  new Date();
            Double pactado= Double.parseDouble(getFletePactado());
            Double adel= Double.parseDouble(getAdelanto());
            Double igv = pactado + (pactado*0.18);
            Double montoFinal = igv - (igv*0.04);
            Double saldo = montoFinal- adel;
         BeanManifiesto bManifiesto = ln_T_SFManifiestoRemote.registrarManifiesto_LN(Integer.parseInt(""+getNidPartyEmpresaElegida()),
                                                                                    date, 
                                                                                    pactado,
                                                                                    Double.parseDouble(getAdelanto()),
                                                                                    ""+getTipoDoc(),
                                                                                    getComentario(),
                                                                                    Integer.parseInt(getFlotaElegida()),
                                                                                    Integer.parseInt(getChoferElegido()),
                                                                                    0,1,"3");//Guias x asignar 
         
        setRucEmpresaElegida("");
        setRazonSocialEmpresaElegida("");
        setNombreFlota("");
        setNombreChofer("");
        setFletePactado("");
        setAdelanto("");
        setComentario("");
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("regManifiesto");
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("regManifiesto1");
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("regManifiesto2");
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("regManifiesto3");
            
            FacesContext ctx = FacesContext.getCurrentInstance();
            ExternalContext extContext = ctx.getExternalContext();
            String url = extContext.encodeActionURL(ctx.getApplication().getViewHandler().getActionURL(ctx, "/faces/Prueba.xhtml"));
            try {
                extContext.redirect(url);
            } catch (IOException ioe) {
                
            }
        
        }catch(Exception e){
            getMensajeManif().add("Algo Ocurrio");
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("regManifiesto3");
        }
        }else{
            
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("regManifiesto3");
        }
        return "";
    }
    
    public String limpiarRegManifiesto(){
        setRazonSocialEmpresaElegida("");
        setNidPartyEmpresaElegida(null);
        setFlotaElegida(null);
        setChoferElegido(null);
        getMensajeManif().clear();
        setRucEmpresaElegida("");
        setNombreFlota("");
        setNombreChofer("");
        setFletePactado("");
        setAdelanto("");
        setComentario("");
        getLstChofers().clear();
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("regManifiesto");
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("regManifiesto1");
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("regManifiesto2");
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("regManifiesto3");
        return "";
    }
    
    public String mostrarManifiesto(){
        setListaManifiesto(ln_C_SFManifiestoRemote.getListaManifsSinAsignar());
        return "";
    }
    
    public String motrarOrdenServ(){
        System.out.println("oServ");
        setListaOrdenServicio(lN_C_SFOrdenServicioRemote.ordenServicioPendiente());
        return "";
    }
    
    public String redirectLogin() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        String url = extContext.encodeActionURL(ctx.getApplication().getViewHandler().getActionURL(ctx, "/frm_login"));
        System.out.println("URL emp:"+url);
        String h = url.replaceAll("LUBAL_SIAT_APP-SILATWEBMOVILEMP-context-root", "silat");
        try {                                       
            extContext.redirect(h);
        } catch (IOException ioe) {
            throw new FacesException(ioe);
        }
        return null; 
    }

    public void setBienvenida(String bienvenida) {
        this.bienvenida = bienvenida;
    }

    public String getBienvenida() {
        return bienvenida;
    }

    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }

    public void setListaOrdenServicio(List<BeanOrdenServicio> listaOrdenServicio) {
        this.listaOrdenServicio = listaOrdenServicio;
    }

    public List<BeanOrdenServicio> getListaOrdenServicio() {
        return listaOrdenServicio;
    }

    public void setListaManifiesto(List<BeanManifiesto> listaManifiesto) {
        this.listaManifiesto = listaManifiesto;
    }

    public List<BeanManifiesto> getListaManifiesto() {
        return listaManifiesto;
    }

    public void setListaProov(List<BeanADRelacionEmpresa> listaProov) {
        this.listaProov = listaProov;
    }

    public List<BeanADRelacionEmpresa> getListaProov() {
        return listaProov;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setRazonSocialEmpresaElegida(String RazonSocialEmpresaElegida) {
        this.RazonSocialEmpresaElegida = RazonSocialEmpresaElegida;
    }

    public String getRazonSocialEmpresaElegida() {
        return RazonSocialEmpresaElegida;
    }

    public void setRucEmpresaElegida(String rucEmpresaElegida) {
        this.rucEmpresaElegida = rucEmpresaElegida;
    }

    public String getRucEmpresaElegida() {
        return rucEmpresaElegida;
    }

    public void setNidPartyEmpresaElegida(BigDecimal nidPartyEmpresaElegida) {
        this.nidPartyEmpresaElegida = nidPartyEmpresaElegida;
    }

    public BigDecimal getNidPartyEmpresaElegida() {
        return nidPartyEmpresaElegida;
    }

    public void setFechaRegManifiesto(String fechaRegManifiesto) {
        this.fechaRegManifiesto = fechaRegManifiesto;
    }

    public String getFechaRegManifiesto() {
        return fechaRegManifiesto;
    }

    public void setFletePactado(String fletePactado) {
        this.fletePactado = fletePactado;
    }

    public String getFletePactado() {
        return fletePactado;
    }

    public void setAdelanto(String adelanto) {
        this.adelanto = adelanto;
    }

    public String getAdelanto() {
        return adelanto;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setCheckBox(boolean checkBox) {
        this.checkBox = checkBox;
    }

    public boolean isCheckBox() {
        return checkBox;
    }

    public void setTipoDoc(int tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public int getTipoDoc() {
        return tipoDoc;
    }

    public void setLstFlotas(List<BeanFlota> lstFlotas) {
        this.lstFlotas = lstFlotas;
    }

    public List<BeanFlota> getLstFlotas() {
        return lstFlotas;
    }

    public void setLstChofers(List<BeanChofer> lstChofers) {
        this.lstChofers = lstChofers;
    }

    public List<BeanChofer> getLstChofers() {
        return lstChofers;
    }
    public void setNombreChofer(String nombreChofer) {
        this.nombreChofer = nombreChofer;
    }

    public String getNombreChofer() {
        return nombreChofer;
    }

    public void setChoferElegido(String choferElegido) {
        this.choferElegido = choferElegido;
    }

    public String getChoferElegido() {
        return choferElegido;
    }

    public void setFlotaElegida(String flotaElegida) {
        this.flotaElegida = flotaElegida;
    }

    public String getFlotaElegida() {
        return flotaElegida;
    }

    public void setNombreFlota(String nombreFlota) {
        this.nombreFlota = nombreFlota;
    }

    public String getNombreFlota() {
        return nombreFlota;
    }

    public void setOrdenServElegida(String ordenServElegida) {
        this.ordenServElegida = ordenServElegida;
    }

    public String getOrdenServElegida() {
        return ordenServElegida;
    }

    public void setNombreEmpresaOrds(String nombreEmpresaOrds) {
        this.nombreEmpresaOrds = nombreEmpresaOrds;
    }

    public String getNombreEmpresaOrds() {
        return nombreEmpresaOrds;
    }

    public void setComentarioOrds(String comentarioOrds) {
        this.comentarioOrds = comentarioOrds;
    }

    public String getComentarioOrds() {
        return comentarioOrds;
    }

    public void setDateOrds(String dateOrds) {
        this.dateOrds = dateOrds;
    }

    public String getDateOrds() {
        return dateOrds;
    }

    public void setNidOrds(String nidOrds) {
        this.nidOrds = nidOrds;
    }

    public String getNidOrds() {
        return nidOrds;
    }

    public void setLstItemsOrds(List<BeanTrItemXOrds> lstItemsOrds) {
        this.lstItemsOrds = lstItemsOrds;
    }

    public List<BeanTrItemXOrds> getLstItemsOrds() {
        return lstItemsOrds;
    }

    public void setLstRemitentes(List<BeanADRelacionEmpresa> lstRemitentes) {
        this.lstRemitentes = lstRemitentes;
    }

    public List<BeanADRelacionEmpresa> getLstRemitentes() {
        return lstRemitentes;
    }

    public void setNidRemitenteElegido(String nidRemitenteElegido) {
        this.nidRemitenteElegido = nidRemitenteElegido;
    }

    public String getNidRemitenteElegido() {
        return nidRemitenteElegido;
    }

    public void setNombreRemitente(String nombreRemitente) {
        this.nombreRemitente = nombreRemitente;
    }

    public String getNombreRemitente() {
        return nombreRemitente;
    }

    public void setRucRemitente(String RucRemitente) {
        this.RucRemitente = RucRemitente;
    }

    public String getRucRemitente() {
        return RucRemitente;
    }

    public void setStyleCreaGuia(String styleCreaGuia) {
        this.styleCreaGuia = styleCreaGuia;
    }

    public String getStyleCreaGuia() {
        return styleCreaGuia;
    }

    public void setDirecsRemin(List<BeanDireccion> direcsRemin) {
        this.direcsRemin = direcsRemin;
    }

    public List<BeanDireccion> getDirecsRemin() {
        return direcsRemin;
    }

    public void setNidDireccionRemitente(String nidDireccionRemitente) {
        this.nidDireccionRemitente = nidDireccionRemitente;
    }

    public String getNidDireccionRemitente() {
        return nidDireccionRemitente;
    }

    public void setNombreDireccionRemitente(String nombreDireccionRemitente) {
        this.nombreDireccionRemitente = nombreDireccionRemitente;
    }

    public String getNombreDireccionRemitente() {
        return nombreDireccionRemitente;
    }

    public void setDirecsDest(List<BeanDireccion> direcsDest) {
        this.direcsDest = direcsDest;
    }

    public List<BeanDireccion> getDirecsDest() {
        return direcsDest;
    }

    public void setNidDestinoElegido(String nidDestinoElegido) {
        this.nidDestinoElegido = nidDestinoElegido;
    }

    public String getNidDestinoElegido() {
        return nidDestinoElegido;
    }

    public void setNidDireccionDestino(String nidDireccionDestino) {
        this.nidDireccionDestino = nidDireccionDestino;
    }

    public String getNidDireccionDestino() {
        return nidDireccionDestino;
    }

    public void setNombreDireccionDestino(String nombreDireccionDestino) {
        this.nombreDireccionDestino = nombreDireccionDestino;
    }

    public String getNombreDireccionDestino() {
        return nombreDireccionDestino;
    }

    public void setListaManifiestoElegir(List<BeanManifiesto> listaManifiestoElegir) {
        this.listaManifiestoElegir = listaManifiestoElegir;
    }

    public List<BeanManifiesto> getListaManifiestoElegir() {
        return listaManifiestoElegir;
    }

    public void setNidManifiestoEleg(String nidManifiestoEleg) {
        this.nidManifiestoEleg = nidManifiestoEleg;
    }

    public String getNidManifiestoEleg() {
        return nidManifiestoEleg;
    }

    public void setComentarioGuia(String comentarioGuia) {
        this.comentarioGuia = comentarioGuia;
    }

    public String getComentarioGuia() {
        return comentarioGuia;
    }

    public void setNPaquetes(String nPaquetes) {
        this.nPaquetes = nPaquetes;
    }

    public String getNPaquetes() {
        return nPaquetes;
    }

    public void setNidFlotaEleg(String nidFlotaEleg) {
        this.nidFlotaEleg = nidFlotaEleg;
    }

    public String getNidFlotaEleg() {
        return nidFlotaEleg;
    }

    public void setNidChofEleg(String nidChofEleg) {
        this.nidChofEleg = nidChofEleg;
    }

    public String getNidChofEleg() {
        return nidChofEleg;
    }

    public void setFechaTrans(Date fechaTrans) {
        this.fechaTrans = fechaTrans;
    }

    public Date getFechaTrans() {
        return fechaTrans;
    }

    public void setFechaEmis(Date fechaEmis) {
        this.fechaEmis = fechaEmis;
    }

    public Date getFechaEmis() {
        return fechaEmis;
    }

    public void setNidRemitenteGuardar(String nidRemitenteGuardar) {
        this.nidRemitenteGuardar = nidRemitenteGuardar;
    }

    public String getNidRemitenteGuardar() {
        return nidRemitenteGuardar;
    }

    public void setStyleLastForm(String styleLastForm) {
        this.styleLastForm = styleLastForm;
    }

    public String getStyleLastForm() {
        return styleLastForm;
    }

    public void setLstItemsOrdsRespal(List<BeanTrItemXOrds> lstItemsOrdsRespal) {
        this.lstItemsOrdsRespal = lstItemsOrdsRespal;
    }

    public List<BeanTrItemXOrds> getLstItemsOrdsRespal() {
        return lstItemsOrdsRespal;
    }

    public void setNidItem(String nidItem) {
        this.nidItem = nidItem;
    }

    public String getNidItem() {
        return nidItem;
    }

    public void setCheckBox1(boolean checkBox1) {
        this.checkBox1 = checkBox1;
    }

    public boolean isCheckBox1() {
        return checkBox1;
    }

    public void setCerrarOS(boolean cerrarOS) {
        this.cerrarOS = cerrarOS;
    }

    public boolean isCerrarOS() {
        return cerrarOS;
    }

    public void setBusquedaRemitente(String busquedaRemitente) {
        this.busquedaRemitente = busquedaRemitente;
    }

    public String getBusquedaRemitente() {
        return busquedaRemitente;
    }

    public void setBusquedaProveedor(String busquedaProveedor) {
        this.busquedaProveedor = busquedaProveedor;
    }

    public String getBusquedaProveedor() {
        return busquedaProveedor;
    }

    public void setCheckBox2(boolean checkBox2) {
        this.checkBox2 = checkBox2;
    }

    public boolean isCheckBox2() {
        return checkBox2;
    }

    public void setManifTransito(boolean manifTransito) {
        this.manifTransito = manifTransito;
    }

    public boolean isManifTransito() {
        return manifTransito;
    }

    public void setStyleItemsDetalleOrdenServicio(String styleItemsDetalleOrdenServicio) {
        this.styleItemsDetalleOrdenServicio = styleItemsDetalleOrdenServicio;
    }

    public String getStyleItemsDetalleOrdenServicio() {
        return styleItemsDetalleOrdenServicio;
    }

    public void setStyleItemsCrearGuia(String styleItemsCrearGuia) {
        this.styleItemsCrearGuia = styleItemsCrearGuia;
    }

    public String getStyleItemsCrearGuia() {
        return styleItemsCrearGuia;
    }

    public void setManifiestoElegido(String ManifiestoElegido) {
        this.ManifiestoElegido = ManifiestoElegido;
    }

    public String getManifiestoElegido() {
        return ManifiestoElegido;
    }

    public void setFechaMnifiestoInfo(String fechaMnifiestoInfo) {
        this.fechaMnifiestoInfo = fechaMnifiestoInfo;
    }

    public String getFechaMnifiestoInfo() {
        return fechaMnifiestoInfo;
    }

    public void setFletePactadoInfo(String fletePactadoInfo) {
        this.fletePactadoInfo = fletePactadoInfo;
    }

    public String getFletePactadoInfo() {
        return fletePactadoInfo;
    }

    public void setAdelantoInfo(String adelantoInfo) {
        this.adelantoInfo = adelantoInfo;
    }

    public String getAdelantoInfo() {
        return adelantoInfo;
    }

    public void setObservacionesInfo(String observacionesInfo) {
        this.observacionesInfo = observacionesInfo;
    }

    public String getObservacionesInfo() {
        return observacionesInfo;
    }

    public void setLstGuiasdeManifiesto(List<BeanTRGuia> lstGuiasdeManifiesto) {
        this.lstGuiasdeManifiesto = lstGuiasdeManifiesto;
    }

    public List<BeanTRGuia> getLstGuiasdeManifiesto() {
        return lstGuiasdeManifiesto;
    }

    public void setLstUndMedida(List<BeanUnidadMedida> lstUndMedida) {
        this.lstUndMedida = lstUndMedida;
    }

    public List<BeanUnidadMedida> getLstUndMedida() {
        return lstUndMedida;
    }

    public void setNidUmedida(String nidUmedida) {
        this.nidUmedida = nidUmedida;
    }

    public String getNidUmedida() {
        return nidUmedida;
    }

    public void setCantidadNewItem(String cantidadNewItem) {
        this.cantidadNewItem = cantidadNewItem;
    }

    public String getCantidadNewItem() {
        return cantidadNewItem;
    }

    public void setDescripcionNewItem(String descripcionNewItem) {
        this.descripcionNewItem = descripcionNewItem;
    }

    public String getDescripcionNewItem() {
        return descripcionNewItem;
    }

    public void setStylerazonSocialManifiesto(String StylerazonSocialManifiesto) {
        this.StylerazonSocialManifiesto = StylerazonSocialManifiesto;
    }

    public String getStylerazonSocialManifiesto() {
        return StylerazonSocialManifiesto;
    }

    public void setMensajeManifiesto(String mensajeManifiesto) {
        this.mensajeManifiesto = mensajeManifiesto;
    }

    public String getMensajeManifiesto() {
        return mensajeManifiesto;
    }

    public void setMensajeManif(List<String> mensajeManif) {
        this.mensajeManif = mensajeManif;
    }

    public List<String> getMensajeManif() {
        return mensajeManif;
    }

    public void setMensajeGuia(List<String> mensajeGuia) {
        this.mensajeGuia = mensajeGuia;
    }

    public List<String> getMensajeGuia() {
        return mensajeGuia;
    }
}
