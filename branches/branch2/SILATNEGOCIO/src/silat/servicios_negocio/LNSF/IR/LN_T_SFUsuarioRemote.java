package silat.servicios_negocio.LNSF.IR;

import java.math.BigDecimal;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanUsuario;

@Remote
public interface LN_T_SFUsuarioRemote {
    BeanUsuario registrarUsuario(BeanUsuario beanUser);
    BeanUsuario desactivarUsuario(BigDecimal nidUsuario, int est);
    BeanUsuario actualizarUsuario(BeanUsuario beanUser);
}
