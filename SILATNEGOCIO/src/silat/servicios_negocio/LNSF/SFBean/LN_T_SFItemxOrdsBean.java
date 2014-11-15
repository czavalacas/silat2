package silat.servicios_negocio.LNSF.SFBean;

import java.math.BigDecimal;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFItemXOrdsLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_T_SFItemXOrdenServLocal;
import silat.servicios_negocio.LNSF.IL.LN_T_SFItemxOrdsLocal;
import silat.servicios_negocio.LNSF.IR.LN_T_SFItemxOrdsRemota;
import silat.servicios_negocio.entidades.trans.TRItemXOrds;

@Stateless(name = "LN_T_SFItemxOrds", mappedName = "map-LN_T_SFItemxOrds")
public class LN_T_SFItemxOrdsBean implements LN_T_SFItemxOrdsRemota, LN_T_SFItemxOrdsLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    @EJB
    private BDL_C_SFItemXOrdsLocal bDL_C_SFItemXOrdsLocal;
    @EJB
    private BDL_T_SFItemXOrdenServLocal bDL_T_SFItemXOrdenServLocal;
    public LN_T_SFItemxOrdsBean() {
    }
    

    public void cambiarEstadoItemOrds(String nidItem){
        TRItemXOrds itemOrds = new TRItemXOrds();
        itemOrds = bDL_C_SFItemXOrdsLocal.findTRItemtById(new BigDecimal(nidItem));
        itemOrds.setCEstado("1");
        bDL_T_SFItemXOrdenServLocal.mergeTRItemXOrds(itemOrds);
    }

}
