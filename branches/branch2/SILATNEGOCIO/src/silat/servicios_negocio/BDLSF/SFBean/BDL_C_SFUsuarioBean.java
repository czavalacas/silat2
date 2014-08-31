package silat.servicios_negocio.BDLSF.SFBean;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFUsuarioLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFUsuarioXPermisoLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFUtilsLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFUsuarioRemote;
import silat.servicios_negocio.entidades.admin.ADClave;
import silat.servicios_negocio.entidades.admin.ADUsuario;

@Stateless(name = "BDL_C_SFUsuario", mappedName = "mapSFUsuario")
public class BDL_C_SFUsuarioBean implements BDL_C_SFUsuarioRemote, 
                                            BDL_C_SFUsuarioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName="SILATNEGOCIO")
    private EntityManager em;
    @EJB
    BDL_C_SFUtilsLocal bdL_C_SFUtilsLocal;
    @EJB
    protected BDL_C_SFUsuarioXPermisoLocal bdL_C_SFUsuarioXPermisoLocal; 
    
    public BDL_C_SFUsuarioBean() {
    }

    /** <code>select o from ADUsuario o</code> */
    public List<ADUsuario> getADUsuarioFindAll() {
        return em.createNamedQuery("ADUsuario.findAll").getResultList();
    }
    
    public List<ADUsuario> getUsuariosNoAdmin(){
            String ejbQL =  "select o " +
                            "from ADUsuario o " +
                            "where o.nTipoUsuario <> 1 ";
            return em.createQuery(ejbQL)                   
                    .getResultList();
    }

    public ADUsuario findADUsuarioById(BigDecimal id) {
        try {
            ADUsuario instance = em.find(ADUsuario.class, id);
            return instance;
        } catch (RuntimeException re) {
            throw re;
        }
    }
    
    public int countNombreUsuario(String nombreUsuario) {
        int cant = 0;
        try {
            String ejbQL = "select o " + 
                           "from ADUsuario o " +
                           "where lower(o.cUsuario) = :usuNomb ";
            List o = em.createQuery(ejbQL).setParameter("usuNomb", nombreUsuario.toLowerCase()).getResultList();
            if (!o.isEmpty()) {
                cant = Integer.parseInt(o.get(0).toString());
            }
            return cant;
        } catch (NoResultException nfe) {
            nfe.printStackTrace();
            return cant;
        }
    }
    
   public Map autenticarUsuario(String username, String clave){     
        String error = "000";    
        Map mapSalida = new HashMap();     
        String ejbQl = " SELECT u " +
                       " FROM ADClave u "+
                       " WHERE u.user = :usuario " +
                       " AND u.cClave = FUNC('AES_ENCRYPT',:cClave,:cClave)" +
                       " AND u.adUsuario.nEstadoUsuario = 1 ";
   
        try{       
           List<ADClave>  listClave =    em.createQuery(ejbQl).setParameter("usuario", username).
                                                               setParameter("cClave",clave).
                                                               getResultList();
          
            if (listClave.size() != 0) {
                mapSalida.put("usuario", listClave.get(0));
            }else{
                error = "LUB-0002";
            }    
        }catch(Exception e){
            e.printStackTrace();
            error = "LUB-0004";
           
        }
        mapSalida.put("error", error);
        return mapSalida;
    }
    
    public boolean esRol(BigDecimal rol,List<BigDecimal> roles){
        boolean flg = false;
        for(int i = 0; i < roles.size(); i++){
            if(roles.get(i).intValue() == rol.intValue()){
                flg = true;
            }
        }
        return flg;
    }
}