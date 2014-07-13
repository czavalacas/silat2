package silat.servicios_negocio.LNSF.SFBean;

import java.math.BigDecimal;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFRolLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFUtilsLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_T_SFRolLocal;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanRol;
import silat.servicios_negocio.LNSF.IL.LN_C_SFCatalogoErroresLocal;
import silat.servicios_negocio.LNSF.IL.LN_T_SFRolLocal;
import silat.servicios_negocio.LNSF.IR.LN_T_SFRolRemote;
import silat.servicios_negocio.entidades.audsis.STRol;

@Stateless(name = "LN_T_SFRol", mappedName = "mapLN_T_SFRol")
public class LN_T_SFRolBean implements LN_T_SFRolRemote, 
                                       LN_T_SFRolLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_T_SFRolLocal bdL_T_SFRolLocal;
    @EJB
    private BDL_C_SFRolLocal bdL_C_SFRolLocal;
    @EJB
    private BDL_C_SFUtilsLocal bdL_C_SFUtilsLocal;
    @EJB
    private LN_C_SFCatalogoErroresLocal ln_C_SFCatalogoErroresLocal;

    public LN_T_SFRolBean() {
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public BeanRol registrarRol(String descRol,
                                int tipEvento,
                                BigDecimal nidRol){
        BeanError beanError = new BeanError();
        BeanRol bRol = new BeanRol();
        STRol eRol = new STRol();
        String error = "000";
        try{
            int cantDesc = 0;
            if(tipEvento == 1){//Registrar
                cantDesc = bdL_C_SFUtilsLocal.findCountByProperty("cDescRole",descRol,"STRol",true,true,"");
                if(cantDesc > 0){
                    error = "LUB-0032";
                }
            }else{
                eRol = bdL_C_SFRolLocal.findSTRolById(nidRol);
                cantDesc = bdL_C_SFUtilsLocal.findCountByProperty("cDescRole",descRol,"STRol",true,false,eRol.getCDescRole());
                if(cantDesc > 0){
                    error = "LUB-0032";
                }
            }
            if(error.equals("000")){
                eRol.setCDescRole(descRol);
                eRol.setNEstado(new BigDecimal("1"));
                if(tipEvento == 1){
                    bdL_T_SFRolLocal.persistSTRol(eRol);
                }else{
                    bdL_T_SFRolLocal.mergeSTRol(eRol);
                }
            }
        }catch(Exception e){
            error = "LUB-0031";
        }finally{
            beanError = ln_C_SFCatalogoErroresLocal.getCatalogoErrores(error);
            bRol.setBeanError(beanError);
            return bRol;
        }
    }
}