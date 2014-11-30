package silat.servicios_negocio.LNSF.SFBean;


import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.io.InputStream;

import java.math.BigDecimal;

import java.util.Date;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.imageio.ImageIO;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFEmpresasLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFGuiaLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFItemXOrdsLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFManifiestoLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_T_SFGuiaLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_T_SFItemXOrdenServLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_T_SFOrdenServicioLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFOrdenServicioLocal;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanTRGuia;
import silat.servicios_negocio.Beans.BeanTRItem;
import silat.servicios_negocio.LNSF.IL.LN_C_SFCatalogoErroresLocal;
import silat.servicios_negocio.LNSF.IL.LN_C_SFManifiestoLocal;
import silat.servicios_negocio.LNSF.IL.LN_C_SFUtilsLocal;
import silat.servicios_negocio.LNSF.IL.LN_T_SFGuiaLocal;
import silat.servicios_negocio.LNSF.IL.LN_T_SFItemLocal;
import silat.servicios_negocio.LNSF.IR.LN_T_SFGuiaRemote;
import silat.servicios_negocio.LNSF.IR.LN_T_SFManifiestoLocal;
import silat.servicios_negocio.entidades.admin.ADEmpresa;
import silat.servicios_negocio.entidades.audsis.TROrdenServicio;
import silat.servicios_negocio.entidades.trans.TRGuia;
import silat.servicios_negocio.entidades.trans.TRItem;
import silat.servicios_negocio.entidades.trans.TRItemXOrds;
import silat.servicios_negocio.entidades.trans.TRManifiesto;
import silat.servicios_negocio.util_formato.Fecha.FechaUtiles;
import silat.servicios_negocio.util_formato.UtilsGeneral;

@Stateless(name = "LN_T_SFGuia", mappedName = "mapLN_T_SFGuia")
public class LN_T_SFGuiaBean implements LN_T_SFGuiaRemote, 
                                        LN_T_SFGuiaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFEmpresasLocal bdL_C_SFEmpresasLocal;
    @EJB
    private LN_C_SFUtilsLocal LN_C_SFUtilsLocal;
    @EJB
    private BDL_C_SFOrdenServicioLocal bdL_C_SFOrdenServicioLocal;
    @EJB
    private BDL_C_SFManifiestoLocal bdL_C_SFManifiestoLocal;
    @EJB
    private BDL_C_SFGuiaLocal bdL_C_SFGuiaLocal;
    @EJB
    private BDL_T_SFGuiaLocal bdL_T_SFGuiaLocal;
    @EJB
    private LN_T_SFItemLocal ln_T_SFItemLocal;
    @EJB
    private LN_C_SFCatalogoErroresLocal ln_C_SFCatalogoErroresLocal;
    @EJB
    private LN_C_SFManifiestoLocal ln_C_SFManifiestoLocal;
    @EJB
    private  LN_T_SFManifiestoLocal ln_T_SFManifiestoLocal;
    @EJB
    private BDL_T_SFItemXOrdenServLocal bdl_T_SFItemXOrdenServLocal;
    @EJB
    private BDL_C_SFItemXOrdsLocal bdl_C_SFItemXOrdsLocal;  
    
    public LN_T_SFGuiaBean() {
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public BeanTRGuia registrarGuia_LN(String cidGuia,int numPaquetes,String obs,String conf,String estGuia,
                                       Date fecEmis,//max 1 semana antes y <= fecDesp
                                       Date fecDesp,//hoy
                                       int nidRemitente,
                                       int nidOS,
                                       int nidManif,
                                       int nidFlota,
                                       int nidChofer,int nidDirecRemi,
                                       int nidDirecDest,int opc,List<BeanTRItem> lstItems,String codUn,String estadoManif,
                                       String imgGuiaProv,boolean cerrarOS,boolean enTransManif){
        BeanError beanError = new BeanError();
        TRGuia eGuia = new TRGuia();
        BeanTRGuia bTRGuia = new BeanTRGuia();
        String error = "000";
        error = validarFechasEmiDesp(fecEmis, fecDesp);
        if(error.equals("000")){
            try{
                if(opc == 1){//Insertar
                    eGuia.setNEstadoGuia("1");
                    eGuia.setCidGuia(cidGuia);
                }else{//Actualizar
                    eGuia = bdL_C_SFGuiaLocal.findTRGuiaById(cidGuia);
                    eGuia.setNEstadoGuia(estGuia);
                }
                ADEmpresa empresa = bdL_C_SFEmpresasLocal.getEmpresaById(new BigDecimal(nidRemitente));
                eGuia.setAdEmpresa(empresa);
                eGuia.setCConformidad(conf);
                eGuia.setCObservaciones(obs);
                eGuia.setFechaDespacho(fecDesp);
                eGuia.setFechaGuia(fecEmis);
                eGuia.setCidUnidadNegocio(codUn);
                eGuia.setNidChof(nidChofer);
                if(imgGuiaProv != null){
                    eGuia.setImgGuia(UtilsGeneral.imagen(imgGuiaProv));
                    /* if(!imgGuiaProv.equals("")){
                        byte[] byt = extractBytes(imgGuiaProv);
                        if(byt != null){
                            eGuia.setImgGuia(byt);
                        }
                    } */
                }
                eGuia.setNidDireccionDestino(nidDirecDest);
                eGuia.setNidDireccionRemitente(nidDirecRemi);
                eGuia.setNidFlota(nidFlota);
                eGuia.setNumPaquetes(numPaquetes);
                List<TROrdenServicio> ordenServicio = bdL_C_SFOrdenServicioLocal.findByCorrelativo(nidOS);
                if(cerrarOS){
                    ordenServicio.get(0).setCEstord("C");//CERRAR LA OS
                }
                /**Asociar la Guia a Crear con la OS Previa*/
                if(ordenServicio.get(0).getCDetalle().equals("SIN GUIAS")){
                    ordenServicio.get(0).setCDetalle("# "+eGuia.getCidGuia());
                }else {
                    ordenServicio.get(0).setCDetalle(ordenServicio.get(0).getCDetalle()+"-"+eGuia.getCidGuia());
                }         
                /***********************************/
                eGuia.setOrdenServicio(ordenServicio.get(0));
                if(nidManif != 0){
                    TRManifiesto manifiesto = bdL_C_SFManifiestoLocal.findTRManifiestoById(nidManif);
                    if(enTransManif){
                        manifiesto.setEstadoManifiestoNegocio("1");//cambiar a EN TRANSITO
                    }
                    eGuia.setTrManifiesto(manifiesto);
                }
              
                
                /**czavalacas 17.11.2014
                 * Codigo para acttualizar el estado de los items que se usaron de la OS a 1 (En Uso o Activo)*/
                
               
                List<TRItemXOrds> itemXord=bdl_C_SFItemXOrdsLocal.getTrItemOrdenServicio_BD(nidOS,0);
                if(lstItems!=null){
                    for(int i=0; i<lstItems.size(); i++){
                        if(lstItems.get(i).getNidItem()!=null){                          
                          for(int j=0; j<itemXord.size(); j++){                          
                            if(itemXord.get(j).getNidItem().intValue()==lstItems.get(i).getNidItem().intValue()){
                                itemXord.get(j).setCEstado("1");
                                itemXord.get(j).setCidGuia(cidGuia);
                                bdl_T_SFItemXOrdenServLocal.mergeTRItemXOrds(itemXord.get(j));
                               }
                            }
                           
                            } else{/**Al no tener un nidItem en el Bean Indica que es un item nuevo 
                                    *y lo asociamos a la OS*/
                                TRItemXOrds itmXOrd=new TRItemXOrds(); 
                                TROrdenServicio ordenServ=new TROrdenServicio();
                                ordenServ.setNidOrdnServ(nidOS);
                                itmXOrd.setTrOrdenServicio(ordenServ);
                                itmXOrd.setCCidGuiaRemitente(lstItems.get(i).getCCidGuiaRemitente());
                                itmXOrd.setCDescItem(lstItems.get(i).getCDescItem());
                                itmXOrd.setCUndMedida(lstItems.get(i).getCUndMedida());
                                itmXOrd.setDPeso(lstItems.get(i).getDPeso());
                                itmXOrd.setNCantidad(lstItems.get(i).getNCantidad());
                                itmXOrd.setOrden(nidOS);
                                itmXOrd.setCEstado("1");
                                itmXOrd.setCidGuia(cidGuia);
                                bdl_T_SFItemXOrdenServLocal.persistTRItemXOrds(itmXOrd);                             
                            }                     
                    }                
                }            
                                
                /***************************************/
                
                List<TRItem> itemsBefore = eGuia.getItemsList();
                if(opc==1){
                   List<TRItem> eItems = LN_C_SFUtilsLocal.beanItemToEntity(lstItems,eGuia);    
                    eGuia.setItemsList(eItems);
                }
                eGuia = bdL_T_SFGuiaLocal.registrarGuia_BD(eGuia, opc);                
           
                if(itemsBefore != null){
                    System.out.println("ENTRO:1::");
                    System.out.println("ENTRO:1::"+itemsBefore.size());
                    if(itemsBefore.size() > 0){
                        System.out.println("ENTRO:2::");
                        ln_T_SFItemLocal.borrarItems(lstItems,itemsBefore);
                    }
                }
                
                
                
                
                if(estadoManif != null){
                    BeanError bError = ln_C_SFManifiestoLocal.guiasOK(nidManif, codUn, cidGuia, 1);
                    if(bError != null){
                        if("000".equals(bError.getCidError())){//todas las guias OK, cambio de estado al manifiesto
                            ln_T_SFManifiestoLocal.cambiarEstadoManifiesto(nidManif, estadoManif);
                        }
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
                error = "LUB-0012";
            }
        }
        beanError = ln_C_SFCatalogoErroresLocal.getCatalogoErrores(error);
        bTRGuia.setBeanError(beanError);
        return bTRGuia;
    }
    
    public BeanTRGuia anularGuia(BeanTRGuia guia){
        BeanError beanError = new BeanError();
        TRGuia eGuia = new TRGuia();
        String error = "000";
        try{
            if("1".equals(guia.getCConformidad())){
                error = "LUB-0023";
            }else{
                String cidGuia = guia.getCidGuia().substring(guia.getCidGuia().indexOf("-") + 1,guia.getCidGuia().length());
                eGuia = bdL_C_SFGuiaLocal.findGuiaByUNCid(guia.getCidUnidadNegocio(),cidGuia);
                eGuia.setNEstadoGuia("0");
                eGuia = bdL_T_SFGuiaLocal.mergeTRGuia(eGuia);
            }
        }catch(Exception e){
            e.printStackTrace();
            error = "LUB-0022";
        }
        beanError = ln_C_SFCatalogoErroresLocal.getCatalogoErrores(error);
        guia.setBeanError(beanError);
        return guia;
    }
    
    public BeanTRGuia registrarGuiaAnulada(String cidGuia,
                                           String codUn,
                                           Date fecEmis,
                                           Date fecDesp){
        BeanError beanError = new BeanError();
        TRGuia eGuia = new TRGuia();
        BeanTRGuia bTRGuia = new BeanTRGuia();
        String error = "000";
        try{
            eGuia.setNEstadoGuia("0");
            eGuia.setCidGuia(cidGuia);
            eGuia.setCConformidad("1");
            eGuia.setFechaDespacho(fecDesp);
            eGuia.setFechaGuia(fecEmis);
            eGuia.setCidUnidadNegocio(codUn);
            ADEmpresa empresa = bdL_C_SFEmpresasLocal.getEmpresaById(new BigDecimal(6));
            eGuia.setAdEmpresa(empresa);
            List<TROrdenServicio> ordenServicio = bdL_C_SFOrdenServicioLocal.findByCorrelativo(1);
            eGuia.setOrdenServicio(ordenServicio.get(0));
            eGuia = bdL_T_SFGuiaLocal.registrarGuia_BD(eGuia,1);
        }catch(Exception e){
            e.printStackTrace();
            error = "LUB-0012";
        }finally{
            beanError = ln_C_SFCatalogoErroresLocal.getCatalogoErrores(error);
            bTRGuia.setBeanError(beanError);
            return bTRGuia;
        }
    }
    
    public String validarFechasEmiDesp(Date fecEmis,//max 1 semana antes y <= fecDesp
                                       Date fecDesp){//hoy
        String error = "000";
        if(fecEmis.after(fecDesp)){
            error = "LUB-0014";
            return error;
        }
        Date fecDespMenosUnaSemana = FechaUtiles.fechaHoyMasXdias(fecDesp,-20);
        if(fecEmis.before(fecDespMenosUnaSemana)){
            error = "LUB-0015";
            return error;
        }
        return error;
    }
    
    /**
     * Metodo para cambiar las fechas de una guia - Permiso 56
     * @param cidGuia codigo de la guia
     * @param codUn   numero de serie
     * @param fecEmis fecha de emision
     * @param fecDesp fecha de despacho
     * @return codigo de error 000 si grabo correctamente, 111 si hubo error
     */
    public String cambiarFechasGuiaPermiso56(String cidGuia,
                                             String codUn,
                                             Date fecEmis,
                                             Date fecDesp){
        try{
            TRGuia eGuia = bdL_C_SFGuiaLocal.findGuiaByUNCid(codUn,cidGuia);
            eGuia.setFechaGuia(fecEmis);
            eGuia.setFechaDespacho(fecDesp);
            eGuia = bdL_T_SFGuiaLocal.mergeTRGuia(eGuia);
            return "000";
        }catch(Exception e){
            e.printStackTrace();
            return "111";
        }
    }
    
    public static byte[] extractBytes(String ImageName) throws IOException {
        File imgPath = new File(ImageName);
        String extension = ImageName.substring(ImageName.lastIndexOf(".") + 1, ImageName.length());
        BufferedImage bufferedImage = ImageIO.read(imgPath);
        ByteOutputStream bos = null;
        try {
            bos = new ByteOutputStream();
            ImageIO.write(bufferedImage,extension, bos);
        } finally {
            try {
                bos.close();
            } catch (Exception e) {
            }
        }
        return bos == null ? null : bos.getBytes();
    }
    
    public void cambiarEstadoWebMovil(String cidGuia, String ruta, byte[] bytes, int valoracion , String comentario){
        TRGuia eGuia = bdL_C_SFGuiaLocal.findTRGuiaById(cidGuia);
        eGuia.setCConformidad("1");
        eGuia.setImgGuia(bytes);
        eGuia.setImgGuiaProv(ruta);
        eGuia.setValoracion(valoracion);
        eGuia.setComentario(comentario);
        bdL_T_SFGuiaLocal.mergeTRGuia(eGuia);
    }
}