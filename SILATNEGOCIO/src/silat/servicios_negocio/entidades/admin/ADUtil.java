package silat.servicios_negocio.entidades.admin;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries( { @NamedQuery(name = "Admutil.findAll", query = "select o from ADUtil o") })
@Table(name="ADMUTIL")
public class ADUtil implements Serializable {
    @Column(name = "DESC_OBJ", nullable = false, length = 100)
    private String descObj;
    @Id
    @Column(name = "NID_OBJ", nullable = false)
    private BigDecimal nidObj;
    @Column(name="TIPOBJ",nullable = false)
    private BigDecimal tipobj;
    @OneToMany(mappedBy = "utilServicioBasico")
    private List<ADGasto> gastosListaServicioBasico;
    @OneToMany(mappedBy = "utilTipoCombustible")
    private List<ADGasto> gastosListaTipoCombustible;
    @OneToMany(mappedBy = "utilTipoMantenimiento")
    private List<ADGasto> gastosListaTipoMantenimiento;

    public ADUtil() {
    }

    public ADUtil(String descObj, BigDecimal nidObj, BigDecimal tipobj) {
        this.descObj = descObj;
        this.nidObj = nidObj;
        this.tipobj = tipobj;
    }

    public String getDescObj() {
        return descObj;
    }

    public void setDescObj(String descObj) {
        this.descObj = descObj;
    }

    public BigDecimal getNidObj() {
        return nidObj;
    }

    public void setNidObj(BigDecimal nidObj) {
        this.nidObj = nidObj;
    }

    public BigDecimal getTipobj() {
        return tipobj;
    }

    public void setTipobj(BigDecimal tipobj) {
        this.tipobj = tipobj;
    }


    public void setGastosListaServicioBasico(List<ADGasto> gastosListaServicioBasico) {
        this.gastosListaServicioBasico = gastosListaServicioBasico;
    }

    public List<ADGasto> getGastosListaServicioBasico() {
        return gastosListaServicioBasico;
    }

    public void setGastosListaTipoCombustible(List<ADGasto> gastosListaTipoCombustible) {
        this.gastosListaTipoCombustible = gastosListaTipoCombustible;
    }

    public List<ADGasto> getGastosListaTipoCombustible() {
        return gastosListaTipoCombustible;
    }

    public void setGastosListaTipoMantenimiento(List<ADGasto> gastosListaTipoMantenimiento) {
        this.gastosListaTipoMantenimiento = gastosListaTipoMantenimiento;
    }

    public List<ADGasto> getGastosListaTipoMantenimiento() {
        return gastosListaTipoMantenimiento;
    }
}
