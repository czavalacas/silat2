package backingWebMovilEmp;

import java.io.IOException;

import java.math.BigDecimal;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
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
import silat.servicios_negocio.LNSF.IL.LN_T_SFManifiestoRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFChoferRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFDireccionRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFEmpresasRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFFlotaRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFManifiestoRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFOrdenServicioRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFRelacionEmpresaRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFUsuarioRemote;

import silat.servicios_negocio.LNSF.IR.LN_C_SFUtilsRemote;
import silat.servicios_negocio.LNSF.IR.LN_T_SFGuiaRemote;
import silat.servicios_negocio.LNSF.SFBean.LN_C_SFFlotaBean;

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
    
    
    //VARIABLES LISTAS
    private List<BeanOrdenServicio> listaOrdenServicio = new ArrayList<BeanOrdenServicio>();
    private List<BeanManifiesto> listaManifiesto = new ArrayList<BeanManifiesto>();
    private List<BeanManifiesto> listaManifiestoElegir = new ArrayList<BeanManifiesto>();
    private List<BeanADRelacionEmpresa> listaProov = new ArrayList<BeanADRelacionEmpresa>();
    private List<BeanFlota> lstFlotas = new ArrayList<BeanFlota>();
    private List<BeanChofer> lstChofers = new ArrayList<BeanChofer>();
    
    private List<BeanTrItemXOrds> lstItemsOrds = new ArrayList<BeanTrItemXOrds>();
    private List<BeanTrItemXOrds> lstItemsOrdsRespal = new ArrayList<BeanTrItemXOrds>();
    
    List<BeanADRelacionEmpresa> lstRemitentes = new ArrayList<BeanADRelacionEmpresa>();
    
    List<BeanDireccion> direcsRemin = new ArrayList<BeanDireccion>();
    
    List<BeanDireccion> direcsDest = new ArrayList<BeanDireccion>();
    
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
    private int varia = 0;
    private int tipoDoc = 1;
    private String flotaElegida;
    private String choferElegido;
    
    private String nombreChofer;
    private String nombreFlota;
    
    private String ordenServElegida;
    private String nombreEmpresaOrds;
    private Date dateOrds;
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
    
    private Date fechaTrans;
    private Date fechaEmis;
    
    private String nidRemitenteGuardar;
    
    private String nidItem;
    
    private String styleCreaGuia = "display:none";
    private String styleLastForm ="display:none"; 

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
        } else {
        }
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
        try{
        int npaq = Integer.parseInt(getNPaquetes());
        String cidGuia = "";
        cidGuia  = ln_C_SFUtilsRemote.generarCorrelativoLN("TRGuia","G",6,"001"); 
        String conf = "2";
        String estGuia = "1";
        Date fechaEmision = getFechaEmis();
        Date fechaDespacho = getFechaTrans();
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
        for(BeanTrItemXOrds bean : getLstItemsOrdsRespal()){
            BeanTRItem bean1 = new BeanTRItem();
            bean1.setCDescItem(bean.getDetalleWebmovilDescripcion());
            bean1.setCUndMedida(bean.getDetalleWebmovilUmedida());
            bean1.setNCantidad(bean.getDetalleWebmovilCantidad());
            bean1.setDPeso(bean.getDPeso());
                BeanTRGuia guia = new BeanTRGuia();
            guia.setCidGuia(cidGuia);
            bean1.setTrGuia(guia);
            lstItems.add(bean1);
        }
        
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
                                                              true,
                                                              true);
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
        String  h = ordenServElegida;
        BeanOrdenServicio bean = new BeanOrdenServicio();
        int g = Integer.parseInt(h);
        bean.setNidOrdnServ(g);
        List <BeanOrdenServicio> lstBeanOrd = lN_C_SFOrdenServicioRemote.findOrdenServicioByAttributesAux(bean);
        setNombreEmpresaOrds(lstBeanOrd.get(0).getDetalleWebmovilEmpresa());
        setComentarioOrds(lstBeanOrd.get(0).getDetalleWebmovilComentario());
        setDateOrds(lstBeanOrd.get(0).getFecOrdnServ());
        setNidOrds(lstBeanOrd.get(0).getNidOrdnServ()+"");
        
        
        setNidDestinoElegido(lstBeanOrd.get(0).getDetalleWebmovilNIDEmpresa());
        int t = Integer.parseInt(lstBeanOrd.get(0).getDetalleWebmovilNIDEmpresa());
        
        setDirecsDest(LN_C_SFDireccionRemote.getDireccionByProp_LN(null,t,null));
        
        getDirecsRemin().clear();
        
        setStyleCreaGuia("display:none");
        
        setLstItemsOrds(lN_C_SFOrdenServicioRemote.ItemsbyOrdenServicio(ordenServElegida));
        setLstItemsOrdsRespal(lN_C_SFOrdenServicioRemote.ItemsbyOrdenServicio(ordenServElegida));
        setListaManifiestoElegir(ln_C_SFManifiestoRemote.getListaManifsSinAsignar());
        
        setNombreDireccionRemitente("");
        setNombreDireccionRemitente("");
        setNidManifiestoEleg("");
        setNombreDireccionDestino("");
        setNombreDireccionRemitente("");
        setNombreRemitente("");
        setComentarioGuia("");
        setNPaquetes("");
        setFechaEmis(null);
        setFechaTrans(null);
        
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
                setFechaEmis(null);
                setFechaTrans(null);
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
    

    public String registrarManifiesto(){
        try{
        if(getNidPartyEmpresaElegida()!=null && getFletePactado()!=null && getAdelanto()!=null && getComentario()!=null && getFlotaElegida()!=null && getChoferElegido()!=null){
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
        }
        }catch(Exception e){
            
        }
        return "";
    }
    
    public String limpiarRegManifiesto(){
        setRazonSocialEmpresaElegida("");
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

    public void setDateOrds(Date dateOrds) {
        this.dateOrds = dateOrds;
    }

    public Date getDateOrds() {
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
}
