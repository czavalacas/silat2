package silat.servicios_negocio.entidades.trans;

import java.io.Serializable;

public class TrmcodiPK implements Serializable {
    public String cidunin;
    public String tipdoc;

    public TrmcodiPK() {
    }

    public TrmcodiPK(String ciduni, String tipdoc) {
        this.cidunin = ciduni;
        this.tipdoc = tipdoc;
    }

    public boolean equals(Object other) {
        if (other instanceof TrmcodiPK) {
            final TrmcodiPK otherTrmcodiPK = (TrmcodiPK)other;
            final boolean areEqual =
                (otherTrmcodiPK.cidunin.equals(cidunin) && otherTrmcodiPK.tipdoc.equals(tipdoc));
            return areEqual;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public String getCidunin() {
        return cidunin;
    }

    public void setCidunin(String cidunin) {
        this.cidunin = cidunin;
    }

    public String getTipdoc() {
        return tipdoc;
    }

    public void setTipdoc(String tipdoc) {
        this.tipdoc = tipdoc;
    }
}
