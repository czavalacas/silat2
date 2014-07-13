package siat.view.backing.administrativo.usuarios;

import java.util.ArrayList;

import java.util.List;

import silat.servicios_negocio.Beans.BeanRol;

public class SessionScopeBeanAdminRoles {
    
    private int exec = 0;
    private String nombBoton;
    private boolean isNuevo = true;
    private List<BeanRol> roles = new ArrayList<BeanRol>();
    private BeanRol rolSelected = new BeanRol();

    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }

    public void setRoles(List<BeanRol> roles) {
        this.roles = roles;
    }

    public List<BeanRol> getRoles() {
        return roles;
    }

    public void setNombBoton(String nombBoton) {
        this.nombBoton = nombBoton;
    }

    public String getNombBoton() {
        return nombBoton;
    }

    public void setIsNuevo(boolean isNuevo) {
        this.isNuevo = isNuevo;
    }

    public boolean isIsNuevo() {
        return isNuevo;
    }

    public void setRolSelected(BeanRol rolSelected) {
        this.rolSelected = rolSelected;
    }

    public BeanRol getRolSelected() {
        return rolSelected;
    }
}
