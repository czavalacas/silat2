package silat.servicios_negocio.BDLSF.IL;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanChofer;
import silat.servicios_negocio.entidades.admin.ADChofer;

@Local
public interface BDL_C_SFChoferLocal {
    List<ADChofer> getADChoferFindAll();
    List<BeanChofer> findChoferesByAttributes(BeanChofer beanChofer);
    
}
