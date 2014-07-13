package silat.servicios_negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanGasto;

@Local
public interface LN_C_SFGastosLocal {
    List<BeanGasto> getGastosByAttributes(BeanGasto beanGasto);
    int cantGastosByFlota(int nidFlota);
}