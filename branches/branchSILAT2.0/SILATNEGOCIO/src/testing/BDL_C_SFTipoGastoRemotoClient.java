package testing;

import java.util.Hashtable;
import java.util.List;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import silat.servicios_negocio.BDLSF.IR.BDL_C_SFTipoGastoRemoto;
import silat.servicios_negocio.entidades.admin.ADTipoGasto;

public class BDL_C_SFTipoGastoRemotoClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            BDL_C_SFTipoGastoRemoto bDL_C_SFTipoGastoRemoto =
                (BDL_C_SFTipoGastoRemoto)context.lookup("LUBAL_SIAT_APP-SILATNEGOCIO-BDL_C_SFTipoGasto#silat.servicios_negocio.BDLSF.IR.BDL_C_SFTipoGastoRemoto");
            for (ADTipoGasto adtipogasto : (List<ADTipoGasto>)bDL_C_SFTipoGastoRemoto.getAdmtigaFindAll()) {
                printADTipoGasto(adtipogasto);
            }
        } catch (CommunicationException ex) {
            System.out.println(ex.getClass().getName());
            System.out.println(ex.getRootCause().getLocalizedMessage());
            System.out.println("\n*** A CommunicationException was raised.  This typically\n*** occurs when the target WebLogic server is not running.\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printADTipoGasto(ADTipoGasto adtipogasto) {
        System.out.println("estadoRegistro = " + adtipogasto.getEstadoRegistro());
        System.out.println("descripcionTipoGasto = " + adtipogasto.getDescripcionTipoGasto());
        System.out.println("nidTiga = " + adtipogasto.getNidTiga());
        System.out.println("gastosLista = " + adtipogasto.getGastosLista());
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        return new InitialContext( env );
    }
}
