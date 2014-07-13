package silat.servicios_negocio;

import java.math.BigDecimal;

import java.util.Hashtable;
import java.util.List;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import silat.servicios_negocio.BDLSF.IR.BDL_C_SFDireccionRemote;
import silat.servicios_negocio.entidades.admin.ADDireccion;

public class BDL_C_SFDireccionRemoteClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            BDL_C_SFDireccionRemote bDL_C_SFDireccionRemote =
                (BDL_C_SFDireccionRemote)context.lookup("LUBAL_SIAT_APP-SILATNEGOCIO-BDL_C_SFDireccion#silat.servicios_negocio.BDLSF.IR.BDL_C_SFDireccionRemote");
            /*for (ADDireccion addireccion : (List<ADDireccion>)bDL_C_SFDireccionRemote.getADDireccionFindAll()) {
                printADDireccion(addireccion);
            }*/
            for (ADDireccion addireccion :
                 (List<ADDireccion>)bDL_C_SFDireccionRemote.getDireccionOfParty(new BigDecimal("96")) /* FIXME: Pass parameters here */) {
                printADDireccion(addireccion);
            }
        } catch (CommunicationException ex) {
            System.out.println(ex.getClass().getName());
            System.out.println(ex.getRootCause().getLocalizedMessage());
            System.out.println("\n*** A CommunicationException was raised.  This typically\n*** occurs when the target WebLogic server is not running.\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printADDireccion(ADDireccion addireccion) {
        System.out.println("cDireccion = " + addireccion.getCDireccion());
        System.out.println("cidUbigeo = " + addireccion.getCidUbigeo());
        System.out.println("nidDireccion = " + addireccion.getNidDireccion());
        System.out.println("party = " + addireccion.getParty());
        System.out.println("ubigeo = " + addireccion.getUbigeo());
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        return new InitialContext( env );
    }
}
