package silat.servicios_negocio.BDLSF.IL;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.trans.TRItemXOrds;

@Local
public interface BDL_T_SFItemXOrdenServLocal {
    TRItemXOrds persistTRItemXOrds(TRItemXOrds TRItemXOrds);

    TRItemXOrds mergeTRItemXOrds(TRItemXOrds TRItemXOrds);

    void removeTRItemXOrds(TRItemXOrds TRItemXOrds);
}
