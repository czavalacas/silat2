package silat.servicios_negocio.BDLSF.IR;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.trans.TrItemPreFactura;

@Remote
public interface BDL_T_SFItemPreFacturaRemote {
    TrItemPreFactura persistTrItemPreFactura(TrItemPreFactura trItemPreFactura);

    TrItemPreFactura mergeTrItemPreFactura(TrItemPreFactura trItemPreFactura);

    void removeTrItemPreFactura(TrItemPreFactura trItemPreFactura);
}
