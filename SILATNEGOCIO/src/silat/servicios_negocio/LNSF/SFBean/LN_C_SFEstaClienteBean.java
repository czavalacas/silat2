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
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFEstaClienteLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFEstaClienteRemoto;
import silat.servicios_negocio.Beans.BeanEstCliente;
import silat.servicios_negocio.LNSF.IL.LN_C_SFEstaClienteLocal;
import silat.servicios_negocio.LNSF.IR.LN_C_SFEstaClienteRemote;
import silat.servicios_negocio.util_formato.Fecha.FechaUtiles;

@Stateless(name = "LN_C_SFEstaCliente", mappedName = "map-LN_C_SFEstaCliente")
public class LN_C_SFEstaClienteBean implements LN_C_SFEstaClienteRemote, LN_C_SFEstaClienteLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFEstaClienteLocal bdL_C_SFEstaClienteLocal;
    
    
    public LN_C_SFEstaClienteBean() {
    }
    
    public List<BeanEstCliente> getGanaciasGeneralCliente(Date fecMin, Date fecMax, int limit){
        if(fecMin == null){
            fecMin = FechaUtiles.getPrimerDiaDelANIO();
        }
        if(fecMax == null){
            fecMax = FechaUtiles.fechaActual();
        }
        if(limit == 0){
            limit = 5;
        }
        return bdL_C_SFEstaClienteLocal.call_Procedure_GET_GANANCIAS_CLIENTE(fecMin, fecMax, limit);    
    }
    
    public List<BeanEstCliente> getGanaciasXMESCliente(Date fecMin, Date fecMax){
        if(fecMin == null){
            fecMin = FechaUtiles.getPrimerDiaDelANIO();
        }
        if(fecMax == null){
            fecMax = FechaUtiles.fechaActual();
        }
        return bdL_C_SFEstaClienteLocal.call_Procedure_GET_GANANCIAS_CLIENTE_X_MES(fecMin, fecMax);    
    }
    
    public List<BeanEstCliente> getOrdenesServicioXCLIENTE(Date fecMin, Date fecMax, int limit, String tipo){
        if(fecMin == null){
            fecMin = FechaUtiles.getPrimerDiaDelANIO();
        }
        if(fecMax == null){
            fecMax = FechaUtiles.fechaActual();
        }
        if(limit == 0){
            limit = 5;
        }
        return bdL_C_SFEstaClienteLocal.call_Procedure_GET_ORDENES_SERVICIO(fecMin, fecMax, limit, tipo);    
    }
    public List<BeanEstCliente> getOrdenesXMeses(Date fecMin, Date fecMax, String tipo){
        if(fecMin == null){
            fecMin = FechaUtiles.getPrimerDiaDelANIO();
        }
        if(fecMax == null){
            fecMax = FechaUtiles.fechaActual();
        }
        return bdL_C_SFEstaClienteLocal.call_Procedure_GET_ORDENES_SERVICIO_X_FECHAS(fecMin, fecMax, tipo);    
    }
    
}
