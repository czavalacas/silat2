package silat.servicios_negocio.BDLSF.IR;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.admin.ADUsuario;

@Remote
public interface BDL_T_SFUsuarioRemote {
    ADUsuario persistADUsuario(ADUsuario ADUsuario);

    ADUsuario mergeADUsuario(ADUsuario ADUsuario);

    void removeADUsuario(ADUsuario ADUsuario);
}
