package silat.servicios_negocio.BDLSF.IR;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanTipoGasto;
import silat.servicios_negocio.entidades.admin.ADTipoGasto;

@Remote
public interface BDL_C_SFTipoGastoRemoto {
    List<ADTipoGasto> getAdmtigaFindAll();
    List<BeanTipoGasto> getTiposGastosAll_BD(BeanTipoGasto btGasto);
    ADTipoGasto findADTipoGastoById(Integer id);
}
