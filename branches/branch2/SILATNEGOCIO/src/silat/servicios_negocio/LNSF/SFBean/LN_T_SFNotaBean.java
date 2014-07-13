package silat.servicios_negocio.LNSF.SFBean;

import java.math.BigDecimal;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFFacturaLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_T_SFNotaLocal;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanFactura;
import silat.servicios_negocio.Beans.BeanNota;
import silat.servicios_negocio.LNSF.IL.LN_C_SFCatalogoErroresLocal;
import silat.servicios_negocio.LNSF.IL.LN_T_SFNotaLocal;
import silat.servicios_negocio.LNSF.IR.LN_T_SFNotaRemote;
import silat.servicios_negocio.entidades.trans.TRFactura;
import silat.servicios_negocio.entidades.trans.TRNota;

@Stateless(name = "LN_T_SFNota", mappedName = "LUBAL_SIAT_APP-SILATNEGOCIO-LN_T_SFNota")
public class LN_T_SFNotaBean implements LN_T_SFNotaRemote,
                                        LN_T_SFNotaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    private LN_C_SFCatalogoErroresLocal ln_C_SFCatalogoErroresLocal;
    @EJB
    private BDL_C_SFFacturaLocal bdL_C_SFFacturaLocal;
    @EJB
    private BDL_T_SFNotaLocal bdL_T_SFNotaLocal;
    
    public LN_T_SFNotaBean() {
    }
    
    public BeanNota registrarNota(String cTipoNota,
                                  BigDecimal dMonto,
                                  BeanFactura trFactura){
        BeanError beanError = new BeanError();
        TRNota eNota = new TRNota();
        BeanNota bNota = new BeanNota();
        String error = "000";
        try{
            eNota.setCTipoNota(cTipoNota);
            eNota.setDMonto(dMonto);
            eNota.setNEstadoNota(1);
            TRFactura eFact = bdL_C_SFFacturaLocal.findTRFacturaById(trFactura.getNidFactura());
            eNota.setTrFactura(eFact);
            eNota = bdL_T_SFNotaLocal.persistTRNota(eNota);
        }catch(Exception e){
            e.printStackTrace();
            error = "LUB-0021";
        }
        beanError = ln_C_SFCatalogoErroresLocal.getCatalogoErrores(error);
        bNota.setBeanError(beanError);
        return bNota;
    }
}