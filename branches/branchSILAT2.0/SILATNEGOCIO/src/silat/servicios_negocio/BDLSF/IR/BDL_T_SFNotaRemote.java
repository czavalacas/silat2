package silat.servicios_negocio.BDLSF.IR;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.trans.TRNota;

@Remote
public interface BDL_T_SFNotaRemote {
    TRNota persistTRNota(TRNota TRNota);

    TRNota mergeTRNota(TRNota TRNota);

    void removeTRNota(TRNota TRNota);
}
