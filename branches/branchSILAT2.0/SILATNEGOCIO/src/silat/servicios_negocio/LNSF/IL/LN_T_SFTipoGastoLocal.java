package silat.servicios_negocio.LNSF.IL;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanTipoGasto;

@Local
public interface LN_T_SFTipoGastoLocal {
    BeanTipoGasto registrarBeanTipoGasto_LN(int tipEvento, 
                                            String descTipGasto, 
                                            String isRuta,
                                            String estado,
                                            int nidTipGasto);;
}
