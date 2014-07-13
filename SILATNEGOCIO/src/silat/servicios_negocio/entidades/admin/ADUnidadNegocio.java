package silat.servicios_negocio.entidades.admin;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries( { @NamedQuery(name = "ADUnidadNegocio.findAll", query = "select o from ADUnidadNegocio o") })
@Table(name = "ADMUNIN")
public class ADUnidadNegocio implements Serializable {
    @Id
    @Column(name = "CID_CODI", nullable = false, length = 3)
    private String cidUnidadNegocio;
    @Column(name = "CUNINEG", nullable = false, length = 200)
    private String descripcionUnidadNegocio;

    public ADUnidadNegocio() {
    }

    public ADUnidadNegocio(String cidUnidadNegocio, String descripcionUnidadNegocio) {
        this.cidUnidadNegocio = cidUnidadNegocio;
        this.descripcionUnidadNegocio = descripcionUnidadNegocio;
    }

    public String getCidUnidadNegocio() {
        return cidUnidadNegocio;
    }

    public void setCidUnidadNegocio(String cidUnidadNegocio) {
        this.cidUnidadNegocio = cidUnidadNegocio;
    }

    public String getDescripcionUnidadNegocio() {
        return descripcionUnidadNegocio;
    }

    public void setDescripcionUnidadNegocio(String descripcionUnidadNegocio) {
        this.descripcionUnidadNegocio = descripcionUnidadNegocio;
    }
}
