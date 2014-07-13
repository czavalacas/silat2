package silat.servicios_negocio.Beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class BeanNota implements Serializable {
    @SuppressWarnings("compatibility:6589598906330548917")
    private static final long serialVersionUID = 1L;
    
    private String cTipoNota;
    private String descTipoNota;
    private BigDecimal dMonto;
    private Integer nEstadoNota;
    private BigDecimal nidNota;
    private BeanFactura trFactura;
    private BeanError beanError = new BeanError();
    private String simbNota;

    public void setCTipoNota(String cTipoNota) {
        this.cTipoNota = cTipoNota;
    }

    public String getCTipoNota() {
        return cTipoNota;
    }

    public void setDMonto(BigDecimal dMonto) {
        this.dMonto = dMonto;
    }

    public BigDecimal getDMonto() {
        return dMonto;
    }

    public void setNEstadoNota(Integer nEstadoNota) {
        this.nEstadoNota = nEstadoNota;
    }

    public Integer getNEstadoNota() {
        return nEstadoNota;
    }

    public void setNidNota(BigDecimal nidNota) {
        this.nidNota = nidNota;
    }

    public BigDecimal getNidNota() {
        return nidNota;
    }

    public void setTrFactura(BeanFactura trFactura) {
        this.trFactura = trFactura;
    }

    public BeanFactura getTrFactura() {
        return trFactura;
    }

    public void setBeanError(BeanError beanError) {
        this.beanError = beanError;
    }

    public BeanError getBeanError() {
        return beanError;
    }

    public void setDescTipoNota(String descTipoNota) {
        this.descTipoNota = descTipoNota;
    }

    public String getDescTipoNota() {
        return descTipoNota;
    }

    public void setSimbNota(String simbNota) {
        this.simbNota = simbNota;
    }

    public String getSimbNota() {
        return simbNota;
    }
}
