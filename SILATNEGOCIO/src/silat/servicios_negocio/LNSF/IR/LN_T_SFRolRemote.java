package silat.servicios_negocio.LNSF.IR;

import java.math.BigDecimal;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanRol;

@Remote
public interface LN_T_SFRolRemote {
    
    BeanRol registrarRol(String descRol,
                         int tipEvento,
                         BigDecimal nidRol);
}
