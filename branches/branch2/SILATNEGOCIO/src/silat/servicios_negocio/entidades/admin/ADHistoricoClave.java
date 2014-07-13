package silat.servicios_negocio.entidades.admin;

import java.io.Serializable;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries( { @NamedQuery(name = "ADHistoricoClave.findAll", query = "select o from ADHistoricoClave o") })
@Table(name = "ADDHISC")
public class ADHistoricoClave implements Serializable {
    @Column(name = "N_CALIDAD_CLAVE", nullable = false)
    private BigDecimal nCalidadClave;
    @Column(name = "N_DIAS_VIGENCIA", nullable = false)
    private BigDecimal nDiasVigencia;
    @Column(name = "N_LONG_CLAVE", nullable = false)
    private BigDecimal nLongitudClave;
    @Id
    @Column(name = "NID_HISTORIAL", nullable = false)
    private BigDecimal nidHistorial;
    @ManyToOne
    @JoinColumn(name = "NID_CLAVE")
    private ADClave adClave;

    public ADHistoricoClave() {
    }

    public ADHistoricoClave(BigDecimal nCalidadClave, BigDecimal nDiasVigencia, BigDecimal nLongitudClave,
                            ADClave adClave, BigDecimal nidHistorial) {
        this.nCalidadClave = nCalidadClave;
        this.nDiasVigencia = nDiasVigencia;
        this.nLongitudClave = nLongitudClave;
        this.adClave = adClave;
        this.nidHistorial = nidHistorial;
    }

    public BigDecimal getNCalidadClave() {
        return nCalidadClave;
    }

    public void setNCalidadClave(BigDecimal nCalidadClave) {
        this.nCalidadClave = nCalidadClave;
    }

    public BigDecimal getNDiasVigencia() {
        return nDiasVigencia;
    }

    public void setNDiasVigencia(BigDecimal nDiasVigencia) {
        this.nDiasVigencia = nDiasVigencia;
    }

    public BigDecimal getNLongitudClave() {
        return nLongitudClave;
    }

    public void setNLongitudClave(BigDecimal nLongitudClave) {
        this.nLongitudClave = nLongitudClave;
    }


    public BigDecimal getNidHistorial() {
        return nidHistorial;
    }

    public void setNidHistorial(BigDecimal nidHistorial) {
        this.nidHistorial = nidHistorial;
    }

    public ADClave getAdClave() {
        return adClave;
    }

    public void setAdClave(ADClave adClave) {
        this.adClave = adClave;
    }
}