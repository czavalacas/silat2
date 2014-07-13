package silat.servicios_negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanChofer;

@Local
public interface LN_C_SFChoferLocal {
    List<BeanChofer> findChofersByAttr_LN(Integer nidEmpresa,
                                          Integer nidChofer);
    List<BeanChofer> traerChoferesPorEmpresa(Integer nidEmpresa);
}
