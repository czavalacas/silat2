package silat.servicios_negocio.LNSF.IL;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanEstCliente;

@Local
public interface LN_C_SFEstaClienteLocal {
    public List<BeanEstCliente> getGanaciasGeneralCliente(Date fecMin, Date fecMax, int limit);
    public List<BeanEstCliente> getGanaciasXMESCliente(Date fecMin, Date fecMax);
    public List<BeanEstCliente> getOrdenesServicioXCLIENTE(Date fecMin, Date fecMax, int limit, String tipo);
    public List<BeanEstCliente> getOrdenesXMeses(Date fecMin, Date fecMax, String tipo);
}
