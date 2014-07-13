package silat.servicios_negocio.BDLSF.IR;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.admin.ADDireccion;

@Remote
public interface BDL_T_SFDireccionRemote {
    ADDireccion persistADDireccion(ADDireccion ADDireccion);

    ADDireccion mergeADDireccion(ADDireccion ADDireccion);

    void removeADDireccion(ADDireccion ADDireccion);
}
