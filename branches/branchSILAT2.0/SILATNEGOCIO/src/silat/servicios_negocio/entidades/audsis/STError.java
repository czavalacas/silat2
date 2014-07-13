package silat.servicios_negocio.entidades.audsis;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries( { @NamedQuery(name = "STError.findAll", query = "select o from STError o") })
@Table(name = "STMERRO")
public class STError implements Serializable {
    @Column(name = "C_ABRV", nullable = false, length = 50)
    private String cAbreviatura;
    @Column(name = "C_DESC_ERROR", nullable = false, length = 100)
    private String cDescripcionError;
    @Column(name = "C_ESTADO", nullable = false, length = 1)
    private String cEstadoError;
    @Id
    @Column(name = "CID_ERROR", nullable = false, length = 8)
    private String cidError;

    public STError() {
    }

    public STError(String cAbreviatura, String cDescripcionError, String cEstadoError, String cidError) {
        this.cAbreviatura = cAbreviatura;
        this.cDescripcionError = cDescripcionError;
        this.cEstadoError = cEstadoError;
        this.cidError = cidError;
    }

    public String getCAbreviatura() {
        return cAbreviatura;
    }

    public void setCAbreviatura(String cAbreviatura) {
        this.cAbreviatura = cAbreviatura;
    }

    public String getCDescripcionError() {
        return cDescripcionError;
    }

    public void setCDescripcionError(String cDescripcionError) {
        this.cDescripcionError = cDescripcionError;
    }

    public String getCEstadoError() {
        return cEstadoError;
    }

    public void setCEstadoError(String cEstadoError) {
        this.cEstadoError = cEstadoError;
    }

    public String getCidError() {
        return cidError;
    }

    public void setCidError(String cidError) {
        this.cidError = cidError;
    }
}
