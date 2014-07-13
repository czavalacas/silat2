package silat.servicios_negocio.entidades.admin;

import java.io.Serializable;

import java.math.BigDecimal;

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
@NamedQueries( { @NamedQuery(name = "ADChofer.findAll", query = "select o from ADChofer o") })
@Table(name = "ADMCHOF")
public class ADChofer implements Serializable {
    @Column(name = "C_ESTREG", nullable = false, length = 1)
    private String estadoRegistro;
    @Column(name = "C_LICEN", nullable = false, length = 20)
    private String licencia;
    @Column(name = "C_NOMB", nullable = false, length = 300)
    private String nombres;
    @Id
    @SequenceGenerator(name = "GEN_SQ_ADMCHOF_01",sequenceName = "SQ_ADMCHOF_01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_SQ_ADMCHOF_01")
    @Column(name = "NID_CHOF", nullable = false)
    private Integer nidChofer;
    @ManyToOne
    @JoinColumn(name = "NID_EMP")
    private ADEmpresa empresa;

    public ADChofer() {
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNidChofer(Integer nidChofer) {
        this.nidChofer = nidChofer;
    }

    public Integer getNidChofer() {
        return nidChofer;
    }

    public void setEmpresa(ADEmpresa empresa) {
        this.empresa = empresa;
    }

    public ADEmpresa getEmpresa() {
        return empresa;
    }
}
