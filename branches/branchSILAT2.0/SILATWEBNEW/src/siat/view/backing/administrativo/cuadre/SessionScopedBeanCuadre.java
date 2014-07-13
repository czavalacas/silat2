package siat.view.backing.administrativo.cuadre;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;

import siat.view.backing.utiles.fecha.FechaUtiles;

import silat.servicios_negocio.Beans.BeanCuadre;

public class SessionScopedBeanCuadre {
    
    private List<BeanCuadre> lstBeanCuadre = new ArrayList<BeanCuadre>();
    private Date fecMin;
    private Date fecMax;
    private ChildPropertyTreeModel permisosTree;
    private int exec = 0;

    public void setLstBeanCuadre(List<BeanCuadre> lstBeanCuadre) {
        this.lstBeanCuadre = lstBeanCuadre;
    }

    public List<BeanCuadre> getLstBeanCuadre() {
        return lstBeanCuadre;
    }

    public void setFecMin(Date fecMin) {
        this.fecMin = fecMin;
    }

    public Date getFecMin() {
        return fecMin;
    }

    public void setFecMax(Date fecMax) {
        this.fecMax = fecMax;
    }

    public Date getFecMax() {
        return fecMax;
    }

    public void setPermisosTree(ChildPropertyTreeModel permisosTree) {
        this.permisosTree = permisosTree;
    }

    public ChildPropertyTreeModel getPermisosTree() {
        return permisosTree;
    }

    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }
}
