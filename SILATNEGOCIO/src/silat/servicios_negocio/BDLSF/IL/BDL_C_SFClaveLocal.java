package silat.servicios_negocio.BDLSF.IL;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.admin.ADClave;

@Local
public interface BDL_C_SFClaveLocal {
    List<ADClave> getADClaveFindAll();
    ADClave getClavePorUsuario(BigDecimal nidUsuario);
}
