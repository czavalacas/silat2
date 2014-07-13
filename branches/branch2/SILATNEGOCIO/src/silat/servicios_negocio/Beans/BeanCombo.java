package silat.servicios_negocio.Beans;

import java.io.Serializable;

public class BeanCombo implements Serializable {

    @SuppressWarnings("compatibility:-7329009168249922443")
    private static final long serialVersionUID = 1L;
    
    private int comboValue;
    private String comboLabel;

    public BeanCombo(Integer valor, String label){
        this.comboValue = valor;
        this.comboLabel = label;
    }

    public BeanCombo(){
    }

    public void setComboLabel(String comboLabel) {
        this.comboLabel = comboLabel;
    }

    public String getComboLabel() {
        return comboLabel;
    }

    public void setComboValue(int comboValue) {
        this.comboValue = comboValue;
    }

    public int getComboValue() {
        return comboValue;
    }
}
