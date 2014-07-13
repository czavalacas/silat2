package silat.servicios_negocio.BDLSF.IR;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.trans.TrPreFactura;

@Remote
public interface BDL_T_SFPreFacturaRemote {
    TrPreFactura persistTrPreFactura(TrPreFactura trPreFactura);

    TrPreFactura mergeTrPreFactura(TrPreFactura trPreFactura);

    void removeTrPreFactura(TrPreFactura trPreFactura);
}
