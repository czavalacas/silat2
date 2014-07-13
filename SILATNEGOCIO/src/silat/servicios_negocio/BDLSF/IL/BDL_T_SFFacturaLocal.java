package silat.servicios_negocio.BDLSF.IL;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.trans.TRFactura;

@Local
public interface BDL_T_SFFacturaLocal {
    TRFactura persistTRFactura(TRFactura TRFactura);

    TRFactura mergeTRFactura(TRFactura TRFactura);

    void removeTRFactura(TRFactura TRFactura);
}
