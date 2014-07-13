package silat.servicios_negocio.BDLSF.IR;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanADRelacionEmpresa;
import silat.servicios_negocio.entidades.admin.ADRelacionEmpresa;

@Remote
public interface BDL_C_SFRelacionEmpresaRemote {
    List<BeanADRelacionEmpresa> getRelacionesEmpresaByAttributes(BeanADRelacionEmpresa beanBusqueda);
    List<BeanADRelacionEmpresa> getListRelaciones(List<ADRelacionEmpresa> lstRelacionEmp);
    List<ADRelacionEmpresa> getRelacionOfEmpresa(BigDecimal nidParty);
}