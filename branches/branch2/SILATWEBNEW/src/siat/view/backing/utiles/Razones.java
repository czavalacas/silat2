package siat.view.backing.utiles;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import javax.faces.component.UISelectItems;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.event.ReturnPopupDataEvent;

public class Razones {
private Map ListRazones;
private String itemValue;
private String itemLabel;
private UISelectItems si1;
private RichSelectOneChoice soc1;
    public Razones() {
        super();
        
    }


    public ArrayList llenarRazonCombo() {
        ArrayList RazonItems = new ArrayList();
        RazonItems.add(new SelectItem("RUC"));
        RazonItems.add(new SelectItem("Razon Social"));
        return RazonItems;
    }

    public void iclov1_returnPopupDataListener(ReturnPopupDataEvent returnPopupDataEvent) {
        // Add event code here...
    }
    public void razonesChangeListener(ValueChangeEvent event) {
     itemValue = (String) event.getNewValue();
        ListRazones.put(0, "RUC");
        ListRazones.put(1, "Razon Social");
    }

    public void llenarRazonesCombo(ReturnPopupDataEvent returnPopupDataEvent) {
        llenarRazonCombo();
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public String getItemLabel() {
        return itemLabel;
    }

    public void setItemLabel(String itemLabel) {
        this.itemLabel = itemLabel;
    }

    public UISelectItems getSi1() {
        return si1;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public RichSelectOneChoice getSoc1() {
        return soc1;
    }

    public void setSoc1(RichSelectOneChoice soc1) {
        this.soc1 = soc1;
    }

    public Map getListRazones() {
        if(ListRazones == null){
            getListRazones().put(0, "RUC");
            getListRazones().put(1, "Razon Social");
        }
        return ListRazones;
    }

    public void setListRazones(Map ListRazones) {
        this.ListRazones = ListRazones;
    }
}
