package silat.servicios_negocio.Beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import silat.servicios_negocio.entidades.admin.ADEmpresa;
import silat.servicios_negocio.entidades.admin.ADTipoRelacion;

public class BeanADRelacionEmpresa implements Serializable {
    @SuppressWarnings("compatibility:3930423142198014509")
    private static final long serialVersionUID = 1L;
    
    private String estadoRegistro;
    private Integer correlativoRelacionEmpresa;
    private BeanEmpresa adEmpresa1;
    private BeanEmpresa adEmpresa2;
    private BeanADTipoRelacion adTipoRelacion;
    private Integer nidTipoRelacion;
    private Integer nidEmpresa1;
    private Integer nidEmpresa2;
    private String descRela;
    private String noLubal;

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setCorrelativoRelacionEmpresa(Integer correlativoRelacionEmpresa) {
        this.correlativoRelacionEmpresa = correlativoRelacionEmpresa;
    }

    public Integer getCorrelativoRelacionEmpresa() {
        return correlativoRelacionEmpresa;
    }

    public void setAdEmpresa1(BeanEmpresa adEmpresa1) {
        this.adEmpresa1 = adEmpresa1;
    }

    public BeanEmpresa getAdEmpresa1() {
        return adEmpresa1;
    }

    public void setAdEmpresa2(BeanEmpresa adEmpresa2) {
        this.adEmpresa2 = adEmpresa2;
    }

    public BeanEmpresa getAdEmpresa2() {
        return adEmpresa2;
    }

    public void setAdTipoRelacion(BeanADTipoRelacion adTipoRelacion) {
        this.adTipoRelacion = adTipoRelacion;
    }

    public BeanADTipoRelacion getAdTipoRelacion() {
        return adTipoRelacion;
    }

    public void setNidTipoRelacion(Integer nidTipoRelacion) {
        this.nidTipoRelacion = nidTipoRelacion;
    }

    public Integer getNidTipoRelacion() {
        return nidTipoRelacion;
    }

    public void setNidEmpresa1(Integer nidEmpresa1) {
        this.nidEmpresa1 = nidEmpresa1;
    }

    public Integer getNidEmpresa1() {
        return nidEmpresa1;
    }

    public void setNidEmpresa2(Integer nidEmpresa2) {
        this.nidEmpresa2 = nidEmpresa2;
    }

    public Integer getNidEmpresa2() {
        return nidEmpresa2;
    }

    public void setDescRela(String descRela) {
        this.descRela = descRela;
    }

    public String getDescRela() {
        return descRela;
    }

    public void setNoLubal(String noLubal) {
        this.noLubal = noLubal;
    }

    public String getNoLubal() {
        return noLubal;
    }
}
