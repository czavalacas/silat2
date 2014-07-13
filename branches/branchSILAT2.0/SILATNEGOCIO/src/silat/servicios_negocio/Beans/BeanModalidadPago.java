package silat.servicios_negocio.Beans;

import java.io.Serializable;
import java.util.List;

public class BeanModalidadPago implements Serializable {
    @SuppressWarnings("compatibility:-7215668011907323521")
    private static final long serialVersionUID = 1L;
    private String estadoRegistro;
    private String descripcionModalidadPago;
    private Integer nidModalidadPago;
    private List<BeanGasto> gastosList;//para el dozzermapper no se pone igual el nombre de las listas

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setDescripcionModalidadPago(String descripcionModalidadPago) {
        this.descripcionModalidadPago = descripcionModalidadPago;
    }

    public String getDescripcionModalidadPago() {
        return descripcionModalidadPago;
    }

    public void setNidModalidadPago(Integer nidModalidadPago) {
        this.nidModalidadPago = nidModalidadPago;
    }

    public Integer getNidModalidadPago() {
        return nidModalidadPago;
    }

    public void setGastosList(List<BeanGasto> gastosList) {
        this.gastosList = gastosList;
    }

    public List<BeanGasto> getGastosList() {
        return gastosList;
    }
}
