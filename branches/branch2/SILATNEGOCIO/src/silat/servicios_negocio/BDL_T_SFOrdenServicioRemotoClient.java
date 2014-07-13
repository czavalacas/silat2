package silat.servicios_negocio;

import java.util.Hashtable;
import java.util.List;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import silat.servicios_negocio.BDLSF.IR.BDL_T_SFOrdenServicioRemoto;
import silat.servicios_negocio.entidades.audsis.TROrdenServicio;

public class BDL_T_SFOrdenServicioRemotoClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            BDL_T_SFOrdenServicioRemoto bDL_T_SFOrdenServicioRemoto =
                (BDL_T_SFOrdenServicioRemoto)context.lookup("mapBDL_T_SFOrdenServicio#silat.servicios_negocio.BDLSF.IR.BDL_T_SFOrdenServicioRemoto");
            for (TROrdenServicio trordenservicio :
                 (List<TROrdenServicio>)bDL_T_SFOrdenServicioRemoto.getTROrdenServicioFindAll()) {
                printTROrdenServicio(trordenservicio);
            }
        } catch (CommunicationException ex) {
            System.out.println(ex.getClass().getName());
            System.out.println(ex.getRootCause().getLocalizedMessage());
            System.out.println("\n*** A CommunicationException was raised.  This typically\n*** occurs when the target WebLogic server is not running.\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printTROrdenServicio(TROrdenServicio trordenservicio) {
        System.out.println("cDetalle = " + trordenservicio.getCDetalle());
        System.out.println("fecOrdnServ = " + trordenservicio.getFecOrdnServ());
        System.out.println("nEstadoOrden = " + trordenservicio.getNEstadoOrden());
        System.out.println("nidOrdnServ = " + trordenservicio.getNidOrdnServ());
        System.out.println("adEmpresa = " + trordenservicio.getAdEmpresa());
        System.out.println("guiasList = " + trordenservicio.getGuiasList());
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        return new InitialContext( env );
    }
}
