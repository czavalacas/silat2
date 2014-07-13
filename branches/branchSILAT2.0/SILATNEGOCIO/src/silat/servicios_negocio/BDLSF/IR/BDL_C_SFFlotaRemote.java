package silat.servicios_negocio.BDLSF.IR;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanFlota;
import silat.servicios_negocio.entidades.admin.ADFlota;

@Remote
public interface BDL_C_SFFlotaRemote {
    List<BeanFlota> findFlotasByAttributes(BeanFlota beanFlota);
    int verificarExistePlaca(String placa);
    List<ADFlota> getFlotasPorEmpresa(Integer nidEmpresa);
    ADFlota findADFlotaById(Integer id);
}
