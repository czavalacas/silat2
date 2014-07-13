package silat.servicios_negocio.LNSF.IR;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanTipoGasto;

@Remote
public interface LN_T_SFTipoGastoRemote {
    
    BeanTipoGasto registrarBeanTipoGasto_LN(int tipEvento, 
                                            String descTipGasto, 
                                            String isRuta,
                                            String estado,
                                            int nidTipGasto);
}
