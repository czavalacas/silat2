package silat.servicios_negocio.LNSF.SFBean;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFEstaClienteLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFEstaViajesLocal;
import silat.servicios_negocio.Beans.BeanEstCliente;
import silat.servicios_negocio.LNSF.IL.LN_C_SFEstaViajesLocal;
import silat.servicios_negocio.LNSF.IR.LN_C_SFEstaViajesRemote;
import silat.servicios_negocio.util_formato.Fecha.FechaUtiles;

@Stateless(name = "LN_C_SFEstaViajes", mappedName = "map-LN_C_SFEstaViajes")
public class LN_C_SFEstaViajesBean implements LN_C_SFEstaViajesRemote, LN_C_SFEstaViajesLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFEstaViajesLocal bdL_C_SFEstaViajesLocal;

    public LN_C_SFEstaViajesBean() {
    }
    
    public List<BeanEstCliente> getViajesExitosos(Date fecMin, Date fecMax, int limitInf, int limitSup){
        if(fecMin == null){
            fecMin = FechaUtiles.getPrimerDiaDelANIO();
        }
        if(fecMax == null){
            fecMax = FechaUtiles.fechaActual();
        }
        return bdL_C_SFEstaViajesLocal.call_Procedure_GET_VIAJES_EXITOSOS(fecMin, fecMax, limitInf, limitSup);    
    }
    
    public List<BeanEstCliente> getViajesMesGeneral(Date fecMin, Date fecMax){
        if(fecMin == null){
            fecMin = FechaUtiles.getPrimerDiaDelANIO();
        }
        if(fecMax == null){
            fecMax = FechaUtiles.fechaActual();
        }
        return bdL_C_SFEstaViajesLocal.call_Procedure_GET_VIAJES_MES(fecMin, fecMax);    
    }
    
    public List<BeanEstCliente> getViajesProvProp(Date fecMin, Date fecMax,int tipo){
        if(fecMin == null){
            fecMin = FechaUtiles.getPrimerDiaDelANIO();
        }
        if(fecMax == null){
            fecMax = FechaUtiles.fechaActual();
        }
        return bdL_C_SFEstaViajesLocal.call_Procedure_GET_VIAJES_MES_PROV_PROP(fecMin, fecMax, tipo);    
    }
    
}
