package silat.servicios_negocio.entidades.trans;

import java.io.Serializable;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.TableGenerator;

import silat.servicios_negocio.entidades.audsis.TROrdenServicio;


@Entity
@NamedQueries( { @NamedQuery(name = "TRUnidadMedida.findAll", query = "select o from TRUnidadMedida o") })
@Table(name = "TRMUNME")
public class TRUnidadMedida implements Serializable {
    @Column(name = "C_DESC_UNME", length = 25)
    private String descUnidadMedida;
    @Column(name = "C_SIGLA")
    private String sigla;
    @Id
    @Column(name = "NID_UNMEDIDA", nullable = false)
    @TableGenerator( name = "trunidad", table = "codigo", pkColumnName = "APP_SEQ_NAME", pkColumnValue = "trunidad.nid_unidad", valueColumnName = "APP_SEQ_VALUE", initialValue = 50, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "trunidad" )
    private Integer nidUnidadMedida;

    public TRUnidadMedida() {
        
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getSigla() {
        return sigla;
    }

    public void setDescUnidadMedida(String descUnidadMedida) {
        this.descUnidadMedida = descUnidadMedida;
    }

    public String getDescUnidadMedida() {
        return descUnidadMedida;
    }

    public void setNidUnidadMedida(Integer nidUnidadMedida) {
        this.nidUnidadMedida = nidUnidadMedida;
    }

    public Integer getNidUnidadMedida() {
        return nidUnidadMedida;
    }
}
