package silat.servicios_negocio.entidades.admin;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries( { @NamedQuery(name = "ADPersona.findAll", query = "select o from ADPersona o") })
@Table(name = "ADDPERS")
public class ADPersona implements Serializable {
    @Column(name = "C_APELLIDOS", nullable = false, length = 60)
    private String cApellidos;
    @Column(name = "C_CARGO", length = 50)
    private String cCargo;
    @Column(name = "C_NOMBRES", nullable = false, length = 50)
    private String cNombres;
    @Temporal(TemporalType.DATE)
    @Column(name = "F_FEC_NAC", nullable = false)
    private Date fecNac;
    @Id
    @Column(name = "NID_PARTY", nullable = false, insertable = false, updatable = false)
    private BigDecimal nidParty;
    @OneToMany(mappedBy = "adPersona")
    private List<ADUsuario> usuariosList;
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "NID_PARTY")
    private ADParty adParty;

    public ADPersona() {
    }

    public ADPersona(String cApellidos, String cCargo, String cNombres, Date fecNac, ADParty adParty) {
        this.cApellidos = cApellidos;
        this.cCargo = cCargo;
        this.cNombres = cNombres;
        this.fecNac = fecNac;
        this.adParty = adParty;
    }

    public String getCApellidos() {
        return cApellidos;
    }

    public void setCApellidos(String cApellidos) {
        this.cApellidos = cApellidos;
    }

    public String getCCargo() {
        return cCargo;
    }

    public void setCCargo(String cCargo) {
        this.cCargo = cCargo;
    }

    public String getCNombres() {
        return cNombres;
    }

    public void setCNombres(String cNombres) {
        this.cNombres = cNombres;
    }

    public Date getFecNac() {
        return fecNac;
    }

    public void setFecNac(Date fecNac) {
        this.fecNac = fecNac;
    }

    public BigDecimal getNidParty() {
        return nidParty;
    }

    public void setNidParty(BigDecimal nidParty) {
        this.nidParty = nidParty;
    }

    public List<ADUsuario> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<ADUsuario> usuariosList) {
        this.usuariosList = usuariosList;
    }

    public ADUsuario addADUsuario(ADUsuario ADUsuario) {
        getUsuariosList().add(ADUsuario);
        ADUsuario.setAdPersona(this);
        return ADUsuario;
    }

    public ADUsuario removeADUsuario(ADUsuario ADUsuario) {
        getUsuariosList().remove(ADUsuario);
        ADUsuario.setAdPersona(null);
        return ADUsuario;
    }

    public ADParty getAdParty() {
        return adParty;
    }

    public void setAdParty(ADParty adParty) {
        this.adParty = adParty;
        if (adParty != null) {
            this.nidParty = adParty.getNidParty();
        }
    }
}
