package silat.servicios_negocio.BDLSF.IR;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.trans.TRItemXOrds;

@Remote
public interface BDL_C_SFItemXOrdsRemote {
    List<TRItemXOrds> getTRItemXOrdsFindAll();
    TRItemXOrds findTRItemtById(BigDecimal id);
    List<TRItemXOrds> getTrItemOrdenServicio_BD(int nidOrdenServ); 
}
