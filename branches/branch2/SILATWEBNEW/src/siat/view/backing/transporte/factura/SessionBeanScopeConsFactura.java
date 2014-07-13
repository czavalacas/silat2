package siat.view.backing.transporte.factura;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import silat.servicios_negocio.Beans.BeanFactura;

public class SessionBeanScopeConsFactura { 
    private int exec = 0;
    private List listaUNs = new ArrayList();
    private List<BeanFactura> lstFacturas = new ArrayList<BeanFactura>();
    private BeanFactura facturaSelect = new BeanFactura();
    private String sourcePrevio;
    /*campos de busqueda*/
    private Date fecMin;
    private Date fecMax;
    private String codFact; 
    private String codGuia;
    private String cliente;
    private String simboloSubTotal;
    private BigDecimal subTotal;
    private String simboloTotal;
    private BigDecimal total;
    private String estadoFactura;
    private String cidUn;
    private String comentarioAnular;
    private String tipNota;
    private BigDecimal montoNota;
    private String busqTipNota;
    private String simbNota;
    private BigDecimal busqMontoNota;
    private boolean renderIsContador = true;
    private Date fechaFactura;
    private String detalleFactura;
    private boolean renderBtnFecha = false;
    private boolean renderPagarFactura57 = false;

    public void setExec(int exec) { 
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }

    public void setListaUNs(List listaUNs) {
        this.listaUNs = listaUNs;
    }

    public List getListaUNs() {
        return listaUNs;
    }

    public void setLstFacturas(List<BeanFactura> lstFacturas) {
        this.lstFacturas = lstFacturas;
    }

    public List<BeanFactura> getLstFacturas() {
        return lstFacturas;
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

    public void setCodFact(String codFact) {
        this.codFact = codFact;
    }

    public String getCodFact() {
        return codFact;
    }

    public void setCodGuia(String codGuia) {
        this.codGuia = codGuia;
    }

    public String getCodGuia() {
        return codGuia;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCliente() {
        return cliente;
    }

    public void setSimboloSubTotal(String simboloSubTotal) {
        this.simboloSubTotal = simboloSubTotal;
    }

    public String getSimboloSubTotal() {
        return simboloSubTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSimboloTotal(String simboloTotal) {
        this.simboloTotal = simboloTotal;
    }

    public String getSimboloTotal() {
        return simboloTotal;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setCidUn(String cidUn) {
        this.cidUn = cidUn;
    }

    public String getCidUn() {
        return cidUn;
    }

    public void setEstadoFactura(String estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public String getEstadoFactura() {
        return estadoFactura;
    }

    public void setFacturaSelect(BeanFactura facturaSelect) {
        this.facturaSelect = facturaSelect;
    }

    public BeanFactura getFacturaSelect() {
        return facturaSelect;
    }

    public void setSourcePrevio(String sourcePrevio) {
        this.sourcePrevio = sourcePrevio;
    }

    public String getSourcePrevio() {
        return sourcePrevio;
    }

    public void setComentarioAnular(String comentarioAnular) {
        this.comentarioAnular = comentarioAnular;
    }

    public String getComentarioAnular() {
        return comentarioAnular;
    }

    public void setTipNota(String tipNota) {
        this.tipNota = tipNota;
    }

    public String getTipNota() {
        return tipNota;
    }

    public void setMontoNota(BigDecimal montoNota) {
        this.montoNota = montoNota;
    }

    public BigDecimal getMontoNota() {
        return montoNota;
    }

    public void setBusqTipNota(String busqTipNota) {
        this.busqTipNota = busqTipNota;
    }

    public String getBusqTipNota() {
        return busqTipNota;
    }

    public void setSimbNota(String simbNota) {
        this.simbNota = simbNota;
    }

    public String getSimbNota() {
        return simbNota;
    }

    public void setBusqMontoNota(BigDecimal busqMontoNota) {
        this.busqMontoNota = busqMontoNota;
    }

    public BigDecimal getBusqMontoNota() {
        return busqMontoNota;
    }

    public void setRenderIsContador(boolean renderIsContador) {
        this.renderIsContador = renderIsContador;
    }

    public boolean isRenderIsContador() {
        return renderIsContador;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setDetalleFactura(String detalleFactura) {
        this.detalleFactura = detalleFactura;
    }

    public String getDetalleFactura() {
        return detalleFactura;
    }

    public void setRenderBtnFecha(boolean renderBtnFecha) {
        this.renderBtnFecha = renderBtnFecha;
    }

    public boolean isRenderBtnFecha() {
        return renderBtnFecha;
    }

    public void setRenderPagarFactura57(boolean renderPagarFactura57) {
        this.renderPagarFactura57 = renderPagarFactura57;
    }

    public boolean isRenderPagarFactura57() {
        return renderPagarFactura57;
    }
}
