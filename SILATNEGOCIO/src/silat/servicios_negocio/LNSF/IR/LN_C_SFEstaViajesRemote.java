package silat.servicios_negocio.LNSF.IR;

import javax.ejb.Remote;
import java.util.Date;
import java.util.List;

import silat.servicios_negocio.Beans.BeanEstCliente;

@Remote
public interface LN_C_SFEstaViajesRemote {
    public List<BeanEstCliente> getViajesExitosos(Date fecMin, Date fecMax, int limitInf, int limitSup);
    public List<BeanEstCliente> getViajesMesGeneral(Date fecMin, Date fecMax);
    public List<BeanEstCliente> getViajesProvProp(Date fecMin, Date fecMax,int tipo);
}
