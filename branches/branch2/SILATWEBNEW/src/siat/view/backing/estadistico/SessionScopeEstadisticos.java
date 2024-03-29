package siat.view.backing.estadistico;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import javax.faces.model.SelectItem;

import silat.servicios_negocio.Beans.BeanCuadre;
import silat.servicios_negocio.Beans.BeanEstCliente;

public class SessionScopeEstadisticos implements Serializable {
    @SuppressWarnings("compatibility:8100234900757476178")
    private static final long serialVersionUID = 1L;
    private int columns= 2;
    private String rowHeights = "350px";
    private Date fecMin;
    private Date fecMax;
    private List<BeanCuadre> listGrafico;
    private List<BeanEstCliente> listGraficoGasMES;
    private List<BeanEstCliente> listGraficoGastosGen;
    private List<BeanEstCliente> listGraficoIngMes;
    private List<SelectItem> listChoice;
    private int visiblePb1 = 0;
    private int visiblePb2 = 0;
    private int visiblePb3 = 0;
    private int visiblePb4 = 0;
    private int visiblePb5 = 0;
    private int visiblePb6 = 0;
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

    public void setListGrafico(List<BeanCuadre> listGrafico) {
        this.listGrafico = listGrafico;
    }

    public List<BeanCuadre> getListGrafico() {
        return listGrafico;
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

    public void setListGraficoGasMES(List<BeanEstCliente> listGraficoGasMES) {
        this.listGraficoGasMES = listGraficoGasMES;
    }

    public List<BeanEstCliente> getListGraficoGasMES() {
        return listGraficoGasMES;
    }

    public void setListGraficoGastosGen(List<BeanEstCliente> listGraficoGastosGen) {
        this.listGraficoGastosGen = listGraficoGastosGen;
    }

    public List<BeanEstCliente> getListGraficoGastosGen() {
        return listGraficoGastosGen;
    }

    public void setListGraficoIngMes(List<BeanEstCliente> listGraficoIngMes) {
        this.listGraficoIngMes = listGraficoIngMes;
    }

    public List<BeanEstCliente> getListGraficoIngMes() {
        return listGraficoIngMes;
    }

    public void setListChoice(List<SelectItem> listChoice) {
        this.listChoice = listChoice;
    }

    public List<SelectItem> getListChoice() {
        return listChoice;
    }
}

