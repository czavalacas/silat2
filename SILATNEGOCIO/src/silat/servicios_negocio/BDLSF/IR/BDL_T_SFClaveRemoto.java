package silat.servicios_negocio.BDLSF.IR;

import java.math.BigDecimal;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.admin.ADClave;

@Remote
public interface BDL_T_SFClaveRemoto {
    ADClave persistADClave(ADClave ADClave);

    ADClave mergeADClave(ADClave ADClave);

    void removeADClave(ADClave ADClave);
    
    String actualizarClave(String cUsuario, String cClave, BigDecimal nidClave);
}
