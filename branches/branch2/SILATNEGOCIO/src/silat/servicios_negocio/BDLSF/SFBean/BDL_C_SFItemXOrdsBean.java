package silat.servicios_negocio.BDLSF.SFBean;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFItemXOrdsLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFItemXOrdsRemote;
import silat.servicios_negocio.entidades.trans.TRItem;
import silat.servicios_negocio.entidades.trans.TRItemXOrds;
import silat.servicios_negocio.entidades.trans.TrItemPreFactura;

@Stateless(name = "BDL_C_SFItemXOrds", mappedName = "LUBAL_SIAT_APP-SILATNEGOCIO-BDL_C_SFItemXOrds")
public class BDL_C_SFItemXOrdsBean implements BDL_C_SFItemXOrdsRemote, 
                                              BDL_C_SFItemXOrdsLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_C_SFItemXOrdsBean() {
    }

    /** <code>select o from TRItemXOrds o</code> */
    public List<TRItemXOrds> getTRItemXOrdsFindAll() {
        return em.createNamedQuery("TRItemXOrds.findAll").getResultList();
    }
    
    public TRItemXOrds findTRItemtById(BigDecimal id) {
        try {
            TRItemXOrds instance = em.find(TRItemXOrds.class, id);
            return instance;
        } catch (RuntimeException re) {
            throw re;
        }
    }
    
    public List<TRItemXOrds> getTrItemOrdenServicio_BD(int nidOrdenServ, int opc) {//opc==0; items no usados en alguna guia
        try{
            String query = "SELECT o " +
                           "FROM TRItemXOrds o " +
                           "WHERE o.trOrdenServicio.nidOrdnServ = :nidOrdServ ";
                   if(opc==0){    
                   query = query.concat("AND o.cEstado='0' ") ;
                            }            
                   query = query.concat("ORDER BY o.orden ASC")    ;
            return em.createQuery(query).setParameter("nidOrdServ",nidOrdenServ).getResultList();
           }catch(Exception e){
            return new ArrayList<TRItemXOrds>();
        }
    }
    public TRItemXOrds traeItemsporID(String id){
        TRItemXOrds item = new TRItemXOrds();
        String query = "SELECT o FROM TRItemXOrds o WHERE o.nidItem = "+id;
        item = (TRItemXOrds) em.createQuery(query).getResultList().get(0);
        return item;
    }
}
