package silat.servicios_negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanItemPreFactura;
import silat.servicios_negocio.entidades.trans.TrItemPreFactura;

@Remote
public interface LN_C_SFItemPreFacturaRemote {
    List<BeanItemPreFactura> findBeanItemPreFactura_LN(Long nidPF);
    void borrarItems(List<BeanItemPreFactura> lstNew,List<TrItemPreFactura> lstBD);
}
