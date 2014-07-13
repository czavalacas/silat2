package silat.servicios_negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanUnidadMedida;
import silat.servicios_negocio.Beans.BeanUnidadNegocio;

@Local
public interface LN_C_SFUnidadNegocioLocal {
    List<BeanUnidadNegocio> getUnidadesNegocio_LN();
}
