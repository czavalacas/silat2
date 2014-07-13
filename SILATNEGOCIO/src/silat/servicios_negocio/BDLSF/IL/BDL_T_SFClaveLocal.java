package silat.servicios_negocio.BDLSF.IL;

import java.math.BigDecimal;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.admin.ADClave;

@Local
public interface BDL_T_SFClaveLocal {
    ADClave persistADClave(ADClave ADClave);

    ADClave mergeADClave(ADClave ADClave);

    void removeADClave(ADClave ADClave);
    
    String actualizarClave(String cUsuario, String cClave, BigDecimal nidClave);
}
