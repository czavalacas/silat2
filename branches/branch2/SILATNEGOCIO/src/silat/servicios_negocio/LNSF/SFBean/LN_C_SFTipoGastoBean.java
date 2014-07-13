package silat.servicios_negocio.LNSF.SFBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFTipoGastoLocal;
import silat.servicios_negocio.Beans.BeanTipoGasto;
import silat.servicios_negocio.LNSF.IL.LN_C_SFTipoGastoLocal;
import silat.servicios_negocio.LNSF.IR.LN_C_SFTipoGastoRemoto;
import silat.servicios_negocio.entidades.admin.ADTipoGasto;

@Stateless(name = "LN_C_SFTipoGasto", mappedName = "LUBAL_SIAT_APP-SILATNEGOCIO-LN_C_SFTipoGasto")
public class LN_C_SFTipoGastoBean implements LN_C_SFTipoGastoRemoto, 
                                             LN_C_SFTipoGastoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    BDL_C_SFTipoGastoLocal bdl_C_SFTipoGastoLocal;
    
    public LN_C_SFTipoGastoBean() {
    }
    
    public List<BeanTipoGasto> getTiposGastosAll_LN(String descTipGasto,String estado){
        BeanTipoGasto bTGasto = new BeanTipoGasto();
        bTGasto.setDescripcionTipoGasto(descTipGasto);
        bTGasto.setEstadoRegistro(estado);
        return bdl_C_SFTipoGastoLocal.getTiposGastosAll_BD(bTGasto);
    }
}