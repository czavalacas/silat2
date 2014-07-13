package silat.servicios_negocio.Beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class BeanUsuario implements Serializable {
    @SuppressWarnings("compatibility:-1884150570849589604")
    private static final long serialVersionUID = 1L;
    
    private String cUsuario;
    private BigDecimal nEstadoUsuario;
    private BigDecimal nTipoUsuario;
    private BigDecimal nidUsuario;
    //private List<ADClave> clavesLista;
    private BeanPersona adPersona;
    private BeanError beanError = new BeanError();
    private String descTipoUsuario;
    private String descEstado;

    public void setCUsuario(String cUsuario) {
        this.cUsuario = cUsuario;
    }
 
    public String getCUsuario() {
        return cUsuario;
    }

    public void setNEstadoUsuario(BigDecimal nEstadoUsuario) {
        this.nEstadoUsuario = nEstadoUsuario;
    }

    public BigDecimal getNEstadoUsuario() {
        return nEstadoUsuario;
    }

    public void setNTipoUsuario(BigDecimal nTipoUsuario) {
        this.nTipoUsuario = nTipoUsuario;
    }

    public BigDecimal getNTipoUsuario() {
        return nTipoUsuario;
    }

    public void setNidUsuario(BigDecimal nidUsuario) {
        this.nidUsuario = nidUsuario;
    }

    public BigDecimal getNidUsuario() {
        return nidUsuario;
    }

    public void setAdPersona(BeanPersona adPersona) {
        this.adPersona = adPersona;
    }

    public BeanPersona getAdPersona() {
        return adPersona;
    }

    public void setBeanError(BeanError beanError) {
        this.beanError = beanError;
    }

    public BeanError getBeanError() {
        return beanError;
    }

    public void setDescTipoUsuario(String descTipoUsuario) {
        this.descTipoUsuario = descTipoUsuario;
    }

    public String getDescTipoUsuario() {
        return descTipoUsuario;
    }

    public void setDescEstado(String descEstado) {
        this.descEstado = descEstado;
    }

    public String getDescEstado() {
        return descEstado;
    }
}
