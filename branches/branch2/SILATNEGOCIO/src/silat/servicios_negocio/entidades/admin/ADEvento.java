package silat.servicios_negocio.entidades.admin;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries( { @NamedQuery(name = "ADEvento.findAll", query = "select o from ADEvento o") })
@Table(name = "ADMEVEN")
public class ADEvento implements Serializable {
    @Column(name = "C_DESC_EVENTO", nullable = false, length = 50)
    private String cDescEvento;
    @Column(name = "N_ESTADO_EVENTO")
    private BigDecimal nEstadoEvento;
    @Id
    @Column(name = "NID_EVENTO", nullable = false)
    private BigDecimal nidEvento;
    @OneToMany(mappedBy = "adEvento")
    private List<AULogg> logsList;

    public ADEvento() {
    }

    public ADEvento(String cDescEvento, BigDecimal nEstadoEvento, BigDecimal nidEvento) {
        this.cDescEvento = cDescEvento;
        this.nEstadoEvento = nEstadoEvento;
        this.nidEvento = nidEvento;
    }

    public String getCDescEvento() {
        return cDescEvento;
    }

    public void setCDescEvento(String cDescEvento) {
        this.cDescEvento = cDescEvento;
    }

    public BigDecimal getNEstadoEvento() {
        return nEstadoEvento;
    }

    public void setNEstadoEvento(BigDecimal nEstadoEvento) {
        this.nEstadoEvento = nEstadoEvento;
    }

    public BigDecimal getNidEvento() {
        return nidEvento;
    }

    public void setNidEvento(BigDecimal nidEvento) {
        this.nidEvento = nidEvento;
    }

    public List<AULogg> getLogsList() {
        return logsList;
    }

    public void setLogsList(List<AULogg> logsList) {
        this.logsList = logsList;
    }

    public AULogg addAULogg(AULogg AULogg) {
        getLogsList().add(AULogg);
        AULogg.setAdEvento(this);
        return AULogg;
    }

    public AULogg removeAULogg(AULogg AULogg) {
        getLogsList().remove(AULogg);
        AULogg.setAdEvento(null);
        return AULogg;
    }
}
