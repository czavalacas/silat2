package silat.servicios_negocio.LNSF.IR;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanCuadre;

@Remote
public interface LN_C_SFCuadreRemote {
    List<BeanCuadre> getReporteCuadre(Date fecMin, Date fecMax);
}
