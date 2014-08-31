package silat.servicios_negocio.entidades.audsis;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import silat.servicios_negocio.entidades.admin.ADEmpresa;
import silat.servicios_negocio.entidades.trans.TRGuia;

@Entity
@NamedQueries( { @NamedQuery(name = "TROrdenServicio.findAll", query = "select o from TROrdenServicio o") })
@Table(name = "TRMORDS")
public class TROrdenServicio implements Serializable {
    @Column(name = "C_DETALLE", length = 500)
    private String cDetalle;
    @Column(name = "C_ESTORD", length = 1)
    private String cEstord;
    @Temporal(TemporalType.DATE)
    @Column(name = "F_FEC_ORDN_SERV", nullable = false)
    private Date fecOrdnServ;
    @Column(name = "N_ESTADO_ORDEN")
    private BigDecimal nEstadoOrden;
    @Id   
    @Column(name = "NID_ORDN_SERV")
    @TableGenerator( name = "trorden", table = "codigo", pkColumnName = "APP_SEQ_NAME", pkColumnValue = "trorden.nid_orden", valueColumnName = "APP_SEQ_VALUE", initialValue = 50, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "trorden" )
    private Integer nidOrdnServ;
    @ManyToOne
    @JoinColumn(name = "N_NID_CLIENTE")
    private ADEmpresa adEmpresa;
    @OneToMany(mappedBy = "ordenServicio", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<TRGuia> guiasList;
    @Column(name = "FLG_VISTO", length = 1)
    private String flgVisto;

    public TROrdenServicio() {
    }

    public TROrdenServicio(String cDetalle, Date fecOrdnServ, BigDecimal nEstadoOrden, ADEmpresa adEmpresa,
                           Integer nidOrdnServ) {
        this.cDetalle = cDetalle;
        this.fecOrdnServ = fecOrdnServ;
        this.nEstadoOrden = nEstadoOrden;
        this.adEmpresa = adEmpresa;
        this.nidOrdnServ = nidOrdnServ;
    }

    public String getCDetalle() {
        return cDetalle;
    }

    public void setCDetalle(String cDetalle) {
        this.cDetalle = cDetalle;
    }

    public Date getFecOrdnServ() {
        return fecOrdnServ;
    }

    public void setFecOrdnServ(Date fecOrdnServ) {
        this.fecOrdnServ = fecOrdnServ;
    }

    public BigDecimal getNEstadoOrden() {
        return nEstadoOrden;
    }

    public void setNEstadoOrden(BigDecimal nEstadoOrden) {
        this.nEstadoOrden = nEstadoOrden;
    }


    public Integer getNidOrdnServ() {
        return nidOrdnServ;
    }

    public void setNidOrdnServ(Integer nidOrdnServ) {
        this.nidOrdnServ = nidOrdnServ;
    }

    public ADEmpresa getAdEmpresa() {
        return adEmpresa;
    }

    public void setAdEmpresa(ADEmpresa adEmpresa) {
        this.adEmpresa = adEmpresa;
    }

    public List<TRGuia> getGuiasList() {
        return guiasList;
    }

    public void setGuiasList(List<TRGuia> guiasList) {
        this.guiasList = guiasList;
    }

    public TRGuia addTRGuia(TRGuia TRGuia) {
        getGuiasList().add(TRGuia);
        TRGuia.setOrdenServicio(this);
        return TRGuia;
    }

    public TRGuia removeTRGuia(TRGuia TRGuia) {
        getGuiasList().remove(TRGuia);
        TRGuia.setOrdenServicio(null);
        return TRGuia;
    }

    public void setCEstord(String cEstord) {
        this.cEstord = cEstord;
    }

    public String getCEstord() {
        return cEstord;
    }

    public void setFlgVisto(String flgVisto) {
        this.flgVisto = flgVisto;
    }

    public String getFlgVisto() {
        return flgVisto;
    }
}
