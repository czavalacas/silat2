package silat.servicios_negocio.BDLSF.IL;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.trans.TRCodigo;

@Local
public interface BDL_T_SFCodigoSequenceLocal {
    TRCodigo persistTRCodigo(TRCodigo TRCodigo);

    TRCodigo mergeTRCodigo(TRCodigo TRCodigo);

    void removeTRCodigo(TRCodigo TRCodigo);
}
