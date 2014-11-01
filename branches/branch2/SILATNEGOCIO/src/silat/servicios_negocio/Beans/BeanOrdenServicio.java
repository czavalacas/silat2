package silat.servicios_negocio.Beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import silat.servicios_negocio.entidades.trans.TRItemXOrds;

public class BeanOrdenServicio implements Serializable{

    @SuppressWarnings("compatibility:4068978779747615176")
    private static final long serialVersionUID = 1L;
    
    private String cDetalle;
    private Date fecOrdnServ;
    private BigDecimal nEstadoOrden;
    private Integer nidOrdnServ;
    private BigDecimal nidEmpresa;
    private String cEstord;
    private String cRazonSocial;
    private String output;
    private String cidError;
    private BeanEmpresa adEmpresa;
    private String cEstadoOrdenDesc;
    private Date fecOrdnMin;
    private Date fecOrdnMax;
    private BeanError beanError;
    private String flgVisto;
    private String comentario;
    private List<BeanTrItemXOrds> itemsLista;
    private Integer nidRemitente;
    private String nidDirecCli;
    private String nidDirecProv;
    
    public void setCDetalle(String cDetalle) {
        this.cDetalle = cDetalle;
    }

    public String getCDetalle() {
        return cDetalle;
    }

    public void setFecOrdnServ(Date fecOrdnServ) {
        this.fecOrdnServ = fecOrdnServ;
    }

    public Date getFecOrdnServ() {
        return fecOrdnServ;
    }

    public void setNEstadoOrden(BigDecimal nEstadoOrden) {
        this.nEstadoOrden = nEstadoOrden;
    }

    public BigDecimal getNEstadoOrden() {
        return nEstadoOrden;
    }

    public void setNidOrdnServ(Integer nidOrdnServ) {
        this.nidOrdnServ = nidOrdnServ;
    }

    public Integer getNidOrdnServ() {
        return nidOrdnServ;
    }

    public void setNidEmpresa(BigDecimal nidEmpresa) {
        this.nidEmpresa = nidEmpresa;
    }

    public BigDecimal getNidEmpresa() {
        return nidEmpresa;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getOutput() {
        return output;
    }

    public void setCidError(String cidError) {
        this.cidError = cidError;
    }

    public String getCidError() {
        return cidError;
    }

    public void setCRazonSocial(String cRazonSocial) {
        this.cRazonSocial = cRazonSocial;
        
    }

    public String getCRazonSocial() {
        return cRazonSocial;
    }

    public void setAdEmpresa(BeanEmpresa adEmpresa) {
        this.adEmpresa = adEmpresa;
    }

    public BeanEmpresa getAdEmpresa() {
        return adEmpresa;
    }

    public void setCEstord(String cEstord) {
        this.cEstord = cEstord;
    }

    public String getCEstord() {
        return cEstord;
    }

    public void setCEstadoOrdenDesc(String cEstadoOrdenDesc) {
        this.cEstadoOrdenDesc = cEstadoOrdenDesc;
    }

    public String getCEstadoOrdenDesc() {
        return cEstadoOrdenDesc;
    }

    public void setFecOrdnMin(Date fecOrdnMin) {
        this.fecOrdnMin = fecOrdnMin;
    }

    public Date getFecOrdnMin() {
        return fecOrdnMin;
    }

    public void setFecOrdnMax(Date fecOrdnMax) {
        this.fecOrdnMax = fecOrdnMax;
    }

    public Date getFecOrdnMax() {
        return fecOrdnMax;
    }

    public void setBeanError(BeanError beanError) {
        this.beanError = beanError;
    }

    public BeanError getBeanError() {
        return beanError;
    }

    public void setFlgVisto(String flgVisto) {
        this.flgVisto = flgVisto;
    }

    public String getFlgVisto() {
        return flgVisto;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setItemsLista(List<BeanTrItemXOrds> itemsLista) {
        this.itemsLista = itemsLista;
    }

    public List<BeanTrItemXOrds> getItemsLista() {
        return itemsLista;
    }

    public void setNidRemitente(Integer nidRemitente) {
        this.nidRemitente = nidRemitente;
    }

    public Integer getNidRemitente() {
        return nidRemitente;
    }


    public void setNidDirecCli(String nidDirecCli) {
        this.nidDirecCli = nidDirecCli;
    }

    public String getNidDirecCli() {
        return nidDirecCli;
    }

    public void setNidDirecProv(String nidDirecProv) {
        this.nidDirecProv = nidDirecProv;
    }

    public String getNidDirecProv() {
        return nidDirecProv;
    }
}
