package silat.servicios_negocio.entidades.trans;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;

import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries( { @NamedQuery(name = "TRFactura.findAll", query = "select o from TRFactura o") })
@Table(name = "TRMFACT")
public class TRFactura implements Serializable {
    @Column(name = "C_COD_FACT", nullable = false, length = 6)
    private String cCodFactura;
    @Column(name = "D_SUB_TOTAL", nullable = false)
    private BigDecimal dSubTotal;
    @Column(name = "D_TOTAL", nullable = false)
    private BigDecimal dTotal;
    @Column(name = "IGV", nullable = false)
    private BigDecimal igvFact;
    @Temporal(TemporalType.DATE)
    @Column(name = "F_FECHA_FACTURA", nullable = false)
    private Date fechaFactura;
    @Column(name = "N_ESTADO_FACTURA", nullable = false)
    private Integer nEstadoFactura;
    @Column(name = "CIDUNIN")
    private String cidUnidadNegocio;
    @Column(name = "N_TIPO_FACTURA", length = 1)
    private String nTipoFactura;
    @Id    
    @Column(name = "NID_FACTURA", nullable = false)
    @TableGenerator( name = "trfactura", table = "codigo", pkColumnName = "APP_SEQ_NAME", pkColumnValue = "trfactura.nid_factura", valueColumnName = "APP_SEQ_VALUE", initialValue = 50, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "trfactura" )
    private Long nidFactura;
    @OneToMany(mappedBy = "trFactura")
    private List<TRNota> notasList;
    @ManyToOne
    @JoinColumn(name = "NID_MANIFIESTO")
    private TRManifiesto trManifiesto;
    @OneToMany(mappedBy = "trFactura"/*,cascade = CascadeType.ALL ,fetch = FetchType.EAGER */)
    private List<TRGuia> guiasList;
    @Column(name = "CIDREPO", length = 30)
    private String cidRepo;
    @Column(name = "CIDGUIAS", length = 5000)
    private String guias;
    @Column(name = "NID_CLIENTE")
    private BigDecimal nidParty;
    @Column(name = "CLIENTE")
    private String cliente;
    @Column(name = "RUC")
    private String ruc;
    @Column(name = "ORDN_SERV")
    private String nidOS;
    @Column(name = "C_CMTANU")
    private String comentarioAnulacion;
    @Column(name = "CANT_FACT_PAG_OS")
    private int cantFacturasPagadasXOS;
    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_PAGO", nullable = false)
    private Date fechaPago;
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "DETRACCION", nullable = false)
    private BigDecimal detraccion;
    @Column(name = "TOTALDETRAC", nullable = false)
    private BigDecimal totalConDetraccion;
    @Column(name = "TIPFACTURA")
    private String tipoFactura;
    @Column(name = "NID_PREFACT")
    private Long nidPreFactura;
    @Column(name = "GUIAS_FOR_REPORT")
    private String guiasForReporte;
    @Column(name = "DETALLE")
    private String detalle;//
    @Column(name = "IS_EDITABLE")
    private String editable;
    @Column(name = "CONTENIDO")
    private String contenido;
    
    public TRFactura() {
    }

    public String getCCodFactura() {
        return cCodFactura;
    }

    public void setCCodFactura(String cCodFactura) {
        this.cCodFactura = cCodFactura;
    }


    public String getNTipoFactura() {
        return nTipoFactura;
    }

    public void setNTipoFactura(String nTipoFactura) {
        this.nTipoFactura = nTipoFactura;
    }

    public List<TRNota> getNotasList() {
        return notasList;
    }

    public void setNotasList(List<TRNota> notasList) {
        this.notasList = notasList;
    }

    public TRNota addTRNota(TRNota TRNota) {
        getNotasList().add(TRNota);
        TRNota.setTrFactura(this);
        return TRNota;
    }

    public TRNota removeTRNota(TRNota TRNota) {
        getNotasList().remove(TRNota);
        TRNota.setTrFactura(null);
        return TRNota;
    }

    public TRManifiesto getTrManifiesto() {
        return trManifiesto;
    }

    public void setTrManifiesto(TRManifiesto trManifiesto) {
        this.trManifiesto = trManifiesto;
    }

    public List<TRGuia> getGuiasList() {
        return guiasList;
    }

    public void setGuiasList(List<TRGuia> guiasList) {
        this.guiasList = guiasList;
    }

    public TRGuia addTRGuia(TRGuia TRGuia) {
        getGuiasList().add(TRGuia);
        TRGuia.setTrFactura(this);
        return TRGuia;
    }

    public TRGuia removeTRGuia(TRGuia TRGuia) {
        getGuiasList().remove(TRGuia);
        TRGuia.setTrFactura(null);
        return TRGuia;
    }
    
    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setNEstadoFactura(Integer nEstadoFactura) {
        this.nEstadoFactura = nEstadoFactura;
    }

    public Integer getNEstadoFactura() {
        return nEstadoFactura;
    }

    public void setCidUnidadNegocio(String cidUnidadNegocio) {
        this.cidUnidadNegocio = cidUnidadNegocio;
    }

    public String getCidUnidadNegocio() {
        return cidUnidadNegocio;
    }

    public void setNidFactura(Long nidFactura) {
        this.nidFactura = nidFactura;
    }

    public Long getNidFactura() {
        return nidFactura;
    }

    public void setDSubTotal(BigDecimal dSubTotal) {
        this.dSubTotal = dSubTotal;
    }

    public BigDecimal getDSubTotal() {
        return dSubTotal;
    }

    public void setDTotal(BigDecimal dTotal) {
        this.dTotal = dTotal;
    }

    public BigDecimal getDTotal() {
        return dTotal;
    }

    public void setIgvFact(BigDecimal igvFact) {
        this.igvFact = igvFact;
    }

    public BigDecimal getIgvFact() {
        return igvFact;
    }

    public void setCidRepo(String cidRepo) {
        this.cidRepo = cidRepo;
    }

    public String getCidRepo() {
        return cidRepo;
    }

    public void setGuias(String guias) {
        this.guias = guias;
    }

    public String getGuias() {
        return guias;
    }

    public void setNidParty(BigDecimal nidParty) {
        this.nidParty = nidParty;
    }

    public BigDecimal getNidParty() {
        return nidParty;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCliente() {
        return cliente;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRuc() {
        return ruc;
    }

    public void setComentarioAnulacion(String comentarioAnulacion) {
        this.comentarioAnulacion = comentarioAnulacion;
    }

    public String getComentarioAnulacion() {
        return comentarioAnulacion;
    }

    public void setCantFacturasPagadasXOS(int cantFacturasPagadasXOS) {
        this.cantFacturasPagadasXOS = cantFacturasPagadasXOS;
    }

    public int getCantFacturasPagadasXOS() {
        return cantFacturasPagadasXOS;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setNidOS(String nidOS) {
        this.nidOS = nidOS;
    }

    public String getNidOS() {
        return nidOS;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDetraccion(BigDecimal detraccion) {
        this.detraccion = detraccion;
    }

    public BigDecimal getDetraccion() {
        return detraccion;
    }

    public void setTotalConDetraccion(BigDecimal totalConDetraccion) {
        this.totalConDetraccion = totalConDetraccion;
    }

    public BigDecimal getTotalConDetraccion() {
        return totalConDetraccion;
    }

    public void setTipoFactura(String tipoFactura) {
        this.tipoFactura = tipoFactura;
    }

    public String getTipoFactura() {
        return tipoFactura;
    }

    public void setNidPreFactura(Long nidPreFactura) {
        this.nidPreFactura = nidPreFactura;
    }

    public Long getNidPreFactura() {
        return nidPreFactura;
    }

    public void setGuiasForReporte(String guiasForReporte) {
        this.guiasForReporte = guiasForReporte;
    }

    public String getGuiasForReporte() {
        return guiasForReporte;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setEditable(String editable) {
        this.editable = editable;
    }

    public String getEditable() {
        return editable;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getContenido() {
        return contenido;
    }
}
