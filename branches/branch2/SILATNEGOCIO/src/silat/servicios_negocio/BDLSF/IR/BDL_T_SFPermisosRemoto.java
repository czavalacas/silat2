package silat.servicios_negocio.BDLSF.IR;

import java.math.BigDecimal;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.admin.ADPermiso;

@Remote
public interface BDL_T_SFPermisosRemoto {
    ADPermiso persistADPermiso(ADPermiso ADPermiso);

    ADPermiso mergeADPermiso(ADPermiso ADPermiso);

    void removeADPermiso(ADPermiso ADPermiso);
    
    ADPermiso findADPermiso(BigDecimal id);
}
