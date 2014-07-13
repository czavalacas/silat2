package silat.servicios_negocio.BDLSF.IR;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.admin.ADEmpresa;
import silat.servicios_negocio.entidades.audsis.STRol;
import silat.servicios_negocio.entidades.audsis.TROrdenServicio;

@Remote
public interface BDL_T_SFOrdenServicioRemoto {


    List<TROrdenServicio> getTROrdenServicioFindAll();

    TROrdenServicio persistTROrdenServicio(TROrdenServicio TROrdenServicio);

    TROrdenServicio mergeTROrdenServicio(TROrdenServicio TROrdenServicio);

    void removeTROrdenServicio(TROrdenServicio TROrdenServicio);
    
    String grabarOrdenServicio(TROrdenServicio TROrdenServicio);
    BigDecimal getNidParty(String Nombre);
    String ModificarOrdenServicio(TROrdenServicio TROrdenServicio);
    void flgVistoNuevasOrdenServicio_BDL();
}
