package silat.servicios_negocio.entidades.admin;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries( { @NamedQuery(name = "ADUbigeo.findAll", query = "select o from ADUbigeo o") })
@Table(name = "ADMUBIG")
public class ADUbigeo implements Serializable {
    @Id
    @Column(name = "CID_UBIGEO", nullable = false, length = 6)
    private String cidUbigeo;
    @Column(name = "C_DESC_UBIGEO", nullable = false)
    private String cDescUbigeo;
    @OneToMany(mappedBy = "ubigeo")
    private List<ADDireccion> direccionesList;

    public ADUbigeo() {
    }

    public ADUbigeo(String cDescUbigeo, String cidUbigeo) {
        this.cDescUbigeo = cDescUbigeo;
        this.cidUbigeo = cidUbigeo;
    }

    public String getCDescUbigeo() {
        return cDescUbigeo;
    }

    public void setCDescUbigeo(String cDescUbigeo) {
        this.cDescUbigeo = cDescUbigeo;
    }

    public String getCidUbigeo() {
        return cidUbigeo;
    }

    public void setCidUbigeo(String cidUbigeo) {
        this.cidUbigeo = cidUbigeo;
    }

    public void setDireccionesList(List<ADDireccion> direccionesList) {
        this.direccionesList = direccionesList;
    }

    public List<ADDireccion> getDireccionesList() {
        return direccionesList;
    }
}
