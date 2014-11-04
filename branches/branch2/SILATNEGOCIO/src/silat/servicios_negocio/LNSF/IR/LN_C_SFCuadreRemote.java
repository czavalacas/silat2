package silat.servicios_negocio.LNSF.IR;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanCuadre;
import silat.servicios_negocio.Beans.BeanEstCliente;

@Remote
public interface LN_C_SFCuadreRemote {
    List<BeanCuadre> getReporteCuadre(Date fecMin, Date fecMax);
    public List<BeanEstCliente> getGastosXMES(Date fecMin, Date fecMax);
    public List<BeanEstCliente> getGastosAdelantos(Date fecMin, Date fecMax);
    public List<BeanEstCliente> getGastosManifiestos(Date fecMin, Date fecMax);
    public List<BeanEstCliente> getIngresosFactura(Date fecMin, Date fecMax);
    public List<BeanEstCliente> getDiferencialNota(Date fecMin, Date fecMax, String tipo);
}
