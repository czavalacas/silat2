package silat.servicios_negocio.BDLSF.IR;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.trans.TRItemXOrds;

@Remote
public interface BDL_T_SFItemXOrdenServRemoto {
    TRItemXOrds persistTRItemXOrds(TRItemXOrds TRItemXOrds);

    TRItemXOrds mergeTRItemXOrds(TRItemXOrds TRItemXOrds);

    void removeTRItemXOrds(TRItemXOrds TRItemXOrds);
}
