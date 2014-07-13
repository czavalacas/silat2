package silat.servicios_negocio.BDLSF.IL;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.trans.TrPreFactura;

@Local
public interface BDL_T_SFPreFacturaLocal {
    TrPreFactura persistTrPreFactura(TrPreFactura trPreFactura);

    TrPreFactura mergeTrPreFactura(TrPreFactura trPreFactura);

    void removeTrPreFactura(TrPreFactura trPreFactura);
}
