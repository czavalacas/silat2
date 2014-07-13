package silat.servicios_negocio.BDLSF.SFBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFUnidadMedidaLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFUnidadMedidaRemote;
import silat.servicios_negocio.Beans.BeanUnidadMedida;
import silat.servicios_negocio.entidades.trans.TRUnidadMedida;

@Stateless(name = "BDL_C_SFUnidadMedida", mappedName = "mapBDL_C_SFUnidadMedida")
public class BDL_C_SFUnidadMedidaBean implements BDL_C_SFUnidadMedidaRemote,
                                                 BDL_C_SFUnidadMedidaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_C_SFUnidadMedidaBean() {
    }

    /** <code>select o from TRUnidadMedida o</code> */
     public List<TRUnidadMedida> getTRUnidadMedidaFindAll() {
         return em.createNamedQuery("TRUnidadMedida.findAll").getResultList();
     }
    
    public List<BeanUnidadMedida> getUnidadesMedida_BDL() {
        try{
            String query = "SELECT NEW silat.servicios_negocio.Beans.BeanUnidadMedida(um.descUnidadMedida," +
                                                                                     "um.sigla," +
                                                                                     "um.nidUnidadMedida) "+
                            "FROM TRUnidadMedida um ";
            List<BeanUnidadMedida> unidades = em.createQuery(query).getResultList();
            return unidades;
        }catch(Exception e){
             e.printStackTrace();
             return new ArrayList<BeanUnidadMedida>();
        }
    }
}
