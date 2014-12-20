package silat.servicios_negocio.entidades.admin;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries( { @NamedQuery(name = "Addgast.findAll", query = "select o from ADGasto o") })
@Table(name="ADDGAST")
public class ADGasto implements Serializable {
    @Column(name = "BL_IMGRECIBO")
    //@Lob
    private byte[] blobImagenRecibo;
    @Column(name = "C_ESTREG", nullable = false, length = 1)
    private String estadoRegistro;
    @Column(name = "CANTPER")
    private Integer cantper;
    @Column(name = "CID_FACTURA", length = 15)
    private String cidFactura;
    @Column(length = 300)
    private String cimgrecibo;
    @Column(name = "D_MONTO_GENERAL", nullable = false)
    private BigDecimal dMontoGeneral;
    @Column(length = 100)
    private String destino;
    @Column(length = 100)
    private String c_detalle; 
    @Column(length = 80)
    private String c_banco;
    @Temporal(TemporalType.DATE)
    @Column(name = "F_FEC_GASTO", nullable = false)
    private Date fechaGasto;
    @ManyToOne
    @JoinColumn(name = "NID_FLOTA")
    private ADFlota adFlota;
    @Id     
    @Column(name = "NID_GASTO", nullable = false)
    @TableGenerator( name = "adgasto", table = "codigo", pkColumnName = "APP_SEQ_NAME", pkColumnValue = "adgasto.nid_gasto", valueColumnName = "APP_SEQ_VALUE", initialValue = 50, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "adgasto" )
    private Long nidGasto;
    @Column(name = "NID_PROTRA")
    private Long nidProtra;
    @Column(name = "NRO_CHEQUE", length = 30)
    private String nroCheque;
    @ManyToOne
    @JoinColumn(name = "NID_MODPAG")
    private ADModalidadPago modalidadPago;
    @ManyToOne
    @JoinColumn(name = "NID_SERBAS")
    private ADUtil utilServicioBasico;
    @ManyToOne
    @JoinColumn(name = "NID_TICO")
    private ADUtil utilTipoCombustible;
    @ManyToOne
    @JoinColumn(name = "N_TIPO_GASTO")
    private ADTipoGasto tipoGasto;
    @ManyToOne
    @JoinColumn(name = "TIPMAN")
    private ADUtil utilTipoMantenimiento;
//cascade={CascadeType.PERSIST},fetch=FetchType.LAZY
    
    public ADGasto() {
        
    }
    
    public ADGasto(String estadoRegistro,Integer cantper,String cidFactura,String cimgrecibo,BigDecimal dMontoGeneral,String destino,
                   String c_detalle,String c_banco,Date fechaGasto,ADFlota adFlota,Long nidGasto,Long nidProtra,
                   String nroCheque,ADModalidadPago modalidadPago,ADUtil utilServicioBasico,ADUtil utilTipoCombustible,
                   ADTipoGasto tipoGasto,ADUtil utilTipoMantenimiento) {
        this.estadoRegistro = estadoRegistro;
        this.cantper = cantper;
        this.cidFactura = cidFactura;
        this.cimgrecibo = cimgrecibo;
        this.dMontoGeneral = dMontoGeneral;
        this.destino = destino;
        this.c_detalle = c_detalle;
        this.c_banco = c_banco;
        this.fechaGasto = fechaGasto;
        this.adFlota = adFlota;
        this.nidGasto = nidGasto;
        this.nidProtra = nidProtra;
        this.nroCheque = nroCheque;
        this.modalidadPago = modalidadPago;
        this.utilServicioBasico = utilServicioBasico;
        this.utilTipoCombustible = utilTipoCombustible;
        this.tipoGasto = tipoGasto;
        this.utilTipoMantenimiento = utilTipoMantenimiento;
    }

    public String getCidFactura() {
        return cidFactura;
    }

    public void setCidFactura(String cidFactura) {
        this.cidFactura = cidFactura;
    }

    public String getCimgrecibo() {
        return cimgrecibo;
    }

    public void setCimgrecibo(String cimgrecibo) {
        this.cimgrecibo = cimgrecibo;
    }

    public BigDecimal getDMontoGeneral() {
        return dMontoGeneral;
    }

    public void setDMontoGeneral(BigDecimal dMontoGeneral) {
        this.dMontoGeneral = dMontoGeneral;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getNroCheque() {
        return nroCheque;
    }

    public void setNroCheque(String nroCheque) {
        this.nroCheque = nroCheque;
    }
   
    public void setBlobImagenRecibo(byte[] blobImagenRecibo) {
        this.blobImagenRecibo = blobImagenRecibo;
    }

    public byte[] getBlobImagenRecibo() {
        return blobImagenRecibo;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setCantper(Integer cantper) {
        this.cantper = cantper;
    }

    public Integer getCantper() {
        return cantper;
    }

    public void setFechaGasto(Date fechaGasto) {
        this.fechaGasto = fechaGasto;
    }

    public Date getFechaGasto() {
        return fechaGasto;
    }

    public void setNidGasto(Long nidGasto) {
        this.nidGasto = nidGasto;
    }

    public Long getNidGasto() {
        return nidGasto;
    }

    public void setNidProtra(Long nidProtra) {
        this.nidProtra = nidProtra;
    }

    public Long getNidProtra() {
        return nidProtra;
    }

    public void setModalidadPago(ADModalidadPago modalidadPago) {
        this.modalidadPago = modalidadPago;
    }

    public ADModalidadPago getModalidadPago() {
        return modalidadPago;
    }

    public void setUtilServicioBasico(ADUtil utilServicioBasico) {
        this.utilServicioBasico = utilServicioBasico;
    }

    public ADUtil getUtilServicioBasico() {
        return utilServicioBasico;
    }

    public void setUtilTipoCombustible(ADUtil utilTipoCombustible) {
        this.utilTipoCombustible = utilTipoCombustible;
    }

    public ADUtil getUtilTipoCombustible() {
        return utilTipoCombustible;
    }

    public void setUtilTipoMantenimiento(ADUtil utilTipoMantenimiento) {
        this.utilTipoMantenimiento = utilTipoMantenimiento;
    }

    public ADUtil getUtilTipoMantenimiento() {
        return utilTipoMantenimiento;
    }

    public void setTipoGasto(ADTipoGasto tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    public ADTipoGasto getTipoGasto() {
        return tipoGasto;
    }

    public void setAdFlota(ADFlota adFlota) {
        this.adFlota = adFlota;
    }

    public ADFlota getAdFlota() {
        return adFlota;
    }

    public void setC_detalle(String c_detalle) {
        this.c_detalle = c_detalle;
    }

    public String getC_detalle() {
        return c_detalle;
    }

    public void setC_banco(String c_banco) {
        this.c_banco = c_banco;
    }

    public String getC_banco() {
        return c_banco;
    }
}
