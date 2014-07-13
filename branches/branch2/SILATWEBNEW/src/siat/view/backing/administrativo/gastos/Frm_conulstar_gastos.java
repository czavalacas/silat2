package siat.view.backing.administrativo.gastos;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.component.UISelectItems;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import javax.naming.Context;
import javax.naming.InitialContext;

import oracle.adf.view.rich.component.rich.RichDialog;
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
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import siat.view.backing.utiles.Utils;

import silat.servicios_negocio.BDLSF.IR.BDL_C_SFFlotaRemote;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFModalidadPago;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFTipoGastoRemoto;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFUtilsRemote;
import silat.servicios_negocio.Beans.BeanADRelacionEmpresa;
import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanFlota;
import silat.servicios_negocio.Beans.BeanGasto;
import silat.servicios_negocio.Beans.BeanModalidadPago;
import silat.servicios_negocio.Beans.BeanTipoGasto;
import silat.servicios_negocio.Beans.BeanUsuarioAutenticado;
import silat.servicios_negocio.LNSF.IR.LN_C_SFGastosRemoto;
import silat.servicios_negocio.LNSF.IR.LN_C_SFRelacionEmpresaRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFTipoGastoRemoto;
import silat.servicios_negocio.LNSF.IR.LN_T_SFGastosRemoto;
import silat.servicios_negocio.entidades.admin.ADFlota;
import silat.servicios_negocio.entidades.admin.ADModalidadPago;
import silat.servicios_negocio.entidades.admin.ADTipoGasto;

public class Frm_conulstar_gastos {
    
    private RichOutputText otTitulo;    
    private RichPanelGridLayout pgl1;
    private RichGridRow gr1;
    private RichGridCell gc1;
    private RichGridCell gc2;
    private RichPanelFormLayout pfl1;
    private RichPanelFormLayout pfl2;
    private RichSelectOneChoice choiceTipoGasto;
    private UISelectItems si1;
    private RichInputText inputTextProveedor;
    private RichSelectOneChoice choiceModoPago;
    private UISelectItems si2;
    private RichInputText inputTextDetalle;
    private RichInputText inputTextFactura;
    private RichInputDate inputDateFechaMinimaid1;
    private RichInputDate inputDateFechaMaxima;
    private RichPanelGridLayout pgl2;
    private RichGridRow gr2;
    private RichGridCell gc3;
    private RichGridCell gc4;
    private RichSelectOneChoice choiceSimboloMonto;
    private UISelectItems si3;
    private RichInputText inputTextMonto;
    private RichCommandButton btnBuscarProveedor;
    private RichTable tbGast;
    private RichCommandButton btnBuscar;
    private RichPanelGridLayout pgl3;
    private RichGridRow gr3;
    private RichGridCell gc5;
    private RichGridCell gc6;
    private RichGridCell gc7;
    private RichGridCell gc8;
    private RichCommandButton btnLimpiar;
    private RichCommandButton btnAnular;
    private RichCommandButton btnExportarExcel;
    private RichPanelGroupLayout pgl4;
    private RichSelectItem si4;
    private RichSelectItem si5;
    private RichSelectItem si6;
    private RichPopup popupProveedor;
    private RichDialog d1;
    private RichTable tbProv;
    private RichSubform s1;
    private RichPopup popImg;
    private RichDialog d2;
    private RichImage imgGasto;
    private RichCommandButton btnImg;
    /*Mis variables*/
    private LN_C_SFTipoGastoRemoto ln_C_SFTipoGastoRemoto;
    private final static String LOOKUP_NAME_SFTIPOGASTO_REMOTO =
        "LUBAL_SIAT_APP-SILATNEGOCIO-LN_C_SFTipoGasto";//#silat.servicios_negocio.LNSF.IR.LN_C_SFTipoGastoRemoto
    private BDL_C_SFModalidadPago bdl_C_SFModalidadPago;
    private final static String LOOKUP_NAME_SFMODALIDADPAGO_REMOTO =
        "LUBAL_SIAT_APP-SILATNEGOCIO-BDL_C_SFModalidadPago";//#silat.servicios_negocio.BDLSF.IR.BDL_C_SFModalidadPago
    private BDL_C_SFUtilsRemote bdl_C_SFUtilsRemote;
    private final static String LOOKUP_NAME_SFUTIL_REMOTO =
        "mapBDL_C_SFUtils";//#silat.servicios_negocio.BDLSF.IR.BDL_C_SFUtilsRemote
    private BDL_C_SFFlotaRemote bdl_C_SFFlotaRemote;
    private final static String LOOKUP_NAME_SFFLOTA_REMOTO =
        "mapBDL_C_SFFlota";//#silat.servicios_negocio.BDLSF.IR.BDL_C_SFFlotaRemote
    private LN_C_SFRelacionEmpresaRemote ln_C_SFRelacionEmpresaRemote;
    private final static String LOOKUP_NAME_SFRELAEMPRE_REMOTO =
        "mapLN_C_SFRelacionEmpresa";//#silat.servicios_negocio.LNSF.IR.LN_C_SFRelacionEmpresaRemote
    private LN_T_SFGastosRemoto ln_T_SFGastosRemoto;
    private final static String LOOKUP_NAME_SFGASTOS_REMOTO =
        "LUBAL_SIAT_APP-SILATNEGOCIO-LN_T_SFGastos";//#silat.servicios_negocio.LNSF.IR.LN_T_SFGastosRemoto
    private LN_C_SFGastosRemoto ln_C_SFGastosRemoto;
    private final static String LOOKUP_NAME_SFGASTOSCONSULTA_REMOTO = "LUBAL_SIAT_APP-SILATNEGOCIO-LN_C_SFGastos";
    private SessionScopeBeanConsultarGastos beanSessionScopedConsultarGastos;
    private List TipoGastos;
    private List ModalidadPago;
    private List ListaFlotasLubal;
    FacesContext ctx = FacesContext.getCurrentInstance();
    private BeanEmpresa beanEmpresa = new BeanEmpresa();
    private BeanUsuarioAutenticado beanUsuario= new BeanUsuarioAutenticado();
    private List<BeanEmpresa> listaProveedores = new ArrayList<BeanEmpresa>();
    private RichSelectOneChoice soc1;
    private UISelectItems si7;

    public Frm_conulstar_gastos() {
        try {
            final Context ctx;
            ctx = new InitialContext();
            ln_C_SFTipoGastoRemoto = (LN_C_SFTipoGastoRemoto) ctx.lookup(LOOKUP_NAME_SFTIPOGASTO_REMOTO);
            bdl_C_SFModalidadPago = (BDL_C_SFModalidadPago)ctx.lookup(LOOKUP_NAME_SFMODALIDADPAGO_REMOTO);
            bdl_C_SFUtilsRemote = (BDL_C_SFUtilsRemote)ctx.lookup(LOOKUP_NAME_SFUTIL_REMOTO);
            bdl_C_SFFlotaRemote = (BDL_C_SFFlotaRemote)ctx.lookup(LOOKUP_NAME_SFFLOTA_REMOTO);
            ln_C_SFRelacionEmpresaRemote = (LN_C_SFRelacionEmpresaRemote)ctx.lookup(LOOKUP_NAME_SFRELAEMPRE_REMOTO);
            ln_T_SFGastosRemoto = (LN_T_SFGastosRemoto)ctx.lookup(LOOKUP_NAME_SFGASTOS_REMOTO);
            ln_C_SFGastosRemoto = (LN_C_SFGastosRemoto)ctx.lookup(LOOKUP_NAME_SFGASTOSCONSULTA_REMOTO);
            this.setTipoGastos(this.llenarTipoGastoCombo());
            this.setModalidadPago(this.llenarModalidadPago());        
            this.setListaProveedores(this.traerProveedores());
            this.setListaFlotasLubal(this.llenarFlotaLubal());
            beanUsuario = (BeanUsuarioAutenticado) Utils.getSession("USER");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @PostConstruct
    public void methodInvokeOncedOnPageLoad(){
        if(getBeanSessionScopedConsultarGastos().getExec() == 0){
            getBeanSessionScopedConsultarGastos().setExec(1);
            filtrarGastos();
        }else{
            //Utils.depurar("POST CONSTRUCT otras veces");
        }
    }
    
    public List<BeanEmpresa> traerProveedores() {
        List<BeanADRelacionEmpresa> listaRela = ln_C_SFRelacionEmpresaRemote.getEmpresaProveedores("");
        List<BeanEmpresa> listaEmpresa = new ArrayList<BeanEmpresa>();
        Iterator it = listaRela.iterator();
        while (it.hasNext()) {
            BeanADRelacionEmpresa beanRela = (BeanADRelacionEmpresa)it.next();
            BeanEmpresa beanEmpresa = new BeanEmpresa();
            beanEmpresa.setNidParty(beanRela.getAdEmpresa1().getNidParty());
            beanEmpresa.setCRazonSocial(beanRela.getAdEmpresa1().getCRazonSocial());
            beanEmpresa.setCRuc(beanRela.getAdEmpresa1().getCRuc());
            beanEmpresa.setAdParty(beanRela.getAdEmpresa1().getAdParty());
            listaEmpresa.add(beanEmpresa);
        }
        return listaEmpresa;
    }

    public String AnularGasto() {
        if (Utils.hasPermiso(beanUsuario.getLstPermisos(), new BigDecimal("18"))) {
            String estado = "0";
            BeanGasto bGasto = ln_T_SFGastosRemoto.anularGasto(beanSessionScopedConsultarGastos.getNidGasto(), estado);
            tbGast.setValue(clearLista());
            Utils.addTarget(tbGast);
            if (bGasto.getBeanError() != null) {
                BeanError error = bGasto.getBeanError();
                int severidad = 0;
                if (error.getCidError().equals("000")) {
                    severidad = 3;
                    Utils.depurar("GASTO ANULADO");
                } else {
                    severidad = 1;
                }
                Utils.throwError_Aux(ctx, error.getCDescripcionError(), severidad);
            } else {
                Utils.throwError_Aux(ctx, "Error Inesperado", 1);
            }
        } else {
            Utils.throwError_Aux(ctx, "NO TIENE PERMISOS PARA REALIZAR ESTA ACCION", 1);
        }
        return null;
    }

    public String getPermisoExportarExcel() {
        if (Utils.hasPermiso(beanUsuario.getLstPermisos(), new BigDecimal("47"))) {
            return "true";
        } else {
            return "false";
        }
    }
    
    public void seleccionarProveedor(SelectionEvent selectionEvent) {
        RichTable t = (RichTable)selectionEvent.getSource();
        Object _selectedRowData = t.getSelectedRowData();
        beanEmpresa = (BeanEmpresa)_selectedRowData;
        beanSessionScopedConsultarGastos.setBeanEmpresa(beanEmpresa);
        beanSessionScopedConsultarGastos.setNidProveedor(beanEmpresa.getNidParty().longValue());
        inputTextProveedor.setValue(beanEmpresa.getCRazonSocial());
        Utils.addTarget(inputTextProveedor);
    }
    
    public ArrayList llenarModalidadPago() {
        ArrayList unItems = new ArrayList();
        List<ADModalidadPago> roles = bdl_C_SFModalidadPago.getAdmmopaFindAll();
        for (ADModalidadPago r : roles) {
            unItems.add(new SelectItem(r.getNidModalidadPago().toString(),
                                       r.getDescripcionModalidadPago().toString()));
        }
        return unItems;
    }
    
    public ArrayList llenarTipoGastoCombo() {
        ArrayList unItems = new ArrayList();
        List<BeanTipoGasto> tipoGastos = ln_C_SFTipoGastoRemoto.getTiposGastosAll_LN(null,"1");
        for (BeanTipoGasto r : tipoGastos) {
            unItems.add(new SelectItem(r.getNidTiga().toString(),
                                       r.getDescripcionTipoGasto().toString()));
        }
        return unItems;
    }

    public String filtrarGastos() {
        beanSessionScopedConsultarGastos.setListGasto(null);
        BeanGasto beanGasto = new BeanGasto();
        if (beanSessionScopedConsultarGastos.getNidTipoGasto() != null) {
            BeanTipoGasto tipoGasto = new BeanTipoGasto();
            tipoGasto.setNidTiga(Integer.parseInt(beanSessionScopedConsultarGastos.getNidTipoGasto()));
            beanGasto.setTipoGasto(tipoGasto);
        }
        if (beanSessionScopedConsultarGastos.getNidModalidadPago() != null) {
            BeanModalidadPago modaPago = new BeanModalidadPago();
            modaPago.setNidModalidadPago(Integer.parseInt(beanSessionScopedConsultarGastos.getNidModalidadPago()));
            beanGasto.setModalidadPago(modaPago);
        }
        if(beanSessionScopedConsultarGastos.getNidFlotaCombustible() != null){
            BeanFlota bFlota = new BeanFlota();
            bFlota.setNidFlota(beanSessionScopedConsultarGastos.getNidFlotaCombustible());
            beanGasto.setAdFlota(bFlota);
        }
        beanGasto.setSimboloMonto(beanSessionScopedConsultarGastos.getSimboloGasto());
        beanGasto.setDMontoGeneral(beanSessionScopedConsultarGastos.getMontoGeneral());
        beanGasto.setFechaGastoMIN(beanSessionScopedConsultarGastos.getFechaGastoMin());
        beanGasto.setFechaGastoMAX(beanSessionScopedConsultarGastos.getFechaGastoMax());
        beanGasto.setC_detalle(beanSessionScopedConsultarGastos.getDetalle());
        beanGasto.setNidProtra(beanSessionScopedConsultarGastos.getNidProveedor());
        beanGasto.setCidFactura(beanSessionScopedConsultarGastos.getCidFactura());
        beanSessionScopedConsultarGastos.setListGasto(ln_C_SFGastosRemoto.getGastosByAttributes(beanGasto));
        if (tbGast != null) {
            Utils.unselectFilas(tbGast);
            tbGast.setValue(beanSessionScopedConsultarGastos.getListGasto());
            Utils.addTarget(tbGast);
        }
        return null;
    }

    public String LimpiarCampos(){
        resetearCamposConsulta();
        return null;
    }
   
    public void limpiar(ActionEvent actionEvent){
        clearLista();
    }

    public List<BeanGasto> clearLista() {
        resetearCamposConsulta();
        BeanGasto beanGasto = new BeanGasto();
        beanSessionScopedConsultarGastos.setListGasto(ln_C_SFGastosRemoto.getGastosByAttributes(beanGasto));
        if (tbGast != null) {
            Utils.unselectFilas(tbGast);
            tbGast.setValue(beanSessionScopedConsultarGastos.getListGasto());
            Utils.addTarget(tbGast);
        }
        return null;
        //return getBeanSessionScopedConsultarGastos().getListGasto();
    }
    
    public void resetearCamposConsulta() {
        beanSessionScopedConsultarGastos.setNidTipoGasto(null);
        beanSessionScopedConsultarGastos.setNidModalidadPago(null);
        beanSessionScopedConsultarGastos.setFechaGastoMin(null);
        beanSessionScopedConsultarGastos.setFechaGastoMax(null);
        beanSessionScopedConsultarGastos.setSimboloGasto(null);
        beanSessionScopedConsultarGastos.setMontoGeneral(null);
        beanSessionScopedConsultarGastos.setNidProveedor(null);
        beanSessionScopedConsultarGastos.setDetalle(null);
        beanSessionScopedConsultarGastos.setCidFactura(null);
        beanSessionScopedConsultarGastos.setListGasto(null);
        beanSessionScopedConsultarGastos.setNidFlotaCombustible(null);
        inputTextProveedor.resetValue();
        Utils.unselectFilas(tbGast);
        Utils.addTarget(inputTextProveedor);
    }

    public void abrirPopImg(ActionEvent ae) {
        RichPopup.PopupHints ph = new RichPopup.PopupHints();
        ph.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN,RichPopup.PopupHints.AlignTypes.ALIGN_AFTER_END);
        ph.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN_ID,otTitulo);
        Long idGasto = getBeanSessionScopedConsultarGastos().getBeanGasto().getNidGasto();
        //source="/imageservlet?cidguia=#{sessionScope.beanSessionRegistrarGuia.cidGuia}&#38;cidunin=#{sessionScope.beanSessionRegistrarGuia.codUN}"
        String rutaImg = "/gastoservlet?nidGasto="+idGasto;
        getBeanSessionScopedConsultarGastos().setRutaImagenServ(rutaImg);
        Utils.showPopUpMIDDLE(popImg);
    }
    
    public void seleccionarGasto(SelectionEvent selectionEvent) {
        RichTable t = (RichTable)selectionEvent.getSource();
        Object _selectedRowData = t.getSelectedRowData();
        BeanGasto gasto = (BeanGasto)_selectedRowData;
        getBeanSessionScopedConsultarGastos().setBeanGasto(gasto);
        beanSessionScopedConsultarGastos.setNidGasto(gasto.getNidGasto());
        btnAnular.setDisabled(false);
        btnImg.setDisabled(false);
        Utils.addTargetMany(btnAnular,btnImg);
    }
    
    public ArrayList llenarFlotaLubal() {
        ArrayList unItems = new ArrayList();
        List<ADFlota> roles = bdl_C_SFFlotaRemote.getFlotasPorEmpresa(5);
        for (ADFlota r : roles) {
            unItems.add(new SelectItem(r.getNidFlota().toString(),
                                       r.getCPlaca() + " | " + r.getCDescFlota().toString()));
        }
        return unItems;
    }
    
    public void setOtTitulo(RichOutputText ot1) {
        this.otTitulo = ot1;
    }

    public RichOutputText getOtTitulo() {
        return otTitulo;
    }

    public void setBeanSessionScopedConsultarGastos(SessionScopeBeanConsultarGastos beanSessionScopedConsultarGastos) {
        this.beanSessionScopedConsultarGastos = beanSessionScopedConsultarGastos;
    }

    public SessionScopeBeanConsultarGastos getBeanSessionScopedConsultarGastos() {
        return beanSessionScopedConsultarGastos;
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

    public void setPfl2(RichPanelFormLayout pfl2) {
        this.pfl2 = pfl2;
    }

    public RichPanelFormLayout getPfl2() {
        return pfl2;
    }

    public void setChoiceTipoGasto(RichSelectOneChoice soc1) {
        this.choiceTipoGasto = soc1;
    }

    public RichSelectOneChoice getChoiceTipoGasto() {
        return choiceTipoGasto;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public UISelectItems getSi1() {
        return si1;
    }

    public void setInputTextProveedor(RichInputText it1) {
        this.inputTextProveedor = it1;
    }

    public RichInputText getInputTextProveedor() {
        return inputTextProveedor;
    }

    public void setChoiceModoPago(RichSelectOneChoice soc2) {
        this.choiceModoPago = soc2;
    }

    public RichSelectOneChoice getChoiceModoPago() {
        return choiceModoPago;
    }

    public void setSi2(UISelectItems si2) {
        this.si2 = si2;
    }

    public UISelectItems getSi2() {
        return si2;
    }

    public void setInputTextDetalle(RichInputText it2) {
        this.inputTextDetalle = it2;
    }

    public RichInputText getInputTextDetalle() {
        return inputTextDetalle;
    }

    public void setInputTextFactura(RichInputText it3) {
        this.inputTextFactura = it3;
    }

    public RichInputText getInputTextFactura() {
        return inputTextFactura;
    }

    public void setInputDateFechaMinimaid1(RichInputDate id1) {
        this.inputDateFechaMinimaid1 = id1;
    }

    public RichInputDate getInputDateFechaMinimaid1() {
        return inputDateFechaMinimaid1;
    }

    public void setInputDateFechaMaxima(RichInputDate id2) {
        this.inputDateFechaMaxima = id2;
    }

    public RichInputDate getInputDateFechaMaxima() {
        return inputDateFechaMaxima;
    }

    public void setPgl2(RichPanelGridLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGridLayout getPgl2() {
        return pgl2;
    }

    public void setGr2(RichGridRow gr2) {
        this.gr2 = gr2;
    }

    public RichGridRow getGr2() {
        return gr2;
    }

    public void setGc3(RichGridCell gc3) {
        this.gc3 = gc3;
    }

    public RichGridCell getGc3() {
        return gc3;
    }

    public void setGc4(RichGridCell gc4) {
        this.gc4 = gc4;
    }

    public RichGridCell getGc4() {
        return gc4;
    }

    public void setChoiceSimboloMonto(RichSelectOneChoice soc3) {
        this.choiceSimboloMonto = soc3;
    }

    public RichSelectOneChoice getChoiceSimboloMonto() {
        return choiceSimboloMonto;
    }

    public void setSi3(UISelectItems si3) {
        this.si3 = si3;
    }

    public UISelectItems getSi3() {
        return si3;
    }

    public void setInputTextMonto(RichInputText it4) {
        this.inputTextMonto = it4;
    }

    public RichInputText getInputTextMonto() {
        return inputTextMonto;
    }

    public void setBtnBuscarProveedor(RichCommandButton cb1) {
        this.btnBuscarProveedor = cb1;
    }

    public RichCommandButton getBtnBuscarProveedor() {
        return btnBuscarProveedor;
    }

    public void setTbGast(RichTable t1) {
        this.tbGast = t1;
    }

    public RichTable getTbGast() {
        return tbGast;
    }

    public void setBtnBuscar(RichCommandButton cb2) {
        this.btnBuscar = cb2;
    }

    public RichCommandButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setPgl3(RichPanelGridLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGridLayout getPgl3() {
        return pgl3;
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

    public void setGc7(RichGridCell gc7) {
        this.gc7 = gc7;
    }

    public RichGridCell getGc7() {
        return gc7;
    }

    public void setGc8(RichGridCell gc8) {
        this.gc8 = gc8;
    }

    public RichGridCell getGc8() {
        return gc8;
    }

    public void setBtnLimpiar(RichCommandButton cb3) {
        this.btnLimpiar = cb3;
    }

    public RichCommandButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public void setBtnAnular(RichCommandButton cb4) {
        this.btnAnular = cb4;
    }

    public RichCommandButton getBtnAnular() {
        return btnAnular;
    }

    public void setBtnExportarExcel(RichCommandButton cb5) {
        this.btnExportarExcel = cb5;
    }

    public RichCommandButton getBtnExportarExcel() {
        return btnExportarExcel;
    }

    public void setPgl4(RichPanelGroupLayout pgl4) {
        this.pgl4 = pgl4;
    }

    public RichPanelGroupLayout getPgl4() {
        return pgl4;
    }

    public void setTipoGastos(List TipoGastos) {
        this.TipoGastos = TipoGastos;
    }

    public List getTipoGastos() {
        return TipoGastos;
    }

    public void setModalidadPago(List ModalidadPago) {
        this.ModalidadPago = ModalidadPago;
    }

    public List getModalidadPago() {
        return ModalidadPago;
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

    public void setListaProveedores(List<BeanEmpresa> listaProveedores) {
        this.listaProveedores = listaProveedores;
    }

    public List<BeanEmpresa> getListaProveedores() {
        return listaProveedores;
    }

    public void setPopupProveedor(RichPopup p1) {
        this.popupProveedor = p1;
    }

    public RichPopup getPopupProveedor() {
        return popupProveedor;
    }

    public void setD1(RichDialog d1) {
        this.d1 = d1;
    }

    public RichDialog getD1() {
        return d1;
    }

    public void setTbProv(RichTable t2) {
        this.tbProv = t2;
    }

    public RichTable getTbProv() {
        return tbProv;
    }

    public void setS1(RichSubform s1) {
        this.s1 = s1;
    }

    public RichSubform getS1() {
        return s1;
    }

    public void setPopImg(RichPopup p1) {
        this.popImg = p1;
    }

    public RichPopup getPopImg() {
        return popImg;
    }

    public void setD2(RichDialog d2) {
        this.d2 = d2;
    }

    public RichDialog getD2() {
        return d2;
    }

    public void setImgGasto(RichImage i1) {
        this.imgGasto = i1;
    }

    public RichImage getImgGasto() {
        return imgGasto;
    }

    public void setBtnImg(RichCommandButton cb1) {
        this.btnImg = cb1;
    }

    public RichCommandButton getBtnImg() {
        return btnImg;
    }

    public void setListaFlotasLubal(List ListaFlotasLubal) {
        this.ListaFlotasLubal = ListaFlotasLubal;
    }

    public List getListaFlotasLubal() {
        return ListaFlotasLubal;
    }

    public void setSoc1(RichSelectOneChoice soc1) {
        this.soc1 = soc1;
    }

    public RichSelectOneChoice getSoc1() {
        return soc1;
    }

    public void setSi7(UISelectItems si7) {
        this.si7 = si7;
    }

    public UISelectItems getSi7() {
        return si7;
    }
}
