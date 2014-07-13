package siat.view.backing.administrativo.gastos;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.Beans.BeanGasto;

public class SessionScopeBeanConsultarGastos {   
    private int exec = 0;
    private String nidTipoGasto;
    private String nidModalidadPago;
    private BigDecimal nidTipoServicio;
    private BigDecimal nidTipoCombustible;
    private BigDecimal nidTipoMantenimiento;
    private Integer nidFlotaCombustible;
    private Integer nidFlotaMantenimiento;
    private Integer cantidadPersonas;
    private Date fechaGasto;
    private String cidFactura;
    private BeanEmpresa beanEmpresa = new BeanEmpresa();
    private Long nidProveedor;
    private byte[] blobImagenRecibo;
    private String rutaImagen;
    private String rutaImagenAux;
    private List<BeanGasto> listGasto = new ArrayList<BeanGasto>();
    private BeanGasto beanGasto = new BeanGasto();
    private String simboloGasto;
    private BigDecimal montoGeneral;
    private Date fechaGastoMin;
    private Date fechaGastoMax;
    private String detalle;
    private String rutaImagenServ;
    private Long nidGasto;

    public void setNidTipoGasto(String nidTipoGasto) {
        this.nidTipoGasto = nidTipoGasto;
    }

    public String getNidTipoGasto() {
        return nidTipoGasto;
    }

    public void setNidModalidadPago(String nidModalidadPago) {
        this.nidModalidadPago = nidModalidadPago;
    }

    public String getNidModalidadPago() {
        return nidModalidadPago;
    }

    public void setNidTipoServicio(BigDecimal nidTipoServicio) {
        this.nidTipoServicio = nidTipoServicio;
    }

    public BigDecimal getNidTipoServicio() {
        return nidTipoServicio;
    }

    public void setNidTipoCombustible(BigDecimal nidTipoCombustible) {
        this.nidTipoCombustible = nidTipoCombustible;
    }

    public BigDecimal getNidTipoCombustible() {
        return nidTipoCombustible;
    }

    public void setNidTipoMantenimiento(BigDecimal nidTipoMantenimiento) {
        this.nidTipoMantenimiento = nidTipoMantenimiento;
    }

    public BigDecimal getNidTipoMantenimiento() {
        return nidTipoMantenimiento;
    }

    public void setNidFlotaCombustible(Integer nidFlotaCombustible) {
        this.nidFlotaCombustible = nidFlotaCombustible;
    }

    public Integer getNidFlotaCombustible() {
        return nidFlotaCombustible;
    }

    public void setNidFlotaMantenimiento(Integer nidFlotaMantenimiento) {
        this.nidFlotaMantenimiento = nidFlotaMantenimiento;
    }

    public Integer getNidFlotaMantenimiento() {
        return nidFlotaMantenimiento;
    }

    public void setCantidadPersonas(Integer cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public Integer getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setFechaGasto(Date fechaGasto) {
        this.fechaGasto = fechaGasto;
    }

    public Date getFechaGasto() {
        return fechaGasto;
    }

    public void setCidFactura(String cidFactura) {
        this.cidFactura = cidFactura;
    }

    public String getCidFactura() {
        return cidFactura;
    }

    public void setBeanEmpresa(BeanEmpresa beanEmpresa) {
        this.beanEmpresa = beanEmpresa;
    }

    public BeanEmpresa getBeanEmpresa() {
        return beanEmpresa;
    }

    public void setNidProveedor(Long nidProveedor) {
        this.nidProveedor = nidProveedor;
    }

    public Long getNidProveedor() {
        return nidProveedor;
    }

    public void setBlobImagenRecibo(byte[] blobImagenRecibo) {
        this.blobImagenRecibo = blobImagenRecibo;
    }

    public byte[] getBlobImagenRecibo() {
        return blobImagenRecibo;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagenAux(String rutaImagenAux) {
        this.rutaImagenAux = rutaImagenAux;
    }

    public String getRutaImagenAux() {
        return rutaImagenAux;
    }

    public void setListGasto(List<BeanGasto> listGasto) {
        this.listGasto = listGasto;
    }

    public List<BeanGasto> getListGasto() {
        return listGasto;
    }

    public void setBeanGasto(BeanGasto beanGasto) {
        this.beanGasto = beanGasto;
    }

    public BeanGasto getBeanGasto() {
        return beanGasto;
    }

    public void setSimboloGasto(String simboloGasto) {
        this.simboloGasto = simboloGasto;
    }

    public String getSimboloGasto() {
        return simboloGasto;
    }

    public void setMontoGeneral(BigDecimal montoGeneral) {
        this.montoGeneral = montoGeneral;
    }

    public BigDecimal getMontoGeneral() {
        return montoGeneral;
    }

    public void setFechaGastoMin(Date fechaGastoMin) {
        this.fechaGastoMin = fechaGastoMin;
    }

    public Date getFechaGastoMin() {
        return fechaGastoMin;
    }

    public void setFechaGastoMax(Date fechaGastoMax) {
        this.fechaGastoMax = fechaGastoMax;
    }

    public Date getFechaGastoMax() {
        return fechaGastoMax;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }

    public void setRutaImagenServ(String rutaImagenServ) {
        this.rutaImagenServ = rutaImagenServ;
    }

    public String getRutaImagenServ() {
        return rutaImagenServ;
    }

    public void setNidGasto(Long nidGasto) {
        this.nidGasto = nidGasto;
    }

    public Long getNidGasto() {
        return nidGasto;
    }
}
