package silat.servicios_negocio.BDLSF.IL;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.trans.Trmcodi;

@Local
public interface BDL_C_SFCodigoLocal {
    Trmcodi findCodigoById(String cidUnin,
                           String tipDoc);
}
