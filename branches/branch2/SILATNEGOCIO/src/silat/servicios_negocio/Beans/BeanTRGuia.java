package silat.servicios_negocio.Beans;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import silat.servicios_negocio.entidades.admin.ADEmpresa;
import silat.servicios_negocio.entidades.audsis.TROrdenServicio;
import silat.servicios_negocio.entidades.trans.TRManifiesto;

public class BeanTRGuia implements Serializable, Cloneable  {

    @SuppressWarnings("compatibility:9154993613739243578")
    private static final long serialVersionUID = 1L;

    private String cConductorNomb;
    private String cConfigVehicular;
    private String cConformidad;
    private String descConformidad;
    private String cDireccionDestino;
    private String cDireccionRemitente;
    private String cMarcaVehiculo;
    private String cObservaciones;
    private String cPlaca;
    private String empCliente;
    private String cRazonSocEmpSub;
    private String cRucSubCont;
    private Date fechaGuia;
    private Date fecGuiaMin;
    private Date fecGuiaMax;
    private Date fechaDespacho;
    private Date fecDespachoMin;
    private Date fecDespachoMax;
    private String nEstadoGuia;
    private String cidGuia;
    private BigDecimal nidCliente; 
    private Integer numPaquetes;
    private Integer nidDireccionDestino;
    private Integer nidDireccionRemitente;
    private Integer nidFlota;
    private Integer nidChof;
    private BeanEmpresa adEmpresa; //Empresa proveedora q entrega carga
    private BeanOrdenServicio ordenServicio;
    private BeanError beanError;
    private String cidUnidadNegocio;
    private String hasManifiesto;
    private String hasFactura;
    private BeanFactura trFactura;
    private BeanManifiesto trManifiesto;
    List<BeanTRItem> itemsLista;
    private String imgGuiaProv;
    private byte[] imgGuia;
    private BigDecimal precio;
    private int isDisableWhenOk_Anulado;
    private String ubigeoDireccionRemitente;
    private String ubigeoDireccionDestino; 
    private BeanItemPreFactura itemPreFactura;
    private String auxIsInComboPreFact;
    private int grupo;
    //EDITABLE
    private boolean selected;
    private String styleAnulado;
    //dfloresgonz 01.05.2014 - Se agrega este campo auxiliar para poder realizar un filtro mas de busqueda
    //para realizar consultas a los items de la GUIA especificamente el texto en su campo C_CID_GUIA_REMITENTE
    private String filtroItemGuiaRemi;
    private String comentario;
    private Integer valoracion;
    private String nativeCidGuia;
    private BeanManifiesto manifiesto;
    
    private String fechaGuiaWebMovilEmp;
    private String imgGuiaProvedor;
    
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
    
    public void setCConductorNomb(String cConductorNomb) {
        this.cConductorNomb = cConductorNomb;
    }

    public String getCConductorNomb() {
        return cConductorNomb;
    }

    public void setCConfigVehicular(String cConfigVehicular) {
        this.cConfigVehicular = cConfigVehicular;
    }

    public String getCConfigVehicular() {
        return cConfigVehicular;
    }

    public void setCConformidad(String cConformidad) {
        this.cConformidad = cConformidad;
    }

    public String getCConformidad() {
        return cConformidad;
    }

    public void setCDireccionDestino(String cDireccionDestino) {
        this.cDireccionDestino = cDireccionDestino;
    }

    public String getCDireccionDestino() {
        return cDireccionDestino;
    }

    public void setCMarcaVehiculo(String cMarcaVehiculo) {
        this.cMarcaVehiculo = cMarcaVehiculo;
    }

    public String getCMarcaVehiculo() {
        return cMarcaVehiculo;
    }

    public void setCObservaciones(String cObservaciones) {
        this.cObservaciones = cObservaciones;
    }

    public String getCObservaciones() {
        return cObservaciones;
    }

    public void setCPlaca(String cPlaca) {
        this.cPlaca = cPlaca;
    }

    public String getCPlaca() {
        return cPlaca;
    }

    public void setCRazonSocEmpSub(String cRazonSocEmpSub) {
        this.cRazonSocEmpSub = cRazonSocEmpSub;
    }

    public String getCRazonSocEmpSub() {
        return cRazonSocEmpSub;
    }

    public void setCRucSubCont(String cRucSubCont) {
        this.cRucSubCont = cRucSubCont;
    }

    public String getCRucSubCont() {
        return cRucSubCont;
    }

    public void setFechaGuia(Date fechaGuia) {
        this.fechaGuia = fechaGuia;
    }

    public Date getFechaGuia() {
        return fechaGuia;
    }

    public void setNEstadoGuia(String nEstadoGuia) {
        this.nEstadoGuia = nEstadoGuia;
    }

    public String getNEstadoGuia() {
        return nEstadoGuia;
    }

    public void setCidGuia(String cidGuia) {
        this.cidGuia = cidGuia;
    }

    public String getCidGuia() {
        return cidGuia;
    }

    public void setNumPaquetes(Integer numPaquetes) {
        this.numPaquetes = numPaquetes;
    }

    public Integer getNumPaquetes() {
        return numPaquetes;
    }

    public void setAdEmpresa(BeanEmpresa adEmpresa) {
        this.adEmpresa = adEmpresa;
    }

    public BeanEmpresa getAdEmpresa() {
        return adEmpresa;
    }

    public void setOrdenServicio(BeanOrdenServicio ordenServicio) {
        this.ordenServicio = ordenServicio;
    }

    public BeanOrdenServicio getOrdenServicio() {
        return ordenServicio;
    }

    public void setNidDireccionDestino(Integer nidDireccionDestino) {
        this.nidDireccionDestino = nidDireccionDestino;
    }

    public Integer getNidDireccionDestino() {
        return nidDireccionDestino;
    }

    public void setNidDireccionRemitente(Integer nidDireccionRemitente) {
        this.nidDireccionRemitente = nidDireccionRemitente;
    }

    public Integer getNidDireccionRemitente() {
        return nidDireccionRemitente;
    }

    public void setNidFlota(Integer nidFlota) {
        this.nidFlota = nidFlota;
    }

    public Integer getNidFlota() {
        return nidFlota;
    }

    public void setNidChof(Integer nidChof) {
        this.nidChof = nidChof;
    }

    public Integer getNidChof() {
        return nidChof;
    }

    public void setBeanError(BeanError beanError) {
        this.beanError = beanError;
    }

    public BeanError getBeanError() {
        return beanError;
    }

    public void setFechaDespacho(Date fechaDespacho) {
        this.fechaDespacho = fechaDespacho;
    }

    public Date getFechaDespacho() {
        return fechaDespacho;
    }

    public void setCidUnidadNegocio(String cidUnidadNegocio) {
        this.cidUnidadNegocio = cidUnidadNegocio;
    }

    public String getCidUnidadNegocio() {
        return cidUnidadNegocio;
    }

    public void setFecGuiaMin(Date fecGuiaMin) {
        this.fecGuiaMin = fecGuiaMin;
    }

    public Date getFecGuiaMin() {
        return fecGuiaMin;
    }

    public void setFecGuiaMax(Date fecGuiaMax) {
        this.fecGuiaMax = fecGuiaMax;
    }

    public Date getFecGuiaMax() {
        return fecGuiaMax;
    }

    public void setFecDespachoMin(Date fecDespachoMin) {
        this.fecDespachoMin = fecDespachoMin;
    }

    public Date getFecDespachoMin() {
        return fecDespachoMin;
    }

    public void setFecDespachoMax(Date fecDespachoMax) {
        this.fecDespachoMax = fecDespachoMax;
    }

    public Date getFecDespachoMax() {
        return fecDespachoMax;
    }

    public void setEmpCliente(String empCliente) {
        this.empCliente = empCliente;
    }

    public String getEmpCliente() {
        return empCliente;
    }

    public void setDescConformidad(String descConformidad) {
        this.descConformidad = descConformidad;
    }

    public String getDescConformidad() {
        return descConformidad;
    }

    public void setCDireccionRemitente(String cDireccionRemitente) {
        this.cDireccionRemitente = cDireccionRemitente;
    }

    public String getCDireccionRemitente() {
        return cDireccionRemitente;
    }

    public void setTrManifiesto(BeanManifiesto trManifiesto) {
        this.trManifiesto = trManifiesto;
    }

    public BeanManifiesto getTrManifiesto() {
        return trManifiesto;
    }

    public void setHasManifiesto(String hasManifiesto) {
        this.hasManifiesto = hasManifiesto;
    }

    public String getHasManifiesto() {
        return hasManifiesto;
    }

    public void setItemsLista(List<BeanTRItem> itemsLista) {
        this.itemsLista = itemsLista;
    }

    public List<BeanTRItem> getItemsLista() {
        return itemsLista;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setHasFactura(String hasFactura) {
        this.hasFactura = hasFactura;
    }

    public String getHasFactura() {
        return hasFactura;
    }

    public void setIsDisableWhenOk_Anulado(int isDisableWhenOk_Anulado) {
        this.isDisableWhenOk_Anulado = isDisableWhenOk_Anulado;
    }

    public int getIsDisableWhenOk_Anulado() {
        return isDisableWhenOk_Anulado;
    }

    public void setUbigeoDireccionRemitente(String ubigeoDireccionRemitente) {
        this.ubigeoDireccionRemitente = ubigeoDireccionRemitente;
    }

    public String getUbigeoDireccionRemitente() {
        return ubigeoDireccionRemitente;
    }

    public void setUbigeoDireccionDestino(String ubigeoDireccionDestino) {
        this.ubigeoDireccionDestino = ubigeoDireccionDestino;
    }

    public String getUbigeoDireccionDestino() {
        return ubigeoDireccionDestino;
    }

    public void setTrFactura(BeanFactura trFactura) {
        this.trFactura = trFactura;
    }

    public BeanFactura getTrFactura() {
        return trFactura;
    }

    public void setNidCliente(BigDecimal nidCliente) {
        this.nidCliente = nidCliente;
    }

    public BigDecimal getNidCliente() {
        return nidCliente;
    }

    public void setItemPreFactura(BeanItemPreFactura itemPreFactura) {
        this.itemPreFactura = itemPreFactura;
    }

    public BeanItemPreFactura getItemPreFactura() {
        return itemPreFactura;
    }

    public void setAuxIsInComboPreFact(String auxIsInComboPreFact) {
        this.auxIsInComboPreFact = auxIsInComboPreFact;
    }

    public String getAuxIsInComboPreFact() {
        return auxIsInComboPreFact;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setStyleAnulado(String styleAnulado) {
        this.styleAnulado = styleAnulado;
    }

    public String getStyleAnulado() {
        return styleAnulado;
    }

    public void setFiltroItemGuiaRemi(String filtroItemGuiaRemi) {
        this.filtroItemGuiaRemi = filtroItemGuiaRemi;
    }

    public String getFiltroItemGuiaRemi() {
        return filtroItemGuiaRemi;
    }


    public void setImgGuiaProv(String imgGuiaProv) {
        this.imgGuiaProv = imgGuiaProv;
    }

    public String getImgGuiaProv() {
        return imgGuiaProv;
    }

    public void setImgGuia(byte[] imgGuia) {
        this.imgGuia = imgGuia;
    }

    public byte[] getImgGuia() {
        return imgGuia;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setValoracion(Integer valoracion) {
        this.valoracion = valoracion;
    }

    public Integer getValoracion() {
        return valoracion;
    }

    public void setNativeCidGuia(String nativeCidGuia) {
        this.nativeCidGuia = nativeCidGuia;
    }

    public String getNativeCidGuia() {
        return nativeCidGuia;
    }

    public void setFechaGuiaWebMovilEmp(String fechaGuiaWebMovilEmp) {
        this.fechaGuiaWebMovilEmp = fechaGuiaWebMovilEmp;
    }

    public String getFechaGuiaWebMovilEmp() {
        return fechaGuiaWebMovilEmp;
    }

    public void setManifiesto(BeanManifiesto manifiesto) {
        this.manifiesto = manifiesto;
    }

    public BeanManifiesto getManifiesto() {
        return manifiesto;
    }

    public void setImgGuiaProvedor(String imgGuiaProvedor) {
        this.imgGuiaProvedor = imgGuiaProvedor;
    }

    public String getImgGuiaProvedor() {
        return imgGuiaProvedor;
    }
}
