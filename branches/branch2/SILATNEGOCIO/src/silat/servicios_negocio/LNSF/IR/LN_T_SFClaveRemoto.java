package silat.servicios_negocio.LNSF.IR;

import java.math.BigDecimal;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanClave;

@Remote
public interface LN_T_SFClaveRemoto {
    BeanClave grabarNuevaContrase�a(String cUsuario, String cClave, BigDecimal nidUsuario);
}
