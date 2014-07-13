package silat.servicios_negocio.entidades.admin;

import java.io.Serializable;

import java.math.BigDecimal;

public class ADUsuarioXPermisoPK implements Serializable {
    public BigDecimal nEstadoRolXPermiso;
    public BigDecimal nidPermiso;
    public BigDecimal nidRole;
    public BigDecimal nidUsuario;

    public ADUsuarioXPermisoPK() {
    }

    public ADUsuarioXPermisoPK(BigDecimal nEstadoRolXPermiso, BigDecimal nidPermiso, BigDecimal nidRole,
                               BigDecimal nidUsuario) {
        this.nEstadoRolXPermiso = nEstadoRolXPermiso;
        this.nidPermiso = nidPermiso;
        this.nidRole = nidRole;
        this.nidUsuario = nidUsuario;
    }

    public boolean equals(Object other) {
        if (other instanceof ADUsuarioXPermisoPK) {
            final ADUsuarioXPermisoPK otherADUsuarioXPermisoPK = (ADUsuarioXPermisoPK)other;
            final boolean areEqual =
                (otherADUsuarioXPermisoPK.nEstadoRolXPermiso.equals(nEstadoRolXPermiso) && otherADUsuarioXPermisoPK.nidPermiso.equals(nidPermiso) &&
                 otherADUsuarioXPermisoPK.nidRole.equals(nidRole) &&
                 otherADUsuarioXPermisoPK.nidUsuario.equals(nidUsuario));
            return areEqual;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public BigDecimal getNEstadoRolXPermiso() {
        return nEstadoRolXPermiso;
    }

    public void setNEstadoRolXPermiso(BigDecimal nEstadoRolXPermiso) {
        this.nEstadoRolXPermiso = nEstadoRolXPermiso;
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
}
