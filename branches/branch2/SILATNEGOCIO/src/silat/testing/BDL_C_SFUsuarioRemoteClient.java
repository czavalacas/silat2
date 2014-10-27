package silat.testing;

import java.util.Hashtable;
import java.util.List;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import silat.servicios_negocio.BDLSF.IR.BDL_C_SFUsuarioRemote;
import silat.servicios_negocio.entidades.admin.ADUsuario;

public class BDL_C_SFUsuarioRemoteClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            BDL_C_SFUsuarioRemote bDL_C_SFUsuarioRemote =
                (BDL_C_SFUsuarioRemote)context.lookup("mapSFUsuario#silat.servicios_negocio.BDLSF.IR.BDL_C_SFUsuarioRemote");
            for (ADUsuario adusuario : (List<ADUsuario>)bDL_C_SFUsuarioRemote.getADUsuarioFindAll()) {
                printADUsuario(adusuario);
            }
            for (ADUsuario adusuario : (List<ADUsuario>)bDL_C_SFUsuarioRemote.getUsuariosNoAdmin()) {
                printADUsuario(adusuario);
            }
        } catch (CommunicationException ex) {
            System.out.println(ex.getClass().getName());
            System.out.println(ex.getRootCause().getLocalizedMessage());
            System.out.println("\n*** A CommunicationException was raised.  This typically\n*** occurs when the target WebLogic server is not running.\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printADUsuario(ADUsuario adusuario) {
        System.out.println("cUsuario = " + adusuario.getCUsuario());
        System.out.println("nEstadoUsuario = " + adusuario.getNEstadoUsuario());
        System.out.println("nTipoUsuario = " + adusuario.getNTipoUsuario());
        System.out.println("nidUsuario = " + adusuario.getNidUsuario());
        System.out.println("clavesList = " + adusuario.getClavesList());
        System.out.println("usuarioXPermisosList = " + adusuario.getUsuarioXPermisosList());
        System.out.println("adPersona = " + adusuario.getAdPersona());
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        return new InitialContext( env );
    }
}
