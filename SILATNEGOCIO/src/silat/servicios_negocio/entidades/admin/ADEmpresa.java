package silat.servicios_negocio.entidades.admin;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import silat.servicios_negocio.entidades.audsis.TROrdenServicio;
import silat.servicios_negocio.entidades.trans.TRGuia;
import silat.servicios_negocio.entidades.trans.TRManifiesto;

@Entity
@NamedQueries( { @NamedQuery(name = "ADEmpresa.findAll", query = "select o from ADEmpresa o") })
@Table(name = "ADDEMPR")
public class ADEmpresa implements Serializable {
    @Column(name = "C_DIRECCION", length = 155)
    private String cDireccion;
    @Column(name = "C_CERINS", length = 30)
    private String cCerins;
    @Column(name = "C_PAG_WEB", length = 100)
    private String cPagWeb;
    @Column(name = "C_RAZON_SOCIAL", nullable = false, length = 100)
    private String cRazonSocial;
    @Column(name = "C_RUC", length = 11)
    private String cRuc;
    @Id
    @Column(name = "NID_PARTY", nullable = false, insertable = false, updatable = false)
    private BigDecimal nidParty;
    @OneToMany(mappedBy = "adEmpresa")
    private List<TROrdenServicio> orderServicioList;
    @OneToMany(mappedBy = "adEmpresa")
    private List<TRGuia> guiasList;
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "NID_PARTY")
    private ADParty adParty;
    @OneToMany(mappedBy = "trManifiesto")
    private List<TRManifiesto> manifiestosList;
    @OneToMany(mappedBy = "adEmpresa1",cascade={CascadeType.ALL})
    private List<ADRelacionEmpresa> relacionEmpresaList1;
    @OneToMany(mappedBy = "adEmpresa2",cascade={CascadeType.ALL})
    private List<ADRelacionEmpresa> relacionEmpresaList2;
    
    public ADEmpresa() {
    }

    public ADEmpresa(String cDireccion, String cPagWeb, String cRazonSocial, String cRuc, ADParty adParty) {
        this.cDireccion = cDireccion;
        this.cPagWeb = cPagWeb;
        this.cRazonSocial = cRazonSocial;
        this.cRuc = cRuc;
        this.adParty = adParty;
    }

    public String getCDireccion() {
        return cDireccion;
    }

    public void setCDireccion(String cDireccion) {
        this.cDireccion = cDireccion;
    }

    public String getCPagWeb() {
        return cPagWeb;
    }

    public void setCPagWeb(String cPagWeb) {
        this.cPagWeb = cPagWeb;
    }

    public String getCRazonSocial() {
        return cRazonSocial;
    }

    public void setCRazonSocial(String cRazonSocial) {
        this.cRazonSocial = cRazonSocial;
    }

    public String getCRuc() {
        return cRuc;
    }

    public void setCRuc(String cRuc) {
        this.cRuc = cRuc;
    }

    public BigDecimal getNidParty() {
        return nidParty;
    }

    public void setNidParty(BigDecimal nidParty) {
        this.nidParty = nidParty;
    }

    public List<TROrdenServicio> getOrderServicioList() {
        return orderServicioList;
    }

    public void setOrderServicioList(List<TROrdenServicio> orderServicioList) {
        this.orderServicioList = orderServicioList;
    }

    public TROrdenServicio addTROrdenServicio(TROrdenServicio TROrdenServicio) {
        getOrderServicioList().add(TROrdenServicio);
        TROrdenServicio.setAdEmpresa(this);
        return TROrdenServicio;
    }

    public TROrdenServicio removeTROrdenServicio(TROrdenServicio TROrdenServicio) {
        getOrderServicioList().remove(TROrdenServicio);
        TROrdenServicio.setAdEmpresa(null);
        return TROrdenServicio;
    }

    public List<TRGuia> getGuiasList() {
        return guiasList;
    }

    public void setGuiasList(List<TRGuia> guiasList) {
        this.guiasList = guiasList;
    }

    public TRGuia addTRGuia(TRGuia TRGuia) {
        getGuiasList().add(TRGuia);
        TRGuia.setAdEmpresa(this);
        return TRGuia;
    }

    public TRGuia removeTRGuia(TRGuia TRGuia) {
        getGuiasList().remove(TRGuia);
        TRGuia.setAdEmpresa(null);
        return TRGuia;
    }

    public ADParty getAdParty() {
        return adParty;
    }

    public void setAdParty(ADParty adParty) {
        this.adParty = adParty;
        if (adParty != null) {
            this.nidParty = adParty.getNidParty();
        }
    }

    public List<TRManifiesto> getManifiestosList() {
        return manifiestosList;
    }

    public void setManifiestosList(List<TRManifiesto> manifiestosList) {
        this.manifiestosList = manifiestosList;
    }

    public TRManifiesto addTRManifiesto(TRManifiesto TRManifiesto) {
        getManifiestosList().add(TRManifiesto);
        TRManifiesto.setTrManifiesto(this);
        return TRManifiesto;
    }

    public TRManifiesto removeTRManifiesto(TRManifiesto TRManifiesto) {
        getManifiestosList().remove(TRManifiesto);
        TRManifiesto.setTrManifiesto(null);
        return TRManifiesto;
    }

    public void setRelacionEmpresaList1(List<ADRelacionEmpresa> relacionEmpresaList1) {
        this.relacionEmpresaList1 = relacionEmpresaList1;
    }

    public List<ADRelacionEmpresa> getRelacionEmpresaList1() {
        return relacionEmpresaList1;
    }

    public void setRelacionEmpresaList2(List<ADRelacionEmpresa> relacionEmpresaList2) {
        this.relacionEmpresaList2 = relacionEmpresaList2;
    }

    public List<ADRelacionEmpresa> getRelacionEmpresaList2() {
        return relacionEmpresaList2;
    }

    public void setCCerins(String cCerins) {
        this.cCerins = cCerins;
    }

    public String getCCerins() {
        return cCerins;
    }
}
