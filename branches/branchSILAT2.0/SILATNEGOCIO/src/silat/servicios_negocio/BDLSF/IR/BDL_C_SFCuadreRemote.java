package silat.servicios_negocio.BDLSF.IR;

import java.math.BigDecimal;

import java.util.Date;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanCuadre;

@Remote
public interface BDL_C_SFCuadreRemote {
    List<BeanCuadre> getReporteCuadre(Date fecMin, Date fecMax);
    List<BeanCuadre> call_Procedure_GET_EGRESOS(Date fecMin, Date fecMax);
    BigDecimal[] call_Procedure_GET_TOTALES(Date fecMin, Date fecMax);
    BigDecimal[] call_Procedure_GET_INGRESOS(Date fecMin, Date fecMax);
}
