package silat.servicios_negocio.Beans;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;

import silat.servicios_negocio.entidades.admin.ADUsuario;

public class BeanClave implements Serializable {
    @SuppressWarnings("compatibility:-2977510981301426970")
    private static final long serialVersionUID = 1L;
    private String cClave;
    private Date fecRegistro;  
    private BigDecimal nEstadoClave;
    private BigDecimal nidClave;
    private ADUsuario adUsuario;
    private BeanError beanError;

    public void setCClave(String cClave) {
        this.cClave = cClave;
    }

    public String getCClave() {
        return cClave;
    }

    public void setFecRegistro(Date fecRegistro) {
        this.fecRegistro = fecRegistro;
    }

    public Date getFecRegistro() {
        return fecRegistro;
    }

    public void setNEstadoClave(BigDecimal nEstadoClave) {
        this.nEstadoClave = nEstadoClave;
    }

    public BigDecimal getNEstadoClave() {
        return nEstadoClave;
    }

    public void setNidClave(BigDecimal nidClave) {
        this.nidClave = nidClave;
    }

    public BigDecimal getNidClave() {
        return nidClave;
    }

    public void setAdUsuario(ADUsuario adUsuario) {
        this.adUsuario = adUsuario;
    }

    public ADUsuario getAdUsuario() {
        return adUsuario;
    }

    public void setBeanError(BeanError beanError) {
        this.beanError = beanError;
    }

    public BeanError getBeanError() {
        return beanError;
    }
}
