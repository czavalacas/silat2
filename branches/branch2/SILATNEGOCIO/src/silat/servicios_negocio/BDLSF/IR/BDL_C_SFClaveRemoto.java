package silat.servicios_negocio.BDLSF.IR;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.admin.ADClave;

@Remote
public interface BDL_C_SFClaveRemoto {
    List<ADClave> getADClaveFindAll();
    ADClave getClavePorUsuario(BigDecimal nidUsuario);
}
