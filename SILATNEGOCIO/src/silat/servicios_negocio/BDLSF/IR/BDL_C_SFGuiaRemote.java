package silat.servicios_negocio.BDLSF.IR;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.Beans.BeanTRGuia;
import silat.servicios_negocio.entidades.trans.TRGuia;

@Remote
public interface BDL_C_SFGuiaRemote {
    TRGuia findTRGuiaById(String id);
    List<TRGuia> findGuiasByManifiesto(int nidManifiesto);
    List<TRGuia> findGuiasByAttributes_BD(String cidGuia,Date fecEmisMin,Date fecEmisMax,Date fecDespMin, 
                                               Date fecDespMax,String empCliente,String empProvCarga,String estGuia,
                                               String hasManif,Integer nidManif,String prov,String cObservaciones,
                                               String nEstadoGuia,int nidOS,String detOS,String hasFactura,String codFactura,
                                               int nEstadoFactura,BigDecimal nidParty,String descCidGuiaRemi_ITEM,String descCidGuia_ITEM);
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
    List<TRGuia> guiasPorParty(int nidCliente);
    List<TRGuia> guiasPorPartyOK(int nidCliente);
    List<TRGuia> findGuiasFiltered(BeanTRGuia filtro);
    int existeGuia(String cidGuia);
}