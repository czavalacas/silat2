package silat.servicios_negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanItemPreFactura;
import silat.servicios_negocio.Beans.BeanPreFactura;
import silat.servicios_negocio.entidades.trans.TrItemPreFactura;
import silat.servicios_negocio.entidades.trans.TrPreFactura;

@Local
public interface LN_T_SFPreFacturaLocal {
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
