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
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

import silat.servicios_negocio.entidades.audsis.TROrdenServicio;

@Entity
@NamedQueries( { @NamedQuery(name = "TRItemXOrds.findAll", query = "select o from TRItemXOrds o") })
@Table(name = "TRDITEMXORDS")
public class TRItemXOrds implements Serializable {
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
    @Id    
    @Column(name = "NID_ITEM", nullable = false)
    @TableGenerator( name = "tritemxords", table = "codigo", pkColumnName = "APP_SEQ_NAME", pkColumnValue = "tritemxords.nid_item", valueColumnName = "APP_SEQ_VALUE", initialValue = 50, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "tritemxords" )
    private BigDecimal nidItem;
    @ManyToOne
    @JoinColumn(name = "NID_ORDN_SERV")
    private TROrdenServicio trOrdenServicio;
    @Column(name = "ORDEN")
    private Integer orden;
    @Column(name = "C_ESTADO", nullable = true, length = 110)
    private String cEstado;
    @Column(name = "CID_GUIA", nullable = true, length = 110)
    private String cidGuia;

    public TRItemXOrds() {
    }

    public TRItemXOrds(String cCidGuiaRemitente, String cDescItem, String cUndMedida, Integer dPeso,
                  Double nCantidad, TROrdenServicio trOrdenServicio, BigDecimal nidItem) {
        this.cCidGuiaRemitente = cCidGuiaRemitente;
        this.cDescItem = cDescItem;
        this.cUndMedida = cUndMedida;
        this.dPeso = dPeso;
        this.nCantidad = nCantidad;
        this.trOrdenServicio = trOrdenServicio;
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
 
    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setTrOrdenServicio(TROrdenServicio trOrdenServicio) {
        this.trOrdenServicio = trOrdenServicio;
    }

    public TROrdenServicio getTrOrdenServicio() {
        return trOrdenServicio;
    }

    public void setCEstado(String cEstado) {
        this.cEstado = cEstado;
    }

    public String getCEstado() {
        return cEstado;
    }

    public void setCidGuia(String cidGuia) {
        this.cidGuia = cidGuia;
    }

    public String getCidGuia() {
        return cidGuia;
    }
}
