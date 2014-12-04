package siat.view.backing.estadistico;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import silat.servicios_negocio.Beans.BeanEstCliente;
import silat.servicios_negocio.Beans.BeanTRGuia;

public class SessionScopeEstadisticosViajes implements Serializable {
    @SuppressWarnings("compatibility:3574118774553731317")
    private static final long serialVersionUID = 1L;
    private List<BeanTRGuia> guias;
    private String titulo;
    private int columns= 2;
    private String rowHeights = "350px";
    private Date fecMin;
    private Date fecMax;
    private int limite;
    private int visiblePb1 = 0;
    private int visiblePb2 = 0;
    private int visiblePb3 = 0;
    private int visiblePb4 = 0;
    private int visiblePb5 = 0;
    private int visiblePb6 = 0;
    private List<BeanEstCliente> listaViajesExitosos;
    private List<BeanEstCliente> listaViajesFallidos;
    private List<BeanEstCliente> listaViajesTotal;
    private List<BeanEstCliente> listaProv;
    private List<BeanEstCliente> listaProp;
    private List listaVacia = new ArrayList();

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getColumns() {
        return columns;
    }

    public void setRowHeights(String rowHeights) {
        this.rowHeights = rowHeights;
    }

    public String getRowHeights() {
        return rowHeights;
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

    public void setLimite(int limite) {
        this.limite = limite;
    }

    public int getLimite() {
        return limite;
    }

    public void setVisiblePb1(int visiblePb1) {
        this.visiblePb1 = visiblePb1;
    }

    public int getVisiblePb1() {
        return visiblePb1;
    }

    public void setVisiblePb2(int visiblePb2) {
        this.visiblePb2 = visiblePb2;
    }

    public int getVisiblePb2() {
        return visiblePb2;
    }

    public void setVisiblePb3(int visiblePb3) {
        this.visiblePb3 = visiblePb3;
    }

    public int getVisiblePb3() {
        return visiblePb3;
    }

    public void setVisiblePb4(int visiblePb4) {
        this.visiblePb4 = visiblePb4;
    }

    public int getVisiblePb4() {
        return visiblePb4;
    }

    public void setVisiblePb5(int visiblePb5) {
        this.visiblePb5 = visiblePb5;
    }

    public int getVisiblePb5() {
        return visiblePb5;
    }

    public void setVisiblePb6(int visiblePb6) {
        this.visiblePb6 = visiblePb6;
    }

    public int getVisiblePb6() {
        return visiblePb6;
    }

    public void setListaVacia(List listaVacia) {
        this.listaVacia = listaVacia;
    }

    public List getListaVacia() {
        return listaVacia;
    }

    public void setListaViajesExitosos(List<BeanEstCliente> listaViajesExitosos) {
        this.listaViajesExitosos = listaViajesExitosos;
    }

    public List<BeanEstCliente> getListaViajesExitosos() {
        return listaViajesExitosos;
    }

    public void setListaViajesFallidos(List<BeanEstCliente> listaViajesFallidos) {
        this.listaViajesFallidos = listaViajesFallidos;
    }

    public List<BeanEstCliente> getListaViajesFallidos() {
        return listaViajesFallidos;
    }

    public void setListaViajesTotal(List<BeanEstCliente> listaViajesTotal) {
        this.listaViajesTotal = listaViajesTotal;
    }

    public List<BeanEstCliente> getListaViajesTotal() {
        return listaViajesTotal;
    }

    public void setListaProv(List<BeanEstCliente> listaProv) {
        this.listaProv = listaProv;
    }

    public List<BeanEstCliente> getListaProv() {
        return listaProv;
    }

    public void setListaProp(List<BeanEstCliente> listaProp) {
        this.listaProp = listaProp;
    }

    public List<BeanEstCliente> getListaProp() {
        return listaProp;
    }

    public void setGuias(List<BeanTRGuia> guias) {
        this.guias = guias;
    }

    public List<BeanTRGuia> getGuias() {
        return guias;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }
}
