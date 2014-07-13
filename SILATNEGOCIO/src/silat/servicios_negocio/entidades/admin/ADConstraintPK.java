package silat.servicios_negocio.entidades.admin;

import java.io.Serializable;

public class ADConstraintPK implements Serializable {
    public String cCampo;
    public String cNombreTabla;
    public String cValorCampo;

    public ADConstraintPK() {
    }

    public ADConstraintPK(String cCampo, String cNombreTabla, String cValorCampo) {
        this.cCampo = cCampo;
        this.cNombreTabla = cNombreTabla;
        this.cValorCampo = cValorCampo;
    }

    public boolean equals(Object other) {
        if (other instanceof ADConstraintPK) {
            final ADConstraintPK otherADConstraintPK = (ADConstraintPK)other;
            final boolean areEqual =
                (otherADConstraintPK.cCampo.equals(cCampo) && otherADConstraintPK.cNombreTabla.equals(cNombreTabla) &&
                 otherADConstraintPK.cValorCampo.equals(cValorCampo));
            return areEqual;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public String getCCampo() {
        return cCampo;
    }

    public void setCCampo(String cCampo) {
        this.cCampo = cCampo;
    }

    public String getCNombreTabla() {
        return cNombreTabla;
    }

    public void setCNombreTabla(String cNombreTabla) {
        this.cNombreTabla = cNombreTabla;
    }

    public String getCValorCampo() {
        return cValorCampo;
    }

    public void setCValorCampo(String cValorCampo) {
        this.cValorCampo = cValorCampo;
    }
}
