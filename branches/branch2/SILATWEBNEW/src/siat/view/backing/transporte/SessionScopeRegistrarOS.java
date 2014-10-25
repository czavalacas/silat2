package siat.view.backing.transporte;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import siat.view.backing.utiles.fecha.FechaUtiles;

import silat.servicios_negocio.Beans.BeanADRelacionEmpresa;
import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.Beans.BeanTRItem;
import silat.servicios_negocio.Beans.BeanUnidadMedida;

public class SessionScopeRegistrarOS {
    
    private List<BeanEmpresa> lstEmpresasCliente;
    private BeanEmpresa empresa;
    private String razSocial;
    private Date fechaHoy = FechaUtiles.fechaActual();
    private int nidOSGenerado;
    private int exec = 0;
    private BeanTRItem beanIteam = new BeanTRItem();
    private List<BeanTRItem> lstItems = new ArrayList<BeanTRItem>();
    private List<BeanADRelacionEmpresa> lstRemitentes= new ArrayList<BeanADRelacionEmpresa>();
    ///////Atributos de Guia
    private String razSocCliente;
    private String rucCliente;
    private String cidDirecDestino;
    private int nidOs = 0;
    private int nidRemitente = 0;
    private String direcRemitente;
    private String cidDirecRemitente;
    private String cidDestinatario;
    private String direcDestino;
    private int nidDirecDestino;
    private int numPaquetes;
    private String cidGuia;
    private String nombOS;
    private String razSocRemitente;
    private String rucRemitente;
    private String comboTipoManif;
    private String pboxDatosEmpTitulo;
    private String nombBtnImg;
    private List lstDirecs;
    private List listaUNs;
    private String codUN;
    private List lstDirecsRemi;
    private int accion = 0; //Registrar = 1, Modificar = 2, Borrar = 3
    private String rutaImgGuia;
    private boolean visibOS = true;
    private boolean disableComponents = false;
    private boolean visibOSAux = false;
    private boolean disabInputFile = false;
    private String imgGuiaProvAux;
    private String imgGuiaProv;
    private List<BeanUnidadMedida> lstUndMedida = new ArrayList<BeanUnidadMedida>();
    private String descUM;
    private String valUM;
    private String update;
    private String razonSocRemi;
    private String rucRemi;    
    
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

    public void setRazSocCliente(String razSocCliente) {
        this.razSocCliente = razSocCliente;
    }

    public String getRazSocCliente() {
        return razSocCliente;
    }

    public void setVisibOS(boolean visibOS) {
        this.visibOS = visibOS;
    }

    public boolean isVisibOS() {
        return visibOS;
    }

    public void setRucCliente(String rucCliente) {
        this.rucCliente = rucCliente;
    }

    public String getRucCliente() {
        return rucCliente;
    }

    public void setDisableComponents(boolean disableComponents) {
        this.disableComponents = disableComponents;
    }

    public boolean isDisableComponents() {
        return disableComponents;
    }

    public void setCidDirecDestino(String cidDirecDestino) {
        this.cidDirecDestino = cidDirecDestino;
    }

    public String getCidDirecDestino() {
        return cidDirecDestino;
    }

    public void setNidOs(int nidOs) {
        this.nidOs = nidOs;
    }

    public int getNidOs() {
        return nidOs;
    }

    public void setNidRemitente(int nidRemitente) {
        this.nidRemitente = nidRemitente;
    }

    public int getNidRemitente() {
        return nidRemitente;
    }

    public void setDirecRemitente(String direcRemitente) {
        this.direcRemitente = direcRemitente;
    }

    public String getDirecRemitente() {
        return direcRemitente;
    }

    public void setCidDirecRemitente(String cidDirecRemitente) {
        this.cidDirecRemitente = cidDirecRemitente;
    }

    public String getCidDirecRemitente() {
        return cidDirecRemitente;
    }

    public void setCidDestinatario(String cidDestinatario) {
        this.cidDestinatario = cidDestinatario;
    }

    public String getCidDestinatario() {
        return cidDestinatario;
    }

    public void setDirecDestino(String direcDestino) {
        this.direcDestino = direcDestino;
    }

    public String getDirecDestino() {
        return direcDestino;
    }

    public void setNidDirecDestino(int nidDirecDestino) {
        this.nidDirecDestino = nidDirecDestino;
    }

    public int getNidDirecDestino() {
        return nidDirecDestino;
    }

    public void setNumPaquetes(int numPaquetes) {
        this.numPaquetes = numPaquetes;
    }

    public int getNumPaquetes() {
        return numPaquetes;
    }

    public void setLstDirecs(List lstDirecs) {
        this.lstDirecs = lstDirecs;
    }

    public List getLstDirecs() {
        return lstDirecs;
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

    public void setLstDirecsRemi(List lstDirecsRemi) {
        this.lstDirecsRemi = lstDirecsRemi;
    }

    public List getLstDirecsRemi() {
        return lstDirecsRemi;
    }

    public void setCidGuia(String cidGuia) {
        this.cidGuia = cidGuia;
    }

    public String getCidGuia() {
        return cidGuia;
    }

    public void setNombOS(String nombOS) {
        this.nombOS = nombOS;
    }

    public String getNombOS() {
        return nombOS;
    }

    public void setRazSocRemitente(String razSocRemitente) {
        this.razSocRemitente = razSocRemitente;
    }

    public String getRazSocRemitente() {
        return razSocRemitente;
    }

    public void setRucRemitente(String rucRemitente) {
        this.rucRemitente = rucRemitente;
    }

    public String getRucRemitente() {
        return rucRemitente;
    }

    public void setComboTipoManif(String comboTipoManif) {
        this.comboTipoManif = comboTipoManif;
    }

    public String getComboTipoManif() {
        return comboTipoManif;
    }

    public void setPboxDatosEmpTitulo(String pboxDatosEmpTitulo) {
        this.pboxDatosEmpTitulo = pboxDatosEmpTitulo;
    }

    public String getPboxDatosEmpTitulo() {
        return pboxDatosEmpTitulo;
    }

    public void setNombBtnImg(String nombBtnImg) {
        this.nombBtnImg = nombBtnImg;
    }

    public String getNombBtnImg() {
        return nombBtnImg;
    }

    public void setVisibOSAux(boolean visibOSAux) {
        this.visibOSAux = visibOSAux;
    }

    public boolean isVisibOSAux() {
        return visibOSAux;
    }

    public void setBeanIteam(BeanTRItem beanIteam) {
        this.beanIteam = beanIteam;
    }

    public BeanTRItem getBeanIteam() {
        return beanIteam;
    }

    public void setAccion(int accion) {
        this.accion = accion;
    }

    public int getAccion() {
        return accion;
    }

    public void setLstItems(List<BeanTRItem> lstItems) {
        this.lstItems = lstItems;
    }

    public List<BeanTRItem> getLstItems() {
        return lstItems;
    }

    public void setRutaImgGuia(String rutaImgGuia) {
        this.rutaImgGuia = rutaImgGuia;
    }

    public String getRutaImgGuia() {
        return rutaImgGuia;
    }

    public void setImgGuiaProvAux(String imgGuiaProvAux) {
        this.imgGuiaProvAux = imgGuiaProvAux;
    }

    public String getImgGuiaProvAux() {
        return imgGuiaProvAux;
    }

    public void setImgGuiaProv(String imgGuiaProv) {
        this.imgGuiaProv = imgGuiaProv;
    }

    public String getImgGuiaProv() {
        return imgGuiaProv;
    }

    public void setDisabInputFile(boolean disabInputFile) {
        this.disabInputFile = disabInputFile;
    }

    public boolean isDisabInputFile() {
        return disabInputFile;
    }

    public void setLstUndMedida(List<BeanUnidadMedida> lstUndMedida) {
        this.lstUndMedida = lstUndMedida;
    }

    public List<BeanUnidadMedida> getLstUndMedida() {
        return lstUndMedida;
    }

    public void setDescUM(String descUM) {
        this.descUM = descUM;
    }

    public String getDescUM() {
        return descUM;
    }

    public void setValUM(String valUM) {
        this.valUM = valUM;
    }

    public String getValUM() {
        return valUM;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getUpdate() {
        return update;
    }

    public void setRazonSocRemi(String razonSocRemi) {
        this.razonSocRemi = razonSocRemi;
    }

    public String getRazonSocRemi() {
        return razonSocRemi;
    }

    public void setRucRemi(String rucRemi) {
        this.rucRemi = rucRemi;
    }

    public String getRucRemi() {
        return rucRemi;
    }

    public void setLstRemitentes(List<BeanADRelacionEmpresa> lstRemitentes) {
        this.lstRemitentes = lstRemitentes;
    }

    public List<BeanADRelacionEmpresa> getLstRemitentes() {
        return lstRemitentes;
    }
}
