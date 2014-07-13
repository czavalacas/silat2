package silat.servicios_negocio.BDLSF.IL;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.admin.ADPermiso;

@Local
public interface BDL_C_SFPermisosLocal {
    List<ADPermiso> getADPermisoFindAll();
    public List<ADPermiso> getNodosByNivel(int nivel);
    
    /*
     * @author dfloresgonz
     * @since 28.03.2013
     * @see silat.servicios_negocio.BDLSF
     */
    public int getNiveles();
    public List<ADPermiso> getHijosByPadre(BigDecimal nidPadre,
                                           String nombUser,
                                           BigDecimal nidRol);
    public ADPermiso getByNidPermiso(BigDecimal nidPermiso);
    public boolean isNodoFinalSFLBD(BigDecimal nidPermiso);
    List<ADPermiso> getHijosByPadreAll(BigDecimal nidPadre);
}
