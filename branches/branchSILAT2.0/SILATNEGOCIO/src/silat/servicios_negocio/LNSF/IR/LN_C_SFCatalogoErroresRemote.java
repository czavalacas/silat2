package silat.servicios_negocio.LNSF.IR;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanError;

@Remote
public interface LN_C_SFCatalogoErroresRemote {
    
    BeanError getCatalogoErrores(String cidError);
}
