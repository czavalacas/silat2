package silat.servicios_negocio.BDLSF.SFBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFFlotaLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFFlotaRemote;
import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.Beans.BeanFlota;
import silat.servicios_negocio.entidades.admin.ADChofer;
import silat.servicios_negocio.entidades.admin.ADEmpresa;
import silat.servicios_negocio.entidades.admin.ADFlota;
import silat.servicios_negocio.entidades.admin.ADGasto;

/**
 * @author dfloresgonz
 * @since 12.08.2013
 */
@Stateless(name = "BDL_C_SFFlota", mappedName = "mapBDL_C_SFFlota")
public class BDL_C_SFFlotaBean implements BDL_C_SFFlotaRemote, 
                                          BDL_C_SFFlotaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_C_SFFlotaBean() {
    }

    public List<BeanFlota> findFlotasByAttributes(BeanFlota beanFlota) {
        try {
            String ejbQl = "SELECT f " +
                           "FROM ADFlota f " + 
                           "WHERE f.nEstadoFlota = 1 ";
            if (beanFlota.getNidEmpresa() != null) {
                ejbQl = ejbQl.concat(" AND f.empresa.nidParty = " + beanFlota.getNidEmpresa());
            }
            if (beanFlota.getNidFlota() != null) {
                ejbQl = ejbQl.concat(" AND f.nidFlota = " + beanFlota.getNidFlota());
            }
            List<ADFlota> lstFlotas = em.createQuery(ejbQl).getResultList();
            int size = lstFlotas == null ? 0 : lstFlotas.size();
            if (size > 0) {
                return getListaFlotas(lstFlotas);
            } else {
                return new ArrayList<BeanFlota>();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<BeanFlota>();
        }
    }

    public int verificarExistePlaca(String placa) {
        int valor = 0;
        try {
            String ejbQl = "select count(e.cPlaca) " +
                           "from ADFlota e " +
                           "where e.cPlaca='"+ placa +"'";            
            Object resultado = em.createQuery(ejbQl).getSingleResult();
            valor = Integer.parseInt(resultado.toString());
            return valor;
        } catch (Exception e) {
            e.printStackTrace();
            return 55555;
        }
    }
    
    public int getNiveles(){
        String ejbQL =  "select count(distinct(o.nNivel)) " +
                        "from ADPermiso o ";
        Object oNiveles = em.createQuery(ejbQL)
                            .getSingleResult();
        int iNiveles = 0;
        if(oNiveles != null){
            iNiveles = Integer.parseInt(oNiveles.toString());
        }
        return iNiveles;
    }

    public List<BeanFlota> getListaFlotas(List<ADFlota> lstFlotas) {
        try {
            MapperIF mapper = new DozerBeanMapper();
            List<BeanFlota> lstBeanFlota = new ArrayList<BeanFlota>();
            BeanFlota beanFlota = null;
            for (ADFlota flota : lstFlotas) {
                beanFlota = (BeanFlota) mapper.map(flota, BeanFlota.class);
                beanFlota.setCertificadoInscripcionEmpresa(flota.getEmpresa().getCCerins());
                lstBeanFlota.add(beanFlota);
            }
            return lstBeanFlota;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<BeanFlota>();
        }        
    }
    public List<ADFlota> getFlotasPorEmpresa(Integer nidEmpresa){
            String ejbQL =  "select o " +
                            "from ADFlota o " +
                            "where o.empresa.nidParty = :nidEmpresa " +
                            "AND o.nEstadoFlota = 1";
            return em.createQuery(ejbQL)
                    .setParameter("nidEmpresa",nidEmpresa)
                    .getResultList();
    }
    
    public ADFlota findADFlotaById(Integer id) {
         try {
             ADFlota instance = em.find(ADFlota.class, id);
             return instance;
         } catch (RuntimeException re) {
             throw re;
         }
     }  
}
