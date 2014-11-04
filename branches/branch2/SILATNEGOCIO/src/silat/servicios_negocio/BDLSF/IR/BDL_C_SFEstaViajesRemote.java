package silat.servicios_negocio.BDLSF.IR;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanEstCliente;

@Remote
public interface BDL_C_SFEstaViajesRemote {
    public List<BeanEstCliente> call_Procedure_GET_VIAJES_EXITOSOS(Date fecMin, Date fecMax, int limitInf, int limitSup);
    public List<BeanEstCliente> call_Procedure_GET_VIAJES_MES(Date fecMin, Date fecMax);
    public List<BeanEstCliente> call_Procedure_GET_VIAJES_MES_PROV_PROP(Date fecMin, Date fecMax,int tipo);
    
}
