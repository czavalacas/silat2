package silat.servicios_negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanChofer;

@Remote
public interface LN_C_SFChoferRemote {
    List<BeanChofer> findChofersByAttr_LN(Integer nidEmpresa,
                                          Integer nidChofer); 
    List<BeanChofer> traerChoferesPorEmpresa(Integer nidEmpresa);
}
