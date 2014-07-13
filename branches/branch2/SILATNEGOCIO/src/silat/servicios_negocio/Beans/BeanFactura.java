package silat.servicios_negocio.Beans;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;

public class BeanFactura implements Serializable {
    @SuppressWarnings("compatibility:-9118406617576164383")
    private static final long serialVersionUID = 1L;
    
    private String cCodFactura;
    private BigDecimal dSubTotal;
    private BigDecimal dTotal;
    private BigDecimal igvFact;
    private Date fechaFactura;
    private Integer nEstadoFactura;
    private String cidUnidadNegocio;
    private String nTipoFactura;
    private Long nidFactura;
    //private List<TRNota> notasList;
    private BeanManifiesto trManifiesto;
    private BeanNota beanNota;
    private List<BeanTRGuia> guiasLista;
    BeanError beanError = new BeanError();
    private String cidRepo;
    private String guias;
    private BigDecimal nidParty;
    private String cliente;
    private Date fecMin; 
    private Date fecMax;
    private String simbSubTotal;
    private String simbTotal;
    private String descEstado;
    private String ruc;
    private String comentarioAnulacion;
    private String nidOS;
    private int cantFacturasPagadasXOS;
    private Date fechaPago;
    private String direccion;
    private BigDecimal detraccion;
    private BigDecimal totalConDetraccion;
    private String tipoFactura;
    private Long nidPreFactura;
    private String guiasForReporte;
    private String detalle;
    private String editable;
    private String contenido;
    private String styleEstado;//dfloresgonz 12.06.2014

    public void setCCodFactura(String cCodFactura) {
        this.cCodFactura = cCodFactura;
    }

    public String getCCodFactura() {
        return cCodFactura;
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

    public void setNTipoFactura(String nTipoFactura) {
        this.nTipoFactura = nTipoFactura;
    }

    public String getNTipoFactura() {
        return nTipoFactura;
    }

    public void setNidFactura(Long nidFactura) {
        this.nidFactura = nidFactura;
    }

    public Long getNidFactura() {
        return nidFactura;
    }

    public void setTrManifiesto(BeanManifiesto trManifiesto) {
        this.trManifiesto = trManifiesto;
    }

    public BeanManifiesto getTrManifiesto() {
        return trManifiesto;
    }

    public void setGuiasLista(List<BeanTRGuia> guiasLista) {
        this.guiasLista = guiasLista;
    }

    public List<BeanTRGuia> getGuiasLista() {
        return guiasLista;
    }

    public void setBeanError(BeanError beanError) {
        this.beanError = beanError;
    }

    public BeanError getBeanError() {
        return beanError;
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

    public void setFecMin(Date fecMin) {
        this.fecMin = fecMin;
    }

    public Date getFecMin() {
        return fecMin;
    }

    public void setFecMax(Date fecMax) {
        this.fecMax = fecMax;
    }

    public Date getFecMax() {
        return fecMax;
    }

    public void setSimbSubTotal(String simbSubTotal) {
        this.simbSubTotal = simbSubTotal;
    }

    public String getSimbSubTotal() {
        return simbSubTotal;
    }

    public void setSimbTotal(String simbTotal) {
        this.simbTotal = simbTotal;
    }

    public String getSimbTotal() {
        return simbTotal;
    }

    public void setDescEstado(String descEstado) {
        this.descEstado = descEstado;
    }

    public String getDescEstado() {
        return descEstado;
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

    public void setBeanNota(BeanNota beanNota) {
        this.beanNota = beanNota;
    }

    public BeanNota getBeanNota() {
        return beanNota;
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

    public void setStyleEstado(String styleEstado) {
        this.styleEstado = styleEstado;
    }

    public String getStyleEstado() {
        return styleEstado;
    }
}
