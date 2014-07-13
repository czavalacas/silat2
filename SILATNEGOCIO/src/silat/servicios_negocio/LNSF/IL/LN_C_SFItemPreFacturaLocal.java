package silat.servicios_negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanItemPreFactura;
import silat.servicios_negocio.entidades.trans.TrItemPreFactura;

@Local
public interface LN_C_SFItemPreFacturaLocal {
    List<BeanItemPreFactura> findBeanItemPreFactura_LN(Long nidPF);
    void borrarItems(List<BeanItemPreFactura> lstNew,List<TrItemPreFactura> lstBD);
}
