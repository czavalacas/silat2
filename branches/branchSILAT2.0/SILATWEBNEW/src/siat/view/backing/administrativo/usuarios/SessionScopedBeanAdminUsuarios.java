package siat.view.backing.administrativo.usuarios;

import java.util.ArrayList;
import java.util.List;

import silat.servicios_negocio.Beans.BeanUsuario;

public class SessionScopedBeanAdminUsuarios {
    
    private int exec = 0;
    private List<BeanUsuario> lstUsuariosNoAdmin = new ArrayList<BeanUsuario>();
    private BeanUsuario beanUsuarioSelected = new BeanUsuario();
    private List listaRoles;
    private String descRol;
    private String nombreBtn;
    private int flgMod;
    private boolean modificarUser = false;
    private String nombreBtnReg;
    private boolean boolModificarUser = false;
    private String user;
    private String rol;

    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }

    public void setLstUsuariosNoAdmin(List<BeanUsuario> lstUsuariosNoAdmin) {
        this.lstUsuariosNoAdmin = lstUsuariosNoAdmin;
    }

    public List<BeanUsuario> getLstUsuariosNoAdmin() {
        return lstUsuariosNoAdmin;
    }

    public void setBeanUsuarioSelected(BeanUsuario beanUsuarioSelected) {
        this.beanUsuarioSelected = beanUsuarioSelected;
    }

    public BeanUsuario getBeanUsuarioSelected() {
        return beanUsuarioSelected;
    }

    public void setListaRoles(List listaRoles) {
        this.listaRoles = listaRoles;
    }

    public List getListaRoles() {
        return listaRoles;
    }

    public void setDescRol(String descRol) {
        this.descRol = descRol;
    }

    public String getDescRol() {
        return descRol;
    }

    public void setNombreBtn(String nombreBtn) {
        this.nombreBtn = nombreBtn;
    }

    public String getNombreBtn() {
        return nombreBtn;
    }

    public void setFlgMod(int flgMod) {
        this.flgMod = flgMod;
    }

    public int getFlgMod() {
        return flgMod;
    }

    public void setModificarUser(boolean modificarUser) {
        this.modificarUser = modificarUser;
    }

    public boolean isModificarUser() {
        return modificarUser;
    }

    public void setNombreBtnReg(String nombreBtnReg) {
        this.nombreBtnReg = nombreBtnReg;
    }

    public String getNombreBtnReg() {
        return nombreBtnReg;
    }

    public void setBoolModificarUser(boolean boolModificarUser) {
        this.boolModificarUser = boolModificarUser;
    }

    public boolean isBoolModificarUser() {
        return boolModificarUser;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }
}
