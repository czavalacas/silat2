package silat.servicios_negocio.LNSF.IR;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanPreFactura;

@Remote
public interface LN_C_SFPreFacturaRemote {
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
