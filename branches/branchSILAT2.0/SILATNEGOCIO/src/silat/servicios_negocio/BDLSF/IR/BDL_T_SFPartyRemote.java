package silat.servicios_negocio.BDLSF.IR;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.admin.ADParty;

@Remote
public interface BDL_T_SFPartyRemote {
    ADParty persistADParty(ADParty ADParty);

    ADParty mergeADParty(ADParty ADParty);

    void removeADParty(ADParty ADParty);
}
