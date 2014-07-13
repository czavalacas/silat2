package silat.servicios_negocio.BDLSF.IL;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.trans.TRItem;

@Local
public interface BDL_T_SFItemLocal {
    TRItem persistTRItem(TRItem TRItem);

    TRItem mergeTRItem(TRItem TRItem);

    void removeTRItem(TRItem TRItem);
}
