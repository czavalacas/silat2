package silat.servicios_negocio.BDLSF.IR;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.trans.TRFactura;

@Remote
public interface BDL_T_SFFacturaRemote {
    TRFactura persistTRFactura(TRFactura TRFactura);

    TRFactura mergeTRFactura(TRFactura TRFactura);

    void removeTRFactura(TRFactura TRFactura);
}
