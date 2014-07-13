package silat.servicios_negocio.BDLSF.IL;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanTRGuia;
import silat.servicios_negocio.entidades.trans.TRGuia;

@Local
public interface BDL_C_SFGuiaLocal {
    TRGuia findTRGuiaById(String id);
    List<TRGuia> findGuiasByManifiesto(int nidManifiesto);
    List<TRGuia> findGuiasByAttributes_BD(BeanTRGuia bGuia);
    TRGuia findGuiaByUNCid(String cidUn, 
                           String cidGuia);
    int contGuiasConFactPagadas(int nidOrdenServicio, Long nidFactura);
    int cantGuiasVigentesByManifiesto(int nidManifiesto);
    int getCountGuiasVigentesByFactura(Long nidFactura);
    int getCountGuiasVigentesByOS_No_Facturadas(int nidOS);
    int getCountGuiasVigentesByFacturaByOrdenServ(Long nidFactura, int nidOS);
    int getCountGuiasVigentesByOrdenServ(int nidOS);
    List<TRGuia> findGuiasByNidCliente(int nidCliente);
    List<TRGuia> findGuiasByNidClienteParaPreFactura(int nidCliente);
    int cantGuiasByDireccion(int nidDireccion);
    int cantGuiasByChofer(int nidChofer);
    int cantGuiasByFlota(int nidFlota);
    int getCantidadGuiasActivasByManifiesto(int nidManifiesto);
    boolean isGuiaExistente(String cidUn,String cidGuia);
}