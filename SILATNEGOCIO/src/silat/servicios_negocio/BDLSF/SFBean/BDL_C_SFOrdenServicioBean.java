package silat.servicios_negocio.BDLSF.SFBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;
import silat.servicios_negocio.Beans.BeanOrdenServicio;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFOrdenServicioRemote;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFUtilsLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFOrdenServicioLocal;
import silat.servicios_negocio.Beans.BeanConstraint;
import silat.servicios_negocio.entidades.audsis.TROrdenServicio;
import silat.servicios_negocio.entidades.trans.TRItemXOrds;
import silat.servicios_negocio.util_formato.Caracter.FormatoLetra;
import silat.servicios_negocio.util_formato.UtilsGeneral;

@Stateless(name = "BDL_C_SFOrdenServicio", mappedName = "LUBAL_SIAT_APP-SILATNEGOCIO-BDL_C_SFOrdenServicio")
public class BDL_C_SFOrdenServicioBean implements BDL_C_SFOrdenServicioRemote, 
                                                  BDL_C_SFOrdenServicioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName="SILATNEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFUtilsLocal bdL_C_SFUtilsLocal;

    public BDL_C_SFOrdenServicioBean() {
    }

    /** <code>select o from TROrdenServicio o</code> */
    public List<TROrdenServicio> getTROrdenServicioFindAll() {
         String ejbQuery = "SELECT u " +
                           "FROM TROrdenServicio u " +
                           "WHERE u.nEstadoOrden = 1 "+
                           "AND u.cEstord = 'P' " +
                           "ORDER BY u.fecOrdnServ DESC ";
         return em.createQuery(ejbQuery).getResultList();
    }
    
    public int countNuevasOS() {
        String ejbQuery = "SELECT count(u.nidOrdnServ) " +
                          "FROM TROrdenServicio u " +
                          "WHERE u.flgVisto = 0 ";
        List lst = em.createQuery(ejbQuery).getResultList();
        if(lst.isEmpty()){
            return 0;
        }else{
            return Integer.parseInt(lst.get(0).toString());
        }
    }
    
    public List<TROrdenServicio> getOrdenServiciobyNombreEmpresa(Date fecOrdnServ){
        String ejbQuery = "Select u from TROrdenServicio u " +
                          "WHERE u.fecOrdnServ = :fecOrdnServ " +
                          "AND u.nEstadoOrden = 1 "+
                          "AND u.cEstord = 'P' " +
                         " ORDER BY u.fecOrdnServ DESC ";
        List<TROrdenServicio> list = new ArrayList<TROrdenServicio>();
        if(fecOrdnServ == null){
            list = getTROrdenServicioFindAll();
        }else{
            list = em.createQuery(ejbQuery).setParameter("fecOrdnServ", fecOrdnServ).getResultList();
        }
        return list;
    }
    
    /**
     * Este metodo reutilizable retorna dependiendo el tipo de busqueda que se realiza,
     * la lista de entidades
     * @author dfloresgonz
     * @since 06.05.2013
     * @param propertyName Nombre del atributo a buscar
     * @param value valor del atributo a buscar
     * @return List de entidades TROrdenServicio
     */
    public List<TROrdenServicio> findByProperty(String propertyName, Object value) {
        try {
            String queryString = "select model " +
                                 "from TROrdenServicio model " +
                                 "where model."+ propertyName + " = :propertyValue";
            return em.createQuery(queryString)
                     .setParameter("propertyValue", value)
                     .getResultList();
        } catch (RuntimeException re) {
            throw re;
        }
    }
    
    /**
     * Dependiendo del atributo se crea el query para buscar la lista de Ordenes
     * de servicio
     * @author dfloresgonz
     * @since 06.05.2013
     * @param beanParametro bean de busqueda con los valores a buscar seteados
     * @return List de BeanOrdenServicio
     */
    public List<TROrdenServicio> findOrdenServicioByAttributes(BeanOrdenServicio beanParametro) {
        try {            
            if(beanParametro.getCDetalle()==null && beanParametro.getCRazonSocial()==null &&
              beanParametro.getCEstadoOrdenDesc()==null && beanParametro.getFecOrdnMin()==null &&
              beanParametro.getFecOrdnMax()==null){
                String queryString = "SELECT o " +
                                     "FROM TROrdenServicio o " +
                                     "WHERE o.cEstord = 'P' " +
                                     "OR o.cEstord = 'F' " +
                                     "OR o.cEstord = 'C' " +
                                     "AND o.nEstadoOrden = 1 " +
                                     "ORDER BY o.nidOrdnServ DESC ";
                return em.createQuery(queryString).getResultList();     
            }
            if(beanParametro.getCDetalle()!=null || beanParametro.getCRazonSocial()!=null ||
              beanParametro.getCEstadoOrdenDesc()!=null || beanParametro.getFecOrdnMin()!=null ||
              beanParametro.getFecOrdnMax()!=null){
                String queryString = "SELECT o " +
                                     "FROM TROrdenServicio o " +                            
                                     "WHERE o.nEstadoOrden = 1 ";
                if(beanParametro.getFecOrdnMin() != null && beanParametro.getFecOrdnMax() != null){
                    queryString = queryString.concat(" AND o.fecOrdnServ BETWEEN :min AND :max ");
                }
                if(beanParametro.getCDetalle() != null){
                    queryString = queryString.concat(" AND upper(o.cDetalle) like '%"+beanParametro.getCDetalle().toUpperCase()+"%' ");
                }
                if(beanParametro.getCRazonSocial() != null){
                    queryString = queryString.concat(" AND upper(o.adEmpresa.cRazonSocial) like '%"+beanParametro.getCRazonSocial().toUpperCase()+ "%' ");
                }
                if(beanParametro.getCEstadoOrdenDesc() != null){
                    queryString = queryString.concat(" AND o.cEstord ='"+beanParametro.getCEstadoOrdenDesc()+"'");
                }           
                queryString= queryString.concat(" ORDER BY o.nidOrdnServ DESC ");
                if(beanParametro.getFecOrdnMin() != null && beanParametro.getFecOrdnMax() != null){
                  // lstGasto = 
                      return  em.createQuery(queryString)
                                 .setParameter("min", beanParametro.getFecOrdnMin(), TemporalType.DATE)
                                 .setParameter("max", beanParametro.getFecOrdnMax(), TemporalType.DATE).getResultList();
                }else{
                    return em.createQuery(queryString).getResultList();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public List<TROrdenServicio> findOrdenServicioPendientesByAttributes_ParaGuia(BeanOrdenServicio beanParametro) {
        try {
            String queryString = "SELECT o " 
                                + "FROM TROrdenServicio o " 
                                + "WHERE o.cEstord = 'P' "
                                + "AND o.nEstadoOrden = 1 ";
            if(beanParametro.getCDetalle() != null){
                queryString = queryString.concat(" AND upper(o.cDetalle) like '%"+beanParametro.getCDetalle().toUpperCase()+"%' ");
            }
            if(beanParametro.getCRazonSocial() != null){
                queryString = queryString.concat(" AND upper(o.adEmpresa.cRazonSocial) like '%"+beanParametro.getCRazonSocial().toUpperCase()+ "%' ");
            }
            queryString = queryString.concat(" ORDER BY o.fecOrdnServ DESC ");
            return em.createQuery(queryString).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<TROrdenServicio> findOrdenServicioPendientesByAttributes(BeanOrdenServicio beanParametro) {
        try {            
            if(beanParametro.getCDetalle()==null && beanParametro.getCRazonSocial()==null &&
              beanParametro.getCEstadoOrdenDesc()==null && beanParametro.getFecOrdnMin()==null &&
              beanParametro.getFecOrdnMax()==null && beanParametro.getNidOrdnServ() == null){
                String queryString = "SELECT o " +
                                     "FROM TROrdenServicio o " +
                                     "WHERE o.cEstord = 'P' " +
                                     "AND o.nEstadoOrden = 1 " +
                                     "ORDER BY o.nidOrdnServ DESC ";
                return em.createQuery(queryString).getResultList();     
            }
            if(beanParametro.getCDetalle()!=null || beanParametro.getCRazonSocial()!=null ||
              beanParametro.getCEstadoOrdenDesc()!=null || beanParametro.getFecOrdnMin()!=null ||
              beanParametro.getFecOrdnMax()!=null || beanParametro.getNidOrdnServ() != null){
                String queryString = "SELECT o " +
                                     "FROM TROrdenServicio o " +                            
                                     "WHERE o.nEstadoOrden = 1 ";
                if(beanParametro.getFecOrdnMin() != null && beanParametro.getFecOrdnMax() != null){
                    queryString = queryString.concat(" AND o.fecOrdnServ BETWEEN :min AND :max ");
                }
                if(beanParametro.getCDetalle() != null){
                    queryString = queryString.concat(" AND upper(o.cDetalle) like '%"+beanParametro.getCDetalle().toUpperCase()+"%' ");
                }
                if(beanParametro.getCRazonSocial() != null){
                    queryString = queryString.concat(" AND upper(o.adEmpresa.cRazonSocial) like '%"+beanParametro.getCRazonSocial().toUpperCase()+ "%' ");
                }
                if(beanParametro.getNidOrdnServ() != null){
                    queryString = queryString.concat(" AND o.nidOrdnServ = "+beanParametro.getNidOrdnServ()+" ");
                }
                if(beanParametro.getCEstadoOrdenDesc() != null){
                    queryString = queryString.concat(" AND o.cEstord ='"+beanParametro.getCEstadoOrdenDesc()+"'");
                }           
                queryString= queryString.concat(" ORDER BY o.nidOrdnServ DESC ");
                if(beanParametro.getFecOrdnMin() != null && beanParametro.getFecOrdnMax() != null){
                  // lstGasto = 
                      return  em.createQuery(queryString)
                                 .setParameter("min", beanParametro.getFecOrdnMin(), TemporalType.DATE)
                                 .setParameter("max", beanParametro.getFecOrdnMax(), TemporalType.DATE).getResultList();
                }else{
                    return em.createQuery(queryString).getResultList();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    
    public int verificarOSConGuia(Integer nidOrdenServicio) {
        int valor = 0;
        try {
            String ejbQl = "select count(e.cidGuia)" +
                           "from TRGuia e " +
                           "where e.ordenServicio.nidOrdnServ = '"+ nidOrdenServicio +"' " +
                           "AND e.ordenServicio.nEstadoOrden = 1";            
            Object resultado = em.createQuery(ejbQl).getSingleResult();
            valor = Integer.parseInt(resultado.toString());       
            return valor;
        } catch (Exception e) {
            e.printStackTrace();
            return 55555;
        }
    }
    
    /**
     * Metodo que usa el mapper para pasar de entidad a bean
     * @author dfloresgonz
     * @since 06.05.2013
     * @param lstOrdenServ Lista de entidades de TROrdenServicio
     * @return lista de BeanOrdenServicio
     */     
    public List<BeanOrdenServicio> getListBeanOrdenServUtil(List<TROrdenServicio> lstOrdenServ){
        try{
            MapperIF mapper = new DozerBeanMapper();
            List<BeanOrdenServicio> lstBeanOrdenServ = new ArrayList<BeanOrdenServicio>();
            BeanOrdenServicio beanOS = null;
            BeanConstraint beanCons = null;
            for(TROrdenServicio ordenServ : lstOrdenServ){
                ordenServ.setCDetalle(FormatoLetra.quitarTagsHTML(ordenServ.getCDetalle()));
                beanOS = (BeanOrdenServicio)mapper.map(ordenServ,BeanOrdenServicio.class);
                beanCons = bdL_C_SFUtilsLocal.getCatalogoConstraints("C_ESTORD","TRMORDS",ordenServ.getCEstord());
                beanOS.setCEstord(beanCons.getCDescrip());
                beanCons = bdL_C_SFUtilsLocal.getCatalogoConstraints("N_ESTADO_ORDEN", "TRMORDS", ordenServ.getNEstadoOrden().toString());
                beanOS.setCEstadoOrdenDesc(beanCons.getCDescrip());
                lstBeanOrdenServ.add(beanOS);
            }
            return lstBeanOrdenServ;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Metodo para buscar segun el correlativo de la TROrdenServicio
     * @author dfloresgonz
     * @since 06.05.2013
     * @param id atributo de la entidad en que buscar
     * @return lista de entidades de TROrdenServicio
     */
    public List<TROrdenServicio> findByCorrelativo(Object id) {
        return findByProperty("nidOrdnServ", id);
    }

    public List<BeanOrdenServicio> findOrdenServicioAllGuiasOK(BeanOrdenServicio beanParametro) {
        try {//Trae OS cerradas y sus guias con estado OK
            String queryString = "SELECT distinct(o) " +
                                 "FROM TROrdenServicio o," +
                                 "TRGuia g "+
                                 "WHERE o.cEstord = 'C' " +
                                 "AND o.nEstadoOrden = 1 " +
                                 "AND o.nidOrdnServ = g.ordenServicio.nidOrdnServ " +
                                 "AND g.trFactura IS NULL " +
                                 "AND g.nEstadoGuia = 1 ";
            if(beanParametro.getCDetalle() != null){
                queryString = queryString.concat(" AND upper(o.cDetalle) like '%"+beanParametro.getCDetalle().toUpperCase()+"%' ");
            }
            if(beanParametro.getCRazonSocial() != null){
                queryString = queryString.concat(" AND upper(o.adEmpresa.cRazonSocial) like '%"+beanParametro.getCRazonSocial().toUpperCase()+ "%' ");
            }
            List<TROrdenServicio> lstOrdenServ =  em.createQuery(queryString)
                                                    .getResultList();
            int size = lstOrdenServ == null ? 0 : lstOrdenServ.size();
            if(size > 0){
                return getListBeanOrdenServUtil(lstOrdenServ);
            }else{
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<TRItemXOrds>getItemsbyOrd(String nidOrds){
        List<TRItemXOrds>listaItems= new ArrayList<TRItemXOrds>();
        String query = "SELECT o FROM TRItemXOrds o WHERE o.orden = '"+nidOrds+"' " +
                       "AND o.cEstado = 0";
        listaItems = em.createQuery(query).getResultList();
        return listaItems;
    }
}
