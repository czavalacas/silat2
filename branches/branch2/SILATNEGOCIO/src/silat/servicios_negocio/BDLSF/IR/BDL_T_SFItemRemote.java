package silat.servicios_negocio.BDLSF.IR;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.trans.TRItem;

@Remote
public interface BDL_T_SFItemRemote {
    TRItem persistTRItem(TRItem TRItem);

    TRItem mergeTRItem(TRItem TRItem);

    void removeTRItem(TRItem TRItem);
}
