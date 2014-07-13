package silat.servicios_negocio.BDLSF.IL;

import java.math.BigDecimal;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.admin.ADParty;

@Local
public interface BDL_C_SFPartyLocal {
    ADParty getADPartybyNid(BigDecimal nidParty);
}
