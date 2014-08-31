package silat.servicios_negocio.entidades.trans;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries( { @NamedQuery(name = "TrPreFactura.findAll", query = "select o from TrPreFactura o") })
@Table(name="TRMPREF")
public class TrPreFactura implements Serializable {
    @Column(name = "CODPEDIDO",nullable = false, length = 12)
    private String codpedido;
    @Column(name = "C_ESTREG", nullable = false, length = 1)
    private String cEstreg;
    @Column(name = "FLG_FACTURA", nullable = false, length = 1)
    private String flgFactura;
    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;
    @Column(name = "NID_CLIENTE", nullable = false)
    private Long nidCliente;
    @Id
    @Column(name = "NID_PREFACT", nullable = false)
    @TableGenerator( name = "trprefac", table = "codigo", pkColumnName = "APP_SEQ_NAME", pkColumnValue = "trprefac.nid_prefac", valueColumnName = "APP_SEQ_VALUE", initialValue = 50, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "trprefac" )
    private Long nidPrefact;
    @Column(name = "PREFACT_TOTAL")
    private BigDecimal prefactTotal;
    @OneToMany(mappedBy = "preFactura",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<TrItemPreFactura> itemsPreFacturaLista;
    @Column(name = "CLIENTE", length = 200)
    private String cliente;
    @Column(name = "GUIAS_TODELETE")
    private String guiasToDelete;
    @Column(name = "GUIAS_TOREPORTE")
    private String guiasToReporte;
    
    public TrPreFactura() {
        
    }

    public TrPreFactura(String cEstreg,
                        String codpedido,
                        Date fechaCreacion,
                        Long nidCliente,
                        Long nidPrefact,
                        BigDecimal prefactTotal) {
        this.cEstreg = cEstreg;
        this.codpedido = codpedido;
        this.fechaCreacion = fechaCreacion;
        this.nidCliente = nidCliente;
        this.nidPrefact = nidPrefact;
        this.prefactTotal = prefactTotal;
    }

    public String getCodpedido() {
        return codpedido;
    }

    public void setCodpedido(String codpedido) {
        this.codpedido = codpedido;
    }

    public String getCEstreg() {
        return cEstreg;
    }

    public void setCEstreg(String cEstreg) {
        this.cEstreg = cEstreg;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Long getNidPrefact() {
        return nidPrefact;
    }

    public void setNidPrefact(Long nidPrefact) {
        this.nidPrefact = nidPrefact;
    }

    public BigDecimal getPrefactTotal() {
        return prefactTotal;
    }

    public void setPrefactTotal(BigDecimal prefactTotal) {
        this.prefactTotal = prefactTotal;
    }


    public TrItemPreFactura addTrdprit(TrItemPreFactura trdprit) {
        getItemsPreFacturaLista().add(trdprit);
        trdprit.setPreFactura(this);
        return trdprit;
    }

    public TrItemPreFactura removeTrdprit(TrItemPreFactura trdprit) {
        getItemsPreFacturaLista().remove(trdprit);
        trdprit.setPreFactura(null);
        return trdprit;
    }

    public void setItemsPreFacturaLista(List<TrItemPreFactura> itemsPreFacturaLista) {
        this.itemsPreFacturaLista = itemsPreFacturaLista;
    }

    public List<TrItemPreFactura> getItemsPreFacturaLista() {
        return itemsPreFacturaLista;
    }

    public void setNidCliente(Long nidCliente) {
        this.nidCliente = nidCliente;
    }

    public Long getNidCliente() {
        return nidCliente;
    }

    public void setFlgFactura(String flgFactura) {
        this.flgFactura = flgFactura;
    }

    public String getFlgFactura() {
        return flgFactura;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCliente() {
        return cliente;
    }

    public void setGuiasToDelete(String guiasToDelete) {
        this.guiasToDelete = guiasToDelete;
    }

    public String getGuiasToDelete() {
        return guiasToDelete;
    }

    public void setGuiasToReporte(String guiasToReporte) {
        this.guiasToReporte = guiasToReporte;
    }

    public String getGuiasToReporte() {
        return guiasToReporte;
    }
}
