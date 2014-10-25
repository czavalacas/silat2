package silat.servicios_negocio.BDLSF.IL;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.trans.TRItemXOrds;

@Local
public interface BDL_C_SFItemXOrdsLocal {
    List<TRItemXOrds> getTRItemXOrdsFindAll();
    TRItemXOrds findTRItemtById(BigDecimal id);
    List<TRItemXOrds> getTrItemOrdenServicio_BD(int nidOrdenServ);
}
