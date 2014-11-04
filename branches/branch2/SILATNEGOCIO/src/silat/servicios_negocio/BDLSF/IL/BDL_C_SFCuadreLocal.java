package silat.servicios_negocio.BDLSF.IL;

import java.math.BigDecimal;

import java.util.Date;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanCuadre;
import silat.servicios_negocio.Beans.BeanEstCliente;

@Local
public interface BDL_C_SFCuadreLocal {
    List<BeanCuadre> getReporteCuadre(Date fecMin, Date fecMax);
    List<BeanCuadre> call_Procedure_GET_EGRESOS(Date fecMin, Date fecMax);
    BigDecimal[] call_Procedure_GET_TOTALES(Date fecMin, Date fecMax);
    BigDecimal[] call_Procedure_GET_INGRESOS(Date fecMin, Date fecMax);
    
    // Metodos agregados para TP-2014
    public List<BeanEstCliente> call_Procedure_GET_GASTOSXMES(Date fecMin, Date fecMax);
    public List<BeanEstCliente> call_Procedure_GET_GASTOSXMESADMANI(Date fecMin, Date fecMax);
    public List<BeanEstCliente> call_Procedure_GET_GASTOSXMESCOMPLMANI(Date fecMin, Date fecMax);
    public List<BeanEstCliente> call_Procedure_GET_INGXMESFACT(Date fecMin, Date fecMax);
    public List<BeanEstCliente> call_Procedure_GET_DIFERENCIALNOTA(Date fecMin, Date fecMax, String tipo);
}
