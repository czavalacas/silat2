package silat.servicios_negocio.LNSF.IR;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanUnidadMedida;

@Remote
public interface LN_T_SFUnidadMedidaRemote {
    
    BeanUnidadMedida registrarUnidadMedida(String descUnidadMedida,
                                           String sigla);
}
