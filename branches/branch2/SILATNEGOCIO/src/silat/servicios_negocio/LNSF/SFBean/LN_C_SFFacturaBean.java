package silat.servicios_negocio.LNSF.SFBean;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFFacturaLocal;
import silat.servicios_negocio.Beans.BeanFactura;
import silat.servicios_negocio.Beans.BeanNota;
import silat.servicios_negocio.LNSF.IL.LN_C_SFFacturaLocal;
import silat.servicios_negocio.LNSF.IR.LN_C_SFFacturaRemote;

@Stateless(name = "LN_C_SFFactura", mappedName = "mapLN_C_SFFactura")
public class LN_C_SFFacturaBean implements LN_C_SFFacturaRemote, 
                                           LN_C_SFFacturaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFFacturaLocal bdL_C_SFFacturaLocal;

    public LN_C_SFFacturaBean() {
    }
     
    public List<BeanFactura> findFacturasByAttr_LN(Date fecMin,
                                                   Date fecMax,
                                                   BigDecimal subTotal,
                                                   BigDecimal total,
                                                   int estado,
                                                   String codFact,
                                                   String cidUN,
                                                   String guias,
                                                   String cliente,
                                                   String simboloSubTotal,
                                                   String simboloTotal,
                                                   String busqTipNota,
                                                   String simbNota,
                                                   BigDecimal busqMontoNota){
        BeanFactura fact = new BeanFactura();
        fact.setFecMax(fecMax); 
        fact.setSimbSubTotal(simboloSubTotal);
        fact.setSimbTotal(simboloTotal);
        fact.setFecMin(fecMin);
        fact.setCCodFactura(codFact);
        fact.setCidUnidadNegocio(cidUN);
        fact.setCliente(cliente);
        fact.setDSubTotal(subTotal);
        fact.setDTotal(total);
        fact.setNEstadoFactura(estado);
        fact.setGuias(guias);
        if(busqTipNota != null){
            BeanNota beanNota = new BeanNota();
            beanNota.setCTipoNota(busqTipNota);
            beanNota.setSimbNota(simbNota);
            beanNota.setDMonto(busqMontoNota);
            fact.setBeanNota(beanNota);
        }
        return bdL_C_SFFacturaLocal.findFacturasByAttr_BD(fact);
    }
    
    public int cantNotaByFactura_LN(Long nidFactura){
        return bdL_C_SFFacturaLocal.cantNotaByFactura(nidFactura);
    }
}