package silat.servicios_negocio.LNSF.IR;

import java.util.List;
import javax.ejb.Remote;
import silat.servicios_negocio.Beans.BeanRol;

@Remote
public interface LN_C_SFRolRemote {
    List<BeanRol> getRolesActivosLN();
    List<BeanRol> getRolesActivosNoAdminLN();
}