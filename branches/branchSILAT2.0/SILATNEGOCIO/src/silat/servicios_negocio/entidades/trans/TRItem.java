package silat.servicios_negocio.entidades.trans;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@NamedQueries( { @NamedQuery(name = "TRItem.findAll", query = "select o from TRItem o") })
@Table(name = "TRDITEM")
public class TRItem implements Serializable {
    @Column(name = "C_CID_GUIA_REMITENTE")
    private String cCidGuiaRemitente;
    @Column(name = "C_DESC_ITEM", nullable = false, length = 110)
    private String cDescItem;
    @Column(name = "C_UND_MEDIDA")
    private String cUndMedida;
    @Column(name = "D_PESO")
    private Integer dPeso;
    @Column(name = "N_CANTIDAD")
    private Double nCantidad;
    @Id//
    @SequenceGenerator(name = "GEN_SQ_TRDITEM_01",sequenceName = "SQ_TRDITEM_01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_SQ_TRDITEM_01")
    @Column(name = "NID_ITEM", nullable = false)
    private BigDecimal nidItem;
    @ManyToOne
    @JoinColumn(name = "CID_GUIA")
    private TRGuia trGuia;
    @Column(name = "ORDEN")
    private Integer orden;

    public TRItem() {
    }

    public TRItem(String cCidGuiaRemitente, String cDescItem, String cUndMedida, Integer dPeso,
                  Double nCantidad, TRGuia trGuia, BigDecimal nidItem) {
        this.cCidGuiaRemitente = cCidGuiaRemitente;
        this.cDescItem = cDescItem;
        this.cUndMedida = cUndMedida;
        this.dPeso = dPeso;
        this.nCantidad = nCantidad;
        this.trGuia = trGuia;
        this.nidItem = nidItem;
    }

    public String getCCidGuiaRemitente() {
        return cCidGuiaRemitente;
    }

    public void setCCidGuiaRemitente(String cCidGuiaRemitente) {
        this.cCidGuiaRemitente = cCidGuiaRemitente;
    }

    public String getCDescItem() {
        return cDescItem;
    }

    public void setCDescItem(String cDescItem) {
        this.cDescItem = cDescItem;
    }

    public String getCUndMedida() {
        return cUndMedida;
    }

    public void setCUndMedida(String cUndMedida) {
        this.cUndMedida = cUndMedida;
    }

    public Integer getDPeso() {
        return dPeso;
    }

    public void setDPeso(Integer dPeso) {
        this.dPeso = dPeso;
    }

    public Double getNCantidad() {
        return nCantidad;
    }

    public void setNCantidad(Double nCantidad) {
        this.nCantidad = nCantidad;
    }


    public BigDecimal getNidItem() {
        return nidItem;
    }

    public void setNidItem(BigDecimal nidItem) {
        this.nidItem = nidItem;
    }

    public TRGuia getTrGuia() {
        return trGuia;
    }

    public void setTrGuia(TRGuia trGuia) {
        this.trGuia = trGuia;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Integer getOrden() {
        return orden;
    }
}
