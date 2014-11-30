package silat.servicios_negocio.BDLSF.SFBean;

import java.math.BigDecimal;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.SQLException;

import java.sql.Types;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import javax.sql.DataSource;

import silat.servicios_negocio.BDLSF.IL.BDL_T_SFEmpresasLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_T_SFPartyLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_T_SFEmpresasRemote;
import silat.servicios_negocio.LNSF.IL.LN_C_SFDireccionLocal;
import silat.servicios_negocio.LNSF.IL.LN_C_SFRelacionEmpresaLocal;
import silat.servicios_negocio.LNSF.IL.LN_T_SFChoferLocal;
import silat.servicios_negocio.LNSF.IL.LN_T_SFFlotaLocal;
import silat.servicios_negocio.LNSF.SFBean.LN_T_SFChoferBean;
import silat.servicios_negocio.entidades.admin.ADEmpresa;
import silat.servicios_negocio.entidades.admin.ADParty;

@Stateless(name = "BDL_T_SFEmpresas", mappedName = "LUBAL_SIAT_APP-SILATNEGOCIO-BDL_T_SFEmpresas")
public class BDL_T_SFEmpresasBean implements BDL_T_SFEmpresasRemote, 
                                             BDL_T_SFEmpresasLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName="SILATNEGOCIO")
    private EntityManager em;
    //  @Resource(mappedName = "java:/app/jdbc/jdbc/ConLubalDS")
    @Resource(mappedName = "jdbc/ConLubalDS")
    private DataSource lubalDS;
    Connection conn = null;
    @EJB
    private LN_C_SFDireccionLocal ln_C_SFDireccionLocal;
    @EJB
    private LN_C_SFRelacionEmpresaLocal ln_C_SFRelacionEmpresaLocal;
    @EJB
    private BDL_T_SFPartyLocal bdL_T_SFPartyLocal;
    @EJB
    private LN_T_SFChoferLocal ln_T_SFChoferLocal;
    @EJB
    private LN_T_SFFlotaLocal ln_T_SFFlotaLocal;
    public BDL_T_SFEmpresasBean() {
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY) 
    public ADEmpresa persistADEmpresa(ADEmpresa adEmpresa) {
        em.persist(adEmpresa);
        em.flush();
        em.refresh(adEmpresa);
        return adEmpresa;
    }

    public ADEmpresa mergeADEmpresa(ADEmpresa adEmpresa) {
        return em.merge(adEmpresa);
    }

    public void removeADEmpresa(ADEmpresa adEmpresa) {
        adEmpresa = em.find(ADEmpresa.class, adEmpresa.getNidParty());
        em.remove(adEmpresa);
    }
    
    @TransactionAttribute(TransactionAttributeType.MANDATORY) 
    public String registrarEmpresa_BDL(ADEmpresa adEmpresa,
                                       String[] cidUbigeo,
                                       String[] cDireccion,
                                       Integer[] nidTire,
                                       //String[] nidChof,
                                       String[] cNombChof,
                                       String[] cLicencia,
                                       String [] cMarcaFlota,
                                       String [] cPlacaFlota,
                                       String [] cConfFlota,
                                       String [] cDescripcionFlota,
                                       String [] cCerInsCripFlota){
        String error = "000";
        try{
            adEmpresa = this.persistADEmpresa(adEmpresa);
            for(int i = 0; i < cidUbigeo.length; i++){
                ln_C_SFDireccionLocal.grabarDireccion(adEmpresa.getAdParty(), cidUbigeo[i], cDireccion[i]);
            }
            for(int i = 0; i < nidTire.length; i++){   
                ln_C_SFRelacionEmpresaLocal.grabarRelacion_Aux(adEmpresa, nidTire[i]);
            }
            for(int i = 0; i <cNombChof.length; i++){
                ln_T_SFChoferLocal.guardarChofer(adEmpresa.getNidParty(), cNombChof[i], cLicencia[i]);
            }
            for(int i = 0; i <cMarcaFlota.length; i++){
                ln_T_SFFlotaLocal.registrarFlota(adEmpresa.getNidParty(), cMarcaFlota[i], cPlacaFlota[i], cConfFlota[i], cDescripcionFlota[i], cCerInsCripFlota[i]);
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("catch registrarEmpresa_BDL");
            error = "LUB-0009";
        }
        return error;
    }
    
    @TransactionAttribute(TransactionAttributeType.MANDATORY) 
    public int borrarEmpresa(BigDecimal nidEmpresa){
        int salida = 1;//No borro
        try{
            conn = lubalDS.getConnection();
            String query = "CALL SP_ELIMINAR_EMPRESA(?,?);";
            CallableStatement stmt = conn.prepareCall(query);
            stmt.setBigDecimal(1,nidEmpresa);
            stmt.registerOutParameter(2, java.sql.Types.INTEGER);            
            stmt.execute();
            conn.close();
            salida = stmt.getInt(2);
        }catch(Exception e){
            e.printStackTrace();
            try {
                conn.close();
            } catch (SQLException f) {
                f.printStackTrace();
            }
            return 1;
        }
        return salida;
    }
}
