package silat.servicios_negocio.BDLSF.IL;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.entidades.admin.ADEmpresa;

@Local
public interface BDL_C_SFEmpresasLocal {
    List<ADEmpresa> getADEmpresaFindAll();
    List<BeanEmpresa> getADEmpresabyName(String valor);
    BigDecimal getNidParty(String Nombre);
    List<BeanEmpresa> getADEmpresas();
    List<BeanEmpresa> findEmpresasByAttributes(BeanEmpresa beanEmpresa);
    List<BeanEmpresa> getListaEmpresas(List<ADEmpresa> lstEmpresas);
    BeanEmpresa getADEmpresasbyNid(BigDecimal nidParty);
    ADEmpresa getEmpresaById(BigDecimal nidParty);
   // BeanEmpresa setUbigeos(BeanEmpresa beanEmpresa);
}
