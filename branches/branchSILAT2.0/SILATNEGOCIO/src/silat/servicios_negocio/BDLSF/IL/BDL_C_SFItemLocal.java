package silat.servicios_negocio.BDLSF.IL;

import java.math.BigDecimal;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.trans.TRItem;

@Local
public interface BDL_C_SFItemLocal {
    TRItem findTRItemtById(BigDecimal id);
}
