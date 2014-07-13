package siat.view.backing.transporte;

import java.math.BigDecimal;

import java.util.Date;
import java.util.HashMap;

import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.naming.Context;
import javax.naming.InitialContext;

import oracle.adf.model.bean.DCDataRow;
import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.RichSubform;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichDecorativeBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adfinternal.view.faces.model.binding.FacesCtrlHierNodeBinding;

import oracle.jbo.Row;

import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import siat.view.backing.utiles.ADFUtil;
import siat.view.backing.utiles.Utils;

import silat.servicios_negocio.BDLSF.IR.BDL_C_SFEmpresasRemote;
import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.Beans.BeanOrdenServicio;
import silat.servicios_negocio.Beans.BeanUsuarioAutenticado;
import silat.servicios_negocio.LNSF.IR.LN_C_SFFlotaRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFOrdenServicioRemote;

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
    private BDL_C_SFEmpresasRemote bdL_C_SFEmpresasRemote;
    private LN_C_SFOrdenServicioRemote ln_C_SFOrdenServicioRemote;
    private String nombreEmpresa;
    private BigDecimal nidPartyEmpresa;
    private BeanOrdenServicio beanOrdenServicio = new BeanOrdenServicio();
    private BeanEmpresa beanEmpresa = new BeanEmpresa();
    private SessionScopeRegistrarOS beanSessionScopedRegistrarOS;
    private String error = "";
    private String cidError = "";
    private Date fechaHoy = new Date();
    FacesContext ctx = FacesContext.getCurrentInstance();
    private RichInputText itNidOS;

    public Frm_registrar_orden_servicio(){
        try{
            final Context ctx;
            ctx = new InitialContext();
            bdL_C_SFEmpresasRemote = (BDL_C_SFEmpresasRemote) ctx.lookup(LOOKUP_NAME_SFC_EMPR_REMOTO);
            ln_C_SFOrdenServicioRemote = (LN_C_SFOrdenServicioRemote)  ctx.lookup(LOOKUP_NAME_SFORDSERV_REMOTO);
        }catch(Exception e){
            e.printStackTrace();
            Utils.redireccionar("/silat/faces/frm_login");
        }
    }
    
    @PostConstruct
    public void methodInvokeOncedOnPageLoad(){
        if(beanSessionScopedRegistrarOS.getExec() == 0){
            beanSessionScopedRegistrarOS.setExec(1);
            beanSessionScopedRegistrarOS.setNidOSGenerado(ln_C_SFOrdenServicioRemote.traerSiguienteValorSequence());
        }else{
            beanSessionScopedRegistrarOS.setNidOSGenerado(ln_C_SFOrdenServicioRemote.traerSiguienteValorSequence());
        }
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
        ADFUtil.setEL("#{pageFlowScope.nidParty}", nidPar);
        try{
            it1.setValue(razSol);
            it4.setValue(new BigDecimal(nidPar+""));
            btnGrabar.setDisabled(false);
            Utils.addTargetMany(it4,it1,btnGrabar);
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
            ADFUtil.setEL("#{pageFlowScope.fecha}",beanSessionScopedRegistrarOS.getFechaHoy());
            Utils.mandarParametro("fecha", "#{pageFlowScope.fecha}", "grabarOrdenServicio");
            Utils.mandarParametro("nidParty", "#{pageFlowScope.nidParty}", "grabarOrdenServicio");
            beanOrdenServicio = (BeanOrdenServicio)ADFUtil.invokeEL("#{bindings.grabarOrdenServicio.execute}");
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
}
