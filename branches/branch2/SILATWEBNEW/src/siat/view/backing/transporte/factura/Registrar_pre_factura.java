package siat.view.backing.transporte.factura;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import javax.naming.Context;
import javax.naming.InitialContext;

import jxl.write.WriteException;

import oracle.adf.model.BindingContext;
import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.RichSubform;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.dnd.DnDAction;
import oracle.adf.view.rich.event.DropEvent;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import org.apache.myfaces.trinidad.model.CollectionModel;

import siat.view.backing.utiles.Utils;

import silat.servicios_negocio.BDLSF.IR.BDL_C_SFUtilsRemote;
import silat.servicios_negocio.Beans.BeanADUbigeo;
import silat.servicios_negocio.Beans.BeanCuadre;
import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanItemPreFactura;
import silat.servicios_negocio.Beans.BeanPreFactura;
import silat.servicios_negocio.Beans.BeanTRGuia;
import silat.servicios_negocio.Beans.BeanTRItem;
import silat.servicios_negocio.Beans.BeanUsuarioAutenticado;
import silat.servicios_negocio.LNSF.IL.LN_C_SFGuiaLocal;
import silat.servicios_negocio.LNSF.IR.LN_C_SFDireccionRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFEmpresasRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFGuiaRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFOrdenServicioRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFPreFacturaRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFUnidadNegocioRemote;
import silat.servicios_negocio.LNSF.IR.LN_T_SFFacturaRemote;
import silat.servicios_negocio.LNSF.IR.LN_T_SFPreFacturaRemote;
import silat.servicios_negocio.entidades.trans.TRGuia;
import silat.servicios_negocio.util_formato.UtilsGeneral;

public class Registrar_pre_factura {
    private RichOutputText otTitulo;
    private RichInputText itClie;
    private RichCommandButton btnBuscClie;
    private RichPopup popClie;
    private RichDialog dclie;
    private RichSubform sfClie;
    private RichInputText itClieRaz;
    private RichCommandButton btnSearchClie;
    private RichTable tbClie;
    private RichTable tabGuia;
    private RichCommandButton btnAddGuia;
    private RichCommandButton btnVerItms;
    private RichSelectOneChoice socGuias;
    private UISelectItems siGuias;
    private RichCommandButton btnEditarGuia;
    private RichInputText itSubTotal;
    private RichInputText itDestino;
    private RichCommandButton btnEditDestino;
    private RichPopup popGuiasCombo;
    private RichCommandButton btnBorrar;
    private RichTable tbGuiasCombo;
    private RichPopup popDestinos;
    private RichSelectOneChoice socDept_1;
    private UISelectItems si1;
    private RichSelectOneChoice socProv_1;
    private UISelectItems si2;
    private RichSelectOneChoice socDist_1;
    private UISelectItems si3;
    private RichSelectOneChoice socDept_2;
    private UISelectItems si4;
    private RichSelectOneChoice socProv_2;
    private UISelectItems si5;
    private RichSelectOneChoice socDist_2;
    private UISelectItems si6;
    private RichCommandButton btnAddItm;
    private RichTable tbItms;
    private RichCommandButton btnExportar;
    private RichCommandButton btnNew;
    private RichCommandButton btnBorrarItm;
    private RichInputText itCodPed;
    private RichCommandButton btnPersistItms;
    /*--------------------------------MIS VARIABLES-------------------------------------------*/
    private SessionScopeBeanRegistrarPreFactura beanSessionRegistrarPreFactura;
    private BeanUsuarioAutenticado beanUsuario = new BeanUsuarioAutenticado();
    private LN_C_SFEmpresasRemote ln_C_SFEmpresasRemote;
    private LN_C_SFGuiaRemote ln_C_SFGuiaRemote;
    private LN_T_SFPreFacturaRemote ln_T_SFPreFacturaRemote;
    private BDL_C_SFUtilsRemote bdL_C_SFUtilsRemote;
    FacesContext ctx = FacesContext.getCurrentInstance();
    private final static String LOOKUP_NAME_SFC_CLIENTE_REMOTO = "mapLN_C_SFEmpresas";
    private final static String LOOKUP_NAME_SFCGUIA_REMOTO     = "mapLN_C_SFGuia";
    private final static String LOOKUP_NAME_SFUTILS_REMOTO     = "mapBDL_C_SFUtils";
    private final static String LOOKUP_NAME_SFTPREFACT_REMOTO  = "mapLN_T_SFPreFactura";

    public Registrar_pre_factura(){
        try{
            final Context ctx;
            ctx = new InitialContext();
            ln_C_SFEmpresasRemote = (LN_C_SFEmpresasRemote)           ctx.lookup(LOOKUP_NAME_SFC_CLIENTE_REMOTO);
            ln_C_SFGuiaRemote = (LN_C_SFGuiaRemote)                   ctx.lookup(LOOKUP_NAME_SFCGUIA_REMOTO);
            bdL_C_SFUtilsRemote = (BDL_C_SFUtilsRemote)               ctx.lookup(LOOKUP_NAME_SFUTILS_REMOTO);
            ln_T_SFPreFacturaRemote = (LN_T_SFPreFacturaRemote)       ctx.lookup(LOOKUP_NAME_SFTPREFACT_REMOTO);
            beanUsuario = (BeanUsuarioAutenticado) Utils.getSession("USER");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostConstruct 
    public void methodOnPostConstruct(){
        if(beanSessionRegistrarPreFactura.getExec() == 0){
            beanSessionRegistrarPreFactura.setExec(1);
            String titulo = "";
            int tipEvento = 0;
            boolean renderearComponente = false;
            if(Utils.getSession("PREFAC") != null){
                titulo = "Modificar Pre-Factura";
                tipEvento = 2;
                BeanPreFactura beanPF = (BeanPreFactura) Utils.getSession("PREFAC");
                Utils.removeSession("PREFAC");
                beanSessionRegistrarPreFactura.setRazonCliente(beanPF.getCliente());
                beanSessionRegistrarPreFactura.setNidClie(new BigDecimal(beanPF.getNidCliente()));
                beanSessionRegistrarPreFactura.setItRazonSocial(beanPF.getCliente());
                beanSessionRegistrarPreFactura.setCodPedido(beanPF.getCodpedido());
                beanSessionRegistrarPreFactura.setNidPreFactura(beanPF.getNidPrefact());
                /************************************* TRAER LAS GUIAS LISTAS A FACTURAR DEL CLIENTE ******************/
                mostrarGuiasAux(beanSessionRegistrarPreFactura.getNidClie());
                /*****************************************************************************************************/
                beanSessionRegistrarPreFactura.setLstBeanItemPreFactura(beanPF.getItemsPreFacturaList());
            }else{
                titulo = "Registrar Pre-Factura";
                tipEvento = 1;
                renderearComponente = true;
            }
            beanSessionRegistrarPreFactura.setTitulo(titulo);
            beanSessionRegistrarPreFactura.setTipEventoGlobal(tipEvento);
            beanSessionRegistrarPreFactura.setRenderearComponente(renderearComponente);
        }else{

        }
    }
    
    public String abrirPopClie(){
        if(itClieRaz != null){
            itClieRaz.setValue(null);
        }
        beanSessionRegistrarPreFactura.setRazonCliente(null);
        Utils.unselectFilas(tbClie);
        mostrarClientes();
        Utils.addTargetMany(itClieRaz);
        Utils.showPopUp(popClie,otTitulo);
        return null;
    }
    
    public String mostrarClientes(){
        String valor = beanSessionRegistrarPreFactura.getRazonCliente(); 
        beanSessionRegistrarPreFactura.setLstClientes(ln_C_SFEmpresasRemote.getADEmpresaCliente(valor));
        return null;
    }
    
    public void selectCliente(SelectionEvent selectionEvent) {
        RichTable t = (RichTable) selectionEvent.getSource();
        BeanEmpresa beanClie = (BeanEmpresa) t.getSelectedRowData();
        beanSessionRegistrarPreFactura.setEmpresaSelected(beanClie);
        beanSessionRegistrarPreFactura.setRazonCliente(beanClie.getCRazonSocial());Utils.depurar("cli: "+beanSessionRegistrarPreFactura.getRazonCliente());
        beanSessionRegistrarPreFactura.setNidClie(beanClie.getNidParty());
        beanSessionRegistrarPreFactura.setGuiaSelected(null);
        beanSessionRegistrarPreFactura.getLstGuiasInCombo().removeAll(beanSessionRegistrarPreFactura.getLstGuiasInCombo());
        socGuias.resetValue();
        beanSessionRegistrarPreFactura.getGuiasCombo().removeAll(beanSessionRegistrarPreFactura.getGuiasCombo());
        beanSessionRegistrarPreFactura.setLstBeanItemPreFactura(new ArrayList<BeanItemPreFactura>());
        siGuias.setValue(null);
        itClie.setValue(beanClie.getCRazonSocial());
        Utils.unselectFilas(tabGuia);
        mostrarGuias();
        popClie.hide();
        Utils.addTargetMany(itClie,tabGuia,socGuias,siGuias,tbItms);
    }
    
    public String mostrarGuias(){//trae guias sin facturas
        BigDecimal nidClie = beanSessionRegistrarPreFactura.getNidClie();
        mostrarGuiasAux(nidClie);
        Utils.addTarget(tabGuia);
        return null;
    }
    
    public void mostrarGuiasAux(BigDecimal nidClie){
        if(nidClie != null){
            beanSessionRegistrarPreFactura.setLstGuias(ln_C_SFGuiaRemote.findGuiasByNidClienteParaPreFactura_LN(nidClie.intValue()));
           /* for(BeanTRGuia guia : beanSessionRegistrarPreFactura.getLstGuias()){
                guia.setAuxIsInComboPreFact("N");
            }*/
        }
    }
    
    public void selectGuia(SelectionEvent selectionEvent) {
        RichTable t = (RichTable) selectionEvent.getSource();
        BeanTRGuia beanTRGuia = (BeanTRGuia) t.getSelectedRowData();
        beanSessionRegistrarPreFactura.setGuiaSelected(beanTRGuia);
        btnAddGuia.setDisabled(false);
        btnVerItms.setDisabled(false);
        Utils.addTargetMany(btnAddGuia,btnVerItms);
    }
    
    public void agregarGuia(ActionEvent actionEvent) {
        if(beanSessionRegistrarPreFactura.getGuiaSelected() != null){
            if(!beanSessionRegistrarPreFactura.getLstGuiasInCombo().contains(beanSessionRegistrarPreFactura.getGuiaSelected()) &&
               !this.isInLstItms(beanSessionRegistrarPreFactura.getGuiaSelected())){
              //  beanSessionRegistrarPreFactura.getGuiaSelected().setAuxIsInComboPreFact("S");
                beanSessionRegistrarPreFactura.getLstGuiasInCombo().add(beanSessionRegistrarPreFactura.getGuiaSelected());
                beanSessionRegistrarPreFactura.setGuiasCombo(this.llenarGuias(beanSessionRegistrarPreFactura.getLstGuiasInCombo())); 
                beanSessionRegistrarPreFactura.getLstGuias().remove(beanSessionRegistrarPreFactura.getGuiaSelected());
            }else{
                Utils.throwError_Aux(ctx,"No se puede agregar el Item, Ya esta agregado.",4);
            }
            Utils.unselectFilas(tabGuia);
            Utils.addTargetMany(socGuias,tabGuia);
        }
    }
    
    private boolean isInLstItms(BeanTRGuia beanGuia){
        if(beanSessionRegistrarPreFactura.getLstBeanItemPreFactura() != null){
            for(BeanItemPreFactura itm : beanSessionRegistrarPreFactura.getLstBeanItemPreFactura()){
                if(ln_C_SFGuiaRemote.contieneItemGuia(beanGuia,itm.getGuiasLista())){
                    return true;//ESTA EN LA LISTA DE ITEMS Y NO DEBERIA AGREGARSE 
                }
            }
            return false;
        }
        return false;
    }
    
    public ArrayList llenarGuias(List<BeanTRGuia> guias){
        ArrayList guiaItems = new ArrayList();
        if(guias != null){
            if(guias.size() > 0){
                for (BeanTRGuia i : guias) {
                    guiaItems.add(new SelectItem(i,i.getCidGuia()));
                }
            }
        }
        return guiaItems;
    }
    
    public void selectGuiaBorrar(SelectionEvent selectionEvent) {
        RichTable t = (RichTable) selectionEvent.getSource();
        BeanTRGuia beanTRGuia = (BeanTRGuia) t.getSelectedRowData();
        beanSessionRegistrarPreFactura.setGuiaSelectedBorrar(beanTRGuia);
        btnBorrar.setDisabled(false);
        Utils.addTarget(btnBorrar);
    }
    
    public void borrarGuia(ActionEvent actionEvent) {
        if(beanSessionRegistrarPreFactura.getGuiaSelectedBorrar() != null){
            //beanSessionRegistrarPreFactura.getGuiaSelectedBorrar().setAuxIsInComboPreFact("N");
            beanSessionRegistrarPreFactura.getLstGuiasInCombo().remove(beanSessionRegistrarPreFactura.getGuiaSelectedBorrar());
            beanSessionRegistrarPreFactura.getLstGuias().add(beanSessionRegistrarPreFactura.getGuiaSelectedBorrar());
            if(beanSessionRegistrarPreFactura.getItemToEditar() != null){
                if(beanSessionRegistrarPreFactura.getItemToEditar().getFlgFromBD() != null){
                    if(beanSessionRegistrarPreFactura.getItemToEditar().getFlgFromBD().equals("1")){
                        beanSessionRegistrarPreFactura.getItemToEditar().getGuiasToNullList().add(beanSessionRegistrarPreFactura.getGuiaSelectedBorrar().getCidGuia());
                    }
                }
            }
            if(tbGuiasCombo != null){
                tbGuiasCombo.setValue(beanSessionRegistrarPreFactura.getLstGuiasInCombo());
                Utils.addTarget(tbGuiasCombo);
                Utils.unselectFilas(tbGuiasCombo);
                beanSessionRegistrarPreFactura.setGuiasCombo(this.llenarGuias(beanSessionRegistrarPreFactura.getLstGuiasInCombo()));
            }
        }
        btnBorrar.setDisabled(true);
        Utils.addTarget(btnBorrar);
    }
    
    public ArrayList fillListUbigeo(int tipUbigeo, String dep, String prov) {
        ArrayList list = new ArrayList();
        List<BeanADUbigeo> entitylist = bdL_C_SFUtilsRemote.getUbigeos(tipUbigeo, dep, prov);
        for (BeanADUbigeo ubigeo : entitylist) {
            list.add(new SelectItem(ubigeo.getCidUbigeo().toString(), ubigeo.getCDescUbigeo().toString()));
        }
        return list;
    }
    
    public void mostrarProvincias(ValueChangeEvent vcl) {
        vcl.getComponent().processUpdates(FacesContext.getCurrentInstance());
        String newValue = vcl.getNewValue().toString();
        beanSessionRegistrarPreFactura.setProvincias(this.fillListUbigeo(2, newValue, null));
        if(beanSessionRegistrarPreFactura.getDistritos() != null){
            beanSessionRegistrarPreFactura.getDistritos().removeAll(beanSessionRegistrarPreFactura.getDistritos());
        }
        beanSessionRegistrarPreFactura.setUbigeoRemite(newValue);
        beanSessionRegistrarPreFactura.setDep1(Utils.getChoiceLabel(vcl));
        beanSessionRegistrarPreFactura.setProv1(null);
        beanSessionRegistrarPreFactura.setDist1(null);
        beanSessionRegistrarPreFactura.setDestino(beanSessionRegistrarPreFactura.generarDestino());
        itDestino.setValue(beanSessionRegistrarPreFactura.getDestino());
        Utils.addTargetMany(socProv_1,socDist_1,itDestino);        
    }

    public void mostrarDistritos(ValueChangeEvent vcl) {
        vcl.getComponent().processUpdates(FacesContext.getCurrentInstance());
        String newValue = vcl.getNewValue().toString();
        beanSessionRegistrarPreFactura.setUbiPro(newValue);
        beanSessionRegistrarPreFactura.setDistritos(this.fillListUbigeo(3, null, newValue));
        beanSessionRegistrarPreFactura.setProv1(Utils.getChoiceLabel(vcl));
        beanSessionRegistrarPreFactura.setDist1(null);
        beanSessionRegistrarPreFactura.setDestino(beanSessionRegistrarPreFactura.generarDestino());
        itDestino.setValue(beanSessionRegistrarPreFactura.getDestino());
        Utils.addTargetMany(socDist_1,itDestino);
    }
    
    public void cambioDistrito(ValueChangeEvent vce) {
        vce.getComponent().processUpdates(FacesContext.getCurrentInstance());
        beanSessionRegistrarPreFactura.setUbiDis(vce.getNewValue().toString());
        beanSessionRegistrarPreFactura.setDist1(Utils.getChoiceLabel(vce));
        beanSessionRegistrarPreFactura.setDestino(beanSessionRegistrarPreFactura.generarDestino());
        itDestino.setValue(beanSessionRegistrarPreFactura.getDestino());
        Utils.addTargetMany(itDestino);
    }
    
     /*******************************************************************************************/
     /*******************************************************************************************/
     /*******************************************************************************************/
     
    public void mostrarProvincias2(ValueChangeEvent vcl) {
        vcl.getComponent().processUpdates(FacesContext.getCurrentInstance());
        String newValue = vcl.getNewValue().toString();
        beanSessionRegistrarPreFactura.setProvincias2(this.fillListUbigeo(2, newValue, null));
        if(beanSessionRegistrarPreFactura.getDistritos2() != null){
            beanSessionRegistrarPreFactura.getDistritos2().removeAll(beanSessionRegistrarPreFactura.getDistritos2());
        }
        beanSessionRegistrarPreFactura.setUbigeoDest(newValue);
        beanSessionRegistrarPreFactura.setDep2(Utils.getChoiceLabel(vcl));
        beanSessionRegistrarPreFactura.setProv2(null);
        beanSessionRegistrarPreFactura.setDist2(null);
        beanSessionRegistrarPreFactura.setDestino(beanSessionRegistrarPreFactura.generarDestino());
        itDestino.setValue(beanSessionRegistrarPreFactura.getDestino());
        Utils.addTargetMany(socProv_2,socDist_2,itDestino);
    }

    public void mostrarDistritos2(ValueChangeEvent vcl) {
        vcl.getComponent().processUpdates(FacesContext.getCurrentInstance());
        String newValue = vcl.getNewValue().toString();
        beanSessionRegistrarPreFactura.setUbiPro2(newValue);
        beanSessionRegistrarPreFactura.setDistritos2(this.fillListUbigeo(3, null, newValue));
        beanSessionRegistrarPreFactura.setProv2(Utils.getChoiceLabel(vcl));
        beanSessionRegistrarPreFactura.setDist2(null);
        beanSessionRegistrarPreFactura.setDestino(beanSessionRegistrarPreFactura.generarDestino());
        itDestino.setValue(beanSessionRegistrarPreFactura.getDestino());
        Utils.addTargetMany(socDist_2,itDestino);
    }
    
    public void cambioDist2(ValueChangeEvent vce) {
        vce.getComponent().processUpdates(FacesContext.getCurrentInstance());
        String newValue = vce.getNewValue().toString();
        beanSessionRegistrarPreFactura.setDist2(Utils.getChoiceLabel(vce));
        beanSessionRegistrarPreFactura.setUbiDis2(newValue);
        beanSessionRegistrarPreFactura.setDestino(beanSessionRegistrarPreFactura.generarDestino());
        itDestino.setValue(beanSessionRegistrarPreFactura.getDestino());
        Utils.addTargetMany(itDestino);
    }
    
    public void abrirDestinos(ActionEvent actionEvent) {
        beanSessionRegistrarPreFactura.setDepartamentos(this.fillListUbigeo(1, null, null));
        beanSessionRegistrarPreFactura.setDepartamentos2(this.fillListUbigeo(1, null, null));
        if(beanSessionRegistrarPreFactura.getUbiDep() != null){
            if(beanSessionRegistrarPreFactura.getItemToEditar().getCliente() != null){
                beanSessionRegistrarPreFactura.setProvincias(this.fillListUbigeo(2,beanSessionRegistrarPreFactura.getUbiDep(), null));
                beanSessionRegistrarPreFactura.setUbiDep(beanSessionRegistrarPreFactura.getUbiDep().substring(0,2)+"0000");
                beanSessionRegistrarPreFactura.setDistritos(this.fillListUbigeo(3, null,beanSessionRegistrarPreFactura.getItemToEditar().getUbigeoOrigenProv()));
                beanSessionRegistrarPreFactura.setUbiPro(beanSessionRegistrarPreFactura.getItemToEditar().getUbigeoOrigenProv());
                beanSessionRegistrarPreFactura.setUbiDis(beanSessionRegistrarPreFactura.getItemToEditar().getUbigeoOrigenDist());
            }else{
                beanSessionRegistrarPreFactura.setProvincias(this.fillListUbigeo(2,beanSessionRegistrarPreFactura.getUbiDep(), null));
                beanSessionRegistrarPreFactura.setDistritos(this.fillListUbigeo(3, null,beanSessionRegistrarPreFactura.getUbiPro()));
            }
            if(socProv_1 != null){
                socProv_1.setValue(beanSessionRegistrarPreFactura.getUbiPro());
                socDist_1.setValue(beanSessionRegistrarPreFactura.getUbiDis());
            }
        }
        if(beanSessionRegistrarPreFactura.getUbiDep2() != null){
            if(beanSessionRegistrarPreFactura.getItemToEditar().getCliente() != null){
                beanSessionRegistrarPreFactura.setProvincias2(this.fillListUbigeo(2,beanSessionRegistrarPreFactura.getUbiDep2(), null));
                beanSessionRegistrarPreFactura.setUbiDep2(beanSessionRegistrarPreFactura.getUbiDep2().substring(0,2)+"0000");
                beanSessionRegistrarPreFactura.setDistritos2(this.fillListUbigeo(3, null,beanSessionRegistrarPreFactura.getItemToEditar().getUbigeoDestinoProv()));
                beanSessionRegistrarPreFactura.setUbiPro2(beanSessionRegistrarPreFactura.getItemToEditar().getUbigeoDestinoProv());
                beanSessionRegistrarPreFactura.setUbiDis2(beanSessionRegistrarPreFactura.getItemToEditar().getUbigeoDestinoDist());
            }else{
                beanSessionRegistrarPreFactura.setProvincias2(this.fillListUbigeo(2,beanSessionRegistrarPreFactura.getUbiDep2(), null));
                beanSessionRegistrarPreFactura.setDistritos2(this.fillListUbigeo(3, null,beanSessionRegistrarPreFactura.getUbiPro2())); 
            }
            if(socDist_1 != null){
                socProv_2.setValue(beanSessionRegistrarPreFactura.getUbiPro2());
                socDist_2.setValue(beanSessionRegistrarPreFactura.getUbiDis2());
            }
        }
        refreshSocs();
        Utils.showPopUp(popDestinos,btnEditDestino);
    }
    
    public void agregarItm(ActionEvent actionEvent){
        if(!validar()){
            return;
        }
        try {
            BeanItemPreFactura beanItm = new BeanItemPreFactura();
            if (beanSessionRegistrarPreFactura.getTipEvento() == 2) {
                beanItm = beanSessionRegistrarPreFactura.getItemToEditar();
            } else {
                int orden = 1;
                if (beanSessionRegistrarPreFactura.getLstBeanItemPreFactura() != null) {
                    if (beanSessionRegistrarPreFactura.getLstBeanItemPreFactura().size() >= 1) {
                        orden = beanSessionRegistrarPreFactura.getLstBeanItemPreFactura().size() + 1;
                    }
                }
                beanItm.setOrden(orden);
            }
            beanItm.setDestino(beanSessionRegistrarPreFactura.getDestino());
            beanItm.setSubtotal(beanSessionRegistrarPreFactura.getSubTotal());
            beanItm.setIgvSubtotal(beanSessionRegistrarPreFactura.getSubTotal().multiply(new BigDecimal(.18)));
            beanItm.setTotalItem(beanItm.getSubtotal().add(beanItm.getIgvSubtotal()));
            beanItm.setCliente(beanSessionRegistrarPreFactura.getRazonCliente());
            String guiasConcat = "";
            String itmsConcat = "";
            for (int i = 0; i < beanSessionRegistrarPreFactura.getLstGuiasInCombo().size(); i++) {
                BeanTRGuia guia = beanSessionRegistrarPreFactura.getLstGuiasInCombo().get(i);
                guiasConcat = guiasConcat.concat(guia.getCidGuia());
                if (i < beanSessionRegistrarPreFactura.getLstGuiasInCombo().size() - 1) {
                    guiasConcat = guiasConcat.concat(",");
                }
                for (int j = 0; j < guia.getItemsLista().size(); j++) {
                    BeanTRItem item = guia.getItemsLista().get(j);
                    String cidGRemi = item.getCCidGuiaRemitente() == null ? "" : item.getCCidGuiaRemitente();
                    String desc = item.getNCantidad() + " - " + item.getCUndMedida() + " - " + item.getCDescItem() + " - " + cidGRemi;
                    itmsConcat = itmsConcat.concat(desc);
                    if (i < guia.getItemsLista().size() - 1) {
                        itmsConcat = itmsConcat.concat(",");
                    }
                }
            }
            String backup_Dep1 = beanSessionRegistrarPreFactura.getDep1();
            String backup_Dep2 = beanSessionRegistrarPreFactura.getDep2();
            beanItm.setDescDepOrigen(backup_Dep1);
            beanItm.setDescDepDestino(backup_Dep2);
            beanItm.setUbigeoOrigen(beanSessionRegistrarPreFactura.getUbiDep());
            beanItm.setUbigeoDestino(beanSessionRegistrarPreFactura.getUbiDep2());
            if (beanSessionRegistrarPreFactura.getUbiPro() != null) {
                beanItm.setUbigeoOrigenProv(beanSessionRegistrarPreFactura.getUbiPro());
            }
            if (beanSessionRegistrarPreFactura.getUbiPro2() != null) {
                beanItm.setUbigeoDestinoProv(beanSessionRegistrarPreFactura.getUbiPro2());
            }
            if (beanSessionRegistrarPreFactura.getUbiDis() != null) {
                beanItm.setUbigeoOrigenDist(beanSessionRegistrarPreFactura.getUbiDis());
            }
            if (beanSessionRegistrarPreFactura.getUbiDis2() != null) {
                beanItm.setUbigeoDestinoDist(beanSessionRegistrarPreFactura.getUbiDis2());
            }
            beanItm.setGuiasLista(clonarGuias(beanSessionRegistrarPreFactura.getLstGuiasInCombo()));
            beanItm.setGuiasConcat(guiasConcat);
            beanItm.setGuiasItmsConcat(itmsConcat);
            if (beanSessionRegistrarPreFactura.getTipEvento() == 1) {
                BeanItemPreFactura ittm = beanItm.clonar();
                beanSessionRegistrarPreFactura.getLstBeanItemPreFactura().add(ittm);
            }
            beanSessionRegistrarPreFactura.setTipEvento(1);
            btnBorrarItm.setDisabled(false);
            resetearForm();
            Utils.unselectFilas(tbItms);
            Utils.addTargetMany(tbItms,btnBorrarItm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void nuevoItem(ActionEvent actionEvent) {
        nuevoItemAux();
    }
    
    public void nuevoItemAux(){
        if(beanSessionRegistrarPreFactura.getLstGuiasInCombo() != null){
            if(!beanSessionRegistrarPreFactura.getLstGuiasInCombo().isEmpty()){
                if(beanSessionRegistrarPreFactura.getItemToEditar() != null){
                    if(beanSessionRegistrarPreFactura.getTipEvento() == 1){
                        beanSessionRegistrarPreFactura.getLstGuias().addAll(beanSessionRegistrarPreFactura.getLstGuiasInCombo());
                    }
                }
            }
        }
        resetearForm();
        beanSessionRegistrarPreFactura.setTipEvento(1);//Insertar
        Utils.unselectFilas(tbItms);
    }
    
    public List<BeanTRGuia> clonarGuias(List<BeanTRGuia> guias){
        List<BeanTRGuia> listaClons = new ArrayList<BeanTRGuia>();
        for(BeanTRGuia g : guias){
            BeanTRGuia clon = (BeanTRGuia) g.clone();
            listaClons.add(clon);
        }
        return listaClons;
    }
    
    public void selectItemPreFactura(SelectionEvent selectionEvent) {
        beanSessionRegistrarPreFactura.setTipEvento(2);//Modificar
        RichTable t = (RichTable) selectionEvent.getSource();
        BeanItemPreFactura beanItm = (BeanItemPreFactura) t.getSelectedRowData();
        beanSessionRegistrarPreFactura.setLstGuiasInCombo(clonarGuias(beanItm.getGuiasLista()));
        beanSessionRegistrarPreFactura.setSubTotal(beanItm.getSubtotal());
        itSubTotal.setValue(beanSessionRegistrarPreFactura.getSubTotal());
        beanSessionRegistrarPreFactura.setDestino(beanItm.getDestino());
        itDestino.setValue(beanItm.getDestino());
        beanSessionRegistrarPreFactura.setUbiDep(beanItm.getUbigeoOrigen());
        beanSessionRegistrarPreFactura.setUbiDep2(beanItm.getUbigeoDestino());
        beanSessionRegistrarPreFactura.setDep1(beanItm.getDescDepOrigen());
        beanSessionRegistrarPreFactura.setDep2(beanItm.getDescDepDestino());
        if(beanItm.getUbigeoOrigenProv() != null){
            beanSessionRegistrarPreFactura.setUbiPro(beanItm.getUbigeoOrigenProv());    
        }
        if(beanItm.getUbigeoOrigenDist() != null){
            beanSessionRegistrarPreFactura.setUbiDis(beanItm.getUbigeoOrigenDist());
        }
        if(beanItm.getUbigeoDestinoProv() != null){
            beanSessionRegistrarPreFactura.setUbiPro2(beanItm.getUbigeoDestinoProv());   
        }
        if(beanItm.getUbigeoDestinoDist() != null){
            beanSessionRegistrarPreFactura.setUbiDis2(beanItm.getUbigeoDestinoDist());   
        }
        beanSessionRegistrarPreFactura.setGuiasCombo(this.llenarGuias(beanSessionRegistrarPreFactura.getLstGuiasInCombo()));
        btnBorrarItm.setDisabled(false);
        Utils.addTargetMany(socGuias,itDestino,itSubTotal,btnAddItm,btnBorrarItm);
        beanSessionRegistrarPreFactura.setItemToEditar(beanItm);
    }
    
    public BeanItemPreFactura getItmById(BeanItemPreFactura itm){
        Iterator it = beanSessionRegistrarPreFactura.getLstBeanItemPreFactura().iterator();
        while(it.hasNext()){
            BeanItemPreFactura item = (BeanItemPreFactura) it.next();
            if(item.getOrden() == itm.getOrden()){
                return item;
            }
        }
        return null;
    }
    
    public void borrar_item(ActionEvent actionEvent) {
        if(beanSessionRegistrarPreFactura.getItemToEditar() != null){
            beanSessionRegistrarPreFactura.getLstGuias().addAll(beanSessionRegistrarPreFactura.getLstGuiasInCombo());
            int ordenBorrar = beanSessionRegistrarPreFactura.getItemToEditar().getOrden();
            int size = beanSessionRegistrarPreFactura.getLstBeanItemPreFactura().size();
            if(ordenBorrar < size){
                if(ordenBorrar > 1){
                    for(BeanItemPreFactura itm : beanSessionRegistrarPreFactura.getLstBeanItemPreFactura()){
                        if(itm.getOrden() > 1){
                            itm.setOrden(itm.getOrden() - 1);    
                        }
                    }
                }else{
                    for(BeanItemPreFactura itm : beanSessionRegistrarPreFactura.getLstBeanItemPreFactura()){
                            itm.setOrden(itm.getOrden() - 1);
                    }
                }
            }
            beanSessionRegistrarPreFactura.getLstBeanItemPreFactura().remove(beanSessionRegistrarPreFactura.getItemToEditar());
            beanSessionRegistrarPreFactura.setItemToEditar(null);
            resetearForm();
            btnBorrarItm.setDisabled(true);
            beanSessionRegistrarPreFactura.setTipEvento(1);
            Utils.addTargetMany(tbItms,btnBorrarItm,btnAddItm);
        }
    }
    
    public void resetearForm(){
        beanSessionRegistrarPreFactura.getLstGuiasInCombo().removeAll(beanSessionRegistrarPreFactura.getLstGuiasInCombo());
        socGuias.resetValue();
        beanSessionRegistrarPreFactura.getGuiasCombo().removeAll(beanSessionRegistrarPreFactura.getGuiasCombo());
        siGuias.setValue(null);
        beanSessionRegistrarPreFactura.setDestino("");
        itDestino.resetValue();
        itDestino.setValue("");
        itSubTotal.resetValue();
        itSubTotal.setValue(null);
        beanSessionRegistrarPreFactura.setSubTotal(null);
        beanSessionRegistrarPreFactura.setGuiaSelectedBorrar(null);
        beanSessionRegistrarPreFactura.setGuiaSelected(null);
        beanSessionRegistrarPreFactura.setUbigeoRemite(null);
        if(socDept_1 != null){
            socDept_1.resetValue();
            socDept_2.resetValue();
        }
        beanSessionRegistrarPreFactura.setUbiDep(null);
        beanSessionRegistrarPreFactura.setUbiPro(null);
        beanSessionRegistrarPreFactura.setUbiDis(null);
        beanSessionRegistrarPreFactura.setUbiDep2(null);
        beanSessionRegistrarPreFactura.setUbiPro2(null);
        beanSessionRegistrarPreFactura.setUbiDis2(null);
        beanSessionRegistrarPreFactura.setDep1(null);
        beanSessionRegistrarPreFactura.setDep2(null);
        beanSessionRegistrarPreFactura.setProv1(null);
        beanSessionRegistrarPreFactura.setProv2(null);
        beanSessionRegistrarPreFactura.setDist2(null);
        beanSessionRegistrarPreFactura.setDist1(null);
        if(beanSessionRegistrarPreFactura.getDistritos2() != null){
            beanSessionRegistrarPreFactura.getDistritos2().removeAll(beanSessionRegistrarPreFactura.getDistritos2());
        }
        if(beanSessionRegistrarPreFactura.getDistritos() != null){
            beanSessionRegistrarPreFactura.getDistritos().removeAll(beanSessionRegistrarPreFactura.getDistritos());
        }
        if(beanSessionRegistrarPreFactura.getProvincias() != null){
            beanSessionRegistrarPreFactura.getProvincias().removeAll(beanSessionRegistrarPreFactura.getProvincias());
        }
        if(beanSessionRegistrarPreFactura.getProvincias2() != null){
            beanSessionRegistrarPreFactura.getProvincias2().removeAll(beanSessionRegistrarPreFactura.getProvincias2());
        }
        if(socProv_1 != null){
            socProv_1.resetValue();
            socProv_2.resetValue();
            socDist_1.resetValue();
            socDist_1.resetValue();
        }
        refreshSocs();
        Utils.addTargetMany(socGuias,itDestino,itSubTotal,itCodPed);
    }
    
    public void refreshSocs(){
        if(socDept_1 != null){
            Utils.addTargetMany(socDept_1,socDept_2);
        }
        if(socProv_1 != null){
            Utils.addTargetMany(socProv_1,socProv_2);
        }
        if(socDist_1 != null){
            Utils.addTargetMany(socDist_1,socDist_2);
        }
    }
    
    private boolean validar(){
        boolean isOk = true;
        if(beanSessionRegistrarPreFactura.getRazonCliente() == null){
            Utils.throwError_Aux(ctx, "Seleccione la empresa.",4);
            isOk = false;
        }else{
            if("".equals(beanSessionRegistrarPreFactura.getRazonCliente())){
                Utils.throwError_Aux(ctx, "Seleccione la empresa.",4);
                isOk = false;
            }
        }
        if(beanSessionRegistrarPreFactura.getDestino() == null){
            Utils.throwError_Aux(ctx, "Debe seleccionar el destino.",4);
            isOk = false;
        }else if("".equals(beanSessionRegistrarPreFactura.getDestino())){
            Utils.throwError_Aux(ctx, "Debe seleccionar el destino.",4);
            isOk = false;
        }
        String msjNoGuias = "Asigne las Guias, como minimo 1";
        if(beanSessionRegistrarPreFactura.getLstGuiasInCombo() == null){
            Utils.throwError_Aux(ctx,msjNoGuias,4);
            isOk = false;
        }else{
            if(beanSessionRegistrarPreFactura.getLstGuiasInCombo().isEmpty()){
                Utils.throwError_Aux(ctx,msjNoGuias,4);
                isOk = false;
            }
        }
        return isOk;
    }
    
    private boolean validarPersist(){
        boolean isOk = true;
        String msj = "Agregue Items de Pre-Factura";
        if(beanSessionRegistrarPreFactura.getRazonCliente() == null){
            Utils.throwError_Aux(ctx,"Seleccione la empresa a Pre-Facturar",4);
            isOk = false;
            return isOk;
        }else{
            if("".equals(beanSessionRegistrarPreFactura.getRazonCliente())){
                Utils.throwError_Aux(ctx,"Seleccione la empresa a Pre-Facturar",4);
                isOk = false;
                return isOk;
            }   
        }
        if(beanSessionRegistrarPreFactura.getLstBeanItemPreFactura() == null){
            Utils.throwError_Aux(ctx,msj,4);
            isOk = false;
            return isOk;
        }else{
            if(beanSessionRegistrarPreFactura.getLstBeanItemPreFactura().isEmpty()){
                Utils.throwError_Aux(ctx,msj,4);
                isOk = false;
            }
        }
        return isOk;
    }
    
    public void grabarPreFactura(ActionEvent actionEvent) {
        try {
            if (validarPersist()) {
                BeanPreFactura bPreFac = new BeanPreFactura();
                if(beanSessionRegistrarPreFactura.getTipEventoGlobal() == 1){
                        bPreFac = ln_T_SFPreFacturaRemote.registrarBeanPreFactura_LN(beanSessionRegistrarPreFactura.getLstBeanItemPreFactura(),
                                                                                     beanSessionRegistrarPreFactura.getNidClie().longValue(),
                                                                                     beanSessionRegistrarPreFactura.getCodPedido(),
                                                                                     beanSessionRegistrarPreFactura.getRazonCliente().toUpperCase());
                }else{
                    bPreFac = ln_T_SFPreFacturaRemote.actualizarBeanPreFactura_LN(beanSessionRegistrarPreFactura.getLstBeanItemPreFactura(),
                                                                                  beanSessionRegistrarPreFactura.getCodPedido(),
                                                                                  beanSessionRegistrarPreFactura.getRazonCliente().toUpperCase(),
                                                                                  beanSessionRegistrarPreFactura.getNidPreFactura());
                }
                if(bPreFac.getBeanError() != null){
                    BeanError error = bPreFac.getBeanError();
                    int severidad = 0;
                    if("000".equals(error.getCidError())){
                        severidad = 3;
                        Utils.depurar("Grabo la pre-factura");
                        if(Utils.hasPermiso(beanUsuario.getLstPermisos(),new BigDecimal("54"))){
                            Utils._redireccionar(ctx,"WEB-INF/consultar_prefactura.xml#consultar_prefactura");
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
            }
        } catch (Exception e) {
            Utils.throwError_Aux(ctx,"Error Inesperado", 1);
        }
    }
    
    public void setOtTitulo(RichOutputText ot1) {
        this.otTitulo = ot1;
    } 

    public RichOutputText getOtTitulo() {
        return otTitulo;
    }

    public void setItClie(RichInputText it1) {
        this.itClie = it1;
    }

    public RichInputText getItClie() {
        return itClie;
    }

    public void setBtnBuscClie(RichCommandButton cb1) {
        this.btnBuscClie = cb1;
    }

    public RichCommandButton getBtnBuscClie() {
        return btnBuscClie;
    }

    public void setBeanSessionRegistrarPreFactura(SessionScopeBeanRegistrarPreFactura beanSessionRegistrarPreFactura) {
        this.beanSessionRegistrarPreFactura = beanSessionRegistrarPreFactura;
    }

    public SessionScopeBeanRegistrarPreFactura getBeanSessionRegistrarPreFactura() {
        return beanSessionRegistrarPreFactura;
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

    public void setItClieRaz(RichInputText it1) {
        this.itClieRaz = it1;
    }

    public RichInputText getItClieRaz() {
        return itClieRaz;
    }

    public void setBtnSearchClie(RichCommandButton cb1) {
        this.btnSearchClie = cb1;
    }

    public RichCommandButton getBtnSearchClie() {
        return btnSearchClie;
    }

    public void setTbClie(RichTable t1) {
        this.tbClie = t1;
    }

    public RichTable getTbClie() {
        return tbClie;
    }

    public void setTabGuia(RichTable t1) {
        this.tabGuia = t1;
    }

    public RichTable getTabGuia() {
        return tabGuia;
    }

    public void setBtnAddGuia(RichCommandButton cb1) {
        this.btnAddGuia = cb1;
    }

    public RichCommandButton getBtnAddGuia() {
        return btnAddGuia;
    }

    public void setBtnVerItms(RichCommandButton cb1) {
        this.btnVerItms = cb1;
    }

    public RichCommandButton getBtnVerItms() {
        return btnVerItms;
    }

    public void setSocGuias(RichSelectOneChoice soc1) {
        this.socGuias = soc1;
    }

    public RichSelectOneChoice getSocGuias() {
        return socGuias;
    }

    public void setSiGuias(UISelectItems si1) {
        this.siGuias = si1;
    }

    public UISelectItems getSiGuias() {
        return siGuias;
    }

    public void setBtnEditarGuia(RichCommandButton cb1) {
        this.btnEditarGuia = cb1;
    }

    public RichCommandButton getBtnEditarGuia() {
        return btnEditarGuia;
    }

    public void setItSubTotal(RichInputText it1) {
        this.itSubTotal = it1;
    }

    public RichInputText getItSubTotal() {
        return itSubTotal;
    }

    public void setItDestino(RichInputText it1) {
        this.itDestino = it1;
    }

    public RichInputText getItDestino() {
        return itDestino;
    }

    public void setBtnEditDestino(RichCommandButton cb1) {
        this.btnEditDestino = cb1;
    }

    public RichCommandButton getBtnEditDestino() {
        return btnEditDestino;
    }

    public void setPopGuiasCombo(RichPopup p1) {
        this.popGuiasCombo = p1;
    }

    public RichPopup getPopGuiasCombo() {
        return popGuiasCombo;
    }

    public void setBtnBorrar(RichCommandButton cb1) {
        this.btnBorrar = cb1;
    }

    public RichCommandButton getBtnBorrar() {
        return btnBorrar;
    }

    public void setTbGuiasCombo(RichTable t1) {
        this.tbGuiasCombo = t1;
    }

    public RichTable getTbGuiasCombo() {
        return tbGuiasCombo;
    }

    public void setPopDestinos(RichPopup p1) {
        this.popDestinos = p1;
    }

    public RichPopup getPopDestinos() {
        return popDestinos;
    }

    public void setSocDept_1(RichSelectOneChoice soc1) {
        this.socDept_1 = soc1;
    }

    public RichSelectOneChoice getSocDept_1() {
        return socDept_1;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public UISelectItems getSi1() {
        return si1;
    }

    public void setSocProv_1(RichSelectOneChoice soc1) {
        this.socProv_1 = soc1;
    }

    public RichSelectOneChoice getSocProv_1() {
        return socProv_1;
    }

    public void setSi2(UISelectItems si2) {
        this.si2 = si2;
    }

    public UISelectItems getSi2() {
        return si2;
    }

    public void setSocDist_1(RichSelectOneChoice soc1) {
        this.socDist_1 = soc1;
    }

    public RichSelectOneChoice getSocDist_1() {
        return socDist_1;
    }

    public void setSi3(UISelectItems si3) {
        this.si3 = si3;
    }

    public UISelectItems getSi3() {
        return si3;
    }

    public void setSocDept_2(RichSelectOneChoice soc1) {
        this.socDept_2 = soc1;
    }

    public RichSelectOneChoice getSocDept_2() {
        return socDept_2;
    }

    public void setSi4(UISelectItems si4) {
        this.si4 = si4;
    }

    public UISelectItems getSi4() {
        return si4;
    }

    public void setSocProv_2(RichSelectOneChoice soc1) {
        this.socProv_2 = soc1;
    }

    public RichSelectOneChoice getSocProv_2() {
        return socProv_2;
    }

    public void setSi5(UISelectItems si5) {
        this.si5 = si5;
    }

    public UISelectItems getSi5() {
        return si5;
    }

    public void setSocDist_2(RichSelectOneChoice soc1) {
        this.socDist_2 = soc1;
    }

    public RichSelectOneChoice getSocDist_2() {
        return socDist_2;
    }

    public void setSi6(UISelectItems si6) {
        this.si6 = si6;
    }

    public UISelectItems getSi6() {
        return si6;
    }

    public void setBtnAddItm(RichCommandButton cb1) {
        this.btnAddItm = cb1;
    }

    public RichCommandButton getBtnAddItm() {
        return btnAddItm;
    }

    public void setTbItms(RichTable t1) {
        this.tbItms = t1;
    }

    public RichTable getTbItms() {
        return tbItms;
    }

    public void setBtnExportar(RichCommandButton cb1) {
        this.btnExportar = cb1;
    }

    public RichCommandButton getBtnExportar() {
        return btnExportar;
    }

    public void setBtnNew(RichCommandButton cb1) {
        this.btnNew = cb1;
    }

    public RichCommandButton getBtnNew() {
        return btnNew;
    }

    public void setBtnBorrarItm(RichCommandButton cb1) {
        this.btnBorrarItm = cb1;
    }

    public RichCommandButton getBtnBorrarItm() {
        return btnBorrarItm;
    }

    public void setItCodPed(RichInputText it1) {
        this.itCodPed = it1;
    }

    public RichInputText getItCodPed() {
        return itCodPed;
    }

    public void setBtnPersistItms(RichCommandButton cb1) {
        this.btnPersistItms = cb1;
    }

    public RichCommandButton getBtnPersistItms() {
        return btnPersistItms;
    }
}
