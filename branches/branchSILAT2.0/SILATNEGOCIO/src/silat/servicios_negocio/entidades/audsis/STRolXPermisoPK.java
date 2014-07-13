package silat.servicios_negocio.entidades.audsis;

import java.io.Serializable;

import java.math.BigDecimal;

public class STRolXPermisoPK implements Serializable {
    public BigDecimal nEstRolXPermiso;
    public BigDecimal nidPermiso;
    public BigDecimal nidRole;

    public STRolXPermisoPK() {
    }

    public STRolXPermisoPK(BigDecimal nEstRolXPermiso, BigDecimal nidPermiso, BigDecimal nidRole) {
        this.nEstRolXPermiso = nEstRolXPermiso;
        this.nidPermiso = nidPermiso;
        this.nidRole = nidRole;
    }

    public boolean equals(Object other) {
        if (other instanceof STRolXPermisoPK) {
            final STRolXPermisoPK otherSTRolXPermisoPK = (STRolXPermisoPK)other;
            final boolean areEqual =
                (otherSTRolXPermisoPK.nEstRolXPermiso.equals(nEstRolXPermiso) && otherSTRolXPermisoPK.nidPermiso.equals(nidPermiso) &&
                 otherSTRolXPermisoPK.nidRole.equals(nidRole));
            return areEqual;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
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
}
