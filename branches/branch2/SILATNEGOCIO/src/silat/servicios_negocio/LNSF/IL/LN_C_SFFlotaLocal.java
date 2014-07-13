package silat.servicios_negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanFlota;

@Local
public interface LN_C_SFFlotaLocal {
    List<BeanFlota> findFlotasByAttr_LN(Integer nidEmpresa,
                                        Integer nidFlota);
    List<BeanFlota> getFlotasPorEmpresa(Integer nidEmpresa);
}
