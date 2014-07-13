package silat.servicios_negocio.BDLSF.IL;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.admin.ADFlota;

@Local
public interface BDL_T_SFFlotaLocal {
    ADFlota persistADFlota(ADFlota ADFlota);

    ADFlota mergeADFlota(ADFlota ADFlota);

    void removeADFlota(ADFlota ADFlota);

    List<ADFlota> getADFlotaFindAll();
}
