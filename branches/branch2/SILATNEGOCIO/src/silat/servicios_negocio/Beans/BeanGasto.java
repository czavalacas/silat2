package silat.servicios_negocio.Beans;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;

import silat.servicios_negocio.entidades.admin.ADFlota;
import silat.servicios_negocio.entidades.admin.ADModalidadPago;
import silat.servicios_negocio.entidades.admin.ADTipoGasto;
import silat.servicios_negocio.entidades.admin.ADUtil;

public class BeanGasto implements Serializable {
    @SuppressWarnings("compatibility:3503452825512725641")
    private static final long serialVersionUID = 1L;
    
    private byte[] blobImagenRecibo;
    private String estadoRegistro;  
    private Integer cantper;   
    private String cidFactura;  
    private String cimgrecibo;   
    private BigDecimal dMontoGeneral;
    private String destino;   
    private String c_detalle;    
    private String c_banco;
    private Date fechaGasto;   
    private BeanFlota adFlota; 
    private Long nidGasto; 
    private Long nidProtra;    
    private String nroCheque;  
    private BeanModalidadPago modalidadPago; 
    private BeanUtil utilServicioBasico; 
    private BeanUtil utilTipoCombustible;    
    private BeanTipoGasto tipoGasto;
    private BeanUtil utilTipoMantenimiento;
    private Date fechaGastoMIN;   
    private Date fechaGastoMAX;   
    private String simboloMonto;
    private String CRazonSocial;
    private BeanError beanError;    

    public void setBlobImagenRecibo(byte[] blobImagenRecibo) {
        this.blobImagenRecibo = blobImagenRecibo;
    }

    public byte[] getBlobImagenRecibo() {
        return blobImagenRecibo;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setCantper(Integer cantper) {
        this.cantper = cantper;
    }

    public Integer getCantper() {
        return cantper;
    }

    public void setCidFactura(String cidFactura) {
        this.cidFactura = cidFactura;
    }

    public String getCidFactura() {
        return cidFactura;
    }

    public void setCimgrecibo(String cimgrecibo) {
        this.cimgrecibo = cimgrecibo;
    }

    public String getCimgrecibo() {
        return cimgrecibo;
    }

    public void setDMontoGeneral(BigDecimal dMontoGeneral) {
        this.dMontoGeneral = dMontoGeneral;
    }

    public BigDecimal getDMontoGeneral() {
        return dMontoGeneral;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getDestino() {
        return destino;
    }

    public void setC_detalle(String c_detalle) {
        this.c_detalle = c_detalle;
    }

    public String getC_detalle() {
        return c_detalle;
    }

    public void setFechaGasto(Date fechaGasto) {
        this.fechaGasto = fechaGasto;
    }

    public Date getFechaGasto() {
        return fechaGasto;
    }

    public void setNidGasto(Long nidGasto) {
        this.nidGasto = nidGasto;
    }

    public Long getNidGasto() {
        return nidGasto;
    }

    public void setNidProtra(Long nidProtra) {
        this.nidProtra = nidProtra;
    }

    public Long getNidProtra() {
        return nidProtra;
    }

    public void setNroCheque(String nroCheque) {
        this.nroCheque = nroCheque;
    }

    public String getNroCheque() {
        return nroCheque;
    }

    public void setFechaGastoMIN(Date fechaGastoMIN) {
        this.fechaGastoMIN = fechaGastoMIN;
    }

    public Date getFechaGastoMIN() {
        return fechaGastoMIN;
    }

    public void setFechaGastoMAX(Date fechaGastoMAX) {
        this.fechaGastoMAX = fechaGastoMAX;
    }

    public Date getFechaGastoMAX() {
        return fechaGastoMAX;
    }

    public void setSimboloMonto(String simboloMonto) {
        this.simboloMonto = simboloMonto;
    }

    public String getSimboloMonto() {
        return simboloMonto;
    }

    public void setCRazonSocial(String CRazonSocial) {
        this.CRazonSocial = CRazonSocial;
    }

    public String getCRazonSocial() {
        return CRazonSocial;
    }

    public void setAdFlota(BeanFlota adFlota) {
        this.adFlota = adFlota;
    }

    public BeanFlota getAdFlota() {
        return adFlota;
    }

    public void setModalidadPago(BeanModalidadPago modalidadPago) {
        this.modalidadPago = modalidadPago;
    }

    public BeanModalidadPago getModalidadPago() {
        return modalidadPago;
    }

    public void setUtilServicioBasico(BeanUtil utilServicioBasico) {
        this.utilServicioBasico = utilServicioBasico;
    }

    public BeanUtil getUtilServicioBasico() {
        return utilServicioBasico;
    }

    public void setUtilTipoCombustible(BeanUtil utilTipoCombustible) {
        this.utilTipoCombustible = utilTipoCombustible;
    }

    public BeanUtil getUtilTipoCombustible() {
        return utilTipoCombustible;
    }

    public void setTipoGasto(BeanTipoGasto tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    public BeanTipoGasto getTipoGasto() {
        return tipoGasto;
    }

    public void setUtilTipoMantenimiento(BeanUtil utilTipoMantenimiento) {
        this.utilTipoMantenimiento = utilTipoMantenimiento;
    }

    public BeanUtil getUtilTipoMantenimiento() {
        return utilTipoMantenimiento;
    }

    public void setBeanError(BeanError beanError) {
        this.beanError = beanError;
    }

    public BeanError getBeanError() {
        return beanError;
    }

    public void setC_banco(String c_banco) {
        this.c_banco = c_banco;
    }

    public String getC_banco() {
        return c_banco;
    }
}
