package silat.servicios_negocio.BDLSF.IL;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.audsis.STRolXPermiso;

@Local
public interface BDL_C_SFRolXPermisoLocal {
    List<STRolXPermiso> getSTRolXPermisoFindAll();    
    int verificarExisteRolPorPermiso(BigDecimal nidRol, BigDecimal nidPermiso);
}
