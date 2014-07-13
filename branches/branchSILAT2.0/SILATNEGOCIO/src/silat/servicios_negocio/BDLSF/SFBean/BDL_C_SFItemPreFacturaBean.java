package silat.servicios_negocio.BDLSF.SFBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFItemPreFacturaLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFItemPreFacturaRemote;
import silat.servicios_negocio.entidades.trans.TrItemPreFactura;

@Stateless(name = "BDL_C_SFItemPreFactura", mappedName = "mapBDL_C_SFItemPreFactura")
public class BDL_C_SFItemPreFacturaBean implements BDL_C_SFItemPreFacturaRemote,
                                                   BDL_C_SFItemPreFacturaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    public BDL_C_SFItemPreFacturaBean() {
    }

    /** <code>select o from TrItemPreFactura o</code> */
    public List<TrItemPreFactura> getTrItemPreFacturaFindAll() {
        return em.createNamedQuery("TrItemPreFactura.findAll").getResultList();
    }
    
    public List<TrItemPreFactura> getTrItemPreFacturaByPreFactura_BD(Long nidPrefact) {
        try{
            String query = "SELECT pfi " +
                           "FROM TrItemPreFactura pfi " +
                           "WHERE pfi.preFactura.nidPrefact = :nidPF " +
                           "ORDER BY pfi.orden ASC ";
            return em.createQuery(query).setParameter("nidPF",nidPrefact).getResultList();
        }catch(Exception e){
            return new ArrayList<TrItemPreFactura>();
        }
    }
}
