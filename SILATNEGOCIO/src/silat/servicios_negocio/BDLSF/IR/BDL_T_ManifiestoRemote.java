package silat.servicios_negocio.BDLSF.IR;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.trans.TRManifiesto;

@Remote
public interface BDL_T_ManifiestoRemote {
    TRManifiesto persistTRManifiesto(TRManifiesto TRManifiesto);

    TRManifiesto mergeTRManifiesto(TRManifiesto TRManifiesto);

    void removeTRManifiesto(TRManifiesto TRManifiesto);
    TRManifiesto registrarManifiesto(TRManifiesto eManifiesto);
    
    String actualizarManifiesto(String observ, double fPactado,double nAdelanto, int nidMan, int nidFlota, int nidChof);
}
