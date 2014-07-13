package silat.servicios_negocio.BDLSF.IL;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.admin.ADTipoGasto;

@Local
public interface BDL_T_SFTipoGastoLocal {
    ADTipoGasto persistADTipoGasto(ADTipoGasto ADTipoGasto);

    ADTipoGasto mergeADTipoGasto(ADTipoGasto ADTipoGasto);

    void removeADTipoGasto(ADTipoGasto ADTipoGasto);
}
