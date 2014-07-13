package silat.servicios_negocio.LNSF.SFBean;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFCuadreLocal;
import silat.servicios_negocio.Beans.BeanCuadre;
import silat.servicios_negocio.LNSF.IL.LN_C_SFCuadreLocal;
import silat.servicios_negocio.LNSF.IR.LN_C_SFCuadreRemote;
import silat.servicios_negocio.util_formato.Fecha.FechaUtiles;
import silat.servicios_negocio.util_formato.UtilsGeneral;

@Stateless(name = "LN_C_SFCuadre", mappedName = "mapLN_C_SFCuadre")
public class LN_C_SFCuadreBean implements LN_C_SFCuadreRemote, 
                                          LN_C_SFCuadreLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFCuadreLocal bdL_C_SFCuadreLocal;
    
    public LN_C_SFCuadreBean() {
    }
    
    public List<BeanCuadre> getReporteCuadre(Date fecMin, Date fecMax){
        if(fecMin == null){
            fecMin = new Date("01/01/2010");
        }
        if(fecMax == null){
            fecMax = FechaUtiles.fechaActual();
        }
        return bdL_C_SFCuadreLocal.getReporteCuadre(fecMin, fecMax); 
    }
}