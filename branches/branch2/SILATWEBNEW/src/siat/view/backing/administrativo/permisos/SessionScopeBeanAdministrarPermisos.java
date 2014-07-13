package siat.view.backing.administrativo.permisos;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;

import silat.servicios_negocio.Beans.BeanPermisos;
import silat.servicios_negocio.Beans.BeanUsuarioAutenticado;

public class SessionScopeBeanAdministrarPermisos {
    private List<BeanPermisos> listPermisos = new ArrayList<BeanPermisos>();
    private List<BeanPermisos> listPermisosAll = new ArrayList<BeanPermisos>();
    private List<BigDecimal> permisosUser = new ArrayList<BigDecimal>();
    private BeanPermisos beanPermiso = new BeanPermisos();
    private ChildPropertyTreeModel permisosTree;
    private BigDecimal nidPermVCE;
    private List<BeanUsuarioAutenticado> listUsuarios = new ArrayList<BeanUsuarioAutenticado>();
    private BeanUsuarioAutenticado beanUsuarioAutenticado = new BeanUsuarioAutenticado();
    private BigDecimal nidRol;
    private BigDecimal nidUsuario;
    private String cUsuario;

    public List<BeanPermisos> getPermisosTodos() {
        List<BeanPermisos> perm = new ArrayList<BeanPermisos>();
        for (int i = 0; i < listPermisosAll.size(); i++) {
            BeanPermisos permi = listPermisosAll.get(i);
            perm.add(permi);
            if (permi.getListaHijos() != null) {
                if (permi.getListaHijos().size() > 0) {
                    perm = setHijo(perm, permi.getListaHijos());
                }
            }
        }
        return perm;
    }

    public List<BeanPermisos> setHijo(List<BeanPermisos> perm, List<BeanPermisos> hijos) {
        if (hijos != null) {
            if (hijos.size() > 0) {
                for (int i = 0; i < hijos.size(); i++) {
                    perm.add(hijos.get(i));
                    perm = setHijo(perm,hijos.get(i).getListaHijos());
                }
            }
        }
        return perm;
    }

    public void setListPermisos(List<BeanPermisos> listPermisos) {
        this.listPermisos = listPermisos;
    }

    public List<BeanPermisos> getListPermisos() {
        return listPermisos;
    }

    public void setBeanPermiso(BeanPermisos beanPermiso) {
        this.beanPermiso = beanPermiso;
    }

    public BeanPermisos getBeanPermiso() {
        return beanPermiso;
    }
 

    public void setPermisosTree(ChildPropertyTreeModel permisosTree) {
        this.permisosTree = permisosTree;
    }

    public ChildPropertyTreeModel getPermisosTree() {
        return permisosTree;
    }

    public void setPermisosUser(List<BigDecimal> permisosUser) {
        this.permisosUser = permisosUser;
    }

    public List<BigDecimal> getPermisosUser() {
        return permisosUser;
    }

    public void setNidPermVCE(BigDecimal nidPermVCE) {
        this.nidPermVCE = nidPermVCE;
    }

    public BigDecimal getNidPermVCE() {
        return nidPermVCE;
    }

    public void setListPermisosAll(List<BeanPermisos> listPermisosAll) {
        this.listPermisosAll = listPermisosAll;
    }

    public List<BeanPermisos> getListPermisosAll() {
        return listPermisosAll;
    }

    public void setListUsuarios(List<BeanUsuarioAutenticado> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public List<BeanUsuarioAutenticado> getListUsuarios() {
        return listUsuarios;
    }

    public void setBeanUsuarioAutenticado(BeanUsuarioAutenticado beanUsuarioAutenticado) {
        this.beanUsuarioAutenticado = beanUsuarioAutenticado;
    }

    public BeanUsuarioAutenticado getBeanUsuarioAutenticado() {
        return beanUsuarioAutenticado;
    }

    public void setNidRol(BigDecimal nidRol) {
        this.nidRol = nidRol;
    }

    public BigDecimal getNidRol() {
        return nidRol;
    }

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
}
