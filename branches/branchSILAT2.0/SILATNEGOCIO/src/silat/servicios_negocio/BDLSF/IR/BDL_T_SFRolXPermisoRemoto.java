package silat.servicios_negocio.BDLSF.IR;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.audsis.STRolXPermiso;

@Remote
public interface BDL_T_SFRolXPermisoRemoto {
    STRolXPermiso persistSTRolXPermiso(STRolXPermiso STRolXPermiso);

    STRolXPermiso mergeSTRolXPermiso(STRolXPermiso STRolXPermiso);

    void removeSTRolXPermiso(STRolXPermiso STRolXPermiso);
}
