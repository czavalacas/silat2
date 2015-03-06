package silat.servicios_negocio.entidades.trans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries( { @NamedQuery(name = "TRCodigo.findAll", query = "select o from TRCodigo o") })
@Table(name = "\"codigo\"")
public class TRCodigo implements Serializable {
    @Id
    @Column(name = "APP_SEQ_NAME", nullable = false)
    private String appSeqName;
    @Column(name = "APP_SEQ_VALUE", nullable = false)
    private long appSeqValue;

    public TRCodigo() {
    }

    public TRCodigo(String appSeqName, long appSeqValue) {
        this.appSeqName = appSeqName;
        this.appSeqValue = appSeqValue;
    }

    public String getAppSeqName() {
        return appSeqName;
    }

    public void setAppSeqName(String appSeqName) {
        this.appSeqName = appSeqName;
    }

    public long getAppSeqValue() {
        return appSeqValue;
    }

    public void setAppSeqValue(long appSeqValue) {
        this.appSeqValue = appSeqValue;
    }
}
