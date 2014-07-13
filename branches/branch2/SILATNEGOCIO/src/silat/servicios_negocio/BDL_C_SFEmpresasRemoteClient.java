package silat.servicios_negocio;

import com.sun.jmx.snmp.Timestamp;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import org.apache.commons.net.ntp.TimeStamp;

import silat.servicios_negocio.BDLSF.IR.BDL_C_SFEmpresasRemote;
import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.entidades.admin.ADEmpresa;

public class BDL_C_SFEmpresasRemoteClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            BDL_C_SFEmpresasRemote bDL_C_SFEmpresasRemote =
                (BDL_C_SFEmpresasRemote)context.lookup("mapBDL_C_SFEmpresas#silat.servicios_negocio.BDLSF.IR.BDL_C_SFEmpresasRemote");
            /* for (ADEmpresa adempresa : (List<ADEmpresa>)bDL_C_SFEmpresasRemote.getADEmpresaFindAll()) {
                printADEmpresa(adempresa);
            } */
            /*System.out.println(bDL_C_SFEmpresasRemote.getNidParty("URP"));
            System.out.println (new Date());
                       
                       Calendar c = new GregorianCalendar(); 
                       
                       String dia, mes, annio;
                       
                       dia = Integer.toString(c.get(Calendar.DATE));
                       mes = Integer.toString(c.get(Calendar.MONTH));
                       annio = Integer.toString(c.get(Calendar.YEAR));
                       
                       System.out.println (dia + "/" + mes +"/" + annio);
*/
            List<BeanEmpresa> empresas = bDL_C_SFEmpresasRemote.getADEmpresas();
            for(int i = 0; i < empresas.size(); i++){
                System.out.println("empresa: " + empresas.get(i).getCRazonSocial());
                System.out.println("empresa: " + empresas.get(i).getAdParty().getNidParty());
                System.out.println("empresa: " + empresas.get(i).getAdParty().getCTipoParty());
            }
        } catch (CommunicationException ex) {
            System.out.println(ex.getClass().getName());
            System.out.println(ex.getRootCause().getLocalizedMessage());
            System.out.println("\n*** A CommunicationException was raised.  This typically\n*** occurs when the target WebLogic server is not running.\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printADEmpresa(ADEmpresa adempresa) {
        System.out.println("cDireccion = " + adempresa.getCDireccion());
        System.out.println("cPagWeb = " + adempresa.getCPagWeb());
        System.out.println("cRazonSocial = " + adempresa.getCRazonSocial());
        System.out.println("cRuc = " + adempresa.getCRuc());
        System.out.println("nidParty = " + adempresa.getNidParty());
        System.out.println("orderServicioList = " + adempresa.getOrderServicioList());
        System.out.println("guiasList = " + adempresa.getGuiasList());
        System.out.println("adParty = " + adempresa.getAdParty());
        System.out.println("manifiestosList = " + adempresa.getManifiestosList());
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        env.put(Context.PROVIDER_URL, "t3://10.1.1.108:7101");
        return new InitialContext( env );
    }
}
