package silat.servicios_negocio.LNSF.IL;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanUnidadMedida;

@Local
public interface LN_T_SFUnidadMedidaLocal {
    BeanUnidadMedida registrarUnidadMedida(String descUnidadMedida,
                                           String sigla);
}
