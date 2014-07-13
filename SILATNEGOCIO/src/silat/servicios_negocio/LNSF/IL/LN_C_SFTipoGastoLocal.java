package silat.servicios_negocio.LNSF.IL;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanTipoGasto;

@Local
public interface LN_C_SFTipoGastoLocal {
    List<BeanTipoGasto> getTiposGastosAll_LN(String descTipGasto,String estado);
}
