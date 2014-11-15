package siat.view.backing.transporte.guia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import silat.servicios_negocio.Beans.BeanTRGuia;

public class SessionScopedBeanConsultarGuia {
    private List<BeanTRGuia> lstGuiasTabla = new ArrayList<BeanTRGuia>();
    private BeanTRGuia guiaSelected = new BeanTRGuia();
    private String cidGuia;
    private Date fecEmisMin;
    private Date fecEmisMax;
    private Date fecDespaMin;
    private Date fecDespaMax;
    private String blnConf;
    private String empreClie;
    private String empreRemi;
    private String hasManif;
    private String prove;
    private String obs;
    private int exec = 0;
    private boolean guiaASC = true;
    private String vigenciaGuia;
    private boolean estConf = true;
    private String nidOS;
    private String detOS;
    private boolean renderBtnEditar = true;
    private boolean renderBtnAnular = true;
    private String hasFactura;
    private Long nidFactura;
    private String nEstadoFactura;
    private Integer nidManif;
    //dfloresgonz 01.05.2014 - Se agrega este campo auxiliar para poder realizar un filtro mas de busqueda
    //para realizar consultas a los items de la GUIA especificamente el texto en su campo C_CID_GUIA_REMITENTE
    private String filtroItemGuiaRemi;
    //dfloresgonz 13.06.2014 - Se agrega el filtro por codigo de factura
    private String codigoFacturaFiltro;
    //dfloresgonz 19.06.2014 - Se agrega variable saber si tiene el permiso de modificar fechas
    private boolean renderBtnFecha = false;
    
    private boolean renderBtnverImagen = true;
    private String img;

    public void setLstGuiasTabla(List<BeanTRGuia> lstGuiasTabla) {
        this.lstGuiasTabla = lstGuiasTabla;
    }

    public List<BeanTRGuia> getLstGuiasTabla() {
        return lstGuiasTabla;
    }

    public void setCidGuia(String cidGuia) {
        this.cidGuia = cidGuia;
    }

    public String getCidGuia() {
        return cidGuia;
    }

    public void setFecEmisMin(Date fecEmisMin) {
        this.fecEmisMin = fecEmisMin;
    }

    public Date getFecEmisMin() {
        return fecEmisMin;
    }

    public void setFecEmisMax(Date fecEmisMax) {
        this.fecEmisMax = fecEmisMax;
    }

    public Date getFecEmisMax() {
        return fecEmisMax;
    }

    public void setFecDespaMin(Date fecDespaMin) {
        this.fecDespaMin = fecDespaMin;
    }

    public Date getFecDespaMin() {
        return fecDespaMin;
    }

    public void setFecDespaMax(Date fecDespaMax) {
        this.fecDespaMax = fecDespaMax;
    }

    public Date getFecDespaMax() {
        return fecDespaMax;
    }

    public void setEmpreClie(String empreClie) {
        this.empreClie = empreClie;
    }

    public String getEmpreClie() {
        return empreClie;
    }

    public void setEmpreRemi(String empreRemi) {
        this.empreRemi = empreRemi;
    }

    public String getEmpreRemi() {
        return empreRemi;
    }

    public void setBlnConf(String blnConf) {
        this.blnConf = blnConf;
    }

    public String getBlnConf() {
        return blnConf;
    }

    public void setHasManif(String hasManif) {
        this.hasManif = hasManif;
    }

    public String getHasManif() {
        return hasManif;
    }

    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }

    public void setGuiaASC(boolean guiaASC) {
        this.guiaASC = guiaASC;
    }

    public boolean isGuiaASC() {
        return guiaASC;
    }

    public void setProve(String prove) {
        this.prove = prove;
    }

    public String getProve() {
        return prove;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getObs() {
        return obs;
    }

    public void setGuiaSelected(BeanTRGuia guiaSelected) {
        this.guiaSelected = guiaSelected;
    }

    public BeanTRGuia getGuiaSelected() {
        return guiaSelected;
    }

    public void setVigenciaGuia(String vigenciaGuia) {
        this.vigenciaGuia = vigenciaGuia;
    }

    public String getVigenciaGuia() {
        return vigenciaGuia;
    }

    public void setEstConf(boolean estConf) {
        this.estConf = estConf;
    }

    public boolean isEstConf() {
        return estConf;
    }

    public void setNidOS(String nidOS) {
        this.nidOS = nidOS;
    }

    public String getNidOS() {
        return nidOS;
    }

    public void setDetOS(String detOS) {
        this.detOS = detOS;
    }

    public String getDetOS() {
        return detOS;
    }

    public void setRenderBtnEditar(boolean renderBtnEditar) {
        this.renderBtnEditar = renderBtnEditar;
    }

    public boolean isRenderBtnEditar() {
        return renderBtnEditar;
    }

    public void setRenderBtnAnular(boolean renderBtnAnular) {
        this.renderBtnAnular = renderBtnAnular;
    }

    public boolean isRenderBtnAnular() {
        return renderBtnAnular;
    }

    public void setHasFactura(String hasFactura) {
        this.hasFactura = hasFactura;
    }

    public String getHasFactura() {
        return hasFactura;
    }

    public void setNidFactura(Long nidFactura) {
        this.nidFactura = nidFactura;
    }

    public Long getNidFactura() {
        return nidFactura;
    }

    public void setNEstadoFactura(String nEstadoFactura) {
        this.nEstadoFactura = nEstadoFactura;
    }

    public String getNEstadoFactura() {
        return nEstadoFactura;
    }

    public void setNidManif(Integer nidManif) {
        this.nidManif = nidManif;
    }

    public Integer getNidManif() {
        return nidManif;
    }

    public void setFiltroItemGuiaRemi(String filtroItemGuiaRemi) {
        this.filtroItemGuiaRemi = filtroItemGuiaRemi;
    }

    public String getFiltroItemGuiaRemi() {
        return filtroItemGuiaRemi;
    }

    public void setCodigoFacturaFiltro(String codigoFacturaFiltro) {
        this.codigoFacturaFiltro = codigoFacturaFiltro;
    }

    public String getCodigoFacturaFiltro() {
        return codigoFacturaFiltro;
    }

    public void setRenderBtnFecha(boolean renderBtnFecha) {
        this.renderBtnFecha = renderBtnFecha;
    }

    public boolean isRenderBtnFecha() {
        return renderBtnFecha;
    }

    public void setRenderBtnverImagen(boolean renderBtnverImagen) {
        this.renderBtnverImagen = renderBtnverImagen;
    }

    public boolean isRenderBtnverImagen() {
        return renderBtnverImagen;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }
}
