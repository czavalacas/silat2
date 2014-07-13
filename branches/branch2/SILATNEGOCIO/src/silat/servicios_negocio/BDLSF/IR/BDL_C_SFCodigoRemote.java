package silat.servicios_negocio.BDLSF.IR;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.trans.Trmcodi;

@Remote
public interface BDL_C_SFCodigoRemote {
    Trmcodi findCodigoById(String cidUnin,
                           String tipDoc);
}
