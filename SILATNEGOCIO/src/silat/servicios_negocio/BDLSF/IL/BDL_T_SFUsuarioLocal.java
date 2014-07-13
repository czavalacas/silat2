package silat.servicios_negocio.BDLSF.IL;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.admin.ADUsuario;

@Local
public interface BDL_T_SFUsuarioLocal {
    ADUsuario persistADUsuario(ADUsuario ADUsuario);

    ADUsuario mergeADUsuario(ADUsuario ADUsuario);

    void removeADUsuario(ADUsuario ADUsuario);
}
