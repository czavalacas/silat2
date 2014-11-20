package siat.view.backing.estadistico;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import silat.servicios_negocio.Beans.BeanEstCliente;

public class SessionScopedEstadisticosCliente implements Serializable{
    @SuppressWarnings("compatibility:-5648964018413670710")
    private static final long serialVersionUID = 1L;
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
    private List listaVacia = new ArrayList();
    private List<BeanEstCliente> lista_Ordenes_Fina;
    private List<BeanEstCliente> lista_Ordenes_Anul;
    private List<BeanEstCliente> lista_Ordenes_MES;
    private List<BeanEstCliente> lista_Clientes;
    private List<BeanEstCliente> lista_Clientes_MES;
    private List<SelectItem> listChoice;

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

    public void setLista_Ordenes_Fina(List<BeanEstCliente> lista_Ordenes_Fina) {
        this.lista_Ordenes_Fina = lista_Ordenes_Fina;
    }

    public List<BeanEstCliente> getLista_Ordenes_Fina() {
        return lista_Ordenes_Fina;
    }

    public void setLista_Ordenes_Anul(List<BeanEstCliente> lista_Ordenes_Anul) {
        this.lista_Ordenes_Anul = lista_Ordenes_Anul;
    }

    public List<BeanEstCliente> getLista_Ordenes_Anul() {
        return lista_Ordenes_Anul;
    }

    public void setLista_Ordenes_MES(List<BeanEstCliente> lista_Ordenes_MES) {
        this.lista_Ordenes_MES = lista_Ordenes_MES;
    }

    public List<BeanEstCliente> getLista_Ordenes_MES() {
        return lista_Ordenes_MES;
    }

    public void setLista_Clientes(List<BeanEstCliente> lista_Clientes) {
        this.lista_Clientes = lista_Clientes;
    }

    public List<BeanEstCliente> getLista_Clientes() {
        return lista_Clientes;
    }

    public void setLista_Clientes_MES(List<BeanEstCliente> lista_Clientes_MES) {
        this.lista_Clientes_MES = lista_Clientes_MES;
    }

    public List<BeanEstCliente> getLista_Clientes_MES() {
        return lista_Clientes_MES;
    }

    public void setListChoice(List<SelectItem> listChoice) {
        this.listChoice = listChoice;
    }

    public List<SelectItem> getListChoice() {
        return listChoice;
    }
}
