package silat.servicios_negocio.Beans;

import java.io.Serializable;

public class BeanUnidadNegocio implements Serializable {
    @SuppressWarnings("compatibility:-8117961110875931349")
    private static final long serialVersionUID = 1L;
    
    private String cidUnidadNegocio;
    private String descripcionUnidadNegocio;

    public void setCidUnidadNegocio(String cidUnidadNegocio) {
        this.cidUnidadNegocio = cidUnidadNegocio;
    }

    public String getCidUnidadNegocio() {
        return cidUnidadNegocio;
    }

    public void setDescripcionUnidadNegocio(String descripcionUnidadNegocio) {
        this.descripcionUnidadNegocio = descripcionUnidadNegocio;
    }

    public String getDescripcionUnidadNegocio() {
        return descripcionUnidadNegocio;
    }
}