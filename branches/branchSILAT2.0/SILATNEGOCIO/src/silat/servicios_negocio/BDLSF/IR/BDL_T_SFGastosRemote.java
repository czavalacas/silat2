package silat.servicios_negocio.BDLSF.IR;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.admin.ADGasto;

@Remote
public interface BDL_T_SFGastosRemote {
    ADGasto persistADGasto(ADGasto ADGasto);

    ADGasto mergeADGasto(ADGasto ADGasto);

    void removeADGasto(ADGasto ADGasto);
    
    ADGasto findADGastoById(Long id);
}
