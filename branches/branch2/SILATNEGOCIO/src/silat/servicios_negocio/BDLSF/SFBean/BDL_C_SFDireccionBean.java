package silat.servicios_negocio.BDLSF.SFBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFDireccionLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFUtilsLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFDireccionRemote;
import silat.servicios_negocio.Beans.BeanADUbigeo;
import silat.servicios_negocio.Beans.BeanChofer;
import silat.servicios_negocio.Beans.BeanCombo;
import silat.servicios_negocio.Beans.BeanDireccion;
import silat.servicios_negocio.entidades.admin.ADChofer;
import silat.servicios_negocio.entidades.admin.ADDireccion;
import silat.servicios_negocio.util_formato.UtilsGeneral;

@Stateless(name = "BDL_C_SFDireccion", mappedName = "LUBAL_SIAT_APP-SILATNEGOCIO-BDL_C_SFDireccion")
public class BDL_C_SFDireccionBean implements BDL_C_SFDireccionRemote, 
                                              BDL_C_SFDireccionLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFUtilsLocal bdL_C_SFUtilsLocal;

    public BDL_C_SFDireccionBean() {
    }

    /** <code>select o from ADDireccion o</code> */
    public List<ADDireccion> getADDireccionFindAll() {
        return em.createNamedQuery("ADDireccion.findAll").getResultList();
    }
    
    public List<ADDireccion> getDireccionOfParty(BigDecimal nidParty) {
        String ejbQuery = "Select u from ADDireccion u where u.party.nidParty = :nidParty";
        return em.createQuery(ejbQuery).setParameter("nidParty", nidParty).getResultList();
    }
    
    public List<BeanDireccion> findDireccionessByAttributes(BeanDireccion beanDireccion) {
        try{
            String ejbQl = "SELECT f " +
                           "FROM ADDireccion f " +
                           " WHERE 1 = 1";
            if(beanDireccion.getNidDireccion() != null){
                ejbQl = ejbQl.concat(" AND f.nidDireccion = "+beanDireccion.getNidDireccion());
            }
            if(beanDireccion.getParty() != null){
                if(beanDireccion.getParty().getNidParty() != null){
                    ejbQl = ejbQl.concat(" AND f.party.nidParty = "+beanDireccion.getParty().getNidParty());
                } 
            }
            if(beanDireccion.getCDireccion() != null){
                ejbQl = ejbQl.concat(" AND upper(f.cDireccion) = "+beanDireccion.getCDireccion().toUpperCase());
            }//System.out.println("query: "+ejbQl);
            List<ADDireccion> lstDirecs = em.createQuery(ejbQl).getResultList();
            int size = lstDirecs == null ? 0 : lstDirecs.size();
            if (size > 0) {
                return getListalstDirecs(lstDirecs);
            } else {
                return new ArrayList<BeanDireccion>();
            }
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<BeanDireccion>();
        }
    }
    
    public BeanDireccion getDireccionUbigeosDesc_Para_Factura(Integer nidDireccion){
        try{
            MapperIF mapper = new DozerBeanMapper();
            String query = "SELECT d "+
                           "FROM ADDireccion d " +
                           "WHERE  d.nidDireccion = "+nidDireccion;
            ADDireccion direc = (ADDireccion) em.createQuery(query).getSingleResult();
            BeanADUbigeo bDistrito = bdL_C_SFUtilsLocal.getUbigeoByCid(direc.getCidUbigeo());//150101
            String cidProv = bDistrito.getCidUbigeo().substring(0,4)+"00";
            BeanADUbigeo bProv = bdL_C_SFUtilsLocal.getUbigeoByCid(cidProv);
            String cidDep = bDistrito.getCidUbigeo().substring(0,2)+"0000";
            BeanADUbigeo bDept = bdL_C_SFUtilsLocal.getUbigeoByCid(cidDep);
            
            BeanDireccion beanDireccion = (BeanDireccion) mapper.map(direc,BeanDireccion.class);
            beanDireccion.setDescDepartamento(bDept.getCDescUbigeo());
            beanDireccion.setDescProvincia(bProv.getCDescUbigeo());
            beanDireccion.setDescDistrito(bDistrito.getCDescUbigeo());
            return beanDireccion;
        }catch(Exception e){
            return null;
        }
    }
    
    public List<BeanCombo> getListaParaCombo(String nombreEntidad, String nombreCampo, String idCampo){
        try{
            String query = "SELECT NEW silat.servicios_negocio.Beans.BeanCombo(o."+idCampo +"," +
                           " o."+nombreCampo+") "+
                           "FROM "+nombreEntidad+" o ";
            List<BeanCombo> results =  em.createQuery(query).getResultList();
            return results;
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<BeanCombo>();
        }
    }
    
    public List<BeanDireccion> getListalstDirecs(List<ADDireccion> lstDirecs){
        try{
            MapperIF mapper = new DozerBeanMapper();
            List<BeanDireccion> lstBeanDirec = new ArrayList<BeanDireccion>();
            BeanDireccion beanDireccion = null;
            for(ADDireccion direc : lstDirecs){
                beanDireccion = (BeanDireccion) mapper.map(direc,BeanDireccion.class);
                lstBeanDirec.add(beanDireccion);
            }
            return lstBeanDirec;
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<BeanDireccion>();
        }
    }
}
