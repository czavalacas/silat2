package silat.servicios_negocio.BDLSF.IR;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.trans.TrItemPreFactura;

@Remote
public interface BDL_C_SFItemPreFacturaRemote {
    List<TrItemPreFactura> getTrItemPreFacturaFindAll();
    List<TrItemPreFactura> getTrItemPreFacturaByPreFactura_BD(Long nidPrefact);
}
