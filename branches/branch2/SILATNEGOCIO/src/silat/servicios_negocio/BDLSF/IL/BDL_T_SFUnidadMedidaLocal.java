package silat.servicios_negocio.BDLSF.IL;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.trans.TRUnidadMedida;

@Local
public interface BDL_T_SFUnidadMedidaLocal {
    TRUnidadMedida persistTRUnidadMedida(TRUnidadMedida TRUnidadMedida);

    TRUnidadMedida mergeTRUnidadMedida(TRUnidadMedida TRUnidadMedida);

    void removeTRUnidadMedida(TRUnidadMedida TRUnidadMedida);
}
