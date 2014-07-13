package silat.servicios_negocio.BDLSF.IL;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.admin.ADChofer;

@Local
public interface BDL_T_SFChoferBeanLocal {
    ADChofer persistADChofer(ADChofer ADChofer);
    ADChofer mergeADChofer(ADChofer ADChofer);
    void removeADChofer(ADChofer ADChofer);
}
