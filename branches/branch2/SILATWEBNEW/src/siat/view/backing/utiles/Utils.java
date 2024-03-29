package siat.view.backing.utiles;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import oracle.adf.model.BindingContext;
import oracle.adf.model.bean.DCDataRow;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

import silat.servicios_negocio.Beans.BeanCombo;
import silat.servicios_negocio.Beans.BeanConstraint;

/** Clase con metodos utils sobre ADF y JSF
 * @author dfloresgonz
 * @version 1.0
 * @since 08.04.2013
 */
public class Utils {
    
    /**
     * @author dfloresgonz
     * @since 08.04.2013
     * @param k Clave unica de identificacion de la variable
     * @param v Valor de la variable a enviar en sesion
     */
    public static void putSession(String k, Object v) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(k, v);
    }
    
    public static void removeSession(String k){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(k);
    }
   
    public static Object getSession(String k){
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(k);
    }
    
    public static void redireccionar(String url){
        try{
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
            FacesContext.getCurrentInstance().responseComplete();
        }catch(Exception e){
            e.printStackTrace();
            e.getMessage();
        }
    }
    
    public static BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }
    
    public static void mandarParametro(String paramAlias, String el, String metodo){
        BindingContainer bindings = getBindings();
        OperationBinding oBinding = bindings.getOperationBinding(metodo);
        oBinding.getParamsMap().put(paramAlias,ADFUtil.evaluateEL(el));
    }

    public static void throwError(FacesContext ctx, 
                           String error, 
                           String cidError) {
        FacesMessage msg = null;
        if (cidError.equals("000")) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, error, error);
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
        }
        msg.setSummary(error);
        ctx.addMessage(null, msg);
    }
    
    /**
     *
     * @author dfloresgonz
     * @param ctx facesContext: FacesContext ctx = FacesContext.getCurrentInstance();
     * @param error Texto a mostrar
     * @param severidad SEVERITY_ERROR = 1,SEVERITY_FATAL = 2, SEVERITY_INFO = 3, SEVERITY_WARN = 4
     */
    public static void throwError_Aux(FacesContext ctx, 
                                     String error,
                                     int severidad) {
        FacesMessage msg = null;
        switch(severidad){
            case 1 : msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);break;
            case 2 : msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, error, error);break;
            case 3 : msg = new FacesMessage(FacesMessage.SEVERITY_INFO, error, error);break;
            case 4 : msg = new FacesMessage(FacesMessage.SEVERITY_WARN, error, error);break;
        }
        msg.setSummary(error);
        ctx.addMessage(null, msg);
    }
    
    public static void writeJavaScriptToClient(String script) {
        FacesContext fctx = FacesContext.getCurrentInstance();
        ExtendedRenderKitService erks = Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
        erks.addScript(fctx, script);
    }
    
    public static void depurar(Object o){
        System.out.println(o);
    }
    
    public static void addTarget(javax.faces.component.UIComponent componente){
        AdfFacesContext.getCurrentInstance().addPartialTarget(componente);
    }
    
    public static String showPopUp(RichPopup p,UIComponent compAlign) {
        try {
            RichPopup.PopupHints ph = new RichPopup.PopupHints();
            ph.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN, RichPopup.PopupHints.AlignTypes.ALIGN_AFTER_END);
            ph.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN_ID,compAlign);
            p.show(ph);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String showPopUpMIDDLE(RichPopup p) {
        try {
            RichPopup.PopupHints ph = new RichPopup.PopupHints();
            p.show(ph);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String showPopUpAFTER_START(RichPopup p,UIComponent compAlign) {
        try {
            RichPopup.PopupHints ph = new RichPopup.PopupHints();
            ph.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN, RichPopup.PopupHints.AlignTypes.ALIGN_AFTER_START);
            ph.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN_ID,compAlign);
            p.show(ph);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String showPopUp_AlignOverlap(RichPopup p,UIComponent compAlign) {
        try {
            RichPopup.PopupHints ph = new RichPopup.PopupHints();
            ph.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN, RichPopup.PopupHints.AlignTypes.ALIGN_OVERLAP);
            ph.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN_ID,compAlign);
            p.show(ph);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * FacesContext fctx = FacesContext.getCurrentInstance();
     * UIViewRoot root = fctx.getViewRoot();
     * UIComponent component = findComponent(fctx.getViewRoot(),clientId);
     * @param base
     * @param id
     * @return
     */
    public static UIComponent findComponent(UIComponent base, String id) {
        if (id.equals(base.getId()))
            return base;

        UIComponent children = null;
        UIComponent result = null;
        Iterator childrens = base.getFacetsAndChildren();
        while (childrens.hasNext() && (result == null)) {
            children = (UIComponent)childrens.next();
            if (id.equals(children.getId())) {
                result = children;
                break;
            }
            result = findComponent(children, id);
            if (result != null) {
                break;
            }
        }
        return result;
    }

    /**
     * OJO No enviar el mismo taskflowId desde donde se esta invocando este metodo, puede que no exista
     * el sessionbeanScoped y se caiga la aplicacion.
     * @param ctx FacesContext ctx = FacesContext.getCurrentInstance();
     * @param taskFLowId el mismo que esta en la tabla ADMPERM, xejm WEB-INF/consultar_guia.xml#consultar_guia
     */
    public static void _redireccionar(FacesContext ctx,String taskFLowId){
        UIComponent component = findComponent(ctx.getViewRoot(),"cl1");   
        RichCommandLink rcl = (RichCommandLink) component;
      //  rcl.setShortDesc(taskFLowId);
        putSession("url",taskFLowId);
        putSession("override","1");
        ActionEvent actionEvent = new ActionEvent(rcl);
        actionEvent.queue();
    }
    
    public static Object getTableSelected(RichTable table) {
        Object _selectedRowData = table.getSelectedRowData();
        JUCtrlHierNodeBinding _nodeBinding = (JUCtrlHierNodeBinding)_selectedRowData;
        DCDataRow rw = (DCDataRow)_nodeBinding.getRow();
        return rw.getDataProvider();
    }
    
    public static void executarJS(String js){
        StringBuffer script = new StringBuffer();
        ExtendedRenderKitService service = (ExtendedRenderKitService)Service.getRenderKitService(FacesContext.getCurrentInstance(),
                                                                            ExtendedRenderKitService.class);

        script.append(js);
        service.addScript(FacesContext.getCurrentInstance(),
                          script.toString());
    }
    
    public static void addTargetMany(javax.faces.component.UIComponent ... componente){
        for(int i = 0; i < componente.length; i++){
            addTarget(componente[i]);
        }
    }
    
    public static ArrayList llenarCombos(List<BeanConstraint> consts) {
        ArrayList confItems = new ArrayList();
        for (BeanConstraint r : consts) {
            confItems.add(new SelectItem(r.getCValorCampo().toString(), 
                                         r.getCDescrip().toString()));
        }
        return confItems;
    }
    
    public static ArrayList llenarBeanCombos(List<BeanCombo> combos) {
        ArrayList combs = new ArrayList();
        for (BeanCombo r : combos) {
            combs.add(new SelectItem(String.valueOf(r.getComboValue()), 
                                     r.getComboLabel().toString()));
        }
        return combs;
    }
    
    public static boolean hasPermiso(List<BigDecimal> lstPermisos,BigDecimal permiso){
        if(lstPermisos.contains(permiso)){
            return true;
        }else{
            return false;
        }
    }
    
    public static boolean validarExtensionImg(String nombreArchivo){
        String extension = nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1, nombreArchivo.length());
        if(extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("jpeg") || extension.equalsIgnoreCase("png")){
            return true;
        }else{
            return false;
        }
    }
    
    public static final String generarContrasena(){
        Random r;
        r = new Random();
        r.setSeed(new Date().getTime());
            for(int i = 0 ; i < 10 ; i++){
                int randomNumber = r.nextInt(999999);
                if(randomNumber>=100000 && randomNumber<=999999){
                    String contraseņa = String.valueOf(randomNumber);
                    return contraseņa;
                }
            }
        return "";
    }
    
    public static void unselectFilas(RichTable tabla){
        if(tabla != null){
            if(tabla.getSelectedRowKeys() != null ){
                tabla.getSelectedRowKeys().removeAll();
                addTarget(tabla);
            }
        }
    }
    
    public static String getChoiceLabel(ValueChangeEvent vce){
        String label = "";
        try { 
            RichSelectOneChoice csoc = (RichSelectOneChoice)vce.getComponent();
            UISelectItems itms = (UISelectItems)csoc.getChildren().get(0);
            List listaRoles = (List) itms.getValue();
            if(listaRoles != null){
                if(listaRoles.size() > 0){
                    for(int i = 0; i < listaRoles.size(); i++){
                        SelectItem selItm = (SelectItem) listaRoles.get(i);
                        if (((String)selItm.getValue()).equals((String)vce.getNewValue())) {
                            label = selItm.getLabel();
                            return label;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return label;
    }
}