package silat.servicios_negocio.LNSF.IL;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanCuadre;

@Local
public interface LN_C_SFCuadreLocal {
    List<BeanCuadre> getReporteCuadre(Date fecMin, Date fecMax);
}
