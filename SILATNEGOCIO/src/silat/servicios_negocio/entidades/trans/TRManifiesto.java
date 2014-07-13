package silat.servicios_negocio.entidades.trans;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import silat.servicios_negocio.entidades.admin.ADEmpresa;
import silat.servicios_negocio.entidades.audsis.TROrdenServicio;

@Entity
@NamedQueries( { @NamedQuery(name = "TRManifiesto.findAll", query = "select o from TRManifiesto o") })
@Table(name = "TRMMANI")
public class TRManifiesto implements Serializable {
    @Column(name = "C_OBSERVACIONES", length = 500)
    private String cObservaciones;
    @Column(name = "C_TIPO_DOC", length = 1)
    private String cTipoDoc;
    @Temporal(TemporalType.DATE)
    @Column(name = "F_FECHA_MANIFIESTO", nullable = false)
    private Date fechaManifiesto;
    @Column(name = "N_ADELANTO")
    private Double nAdelanto;
    @Column(name = "N_EST_MANIFIESTO", nullable = false)
    private Integer nEstManifiesto;
    @Column(name = "N_FLETE_PACTADO", nullable = false)
    private Double nFletePactado;
    @Id
    @SequenceGenerator(name = "GEN_SQ_TRMMANI_01",sequenceName = "SQ_TRMMANI_01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_SQ_TRMMANI_01")
    @Column(name = "NID_MANIFIESTO")
    private Integer nidManifiesto;
    @OneToMany(mappedBy = "trManifiesto")
    private List<TRGuia> guiasList;
    @OneToMany(mappedBy = "trManifiesto")
    private List<TRFactura> facturasList;
    @ManyToOne
    @JoinColumn(name = "NID_PROV_TRANS")
    private ADEmpresa trManifiesto;
    @Column(name = "NID_CHOF")
    private Integer nidChof;
    @Column(name = "NID_FLOTA")
    private Integer nidFlota;
    @Column(name = "IGV")
    private Double igv;
    @Column(name = "MONTOFIN")
    private Double detraccion;//MONTO FINAL
    @Column(name = "ESTMANI", length = 1)
    private String estadoManifiestoNegocio;
    @Column(name = "DETRA")
    private Double detraccionReal;

    public TRManifiesto() {
    }

    public String getCObservaciones() {
        return cObservaciones;
    }

    public void setCObservaciones(String cObservaciones) {
        this.cObservaciones = cObservaciones;
    }

    public String getCTipoDoc() {
        return cTipoDoc;
    }

    public void setCTipoDoc(String cTipoDoc) {
        this.cTipoDoc = cTipoDoc;
    }

    public Date getFechaManifiesto() {
        return fechaManifiesto;
    }

    public void setFechaManifiesto(Date fechaManifiesto) {
        this.fechaManifiesto = fechaManifiesto;
    }

    public Double getNAdelanto() {
        return nAdelanto;
    }

    public void setNAdelanto(Double nAdelanto) {
        this.nAdelanto = nAdelanto;
    }

    public Integer getNEstManifiesto() {
        return nEstManifiesto;
    }

    public void setNEstManifiesto(Integer nEstManifiesto) {
        this.nEstManifiesto = nEstManifiesto;
    }

    public Double getNFletePactado() {
        return nFletePactado;
    }

    public void setNFletePactado(Double nFletePactado) {
        this.nFletePactado = nFletePactado;
    }

    public Integer getNidManifiesto() {
        return nidManifiesto;
    }

    public void setNidManifiesto(Integer nidManifiesto) {
        this.nidManifiesto = nidManifiesto;
    }


    public List<TRGuia> getGuiasList() {
        return guiasList;
    }

    public void setGuiasList(List<TRGuia> guiasList) {
        this.guiasList = guiasList;
    }

    public TRGuia addTRGuia(TRGuia TRGuia) {
        getGuiasList().add(TRGuia);
        TRGuia.setTrManifiesto(this);
        return TRGuia;
    }

    public TRGuia removeTRGuia(TRGuia TRGuia) {
        getGuiasList().remove(TRGuia);
        TRGuia.setTrManifiesto(null);
        return TRGuia;
    }

    public List<TRFactura> getFacturasList() {
        return facturasList;
    }

    public void setFacturasList(List<TRFactura> facturasList) {
        this.facturasList = facturasList;
    }

    public TRFactura addTRFactura(TRFactura TRFactura) {
        getFacturasList().add(TRFactura);
        TRFactura.setTrManifiesto(this);
        return TRFactura;
    }

    public TRFactura removeTRFactura(TRFactura TRFactura) {
        getFacturasList().remove(TRFactura);
        TRFactura.setTrManifiesto(null);
        return TRFactura;
    }

    public ADEmpresa getTrManifiesto() {
        return trManifiesto;
    }

    public void setTrManifiesto(ADEmpresa trManifiesto) {
        this.trManifiesto = trManifiesto;
    }

    public void setNidChof(Integer nidChof) {
        this.nidChof = nidChof;
    }

    public Integer getNidChof() {
        return nidChof;
    }

    public void setNidFlota(Integer nidFlota) {
        this.nidFlota = nidFlota;
    }

    public Integer getNidFlota() {
        return nidFlota;
    }

    public void setIgv(Double igv) {
        this.igv = igv;
    }

    public Double getIgv() {
        return igv;
    }

    public void setDetraccion(Double detraccion) {
        this.detraccion = detraccion;
    }

    public Double getDetraccion() {
        return detraccion;
    }

    public void setEstadoManifiestoNegocio(String estadoManifiestoNegocio) {
        this.estadoManifiestoNegocio = estadoManifiestoNegocio;
    }

    public String getEstadoManifiestoNegocio() {
        return estadoManifiestoNegocio;
    }

    public void setDetraccionReal(Double detraccionReal) {
        this.detraccionReal = detraccionReal;
    }

    public Double getDetraccionReal() {
        return detraccionReal;
    }
}
