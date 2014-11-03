package silat.servicios_negocio.BDLSF.SFBean;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFItemLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFItemRemote;
import silat.servicios_negocio.entidades.trans.TRItem;
import silat.servicios_negocio.entidades.trans.TRItemXOrds;

@Stateless(name = "BDL_C_SFItem", mappedName = "mapBDL_C_SFItem")
public class BDL_C_SFItemBean implements BDL_C_SFItemRemote, 
                                         BDL_C_SFItemLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_C_SFItemBean() {
    }
    
    public TRItem findTRItemtById(BigDecimal id) {
        try {
            TRItem instance = em.find(TRItem.class, id);
            return instance;
        } catch (RuntimeException re) {
            throw re;
        }
    }
    
    public List<TRItem> getTrItemGuia_BD(String cidGuia) {
        try{
               System.out.println("::Cid Guia:: "+cidGuia);
            String query = "SELECT o " +
                           "FROM TRItem o " +
                           "WHERE o.trGuia.cidGuia = :cidGuia " +
                           "ORDER BY o.orden ASC";           
            System.out.println("::QUERY:: "+query);
            return em.createQuery(query).setParameter("cidGuia",cidGuia).getResultList();
           }catch(Exception e){
            return new ArrayList<TRItem>();
        }
    }
}