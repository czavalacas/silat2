package silat.servicios_negocio.BDLSF.IR;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanGasto;
import silat.servicios_negocio.entidades.admin.ADGasto;

@Remote
public interface BDL_C_SFGastoRemoto {
    List<ADGasto> getAddgastFindAll();
    List<ADGasto> findGastosByAttributes(BeanGasto beanGasto);
    int cantGastosByFlota(int nidFlota);
}
