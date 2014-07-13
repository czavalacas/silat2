package silat.servicios_negocio.BDLSF.IR;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.admin.ADPermiso;

@Remote
public interface BDL_C_SFPermisosRemote {
    List<ADPermiso> getADPermisoFindAll();
    List<ADPermiso> getNodosByNivel(int nivel);
    int getNiveles();
    List<ADPermiso> getHijosByPadre(BigDecimal nidPadre,
                                    String nombUser,
                                    BigDecimal nidRol);
    ADPermiso getByNidPermiso(BigDecimal nidPermiso);
    boolean isNodoFinalSFLBD(BigDecimal nidPermiso);
    List<ADPermiso> getHijosByPadreAll(BigDecimal nidPadre);
}