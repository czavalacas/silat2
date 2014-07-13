package silat.servicios_negocio.BDLSF.IL;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.trans.TrItemPreFactura;

@Local
public interface BDL_T_SFItemPreFacturaLocal {
    TrItemPreFactura persistTrItemPreFactura(TrItemPreFactura trItemPreFactura);

    TrItemPreFactura mergeTrItemPreFactura(TrItemPreFactura trItemPreFactura);

    void removeTrItemPreFactura(TrItemPreFactura trItemPreFactura);
}
