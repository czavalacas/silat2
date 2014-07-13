package silat.servicios_negocio.BDLSF.IL;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.trans.TrItemPreFactura;

@Local
public interface BDL_C_SFItemPreFacturaLocal {
    List<TrItemPreFactura> getTrItemPreFacturaFindAll();
    List<TrItemPreFactura> getTrItemPreFacturaByPreFactura_BD(Long nidPrefact);
}
