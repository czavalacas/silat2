package silat.servicios_negocio.BDLSF.IL;

import java.math.BigDecimal;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.admin.ADPermiso;

@Local
public interface BDL_T_SFPermisosLocal {
    ADPermiso persistADPermiso(ADPermiso ADPermiso);

    ADPermiso mergeADPermiso(ADPermiso ADPermiso);

    void removeADPermiso(ADPermiso ADPermiso);
    
    ADPermiso findADPermiso(BigDecimal id);
}
