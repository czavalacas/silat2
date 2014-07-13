package silat.servicios_negocio.BDLSF.IL;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanUnidadMedida;
import silat.servicios_negocio.entidades.trans.TRUnidadMedida;

@Local
public interface BDL_C_SFUnidadMedidaLocal {
    List<TRUnidadMedida> getTRUnidadMedidaFindAll();
    List<BeanUnidadMedida> getUnidadesMedida_BDL();
}
