package silat.servicios_negocio.BDLSF.IL;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.trans.TRCodigo;

@Local
public interface BDL_C_SFCodigoSequenceLocal {
    List<TRCodigo> getTRCodigoFindAll();
    TRCodigo findTRCodigoSequence(String id);
}
