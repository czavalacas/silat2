package silat.servicios_negocio.BDLSF.IL;

import java.math.BigDecimal;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.trans.TRGuia;

@Local
public interface BDL_T_SFGuiaLocal {
    TRGuia persistTRGuia(TRGuia TRGuia);

    TRGuia mergeTRGuia(TRGuia TRGuia);

    void removeTRGuia(TRGuia TRGuia);
    TRGuia registrarGuia_BD(TRGuia trGuia, int opc);
    void cambiarNidItmPreFacturaANullXQSeraBorrada(BigDecimal nidPref);
}
