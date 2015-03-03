package silat.servicios_negocio.BDLSF.SFBean;

import java.util.ArrayList;
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

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFManifiestoLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFUtilsLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFManifiestoRemote;
import silat.servicios_negocio.Beans.BeanChofer;
import silat.servicios_negocio.Beans.BeanConstraint;
import silat.servicios_negocio.Beans.BeanFlota;
import silat.servicios_negocio.Beans.BeanManifiesto;
import silat.servicios_negocio.LNSF.IL.LN_C_SFChoferLocal;
import silat.servicios_negocio.LNSF.IL.LN_C_SFFlotaLocal;
import silat.servicios_negocio.entidades.admin.ADChofer;
import silat.servicios_negocio.entidades.admin.ADConstraint;
import silat.servicios_negocio.entidades.admin.ADConstraintPK;
import silat.servicios_negocio.entidades.trans.TRManifiesto;
import silat.servicios_negocio.util_formato.UtilsGeneral;

@Stateless(name = "BDL_C_SFManifiesto", mappedName = "mapBDL_C_SFManifiesto")
public class BDL_C_SFManifiestoBean implements BDL_C_SFManifiestoRemote, 
                                               BDL_C_SFManifiestoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFUtilsLocal bdL_C_SFUtilsLocal;
    @EJB
    private LN_C_SFFlotaLocal ln_C_SFFlotaLocal;
    @EJB
    private LN_C_SFChoferLocal ln_C_SFChoferLocal;
    
    public BDL_C_SFManifiestoBean() {
    }

    /** <code>select o from TRManifiesto o</code> */
    public List<TRManifiesto> getTRManifiestoFindAll() {
        return em.createNamedQuery("TRManifiesto.findAll").getResultList();
    }
    
    public TRManifiesto findTRManifiestoById(Integer id) {
        try {
            TRManifiesto instance = em.find(TRManifiesto.class, id);
            return instance;
        } catch (RuntimeException re) {
            throw re;
        }
    }
    
    public List<BeanManifiesto> findManifiestosByAttributes(BeanManifiesto beanManifiesto) {
        try{
            String ejbQl = "SELECT m " +
                           "FROM TRManifiesto m " +
                           "WHERE 1 = 1 ";
            if(beanManifiesto.getFechaManifiestoMIN() != null && beanManifiesto.getFechaManifiestoMAX() != null){
                ejbQl = ejbQl.concat(" AND m.fechaManifiesto BETWEEN :min AND :max ");
            }
            if(beanManifiesto.getNidManifiesto() != null){
                if(beanManifiesto.getNidManifiesto() != 0){
                    ejbQl = ejbQl.concat(" AND m.nidManifiesto = "+beanManifiesto.getNidManifiesto());
                }
            }
            if(beanManifiesto.getNFletePactado() != null && beanManifiesto.getSimboloFLete() != null){
                ejbQl = ejbQl.concat(" AND m.nFletePactado "+beanManifiesto.getSimboloFLete()+" "+beanManifiesto.getNFletePactado());
            }
            if(beanManifiesto.getNAdelanto() != null && beanManifiesto.getSimboloAdelanto() != null){
                ejbQl = ejbQl.concat(" AND m.nAdelanto "+beanManifiesto.getSimboloAdelanto()+" "+beanManifiesto.getNAdelanto());
            }
            if(beanManifiesto.getNEstManifiesto() != null){
                ejbQl = ejbQl.concat(" AND m.nEstManifiesto = "+beanManifiesto.getNEstManifiesto()+" ");
            }
            if(beanManifiesto.getCObservaciones() != null){
                ejbQl = ejbQl.concat(" AND upper(m.cObservaciones) like '%"+beanManifiesto.getCObservaciones().toUpperCase()+"%' ");
            }
            if(beanManifiesto.getEstadoManifiestoNegocio() != null){
                ejbQl = ejbQl.concat(" AND m.estadoManifiestoNegocio = "+beanManifiesto.getEstadoManifiestoNegocio());
            }
            if(beanManifiesto.getTrManifiesto() != null){
                if(beanManifiesto.getTrManifiesto().getNidParty() != null){
                    ejbQl = ejbQl.concat(" AND m.trManifiesto.nidParty = "+beanManifiesto.getTrManifiesto().getNidParty());   
                }
                if(beanManifiesto.getTrManifiesto().getCRazonSocial() != null){
                    ejbQl = ejbQl.concat(" AND upper(m.trManifiesto.cRazonSocial) like '%"+beanManifiesto.getTrManifiesto().getCRazonSocial().toUpperCase()+"%' ");
                }
            }
            ejbQl = ejbQl.concat(" ORDER BY m.nidManifiesto DESC ");
            List<TRManifiesto> lstManif = new ArrayList<TRManifiesto>();
            if(beanManifiesto.getFechaManifiestoMIN() != null && beanManifiesto.getFechaManifiestoMAX() != null){
                lstManif = em.createQuery(ejbQl)
                             .setParameter("min", beanManifiesto.getFechaManifiestoMIN(), TemporalType.DATE)
                             .setParameter("max", beanManifiesto.getFechaManifiestoMAX(), TemporalType.DATE).getResultList();
            }else{
                lstManif = em.createQuery(ejbQl).getResultList();
            }
            int size = lstManif == null ? 0 : lstManif.size();
            if (size > 0) {
                return getListaManifs(lstManif);
            } else {
                return new ArrayList<BeanManifiesto>();
            }
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<BeanManifiesto>();
        }
    }
    
    public List<BeanManifiesto> getListaManifs(List<TRManifiesto> lstManif){
        try{
            MapperIF mapper = new DozerBeanMapper();
            List<BeanManifiesto> lstBeanManif = new ArrayList<BeanManifiesto>();
            BeanManifiesto beanManifiesto = null;
            int indx = 0;
            for(TRManifiesto manif : lstManif){
                beanManifiesto = (BeanManifiesto) mapper.map(manif,BeanManifiesto.class);
                beanManifiesto.setIndex(indx);             
                BeanConstraint constr = bdL_C_SFUtilsLocal.getCatalogoConstraints("C_TIPO_DOC","TRMMANI",manif.getCTipoDoc());
                beanManifiesto.setTipoDoc1Car(constr.getCDescrip());
                constr = bdL_C_SFUtilsLocal.getCatalogoConstraints("ESTMANI","TRMMANI",manif.getEstadoManifiestoNegocio());
                beanManifiesto.setDescEstadoManifiestoNegocio(constr.getCDescrip());
                List<BeanFlota> lstBeanFlota = ln_C_SFFlotaLocal.findFlotasByAttr_LN(null,manif.getNidFlota());
                if(lstBeanFlota != null){
                    if(lstBeanFlota.size() > 0){
                        BeanFlota beanFlota = lstBeanFlota.get(0);
                        beanManifiesto.setBeanFlota(beanFlota);
                    }
                }
                List<BeanChofer> lstBeanChofer = ln_C_SFChoferLocal.findChofersByAttr_LN(null,manif.getNidChof());
                if(lstBeanChofer != null){
                    if(lstBeanChofer.size() > 0){
                        BeanChofer beanChofer = lstBeanChofer.get(0);
                        beanManifiesto.setBeanChofer(beanChofer);
                    }
                }
                lstBeanManif.add(beanManifiesto);
                indx++;
            }
            return lstBeanManif;
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<BeanManifiesto>();
        }
    }
    
    public int cantManifiestosByChofer(int nidChofer){
        try{
            String query = "SELECT count(m.nidManifiesto) " +
                           "FROM TRManifiesto m " +
                           "WHERE m.nEstManifiesto = 1 " +
                           "AND m.nidChof = :nidChof ";
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
    
    public int cantManifiestosByFlota(int nidFlota){
        try{
            String query = "SELECT count(m.nidManifiesto) " +
                           "FROM TRManifiesto m " +
                           "WHERE m.nEstManifiesto = 1 " +
                           "AND m.nidFlota = :nidFlota ";
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
    
    public List<TRManifiesto> findManifiestobyEstadoporAsignar(){
        String ejbQl = "SELECT m " +
                       "FROM TRManifiesto m JOIN FETCH m.trManifiesto " +
                       "WHERE 1 = 1 " +
                       "AND m.estadoManifiestoNegocio = 3";
        return em.createQuery(ejbQl).getResultList();
    }
    
    public int existeManifiesto(int nidManifiesto){
        try{
            String query = "SELECT count(m.nidManifiesto) " +
                           "FROM TRManifiesto m " +
                           "WHERE m.nidManifiesto = :nidManifiesto ";                           
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
}
