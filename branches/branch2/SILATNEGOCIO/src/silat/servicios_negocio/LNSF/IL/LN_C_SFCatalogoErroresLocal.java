package silat.servicios_negocio.LNSF.IL;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanError;

@Local
public interface LN_C_SFCatalogoErroresLocal {
    
    BeanError getCatalogoErrores(String cidError);
}
