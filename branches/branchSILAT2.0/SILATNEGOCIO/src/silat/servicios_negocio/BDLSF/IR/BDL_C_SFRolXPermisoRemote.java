package silat.servicios_negocio.BDLSF.IR;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.audsis.STRolXPermiso;

@Remote
public interface BDL_C_SFRolXPermisoRemote {
    List<STRolXPermiso> getSTRolXPermisoFindAll();
    int verificarExisteRolPorPermiso(BigDecimal nidRol, BigDecimal nidPermiso);
}
