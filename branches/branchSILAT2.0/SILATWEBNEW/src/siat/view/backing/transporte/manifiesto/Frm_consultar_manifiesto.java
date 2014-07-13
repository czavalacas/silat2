package siat.view.backing.transporte.manifiesto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
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
import oracle.adf.view.rich.component.rich.layout.RichDecorativeBox;
import oracle.adf.view.rich.component.rich.layout.RichGridCell;
import oracle.adf.view.rich.component.rich.layout.RichGridRow;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGridLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.model.SortCriterion;
import oracle.adf.view.rich.render.ClientEvent;

import org.apache.myfaces.trinidad.component.core.data.CoreTable;
import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.event.SortEvent;

import siat.view.backing.utiles.Utils;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanManifiesto;
import silat.servicios_negocio.Beans.BeanTRGuia;
import silat.servicios_negocio.Beans.BeanUsuarioAutenticado;
import silat.servicios_negocio.LNSF.IL.LN_T_SFManifiestoRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFGuiaRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFManifiestoRemote;

public class Frm_consultar_manifiesto {
    private RichDecorativeBox db1;
    private RichOutputLabel olTitulo;
    private RichPanelGroupLayout pgl1;
    private RichSubform s1;
    private RichPanelFormLayout pfl1;
    private RichTable tbManif;
    private RichInputDate fecMin;
    private RichInputDate fecMax;
    private RichInputText idManif;
    private RichInputText empProv;
    private RichInputText flete;
    private RichInputText adel;
    private RichInputText observ;
    private RichPanelGridLayout pgl2;
    private RichGridRow gr1;
    private RichGridCell gc1;
    private RichGridCell gc2;
    private RichGridRow gr2;
    private RichGridCell gc3;
    private RichGridCell gc4;
    private RichSelectOneChoice socCantFlete;
    private RichSelectItem si1;
    private RichSelectItem si2;
    private RichSelectItem si3;
    private RichSelectOneChoice socCantAdel;
    private RichSelectItem si4;
    private RichSelectItem si5;
    private RichSelectItem si6;
    private RichCommandButton btnBuscar;
    private RichCommandButton btnLimpiar;
    private RichCommandButton btnOrdFec;
    private RichCommandButton btnOrdFlete;
    private RichCommandButton btnOrdAdel;
    private RichCommandButton btnProv;
    private RichCommandButton btnObs;
    private RichPopup popGuias;
    private RichDialog diaGuias;
    private RichCommandButton btnGuias;
    private RichCommandButton btnEditar;
    private RichTable t2;
    private RichSelectOneChoice socEstados;
    private UISelectItems si7;
    private RichPopup popEditarManif;
    private RichPopup diaAnular;
    private RichDialog d1;
    private RichSubform s2;
    private RichInputText itFlete;
    private RichPanelFormLayout pfl2;
    private RichInputDate fecManif;
    private RichInputText itAdelanto;
    private RichInputText itSaldo;
    private RichInputText itIGV;
    private RichInputText itMontoFin;
    private RichSelectOneChoice socTipDoc;
    private UISelectItems si8;
    private RichInputText itObs;
    private RichSelectItem si9;
    private RichSelectOneChoice socEstdos;
    private UISelectItems si10;
    private RichInputText itDetraccion;
    private RichCommandButton btnAnularManif;
    private RichSelectOneChoice socEstManif;
    private RichSelectItem si12;
    private RichSelectItem si11;
    private RichCommandButton cb1;
    private RichCommandButton btnFecha;
    private RichPopup popFecManif;
    private RichInputDate fecMa;
    /*--------Mis Variables------------*/
    private final static String LOOKUP_NAME_SFCMANIF_REMOTO     = "mapLN_C_SFManifiesto";
    private final static String LOOKUP_NAME_SFCGUIA_REMOTO      = "mapLN_C_SFGuia";
    private final static String LOOKUP_NAME_SFMANIFIESTO_REMOTO = "mapLN_T_SFManifiesto";
    private LN_C_SFManifiestoRemote ln_C_SFManifiestoRemote;
    private LN_C_SFGuiaRemote ln_C_SFGuiaRemote;
    private LN_T_SFManifiestoRemote ln_T_SFManifiestoRemote;
    FacesContext ctx = FacesContext.getCurrentInstance();
    private SessionScopedBeanConsultarManifiesto beanSessionConsultarManifiesto;
    private List lstEstados;
    private BeanUsuarioAutenticado beanUsuario = new BeanUsuarioAutenticado();

    public Frm_consultar_manifiesto(){
        try{
            final Context ctx;
            ctx = new InitialContext();
            ln_C_SFManifiestoRemote = (LN_C_SFManifiestoRemote) ctx.lookup(LOOKUP_NAME_SFCMANIF_REMOTO);
            ln_C_SFGuiaRemote = (LN_C_SFGuiaRemote) ctx.lookup(LOOKUP_NAME_SFCGUIA_REMOTO);
            ln_T_SFManifiestoRemote = (LN_T_SFManifiestoRemote) ctx.lookup(LOOKUP_NAME_SFMANIFIESTO_REMOTO);
            this.setLstEstados(Utils.llenarCombos(ln_C_SFGuiaRemote.getListADMCONS("TRMMANI","ESTMANI")));
            beanUsuario = (BeanUsuarioAutenticado) Utils.getSession("USER");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @PostConstruct
    public void methodInvokeOncedOnPageLoad(){
        if(getBeanSessionConsultarManifiesto().getExec() == 0){
            getBeanSessionConsultarManifiesto().setExec(1);
            buscarManifiesto();
            if(Utils.hasPermiso(beanUsuario.getLstPermisos(),new BigDecimal(56))){//Cambiar Fechas
                beanSessionConsultarManifiesto.setRenderBtnFecha(true);
            }
        }else{
            buscarManifiesto();
            //Utils.depurar("POST CONSTRUCT otras veces");
        }
    }
    
    public String buscarManifiesto(){
        Date fecMin = getBeanSessionConsultarManifiesto().getFecMin();
        Date fecMax = getBeanSessionConsultarManifiesto().getFecMax();
        Integer nidManif = getBeanSessionConsultarManifiesto().getNidManif();
        int nidEmpProv = getBeanSessionConsultarManifiesto().getNidEmpProv();
        String razonSocial = getBeanSessionConsultarManifiesto().getRazonSocial();
        Double flete = getBeanSessionConsultarManifiesto().getFlete();
        Double adelanto = getBeanSessionConsultarManifiesto().getAdelanto();
        String simboloFLete = getBeanSessionConsultarManifiesto().getSimboloFLete();
        String simboloAdela = getBeanSessionConsultarManifiesto().getSimboloAdela();
        String observ = getBeanSessionConsultarManifiesto().getObserv();
        String estManif = getBeanSessionConsultarManifiesto().getEstadoManif();
        String estManifVigNoVig = beanSessionConsultarManifiesto.getEstadoManifActivoNoAct();
        int intEstManifVigNoVig = (estManifVigNoVig == null) ? 1 : Integer.parseInt(estManifVigNoVig);
        getBeanSessionConsultarManifiesto().setLstBeanManifiesto(ln_C_SFManifiestoRemote._findManifiestosByAttr_LN(fecMin, 
                                                                                                                   fecMax, 
                                                                                                                   nidManif, 
                                                                                                                   nidEmpProv, 
                                                                                                                   razonSocial, 
                                                                                                                   flete, 
                                                                                                                   adelanto, 
                                                                                                                   simboloFLete, 
                                                                                                                   simboloAdela, 
                                                                                                                   observ,
                                                                                                                   estManif,
                                                                                                                   intEstManifVigNoVig));
        if(tbManif != null){
            Utils.unselectFilas(tbManif);
            tbManif.setValue(beanSessionConsultarManifiesto.getLstBeanManifiesto());
            Utils.addTarget(tbManif);
        }
        return null;
    }
    
    public void limpiar(ActionEvent actionEvent){
        _limpiar();
    }
    
    public List<BeanManifiesto>  _limpiar(){
        getBeanSessionConsultarManifiesto().setAdelanto(null);
        getBeanSessionConsultarManifiesto().setFecMax(null);
        getBeanSessionConsultarManifiesto().setFecMin(null);
        getBeanSessionConsultarManifiesto().setFlete(null);
        getBeanSessionConsultarManifiesto().setNidEmpProv(0);
        getBeanSessionConsultarManifiesto().setNidManif(null);
        getBeanSessionConsultarManifiesto().setObserv(null);
        getBeanSessionConsultarManifiesto().setRazonSocial(null);
        getBeanSessionConsultarManifiesto().setSimboloAdela(null);
        getBeanSessionConsultarManifiesto().setSimboloFLete(null);
        getBeanSessionConsultarManifiesto().setEstadoManif(null);
        beanSessionConsultarManifiesto.setEstadoManifActivoNoAct(null);
        getBeanSessionConsultarManifiesto().setLstBeanManifiesto(ln_C_SFManifiestoRemote._findManifiestosByAttr_LN(null, 
                                                                                                                   null, 
                                                                                                                   null, 
                                                                                                                   null, 
                                                                                                                   null, 
                                                                                                                   null, 
                                                                                                                   null, 
                                                                                                                   null, 
                                                                                                                   null, 
                                                                                                                   null,
                                                                                                                   null,
                                                                                                                   1));
        if(tbManif != null){
            Utils.unselectFilas(tbManif);
            tbManif.setValue(beanSessionConsultarManifiesto.getLstBeanManifiesto());
            Utils.addTarget(tbManif);
        }
        return getBeanSessionConsultarManifiesto().getLstBeanManifiesto();
    }
    
    public void selectManifiesto(SelectionEvent selectionEvent) {
        RichTable t = (RichTable)selectionEvent.getSource();
        BeanManifiesto beanManif = (BeanManifiesto) t.getSelectedRowData();
        beanSessionConsultarManifiesto.setFecManf(beanManif.getFechaManifiesto());
        beanSessionConsultarManifiesto.setNidManifies(beanManif.getNidManifiesto());
        beanSessionConsultarManifiesto.setDflete(beanManif.getNFletePactado());
        beanSessionConsultarManifiesto.setDadelanto(beanManif.getNAdelanto());
        beanSessionConsultarManifiesto.setObservManif(beanManif.getCObservaciones());
        beanSessionConsultarManifiesto.setEstadoManifNeg(beanManif.getEstadoManifiestoNegocio());
        beanSessionConsultarManifiesto.setIgvManif(beanManif.getIgv());
        beanSessionConsultarManifiesto.setDetracManif(beanManif.getDetraccionReal());
        beanSessionConsultarManifiesto.setMontoFinalVal(beanManif.getDetraccion());
        beanSessionConsultarManifiesto.setTipDoc(beanManif.getCTipoDoc());
        btnEditar.setDisabled(false);
        btnGuias.setDisabled(false);
        if(manifiestoHasGuiasActivas(beanManif.getNidManifiesto()) == false){
            btnAnularManif.setDisabled(false);
        }else{
            btnAnularManif.setDisabled(true);
        }
        if(beanManif.getNEstManifiesto() == 0){
            btnGuias.setDisabled(true);
            btnEditar.setDisabled(true);
            btnAnularManif.setDisabled(true);
        }
        if(beanSessionConsultarManifiesto.isRenderBtnFecha()){
            btnFecha.setDisabled(false);
        }else{
            btnFecha.setDisabled(true);
        }
        Utils.addTargetMany(btnGuias,btnEditar,btnAnularManif,btnFecha);
    }
    
    public String sortByAtributos(String nombre){
        List sortList = new ArrayList();
        boolean bool = true;
        if(getBeanSessionConsultarManifiesto().isIsAsc() != true){
            bool = false;
            getBeanSessionConsultarManifiesto().setIsAsc(true);
        }else{//TRUE
            getBeanSessionConsultarManifiesto().setIsAsc(false);
        }
        SortCriterion sc2 = new SortCriterion(nombre,bool);
        sortList.add(sc2);
        RichTable ct = getTbManif();
        ct.setSortCriteria(sortList);
        Utils.addTarget(ct);
        return null;
    }
    
    public void alwp(ActionEvent ae) {
      String param = (String)ae.getComponent().getAttributes().get("columna");
      sortByAtributos(param);
    }
    
    public void verGuias(ActionEvent ae) {
        if(beanSessionConsultarManifiesto.getNidManifies() != 0){
            getBeanSessionConsultarManifiesto().setLstGuiasTabla(ln_C_SFGuiaRemote.findGuiasByManifiesto_LN(getBeanSessionConsultarManifiesto().getNidManifies()));
            getBeanSessionConsultarManifiesto().setDialogTitulo("Guias de Remision del Manifiesto Seleccionado Codigo: "+getBeanSessionConsultarManifiesto().getNidManifies());
            Utils.showPopUpMIDDLE(popGuias);
        }else{
            Utils.throwError_Aux(ctx,"Seleccione un Manifiesto",4);   
        }
    }

    public boolean manifiestoHasGuiasActivas(int nidManif){
        if(beanSessionConsultarManifiesto.getNidManifies() != 0){
            return ln_C_SFGuiaRemote.manifiestoHasGuiasActivas_LN(nidManif);
        }else{
            return true;
        }
    }
    
    public void editarManifiesto(ActionEvent ae) {
        if(beanSessionConsultarManifiesto.getNidManifies() != 0){
            if (Utils.hasPermiso(beanUsuario.getLstPermisos(),new BigDecimal("32"))) { //Tiene permiso 32 (ADMPERM) modificar manifiesto
                String estManifNeg = beanSessionConsultarManifiesto.getEstadoManifNeg();
                int opc = Integer.parseInt(estManifNeg);
                switch(opc){
                    case 1: manejarEdicionManif(false,true);break;//EN TRANSITO
                    case 2: manejarEdicionManif(false,false);break;//CONFORME X REGULARIZAR
                    case 3: manejarEdicionManif(false,false);break;//GUIAS X ASIGNAR
                    case 4: manejarEdicionManif(true,true);break;//CANCELADO
                    case 5: manejarEdicionManif(true,false);break;//X CANCELAR
                }
                if(socEstdos != null){
                    socEstdos.resetValue();
                    Utils.addTarget(socEstdos);
                }
                beanSessionConsultarManifiesto.setSaldo(beanSessionConsultarManifiesto.getMontoFinalVal() - beanSessionConsultarManifiesto.getDadelanto());
                Utils.showPopUpMIDDLE(popEditarManif);
                if(itObs != null){
                    itObs.resetValue();
                }
            }
        }else{
            Utils.throwError_Aux(ctx,"Seleccione un Manifiesto",4);   
        }
    }
    
    public void manejarEdicionManif(boolean estCompEdit, boolean comboEst){
        beanSessionConsultarManifiesto.setDisabComp(estCompEdit);
        beanSessionConsultarManifiesto.setDisabEstado(comboEst);
    }
    
    public void cambiarFlete(ValueChangeEvent vce) {
        try{
            Object flete = vce.getNewValue();
            Double dFlete = Double.parseDouble(flete.toString());
            Double adela = 0.0;
            Double detrac = 0.0;
            Double montoFinal = 0.0;
            if(beanSessionConsultarManifiesto.getAdelanto() != null){
                adela = beanSessionConsultarManifiesto.getAdelanto();
            }
            Double saldo = 0.0;//dFlete - adela;
            if(saldo < 0.0){
                Utils.throwError_Aux(ctx,"El flete no puede ser menor al adelanto",4);
                dFlete = 0.0;
                saldo = 0.0;
                itFlete.resetValue();
                itIGV.resetValue();
                itDetraccion.resetValue();
                itMontoFin.resetValue();
            }else{
                Double igv = dFlete + (dFlete * .18);
                beanSessionConsultarManifiesto.setIgvVal(igv);
                itIGV.setValue(igv);
                if(igv >= 400.0){
                    montoFinal = igv - (igv * .04);
                    detrac = igv * .04;
                }else{
                    montoFinal = igv;
                }
                itMontoFin.setValue(montoFinal);
                itDetraccion.setValue(detrac);
                saldo = montoFinal - adela;
                itSaldo.setValue(saldo);
            }
            beanSessionConsultarManifiesto.setDflete(dFlete);
            beanSessionConsultarManifiesto.setSaldo(saldo);
            beanSessionConsultarManifiesto.setDetraccionVal(detrac);
            beanSessionConsultarManifiesto.setMontoFinalVal(montoFinal);
            Utils.addTargetMany(itFlete,itIGV,itMontoFin,itSaldo,itDetraccion);
        }catch(Exception e){
            Utils.throwError_Aux(ctx,"Ingrese un valor numerico positivo mayor a 0",4);
        }
    }

    public void cambiarAdelanto(ValueChangeEvent vce) {
        try{
            Object adela = vce.getNewValue();
            Double dAdela = Double.parseDouble(adela.toString());
            Double montoFinal = 0.0;
            if(beanSessionConsultarManifiesto.getMontoFinalVal() != null){
                montoFinal = beanSessionConsultarManifiesto.getMontoFinalVal();
            }
            Double saldo = montoFinal - dAdela;
            if(saldo < 0){
                Utils.throwError_Aux(ctx,"El adelanto no puede ser mayor a 0",4);
                dAdela = 0.0;
                itAdelanto.resetValue();
                saldo = 0.0;
            }else{
                itSaldo.setValue(saldo);
            }
            beanSessionConsultarManifiesto.setAdelanto(dAdela);
            beanSessionConsultarManifiesto.setSaldo(saldo);
            Utils.addTargetMany(itAdelanto,itSaldo);
        }catch(Exception e){
            Utils.throwError_Aux(ctx,"Ingrese un valor numerico positivo mayor a 0",4);
        }
    }
    
    public void dialogCambiarFechasManif(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
            try{
                String result = ln_T_SFManifiestoRemote.cambiarFechaManifiestoPermiso56(beanSessionConsultarManifiesto.getNidManifies(), 
                                                                                        beanSessionConsultarManifiesto.getFecManf());
                    if("000".equals(result)){
                        _limpiar();
                        Utils.throwError_Aux(ctx,"Se modifico la fecha del manifiesto", 3);
                    }else{
                        Utils.throwError_Aux(ctx,"Hubo un error al modificar la fecha.", 1);
                    }
            }catch(Exception e){
                e.printStackTrace();
                Utils.throwError_Aux(ctx,"Error Inesperado", 1);
            }
            btnFecha.setDisabled(true);
            Utils.addTarget(btnFecha);
            popFecManif.hide();
        }
    }
           
    public void dialogModificarManifOKListener(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
            registrarManifiesto();
        }
    }
    
    public void onDialogCancel(ClientEvent ce) {
        itAdelanto.resetValue();
        itSaldo.resetValue();
        itFlete.resetValue();
        itObs.resetValue();
        socEstdos.resetValue();
        Utils.unselectFilas(tbManif);
        beanSessionConsultarManifiesto.setObservManif(null);
        beanSessionConsultarManifiesto.setEstadoManifNeg(null);
        btnEditar.setDisabled(true);
        btnGuias.setDisabled(true);
        btnAnularManif.setDisabled(true);
        getBeanSessionConsultarManifiesto().setSaldo(0.0);
        Utils.addTargetMany(itFlete,itIGV,itMontoFin,itSaldo,itObs,socEstdos,btnEditar,btnGuias);
        popEditarManif.hide();
        FacesContext fctx = FacesContext.getCurrentInstance();
        fctx.renderResponse();
    }
    
    public void cambiarEstaManif(ValueChangeEvent vce) {
        try{
            String oldVal = vce.getOldValue().toString();
            String val = vce.getNewValue().toString();
            if(oldVal.equals("2") && (!val.equals("4") && !val.equals("5"))){
                manejarEstados(oldVal);
            }
            if(oldVal.equals("5") && !val.equals("4")){
                manejarEstados(oldVal);
            }
            if(oldVal.equals("3") && !val.equals("1")){
                manejarEstados(oldVal);
            }
            if(oldVal.equals("3") && val.equals("1")){
                int cant = ln_C_SFGuiaRemote.cantGuiasVigentesByManifiesto_LN(beanSessionConsultarManifiesto.getNidManifies());
                if(cant <= 0){
                    socEstdos.setValue(oldVal);
                    Utils.addTarget(socEstdos);
                    Utils.throwError_Aux(ctx,"El manifiesto debe tener al menos 1 Guia asignada",4);
                    return;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            return;
        }
    }
    
    public void manejarEstados(String oldVal){
        Utils.throwError_Aux(ctx,"No puede cambiar al estado seleccionado",4);
        socEstdos.setValue(oldVal);
        Utils.addTarget(socEstdos);
        return;
    }
    
    public String registrarManifiesto(){
        try{
            String obsv = getBeanSessionConsultarManifiesto().getObservManif();
            if(obsv != null && obsv.length() >= 300){
                obsv = obsv.substring(0,300);
            }
            if(getBeanSessionConsultarManifiesto().getDadelanto() != null){
                if(getBeanSessionConsultarManifiesto().getDadelanto() >= getBeanSessionConsultarManifiesto().getDflete()){
                    Utils.throwError_Aux(ctx,"El monto del adelanto no puede ser mayor igual que el flete",4);
                    Utils.unselectFilas(tbManif);
                    return null;
                }
            }
            BeanManifiesto bManifiesto = ln_T_SFManifiestoRemote.registrarManifiesto_LN(0,
                                                                                        null, 
                                                                                        getBeanSessionConsultarManifiesto().getDflete(),
                                                                                        getBeanSessionConsultarManifiesto().getDadelanto(),
                                                                                        null,
                                                                                        obsv,
                                                                                        0,
                                                                                        0,
                                                                                        getBeanSessionConsultarManifiesto().getNidManifies(), 
                                                                                        1,
                                                                                        getBeanSessionConsultarManifiesto().getEstadoManifNeg());
            if(bManifiesto.getBeanError() != null){
                BeanError error = bManifiesto.getBeanError();
                int severidad = 0;
                if(error.getCidError().equals("000")){
                    severidad = 3;
                    _limpiar();
                }else{
                    severidad = 1;
                }
                Utils.throwError_Aux(ctx,error.getCDescripcionError(), severidad);
            }else{
                Utils.throwError_Aux(ctx,"Error Inesperado", 1);
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            Utils.throwError_Aux(ctx,"Error Inesperado", 1);
            return null;
        }
    }
    
    public void anularManifiesto(ActionEvent actionEvent) {
        Utils.showPopUpMIDDLE(diaAnular);
    }
           
    public void cambiarFechaManif(ActionEvent actionEvent) {
        Utils.showPopUpMIDDLE(popFecManif);
    }
    
    public void dialogAnulaManifListener(DialogEvent de) {
        if (de.getOutcome() == DialogEvent.Outcome.ok){
            BeanManifiesto bManif = ln_T_SFManifiestoRemote.anularManifiesto(beanSessionConsultarManifiesto.getNidManifies());
            if(bManif != null){
                if(bManif.getBeanError() != null){
                    BeanError bError = bManif.getBeanError();
                    int severidad = 1;
                    if(bError.getCidError().equals("000")){
                        severidad = 3;
                        _limpiar();
                    }
                    Utils.throwError_Aux(ctx,bError.getCDescripcionError(),severidad);
                }else{
                    Utils.throwError_Aux(ctx,"Error inesperado",1);
                }
            }else{
                Utils.throwError_Aux(ctx,"Error inesperado",1);
            }
            diaAnular.hide();
        }
    }
    
    public void setDb1(RichDecorativeBox db1) {
        this.db1 = db1;
    }

    public RichDecorativeBox getDb1() {
        return db1;
    }

    public void setOlTitulo(RichOutputLabel ol1) {
        this.olTitulo = ol1;
    }

    public RichOutputLabel getOlTitulo() {
        return olTitulo;
    }

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }

    public void setS1(RichSubform s1) {
        this.s1 = s1;
    }

    public RichSubform getS1() {
        return s1;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setBeanSessionConsultarManifiesto(SessionScopedBeanConsultarManifiesto beanSessionConsultarManifiesto) {
        this.beanSessionConsultarManifiesto = beanSessionConsultarManifiesto;
    }

    public SessionScopedBeanConsultarManifiesto getBeanSessionConsultarManifiesto() {
        return beanSessionConsultarManifiesto;
    }

    public void setTbManif(RichTable t1) {
        this.tbManif = t1;
    }

    public RichTable getTbManif() {
        return tbManif;
    }

    public void setFecMin(RichInputDate id1) {
        this.fecMin = id1;
    }

    public RichInputDate getFecMin() {
        return fecMin;
    }

    public void setFecMax(RichInputDate id1) {
        this.fecMax = id1;
    }

    public RichInputDate getFecMax() {
        return fecMax;
    }

    public void setIdManif(RichInputText it1) {
        this.idManif = it1;
    }

    public RichInputText getIdManif() {
        return idManif;
    }

    public void setEmpProv(RichInputText it2) {
        this.empProv = it2;
    }

    public RichInputText getEmpProv() {
        return empProv;
    }

    public void setFlete(RichInputText it3) {
        this.flete = it3;
    }

    public RichInputText getFlete() {
        return flete;
    }

    public void setAdel(RichInputText it4) {
        this.adel = it4;
    }

    public RichInputText getAdel() {
        return adel;
    }

    public void setObserv(RichInputText it5) {
        this.observ = it5;
    }

    public RichInputText getObserv() {
        return observ;
    }

    public void setPgl2(RichPanelGridLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGridLayout getPgl2() {
        return pgl2;
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

    public void setSocCantFlete(RichSelectOneChoice soc1) {
        this.socCantFlete = soc1;
    }

    public RichSelectOneChoice getSocCantFlete() {
        return socCantFlete;
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

    public void setSocCantAdel(RichSelectOneChoice soc1) {
        this.socCantAdel = soc1;
    }

    public RichSelectOneChoice getSocCantAdel() {
        return socCantAdel;
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

    public void setBtnOrdFec(RichCommandButton cb1) {
        this.btnOrdFec = cb1;
    }

    public RichCommandButton getBtnOrdFec() {
        return btnOrdFec;
    }

    public void setBtnOrdFlete(RichCommandButton cb2) {
        this.btnOrdFlete = cb2;
    }

    public RichCommandButton getBtnOrdFlete() {
        return btnOrdFlete;
    }

    public void setBtnOrdAdel(RichCommandButton cb3) {
        this.btnOrdAdel = cb3;
    }

    public RichCommandButton getBtnOrdAdel() {
        return btnOrdAdel;
    }

    public void setBtnProv(RichCommandButton cb4) {
        this.btnProv = cb4;
    }

    public RichCommandButton getBtnProv() {
        return btnProv;
    }

    public void setBtnObs(RichCommandButton cb5) {
        this.btnObs = cb5;
    }

    public RichCommandButton getBtnObs() {
        return btnObs;
    }

    public void setPopGuias(RichPopup p1) {
        this.popGuias = p1;
    }

    public RichPopup getPopGuias() {
        return popGuias;
    }

    public void setDiaGuias(RichDialog d1) {
        this.diaGuias = d1;
    }

    public RichDialog getDiaGuias() {
        return diaGuias;
    }

    public void setT2(RichTable t2) {
        this.t2 = t2;
    }

    public RichTable getT2() {
        return t2;
    }

    public void setLstEstados(List lstEstados) {
        this.lstEstados = lstEstados;
    }

    public List getLstEstados() {
        return lstEstados;
    }

    public void setSocEstados(RichSelectOneChoice soc1) {
        this.socEstados = soc1;
    }

    public RichSelectOneChoice getSocEstados() {
        return socEstados;
    }

    public void setSi7(UISelectItems si7) {
        this.si7 = si7;
    }

    public UISelectItems getSi7() {
        return si7;
    }

    public void setBtnGuias(RichCommandButton cb1) {
        this.btnGuias = cb1;
    }

    public RichCommandButton getBtnGuias() {
        return btnGuias;
    }

    public void setBtnEditar(RichCommandButton cb1) {
        this.btnEditar = cb1;
    }

    public RichCommandButton getBtnEditar() {
        return btnEditar;
    }

    public void setPopEditarManif(RichPopup p1) {
        this.popEditarManif = p1;
    }

    public RichPopup getPopEditarManif() {
        return popEditarManif;
    }

    public void setD1(RichDialog d1) {
        this.d1 = d1;
    }

    public RichDialog getD1() {
        return d1;
    }

    public void setS2(RichSubform s2) {
        this.s2 = s2;
    }

    public RichSubform getS2() {
        return s2;
    }

    public void setItFlete(RichInputText it1) {
        this.itFlete = it1;
    }

    public RichInputText getItFlete() {
        return itFlete;
    }

    public void setPfl2(RichPanelFormLayout pfl2) {
        this.pfl2 = pfl2;
    }

    public RichPanelFormLayout getPfl2() {
        return pfl2;
    }

    public void setFecManif(RichInputDate id1) {
        this.fecManif = id1;
    }

    public RichInputDate getFecManif() {
        return fecManif;
    }

    public void setItAdelanto(RichInputText it1) {
        this.itAdelanto = it1;
    }

    public RichInputText getItAdelanto() {
        return itAdelanto;
    }

    public void setItSaldo(RichInputText it2) {
        this.itSaldo = it2;
    }

    public RichInputText getItSaldo() {
        return itSaldo;
    }

    public void setItIGV(RichInputText it3) {
        this.itIGV = it3;
    }

    public RichInputText getItIGV() {
        return itIGV;
    }

    public void setItMontoFin(RichInputText it4) {
        this.itMontoFin = it4;
    }

    public RichInputText getItMontoFin() {
        return itMontoFin;
    }

    public void setSocTipDoc(RichSelectOneChoice soc1) {
        this.socTipDoc = soc1;
    }

    public RichSelectOneChoice getSocTipDoc() {
        return socTipDoc;
    }

    public void setItObs(RichInputText it5) {
        this.itObs = it5;
    }

    public RichInputText getItObs() {
        return itObs;
    }

    public void setSi9(RichSelectItem si9) {
        this.si9 = si9;
    }

    public RichSelectItem getSi9() {
        return si9;
    }

    public void setSocEstdos(RichSelectOneChoice soc1) {
        this.socEstdos = soc1;
    }

    public RichSelectOneChoice getSocEstdos() {
        return socEstdos;
    }

    public void setSi10(UISelectItems si10) {
        this.si10 = si10;
    }

    public UISelectItems getSi10() {
        return si10;
    }

    public void setLn_T_SFManifiestoRemote(LN_T_SFManifiestoRemote ln_T_SFManifiestoRemote) {
        this.ln_T_SFManifiestoRemote = ln_T_SFManifiestoRemote;
    }

    public LN_T_SFManifiestoRemote getLn_T_SFManifiestoRemote() {
        return ln_T_SFManifiestoRemote;
    }

    public void setItDetraccion(RichInputText it1) {
        this.itDetraccion = it1;
    }

    public RichInputText getItDetraccion() {
        return itDetraccion;
    }

    public void setBtnAnularManif(RichCommandButton cb1) {
        this.btnAnularManif = cb1;
    }

    public RichCommandButton getBtnAnularManif() {
        return btnAnularManif;
    }

    public void setDiaAnular(RichPopup diaAnular) {
        this.diaAnular = diaAnular;
    }

    public RichPopup getDiaAnular() {
        return diaAnular;
    }

    public void setSocEstManif(RichSelectOneChoice soc1) {
        this.socEstManif = soc1;
    }

    public RichSelectOneChoice getSocEstManif() {
        return socEstManif;
    }

    public void setSi12(RichSelectItem si12) {
        this.si12 = si12;
    }

    public RichSelectItem getSi12() {
        return si12;
    }

    public void setSi11(RichSelectItem si11) {
        this.si11 = si11;
    }

    public RichSelectItem getSi11() {
        return si11;
    }

    public void setCb1(RichCommandButton cb1) {
        this.cb1 = cb1;
    }

    public RichCommandButton getCb1() {
        return cb1;
    }

    public void setBtnFecha(RichCommandButton cb2) {
        this.btnFecha = cb2;
    }

    public RichCommandButton getBtnFecha() {
        return btnFecha;
    }

    public void setPopFecManif(RichPopup p1) {
        this.popFecManif = p1;
    }

    public RichPopup getPopFecManif() {
        return popFecManif;
    }

    public void setFecMa(RichInputDate id1) {
        this.fecMa = id1;
    }

    public RichInputDate getFecMa() {
        return fecMa;
    }
}
