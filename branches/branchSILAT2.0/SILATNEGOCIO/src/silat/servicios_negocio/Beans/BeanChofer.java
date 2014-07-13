package silat.servicios_negocio.Beans;

import java.io.Serializable;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Id;

public class BeanChofer implements Serializable {
    @SuppressWarnings("compatibility:1036805861026205459")
    private static final long serialVersionUID = 1L;
    
    private String estadoRegistro;
    private String licencia;
    private String nombres;
    private Integer nidChofer;
    private Integer nidEmpresa;

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNidChofer(Integer nidChofer) {
        this.nidChofer = nidChofer;
    }

    public Integer getNidChofer() {
        return nidChofer;
    }

    public void setNidEmpresa(Integer nidEmpresa) {
        this.nidEmpresa = nidEmpresa;
    }

    public Integer getNidEmpresa() {
        return nidEmpresa;
    }
}
