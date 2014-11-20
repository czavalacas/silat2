package silat.servicios_negocio.Beans;

import java.io.Serializable;
import java.util.Date;

public class BeanManifiesto implements Serializable, Prototype {
    @SuppressWarnings("compatibility:-4376050120073302764")
    private static final long serialVersionUID = 1L;
    
    private String cObservaciones;
    private String cTipoDoc;
    private String tipoDoc1Car;
    private Date fechaManifiesto;
    private Double nAdelanto;
    private Integer nEstManifiesto;
    private Double nFletePactado;
    private Integer nidManifiesto;
    private BeanEmpresa trManifiesto;
    private Integer nidChof;
    private Integer nidFlota;
    private Date fechaManifiestoMIN;
    private Date fechaManifiestoMAX;
    private BeanError beanError;
    private String simboloFLete;
    private String simboloAdelanto;
    private BeanFlota beanFlota;
    private BeanChofer beanChofer;
    private Double igv;
    private Double detraccion;
    private String estadoManifiestoNegocio;
    private String descEstadoManifiestoNegocio;
    private int index;
    private Double detraccionReal;
    
    private String detalleWebMovilEMPManifiesto;

    public BeanManifiesto(){
        
    }
    
    public BeanManifiesto(String cObservaciones, String cTipoDoc, String tipoDoc1Car, Date fechaManifiesto,
                          Double nAdelanto, Integer nEstManifiesto, Double nFletePactado, Integer nidManifiesto,
                          BeanEmpresa trManifiesto, Integer nidChof, Integer nidFlota, Date fechaManifiestoMIN,
                          Date fechaManifiestoMAX, BeanError beanError, String simboloFLete, String simboloAdelanto,
                          BeanFlota beanFlota, BeanChofer beanChofer, Double igv, Double detraccion,
                          String estadoManifiestoNegocio, String descEstadoManifiestoNegocio,int index) {
        this.cObservaciones = cObservaciones;
        this.cTipoDoc = cTipoDoc;
        this.tipoDoc1Car = tipoDoc1Car;
        this.fechaManifiesto = fechaManifiesto;
        this.nAdelanto = nAdelanto;
        this.nEstManifiesto = nEstManifiesto;
        this.nFletePactado = nFletePactado;
        this.nidManifiesto = nidManifiesto;
        this.trManifiesto = trManifiesto;
        this.nidChof = nidChof;
        this.nidFlota = nidFlota;
        this.fechaManifiestoMIN = fechaManifiestoMIN;
        this.fechaManifiestoMAX = fechaManifiestoMAX;
        this.beanError = beanError;
        this.simboloFLete = simboloFLete;
        this.simboloAdelanto = simboloAdelanto;
        this.beanFlota = beanFlota;
        this.beanChofer = beanChofer;
        this.igv = igv;
        this.detraccion = detraccion;
        this.estadoManifiestoNegocio = estadoManifiestoNegocio;
        this.descEstadoManifiestoNegocio = descEstadoManifiestoNegocio;
        this.index = index;
    }

    @Override
    public Prototype doClone() {
        return new BeanManifiesto(cObservaciones, cTipoDoc, tipoDoc1Car, fechaManifiesto, nAdelanto, nEstManifiesto,
                                  nFletePactado, nidManifiesto, trManifiesto, nidChof, nidFlota, fechaManifiestoMIN,
                                  fechaManifiestoMAX, beanError, simboloFLete, simboloAdelanto, beanFlota, beanChofer,
                                  igv, detraccion, estadoManifiestoNegocio, descEstadoManifiestoNegocio,index);
    }
    
    public void setCObservaciones(String cObservaciones) {
        this.cObservaciones = cObservaciones;
    }

    public String getCObservaciones() {
        return cObservaciones;
    }

    public void setCTipoDoc(String cTipoDoc) {
        this.cTipoDoc = cTipoDoc;
    }

    public String getCTipoDoc() {
        return cTipoDoc;
    }

    public void setFechaManifiesto(Date fechaManifiesto) {
        this.fechaManifiesto = fechaManifiesto;
    }

    public Date getFechaManifiesto() {
        return fechaManifiesto;
    }

    public void setNAdelanto(Double nAdelanto) {
        this.nAdelanto = nAdelanto;
    }

    public Double getNAdelanto() {
        return nAdelanto;
    }

    public void setNEstManifiesto(Integer nEstManifiesto) {
        this.nEstManifiesto = nEstManifiesto;
    }

    public Integer getNEstManifiesto() {
        return nEstManifiesto;
    }

    public void setNFletePactado(Double nFletePactado) {
        this.nFletePactado = nFletePactado;
    }

    public Double getNFletePactado() {
        return nFletePactado;
    }

    public void setNidManifiesto(Integer nidManifiesto) {
        this.nidManifiesto = nidManifiesto;
    }

    public Integer getNidManifiesto() {
        return nidManifiesto;
    }

    public void setNidChof(Integer nidChof) {
        this.nidChof = nidChof;
    }

    public Integer getNidChof() {
        return nidChof;
    }

    public void setNidFlota(Integer nidFlota) {
        this.nidFlota = nidFlota;
    }

    public Integer getNidFlota() {
        return nidFlota;
    }

    public void setTrManifiesto(BeanEmpresa trManifiesto) {
        this.trManifiesto = trManifiesto;
    }

    public BeanEmpresa getTrManifiesto() {
        return trManifiesto;
    }

    public void setFechaManifiestoMIN(Date fechaManifiestoMIN) {
        this.fechaManifiestoMIN = fechaManifiestoMIN;
    }

    public Date getFechaManifiestoMIN() {
        return fechaManifiestoMIN;
    }

    public void setFechaManifiestoMAX(Date fechaManifiestoMAX) {
        this.fechaManifiestoMAX = fechaManifiestoMAX;
    }

    public Date getFechaManifiestoMAX() {
        return fechaManifiestoMAX;
    }

    public void setTipoDoc1Car(String tipoDoc1Car) {
        this.tipoDoc1Car = tipoDoc1Car;
    }

    public String getTipoDoc1Car() {
        return tipoDoc1Car;
    }

    public void setBeanError(BeanError beanError) {
        this.beanError = beanError;
    }

    public BeanError getBeanError() {
        return beanError;
    }

    public void setSimboloFLete(String simboloFLete) {
        this.simboloFLete = simboloFLete;
    }

    public String getSimboloFLete() {
        return simboloFLete;
    }

    public void setSimboloAdelanto(String simboloAdelanto) {
        this.simboloAdelanto = simboloAdelanto;
    }

    public String getSimboloAdelanto() {
        return simboloAdelanto;
    }

    public void setBeanFlota(BeanFlota beanFlota) {
        this.beanFlota = beanFlota;
    }

    public BeanFlota getBeanFlota() {
        return beanFlota;
    }

    public void setBeanChofer(BeanChofer beanChofer) {
        this.beanChofer = beanChofer;
    }

    public BeanChofer getBeanChofer() {
        return beanChofer;
    }

    public void setIgv(Double igv) {
        this.igv = igv;
    }

    public Double getIgv() {
        return igv;
    }

    public void setDetraccion(Double detraccion) {
        this.detraccion = detraccion;
    }

    public Double getDetraccion() {
        return detraccion;
    }

    public void setEstadoManifiestoNegocio(String estadoManifiestoNegocio) {
        this.estadoManifiestoNegocio = estadoManifiestoNegocio;
    }

    public String getEstadoManifiestoNegocio() {
        return estadoManifiestoNegocio;
    }

    public void setDescEstadoManifiestoNegocio(String descEstadoManifiestoNegocio) {
        this.descEstadoManifiestoNegocio = descEstadoManifiestoNegocio;
    }

    public String getDescEstadoManifiestoNegocio() {
        return descEstadoManifiestoNegocio;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setDetraccionReal(Double detraccionReal) {
        this.detraccionReal = detraccionReal;
    }

    public Double getDetraccionReal() {
        return detraccionReal;
    }

    public void setDetalleWebMovilEMPManifiesto(String detalleWebMovilEMPManifiesto) {
        this.detalleWebMovilEMPManifiesto = detalleWebMovilEMPManifiesto;
    }

    public String getDetalleWebMovilEMPManifiesto() {
        return detalleWebMovilEMPManifiesto;
    }
}
