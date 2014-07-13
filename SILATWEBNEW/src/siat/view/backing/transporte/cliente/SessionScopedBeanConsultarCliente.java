package siat.view.backing.transporte.cliente;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import silat.servicios_negocio.Beans.BeanADRelacionEmpresa;
import silat.servicios_negocio.Beans.BeanChofer;
import silat.servicios_negocio.Beans.BeanDireccion;
import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.Beans.BeanFlota;

public class SessionScopedBeanConsultarCliente implements Serializable {
    private List<BeanDireccion> beanListDire = new ArrayList<BeanDireccion>();
    private List<BeanADRelacionEmpresa> beanListRela = new ArrayList<BeanADRelacionEmpresa>();
    private List<BeanChofer> beanListChofer = new ArrayList<BeanChofer>();
    private List<BeanFlota> beanListFlota =new ArrayList<BeanFlota>();
    private List<BeanEmpresa> beanListEmpresas=new ArrayList<BeanEmpresa>();
    private BeanEmpresa beanEmpresa=new BeanEmpresa();
    private BigDecimal nidParty;
    private String nombreEmpresa;
    private String razonSocial;
    private String ruc;
    private String relacionEmpresa;
    private int exec = 0;
    

    public void setBeanListDire(List<BeanDireccion> beanListDire) {
        this.beanListDire = beanListDire;
    }

    public List<BeanDireccion> getBeanListDire() {
        return beanListDire;
    }

    public void setBeanListRela(List<BeanADRelacionEmpresa> beanListRela) {
        this.beanListRela = beanListRela;
    }

    public List<BeanADRelacionEmpresa> getBeanListRela() {
        return beanListRela;
    }

    public void setNidParty(BigDecimal nidParty) {
        this.nidParty = nidParty;
    }

    public BigDecimal getNidParty() {
        return nidParty;
    }

    public void setBeanListChofer(List<BeanChofer> beanListChofer) {
        this.beanListChofer = beanListChofer;
    }

    public List<BeanChofer> getBeanListChofer() {
        return beanListChofer;
    }

    public void setBeanListFlota(List<BeanFlota> beanListFlota) {
        this.beanListFlota = beanListFlota;
    }

    public List<BeanFlota> getBeanListFlota() {
        return beanListFlota;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setBeanListEmpresas(List<BeanEmpresa> beanListEmpresas) {
        this.beanListEmpresas = beanListEmpresas;
    }

    public List<BeanEmpresa> getBeanListEmpresas() {
        return beanListEmpresas;
    }

    public void setBeanEmpresa(BeanEmpresa beanEmpresa) {
        this.beanEmpresa = beanEmpresa;
    }

    public BeanEmpresa getBeanEmpresa() {
        return beanEmpresa;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRelacionEmpresa(String relacionEmpresa) {
        this.relacionEmpresa = relacionEmpresa;
    }

    public String getRelacionEmpresa() {
        return relacionEmpresa;
    }

    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }
}
