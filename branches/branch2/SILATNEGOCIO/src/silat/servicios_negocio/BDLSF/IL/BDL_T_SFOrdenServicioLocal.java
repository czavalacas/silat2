package silat.servicios_negocio.BDLSF.IL;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanTRItem;
import silat.servicios_negocio.entidades.admin.ADEmpresa;
import silat.servicios_negocio.entidades.audsis.STRol;
import silat.servicios_negocio.entidades.audsis.TROrdenServicio;

@Local
public interface BDL_T_SFOrdenServicioLocal {


    List<TROrdenServicio> getTROrdenServicioFindAll();

    TROrdenServicio persistTROrdenServicio(TROrdenServicio TROrdenServicio);

    TROrdenServicio mergeTROrdenServicio(TROrdenServicio TROrdenServicio);

    void removeTROrdenServicio(TROrdenServicio TROrdenServicio);

    String grabarOrdenServicio(TROrdenServicio TROrdenServicio, List<BeanTRItem> listaItems);

    BigDecimal getNidParty(String Nombre);
    
    String ModificarOrdenServicio(TROrdenServicio TROrdenServicio);
    
    void flgVistoNuevasOrdenServicio_BDL();
}
