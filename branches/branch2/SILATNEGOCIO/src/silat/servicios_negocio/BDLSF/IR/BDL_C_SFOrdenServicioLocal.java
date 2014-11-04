package silat.servicios_negocio.BDLSF.IR;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanOrdenServicio;
import silat.servicios_negocio.entidades.audsis.TROrdenServicio;
import silat.servicios_negocio.entidades.trans.TRItemXOrds;

@Local
public interface BDL_C_SFOrdenServicioLocal {
    List<TROrdenServicio> getTROrdenServicioFindAll();
    List<TROrdenServicio> getOrdenServiciobyNombreEmpresa(Date fecOrdnServ);
    List<TROrdenServicio> findByCorrelativo(Object id);
    List<TROrdenServicio> findOrdenServicioByAttributes(BeanOrdenServicio beanParametro);
    List<TROrdenServicio> findOrdenServicioPendientesByAttributes(BeanOrdenServicio beanParametro);
    List<BeanOrdenServicio> findOrdenServicioAllGuiasOK(BeanOrdenServicio beanParametro);
    int verificarOSConGuia(Integer nidOrdenServicio);
    int countNuevasOS();
    List<TROrdenServicio> findOrdenServicioPendientesByAttributes_ParaGuia(BeanOrdenServicio beanParametro);
    List<TRItemXOrds>getItemsbyOrd(String nidOrds);
}