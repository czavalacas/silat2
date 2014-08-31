package silat.servicios_negocio.entidades.admin;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@NamedQueries( { @NamedQuery(name = "ADFlota.findAll", query = "select o from ADFlota o") })
@Table(name = "ADMFLOT")
public class ADFlota implements Serializable {
    @Column(name = "C_CONFVEH", nullable = false)
    private String cConfveh;
    @Column(name = "C_MARVEHI", nullable = false)
    private String cMarvehi;
    @Id  
    @Column(name = "NID_FLOTA", nullable = false)
    @TableGenerator( name = "adflota", table = "codigo", pkColumnName = "APP_SEQ_NAME", pkColumnValue = "adflota.nid_flota", valueColumnName = "APP_SEQ_VALUE", initialValue = 50, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "adflota" )
    private Integer nidFlota;
    @ManyToOne
    @JoinColumn(name = "NID_EMPR")
    private ADEmpresa empresa;
    @Column(name = "C_DESC_FLOTA", nullable = false, length = 30)
    private String cDescFlota;
    @Column(name = "C_PLACA", nullable = false, length = 10)
    private String cPlaca;
    @Column(name = "C_SOAT", length = 15)
    private String cSoat;
    @Column(name = "N_ESTADO_FLOTA", nullable = false)
    private Integer nEstadoFlota;
    @OneToMany(mappedBy = "adFlota")
    private List<ADGasto> gastosList;

    public ADFlota() {
    }

    public ADFlota(String cDescFlota, String cPlaca, String cSoat, Integer nEstadoFlota) {
        this.cDescFlota = cDescFlota;
        this.cPlaca = cPlaca;
        this.cSoat = cSoat;
        this.nEstadoFlota = nEstadoFlota;
    }

    public String getCDescFlota() {
        return cDescFlota;
    }

    public void setCDescFlota(String cDescFlota) {
        this.cDescFlota = cDescFlota;
    }

    public String getCPlaca() {
        return cPlaca;
    }

    public void setCPlaca(String cPlaca) {
        this.cPlaca = cPlaca;
    } 

    public String getCSoat() {
        return cSoat;
    }

    public void setCSoat(String cSoat) {
        this.cSoat = cSoat;
    }

    public Integer getNEstadoFlota() {
        return nEstadoFlota;
    }

    public void setNEstadoFlota(Integer nEstadoFlota) {
        this.nEstadoFlota = nEstadoFlota;
    }

    public List<ADGasto> getGastosList() {
        return gastosList;
    }

    public void setGastosList(List<ADGasto> gastosList) {
        this.gastosList = gastosList;
    }

    public ADGasto addADGasto(ADGasto ADGasto) {
        getGastosList().add(ADGasto);
        ADGasto.setAdFlota(this);
        return ADGasto;
    }

    public ADGasto removeADGasto(ADGasto ADGasto) {
        getGastosList().remove(ADGasto);
        ADGasto.setAdFlota(null);
        return ADGasto;
    }

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

    public void setEmpresa(ADEmpresa empresa) {
        this.empresa = empresa;
    }

    public ADEmpresa getEmpresa() {
        return empresa;
    }
}
