package silat.servicios_negocio.LNSF.IL;

import java.math.BigDecimal;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanError;

@Local
public interface LN_T_SFEmpresaLocal {
    BeanError borrarEmpresa(BigDecimal nidEmpresa);
}
