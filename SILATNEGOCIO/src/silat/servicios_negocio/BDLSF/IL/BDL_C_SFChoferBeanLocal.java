package silat.servicios_negocio.BDLSF.IL;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanChofer;
import silat.servicios_negocio.entidades.admin.ADChofer;

@Local
public interface BDL_C_SFChoferBeanLocal {
    List<ADChofer> getChoferesPorEmpresa(Integer nidEmpresa);
    List<ADChofer> getADChoferFindAll();
    ADChofer getChoferById(Integer nidChofer);
}
