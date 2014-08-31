package silat.servicios_negocio.entidades.admin;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@NamedQueries( { @NamedQuery(name = "Admtiga.findAll", query = "select o from ADTipoGasto o") })
@Table(name="ADMTIGA")
public class ADTipoGasto implements Serializable {
    @Column(name = "C_ESTREG", nullable = false, length = 1)
    private String estadoRegistro;
    @Column(name = "DES_TIGA", nullable = false, length = 80)
    private String descripcionTipoGasto;
    @Id    
    @Column(name = "NID_TIGA", nullable = false)
    @TableGenerator( name = "adtiga", table = "codigo", pkColumnName = "APP_SEQ_NAME", pkColumnValue = "adtiga.nid_tiga", valueColumnName = "APP_SEQ_VALUE", initialValue = 50, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "adtiga" )
    private Integer nidTiga;
    @OneToMany(mappedBy = "tipoGasto")
    private List<ADGasto> gastosLista;
    @Column(name = "ISRUTA", length = 1)
    private String isRuta;

    public ADTipoGasto() {
    }

    public ADGasto addAddgast(ADGasto addgast) {
        getGastosLista().add(addgast);
        addgast.setTipoGasto(this);
        return addgast;
    }

    public ADGasto removeAddgast(ADGasto addgast) {
        getGastosLista().remove(addgast);
        addgast.setTipoGasto(null);
        return addgast;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setDescripcionTipoGasto(String descripcionTipoGasto) {
        this.descripcionTipoGasto = descripcionTipoGasto;
    }

    public String getDescripcionTipoGasto() {
        return descripcionTipoGasto;
    }

    public void setNidTiga(Integer nidTiga) {
        this.nidTiga = nidTiga;
    }

    public Integer getNidTiga() {
        return nidTiga;
    }

    public void setGastosLista(List<ADGasto> gastosLista) {
        this.gastosLista = gastosLista;
    }

    public List<ADGasto> getGastosLista() {
        return gastosLista;
    }

    public void setIsRuta(String isRuta) {
        this.isRuta = isRuta;
    }

    public String getIsRuta() {
        return isRuta;
    }
}
