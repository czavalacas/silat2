package silat.servicios_negocio.Beans;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

public class BeanUsuarioAutenticado implements Serializable{

    @SuppressWarnings("compatibility:2098412604673550780")
    private static final long serialVersionUID = 1L;
    private String cUsuario;
    private BigDecimal nidUsuario;
    private String cApellidos;
    private String cCargo;
    private String cNombres;
    private Date fecNac;
    private BigDecimal nidParty;
    private String cEmail;
    private String cTelf;
    private String cTipoParty;
    private String rol;
    private BigDecimal nidRol;
    private String output;
    private BeanPermisos permiso;
    private List<BigDecimal> lstPermisos;

    public void setCUsuario(String cUsuario) {
        this.cUsuario = cUsuario;
    }

    public String getCUsuario() {
        return cUsuario;
    }

    public void setNidUsuario(BigDecimal nidUsuario) {
        this.nidUsuario = nidUsuario;
    }

    public BigDecimal getNidUsuario() {
        return nidUsuario;
    }

    public void setCApellidos(String cApellidos) {
        this.cApellidos = cApellidos;
    }

    public String getCApellidos() {
        return cApellidos;
    }

    public void setCCargo(String cCargo) {
        this.cCargo = cCargo;
    }

    public String getCCargo() {
        return cCargo;
    }

    public void setCNombres(String cNombres) {
        this.cNombres = cNombres;
    }

    public String getCNombres() {
        return cNombres;
    }

    public void setFecNac(Date fecNac) {
        this.fecNac = fecNac;
    }

    public Date getFecNac() {
        return fecNac;
    }

    public void setNidParty(BigDecimal nidParty) {
        this.nidParty = nidParty;
    }

    public BigDecimal getNidParty() {
        return nidParty;
    }

    public void setCEmail(String cEmail) {
        this.cEmail = cEmail;
    }

    public String getCEmail() {
        return cEmail;
    }

    public void setCTelf(String cTelf) {
        this.cTelf = cTelf;
    }

    public String getCTelf() {
        return cTelf;
    }

    public void setCTipoParty(String cTipoParty) {
        this.cTipoParty = cTipoParty;
    }

    public String getCTipoParty() {
        return cTipoParty;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getOutput() {
        return output;
    }

    public void setPermiso(BeanPermisos permiso) {
        this.permiso = permiso;
    }

    public BeanPermisos getPermiso() {
        return permiso;
    }

    public void setLstPermisos(List<BigDecimal> lstPermisos) {
        this.lstPermisos = lstPermisos;
    }

    public List<BigDecimal> getLstPermisos() {
        return lstPermisos;
    }

    public void setNidRol(BigDecimal nidRol) {
        this.nidRol = nidRol;
    }

    public BigDecimal getNidRol() {
        return nidRol;
    }
}
