package silat.servicios_negocio.BDLSF.IL;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.audsis.STRolXPermiso;

@Local
public interface BDL_T_SFRolXPermisoLocal {
    STRolXPermiso persistSTRolXPermiso(STRolXPermiso STRolXPermiso);

    STRolXPermiso mergeSTRolXPermiso(STRolXPermiso STRolXPermiso);

    void removeSTRolXPermiso(STRolXPermiso STRolXPermiso);
}
