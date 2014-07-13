package silat.servicios_negocio.LNSF.SFBean;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFCodigoLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFUtilsLocal;
import silat.servicios_negocio.LNSF.IL.LN_C_SFCodigoLocal;
import silat.servicios_negocio.LNSF.IR.LN_C_SFCodigoRemote;
import silat.servicios_negocio.entidades.trans.Trmcodi;

@Stateless(name = "LN_C_SFCodigo", mappedName = "mapLN_C_SFCodigo")
public class LN_C_SFCodigoBean implements LN_C_SFCodigoRemote, 
                                          LN_C_SFCodigoLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_C_SFUtilsLocal bdL_C_SFUtilsLocal;
    
    public LN_C_SFCodigoBean() {
    }
    
    public String validarNuevoCodigo(String cidUnin,
                                     String tipDoc,
                                     String codigo){
        try{
           // Trmcodi eCodigo = bdL_C_SFCodigoLocal.findCodigoById(cidUnin, tipDoc);
            String entidad = "";
            String atributo = "";
            if(tipDoc.equals("G")){
                entidad = "TRGuia";
                atributo = "cidGuia";
            }else if(tipDoc.equals("F")){
                entidad = "TRFactura";
                atributo = "cCodFactura";
            }
            String maxNroDocumento = bdL_C_SFUtilsLocal.getMaxCodigoDocByCidUnin(cidUnin, atributo, entidad);
            int codigoActual = Integer.parseInt(maxNroDocumento);
            int codigoNuevo = Integer.parseInt(codigo);
            if(codigoNuevo <= codigoActual){
                return "LUB-0037";
            }
            return "000";
        }catch(Exception e){
            e.printStackTrace();
            return "LUB-0031";
        }
    }
    
    public int isCodigoExistente(String cidUnin,
                                 String tipDoc,
                                 String codigo){
        String entidad = "";
        String atributo = "";
        if(tipDoc.equals("G")){
            entidad = "TRGuia";
            atributo = "cidGuia";
        }else if(tipDoc.equals("F")){
            entidad = "TRFactura";
            atributo = "cCodFactura";
        }
        return bdL_C_SFUtilsLocal.isCodigoExistente(cidUnin, atributo, entidad, codigo);
    }
}