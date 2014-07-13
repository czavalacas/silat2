package silat.servicios_negocio.BDLSF.IL;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.admin.ADParty;

@Local
public interface BDL_T_SFPartyLocal {
    ADParty persistADParty(ADParty ADParty);

    ADParty mergeADParty(ADParty ADParty);

    void removeADParty(ADParty ADParty);
}
