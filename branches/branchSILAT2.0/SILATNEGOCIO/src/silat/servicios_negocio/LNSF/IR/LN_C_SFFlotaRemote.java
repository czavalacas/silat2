package silat.servicios_negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanFlota;

@Remote
public interface LN_C_SFFlotaRemote {
    List<BeanFlota> findFlotasByAttr_LN(Integer nidEmpresa,
                                        Integer nidFlota);
    List<BeanFlota> getFlotasPorEmpresa(Integer nidEmpresa);
}
