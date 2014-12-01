package silat.servicios_negocio.LNSF.IR;

import java.math.BigDecimal;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanFlota;
import silat.servicios_negocio.entidades.admin.ADFlota;

@Remote
public interface LN_T_SFFlotaRemote {
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
