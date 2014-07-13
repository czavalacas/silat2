package siat.view.backing.administrativo.configuracion;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.naming.Context;
import javax.naming.InitialContext;
import oracle.adf.view.rich.component.rich.RichSubform;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectItem;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.adf.view.rich.component.rich.nav.RichCommandButton;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import siat.view.backing.utiles.Utils;

import silat.servicios_negocio.Beans.BeanCodigo;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanUnidadNegocio;
import silat.servicios_negocio.LNSF.IR.LN_C_SFUnidadNegocioRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFUtilsRemote;
import silat.servicios_negocio.LNSF.IR.LN_T_SFCodigoRemote;

public class Frm_configurar_codigos {
    private RichSubform sfConfC;
    private RichSelectOneChoice socTipDoc;
    private RichSelectItem siTipDoc1;
    private RichSelectItem siTipDoc2;
    private RichInputText itCodigo;
    private RichInputText itCod;
    private SessionScopedBeanConfigurarCodigos beanSessionConfigCodigos;
    FacesContext ctx = FacesContext.getCurrentInstance();
    private LN_C_SFUtilsRemote LN_C_SFUtilsRemote;
    private LN_T_SFCodigoRemote ln_T_SFCodigoRemote;
    private final static String LOOKUP_NAME_SFUTILS = "mapLN_C_SFUtils";
    private final static String LOOKUP_NAME_SFT_CODIGO = "mapLN_T_SFCodigo";
    private RichTable tbCodig;
    private RichInputText itSerie;
    private RichCommandButton btnActualizar;

    public Frm_configurar_codigos(){
        try{
            final Context ctx;
            ctx = new InitialContext();
            LN_C_SFUtilsRemote = (LN_C_SFUtilsRemote) ctx.lookup(LOOKUP_NAME_SFUTILS);
            ln_T_SFCodigoRemote = (LN_T_SFCodigoRemote) ctx.lookup(LOOKUP_NAME_SFT_CODIGO);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @PostConstruct 
    public void methodOnPostConstruct(){
        if(beanSessionConfigCodigos.getExec() == 0){
            beanSessionConfigCodigos.setExec(1);
            beanSessionConfigCodigos.setLstCodigos(this.LN_C_SFUtilsRemote.getCodigos());
        }else{
            beanSessionConfigCodigos.setLstCodigos(this.LN_C_SFUtilsRemote.getCodigos());
        }
    }
    
    
    public void selectCodigo(SelectionEvent selectionEvent) {
        RichTable t = (RichTable)selectionEvent.getSource();
        BeanCodigo beanCodigo = (BeanCodigo) t.getSelectedRowData();
        beanSessionConfigCodigos.setCodUN(beanCodigo.getCidunin());
        beanSessionConfigCodigos.setCodigo(beanCodigo.getCodigo());
        beanSessionConfigCodigos.setDoc(beanCodigo.getTipdoc());
        Utils.addTargetMany(socTipDoc,itCod,itSerie);
    }
    
    public String btnActualizar_action() {
        String codigo = beanSessionConfigCodigos.getCodigo();
        int iCodigo = 0;
        try{
            iCodigo = Integer.parseInt(codigo);
        }catch(Exception e){
            Utils.throwError_Aux(ctx,"Escriba solo numeros.",4);
            return null;
        }
        String cidUnin = beanSessionConfigCodigos.getCodUN();
        String code = beanSessionConfigCodigos.getCodigo();
        String tipDoc = beanSessionConfigCodigos.getDoc();
        if(cidUnin == null || tipDoc == null){
            Utils.throwError_Aux(ctx,"Llene todos los campos",4);
            return null;
        }
        if(cidUnin == null && tipDoc == null){
            Utils.throwError_Aux(ctx,"Llene todos los campos",4);
            return null;
        }
        BeanCodigo bCodigo = ln_T_SFCodigoRemote.actualizarCodigo(cidUnin,code,tipDoc);
        if(bCodigo.getBeanError() != null){
            BeanError error = bCodigo.getBeanError();
            if(error != null){
                int severidad = 3;
                if(error.getCidError().equals("000")){
                    beanSessionConfigCodigos.setLstCodigos(this.LN_C_SFUtilsRemote.getCodigos());
                    tbCodig.setValue(beanSessionConfigCodigos.getLstCodigos());
                    Utils.unselectFilas(tbCodig);
                    beanSessionConfigCodigos.setCodUN(null);
                    beanSessionConfigCodigos.setCodigo(null);
                    beanSessionConfigCodigos.setDoc(null);
                    itCod.resetValue();
                    itSerie.resetValue();
                    socTipDoc.resetValue();
                    Utils.addTargetMany(itCod,itSerie,socTipDoc,tbCodig);
                }else{
                    severidad = 1;    
                }
                Utils.throwError_Aux(ctx,error.getCDescripcionError(),severidad);
            }else{
                Utils.throwError_Aux(ctx,"Error Inesperado.",1);
            }
        }else{
            Utils.throwError_Aux(ctx,"Error Inesperado.",1);
        }
        return null;
    }
    
    public void setSfConfC(RichSubform s1) {
        this.sfConfC = s1;
    }

    public RichSubform getSfConfC() {
        return sfConfC;
    }

    public void setSocTipDoc(RichSelectOneChoice soc1) {
        this.socTipDoc = soc1;
    }

    public RichSelectOneChoice getSocTipDoc() { 
        return socTipDoc;
    }

    public void setSiTipDoc1(RichSelectItem si1) {
        this.siTipDoc1 = si1;
    }

    public RichSelectItem getSiTipDoc1() {
        return siTipDoc1;
    }

    public void setSiTipDoc2(RichSelectItem si2) {
        this.siTipDoc2 = si2;
    }

    public RichSelectItem getSiTipDoc2() {
        return siTipDoc2;
    }

    public void setBeanSessionConfigCodigos(SessionScopedBeanConfigurarCodigos beanSessionConfigCodigos) {
        this.beanSessionConfigCodigos = beanSessionConfigCodigos;
    }

    public SessionScopedBeanConfigurarCodigos getBeanSessionConfigCodigos() {
        return beanSessionConfigCodigos;
    }

    public void setItCodigo(RichInputText itCodigo) {
        this.itCodigo = itCodigo;
    }

    public RichInputText getItCodigo() {
        return itCodigo;
    }

    public void setItCod(RichInputText it1) {
        this.itCod = it1;
    }

    public RichInputText getItCod() {
        return itCod;
    }

    public void setTbCodig(RichTable t2) {
        this.tbCodig = t2;
    }

    public RichTable getTbCodig() {
        return tbCodig;
    }

    public void setItSerie(RichInputText it1) {
        this.itSerie = it1;
    }

    public RichInputText getItSerie() {
        return itSerie;
    }

    public void setBtnActualizar(RichCommandButton cb1) {
        this.btnActualizar = cb1;
    }

    public RichCommandButton getBtnActualizar() {
        return btnActualizar;
    }
}
