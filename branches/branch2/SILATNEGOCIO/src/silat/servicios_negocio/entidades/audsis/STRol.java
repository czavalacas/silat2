package silat.servicios_negocio.entidades.audsis;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@NamedQueries( { @NamedQuery(name = "STRol.findAll", query = "select o from STRol o") })
@Table(name = "STMROLE")
public class STRol implements Serializable {
    @Column(name = "C_DESC_ROLE", nullable = false, length = 30)
    private String cDescRole;
    @Column(name = "N_ESTADO", nullable = false)
    private BigDecimal nEstado;
    @Id    
    @Column(name = "NID_ROLE", nullable = false)
    @TableGenerator( name = "strol", table = "codigo", pkColumnName = "APP_SEQ_NAME", pkColumnValue = "strol.nid_rol", valueColumnName = "APP_SEQ_VALUE", initialValue = 50, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "strol" )
    private BigDecimal nidRole;
    @OneToMany(mappedBy = "stRol")
    private List<STRolXPermiso> rolXPermisoList;

    public STRol() {
    }

    public STRol(String cDescRole, BigDecimal nEstado, BigDecimal nidRole) {
        this.cDescRole = cDescRole;
        this.nEstado = nEstado;
        this.nidRole = nidRole;
    }

    public String getCDescRole() {
        return cDescRole;
    }

    public void setCDescRole(String cDescRole) {
        this.cDescRole = cDescRole;
    }

    public BigDecimal getNEstado() {
        return nEstado;
    }

    public void setNEstado(BigDecimal nEstado) {
        this.nEstado = nEstado;
    }

    public BigDecimal getNidRole() {
        return nidRole;
    }

    public void setNidRole(BigDecimal nidRole) {
        this.nidRole = nidRole;
    }

    public List<STRolXPermiso> getRolXPermisoList() {
        return rolXPermisoList;
    }

    public void setRolXPermisoList(List<STRolXPermiso> rolXPermisoList) {
        this.rolXPermisoList = rolXPermisoList;
    }

    public STRolXPermiso addSTRolXPermiso(STRolXPermiso STRolXPermiso) {
        getRolXPermisoList().add(STRolXPermiso);
        STRolXPermiso.setStRol(this);
        return STRolXPermiso;
    }

    public STRolXPermiso removeSTRolXPermiso(STRolXPermiso STRolXPermiso) {
        getRolXPermisoList().remove(STRolXPermiso);
        STRolXPermiso.setStRol(null);
        return STRolXPermiso;
    }
}
