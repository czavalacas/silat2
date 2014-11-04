package silat.servicios_negocio.LNSF.IR;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanEstCliente;

@Remote
public interface LN_C_SFEstaClienteRemote {
    public List<BeanEstCliente> getGanaciasGeneralCliente(Date fecMin, Date fecMax, int limit);
    public List<BeanEstCliente> getGanaciasXMESCliente(Date fecMin, Date fecMax);
    public List<BeanEstCliente> getOrdenesServicioXCLIENTE(Date fecMin, Date fecMax, int limit, String tipo);
    public List<BeanEstCliente> getOrdenesXMeses(Date fecMin, Date fecMax, String tipo);
}
