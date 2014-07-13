package silat.servicios_negocio.entidades.admin;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries( { @NamedQuery(name = "ADClave.findAll", query = "select o from ADClave o") })
@Table(name = "ADDCLAV")
public class ADClave implements Serializable {
    @Column(name = "C_CLAVE", length = 32)
    private String cClave;
    @Temporal(TemporalType.DATE)
    @Column(name = "F_FEC_REGISTRO", nullable = false)
    private Date fecRegistro;
    @Column(name = "N_ESTADO_CLAVE", nullable = false)
    private BigDecimal nEstadoClave;
    @Id
    @SequenceGenerator(name = "GEN_SQ_ADDCLAV_01",sequenceName = "SQ_ADDCLAV_01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_SQ_ADDCLAV_01")
    @Column(name = "NID_CLAVE", nullable = false)
    private BigDecimal nidClave;
    @ManyToOne
    @JoinColumn(name = "NID_USUARIO")
    private ADUsuario adUsuario;
    @OneToMany(mappedBy = "adClave")
    private List<ADHistoricoClave> historicoClavesList;
    @Column(name = "USUARIO")
    private String user;

    public ADClave() {
    }

    public ADClave(String cClave, Date fecRegistro, BigDecimal nEstadoClave, BigDecimal nidClave,
                   ADUsuario adUsuario) {
        this.cClave = cClave;
        this.fecRegistro = fecRegistro;
        this.nEstadoClave = nEstadoClave;
        this.nidClave = nidClave;
        this.adUsuario = adUsuario;
    }

    public String getCClave() {
        return cClave;
    }

    public void setCClave(String cClave) {
        this.cClave = cClave;
    }

    public Date getFecRegistro() {
        return fecRegistro;
    }

    public void setFecRegistro(Date fecRegistro) {
        this.fecRegistro = fecRegistro;
    }

    public BigDecimal getNEstadoClave() {
        return nEstadoClave;
    }

    public void setNEstadoClave(BigDecimal nEstadoClave) {
        this.nEstadoClave = nEstadoClave;
    }

    public BigDecimal getNidClave() {
        return nidClave;
    }

    public void setNidClave(BigDecimal nidClave) {
        this.nidClave = nidClave;
    }


    public ADUsuario getAdUsuario() {
        return adUsuario;
    }

    public void setAdUsuario(ADUsuario adUsuario) {
        this.adUsuario = adUsuario;
    }

    public List<ADHistoricoClave> getHistoricoClavesList() {
        return historicoClavesList;
    }

    public void setHistoricoClavesList(List<ADHistoricoClave> historicoClavesList) {
        this.historicoClavesList = historicoClavesList;
    }

    public ADHistoricoClave addADHistoricoClave(ADHistoricoClave ADHistoricoClave) {
        getHistoricoClavesList().add(ADHistoricoClave);
        ADHistoricoClave.setAdClave(this);
        return ADHistoricoClave;
    }

    public ADHistoricoClave removeADHistoricoClave(ADHistoricoClave ADHistoricoClave) {
        getHistoricoClavesList().remove(ADHistoricoClave);
        ADHistoricoClave.setAdClave(null);
        return ADHistoricoClave;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }
}
