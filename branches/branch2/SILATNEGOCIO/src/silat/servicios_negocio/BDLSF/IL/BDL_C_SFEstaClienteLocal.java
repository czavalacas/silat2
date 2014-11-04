package silat.servicios_negocio.BDLSF.IL;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanEstCliente;

@Local
public interface BDL_C_SFEstaClienteLocal {
    public List<BeanEstCliente> call_Procedure_GET_GANANCIAS_CLIENTE(Date fecMin, Date fecMax, int limit);
    public List<BeanEstCliente> call_Procedure_GET_GANANCIAS_CLIENTE_X_MES(Date fecMin, Date fecMax);
    public List<BeanEstCliente> call_Procedure_GET_ORDENES_SERVICIO(Date fecMin, Date fecMax, int limit, String tipo);
    public List<BeanEstCliente> call_Procedure_GET_ORDENES_SERVICIO_X_FECHAS(Date fecMin, Date fecMax, String tipo);
}
