package silat.servicios_negocio.entidades.trans;

import java.io.Serializable;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@NamedQueries( { @NamedQuery(name = "TRNota.findAll", query = "select o from TRNota o") })
@Table(name = "TRDNOTA")
public class TRNota implements Serializable {
    @Column(name = "C_TIPO_NOTA", nullable = false, length = 1)
    private String cTipoNota;
    @Column(name = "D_MONTO", nullable = false)
    private BigDecimal dMonto;
    @Column(name = "N_ESTADO_NOTA", nullable = false)
    private Integer nEstadoNota;
    @Id
    @SequenceGenerator(name = "GEN_SQ_TRDNOTA_01",sequenceName = "SQ_TRDNOTA_01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_SQ_TRDNOTA_01")
    @Column(name = "NID_NOTA", nullable = false)
    private BigDecimal nidNota;
    @ManyToOne
    @JoinColumn(name = "NID_FACTURA")
    private TRFactura trFactura;

    public TRNota() {
    }

    public TRNota(String cTipoNota, BigDecimal dMonto, Integer nEstadoNota, TRFactura trFactura,
                  BigDecimal nidNota) {
        this.cTipoNota = cTipoNota;
        this.dMonto = dMonto;
        this.nEstadoNota = nEstadoNota;
        this.trFactura = trFactura;
        this.nidNota = nidNota;
    }

    public String getCTipoNota() {
        return cTipoNota;
    }

    public void setCTipoNota(String cTipoNota) {
        this.cTipoNota = cTipoNota;
    }

    public BigDecimal getDMonto() {
        return dMonto;
    }

    public void setDMonto(BigDecimal dMonto) {
        this.dMonto = dMonto;
    }

    public Integer getNEstadoNota() {
        return nEstadoNota;
    }

    public void setNEstadoNota(Integer nEstadoNota) {
        this.nEstadoNota = nEstadoNota;
    }


    public BigDecimal getNidNota() {
        return nidNota;
    }

    public void setNidNota(BigDecimal nidNota) {
        this.nidNota = nidNota;
    }

    public TRFactura getTrFactura() {
        return trFactura;
    }

    public void setTrFactura(TRFactura trFactura) {
        this.trFactura = trFactura;
    }
}
