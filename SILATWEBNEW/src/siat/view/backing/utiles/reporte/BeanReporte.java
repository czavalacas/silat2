package siat.view.backing.utiles.reporte;

import java.io.Serializable;

public class BeanReporte implements Serializable {
    @SuppressWarnings("compatibility:7647410354823637093")
    private static final long serialVersionUID = 1L;
    
    private String nombreParametro;
    private String valorParametro;
    private Object valorPrimitivo;

    public void setNombreParametro(String nombreParametro) {
        this.nombreParametro = nombreParametro;
    }

    public String getNombreParametro() {
        return nombreParametro;
    }

    public void setValorParametro(String valorParametro) {
        this.valorParametro = valorParametro;
    }

    public String getValorParametro() {
        return valorParametro;
    }

    public void setValorPrimitivo(Object valorPrimitivo) {
        this.valorPrimitivo = valorPrimitivo;
    }

    public Object getValorPrimitivo() {
        return valorPrimitivo;
    }
}
