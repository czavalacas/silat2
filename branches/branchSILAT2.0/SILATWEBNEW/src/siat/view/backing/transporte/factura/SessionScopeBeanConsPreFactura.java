package siat.view.backing.transporte.factura;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import siat.view.backing.utiles.fecha.FechaUtiles;

import silat.servicios_negocio.Beans.BeanItemPreFactura;
import silat.servicios_negocio.Beans.BeanPreFactura;

public class SessionScopeBeanConsPreFactura{
    
    private String codpedido;
    private String flgFactura;
    private Date fecMin;
    private Date fecMax;
    private String cliente;
    private String cidguia;
    private Long nidPrefact;
    private int exec = 0;
    private List<BeanPreFactura> lstBeanPreFactura = new ArrayList<BeanPreFactura>();
    private BeanPreFactura selectedBeanPreFactura = new BeanPreFactura();
    private List<BeanItemPreFactura> lstBeanItemPreFactura = new ArrayList<BeanItemPreFactura>();
    private String sourcePrevio;
    private List lstDirecs = new ArrayList();
    private String direc;
    private List listaUNs = new ArrayList();
    private String codUN;
    private Date fecFactura = FechaUtiles.fechaActual();
    private String codFactura;
    private String tipFactura;//1 = regular, 2 = guias, 3 = solo una descripcion
    private String tipFacturaForPrevio;
    
    public void setCodpedido(String codpedido) {
        this.codpedido = codpedido;
    }

    public String getCodpedido() {
        return codpedido;
    }

    public void setFlgFactura(String flgFactura) {
        this.flgFactura = flgFactura;
    }

    public String getFlgFactura() {
        return flgFactura;
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

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCidguia(String cidguia) {
        this.cidguia = cidguia;
    }

    public String getCidguia() {
        return cidguia;
    }

    public void setNidPrefact(Long nidPrefact) {
        this.nidPrefact = nidPrefact;
    }

    public Long getNidPrefact() {
        return nidPrefact;
    }

    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }

    public void setLstBeanPreFactura(List<BeanPreFactura> lstBeanPreFactura) {
        this.lstBeanPreFactura = lstBeanPreFactura;
    }

    public List<BeanPreFactura> getLstBeanPreFactura() {
        return lstBeanPreFactura;
    }

    public void setSelectedBeanPreFactura(BeanPreFactura selectedBeanPreFactura) {
        this.selectedBeanPreFactura = selectedBeanPreFactura;
    }

    public BeanPreFactura getSelectedBeanPreFactura() {
        return selectedBeanPreFactura;
    }

    public void setLstBeanItemPreFactura(List<BeanItemPreFactura> lstBeanItemPreFactura) {
        this.lstBeanItemPreFactura = lstBeanItemPreFactura;
    }

    public List<BeanItemPreFactura> getLstBeanItemPreFactura() {
        return lstBeanItemPreFactura;
    }

    public void setSourcePrevio(String sourcePrevio) {
        this.sourcePrevio = sourcePrevio;
    }

    public String getSourcePrevio() {
        return sourcePrevio;
    }

    public void setLstDirecs(List lstDirecs) {
        this.lstDirecs = lstDirecs;
    }

    public List getLstDirecs() {
        return lstDirecs;
    }

    public void setDirec(String direc) {
        this.direc = direc;
    }

    public String getDirec() {
        return direc;
    }

    public void setListaUNs(List listaUNs) {
        this.listaUNs = listaUNs;
    }

    public List getListaUNs() {
        return listaUNs;
    }

    public void setCodUN(String codUN) {
        this.codUN = codUN;
    }

    public String getCodUN() {
        return codUN;
    }

    public void setFecFactura(Date fecFactura) {
        this.fecFactura = fecFactura;
    }

    public Date getFecFactura() {
        return fecFactura;
    }

    public void setCodFactura(String codFactura) {
        this.codFactura = codFactura;
    }

    public String getCodFactura() {
        return codFactura;
    }

    public void setTipFactura(String tipFactura) {
        this.tipFactura = tipFactura;
    }

    public String getTipFactura() {
        return tipFactura;
    }

    public void setTipFacturaForPrevio(String tipFacturaForPrevio) {
        this.tipFacturaForPrevio = tipFacturaForPrevio;
    }

    public String getTipFacturaForPrevio() {
        return tipFacturaForPrevio;
    }
}
