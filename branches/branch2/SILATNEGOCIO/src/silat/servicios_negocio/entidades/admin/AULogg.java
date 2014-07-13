package silat.servicios_negocio.entidades.admin;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries( { @NamedQuery(name = "AULogg.findAll", query = "select o from AULogg o") })
@Table(name = "AUDLOGG")
public class AULogg implements Serializable {
    @Column(name = "C_HORA", nullable = false, length = 20)
    private String cHora;
    @Column(name = "C_HORA_CONEXION", nullable = false, length = 20)
    private String cHoraConexion;
    @Column(name = "C_HORA_DESCNX", length = 20)
    private String cHoraDescnx;
    @Column(name = "C_IP_PRIVADA", nullable = false, length = 20)
    private String cIpPrivada;
    @Column(name = "C_IP_PUBLICA", nullable = false, length = 20)
    private String cIpPublica;
    @Column(name = "C_NAVEGADOR", nullable = false, length = 30)
    private String cNavegador;
    @Column(name = "C_SISTEMA_OPP", nullable = false, length = 30)
    private String cSistemaOpp;
    @Temporal(TemporalType.DATE)
    @Column(name = "D_FEC_CONEXION", nullable = false)
    private Date dFecConexion;
    @Temporal(TemporalType.DATE)
    @Column(name = "F_FEC_DESCNX", nullable = false)
    private Date fecDescnx;
    @Temporal(TemporalType.DATE)
    @Column(name = "F_FECHA", nullable = false)
    private Date fechaRegistro;
    @Column(name = "N_MODULO", nullable = false)
    private BigDecimal nModulo;
    @Column(name = "N_TIP_REGISTRO")
    private BigDecimal nTipRegistro;
    @Id
    @Column(name = "NID_LOG", nullable = false)
    private BigDecimal nidLog;
    @ManyToOne
    @JoinColumn(name = "NID_EVENTO")
    private ADEvento adEvento;

    public AULogg() {
    }

    public AULogg(String cHora, String cHoraConexion, String cHoraDescnx, String cIpPrivada, String cIpPublica,
                  String cNavegador, String cSistemaOpp, Date dFecConexion, Date fecDescnx, Date fechaRegistro,
                  BigDecimal nModulo, BigDecimal nTipRegistro, ADEvento adEvento, BigDecimal nidLog) {
        this.cHora = cHora;
        this.cHoraConexion = cHoraConexion;
        this.cHoraDescnx = cHoraDescnx;
        this.cIpPrivada = cIpPrivada;
        this.cIpPublica = cIpPublica;
        this.cNavegador = cNavegador;
        this.cSistemaOpp = cSistemaOpp;
        this.dFecConexion = dFecConexion;
        this.fecDescnx = fecDescnx;
        this.fechaRegistro = fechaRegistro;
        this.nModulo = nModulo;
        this.nTipRegistro = nTipRegistro;
        this.adEvento = adEvento;
        this.nidLog = nidLog;
    }

    public String getCHora() {
        return cHora;
    }

    public void setCHora(String cHora) {
        this.cHora = cHora;
    }

    public String getCHoraConexion() {
        return cHoraConexion;
    }

    public void setCHoraConexion(String cHoraConexion) {
        this.cHoraConexion = cHoraConexion;
    }

    public String getCHoraDescnx() {
        return cHoraDescnx;
    }

    public void setCHoraDescnx(String cHoraDescnx) {
        this.cHoraDescnx = cHoraDescnx;
    }

    public String getCIpPrivada() {
        return cIpPrivada;
    }

    public void setCIpPrivada(String cIpPrivada) {
        this.cIpPrivada = cIpPrivada;
    }

    public String getCIpPublica() {
        return cIpPublica;
    }

    public void setCIpPublica(String cIpPublica) {
        this.cIpPublica = cIpPublica;
    }

    public String getCNavegador() {
        return cNavegador;
    }

    public void setCNavegador(String cNavegador) {
        this.cNavegador = cNavegador;
    }

    public String getCSistemaOpp() {
        return cSistemaOpp;
    }

    public void setCSistemaOpp(String cSistemaOpp) {
        this.cSistemaOpp = cSistemaOpp;
    }

    public Date getDFecConexion() {
        return dFecConexion;
    }

    public void setDFecConexion(Date dFecConexion) {
        this.dFecConexion = dFecConexion;
    }

    public Date getFecDescnx() {
        return fecDescnx;
    }

    public void setFecDescnx(Date fecDescnx) {
        this.fecDescnx = fecDescnx;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public BigDecimal getNModulo() {
        return nModulo;
    }

    public void setNModulo(BigDecimal nModulo) {
        this.nModulo = nModulo;
    }

    public BigDecimal getNTipRegistro() {
        return nTipRegistro;
    }

    public void setNTipRegistro(BigDecimal nTipRegistro) {
        this.nTipRegistro = nTipRegistro;
    }


    public BigDecimal getNidLog() {
        return nidLog;
    }

    public void setNidLog(BigDecimal nidLog) {
        this.nidLog = nidLog;
    }

    public ADEvento getAdEvento() {
        return adEvento;
    }

    public void setAdEvento(ADEvento adEvento) {
        this.adEvento = adEvento;
    }
}
