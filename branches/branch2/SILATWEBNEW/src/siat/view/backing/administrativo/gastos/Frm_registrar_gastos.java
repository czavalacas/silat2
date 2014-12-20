package siat.view.backing.administrativo.gastos;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.awt.font.TextMeasurer;
import java.awt.image.BufferedImage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
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
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.RichSubform;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectItem;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichGridCell;
import oracle.adf.view.rich.component.rich.layout.RichGridRow;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGridLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;

import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.adfdt.model.meta.bean.BeanUtil;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import org.apache.myfaces.trinidad.model.UploadedFile;

import siat.view.backing.transporte.cliente.Frm_registrar_cliente;

import siat.view.backing.utiles.Utils;

import silat.servicios_negocio.BDLSF.IR.BDL_C_SFFlotaRemote;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFModalidadPago;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFTipoGastoRemoto;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFUtilsRemote;
import silat.servicios_negocio.Beans.BeanADRelacionEmpresa;
import silat.servicios_negocio.Beans.BeanChofer;
import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanGasto;
import silat.servicios_negocio.Beans.BeanManifiesto;
import silat.servicios_negocio.Beans.BeanTipoGasto;
import silat.servicios_negocio.Beans.BeanUnidadMedida;
import silat.servicios_negocio.Beans.BeanUsuarioAutenticado;
import silat.servicios_negocio.LNSF.IL.LN_T_SFManifiestoRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFFlotaRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFGastosRemoto;
import silat.servicios_negocio.LNSF.IR.LN_C_SFManifiestoRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFRelacionEmpresaRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFTipoGastoRemoto;
import silat.servicios_negocio.LNSF.IR.LN_C_SFUtilsRemote;
import silat.servicios_negocio.LNSF.IR.LN_T_SFGastosRemoto;
import silat.servicios_negocio.LNSF.IR.LN_T_SFTipoGastoRemote;
import silat.servicios_negocio.entidades.admin.ADFlota;
import silat.servicios_negocio.entidades.admin.ADModalidadPago;
import silat.servicios_negocio.entidades.admin.ADTipoGasto;
import silat.servicios_negocio.entidades.admin.ADUtil;

/**@author: czavalacas
 * @class: frm_registrar_gastos
 * @descr: clase de respaldo de la vista registrar_gastos*/

public class Frm_registrar_gastos {
    private RichPanelBox pb1;
    private RichPanelFormLayout pfl1;
    private RichSelectOneChoice choiceTipoGasto;
    private UISelectItems si1;
    private RichSelectOneChoice choiceModalidadPago;
    private UISelectItems si2;
    private RichInputText inputTextMonto;
    private RichInputDate inputFecha;
    private RichInputText inputDetalle;
    private RichPanelGridLayout pgl1;
    private RichGridRow gr1;
    private RichGridCell gc1;
    private RichGridCell gc2;
    private RichPanelBox pb2;
    private RichPanelBox pb3;
    private RichPanelFormLayout pfl2;
    private RichPanelFormLayout pfl3;
    private RichInputText inputProveedor;
    private RichInputText inputFactura;
    private RichInputText inputDestino;
    private RichSelectOneChoice choiceTipoServicio;
    private UISelectItems si3;
    private RichCommandButton btnBuscarProveedor;
    private RichSelectOneChoice choiceTipoCombustible;
    private UISelectItems si4;
    private RichSelectOneChoice choiceFlota;
    private UISelectItems si5;
    private RichInputText inputCantidadPersonas;
    private RichSelectOneChoice choiceTipoMantenimiento;
    private UISelectItems si7;
    private RichCommandButton btnRegistrarGastos;
    private RichInputText inputNumeroCheque;
    private RichInputFile inputImagenRecibo;
    private RichPopup popupProveedores;
    private RichDialog d1;
    private RichSubform s1;
    private RichTable tbProv;
    private RichImage imgGasto;
    private RichPopup popupImagen;
    private RichDialog dialogImagen;
    private RichTable t1;
    private RichCommandButton cb1;
    private RichPopup popupConfirmar;
    private RichDialog d2;
    private RichSelectOneChoice choiceBanco;
    private UISelectItems si8;
    private RichSelectItem si9;
    private RichSelectItem si10;
    private RichCommandButton btnTipGas;
    private RichPopup popTipG;
    private RichDialog diaTipG;
    private RichSubform sfTipGa;
    private RichInputText itTipGast;
    private RichSelectBooleanCheckbox sbcIsRuta;
    private RichCommandButton btnRegTipG;
    private RichCommandButton btnBuscTipG;
    private RichTable tbTipGa;
    /*Mis Variables*/
    private LN_C_SFTipoGastoRemoto ln_C_SFTipoGastoRemoto;
    private final static String LOOKUP_NAME_SFTIPOGASTO_REMOTO =
        "LUBAL_SIAT_APP-SILATNEGOCIO-LN_C_SFTipoGasto";//#silat.servicios_negocio.LNSF.IR.LN_C_SFTipoGastoRemoto
    private LN_T_SFTipoGastoRemote ln_T_SFTipoGastoRemote;
    private final static String LOOKUP_NAME_SF_T_TIPOGASTO_REMOTO =
        "mapLN_T_SFTipoGasto";//#silat.servicios_negocio.LNSF.IR.LN_T_SFTipoGastoRemote
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
    private final static String LOOKUP_NAME_SFGASTOSCONSULTA_REMOTO =
        "LUBAL_SIAT_APP-SILATNEGOCIO-LN_C_SFGastos";//#silat.servicios_negocio.LNSF.IR.LN_C_SFGastosRemoto
    private final static String LOOKUP_NAME_SFUTILS_REMOTO = "mapLN_C_SFUtils";//#silat.servicios_negocio.LNSF.IR.LN_C_SFUtilsRemote
    private LN_C_SFUtilsRemote ln_C_SFUtilsRemote;
    private final static String LOOKUP_NAME_SF_C_MANIFIESTO_REMOTO = "mapLN_C_SFManifiesto";
    private LN_C_SFManifiestoRemote ln_C_SFManifiestoRemote;
    private final static String LOOKUP_NAME_SF_T_MANIFIESTO_REMOTO = "mapLN_T_SFManifiesto";
    private LN_T_SFManifiestoRemote ln_T_SFManifiestoRemote;
    private List TipoGastos;
    private List ModalidadPago;
    private List TipoServicio;
    private List TipoCombustible;
    private List TipoMantenimiento;
    private List ListaFlotasLubal;
    private List listaManifiestos;
    private BeanEmpresa beanEmpresa = new BeanEmpresa();
    private BeanUsuarioAutenticado beanUsuario=new BeanUsuarioAutenticado();
    private List<BeanEmpresa> listaProveedores = new ArrayList<BeanEmpresa>();
    private SessionScopeBeanRegistrarGastos beanSessionScopedRegistrarGastos;
    FacesContext ctx = FacesContext.getCurrentInstance();
    
    private RichSelectOneChoice socEstTipG;
    private UISelectItems si6;
    private RichSelectOneChoice choiceManifiesto;
    private UISelectItems si11;
    

    public Frm_registrar_gastos() {
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
            ln_C_SFUtilsRemote = (LN_C_SFUtilsRemote)ctx.lookup(LOOKUP_NAME_SFUTILS_REMOTO);
            ln_T_SFTipoGastoRemote = (LN_T_SFTipoGastoRemote) ctx.lookup(LOOKUP_NAME_SF_T_TIPOGASTO_REMOTO);
            ln_C_SFManifiestoRemote = (LN_C_SFManifiestoRemote)ctx.lookup(LOOKUP_NAME_SF_C_MANIFIESTO_REMOTO);
            ln_T_SFManifiestoRemote = (LN_T_SFManifiestoRemote)ctx.lookup(LOOKUP_NAME_SF_T_MANIFIESTO_REMOTO);
            this.setModalidadPago(this.llenarModalidadPago());
            this.setTipoServicio(this.llenarTipoServicio());
            this.setListaManifiestos(this.llenarManifiestos());
            this.setTipoMantenimiento(this.llenarTipoMantenimiento());
            this.setTipoCombustible(this.llenarTipoCombustible());
            this.setListaFlotasLubal(this.llenarFlotaLubal());
            this.setListaProveedores(this.traerProveedores());
            this.setTipoGastos(this.llenarTipoGastoCombo());            

            beanUsuario = (BeanUsuarioAutenticado) Utils.getSession("USER");
            setTipoGastos(this.llenarTipoGastoCombo());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String setProveedores() {
        //beanSessionActualizarCliente.setBeanListFlota(ln_C_SFFlotaRemote.getFlotasPorEmpresa(beanSessionActualizarCliente.getNidParty().intValue()));
        Utils.showPopUpMIDDLE(popupProveedores);
        return null;
    }
    
    public void seleccionarProveedor(SelectionEvent selectionEvent) {
        RichTable t = (RichTable)selectionEvent.getSource();
        Object _selectedRowData = t.getSelectedRowData();
        beanEmpresa = (BeanEmpresa)_selectedRowData;
        beanSessionScopedRegistrarGastos.setBeanEmpresa(beanEmpresa);
        beanSessionScopedRegistrarGastos.setNidProveedor(beanEmpresa.getNidParty().longValue());
        inputProveedor.setValue(beanEmpresa.getCRazonSocial());
        Utils.addTarget(inputProveedor);
        popupProveedores.hide();
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
    
    public ArrayList llenarManifiestos() {
        ArrayList unItems = new ArrayList();
        List<BeanManifiesto> manifiestos = ln_C_SFManifiestoRemote.findManifiestoXPagar();
        for (BeanManifiesto m : manifiestos) {
            unItems.add(new SelectItem(m.getNidManifiesto(),
                                       "Manifiesto - "+m.getNidManifiesto()));
        }
        return unItems;
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

    public ArrayList llenarTipoGastoCombo() {
        ArrayList unItems = new ArrayList();
        List<BeanTipoGasto> tipoGastos = ln_C_SFTipoGastoRemoto.getTiposGastosAll_LN(null,"1");
        for (BeanTipoGasto r : tipoGastos) {
            unItems.add(new SelectItem(r,
                                       r.getDescripcionTipoGasto().toString()));
        }
        return unItems;
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

    public ArrayList llenarTipoServicio() {
        ArrayList unItems = new ArrayList();
        List<ADUtil> roles = bdl_C_SFUtilsRemote.getUtilPorTipoObjeto(new BigDecimal(1));
        for (ADUtil r : roles) {
            unItems.add(new SelectItem(r.getNidObj().toString(), r.getDescObj().toString()));
        }
        return unItems;
    }

    public ArrayList llenarTipoCombustible() {
        ArrayList unItems = new ArrayList();
        List<ADUtil> roles = bdl_C_SFUtilsRemote.getUtilPorTipoObjeto(new BigDecimal(3));
        for (ADUtil r : roles) {
            unItems.add(new SelectItem(r.getNidObj().toString(), r.getDescObj().toString()));
        }
        return unItems;
    }

    public ArrayList llenarTipoMantenimiento() {
        ArrayList unItems = new ArrayList();
        List<ADUtil> roles = bdl_C_SFUtilsRemote.getUtilPorTipoObjeto(new BigDecimal(2));
        for (ADUtil r : roles) {
            unItems.add(new SelectItem(r.getNidObj().toString(), r.getDescObj().toString()));
        }
        return unItems;
    }

    public void mostrarIsRuta(String isRuta){
        boolean estado = true;
        if(isRuta.equalsIgnoreCase("S")){
            estado = true;
        }else{
            estado = false;
        }
        beanSessionScopedRegistrarGastos.setEstadoInFlotaV(estado);
        beanSessionScopedRegistrarGastos.setEstadoInFlotaR(estado);
        choiceFlota.setRequired(estado);
        choiceFlota.setVisible(estado);
        Utils.addTargetMany(choiceFlota);
    }
    
    public void camposTodosAux(boolean campServTrans, boolean campComb, boolean campBiat, boolean campMante,boolean campServ, boolean campPers,boolean campMani,String isRuta){
        resetearCampos();
        inputTextMonto.setDisabled(false);
        camposServicioTranporte(campServTrans);
        camposCombustible(campComb);
        camposBiaticos(campBiat);
        camposMantenimientoFlota(campMante);
        camposServicioBasico(campServ);
        camposPersonal(campPers);
        camposManifiesto(campMani);
        mostrarIsRuta(isRuta);
    }
    
    public void camposManifiesto(boolean estado){
        beanSessionScopedRegistrarGastos.setEstadoInManifiesto(estado);
        Utils.addTarget(choiceManifiesto);
        if(estado){
            inputTextMonto.setDisabled(true);
            Utils.addTarget(inputTextMonto);    
        }
    }
    
    public void camposServicioTranporte(boolean estado) {
        inputProveedor.setVisible(estado);
        inputProveedor.setRequired(estado);
        btnBuscarProveedor.setVisible(estado);
        inputFactura.setVisible(estado);
        inputFactura.setRequired(estado);
        Utils.addTargetMany(inputProveedor,btnBuscarProveedor,inputFactura);
        beanSessionScopedRegistrarGastos.setEstadoInProveedorV(estado);
        beanSessionScopedRegistrarGastos.setEstadoInProveedorR(estado);
        beanSessionScopedRegistrarGastos.setEstadoBtnProveedorV(estado);        
        beanSessionScopedRegistrarGastos.setEstadoinFacturaV(estado);
        beanSessionScopedRegistrarGastos.setEstadoinFacturaR(estado);
    }

    public void camposBiaticos(boolean estado) {
        beanSessionScopedRegistrarGastos.setEstadoInDestinoV(estado);
        beanSessionScopedRegistrarGastos.setEstadoInDestinoR(estado);
        inputDestino.setVisible(estado);
        inputDestino.setRequired(estado);
        Utils.addTarget(inputDestino);
    }

    public void camposServicioBasico(boolean estado) {
        beanSessionScopedRegistrarGastos.setEstadoInTipoServicioV(estado);
        beanSessionScopedRegistrarGastos.setEstadoInTipoServicioR(estado);
        choiceTipoServicio.setRequired(estado);
        choiceTipoServicio.setVisible(estado);
        Utils.addTarget(choiceTipoServicio);
    }

    public void camposCombustible(boolean estado) {
        beanSessionScopedRegistrarGastos.setEstadoInTipoCombustibleV(estado);
        beanSessionScopedRegistrarGastos.setEstadoInTipoCombustibleR(estado);
        choiceTipoCombustible.setRequired(estado);
        choiceTipoCombustible.setVisible(estado);
        Utils.addTargetMany(choiceFlota,choiceTipoCombustible);
    }

    public void camposPersonal(boolean estado) {
        beanSessionScopedRegistrarGastos.setEstadoinCantidadPersonasV(estado);
        beanSessionScopedRegistrarGastos.setEstadoinCantidadPersonasR(estado);
        inputCantidadPersonas.setVisible(estado);
        inputCantidadPersonas.setRequired(estado);
        Utils.addTarget(inputCantidadPersonas);
    }

    public void camposMantenimientoFlota(boolean estado) {
        beanSessionScopedRegistrarGastos.setEstadoInTipoMantenimientoV(estado);
        beanSessionScopedRegistrarGastos.setEstadoInTipoMantenimientoR(estado);
        choiceTipoMantenimiento.setVisible(estado);
        choiceTipoMantenimiento.setRequired(estado);
        Utils.addTargetMany(/*choiceFlotasMantenimiento,*/choiceTipoMantenimiento);
    }

    public void resetearCampos() {
        inputTextMonto.resetValue();
        beanSessionScopedRegistrarGastos.setNidManifiesto(null);
        inputProveedor.resetValue();
        inputFactura.resetValue();
        choiceManifiesto.resetValue();
        beanSessionScopedRegistrarGastos.setBeanEmpresa(null);
        beanSessionScopedRegistrarGastos.setNidProveedor(null);
        beanSessionScopedRegistrarGastos.setCidFactura(null);
        choiceTipoServicio.resetValue();
        beanSessionScopedRegistrarGastos.setNidTipoServicio(null);
        beanSessionScopedRegistrarGastos.setBanco(null);
        inputDestino.resetValue();
        inputNumeroCheque.resetValue();
        choiceFlota.resetValue();
        choiceTipoCombustible.resetValue();
        beanSessionScopedRegistrarGastos.setNidFlota(null);
        beanSessionScopedRegistrarGastos.setNidTipoCombustible(null);
        inputCantidadPersonas.resetValue();
        beanSessionScopedRegistrarGastos.setNidFlotaMantenimiento(null);
        choiceTipoMantenimiento.resetValue();
        beanSessionScopedRegistrarGastos.setNidTipoMantenimiento(null);
        Utils.addTargetMany(inputProveedor,inputFactura,choiceTipoServicio,inputNumeroCheque,inputDestino,choiceFlota,
                            choiceTipoCombustible,inputCantidadPersonas/*,choiceFlotasMantenimiento,*/,choiceTipoMantenimiento,
                            choiceManifiesto,inputTextMonto);
    }

    public void mostrarInputText(ValueChangeEvent vcl) {
        vcl.getComponent().processUpdates(FacesContext.getCurrentInstance());
        BeanTipoGasto beanTipGasto = (BeanTipoGasto) vcl.getNewValue();
        String isRuta = beanTipGasto.getIsRuta();
        int num = beanTipGasto.getNidTiga();
        switch(num){
            case 1: camposTodosAux(true, false, false, false, false,false,false,isRuta);break;
            case 2: camposTodosAux(false, false,true, false, false,false,false,isRuta);break;
            case 3: camposTodosAux(true, false, false, false,true,false,false,isRuta);break;
            case 4: camposTodosAux(false, true, false, false, false,false,false,isRuta);break;
            case 5: camposTodosAux(false, false, false, false, false,true,false,isRuta);break;
            case 8: camposTodosAux(false, false, false,true, false,false,false,isRuta);break;
            case 76: camposTodosAux(false, false, false,false, false,false,true,isRuta);break;
            default : camposTodosAux(false, false, false, false, false,false,false,isRuta);break;
        }
    }

    public void campoCheque(boolean estado) {
        beanSessionScopedRegistrarGastos.setEstadoInNumChequeV(estado);
        beanSessionScopedRegistrarGastos.setEstadoInNumChequeR(estado);
        inputNumeroCheque.setVisible(estado);
        inputNumeroCheque.setRequired(estado);
        beanSessionScopedRegistrarGastos.setEstadoInBancoV(estado);
        beanSessionScopedRegistrarGastos.setEstadoInBancoR(estado);
        choiceBanco.setVisible(estado);
        choiceBanco.setRequired(estado);
        Utils.addTargetMany(inputNumeroCheque,choiceBanco);
    }

    public void campoImagen(boolean estado) {
        beanSessionScopedRegistrarGastos.setEstadoBtnSubirImagenV(estado);
        cb1.setVisible(estado);
        Utils.addTarget(cb1);
    }

    public void resetearCampoEvidencia() {
        inputNumeroCheque.resetValue();
        Utils.addTarget(inputNumeroCheque);
    }

    public void eventoModalidadPagoAux(boolean cheque, boolean campoImg){
        resetearCampoEvidencia();
        campoCheque(cheque);
        campoImagen(campoImg);
    }
    
    public void eventoModalidadPago(ValueChangeEvent vcl) {
        vcl.getComponent().processUpdates(FacesContext.getCurrentInstance());
        String newValue = vcl.getNewValue().toString();
        int num = Integer.parseInt(newValue);
        switch(num){
            case 1 : eventoModalidadPagoAux(true,false);break;
            case 2 : eventoModalidadPagoAux(false,true);break;
            case 3 : eventoModalidadPagoAux(false,true);break;
            case 4 : eventoModalidadPagoAux(false,true);break;
        }
        beanSessionScopedRegistrarGastos.setNomBanco("");
        System.out.println("Valor de Monto: "+inputTextMonto.getValue());
    }

    public void eventoSeleccionaFlotaCombustible(ValueChangeEvent vcl) {
        try {
            vcl.getComponent().processUpdates(FacesContext.getCurrentInstance());
            beanSessionScopedRegistrarGastos.setNidFlota(Integer.parseInt(vcl.getNewValue().toString()));
        } catch (Exception nfe) {
            beanSessionScopedRegistrarGastos.setNidFlota(null);
            nfe.printStackTrace();
        }
    }
    
    public void eventoTipoManifiesto(ValueChangeEvent vcl) {
        vcl.getComponent().processUpdates(FacesContext.getCurrentInstance());
        if(vcl.getNewValue() != null){
            String newValue = vcl.getNewValue().toString();
            beanSessionScopedRegistrarGastos.setManifiesto(ln_C_SFManifiestoRemote.findManifiestosByAttr_LN(null, null, Integer.parseInt(newValue), null, null, null).get(0));
            beanSessionScopedRegistrarGastos.getManifiesto().setEstadoManifiestoNegocio(4+"");
            inputTextMonto.setValue(beanSessionScopedRegistrarGastos.getManifiesto().getNFletePactado());
            beanSessionScopedRegistrarGastos.setMontoGeneral(new BigDecimal(beanSessionScopedRegistrarGastos.getManifiesto().getNFletePactado()));
            inputDetalle.setValue("Pago de Manifiesto "+newValue);
            Utils.addTargetMany(inputTextMonto,inputDetalle);
        }
        else{
            beanSessionScopedRegistrarGastos.setManifiesto(null);
            inputTextMonto.setValue("");
            inputDetalle.setValue("");
            Utils.addTargetMany(inputTextMonto,inputDetalle);
        }
    }

    public void eventoSeleccionaFlotaMantenimiento(ValueChangeEvent vcl) {
        vcl.getComponent().processUpdates(FacesContext.getCurrentInstance());
        String newValue = vcl.getNewValue().toString();
    }
    
    public void eventoTipoBanco(ValueChangeEvent vcl) {
        vcl.getComponent().processUpdates(FacesContext.getCurrentInstance());
        String newValue = vcl.getNewValue().toString();
        beanSessionScopedRegistrarGastos.setNomBanco(newValue);
    }

    public void eventoTipoServicio(ValueChangeEvent vcl) {
        vcl.getComponent().processUpdates(FacesContext.getCurrentInstance());
        String newValue = vcl.getNewValue().toString();
    }

    public void eventoTipoCombustible(ValueChangeEvent vcl) {
        vcl.getComponent().processUpdates(FacesContext.getCurrentInstance());
        String newValue = vcl.getNewValue().toString();
    }

    public void eventoTipoMantenimiento(ValueChangeEvent vcl) {
        vcl.getComponent().processUpdates(FacesContext.getCurrentInstance());
        String newValue = vcl.getNewValue().toString();
    }

    public void abrirPopSubirImg(ActionEvent actionEvent) {
        Utils.showPopUpMIDDLE(popupImagen);
    }

    public String registrarGasto() {
        try {
            String estado = "1";
            int nidTipoGasto = beanSessionScopedRegistrarGastos.getBeanTipoGasto().getNidTiga();
            int nidModoPago = Integer.parseInt(beanSessionScopedRegistrarGastos.getNidModalidadPago());
            BigDecimal monto = beanSessionScopedRegistrarGastos.getMontoGeneral();
            //BigDecimal monto = new BigDecimal(1);
            Date fechaGasto = beanSessionScopedRegistrarGastos.getFechaGasto();
            String destino = null;
            if (inputDestino.getValue() != null) {
                destino = (String) inputDestino.getValue();
            }
            String numCheque = null;
            if (beanSessionScopedRegistrarGastos.getCheque() != null) {
                numCheque = (String) inputNumeroCheque.getValue();
            }
            String detalle = null;
            if (inputDetalle.getValue() != null) {
                detalle = (String) inputDetalle.getValue();
            }
            Integer nidFlota = null;
            if (beanSessionScopedRegistrarGastos.getNidFlota() != null) {
                nidFlota = beanSessionScopedRegistrarGastos.getNidFlota();
            }
            if (beanSessionScopedRegistrarGastos.getManifiesto() != null){
                
            }
            
            /*
             * Codigo agregado para cambiar el estado de u manifiesto a pagado
             */
            
            if(beanSessionScopedRegistrarGastos.getManifiesto() != null){
                int nidMan = beanSessionScopedRegistrarGastos.getManifiesto().getNidManifiesto();
                System.out.println("Valor de nidManifiesto "+ nidMan);
                ln_T_SFManifiestoRemote.cambiarEstadoManifiesto(nidMan, 4+"");
            }
            
            BeanGasto bGasto = ln_T_SFGastosRemoto.insertarGasto(nidTipoGasto, nidModoPago, monto, fechaGasto, nidFlota,
                                                                  beanSessionScopedRegistrarGastos.getNidProveedor(),
                                                                  beanSessionScopedRegistrarGastos.getCidFactura(), destino,
                                                                  beanSessionScopedRegistrarGastos.getNidTipoServicio(),
                                                                  beanSessionScopedRegistrarGastos.getNidTipoCombustible(),
                                                                  beanSessionScopedRegistrarGastos.getCantidadPersonas(),
                                                                  beanSessionScopedRegistrarGastos.getNidTipoMantenimiento(),
                                                                  numCheque, beanSessionScopedRegistrarGastos.getRutaImagen(),
                                                                  beanSessionScopedRegistrarGastos.getBlobImagenRecibo(), detalle,
                                                                  beanSessionScopedRegistrarGastos.getNomBanco(),estado);

            
            resetearCamposBasico();
            resetearCampos();
            if (bGasto.getBeanError() != null) {
                BeanError error = bGasto.getBeanError();
                int severidad = 0;
                if (error.getCidError().equals("000") ) {
                    severidad = 3;
                    Utils.depurar("GRABO Gasto");
                } else {
                    severidad = 1;
                }
                if(Utils.hasPermiso(beanUsuario.getLstPermisos(),new BigDecimal("19"))){
                    Utils._redireccionar(ctx, "WEB-INF/consultar_gastos.xml#consultar_gastos");
                }else{
                    Utils._redireccionar(ctx, "WEB-INF/mainTF.xml#mainTF");
                }
                Utils.throwError_Aux(ctx, error.getCDescripcionError(), severidad);
            } else {
                Utils.throwError_Aux(ctx, "Error Inesperado", 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Utils.throwError_Aux(ctx, "Error Inesperado", 1);
        }
        return null;
    }

    public void resetearCamposBasico() {
        choiceTipoGasto.resetValue();
        choiceModalidadPago.resetValue();
        inputFecha.resetValue();
        inputDetalle.resetValue();
        inputTextMonto.resetValue();
        Utils.addTargetMany(choiceTipoGasto,choiceModalidadPago,inputFecha,inputDetalle,inputTextMonto);
        beanSessionScopedRegistrarGastos.setNidTipoGasto(null);
        beanSessionScopedRegistrarGastos.setNidModalidadPago(null);
        beanSessionScopedRegistrarGastos.setFechaGasto(null);
    }

    public void uploadFileValueChangeEvent(ValueChangeEvent valueChangeEvent) {
        try{
            UploadedFile file = (UploadedFile) valueChangeEvent.getNewValue();
            InputStream inputStream1 = file.getInputStream();
            long fileSize = file.getLength() / (1024 * 1024);//megabytes
            if(file.getLength() > 1602864){
                Utils.throwError_Aux(ctx,"El archivo no puede ser de mas de 1.5 MB.",4);
                return;
            }
            if(Utils.validarExtensionImg(file.getFilename())){
                String ext = file.getFilename().substring(file.getFilename().lastIndexOf(".") + 1, file.getFilename().length());
                ServletContext servletCtx = (ServletContext)ctx.getExternalContext().getContext();
                String imageDirPath = servletCtx.getRealPath("/");

                InputStream inputStream = file.getInputStream();
                BufferedImage input = ImageIO.read(inputStream);
                
                byte [] bytes = getBytes(inputStream1);
                beanSessionScopedRegistrarGastos.setBlobImagenRecibo(bytes);
                
                String cidGuiaFull = beanSessionScopedRegistrarGastos.getNidTipoGasto()+"-"+beanSessionScopedRegistrarGastos.getNidModalidadPago();
                String timePath = GregorianCalendar.getInstance().getTimeInMillis()+"";
                String rutaLocal = "";
                if(File.separator.equals("/")){
                    rutaLocal = File.separator+"recursos" + File.separator + "img" + File.separator + "gastos" + File.separator + cidGuiaFull + timePath + "." + ext;   
                }else{
                    rutaLocal = "recursos" + File.separator + "img" + File.separator + "gastos" + File.separator + cidGuiaFull + timePath + "." + ext;
                }
                String rutaImg = imageDirPath + rutaLocal;
                File outputFile = new File(rutaImg);
                beanSessionScopedRegistrarGastos.setRutaImagenAux(rutaImg);
                imgGasto.setSource(rutaLocal);//le seteo el source al componente img para q muestra
                Utils.addTarget(imgGasto);//actualizo para q se refleje en pantalla
                ImageIO.write(input,ext,outputFile);
            }else{
                Utils.throwError_Aux(ctx,"El archivo no es de tipo imagen suba un jpg/png",4);
                inputImagenRecibo.resetValue();
                Utils.addTarget(inputImagenRecibo);
            }
        }catch(Exception e){
            Utils.throwError_Aux(ctx,"Hubo un error a subir la imagen ingrese nuevamente",4);
        }
    }
    
    public byte[] getBytes(InputStream is) throws IOException {
      int len;
      int size = 1024;
      byte[] buf;

      if (is instanceof ByteArrayInputStream) {
        size = is.available();
        buf = new byte[size];
        len = is.read(buf, 0, size);
      } else {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        buf = new byte[size];
        while ((len = is.read(buf, 0, size)) != -1)
          bos.write(buf, 0, len);
        buf = bos.toByteArray();
      }
      return buf;
    }
    
    public void onDialogImagenCancel(ClientEvent ce) {
        beanSessionScopedRegistrarGastos.setRutaImagen(null);
        beanSessionScopedRegistrarGastos.setRutaImagenAux(null);
        imgGasto.setSource(beanSessionScopedRegistrarGastos.getRutaImagen());
        Utils.addTarget(imgGasto);
        ctx.renderResponse();
        popupImagen.hide();
    }

    public void dialogImgok(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
            if(beanSessionScopedRegistrarGastos.getRutaImagenAux() != null){
                beanSessionScopedRegistrarGastos.setRutaImagen(beanSessionScopedRegistrarGastos.getRutaImagenAux());
            }
        }
    }
        
    public void abrirRegTipGasto(ActionEvent actionEvent) {
        Utils.unselectFilas(tbTipGa);
        beanSessionScopedRegistrarGastos.setNombBtnTipGasto("Registrar Tipo Gasto");
        beanSessionScopedRegistrarGastos.setIsRegNuevTipGast(true);
        if(itTipGast != null){
            itTipGast.resetValue();
            sbcIsRuta.resetValue();  
        }
        beanSessionScopedRegistrarGastos.setSelecChkBox(false);
        beanSessionScopedRegistrarGastos.setTipGasto(null);
        beanSessionScopedRegistrarGastos.setEstTipGasto(null);
        beanSessionScopedRegistrarGastos.setLstEstadosTipGasto(Utils.llenarCombos(ln_C_SFUtilsRemote.getListADMCONS("ADMTIGA","C_ESTREG")));
        beanSessionScopedRegistrarGastos.setTipoGastos(this.ln_C_SFTipoGastoRemoto.getTiposGastosAll_LN(null,"1"));
        Utils.showPopUpMIDDLE(popTipG);
    }
    
    public void selectTipoGasto(SelectionEvent se) {
        try{
            RichTable t = (RichTable)se.getSource();
            BeanTipoGasto bTipGasto = (BeanTipoGasto) t.getSelectedRowData();
            int nidTipGasto = bTipGasto.getNidTiga();
            beanSessionScopedRegistrarGastos.setNidTipGasto(nidTipGasto);
            beanSessionScopedRegistrarGastos.setTipGasto(bTipGasto.getDescripcionTipoGasto());
            beanSessionScopedRegistrarGastos.setEstTipGasto(bTipGasto.getEstadoRegistro());
            String isRuta = bTipGasto.getIsRuta();
            boolean selecChkBox = (isRuta.equals("S") ? true : false);
            beanSessionScopedRegistrarGastos.setSelecChkBox(selecChkBox);
            beanSessionScopedRegistrarGastos.setIsRegNuevTipGast(false);
            beanSessionScopedRegistrarGastos.setNombBtnTipGasto("Modificar Tipo Gasto");
            Utils.addTargetMany(itTipGast,sbcIsRuta,btnRegTipG,socEstTipG);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void registrarTipoGasto(ActionEvent actionEvent) {
        try{
            if(beanSessionScopedRegistrarGastos.getTipGasto() == null){
                Utils.throwError_Aux(ctx,"Escriba una descripcion de Tipo de Gasto",4);
                return;
            }
            if(beanSessionScopedRegistrarGastos.getEstTipGasto() == null){
                Utils.throwError_Aux(ctx,"Seleccione un Estado de Tipo de Gasto",4);
                return;
            }
            String estado = beanSessionScopedRegistrarGastos.getEstTipGasto();
            int nidTipGasto = 0;
            int tipEvento = 0;
            if(beanSessionScopedRegistrarGastos.isIsRegNuevTipGast()){
                tipEvento = 1;
            }else{
                tipEvento = 0;
                nidTipGasto = beanSessionScopedRegistrarGastos.getNidTipGasto();
            }
            String descTipGasto = beanSessionScopedRegistrarGastos.getTipGasto();
            boolean selecCB = beanSessionScopedRegistrarGastos.isSelecChkBox();
            String isRuta = (selecCB == true) ? "S" : "N";
            BeanTipoGasto bTGasto = ln_T_SFTipoGastoRemote.registrarBeanTipoGasto_LN(tipEvento,
                                                                                     descTipGasto, 
                                                                                     isRuta, 
                                                                                     estado, 
                                                                                     nidTipGasto);
            if (bTGasto.getBeanError() != null) {
                BeanError error = bTGasto.getBeanError();
                int severidad = 0;
                if (error.getCidError().equals("000")) {
                    severidad = 3;
                    Utils.depurar("Grabo/modifico el tipo de gastos");
                    beanSessionScopedRegistrarGastos.setTipGasto(null);
                    beanSessionScopedRegistrarGastos.setIsRegNuevTipGast(true);
                    beanSessionScopedRegistrarGastos.setNombBtnTipGasto("Registrar Tipo Gasto");
                    this.setTipoGastos(this.llenarTipoGastoCombo());
                    choiceTipoGasto.resetValue();
                    beanSessionScopedRegistrarGastos.setBeanTipoGasto(null);
                    itTipGast.resetValue();
                    camposTodosAux(false, false, false, false, false,false,false,"N");
                    sbcIsRuta.resetValue();
                    beanSessionScopedRegistrarGastos.setSelecChkBox(false);
                    beanSessionScopedRegistrarGastos.setEstTipGasto("1");
                    Utils.addTargetMany(btnRegTipG,itTipGast,sbcIsRuta,choiceTipoGasto);
                    buscarTipGastos();
                } else {
                    severidad = 1;
                }
                Utils.throwError_Aux(ctx, error.getCDescripcionError(), severidad);
            } else {
                Utils.throwError_Aux(ctx, "Error Inesperado", 1);
            }
            
        }catch(Exception e){
            e.printStackTrace();
            Utils.throwError_Aux(ctx,"No se pudo registrar/Modificar el Tipo Gasto",4);
        }
    }
    
    public String buscarTipGastos(){
        String descTipGasto = beanSessionScopedRegistrarGastos.getTipGasto();
        String estado = beanSessionScopedRegistrarGastos.getEstTipGasto();
        beanSessionScopedRegistrarGastos.setTipoGastos(this.ln_C_SFTipoGastoRemoto.getTiposGastosAll_LN(descTipGasto,estado));
        Utils.unselectFilas(tbTipGa);
        if(tbTipGa != null){
            tbTipGa.setValue(beanSessionScopedRegistrarGastos.getTipoGastos());
            Utils.addTarget(tbTipGa);
        }
        return null;
    }
    
    public void setBeanSessionScopedRegistrarGastos(SessionScopeBeanRegistrarGastos beanSessionScopedRegistrarGastos) {
        this.beanSessionScopedRegistrarGastos = beanSessionScopedRegistrarGastos;
    }

    public SessionScopeBeanRegistrarGastos getBeanSessionScopedRegistrarGastos() {
        return beanSessionScopedRegistrarGastos;
    }

    public void setPb1(RichPanelBox pb1) {
        this.pb1 = pb1;
    }

    public RichPanelBox getPb1() {
        return pb1;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
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

    public void setChoiceModalidadPago(RichSelectOneChoice soc2) {
        this.choiceModalidadPago = soc2;
    }

    public RichSelectOneChoice getChoiceModalidadPago() {
        return choiceModalidadPago;
    }

    public void setSi2(UISelectItems si2) {
        this.si2 = si2;
    }

    public UISelectItems getSi2() {
        return si2;
    }

    public void setInputTextMonto(RichInputText it1) {
        this.inputTextMonto = it1;
    }

    public RichInputText getInputTextMonto() {
        return inputTextMonto;
    }

    public void setInputFecha(RichInputDate id1) {
        this.inputFecha = id1;
    }

    public RichInputDate getInputFecha() {
        return inputFecha;
    }

    public void setInputDetalle(RichInputText it2) {
        this.inputDetalle = it2;
    }

    public RichInputText getInputDetalle() {
        return inputDetalle;
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

    public void setPb2(RichPanelBox pb2) {
        this.pb2 = pb2;
    }

    public RichPanelBox getPb2() {
        return pb2;
    }

    public void setPb3(RichPanelBox pb3) {
        this.pb3 = pb3;
    }

    public RichPanelBox getPb3() {
        return pb3;
    }

    public void setPfl2(RichPanelFormLayout pfl2) {
        this.pfl2 = pfl2;
    }

    public RichPanelFormLayout getPfl2() {
        return pfl2;
    }

    public void setPfl3(RichPanelFormLayout pfl3) {
        this.pfl3 = pfl3;
    }

    public RichPanelFormLayout getPfl3() {
        return pfl3;
    }

    public void setInputProveedor(RichInputText it3) {
        this.inputProveedor = it3;
    }

    public RichInputText getInputProveedor() {
        return inputProveedor;
    }

    public void setInputFactura(RichInputText it4) {
        this.inputFactura = it4;
    }

    public RichInputText getInputFactura() {
        return inputFactura;
    }

    public void setInputDestino(RichInputText it5) {
        this.inputDestino = it5;
    }

    public RichInputText getInputDestino() {
        return inputDestino;
    }

    public void setChoiceTipoServicio(RichSelectOneChoice soc3) {
        this.choiceTipoServicio = soc3;
    }

    public RichSelectOneChoice getChoiceTipoServicio() {
        return choiceTipoServicio;
    }

    public void setSi3(UISelectItems si3) {
        this.si3 = si3;
    }

    public UISelectItems getSi3() {
        return si3;
    }

    public void setBtnBuscarProveedor(RichCommandButton cb1) {
        this.btnBuscarProveedor = cb1;
    }

    public RichCommandButton getBtnBuscarProveedor() {
        return btnBuscarProveedor;
    }

    public void setChoiceTipoCombustible(RichSelectOneChoice soc4) {
        this.choiceTipoCombustible = soc4;
    }

    public RichSelectOneChoice getChoiceTipoCombustible() {
        return choiceTipoCombustible;
    }

    public void setSi4(UISelectItems si4) {
        this.si4 = si4;
    }

    public UISelectItems getSi4() {
        return si4;
    }

    public void setChoiceFlota(RichSelectOneChoice soc5) {
        this.choiceFlota = soc5;
    }

    public RichSelectOneChoice getChoiceFlota() {
        return choiceFlota;
    }

    public void setSi5(UISelectItems si5) {
        this.si5 = si5;
    }

    public UISelectItems getSi5() {
        return si5;
    }

    public void setInputCantidadPersonas(RichInputText it6) {
        this.inputCantidadPersonas = it6;
    }

    public RichInputText getInputCantidadPersonas() {
        return inputCantidadPersonas;
    }


    public void setChoiceTipoMantenimiento(RichSelectOneChoice soc7) {
        this.choiceTipoMantenimiento = soc7;
    }

    public RichSelectOneChoice getChoiceTipoMantenimiento() {
        return choiceTipoMantenimiento;
    }

    public void setSi7(UISelectItems si7) {
        this.si7 = si7;
    }

    public UISelectItems getSi7() {
        return si7;
    }

    public void setBtnRegistrarGastos(RichCommandButton cb2) {
        this.btnRegistrarGastos = cb2;
    }

    public RichCommandButton getBtnRegistrarGastos() {
        return btnRegistrarGastos;
    }

    public void setInputNumeroCheque(RichInputText it7) {
        this.inputNumeroCheque = it7;
    }

    public RichInputText getInputNumeroCheque() {
        return inputNumeroCheque;
    }

    public void setInputImagenRecibo(RichInputFile if1) {
        this.inputImagenRecibo = if1;
    }

    public RichInputFile getInputImagenRecibo() {
        return inputImagenRecibo;
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

    public void setTipoServicio(List TipoServicio) {
        this.TipoServicio = TipoServicio;
    }

    public List getTipoServicio() {
        return TipoServicio;
    }

    public void setTipoCombustible(List TipoCombustible) {
        this.TipoCombustible = TipoCombustible;
    }

    public List getTipoCombustible() {
        return TipoCombustible;
    }

    public void setTipoMantenimiento(List TipoMantenimiento) {
        this.TipoMantenimiento = TipoMantenimiento;
    }

    public List getTipoMantenimiento() {
        return TipoMantenimiento;
    }

    public void setListaFlotasLubal(List ListaFlotasLubal) {
        this.ListaFlotasLubal = ListaFlotasLubal;
    }

    public List getListaFlotasLubal() {
        return ListaFlotasLubal;
    }

    public void setPopupProveedores(RichPopup p1) {
        this.popupProveedores = p1;
    }

    public RichPopup getPopupProveedores() {
        return popupProveedores;
    }

    public void setD1(RichDialog d1) {
        this.d1 = d1;
    }

    public RichDialog getD1() {
        return d1;
    }

    public void setS1(RichSubform s1) {
        this.s1 = s1;
    }

    public RichSubform getS1() {
        return s1;
    }

    public void setTbProv(RichTable t1) {
        this.tbProv = t1;
    }

    public RichTable getTbProv() {
        return tbProv;
    }

    public void setListaProveedores(List<BeanEmpresa> listaProveedores) {
        this.listaProveedores = listaProveedores;
    }

    public List<BeanEmpresa> getListaProveedores() {
        return listaProveedores;
    }

    public void setImgGasto(RichImage i1) {
        this.imgGasto = i1;
    }

    public RichImage getImgGasto() {
        return imgGasto;
    }

    public void setPopupImagen(RichPopup p1) {
        this.popupImagen = p1;
    }

    public RichPopup getPopupImagen() {
        return popupImagen;
    }

    public void setDialogImagen(RichDialog d2) {
        this.dialogImagen = d2;
    }

    public RichDialog getDialogImagen() {
        return dialogImagen;
    }

    public void setT1(RichTable t1) {
        this.t1 = t1;
    }

    public RichTable getT1() {
        return t1;
    }

    public void setCb1(RichCommandButton cb1) {
        this.cb1 = cb1;
    }

    public RichCommandButton getCb1() {
        return cb1;
    }

    public void setPopupConfirmar(RichPopup p1) {
        this.popupConfirmar = p1;
    }

    public RichPopup getPopupConfirmar() {
        return popupConfirmar;
    }

    public void setD2(RichDialog d2) {
        this.d2 = d2;
    }

    public RichDialog getD2() {
        return d2;
    }


    public void setChoiceBanco(RichSelectOneChoice soc1) {
        this.choiceBanco = soc1;
    }

    public RichSelectOneChoice getChoiceBanco() {
        return choiceBanco;
    }

    public void setSi8(UISelectItems si8) {
        this.si8 = si8;
    }

    public UISelectItems getSi8() {
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

    public void setBtnTipGas(RichCommandButton cb2) {
        this.btnTipGas = cb2;
    }

    public RichCommandButton getBtnTipGas() {
        return btnTipGas;
    }

    public void setPopTipG(RichPopup p1) {
        this.popTipG = p1;
    }

    public RichPopup getPopTipG() {
        return popTipG;
    }

    public void setDiaTipG(RichDialog d3) {
        this.diaTipG = d3;
    }

    public RichDialog getDiaTipG() {
        return diaTipG;
    }

    public void setSfTipGa(RichSubform s2) {
        this.sfTipGa = s2;
    }

    public RichSubform getSfTipGa() {
        return sfTipGa;
    }

    public void setItTipGast(RichInputText it1) {
        this.itTipGast = it1;
    }

    public RichInputText getItTipGast() {
        return itTipGast;
    }

    public void setSbcIsRuta(RichSelectBooleanCheckbox sbc1) {
        this.sbcIsRuta = sbc1;
    }

    public RichSelectBooleanCheckbox getSbcIsRuta() {
        return sbcIsRuta;
    }

    public void setBtnRegTipG(RichCommandButton cb2) {
        this.btnRegTipG = cb2;
    }

    public RichCommandButton getBtnRegTipG() {
        return btnRegTipG;
    }

    public void setBtnBuscTipG(RichCommandButton cb2) {
        this.btnBuscTipG = cb2;
    }

    public RichCommandButton getBtnBuscTipG() {
        return btnBuscTipG;
    }

    public void setTbTipGa(RichTable t2) {
        this.tbTipGa = t2;
    }

    public RichTable getTbTipGa() {
        return tbTipGa;
    }

    public void setSocEstTipG(RichSelectOneChoice soc1) {
        this.socEstTipG = soc1;
    }

    public RichSelectOneChoice getSocEstTipG() {
        return socEstTipG;
    }

    public void setSi6(UISelectItems si6) {
        this.si6 = si6;
    }

    public UISelectItems getSi6() {
        return si6;
    }

    public void setChoiceManifiesto(RichSelectOneChoice choiceManifiesto) {
        this.choiceManifiesto = choiceManifiesto;
    }

    public RichSelectOneChoice getChoiceManifiesto() {
        return choiceManifiesto;
    }

    public void setSi11(UISelectItems si11) {
        this.si11 = si11;
    }

    public UISelectItems getSi11() {
        return si11;
    }


    public void setListaManifiestos(List listaManifiestos) {
        this.listaManifiestos = listaManifiestos;
    }

    public List getListaManifiestos() {
        return listaManifiestos;
    }
}
