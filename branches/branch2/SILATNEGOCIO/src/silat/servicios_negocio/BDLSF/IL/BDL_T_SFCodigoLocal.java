package silat.servicios_negocio.BDLSF.IL;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.trans.Trmcodi;

@Local
public interface BDL_T_SFCodigoLocal {
    Trmcodi persistTrmcodi(Trmcodi trmcodi);

    Trmcodi mergeTrmcodi(Trmcodi trmcodi);

    void removeTrmcodi(Trmcodi trmcodi);
}
