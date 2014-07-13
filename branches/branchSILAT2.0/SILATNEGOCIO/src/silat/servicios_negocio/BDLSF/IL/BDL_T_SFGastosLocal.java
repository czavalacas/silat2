package silat.servicios_negocio.BDLSF.IL;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.admin.ADGasto;

@Local
public interface BDL_T_SFGastosLocal {
    ADGasto persistADGasto(ADGasto ADGasto);

    ADGasto mergeADGasto(ADGasto ADGasto);

    void removeADGasto(ADGasto ADGasto);
    
    ADGasto findADGastoById(Long id);
}
