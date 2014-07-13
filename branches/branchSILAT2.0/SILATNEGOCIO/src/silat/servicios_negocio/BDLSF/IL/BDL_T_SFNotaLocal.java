package silat.servicios_negocio.BDLSF.IL;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.trans.TRNota;

@Local
public interface BDL_T_SFNotaLocal {
    TRNota persistTRNota(TRNota TRNota);

    TRNota mergeTRNota(TRNota TRNota);

    void removeTRNota(TRNota TRNota);
}
