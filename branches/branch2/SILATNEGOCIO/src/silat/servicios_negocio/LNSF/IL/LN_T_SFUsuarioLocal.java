package silat.servicios_negocio.LNSF.IL;

import java.math.BigDecimal;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanUsuario;

@Local
public interface LN_T_SFUsuarioLocal {
    BeanUsuario registrarUsuario(BeanUsuario beanUser);
    BeanUsuario desactivarUsuario(BigDecimal nidUsuario, int est);
    BeanUsuario actualizarUsuario(BeanUsuario beanUser);
}
