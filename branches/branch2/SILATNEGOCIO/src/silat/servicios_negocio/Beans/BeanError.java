package silat.servicios_negocio.Beans;

import java.io.Serializable;

public class BeanError implements Serializable{

    @SuppressWarnings("compatibility:7156263231781149938")
    private static final long serialVersionUID = 1L;
    private String cidError;
    private String cAbreviatura;
    private String cDescripcionError;
    private String cEstadoError;

    public void setCidError(String cidError) {
        this.cidError = cidError;
    }

    public String getCidError() {
        return cidError;
    }

    public void setCAbreviatura(String cAbreviatura) {
        this.cAbreviatura = cAbreviatura;
    }

    public String getCAbreviatura() {
        return cAbreviatura;
    }

    public void setCDescripcionError(String cDescripcionError) {
        this.cDescripcionError = cDescripcionError;
    }

    public String getCDescripcionError() {
        return cDescripcionError;
    }

    public void setCEstadoError(String cEstadoError) {
        this.cEstadoError = cEstadoError;
    }

    public String getCEstadoError() {
        return cEstadoError;
    }
}
