package siat.view.backing.administrativo.gastos;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.Beans.BeanGasto;
import silat.servicios_negocio.Beans.BeanTipoGasto;

public class SessionScopeBeanRegistrarGastos {
    private String nidTipoGasto;
    private BeanTipoGasto beanTipoGasto = new BeanTipoGasto();
    private String nidModalidadPago;
    private BigDecimal nidTipoServicio;
    private BigDecimal nidTipoCombustible;
    private BigDecimal nidTipoMantenimiento;
    private Integer nidManifiesto;
    private Integer nidFlota;
    private Integer nidFlotaMantenimiento;
    private Integer cantidadPersonas;
    private Date fechaGasto;
    private String cidFactura;
    private BeanEmpresa beanEmpresa= new BeanEmpresa();
    private Long nidProveedor;
    private byte[] blobImagenRecibo;
    private String rutaImagen;
    private String rutaImagenAux;
    private List<BeanGasto> listGasto= new ArrayList<BeanGasto>();
    private BeanGasto beanGasto= new BeanGasto();
    private String simboloGasto;
    private BigDecimal montoGeneral;
    private Date fechaGastoMin;
    private Date fechaGastoMax;
    private String detalle;
    private String banco;
    //////////
    private boolean estadoInProveedorV=false;
    private boolean estadoBtnProveedorV=false;    
    private boolean estadoinFacturaV=false;
    private boolean estadoInProveedorR=false;
    private boolean estadoBtnProveedorR=false;    
    private boolean estadoinFacturaR=false;
    private boolean estadoInDestinoV=false;
    private boolean estadoInDestinoR=false;
    private boolean estadoInTipoServicioV=false;
    private boolean estadoInTipoServicioR=false;
    private boolean estadoInFlotaV = false;
    private boolean estadoInFlotaR = false;
    private boolean estadoInTipoCombustibleV=false;
    private boolean estadoInTipoCombustibleR=false;    
    private boolean estadoinCantidadPersonasV=false;
    private boolean estadoinCantidadPersonasR=false;
    private boolean estadoInFlotaMantenimientoV=false;    
    private boolean estadoInFlotaMantenimientoR=false;
    private boolean estadoInTipoMantenimientoV=false;
    private boolean estadoInTipoMantenimientoR=false;
    private boolean estadoBtnSubirImagenV=false;
    private boolean estadoBtnSubirImagenR=false;
    private boolean estadoInBancoV=false;
    private boolean estadoInBancoR=false;
    private boolean estadoInNumChequeV=false;
    private boolean estadoInNumChequeR=false;
    private boolean estadoInManifiesto=false;
    List<BeanTipoGasto> tipoGastos = new ArrayList<BeanTipoGasto>();
    private String tipGasto;
    private boolean selecChkBox;
    private String nombBtnTipGasto;
    private boolean isRegNuevTipGast = true;
    private int nidTipGasto;
    private String estTipGasto;
    private List lstEstadosTipGasto = new ArrayList();

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

    public void setBeanEmpresa(BeanEmpresa beanEmpresa) {
        this.beanEmpresa = beanEmpresa;
    }

    public BeanEmpresa getBeanEmpresa() {
        return beanEmpresa;
    }

    public void setFechaGasto(Date fechaGasto) {
        this.fechaGasto = fechaGasto;
    }

    public Date getFechaGasto() {
        return fechaGasto;
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

   

    public void setCidFactura(String cidFactura) {
        this.cidFactura = cidFactura;
    }

    public String getCidFactura() {
        return cidFactura;
    }

    public void setCantidadPersonas(Integer cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public Integer getCantidadPersonas() {
        return cantidadPersonas;
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

    public void setNidFlotaMantenimiento(Integer nidFlotaMantenimiento) {
        this.nidFlotaMantenimiento = nidFlotaMantenimiento;
    }

    public Integer getNidFlotaMantenimiento() {
        return nidFlotaMantenimiento;
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

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getBanco() {
        return banco;
    }

    public void setEstadoInProveedorV(boolean estadoInProveedorV) {
        this.estadoInProveedorV = estadoInProveedorV;
    }

    public boolean isEstadoInProveedorV() {
        return estadoInProveedorV;
    }

    public void setEstadoBtnProveedorV(boolean estadoBtnProveedorV) {
        this.estadoBtnProveedorV = estadoBtnProveedorV;
    }

    public boolean isEstadoBtnProveedorV() {
        return estadoBtnProveedorV;
    }

    public void setEstadoinFacturaV(boolean estadoinFacturaV) {
        this.estadoinFacturaV = estadoinFacturaV;
    }

    public boolean isEstadoinFacturaV() {
        return estadoinFacturaV;
    }

    public void setEstadoInProveedorR(boolean estadoInProveedorR) {
        this.estadoInProveedorR = estadoInProveedorR;
    }

    public boolean isEstadoInProveedorR() {
        return estadoInProveedorR;
    }

    public void setEstadoBtnProveedorR(boolean estadoBtnProveedorR) {
        this.estadoBtnProveedorR = estadoBtnProveedorR;
    }

    public boolean isEstadoBtnProveedorR() {
        return estadoBtnProveedorR;
    }

    public void setEstadoinFacturaR(boolean estadoinFacturaR) {
        this.estadoinFacturaR = estadoinFacturaR;
    }

    public boolean isEstadoinFacturaR() {
        return estadoinFacturaR;
    }

    public void setEstadoInDestinoV(boolean estadoInDestinoV) {
        this.estadoInDestinoV = estadoInDestinoV;
    }

    public boolean isEstadoInDestinoV() {
        return estadoInDestinoV;
    }

    public void setEstadoInDestinoR(boolean estadoInDestinoR) {
        this.estadoInDestinoR = estadoInDestinoR;
    }

    public boolean isEstadoInDestinoR() {
        return estadoInDestinoR;
    }

    public void setEstadoInTipoServicioV(boolean estadoInTipoServicioV) {
        this.estadoInTipoServicioV = estadoInTipoServicioV;
    }

    public boolean isEstadoInTipoServicioV() {
        return estadoInTipoServicioV;
    }

    public void setEstadoInTipoServicioR(boolean estadoInTipoServicioR) {
        this.estadoInTipoServicioR = estadoInTipoServicioR;
    }

    public boolean isEstadoInTipoServicioR() {
        return estadoInTipoServicioR;
    }


    public void setEstadoInTipoCombustibleV(boolean estadoInTipoCombustibleV) {
        this.estadoInTipoCombustibleV = estadoInTipoCombustibleV;
    }

    public boolean isEstadoInTipoCombustibleV() {
        return estadoInTipoCombustibleV;
    }

    public void setEstadoInTipoCombustibleR(boolean estadoInTipoCombustibleR) {
        this.estadoInTipoCombustibleR = estadoInTipoCombustibleR;
    }

    public boolean isEstadoInTipoCombustibleR() {
        return estadoInTipoCombustibleR;
    }

    public void setEstadoinCantidadPersonasV(boolean estadoinCantidadPersonasV) {
        this.estadoinCantidadPersonasV = estadoinCantidadPersonasV;
    }

    public boolean isEstadoinCantidadPersonasV() {
        return estadoinCantidadPersonasV;
    }

    public void setEstadoinCantidadPersonasR(boolean estadoinCantidadPersonasR) {
        this.estadoinCantidadPersonasR = estadoinCantidadPersonasR;
    }

    public boolean isEstadoinCantidadPersonasR() {
        return estadoinCantidadPersonasR;
    }


    public void setEstadoInTipoMantenimientoV(boolean estadoInTipoMantenimientoV) {
        this.estadoInTipoMantenimientoV = estadoInTipoMantenimientoV;
    }

    public boolean isEstadoInTipoMantenimientoV() {
        return estadoInTipoMantenimientoV;
    }

    public void setEstadoInTipoMantenimientoR(boolean estadoInTipoMantenimientoR) {
        this.estadoInTipoMantenimientoR = estadoInTipoMantenimientoR;
    }

    public boolean isEstadoInTipoMantenimientoR() {
        return estadoInTipoMantenimientoR;
    }

    public void setEstadoBtnSubirImagenV(boolean estadoBtnSubirImagenV) {
        this.estadoBtnSubirImagenV = estadoBtnSubirImagenV;
    }

    public boolean isEstadoBtnSubirImagenV() {
        return estadoBtnSubirImagenV;
    }

    public void setEstadoBtnSubirImagenR(boolean estadoBtnSubirImagenR) {
        this.estadoBtnSubirImagenR = estadoBtnSubirImagenR;
    }

    public boolean isEstadoBtnSubirImagenR() {
        return estadoBtnSubirImagenR;
    }

    public void setEstadoInBancoV(boolean estadoInBancoV) {
        this.estadoInBancoV = estadoInBancoV;
    }

    public boolean isEstadoInBancoV() {
        return estadoInBancoV;
    }

    public void setEstadoInBancoR(boolean estadoInBancoR) {
        this.estadoInBancoR = estadoInBancoR;
    }

    public boolean isEstadoInBancoR() {
        return estadoInBancoR;
    }

    public void setEstadoInNumChequeV(boolean estadoInNumChequeV) {
        this.estadoInNumChequeV = estadoInNumChequeV;
    }

    public boolean isEstadoInNumChequeV() {
        return estadoInNumChequeV;
    }

    public void setEstadoInNumChequeR(boolean estadoInNumChequeR) {
        this.estadoInNumChequeR = estadoInNumChequeR;
    }

    public boolean isEstadoInNumChequeR() {
        return estadoInNumChequeR;
    }

    public void setEstadoInFlotaMantenimientoV(boolean estadoInFlotaMantenimientoV) {
        this.estadoInFlotaMantenimientoV = estadoInFlotaMantenimientoV;
    }

    public boolean isEstadoInFlotaMantenimientoV() {
        return estadoInFlotaMantenimientoV;
    }

    public void setEstadoInFlotaMantenimientoR(boolean estadoInFlotaMantenimientoR) {
        this.estadoInFlotaMantenimientoR = estadoInFlotaMantenimientoR;
    }

    public boolean isEstadoInFlotaMantenimientoR() {
        return estadoInFlotaMantenimientoR;
    }

    public void setNidFlota(Integer nidFlota) {
        this.nidFlota = nidFlota;
    }

    public Integer getNidFlota() {
        return nidFlota;
    }

    public void setEstadoInFlotaV(boolean estadoInFlotaV) {
        this.estadoInFlotaV = estadoInFlotaV;
    }

    public boolean isEstadoInFlotaV() {
        return estadoInFlotaV;
    }

    public void setEstadoInFlotaR(boolean estadoInFlotaR) {
        this.estadoInFlotaR = estadoInFlotaR;
    }

    public boolean isEstadoInFlotaR() {
        return estadoInFlotaR;
    }

    public void setBeanTipoGasto(BeanTipoGasto beanTipoGasto) {
        this.beanTipoGasto = beanTipoGasto;
    }

    public BeanTipoGasto getBeanTipoGasto() {
        return beanTipoGasto;
    }

    public void setTipoGastos(List<BeanTipoGasto> tipoGastos) {
        this.tipoGastos = tipoGastos;
    }

    public List<BeanTipoGasto> getTipoGastos() {
        return tipoGastos;
    }

    public void setTipGasto(String tipGasto) {
        this.tipGasto = tipGasto;
    }

    public String getTipGasto() {
        return tipGasto;
    }

    public void setSelecChkBox(boolean selecChkBox) {
        this.selecChkBox = selecChkBox;
    }

    public boolean isSelecChkBox() {
        return selecChkBox;
    }

    public void setNombBtnTipGasto(String nombBtnTipGasto) {
        this.nombBtnTipGasto = nombBtnTipGasto;
    }

    public String getNombBtnTipGasto() {
        return nombBtnTipGasto;
    }

    public void setIsRegNuevTipGast(boolean isRegNuevTipGast) {
        this.isRegNuevTipGast = isRegNuevTipGast;
    }

    public boolean isIsRegNuevTipGast() {
        return isRegNuevTipGast;
    }

    public void setNidTipGasto(int nidTipGasto) {
        this.nidTipGasto = nidTipGasto;
    }

    public int getNidTipGasto() {
        return nidTipGasto;
    }

    public void setEstTipGasto(String estTipGasto) {
        this.estTipGasto = estTipGasto;
    }

    public String getEstTipGasto() {
        return estTipGasto;
    }

    public void setLstEstadosTipGasto(List lstEstadosTipGasto) {
        this.lstEstadosTipGasto = lstEstadosTipGasto;
    }

    public List getLstEstadosTipGasto() {
        return lstEstadosTipGasto;
    }

    public void setEstadoInManifiesto(boolean estadoInManifiesto) {
        this.estadoInManifiesto = estadoInManifiesto;
    }

    public boolean isEstadoInManifiesto() {
        return estadoInManifiesto;
    }

    public void setNidManifiesto(Integer nidManifiesto) {
        this.nidManifiesto = nidManifiesto;
    }

    public Integer getNidManifiesto() {
        return nidManifiesto;
    }
}
