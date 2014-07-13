package silat.servicios_negocio.BDLSF.IL;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.audsis.STRol;

@Local
public interface BDL_T_SFRolLocal {
    STRol persistSTRol(STRol STRol);

    STRol mergeSTRol(STRol STRol);

    void removeSTRol(STRol STRol);
}
