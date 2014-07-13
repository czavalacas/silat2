package testing;

import java.math.BigDecimal;

import java.util.Hashtable;

import java.util.Iterator;
import java.util.List;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import silat.servicios_negocio.BDLSF.IR.BDL_C_SFChoferBean;
import silat.servicios_negocio.Beans.BeanChofer;
import silat.servicios_negocio.entidades.admin.ADChofer;

public class BDL_C_SFChoferBeanClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            BDL_C_SFChoferBean bDL_C_SFChoferBean =
                (BDL_C_SFChoferBean)context.lookup("LUBAL_SIAT_APP-SILATNEGOCIO-BDL_C_SFChoferBean#silat.servicios_negocio.BDLSF.IR.BDL_C_SFChoferBean");
         //   List<BeanChofer> lista = bDL_C_SFChoferBean.getChoferesPorEmpresa(189);
          // List<ADChofer> lista = bDL_C_SFChoferBean.getADChoferFindAll();
     //     ADChofer entidad=bDL_C_SFChoferBean.getChoferById(new BigDecimal(2));
        //    Iterator it=lista.iterator();
      /*    while(it.hasNext()){
                ADChofer entidad=(ADChofer)it.next();
                System.out.println("NID CHOFER: "+entidad.getNidChofer()+ " NOMBRES : "+entidad.getNombres()
                                   +" LICENCIA : "+entidad.getLicencia());
            }*/
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
