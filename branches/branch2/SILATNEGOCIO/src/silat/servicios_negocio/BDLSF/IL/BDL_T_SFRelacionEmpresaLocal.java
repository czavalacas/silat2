package silat.servicios_negocio.BDLSF.IL;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.admin.ADRelacionEmpresa;

@Local
public interface BDL_T_SFRelacionEmpresaLocal {
    ADRelacionEmpresa persistADRelacionEmpresa(ADRelacionEmpresa ADRelacionEmpresa);

    ADRelacionEmpresa mergeADRelacionEmpresa(ADRelacionEmpresa ADRelacionEmpresa);

    void removeADRelacionEmpresa(ADRelacionEmpresa ADRelacionEmpresa);
}
