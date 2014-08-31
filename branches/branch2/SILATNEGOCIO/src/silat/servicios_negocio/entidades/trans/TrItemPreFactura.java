package silat.servicios_negocio.entidades.trans;

import java.io.Serializable;

import java.math.BigDecimal;

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

@Entity
@NamedQueries( { @NamedQuery(name = "TrItemPreFactura.findAll", query = "select o from TrItemPreFactura o") })
@Table(name="TRDPRIT")
public class TrItemPreFactura implements Serializable {
    @Column(length = 250)
    private String destino;
    @Column(name = "IGV_SUBTOTAL")
    private BigDecimal igvSubtotal;
    @Id    
    @Column(name = "NID_PREFITM", nullable = false)
    @TableGenerator( name = "tritemPrefa", table = "codigo", pkColumnName = "APP_SEQ_NAME", pkColumnValue = "tritemPrefa.nid_itemPre", valueColumnName = "APP_SEQ_VALUE", initialValue = 50, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "tritemPrefa" )
    private BigDecimal nidPrefitm;
    @Column(nullable = false)
    private BigDecimal subtotal;
    @Column(name = "TOTAL_ITEM")
    private BigDecimal totalItem;
    @ManyToOne
    @JoinColumn(name = "NID_PREFACT")
    private TrPreFactura preFactura;
    @OneToMany(mappedBy = "itemPreFactura",fetch = FetchType.EAGER)
    private List<TRGuia> guiasList;
    @Column(name = "N_ORDEN")
    private int orden;
    @Column(name = "UBIGEO_ORIGEN")
    private String ubigeoOrigen;
    @Column(name = "UBIGEO_DESTINO")
    private String ubigeoDestino;
    @Column(name = "UBIGEO_ORI_PROV")
    private String ubigeoOrigenProv;
    @Column(name = "UBIGEO_DES_PROV")
    private String ubigeoDestinoProv;
    @Column(name = "UBIGEO_ORI_DIST")
    private String ubigeoOrigenDist;
    @Column(name = "UBIGEO_DES_DIST")
    private String ubigeoDestinoDist;
    @Column(name = "GUIAS_CONCAT")
    private String guiasConcat;
    @Column(name = "CLIENTE")
    private String cliente;
    @Column(name = "DESC_DEPART_ORIGEN")
    private String descDepOrigen;
    @Column(name = "DESC_DEPART_DESTINO")
    private String descDepDestino;
    @Column(name = "FLG_QUITAR_GUIA")
    private String flgQuitarGuia;
    @Column(name = "GUIAS_TONULL")
    private String guiasToNull;

    public TrItemPreFactura() {
    }

    public TrItemPreFactura(String destino, BigDecimal igvSubtotal, TrPreFactura itemsPreFacturaLista, BigDecimal nidPrefitm,
                   BigDecimal subtotal, BigDecimal totalItem) {
        this.destino = destino;
        this.igvSubtotal = igvSubtotal;
        this.preFactura = itemsPreFacturaLista;
        this.nidPrefitm = nidPrefitm;
        this.subtotal = subtotal;
        this.totalItem = totalItem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public BigDecimal getIgvSubtotal() {
        return igvSubtotal;
    }

    public void setIgvSubtotal(BigDecimal igvSubtotal) {
        this.igvSubtotal = igvSubtotal;
    }


    public BigDecimal getNidPrefitm() {
        return nidPrefitm;
    }

    public void setNidPrefitm(BigDecimal nidPrefitm) {
        this.nidPrefitm = nidPrefitm;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(BigDecimal totalItem) {
        this.totalItem = totalItem;
    }

    public void setPreFactura(TrPreFactura preFactura) {
        this.preFactura = preFactura;
    }

    public TrPreFactura getPreFactura() {
        return preFactura;
    }

    public void setGuiasList(List<TRGuia> guiasList) {
        this.guiasList = guiasList;
    }

    public List<TRGuia> getGuiasList() {
        return guiasList;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getOrden() {
        return orden;
    }

    public void setUbigeoOrigen(String ubigeoOrigen) {
        this.ubigeoOrigen = ubigeoOrigen;
    }

    public String getUbigeoOrigen() {
        return ubigeoOrigen;
    }

    public void setUbigeoDestino(String ubigeoDestino) {
        this.ubigeoDestino = ubigeoDestino;
    }

    public String getUbigeoDestino() {
        return ubigeoDestino;
    }

    public void setUbigeoOrigenProv(String ubigeoOrigenProv) {
        this.ubigeoOrigenProv = ubigeoOrigenProv;
    }

    public String getUbigeoOrigenProv() {
        return ubigeoOrigenProv;
    }

    public void setUbigeoDestinoProv(String ubigeoDestinoProv) {
        this.ubigeoDestinoProv = ubigeoDestinoProv;
    }

    public String getUbigeoDestinoProv() {
        return ubigeoDestinoProv;
    }

    public void setUbigeoOrigenDist(String ubigeoOrigenDist) {
        this.ubigeoOrigenDist = ubigeoOrigenDist;
    }

    public String getUbigeoOrigenDist() {
        return ubigeoOrigenDist;
    }

    public void setUbigeoDestinoDist(String ubigeoDestinoDist) {
        this.ubigeoDestinoDist = ubigeoDestinoDist;
    }

    public String getUbigeoDestinoDist() {
        return ubigeoDestinoDist;
    }

    public void setGuiasConcat(String guiasConcat) {
        this.guiasConcat = guiasConcat;
    }

    public String getGuiasConcat() {
        return guiasConcat;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCliente() {
        return cliente;
    }

    public void setDescDepOrigen(String descDepOrigen) {
        this.descDepOrigen = descDepOrigen;
    }

    public String getDescDepOrigen() {
        return descDepOrigen;
    }

    public void setDescDepDestino(String descDepDestino) {
        this.descDepDestino = descDepDestino;
    }

    public String getDescDepDestino() {
        return descDepDestino;
    }

    public void setFlgQuitarGuia(String flgQuitarGuia) {
        this.flgQuitarGuia = flgQuitarGuia;
    }

    public String getFlgQuitarGuia() {
        return flgQuitarGuia;
    }

    public void setGuiasToNull(String guiasToNull) {
        this.guiasToNull = guiasToNull;
    }

    public String getGuiasToNull() {
        return guiasToNull;
    }
}
