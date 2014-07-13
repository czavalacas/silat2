package silat.servicios_negocio.BDLSF.IR;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanPreFactura;
import silat.servicios_negocio.entidades.trans.TrPreFactura;

@Remote
public interface BDL_C_SFPreFacturaRemote {
    List<TrPreFactura> getTrPreFacturaFindAll();
    List<TrPreFactura> findTrPreFacturaByAttributes_BD(BeanPreFactura beanPreFactura);
    TrPreFactura findTrPreFacturaById(Long nidPrefact);
    BigDecimal[] call_Procedure_GET_PREFACTURA_MONTOS(Long nidPF);
}
