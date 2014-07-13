package silat.servicios_negocio.entidades.admin;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries( { @NamedQuery(name = "ADConstraint.findAll", query = "select o from ADConstraint o") })
@Table(name = "ADMCONS")
@IdClass(ADConstraintPK.class)
public class ADConstraint implements Serializable {
    @Column(name = "C_ABRV", length = 10)
    private String cAbrv;
    @Id
    @Column(name = "C_CAMPO", nullable = false, length = 30)
    private String cCampo;
    @Column(name = "C_DESC", nullable = false, length = 20)
    private String cDescrip;
    @Id
    @Column(name = "C_NOB_TBL", nullable = false, length = 30)
    private String cNombreTabla;
    @Id
    @Column(name = "C_VALCON", nullable = false, length = 1)
    private String cValorCampo;

    public ADConstraint() {
    }

    public ADConstraint(String cAbrv, String cCampo, String cDescrip, String cNombreTabla, String cValorCampo) {
        this.cAbrv = cAbrv;
        this.cCampo = cCampo;
        this.cDescrip = cDescrip;
        this.cNombreTabla = cNombreTabla;
        this.cValorCampo = cValorCampo;
    }

    public String getCAbrv() {
        return cAbrv;
    }

    public void setCAbrv(String cAbrv) {
        this.cAbrv = cAbrv;
    }

    public String getCCampo() {
        return cCampo;
    }

    public void setCCampo(String cCampo) {
        this.cCampo = cCampo;
    }

    public String getCDescrip() {
        return cDescrip;
    }

    public void setCDescrip(String cDescrip) {
        this.cDescrip = cDescrip;
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
