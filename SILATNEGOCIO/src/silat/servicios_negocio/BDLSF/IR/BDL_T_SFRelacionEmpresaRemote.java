package silat.servicios_negocio.BDLSF.IR;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.admin.ADRelacionEmpresa;

@Remote
public interface BDL_T_SFRelacionEmpresaRemote {
    ADRelacionEmpresa persistADRelacionEmpresa(ADRelacionEmpresa ADRelacionEmpresa);

    ADRelacionEmpresa mergeADRelacionEmpresa(ADRelacionEmpresa ADRelacionEmpresa);

    void removeADRelacionEmpresa(ADRelacionEmpresa ADRelacionEmpresa);
}
