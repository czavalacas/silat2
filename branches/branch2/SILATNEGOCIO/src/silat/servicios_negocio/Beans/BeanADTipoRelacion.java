package silat.servicios_negocio.Beans;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import silat.servicios_negocio.entidades.admin.ADRelacionEmpresa;

public class BeanADTipoRelacion implements Serializable {
    @SuppressWarnings("compatibility:-8558577149125820270")
    private static final long serialVersionUID = 1L;
    
    private String descripcionTipoRelacion;
    private String estadoRegistro;
    private Integer nidTipoRelacion;
    private List<ADRelacionEmpresa> relacionEmpresaList_1;
    private List<ADRelacionEmpresa> relacionEmpresaList_2;

    public void setDescripcionTipoRelacion(String descripcionTipoRelacion) {
        this.descripcionTipoRelacion = descripcionTipoRelacion;
    }

    public String getDescripcionTipoRelacion() {
        return descripcionTipoRelacion;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setNidTipoRelacion(Integer nidTipoRelacion) {
        this.nidTipoRelacion = nidTipoRelacion;
    }

    public Integer getNidTipoRelacion() {
        return nidTipoRelacion;
    }

    public void setRelacionEmpresaList_1(List<ADRelacionEmpresa> relacionEmpresaList_1) {
        this.relacionEmpresaList_1 = relacionEmpresaList_1;
    }

    public List<ADRelacionEmpresa> getRelacionEmpresaList_1() {
        return relacionEmpresaList_1;
    }

    public void setRelacionEmpresaList_2(List<ADRelacionEmpresa> relacionEmpresaList_2) {
        this.relacionEmpresaList_2 = relacionEmpresaList_2;
    }

    public List<ADRelacionEmpresa> getRelacionEmpresaList_2() {
        return relacionEmpresaList_2;
    }
}
