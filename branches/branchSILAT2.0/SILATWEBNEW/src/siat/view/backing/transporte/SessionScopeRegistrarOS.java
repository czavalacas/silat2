package siat.view.backing.transporte;

import java.util.Date;
import java.util.List;

import siat.view.backing.utiles.fecha.FechaUtiles;

import silat.servicios_negocio.Beans.BeanEmpresa;

public class SessionScopeRegistrarOS {
    
    private List<BeanEmpresa> lstEmpresasCliente;
    private BeanEmpresa empresa;
    private String razSocial;
    private Date fechaHoy = FechaUtiles.fechaActual();
    private int nidOSGenerado;
    private int exec = 0;

    public void setLstEmpresasCliente(List<BeanEmpresa> lstEmpresasCliente) {
        this.lstEmpresasCliente = lstEmpresasCliente;
    }

    public List<BeanEmpresa> getLstEmpresasCliente() {
        return lstEmpresasCliente;
    }

    public void setRazSocial(String razSocial) {
        this.razSocial = razSocial;
    }

    public String getRazSocial() {
        return razSocial;
    }

    public void setEmpresa(BeanEmpresa empresa) {
        this.empresa = empresa;
    }

    public BeanEmpresa getEmpresa() {
        return empresa;
    }

    public void setFechaHoy(Date fechaHoy) {
        this.fechaHoy = fechaHoy;
    }

    public Date getFechaHoy() {
        return fechaHoy;
    }

    public void setNidOSGenerado(int nidOSGenerado) {
        this.nidOSGenerado = nidOSGenerado;
    }

    public int getNidOSGenerado() {
        return nidOSGenerado;
    }

    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }
}
