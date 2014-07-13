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
import javax.persistence.TemporalType;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFFacturaLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFUtilsLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFFacturaRemote;
import silat.servicios_negocio.Beans.BeanChofer;
import silat.servicios_negocio.Beans.BeanConstraint;
import silat.servicios_negocio.Beans.BeanFactura;
import silat.servicios_negocio.Beans.BeanFlota;
import silat.servicios_negocio.Beans.BeanManifiesto;
import silat.servicios_negocio.Beans.BeanNota;
import silat.servicios_negocio.Beans.BeanTRItem;
import silat.servicios_negocio.entidades.trans.TRFactura;
import silat.servicios_negocio.entidades.trans.TRManifiesto;
import silat.servicios_negocio.entidades.trans.TRNota;

@Stateless(name = "BDL_C_SFFactura", mappedName = "mapBDL_C_SFFactura")
public class BDL_C_SFFacturaBean implements BDL_C_SFFacturaRemote, 
                                            BDL_C_SFFacturaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFUtilsLocal bdL_C_SFUtilsLocal;
    
    public BDL_C_SFFacturaBean() {
    }

    /** <code>select o from TRFactura o</code> */
    public List<TRFactura> getTRFacturaFindAll() {
        return em.createNamedQuery("TRFactura.findAll").getResultList();
    }
    
    public TRFactura findTRFacturaById(Long id) {
        try {
            TRFactura instance = em.find(TRFactura.class, id);
            return instance;
        } catch (RuntimeException re) {
            throw re;
        }
    }
    
    public List<BeanFactura> findFacturasByAttr_BD(BeanFactura fac){
        try{
            String ejbQl = "SELECT f " +
                           "FROM TRFactura f ";
             if(fac.getBeanNota() != null) {
                 if (fac.getBeanNota().getCTipoNota() != null) {
                     ejbQl = ejbQl.concat(", TRNota n WHERE f.nidFactura = n.trFactura.nidFactura  AND n.cTipoNota = '"+fac.getBeanNota().getCTipoNota()+"' ");
                 }
                 if (fac.getBeanNota().getCTipoNota() != null && fac.getBeanNota().getDMonto() != null && fac.getBeanNota().getSimbNota() != null){
                     if(fac.getBeanNota().getDMonto() != new BigDecimal(0)){
                        ejbQl = ejbQl.concat(" AND n.dMonto " + fac.getBeanNota().getSimbNota() + "'" + fac.getBeanNota().getDMonto() + "' ");
                     } 
                 }else if(fac.getBeanNota().getCTipoNota() == null && fac.getBeanNota().getDMonto() != null &&
                          fac.getBeanNota().getSimbNota() != null){
                    if(fac.getBeanNota().getDMonto() != new BigDecimal(0)){
                        ejbQl = ejbQl.concat(", TRNota n WHERE f.nidFactura = n.trFactura.nidFactura  AND n.dMonto " + fac.getBeanNota().getSimbNota() +" '" + fac.getBeanNota().getDMonto() + "' ");
                    }
                }
            }else{
                ejbQl = ejbQl.concat(" WHERE 1 = 1 ");
            }
            if(fac.getFecMin() != null && fac.getFecMax() != null){
                ejbQl = ejbQl.concat(" AND f.fechaFactura BETWEEN :fecMinFac AND :fecMaxFac ");
            }
            if(fac.getDSubTotal() != null && fac.getSimbSubTotal() != null){
                if(fac.getDSubTotal() != new BigDecimal(0)){
                    ejbQl = ejbQl.concat(" AND f.dSubTotal "+fac.getSimbSubTotal()+" "+fac.getDSubTotal());
                }             
            }
            if(fac.getDTotal() != null && fac.getSimbTotal() != null){
                if(fac.getDTotal() != new BigDecimal(0)){
                    ejbQl = ejbQl.concat(" AND f.dTotal "+fac.getSimbTotal()+" "+fac.getDTotal());
                }  
            }
            if(fac.getNEstadoFactura() != null){
                if(fac.getNEstadoFactura() != 0){
                    ejbQl = ejbQl.concat(" AND f.nEstadoFactura  = "+fac.getNEstadoFactura());
                }  
            }
            if(fac.getCCodFactura() != null){
                ejbQl = ejbQl.concat(" AND f.cCodFactura  like '%"+fac.getCCodFactura()+"%' ");
            }
            if(fac.getCidUnidadNegocio() != null){
                ejbQl = ejbQl.concat(" AND f.cidUnidadNegocio  like '%"+fac.getCidUnidadNegocio()+"%' ");
            }
            if(fac.getGuias() != null){
                ejbQl = ejbQl.concat(" AND f.guias  like '%"+fac.getGuias()+"%' ");
            }
            if(fac.getCliente() != null){
                ejbQl = ejbQl.concat(" AND upper(f.cliente)  like '%"+fac.getCliente().toUpperCase()+"%' ");
            }
            List<TRFactura> lstFacts = new ArrayList<TRFactura>();
            if(fac.getFecMin() != null && fac.getFecMax() != null){
                lstFacts = em.createQuery(ejbQl).setParameter("fecMinFac",fac.getFecMin(), TemporalType.DATE).
                                                 setParameter("fecMaxFac", fac.getFecMax(),TemporalType.DATE).getResultList();
            }else{
                lstFacts = em.createQuery(ejbQl).getResultList();
            }
            int size = lstFacts == null ? 0 : lstFacts.size();
            if (size > 0) {
                return getListaFacturas(lstFacts);
            } else {
                return new ArrayList<BeanFactura>();
            }
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<BeanFactura>();
        }
    }
    
    public List<BeanFactura> getListaFacturas(List<TRFactura> lstFac){
        try{
            MapperIF mapper = new DozerBeanMapper();
            List<BeanFactura> lstBeanFact = new ArrayList<BeanFactura>();
            BeanFactura beanFactura = null;
            for(TRFactura fact : lstFac){
                beanFactura = (BeanFactura) mapper.map(fact,BeanFactura.class);
                BeanConstraint constr = bdL_C_SFUtilsLocal.getCatalogoConstraints("N_ESTADO_FACTURA","TRMFACT",fact.getNEstadoFactura().toString());
                beanFactura.setDescEstado(constr.getCDescrip());
                beanFactura.setBeanNota(getNota(fact.getNotasList()));
                String estilo = null;
                switch(beanFactura.getNEstadoFactura()){
                    case 1 : estilo = "color:White; font-weight:bold;text-align:center; background-color:green";break;//PAGADA
                    case 2 : estilo = "color:White; font-weight:bold;text-align:center; background-color:orange";break;//X CANCELAR
                    case 3 : estilo = "color:White; font-weight:bold;text-align:center; background-color:red";break;//ANULADA
                }
                beanFactura.setStyleEstado(estilo);
                lstBeanFact.add(beanFactura);
            }
            return lstBeanFact;
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<BeanFactura>();
        }
    }
    
    public BeanNota getNota(List<TRNota> notas){
        BeanNota bNota = new BeanNota();
        MapperIF mapper = new DozerBeanMapper();
        if(notas != null){
            if(notas.size() > 0){
                TRNota eNota = notas.get(0);
                bNota = (BeanNota)mapper.map(eNota, BeanNota.class);
                String descTipo = (bNota.getCTipoNota().equals("C") ? "Credito" : "Debito");
                bNota.setDescTipoNota(descTipo);
            }
        }
        return bNota;
    }

    public int cantNotaByFactura(Long nidFactura) {
        int cant = 0;
        try {
            String query =
                "SELECT count(f.nidFactura) " + "FROM TRFactura f," + "TRNota n " + "WHERE f.nidFactura = n.trFactura.nidFactura " +
                "AND f.nidFactura = " + nidFactura;
            Object count = em.createQuery(query).getSingleResult();
            if (count != null) {
                int iCount = Integer.parseInt(count.toString());
                cant = iCount;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cant;
    }
}
