package silat.servicios_negocio.LNSF.IL;

import java.math.BigDecimal;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanRol;

@Local
public interface LN_T_SFRolLocal {
    BeanRol registrarRol(String descRol,
                         int tipEvento,
                         BigDecimal nidRol);
}
