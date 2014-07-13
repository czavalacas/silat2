package silat.servicios_negocio.Beans;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.List;

public class BeanPermisos implements Serializable{

    @SuppressWarnings("compatibility:-1693567435817507401")
    private static final long serialVersionUID = 1L;
    private String cDescPermiso;
    private String cIcono;
    private String cIndFld;
    private String cNombreObj;
    private String cUrl;
    private BigDecimal nEstadoPermiso;
    private BigDecimal nMenuPadre;
    private BigDecimal nNivel;
    private BigDecimal nidPermiso;
    private List<BeanPermisos> listaHijos;
    private String indMostrar;
    private List<BigDecimal> lstPermisos;
    private String habilidad;
    private String hasPermiso;
    private BeanError beanError;

    public void setCDescPermiso(String cDescPermiso) {
        this.cDescPermiso = cDescPermiso;
    }

    public String getCDescPermiso() {
        return cDescPermiso;
    }

    public void setCIcono(String cIcono) {
        this.cIcono = cIcono;
    }

    public String getCIcono() {
        return cIcono;
    }

    public void setCIndFld(String cIndFld) {
        this.cIndFld = cIndFld;
    }

    public String getCIndFld() {
        return cIndFld;
    }

    public void setCNombreObj(String cNombreObj) {
        this.cNombreObj = cNombreObj;
    }

    public String getCNombreObj() {
        return cNombreObj;
    }

    public void setCUrl(String cUrl) {
        this.cUrl = cUrl;
    }

    public String getCUrl() {
        return cUrl;
    }

    public void setNEstadoPermiso(BigDecimal nEstadoPermiso) {
        this.nEstadoPermiso = nEstadoPermiso;
    }

    public BigDecimal getNEstadoPermiso() {
        return nEstadoPermiso;
    }

    public void setNMenuPadre(BigDecimal nMenuPadre) {
        this.nMenuPadre = nMenuPadre;
    }

    public BigDecimal getNMenuPadre() {
        return nMenuPadre;
    }

    public void setNNivel(BigDecimal nNivel) {
        this.nNivel = nNivel;
    }

    public BigDecimal getNNivel() {
        return nNivel;
    }

    public void setNidPermiso(BigDecimal nidPermiso) {
        this.nidPermiso = nidPermiso;
    }

    public BigDecimal getNidPermiso() {
        return nidPermiso;
    }

    public void setListaHijos(List<BeanPermisos> listaHijos) { 
        this.listaHijos = listaHijos;
    }

    public List<BeanPermisos> getListaHijos() {
        return listaHijos;
    }

    public void setIndMostrar(String indMostrar) {
        this.indMostrar = indMostrar;
    }

    public String getIndMostrar() {
        return indMostrar;
    }

    public void setLstPermisos(List<BigDecimal> lstPermisos) {
        this.lstPermisos = lstPermisos;
    }

    public List<BigDecimal> getLstPermisos() {
        return lstPermisos;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHasPermiso(String hasPermiso) {
        this.hasPermiso = hasPermiso;
    }

    public String getHasPermiso() {
        return hasPermiso;
    }

    public void setBeanError(BeanError beanError) {
        this.beanError = beanError;
    }

    public BeanError getBeanError() {
        return beanError;
    }
}
