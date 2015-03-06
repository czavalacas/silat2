package silat.servicios_negocio.BDLSF.IR;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.trans.TRCodigo;

@Remote
public interface BDL_C_SFCodigoSequenceRemoto {
    List<TRCodigo> getTRCodigoFindAll();
    TRCodigo findTRCodigoSequence(String id);
}
