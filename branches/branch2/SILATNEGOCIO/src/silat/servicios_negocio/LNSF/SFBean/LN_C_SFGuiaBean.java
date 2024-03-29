package silat.servicios_negocio.LNSF.SFBean;

import java.math.BigDecimal;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFGuiaLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFItemLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFUtilsLocal;
import silat.servicios_negocio.Beans.BeanConstraint;
import silat.servicios_negocio.Beans.BeanDireccion;
import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.Beans.BeanFactura;
import silat.servicios_negocio.Beans.BeanManifiesto;
import silat.servicios_negocio.Beans.BeanOrdenServicio;
import silat.servicios_negocio.Beans.BeanTRGuia;
import silat.servicios_negocio.Beans.BeanTRItem;
import silat.servicios_negocio.LNSF.IL.LN_C_SFDireccionLocal;
import silat.servicios_negocio.LNSF.IL.LN_C_SFFlotaLocal;
import silat.servicios_negocio.LNSF.IL.LN_C_SFGuiaLocal;
import silat.servicios_negocio.LNSF.IR.LN_C_SFDireccionRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFGuiaRemote;
import silat.servicios_negocio.entidades.trans.TRGuia;
import silat.servicios_negocio.entidades.trans.TRItem;
import silat.servicios_negocio.entidades.trans.TRManifiesto;
import silat.servicios_negocio.util_formato.UtilsGeneral;

@Stateless(name = "LN_C_SFGuia", mappedName = "mapLN_C_SFGuia")
public class LN_C_SFGuiaBean implements LN_C_SFGuiaRemote, 
                                        LN_C_SFGuiaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFGuiaLocal bdL_C_SFGuiaLocal; 
    @EJB
    private BDL_C_SFUtilsLocal bdL_C_SFUtilsLocal;
    @EJB
    private LN_C_SFDireccionLocal ln_C_SFDireccionLocal;
    @EJB
    private BDL_C_SFItemLocal bdl_C_SFItemLocal;

    public LN_C_SFGuiaBean() {
    }
    
    public List<BeanTRGuia> getGuiasEstadisticas(BeanTRGuia filtro){
        return converter(bdL_C_SFGuiaLocal.findGuiasFiltered(filtro));
    }
    
    public List<BeanTRGuia> converter(List<TRGuia> entityList){
        List<BeanTRGuia> lista = new ArrayList<BeanTRGuia>();
        for(TRGuia entity : entityList ){
            BeanTRGuia bean = new BeanTRGuia();
            bean.setCidGuia(entity.getCidGuia());
            bean.setFechaGuia(entity.getFechaGuia());
            bean.setValoracion(entity.getValoracion());
            lista.add(bean);
        }
        return lista;
    }
    
    public List<BeanTRGuia> findGuiasByAttr_LN(String cidGuia,Date fecEmisMin,Date fecEmisMax,Date fecDespMin, 
                                               Date fecDespMax,String empCliente,String empProvCarga,String estGuia,
                                               String hasManif,Integer nidManif,String prov,String cObservaciones,
                                               String nEstadoGuia,int nidOS,String detOS,String hasFactura,String codFactura,
                                               int nEstadoFactura,BigDecimal nidParty,String descCidGuiaRemi_ITEM,String descCidGuia_ITEM){
        try{      
           return getListaGuias(bdL_C_SFGuiaLocal.findGuiasByAttributes_BD(cidGuia,fecEmisMin,fecEmisMax, fecDespMin, 
                                                fecDespMax, empCliente, empProvCarga, estGuia,
                                                hasManif, nidManif, prov, cObservaciones,
                                                nEstadoGuia, nidOS, detOS, hasFactura, codFactura,
                                                nEstadoFactura, nidParty, descCidGuiaRemi_ITEM, descCidGuia_ITEM));
           
        }catch(Exception e){
            List<BeanTRGuia> getListaGuias = new ArrayList<BeanTRGuia>();
            return getListaGuias;
        }
    }
    
    public List<BeanTRGuia> findGuiasByManifiesto_LN(Integer nidManif){
        try{
            return getListaGuiasByManifiesto(bdL_C_SFGuiaLocal.findGuiasByManifiesto(nidManif));
        }catch(Exception e){
            return new ArrayList<BeanTRGuia>();
        }
    }
    
    public List<BeanTRGuia> findGuiasByNidCliente_LN(int nidCliente){
        List<BeanTRGuia> listaGuias = getListaGuias_PorFacturar(bdL_C_SFGuiaLocal.findGuiasByNidCliente(nidCliente));
        return listaGuias;
    }
    
    public List<BeanTRGuia> findGuiasByNidClienteParaPreFactura_LN(int nidCliente){
        List<BeanTRGuia> listaGuias = getListaGuias_PorFacturar(bdL_C_SFGuiaLocal.findGuiasByNidClienteParaPreFactura(nidCliente));
        return listaGuias;
    }
    
    public List<BeanTRItem> passItems(List<TRItem> items){
        List<BeanTRItem> it = new ArrayList<BeanTRItem>();
        for(TRItem entidad:items){
            BeanTRItem bean = new BeanTRItem();
            bean.setCCidGuiaRemitente(entidad.getCCidGuiaRemitente());
            bean.setCDescItem(entidad.getCDescItem());
            bean.setCUndMedida(entidad.getCUndMedida());
            bean.setDPeso(entidad.getDPeso());
            bean.setIdItem(entidad.getNidItem());
            bean.setNCantidad(entidad.getNCantidad());
            bean.setNidItem(entidad.getNidItem());
            bean.setOrden(entidad.getOrden());
            
            it.add(bean);
        }
        return it;
    }
    
    public List<BeanTRGuia> getListaGuias(List<TRGuia> listGuia){
        try{
        List<BeanTRGuia> listBeanGuias = new ArrayList<BeanTRGuia>();            
        int idx = 0;
        for(TRGuia entidad:listGuia){
            BeanTRGuia beanGuia = new BeanTRGuia();
            BeanOrdenServicio beanOrdenervicio = new BeanOrdenServicio();
            BeanEmpresa beanEmpOrdenServ = new BeanEmpresa();
            BeanEmpresa beanEmpGuia = new BeanEmpresa();
            BeanManifiesto beanManifGuia = new BeanManifiesto();
            BeanEmpresa beanManifManif = new BeanEmpresa();
            BeanFactura beanFactGuia = new BeanFactura();
            
            //Solo Con TRGuia
            beanGuia.setCidGuia(entidad.getCidUnidadNegocio() +"-"+ entidad.getCidGuia());
            beanGuia.setCidGuiaToImg(entidad.getCidGuia());
            beanGuia.setCidUnidadNegocio(entidad.getCidUnidadNegocio());
            beanGuia.setNativeCidGuia(entidad.getCidGuia());
            beanGuia.setFechaGuia(entidad.getFechaGuia());
            beanGuia.setFechaDespacho(entidad.getFechaDespacho());
            //beanGuia.setItemsLista(passItems(entidad.getItemsList()));
            beanGuia.setNumPaquetes(entidad.getNumPaquetes());
            //CLIENTE
            beanGuia.setEmpCliente(entidad.getOrdenServicio().getAdEmpresa().getCRazonSocial());
            beanGuia.setCObservaciones(entidad.getCObservaciones());
            beanGuia.setNidFlota(entidad.getNidFlota());
            beanGuia.setImgGuiaProv(entidad.getImgGuiaProv());
            beanGuia.setImgGuiaProvedor(entidad.getImgGuiaProvedor());
            beanGuia.setNidDireccionRemitente(entidad.getNidDireccionRemitente());
            beanGuia.setNidDireccionDestino(entidad.getNidDireccionDestino());
            BeanConstraint constr = bdL_C_SFUtilsLocal.getCatalogoConstraints("C_CONFORMIDAD","TRMGUIA",entidad.getCConformidad());
            beanGuia.setDescConformidad(constr.getCDescrip());
            beanGuia.setCConformidad(entidad.getCConformidad());
            if(entidad.getImgGuiaProv()!=null || entidad.getImgGuia()!=null){
                beanGuia.setHasImgGuia("SI");
            }
            if(entidad.getImgGuiaProv()==null && entidad.getImgGuia()==null){
                beanGuia.setHasImgGuia("NO");
                }            
            if(entidad.getImgGuiaProvedor()!=null){
                beanGuia.setHasImgGuiaProv("SI");
            }else{
                beanGuia.setHasImgGuiaProv("NO");
                }            
            /***Nuevo para Direcciones**/   
            if(entidad.getNidDireccionDestino()!=null){
            beanGuia.setCDireccionDestino(ln_C_SFDireccionLocal.getDescripcionDirecByNid(entidad.getNidDireccionDestino()));
            }
            if(entidad.getNidDireccionRemitente()!=null){
            beanGuia.setCDireccionRemitente(ln_C_SFDireccionLocal.getDescripcionDirecByNid(entidad.getNidDireccionRemitente()));
            }
                /****/  
                //solo con Empresa-TRGuia
                beanEmpGuia.setCRazonSocial(entidad.getAdEmpresa().getCRazonSocial());
                beanEmpGuia.setCRuc(entidad.getAdEmpresa().getCRuc());
                beanEmpGuia.setNidParty(entidad.getAdEmpresa().getNidParty());
                beanGuia.setAdEmpresa(beanEmpGuia);
            
            //Solo Con Orden Servicio
            beanOrdenervicio.setCDetalle(entidad.getOrdenServicio().getCDetalle());
            beanOrdenervicio.setNidOrdnServ(entidad.getOrdenServicio().getNidOrdnServ());
            beanOrdenervicio.setFecOrdnServ(entidad.getOrdenServicio().getFecOrdnServ());
                //Solo con Empresa-OrdenServicio
                beanEmpOrdenServ.setCRazonSocial(entidad.getOrdenServicio().getAdEmpresa().getCRazonSocial());
                beanEmpOrdenServ.setCRuc(entidad.getOrdenServicio().getAdEmpresa().getCRuc());
                beanEmpOrdenServ.setNidParty(entidad.getOrdenServicio().getAdEmpresa().getNidParty());
                beanOrdenervicio.setAdEmpresa(beanEmpOrdenServ);
            beanGuia.setOrdenServicio(beanOrdenervicio);
            
            //Solo Con Manifiesto
            if(entidad.getTrManifiesto()!=null){
            beanManifGuia.setNidManifiesto(entidad.getTrManifiesto().getNidManifiesto());
            beanManifGuia.setFechaManifiesto(entidad.getTrManifiesto().getFechaManifiesto());
            beanManifGuia.setNFletePactado(entidad.getTrManifiesto().getNFletePactado());
            beanManifGuia.setNAdelanto(entidad.getTrManifiesto().getNAdelanto());
            beanManifGuia.setIgv(entidad.getTrManifiesto().getIgv());
            beanManifGuia.setNidFlota(entidad.getTrManifiesto().getNidFlota());
            beanManifGuia.setNidChof(entidad.getTrManifiesto().getNidChof());
            beanManifGuia.setDetraccion(entidad.getTrManifiesto().getDetraccion());
            beanManifGuia.setDetraccionReal(entidad.getTrManifiesto().getDetraccionReal());
            beanManifGuia.setCTipoDoc(entidad.getTrManifiesto().getCTipoDoc());
            beanManifGuia.setCObservaciones(entidad.getTrManifiesto().getCObservaciones());
                //Solo con Manifiesto-Manifiesto
                beanManifManif.setCRazonSocial(entidad.getTrManifiesto().getTrManifiesto().getCRazonSocial());
                beanManifManif.setCRuc(entidad.getTrManifiesto().getTrManifiesto().getCRuc());
                beanManifManif.setNidParty(entidad.getTrManifiesto().getTrManifiesto().getNidParty());
                beanManifGuia.setTrManifiesto(beanManifManif);
            beanGuia.setTrManifiesto(beanManifGuia);
            }
            //Solo Con Factur
            if(entidad.getTrFactura()!=null){
            beanFactGuia.setCidUnidadNegocio(entidad.getTrFactura().getCidUnidadNegocio());            
            beanFactGuia.setCCodFactura(entidad.getTrFactura().getCCodFactura());
            beanGuia.setTrFactura(beanFactGuia);
            }
            if("0".equals(entidad.getNEstadoGuia())){
                beanGuia.setStyleAnulado("background-color:Red;color:White;");
            }
            if(entidad.getImgGuia()!=null){
                beanGuia.setImgGuia(entidad.getImgGuia());
            }
            listBeanGuias.add(beanGuia); 
            if((idx + 1) < listGuia.size()){
                int nextCidGuia = new Integer(listGuia.get((idx + 1 )).getCidGuia());
                int thisGuia = new Integer(entidad.getCidGuia());
                int cantGuiasFaltantes = thisGuia - nextCidGuia;
                if(cantGuiasFaltantes > 1){//Quiere decir que faltan
                    BeanTRGuia g = null;
                    for(int i = 0 ; i < cantGuiasFaltantes - 1; i++){
                        g = new BeanTRGuia();
                        String newCidGuia = ""+((thisGuia - (i + 1)));
                        if(newCidGuia.length() < 6){
                            int cantCeros = 6 - newCidGuia.length();
                            String ceros = "";
                            for(int j = 0 ; j < cantCeros; j++){
                                ceros = "0";
                            }
                            newCidGuia = ceros + newCidGuia;
                        }
                        if(!bdL_C_SFGuiaLocal.isGuiaExistente("001", newCidGuia)){
                            g.setCidGuia("001-"+newCidGuia);
                            g.setCidUnidadNegocio("001");
                            g.setNEstadoGuia("0");
                            g.setEmpCliente("GUIA AUN NO REGISTRADA");
                            g.setStyleAnulado("background-color:Grey;color:Black;");
                            listBeanGuias.add(g);
                        }
                    }
                }
            }
            idx++;
        }
            
         return listBeanGuias;   
    }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<BeanTRGuia>();
        } 
    }
    
    public List<BeanTRGuia> getListaGuiasByManifForVerificacionGuiasOK(List<TRGuia> lstGuia){
        try {
            List<BeanTRGuia> lstBeanGuias = new ArrayList<BeanTRGuia>();
            BeanTRGuia beanGuia = null;
            for (TRGuia guia : lstGuia) {
                beanGuia = new BeanTRGuia();
                beanGuia.setCConformidad(guia.getCConformidad());
                beanGuia.setCObservaciones(guia.getCObservaciones());
                
                String newstring = new SimpleDateFormat("yyyy-MM-dd").format(guia.getFechaGuia());
                beanGuia.setFechaGuiaWebMovilEmp(newstring);
                
                beanGuia.setFechaGuia(guia.getFechaGuia());
                beanGuia.setCidGuia(guia.getCidUnidadNegocio() + "-" + guia.getCidGuia());
                lstBeanGuias.add(beanGuia);
            }
            return lstBeanGuias;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<BeanTRGuia>();
        }
    }
    
    public List<BeanTRGuia> getListaGuiasByManifiesto(List<TRGuia> lstGuia){
        try{
            MapperIF mapper = new DozerBeanMapper();
            List<BeanTRGuia> lstBeanGuias = new ArrayList<BeanTRGuia>();
            BeanTRGuia beanGuias = new BeanTRGuia();
            for(TRGuia guia : lstGuia){
                beanGuias = (BeanTRGuia) mapper.map(guia,BeanTRGuia.class);
                BeanConstraint constr = bdL_C_SFUtilsLocal.getCatalogoConstraints("C_CONFORMIDAD","TRMGUIA",guia.getCConformidad());
                beanGuias.setDescConformidad(constr.getCDescrip());
                if(guia.getOrdenServicio().getAdEmpresa() != null){
                    beanGuias.setEmpCliente(guia.getOrdenServicio().getAdEmpresa().getCRazonSocial());    
                }
                beanGuias.setCidGuia(beanGuias.getCidUnidadNegocio()+"-"+beanGuias.getCidGuia());
                if(guia.getNidDireccionDestino() != null && guia.getNidDireccionRemitente() != null){
                    List<BeanDireccion> direcs = ln_C_SFDireccionLocal.getDireccionByProp_LN(guia.getNidDireccionDestino(),null,null);
                    if(direcs != null){
                        if(direcs.size() > 0){
                            beanGuias.setCDireccionDestino(direcs.get(0).getCDireccion());
                        }
                    }
                    direcs = ln_C_SFDireccionLocal.getDireccionByProp_LN(guia.getNidDireccionRemitente(),null,null);
                    if(direcs != null){
                        if(direcs.size() > 0){
                            beanGuias.setCDireccionRemitente(direcs.get(0).getCDireccion());
                        }
                    }    
                }
                if("0".equals(guia.getNEstadoGuia())){
                    beanGuias.setStyleAnulado("background-color:Red;color:White;");
                }
                beanGuias.setItemsLista(this.entityItemListtoBean(guia.getItemsList()));
                beanGuias.setPrecio(new BigDecimal(0));
                lstBeanGuias.add(beanGuias);
            }
            return lstBeanGuias;
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<BeanTRGuia>();
        }
    }
    
    public List<BeanTRGuia> getListaGuias_PorFacturar(List<TRGuia> lstGuia){
        try{
      
          List<BeanTRGuia> lstBeanGuias = new ArrayList<BeanTRGuia>();      
          for(TRGuia guia : lstGuia){
              BeanTRGuia beanGuias = new BeanTRGuia();
 
              beanGuias.setFechaGuia(guia.getFechaGuia());
              beanGuias.setFechaDespacho(guia.getFechaDespacho());
              
              BeanOrdenServicio orden=new BeanOrdenServicio();
              orden.setNidOrdnServ(guia.getOrdenServicio().getNidOrdnServ());
              beanGuias.setOrdenServicio(orden);
              
              BeanEmpresa empresa=new BeanEmpresa();
              empresa.setCRazonSocial(guia.getAdEmpresa().getCRazonSocial());
              beanGuias.setAdEmpresa(empresa);
              
              beanGuias.setCObservaciones(guia.getCObservaciones());
              
              BeanConstraint constr = bdL_C_SFUtilsLocal.getCatalogoConstraints("C_CONFORMIDAD","TRMGUIA",guia.getCConformidad());
              beanGuias.setDescConformidad(constr.getCDescrip());
              if(guia.getOrdenServicio().getAdEmpresa() != null){
                  beanGuias.setEmpCliente(guia.getOrdenServicio().getAdEmpresa().getCRazonSocial());    
              }
              
              beanGuias.setNativeCidGuia(guia.getCidGuia());
              beanGuias.setCidGuia(guia.getCidUnidadNegocio()+"-"+guia.getCidGuia());
                              
              if(guia.getNidDireccionDestino()!=null){
              beanGuias.setNidDireccionDestino(guia.getNidDireccionDestino());
              beanGuias.setCDireccionDestino(ln_C_SFDireccionLocal.getDescripcionDirecByNid(guia.getNidDireccionDestino()));
              }
              if(guia.getNidDireccionRemitente()!=null){
              beanGuias.setNidDireccionRemitente(guia.getNidDireccionRemitente());
              beanGuias.setCDireccionRemitente(ln_C_SFDireccionLocal.getDescripcionDirecByNid(guia.getNidDireccionRemitente()));
              }     
              beanGuias.setPrecio(new BigDecimal(0));
              lstBeanGuias.add(beanGuias);
          }
          return lstBeanGuias;
        }catch(Exception e){
          e.printStackTrace();
          return new ArrayList<BeanTRGuia>();
        }
    }
    
    public List<BeanTRGuia> getGuiasByManifiesto_LN(int nidManifiesto){
        return this.getListaGuiasByManifForVerificacionGuiasOK(bdL_C_SFGuiaLocal.findGuiasByManifiesto(nidManifiesto));
    }
    
    public List<BeanTRGuia> getGuiasByOrdenServicio(int nidOS){
        String cidGuia = null;
        Date fecEmisMin = null;
        Date fecEmisMax = null;
        Date fecDespMin = null; 
        Date fecDespMax = null;
        String empCliente = null;
        String empProvCarga = null;
        String estGuia = null;
        String hasManif = null;
        Integer nidManif = null;
        String prov = null;
        String cObservaciones  = null;
        String nEstadoGuia = null;
        String detOS = null;
        String hasFactura = null;
        String codFactura = null;
        int nEstadoFactura = 0;
        BigDecimal nidParty = null;
        String descCidGuiaRemi_ITEM = null;
        String descGuia_ITEM = null;
        try{
            BeanTRGuia beanGuia = new BeanTRGuia();
            if(nidOS != 0){
                BeanOrdenServicio beanOS = new BeanOrdenServicio();
                beanOS.setNidOrdnServ(nidOS);
                beanGuia.setOrdenServicio(beanOS);
                beanGuia.setCConformidad("1");
                beanGuia.setHasFactura("0");
            }
            List<BeanTRGuia> getListaGuias = getListaGuias(bdL_C_SFGuiaLocal.findGuiasByAttributes_BD( cidGuia, fecEmisMin, fecEmisMax, fecDespMin, 
                                                fecDespMax, empCliente, empProvCarga, estGuia,
                                                hasManif, nidManif, prov, cObservaciones,
                                                nEstadoGuia, nidOS, detOS, hasFactura, codFactura,
                                                nEstadoFactura, nidParty, descCidGuiaRemi_ITEM,descGuia_ITEM ));
            return getListaGuias;
        }catch(Exception e){
            List<BeanTRGuia> getListaGuias = new ArrayList<BeanTRGuia>();
            return getListaGuias;
        }
    }
    
    public List<BeanConstraint> getListConformidad(){
        return bdL_C_SFUtilsLocal.getListConstraints("C_CONFORMIDAD","TRMGUIA");
    }
    
    public List<BeanConstraint> getListADMCONS(String nombreTabla, String nombreCampo){
        return bdL_C_SFUtilsLocal.getListConstraints(nombreCampo,nombreTabla);
    }
    
    public List<BeanTRItem> entityItemListtoBean(List<TRItem> itemsList){
        MapperIF mapper = new DozerBeanMapper();
        List<BeanTRItem> itemsListBeans = new ArrayList<BeanTRItem>();
        Collections.sort(itemsList, new Comparator<TRItem>() {
            public int compare(TRItem s1,TRItem s2) {
                return s1.getOrden().compareTo(s2.getOrden());
            }
        });
        if(itemsList != null){
            if(itemsList.size() > 0){
                int idItem = 0;
                for(TRItem item : itemsList){
                    BeanTRItem bItem = new BeanTRItem();
                    bItem = (BeanTRItem) mapper.map(item,BeanTRItem.class);
                    bItem.setIdItem(new BigDecimal(idItem));
                    itemsListBeans.add(bItem);
                    idItem++;
                }
            }
        }
        return itemsListBeans;
    }
    public List<BeanTRGuia> guiasByNidParty(int nidCliente){
        System.out.println(":::ENTRO 0:::");
        List<TRGuia> lstGuia = bdL_C_SFGuiaLocal.guiasPorParty(nidCliente);
        if(lstGuia!=null){
        System.out.println("::::::"+lstGuia.size());}
        List<BeanTRGuia> lstBeanGuias = new ArrayList<BeanTRGuia>();
        for(TRGuia guia : lstGuia){
                BeanTRGuia beanGuias = new BeanTRGuia();
                BeanEmpresa beanEm = new BeanEmpresa();
                BeanEmpresa beanEm1 = new BeanEmpresa();
                BeanManifiesto beanMan = new BeanManifiesto();
            
                beanGuias.setCidGuia(guia.getCidGuia());
                beanGuias.setCObservaciones(guia.getCObservaciones());
                beanGuias.setCConformidad("Pendiente");
                beanGuias.setFechaGuia(guia.getFechaGuia());
                beanGuias.setEmpCliente(guia.getOrdenServicio().getAdEmpresa().getCRazonSocial());
                beanGuias.setItemsLista(this.itemsLista(guia.getItemsList()));
                beanGuias.setNumPaquetes(guia.getNumPaquetes());
            
                beanEm.setCRazonSocial(guia.getAdEmpresa().getCRazonSocial());
                beanGuias.setAdEmpresa(beanEm);
            
                beanEm1.setCRazonSocial(guia.getTrManifiesto().getTrManifiesto().getCRazonSocial());
                beanMan.setTrManifiesto(beanEm1);
                beanMan.setNidManifiesto(guia.getTrManifiesto().getNidManifiesto());
                beanGuias.setTrManifiesto(beanMan);
            
            
                List<BeanDireccion> direcs = ln_C_SFDireccionLocal.getDireccionByProp_LN(guia.getNidDireccionDestino(),null,null);
                if(direcs != null){
                    if(direcs.size() > 0){
                        beanGuias.setCDireccionDestino(direcs.get(0).getCDireccion());
                    }
                }
                direcs = ln_C_SFDireccionLocal.getDireccionByProp_LN(guia.getNidDireccionRemitente(),null,null);
                if(direcs != null){
                    if(direcs.size() > 0){
                        beanGuias.setCDireccionRemitente(direcs.get(0).getCDireccion());
                    }
                }
            
                lstBeanGuias.add(beanGuias);
            
        }
        return lstBeanGuias;
    }
    public List<BeanTRGuia> guiasByNidPartyOK(int nidCliente){
        System.out.println("::::ENTRO1::");
        List<TRGuia> lstGuia = bdL_C_SFGuiaLocal.guiasPorPartyOK(nidCliente);
        if(lstGuia!=null){
        System.out.println("::::::"+lstGuia.size());}
        List<BeanTRGuia> lstBeanGuias = new ArrayList<BeanTRGuia>();
        for(TRGuia guia : lstGuia){
                BeanTRGuia beanGuias = new BeanTRGuia();
                BeanEmpresa beanEm = new BeanEmpresa();
                BeanEmpresa beanEm1 = new BeanEmpresa();
                BeanManifiesto beanMan = new BeanManifiesto();
                System.out.println("::55::");
                beanGuias.setCidGuia(guia.getCidGuia());
                beanGuias.setCObservaciones(guia.getCObservaciones());
                beanGuias.setCConformidad("OK");
            System.out.println("::55::");
                beanGuias.setImgGuia(guia.getImgGuia());
            
                beanGuias.setImgGuiaProv(guia.getImgGuiaProv());
            
                beanGuias.setFechaGuia(guia.getFechaGuia());
            System.out.println("::55::");
                beanGuias.setEmpCliente(guia.getOrdenServicio().getAdEmpresa().getCRazonSocial());
                beanGuias.setItemsLista(this.itemsLista(guia.getItemsList()));
            System.out.println("::55::");
                beanGuias.setNumPaquetes(guia.getNumPaquetes());
            System.out.println("::55::");
                beanEm.setCRazonSocial(guia.getAdEmpresa().getCRazonSocial());
                beanGuias.setAdEmpresa(beanEm);
            System.out.println("::55::");
            if(guia.getTrManifiesto()!=null){
                beanEm1.setCRazonSocial(guia.getTrManifiesto().getTrManifiesto().getCRazonSocial());
                beanMan.setTrManifiesto(beanEm1);
                beanGuias.setTrManifiesto(beanMan);
            }
               
            
            System.out.println("::55::");
                List<BeanDireccion> direcs = ln_C_SFDireccionLocal.getDireccionByProp_LN(guia.getNidDireccionDestino(),null,null);
                if(direcs != null){
                    if(direcs.size() > 0){
                        beanGuias.setCDireccionDestino(direcs.get(0).getCDireccion());
                    }
                }
                direcs = ln_C_SFDireccionLocal.getDireccionByProp_LN(guia.getNidDireccionRemitente(),null,null);
                if(direcs != null){
                    if(direcs.size() > 0){
                        beanGuias.setCDireccionRemitente(direcs.get(0).getCDireccion());
                    }
                }
            System.out.println("::88888::");
                lstBeanGuias.add(beanGuias);
            
        }
        return lstBeanGuias;
    }
    public List<BeanTRItem> itemsLista(List<TRItem> itemsList){
        List<BeanTRItem> itemsListBeans = new ArrayList<BeanTRItem>();
        for(TRItem item : itemsList){
            BeanTRItem bItem = new BeanTRItem();
            bItem.setIdItem(item.getNidItem());
            bItem.setCDescItem(item.getCDescItem());
            bItem.setNCantidad(item.getNCantidad());
            bItem.setCUndMedida(item.getCUndMedida());
            bItem.setDPeso(item.getDPeso());
            itemsListBeans.add(bItem);
        }
        return itemsListBeans;
    }
    
    public List<TRGuia> beanListGuiasToEntity(List<BeanTRGuia> guiasList){
        MapperIF mapper = new DozerBeanMapper();
        List<TRGuia> guiasListBeans = new ArrayList<TRGuia>();
        if(guiasList != null){
            if(guiasList.size() > 0){
                for(BeanTRGuia g : guiasList){
                    TRGuia entidad = (TRGuia) mapper.map(g,TRGuia.class);
                    guiasListBeans.add(entidad);
                }
            }
        }
        return guiasListBeans;
    }
    
    public int cantGuiasVigentesByManifiesto_LN(int nidOS){
        return bdL_C_SFGuiaLocal.cantGuiasVigentesByManifiesto(nidOS);
    }
    
    public boolean contieneItemGuia(BeanTRGuia eIteam,List<BeanTRGuia> lstItem){
        for(BeanTRGuia beanItem : lstItem){
            if(UtilsGeneral.comparaNidItems(eIteam,beanItem,"cidGuia")){
                return true;
            }
        }
        return false;
    }
    
    public boolean contieneItemGuiaSolo4RegFactura(BeanTRGuia eIteam,List<BeanTRGuia> lstItem){
        for(BeanTRGuia beanItem : lstItem){
            if(beanItem.getCidGuia().equals(eIteam.getCidUnidadNegocio()+"-"+eIteam.getCidGuia())){
                return true;
            }
        }
        return false;
    }
    
    public boolean contieneItem_Guia(BeanTRItem eIteam,List<BeanTRItem> lstItem){
        for(BeanTRItem beanItem : lstItem){
            if(UtilsGeneral.comparaNidItems(eIteam,beanItem,"nidItem")){
                return true;
            }
        }
        return false;
    }
    
    public int cantGuiasByDireccion(int nidDireccion){
        return bdL_C_SFGuiaLocal.cantGuiasByDireccion(nidDireccion);
    }
    
    public int cantGuiasByChofer(int nidChofer){
        return bdL_C_SFGuiaLocal.cantGuiasByChofer(nidChofer);
    }
    
    public int cantGuiasByFlota(int nidFlota){
        return bdL_C_SFGuiaLocal.cantGuiasByFlota(nidFlota);
    }
    
    public boolean manifiestoHasGuiasActivas_LN(int nidManifesto){
        return bdL_C_SFGuiaLocal.getCantidadGuiasActivasByManifiesto(nidManifesto) > 0 ? true : false;
    }
    
    
    public List<BeanTRItem> getListaItemsByCidGuia(String cidGuia){
        List<TRItem> items=bdl_C_SFItemLocal.getTrItemGuia_BD(cidGuia);
        if(items!=null){
        System.out.println("::::LISTA ITEMS SIZE:::"+ items.size());}
        else{
            System.out.println("::NULO::");}  
        
        List<BeanTRItem> it = new ArrayList<BeanTRItem>();
        for(TRItem entidad:items){
            BeanTRItem bean = new BeanTRItem();
            bean.setCCidGuiaRemitente(entidad.getCCidGuiaRemitente());
            bean.setCDescItem(entidad.getCDescItem());
            bean.setCUndMedida(entidad.getCUndMedida());
            bean.setDPeso(entidad.getDPeso());
            bean.setIdItem(entidad.getNidItem());
            bean.setNCantidad(entidad.getNCantidad());
            bean.setNidItem(entidad.getNidItem());
            bean.setOrden(entidad.getOrden());
          /**Nuevo debido a que registro de facturas necesita 
           * estos parametros(Agiliza registro de factura)17-03-2015**/ 
            BeanTRGuia guia=new BeanTRGuia();
            guia.setCidGuia(entidad.getTrGuia().getCidGuia());
            guia.setCidUnidadNegocio(entidad.getTrGuia().getCidUnidadNegocio());
            bean.setTrGuia(guia);
          /****/              
            it.add(bean);
        }
        return it;
    }
    
    public int contieneGuiasPendientesByOS(int nidOS){
        return bdL_C_SFGuiaLocal.getCountGuiasVigentesByOrdenServ(nidOS);
    }
    
    public int existeGuiaByCidGuia(String cidGuia){
        return bdL_C_SFGuiaLocal.existeGuia(cidGuia);
    }
    
}