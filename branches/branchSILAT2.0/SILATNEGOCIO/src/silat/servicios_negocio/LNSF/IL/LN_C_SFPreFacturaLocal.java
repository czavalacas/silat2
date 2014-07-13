package silat.servicios_negocio.LNSF.IL;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanPreFactura;

@Local
public interface LN_C_SFPreFacturaLocal {
    List<BeanPreFactura> findBeanPreFacturaByAttr_LN(String codpedido,
                                                     String flgFactura,
                                                     Date fecMin,
                                                     Date fecMax,
                                                     String cliente,
                                                     String cidguia,
                                                     Long nidPrefact);
    BigDecimal[] call_Procedure_GET_PREFACTURA_MONTOS_LN(Long nidPF);
    String getCodigoPedidoByPreFactura(Long nidPref);
}
