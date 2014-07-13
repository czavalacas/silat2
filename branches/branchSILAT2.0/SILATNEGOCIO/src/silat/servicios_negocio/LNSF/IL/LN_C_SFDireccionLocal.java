package silat.servicios_negocio.LNSF.IL;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanDireccion;
import silat.servicios_negocio.entidades.admin.ADDireccion;
import silat.servicios_negocio.entidades.admin.ADParty;

@Local
public interface LN_C_SFDireccionLocal {
    ADDireccion grabarRelacion(BigDecimal nidParty,String cidUbigeo,String cDireccion);
    ADDireccion borrarRelacion(BigDecimal nidParty);
    List<BeanDireccion> getRelacion(BigDecimal nidParty);
    List<BeanDireccion> getDireccionByProp_LN(Integer nidDireccion,
                                              Integer nidParty,
                                              String cDireccion);
    ADDireccion grabarDireccion(ADParty party,
                                String cidUbigeo,
                                String cDireccion);
    ADDireccion insertarDireccion(BigDecimal nidParty,
                                           String cidUbigeo,
                                           String cDireccion);
    ADDireccion modificarDireccion(BigDecimal nidDireccion,
                                              BigDecimal nidParty,
                                           String cidUbigeo,
                                           String cDireccion);
    void  borrarDireccion(BigDecimal nidDireccion);
    BeanDireccion getDireccionUbigeosDesc_Para_Factura(Integer nidDireccion);

}
