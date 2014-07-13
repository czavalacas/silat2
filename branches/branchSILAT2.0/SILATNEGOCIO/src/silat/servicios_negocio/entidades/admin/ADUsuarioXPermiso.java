package silat.servicios_negocio.entidades.admin;

import java.io.Serializable;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import silat.servicios_negocio.entidades.audsis.STRolXPermiso;

@Entity
@NamedQueries( { @NamedQuery(name = "ADUsuarioXPermiso.findAll", query = "select o from ADUsuarioXPermiso o") })
@Table(name = "ADDUSPE")
@IdClass(ADUsuarioXPermisoPK.class)
public class ADUsuarioXPermiso implements Serializable {
    @Id
    @Column(name = "N_EST_ROLPE", nullable = false, insertable = false, updatable = false)
    private BigDecimal nEstadoRolXPermiso;
    @Column(name = "N_EST_USPE", nullable = false, length = 1)
    private String nEstadoUsuarioXPermiso;
    @Id
    @Column(name = "NID_PERMISO", nullable = false, insertable = false, updatable = false)
    private BigDecimal nidPermiso;
    @Id
    @Column(name = "NID_ROLE", nullable = false, insertable = false, updatable = false)
    private BigDecimal nidRole;
    @Id
    @Column(name = "NID_USUARIO", nullable = false, insertable = false, updatable = false)
    private BigDecimal nidUsuario;
    @ManyToOne
    @JoinColumns( { @JoinColumn(name = "NID_ROLE", referencedColumnName = "NID_ROLE"),
                    @JoinColumn(name = "NID_PERMISO", referencedColumnName = "NID_PERMISO"),
                    @JoinColumn(name = "N_EST_ROLPE", referencedColumnName = "N_EST_ROL_PERM") })
    private STRolXPermiso rolXPermiso;
    @ManyToOne
    @JoinColumn(name = "NID_USUARIO")
    private ADUsuario adUsuario;

    public ADUsuarioXPermiso() {
    }

    public ADUsuarioXPermiso(String nEstadoUsuarioXPermiso, STRolXPermiso rolXPermiso, ADUsuario adUsuario) {
        this.nEstadoUsuarioXPermiso = nEstadoUsuarioXPermiso;
        this.rolXPermiso = rolXPermiso;
        this.adUsuario = adUsuario;
    }

    public BigDecimal getNEstadoRolXPermiso() {
        return nEstadoRolXPermiso;
    }

    public void setNEstadoRolXPermiso(BigDecimal nEstadoRolXPermiso) {
        this.nEstadoRolXPermiso = nEstadoRolXPermiso;
    }

    public String getNEstadoUsuarioXPermiso() {
        return nEstadoUsuarioXPermiso;
    }

    public void setNEstadoUsuarioXPermiso(String nEstadoUsuarioXPermiso) {
        this.nEstadoUsuarioXPermiso = nEstadoUsuarioXPermiso;
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

    public BigDecimal getNidUsuario() {
        return nidUsuario;
    }

    public void setNidUsuario(BigDecimal nidUsuario) {
        this.nidUsuario = nidUsuario;
    }

    public STRolXPermiso getRolXPermiso() {
        return rolXPermiso;
    }

    public void setRolXPermiso(STRolXPermiso rolXPermiso) {
        this.rolXPermiso = rolXPermiso;
        if (rolXPermiso != null) {
            this.nEstadoRolXPermiso = rolXPermiso.getNEstRolXPermiso();
            this.nidPermiso = rolXPermiso.getNidPermiso();
            this.nidRole = rolXPermiso.getNidRole();
        }
    }

    public ADUsuario getAdUsuario() {
        return adUsuario;
    }

    public void setAdUsuario(ADUsuario adUsuario) {
        this.adUsuario = adUsuario;
        if (adUsuario != null) {
            this.nidUsuario = adUsuario.getNidUsuario();
        }
    }
}