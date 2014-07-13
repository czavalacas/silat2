package silat.servicios_negocio.Beans;

import java.io.Serializable;

import java.math.BigDecimal;

public class BeanDireccion implements Serializable {
    @SuppressWarnings("compatibility:4462604781267734032")
    private static final long serialVersionUID = 1L;
    
    private String cDireccion;
    private String cidUbigeo;
    private String departamento;
    private String provicia;
    private String distrito;
    private String descDepartamento;
    private String descProvincia;
    private String descDistrito;
    private Integer nidDireccion;
    private BeanEmpresa party;
    
    public BeanDireccion(String descDepartamento, String descProvincia, String descDistrito, String cDireccion){
        this.descDepartamento = descDepartamento;
        this.descProvincia = descProvincia;
        this.descDistrito = descDistrito;
        this.cDireccion = cDireccion;
    }

    public BeanDireccion(){
        
    }
    
    public void setCidUbigeo(String cidUbigeo) {
        this.cidUbigeo = cidUbigeo;
    }

    public String getCidUbigeo() {
        return cidUbigeo;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setProvicia(String provicia) {
        this.provicia = provicia;
    }

    public String getProvicia() {
        return provicia;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getDistrito() {
        return distrito;
    }

   

    public void setParty(BeanEmpresa party) {
        this.party = party;
    }

    public BeanEmpresa getParty() {
        return party;
    }

    public void setCDireccion(String cDireccion) {
        this.cDireccion = cDireccion;
    }

    public String getCDireccion() {
        return cDireccion;
    }

    public void setNidDireccion(Integer nidDireccion) {
        this.nidDireccion = nidDireccion;
    }

    public Integer getNidDireccion() {
        return nidDireccion;
    }

    public void setDescDepartamento(String descDepartamento) {
        this.descDepartamento = descDepartamento;
    }

    public String getDescDepartamento() {
        return descDepartamento;
    }

    public void setDescProvincia(String descProvincia) {
        this.descProvincia = descProvincia;
    }

    public String getDescProvincia() {
        return descProvincia;
    }

    public void setDescDistrito(String descDistrito) {
        this.descDistrito = descDistrito;
    }

    public String getDescDistrito() {
        return descDistrito;
    }
}
