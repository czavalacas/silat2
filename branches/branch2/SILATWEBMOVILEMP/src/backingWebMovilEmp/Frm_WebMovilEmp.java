package backingWebMovilEmp;

import java.math.BigDecimal;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.faces.component.UIOutput;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import javax.naming.Context;
import javax.naming.InitialContext;

import silat.servicios_negocio.Beans.BeanADRelacionEmpresa;
import silat.servicios_negocio.Beans.BeanChofer;
import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.Beans.BeanFlota;
import silat.servicios_negocio.Beans.BeanManifiesto;
import silat.servicios_negocio.Beans.BeanOrdenServicio;
import silat.servicios_negocio.Beans.BeanTRItem;
import silat.servicios_negocio.Beans.BeanTRItemsWebMovil;
import silat.servicios_negocio.LNSF.IL.LN_T_SFManifiestoRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFChoferRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFEmpresasRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFFlotaRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFManifiestoRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFOrdenServicioRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFRelacionEmpresaRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFUsuarioRemote;


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
    
    
    //VARIABLES LISTAS
    private List<BeanOrdenServicio> listaOrdenServicio = new ArrayList<BeanOrdenServicio>();
    private List<BeanManifiesto> listaManifiesto = new ArrayList<BeanManifiesto>();
    private List<BeanADRelacionEmpresa> listaProov = new ArrayList<BeanADRelacionEmpresa>();
    private List lstFlotas;
    private List lstChofers;
    
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
        } else {
        }
    }
    
    public String editAction() {
                String h = action;
                System.out.println("NidPartyEmpr: "+h);
                for(BeanADRelacionEmpresa bean : getListaProov()){
                    setRazonSocialEmpresaElegida(bean.getAdEmpresa1().getCRazonSocial());
                    setRucEmpresaElegida(bean.getAdEmpresa1().getCRuc());
                    setNidPartyEmpresaElegida(bean.getAdEmpresa1().getNidParty());
                }
                this.setLstFlotas(this.llenarFlotasCombo(getNidPartyEmpresaElegida().intValue(),null));
                this.setLstChofers(this.llenarChofersCombo(getNidPartyEmpresaElegida().intValue(),null));
                FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("modalFlotas");
                FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("modalChoferes");
               return "";
            }
    
    public ArrayList llenarFlotasCombo(Integer nidParty, Integer nidID) {       
        return llenarCombo(nidParty, nidID, 0);
    }
    
    public boolean onDelete(AjaxBehaviorEvent event){
        if(varia == 0){
            System.out.println("Entroo");
            setLstFlotas(this.llenarFlotasCombo(5,null));
            setLstChofers(this.llenarChofersCombo(5,null));
            BeanEmpresa empresa = ln_C_SFEmpresasRemote.selectedEmpresa(new BigDecimal(5));
            setRazonSocialEmpresaElegida(empresa.getCRazonSocial());
            setRucEmpresaElegida(empresa.getCRuc());
            setNidPartyEmpresaElegida(empresa.getNidParty());
            System.out.println("Entroo1");
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("regManifiesto");
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("modChof");
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("modFlot");
            setTipoDoc(1);
            varia = 1;
        }else{
            System.out.println("Entroo");
            setRazonSocialEmpresaElegida("");
            setRucEmpresaElegida("");
            BigDecimal nn = null;
            setNidPartyEmpresaElegida(nn);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("regManifiesto");
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("modChof");
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("modFlot");
            setTipoDoc(2);
            varia = 0;
        }
        return true;
    }
    
    public ArrayList llenarChofersCombo(Integer nidParty,Integer nidID) {       
        return  llenarCombo(nidParty,nidID,1);
    }
    
    public ArrayList llenarCombo(Integer nidParty,Integer nidID, int opc){
        //opc 0 = flotas, opc = 1 chofer
        ArrayList items=new ArrayList();
        if(opc == 0){
            List<BeanFlota> flotas = ln_C_SFFlotaRemote.findFlotasByAttr_LN(nidParty,nidID);
            System.out.println("::nUM FLOTAS:::  "+flotas.size());
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
    
    public String registrarManifiesto(){
        /* BeanManifiesto bManifiesto = ln_T_SFManifiestoRemote.registrarManifiesto_LN(getNidPartyEmpresaElegida(),
                                                                                    getFechaRegManifiesto(), 
                                                                                    getFletePactado(),
                                                                                    getAdelanto(),
                                                                                    getTipoDoc(),
                                                                                    getComentario(),
                                                                                    Integer.parseInt(getBeanSessionRegistrarManifiesto().getCidFlota()),
                                                                                    Integer.parseInt(getBeanSessionRegistrarManifiesto().getCidChofer()),
                                                                                    0,1,"3");//Guias x asignar */
         
        return "";
    }
    
    public String mostrarManifiesto(){
        System.out.println("maif");
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

    public void setLstFlotas(List<ArrayList> lstFlotas) {
        this.lstFlotas = lstFlotas;
    }

    public List<ArrayList> getLstFlotas() {
        return lstFlotas;
    }

    public void setNidPartyEmpresaElegida(BigDecimal nidPartyEmpresaElegida) {
        this.nidPartyEmpresaElegida = nidPartyEmpresaElegida;
    }

    public BigDecimal getNidPartyEmpresaElegida() {
        return nidPartyEmpresaElegida;
    }

    public void setLstChofers(List<ArrayList> lstChofers) {
        this.lstChofers = lstChofers;
    }

    public List<ArrayList> getLstChofers() {
        return lstChofers;
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
}
