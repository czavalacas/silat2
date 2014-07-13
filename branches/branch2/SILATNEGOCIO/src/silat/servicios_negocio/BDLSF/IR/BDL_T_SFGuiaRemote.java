package silat.servicios_negocio.BDLSF.IR;

import java.math.BigDecimal;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.trans.TRGuia;

@Remote
public interface BDL_T_SFGuiaRemote {
    TRGuia persistTRGuia(TRGuia TRGuia);

    TRGuia mergeTRGuia(TRGuia TRGuia);

    void removeTRGuia(TRGuia TRGuia);
    TRGuia registrarGuia_BD(TRGuia trGuia, int opc);
    void cambiarNidItmPreFacturaANullXQSeraBorrada(BigDecimal nidPref);
}
