package silat.servicios_negocio.LNSF.SFBean;

import java.math.BigDecimal;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFFlotaLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_T_SFFlotaLocal;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanFlota;
import silat.servicios_negocio.Beans.BeanGasto;
import silat.servicios_negocio.LNSF.IL.LN_C_SFCatalogoErroresLocal;
import silat.servicios_negocio.LNSF.IL.LN_T_SFFlotaLocal;
import silat.servicios_negocio.LNSF.IR.LN_T_SFFlotaRemote;
import silat.servicios_negocio.entidades.admin.ADEmpresa;
import silat.servicios_negocio.entidades.admin.ADFlota;
import silat.servicios_negocio.entidades.admin.ADGasto;
import silat.servicios_negocio.entidades.admin.ADParty;

@Stateless(name = "LN_T_SFFlota", mappedName = "LUBAL_SIAT_APP-SILATNEGOCIO-LN_T_SFFlota")
public class LN_T_SFFlotaBean implements LN_T_SFFlotaRemote,
                                         LN_T_SFFlotaLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;

    @EJB
    BDL_T_SFFlotaLocal bdl_T_SFFlotaLocal;
    @EJB
    BDL_C_SFFlotaLocal bdl_C_SFFlotaLocal;
    @EJB
    LN_C_SFCatalogoErroresLocal ln_C_SFCatalogoErroresLocal;

    public LN_T_SFFlotaBean() {
    }

    public ADFlota registrarFlota(BigDecimal nidParty, String marca, String placa, String configuracion,
                                  String descripcion) {
        ADParty party = new ADParty();
        party.setNidParty(nidParty);
        ADEmpresa empresa = new ADEmpresa();
        empresa.setAdParty(party);
        ADFlota entida = new ADFlota();
        entida.setCMarvehi(marca.toUpperCase());
        entida.setCPlaca(placa.toUpperCase());
        entida.setCConfveh(configuracion.toUpperCase());
        entida.setCSoat("");
        if (descripcion == null) {
            entida.setCDescFlota(descripcion);
        } else {
            entida.setCDescFlota(descripcion.toUpperCase());
        }
        entida.setNEstadoFlota(1);
        entida.setEmpresa(empresa);
        return bdl_T_SFFlotaLocal.persistADFlota(entida);
    }

    public BeanFlota actualizarFlota(BigDecimal nidParty,
                                     Integer nidFlota, 
                                     String marca, 
                                     String placa,
                                     String configuracion,
                                     String descripcion) {
        String error = "000";
        BeanError beanError = new BeanError();
        BeanFlota bFlota = new BeanFlota();
        try {
            ADParty party = new ADParty();
            party.setNidParty(nidParty);
            ADEmpresa empresa = new ADEmpresa();
            empresa.setAdParty(party);
            ADFlota entida = new ADFlota();
            entida.setNidFlota(nidFlota);
            entida.setCMarvehi(marca.toUpperCase());
            entida.setCPlaca(placa.toUpperCase());
            entida.setCConfveh(configuracion.toUpperCase());
            entida.setCSoat("");
            if (descripcion == null) {
                entida.setCDescFlota(descripcion);
            } else {
                entida.setCDescFlota(descripcion.toUpperCase());
            }
            entida.setNEstadoFlota(1);
            entida.setEmpresa(empresa);
            bdl_T_SFFlotaLocal.mergeADFlota(entida);
        } catch (Exception e) {
            error = "LUB-0035";
            e.printStackTrace();
        } finally {
            beanError = ln_C_SFCatalogoErroresLocal.getCatalogoErrores(error);
            bFlota.setBeanError(beanError);
            return bFlota;
        }
    }

    public void removerFlota(Integer nidFlota) {
        ADFlota entida = new ADFlota();
        entida.setNidFlota(nidFlota);
        bdl_T_SFFlotaLocal.removeADFlota(entida);
    }

    public BeanFlota anularFlota(Integer nidFlota, String estado) {
        ADFlota eFlota = new ADFlota();
        BeanFlota bFlota = new BeanFlota();
        BeanError beanError = new BeanError();
        String error = "000";
        try {
            eFlota = bdl_C_SFFlotaLocal.findADFlotaById(nidFlota);
            eFlota.setNEstadoFlota(Integer.parseInt(estado));
            bdl_T_SFFlotaLocal.mergeADFlota(eFlota);
        } catch (Exception e) {
            error = "LUB-0034";
            e.printStackTrace();
            return null;
        }
        beanError = ln_C_SFCatalogoErroresLocal.getCatalogoErrores(error);
        bFlota.setBeanError(beanError);
        return bFlota;
    }
}
