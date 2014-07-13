package siat.view.backing.transporte.manifiesto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import silat.servicios_negocio.Beans.BeanManifiesto;
import silat.servicios_negocio.Beans.BeanTRGuia;

public class SessionScopedBeanConsultarManifiesto {
    
    private int exec = 0;
    private List<BeanManifiesto> lstBeanManifiesto = new ArrayList<BeanManifiesto>();
    private List<BeanTRGuia> lstGuiasTabla = new ArrayList<BeanTRGuia>();
    private BeanManifiesto beanManif =new BeanManifiesto();
    private BeanManifiesto beanManif_BU =new BeanManifiesto();
    private Date fecMin;
    private Date fecMax;
    private Integer nidManif;
    private int nidEmpProv;
    private String razonSocial;
    private Double flete;
    private Double adelanto;
    private String simboloFLete;
    private String simboloAdela;
    private String observ;
    private boolean isAsc = true;
    private boolean disabComp = false;
    private String dialogTitulo;
    private String estadoManif;
    private Double saldo;
    private boolean disabEstado = false;
    /*Campos*/
    private Date fecManf;
    private int nidManifies;
    private Double dflete;
    private Double dadelanto;
    private String observManif;
    private String estadoManifNeg;
    private Double igvManif;
    private Double detracManif;
    private String tipDoc;
    private Double igvVal;
    private Double saldoVal;
    private Double montoFinalVal;
    private Double detraccionVal;
    //dfloresgonz 06.11.2013
    private String estadoManifActivoNoAct = "1";
    //dfloresgonz 19.06.2014 - Se agrega variable saber si tiene el permiso de modificar fechas
    private boolean renderBtnFecha = false;
    
    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }

    public void setLstBeanManifiesto(List<BeanManifiesto> lstBeanManifiesto) {
        this.lstBeanManifiesto = lstBeanManifiesto;
    }

    public List<BeanManifiesto> getLstBeanManifiesto() {
        return lstBeanManifiesto;
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

    public void setNidManif(Integer nidManif) {
        this.nidManif = nidManif;
    }

    public Integer getNidManif() {
        return nidManif;
    }

    public void setNidEmpProv(int nidEmpProv) {
        this.nidEmpProv = nidEmpProv;
    }

    public int getNidEmpProv() {
        return nidEmpProv;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setFlete(Double flete) {
        this.flete = flete;
    }

    public Double getFlete() {
        return flete;
    }

    public void setAdelanto(Double adelanto) {
        this.adelanto = adelanto;
    }

    public Double getAdelanto() {
        return adelanto;
    }

    public void setSimboloFLete(String simboloFLete) {
        this.simboloFLete = simboloFLete;
    }

    public String getSimboloFLete() {
        return simboloFLete;
    }

    public void setSimboloAdela(String simboloAdela) {
        this.simboloAdela = simboloAdela;
    }

    public String getSimboloAdela() {
        return simboloAdela;
    }

    public void setObserv(String observ) {
        this.observ = observ;
    }

    public String getObserv() {
        return observ;
    }

    public void setIsAsc(boolean isAsc) {
        this.isAsc = isAsc;
    }

    public boolean isIsAsc() {
        return isAsc;
    }

    public void setLstGuiasTabla(List<BeanTRGuia> lstGuiasTabla) {
        this.lstGuiasTabla = lstGuiasTabla;
    }

    public List<BeanTRGuia> getLstGuiasTabla() {
        return lstGuiasTabla;
    }

    public void setDialogTitulo(String dialogTitulo) {
        this.dialogTitulo = dialogTitulo;
    }

    public String getDialogTitulo() {
        return dialogTitulo;
    }

    public void setEstadoManif(String estadoManif) {
        this.estadoManif = estadoManif;
    }

    public String getEstadoManif() {
        return estadoManif;
    }

    public void setBeanManif(BeanManifiesto beanManif) {
        this.beanManif = beanManif;
    }

    public BeanManifiesto getBeanManif() {
        return beanManif;
    }

    public void setDisabComp(boolean disabComp) {
        this.disabComp = disabComp;
    }

    public boolean isDisabComp() {
        return disabComp;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setBeanManif_BU(BeanManifiesto beanManif_BU) {
        this.beanManif_BU = beanManif_BU;
    }

    public BeanManifiesto getBeanManif_BU() {
        return beanManif_BU;
    }

    public void setDisabEstado(boolean disabEstado) {
        this.disabEstado = disabEstado;
    }

    public boolean isDisabEstado() {
        return disabEstado;
    }

    public void setFecManf(Date fecManf) {
        this.fecManf = fecManf;
    }

    public Date getFecManf() {
        return fecManf;
    }

    public void setNidManifies(int nidManifies) {
        this.nidManifies = nidManifies;
    }

    public int getNidManifies() {
        return nidManifies;
    }

    public void setDflete(Double dflete) {
        this.dflete = dflete;
    }

    public Double getDflete() {
        return dflete;
    }

    public void setDadelanto(Double dadelanto) {
        this.dadelanto = dadelanto;
    }

    public Double getDadelanto() {
        return dadelanto;
    }

    public void setObservManif(String observManif) {
        this.observManif = observManif;
    }

    public String getObservManif() {
        return observManif;
    }

    public void setEstadoManifNeg(String estadoManifNeg) {
        this.estadoManifNeg = estadoManifNeg;
    }

    public String getEstadoManifNeg() {
        return estadoManifNeg;
    }

    public void setIgvManif(Double igvManif) {
        this.igvManif = igvManif;
    }

    public Double getIgvManif() {
        return igvManif;
    }

    public void setDetracManif(Double detracManif) {
        this.detracManif = detracManif;
    }

    public Double getDetracManif() {
        return detracManif;
    }

    public void setTipDoc(String tipDoc) {
        this.tipDoc = tipDoc;
    }

    public String getTipDoc() {
        return tipDoc;
    }

    public void setIgvVal(Double igvVal) {
        this.igvVal = igvVal;
    }

    public Double getIgvVal() {
        return igvVal;
    }

    public void setSaldoVal(Double saldoVal) {
        this.saldoVal = saldoVal;
    }

    public Double getSaldoVal() {
        return saldoVal;
    }

    public void setMontoFinalVal(Double montoFinalVal) {
        this.montoFinalVal = montoFinalVal;
    }

    public Double getMontoFinalVal() {
        return montoFinalVal;
    }

    public void setDetraccionVal(Double detraccionVal) {
        this.detraccionVal = detraccionVal;
    }

    public Double getDetraccionVal() {
        return detraccionVal;
    }

    public void setEstadoManifActivoNoAct(String estadoManifActivoNoAct) {
        this.estadoManifActivoNoAct = estadoManifActivoNoAct;
    }

    public String getEstadoManifActivoNoAct() {
        return estadoManifActivoNoAct;
    }

    public void setRenderBtnFecha(boolean renderBtnFecha) {
        this.renderBtnFecha = renderBtnFecha;
    }

    public boolean isRenderBtnFecha() {
        return renderBtnFecha;
    }
}
