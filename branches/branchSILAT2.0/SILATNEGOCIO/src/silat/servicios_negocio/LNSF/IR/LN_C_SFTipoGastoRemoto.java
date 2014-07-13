package silat.servicios_negocio.LNSF.IR;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanTipoGasto;

@Remote
public interface LN_C_SFTipoGastoRemoto {
    List<BeanTipoGasto> getTiposGastosAll_LN(String descTipGasto,String estado);
}
