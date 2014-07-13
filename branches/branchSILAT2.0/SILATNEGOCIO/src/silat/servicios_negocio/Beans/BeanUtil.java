package silat.servicios_negocio.Beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class BeanUtil implements Serializable {

    @SuppressWarnings("compatibility:2287241888543586483")
    private static final long serialVersionUID = 1L;
    
    private String descObj;
    private BigDecimal nidObj;
    private BigDecimal tipobj;
    private List<BeanGasto> gastosListasServicioBasico;
    private List<BeanGasto> gastosListasTipoCombustible;
    private List<BeanGasto> gastosListasTipoMantenimiento;

    public void setDescObj(String descObj) {
        this.descObj = descObj;
    }

    public String getDescObj() {
        return descObj;
    }

    public void setNidObj(BigDecimal nidObj) {
        this.nidObj = nidObj;
    }

    public BigDecimal getNidObj() {
        return nidObj;
    }

    public void setTipobj(BigDecimal tipobj) {
        this.tipobj = tipobj;
    }

    public BigDecimal getTipobj() {
        return tipobj;
    }

    public void setGastosListasServicioBasico(List<BeanGasto> gastosListasServicioBasico) {
        this.gastosListasServicioBasico = gastosListasServicioBasico;
    }

    public List<BeanGasto> getGastosListasServicioBasico() {
        return gastosListasServicioBasico;
    }

    public void setGastosListasTipoCombustible(List<BeanGasto> gastosListasTipoCombustible) {
        this.gastosListasTipoCombustible = gastosListasTipoCombustible;
    }

    public List<BeanGasto> getGastosListasTipoCombustible() {
        return gastosListasTipoCombustible;
    }

    public void setGastosListasTipoMantenimiento(List<BeanGasto> gastosListasTipoMantenimiento) {
        this.gastosListasTipoMantenimiento = gastosListasTipoMantenimiento;
    }

    public List<BeanGasto> getGastosListasTipoMantenimiento() {
        return gastosListasTipoMantenimiento;
    }
}
