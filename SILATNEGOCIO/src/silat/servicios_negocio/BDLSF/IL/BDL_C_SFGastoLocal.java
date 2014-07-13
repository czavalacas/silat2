package silat.servicios_negocio.BDLSF.IL;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanGasto;
import silat.servicios_negocio.entidades.admin.ADGasto;

@Local
public interface BDL_C_SFGastoLocal {
    List<ADGasto> getAddgastFindAll();
    List<ADGasto> findGastosByAttributes(BeanGasto beanGasto);
    int cantGastosByFlota(int nidFlota);
}
