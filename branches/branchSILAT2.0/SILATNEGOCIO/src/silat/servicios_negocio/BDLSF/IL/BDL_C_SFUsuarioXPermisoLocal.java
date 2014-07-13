package silat.servicios_negocio.BDLSF.IL;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.admin.ADUsuarioXPermiso;

@Local
public interface BDL_C_SFUsuarioXPermisoLocal {
    List<ADUsuarioXPermiso> getADUsuarioXPermisoFindAll();
    List<BigDecimal> getPermisosByUsuario();
    List<BigDecimal> getRolesByUsuario(BigDecimal nidUsuario);
    List<ADUsuarioXPermiso> getPermisosPorUsuario(BigDecimal nidUsuario);
}