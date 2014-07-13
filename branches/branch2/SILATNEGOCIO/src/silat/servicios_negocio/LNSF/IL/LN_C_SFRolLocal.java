package silat.servicios_negocio.LNSF.IL;

import java.util.List;
import javax.ejb.Local;
import silat.servicios_negocio.Beans.BeanRol;

@Local
public interface LN_C_SFRolLocal {
    List<BeanRol> getRolesActivosLN();
    List<BeanRol> getRolesActivosNoAdminLN();
}