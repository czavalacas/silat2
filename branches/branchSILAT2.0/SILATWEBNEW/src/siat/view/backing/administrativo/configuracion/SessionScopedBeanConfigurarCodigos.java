package siat.view.backing.administrativo.configuracion;

import java.util.ArrayList;
import java.util.List;

import silat.servicios_negocio.Beans.BeanCodigo;

public class SessionScopedBeanConfigurarCodigos {
    private List listaUNs = new ArrayList();
    private String codUN;
    private int exec = 0;
    private List<BeanCodigo> lstCodigos = new ArrayList<BeanCodigo>();
    private String codigo;
    private String doc;

    public void setListaUNs(List listaUNs) {
        this.listaUNs = listaUNs;
    } 

    public List getListaUNs() {
        return listaUNs;
    }

    public void setCodUN(String codUN) {
        this.codUN = codUN;
    }

    public String getCodUN() {
        return codUN;
    }

    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }

    public void setLstCodigos(List<BeanCodigo> lstCodigos) {
        this.lstCodigos = lstCodigos;
    }

    public List<BeanCodigo> getLstCodigos() {
        return lstCodigos;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getDoc() {
        return doc;
    }
}
