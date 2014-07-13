package silat.servicios_negocio.BDLSF.IR;

import java.math.BigDecimal;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.trans.TRItem;

@Remote
public interface BDL_C_SFItemRemote {
    TRItem findTRItemtById(BigDecimal id);
}
