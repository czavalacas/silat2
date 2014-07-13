package silat.servicios_negocio.LNSF.IR;

import java.math.BigDecimal;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanError;

@Remote
public interface LN_T_SFEmpresaRemote {
    
    BeanError borrarEmpresa(BigDecimal nidEmpresa);
}
