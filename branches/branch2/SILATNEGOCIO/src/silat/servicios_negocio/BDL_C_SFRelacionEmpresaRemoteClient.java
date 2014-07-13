package silat.servicios_negocio;

import java.util.Hashtable;

import java.util.List;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import silat.servicios_negocio.BDLSF.IR.BDL_C_SFRelacionEmpresaRemote;
import silat.servicios_negocio.Beans.BeanADRelacionEmpresa;
import silat.servicios_negocio.Beans.BeanEmpresa;

public class BDL_C_SFRelacionEmpresaRemoteClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            BDL_C_SFRelacionEmpresaRemote bDL_C_SFRelacionEmpresaRemote =
                (BDL_C_SFRelacionEmpresaRemote)context.lookup("mapBDL_C_SFRelacionEmpresa#silat.servicios_negocio.BDLSF.IR.BDL_C_SFRelacionEmpresaRemote");
            BeanADRelacionEmpresa beanBusqueda = new BeanADRelacionEmpresa();
            beanBusqueda.setNidEmpresa2(5);
            beanBusqueda.setNidTipoRelacion(2);
            List<BeanADRelacionEmpresa> listRel = bDL_C_SFRelacionEmpresaRemote.getRelacionesEmpresaByAttributes(beanBusqueda);
            for (int i = 0; i < listRel.size() ; i++) {
                BeanADRelacionEmpresa rel = listRel.get(i);
                System.out.println("empresa : "+rel.getAdEmpresa2().getCRazonSocial());
                System.out.println("tiene como "+rel.getAdTipoRelacion().getDescripcionTipoRelacion());
                System.out.println("a la empresa : "+rel.getAdEmpresa1().getCRazonSocial());
                System.out.println("============================================================================\n");
            }
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
        env.put(Context.PROVIDER_URL, "t3://192.168.1.34:7101");
        return new InitialContext( env );
    }
}
