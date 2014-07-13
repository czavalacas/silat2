package silat.servicios_negocio.BDLSF.IR;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.admin.ADTipoGasto;

@Remote
public interface BDL_T_SFTipoGastoRemote {
    ADTipoGasto persistADTipoGasto(ADTipoGasto ADTipoGasto);

    ADTipoGasto mergeADTipoGasto(ADTipoGasto ADTipoGasto);

    void removeADTipoGasto(ADTipoGasto ADTipoGasto);
}
