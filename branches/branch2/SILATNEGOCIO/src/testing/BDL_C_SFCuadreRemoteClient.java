package testing;

import java.math.BigDecimal;

import java.util.Date;
import java.util.Hashtable;

import java.util.Iterator;
import java.util.List;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import silat.servicios_negocio.BDLSF.IR.BDL_C_SFCuadreRemote;
import silat.servicios_negocio.Beans.BeanCuadre;
import silat.servicios_negocio.util_formato.UtilsGeneral;

public class BDL_C_SFCuadreRemoteClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            BDL_C_SFCuadreRemote bDL_C_SFCuadreRemote =
                (BDL_C_SFCuadreRemote)context.lookup("mapBDL_C_SFCuadre#silat.servicios_negocio.BDLSF.IR.BDL_C_SFCuadreRemote");
          //  BigDecimal[] b = bDL_C_SFCuadreRemote.call_Procedure_GET_INGRESOS(new Date(),new Date());
            List<BeanCuadre> lst = bDL_C_SFCuadreRemote.call_Procedure_GET_EGRESOS(new Date(),new Date());
            Iterator it = lst.iterator();
            while(it.hasNext()){
                BeanCuadre bc = (BeanCuadre) it.next();
                UtilsGeneral.depurar("bc: "+bc.getDescCuadre()+" monto: "+bc.getEgreso());
            }
            //UtilsGeneral.depurar("Ing_fact: "+b[0]+" ingre_nota_deb: "+b[1]);
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
