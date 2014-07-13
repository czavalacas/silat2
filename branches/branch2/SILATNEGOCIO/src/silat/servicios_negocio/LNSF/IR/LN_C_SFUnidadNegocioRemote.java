package silat.servicios_negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanUnidadMedida;
import silat.servicios_negocio.Beans.BeanUnidadNegocio;

@Remote
public interface LN_C_SFUnidadNegocioRemote {
    List<BeanUnidadNegocio> getUnidadesNegocio_LN();
}
