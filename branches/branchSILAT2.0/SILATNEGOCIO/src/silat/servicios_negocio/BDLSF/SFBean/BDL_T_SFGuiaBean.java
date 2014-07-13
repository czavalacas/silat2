package silat.servicios_negocio.BDLSF.SFBean;

import java.math.BigDecimal;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_T_SFGuiaLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_T_SFGuiaRemote;
import silat.servicios_negocio.entidades.trans.TRGuia;
import silat.servicios_negocio.util_formato.UtilsGeneral;

@Stateless(name = "BDL_T_SFGuia", mappedName = "mapBDL_T_SFGuia")
public class BDL_T_SFGuiaBean implements BDL_T_SFGuiaRemote, 
                                         BDL_T_SFGuiaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_T_SFGuiaBean() {
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public TRGuia persistTRGuia(TRGuia trGuia) {
        em.persist(trGuia);
        em.flush();
        em.refresh(trGuia);
        return trGuia;
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public TRGuia mergeTRGuia(TRGuia TRGuia) {
        return em.merge(TRGuia);
    }

    public void removeTRGuia(TRGuia TRGuia) {
        TRGuia = em.find(TRGuia.class, TRGuia.getCidGuia());
        em.remove(TRGuia);
    }
    
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public TRGuia registrarGuia_BD(TRGuia trGuia, int opc){
        try{
            if(opc == 2){//MERGE/UPDATE
                trGuia = this.mergeTRGuia(trGuia);
            }else{//PERSIST/INSERT = 1
                trGuia = this.persistTRGuia(trGuia);
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return trGuia;
    }
    
    public void cambiarNidItmPreFacturaANullXQSeraBorrada(BigDecimal nidPref){
        try {
            String query = "UPDATE TRMGUIA G " + 
                           "SET G.NID_PREFITM = NULL " +
                           "WHERE G.NID_PREFITM = " + nidPref;
            int c = em.createNativeQuery(query).executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
