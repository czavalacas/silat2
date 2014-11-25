package siat.view.backing.transporte.manifiesto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.collections.BeanMap;

import siat.view.backing.utiles.Utils;
import siat.view.backing.utiles.fecha.FechaUtiles;

import silat.servicios_negocio.Beans.BeanADRelacionEmpresa;
import silat.servicios_negocio.Beans.BeanManifiesto;
import silat.servicios_negocio.Beans.BeanOrdenServicio;
import silat.servicios_negocio.Beans.BeanTRItem;
import silat.servicios_negocio.Beans.BeanUnidadMedida;

public class SessionScopedBeanRegistrarManifiesto {
    private BeanTRItem beanIteam = new BeanTRItem();
    private List<BeanTRItem> lstItems = new ArrayList<BeanTRItem>();
    private List<BeanADRelacionEmpresa> lstRemitentes;
    private List<BeanADRelacionEmpresa> lstProveedores;
    private List<BeanManifiesto> lstManif;
    private List<BeanOrdenServicio> lstOS;
    private List lstFlotas;
    private List lstChofers;
    private List lstDirecs;
    private List listaUNs;
    private String codUN;
    private List lstDirecsRemi;
    private List lstGuiasManif;
    private Date manifFecMin;
    private Date manifFecMax;
    private int manifId;
    private String manifRazSoc;
    private String nombreDialog;
    private String tituloWindow;
    private String estManif;
    private String detallOS;
    private String razSocOS;
    private int accion = 0; //Registrar = 1, Modificar = 2, Borrar = 3
    private boolean estadoFormManif = false;
    private boolean llenarCombosAfterInsertManif = false;
    private String imgGuiaProv;
    private String sourceImg;
    private String imgGuiaProvAux;
    private String update;
    private String razonSocRemi;
    private String rucRemi;
    private BeanManifiesto manifIngresado;
    private int exec = 0;
    private int nidManifiesto = 0;
    private Date fechaEmision = FechaUtiles.fechaActual();
    private Date fechaDespacho = FechaUtiles.fechaActual();
    private String cidGuia;
    private String nombOS;
    private String razSocCliente;
    private String rucCliente;
    private String razSocRemitente;
    private String rucRemitente;
    private String comboTipoManif;
    private String pboxDatosEmpTitulo;
    private String nombBtnImg;
    /*Variable inicializadas*/
    private int nidOs = 0;
    private int nidRemitente = 0;
    private String direcRemitente;
    private String cidDirecRemitente;
    private String cidDestinatario;
    private String direcDestino;
    private int nidDirecDestino;
    private String cidDirecDestino;
    private int numPaquetes;
    //   private String conformidad;
    private String empresaSC;
    private String rucEmpSC;
    private int estadoGuia;
    private Date fechaGuia;
    private String txtObservGuia;
    private int valorComboManif = 0;
    private String cidFlota;
    private String cidChofer;
    /*Datos manifiesto*/
    private int nidEmpProvTrans = 0;
    private Date fecManif = FechaUtiles.fechaActual();
    private Double fletePactado = 0.0;
    private Double adelanto = 0.0;
    private Double saldo = 0.0;
    private Double igv = 0.0;
    private Double detrac = 0.0;
    private String tipDoc;
    private String observ;
    /*Fin Datos manifiesto*/
    /*Componentes*/
    private boolean pboxManifiestoVis = false;
    private boolean pboxDatosEmpVis = false;
    private boolean pboxDatosTransVis = false;
    private boolean txtFleteDisable = false;
    private boolean txtAdelaDisable = false;
    private boolean txtTipDocuDisable = false;
    private boolean txtObsvDisable = false;
    private boolean visibTxtRazSocProvTransBehav = false;
    private boolean visibTxtRazSocProvTrans2 = false;
    private boolean disabSocFlota = true;
    private boolean disabSocChofer = true;
    private boolean visibRucEmpTrans = false;
    private boolean visibSocFlota = false;
    private boolean visibSocChofer = false;
    private boolean disableWhenOK = false;
    private boolean rendBuscManif = false;
    private boolean disabRegisManif = false;
    private boolean disabInputFile = false;
    /**/
    private String marcaVehi;
    private String placa;
    private String configVehi;
    private String licen;
    private String conductor;
    private String certiInsc;
    private boolean visibleSocConf = false;
    private List lstConf;
    private String confo;
    private boolean visibOSAux = false;
    private boolean visibOS = true;
    private boolean disableComponents = false;
    private Double igvVal;
    private Double saldoVal;
    private Double montoFinalVal;
    private Double detraccionVal;
    private String razSocProv;
    private String textChBoxEstOS;
    private String textChBoxEstManif;
    private boolean estCambioOS = false;
    private boolean estCambioManif = false;
    private List<BeanUnidadMedida> lstUndMedida = new ArrayList<BeanUnidadMedida>();
    private BeanUnidadMedida beanUnidadMedida;
    private String descUM;
    private String valUM;
    //dfloresgonz 01.05.2014 - Variable que tendra la ruta de llamada al servlet de mostrar imagen
    private String rutaImgGuia;
    private boolean transportePropio = false;

    public SessionScopedBeanRegistrarManifiesto(){
        
    }
    public boolean contiene(BeanTRItem beanIteam) {
        for (BeanTRItem item : getLstItems()) {
            if (comparaItems(item, beanIteam)) {
                return true;
            }
        }
        return false;
    }

    public boolean comparaItems(Object oldObject, Object newObject) {
        try {
            BeanMap map = new BeanMap(oldObject);
            PropertyUtilsBean propUtils = new PropertyUtilsBean();
            int a = 0;
            for (Object propNameObject : map.keySet()) { //System.out.println("a: "+a);a++;
                String propertyName = (String)propNameObject;
                Object property1 = propUtils.getProperty(oldObject, propertyName);
                Object property2 = propUtils.getProperty(newObject, propertyName);
                if (property1 != null && property2 != null) {
                    if (property1.equals(property2)) {
                        System.out.println("  " + propertyName + " is equal");
                    } else {
                        System.out.println("> " + propertyName + " is different (oldValue=\"" + property1 +
                                           "\", newValue=\"" + property2 + "\")");
                        return false;
                    }
                }
                if ((property1 != null && property2 == null) || (property1 == null && property2 != null)) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean isLstItems_OK() {
        boolean lstItemsOK = false;
        int tamano = (this.getLstItems() != null ? this.getLstItems().size() : 0);
        if (tamano > 0) {
            lstItemsOK = true;
        }
        if (tamano <= 0) {
            lstItemsOK = false;
            FacesContext ctx = FacesContext.getCurrentInstance();
            Utils.throwError_Aux(ctx, "Debe ingresar al menos un Item.", 4);
        }
        return lstItemsOK;
    }
    
    public void setBeanIteam(BeanTRItem beanIteam) {
        this.beanIteam = beanIteam;
    }

    public BeanTRItem getBeanIteam() {
        return beanIteam;
    }

    public void setLstItems(List<BeanTRItem> lstItems) {
        this.lstItems = lstItems;
    }

    public List<BeanTRItem> getLstItems() {
        return lstItems;
    }

    public void setLstRemitentes(List<BeanADRelacionEmpresa> lstRemitentes) {
        this.lstRemitentes = lstRemitentes;
    }

    public List<BeanADRelacionEmpresa> getLstRemitentes() {
        return lstRemitentes;
    }

    public void setLstProveedores(List<BeanADRelacionEmpresa> lstProveedores) {
        this.lstProveedores = lstProveedores;
    }

    public List<BeanADRelacionEmpresa> getLstProveedores() {
        return lstProveedores;
    }

    public void setLstManif(List<BeanManifiesto> lstManif) {
        this.lstManif = lstManif;
    }

    public List<BeanManifiesto> getLstManif() {
        return lstManif;
    }

    public void setLstOS(List<BeanOrdenServicio> lstOS) {
        this.lstOS = lstOS;
    }

    public List<BeanOrdenServicio> getLstOS() {
        return lstOS;
    }

    public void setLstFlotas(List lstFlotas) {
        this.lstFlotas = lstFlotas;
    }

    public List getLstFlotas() {
        return lstFlotas;
    }

    public void setLstChofers(List lstChofers) {
        this.lstChofers = lstChofers;
    }

    public List getLstChofers() {
        return lstChofers;
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

    public void setLstGuiasManif(List lstGuiasManif) {
        this.lstGuiasManif = lstGuiasManif;
    }

    public List getLstGuiasManif() {
        return lstGuiasManif;
    }

    public void setManifFecMin(Date manifFecMin) {
        this.manifFecMin = manifFecMin;
    }

    public Date getManifFecMin() {
        return manifFecMin;
    }

    public void setManifFecMax(Date manifFecMax) {
        this.manifFecMax = manifFecMax;
    }

    public Date getManifFecMax() {
        return manifFecMax;
    }

    public void setManifId(int manifId) {
        this.manifId = manifId;
    }

    public int getManifId() {
        return manifId;
    }

    public void setManifRazSoc(String manifRazSoc) {
        this.manifRazSoc = manifRazSoc;
    }

    public String getManifRazSoc() {
        return manifRazSoc;
    }

    public void setNombreDialog(String nombreDialog) {
        this.nombreDialog = nombreDialog;
    }

    public String getNombreDialog() {
        return nombreDialog;
    }

    public void setTituloWindow(String tituloWindow) {
        this.tituloWindow = tituloWindow;
    }

    public String getTituloWindow() {
        return tituloWindow;
    }

    public void setEstManif(String estManif) {
        this.estManif = estManif;
    }

    public String getEstManif() {
        return estManif;
    }

    public void setDetallOS(String detallOS) {
        this.detallOS = detallOS;
    }

    public String getDetallOS() {
        return detallOS;
    }

    public void setRazSocOS(String razSocOS) {
        this.razSocOS = razSocOS;
    }

    public String getRazSocOS() {
        return razSocOS;
    }

    public void setAccion(int accion) {
        this.accion = accion;
    }

    public int getAccion() {
        return accion;
    }

    public void setEstadoFormManif(boolean estadoFormManif) {
        this.estadoFormManif = estadoFormManif;
    }

    public boolean isEstadoFormManif() {
        return estadoFormManif;
    }

    public void setLlenarCombosAfterInsertManif(boolean llenarCombosAfterInsertManif) {
        this.llenarCombosAfterInsertManif = llenarCombosAfterInsertManif;
    }

    public boolean isLlenarCombosAfterInsertManif() {
        return llenarCombosAfterInsertManif;
    }

    public void setImgGuiaProv(String imgGuiaProv) {
        this.imgGuiaProv = imgGuiaProv;
    }

    public String getImgGuiaProv() {
        return imgGuiaProv;
    }

    public void setSourceImg(String sourceImg) {
        this.sourceImg = sourceImg;
    }

    public String getSourceImg() {
        return sourceImg;
    }

    public void setImgGuiaProvAux(String imgGuiaProvAux) {
        this.imgGuiaProvAux = imgGuiaProvAux;
    }

    public String getImgGuiaProvAux() {
        return imgGuiaProvAux;
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

    public void setManifIngresado(BeanManifiesto manifIngresado) {
        this.manifIngresado = manifIngresado;
    }

    public BeanManifiesto getManifIngresado() {
        return manifIngresado;
    }

    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }

    public void setNidManifiesto(int nidManifiesto) {
        this.nidManifiesto = nidManifiesto;
    }

    public int getNidManifiesto() {
        return nidManifiesto;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaDespacho(Date fechaDespacho) {
        this.fechaDespacho = fechaDespacho;
    }

    public Date getFechaDespacho() {
        return fechaDespacho;
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

    public void setRazSocCliente(String razSocCliente) {
        this.razSocCliente = razSocCliente;
    }

    public String getRazSocCliente() {
        return razSocCliente;
    }

    public void setRucCliente(String rucCliente) {
        this.rucCliente = rucCliente;
    }

    public String getRucCliente() {
        return rucCliente;
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

    public void setCidDirecDestino(String cidDirecDestino) {
        this.cidDirecDestino = cidDirecDestino;
    }

    public String getCidDirecDestino() {
        return cidDirecDestino;
    }

    public void setNumPaquetes(int numPaquetes) {
        this.numPaquetes = numPaquetes;
    }

    public int getNumPaquetes() {
        return numPaquetes;
    }

    public void setEmpresaSC(String empresaSC) {
        this.empresaSC = empresaSC;
    }

    public String getEmpresaSC() {
        return empresaSC;
    }

    public void setRucEmpSC(String rucEmpSC) {
        this.rucEmpSC = rucEmpSC;
    }

    public String getRucEmpSC() {
        return rucEmpSC;
    }

    public void setEstadoGuia(int estadoGuia) {
        this.estadoGuia = estadoGuia;
    }

    public int getEstadoGuia() {
        return estadoGuia;
    }

    public void setFechaGuia(Date fechaGuia) {
        this.fechaGuia = fechaGuia;
    }

    public Date getFechaGuia() {
        return fechaGuia;
    }

    public void setTxtObservGuia(String txtObservGuia) {
        this.txtObservGuia = txtObservGuia;
    }

    public String getTxtObservGuia() {
        return txtObservGuia;
    }

    public void setValorComboManif(int valorComboManif) {
        this.valorComboManif = valorComboManif;
    }

    public int getValorComboManif() {
        return valorComboManif;
    }

    public void setCidFlota(String cidFlota) {
        this.cidFlota = cidFlota;
    }

    public String getCidFlota() {
        return cidFlota;
    }

    public void setCidChofer(String cidChofer) {
        this.cidChofer = cidChofer;
    }

    public String getCidChofer() {
        return cidChofer;
    }

    public void setNidEmpProvTrans(int nidEmpProvTrans) {
        this.nidEmpProvTrans = nidEmpProvTrans;
    }

    public int getNidEmpProvTrans() {
        return nidEmpProvTrans;
    }

    public void setFecManif(Date fecManif) {
        this.fecManif = fecManif;
    }

    public Date getFecManif() {
        return fecManif;
    }

    public void setFletePactado(Double fletePactado) {
        this.fletePactado = fletePactado;
    }

    public Double getFletePactado() {
        return fletePactado;
    }

    public void setAdelanto(Double adelanto) {
        this.adelanto = adelanto;
    }

    public Double getAdelanto() {
        return adelanto;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setIgv(Double igv) {
        this.igv = igv;
    }

    public Double getIgv() {
        return igv;
    }

    public void setDetrac(Double detrac) {
        this.detrac = detrac;
    }

    public Double getDetrac() {
        return detrac;
    }

    public void setTipDoc(String tipDoc) {
        this.tipDoc = tipDoc;
    }

    public String getTipDoc() {
        return tipDoc;
    }

    public void setObserv(String observ) {
        this.observ = observ;
    }

    public String getObserv() {
        return observ;
    }

    public void setPboxManifiestoVis(boolean pboxManifiestoVis) {
        this.pboxManifiestoVis = pboxManifiestoVis;
    }

    public boolean isPboxManifiestoVis() {
        return pboxManifiestoVis;
    }

    public void setPboxDatosEmpVis(boolean pboxDatosEmpVis) {
        this.pboxDatosEmpVis = pboxDatosEmpVis;
    }

    public boolean isPboxDatosEmpVis() {
        return pboxDatosEmpVis;
    }

    public void setPboxDatosTransVis(boolean pboxDatosTransVis) {
        this.pboxDatosTransVis = pboxDatosTransVis;
    }

    public boolean isPboxDatosTransVis() {
        return pboxDatosTransVis;
    }

    public void setTxtFleteDisable(boolean txtFleteDisable) {
        this.txtFleteDisable = txtFleteDisable;
    }

    public boolean isTxtFleteDisable() {
        return txtFleteDisable;
    }

    public void setTxtAdelaDisable(boolean txtAdelaDisable) {
        this.txtAdelaDisable = txtAdelaDisable;
    }

    public boolean isTxtAdelaDisable() {
        return txtAdelaDisable;
    }

    public void setTxtTipDocuDisable(boolean txtTipDocuDisable) {
        this.txtTipDocuDisable = txtTipDocuDisable;
    }

    public boolean isTxtTipDocuDisable() {
        return txtTipDocuDisable;
    }

    public void setTxtObsvDisable(boolean txtObsvDisable) {
        this.txtObsvDisable = txtObsvDisable;
    }

    public boolean isTxtObsvDisable() {
        return txtObsvDisable;
    }

    public void setVisibTxtRazSocProvTransBehav(boolean visibTxtRazSocProvTransBehav) {
        this.visibTxtRazSocProvTransBehav = visibTxtRazSocProvTransBehav;
    }

    public boolean isVisibTxtRazSocProvTransBehav() {
        return visibTxtRazSocProvTransBehav;
    }

    public void setVisibTxtRazSocProvTrans2(boolean visibTxtRazSocProvTrans2) {
        this.visibTxtRazSocProvTrans2 = visibTxtRazSocProvTrans2;
    }

    public boolean isVisibTxtRazSocProvTrans2() {
        return visibTxtRazSocProvTrans2;
    }

    public void setDisabSocFlota(boolean disabSocFlota) {
        this.disabSocFlota = disabSocFlota;
    }

    public boolean isDisabSocFlota() {
        return disabSocFlota;
    }

    public void setDisabSocChofer(boolean disabSocChofer) {
        this.disabSocChofer = disabSocChofer;
    }

    public boolean isDisabSocChofer() {
        return disabSocChofer;
    }

    public void setVisibRucEmpTrans(boolean visibRucEmpTrans) {
        this.visibRucEmpTrans = visibRucEmpTrans;
    }

    public boolean isVisibRucEmpTrans() {
        return visibRucEmpTrans;
    }

    public void setVisibSocFlota(boolean visibSocFlota) {
        this.visibSocFlota = visibSocFlota;
    }

    public boolean isVisibSocFlota() {
        return visibSocFlota;
    }

    public void setVisibSocChofer(boolean visibSocChofer) {
        this.visibSocChofer = visibSocChofer;
    }

    public boolean isVisibSocChofer() {
        return visibSocChofer;
    }

    public void setDisableWhenOK(boolean disableWhenOK) {
        this.disableWhenOK = disableWhenOK;
    }

    public boolean isDisableWhenOK() {
        return disableWhenOK;
    }

    public void setRendBuscManif(boolean rendBuscManif) {
        this.rendBuscManif = rendBuscManif;
    }

    public boolean isRendBuscManif() {
        return rendBuscManif;
    }

    public void setDisabRegisManif(boolean disabRegisManif) {
        this.disabRegisManif = disabRegisManif;
    }

    public boolean isDisabRegisManif() {
        return disabRegisManif;
    }

    public void setDisabInputFile(boolean disabInputFile) {
        this.disabInputFile = disabInputFile;
    }

    public boolean isDisabInputFile() {
        return disabInputFile;
    }

    public void setMarcaVehi(String marcaVehi) {
        this.marcaVehi = marcaVehi;
    }

    public String getMarcaVehi() {
        return marcaVehi;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getPlaca() {
        return placa;
    }

    public void setConfigVehi(String configVehi) {
        this.configVehi = configVehi;
    }

    public String getConfigVehi() {
        return configVehi;
    }

    public void setLicen(String licen) {
        this.licen = licen;
    }

    public String getLicen() {
        return licen;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    public String getConductor() {
        return conductor;
    }

    public void setCertiInsc(String certiInsc) {
        this.certiInsc = certiInsc;
    }

    public String getCertiInsc() {
        return certiInsc;
    }

    public void setVisibleSocConf(boolean visibleSocConf) {
        this.visibleSocConf = visibleSocConf;
    }

    public boolean isVisibleSocConf() {
        return visibleSocConf;
    }

    public void setLstConf(List lstConf) {
        this.lstConf = lstConf;
    }

    public List getLstConf() {
        return lstConf;
    }

    public void setConfo(String confo) {
        this.confo = confo;
    }

    public String getConfo() {
        return confo;
    }

    public void setVisibOSAux(boolean visibOSAux) {
        this.visibOSAux = visibOSAux;
    }

    public boolean isVisibOSAux() {
        return visibOSAux;
    }

    public void setVisibOS(boolean visibOS) {
        this.visibOS = visibOS;
    }

    public boolean isVisibOS() {
        return visibOS;
    }

    public void setDisableComponents(boolean disableComponents) {
        this.disableComponents = disableComponents;
    }

    public boolean isDisableComponents() {
        return disableComponents;
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

    public void setRazSocProv(String razSocProv) {
        this.razSocProv = razSocProv;
    }

    public String getRazSocProv() {
        return razSocProv;
    }

    public void setTextChBoxEstOS(String textChBoxEstOS) {
        this.textChBoxEstOS = textChBoxEstOS;
    }

    public String getTextChBoxEstOS() {
        return textChBoxEstOS;
    }

    public void setTextChBoxEstManif(String textChBoxEstManif) {
        this.textChBoxEstManif = textChBoxEstManif;
    }

    public String getTextChBoxEstManif() {
        return textChBoxEstManif;
    }

    public void setEstCambioOS(boolean estCambioOS) {
        this.estCambioOS = estCambioOS;
    }

    public boolean isEstCambioOS() {
        return estCambioOS;
    }

    public void setEstCambioManif(boolean estCambioManif) {
        this.estCambioManif = estCambioManif;
    }

    public boolean isEstCambioManif() {
        return estCambioManif;
    }

    public void setLstUndMedida(List<BeanUnidadMedida> lstUndMedida) {
        this.lstUndMedida = lstUndMedida;
    }

    public List<BeanUnidadMedida> getLstUndMedida() {
        return lstUndMedida;
    }

    public void setBeanUnidadMedida(BeanUnidadMedida beanUnidadMedida) {
        this.beanUnidadMedida = beanUnidadMedida;
    }

    public BeanUnidadMedida getBeanUnidadMedida() {
        return beanUnidadMedida;
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

    public void setRutaImgGuia(String rutaImgGuia) {
        this.rutaImgGuia = rutaImgGuia;
    }

    public String getRutaImgGuia() {
        return rutaImgGuia;
    }

    public void setTransportePropio(boolean transportePropio) {
        this.transportePropio = transportePropio;
    }

    public boolean isTransportePropio() {
        return transportePropio;
    }

}
