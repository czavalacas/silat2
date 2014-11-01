package silat.servicios_negocio.LNSF.IL;

import java.math.BigDecimal;

import java.util.Date;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanOrdenServicio;

@Local
public interface LN_C_SFOrdenServicioLocal {
    
    BeanOrdenServicio grabarOrdenServicio(BigDecimal nidParty,String cDetalle,Date fecha,String direcCli, String direcRemi, int nidRemitente);
    List<BeanOrdenServicio> MostrarOrdenServ(Date fecOrdnServ);
    BeanOrdenServicio ModificarOrdenServicio(BigDecimal nidParty,String cDetalle,Date fecha,Integer nidOrdenServ, String estado,boolean isCambiarEmpresa,String comentario);
    List<BeanOrdenServicio> MostrarOrdenServNF();
    List<BeanOrdenServicio> findOrdenServicioByAttributesAux(BeanOrdenServicio beanOrdServ);
    List<BeanOrdenServicio> findOrdenServicioGuiasOK(String detalle, String razonSocial);
    int countNuevasOS_LN();
    List<BeanOrdenServicio> findOrdenServicioByAttributesAux_Para_Guia(BeanOrdenServicio beanOrdServ);
    int traerSiguienteValorSequence();
    String cambiarFechaOS_Permiso56(Integer nidOrdenServ,Date fechaOS);
    List<BeanOrdenServicio> ordenServicioPendiente();
}
