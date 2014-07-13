package silat.servicios_negocio.BDLSF.IR;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.admin.ADUsuarioXPermiso;

@Remote
public interface BDL_C_SFUsuarioXPermisoRemote {
    List<ADUsuarioXPermiso> getADUsuarioXPermisoFindAll();
    List<BigDecimal> getPermisosByUsuario();
    List<BigDecimal> getRolesByUsuario(BigDecimal nidUsuario);
    List<ADUsuarioXPermiso> getPermisosPorUsuario(BigDecimal nidUsuario);
}