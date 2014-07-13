package silat.servicios_negocio.BDLSF.IR;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanUnidadMedida;
import silat.servicios_negocio.entidades.trans.TRUnidadMedida;

@Remote
public interface BDL_C_SFUnidadMedidaRemote {
    List<TRUnidadMedida> getTRUnidadMedidaFindAll();
    List<BeanUnidadMedida> getUnidadesMedida_BDL();
}
