package testing;

import java.util.Hashtable;
import java.util.List;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import silat.servicios_negocio.BDLSF.IR.BDL_C_SFModalidadPago;
import silat.servicios_negocio.entidades.admin.ADModalidadPago;

public class BDL_C_SFModalidadPagoClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            BDL_C_SFModalidadPago bDL_C_SFModalidadPago =
                (BDL_C_SFModalidadPago)context.lookup("LUBAL_SIAT_APP-SILATNEGOCIO-BDL_C_SFModalidadPago#silat.servicios_negocio.BDLSF.IR.BDL_C_SFModalidadPago");
            for (ADModalidadPago admodalidadpago : (List<ADModalidadPago>)bDL_C_SFModalidadPago.getAdmmopaFindAll()) {
                printADModalidadPago(admodalidadpago);
            }
        } catch (CommunicationException ex) {
            System.out.println(ex.getClass().getName());
            System.out.println(ex.getRootCause().getLocalizedMessage());
            System.out.println("\n*** A CommunicationException was raised.  This typically\n*** occurs when the target WebLogic server is not running.\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printADModalidadPago(ADModalidadPago admodalidadpago) {
        System.out.println("estadoRegistro = " + admodalidadpago.getEstadoRegistro());
        System.out.println("descripcionModalidadPago = " + admodalidadpago.getDescripcionModalidadPago());
        System.out.println("nidModalidadPago = " + admodalidadpago.getNidModalidadPago());
        System.out.println("gastosLista = " + admodalidadpago.getGastosLista());
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        return new InitialContext( env );
    }
}
