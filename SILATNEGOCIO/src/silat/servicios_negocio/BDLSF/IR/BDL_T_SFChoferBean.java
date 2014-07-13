package silat.servicios_negocio.BDLSF.IR;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.admin.ADChofer;

@Remote
public interface BDL_T_SFChoferBean {
    ADChofer persistADChofer(ADChofer ADChofer);
    ADChofer mergeADChofer(ADChofer ADChofer);
    void removeADChofer(ADChofer ADChofer);
}
