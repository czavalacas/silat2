package silat.servicios_negocio;

import java.util.Hashtable;
import java.util.List;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import silat.servicios_negocio.BDLSF.IR.BDL_C_SFCatalogoErroresRemote;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.entidades.audsis.STError;

public class BDL_C_SFCatalogoErroresRemoteClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            BDL_C_SFCatalogoErroresRemote bDL_C_SFCatalogoErroresRemote =
                (BDL_C_SFCatalogoErroresRemote)context.lookup("mapBDL_C_SFCatalogoErrores#silat.servicios_negocio.BDLSF.IR.BDL_C_SFCatalogoErroresRemote");
            //BeanError sterror = bDL_C_SFCatalogoErroresRemote.getErroByCid("LUB-0006");
            //System.out.println("sdf BeanError: " + sterror.getCDescripcionError());
            List<BeanError> lstErrores = bDL_C_SFCatalogoErroresRemote.findAll();
            for(int i = 0; i < lstErrores.size(); i++){
                System.out.println("BeanError: " + lstErrores.get(i).getCDescripcionError());
                System.out.println("BeanError: " + lstErrores.get(i).getCAbreviatura());
                System.out.println("BeanError: " + lstErrores.get(i).getCidError());
                System.out.println("--------------------------------");
            }
            
        } catch (CommunicationException ex) {
            System.out.println(ex.getClass().getName());
            System.out.println(ex.getRootCause().getLocalizedMessage());
            System.out.println("\n*** A CommunicationException was raised.  This typically\n*** occurs when the target WebLogic server is not running.\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printSTError(STError sterror) {
        System.out.println("cAbreviatura = " + sterror.getCAbreviatura());
        System.out.println("cDescripcionError = " + sterror.getCDescripcionError());
        System.out.println("cEstadoError = " + sterror.getCEstadoError());
        System.out.println("cidError = " + sterror.getCidError());
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        env.put(Context.PROVIDER_URL, "t3://192.168.1.34:7101");
        return new InitialContext( env );
    }
}
