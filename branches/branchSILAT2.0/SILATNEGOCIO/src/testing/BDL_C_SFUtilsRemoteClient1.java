package testing;

import java.util.Hashtable;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import silat.servicios_negocio.BDLSF.IR.BDL_C_SFUtilsRemote;

public class BDL_C_SFUtilsRemoteClient1 {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            BDL_C_SFUtilsRemote bDL_C_SFUtilsRemote =
                (BDL_C_SFUtilsRemote)context.lookup("mapBDL_C_SFUtils#silat.servicios_negocio.BDLSF.IR.BDL_C_SFUtilsRemote");
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
