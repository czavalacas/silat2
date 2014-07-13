package silat.servicios_negocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import java.util.List;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import silat.servicios_negocio.BDLSF.IR.BDL_C_SFUtilsRemote;
import silat.servicios_negocio.Beans.BeanADUbigeo;
import silat.servicios_negocio.entidades.admin.ADUbigeo;

public class BDL_C_SFUtilsRemoteClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            BDL_C_SFUtilsRemote bDL_C_SFUtilsRemote =
                (BDL_C_SFUtilsRemote)context.lookup("mapBDL_C_SFUtils#silat.servicios_negocio.BDLSF.IR.BDL_C_SFUtilsRemote");
            //bDL_C_SFUtilsRemote.call_Function_validar_usuario("lbaca","12345678","1");
         //   System.out.println("vEncrypted: " + encr);
           /* String escc = encr[2];
            bDL_C_SFUtilsRemote.callSP_Desencriptar(escc,"3");*/
          /* HashMap m = bDL_C_SFUtilsRemote.generarCorrelativo("TRGuia", "cidGuia",6);
            System.out.println(m.get("error"));
            System.out.println(m.get("correlativo"));*/
            List<BeanADUbigeo> lstBeanUbigeo = bDL_C_SFUtilsRemote.getUbigeos(1,null,null);
            for (BeanADUbigeo ubigeo : lstBeanUbigeo) {
                 System.out.println("departamentos: "+ubigeo.getCidUbigeo()+" - "+ubigeo.getCDescUbigeo());
            }
            System.out.println("ejemplo de provincias -------------------------------");
            List<BeanADUbigeo> lstBeanUbigeo2 = bDL_C_SFUtilsRemote.getUbigeos(2, "150000", null); //provincias de lima
            for (BeanADUbigeo ubigeo : lstBeanUbigeo2) {
                System.out.println("pronvincias de Lima: " + ubigeo.getCidUbigeo() + " - " + ubigeo.getCDescUbigeo());
            }
            System.out.println("ejemplo de distritos -------------------------------");
            List<BeanADUbigeo> lstBeanUbigeo3 = bDL_C_SFUtilsRemote.getUbigeos(3,null, "150100"); //distritos de lima
            for (BeanADUbigeo ubigeo : lstBeanUbigeo3) {
                System.out.println("distritos de Lima: " + ubigeo.getCidUbigeo() + " - " + ubigeo.getCDescUbigeo());
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
        env.put(Context.PROVIDER_URL, "t3://10.1.1.108:7101");
        return new InitialContext( env );
    }
}
