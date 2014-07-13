package silat.servicios_negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanUnidadMedida;

@Remote
public interface LN_C_SFUnidadMedidaRemote {
    List<BeanUnidadMedida> getUnidadesMedida_LN();
}
