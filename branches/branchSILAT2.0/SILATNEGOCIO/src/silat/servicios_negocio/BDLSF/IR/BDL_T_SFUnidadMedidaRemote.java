package silat.servicios_negocio.BDLSF.IR;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.trans.TRUnidadMedida;

@Remote
public interface BDL_T_SFUnidadMedidaRemote {
    TRUnidadMedida persistTRUnidadMedida(TRUnidadMedida TRUnidadMedida);

    TRUnidadMedida mergeTRUnidadMedida(TRUnidadMedida TRUnidadMedida);

    void removeTRUnidadMedida(TRUnidadMedida TRUnidadMedida);
}
