package testing;

import java.math.BigDecimal;

import java.util.Hashtable;
import java.util.List;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import silat.servicios_negocio.BDLSF.IR.BDL_C_SFGastoRemoto;
import silat.servicios_negocio.Beans.BeanGasto;
import silat.servicios_negocio.Beans.BeanTipoGasto;
import silat.servicios_negocio.entidades.admin.ADGasto;
import silat.servicios_negocio.entidades.admin.ADTipoGasto;
import silat.servicios_negocio.util_formato.UtilsGeneral;

public class BDL_C_SFGastoRemotoClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            BDL_C_SFGastoRemoto bDL_C_SFGastoRemoto =
                (BDL_C_SFGastoRemoto)context.lookup("LUBAL_SIAT_APP-SILATNEGOCIO-BDL_C_SFGasto#silat.servicios_negocio.BDLSF.IR.BDL_C_SFGastoRemoto");
           
            ADTipoGasto TipoGasto= new ADTipoGasto();
            TipoGasto.setNidTiga(1);
            
            BeanGasto beanGasto=new  BeanGasto();
           // beanGasto.setTipoGasto(TipoGasto);
            beanGasto.setSimboloMonto("<");
            beanGasto.setDMontoGeneral(new BigDecimal(250.0));
            for (ADGasto adgasto :
                 (List<ADGasto>)bDL_C_SFGastoRemoto.findGastosByAttributes(beanGasto) /* FIXME: Pass parameters here */) {
                printADGasto(adgasto);  
                UtilsGeneral.depurar("RECORRIENDO EL FOr /////////");
            }
            
        } catch (CommunicationException ex) {
            System.out.println(ex.getClass().getName());
            System.out.println(ex.getRootCause().getLocalizedMessage());
            System.out.println("\n*** A CommunicationException was raised.  This typically\n*** occurs when the target WebLogic server is not running.\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printADGasto(ADGasto adgasto) {
        System.out.println("cidFactura = " + adgasto.getCidFactura());
        System.out.println("cimgrecibo = " + adgasto.getCimgrecibo());
        System.out.println("dMontoGeneral = " + adgasto.getDMontoGeneral());
        System.out.println("destino = " + adgasto.getDestino());
        System.out.println("nroCheque = " + adgasto.getNroCheque());
  //      System.out.println("blobImagenRecibo = " + adgasto.getBlobImagenRecibo());
        System.out.println("estadoRegistro = " + adgasto.getEstadoRegistro());
        System.out.println("cantper = " + adgasto.getCantper());
        System.out.println("fechaGasto = " + adgasto.getFechaGasto());
        System.out.println("nidGasto = " + adgasto.getNidGasto());
        System.out.println("nidProtra = " + adgasto.getNidProtra());
        System.out.println("modalidadPago = " + adgasto.getModalidadPago());
        System.out.println("utilServicioBasico = " + adgasto.getUtilServicioBasico());
        System.out.println("utilTipoCombustible = " + adgasto.getUtilTipoCombustible());
        System.out.println("utilTipoMantenimiento = " + adgasto.getUtilTipoMantenimiento());
        System.out.println("tipoGasto = " + adgasto.getTipoGasto());
        System.out.println("adFlota = " + adgasto.getAdFlota());
        System.out.println("c_detalle = " + adgasto.getC_detalle());
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        return new InitialContext( env );
    }
}
