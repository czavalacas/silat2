package siat.view.backing.transporte;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlOutputText;

import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import javax.naming.Context;
import javax.naming.InitialContext;

import oracle.adf.model.OperationBinding;
import oracle.adf.model.BindingContext;
import oracle.adf.model.bean.DCDataRow;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.RichSubform;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectItem;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichDecorativeBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.event.DialogEvent;

import oracle.adfinternal.view.faces.model.binding.FacesCtrlHierNodeBinding;

import oracle.jbo.Row;

import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import org.apache.myfaces.trinidad.context.RequestContext;
import org.apache.myfaces.trinidad.event.SelectionEvent;

import siat.view.backing.administrativo.gastos.Frm_registrar_gastos;
import siat.view.backing.utiles.ADFUtil;

import siat.view.backing.utiles.Utils;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFOrdenServicioRemote;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFEmpresasRemote;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFFlotaRemote;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFModalidadPago;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFTipoGastoRemoto;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFUtilsRemote;
import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanGasto;
import silat.servicios_negocio.Beans.BeanModalidadPago;
import silat.servicios_negocio.Beans.BeanOrdenServicio;
import silat.servicios_negocio.Beans.BeanTipoGasto;
import silat.servicios_negocio.Beans.BeanUsuarioAutenticado;
import silat.servicios_negocio.LNSF.IR.LN_C_SFGastosRemoto;
import silat.servicios_negocio.LNSF.IR.LN_C_SFOrdenServicioRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFRelacionEmpresaRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFUtilsRemote;
import silat.servicios_negocio.LNSF.IR.LN_T_SFGastosRemoto;

public class Frm_actualizar_orden_servicio {
    private RichDecorativeBox db1;
    private RichPanelGroupLayout pgl1;
    private RichTable tbOS;
    private HtmlOutputText ot8;
    private RichPanelGroupLayout pgl3;
    private RichPanelGroupLayout pgl2;
    private RichPanelSplitter ps1;
    private RichPopup p2;
    private RichPanelGroupLayout pgl5;
    private RichCommandButton btnEditar;
    private RichPanelFormLayout pfl1;
    private RichInputText it1;
    private RichInputText it2;
    private RichInputDate id1;
    private RichInputText it3;
    private RichInputText it4;
    private RichCommandButton cb1;
    private RichInputText it5;
    private RichSubform sfOS;
    private RichInputText inputCliente;
    private RichInputDate inputFechaMin;
    private RichInputDate inputFechaMax;
    private RichInputText inputDetalle;
    private RichSelectOneChoice choicEstado;
    private UISelectItems itemEstado;
    private RichCommandButton btnLimpiar;
    private RichCommandButton btnBuscar;
    private RichSelectOneChoice choiceEditarEstado;
    private UISelectItems si1;
    private RichSelectItem si2;
    private RichSelectItem si3;
    private RichDialog d1;
    private RichPopup popClientes;
    private RichTable tbCli;
    private RichInputText itCodOS;
    private RichCommandButton btnFec;
    private RichPopup popFecOS;
    private RichInputDate fecOS;
    private String nombreEmpresa;
    private BigDecimal nidPartyEmpresa;
    private BeanOrdenServicio beanOrdServ = new BeanOrdenServicio();
    private String error = "";
    private String cidError = "";
    private boolean anulada = false;
    private SessionScopeBeanActualizarOrdServ beanSessionActualizarOrdenServicio;
    private List lstEstados = new ArrayList();
    private LN_C_SFUtilsRemote ln_C_SFUtilsRemote = null;
    private final static String LOOKUP_NAME_SFUTILS_REMOTO    = "mapLN_C_SFUtils";
    private final static String LOOKUP_NAME_SFCONTORDS_REMOTO = "LUBAL_SIAT_APP-SILATNEGOCIO-BDL_C_SFOrdenServicio";
    private final static String LOOKUP_NAME_SFC_EMPR_REMOTO   = "mapBDL_C_SFEmpresas";
    private final static String LOOKUP_NAME_SFORDSERV_REMOTO  = "mapLN_C_SFOrdenServicio";
    private BDL_C_SFOrdenServicioRemote bdl_C_SFOrdenServicioRemote;
    private LN_C_SFOrdenServicioRemote ln_C_SFOrdenServicioRemote;
    private BDL_C_SFEmpresasRemote bdL_C_SFEmpresasRemote;
    FacesContext ctx = FacesContext.getCurrentInstance();
    private BeanUsuarioAutenticado beanUsuario = (BeanUsuarioAutenticado) Utils.getSession("USER");

    public Frm_actualizar_orden_servicio() {
        try {
            final Context ctx;
            ctx = new InitialContext();
            ln_C_SFUtilsRemote = (LN_C_SFUtilsRemote)                  ctx.lookup(LOOKUP_NAME_SFUTILS_REMOTO);
            this.setLstEstados(Utils.llenarCombos(ln_C_SFUtilsRemote.getListADMCONS("TRMORDS", "C_ESTORD")));
            ln_C_SFOrdenServicioRemote = (LN_C_SFOrdenServicioRemote)  ctx.lookup(LOOKUP_NAME_SFORDSERV_REMOTO);
            bdl_C_SFOrdenServicioRemote = (BDL_C_SFOrdenServicioRemote)ctx.lookup(LOOKUP_NAME_SFCONTORDS_REMOTO);
            bdL_C_SFEmpresasRemote = (BDL_C_SFEmpresasRemote)          ctx.lookup(LOOKUP_NAME_SFC_EMPR_REMOTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
    @PostConstruct
    public void methodInvokeOncedOnPageLoad(){
        if(beanSessionActualizarOrdenServicio.getExec() == 0){
            beanSessionActualizarOrdenServicio.setExec(1);
            filtrarOrdenServicio();
            if(Utils.hasPermiso(beanUsuario.getLstPermisos(),new BigDecimal(24))){
                beanSessionActualizarOrdenServicio.setRenderBtnEditar(true);
            }else{
                beanSessionActualizarOrdenServicio.setRenderBtnEditar(false);
            }
            if(Utils.hasPermiso(beanUsuario.getLstPermisos(),new BigDecimal(56))){//Cambiar Fechas
                beanSessionActualizarOrdenServicio.setRenderBtnFecha(true);
            }
        }else{
            //Utils.depurar("POST CONSTRUCT otras veces");
        }
    }

    public boolean isCPA(String x) {
        if ("C".equals(x) || "P".equals(x) || "A".equals(x)) {
            return true;
        } else {
            return false;
        }
    }

    public void eventoEstadoOrdServEditar(ValueChangeEvent vcl) {
        vcl.getComponent().processUpdates(FacesContext.getCurrentInstance());
        String newValue = vcl.getNewValue().toString();
        if (isCPA(newValue) == false) {
            Utils.throwError_Aux(ctx, "Solo puede cambiar a estado 'Cerrado' O 'Anulado'", 1);
            choiceEditarEstado.resetValue();
            Utils.addTarget(choiceEditarEstado);
            beanSessionActualizarOrdenServicio.setEstadoOrdnServicioEditar("P");
        }
        if ("A".equals(newValue)) {
            anulada = true;
            Utils.addTarget(it4);
            System.out.println("Entro");
            int num = bdl_C_SFOrdenServicioRemote.verificarOSConGuia(beanSessionActualizarOrdenServicio.getNidOrdenServ());
            if (num != 0) {
                Utils.throwError_Aux(ctx, "Operacion rechazada, Orden de servicio asociada con una guia", 1);
                choiceEditarEstado.resetValue();
                Utils.addTarget(choiceEditarEstado);
                beanSessionActualizarOrdenServicio.setEstadoOrdnServicioEditar("P");
            }
        }
        else{
            anulada = false;
            Utils.addTarget(it4);
            System.out.println("Salio");
        }
    }
    
    public void limpiar(ActionEvent actionEvent){
        clearLista();
    }

    public List<BeanOrdenServicio> clearLista() {
        resetearCamposConsulta();
        BeanOrdenServicio beanOrdServ = new BeanOrdenServicio();
        beanSessionActualizarOrdenServicio.setListOrdenServ(ln_C_SFOrdenServicioRemote.findOrdenServicioByAttributesAux(beanOrdServ));
        if (tbOS != null) {
            Utils.unselectFilas(tbOS);
            tbOS.setValue(beanSessionActualizarOrdenServicio.getListOrdenServ());
            Utils.addTarget(tbOS);
        }
        return null;
    }
    
    public void resetearCamposConsulta() {       
        beanSessionActualizarOrdenServicio.setFecOrdnMin(null);
        beanSessionActualizarOrdenServicio.setFecOrdnMax(null);
        beanSessionActualizarOrdenServicio.setCRazonSocial(null);
        beanSessionActualizarOrdenServicio.setCEstadoOrdenDesc(null);
        beanSessionActualizarOrdenServicio.setCDetalle(null);
        beanSessionActualizarOrdenServicio.setListOrdenServ(null); 
        beanSessionActualizarOrdenServicio.setNidOS(null);
    }

    public String ActualizarOrdenServicio() {
        BeanOrdenServicio beanOrden = ln_C_SFOrdenServicioRemote.ModificarOrdenServicio(beanSessionActualizarOrdenServicio.getNidEmp(),
                                                                                        beanSessionActualizarOrdenServicio.getCDetalleEditar(),
                                                                                        beanSessionActualizarOrdenServicio.getFechaOrdEditar(),
                                                                                        beanSessionActualizarOrdenServicio.getNidOrdenServ(),
                                                                                        beanSessionActualizarOrdenServicio.getEstadoOrdnServicioEditar(),
                                                                                        beanSessionActualizarOrdenServicio.isRenderBtnChangeCliente(),
                                                                                        beanSessionActualizarOrdenServicio.getBeanOrden().getComentario());
        p2.hide();
        clearLista();
        if (beanOrden.getBeanError() != null) {
            BeanError error = beanOrden.getBeanError();
            int severidad = 0;
            if ("000".equals(error.getCidError())) {
                severidad = 3;
            } else {
                severidad = 1;
            }
            Utils.unselectFilas(tbOS);
            Utils.throwError_Aux(ctx, error.getCDescripcionError(), severidad);
        } else {
            Utils.throwError_Aux(ctx, "Error Inesperado", 1);
        }
        return null;
    }
    
    public String showPopUp(){
        try{
            Utils.showPopUpMIDDLE(p2);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String filtrarOrdenServicio() {
        beanSessionActualizarOrdenServicio.setListOrdenServ(null);
        BeanOrdenServicio beanOrdServ = new BeanOrdenServicio();
        beanOrdServ.setCRazonSocial(beanSessionActualizarOrdenServicio.getCRazonSocial());
        beanOrdServ.setCDetalle(beanSessionActualizarOrdenServicio.getCDetalle());
        beanOrdServ.setCEstadoOrdenDesc(beanSessionActualizarOrdenServicio.getCEstadoOrdenDesc());
        beanOrdServ.setFecOrdnMax(beanSessionActualizarOrdenServicio.getFecOrdnMax());
        beanOrdServ.setFecOrdnMin(beanSessionActualizarOrdenServicio.getFecOrdnMin());
        beanOrdServ.setNidOrdnServ(beanSessionActualizarOrdenServicio.getNidOS());
        beanSessionActualizarOrdenServicio.setListOrdenServ(ln_C_SFOrdenServicioRemote.findOrdenServicioByAttributesAux(beanOrdServ));
        if (tbOS != null) {
            tbOS.setValue(beanSessionActualizarOrdenServicio.getListOrdenServ());
            Utils.unselectFilas(tbOS);
            Utils.addTarget(tbOS);
        }
        return null;
    }
    
    public String registrarAction(){
        FacesContext ctx = FacesContext.getCurrentInstance();
        Utils.throwError_Aux(ctx,getError(),1);
        return null;
    }

    /**
     * @autor: Jorge Cespedes
     * @date: 10/05/13
     * Metodo para refrescar
     * dentro de DCB.findIteratorBinding("MostrarOrdenServNFIterator");
     * va entre comillas el nombre del iterador al cual apuntan,
     * este se encuentra en el pagedef.xml
     */
    public String refresh() {
        DCBindingContainer DCB = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iter = DCB.findIteratorBinding("MostrarOrdenServNFIterator");
        iter.executeQuery();
        return null;
    }

    public void getActualizar(SelectionEvent selectionEvent) {
        //     getBeanSessionActualizarOrdenServicio().setBeanOrden((BeanOrdenServicio)getTableSelected(tbOrdServ));
        RichTable t = (RichTable)selectionEvent.getSource();
        Object _selectedRowData = t.getSelectedRowData();
        BeanOrdenServicio ordenServ = (BeanOrdenServicio)_selectedRowData;
        beanSessionActualizarOrdenServicio.setBeanOrden(ordenServ);
        beanSessionActualizarOrdenServicio.setNidOrdenServ(ordenServ.getNidOrdnServ());
        beanSessionActualizarOrdenServicio.setEstadoOrdnServicioEditar(ordenServ.getCEstord());
        beanSessionActualizarOrdenServicio.setCDetalleEditar(ordenServ.getCDetalle());
        beanSessionActualizarOrdenServicio.setCRazonSocialEditar(ordenServ.getCRazonSocial());
        beanSessionActualizarOrdenServicio.setFechaOrdEditar(ordenServ.getFecOrdnServ());
        beanSessionActualizarOrdenServicio.setRenderBtnChangeCliente(isRenderearBtnCambiarCliente());
        beanSessionActualizarOrdenServicio.setNidEmp(ordenServ.getAdEmpresa().getNidParty());
        if ("P".equals(beanSessionActualizarOrdenServicio.getEstadoOrdnServicioEditar())) {
            btnEditar.setDisabled(false);
        } else {
            btnEditar.setDisabled(true);
        }
        if(beanSessionActualizarOrdenServicio.isRenderBtnFecha()){
            btnFec.setDisabled(false);
        }else{
            btnFec.setDisabled(true);
        }
        Utils.addTargetMany(btnFec,btnEditar);
    }
    
    private boolean isRenderearBtnCambiarCliente(){
        int num = bdl_C_SFOrdenServicioRemote.verificarOSConGuia(beanSessionActualizarOrdenServicio.getNidOrdenServ());
        if (num != 0) {
            return false;
        }
        return true;
    }
    
    public String buscarEmpresasCliente(){
        beanSessionActualizarOrdenServicio.setLstEmpresasCliente(this.bdL_C_SFEmpresasRemote.getADEmpresabyName(beanSessionActualizarOrdenServicio.getRazSocial()));
        return null;
    }
    
    public void selectNewCliente(SelectionEvent selectionEvent) {
        RichTable t = (RichTable)selectionEvent.getSource();
        Object _selectedRowData = t.getSelectedRowData();
        BeanEmpresa empresa = (BeanEmpresa) _selectedRowData;
        beanSessionActualizarOrdenServicio.setNidEmp(empresa.getNidParty());
        beanSessionActualizarOrdenServicio.setCRazonSocialEditar(empresa.getCRazonSocial());
        popClientes.hide();
        Utils.addTargetMany(it5,it1);
    }
    
    public void abrirPopNewCliente(ActionEvent ae) {
        beanSessionActualizarOrdenServicio.setLstEmpresasCliente(this.bdL_C_SFEmpresasRemote.getADEmpresabyName(""));
        Utils.showPopUpMIDDLE(popClientes);
    }
           
    public void abrirPopUpFecha(ActionEvent ae) {
        Utils.showPopUpMIDDLE(popFecOS);
    }
    
    public void dialogCambiarFechasOS(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
            try{
                String result = ln_C_SFOrdenServicioRemote.cambiarFechaOS_Permiso56(beanSessionActualizarOrdenServicio.getNidOrdenServ(), 
                                                                                    beanSessionActualizarOrdenServicio.getFechaOrdEditar());
                if("000".equals(result)){
                    clearLista();
                    Utils.throwError_Aux(ctx,"Se modifico la fecha de la Orden de Servicio", 3);
                }else{
                    Utils.throwError_Aux(ctx,"Hubo un error al modificar la fecha.", 1);
                }
            }catch(Exception e){
                e.printStackTrace();
                Utils.throwError_Aux(ctx,"Error Inesperado", 1);
            }
            beanSessionActualizarOrdenServicio.setNidOrdenServ(null);
            beanSessionActualizarOrdenServicio.setFechaOrdEditar(null);
            btnFec.setDisabled(true);
            Utils.addTarget(btnFec);
            popFecOS.hide();
        }
    }
           
    public void setDb1(RichDecorativeBox db1) {
        this.db1 = db1;
    }

    public RichDecorativeBox getDb1() {
        return db1;
    }


    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }


    public void setTbOS(RichTable t1) {
        this.tbOS = t1;
    }

    public RichTable getTbOS() {
        return tbOS;
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


    public void setOt8(HtmlOutputText ot8) {
        this.ot8 = ot8;
    }

    public HtmlOutputText getOt8() {
        return ot8;
    }

    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
    }

    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }

    public void setPs1(RichPanelSplitter ps1) {
        this.ps1 = ps1;
    }

    public RichPanelSplitter getPs1() {
        return ps1;
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

    public void setP2(RichPopup p2) {
        this.p2 = p2;
    }

    public RichPopup getP2() {
        return p2;
    }

    public void setPgl5(RichPanelGroupLayout pgl5) {
        this.pgl5 = pgl5;
    }

    public RichPanelGroupLayout getPgl5() {
        return pgl5;
    }


    public void setBtnEditar(RichCommandButton cb5) {
        this.btnEditar = cb5;
    }

    public RichCommandButton getBtnEditar() {
        return btnEditar;
    }

    public void setBeanOrdServ(BeanOrdenServicio beanOrdServ) {
        this.beanOrdServ = beanOrdServ;
    }

    public BeanOrdenServicio getBeanOrdServ() {
        return beanOrdServ;
    }

    public void setBeanSessionActualizarOrdenServicio(SessionScopeBeanActualizarOrdServ beanSessionActualizarOrdenServicio) {
        this.beanSessionActualizarOrdenServicio = beanSessionActualizarOrdenServicio;
    }

    public SessionScopeBeanActualizarOrdServ getBeanSessionActualizarOrdenServicio() {
        return beanSessionActualizarOrdenServicio;
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

    public void setIt2(RichInputText it2) {
        this.it2 = it2;
    }

    public RichInputText getIt2() {
        return it2;
    }

    public void setId1(RichInputDate id1) {
        this.id1 = id1;
    }

    public RichInputDate getId1() {
        return id1;
    }

    public void setIt3(RichInputText it3) {
        this.it3 = it3;
    }

    public RichInputText getIt3() {
        return it3;
    }

    public void setCb1(RichCommandButton cb1) {
        this.cb1 = cb1;
    }

    public RichCommandButton getCb1() {
        return cb1;
    }

    public void setIt5(RichInputText it5) {
        this.it5 = it5;
    }

    public RichInputText getIt5() {
        return it5;
    }


    public void setSfOS(RichSubform s1) {
        this.sfOS = s1;
    }

    public RichSubform getSfOS() {
        return sfOS;
    }

    public void setInputCliente(RichInputText it4) {
        this.inputCliente = it4;
    }

    public RichInputText getInputCliente() {
        return inputCliente;
    }

    public void setInputFechaMin(RichInputDate id2) {
        this.inputFechaMin = id2;
    }

    public RichInputDate getInputFechaMin() {
        return inputFechaMin;
    }

    public void setInputFechaMax(RichInputDate id3) {
        this.inputFechaMax = id3;
    }

    public RichInputDate getInputFechaMax() {
        return inputFechaMax;
    }

    public void setInputDetalle(RichInputText it6) {
        this.inputDetalle = it6;
    }

    public RichInputText getInputDetalle() {
        return inputDetalle;
    }

    public void setChoicEstado(RichSelectOneChoice soc1) {
        this.choicEstado = soc1;
    }

    public RichSelectOneChoice getChoicEstado() {
        return choicEstado;
    }

    public void setItemEstado(UISelectItems si1) {
        this.itemEstado = si1;
    }

    public UISelectItems getItemEstado() {
        return itemEstado;
    }

    public void setBtnLimpiar(RichCommandButton cb2) {
        this.btnLimpiar = cb2;
    }

    public RichCommandButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public void setBtnBuscar(RichCommandButton cb3) {
        this.btnBuscar = cb3;
    }

    public RichCommandButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setLstEstados(List lstEstados) {
        this.lstEstados = lstEstados;
    }

    public List getLstEstados() {
        return lstEstados;
    }

    public void setChoiceEditarEstado(RichSelectOneChoice soc1) {
        this.choiceEditarEstado = soc1;
    }

    public RichSelectOneChoice getChoiceEditarEstado() {
        return choiceEditarEstado;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public UISelectItems getSi1() {
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


    public void setD1(RichDialog d1) {
        this.d1 = d1;
    }

    public RichDialog getD1() {
        return d1;
    }

    public void setPopClientes(RichPopup p1) {
        this.popClientes = p1;
    }

    public RichPopup getPopClientes() {
        return popClientes;
    }

    public void setTbCli(RichTable t1) {
        this.tbCli = t1;
    }

    public RichTable getTbCli() {
        return tbCli;
    }

    public void setItCodOS(RichInputText it4) {
        this.itCodOS = it4;
    }

    public RichInputText getItCodOS() {
        return itCodOS;
    }

    public void setBtnFec(RichCommandButton cb2) {
        this.btnFec = cb2;
    }

    public RichCommandButton getBtnFec() {
        return btnFec;
    }

    public void setPopFecOS(RichPopup p1) {
        this.popFecOS = p1;
    }

    public RichPopup getPopFecOS() {
        return popFecOS;
    }

    public void setFecOS(RichInputDate id2) {
        this.fecOS = id2;
    }

    public RichInputDate getFecOS() {
        return fecOS;
    }

    public void setIt4(RichInputText it4) {
        this.it4 = it4;
    }

    public RichInputText getIt4() {
        return it4;
    }

    public void setAnulada(boolean anulada) {
        this.anulada = anulada;
    }

    public boolean isAnulada() {
        return anulada;
    }
}
