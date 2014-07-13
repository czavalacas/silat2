package silat.servicios_negocio.BDLSF.IR;

import java.math.BigDecimal;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.admin.ADParty;

@Remote
public interface BDL_C_SFPartyRemote {
    ADParty getADPartybyNid(BigDecimal nidParty);
}
