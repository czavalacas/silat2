package silat.servicios_negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanUnidadMedida;

@Local
public interface LN_C_SFUnidadMedidaLocal {
    List<BeanUnidadMedida> getUnidadesMedida_LN();
}
