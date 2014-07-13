package silat.servicios_negocio.BDLSF.IL;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanTipoGasto;
import silat.servicios_negocio.entidades.admin.ADTipoGasto;

@Local
public interface BDL_C_SFTipoGastoLocal {
    List<ADTipoGasto> getAdmtigaFindAll();
    List<BeanTipoGasto> getTiposGastosAll_BD(BeanTipoGasto btGasto);
    ADTipoGasto findADTipoGastoById(Integer id);
}
