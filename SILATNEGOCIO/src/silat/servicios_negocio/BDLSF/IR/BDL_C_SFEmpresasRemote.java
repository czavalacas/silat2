package silat.servicios_negocio.BDLSF.IR;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.entidades.admin.ADEmpresa;

@Remote
public interface BDL_C_SFEmpresasRemote {
    List<ADEmpresa> getADEmpresaFindAll();
    List<BeanEmpresa> getADEmpresabyName(String valor);
    BigDecimal getNidParty(String Nombre);
    List<BeanEmpresa> getADEmpresas();
    List<BeanEmpresa> findEmpresasByAttributes(BeanEmpresa beanEmpresa);
    List<BeanEmpresa> getListaEmpresas(List<ADEmpresa> lstEmpresas);
    BeanEmpresa getADEmpresasbyNid(BigDecimal nidParty);
    ADEmpresa getEmpresaById(BigDecimal nidParty);
  //  BeanEmpresa setUbigeos(BeanEmpresa beanEmpresa);
}
