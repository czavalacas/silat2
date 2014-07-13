package silat.servicios_negocio.BDLSF.IL;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.admin.ADModalidadPago;

@Local
public interface BDL_C_SFModalidadPagoLocal {
    List<ADModalidadPago> getAdmmopaFindAll();
}
