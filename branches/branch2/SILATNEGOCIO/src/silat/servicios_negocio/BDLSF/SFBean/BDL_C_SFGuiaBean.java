package silat.servicios_negocio.BDLSF.SFBean;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import javax.persistence.TemporalType;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFGuiaLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFGuiaRemote;
import silat.servicios_negocio.Beans.BeanManifiesto;
import silat.servicios_negocio.Beans.BeanTRGuia;
import silat.servicios_negocio.entidades.trans.TRGuia;
import silat.servicios_negocio.entidades.trans.TRManifiesto;
import silat.servicios_negocio.util_formato.UtilsGeneral;

@Stateless(name = "BDL_C_SFGuia", mappedName = "mapBDL_C_SFGuia")
public class BDL_C_SFGuiaBean implements BDL_C_SFGuiaRemote, 
                                         BDL_C_SFGuiaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_C_SFGuiaBean() {
    }
    
    public TRGuia findTRGuiaById(String id) {
        try {
            TRGuia instance = em.find(TRGuia.class, id);
            return instance;
        } catch (RuntimeException re) {
            throw re;
        }
    }
            
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public TRGuia findGuiaByUNCid(String cidUn,String cidGuia){
        try{
            String ejbQl = "SELECT g " +
                           "FROM TRGuia g " +
                           "WHERE g.nEstadoGuia = 1 " +
                           "AND g.cidGuia = '"+cidGuia+"' " +
                           "AND g.cidUnidadNegocio = '"+cidUn+"' ";
            TRGuia guia = (TRGuia) em.createQuery(ejbQl).getSingleResult();
            return guia;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean isGuiaExistente(String cidUn,String cidGuia){
        try{
            String strQuery = "SELECT COUNT(1) " +
                              "FROM TRGuia g " +
                              "WHERE g.cidGuia = :cidGuia " +
                              "AND g.cidUnidadNegocio = :cidUn ";
            List lst = em.createQuery(strQuery).setParameter("cidGuia",cidGuia).setParameter("cidUn",cidUn).getResultList();
            if(lst.isEmpty()){
                return false;
            }else{
                int cant = Integer.parseInt(lst.get(0).toString());
                return (cant > 0 ? true : false);
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public List<TRGuia> findGuiasByManifiesto(int nidManifiesto){
        try{
            String ejbQl = "SELECT g " +
                           "FROM TRGuia g " +
                           "WHERE g.nEstadoGuia = 1 " +
                           "AND g.trManifiesto.nidManifiesto = :nidManifiesto ";
            List<TRGuia> lstGuias = em.createQuery(ejbQl).setParameter("nidManifiesto",nidManifiesto).getResultList();
            int size = lstGuias == null ? 0 : lstGuias.size();
            if (size > 0) {
                return lstGuias;
            } else {
                return new ArrayList<TRGuia>();
            }
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<TRGuia>();
        }
    }
    public List<TRGuia> guiasPorParty(int nidCliente){
            String ejbQl = "SELECT g " +
                           "FROM TRGuia g " +
                           "WHERE g.ordenServicio.adEmpresa.nidParty = '"+""+nidCliente+"' " +
                           "AND g.cConformidad = '2'";
            return em.createQuery(ejbQl).getResultList();
    }
    
    public List<TRGuia> guiasPorPartyOK(int nidCliente){
            String ejbQl = "SELECT g " +
                           "FROM TRGuia g " +
                           "WHERE g.ordenServicio.adEmpresa.nidParty = '"+""+nidCliente+"' " +
                           "AND g.cConformidad = '1'";
            return em.createQuery(ejbQl).getResultList();
    }
    
    public int getCantidadGuiasActivasByManifiesto(int nidManifiesto){
        try{
            String ejbQl = "SELECT COUNT(1) " +
                           "FROM TRGuia g " +
                           "WHERE g.nEstadoGuia = 1 " +
                           "AND g.trManifiesto.nidManifiesto = :nidManifiesto " +
                           "AND g.trFactura IS NULL ";
            List lst = em.createQuery(ejbQl).setParameter("nidManifiesto",nidManifiesto).getResultList();
            if(lst.isEmpty()){
                return 0;
            }else{
                return Integer.parseInt(lst.get(0).toString());
            }
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    
    public List<TRGuia> findGuiasByNidCliente(int nidCliente){
        try{
            String ejbQl = "SELECT g " +
                           "FROM TRGuia g " +
                           "WHERE g.nEstadoGuia = 1 " +
                           "AND g.cConformidad = '1' "+
                           "AND g.trFactura IS NULL "+
                           "AND g.itemPreFactura IS NULL "+
                           "AND g.ordenServicio.adEmpresa.nidParty = "+nidCliente+" ";
            List<TRGuia> guias = em.createQuery(ejbQl).getResultList();
            return guias;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public List<TRGuia> findGuiasByNidClienteParaPreFactura(int nidCliente){
        try{
            String ejbQl = "SELECT g " +
                           "FROM TRGuia g " +
                           "WHERE g.nEstadoGuia = 1 " +
                           "AND g.cConformidad = '1' "+
                           "AND g.trFactura IS NULL "+
                           "AND g.itemPreFactura IS NULL "+
                           "AND g.ordenServicio.adEmpresa.nidParty = "+nidCliente+" ";
            List<TRGuia> guias = em.createQuery(ejbQl).getResultList();
            return guias;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public List<TRGuia> findGuiasByAttributes_BD(String cidGuia,Date fecEmisMin,Date fecEmisMax,Date fecDespMin, 
                                               Date fecDespMax,String empCliente,String empProvCarga,String estGuia,
                                               String hasManif,Integer nidManif,String prov,String cObservaciones,
                                               String nEstadoGuia,int nidOS,String detOS,String hasFactura,String codFactura,
                                               int nEstadoFactura,BigDecimal nidParty,String descCidGuiaRemi_ITEM){
        try{
            String ejbQl = "SELECT g " +
                           "FROM TRGuia g ";
            if(descCidGuiaRemi_ITEM != null){
                ejbQl = ejbQl.concat(" ,TRItem it  WHERE it.trGuia.cidGuia = g.cidGuia AND UPPER(it.cCidGuiaRemitente) like UPPER('%"+descCidGuiaRemi_ITEM+"%') ");
            }else{
                ejbQl = ejbQl.concat(" WHERE 1 = 1 ");
            }
            if(nEstadoGuia!= null){
                ejbQl = ejbQl.concat(" AND g.nEstadoGuia = '"+nEstadoGuia+"' ");
            }
            if(fecEmisMin!= null && fecEmisMax != null){
                ejbQl = ejbQl.concat(" AND g.fechaGuia BETWEEN :FecGuiaMin AND :FecGuiamax ");
            }
            if(fecDespMin!= null && fecDespMax!= null){
                ejbQl = ejbQl.concat(" AND g.fechaDespacho BETWEEN :FecDespMin AND :FecDespmax ");
            }
            if(empCliente!= null){
                ejbQl = ejbQl.concat(" AND upper(g.ordenServicio.adEmpresa.cRazonSocial) like '%"+empCliente.toUpperCase()+"%' ");
            }
           /* if(bGuia.getFiltroItemGuiaRemi() != null){
                ejbQl = ejbQl.concat(" AND it.trGuia.cidGuia = g.cidGuia AND UPPER(it.cCidGuiaRemitente) like UPPER('%"+bGuia.getFiltroItemGuiaRemi()+"%') ");
            }*/
         /*   if(bGuia.getNidCliente() != null){
                if(bGuia.getNidCliente() != new BigDecimal(0)){
                    ejbQl = ejbQl.concat(" AND g.ordenServicio.adEmpresa.nidParty = "+bGuia.getNidCliente()+" ");
                }
            }*/
            if(empProvCarga != null){
                if(empProvCarga!= null){
                    ejbQl = ejbQl.concat(" AND upper(g.adEmpresa.cRazonSocial) like '%"+empProvCarga.toUpperCase()+"%' ");
                } 
            }
            if(cObservaciones != null){
                ejbQl = ejbQl.concat(" AND upper(g.cObservaciones) like '%"+cObservaciones.toUpperCase()+"%' ");
            }
            if(nidOS != 0){
                if(nidOS!= 0){
                    ejbQl = ejbQl.concat(" AND g.ordenServicio.nidOrdnServ = "+nidOS+" ");
                }
                if(detOS != null){
                    ejbQl = ejbQl.concat(" AND upper(g.ordenServicio.cDetalle) like '%"+detOS+"%' ");
                }
            }
            if(estGuia != null){
                ejbQl = ejbQl.concat(" AND g.cConformidad = '"+estGuia+"'");
            }
            if(cidGuia!= null){
                ejbQl = ejbQl.concat(" AND g.cidGuia like '%"+cidGuia+"%' ");
            }
            if(hasManif!= null){
                if(hasManif.equals("1")){
                    ejbQl = ejbQl.concat(" AND g.trManifiesto IS NOT NULL ");
                }else{
                    ejbQl = ejbQl.concat(" AND g.trManifiesto IS NULL ");
                }
            }
            if(hasFactura!= null){
                if(hasFactura.equals("1")){
                    ejbQl = ejbQl.concat(" AND g.trFactura IS NOT NULL ");
                    if(codFactura!= null){
                        if(codFactura!= null){
                            ejbQl = ejbQl.concat(" AND g.trFactura.nidFactura like '%"+codFactura+"%' ");
                        }
                        if(nEstadoFactura!= 0){
                            ejbQl = ejbQl.concat(" AND g.trFactura.nEstadoFactura = "+nEstadoFactura);
                        }    
                    }
                }else{
                    ejbQl = ejbQl.concat(" AND g.trFactura IS NULL ");
                }
            }else{
                if(codFactura!= null){
                    if(codFactura!= null){
                        ejbQl = ejbQl.concat(" AND g.trFactura.cCodFactura like '%"+codFactura+"%' ");
                    }
                    if(nEstadoFactura != 0){
                        ejbQl = ejbQl.concat(" AND g.trFactura.nEstadoFactura = "+nEstadoFactura);
                    }    
                }
            }
            if(nidManif!= null){
                if(nidManif!= null){
                    ejbQl = ejbQl.concat(" AND g.trManifiesto.nidManifiesto = "+nidManif);
                }
                if(prov != null){
                    ejbQl = ejbQl.concat(" AND upper(g.trManifiesto.trManifiesto.cRazonSocial) like '%"+prov+"%' ");
                }
            }
            ejbQl = ejbQl.concat(" ORDER BY g.cidGuia DESC ");
            //UtilsGeneral.depurar("query guias "+ejbQl);
            List<TRGuia> lstGuias = new ArrayList<TRGuia>();
            if((fecEmisMin != null && fecEmisMax != null) &&
               (fecDespMin!= null && fecDespMax != null)){
                 lstGuias = em.createQuery(ejbQl).setParameter("FecGuiaMin", fecEmisMin, TemporalType.DATE)
                                                 .setParameter("FecGuiamax", fecEmisMax, TemporalType.DATE)                             
                                                 .setParameter("FecDespMin", fecDespMin, TemporalType.DATE)
                                                 .setParameter("FecDespmax", fecDespMax, TemporalType.DATE).getResultList();
            }else if(fecEmisMin != null && fecEmisMax != null){
                lstGuias = em.createQuery(ejbQl)
                             .setParameter("FecGuiaMin", fecEmisMin, TemporalType.DATE)
                             .setParameter("FecGuiamax", fecEmisMax, TemporalType.DATE).getResultList();
            }else if(fecDespMin != null && fecDespMax != null){
                lstGuias = em.createQuery(ejbQl)
                             .setParameter("FecDespMin", fecDespMin, TemporalType.DATE)
                             .setParameter("FecDespmax", fecDespMax, TemporalType.DATE).getResultList();
            }else{
                lstGuias = em.createQuery(ejbQl).setMaxResults(600).getResultList();
            }
            int size = lstGuias == null ? 0 : lstGuias.size();
            if (size > 0) {
                return lstGuias;
            } else {
                return new ArrayList<TRGuia>();
            }
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<TRGuia>();
        }
    }

    public int contGuiasConFactPagadas(int nidOrdenServicio, Long nidFactura){
        int cantFactPagadasXOrdenServicio = 0;
        try{
            String query = "SELECT COUNT(1) " +
                            "FROM TRGuia g, " +
                            "TRFactura f," +
                            "TROrdenServicio os " +
                            "WHERE g.trFactura.nidFactura = f.nidFactura " +
                            "AND g.ordenServicio.nidOrdnServ = " +nidOrdenServicio+" "+
                            "AND f.nEstadoFactura = 1 " +
                            "AND g.nEstadoGuia = '1' " +
                            "AND f.nidFactura <> "+nidFactura;//Traer cont de las guias con facturas pagadas
            Object coun = em.createQuery(query).getSingleResult();
            if(coun != null){
                int iCoun = Integer.parseInt(coun.toString());UtilsGeneral.depurar("cantguiasfactPaag "+iCoun);
                cantFactPagadasXOrdenServicio = iCoun;
            }
        }catch(Exception e){
            e.printStackTrace();
            cantFactPagadasXOrdenServicio = 0;
        }
        return cantFactPagadasXOrdenServicio;
    }
    
    public int getCountGuiasVigentesByFactura(Long nidFactura){
        try{
            String query = "SELECT COUNT(1) " +
                           "FROM TRGuia g " +
                           "WHERE g.nEstadoGuia = '1' " +
                           "AND g.trFactura.nidFactura = :nidFactura ";
            List lst = em.createQuery(query).setParameter("nidFactura", nidFactura).setMaxResults(1).getResultList();
            if(lst.isEmpty()){
                return 0;
            }else{
                return Integer.parseInt(lst.get(0).toString());
            }
        }catch(Exception e){
            return 0;
        }
    }
    
    public int getCountGuiasVigentesByFacturaByOrdenServ(Long nidFactura, int nidOS){
        try{
            String query = "SELECT COUNT(1) " +
                           "FROM TRGuia g " +
                           "WHERE g.nEstadoGuia = '1' " +
                           "AND g.ordenServicio.nidOrdnServ =  "+nidOS+" "+
                           "AND g.trFactura.nidFactura = :nidFactura ";
            List lst = em.createQuery(query).setParameter("nidFactura", nidFactura).getResultList();
            if(lst.isEmpty()){
                return 0;
            }else{
                return Integer.parseInt(lst.get(0).toString());
            }
        }catch(Exception e){
            return 0;
        }
    }
    
    public int getCountGuiasVigentesByOS_No_Facturadas(int nidOS){
        try{
            String query = "SELECT COUNT(1) " +
                           "FROM TRGuia g, " +
                           " TROrdenServicio os " +
                           "WHERE g.nEstadoGuia = '1' " +
                           "AND g.ordenServicio.nidOrdnServ = "+nidOS+" "+
                           "AND g.trFactura IS NULL ";
            String query2 = "SELECT count(g.cidGuia) " +
                           "FROM TRGuia g, " +
                           " TROrdenServicio os " +
                           "WHERE g.nEstadoGuia = '1' " +
                           "AND g.ordenServicio.nidOrdnServ = "+nidOS+" "+
                           "AND g.trFactura.nEstadoFactura <> 1 "+
                           "AND g.trFactura.nEstadoFactura <> 3 ";
            List lst = em.createQuery(query).getResultList();
            List lst2 = em.createQuery(query2).getResultList();
            if(lst.isEmpty() && lst2.isEmpty()){
                return 0;
            }else{
                int total = Integer.parseInt(lst.get(0).toString()) + Integer.parseInt(lst2.get(0).toString());
                return total;
            }
        }catch(Exception e){
            return 0;
        }
    }
    
    public int cantGuiasVigentesByManifiesto(int nidManifiesto){
        try{
            String query = "SELECT COUNT(1) " +
                           "FROM TRGuia g " +
                           "WHERE g.nEstadoGuia = 1 " +
                           "AND g.trManifiesto.nidManifiesto = :nidManifiesto ";
            Object o = em.createQuery(query).setParameter("nidManifiesto",nidManifiesto).getSingleResult();
            int cant = 0;
            if(o != null){
                cant = Integer.parseInt(o.toString());
            }
            return cant;
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    
    public int getCountGuiasVigentesByOrdenServ(int nidOS){
        try{
            String query = "SELECT COUNT(1) " +
                           "FROM TRGuia g " +
                           "WHERE g.nEstadoGuia = '1' " +
                           "AND g.ordenServicio.nidOrdnServ =  :nidOS ";
            List lst = em.createQuery(query).setParameter("nidOS",nidOS).getResultList();
            if(lst.isEmpty()){
                return 0;
            }else{
                return Integer.parseInt(lst.get(0).toString());
            }
        }catch(Exception e){
            return 0;
        }
    }
    
    public int cantGuiasByDireccion(int nidDireccion){
        try{
            String query = "SELECT COUNT(1) " +
                           "FROM TRGuia g " +
                           "WHERE g.nEstadoGuia = 1 " +
                           "AND g.nidDireccionRemitente = :nidDirec " +
                           "OR g.nidDireccionDestino = :nidDirec ";
            Object o = em.createQuery(query).setParameter("nidDirec",nidDireccion).getSingleResult();
            int cant = 0;
            if(o != null){
                cant = Integer.parseInt(o.toString());
            }
            return cant;
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    
    public int cantGuiasByChofer(int nidChofer){
        try{
            String query = "SELECT COUNT(1) " +
                           "FROM TRGuia g " +
                           "WHERE g.nEstadoGuia = 1 " +
                           "AND g.nidChof = :nidChof ";
            Object o = em.createQuery(query).setParameter("nidChof",nidChofer).getSingleResult();
            int cant = 0;
            if(o != null){
                cant = Integer.parseInt(o.toString());
            }
            return cant;
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    
    public int cantGuiasByFlota(int nidFlota){
        try{
            String query = "SELECT COUNT(1) " +
                           "FROM TRGuia g " +
                           "WHERE g.nEstadoGuia = 1 " +
                           "AND g.nidFlota = :nidFlota ";
            Object o = em.createQuery(query).setParameter("nidFlota",nidFlota).getSingleResult();
            int cant = 0;
            if(o != null){
                cant = Integer.parseInt(o.toString());
            }
            return cant;
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}