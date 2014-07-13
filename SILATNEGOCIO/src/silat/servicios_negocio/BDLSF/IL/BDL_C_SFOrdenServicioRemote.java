package silat.servicios_negocio.BDLSF.IL;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanOrdenServicio;
import silat.servicios_negocio.entidades.audsis.TROrdenServicio;

@Remote
public interface BDL_C_SFOrdenServicioRemote {
    List<TROrdenServicio> getTROrdenServicioFindAll();
    List<TROrdenServicio> getOrdenServiciobyNombreEmpresa(Date fecOrdnServ);
    List<TROrdenServicio> findByCorrelativo(Object id);
    List<TROrdenServicio> findOrdenServicioByAttributes(BeanOrdenServicio beanParametro);
    List<TROrdenServicio> findOrdenServicioPendientesByAttributes(BeanOrdenServicio beanParametro);
    List<BeanOrdenServicio> findOrdenServicioAllGuiasOK(BeanOrdenServicio beanParametro);
    int verificarOSConGuia(Integer nidOrdenServicio);
    int countNuevasOS();
    List<TROrdenServicio> findOrdenServicioPendientesByAttributes_ParaGuia(BeanOrdenServicio beanParametro);
}