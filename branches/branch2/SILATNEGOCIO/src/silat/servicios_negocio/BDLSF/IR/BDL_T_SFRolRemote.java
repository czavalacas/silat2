package silat.servicios_negocio.BDLSF.IR;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.audsis.STRol;

@Remote
public interface BDL_T_SFRolRemote {
    STRol persistSTRol(STRol STRol);

    STRol mergeSTRol(STRol STRol);

    void removeSTRol(STRol STRol);
}
