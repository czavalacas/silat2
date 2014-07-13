package silat.servicios_negocio.Beans;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.List;


public class BeanEmpresa implements Serializable{
    @SuppressWarnings("compatibility:5924326525754229152")
    private static final long serialVersionUID = 1L;
    private String cDireccion;
    private String cPagWeb;
    private String cRazonSocial;
    private String cRuc;
    private String cCerins;
    private BigDecimal nidParty;
    private String departamentos;
    private String provincias;
    private String distritos;
    private BeanADRelacionEmpresa relaEmpresa;
    private BeanParty adParty;
    private List<BeanFlota> listaFLotas;
    private List<BeanChofer> listaChofers;
    private BeanError error = new BeanError();
        
    public void setCDireccion(String cDireccion) {
        this.cDireccion = cDireccion;
    }

    public String getCDireccion() {
        return cDireccion;
    }

    public void setCPagWeb(String cPagWeb) {
        this.cPagWeb = cPagWeb;
    }

    public String getCPagWeb() {
        return cPagWeb;
    }

    public void setCRazonSocial(String cRazonSocial) {
        this.cRazonSocial = cRazonSocial;
    }

    public String getCRazonSocial() {
        return cRazonSocial;
    }

    public void setCRuc(String cRuc) {
        this.cRuc = cRuc;
    }

    public String getCRuc() {
        return cRuc;
    }

    public void setNidParty(BigDecimal nidParty) {
        this.nidParty = nidParty;
    }

    public BigDecimal getNidParty() {
        return nidParty;
    }

    public void setAdParty(BeanParty adParty) {
        this.adParty = adParty;
    }

    public BeanParty getAdParty() {
        return adParty;
    }

    public void setDepartamentos(String departamentos) {
        this.departamentos = departamentos;
    }

    public String getDepartamentos() {
        return departamentos;
    }

    public void setProvincias(String provincias) {
        this.provincias = provincias;
    }

    public String getProvincias() {
        return provincias;
    }

    public void setDistritos(String distritos) {
        this.distritos = distritos;
    }

    public String getDistritos() {
        return distritos;
    }

    public void setListaFLotas(List<BeanFlota> listaFLotas) {
        this.listaFLotas = listaFLotas;
    }

    public List<BeanFlota> getListaFLotas() {
        return listaFLotas;
    }

    public void setListaChofers(List<BeanChofer> listaChofers) {
        this.listaChofers = listaChofers;
    }

    public List<BeanChofer> getListaChofers() {
        return listaChofers;
    }

    public void setCCerins(String cCerins) {
        this.cCerins = cCerins;
    }

    public String getCCerins() {
        return cCerins;
    }

    public void setRelaEmpresa(BeanADRelacionEmpresa relaEmpresa) {
        this.relaEmpresa = relaEmpresa;
    }

    public BeanADRelacionEmpresa getRelaEmpresa() {
        return relaEmpresa;
    }

    public void setError(BeanError error) {
        this.error = error;
    }

    public BeanError getError() {
        return error;
    }
}
