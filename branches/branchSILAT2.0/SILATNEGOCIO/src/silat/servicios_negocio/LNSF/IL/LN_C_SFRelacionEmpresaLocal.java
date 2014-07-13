package silat.servicios_negocio.LNSF.IL;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanADRelacionEmpresa;
import silat.servicios_negocio.entidades.admin.ADEmpresa;
import silat.servicios_negocio.entidades.admin.ADRelacionEmpresa;

@Local
public interface LN_C_SFRelacionEmpresaLocal {
    List<BeanADRelacionEmpresa> getEmpresaProveedores(String razonSocial);
    List<BeanADRelacionEmpresa> getEmpresaProveedoresCliente(String razonSocial,String ruc);
    public ADRelacionEmpresa grabarRelacion(BigDecimal nidParty,Integer nidTire);
    ADRelacionEmpresa borrarRelacion(BigDecimal nidParty);
    List<BeanADRelacionEmpresa> getRelacion(BigDecimal nidParty);
    ADRelacionEmpresa grabarRelacion_Aux(ADEmpresa empresaemp,
                                         Integer nidTire);
}
