package siat.view.backing.transporte.factura;

import java.io.UnsupportedEncodingException;

import java.math.BigDecimal;

import java.math.RoundingMode;

import java.net.URLDecoder;

import java.net.URLEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import javax.naming.Context;
import javax.naming.InitialContext;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichPanelWindow;
import oracle.adf.view.rich.component.rich.RichPoll;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.RichSubform;
import oracle.adf.view.rich.component.rich.data.RichColumn;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectItem;
import oracle.adf.view.rich.component.rich.input.RichSelectManyShuttle;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.input.RichSelectOneListbox;
import oracle.adf.view.rich.component.rich.layout.RichGridCell;
import oracle.adf.view.rich.component.rich.layout.RichGridRow;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGridLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichInlineFrame;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.adf.view.rich.component.rich.output.RichSeparator;
import oracle.adf.view.rich.component.rich.output.RichSpacer;

import oracle.adf.view.rich.event.DialogEvent;

import org.apache.myfaces.trinidad.event.PollEvent;
import org.apache.myfaces.trinidad.event.SelectionEvent;

import siat.view.backing.utiles.Utils;

import silat.servicios_negocio.BDLSF.IR.BDL_C_SFUtilsRemote;
import silat.servicios_negocio.Beans.BeanDireccion;
import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanFactura;
import silat.servicios_negocio.Beans.BeanOrdenServicio;
import silat.servicios_negocio.Beans.BeanReportePrevio;
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
import silat.servicios_negocio.LNSF.IR.LN_C_SFUnidadNegocioRemote;
import silat.servicios_negocio.LNSF.IR.LN_T_SFFacturaRemote;
import silat.servicios_negocio.LNSF.IR.LN_T_SFGuiaRemote;
import silat.servicios_negocio.entidades.trans.TRItem;

/**
 * @author dfloresgonz
 */
public class Frm_registrar_factura {
    
    private RichOutputText otTitulo;
    private RichPanelGridLayout pglDatos;
    private RichGridRow filaUno;
    private RichGridCell celIzq;
    private RichGridCell celDos;
    private RichPanelFormLayout pflDatos;
    private RichCommandButton buscCli;
    private RichInputText itClien;
    private RichInputText itIGV;
    private RichInputText itSubTot;
    private RichInputText itMonFinal;
    private RichPopup popClie;
    private RichDialog dclie;
    private RichSubform sfClie;
    private RichPanelFormLayout pflClie;
    private RichInputText itClieRaz;
    private RichTable tbClie;
    private RichCommandButton btnBuscClie;
    private RichTable tabGuia;
    private RichSpacer s1;
    private RichSelectManyShuttle sitems;
    private UISelectItems si1;
    private RichSeparator s2;
    private RichSelectOneListbox solFin;
    private UISelectItems siFin;
    private RichCommandButton btnPrecio;
    private RichPopup popPrec;
    private RichDialog diaPre;
    private RichSubform sfPrec;
    private RichInputText itPrec;
    private RichCommandButton btnRegPrec;
    private RichCommandButton btnPrevia;
    private RichPopup popPrevio;
    private RichPanelWindow pwPrevio;
    private RichInlineFrame ifPrevio;
    private RichSelectOneChoice socDirecs;
    private UISelectItems si2;
    private RichSelectOneChoice socSerie;
    private UISelectItems siSerie;
    private RichInputText itCodFact;
    private RichPoll p1;
    private RichPoll pcodFac;
    private RichCommandButton btnGrabar;
    private RichSelectOneChoice socRemis;
    private UISelectItems si3;
    private RichSelectOneChoice socDest;
    private UISelectItems si4;
    private RichCommandButton btnRefr;
    /*Mis Variables*/
    private final static String LOOKUP_NAME_SFC_OS_REMOTO      = "mapLN_C_SFOrdenServicio";
    private final static String LOOKUP_NAME_SFCGUIA_REMOTO     = "mapLN_C_SFGuia";
    private final static String LOOKUP_NAME_SF_T_REPO_REMOTO   = "mapBDL_C_SFUtils";
    private final static String LOOKUP_NAME_SFDIRECCION_REMOTO = "LUBAL_SIAT_APP-SILATNEGOCIO-LN_C_SFDireccion";
    private final static String LOOKUP_NAME_SFUN_REMOTO        = "mapLN_C_SFUnidadNegocio";
    private final static String LOOKUP_NAME_SFFACT_REMOTO      = "mapLN_T_SFFactura";
    private final static String LOOKUP_NAME_SFC_CLIENTE_REMOTO = "mapLN_C_SFEmpresas";
    private LN_C_SFGuiaRemote ln_C_SFGuiaRemote;
    private BDL_C_SFUtilsRemote bdL_C_SFUtilsRemote;
    private LN_C_SFDireccionRemote ln_C_SFDireccionRemote;
    private LN_C_SFOrdenServicioRemote ln_C_SFOrdenServicioRemote;
    private LN_C_SFUnidadNegocioRemote ln_C_SFUnidadNegocioRemote;
    private LN_T_SFFacturaRemote ln_T_SFFacturaRemote;
    private LN_C_SFEmpresasRemote ln_C_SFEmpresasRemote;
    private SessionScopeBeanRegistrarFactura beanSessionScopeRegistrarFactura;
    FacesContext ctx = FacesContext.getCurrentInstance();
    private final static String sep = "&";
    private BeanUsuarioAutenticado beanUsuario = new BeanUsuarioAutenticado();
    private RichSelectOneChoice socTipo;
    private RichSelectItem si5;
    private RichSelectItem si6;
    private RichInputText itDet;
    private RichInputDate itFecFac;
    private RichSelectBooleanCheckbox cbEditable;
    private RichColumn colObs;
    private RichPopup popEdit;
    private RichInputText itPrecEdit;
    private RichCommandButton btnPrecEdit;
    private RichInputText itContenido;
    private RichCommandButton btnFlete;
    private RichColumn colRazSoc;
    private int cont=0;

    public Frm_registrar_factura(){
        try{
            final Context ctx;
            ctx = new InitialContext();
            ln_C_SFOrdenServicioRemote = (LN_C_SFOrdenServicioRemote) ctx.lookup(LOOKUP_NAME_SFC_OS_REMOTO);
            ln_C_SFGuiaRemote = (LN_C_SFGuiaRemote)                   ctx.lookup(LOOKUP_NAME_SFCGUIA_REMOTO);
            bdL_C_SFUtilsRemote = (BDL_C_SFUtilsRemote)               ctx.lookup(LOOKUP_NAME_SF_T_REPO_REMOTO);
            ln_C_SFDireccionRemote = (LN_C_SFDireccionRemote)         ctx.lookup(LOOKUP_NAME_SFDIRECCION_REMOTO);
            ln_C_SFUnidadNegocioRemote = (LN_C_SFUnidadNegocioRemote) ctx.lookup(LOOKUP_NAME_SFUN_REMOTO);
            ln_T_SFFacturaRemote = (LN_T_SFFacturaRemote)             ctx.lookup(LOOKUP_NAME_SFFACT_REMOTO);
            ln_C_SFEmpresasRemote = (LN_C_SFEmpresasRemote)           ctx.lookup(LOOKUP_NAME_SFC_CLIENTE_REMOTO);
            beanUsuario = (BeanUsuarioAutenticado) Utils.getSession("USER");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }  
    
    @PostConstruct 
    public void methodOnPostConstruct(){
        if(beanSessionScopeRegistrarFactura.getExec() == 0){
            beanSessionScopeRegistrarFactura.setExec(1);
            beanSessionScopeRegistrarFactura.setListaUNs(this.llenarUNCombo());
           // mostrarGuias();
        }else{
            //mostrarGuias();
        }
    }
    
    public void registrarFactura(ActionEvent ae) {
        try{
            if(!isOK()){
                return; 
            }
            Date fechaFactura = beanSessionScopeRegistrarFactura.getFecFactura();
            BigDecimal subTotal = beanSessionScopeRegistrarFactura.getSub_total().setScale(2, RoundingMode.DOWN);
            String cidSerie = beanSessionScopeRegistrarFactura.getCodUN();
            System.out.println(":::CIDREPO 2 ::: "+beanSessionScopeRegistrarFactura.getCidRepoToDelete());
            
            String cidRepo = beanSessionScopeRegistrarFactura.getCidRepoToDelete();
            
            BigDecimal nidParty = beanSessionScopeRegistrarFactura.getNidParty();
            String cliente = beanSessionScopeRegistrarFactura.getRazonClienteForFact();
            String ruc = beanSessionScopeRegistrarFactura.getRuc();
            String nidOServs = beanSessionScopeRegistrarFactura.getNidOrdServicios();
            String direccion = beanSessionScopeRegistrarFactura.getDirec();
            String contenido = beanSessionScopeRegistrarFactura.getContenido();
            String guias = "";
            String guiasForRepo = "";
            if(beanSessionScopeRegistrarFactura.isEditable()){
                setearReporteRegistroEditable(false);
                guias = beanSessionScopeRegistrarFactura.getGuiasPDF_Editable()[0]; 
                guiasForRepo = beanSessionScopeRegistrarFactura.getGuiasPDF_Editable()[1]; 
            }else{           
                cont=1;
                setearReporteRegistro(false,cidRepo);
                guias = beanSessionScopeRegistrarFactura.getGuiasPDF()[0]; 
                guiasForRepo = beanSessionScopeRegistrarFactura.getGuiasPDF()[1]; 
            }
            BeanFactura bFactura = ln_T_SFFacturaRemote.registrarFactura_LN("C",
                                                                            fechaFactura,
                                                                            subTotal,
                                                                            2,
                                                                            cidSerie,
                                                                            cidRepo,
                                                                            guias,
                                                                            nidParty,
                                                                            cliente,
                                                                            ruc,
                                                                            nidOServs,
                                                                            direccion,
                                                                            "1",
                                                                            null,
                                                                            guiasForRepo,
                                                                            beanSessionScopeRegistrarFactura.isEditable(),
                                                                            contenido);
            if(bFactura.getBeanError() != null){
                BeanError error = bFactura.getBeanError();
                int severidad = 0;
                if(error.getCidError().equals("000")){
                    severidad = 3;
                    Utils.depurar("Grabo la factura");
                    if(Utils.hasPermiso(beanUsuario.getLstPermisos(),new BigDecimal("38"))){
                        Utils._redireccionar(ctx,"WEB-INF/consultar_facturas.xml#consultar_facturas");
                    }else{
                        Utils._redireccionar(ctx, "WEB-INF/mainTF.xml#mainTF");
                    }
                }else{
                    severidad = 1;
                }
                Utils.throwError_Aux(ctx,error.getCDescripcionError(), severidad);
            }else{
                Utils.throwError_Aux(ctx,"Error Inesperado", 1);
            }         
        }catch(Exception e){
            Utils.throwError_Aux(ctx,"Error Inesperado", 1);
        }
    }
    
    public String mostrarGuias(){//trae guias sin facturas
        BigDecimal nidClie = beanSessionScopeRegistrarFactura.getNidClie();
        if(nidClie != null){
            beanSessionScopeRegistrarFactura.setLstGuias(ln_C_SFGuiaRemote.findGuiasByNidCliente_LN(nidClie.intValue()));
        }
        return null;
    }
    
    public String abrirPopClie(){
        Utils.unselectFilas(tbClie);
        mostrarClientes();
        Utils.showPopUpMIDDLE(popClie);
        return null;
    }
    
    public String mostrarClientes(){
        String valor = beanSessionScopeRegistrarFactura.getRazonCliente(); 
        beanSessionScopeRegistrarFactura.setLstClientes(ln_C_SFEmpresasRemote.getADEmpresaCliente(valor));
        return null;
    }
    
    public String abrirPopPrecio(){
        if(beanSessionScopeRegistrarFactura.isEditable()){
            Utils.showPopUpMIDDLE(popEdit);
            return null;
        }
        if("4".equals(beanSessionScopeRegistrarFactura.getTipFactura())){
            if(beanSessionScopeRegistrarFactura.getGuia() != null){
                if(beanSessionScopeRegistrarFactura.getGuia().getGrupo() == 0){
                    Utils.throwError_Aux(ctx,"Asignele un grupo para que pueda agregarle precio",4);
                    return null;
                }
                if(tipo2FacturaHasPrecio(beanSessionScopeRegistrarFactura.getGuia().getGrupo(),beanSessionScopeRegistrarFactura.getGuia()) == true){
                    return null;
                }
            }else{
                return null;
            }
            if(beanSessionScopeRegistrarFactura.isNoPrecioMalaSecuenciaGrupo()){
                return null;
            }
        }
        beanSessionScopeRegistrarFactura.setBdPrecio(null);
        if(isAgregarPrecio()){
            if(itPrec != null){
                itPrec.setValue(beanSessionScopeRegistrarFactura.getGuia().getPrecio());
            }
            Utils.showPopUpMIDDLE(popPrec);
            return null; 
        }else{
            Utils.throwError_Aux(ctx,"Debe agregar al menos 1 Item de esta guia",4);    
            popPrec.hide();
            return null;
        }
    }
    
    public boolean isOK(){
        boolean isOk = true;
        if(beanSessionScopeRegistrarFactura.getCodUN() == null){
            Utils.throwError_Aux(ctx,"Seleccionar una Serie",4);
            return false;
        }
        if(beanSessionScopeRegistrarFactura.getDirec() == null){
            Utils.throwError_Aux(ctx,"Seleccionar una direccion",4);
            return false;
        }
        if(beanSessionScopeRegistrarFactura.getDirec().equals("")){
            Utils.throwError_Aux(ctx,"Seleccionar una direccion",4);
            return false;
        }
        if(beanSessionScopeRegistrarFactura.isEditable() == false){
            if(beanSessionScopeRegistrarFactura.getLstItemsFinalAll() == null){
                Utils.throwError_Aux(ctx,"Agregue al menos 1 Item",4);
                return false;
            }
            if(beanSessionScopeRegistrarFactura.getLstItemsFinalAll().size() <= 0){
                Utils.throwError_Aux(ctx,"Agregue al menos 1 Item",4);
                return false;
            }
        }
        if(beanSessionScopeRegistrarFactura.isEditable()){
            if(beanSessionScopeRegistrarFactura.getGuiasPDF_Editable() == null){
                Utils.throwError_Aux(ctx,"Seleccionar al menos 1 Guia",4);
                return false;
            }else{
                if(beanSessionScopeRegistrarFactura.getGuiasPDF_Editable().equals("")){
                    Utils.throwError_Aux(ctx,"Seleccionar al menos 1 Guia",4);
                    return false;
                }
            }
            if(beanSessionScopeRegistrarFactura.getContenido() == null){
                Utils.throwError_Aux(ctx,"Redacte el contenido de la Factura",4);
                return false;
            }else{
                if(beanSessionScopeRegistrarFactura.getContenido().equals("")){
                    Utils.throwError_Aux(ctx,"Redacte el contenido de la Factura",4);
                    return false;
                }
            }
        }
        if(beanSessionScopeRegistrarFactura.getCodFactura() == null){
            Utils.throwError_Aux(ctx,"Espere a que se genere el codigo de Factura",4);
            return false;
        }
        if(beanSessionScopeRegistrarFactura.getSub_total() == null){
            Utils.throwError_Aux(ctx,"Asigne un precio mayor a 0.",4);
            return false;
        }else{
            if(beanSessionScopeRegistrarFactura.getSub_total().intValue() <= 0){
                Utils.throwError_Aux(ctx,"Asigne un precio mayor a 0",4);
                return false;
            }
        }
        return isOk;
    }
    
    public String abrirPopPrevio(){
        if(!isOK()){
            return null;
        }
        if(beanSessionScopeRegistrarFactura.isEditable()){
            setearReporteRegistroEditable(true);
        }else{
            setearReporteRegistro(true,null);
        }
        return null;
    }
    
    private BeanTRGuia getGuiaByItem(BeanTRItem itm){
        for(BeanTRGuia g : beanSessionScopeRegistrarFactura.getLstGuiasConPrecio()){
            if(g.getCidGuia().equals(itm.getTrGuia().getCidUnidadNegocio()+"-"+itm.getTrGuia().getCidGuia())){
                return g;
            }
        }
        return null;
    }
    
    private int getGrupoByGuia(BeanTRItem itm){
        int grupo = 0;
        for(BeanTRGuia g : beanSessionScopeRegistrarFactura.getLstGuiasConPrecio()){
            if(g.getCidGuia().equals(itm.getTrGuia().getCidUnidadNegocio()+"-"+itm.getTrGuia().getCidGuia())){
                grupo = g.getGrupo();
                return grupo;
            }
        }
        return grupo;
    }
    
    private boolean tipo2FacturaHasPrecio(int grupo,BeanTRGuia bGuia){
        boolean hasPrecio = false;
        for(BeanTRGuia guia : beanSessionScopeRegistrarFactura.getLstGuias()){
            if(guia.getGrupo() == grupo && !guia.getCidGuia().equals(bGuia.getCidGuia())){
                if(guia.getPrecio().compareTo(new BigDecimal(0)) == 1){
                    hasPrecio = true;
                    return hasPrecio;
                }
            }
        }
        return hasPrecio;
    }
    
    private boolean tipo2FacturaHasPrecio(int grupo){
        boolean hasPrecio = false;
        for(BeanTRGuia guia : beanSessionScopeRegistrarFactura.getLstGuias()){
            if(guia.getGrupo() == grupo){
                if(guia.getPrecio().compareTo(new BigDecimal(0)) == 1){
                    hasPrecio = true;
                    return hasPrecio;
                }
            }
        }
        return hasPrecio;
    }
    
    private boolean allGrupoHavePrecio(){
        boolean result = true;
        List<Integer> listGrupos = new ArrayList<Integer>();
        for(BeanTRGuia guia : beanSessionScopeRegistrarFactura.getLstGuias()){
            if(!listGrupos.contains(guia.getGrupo()) && guia.getGrupo() != 0){
                listGrupos.add(guia.getGrupo());   
            }
        }
        for(Integer grup : listGrupos){
            if(grup != 0){
                result = tipo2FacturaHasPrecio(grup);
                if(result == false){//Hay un grupo sin precio ERROR!!!!
                    Utils.throwError_Aux(ctx,"Por favor ponga un precio al grupo: "+grup,4);
                    return false;
                }
            }
        }
        return result;
    }
    
    private Map getPrecio_Ruta_OfGrupo(int grupo){
        Map mapa = new HashMap();
        BigDecimal price = new BigDecimal("0");
        for(BeanTRGuia guia : beanSessionScopeRegistrarFactura.getLstGuiasConPrecio()){
            if(guia.getGrupo() == grupo){
                if(guia.getPrecio().compareTo(new BigDecimal(0)) == 1){
                    price = guia.getPrecio();
                    String ruta = "";
                    String dirRemi = (guia.getUbigeoDireccionRemitente() == null) ? guia.getCDireccionRemitente() : guia.getUbigeoDireccionRemitente();
                    String dirDesti = (guia.getUbigeoDireccionDestino() == null) ? guia.getCDireccionDestino() : guia.getUbigeoDireccionDestino();
                    ruta = "TRANSPORTE "+dirRemi+" ----- "+dirDesti;
                    mapa.put("PRECIO", price);
                    mapa.put("RUTA", ruta);
                    return mapa;
                }
            }
        }
        return mapa;
    }
    
    public void setearReporteRegistro(boolean isReporte,String cidRep0){
        try {            
            beanSessionScopeRegistrarFactura.setLstGuiasConPrecio(new ArrayList<BeanTRGuia>());
            String jasper = "vista_previa_factura.jasper";
            String monto = beanSessionScopeRegistrarFactura.getTotal().setScale(2, RoundingMode.DOWN).toString();
            String igv = beanSessionScopeRegistrarFactura.getIgv().setScale(2, RoundingMode.DOWN).toString();
            String subTotal = beanSessionScopeRegistrarFactura.getSub_total().setScale(2, RoundingMode.DOWN).toString();
            String cliente = beanSessionScopeRegistrarFactura.getRazonClienteForFact();
            String claveRandom = Utils.generarContrasena();
            String direccion = beanSessionScopeRegistrarFactura.getDirec();
            String codFact = beanSessionScopeRegistrarFactura.getCodUN() + "-" + beanSessionScopeRegistrarFactura.getCodFactura();
            String timePath= GregorianCalendar.getInstance().getTimeInMillis() + claveRandom;
         
            String detalle = beanSessionScopeRegistrarFactura.getDetalleFactura() != null ? beanSessionScopeRegistrarFactura.getDetalleFactura().replaceAll("%","") : beanSessionScopeRegistrarFactura.getDetalleFactura() ;
            if (cont==0){
            if (beanSessionScopeRegistrarFactura.getCidRepoToDelete() != null) {
                bdL_C_SFUtilsRemote.removerItmsRepo(beanSessionScopeRegistrarFactura.getCidRepoToDelete());
            }     
            }
            beanSessionScopeRegistrarFactura.setCidRepoToDelete(timePath);
            System.out.println(":::CIDREPO 1 ::: "+beanSessionScopeRegistrarFactura.getCidRepoToDelete());
            String ruc = beanSessionScopeRegistrarFactura.getEmpresaSelected().getCRuc();
            List<BeanReportePrevio> lstReportItem = new ArrayList<BeanReportePrevio>();
            int tipFact = Integer.parseInt(beanSessionScopeRegistrarFactura.getTipFactura());
            if (tipFact == 1) {
                lstReportItem = tipUnoFactura(timePath, lstReportItem);
            } else {
                if (allGrupoHavePrecio() == false) {
                    return;
                }
                lstReportItem = tipUno_2Factura(timePath, lstReportItem);
            }
            if (cont==0){
            bdL_C_SFUtilsRemote.grabarItemReporte(lstReportItem);
            }
            String guias = beanSessionScopeRegistrarFactura.getGuiasPDF()[1];
            if (isReporte) {
                String path =
                    "/showpdfservlet?jasper=" + jasper + sep + "monto=" + monto + sep + "igv=" + igv + sep + "subTotal=" +
                    subTotal + sep + "estado=previo" + sep + "cliente=" + URLEncoder.encode(cliente, "UTF-8") + sep + "guias=" + guias + sep +
                    "direccion=" + URLEncoder.encode(direccion, "UTF-8") + sep + "timepath=" + timePath + sep + "ruc=" + ruc + sep + "codFact=" +
                    codFact + sep + "fecha_pago=" + null + sep + "fechaHoy=" + null + sep + "tipFactRepo=" + tipFact +
                    sep + "formato_repo=PDF" + sep + "detalle=" + URLEncoder.encode(detalle, "UTF-8");
                beanSessionScopeRegistrarFactura.setSourcePrevio(path);
                Utils.showPopUpMIDDLE(popPrevio);
            }
            cont=0;
        } catch (Exception nfe) {
            nfe.printStackTrace();
        }
        return;
    }
    
    public void setearReporteRegistroEditable(boolean isReporte){
        try {
            
            beanSessionScopeRegistrarFactura.setLstGuiasConPrecio(new ArrayList<BeanTRGuia>());
            String jasper = "vista_previa_factura_editable.jasper";
            String monto = beanSessionScopeRegistrarFactura.getTotal().setScale(2, RoundingMode.DOWN).toString();
            String igv = beanSessionScopeRegistrarFactura.getIgv().setScale(2, RoundingMode.DOWN).toString();
            String subTotal = beanSessionScopeRegistrarFactura.getSub_total().setScale(2, RoundingMode.DOWN).toString();
            String cliente = beanSessionScopeRegistrarFactura.getRazonClienteForFact();
            String claveRandom = Utils.generarContrasena();
            String direccion = beanSessionScopeRegistrarFactura.getDirec();
            String codFact = beanSessionScopeRegistrarFactura.getCodUN() + "-" + beanSessionScopeRegistrarFactura.getCodFactura();          
            String timePath = GregorianCalendar.getInstance().getTimeInMillis() + claveRandom;          
            String contenido = beanSessionScopeRegistrarFactura.getContenido() != null ? beanSessionScopeRegistrarFactura.getContenido().replaceAll("%","") : beanSessionScopeRegistrarFactura.getContenido();
            if (beanSessionScopeRegistrarFactura.getCidRepoToDelete() != null) {
                bdL_C_SFUtilsRemote.removerItmsRepo(beanSessionScopeRegistrarFactura.getCidRepoToDelete());
            }
            beanSessionScopeRegistrarFactura.setCidRepoToDelete(timePath);
            String ruc = beanSessionScopeRegistrarFactura.getEmpresaSelected().getCRuc();
            int tipFact = Integer.parseInt(beanSessionScopeRegistrarFactura.getTipFactura());
            String guias = beanSessionScopeRegistrarFactura.getGuiasPDF_Editable()[1];
            if (isReporte) {
                String path =
                    "/showpdfservlet?jasper=" + jasper + sep + "monto=" + monto + sep + "igv=" + igv + sep + "subTotal=" +
                    subTotal + sep + "estado=previo" + sep + "cliente=" + URLEncoder.encode(cliente, "UTF-8") + sep + "guias=" + guias + sep +
                    "direccion=" + URLEncoder.encode(direccion, "UTF-8") + sep + "timepath=" + timePath + sep + "ruc=" + ruc + sep + "codFact=" +
                    codFact + sep + "fecha_pago=" + null + sep + "fechaHoy=" + null + sep + "tipFactRepo=" + tipFact +
                    sep + "formato_repo=PDF" + sep + "contenido="+URLEncoder.encode(contenido, "UTF-8");
                beanSessionScopeRegistrarFactura.setSourcePrevio(path);
                Utils.depurar("path: " + path);
                Utils.showPopUpMIDDLE(popPrevio);
            }
        } catch (Exception nfe) {
            nfe.printStackTrace();
        }
        return;
    }
    
    public List<BeanReportePrevio> tipUnoFactura(String timePath,List<BeanReportePrevio> lstReportItem){
        if(beanSessionScopeRegistrarFactura.getLstGuias() != null){
            if(beanSessionScopeRegistrarFactura.getLstGuias().size() > 0){
                for(int i = 0; i < beanSessionScopeRegistrarFactura.getLstGuias().size(); i++){//Utils.depurar("gr: "+beanSessionScopeRegistrarFactura.getLstGuias().get(i).getGrupo());
                    if(beanSessionScopeRegistrarFactura.getLstGuias().get(i).getPrecio() != null){
                         if(beanSessionScopeRegistrarFactura.getLstGuias().get(i).getPrecio().compareTo(BigDecimal.ZERO) == 1){
                             if(!beanSessionScopeRegistrarFactura.getLstGuiasConPrecio().contains(beanSessionScopeRegistrarFactura.getLstGuias().get(i))){
                                 beanSessionScopeRegistrarFactura.getLstGuiasConPrecio().add(beanSessionScopeRegistrarFactura.getLstGuias().get(i));
                             }    
                         }
                    }
                }
            } 
        }
        Iterator it = beanSessionScopeRegistrarFactura.getLstItemsFinalAll().iterator();
        List<BeanTRItem> itmsFin = new ArrayList<BeanTRItem>();  
        int index = 0;
        while(it.hasNext()){
            BeanTRItem itme = (BeanTRItem) it.next();
            String cidCmpl = itme.getTrGuia().getCidUnidadNegocio()+"-"+(itme.getTrGuia().getCidGuia());
            if(ln_C_SFGuiaRemote.contieneItemGuiaSolo4RegFactura(itme.getTrGuia(),beanSessionScopeRegistrarFactura.getLstGuiasConPrecio())){
                BigDecimal price = new BigDecimal("0");
                int grupo = 0;
                for(int i = 0; i < beanSessionScopeRegistrarFactura.getLstGuiasConPrecio().size(); i++){
                    if(beanSessionScopeRegistrarFactura.getLstGuiasConPrecio().get(i).getCidGuia().equals(cidCmpl)){
                        price = beanSessionScopeRegistrarFactura.getLstGuiasConPrecio().get(i).getPrecio();
                        grupo = beanSessionScopeRegistrarFactura.getLstGuiasConPrecio().get(i).getGrupo();
                    }
                }
                itme.setPrecioAux4AddToItmRepo(price);
                itme.setGrupo(grupo);
                itmsFin.add(itme);
            }
            index++;
        }
        Collections.sort(itmsFin, new Comparator<BeanTRItem>() {
            public int compare(BeanTRItem s1,BeanTRItem s2) {
                return s1.getOrden().compareTo(s2.getOrden());
            }
        });
        String ruta = "";
        Iterator iti = itmsFin.iterator();
        int index2 = 0;
        while(iti.hasNext()){
            BeanTRItem itm = (BeanTRItem) iti.next();
            BeanReportePrevio repPrev = new BeanReportePrevio();
            BeanTRGuia guia = this.getGuiaByItem(itm);
            String dirRemi = (guia.getUbigeoDireccionRemitente() == null) ? guia.getCDireccionRemitente() : guia.getUbigeoDireccionRemitente();
            String dirDesti = (guia.getUbigeoDireccionDestino() == null) ? guia.getCDireccionDestino() : guia.getUbigeoDireccionDestino();
            ruta = "TRANSPORTE "+dirRemi+" ----- "+dirDesti;
            repPrev.setRuta(ruta);
            repPrev.setGrupo(itm.getGrupo());
            repPrev.setPrecio(itm.getPrecioAux4AddToItmRepo());
            String cidGuiFull = "";
            if(itm.getTrGuia().getCidGuia().length() > 10){
                cidGuiFull = itm.getTrGuia().getCidGuia().substring(itm.getTrGuia().getCidGuia().length()-10,itm.getTrGuia().getCidGuia().length());
            }else{
                cidGuiFull = itm.getTrGuia().getCidGuia();
            }
            repPrev.setCidGuiaFull(cidGuiFull);
            String desc = (itm.getCDescItem() == null ? "" : itm.getCDescItem())+ " "+(itm.getCCidGuiaRemitente() == null ? "" : "- "+itm.getCCidGuiaRemitente());
            repPrev.setCDescItem(desc);
            repPrev.setCUndMedida(itm.getCUndMedida());
            repPrev.setCidRepo(timePath);
            repPrev.setNCantidad(itm.getNCantidad());
            lstReportItem.add(repPrev);
            index2++;
        }
        return lstReportItem;
    }
    
    public List<BeanReportePrevio> tipUno_2Factura(String timePath,List<BeanReportePrevio> lstReportItem){
        if(beanSessionScopeRegistrarFactura.getLstGuias() != null){
            if(beanSessionScopeRegistrarFactura.getLstGuias().size() > 0){
                for(int i = 0; i < beanSessionScopeRegistrarFactura.getLstGuias().size(); i++){//Utils.depurar("gr: "+beanSessionScopeRegistrarFactura.getLstGuias().get(i).getGrupo());
                     if(beanSessionScopeRegistrarFactura.getLstGuias().get(i).getGrupo() > 0){
                         if(!beanSessionScopeRegistrarFactura.getLstGuiasConPrecio().contains(beanSessionScopeRegistrarFactura.getLstGuias().get(i))){
                             beanSessionScopeRegistrarFactura.getLstGuiasConPrecio().add(beanSessionScopeRegistrarFactura.getLstGuias().get(i));
                         }    
                     }
                }
            } 
        }
        Iterator it = beanSessionScopeRegistrarFactura.getLstItemsFinalAll().iterator();
        List<BeanTRItem> itmsFin = new ArrayList<BeanTRItem>();  
        int index = 0;
        while(it.hasNext()){
            BeanTRItem itme = (BeanTRItem) it.next();
           // String cidCmpl = itme.getTrGuia().getCidUnidadNegocio()+"-"+(itme.getTrGuia().getCidGuia());
            if(ln_C_SFGuiaRemote.contieneItemGuiaSolo4RegFactura(itme.getTrGuia(),beanSessionScopeRegistrarFactura.getLstGuiasConPrecio())){
                int grupo = this.getGrupoByGuia(itme);
                Map mapa = this.getPrecio_Ruta_OfGrupo(grupo);
                BigDecimal price = (BigDecimal) mapa.get("PRECIO");
                itme.setRuta((String) mapa.get("RUTA"));
                itme.setPrecioAux4AddToItmRepo(price);
                itme.setGrupo(grupo);
                itmsFin.add(itme);
            }
            index++;
        }
        Collections.sort(itmsFin, new Comparator<BeanTRItem>() {
            public int compare(BeanTRItem s1,BeanTRItem s2) {
                return s1.getOrden().compareTo(s2.getOrden());
            }
        });
        Iterator iti = itmsFin.iterator();
        int index2 = 0;
        while(iti.hasNext()){
            BeanTRItem itm = (BeanTRItem) iti.next();
            BeanReportePrevio repPrev = new BeanReportePrevio();
            repPrev.setRuta(itm.getRuta());
            repPrev.setGrupo(itm.getGrupo());
            repPrev.setPrecio(itm.getPrecioAux4AddToItmRepo());
            String cidGuiFull = "";
            if(itm.getTrGuia().getCidGuia().length() > 10){
                cidGuiFull = itm.getTrGuia().getCidGuia().substring(itm.getTrGuia().getCidGuia().length()-10,itm.getTrGuia().getCidGuia().length());
            }else{
                cidGuiFull = itm.getTrGuia().getCidGuia();
            }
            repPrev.setCidGuiaFull(cidGuiFull);
            String desc = (itm.getCDescItem() == null ? "" : itm.getCDescItem())+ " "+(itm.getCCidGuiaRemitente() == null ? "" : "- "+itm.getCCidGuiaRemitente());
            repPrev.setCDescItem(desc);
            repPrev.setCUndMedida(itm.getCUndMedida());
            repPrev.setCidRepo(timePath);
            repPrev.setNCantidad(itm.getNCantidad());
            lstReportItem.add(repPrev);
            index2++;
        }
        return lstReportItem;
    }
    
    public void dialogPrecioListener(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
          // MANDA EL Precio al objeto Guia seleccionada
        }
    }
    
    public void agregarPrecio(ActionEvent ae) {
        try{
            BigDecimal precio = beanSessionScopeRegistrarFactura.getBdPrecio();
            beanSessionScopeRegistrarFactura.getGuia().setPrecio(precio);
            tabGuia.setValue(beanSessionScopeRegistrarFactura.getLstGuias());
            Utils.addTarget(tabGuia);
            popPrec.hide();   
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void seleccionarCliente(SelectionEvent selectionEvent) {
        RichTable t = (RichTable) selectionEvent.getSource();
        BeanEmpresa beanClie = (BeanEmpresa) t.getSelectedRowData();
        beanSessionScopeRegistrarFactura.setEmpresaSelected(beanClie);
        beanSessionScopeRegistrarFactura.setRazonClienteForFact(beanClie.getCRazonSocial());
        beanSessionScopeRegistrarFactura.setRuc(beanClie.getCRuc());
        beanSessionScopeRegistrarFactura.setLstDirecs(this.llenarDireccionCombo(beanClie.getNidParty().intValue()));
        beanSessionScopeRegistrarFactura.setNidClie(beanClie.getNidParty());
        mostrarGuias();
        btnRefr.setDisabled(false);
        tabGuia.setValue(beanSessionScopeRegistrarFactura.getLstGuias());
        beanSessionScopeRegistrarFactura.setItems(null);
        beanSessionScopeRegistrarFactura.setLstItemsFinal(new ArrayList<BeanTRItem>());
        beanSessionScopeRegistrarFactura.setItemsFin(null);
        beanSessionScopeRegistrarFactura.setDisabBtnPrecio(false);
        btnPrecio.setDisabled(false);
        try {
            itClien.setValue(beanClie.getCRazonSocial());
            Utils.unselectFilas(tabGuia);
            popClie.hide();
            Utils.addTargetMany(itClien,tabGuia,sitems,solFin,socDirecs,btnRefr,btnPrecio);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    public void seleccionarGuia(SelectionEvent selectionEvent) {
        try {
            RichTable t = (RichTable)selectionEvent.getSource();
            BeanTRGuia beanGuia = (BeanTRGuia)t.getSelectedRowData();
            beanGuia.setItemsLista(ln_C_SFGuiaRemote.getListaItemsByCidGuia(beanGuia.getNativeCidGuia()));  /**Agregamos Items**/          
            beanSessionScopeRegistrarFactura.setGuia(beanGuia);                  
            if(beanSessionScopeRegistrarFactura.isEditable()){//Se hizo check en editable
                if(beanGuia.isSelected()){
                    beanGuia.setSelected(false);
                }else{
                    beanGuia.setSelected(true);
                }
            }else{
                BeanDireccion bDirRemi = ln_C_SFDireccionRemote.getDireccionUbigeosDesc_Para_Factura(beanGuia.getNidDireccionRemitente());
                BeanDireccion bDirDesti = ln_C_SFDireccionRemote.getDireccionUbigeosDesc_Para_Factura(beanGuia.getNidDireccionDestino());
                beanSessionScopeRegistrarFactura.setLstUbiDirecRemi(this.llenarDireccionComboParaDetalleFactura(bDirRemi));
                beanSessionScopeRegistrarFactura.setLstUbiDirecDesti(this.llenarDireccionComboParaDetalleFactura(bDirDesti));
                sitems.setDisabled(false);
                beanSessionScopeRegistrarFactura.setIsDisableSitems(false);
                beanSessionScopeRegistrarFactura.setCidGuiaGrupo(beanGuia.getCidGuia());
                btnPrecio.setDisabled(false);
                if(beanSessionScopeRegistrarFactura.getTipFactura().equals("4")){
                   /* if(beanGuia.getGrupo() == 0){
                        btnPrecio.setDisabled(true);
                    }else{*/
                        btnPrecio.setDisabled(this.tipo2FacturaHasPrecio(beanGuia.getGrupo(),beanGuia));   
                  //  }
                }
                socRemis.resetValue();
                socDest.resetValue();
                Utils.addTargetMany(socDest,socRemis);
                List<BeanTRItem> shutValue = new ArrayList<BeanTRItem>();
                List<BeanTRItem> shutItems = new ArrayList<BeanTRItem>();
                Iterator itLis = beanGuia.getItemsLista().iterator();
                while (itLis.hasNext()) {
                    BeanTRItem oNew = (BeanTRItem)itLis.next();
                    Iterator itFinal = beanSessionScopeRegistrarFactura.getLstItemsFinalAll().iterator();
                    if(!itFinal.hasNext()){
                        shutItems = beanGuia.getItemsLista();
                    }else{
                        if(ln_C_SFGuiaRemote.contieneItem_Guia(oNew, beanSessionScopeRegistrarFactura.getLstItemsFinalAll())){
                            shutValue.add(oNew);
                            shutItems.add(oNew);
                        }else{
                            shutItems.add(oNew);
                        }
                        sitems.setValue(shutValue);
                    } 
                }
                beanSessionScopeRegistrarFactura.setItems(this.llenarShuttleItems(shutItems));
                Utils.addTargetMany(sitems,btnPrecio,tabGuia,socDirecs,sitems);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }
    
    public void selectUbiRemi(ValueChangeEvent vce) {
        try{
            String ubiRemiSelec = (String) vce.getNewValue();
            beanSessionScopeRegistrarFactura.getGuia().setUbigeoDireccionRemitente(ubiRemiSelec);
            tabGuia.setValue(beanSessionScopeRegistrarFactura.getLstGuias());
            Utils.addTarget(tabGuia);
        }catch(Exception e){
            return;
        }
    }

    public void selectUbiDesti(ValueChangeEvent vce) {
        try{
            String ubiDestiSelec = (String) vce.getNewValue();
            beanSessionScopeRegistrarFactura.getGuia().setUbigeoDireccionDestino(ubiDestiSelec);
            tabGuia.setValue(beanSessionScopeRegistrarFactura.getLstGuias());
            Utils.addTarget(tabGuia);
        }catch(Exception e){
            return;
        }
    }
    
    public ArrayList llenarShuttleItems(List<BeanTRItem> items){ 
        ArrayList guiaItems = new ArrayList();
        if(items != null){
            if(items.size() > 0){
                for (BeanTRItem i : items) {
                    String cidGRemi = i.getCCidGuiaRemitente() == null ? "" : "- "+i.getCCidGuiaRemitente();
                    String desc = i.getNCantidad()+ " - "+i.getCUndMedida()+ " - "+i.getCDescItem() + cidGRemi;
                    guiaItems.add(new SelectItem(i,desc));
                }
            }
        }
        return guiaItems;
    }
    
    public void cambioItems(ValueChangeEvent vce) {
        try {
            List<BeanTRItem> arrItms = (List<BeanTRItem>)vce.getNewValue();
            if(beanSessionScopeRegistrarFactura.getLstItemsFinal() != null){
                beanSessionScopeRegistrarFactura.getLstItemsFinal().removeAll(beanSessionScopeRegistrarFactura.getLstItemsFinal());
            }
            if(beanSessionScopeRegistrarFactura.getItemsFin() != null){
                beanSessionScopeRegistrarFactura.getItemsFin().removeAll(beanSessionScopeRegistrarFactura.getItemsFin());
            }
            beanSessionScopeRegistrarFactura.setLstItemsFinal(arrItms);
            if(arrItms != null){
                beanSessionScopeRegistrarFactura.actualizarListaFinal(arrItms,0); 
            }else{
                List<BeanTRItem> oldItms = new ArrayList<BeanTRItem>();
                RichSelectManyShuttle shut = (RichSelectManyShuttle) vce.getComponent();
                UISelectItems itms = (UISelectItems)shut.getChildren().get(0);
                List listaItems = (List) itms.getValue();
                for(int i = 0; i < listaItems.size(); i++){
                    SelectItem selItm = (SelectItem) listaItems.get(i);
                    BeanTRItem itm = (BeanTRItem) selItm.getValue();
                    oldItms.add(itm);
                }
                beanSessionScopeRegistrarFactura.actualizarListaFinal(oldItms,1);    
            }
            List<BeanTRItem> hashToList = new ArrayList<BeanTRItem>(beanSessionScopeRegistrarFactura.getHashItemsFinal());
            if(beanSessionScopeRegistrarFactura.getLstItemsFinalAll() != null){
                if(!beanSessionScopeRegistrarFactura.getLstItemsFinalAll().isEmpty()){
                    beanSessionScopeRegistrarFactura.getLstItemsFinalAll().removeAll(beanSessionScopeRegistrarFactura.getLstItemsFinalAll());
                }
            }
            Collections.sort(hashToList, new Comparator<BeanTRItem>() {
                public int compare(BeanTRItem s1,BeanTRItem s2) {
                    return s1.getOrden().compareTo(s2.getOrden());
                }
            });
            beanSessionScopeRegistrarFactura.setLstItemsFinalAll(hashToList);
            beanSessionScopeRegistrarFactura.setItemsFin(this.llenarFinalItems(hashToList));
            beanSessionScopeRegistrarFactura.getSub_total();
            tabGuia.setValue(beanSessionScopeRegistrarFactura.getLstGuias());
            Utils.addTargetMany(tabGuia,itSubTot,itMonFinal,itIGV,solFin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void cambioNoItems(ValueChangeEvent vce) {
    }
    
    public boolean borrar(List<BeanTRItem> arrItmsOld,List<BeanTRItem> arrItms){
        if(arrItmsOld == null){
            return true;
        }
        if(arrItms == null){
            return true;
        }
        if(arrItmsOld.size() > arrItms.size()){
            return true;
        }
        return false;
    }
    
    public ArrayList llenarFinalItems(List<BeanTRItem> items){
        ArrayList guiaItems = new ArrayList();
        if(items != null){
            if(items.size() > 0){
                for (BeanTRItem i : items) {
                    String cidGRemi = i.getCCidGuiaRemitente() == null ? "" : "- "+i.getCCidGuiaRemitente();
                    String desc = i.getNCantidad()+ " - "+i.getCUndMedida()+ " - "+i.getCDescItem()+ " "+cidGRemi;
                    guiaItems.add(new SelectItem(i,desc));
                }
            }
        }
        return guiaItems;
    }
    
    public boolean getEditarGrupo(){
        String cidGuia = beanSessionScopeRegistrarFactura.getCidGuiaGrupo(); 
        if(cidGuia != null){
            List<BeanTRItem> items = beanSessionScopeRegistrarFactura.getLstItemsFinalAll();
            Iterator it = items.iterator();
            while(it.hasNext()){
                BeanTRItem item = (BeanTRItem) it.next();
                String cid = "";
                if(item.getTrGuia().getCidGuia().length() > 10){
                    cid = item.getTrGuia().getCidGuia().substring((item.getTrGuia().getCidGuia().length()-10),item.getTrGuia().getCidGuia().length());
                }else if(item.getTrGuia().getCidGuia().length() == 6){
                    cid = item.getTrGuia().getCidUnidadNegocio()+"-"+item.getTrGuia().getCidGuia();
                }else{
                    cid = item.getTrGuia().getCidGuia();
                }
                if(cid.equals(cidGuia)){
                    return false;
                }
            }
            return true;
        }else{
            return true;
        }
    }
    
    public void cambioGrupo(ValueChangeEvent vce) {
        Object newGrupo = vce.getNewValue();
        if(newGrupo != null){
            int iGrupo = Integer.parseInt(newGrupo.toString());
            int actual = getNextGrupo();
            if(actual + 1 >= iGrupo){
                beanSessionScopeRegistrarFactura.setNoPrecioMalaSecuenciaGrupo(false);
            }else{
                Utils.throwError_Aux(ctx,"No esta siguiendo la secuencia el siguiente numero deberia ser: "+(actual + 1)+" o seguir usando el "+actual,4);
                beanSessionScopeRegistrarFactura.setNoPrecioMalaSecuenciaGrupo(true);
                newGrupo = 0;
                if(vce.getComponent() instanceof org.apache.myfaces.trinidad.component.UIXValue){//@TODO para Utils
                    org.apache.myfaces.trinidad.component.UIXValue val = (org.apache.myfaces.trinidad.component.UIXValue)vce.getComponent();
                    val.setValue(vce.getOldValue());
                    vce.getComponent().processUpdates(ctx);
                }
                return;
            }
        }
    }
    
    private int getNextGrupo(){
        int nextGrupo = 0;
        List<Integer> listGrupos = new ArrayList<Integer>();
        for(BeanTRGuia guia : beanSessionScopeRegistrarFactura.getLstGuias()){
            listGrupos.add(guia.getGrupo());
        }
        nextGrupo = Collections.max(listGrupos);
        return nextGrupo;
    }
    
    public boolean isAgregarPrecio(){
        BeanTRGuia guia = beanSessionScopeRegistrarFactura.getGuia();
        if(guia != null){
            String cidGuia = guia.getCidGuia(); //guia.getCidGuia().substring(guia.getCidGuia().indexOf("-")+1,guia.getCidGuia().length());
            List<BeanTRItem> items = beanSessionScopeRegistrarFactura.getLstItemsFinalAll();
            Iterator it = items.iterator();
            while(it.hasNext()){
                BeanTRItem item = (BeanTRItem) it.next();
                String cid = "";
                if(item.getTrGuia().getCidGuia().length() > 10){
                    cid = item.getTrGuia().getCidGuia().substring((item.getTrGuia().getCidGuia().length()-10),item.getTrGuia().getCidGuia().length());
                }else if(item.getTrGuia().getCidGuia().length() == 6){
                    cid = item.getTrGuia().getCidUnidadNegocio()+"-"+item.getTrGuia().getCidGuia();
                }else{
                    cid = item.getTrGuia().getCidGuia();
                }
                if(cid.equals(cidGuia)){
                    return true;
                }
            }
        }
        return false;
    }
    
    public ArrayList llenarDireccionCombo(Integer nidParty){
        ArrayList direcsItems = new ArrayList();
        List<BeanDireccion> direcs = ln_C_SFDireccionRemote.getDireccionByProp_LN(null,nidParty,null);
        if(direcs != null){
            if(direcs.size() > 0){
                for (BeanDireccion d : direcs) {
                    direcsItems.add(new SelectItem(d.getCDireccion(), 
                                                   d.getCDireccion()));
                }
            }
        }
        return direcsItems;
    }
    
    public ArrayList llenarDireccionComboParaDetalleFactura(BeanDireccion beanDirec){
        ArrayList direcsItems = new ArrayList();
        if(beanDirec != null){
            direcsItems.add(new SelectItem(beanDirec.getDescDepartamento(), 
                                           "Departamento: "+beanDirec.getDescDepartamento()));
            direcsItems.add(new SelectItem(beanDirec.getDescProvincia(), 
                                           "Provincia: "+beanDirec.getDescProvincia()));
            direcsItems.add(new SelectItem(beanDirec.getDescDistrito(), 
                                           "Distrito: "+beanDirec.getDescDistrito()));
            direcsItems.add(new SelectItem(beanDirec.getCDireccion(),
                                           "Direcccion: "+beanDirec.getCDireccion()));
        }
        return direcsItems;
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
    
    public void exeCodFacturaPoll(PollEvent pe) {
        String cidUn = beanSessionScopeRegistrarFactura.getCodUN();
        String codFac = "";
        if(cidUn != null){
            codFac = bdL_C_SFUtilsRemote.call_Procedure_GET_COD_FACTURA_BY_UN(cidUn);
            beanSessionScopeRegistrarFactura.setCodFactura(codFac);
            Utils.addTargetMany(itCodFact);
        }
    }
    
    public void cambioTipo(ValueChangeEvent vce) {
        String val = vce.getNewValue().toString();
        beanSessionScopeRegistrarFactura.setPrecioFinalEditable(null);
        if(val.equals("1")){
            beanSessionScopeRegistrarFactura.setEditableIsVisible(true);
            beanSessionScopeRegistrarFactura.setEditable(false);
            cbEditable.setVisible(true);
            cbEditable.setValue(false);
            cbEditable.setSelected(false);
            Utils.addTargetMany(cbEditable);
        }else{
            beanSessionScopeRegistrarFactura.setEditableIsVisible(false);
            beanSessionScopeRegistrarFactura.setEditable(false);
            cbEditable.setVisible(false);
            cbEditable.setValue(false);
            cbEditable.setSelected(false);
            socRemis.setVisible(true);
            socDest.setVisible(true);
            itDet.setVisible(true);
            sitems.setVisible(true);
            itContenido.setVisible(false);
            btnFlete.setVisible(false);
            solFin.setVisible(true);
            Utils.addTargetMany(cbEditable,socRemis,socDest,itDet,sitems,solFin,itContenido,btnFlete);
        }
        beanSessionScopeRegistrarFactura.setLstGuias(new ArrayList<BeanTRGuia>());
        beanSessionScopeRegistrarFactura.setLstGuiasConPrecio(new ArrayList<BeanTRGuia>());
        beanSessionScopeRegistrarFactura.setTotal(null);
        beanSessionScopeRegistrarFactura.setIgv(null);
        beanSessionScopeRegistrarFactura.setSubTotal(null);
        beanSessionScopeRegistrarFactura.setRazonCliente(null);
        beanSessionScopeRegistrarFactura.setRazonClienteForFact(null);
        beanSessionScopeRegistrarFactura.setDirec(null);
        beanSessionScopeRegistrarFactura.setCidRepoToDelete(null);
        beanSessionScopeRegistrarFactura.setEmpresaSelected(null);
        beanSessionScopeRegistrarFactura.setLstItemsFinal(new ArrayList<BeanTRItem>());
        beanSessionScopeRegistrarFactura.setLstItemsFinalAll(new ArrayList<BeanTRItem>());
        beanSessionScopeRegistrarFactura.setBdPrecio(null);
        beanSessionScopeRegistrarFactura.setGuia(null);
        beanSessionScopeRegistrarFactura.setEmpresaSelected(null);
        beanSessionScopeRegistrarFactura.setRuc(null);
        beanSessionScopeRegistrarFactura.setLstDirecs(new ArrayList());
        beanSessionScopeRegistrarFactura.setNidClie(null);
        beanSessionScopeRegistrarFactura.setItems(null);
        beanSessionScopeRegistrarFactura.setItemsFin(null);
        beanSessionScopeRegistrarFactura.setLstUbiDirecRemi(null);
        beanSessionScopeRegistrarFactura.setLstUbiDirecDesti(null);
        beanSessionScopeRegistrarFactura.setCidGuiaGrupo(null);
        beanSessionScopeRegistrarFactura.setNoPrecioMalaSecuenciaGrupo(false);
        beanSessionScopeRegistrarFactura.setHashItemsFinal(new HashSet<BeanTRItem>());
        itClien.resetValue();
        itSubTot.resetValue();
        itIGV.resetValue();
        itMonFinal.resetValue();
        socDirecs.resetValue();
        socRemis.resetValue();
        sitems.resetValue();
        socDest.resetValue();
        solFin.resetValue();
        tabGuia.setValue(beanSessionScopeRegistrarFactura.getLstGuias());
        Utils.addTargetMany(tabGuia,itClien,itSubTot,itIGV,itMonFinal,socDirecs,socRemis,socDest,sitems,solFin);
    }
    
    public void cambioEditable(ValueChangeEvent vce) {
        boolean editable = (Boolean)vce.getNewValue();
        beanSessionScopeRegistrarFactura.setEditable(editable);
        beanSessionScopeRegistrarFactura.setPrecioFinalEditable(null);
        if(editable){
            socRemis.setVisible(false);
            socDest.setVisible(false);
            itDet.setVisible(false);
            sitems.setVisible(false);
            solFin.setVisible(false);
            itContenido.setVisible(true);
            btnFlete.setVisible(true);
        }else{
            socRemis.setVisible(true);
            socDest.setVisible(true);
            itDet.setVisible(true);
            sitems.setVisible(true);
            itContenido.setVisible(false);
            btnFlete.setVisible(false);
            solFin.setVisible(true);
        }
        Utils.addTargetMany(socRemis,socDest,itDet,sitems,solFin,itContenido,btnFlete);
    }
    
    public void agregarFlete(ActionEvent actionEvent) {
        if(beanSessionScopeRegistrarFactura.getContenido() == null){
            beanSessionScopeRegistrarFactura.setContenido("");
        }
        itContenido.setValue(itContenido.getValue().toString().concat("                FLETE     :      ...................................................................................................................S/.           0000.00"));
        Utils.addTarget(itContenido);
    }
    
    public void cerrarPrecio(ActionEvent actionEvent) {
        popEdit.hide();
    }
    
    public void setBeanSessionScopeRegistrarFactura(SessionScopeBeanRegistrarFactura beanSessionScopeRegistrarFactura) {
        this.beanSessionScopeRegistrarFactura = beanSessionScopeRegistrarFactura;
    }

    public SessionScopeBeanRegistrarFactura getBeanSessionScopeRegistrarFactura() {
        return beanSessionScopeRegistrarFactura;
    }


    public void setOtTitulo(RichOutputText ot1) {
        this.otTitulo = ot1;
    }

    public RichOutputText getOtTitulo() {
        return otTitulo;
    }

    public void setPglDatos(RichPanelGridLayout pgl1) {
        this.pglDatos = pgl1;
    }

    public RichPanelGridLayout getPglDatos() {
        return pglDatos;
    }

    public void setFilaUno(RichGridRow gr1) {
        this.filaUno = gr1;
    }

    public RichGridRow getFilaUno() {
        return filaUno;
    }

    public void setCelIzq(RichGridCell gc1) {
        this.celIzq = gc1;
    }

    public RichGridCell getCelIzq() {
        return celIzq;
    }

    public void setCelDos(RichGridCell gc2) {
        this.celDos = gc2;
    }

    public RichGridCell getCelDos() {
        return celDos;
    }

    public void setPflDatos(RichPanelFormLayout pfl1) {
        this.pflDatos = pfl1;
    }

    public RichPanelFormLayout getPflDatos() {
        return pflDatos;
    }


    public void setBuscCli(RichCommandButton cb1) {
        this.buscCli = cb1;
    }

    public RichCommandButton getBuscCli() {
        return buscCli;
    }

    public void setItClien(RichInputText it1) {
        this.itClien = it1;
    }

    public RichInputText getItClien() {
        return itClien;
    }

    public void setItIGV(RichInputText it1) {
        this.itIGV = it1;
    }

    public RichInputText getItIGV() {
        return itIGV;
    }

    public void setItSubTot(RichInputText it1) {
        this.itSubTot = it1;
    }

    public RichInputText getItSubTot() {
        return itSubTot;
    }

    public void setItMonFinal(RichInputText it1) {
        this.itMonFinal = it1;
    }

    public RichInputText getItMonFinal() {
        return itMonFinal;
    }

    public void setPopClie(RichPopup p1) {
        this.popClie = p1;
    }

    public RichPopup getPopClie() {
        return popClie;
    }

    public void setDclie(RichDialog d1) {
        this.dclie = d1;
    }

    public RichDialog getDclie() {
        return dclie;
    }

    public void setSfClie(RichSubform s1) {
        this.sfClie = s1;
    }

    public RichSubform getSfClie() {
        return sfClie;
    }

    public void setPflClie(RichPanelFormLayout pfl1) {
        this.pflClie = pfl1;
    }

    public RichPanelFormLayout getPflClie() {
        return pflClie;
    }

    public void setItClieRaz(RichInputText it1) {
        this.itClieRaz = it1;
    }

    public RichInputText getItClieRaz() {
        return itClieRaz;
    }


    public void setTbClie(RichTable t1) {
        this.tbClie = t1;
    }

    public RichTable getTbClie() {
        return tbClie;
    }

    public void setBtnBuscClie(RichCommandButton cb1) {
        this.btnBuscClie = cb1;
    }

    public RichCommandButton getBtnBuscClie() {
        return btnBuscClie;
    }

    public void setTabGuia(RichTable t2) {
        this.tabGuia = t2;
    }

    public RichTable getTabGuia() {
        return tabGuia;
    }

    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }

    public void setSitems(RichSelectManyShuttle sms1) {
        this.sitems = sms1;
    }

    public RichSelectManyShuttle getSitems() {
        return sitems;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public UISelectItems getSi1() {
        return si1;
    }

    public void setS2(RichSeparator s2) {
        this.s2 = s2;
    }

    public RichSeparator getS2() {
        return s2;
    }

    public void setSolFin(RichSelectOneListbox sol1) {
        this.solFin = sol1;
    }

    public RichSelectOneListbox getSolFin() {
        return solFin;
    }

    public void setSiFin(UISelectItems si2) {
        this.siFin = si2;
    }

    public UISelectItems getSiFin() {
        return siFin;
    }


    public void setBtnPrecio(RichCommandButton cb1) {
        this.btnPrecio = cb1;
    }

    public RichCommandButton getBtnPrecio() {
        return btnPrecio;
    }

    public void setPopPrec(RichPopup p1) {
        this.popPrec = p1;
    }

    public RichPopup getPopPrec() {
        return popPrec;
    }

    public void setDiaPre(RichDialog d1) {
        this.diaPre = d1;
    }

    public RichDialog getDiaPre() {
        return diaPre;
    }

    public void setSfPrec(RichSubform s3) {
        this.sfPrec = s3;
    }

    public RichSubform getSfPrec() {
        return sfPrec;
    }

    public void setItPrec(RichInputText it1) {
        this.itPrec = it1;
    }

    public RichInputText getItPrec() {
        return itPrec;
    }

    public void setBtnRegPrec(RichCommandButton cb1) {
        this.btnRegPrec = cb1;
    }

    public RichCommandButton getBtnRegPrec() {
        return btnRegPrec;
    }

    public void setBtnPrevia(RichCommandButton cb1) {
        this.btnPrevia = cb1;
    }

    public RichCommandButton getBtnPrevia() {
        return btnPrevia;
    }

    public void setPopPrevio(RichPopup p1) {
        this.popPrevio = p1;
    }

    public RichPopup getPopPrevio() {
        return popPrevio;
    }

    public void setPwPrevio(RichPanelWindow pw1) {
        this.pwPrevio = pw1;
    }

    public RichPanelWindow getPwPrevio() {
        return pwPrevio;
    }

    public void setIfPrevio(RichInlineFrame if1) {
        this.ifPrevio = if1;
    }

    public RichInlineFrame getIfPrevio() {
        return ifPrevio;
    }

    public void setSocDirecs(RichSelectOneChoice soc1) {
        this.socDirecs = soc1;
    }

    public RichSelectOneChoice getSocDirecs() {
        return socDirecs;
    }

    public void setSi2(UISelectItems si2) {
        this.si2 = si2;
    }

    public UISelectItems getSi2() {
        return si2;
    }


    public void setSocSerie(RichSelectOneChoice soc1) {
        this.socSerie = soc1;
    }

    public RichSelectOneChoice getSocSerie() {
        return socSerie;
    }

    public void setSiSerie(UISelectItems si3) {
        this.siSerie = si3;
    }

    public UISelectItems getSiSerie() {
        return siSerie;
    }

    public void cambioUN(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
    }

    public void setItCodFact(RichInputText it1) {
        this.itCodFact = it1;
    }

    public RichInputText getItCodFact() {
        return itCodFact;
    }

    public void setP1(RichPoll p1) {
        this.p1 = p1;
    }

    public RichPoll getP1() {
        return p1;
    }

    public void setPcodFac(RichPoll p2) {
        this.pcodFac = p2;
    }

    public RichPoll getPcodFac() {
        return pcodFac;
    }

    public void setBtnGrabar(RichCommandButton cb1) {
        this.btnGrabar = cb1;
    }

    public RichCommandButton getBtnGrabar() {
        return btnGrabar;
    }

    public void setSocRemis(RichSelectOneChoice soc1) {
        this.socRemis = soc1;
    }

    public RichSelectOneChoice getSocRemis() {
        return socRemis;
    }

    public void setSi3(UISelectItems si3) {
        this.si3 = si3;
    }

    public UISelectItems getSi3() {
        return si3;
    }

    public void setSocDest(RichSelectOneChoice soc1) {
        this.socDest = soc1;
    }

    public RichSelectOneChoice getSocDest() {
        return socDest;
    }

    public void setSi4(UISelectItems si4) {
        this.si4 = si4;
    }

    public UISelectItems getSi4() {
        return si4;
    }

    public void setBtnRefr(RichCommandButton cb1) {
        this.btnRefr = cb1;
    }

    public RichCommandButton getBtnRefr() {
        return btnRefr;
    }


    public void setSocTipo(RichSelectOneChoice soc1) {
        this.socTipo = soc1;
    }

    public RichSelectOneChoice getSocTipo() {
        return socTipo;
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

    public void setItDet(RichInputText it2) {
        this.itDet = it2;
    }

    public RichInputText getItDet() {
        return itDet;
    }

    public void setItFecFac(RichInputDate id1) {
        this.itFecFac = id1;
    }

    public RichInputDate getItFecFac() {
        return itFecFac;
    }

    public void setCbEditable(RichSelectBooleanCheckbox sbc1) {
        this.cbEditable = sbc1;
    }

    public RichSelectBooleanCheckbox getCbEditable() {
        return cbEditable;
    }

    public void setColObs(RichColumn colObs) {
        this.colObs = colObs;
    }

    public RichColumn getColObs() {
        return colObs;
    }

    public void setPopEdit(RichPopup p2) {
        this.popEdit = p2;
    }

    public RichPopup getPopEdit() {
        return popEdit;
    }

    public void setItPrecEdit(RichInputText it2) {
        this.itPrecEdit = it2;
    }

    public RichInputText getItPrecEdit() {
        return itPrecEdit;
    }

    public void setBtnPrecEdit(RichCommandButton cb1) {
        this.btnPrecEdit = cb1;
    }

    public RichCommandButton getBtnPrecEdit() {
        return btnPrecEdit;
    }

    public void setItContenido(RichInputText it2) {
        this.itContenido = it2;
    }

    public RichInputText getItContenido() {
        return itContenido;
    }


    public void setBtnFlete(RichCommandButton btnFlete) {
        this.btnFlete = btnFlete;
    }

    public RichCommandButton getBtnFlete() {
        return btnFlete;
    }

    public void setColRazSoc(RichColumn colRazSoc) {
        this.colRazSoc = colRazSoc;
    }

    public RichColumn getColRazSoc() {
        return colRazSoc;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    public int getCont() {
        return cont;
    }
}
