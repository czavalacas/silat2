package testing;

import java.math.BigDecimal;

import java.util.Date;
import java.util.Hashtable;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import silat.servicios_negocio.LNSF.IR.LN_T_SFGastosRemoto;

import utils.system;

public class LN_T_SFGastosRemotoClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            LN_T_SFGastosRemoto lN_T_SFGastosRemoto =
                (LN_T_SFGastosRemoto)context.lookup("LUBAL_SIAT_APP-SILATNEGOCIO-LN_T_SFGastos#silat.servicios_negocio.LNSF.IR.LN_T_SFGastosRemoto");
            Date fecha= new Date(System.currentTimeMillis());  
            lN_T_SFGastosRemoto.insertarGasto(1, 1, new BigDecimal(200), fecha , 1,1L , "", "", new BigDecimal(3), new BigDecimal(4), 9, new BigDecimal(5), "", "", new byte[5], "","","1");
            
            
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
