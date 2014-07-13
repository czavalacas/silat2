package silat.servicios_negocio.BDLSF.IL;

import java.math.BigDecimal;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.admin.ADUsuarioXPermiso;

@Local
public interface BDL_T_SFUsuarioXPermisoLocal {
    ADUsuarioXPermiso persistADUsuarioXPermiso(ADUsuarioXPermiso ADUsuarioXPermiso);

    ADUsuarioXPermiso mergeADUsuarioXPermiso(ADUsuarioXPermiso ADUsuarioXPermiso);

    void removeADUsuarioXPermiso(ADUsuarioXPermiso ADUsuarioXPermiso);
    
    ADUsuarioXPermiso borrarUsuarioXPermiso(BigDecimal nidUsuario);
    
}
