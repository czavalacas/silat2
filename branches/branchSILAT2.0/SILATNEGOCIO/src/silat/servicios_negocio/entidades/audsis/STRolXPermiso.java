package silat.servicios_negocio.entidades.audsis;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import silat.servicios_negocio.entidades.admin.ADPermiso;
import silat.servicios_negocio.entidades.admin.ADUsuarioXPermiso;

@Entity
@NamedQueries( { @NamedQuery(name = "STRolXPermiso.findAll", query = "select o from STRolXPermiso o") })
@Table(name = "STDROPE")
@IdClass(STRolXPermisoPK.class)
public class STRolXPermiso implements Serializable {
    @Id
    @Column(name = "N_EST_ROL_PERM", nullable = false)
    private BigDecimal nEstRolXPermiso;
    @Id
    @Column(name = "NID_PERMISO", nullable = false, insertable = false, updatable = false)
    private BigDecimal nidPermiso;
    @Id
    @Column(name = "NID_ROLE", nullable = false, insertable = false, updatable = false)
    private BigDecimal nidRole;
    @OneToMany(mappedBy = "rolXPermiso")
    private List<ADUsuarioXPermiso> usuarioXPermisosList;
    @ManyToOne
    @JoinColumn(name = "NID_PERMISO")
    private ADPermiso adPermiso;
    @ManyToOne
    @JoinColumn(name = "NID_ROLE")
    private STRol stRol;

    public STRolXPermiso() {
    }

    public STRolXPermiso(BigDecimal nEstRolXPermiso, ADPermiso adPermiso, STRol stRol) {
        this.nEstRolXPermiso = nEstRolXPermiso;
        this.adPermiso = adPermiso;
        this.stRol = stRol;
    }

    public BigDecimal getNEstRolXPermiso() {
        return nEstRolXPermiso;
    }

    public void setNEstRolXPermiso(BigDecimal nEstRolXPermiso) {
        this.nEstRolXPermiso = nEstRolXPermiso;
    }

    public BigDecimal getNidPermiso() {
        return nidPermiso;
    }

    public void setNidPermiso(BigDecimal nidPermiso) {
        this.nidPermiso = nidPermiso;
    }

    public BigDecimal getNidRole() {
        return nidRole;
    }

    public void setNidRole(BigDecimal nidRole) {
        this.nidRole = nidRole;
    }

    public List<ADUsuarioXPermiso> getUsuarioXPermisosList() {
        return usuarioXPermisosList;
    }

    public void setUsuarioXPermisosList(List<ADUsuarioXPermiso> usuarioXPermisosList) {
        this.usuarioXPermisosList = usuarioXPermisosList;
    }

    public ADUsuarioXPermiso addADUsuarioXPermiso(ADUsuarioXPermiso ADUsuarioXPermiso) {
        getUsuarioXPermisosList().add(ADUsuarioXPermiso);
        ADUsuarioXPermiso.setRolXPermiso(this);
        return ADUsuarioXPermiso;
    }

    public ADUsuarioXPermiso removeADUsuarioXPermiso(ADUsuarioXPermiso ADUsuarioXPermiso) {
        getUsuarioXPermisosList().remove(ADUsuarioXPermiso);
        ADUsuarioXPermiso.setRolXPermiso(null);
        return ADUsuarioXPermiso;
    }

    public ADPermiso getAdPermiso() {
        return adPermiso;
    }

    public void setAdPermiso(ADPermiso adPermiso) {
        this.adPermiso = adPermiso;
        if (adPermiso != null) {
            this.nidPermiso = adPermiso.getNidPermiso();
        }
    }

    public STRol getStRol() {
        return stRol;
    }

    public void setStRol(STRol stRol) {
        this.stRol = stRol;
        if (stRol != null) {
            this.nidRole = stRol.getNidRole();
        }
    }
}
