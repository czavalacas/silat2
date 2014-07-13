package siat.view.backing.transporte.factura;

import java.io.UnsupportedEncodingException;

import java.math.BigDecimal;

import java.net.URLEncoder;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.naming.Context;
import javax.naming.InitialContext;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichPanelWindow;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.RichSubform;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectItem;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichGridCell;
import oracle.adf.view.rich.component.rich.layout.RichGridRow;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGridLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichInlineFrame;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.event.DialogEvent;

import siat.view.backing.utiles.Utils;

import siat.view.backing.utiles.fecha.FechaUtiles;

import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanFactura;
import silat.servicios_negocio.Beans.BeanNota;
import silat.servicios_negocio.Beans.BeanUnidadMedida;
import silat.servicios_negocio.Beans.BeanUnidadNegocio;
import silat.servicios_negocio.Beans.BeanUsuarioAutenticado;
import silat.servicios_negocio.LNSF.IR.LN_C_SFFacturaRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFPreFacturaRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFUnidadNegocioRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFUtilsRemote;
import silat.servicios_negocio.LNSF.IR.LN_T_SFFacturaRemote;
import silat.servicios_negocio.LNSF.IR.LN_T_SFNotaRemote;

public class Frm_consultar_factura {

    private RichOutputText otTitulo;
    private RichSubform sfrm;
    private RichPanelGridLayout pgl1;
    private RichGridRow gr1;
    private RichGridCell gc1;
    private RichGridCell gc2;
    private RichPanelFormLayout pfl1;
    private RichInputDate idFecMin;
    private RichInputDate idFecMax;
    private RichInputText itCodFac;
    private RichInputText itGuias;
    private RichInputText itClie;
    private RichPanelFormLayout pfl2;
    private RichSelectOneChoice socCanSubTot;
    private RichSelectItem si1;
    private RichSelectItem si2;
    private RichSelectItem si3;
    private RichInputText itSubTot;
    private RichSelectOneChoice socCanTot;
    private RichSelectItem si4;
    private RichSelectItem si5;
    private RichSelectItem si6;
    private RichInputText itTotal;
    private RichSelectOneChoice socEst;
    private UISelectItems siEst;
    private RichSelectOneChoice socUN;
    private UISelectItems siUN;
    private RichCommandButton btnBuscar;
    private RichCommandButton btnLimpiar;
    private RichCommandButton btnVerFact;
    private RichTable tbFact;
    private RichPopup popFact;
    private RichPanelWindow pwFact;
    private RichInlineFrame ifFact;
    private RichCommandButton btnAnular;
    private RichInputText itCmt;
    private RichCommandButton btnPagar;
    private RichPopup popPagar;
    private RichDialog diagPagar;
    private RichCommandButton btnNota;
    private RichPopup popNota;
    private RichDialog diaNota;
    private RichSelectOneChoice socNota;
    private RichSelectItem siCred;
    private RichSelectItem siDeb;
    private RichInputText itMontoNota;
    private RichSelectOneChoice socNotBusq;
    private RichSelectItem sicr;
    private RichSelectItem side;
    private RichSelectOneChoice socMonNot;
    private RichSelectItem siNot;
    private RichSelectItem siMay;
    private RichSelectItem siIg;
    private RichInputText itMonNotBus;
    private RichCommandButton btnActFact;
    private RichPopup popAct;
    private RichInputDate idFec;
    private RichInputText itDet;
    private RichCommandButton cb1;
    /*Mis variables*/
    private final static String LOOKUP_NAME_SFUN_REMOTO = "mapLN_C_SFUnidadNegocio";//#silat.servicios_negocio.LNSF.IR.LN_C_SFUnidadNegocioRemote
    private final static String LOOKUP_NAME_SFUTILS_REMOTO = "mapLN_C_SFUtils";//#silat.servicios_negocio.LNSF.IR.LN_C_SFUtilsRemote
    private final static String LOOKUP_NAME_SFFACT_REMOTO = "mapLN_C_SFFactura";//#silat.servicios_negocio.LNSF.IR.LN_C_SFFacturaRemote
    private final static String LOOKUP_NAME_SFT_FACT_REMOTO = "mapLN_T_SFFactura";//#silat.servicios_negocio.LNSF.IR.LN_T_SFFacturaRemote
    private final static String LOOKUP_NAME_SFT_NOTA_REMOTO = "LUBAL_SIAT_APP-SILATNEGOCIO-LN_T_SFNota";//#silat.servicios_negocio.LNSF.IR.LN_T_SFNotaRemote
    private final static String LOOKUP_NAME_SFCPREFACT_REMOTO     = "mapLN_C_SFPreFactura";
    private SessionBeanScopeConsFactura beanSessionScopeConsFactura;
    private LN_C_SFUnidadNegocioRemote ln_C_SFUnidadNegocioRemote;
    private LN_C_SFUtilsRemote ln_C_SFUtilsRemote;
    private LN_T_SFFacturaRemote ln_T_SFFacturaRemote;
    private LN_C_SFFacturaRemote ln_C_SFFacturaRemote;
    private LN_C_SFPreFacturaRemote ln_C_SFPreFacturaRemote;
    private LN_T_SFNotaRemote ln_T_SFNotaRemote;
    private List lstEstados = new ArrayList();
    private BeanUsuarioAutenticado beanUsuario = new BeanUsuarioAutenticado();
    private final static String sep = "&";
    FacesContext ctx = FacesContext.getCurrentInstance();

    public Frm_consultar_factura() {
        try {
            final Context ctx;
            ctx = new InitialContext();
            ln_C_SFUnidadNegocioRemote = (LN_C_SFUnidadNegocioRemote)ctx.lookup(LOOKUP_NAME_SFUN_REMOTO);
            ln_C_SFUtilsRemote = (LN_C_SFUtilsRemote)ctx.lookup(LOOKUP_NAME_SFUTILS_REMOTO);
            ln_C_SFFacturaRemote = (LN_C_SFFacturaRemote)ctx.lookup(LOOKUP_NAME_SFFACT_REMOTO);
            ln_T_SFFacturaRemote = (LN_T_SFFacturaRemote) ctx.lookup(LOOKUP_NAME_SFT_FACT_REMOTO);
            ln_T_SFNotaRemote = (LN_T_SFNotaRemote) ctx.lookup(LOOKUP_NAME_SFT_NOTA_REMOTO);
            ln_C_SFPreFacturaRemote = (LN_C_SFPreFacturaRemote)        ctx.lookup(LOOKUP_NAME_SFCPREFACT_REMOTO);
            beanUsuario = (BeanUsuarioAutenticado) Utils.getSession("USER");
            this.setLstEstados(Utils.llenarCombos(ln_C_SFUtilsRemote.getListADMCONS("TRMFACT","N_ESTADO_FACTURA")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @PostConstruct 
    public void methodOnPostConstruct(){
        if(beanSessionScopeConsFactura.getExec() == 0){
            beanSessionScopeConsFactura.setExec(1);
            beanSessionScopeConsFactura.setListaUNs(this.llenarUNCombo());
            if(beanUsuario.getNidRol().intValue() == 5){//ES CONTADOR
                beanSessionScopeConsFactura.setRenderIsContador(false);
            }else{
                beanSessionScopeConsFactura.setRenderIsContador(true);
            }
            if(Utils.hasPermiso(beanUsuario.getLstPermisos(),new BigDecimal("56"))){//Cambiar Fechas
                beanSessionScopeConsFactura.setRenderBtnFecha(true);
            }
            if(Utils.hasPermiso(beanUsuario.getLstPermisos(),new BigDecimal("57"))){//Pagar Factura
                beanSessionScopeConsFactura.setRenderPagarFactura57(true);
            }
        }else{
            buscarFacturas();
        }
    }

    public String buscarFacturas(){
        Date fecMin = beanSessionScopeConsFactura.getFecMin();
        Date fecMax = beanSessionScopeConsFactura.getFecMax();
        BigDecimal subTotal = beanSessionScopeConsFactura.getSubTotal();
        int estado = beanSessionScopeConsFactura.getEstadoFactura() != null ? Integer.parseInt(beanSessionScopeConsFactura.getEstadoFactura()) : 0;
        BigDecimal total = beanSessionScopeConsFactura.getTotal();
        String codFact = beanSessionScopeConsFactura.getCodFact();
        String cidUN = beanSessionScopeConsFactura.getCidUn();
        String guias = beanSessionScopeConsFactura.getCodGuia();
        String cliente = beanSessionScopeConsFactura.getCliente();
        String simboloSubTotal = beanSessionScopeConsFactura.getSimboloSubTotal();
        String simboloTotal = beanSessionScopeConsFactura.getSimboloTotal();
        String tipNota = beanSessionScopeConsFactura.getBusqTipNota();
        String simbMontNota = beanSessionScopeConsFactura.getSimbNota();
        BigDecimal montNota = beanSessionScopeConsFactura.getBusqMontoNota();
        beanSessionScopeConsFactura.setLstFacturas(ln_C_SFFacturaRemote.findFacturasByAttr_LN(fecMin,fecMax,subTotal,total,
                                                                                              estado,codFact,cidUN,guias,
                                                                                              cliente,simboloSubTotal,simboloTotal,
                                                                                              tipNota,simbMontNota,montNota));
        if(tbFact != null){
            tbFact.setValue(beanSessionScopeConsFactura.getLstFacturas());
            Utils.addTarget(tbFact);
        }
        return null;
    }
    
    public void limpiar(ActionEvent ae){
        _limpiar();
    }
    
    public void pagarFact(ActionEvent ae){
        if(beanSessionScopeConsFactura.getFacturaSelect().getNidFactura() == null){
            Utils.throwError_Aux(ctx,"Debe seleccionar una factura para pagarla.", 4);
            return;
        }
        if(beanSessionScopeConsFactura.getFacturaSelect().getNEstadoFactura() != 2){
            Utils.throwError_Aux(ctx,"La factura debe estar en estado POR CANCELAR.", 4);
            return;
        }
        Utils.showPopUpMIDDLE(popPagar);
    }
    
    public void agregarNota(ActionEvent ae){
        if(beanSessionScopeConsFactura.getFacturaSelect().getNidFactura() == null){
            Utils.throwError_Aux(ctx,"Debe seleccionar una factura para pagarla.", 4);
            return;
        }
        if(beanSessionScopeConsFactura.getFacturaSelect().getNEstadoFactura() != 2){
            Utils.throwError_Aux(ctx,"La factura debe estar en estado POR CANCELAR.", 4);
            return;
        }
        int hasNota = ln_C_SFFacturaRemote.cantNotaByFactura_LN(beanSessionScopeConsFactura.getFacturaSelect().getNidFactura());
        if(hasNota > 0){
            Utils.throwError_Aux(ctx, "La factura ya tiene registrada una Nota.", 4);
            return;
        }
        Utils.showPopUpMIDDLE(popNota);
    }
    
    public void verFactura(ActionEvent ae){
        try {
            if (beanSessionScopeConsFactura.getFacturaSelect().getNidFactura() == null) {
                Utils.throwError_Aux(ctx, "Debe seleccionar una factura para anularla.", 4);
                return;
            }
            BeanFactura beanFact = beanSessionScopeConsFactura.getFacturaSelect();
            String jasper = ("1".equals(beanFact.getEditable()) ? "vista_print_factura_editable.jasper" : "vista_print_factura.jasper");
            String monto = beanFact.getDTotal().toString();
            String igv = beanFact.getIgvFact().toString();
            String subTotal = beanFact.getDSubTotal().toString();
            String cliente = beanFact.getCliente();
            String guias = beanFact.getGuiasForReporte();
            String direccion = beanFact.getDireccion();
            String codFact = beanFact.getCidUnidadNegocio() + "-" + beanFact.getCCodFactura();
            String timePath = codFact;
            String ruc = beanFact.getRuc();
            String estado = String.valueOf(beanFact.getNEstadoFactura());
            Date fechaPago = beanFact.getFechaPago();
            Date fechaFactura = beanFact.getFechaFactura();
            String tipFactRepo = beanFact.getTipoFactura();
            String detalle = beanFact.getDetalle() != null ? beanFact.getDetalle().replaceAll("%","") : beanFact.getDetalle();
            String contenido = beanFact.getContenido() != null ? beanFact.getContenido().replaceAll("%","") : beanFact.getContenido();
            String pedido = null;
            /*if(!tipFactRepo.equals("1")){
            pedido = ln_C_SFPreFacturaRemote.getCodigoPedidoByPreFactura(beanFact.getNidPreFactura());
            guias = beanFact.getGuiasForReporte();
        }*/
            String nidPreFact = "";
            if (beanFact.getNidPreFactura() != null) {
                nidPreFact = beanFact.getNidPreFactura().toString();
            }
            String path =
                "/showpdfservlet?jasper=" + jasper + sep + "monto=" + monto + sep + "igv=" + igv + sep + "subTotal=" +
                subTotal + sep + "cliente=" + URLEncoder.encode(cliente, "UTF-8") + sep + "guias=" + guias + sep + "direccion=" +
                URLEncoder.encode(direccion, "UTF-8") + sep + "timepath=" + timePath + sep + "ruc=" + ruc + sep +
                "codFact=" + codFact + sep + "estado=" + estado + sep + "fecha_pago=" + fechaPago + sep + "fechaHoy=" +
                fechaFactura + sep + "tipFactRepo=" + tipFactRepo + sep + "nidPreFact=" + nidPreFact + sep +
                "pedido=" + pedido + sep + "formato_repo=PDF" + sep + "detalle=" + detalle + sep + "contenido=" +
                URLEncoder.encode(contenido, "UTF-8");
            //   Utils.depurar("pathttttt: "+path);
            beanSessionScopeConsFactura.setSourcePrevio(path);
            Utils.showPopUpMIDDLE(popFact);
        } catch (Exception uee) {
            uee.printStackTrace();
        }
    }
    
    public List<BeanFactura> _limpiar() {
        beanSessionScopeConsFactura.setFecMin(null);
        beanSessionScopeConsFactura.setFecMax(null);
        beanSessionScopeConsFactura.setSubTotal(null);
        beanSessionScopeConsFactura.setEstadoFactura(null);
        beanSessionScopeConsFactura.setTotal(null); 
        beanSessionScopeConsFactura.setCodFact(null);
        beanSessionScopeConsFactura.setCidUn(null);
        beanSessionScopeConsFactura.setCodGuia(null);
        beanSessionScopeConsFactura.setCliente(null);
        beanSessionScopeConsFactura.setSimboloSubTotal(null);
        beanSessionScopeConsFactura.setSimboloTotal(null);
        beanSessionScopeConsFactura.setBusqTipNota(null);
        beanSessionScopeConsFactura.setSimbNota(null);
        beanSessionScopeConsFactura.setBusqMontoNota(null);
        beanSessionScopeConsFactura.setLstFacturas(ln_C_SFFacturaRemote.findFacturasByAttr_LN(null,null,new BigDecimal(0),new BigDecimal(0),
                                                                                              0,null,null,null,null,
                                                                                              null,null,null,null,new BigDecimal(0)));
        if(tbFact != null){
            tbFact.setValue(beanSessionScopeConsFactura.getLstFacturas());
            Utils.addTarget(tbFact);
        }
        return beanSessionScopeConsFactura.getLstFacturas();
    }
    
    public void selectFactura(SelectionEvent se){
        RichTable t = (RichTable) se.getSource();
        BeanFactura beanFact = (BeanFactura) t.getSelectedRowData();
        beanSessionScopeConsFactura.setFacturaSelect(beanFact);
        if(beanFact.getNEstadoFactura() != null){
            if(beanFact.getNEstadoFactura() == 3 || beanFact.getNEstadoFactura() == 1){//la factura seleccionada esta anulada o cancelada
                btnAnular.setDisabled(true);
                btnPagar.setDisabled(true);
                btnNota.setDisabled(true);
                btnActFact.setDisabled(true);
            }else{
                btnAnular.setDisabled(false);
                if(beanSessionScopeConsFactura.isRenderPagarFactura57()){//seteado en el postConstruct
                    btnPagar.setDisabled(false);   
                }
                btnNota.setDisabled(false);
                btnActFact.setDisabled(false);
            }
            if(beanSessionScopeConsFactura.isRenderBtnFecha()){//seteado en el postConstruct
                btnActFact.setDisabled(false);
            }
            if(beanFact.getBeanNota() != null){
                if(beanFact.getBeanNota().getCTipoNota() != null){
                    btnNota.setDisabled(true);
                }
            }else{
                btnNota.setDisabled(false);
            }
            btnVerFact.setDisabled(false);
            Utils.addTargetMany(btnAnular,btnPagar,btnNota,btnVerFact,btnActFact);
        }else{
            buscarFacturas();   
        }
    }
    
    public void dialogLogoutListener(DialogEvent de) {//ANULAR FACTURA
        if (de.getOutcome() == DialogEvent.Outcome.ok) {
            if(beanSessionScopeConsFactura.getFacturaSelect().getNidFactura() == null){
                Utils.throwError_Aux(ctx,"Debe seleccionar una factura para anularla.", 4);
                return;
            }
            String comentarioAnular = beanSessionScopeConsFactura.getComentarioAnular();
            Long nidFac = beanSessionScopeConsFactura.getFacturaSelect().getNidFactura();
            BeanFactura bFactura = ln_T_SFFacturaRemote.anularFactura_LN(comentarioAnular, nidFac);
            if(bFactura.getBeanError() != null){
                BeanError error = bFactura.getBeanError();
                int severidad = 0;
                if("000".equals(error.getCidError())){
                    severidad = 3;
                    _limpiar();
                    Utils.unselectFilas(tbFact);
                    Utils.depurar("Anulo la factura");
                }else{
                    severidad = 1;
                }
                Utils.throwError_Aux(ctx,error.getCDescripcionError(), severidad);
                beanSessionScopeConsFactura.setComentarioAnular(null);
                if(itCmt != null){
                    itCmt.resetValue();
                }
            }else{
                Utils.throwError_Aux(ctx,"Error Inesperado", 1);
            }
        }
    }
    
    public void dialogPagarListener(DialogEvent de){
        if (de.getOutcome() == DialogEvent.Outcome.ok){
            Long nidFactura = beanSessionScopeConsFactura.getFacturaSelect().getNidFactura();
            BeanFactura bFactura = ln_T_SFFacturaRemote.pagarFactura_LN(nidFactura);
            if(bFactura.getBeanError() != null){
                BeanError error = bFactura.getBeanError();
                int severidad = 0;
                if("000".equals(error.getCidError())){
                    severidad = 3;
                    tbFact.setValue(_limpiar());
                    Utils.unselectFilas(tbFact);
                    Utils.addTarget(tbFact);
                    Utils.depurar("Se pago la factura");
                }else{
                    severidad = 1;
                }
                Utils.throwError_Aux(ctx,error.getCDescripcionError(), severidad);
            }else{
                Utils.throwError_Aux(ctx,"Error Inesperado", 1);
            }     
        }
    }
    
    public void dialogNotaListener(DialogEvent de){
        if (de.getOutcome() == DialogEvent.Outcome.ok){
            BeanFactura bFactura = beanSessionScopeConsFactura.getFacturaSelect();
            BigDecimal montoNota = beanSessionScopeConsFactura.getMontoNota();
            String tipNota = beanSessionScopeConsFactura.getTipNota();
            if("C".equals(tipNota)){
                if(montoNota.compareTo(bFactura.getDSubTotal()) >= 0 ){//la nota es mayor o igual q el monto final
                    Utils.throwError_Aux(ctx,"El monto de la nota no puede ser mayor o igual que la factura",4);
                    return;
                }
            }
            BeanNota beanNota = ln_T_SFNotaRemote.registrarNota(tipNota,montoNota,bFactura);
            if(beanNota.getBeanError() != null){
                BeanError error = beanNota.getBeanError();
                int severidad = 0;
                if("000".equals(error.getCidError())){
                    severidad = 3;
                    tbFact.setValue(_limpiar());
                    Utils.unselectFilas(tbFact);
                    Utils.addTarget(tbFact);
                    Utils.depurar("Se agrego la Nota");
                }else{
                    severidad = 1;
                }
                Utils.throwError_Aux(ctx,error.getCDescripcionError(), severidad);
            }else{
                Utils.throwError_Aux(ctx,"Error Inesperado", 1);
            }
        }
    }
    
    public void actualizarFactura(DialogEvent de) {
        if (de.getOutcome() == DialogEvent.Outcome.ok){
            Long nidFactura = beanSessionScopeConsFactura.getFacturaSelect().getNidFactura();
            BeanFactura bFactura = ln_T_SFFacturaRemote.actualizarFactura_LN(beanSessionScopeConsFactura.getDetalleFactura(),
                                                                             beanSessionScopeConsFactura.getFechaFactura(),
                                                                             nidFactura,
                                                                             beanSessionScopeConsFactura.isRenderBtnFecha());
            if(bFactura.getBeanError() != null){
                BeanError error = bFactura.getBeanError();
                int severidad = 0;
                if("000".equals(error.getCidError())){
                    severidad = 3;
                    tbFact.setValue(_limpiar());
                    Utils.unselectFilas(tbFact);
                    Utils.addTarget(tbFact);
                    beanSessionScopeConsFactura.setDetalleFactura(null);
                    itDet.setValue(null);
                    Utils.depurar("Se actualizo la factura");
                }else{
                    severidad = 1;
                }
                Utils.throwError_Aux(ctx,error.getCDescripcionError(), severidad);
            }else{
                Utils.throwError_Aux(ctx,"Error Inesperado", 1);
            }
        }
    }
    
    public void abrirPopAct(ActionEvent actionEvent) {
        if(beanSessionScopeConsFactura.getFacturaSelect().getNidFactura() == null){
            Utils.throwError_Aux(ctx,"Debe seleccionar una factura para actualizarla.", 4);
            return;
        }
        if(beanSessionScopeConsFactura.getFacturaSelect().getNEstadoFactura() != 2 && beanSessionScopeConsFactura.isRenderBtnFecha() == false){
            Utils.throwError_Aux(ctx,"La factura debe estar en estado POR CANCELAR.", 4);
            return;
        }
        beanSessionScopeConsFactura.setDetalleFactura(beanSessionScopeConsFactura.getFacturaSelect().getDetalle());
        beanSessionScopeConsFactura.setFechaFactura(beanSessionScopeConsFactura.getFacturaSelect().getFechaFactura());
        Utils.showPopUpMIDDLE(popAct);
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
    
    public void setOtTitulo(RichOutputText ot1) {
        this.otTitulo = ot1;
    }

    public RichOutputText getOtTitulo() {
        return otTitulo;
    }

    public void setSfrm(RichSubform s1) {
        this.sfrm = s1;
    }

    public RichSubform getSfrm() {
        return sfrm;
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

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setIdFecMin(RichInputDate id1) {
        this.idFecMin = id1;
    }

    public RichInputDate getIdFecMin() {
        return idFecMin;
    }

    public void setIdFecMax(RichInputDate id2) {
        this.idFecMax = id2;
    }

    public RichInputDate getIdFecMax() {
        return idFecMax;
    }

    public void setItCodFac(RichInputText it1) {
        this.itCodFac = it1;
    }

    public RichInputText getItCodFac() {
        return itCodFac;
    }

    public void setItGuias(RichInputText it2) {
        this.itGuias = it2;
    }

    public RichInputText getItGuias() {
        return itGuias;
    }

    public void setItClie(RichInputText it3) {
        this.itClie = it3;
    }

    public RichInputText getItClie() {
        return itClie;
    }

    public void setBeanSessionScopeConsFactura(SessionBeanScopeConsFactura beanSessionScopeConsFactura) {
        this.beanSessionScopeConsFactura = beanSessionScopeConsFactura;
    }

    public SessionBeanScopeConsFactura getBeanSessionScopeConsFactura() {
        return beanSessionScopeConsFactura;
    }

    public void setLstEstados(List lstEstados) {
        this.lstEstados = lstEstados;
    }

    public List getLstEstados() {
        return lstEstados;
    }

    public void setPfl2(RichPanelFormLayout pfl2) {
        this.pfl2 = pfl2;
    }

    public RichPanelFormLayout getPfl2() {
        return pfl2;
    }

    public void setSocCanSubTot(RichSelectOneChoice soc1) {
        this.socCanSubTot = soc1;
    }

    public RichSelectOneChoice getSocCanSubTot() {
        return socCanSubTot;
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

    public void setItSubTot(RichInputText it1) {
        this.itSubTot = it1;
    }

    public RichInputText getItSubTot() {
        return itSubTot;
    }

    public void setSocCanTot(RichSelectOneChoice soc1) {
        this.socCanTot = soc1;
    }

    public RichSelectOneChoice getSocCanTot() {
        return socCanTot;
    }

    public void setSi4(RichSelectItem si4) {
        this.si4 = si4;
    }

    public RichSelectItem getSi4() {
        return si4;
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

    public void setItTotal(RichInputText it1) {
        this.itTotal = it1;
    }

    public RichInputText getItTotal() {
        return itTotal;
    }

    public void setSocEst(RichSelectOneChoice soc1) {
        this.socEst = soc1;
    }

    public RichSelectOneChoice getSocEst() {
        return socEst;
    }

    public void setSiEst(UISelectItems si7) {
        this.siEst = si7;
    }

    public UISelectItems getSiEst() {
        return siEst;
    }

    public void setSocUN(RichSelectOneChoice soc1) {
        this.socUN = soc1;
    }

    public RichSelectOneChoice getSocUN() {
        return socUN;
    }

    public void setSiUN(UISelectItems si7) {
        this.siUN = si7;
    }

    public UISelectItems getSiUN() {
        return siUN;
    }

    public void setBtnBuscar(RichCommandButton cb1) {
        this.btnBuscar = cb1;
    }

    public RichCommandButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnLimpiar(RichCommandButton cb1) {
        this.btnLimpiar = cb1;
    }

    public RichCommandButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public void setBtnVerFact(RichCommandButton cb1) {
        this.btnVerFact = cb1;
    }

    public RichCommandButton getBtnVerFact() {
        return btnVerFact;
    }

    public void setTbFact(RichTable t1) {
        this.tbFact = t1;
    }

    public RichTable getTbFact() {
        return tbFact;
    }

    public void setPopFact(RichPopup p1) {
        this.popFact = p1;
    }

    public RichPopup getPopFact() {
        return popFact;
    }

    public void setPwFact(RichPanelWindow pw1) {
        this.pwFact = pw1;
    }

    public RichPanelWindow getPwFact() {
        return pwFact;
    }

    public void setIfFact(RichInlineFrame if1) {
        this.ifFact = if1;
    }

    public RichInlineFrame getIfFact() {
        return ifFact;
    }

    public void setBtnAnular(RichCommandButton cb1) {
        this.btnAnular = cb1;
    }

    public RichCommandButton getBtnAnular() {
        return btnAnular;
    }

    public void setItCmt(RichInputText it1) {
        this.itCmt = it1;
    }

    public RichInputText getItCmt() {
        return itCmt;
    }

    public void setBtnPagar(RichCommandButton cb1) {
        this.btnPagar = cb1;
    }

    public RichCommandButton getBtnPagar() {
        return btnPagar;
    }

    public void setPopPagar(RichPopup p1) {
        this.popPagar = p1;
    }

    public RichPopup getPopPagar() {
        return popPagar;
    }

    public void setDiagPagar(RichDialog d1) {
        this.diagPagar = d1;
    }

    public RichDialog getDiagPagar() {
        return diagPagar;
    }

    public void setBtnNota(RichCommandButton cb1) {
        this.btnNota = cb1;
    }

    public RichCommandButton getBtnNota() {
        return btnNota;
    }

    public void setPopNota(RichPopup p1) {
        this.popNota = p1;
    }

    public RichPopup getPopNota() {
        return popNota;
    }

    public void setDiaNota(RichDialog d1) {
        this.diaNota = d1;
    }

    public RichDialog getDiaNota() {
        return diaNota;
    }

    public void setSocNota(RichSelectOneChoice soc1) {
        this.socNota = soc1;
    }

    public RichSelectOneChoice getSocNota() {
        return socNota;
    }

    public void setSiCred(RichSelectItem si7) {
        this.siCred = si7;
    }

    public RichSelectItem getSiCred() {
        return siCred;
    }

    public void setSiDeb(RichSelectItem si8) {
        this.siDeb = si8;
    }

    public RichSelectItem getSiDeb() {
        return siDeb;
    }

    public void setItMontoNota(RichInputText it1) {
        this.itMontoNota = it1;
    }

    public RichInputText getItMontoNota() {
        return itMontoNota;
    }

    public void setSocNotBusq(RichSelectOneChoice soc1) {
        this.socNotBusq = soc1;
    }

    public RichSelectOneChoice getSocNotBusq() {
        return socNotBusq;
    }

    public void setSicr(RichSelectItem si7) {
        this.sicr = si7;
    }

    public RichSelectItem getSicr() {
        return sicr;
    }

    public void setSide(RichSelectItem si8) {
        this.side = si8;
    }

    public RichSelectItem getSide() {
        return side;
    }

    public void setSocMonNot(RichSelectOneChoice soc1) {
        this.socMonNot = soc1;
    }

    public RichSelectOneChoice getSocMonNot() {
        return socMonNot;
    }

    public void setSiNot(RichSelectItem si7) {
        this.siNot = si7;
    }

    public RichSelectItem getSiNot() {
        return siNot;
    }

    public void setSiMay(RichSelectItem si8) {
        this.siMay = si8;
    }

    public RichSelectItem getSiMay() {
        return siMay;
    }

    public void setSiIg(RichSelectItem si9) {
        this.siIg = si9;
    }

    public RichSelectItem getSiIg() {
        return siIg;
    }

    public void setItMonNotBus(RichInputText it1) {
        this.itMonNotBus = it1;
    }

    public RichInputText getItMonNotBus() {
        return itMonNotBus;
    }

    public void setBtnActFact(RichCommandButton cb1) {
        this.btnActFact = cb1;
    }

    public RichCommandButton getBtnActFact() {
        return btnActFact;
    }

    public void setPopAct(RichPopup p1) {
        this.popAct = p1;
    }

    public RichPopup getPopAct() {
        return popAct;
    }

    public void setIdFec(RichInputDate id1) {
        this.idFec = id1;
    }

    public RichInputDate getIdFec() {
        return idFec;
    }

    public void setItDet(RichInputText it1) {
        this.itDet = it1;
    }

    public RichInputText getItDet() {
        return itDet;
    }

    public void setCb1(RichCommandButton cb1) {
        this.cb1 = cb1;
    }

    public RichCommandButton getCb1() {
        return cb1;
    }


}
