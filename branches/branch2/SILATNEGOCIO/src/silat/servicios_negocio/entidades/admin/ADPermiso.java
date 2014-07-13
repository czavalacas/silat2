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

import silat.servicios_negocio.entidades.audsis.STRolXPermiso;

@Entity
@NamedQueries( { @NamedQuery(name = "ADPermiso.findAll", query = "select o from ADPermiso o") })
@Table(name = "ADMPERM")
public class ADPermiso implements Serializable {
    @Column(name = "C_DESC_PERMISO", nullable = false, length = 30)
    private String cDescPermiso;
    @Column(name = "C_ICONO", length = 400)
    private String cIcono;
    @Column(name = "C_IND_FLD", nullable = false, length = 1)
    private String cIndFld;
    @Column(name = "C_NOMBRE_OBJ", length = 60)
    private String cNombreObj;
    @Column(name = "C_URL", length = 400)
    private String cUrl;
    @Column(name = "N_ESTADO_PERMISO", nullable = false)
    private BigDecimal nEstadoPermiso;
    @Column(name = "N_MENU_PADRE", nullable = false)
    private BigDecimal nMenuPadre;
    @Column(name = "N_NIVEL")
    private BigDecimal nNivel;
    @Id
    @Column(name = "NID_PERMISO", nullable = false)
    private BigDecimal nidPermiso;
    @OneToMany(mappedBy = "adPermiso")
    private List<STRolXPermiso> rolXPermisosList;
    @Column(name = "HABILIDAD", length = 1)
    private String habilidad;

    public ADPermiso() {
    }

    public ADPermiso(String cDescPermiso, String cIcono, String cIndFld, String cNombreObj, String cUrl,
                     BigDecimal nEstadoPermiso, BigDecimal nMenuPadre, BigDecimal nNivel, BigDecimal nidPermiso) {
        this.cDescPermiso = cDescPermiso;
        this.cIcono = cIcono;
        this.cIndFld = cIndFld;
        this.cNombreObj = cNombreObj;
        this.cUrl = cUrl;
        this.nEstadoPermiso = nEstadoPermiso;
        this.nMenuPadre = nMenuPadre;
        this.nNivel = nNivel;
        this.nidPermiso = nidPermiso;
    }

    public String getCDescPermiso() {
        return cDescPermiso;
    }

    public void setCDescPermiso(String cDescPermiso) {
        this.cDescPermiso = cDescPermiso;
    }

    public String getCIcono() {
        return cIcono;
    }

    public void setCIcono(String cIcono) {
        this.cIcono = cIcono;
    }

    public String getCIndFld() {
        return cIndFld;
    }

    public void setCIndFld(String cIndFld) {
        this.cIndFld = cIndFld;
    }

    public String getCNombreObj() {
        return cNombreObj;
    }

    public void setCNombreObj(String cNombreObj) {
        this.cNombreObj = cNombreObj;
    }

    public String getCUrl() {
        return cUrl;
    }

    public void setCUrl(String cUrl) {
        this.cUrl = cUrl;
    }

    public BigDecimal getNEstadoPermiso() {
        return nEstadoPermiso;
    }

    public void setNEstadoPermiso(BigDecimal nEstadoPermiso) {
        this.nEstadoPermiso = nEstadoPermiso;
    }

    public BigDecimal getNMenuPadre() {
        return nMenuPadre;
    }

    public void setNMenuPadre(BigDecimal nMenuPadre) {
        this.nMenuPadre = nMenuPadre;
    }

    public BigDecimal getNNivel() {
        return nNivel;
    }

    public void setNNivel(BigDecimal nNivel) {
        this.nNivel = nNivel;
    }

    public BigDecimal getNidPermiso() {
        return nidPermiso;
    }

    public void setNidPermiso(BigDecimal nidPermiso) {
        this.nidPermiso = nidPermiso;
    }

    public List<STRolXPermiso> getRolXPermisosList() {
        return rolXPermisosList;
    }

    public void setRolXPermisosList(List<STRolXPermiso> rolXPermisosList) {
        this.rolXPermisosList = rolXPermisosList;
    }

    public STRolXPermiso addSTRolXPermiso(STRolXPermiso STRolXPermiso) {
        getRolXPermisosList().add(STRolXPermiso);
        STRolXPermiso.setAdPermiso(this);
        return STRolXPermiso;
    }

    public STRolXPermiso removeSTRolXPermiso(STRolXPermiso STRolXPermiso) {
        getRolXPermisosList().remove(STRolXPermiso);
        STRolXPermiso.setAdPermiso(null);
        return STRolXPermiso;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    public String getHabilidad() {
        return habilidad;
    }
}
