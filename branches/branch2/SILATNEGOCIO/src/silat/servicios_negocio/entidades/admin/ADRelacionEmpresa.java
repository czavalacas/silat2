package silat.servicios_negocio.entidades.admin;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@NamedQueries( { @NamedQuery(name = "Addrela.findAll", query = "select o from ADRelacionEmpresa o") })
@Table(name="ADDRELA")
public class ADRelacionEmpresa implements Serializable {
    @Column(name = "C_ESTREG", nullable = false, length = 1)
    private String estadoRegistro;
    @Id
    @SequenceGenerator(name = "GEN_SQ_ADDRELA_01",sequenceName = "SQ_ADDRELA_01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_SQ_ADDRELA_01")
    @Column(name = "N_CORREL", nullable = false)
    private Integer correlativoRelacionEmpresa;
    @ManyToOne
    @JoinColumn(name = "NID_PARTY_1")
    private ADEmpresa adEmpresa1;
    @ManyToOne
    @JoinColumn(name = "NID_PARTY_2")
    private ADEmpresa adEmpresa2;//LUBAL
    @ManyToOne
    @JoinColumn(name = "N_TIPREL")
    private ADTipoRelacion adTipoRelacion;

    public ADRelacionEmpresa() {
    }

    public ADRelacionEmpresa(String estadoRegistro, 
                             Integer correlativoRelacionEmpresa, 
                             ADTipoRelacion adTipoRelacion, 
                             ADEmpresa adEmpresa1, 
                             ADEmpresa adEmpresa2) {
        this.estadoRegistro = estadoRegistro;
        this.correlativoRelacionEmpresa = correlativoRelacionEmpresa;
        this.adTipoRelacion = adTipoRelacion;
        this.adEmpresa1 = adEmpresa1;
        this.adEmpresa2 = adEmpresa2;
    }


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

    public void setAdTipoRelacion(ADTipoRelacion adTipoRelacion) {
        this.adTipoRelacion = adTipoRelacion;
    }

    public ADTipoRelacion getAdTipoRelacion() {
        return adTipoRelacion;
    }

    public void setAdEmpresa1(ADEmpresa adEmpresa1) {
        this.adEmpresa1 = adEmpresa1;
    }

    public ADEmpresa getAdEmpresa1() {
        return adEmpresa1;
    }

    public void setAdEmpresa2(ADEmpresa adEmpresa2) {
        this.adEmpresa2 = adEmpresa2;
    }

    public ADEmpresa getAdEmpresa2() {
        return adEmpresa2;
    }
}
