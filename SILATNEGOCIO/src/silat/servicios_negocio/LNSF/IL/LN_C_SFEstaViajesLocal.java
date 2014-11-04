package silat.servicios_negocio.LNSF.IL;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanEstCliente;

@Local
public interface LN_C_SFEstaViajesLocal {
    public List<BeanEstCliente> getViajesExitosos(Date fecMin, Date fecMax, int limitInf, int limitSup);
    public List<BeanEstCliente> getViajesMesGeneral(Date fecMin, Date fecMax);
    public List<BeanEstCliente> getViajesProvProp(Date fecMin, Date fecMax,int tipo);
}
