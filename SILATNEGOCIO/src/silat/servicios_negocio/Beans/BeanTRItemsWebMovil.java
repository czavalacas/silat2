package silat.servicios_negocio.Beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class BeanTRItemsWebMovil implements Serializable {

    /**
     * Este Bean creado por motivos de uso de WebMovil
     * @author: Ricardo Vasquez
     * @Fecha: 14/10/2014
     * @see Frm_WebMovil
     */
    @SuppressWarnings("compatibility:4729916666598171982")
    private static final long serialVersionUID = 1L;
    private String Descripcion;
    private String UMedida;
    private Integer Peso;
    private Double Cantidad;
    private BigDecimal idItem;

    public void setIdItem(BigDecimal idItem) {
        this.idItem = idItem;
    }

    public BigDecimal getIdItem() {
        return idItem;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setUMedida(String UMedida) {
        this.UMedida = UMedida;
    }

    public String getUMedida() {
        return UMedida;
    }

    public void setPeso(Integer Peso) {
        this.Peso = Peso;
    }

    public Integer getPeso() {
        return Peso;
    }

    public void setCantidad(Double Cantidad) {
        this.Cantidad = Cantidad;
    }

    public Double getCantidad() {
        return Cantidad;
    }
}
