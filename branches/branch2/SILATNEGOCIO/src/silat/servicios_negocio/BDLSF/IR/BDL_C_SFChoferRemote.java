package silat.servicios_negocio.BDLSF.IR;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanChofer;
import silat.servicios_negocio.entidades.admin.ADChofer;

@Remote
public interface BDL_C_SFChoferRemote {
    List<ADChofer> getADChoferFindAll();
    int verificarLicenciaPlaca(String licencia);
    List<BeanChofer> findChoferesByAttributes(BeanChofer beanChofer);
}
