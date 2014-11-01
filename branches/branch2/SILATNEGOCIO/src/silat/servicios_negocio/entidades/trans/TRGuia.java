package silat.servicios_negocio.entidades.trans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import silat.servicios_negocio.entidades.admin.ADEmpresa;
import silat.servicios_negocio.entidades.audsis.TROrdenServicio;

@Entity
@NamedQueries( { @NamedQuery(name = "TRGuia.findAll", query = "select o from TRGuia o") })
@Table(name = "TRMGUIA")
public class TRGuia implements Serializable {
    @Column(name = "C_CONFORMIDAD", nullable = false)
    private String cConformidad;
    @Column(name = "C_OBSERVACIONES")
    private String cObservaciones;
    @Temporal(TemporalType.DATE)
    @Column(name = "F_FECHA_GUIA", nullable = false)
    private Date fechaGuia;//
    @Temporal(TemporalType.DATE)
    @Column(name = "F_FECHA_DESPACHO", nullable = false)
    private Date fechaDespacho;
    @Column(name = "N_ESTADO_GUIA", nullable = false)
    private String nEstadoGuia;
    @Column(name = "NID_CHOF")
    private Integer nidChof;
    @Column(name = "NID_FLOTA")
    private Integer nidFlota;
    @Column(name = "NID_DIRERE")
    private Integer nidDireccionRemitente;
    @Column(name = "NID_DIREDE")
    private Integer nidDireccionDestino;
    @Id
    @Column(name = "CID_GUIA", nullable = false)
    private String cidGuia;
    @Column(name = "N_NUM_PAQUETES")
    private Integer numPaquetes;
    @ManyToOne
    @JoinColumn(name = "NID_MANIFIESTO")
    private TRManifiesto trManifiesto;
    @ManyToOne
    @JoinColumn(name = "NID_EMPR_PROV")
    private ADEmpresa adEmpresa;
    @ManyToOne
    @JoinColumn(name = "NID_ORDEN_SERVICIO")
    private TROrdenServicio ordenServicio;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "trGuia",fetch = FetchType.EAGER)
    private List<TRItem> itemsList;
    @ManyToOne
    @JoinColumn(name = "NID_FACTURA")
    private TRFactura trFactura;
    @Column(name = "CIDUNIN")
    private String cidUnidadNegocio;
    @Column(name = "IMG_GR")
    //@Lob
    private byte[] imgGuia;
    @Column(name = "IMG_GUIA_REMI")
    private String imgGuiaProv;
    @Column(name = "D_PRECIO")
    private BigDecimal precio;
    @ManyToOne
    @JoinColumn(name = "NID_PREFITM")
    private TrItemPreFactura itemPreFactura;
    @Column(name = "COMENTARIO")
    private String comentario;
    @Column(name = "VALORACION")
    private Integer valoracion;
    
    
    public TRGuia() {
    }

    public String getCConformidad() {
        return cConformidad;
    }

    public void setCConformidad(String cConformidad) {
        this.cConformidad = cConformidad;
    }

    public String getCObservaciones() {
        return cObservaciones;
    }

    public void setCObservaciones(String cObservaciones) {
        this.cObservaciones = cObservaciones;
    }

    public Date getFechaGuia() {
        return fechaGuia;
    }

    public void setFechaGuia(Date fechaGuia) {
        this.fechaGuia = fechaGuia;
    }

    public String getNEstadoGuia() {
        return nEstadoGuia;
    }

    public void setNEstadoGuia(String nEstadoGuia) {
        this.nEstadoGuia = nEstadoGuia;
    }


    public String getCidGuia() {
        return cidGuia;
    }

    public void setCidGuia(String cidGuia) {
        this.cidGuia = cidGuia;
    }

    public TRManifiesto getTrManifiesto() {
        return trManifiesto;
    }

    public void setTrManifiesto(TRManifiesto trManifiesto) {
        this.trManifiesto = trManifiesto;
    }

    public ADEmpresa getAdEmpresa() {
        return adEmpresa;
    }

    public void setAdEmpresa(ADEmpresa adEmpresa) {
        this.adEmpresa = adEmpresa;
    }

    public TROrdenServicio getOrdenServicio() {
        return ordenServicio;
    }

    public void setOrdenServicio(TROrdenServicio ordenServicio) {
        this.ordenServicio = ordenServicio;
    }

    public List<TRItem> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<TRItem> itemsList) {
        this.itemsList = itemsList;
    }

    public TRItem addTRItem(TRItem TRItem) {
        getItemsList().add(TRItem);
        TRItem.setTrGuia(this);
        return TRItem;
    }

    public TRItem removeTRItem(TRItem TRItem) {
        getItemsList().remove(TRItem);
        TRItem.setTrGuia(null);
        return TRItem;
    }

    public TRFactura getTrFactura() {
        return trFactura;
    }

    public void setTrFactura(TRFactura trFactura) {
        this.trFactura = trFactura;
    }

    public void setNumPaquetes(Integer numPaquetes) {
        this.numPaquetes = numPaquetes;
    }

    public Integer getNumPaquetes() {
        return numPaquetes;
    }

    public void setNidChof(Integer nidChof) {
        this.nidChof = nidChof;
    }

    public Integer getNidChof() {
        return nidChof;
    }

    public void setNidFlota(Integer nidFlota) {
        this.nidFlota = nidFlota;
    }

    public Integer getNidFlota() {
        return nidFlota;
    }

    public void setNidDireccionRemitente(Integer nidDireccionRemitente) {
        this.nidDireccionRemitente = nidDireccionRemitente;
    }

    public Integer getNidDireccionRemitente() {
        return nidDireccionRemitente;
    }

    public void setNidDireccionDestino(Integer nidDireccionDestino) {
        this.nidDireccionDestino = nidDireccionDestino;
    }

    public Integer getNidDireccionDestino() {
        return nidDireccionDestino;
    }

    public void setFechaDespacho(Date fechaDespacho) {
        this.fechaDespacho = fechaDespacho;
    }

    public Date getFechaDespacho() {
        return fechaDespacho;
    }

    public void setCidUnidadNegocio(String cidUnidadNegocio) {
        this.cidUnidadNegocio = cidUnidadNegocio;
    }

    public String getCidUnidadNegocio() {
        return cidUnidadNegocio;
    }

    public void setImgGuia(byte[] imgGuia) {
        this.imgGuia = imgGuia;
    }

    public byte[] getImgGuia() {
        return imgGuia;
    }

    public void setImgGuiaProv(String imgGuiaProv) {
        this.imgGuiaProv = imgGuiaProv;
    }

    public String getImgGuiaProv() {
        return imgGuiaProv;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setItemPreFactura(TrItemPreFactura itemPreFactura) {
        this.itemPreFactura = itemPreFactura;
    }

    public TrItemPreFactura getItemPreFactura() {
        return itemPreFactura;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setValoracion(Integer valoracion) {
        this.valoracion = valoracion;
    }

    public Integer getValoracion() {
        return valoracion;
    }
}
