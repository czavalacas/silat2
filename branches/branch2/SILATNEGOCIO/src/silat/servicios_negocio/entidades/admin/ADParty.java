package silat.servicios_negocio.entidades.admin;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@NamedQueries( { @NamedQuery(name = "ADParty.findAll", query = "select o from ADParty o") })
@Table(name = "ADMPART")
public class ADParty implements Serializable {
    @Column(name = "C_DETALLE")
    private String cDetalle;
    @Column(name = "C_EMAIL", length = 100)
    private String cEmail;
    @Column(name = "C_TELF", length = 40)
    private String cTelf;
    @Column(name = "C_TIPO_PARTY", length = 1)
    private String cTipoParty;
    @Id
    @SequenceGenerator(name = "GEN_SQ_ADMPART_01",sequenceName = "SQ_ADMPART_01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_SQ_ADMPART_01")
    @Column(name = "NID_PARTY", nullable = false)
    private BigDecimal nidParty;
    @OneToOne(mappedBy = "adParty")
    private List<ADEmpresa> empresasList;
    @OneToOne(mappedBy = "adParty")
    private List<ADPersona> personasList;
    @OneToMany(mappedBy = "party")
    private List<ADDireccion> direccionesList;

    public ADParty() {
    }

    public ADParty(String cDetalle, String cEmail, String cTelf, String cTipoParty,
                   BigDecimal nidParty) {
        this.cDetalle = cDetalle;
        this.cEmail = cEmail;
        this.cTelf = cTelf;
        this.cTipoParty = cTipoParty;
        this.nidParty = nidParty;
    }

    public String getCDetalle() {
        return cDetalle;
    }

    public void setCDetalle(String cDetalle) {
        this.cDetalle = cDetalle;
    }

    public String getCEmail() {
        return cEmail;
    }

    public void setCEmail(String cEmail) {
        this.cEmail = cEmail;
    }

    public String getCTelf() {
        return cTelf;
    }

    public void setCTelf(String cTelf) {
        this.cTelf = cTelf;
    }

    public String getCTipoParty() {
        return cTipoParty;
    }

    public void setCTipoParty(String cTipoParty) {
        this.cTipoParty = cTipoParty;
    }


    public BigDecimal getNidParty() {
        return nidParty;
    }

    public void setNidParty(BigDecimal nidParty) {
        this.nidParty = nidParty;
    }

    public void setEmpresasList(List<ADEmpresa> empresasList) {
        this.empresasList = empresasList;
    }

    public List<ADEmpresa> getEmpresasList() {
        return empresasList;
    }

    public void setPersonasList(List<ADPersona> personasList) {
        this.personasList = personasList;
    }

    public List<ADPersona> getPersonasList() {
        return personasList;
    }

    public void setDireccionesList(List<ADDireccion> direccionesList) {
        this.direccionesList = direccionesList;
    }

    public List<ADDireccion> getDireccionesList() {
        return direccionesList;
    }
}
