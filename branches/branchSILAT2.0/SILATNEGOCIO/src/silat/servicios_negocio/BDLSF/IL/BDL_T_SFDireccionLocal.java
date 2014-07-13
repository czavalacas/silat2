package silat.servicios_negocio.BDLSF.IL;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.admin.ADDireccion;

@Local
public interface BDL_T_SFDireccionLocal {
    ADDireccion persistADDireccion(ADDireccion ADDireccion);

    ADDireccion mergeADDireccion(ADDireccion ADDireccion);

    void removeADDireccion(ADDireccion ADDireccion);
}
