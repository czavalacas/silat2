package silat.servicios_negocio.BDLSF.IR;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanDireccion;
import silat.servicios_negocio.entidades.admin.ADDireccion;

@Remote
public interface BDL_C_SFDireccionRemote {
    List<ADDireccion> getADDireccionFindAll();
    List<ADDireccion> getDireccionOfParty(BigDecimal nidParty);
    List<BeanDireccion> findDireccionessByAttributes(BeanDireccion beanDireccion);
    BeanDireccion getDireccionUbigeosDesc_Para_Factura(Integer nidDireccion);
    String getDescripcionDireccionByNid(int nidDireccion);
}
