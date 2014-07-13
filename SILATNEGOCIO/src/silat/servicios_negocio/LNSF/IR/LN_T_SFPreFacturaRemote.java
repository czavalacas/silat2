package silat.servicios_negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanItemPreFactura;
import silat.servicios_negocio.Beans.BeanPreFactura;
import silat.servicios_negocio.entidades.trans.TrItemPreFactura;
import silat.servicios_negocio.entidades.trans.TrPreFactura;

@Remote
public interface LN_T_SFPreFacturaRemote {
    BeanPreFactura registrarBeanPreFactura_LN(List<BeanItemPreFactura> lstItmsPreFactura,
                                              Long nidCliente,
                                              String codpedido,
                                              String cliente);
    List<TrItemPreFactura> lstBeanToEntidad(List<BeanItemPreFactura> lstItmsPreFactura,
                                            TrPreFactura ePreFactura);
    BeanPreFactura actualizarBeanPreFactura_LN(List<BeanItemPreFactura> lstItmsPreFactura,
                                              String codpedido,
                                              String cliente,
                                              Long nidPreFact);
    BeanPreFactura borrarPreFactura_LN(Long nidPreFact);
}
