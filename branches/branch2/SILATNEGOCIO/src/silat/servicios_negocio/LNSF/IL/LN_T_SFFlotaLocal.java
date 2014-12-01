package silat.servicios_negocio.LNSF.IL;

import java.math.BigDecimal;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanFlota;
import silat.servicios_negocio.entidades.admin.ADFlota;

@Local
public interface LN_T_SFFlotaLocal {
    ADFlota registrarFlota(BigDecimal nidParty, 
                                      String marca, 
                                      String placa, 
                                      String configuracion, 
                                      String descripcion,
                                      String cCerIns);
    void removerFlota(Integer nidFlota);
    BeanFlota actualizarFlota(BigDecimal nidParty, 
                                      Integer nidFlota,
                                      String marca, 
                                      String placa, 
                                      String configuracion, 
                                      String descripcion,
                                      String cCeInsFlota);
    BeanFlota anularFlota(Integer nidFlota, String estado);
}
