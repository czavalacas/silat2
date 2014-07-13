package silat.servicios_negocio.BDLSF.IR;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanChofer;
import silat.servicios_negocio.entidades.admin.ADChofer;

@Remote
public interface BDL_C_SFChoferBean {
    List<ADChofer> getChoferesPorEmpresa(Integer nidEmpresa);
    List<ADChofer> getADChoferFindAll();
    ADChofer getChoferById(Integer nidChofer);
}
