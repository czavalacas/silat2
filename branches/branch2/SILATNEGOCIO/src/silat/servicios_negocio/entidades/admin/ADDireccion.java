package silat.servicios_negocio.entidades.admin;

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

@Entity
@NamedQueries( { @NamedQuery(name = "ADDireccion.findAll", query = "select o from ADDireccion o") })
@Table(name = "ADDDIRE")
public class ADDireccion implements Serializable {
    @Column(name = "C_DIRECCION", nullable = false, length = 200)
    private String cDireccion;
    @Column(name = "CID_UBIGEO", nullable = false, length = 6)
    private String cidUbigeo;
    @Id
    @SequenceGenerator(name = "GEN_SQ_ADDDIRE_01",sequenceName = "SQ_ADDDIRE_01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_SQ_ADDDIRE_01")    
    @Column(name = "NID_DIRECCION", nullable = false)
    private BigDecimal nidDireccion;
    @ManyToOne
    @JoinColumn(name = "NID_PARTY")
    private ADParty party;
    @ManyToOne
    @JoinColumn(name = "CID_UBIGEO", insertable=false, updatable=false)
    private ADUbigeo ubigeo;
    
    public ADDireccion() {
    }

    public ADDireccion(String cDireccion, String cidUbigeo, BigDecimal nidDireccion,ADParty party) {
        this.cDireccion = cDireccion;
        this.cidUbigeo = cidUbigeo;
        this.nidDireccion = nidDireccion;
        this.party = party;
    }

    public String getCDireccion() {
        return cDireccion;
    }

    public void setCDireccion(String cDireccion) {
        this.cDireccion = cDireccion;
    }

    public String getCidUbigeo() {
        return cidUbigeo;
    }

    public void setCidUbigeo(String cidUbigeo) {
        this.cidUbigeo = cidUbigeo;
    }

    public BigDecimal getNidDireccion() {
        return nidDireccion;
    }

    public void setNidDireccion(BigDecimal nidDireccion) {
        this.nidDireccion = nidDireccion;
    }

    public void setParty(ADParty party) {
        this.party = party;
    }

    public ADParty getParty() {
        return party;
    }

    public void setUbigeo(ADUbigeo ubigeo) {
        this.ubigeo = ubigeo;
    }

    public ADUbigeo getUbigeo() {
        return ubigeo;
    }
}
