package silat.servicios_negocio.BDLSF.SFBean;

import java.math.BigDecimal;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_T_SFOrdenServicioLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_T_SFOrdenServicioRemoto;
import silat.servicios_negocio.entidades.admin.ADEmpresa;
import silat.servicios_negocio.entidades.audsis.STError;
import silat.servicios_negocio.entidades.audsis.STRol;
import silat.servicios_negocio.entidades.audsis.TROrdenServicio;
import silat.servicios_negocio.util_formato.UtilsGeneral;

@Stateless(name = "BDL_T_SFOrdenServicio", mappedName = "mapBDL_T_SFOrdenServicio")
public class BDL_T_SFOrdenServicioBean implements BDL_T_SFOrdenServicioRemoto, 
                                                  BDL_T_SFOrdenServicioLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName="SILATNEGOCIO")
    private EntityManager em;
    private final static String NO_ERROR = "000";
    
    public BDL_T_SFOrdenServicioBean() {
    }


    /** <code>select o from TROrdenServicio o</code> */
    public List<TROrdenServicio> getTROrdenServicioFindAll() {
        return em.createNamedQuery("TROrdenServicio.findAll").getResultList();
    }
    public BigDecimal getNidParty(String Nombre){
        String ejbQuery = "Select u.nidParty from ADEmpresa u where u.cRazonSocial = :Nombre";
        return (BigDecimal)em.createQuery(ejbQuery).setParameter("Nombre", Nombre).getSingleResult();
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public TROrdenServicio persistTROrdenServicio(TROrdenServicio TROrdenServicio) {
        em.persist(TROrdenServicio);
        return TROrdenServicio;
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public TROrdenServicio mergeTROrdenServicio(TROrdenServicio TROrdenServicio) {
        return em.merge(TROrdenServicio);
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void removeTROrdenServicio(TROrdenServicio TROrdenServicio) {
        TROrdenServicio = em.find(TROrdenServicio.class, TROrdenServicio.getNidOrdnServ());
        em.remove(TROrdenServicio);
    }
    
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public String grabarOrdenServicio(TROrdenServicio TROrdenServicio){
        String error = NO_ERROR;
        try{
            persistTROrdenServicio(TROrdenServicio); 
            return error;
        }catch(Exception e){
            e.printStackTrace();
            error = "LUB-0006";
            return error;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public String ModificarOrdenServicio(TROrdenServicio TROrdenServicio){
        String error = NO_ERROR;
        try{
            mergeTROrdenServicio(TROrdenServicio);
            return error;
        }catch(Exception e){
            e.printStackTrace();
            error = "LUB-0006";
            return error;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void flgVistoNuevasOrdenServicio_BDL(){
        String query = "UPDATE TROrdenServicio os " + 
                       "set os.flgVisto = :estVisto";
        final int cambiosOS = em.createQuery(query).setParameter("estVisto","1").executeUpdate();
        UtilsGeneral.depurar("cambios : "+cambiosOS);
    }
}
