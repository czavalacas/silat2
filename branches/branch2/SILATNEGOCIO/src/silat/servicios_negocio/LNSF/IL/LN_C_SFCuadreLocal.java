package silat.servicios_negocio.LNSF.IL;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanCuadre;
import silat.servicios_negocio.Beans.BeanEstCliente;

@Local
public interface LN_C_SFCuadreLocal {
    List<BeanCuadre> getReporteCuadre(Date fecMin, Date fecMax);
    public List<BeanEstCliente> getGastosXMES(Date fecMin, Date fecMax);
    public List<BeanEstCliente> getGastosAdelantos(Date fecMin, Date fecMax);
    public List<BeanEstCliente> getGastosManifiestos(Date fecMin, Date fecMax);
    public List<BeanEstCliente> getIngresosFactura(Date fecMin, Date fecMax);
    public List<BeanEstCliente> getDiferencialNota(Date fecMin, Date fecMax, String tipo);
}
