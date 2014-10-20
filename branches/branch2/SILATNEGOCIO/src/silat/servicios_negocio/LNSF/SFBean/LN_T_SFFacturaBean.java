package silat.servicios_negocio.LNSF.SFBean;

import java.math.BigDecimal;

import java.sql.Timestamp;

import java.util.Arrays;
import java.util.Date;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFFacturaLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFGuiaLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFUtilsLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_T_SFFacturaLocal;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanFactura;
import silat.servicios_negocio.LNSF.IL.LN_C_SFCatalogoErroresLocal;
import silat.servicios_negocio.LNSF.IL.LN_T_SFFacturaLocal;
import silat.servicios_negocio.LNSF.IR.LN_T_SFFacturaRemote;
import silat.servicios_negocio.entidades.trans.TRFactura;
import silat.servicios_negocio.entidades.trans.TRGuia;
import silat.servicios_negocio.entidades.trans.TRManifiesto;
import silat.servicios_negocio.util_formato.Caracter.FormatoLetra;
import silat.servicios_negocio.util_formato.Fecha.FechaUtiles;
import silat.servicios_negocio.util_formato.UtilsGeneral;

@Stateless(name = "LN_T_SFFactura", mappedName = "mapLN_T_SFFactura")
public class LN_T_SFFacturaBean implements LN_T_SFFacturaRemote, 
                                           LN_T_SFFacturaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_T_SFFacturaLocal bdL_T_SFFacturaLocal;
    @EJB
    private LN_C_SFCatalogoErroresLocal ln_C_SFCatalogoErroresLocal;
    @EJB
    private BDL_C_SFFacturaLocal bdL_C_SFFacturaLocal;
    @EJB
    private BDL_C_SFGuiaLocal bdL_C_SFGuiaLocal;
    
    public LN_T_SFFacturaBean() {
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public BeanFactura registrarFactura_LN(String tipoFactura,
                                           Date fechaFactura,
                                           BigDecimal subTotal,
                                           int estadoFactura,
                                           String cidSerie,
                                           String cidRepo,
                                           String guias,
                                           BigDecimal nidParty,
                                           String cliente,
                                           String ruc,
                                           String nidOServ, 
                                           String direccion,
                                           String tipFactura,
                                           Long nidPreFactura,
                                           String guiasForReporte,
                                           boolean isEditable,
                                           String contenido){
        BeanError beanError = new BeanError();
        TRFactura eFactura = new TRFactura();
        BeanFactura bFactura = new BeanFactura();
        String error = "000";
        try{
            if(guias != null && tipFactura.equals("1")){
                guias = uniqueVector(guias);
            }
            if(nidOServ != null){
                nidOServ = uniqueVector(nidOServ);
            }
            eFactura.setCidUnidadNegocio(cidSerie);
            eFactura.setDSubTotal(subTotal);
            eFactura.setFechaFactura(fechaFactura);
            eFactura.setNidOS(nidOServ);
            eFactura.setEditable(isEditable == true ? "1" : "0");
            if(contenido!=null){
                eFactura.setContenido(contenido);
            }else{
                eFactura.setContenido("");
            }
            eFactura.setTipoFactura(tipFactura);//3 tipos, 1 = factura normal, 2 = factura de guias, 3 = factura con 1 linea
            eFactura.setDireccion(direccion);
            eFactura.setNEstadoFactura(estadoFactura);
            eFactura.setNTipoFactura(tipoFactura);
            eFactura.setCidRepo(cidRepo);
            eFactura.setGuiasForReporte(guiasForReporte);
            eFactura.setRuc(ruc);
            eFactura.setNidPreFactura(nidPreFactura);
            eFactura.setGuias(guias);
            eFactura.setNidParty(nidParty);
            eFactura.setCliente(cliente);
            eFactura.setCCodFactura("1");//para que no se caiga
            eFactura.setDTotal(new BigDecimal(1));//para que no se caiga         
            eFactura = bdL_T_SFFacturaLocal.persistTRFactura(eFactura);
        }catch(Exception e){
            e.printStackTrace();
            error = "LUB-0019";
        }
        beanError = ln_C_SFCatalogoErroresLocal.getCatalogoErrores(error);
        bFactura.setBeanError(beanError);
        return bFactura;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public BeanFactura anularFactura_LN(String comentarioAnular,
                                        Long nidFactura){
        BeanError beanError = new BeanError();
        BeanFactura bFactura = new BeanFactura();
        String error = "000";
        try{
            TRFactura eFactura = bdL_C_SFFacturaLocal.findTRFacturaById(nidFactura);
            eFactura.setNEstadoFactura(3);
            eFactura.setComentarioAnulacion(comentarioAnular.toUpperCase());
            eFactura = bdL_T_SFFacturaLocal.mergeTRFactura(eFactura);
        }catch(Exception e){
            e.printStackTrace();
            error = "LUB-0020";
        }
        beanError = ln_C_SFCatalogoErroresLocal.getCatalogoErrores(error);
        bFactura.setBeanError(beanError);
        return bFactura;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public BeanFactura actualizarFactura_LN(String detalle,
                                            Date fechaFactura,
                                            Long nidFactura,
                                            boolean editarFecha){
        BeanError beanError = new BeanError();
        BeanFactura bFactura = new BeanFactura();
        String error = "000";
        try{
            TRFactura eFactura = bdL_C_SFFacturaLocal.findTRFacturaById(nidFactura);
            if(!editarFecha){//Si no tiene el permiso 57 valida las fechas. dfloresgonz 19.06.2014
                error = this.validarFechaFacturaDesp(fechaFactura);
            }     
            if("000".equals(error)){
                eFactura.setFechaFactura(fechaFactura);
                eFactura.setDetalle(detalle);
                eFactura = bdL_T_SFFacturaLocal.mergeTRFactura(eFactura);
            }
        }catch(Exception e){
            e.printStackTrace();
            error = "LUB-0020";
        }
        beanError = ln_C_SFCatalogoErroresLocal.getCatalogoErrores(error);
        bFactura.setBeanError(beanError);
        return bFactura;
    }
    
    public String validarFechaFacturaDesp(Date fechaFactura){//hoy
        String error = "000";
        Date hoy = FechaUtiles.fechaActual();
        Date fecDespMenosUnMES = FechaUtiles.fechaHoyMasXdias(hoy,-30);
        if(fechaFactura.before(fecDespMenosUnMES)){
            error = "LUB-0038";
            return error;
        }
        Date fecDespMasUnMES = FechaUtiles.fechaHoyMasXdias(hoy,30);
        if(fechaFactura.after(fecDespMasUnMES)){
            error = "LUB-0039";
            return error;
        }
        return error;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public BeanFactura pagarFactura_LN(Long nidFactura){
        BeanError beanError = new BeanError();
        BeanFactura bFactura = new BeanFactura();
        String error = "000";
        try{
            TRFactura eFactura = bdL_C_SFFacturaLocal.findTRFacturaById(nidFactura);
            String ordServGuias = eFactura.getNidOS();
            String newOS = "";
            if(ordServGuias != null){
                String[] ordServVec = ordServGuias.split(",");
                if(ordServVec.length > 0){
                    Map mapaTodoNoFacturados = new HashMap();
                    Map mapaGuiasThisFactura = new HashMap();
                    for(int i = 0; i < ordServVec.length; i++){
                        int nidOServ = Integer.parseInt(ordServVec[i]);
                        int cantGuiaVig_NoFact_x_OS = bdL_C_SFGuiaLocal.getCountGuiasVigentesByOS_No_Facturadas(nidOServ);
                        int cantGuiasVig_ByFactura_x_OS = bdL_C_SFGuiaLocal.getCountGuiasVigentesByFacturaByOrdenServ(nidFactura,nidOServ);
                        mapaTodoNoFacturados.put(nidOServ,cantGuiaVig_NoFact_x_OS);
                        mapaGuiasThisFactura.put(nidOServ,cantGuiasVig_ByFactura_x_OS);
                    }
                    
                    Iterator it = mapaTodoNoFacturados.entrySet().iterator();
                    Iterator it2 = mapaGuiasThisFactura.entrySet().iterator();
                    while (it.hasNext() && it2.hasNext()) {
                        Map.Entry mapaTodosNoFact = (Map.Entry) it.next();
                        Map.Entry mapaThisFact = (Map.Entry) it2.next();
                        if(mapaTodosNoFact.getValue().equals(mapaThisFact.getValue())){
                            //La cantidad de guias que faltan facturar es igual a la cantidad de guias que tiene esta factura x lo tanto se finalizara la OS
                            newOS = newOS.concat(mapaTodosNoFact.getKey()+"");
                            if(it.hasNext()){
                                newOS = newOS.concat(",");
                            }
                        }
                    }
                     if(newOS.length() > 0){
                         if(newOS.lastIndexOf(",") + 1 == newOS.length()){//el ultimo caracter es una coma
                             newOS = newOS.substring(0,newOS.length() - 1);
                         }
                     }
                }
            }
            eFactura.setNidOS(newOS);
            eFactura.setNEstadoFactura(1);
            eFactura.setFechaPago(new Timestamp(System.currentTimeMillis()));
            eFactura = bdL_T_SFFacturaLocal.mergeTRFactura(eFactura);
        }catch(Exception e){
            e.printStackTrace();
            error = "LUB-0020";
        }
        beanError = ln_C_SFCatalogoErroresLocal.getCatalogoErrores(error);
        bFactura.setBeanError(beanError);
        return bFactura;
    }
    
    public String uniqueVector(String strEva){
        String[] guiaArry = strEva.split(",");
        Set<String> s = new HashSet<String>(Arrays.asList(guiaArry));
        Object[] arr =  s.toArray();
        String[] stringArray = FormatoLetra.convert(arr,String.class);
        strEva = "";
        for(int i = 0; i < stringArray.length; i++){
            strEva = strEva.concat(stringArray[i]);
            if(i < stringArray.length - 1){
                strEva = strEva.concat(",");
            }
        }
        if(strEva.lastIndexOf(",") + 1 == strEva.length()){//el ultimo caracter es una coma
            strEva = strEva.substring(0,strEva.length() - 1);
        }
        return strEva;
    }
}