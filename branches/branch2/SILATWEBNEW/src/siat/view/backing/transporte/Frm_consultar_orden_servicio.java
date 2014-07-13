package siat.view.backing.transporte;

import java.math.BigDecimal;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlOutputText;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichDecorativeBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adfinternal.view.faces.model.binding.FacesCtrlHierNodeBinding;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import siat.view.backing.utiles.ADFUtil;

import silat.servicios_negocio.Beans.BeanOrdenServicio;
import silat.servicios_negocio.Beans.BeanUnidadMedida;

public class Frm_consultar_orden_servicio {
    private RichDecorativeBox db1;
    private RichInputDate id1;
    private RichCommandButton cb1;
    private RichPanelFormLayout pfl2;
    private RichInputText it1;
    private RichInputText it2;
    private RichInputText it3;
    private RichCommandButton cb2;
    private HtmlOutputText ot4;
    private RichPanelGroupLayout pgl2;
    private RichPanelGroupLayout pgl1;
    private RichPopup p1;
    private RichCommandButton cb3;
    private RichTable t2;
    // mis variables
    private RichPanelSplitter ps1;
    private RichPanelGroupLayout pgl3;
    private RichPanelGroupLayout pgl4;
    private RichTable t1;

    public void setDb1(RichDecorativeBox db1) {
        this.db1 = db1;
    }

    public RichDecorativeBox getDb1() {
        return db1;
    }

    public void getActualizar(SelectionEvent selectionEvent){
       RichTable t = (RichTable)selectionEvent.getSource();
       FacesCtrlHierNodeBinding rowData = (FacesCtrlHierNodeBinding)t.getSelectedRowData();
       Integer nidOrdnServ = rowData.getAttribute("nidOrdnServ").hashCode();
       try{
           it1.setValue(nidOrdnServ);
           AdfFacesContext.getCurrentInstance().addPartialTarget(it1);
       }catch(Exception e){
       }
    }

    
     /**
     *Metodo para abrir popups desde BackingBean
     *@author Jorge Cespedes
     *@since  10.05.2013
     *@params recordar que donde dice cb3 es el id del componente que se usa y p1 es el id del popup
     */
    public String registrarAction(){
        try{
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            hints.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN_ID, cb3)
                 .add(RichPopup.PopupHints.HintTypes.HINT_LAUNCH_ID, cb3)
                 .add(RichPopup.PopupHints.HintTypes.HINT_ALIGN, 
                     RichPopup.PopupHints.AlignTypes.ALIGN_AFTER_END);
            p1.show(hints);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public void setId1(RichInputDate id1) {
        this.id1 = id1;
    }

    public RichInputDate getId1() {
        return id1;
    }

    public void setCb1(RichCommandButton cb1) {
        this.cb1 = cb1;
    }

    public RichCommandButton getCb1() {
        return cb1;
    }


    public void setPfl2(RichPanelFormLayout pfl2) {
        this.pfl2 = pfl2;
    }

    public RichPanelFormLayout getPfl2() {
        return pfl2;
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

    public void setIt3(RichInputText it3) {
        this.it3 = it3;
    }

    public RichInputText getIt3() {
        return it3;
    }

    public void setCb2(RichCommandButton cb2) {
        this.cb2 = cb2;
    }

    public RichCommandButton getCb2() {
        return cb2;
    }

    public void setOt4(HtmlOutputText ot4) {
        this.ot4 = ot4;
    }

    public HtmlOutputText getOt4() {
        return ot4;
    }

    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }


    public void setP1(RichPopup p1) {
        this.p1 = p1;
    }

    public RichPopup getP1() {
        return p1;
    }


    public void setCb3(RichCommandButton cb3) {
        this.cb3 = cb3;
    }

    public RichCommandButton getCb3() {
        return cb3;
    }

    public void setT2(RichTable t2) {
        this.t2 = t2;
    }

    public RichTable getT2() {
        return t2;
    }

    public void SelectedList(ActionEvent actionEvent) {
        // Add event code here...
    }

    public void setPs1(RichPanelSplitter ps1) {
        this.ps1 = ps1;
    }

    public RichPanelSplitter getPs1() {
        return ps1;
    }

    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
    }

    public void setPgl4(RichPanelGroupLayout pgl4) {
        this.pgl4 = pgl4;
    }

    public RichPanelGroupLayout getPgl4() {
        return pgl4;
    }

    public void setT1(RichTable t1) {
        this.t1 = t1;
    }

    public RichTable getT1() {
        return t1;
    }
}
