package silat.servicios_negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanGasto;

@Remote
public interface LN_C_SFGastosRemoto {
    List<BeanGasto> getGastosByAttributes(BeanGasto beanGasto);
    int cantGastosByFlota(int nidFlota);
}