package silat.servicios_negocio.Beans;

import java.io.Serializable;

/**
 * @author dfloresgonz
 * @since 12.08.2013
 */
public class BeanFlota implements Serializable {
    @SuppressWarnings("compatibility:-4972856774401678476")
    private static final long serialVersionUID = 1L;
    
    private String cConfveh;
    private String cMarvehi;
    private Integer nidFlota;
    private Integer nidEmpresa;
    private String cDescFlota;
    private String cPlaca;
    private String cSoat;
    private Integer nEstadoFlota;
    private String certificadoInscripcionEmpresa;
    private BeanError beanError;  

    public void setCConfveh(String cConfveh) {
        this.cConfveh = cConfveh;
    }

    public String getCConfveh() {
        return cConfveh;
    }

    public void setCMarvehi(String cMarvehi) {
        this.cMarvehi = cMarvehi;
    }

    public String getCMarvehi() {
        return cMarvehi;
    }

    public void setNidFlota(Integer nidFlota) {
        this.nidFlota = nidFlota;
    }

    public Integer getNidFlota() {
        return nidFlota;
    }

    public void setNidEmpresa(Integer nidEmpresa) {
        this.nidEmpresa = nidEmpresa;
    }

    public Integer getNidEmpresa() {
        return nidEmpresa;
    }

    public void setCDescFlota(String cDescFlota) {
        this.cDescFlota = cDescFlota;
    }

    public String getCDescFlota() {
        return cDescFlota;
    }

    public void setCPlaca(String cPlaca) { 
        this.cPlaca = cPlaca;
    }

    public String getCPlaca() {
        return cPlaca;
    }

    public void setCSoat(String cSoat) {
        this.cSoat = cSoat;
    }

    public String getCSoat() {
        return cSoat;
    }

    public void setNEstadoFlota(Integer nEstadoFlota) {
        this.nEstadoFlota = nEstadoFlota;
    }

    public Integer getNEstadoFlota() {
        return nEstadoFlota;
    }

    public void setCertificadoInscripcionEmpresa(String certificadoInscripcionEmpresa) {
        this.certificadoInscripcionEmpresa = certificadoInscripcionEmpresa;
    }

    public String getCertificadoInscripcionEmpresa() {
        return certificadoInscripcionEmpresa;
    }

    public void setBeanError(BeanError beanError) {
        this.beanError = beanError;
    }

    public BeanError getBeanError() {
        return beanError;
    }
}
