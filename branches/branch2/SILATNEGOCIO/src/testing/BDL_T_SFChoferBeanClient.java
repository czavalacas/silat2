package testing;

import java.math.BigDecimal;

import java.util.Hashtable;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import silat.servicios_negocio.BDLSF.IR.BDL_T_SFChoferBean;
import silat.servicios_negocio.entidades.admin.ADChofer;
import silat.servicios_negocio.entidades.admin.ADDireccion;
import silat.servicios_negocio.entidades.admin.ADEmpresa;
import silat.servicios_negocio.entidades.admin.ADParty;
import silat.servicios_negocio.entidades.admin.ADUbigeo;

public class BDL_T_SFChoferBeanClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            BDL_T_SFChoferBean bDL_T_SFChoferBean =
                (BDL_T_SFChoferBean)context.lookup("LUBAL_SIAT_APP-SILATNEGOCIO-BDL_T_SFChoferBean#silat.servicios_negocio.BDLSF.IR.BDL_T_SFChoferBean");
           
           
            ADParty party= new ADParty();
            party.setNidParty(new BigDecimal(6));            
            ADEmpresa empresa = new ADEmpresa();
            empresa.setAdParty(party);
            ADChofer entidad=new ADChofer();
            entidad.setLicencia("A3");
            entidad.setNombres("PEPITO MAS NAKI");
            entidad.setEstadoRegistro("1");
            entidad.setEmpresa(empresa);
            bDL_T_SFChoferBean.persistADChofer(entidad);
        
        } catch (CommunicationException ex) {
            System.out.println(ex.getClass().getName());
            System.out.println(ex.getRootCause().getLocalizedMessage());
            System.out.println("\n*** A CommunicationException was raised.  This typically\n*** occurs when the target WebLogic server is not running.\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        return new InitialContext( env );
    }
}
