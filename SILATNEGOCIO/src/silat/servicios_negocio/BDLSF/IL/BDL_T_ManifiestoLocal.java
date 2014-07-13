package silat.servicios_negocio.BDLSF.IL;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.trans.TRManifiesto;

@Local
public interface BDL_T_ManifiestoLocal {
    TRManifiesto persistTRManifiesto(TRManifiesto TRManifiesto);

    TRManifiesto mergeTRManifiesto(TRManifiesto TRManifiesto);

    void removeTRManifiesto(TRManifiesto TRManifiesto);
    TRManifiesto registrarManifiesto(TRManifiesto eManifiesto);
}
