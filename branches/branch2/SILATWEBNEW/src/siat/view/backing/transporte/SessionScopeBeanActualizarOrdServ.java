package siat.view.backing.transporte;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.Beans.BeanOrdenServicio;
import silat.servicios_negocio.Beans.BeanTrItemXOrds;

public class SessionScopeBeanActualizarOrdServ implements Serializable {
    
    private BeanOrdenServicio beanOrden = new BeanOrdenServicio();
    private Integer nidOrdenServ;
    private List<BeanOrdenServicio> listOrdenServ=new ArrayList<BeanOrdenServicio>();
    private BigDecimal nidEmpresa;
    private String cRazonSocial;
    private String cDetalle;
    private String cEstadoOrdenDesc;
    private Date fecOrdnServ;
    private Date fecOrdnMin;
    private Date fecOrdnMax;
    private String estadoOrdnServicioEditar;
    private String cRazonSocialEditar;
    private String cDetalleEditar;
    private Date fechaOrdEditar;
    private int exec = 0;
    private boolean renderBtnEditar = true;
    private boolean renderBtnChangeCliente;
    private String razSocial;
    private List<BeanEmpresa> lstEmpresasCliente;
    private BigDecimal nidEmp;
    private Integer nidOS;
    private List<BeanTrItemXOrds> lstItemsOrdnS=new ArrayList<BeanTrItemXOrds>();
    
    
    //dfloresgonz 19.06.2014 - Se agrega variable saber si tiene el permiso de modificar fechas
    private boolean renderBtnFecha = false;

    public void setBeanOrden(BeanOrdenServicio beanOrden) {
        this.beanOrden = beanOrden;
    }

    public BeanOrdenServicio getBeanOrden() {
        return beanOrden;
    }

    public void setNidEmpresa(BigDecimal nidEmpresa) {
        this.nidEmpresa = nidEmpresa;
    }

    public BigDecimal getNidEmpresa() {
        return nidEmpresa;
    }

    public void setCRazonSocial(String cRazonSocial) {
        this.cRazonSocial = cRazonSocial;
    }

    public String getCRazonSocial() {
        return cRazonSocial;
    }

    public void setCDetalle(String cDetalle) {
        this.cDetalle = cDetalle;
    }

    public String getCDetalle() {
        return cDetalle;
    }

    public void setCEstadoOrdenDesc(String cEstadoOrdenDesc) {
        this.cEstadoOrdenDesc = cEstadoOrdenDesc;
    }

    public String getCEstadoOrdenDesc() {
        return cEstadoOrdenDesc;
    }

    public void setFecOrdnServ(Date fecOrdnServ) {
        this.fecOrdnServ = fecOrdnServ;
    }

    public Date getFecOrdnServ() {
        return fecOrdnServ;
    }

    public void setFecOrdnMin(Date fecOrdnMin) {
        this.fecOrdnMin = fecOrdnMin;
    }

    public Date getFecOrdnMin() {
        return fecOrdnMin;
    }

    public void setFecOrdnMax(Date fecOrdnMax) {
        this.fecOrdnMax = fecOrdnMax;
    }

    public Date getFecOrdnMax() {
        return fecOrdnMax;
    }

    public void setListOrdenServ(List<BeanOrdenServicio> listOrdenServ) {
        this.listOrdenServ = listOrdenServ;
    }

    public List<BeanOrdenServicio> getListOrdenServ() {
        return listOrdenServ;
    }

    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }

   

    public void setEstadoOrdnServicioEditar(String estadoOrdnServicioEditar) {
        this.estadoOrdnServicioEditar = estadoOrdnServicioEditar;
    }

    public String getEstadoOrdnServicioEditar() {
        return estadoOrdnServicioEditar;
    }

    public void setCRazonSocialEditar(String cRazonSocialEditar) {
        this.cRazonSocialEditar = cRazonSocialEditar;
    }

    public String getCRazonSocialEditar() {
        return cRazonSocialEditar;
    }

    public void setCDetalleEditar(String cDetalleEditar) {
        this.cDetalleEditar = cDetalleEditar;
    }

    public String getCDetalleEditar() {
        return cDetalleEditar;
    }

    public void setFechaOrdEditar(Date fechaOrdEditar) {
        this.fechaOrdEditar = fechaOrdEditar;
    }

    public Date getFechaOrdEditar() {
        return fechaOrdEditar;
    }


    public void setNidOrdenServ(Integer nidOrdenServ) {
        this.nidOrdenServ = nidOrdenServ;
    }

    public Integer getNidOrdenServ() {
        return nidOrdenServ;
    }

    public void setRenderBtnEditar(boolean renderBtnEditar) {
        this.renderBtnEditar = renderBtnEditar;
    }

    public boolean isRenderBtnEditar() {
        return renderBtnEditar;
    }

    public void setRenderBtnChangeCliente(boolean renderBtnChangeCliente) {
        this.renderBtnChangeCliente = renderBtnChangeCliente;
    }

    public boolean isRenderBtnChangeCliente() {
        return renderBtnChangeCliente;
    }

    public void setRazSocial(String razSocial) {
        this.razSocial = razSocial;
    }

    public String getRazSocial() {
        return razSocial;
    }

    public void setLstEmpresasCliente(List<BeanEmpresa> lstEmpresasCliente) {
        this.lstEmpresasCliente = lstEmpresasCliente;
    }

    public List<BeanEmpresa> getLstEmpresasCliente() {
        return lstEmpresasCliente;
    }

    public void setNidEmp(BigDecimal nidEmp) {
        this.nidEmp = nidEmp;
    }

    public BigDecimal getNidEmp() {
        return nidEmp;
    }

    public void setNidOS(Integer nidOS) {
        this.nidOS = nidOS;
    }

    public Integer getNidOS() {
        return nidOS;
    }

    public void setRenderBtnFecha(boolean renderBtnFecha) {
        this.renderBtnFecha = renderBtnFecha;
    }

    public boolean isRenderBtnFecha() {
        return renderBtnFecha;
    }

    public void setLstItemsOrdnS(List<BeanTrItemXOrds> lstItemsOrdnS) {
        this.lstItemsOrdnS = lstItemsOrdnS;
    }

    public List<BeanTrItemXOrds> getLstItemsOrdnS() {
        return lstItemsOrdnS;
    }
}
