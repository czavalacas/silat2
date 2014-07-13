package silat.servicios_negocio.LNSF.IL;

import java.math.BigDecimal;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanClave;

@Local
public interface LN_T_SFClaveLocal {
    BeanClave grabarNuevaContraseña(String cUsuario, String cClave, BigDecimal nidClave);
}
