package silat.servicios_negocio.BDLSF.IR;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.admin.ADFlota;

@Remote
public interface BDL_T_SFFlotaRemote {
    ADFlota persistADFlota(ADFlota ADFlota);

    ADFlota mergeADFlota(ADFlota ADFlota);

    void removeADFlota(ADFlota ADFlota);

    List<ADFlota> getADFlotaFindAll();
}
