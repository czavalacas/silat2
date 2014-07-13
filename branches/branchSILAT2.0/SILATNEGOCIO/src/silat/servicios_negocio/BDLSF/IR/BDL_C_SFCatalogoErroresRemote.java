package silat.servicios_negocio.BDLSF.IR;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.entidades.audsis.STError;

@Remote
public interface BDL_C_SFCatalogoErroresRemote {
    List<STError> getSTErrorFindAll();
    STError getErrorByCodigo(String cidError);
    //BeanError getErroByCid(String cidError);
    List<BeanError> findAll();
}
