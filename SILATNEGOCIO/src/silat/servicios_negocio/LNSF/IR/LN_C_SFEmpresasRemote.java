package silat.servicios_negocio.LNSF.IR;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.Beans.BeanError;

@Remote
public interface LN_C_SFEmpresasRemote {
    List<BeanEmpresa> getEmpresas();
    List<BeanEmpresa> getADEmpresaCliente(String valor);
    List<BeanEmpresa> findEmpresasByAttributes(BeanEmpresa beanEmpresa);
    BeanError crearEmpresa(  String[] cDireccion,
                                        String cPagWeb,
                                        String cRazonSocial,
                                        String cRuc,
                                        String cCerins,
                                        String[] cidUbigeo,
                                        String cDetalle,
                                        String cEmail,
                                        String cTelf,
                                        Integer[] nidTire,
                                        String[] cNombChofer,
                                        String[] cLicenciaChofer,
                                        String [] cMarcaFlota,
                                        String [] cPlacaFlota,
                                        String [] cConfFlota,
                                        String [] cDescripcionFlota,
                                        String [] cCerInsCripFlota);
    BeanError modificarEmpresa(  BigDecimal nidParty,                                   
                                    String cPagWeb,
                                    String cRazonSocial,
                                    String cRuc,
                                    String cCerins,
                                    String cDetalle,
                                    String cEmail,
                                    String cTelf,
                                    Integer[] nidTire);
    BeanEmpresa selectedEmpresa(BigDecimal nidParty);
    int getCountEmpresaByName(String valorCampo, String nombreCampo,boolean isUpdate,String oldValue);
}