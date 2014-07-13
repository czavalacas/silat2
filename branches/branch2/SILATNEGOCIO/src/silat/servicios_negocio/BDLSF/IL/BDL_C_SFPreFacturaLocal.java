package silat.servicios_negocio.BDLSF.IL;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanPreFactura;
import silat.servicios_negocio.entidades.trans.TrPreFactura;

@Local
public interface BDL_C_SFPreFacturaLocal {
    List<TrPreFactura> getTrPreFacturaFindAll();
    List<TrPreFactura> findTrPreFacturaByAttributes_BD(BeanPreFactura beanPreFactura);
    TrPreFactura findTrPreFacturaById(Long nidPrefact);
    BigDecimal[] call_Procedure_GET_PREFACTURA_MONTOS(Long nidPF);
}
