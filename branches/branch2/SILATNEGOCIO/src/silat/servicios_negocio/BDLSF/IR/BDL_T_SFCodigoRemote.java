package silat.servicios_negocio.BDLSF.IR;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.trans.Trmcodi;

@Remote
public interface BDL_T_SFCodigoRemote {
    Trmcodi persistTrmcodi(Trmcodi trmcodi);

    Trmcodi mergeTrmcodi(Trmcodi trmcodi);

    void removeTrmcodi(Trmcodi trmcodi);
}
