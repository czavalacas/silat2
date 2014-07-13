package silat.servicios_negocio.LNSF.IR;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanADRelacionEmpresa;
import silat.servicios_negocio.entidades.admin.ADEmpresa;
import silat.servicios_negocio.entidades.admin.ADRelacionEmpresa;

@Remote
public interface LN_C_SFRelacionEmpresaRemote {
    List<BeanADRelacionEmpresa> getEmpresaProveedores(String razonSocial);
    List<BeanADRelacionEmpresa> getEmpresaProveedoresCliente(String razonSocial,String ruc);
    public ADRelacionEmpresa grabarRelacion(BigDecimal nidParty,Integer nidTire);
    ADRelacionEmpresa borrarRelacion(BigDecimal nidParty);
    List<BeanADRelacionEmpresa> getRelacion(BigDecimal nidParty);
    ADRelacionEmpresa grabarRelacion_Aux(ADEmpresa empresaemp,
                                         Integer nidTire);
}
