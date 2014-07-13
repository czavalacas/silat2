package silat.servicios_negocio.BDLSF.IL;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanDireccion;
import silat.servicios_negocio.entidades.admin.ADDireccion;

@Local
public interface BDL_C_SFDireccionLocal {
    List<ADDireccion> getADDireccionFindAll();
    List<ADDireccion> getDireccionOfParty(BigDecimal nidParty);
    List<BeanDireccion> findDireccionessByAttributes(BeanDireccion beanDireccion);
    BeanDireccion getDireccionUbigeosDesc_Para_Factura(Integer nidDireccion);
}
