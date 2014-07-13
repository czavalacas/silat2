package silat.servicios_negocio.entidades.admin;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

@Entity
@NamedQueries( { @NamedQuery(name = "ADUsuario.findAll", query = "select o from ADUsuario o") })
@Table(name = "ADMUSUA")
public class ADUsuario implements Serializable {
    @Column(name = "C_USUARIO", nullable = false, length = 20)
    private String cUsuario;
    @Column(name = "N_ESTADO_USUARIO", nullable = false)
    private BigDecimal nEstadoUsuario;
    @Column(name = "N_TIPO_USUARIO", nullable = false)
    private BigDecimal nTipoUsuario;
    @Id
    @SequenceGenerator(name = "GEN_SQ_ADMUSUA_01",sequenceName = "SQ_ADMUSUA_01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_SQ_ADMUSUA_01")
    @Column(name = "NID_USUARIO", nullable = false)
    private BigDecimal nidUsuario;
    @OneToMany(mappedBy = "adUsuario",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<ADClave> clavesList;
    @OneToMany(mappedBy = "adUsuario")
    private List<ADUsuarioXPermiso> usuarioXPermisosList;
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "NID_PARTY")
    private ADPersona adPersona;

    public ADUsuario() {
    }

    public ADUsuario(String cUsuario, BigDecimal nEstadoUsuario, BigDecimal nTipoUsuario, ADPersona adPersona,
                     BigDecimal nidUsuario) {
        this.cUsuario = cUsuario;
        this.nEstadoUsuario = nEstadoUsuario;
        this.nTipoUsuario = nTipoUsuario;
        this.adPersona = adPersona;
        this.nidUsuario = nidUsuario;
    }

    public String getCUsuario() {
        return cUsuario;
    }

    public void setCUsuario(String cUsuario) {
        this.cUsuario = cUsuario;
    }

    public BigDecimal getNEstadoUsuario() {
        return nEstadoUsuario;
    }

    public void setNEstadoUsuario(BigDecimal nEstadoUsuario) {
        this.nEstadoUsuario = nEstadoUsuario;
    }

    public BigDecimal getNTipoUsuario() {
        return nTipoUsuario;
    }

    public void setNTipoUsuario(BigDecimal nTipoUsuario) {
        this.nTipoUsuario = nTipoUsuario;
    }


    public BigDecimal getNidUsuario() {
        return nidUsuario;
    }

    public void setNidUsuario(BigDecimal nidUsuario) {
        this.nidUsuario = nidUsuario;
    }

    public List<ADClave> getClavesList() {
        return clavesList;
    }

    public void setClavesList(List<ADClave> clavesList) {
        this.clavesList = clavesList;
    }

    public ADClave addADClave(ADClave ADClave) {
        getClavesList().add(ADClave);
        ADClave.setAdUsuario(this);
        return ADClave;
    }

    public ADClave removeADClave(ADClave ADClave) {
        getClavesList().remove(ADClave);
        ADClave.setAdUsuario(null);
        return ADClave;
    }

    public List<ADUsuarioXPermiso> getUsuarioXPermisosList() {
        return usuarioXPermisosList;
    }

    public void setUsuarioXPermisosList(List<ADUsuarioXPermiso> usuarioXPermisosList) {
        this.usuarioXPermisosList = usuarioXPermisosList;
    }

    public ADUsuarioXPermiso addADUsuarioXPermiso(ADUsuarioXPermiso ADUsuarioXPermiso) {
        getUsuarioXPermisosList().add(ADUsuarioXPermiso);
        ADUsuarioXPermiso.setAdUsuario(this);
        return ADUsuarioXPermiso;
    }

    public ADUsuarioXPermiso removeADUsuarioXPermiso(ADUsuarioXPermiso ADUsuarioXPermiso) {
        getUsuarioXPermisosList().remove(ADUsuarioXPermiso);
        ADUsuarioXPermiso.setAdUsuario(null);
        return ADUsuarioXPermiso;
    }

    public ADPersona getAdPersona() {
        return adPersona;
    }

    public void setAdPersona(ADPersona adPersona) {
        this.adPersona = adPersona;
    }
}