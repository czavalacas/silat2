package silat.servicios_negocio.BDLSF.IL;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanADRelacionEmpresa;
import silat.servicios_negocio.entidades.admin.ADRelacionEmpresa;

@Local
public interface BDL_C_SFRelacionEmpresaLocal {
    List<BeanADRelacionEmpresa> getRelacionesEmpresaByAttributes(BeanADRelacionEmpresa beanBusqueda);
    List<BeanADRelacionEmpresa> getListRelaciones(List<ADRelacionEmpresa> lstRelacionEmp);
    List<ADRelacionEmpresa> getRelacionOfEmpresa(BigDecimal nidParty);
}