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
@NamedQueries( { @NamedQuery(name = "Admmopa.findAll", query = "select o from ADModalidadPago o") })
@Table(name="ADMMOPA")
public class ADModalidadPago implements Serializable {
    @Column(name = "C_ESTREG", nullable = false, length = 1)
    private String estadoRegistro;
    @Column(name="DESCMOPA", nullable = false, length = 60)
    private String descripcionModalidadPago;
    @Id
    @Column(name = "NID_MODPAG", nullable = false)
    private Integer nidModalidadPago;
    @OneToMany(mappedBy = "modalidadPago")
    private List<ADGasto> gastosLista;

    public ADModalidadPago() {
    }

    public ADGasto addAddgast(ADGasto addgast) {
        getGastosLista().add(addgast);
        addgast.setModalidadPago(this);
        return addgast;
    }

    public ADGasto removeAddgast(ADGasto addgast) {
        getGastosLista().remove(addgast);
        addgast.setModalidadPago(null);
        return addgast;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setDescripcionModalidadPago(String descripcionModalidadPago) {
        this.descripcionModalidadPago = descripcionModalidadPago;
    }

    public String getDescripcionModalidadPago() {
        return descripcionModalidadPago;
    }

    public void setNidModalidadPago(Integer nidModalidadPago) {
        this.nidModalidadPago = nidModalidadPago;
    }

    public Integer getNidModalidadPago() {
        return nidModalidadPago;
    }

    public void setGastosLista(List<ADGasto> gastosLista) {
        this.gastosLista = gastosLista;
    }

    public List<ADGasto> getGastosLista() {
        return gastosLista;
    }
}
