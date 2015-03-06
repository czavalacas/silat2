package silat.servicios_negocio.BDLSF.IR;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.trans.TRCodigo;

@Remote
public interface BDL_T_SFCodigoSequenceRemoto {
    TRCodigo persistTRCodigo(TRCodigo TRCodigo);

    TRCodigo mergeTRCodigo(TRCodigo TRCodigo);

    void removeTRCodigo(TRCodigo TRCodigo);
}
