package silat.servicios_negocio.Beans;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import silat.servicios_negocio.util_formato.UtilsGeneral;

public class BeanCuadre implements Serializable {
    @SuppressWarnings("compatibility:5162855324934292965")
    private static final long serialVersionUID = 1L;
    
    private String descCuadre;
    private BigDecimal ingreso;
    private BigDecimal egreso;
    private BigDecimal balance;
    private String color;
    private List<BeanCuadre> lstSubCuadres = new ArrayList<BeanCuadre>();

    public void setDescCuadre(String descCuadre) {
        this.descCuadre = descCuadre;
    }

    public String getDescCuadre() {
        return descCuadre;
    }

    public void setIngreso(BigDecimal ingreso) {
        this.ingreso = ingreso;
    }

    public BigDecimal getIngreso() {
        return ingreso;
    }

    public void setEgreso(BigDecimal egreso) {
        this.egreso = egreso;
    }

    public BigDecimal getEgreso() {
        return egreso;
    }

    public void setLstSubCuadres(List<BeanCuadre> lstSubCuadres) {
        this.lstSubCuadres = lstSubCuadres;
    }

    public List<BeanCuadre> getLstSubCuadres() {
        return lstSubCuadres;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return ingreso.subtract(egreso);
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        BigDecimal bala = ingreso.subtract(egreso);
        String col = "";
        if(bala.compareTo(new BigDecimal(0)) == 0){
            col = "color:Orange;"; 
        }else if(bala.compareTo(new BigDecimal(0)) == -1){
            col = "color:Red;";
        }else{
            col = "color:Green;";
        }
        return col;
    }
}
