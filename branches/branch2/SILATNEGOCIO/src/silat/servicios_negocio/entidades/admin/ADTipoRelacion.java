package silat.servicios_negocio.entidades.admin;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries( { @NamedQuery(name = "Admtire.findAll", query = "select o from ADTipoRelacion o") })
@Table(name="ADMTIRE")
public class ADTipoRelacion implements Serializable {
    @Column(name = "C_DESTIP", nullable = false, length = 60)
    private String descripcionTipoRelacion;
    @Column(name = "C_ESTREG", nullable = false, length = 1)
    private String estadoRegistro;
    @Id
    @Column(name = "NID_TIRE", nullable = false)
    private Integer nidTipoRelacion;
    @OneToMany(mappedBy = "adTipoRelacion")
    private List<ADRelacionEmpresa> relacionEmpresaList1;
    @OneToMany(mappedBy = "adTipoRelacion")
    private List<ADRelacionEmpresa> relacionEmpresaList2;

    public ADTipoRelacion() {
    }

    public ADTipoRelacion(String descripcionTipoRelacion, String estadoRegistro, Integer nidTipoRelacion) {
        this.descripcionTipoRelacion = descripcionTipoRelacion;
        this.estadoRegistro = estadoRegistro;
        this.nidTipoRelacion = nidTipoRelacion;
    }

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
}
