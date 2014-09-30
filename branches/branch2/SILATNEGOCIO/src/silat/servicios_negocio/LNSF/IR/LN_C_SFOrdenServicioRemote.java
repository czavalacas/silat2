package silat.servicios_negocio.LNSF.IR;

import java.math.BigDecimal;

import java.util.Date;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanOrdenServicio;

@Remote
public interface LN_C_SFOrdenServicioRemote{

    BeanOrdenServicio grabarOrdenServicio(BigDecimal nidParty,String cDetalle,Date fecha);
    List<BeanOrdenServicio> MostrarOrdenServ(Date fecOrdnServ);
    BeanOrdenServicio ModificarOrdenServicio(BigDecimal nidParty,String cDetalle,Date fecha,Integer nidOrdenServ, String estado,boolean isCambiarEmpresa,String comentario);
    List<BeanOrdenServicio> MostrarOrdenServNF();
    List<BeanOrdenServicio> findOrdenServicioByAttributesAux(BeanOrdenServicio beanOrdServ);
    List<BeanOrdenServicio> findOrdenServicioGuiasOK(String detalle, String razonSocial);
    int countNuevasOS_LN();
    List<BeanOrdenServicio> findOrdenServicioByAttributesAux_Para_Guia(BeanOrdenServicio beanOrdServ);
    int traerSiguienteValorSequence();
    String cambiarFechaOS_Permiso56(Integer nidOrdenServ,Date fechaOS);
}