package silat.servicios_negocio.Beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class BeanPreFactura implements Serializable {
    @SuppressWarnings("compatibility:8831412733783061269")
    private static final long serialVersionUID = 1L;
    
    private String codpedido;
    private String cEstreg;
    private Date fechaCreacion;
    private Long nidCliente;
    private Long nidPrefact;
    private BigDecimal prefactTotal;
    private List<BeanItemPreFactura> itemsPreFacturaList;
    private String flgFactura;
    BeanError beanError = new BeanError();
    private String cliente;
    //PARAMETROS EXTRAS PARA BUSQUEDAS FILTRADAS
    private Date fechaCreacionMIN;
    private Date fechaCreacionMAX;
    private String cidGuia;
    private BeanEmpresa empresa = new BeanEmpresa();
    private String guiasToReporte;
    private String ordServ;
    private String guiasToTrigger;

    public void setCodpedido(String codpedido) {
        this.codpedido = codpedido;
    }

    public String getCodpedido() {
        return codpedido;
    }

    public void setCEstreg(String cEstreg) {
        this.cEstreg = cEstreg;
    }

    public String getCEstreg() {
        return cEstreg;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setNidCliente(Long nidCliente) {
        this.nidCliente = nidCliente;
    }

    public Long getNidCliente() {
        return nidCliente;
    }

    public void setNidPrefact(Long nidPrefact) {
        this.nidPrefact = nidPrefact;
    }

    public Long getNidPrefact() {
        return nidPrefact;
    }

    public void setPrefactTotal(BigDecimal prefactTotal) {
        this.prefactTotal = prefactTotal;
    }

    public BigDecimal getPrefactTotal() {
        return prefactTotal;
    }

    public void setItemsPreFacturaList(List<BeanItemPreFactura> itemsPreFacturaList) {
        this.itemsPreFacturaList = itemsPreFacturaList;
    }

    public List<BeanItemPreFactura> getItemsPreFacturaList() {
        return itemsPreFacturaList;
    }

    public void setFlgFactura(String flgFactura) {
        this.flgFactura = flgFactura;
    }

    public String getFlgFactura() {
        return flgFactura;
    }

    public void setBeanError(BeanError beanError) {
        this.beanError = beanError;
    }

    public BeanError getBeanError() {
        return beanError;
    }

    public void setFechaCreacionMIN(Date fechaCreacionMIN) {
        this.fechaCreacionMIN = fechaCreacionMIN;
    }

    public Date getFechaCreacionMIN() {
        return fechaCreacionMIN;
    }

    public void setFechaCreacionMAX(Date fechaCreacionMAX) {
        this.fechaCreacionMAX = fechaCreacionMAX;
    }

    public Date getFechaCreacionMAX() {
        return fechaCreacionMAX;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCidGuia(String cidGuia) {
        this.cidGuia = cidGuia;
    }

    public String getCidGuia() {
        return cidGuia;
    }

    public void setEmpresa(BeanEmpresa empresa) {
        this.empresa = empresa;
    }

    public BeanEmpresa getEmpresa() {
        return empresa;
    }

    public void setGuiasToReporte(String guiasToReporte) {
        this.guiasToReporte = guiasToReporte;
    }

    public String getGuiasToReporte() {
        return guiasToReporte;
    }

    public void setOrdServ(String ordServ) {
        this.ordServ = ordServ;
    }

    public String getOrdServ() {
        return ordServ;
    }

    public void setGuiasToTrigger(String guiasToTrigger) {
        this.guiasToTrigger = guiasToTrigger;
    }

    public String getGuiasToTrigger() {
        return guiasToTrigger;
    }
}
