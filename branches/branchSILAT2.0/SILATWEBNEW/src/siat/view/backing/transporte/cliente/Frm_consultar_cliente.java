package siat.view.backing.transporte.cliente;

import java.math.BigDecimal;

import java.util.ArrayList;

import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlOutputText;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import javax.naming.Context;
import javax.naming.InitialContext;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.RichSubform;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichDecorativeBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adfinternal.view.faces.model.binding.FacesCtrlHierNodeBinding;

import oracle.jbo.Row;

import oracle.jbo.Session;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import siat.view.backing.utiles.Utils;

import silat.servicios_negocio.BDLSF.IR.BDL_C_SFEmpresasRemote;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFUtilsRemote;
import silat.servicios_negocio.Beans.BeanADRelacionEmpresa;
import silat.servicios_negocio.Beans.BeanADTipoRelacion;
import silat.servicios_negocio.Beans.BeanConstraint;
import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.Beans.BeanFlota;
import silat.servicios_negocio.Beans.BeanOrdenServicio;
import silat.servicios_negocio.LNSF.IR.LN_C_SFChoferRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFDireccionRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFEmpresasRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFFlotaRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFRelacionEmpresaRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFUtilsRemote;
import silat.servicios_negocio.LNSF.SFBean.LN_C_SFEmpresasBean;
import silat.servicios_negocio.entidades.admin.ADEmpresa;
import silat.servicios_negocio.entidades.admin.ADParty;
import silat.servicios_negocio.entidades.admin.ADRelacionEmpresa;
import silat.servicios_negocio.entidades.admin.ADTipoRelacion;
//import silat.servicios_negocio.LN_C_SFDireccionRemoteClient;

public class Frm_consultar_cliente {
    private RichDecorativeBox db1;
    private RichPanelGroupLayout pgl1;
    private HtmlOutputText ot1;
    private RichPanelGroupLayout pgl2;
    private RichTable t1;
    private RichPanelGroupLayout pgl3;
    private RichCommandButton cb1;
    private RichSpacer s1;
    private RichCommandButton cb2;
    private RichPopup p1;
    private RichPopup p2;
    private RichTable t2;
    private RichTable t3;
    //////////////////popup choferes
    private RichPopup popupChoferes;
    private RichTable tablaPopupChofer;
    private RichSpacer s2;
    private RichCommandButton btnVerChoferes;
    private RichPopup popupVerFlotas;
    private RichTable tabFlotas;
    private RichSpacer s3;
    private RichCommandButton btnVerFlotas;
    private RichDialog d1;
    private RichDialog d2;
    private RichDialog d3;
    private RichDialog d4;
    private RichSubform sfEM;
    private RichInputText it1;
    private RichInputText it2;
    private RichSelectOneChoice soc1;
    private UISelectItems si1;
    private RichCommandButton btnBscar;
    private RichCommandButton btnLimpiar;
    // My Variable
    private SessionScopedBeanConsultarCliente beanSessionConsultarCliente;
    private LN_C_SFDireccionRemote ln_C_SFDireccionRemote = null;
    private final static String LOOKUP_NAME_SFDireccion_REMOTO = "LUBAL_SIAT_APP-SILATNEGOCIO-LN_C_SFDireccion";//#silat.servicios_negocio.LNSF.IR.LN_C_SFDireccionRemote
    private LN_C_SFRelacionEmpresaRemote ln_C_SFRelacionEmpresaRemote = null;
    private final static String LOOKUP_NAME_SFRelacionEmpresa_REMOTO = "mapLN_C_SFRelacionEmpresa";//#silat.servicios_negocio.LNSF.IR.LN_C_SFRelacionEmpresaRemote
    private LN_C_SFChoferRemote ln_C_SFChoferRemote=null;
    private final static String LOOKUP_NAME_SFChofer_REMOTO = "mapLN_C_SFChofer";//#silat.servicios_negocio.LNSF.IR.LN_C_SFChoferRemote
    private LN_C_SFFlotaRemote ln_C_SFFlotaRemote=null;
    private final static String LOOKUP_NAME_SFFlota_REMOTO= "mapLN_C_SFFlota";//#silat.servicios_negocio.LNSF.IR.LN_C_SFFlotaRemote
    private LN_C_SFEmpresasRemote ln_C_SFEmpresasRemote=null;
    private final static String LOOKUP_NAME_SFEmpresa_REMOTO = "mapLN_C_SFEmpresas";//#silat.servicios_negocio.LNSF.IR.LN_C_SFEmpresasRemote
    private LN_C_SFUtilsRemote ln_C_SFUtilsRemote = null;
    private final static String LOOKUP_NAME_SFUTILS_REMOTO = "mapLN_C_SFUtils";//#silat.servicios_negocio.LNSF.IR.LN_C_SFUtilsRemote
    private BeanEmpresa beanEmpresa=new BeanEmpresa();
    private List listRelacion;

    public Frm_consultar_cliente(){
        try {
            final Context ctx;
            ctx = new InitialContext();
            ln_C_SFUtilsRemote = (LN_C_SFUtilsRemote)ctx.lookup(LOOKUP_NAME_SFUTILS_REMOTO);
            ln_C_SFDireccionRemote = (LN_C_SFDireccionRemote)ctx.lookup(LOOKUP_NAME_SFDireccion_REMOTO);
            ln_C_SFRelacionEmpresaRemote = (LN_C_SFRelacionEmpresaRemote)ctx.lookup(LOOKUP_NAME_SFRelacionEmpresa_REMOTO);
            ln_C_SFChoferRemote = (LN_C_SFChoferRemote)ctx.lookup(LOOKUP_NAME_SFChofer_REMOTO);   
            ln_C_SFFlotaRemote = (LN_C_SFFlotaRemote)ctx.lookup(LOOKUP_NAME_SFFlota_REMOTO);
            ln_C_SFEmpresasRemote = (LN_C_SFEmpresasRemote)ctx.lookup(LOOKUP_NAME_SFEmpresa_REMOTO);
            //this.setListRelacion(Utils.llenarCombos(ln_C_SFUtilsRemote.getListADMCONS("ADMTIRE","C_DESTIP")));  
            this.setListRelacion(Utils.llenarBeanCombos(ln_C_SFUtilsRemote.getListaParaCombo_LN("ADTipoRelacion","descripcionTipoRelacion","nidTipoRelacion")));  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @PostConstruct
    public void postConstructor(){
        if(beanSessionConsultarCliente.getExec() == 0){
            beanSessionConsultarCliente.setExec(1);
            filtrarEmpresas();           
        }else{
            //Utils.depurar("POST CONSTRUCT otras veces");
        }
         
    }
    
    
    public String filtrarEmpresas() {
        beanSessionConsultarCliente.setBeanListEmpresas(null);
        BeanEmpresa beanEmpresa = new BeanEmpresa();
        beanEmpresa.setCRazonSocial(beanSessionConsultarCliente.getRazonSocial());
        beanEmpresa.setCRuc(beanSessionConsultarCliente.getRuc());
        if (beanSessionConsultarCliente.getRelacionEmpresa() != null) {
            BeanADTipoRelacion tipoRela = new BeanADTipoRelacion();
            tipoRela.setNidTipoRelacion(Integer.parseInt(beanSessionConsultarCliente.getRelacionEmpresa()));
            BeanADRelacionEmpresa relaEmpresa = new BeanADRelacionEmpresa();
            relaEmpresa.setAdTipoRelacion(tipoRela);
            beanEmpresa.setRelaEmpresa(relaEmpresa);
        }
        beanSessionConsultarCliente.setBeanListEmpresas(ln_C_SFEmpresasRemote.findEmpresasByAttributes(beanEmpresa));
        if (t1 != null) {
            t1.setValue(beanSessionConsultarCliente.getBeanListEmpresas());
            Utils.addTarget(t1);
        }
        return null;
    }

    public void limpiar(ActionEvent actionEvent) {
        clearLista();
    }

    public List<BeanEmpresa> clearLista() {
        resetearCamposConsulta();
        BeanEmpresa beanEmpresa = new BeanEmpresa();
        beanSessionConsultarCliente.setBeanListEmpresas(ln_C_SFEmpresasRemote.findEmpresasByAttributes(beanEmpresa));
        if (t1 != null) {
            t1.setValue(beanSessionConsultarCliente.getBeanListEmpresas());
            Utils.addTarget(t1);
        }
        return beanSessionConsultarCliente.getBeanListEmpresas();
    }
    
    public void resetearCamposConsulta() {       
        beanSessionConsultarCliente.setRazonSocial(null);
        beanSessionConsultarCliente.setRelacionEmpresa(null);
        beanSessionConsultarCliente.setRuc(null);
        beanSessionConsultarCliente.setBeanListEmpresas(null);        
    }
    
    public void seleccionar(SelectionEvent selectionEvent){
        cb1.setDisabled(false);
        cb2.setDisabled(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(cb1);
        AdfFacesContext.getCurrentInstance().addPartialTarget(cb2);
        RichTable t = (RichTable)selectionEvent.getSource();
        Object _selectedRowData = t.getSelectedRowData();
        beanEmpresa = (BeanEmpresa)_selectedRowData;
        beanSessionConsultarCliente.setBeanEmpresa(beanEmpresa);                
        beanSessionConsultarCliente.setNidParty(beanEmpresa.getNidParty());
        beanSessionConsultarCliente.setNombreEmpresa(beanEmpresa.getCRazonSocial()); 
        botonesProveedor();
    }

    public void botonesProveedor() {
        int num = 0;
        List<BeanADRelacionEmpresa> objArr =
            ln_C_SFRelacionEmpresaRemote.getRelacion(beanSessionConsultarCliente.getNidParty());
        if (objArr != null) { //objArr.size() > 0
            for (int x = 0; x < objArr.size(); x++) {
                String obj = objArr.get(x).getDescRela();
                Utils.depurar(obj);
                if (obj.equals("Proveedor")) { //Proveedor
                    num++;
                }
            }
            if (num != 0) {
                btnVerChoferes.setDisabled(false);
                btnVerFlotas.setDisabled(false);
                AdfFacesContext.getCurrentInstance().addPartialTarget(btnVerChoferes);
                AdfFacesContext.getCurrentInstance().addPartialTarget(btnVerFlotas);
            }
            if (num == 0) {
                btnVerChoferes.setDisabled(true);
                btnVerFlotas.setDisabled(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(btnVerChoferes);
                AdfFacesContext.getCurrentInstance().addPartialTarget(btnVerFlotas);
            }
        }
    }
    
    public String verChoferes(){
        if(beanSessionConsultarCliente.getNidParty() != null){
            beanSessionConsultarCliente.setBeanListChofer(ln_C_SFChoferRemote.traerChoferesPorEmpresa(beanSessionConsultarCliente.getNidParty().intValue()));
            Utils.showPopUpMIDDLE(popupChoferes);
        }
        return null;
    }
    
    public String verFlotas(){
        if(beanSessionConsultarCliente.getNidParty() != null){
            beanSessionConsultarCliente.setBeanListFlota(ln_C_SFFlotaRemote.getFlotasPorEmpresa(getBeanSessionConsultarCliente().getNidParty().intValue()));
            Utils.showPopUpMIDDLE(popupVerFlotas);
        }
        return null;
    }

    public String setDireccion(){
        if(beanSessionConsultarCliente.getNidParty() != null){
            beanSessionConsultarCliente.setBeanListDire(ln_C_SFDireccionRemote.getRelacion(beanSessionConsultarCliente.getNidParty()));
            Utils.showPopUpMIDDLE(p1);
        }
        return null;
    }
    
    public String setRelacion(){
        if(beanSessionConsultarCliente.getNidParty() != null){
            beanSessionConsultarCliente.setBeanListRela(ln_C_SFRelacionEmpresaRemote.getRelacion(beanSessionConsultarCliente.getNidParty()));
            Utils.showPopUpMIDDLE(p2);  
        }
        return null;
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

    public void setOt1(HtmlOutputText ot1) {
        this.ot1 = ot1;
    }

    public HtmlOutputText getOt1() {
        return ot1;
    }

    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }

    public void setT1(RichTable t1) {
        this.t1 = t1;
    }

    public RichTable getT1() {
        return t1;
    }

    public void setBeanSessionConsultarCliente(SessionScopedBeanConsultarCliente beanSessionConsultarCliente) {
        this.beanSessionConsultarCliente = beanSessionConsultarCliente;
    }

    public SessionScopedBeanConsultarCliente getBeanSessionConsultarCliente() {
        return beanSessionConsultarCliente;
    }

    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
    }

    public void setCb1(RichCommandButton cb1) {
        this.cb1 = cb1;
    }

    public RichCommandButton getCb1() {
        return cb1;
    }

    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }

    public void setCb2(RichCommandButton cb2) {
        this.cb2 = cb2;
    }

    public RichCommandButton getCb2() {
        return cb2;
    }

    public void setP1(RichPopup p1) {
        this.p1 = p1;
    }

    public RichPopup getP1() {
        return p1;
    }

    public void setP2(RichPopup p2) {
        this.p2 = p2;
    }

    public RichPopup getP2() {
        return p2;
    }

    public void setT2(RichTable t2) {
        this.t2 = t2;
    }

    public RichTable getT2() {
        return t2;
    }

    public void setT3(RichTable t3) {
        this.t3 = t3;
    }

    public RichTable getT3() {
        return t3;
    }


    public void setPopupChoferes(RichPopup popupChoferes) {
        this.popupChoferes = popupChoferes;
    }

    public RichPopup getPopupChoferes() {
        return popupChoferes;
    }


    public void setTablaPopupChofer(RichTable tablaPopupChofer) {
        this.tablaPopupChofer = tablaPopupChofer;
    }

    public RichTable getTablaPopupChofer() {
        return tablaPopupChofer;
    }

    public void setS2(RichSpacer s2) {
        this.s2 = s2;
    }

    public RichSpacer getS2() {
        return s2;
    }

    public void setBtnVerChoferes(RichCommandButton btnVerChoferes) {
        this.btnVerChoferes = btnVerChoferes;
    }

    public RichCommandButton getBtnVerChoferes() {
        return btnVerChoferes;
    }

    public void setPopupVerFlotas(RichPopup popupVerFlotas) {
        this.popupVerFlotas = popupVerFlotas;
    }

    public RichPopup getPopupVerFlotas() {
        return popupVerFlotas;
    }

    public void setTabFlotas(RichTable tabFlotas) {
        this.tabFlotas = tabFlotas;
    }

    public RichTable getTabFlotas() {
        return tabFlotas;
    }

    public void setS3(RichSpacer s3) {
        this.s3 = s3;
    }

    public RichSpacer getS3() {
        return s3;
    }


    public void setBtnVerFlotas(RichCommandButton btnVerFlotas) {
        this.btnVerFlotas = btnVerFlotas;
    }

    public RichCommandButton getBtnVerFlotas() {
        return btnVerFlotas;
    }

    public void setD1(RichDialog d1) {
        this.d1 = d1;
    }

    public RichDialog getD1() {
        return d1;
    }

    public void setD2(RichDialog d2) {
        this.d2 = d2;
    }

    public RichDialog getD2() {
        return d2;
    }

    public void setD3(RichDialog d3) {
        this.d3 = d3;
    }

    public RichDialog getD3() {
        return d3;
    }

    public void setD4(RichDialog d4) {
        this.d4 = d4;
    }

    public RichDialog getD4() {
        return d4;
    }

    public void setSfEM(RichSubform s4) {
        this.sfEM = s4;
    }

    public RichSubform getSfEM() {
        return sfEM;
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

    public void setSoc1(RichSelectOneChoice soc1) {
        this.soc1 = soc1;
    }

    public RichSelectOneChoice getSoc1() {
        return soc1;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public UISelectItems getSi1() {
        return si1;
    }

    public void setListRelacion(List listRelacion) {
        this.listRelacion = listRelacion;
    }

    public List getListRelacion() {
        return listRelacion;
    }

    public void setBtnBscar(RichCommandButton cb3) {
        this.btnBscar = cb3;
    }

    public RichCommandButton getBtnBscar() {
        return btnBscar;
    }

    public void setBtnLimpiar(RichCommandButton cb4) {
        this.btnLimpiar = cb4;
    }

    public RichCommandButton getBtnLimpiar() {
        return btnLimpiar;
    }
}
