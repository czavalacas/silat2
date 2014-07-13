package silat.servicios_negocio.BDLSF.SFBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFTipoGastoLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFTipoGastoRemoto;
import silat.servicios_negocio.Beans.BeanTipoGasto;
import silat.servicios_negocio.entidades.admin.ADTipoGasto;

@Stateless(name = "BDL_C_SFTipoGasto", mappedName = "LUBAL_SIAT_APP-SILATNEGOCIO-BDL_C_SFTipoGasto")
public class BDL_C_SFTipoGastoBean implements BDL_C_SFTipoGastoRemoto, 
                                              BDL_C_SFTipoGastoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_C_SFTipoGastoBean() {
    }

    /** <code>select o from ADTipoGasto o</code> */
    public List<ADTipoGasto> getAdmtigaFindAll() {
        return em.createNamedQuery("Admtiga.findAll").getResultList();
    }
    
    public List<BeanTipoGasto> getTiposGastosAll_BD(BeanTipoGasto btGasto){
        try{
            String query = "SELECT NEW silat.servicios_negocio.Beans.BeanTipoGasto(o.estadoRegistro," +
                                                                                  "o.descripcionTipoGasto," +
                                                                                  "o.nidTiga," +
                                                                                  "o.isRuta," +
                                                                                  "c.cDescrip) "+
                           "FROM ADTipoGasto o," +
                           "ADConstraint c " +
                           "WHERE c.cNombreTabla = 'ADMTIGA' " +
                           "AND c.cCampo = 'C_ESTREG' " +
                           "AND c.cValorCampo = o.estadoRegistro ";
            if(btGasto != null){
                if(btGasto.getDescripcionTipoGasto() != null){
                    query = query.concat(" AND upper(o.descripcionTipoGasto) like '%"+btGasto.getDescripcionTipoGasto().toUpperCase()+"%' ");
                }
                if(btGasto.getEstadoRegistro() != null){
                    query = query.concat(" AND o.estadoRegistro = '"+btGasto.getEstadoRegistro()+"' ");
                }
            }
            List<BeanTipoGasto> results =  em.createQuery(query).getResultList();
            return results;
        }catch(Exception e){
             e.printStackTrace();
             return new ArrayList<BeanTipoGasto>();
        }
    }
        
     public ADTipoGasto findADTipoGastoById(Integer id) {
         try {
             ADTipoGasto instance = em.find(ADTipoGasto.class, id);
             return instance;
         } catch (RuntimeException re) {
             throw re;
         }
     }
}
