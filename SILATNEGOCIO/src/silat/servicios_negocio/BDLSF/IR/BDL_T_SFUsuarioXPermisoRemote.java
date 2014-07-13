package silat.servicios_negocio.BDLSF.IR;

import java.math.BigDecimal;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.admin.ADUsuarioXPermiso;

@Remote
public interface BDL_T_SFUsuarioXPermisoRemote {
    ADUsuarioXPermiso persistADUsuarioXPermiso(ADUsuarioXPermiso ADUsuarioXPermiso);

    ADUsuarioXPermiso mergeADUsuarioXPermiso(ADUsuarioXPermiso ADUsuarioXPermiso);

    void removeADUsuarioXPermiso(ADUsuarioXPermiso ADUsuarioXPermiso);
    
    ADUsuarioXPermiso borrarUsuarioXPermiso(BigDecimal nidUsuario);
    
}
