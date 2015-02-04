package siat.view.backing.transporte.guia;

import java.math.BigDecimal;
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

import net.sf.jmimemagic.Magic;

import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;

import oracle.adf.model.bean.DCDataRow;
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
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.component.rich.output.RichSeparator;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.model.SortCriterion;
import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;
import org.apache.myfaces.trinidad.component.UIXGroup;
import org.apache.myfaces.trinidad.event.RangeChangeEvent;
import org.apache.myfaces.trinidad.event.SelectionEvent;
import siat.view.backing.utiles.Utils;
import silat.servicios_negocio.Beans.BeanConstraint;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanTRGuia;
import silat.servicios_negocio.Beans.BeanTRItem;
import silat.servicios_negocio.Beans.BeanTRItemImgWebMovil;
import silat.servicios_negocio.Beans.BeanUsuarioAutenticado;
import silat.servicios_negocio.LNSF.IR.LN_C_SFGuiaRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFUtilsRemote;
import silat.servicios_negocio.LNSF.IR.LN_T_SFGuiaRemote;

public class Frm_consultar_guia {
    private RichDecorativeBox db1;
    private RichOutputLabel ol1;
    private RichInputDate id1;
    private RichInputDate fecEmi;
    private RichInputDate fecDesp;
    private RichPanelGroupLayout pgl1;
    private RichPanelFormLayout pfl1;
    private RichInputText cidGuia;
    private RichInputDate fecEmiMin;
    private RichInputDate fecEmiMax;
    private RichInputDate fecDespMin;
    private RichInputDate fecDespMax;
    private RichInputText empClien;
    private RichInputText empRemi;
    private RichCommandButton btnBuscarGuia;
    private RichSelectOneChoice socConfor;
    private UISelectItems si1;
    private RichSelectOneChoice socConManif;
    private RichSelectItem si2;
    private RichSelectItem si3;
    private UIXGroup g1;
    private RichTable tbGuias;
    private RichSubform sfrmg;
    private RichSeparator s1;
    private RichCommandButton btnLimpiar;
    private RichInputText txtProvTrans;
    private RichPanelGridLayout pgl3;
    private RichGridRow gr2;
    private RichGridCell gc6;
    private RichGridCell gc7;
    private RichGridCell gc8;
    private RichGridCell gc9;
    private RichInputText itObs;
    private RichCommandButton btnAnular;
    private RichCommandButton btnEditar;
    private RichPopup popAnular;
    private RichDialog diaAnular;
    private RichSelectOneChoice socVigencia;
    private UISelectItems siVig;
    private RichInputText itnidOS;
    private RichInputText itDetOS;
    private RichSelectOneChoice socHasFact;
    private RichSelectItem si4;
    private RichSelectItem si5;
    private RichSelectOneChoice socEstFac;
    private UISelectItems si6;
    private RichInputText itCodFac;
    private RichInputText itManif;
    private RichInputText itGRemiItm;
    private RichCommandButton cb1;
    private RichCommandButton btnFecha;
    private RichPopup popFechas;
    private RichInputDate fecEmiEdit;
    private RichInputDate fecDespEdit;
    /*Mis variables*/
    private final static String LOOKUP_NAME_SFCGUIA_REMOTO = "mapLN_C_SFGuia";
    private final static String LOOKUP_NAME_SFTGUIA_REMOTO = "mapLN_T_SFGuia";
    private final static String LOOKUP_NAME_SFUTILS_REMOTO = "mapLN_C_SFUtils";
    private LN_C_SFUtilsRemote ln_C_SFUtilsRemote;
    private LN_C_SFGuiaRemote ln_C_SFGuiaRemote;
    private LN_T_SFGuiaRemote LN_T_SFGuiaRemote;
    private List lstEstados = new ArrayList();
    private List lstConf;
    private List lstVigencia;
    private SessionScopedBeanConsultarGuia beanSessionConsultarGuia;
    private BeanUsuarioAutenticado beanUsuario = new BeanUsuarioAutenticado();
    FacesContext ctx = FacesContext.getCurrentInstance();
    
    private RichCommandButton btnverImagen;
    private RichPopup popImagen;
    private RichImage imgGuia;
    private RichInputText itGItm;
    
    private RichCommandButton btnverImagenProv;
    private RichPopup popImagen1;
    private RichPanelFormLayout formVerImgProv;
    private RichPanelFormLayout formVerImg;

    public Frm_consultar_guia(){
        try{
            final Context ctx;
            ctx = new InitialContext();
            ln_C_SFGuiaRemote = (LN_C_SFGuiaRemote) ctx.lookup(LOOKUP_NAME_SFCGUIA_REMOTO);
            LN_T_SFGuiaRemote = (LN_T_SFGuiaRemote) ctx.lookup(LOOKUP_NAME_SFTGUIA_REMOTO);
            ln_C_SFUtilsRemote = (LN_C_SFUtilsRemote)ctx.lookup(LOOKUP_NAME_SFUTILS_REMOTO);
            this.setLstVigencia(Utils.llenarCombos(ln_C_SFUtilsRemote.getListADMCONS("TRMGUIA","N_ESTADO_GUIA")));
            this.setLstConf(this.llenarConfCombo());
            this.setLstEstados(Utils.llenarCombos(ln_C_SFUtilsRemote.getListADMCONS("TRMFACT","N_ESTADO_FACTURA")));
            beanUsuario = (BeanUsuarioAutenticado) Utils.getSession("USER");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @PostConstruct
    public void methodInvokeOncedOnPageLoad(){
        if(getBeanSessionConsultarGuia().getExec() == 0){
            getBeanSessionConsultarGuia().setExec(1);
            //buscarTabla(); //dfloresgonz 01.05.2014 se comenta para que al inicio no cargue todo sino el usuario tenga que aplastar el boton
            if(Utils.hasPermiso(beanUsuario.getLstPermisos(),new BigDecimal(28))){
                beanSessionConsultarGuia.setRenderBtnEditar(true);
                beanSessionConsultarGuia.setRenderBtnverImagen(true);
                beanSessionConsultarGuia.setRenderBtnverImagenProv(true);
                beanSessionConsultarGuia.setRenderBtnAnular(true);
            }else{
                beanSessionConsultarGuia.setRenderBtnEditar(false);
                beanSessionConsultarGuia.setRenderBtnverImagen(false);
                beanSessionConsultarGuia.setRenderBtnverImagenProv(false);
                beanSessionConsultarGuia.setRenderBtnAnular(false);
            }
            if(Utils.hasPermiso(beanUsuario.getLstPermisos(),new BigDecimal(56))){//Cambiar Fechas
                beanSessionConsultarGuia.setRenderBtnFecha(true);
            }
        }else{
            
            //Utils.depurar("POST CONSTRUCT otras veces");
        }
    }
    
    public String buscarTabla() {
        beanSessionConsultarGuia.setEstConf(true);
        beanSessionConsultarGuia.setLstGuiasTabla(new ArrayList<BeanTRGuia>());
        String _cidGuia = getBeanSessionConsultarGuia().getCidGuia();
        String _empClien = getBeanSessionConsultarGuia().getEmpreClie();
        String empRemite = getBeanSessionConsultarGuia().getEmpreRemi();
        String confor = getBeanSessionConsultarGuia().getBlnConf();
        Date fecEmMin = getBeanSessionConsultarGuia().getFecEmisMin();
        Date fecEmMax = getBeanSessionConsultarGuia().getFecEmisMax();
        Date fecDespaMin = getBeanSessionConsultarGuia().getFecDespaMin();
        Date fecDespaMax = getBeanSessionConsultarGuia().getFecDespaMax();
        String hasMan = getBeanSessionConsultarGuia().getHasManif();
        String prov = getBeanSessionConsultarGuia().getProve();
        String obs = getBeanSessionConsultarGuia().getObs();
        String nEstadoGuia = beanSessionConsultarGuia.getVigenciaGuia();
        Integer nidManif = beanSessionConsultarGuia.getNidManif();
        String filtroItemGuiaRemi = beanSessionConsultarGuia.getFiltroItemGuiaRemi();
        String filtroGuiaItem= beanSessionConsultarGuia.getFiltroItemGuia();
        System.out.println("Filtro Item : "+filtroItemGuiaRemi);
        int nidOS = 0;
        if(beanSessionConsultarGuia.getNidOS() != null){
            if(!beanSessionConsultarGuia.getNidOS().equals("")){
                nidOS = Integer.parseInt(beanSessionConsultarGuia.getNidOS());   
            }
        }
        String detOS = beanSessionConsultarGuia.getDetOS();
        String hasFactura = beanSessionConsultarGuia.getHasFactura();
        int nEstadoFactura = beanSessionConsultarGuia.getNEstadoFactura() != null ? Integer.parseInt(beanSessionConsultarGuia.getNEstadoFactura()) : 0;
        if(nEstadoGuia != null){
            if(nEstadoGuia.equals("0")){
                beanSessionConsultarGuia.setEstConf(false);
            }
        }else{
            //nEstadoGuia = "1";
        }
        getBeanSessionConsultarGuia().setLstGuiasTabla(this.ln_C_SFGuiaRemote.findGuiasByAttr_LN(_cidGuia,fecEmMin,fecEmMax,fecDespaMin,
                                                                                                 fecDespaMax,_empClien,empRemite,confor,
                                                                                                 hasMan,nidManif,prov,obs,nEstadoGuia,
                                                                                                 nidOS,detOS,hasFactura,
                                                                                                 beanSessionConsultarGuia.getCodigoFacturaFiltro(),
                                                                                                 nEstadoFactura,new BigDecimal(0),filtroItemGuiaRemi,filtroGuiaItem));
        if(tbGuias != null){
            tbGuias.setValue(beanSessionConsultarGuia.getLstGuiasTabla());
            Utils.addTarget(tbGuias);
        }
        return null;
    }
    
    public void limpiar(ActionEvent actionEvent) {
        _limpiar();
    }
    
    public List<BeanTRGuia> _limpiar(){
        beanSessionConsultarGuia.setLstGuiasTabla(new ArrayList<BeanTRGuia>());
        beanSessionConsultarGuia.setEstConf(true);
        getBeanSessionConsultarGuia().setCidGuia(null);
        getBeanSessionConsultarGuia().setEmpreClie(null);
        getBeanSessionConsultarGuia().setEmpreRemi(null);
        getBeanSessionConsultarGuia().setBlnConf(null);
        getBeanSessionConsultarGuia().setFecEmisMin(null);
        getBeanSessionConsultarGuia().setFecEmisMax(null);
        getBeanSessionConsultarGuia().setFecDespaMin(null);
        getBeanSessionConsultarGuia().setFecDespaMax(null);
        getBeanSessionConsultarGuia().setHasManif(null);
        getBeanSessionConsultarGuia().setProve(null);
        getBeanSessionConsultarGuia().setObs(null);
        beanSessionConsultarGuia.setVigenciaGuia(null);
        beanSessionConsultarGuia.setNidOS("0");
        beanSessionConsultarGuia.setDetOS(null);
        beanSessionConsultarGuia.setHasFactura(null);
        beanSessionConsultarGuia.setCodigoFacturaFiltro(null);
        beanSessionConsultarGuia.setNEstadoFactura(null);
        beanSessionConsultarGuia.setNidManif(null);
        beanSessionConsultarGuia.setFiltroItemGuiaRemi(null);
        getBeanSessionConsultarGuia().setLstGuiasTabla(this.ln_C_SFGuiaRemote.findGuiasByAttr_LN(null,null,null,null,null,
                                                                                                 null,null,null,null,null,
                                                                                                 null,null,null,0,null,null,
                                                                                                 null,0,new BigDecimal(0),null,null));
        if(tbGuias != null){
            tbGuias.setValue(beanSessionConsultarGuia.getLstGuiasTabla());
            Utils.addTarget(tbGuias);
        }
        return beanSessionConsultarGuia.getLstGuiasTabla();
    }
    
    public void selectGuias(SelectionEvent se) {
        if(Utils.hasPermiso(beanUsuario.getLstPermisos(),new BigDecimal("28"))){//Modificar Guia
            RichTable t = (RichTable)se.getSource();
            BeanTRGuia beanGuia = (BeanTRGuia) t.getSelectedRowData();
            if("0".equals(beanGuia.getNEstadoGuia())){//ANULADA
                beanGuia.setIsDisableWhenOk_Anulado(1);
                btnAnular.setDisabled(true);
                btnEditar.setDisabled(true);
                btnverImagen.setDisabled(true);
                if(beanGuia.getImgGuiaProvedor()!=null){
                    btnverImagenProv.setDisabled(false);
                }else if(beanGuia.getImgGuiaProvedor()==null){
                    btnverImagenProv.setDisabled(true);
                }
            }else if("2".equals(beanGuia.getCConformidad())){//PENDIENTE
                btnAnular.setDisabled(false);
                btnEditar.setDisabled(false);
                btnverImagen.setDisabled(true);
                if(beanGuia.getImgGuiaProvedor()!=null){
                    btnverImagenProv.setDisabled(false);
                }else if(beanGuia.getImgGuiaProvedor()==null){
                    btnverImagenProv.setDisabled(true);
                }
            }else if("1".equals(beanGuia.getCConformidad())){//OK
                btnAnular.setDisabled(true);
                btnEditar.setDisabled(false);
                if(beanGuia.getImgGuiaProv()!=null){
                    btnverImagen.setDisabled(false);
                }
                if(beanGuia.getImgGuiaProvedor()!=null){
                    btnverImagenProv.setDisabled(false);
                }else if(beanGuia.getImgGuiaProvedor()==null){
                    btnverImagenProv.setDisabled(true);
                }
            }                   
            Utils.addTargetMany(btnAnular,btnEditar);
            beanSessionConsultarGuia.setGuiaSelected(beanGuia);
        }
        if(beanSessionConsultarGuia.isRenderBtnFecha()){
            if(beanSessionConsultarGuia.getGuiaSelected() == null){
                RichTable t = (RichTable)se.getSource();
                beanSessionConsultarGuia.setGuiaSelected((BeanTRGuia) t.getSelectedRowData());
            }
            if(!"0".equals(beanSessionConsultarGuia.getGuiaSelected().getNEstadoGuia())){
                btnFecha.setDisabled(false);
                Utils.addTarget(btnFecha);
            }else{
                btnFecha.setDisabled(true);
                Utils.addTarget(btnFecha);
            }
        }
    }
    
    public String sortByGuia(String nombre){
        List sortList = new ArrayList();
        boolean bool = true;
        if(getBeanSessionConsultarGuia().isGuiaASC() != true){
            bool = false;
            getBeanSessionConsultarGuia().setGuiaASC(true);
        }else{//TRUE
            getBeanSessionConsultarGuia().setGuiaASC(false);
        }
        SortCriterion sc2 = new SortCriterion(nombre,bool);
        sortList.add(sc2);
        RichTable ct = getTbGuias();
        ct.setSortCriteria(sortList);
        Utils.addTarget(ct);
        return null;
    }
    
    public void alwp(ActionEvent actionEvent) {
      String param = (String)actionEvent.getComponent().getAttributes().get("columna");
      sortByGuia(param);
    }
    
    public ArrayList llenarConfCombo() {
        ArrayList confItems = new ArrayList();
        List<BeanConstraint> consts = ln_C_SFGuiaRemote.getListConformidad();
        for (BeanConstraint r : consts) {
            confItems.add(new SelectItem(r.getCValorCampo().toString(),r.getCDescrip().toString()));
        }
        return confItems;
    }
    
    public void dialogCambiarFechas(DialogEvent de) {
        try {
            if (de.getOutcome() == DialogEvent.Outcome.ok) {
                String cidGuia = beanSessionConsultarGuia.getGuiaSelected().getCidGuia().substring(beanSessionConsultarGuia.getGuiaSelected().getCidGuia().indexOf("-") +1,
                                                                                                   beanSessionConsultarGuia.getGuiaSelected().getCidGuia().length());
                String result = LN_T_SFGuiaRemote.cambiarFechasGuiaPermiso56(cidGuia, beanSessionConsultarGuia.getGuiaSelected().getCidUnidadNegocio(),
                                                                                      beanSessionConsultarGuia.getGuiaSelected().getFechaGuia(),
                                                                                      beanSessionConsultarGuia.getGuiaSelected().getFechaDespacho());
                if("000".equals(result)){
                    _limpiar();
                    Utils.unselectFilas(tbGuias);
                    Utils.throwError_Aux(ctx, "Se actualizaron las fechas de la Guia", 3);
                }else{
                    Utils.throwError_Aux(ctx, "Hubo un error al grabar las fechas", 1);
                }
                beanSessionConsultarGuia.setGuiaSelected(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Utils.throwError_Aux(ctx, "Hubo un error al grabar las fechas.", 4);
        }
        btnFecha.setDisabled(true);
        Utils.addTarget(btnFecha);
        popFechas.hide();
    }
    
    public void cambiarFechas(ActionEvent ae){
        if(beanSessionConsultarGuia.getGuiaSelected() != null){
            Utils.showPopUpMIDDLE(popFechas);
        }
    }
    
    public void anularGuia(ActionEvent ae) {
        if(Utils.hasPermiso(beanUsuario.getLstPermisos(),new BigDecimal("28"))){
            if(beanSessionConsultarGuia.getGuiaSelected().getCidGuia() != null){
                Utils.showPopUpMIDDLE(popAnular);
            }else{
                Utils.throwError_Aux(ctx,"Seleccione una Guia para anularla.",4);
                return;
            }
        }
    }
    
    public void editarGuia(ActionEvent ae) {
        if(Utils.hasPermiso(beanUsuario.getLstPermisos(),new BigDecimal("28"))){
            if(beanSessionConsultarGuia.getGuiaSelected().getCidGuia() != null){
                beanSessionConsultarGuia.getGuiaSelected().setItemsLista(ln_C_SFGuiaRemote.getListaItemsByCidGuia(beanSessionConsultarGuia.getGuiaSelected().getNativeCidGuia()));
                Utils.putSession("GUIA",beanSessionConsultarGuia.getGuiaSelected());
                Utils._redireccionar(ctx,"/WEB-INF/registrar_guia.xml#registrar_guia");
            }else{
                Utils.throwError_Aux(ctx,"Seleccione una Guia para editarla.",4);    
                return;
            }
        }
    }
    
    public void verImagen(ActionEvent ae){
        if(beanSessionConsultarGuia.getGuiaSelected() != null){
            beanSessionConsultarGuia.setImg("");
            if(beanSessionConsultarGuia.getGuiaSelected().getImgGuiaProv()!=null){
                beanSessionConsultarGuia.setImg(beanSessionConsultarGuia.getGuiaSelected().getImgGuiaProv());
                beanSessionConsultarGuia.setDescripImg("");
                Utils.showPopUpMIDDLE(popImagen); 
            }else{
                beanSessionConsultarGuia.setImg("#");
                beanSessionConsultarGuia.setDescripImg("");
                Utils.showPopUpMIDDLE(popImagen); 
            }
            }
        }
    
    public void verImagenProv(ActionEvent ae){
        if(beanSessionConsultarGuia.getGuiaSelected() != null){
            if(beanSessionConsultarGuia.getGuiaSelected().getImgGuiaProvedor()!=null){
                beanSessionConsultarGuia.getLstItemsImg().clear();
            String imagenes = beanSessionConsultarGuia.getGuiaSelected().getImgGuiaProvedor();
                String[] parts = imagenes.split("data");
                for(int i = 0;i<parts.length;i++){
                    if(i!=0){
                        BeanTRItemImgWebMovil bean = new BeanTRItemImgWebMovil();
                        
                        String[] parts_down = parts[i].split("dEsCrIp");
                        
                        String part1 = "";
                        String part2 = "";
                        
                            part1 = parts_down[0];
                            part2 = parts_down[1];
                        
                        bean.setImg("data"+part1);
                        bean.setDescripimg("DESCRIPCION: "+part2);
                        
                        beanSessionConsultarGuia.getLstItemsImg().add(bean);
                    }
                }
                Utils.addTarget(popImagen1);
                Utils.showPopUpMIDDLE(popImagen1); 
            }else{
                beanSessionConsultarGuia.setImg("#");
                beanSessionConsultarGuia.setDescripImg("");
                Utils.showPopUpMIDDLE(popImagen1); 
            }
            }
        }
    
    public String verImagenGrande(){
        String index = beanSessionConsultarGuia.getIndexImg();
        int ind = Integer.parseInt(index);
        for(int i = 0;i<beanSessionConsultarGuia.getLstItemsImg().size();i++){
            if(i == ind){
                beanSessionConsultarGuia.setImg(beanSessionConsultarGuia.getLstItemsImg().get(i).getImg());
                beanSessionConsultarGuia.setDescripImg(beanSessionConsultarGuia.getLstItemsImg().get(i).getDescripimg());
            }
        }
        Utils.addTarget(popImagen);
        Utils.showPopUpMIDDLE(popImagen); 
        return "";
    }
    
           
    public void dialogAnularGuiaListener(DialogEvent de) {
        if (de.getOutcome() == DialogEvent.Outcome.ok) {
            BeanTRGuia bGuia = LN_T_SFGuiaRemote.anularGuia(beanSessionConsultarGuia.getGuiaSelected());
            if(bGuia.getBeanError() != null){
                BeanError error = bGuia.getBeanError();
                int severidad = 0;
                if("000".equals(error.getCidError())){
                    severidad = 3;
                    _limpiar();
                    Utils.unselectFilas(tbGuias);
                    Utils.depurar("Anulo la Guia");
                }else{
                    severidad = 1;
                }
                Utils.throwError_Aux(ctx,error.getCDescripcionError(), severidad);
            }else{
                Utils.throwError_Aux(ctx,"Error Inesperado", 1);
            }
        }
    }
    
    public void setDb1(RichDecorativeBox db1) {
        this.db1 = db1;
    }

    public RichDecorativeBox getDb1() {
        return db1;
    }

    public void setOl1(RichOutputLabel ol1) {
        this.ol1 = ol1;
    }

    public RichOutputLabel getOl1() {
        return ol1;
    }


    public void setId1(RichInputDate id1) {
        this.id1 = id1;
    }

    public RichInputDate getId1() {
        return id1;
    }

    public void setFecEmi(RichInputDate id2) {
        this.fecEmi = id2;
    }

    public RichInputDate getFecEmi() {
        return fecEmi;
    }

    public void setFecDesp(RichInputDate id3) {
        this.fecDesp = id3;
    }

    public RichInputDate getFecDesp() {
        return fecDesp;
    }

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setCidGuia(RichInputText it1) {
        this.cidGuia = it1;
    }

    public RichInputText getCidGuia() {
        return cidGuia;
    }


    public void setFecEmiMin(RichInputDate id4) {
        this.fecEmiMin = id4;
    }

    public RichInputDate getFecEmiMin() {
        return fecEmiMin;
    }

    public void setFecEmiMax(RichInputDate id5) {
        this.fecEmiMax = id5;
    }

    public RichInputDate getFecEmiMax() {
        return fecEmiMax;
    }

    public void setFecDespMin(RichInputDate id6) {
        this.fecDespMin = id6;
    }

    public RichInputDate getFecDespMin() {
        return fecDespMin;
    }

    public void setFecDespMax(RichInputDate id7) {
        this.fecDespMax = id7;
    }

    public RichInputDate getFecDespMax() {
        return fecDespMax;
    }

    public void setEmpClien(RichInputText it3) {
        this.empClien = it3;
    }

    public RichInputText getEmpClien() {
        return empClien;
    }


    public void setEmpRemi(RichInputText it5) {
        this.empRemi = it5;
    }

    public RichInputText getEmpRemi() {
        return empRemi;
    }

    public void setBtnBuscarGuia(RichCommandButton cb1) {
        this.btnBuscarGuia = cb1;
    }

    public RichCommandButton getBtnBuscarGuia() {
        return btnBuscarGuia;
    }


    public void setLn_C_SFGuiaRemote(LN_C_SFGuiaRemote ln_C_SFGuiaRemote) {
        this.ln_C_SFGuiaRemote = ln_C_SFGuiaRemote;
    }

    public LN_C_SFGuiaRemote getLn_C_SFGuiaRemote() {
        return ln_C_SFGuiaRemote;
    }

    public void setLstConf(List lstConf) {
        this.lstConf = lstConf;
    }

    public List getLstConf() {
        return lstConf;
    }

    public void setSocConfor(RichSelectOneChoice soc1) {
        this.socConfor = soc1;
    }

    public RichSelectOneChoice getSocConfor() {
        return socConfor;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public UISelectItems getSi1() {
        return si1;
    }

    public void setSocConManif(RichSelectOneChoice soc2) {
        this.socConManif = soc2;
    }

    public RichSelectOneChoice getSocConManif() {
        return socConManif;
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

    public void setG1(UIXGroup g1) {
        this.g1 = g1;
    }

    public UIXGroup getG1() {
        return g1;
    }

    public void setTbGuias(RichTable t1) {
        this.tbGuias = t1;
    }

    public RichTable getTbGuias() {
        return tbGuias;
    }

    public void setBeanSessionConsultarGuia(SessionScopedBeanConsultarGuia beanSessionConsultarGuia) {
        this.beanSessionConsultarGuia = beanSessionConsultarGuia;
    }

    public SessionScopedBeanConsultarGuia getBeanSessionConsultarGuia() {
        return beanSessionConsultarGuia;
    }

    public void setSfrmg(RichSubform s1) {
        this.sfrmg = s1;
    }

    public RichSubform getSfrmg() {
        return sfrmg;
    }


    public void setS1(RichSeparator s1) {
        this.s1 = s1;
    }

    public RichSeparator getS1() {
        return s1;
    }


    public void setBtnLimpiar(RichCommandButton cb1) {
        this.btnLimpiar = cb1;
    }

    public RichCommandButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public void setTxtProvTrans(RichInputText it1) {
        this.txtProvTrans = it1;
    }

    public RichInputText getTxtProvTrans() {
        return txtProvTrans;
    }


    public void setPgl3(RichPanelGridLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGridLayout getPgl3() {
        return pgl3;
    }

    public void setGr2(RichGridRow gr2) {
        this.gr2 = gr2;
    }

    public RichGridRow getGr2() {
        return gr2;
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

    public void setGc9(RichGridCell gc9) {
        this.gc9 = gc9;
    }

    public RichGridCell getGc9() {
        return gc9;
    }


    public void setItObs(RichInputText it1) {
        this.itObs = it1;
    }

    public RichInputText getItObs() {
        return itObs;
    }

    public void setBtnAnular(RichCommandButton cb1) {
        this.btnAnular = cb1;
    }

    public RichCommandButton getBtnAnular() {
        return btnAnular;
    }

    public void setBtnEditar(RichCommandButton cb1) {
        this.btnEditar = cb1;
    }

    public RichCommandButton getBtnEditar() {
        return btnEditar;
    }

    public void setPopAnular(RichPopup p1) {
        this.popAnular = p1;
    }

    public RichPopup getPopAnular() {
        return popAnular;
    }

    public void setDiaAnular(RichDialog d1) {
        this.diaAnular = d1;
    }

    public RichDialog getDiaAnular() {
        return diaAnular;
    }

    public void setLstVigencia(List lstVigencia) {
        this.lstVigencia = lstVigencia;
    }

    public List getLstVigencia() {
        return lstVigencia;
    }

    public void setSocVigencia(RichSelectOneChoice soc1) {
        this.socVigencia = soc1;
    }

    public RichSelectOneChoice getSocVigencia() {
        return socVigencia;
    }

    public void setSiVig(UISelectItems si4) {
        this.siVig = si4;
    }

    public UISelectItems getSiVig() {
        return siVig;
    }

    public void setItnidOS(RichInputText it1) {
        this.itnidOS = it1;
    }

    public RichInputText getItnidOS() {
        return itnidOS;
    }

    public void setItDetOS(RichInputText it1) {
        this.itDetOS = it1;
    }

    public RichInputText getItDetOS() {
        return itDetOS;
    }

    public void setSocHasFact(RichSelectOneChoice soc1) {
        this.socHasFact = soc1;
    }

    public RichSelectOneChoice getSocHasFact() {
        return socHasFact;
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

    public void setLstEstados(List lstEstados) {
        this.lstEstados = lstEstados;
    }

    public List getLstEstados() {
        return lstEstados;
    }

    public void setSocEstFac(RichSelectOneChoice soc2) {
        this.socEstFac = soc2;
    }

    public RichSelectOneChoice getSocEstFac() {
        return socEstFac;
    }

    public void setSi6(UISelectItems si6) {
        this.si6 = si6;
    }

    public UISelectItems getSi6() {
        return si6;
    }

    public void setItCodFac(RichInputText it1) {
        this.itCodFac = it1;
    }

    public RichInputText getItCodFac() {
        return itCodFac;
    }


    public void setItManif(RichInputText it2) {
        this.itManif = it2;
    }

    public RichInputText getItManif() {
        return itManif;
    }

    public void setItGRemiItm(RichInputText it1) {
        this.itGRemiItm = it1;
    }

    public RichInputText getItGRemiItm() {
        return itGRemiItm;
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

    public void setPopFechas(RichPopup p1) {
        this.popFechas = p1;
    }

    public RichPopup getPopFechas() {
        return popFechas;
    }

    public void setFecEmiEdit(RichInputDate fecEmiEdit) {
        this.fecEmiEdit = fecEmiEdit;
    }

    public RichInputDate getFecEmiEdit() {
        return fecEmiEdit;
    }

    public void setFecDespEdit(RichInputDate fecDespEdit) {
        this.fecDespEdit = fecDespEdit;
    }

    public RichInputDate getFecDespEdit() {
        return fecDespEdit;
    }

    public void setBtnverImagen(RichCommandButton btnverImagen) {
        this.btnverImagen = btnverImagen;
    }

    public RichCommandButton getBtnverImagen() {
        return btnverImagen;
    }

    public void setPopImagen(RichPopup popImagen) {
        this.popImagen = popImagen;
    }

    public RichPopup getPopImagen() {
        return popImagen;
    }

    public void setImgGuia(RichImage imgGuia) {
        this.imgGuia = imgGuia;
    }

    public RichImage getImgGuia() {
        return imgGuia;
    }

    public void setItGItm(RichInputText itGItm) {
        this.itGItm = itGItm;
    }

    public RichInputText getItGItm() {
        return itGItm;
    }

    public void setBtnverImagenProv(RichCommandButton btnverImagenProv) {
        this.btnverImagenProv = btnverImagenProv;
    }

    public RichCommandButton getBtnverImagenProv() {
        return btnverImagenProv;
    }

    public void setPopImagen1(RichPopup popImagen1) {
        this.popImagen1 = popImagen1;
    }

    public RichPopup getPopImagen1() {
        return popImagen1;
    }

    public void setFormVerImgProv(RichPanelFormLayout formVerImgProv) {
        this.formVerImgProv = formVerImgProv;
    }

    public RichPanelFormLayout getFormVerImgProv() {
        return formVerImgProv;
    }

    public void setFormVerImg(RichPanelFormLayout formVerImg) {
        this.formVerImg = formVerImg;
    }

    public RichPanelFormLayout getFormVerImg() {
        return formVerImg;
    }
}
