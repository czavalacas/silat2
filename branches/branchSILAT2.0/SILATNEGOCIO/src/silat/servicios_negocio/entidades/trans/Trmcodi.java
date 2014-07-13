package silat.servicios_negocio.entidades.trans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries( { @NamedQuery(name = "Trmcodi.findAll", query = "select o from Trmcodi o") })
@IdClass(TrmcodiPK.class)
public class Trmcodi implements Serializable {
    @Id
    @Column(nullable = false, length = 3)
    private String cidunin;
    @Column(nullable = false, length = 6)
    private String codigo;
    @Id
    @Column(nullable = false, length = 20)
    private String tipdoc;

    public Trmcodi() {
    }

    public Trmcodi(String cidunin, String codigo, String tipdoc) {
        this.cidunin = cidunin;
        this.codigo = codigo;
        this.tipdoc = tipdoc;
    }

    public String getCidunin() {
        return cidunin;
    }

    public void setCidunin(String cidunin) {
        this.cidunin = cidunin;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipdoc() {
        return tipdoc;
    }

    public void setTipdoc(String tipdoc) {
        this.tipdoc = tipdoc;
    }
}
