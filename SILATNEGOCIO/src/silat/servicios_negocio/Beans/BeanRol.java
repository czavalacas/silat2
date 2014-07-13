package silat.servicios_negocio.Beans;

import java.io.Serializable;

import java.math.BigDecimal;

public class BeanRol implements Serializable{

    @SuppressWarnings("compatibility:-5430975764912426500")
    private static final long serialVersionUID = 1L;
    private String cDescRole;
    private BigDecimal nEstado;
    private BigDecimal nidRole;
    BeanError beanError = new BeanError();

    public void setCDescRole(String cDescRole) {
        this.cDescRole = cDescRole;
    }

    public String getCDescRole() {
        return cDescRole;
    }

    public void setNEstado(BigDecimal nEstado) {
        this.nEstado = nEstado;
    }

    public BigDecimal getNEstado() {
        return nEstado;
    }

    public void setNidRole(BigDecimal nidRole) {
        this.nidRole = nidRole;
    }

    public BigDecimal getNidRole() {
        return nidRole;
    }

    public void setBeanError(BeanError beanError) {
        this.beanError = beanError;
    }

    public BeanError getBeanError() {
        return beanError;
    }
}
