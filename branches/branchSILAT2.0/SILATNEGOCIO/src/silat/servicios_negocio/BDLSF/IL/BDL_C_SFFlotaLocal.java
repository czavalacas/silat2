package silat.servicios_negocio.BDLSF.IL;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanFlota;
import silat.servicios_negocio.entidades.admin.ADFlota;

@Local
public interface BDL_C_SFFlotaLocal {
    List<BeanFlota> findFlotasByAttributes(BeanFlota beanFlota);
    int verificarExistePlaca(String placa);
    List<ADFlota> getFlotasPorEmpresa(Integer nidEmpresa);
    ADFlota findADFlotaById(Integer id);
}
