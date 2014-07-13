package silat.servicios_negocio.BDLSF.IL;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.entidades.audsis.STError;

@Local
public interface BDL_C_SFCatalogoErroresLocal {
    List<STError> getSTErrorFindAll();
    STError getErrorByCodigo(String cidError);
    //BeanError getErroByCid(String cidError);
    List<BeanError> findAll();
}
