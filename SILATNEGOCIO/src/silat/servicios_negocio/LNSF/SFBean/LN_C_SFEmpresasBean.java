package silat.servicios_negocio.LNSF.SFBean;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFEmpresasLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFUtilsLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_T_SFEmpresasLocal;
import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.LNSF.IL.LN_C_SFCatalogoErroresLocal;
import silat.servicios_negocio.LNSF.IL.LN_C_SFDireccionLocal;
import silat.servicios_negocio.LNSF.IL.LN_C_SFEmpresasLocal;
import silat.servicios_negocio.LNSF.IL.LN_C_SFPartyLocal;
import silat.servicios_negocio.LNSF.IL.LN_C_SFRelacionEmpresaLocal;
import silat.servicios_negocio.LNSF.IR.LN_C_SFEmpresasRemote;
import silat.servicios_negocio.entidades.admin.ADChofer;
import silat.servicios_negocio.entidades.admin.ADEmpresa;
import silat.servicios_negocio.entidades.admin.ADParty;
import silat.servicios_negocio.util_formato.UtilsGeneral;

@Stateless(name = "LN_C_SFEmpresas", mappedName = "mapLN_C_SFEmpresas")
public class LN_C_SFEmpresasBean implements LN_C_SFEmpresasRemote, 
                                            LN_C_SFEmpresasLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName="SILATNEGOCIO")
    private EntityManager em;
    
    @EJB
    protected BDL_C_SFEmpresasLocal bdL_C_SFEmpresasLocal;
    @EJB
    protected BDL_T_SFEmpresasLocal bdL_T_SFEmpresaLocal;
    @EJB
    protected LN_C_SFPartyLocal ln_C_SFPartyLocal;
    @EJB
    protected LN_C_SFRelacionEmpresaLocal ln_C_SFRelacionEmpresaLocal;
    @EJB
    protected LN_C_SFDireccionLocal ln_C_SFDireccionLocal;
    @EJB
    protected LN_C_SFCatalogoErroresLocal ln_C_SFCatalogoErroresLocal;
    @EJB
    private BDL_C_SFUtilsLocal bdL_C_SFUtilsLocal;
    
    public LN_C_SFEmpresasBean() {
        
    }
    
    public List<BeanEmpresa> getADEmpresaCliente(String valor){
        return bdL_C_SFEmpresasLocal.getADEmpresabyName(valor);
    }
    
    public List<BeanEmpresa> getEmpresas(){
        List<BeanEmpresa> listbeanemp = bdL_C_SFEmpresasLocal.getADEmpresas();
        return listbeanemp;
    }
      
    public int getCountEmpresaByName(String nombreCampo, String valorCampo,boolean isUpdate,String oldValue){
        if(valorCampo == null){
            valorCampo = "";
        }
        return bdL_C_SFUtilsLocal.findCountByProperty(nombreCampo,valorCampo.toUpperCase(),"ADEmpresa",true,isUpdate,oldValue);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public BeanError crearEmpresa(  String[] cDireccion,
                                    String cPagWeb,
                                    String cRazonSocial,
                                    String cRuc,
                                    String cCerins,
                                    String[] cidUbigeo,
                                    String cDetalle,
                                    String cEmail,
                                    String cTelf,                                    
                                    Integer[] nidTire,
                                    String[] cNombChofer,
                                    String[] cLicenciaChofer,
                                    String [] cMarcaFlota,
                                    String [] cPlacaFlota,
                                    String [] cConfFlota,
                                    String [] cDescripcionFlota){
        String error = "000";
        BeanError beanError = new BeanError();
        try{
            int cant = getCountEmpresaByName("cRazonSocial",cRazonSocial,false,"");
            int cantcRuc = getCountEmpresaByName("cRuc",cRuc,false,"");
            if(cantcRuc > 0){
                error = "LUB-0028";
            }else if(cant > 0){
                error = "LUB-0027";
            }else if(error.equals("000")){
                ADEmpresa empresa = new ADEmpresa();
                ADParty party = new ADParty();
                party.setCDetalle(cDetalle);
                party.setCEmail(cEmail);
                party.setCTelf(cTelf);
                party.setCTipoParty("E");//empresa
                empresa.setCPagWeb(cPagWeb);
                empresa.setCRazonSocial(cRazonSocial.toUpperCase());
                empresa.setCRuc(cRuc);
                if(cCerins!=null){
                    empresa.setCCerins(cCerins.toUpperCase());
                }
                if(cCerins==null){
                    empresa.setCCerins(cCerins);
                }                
                empresa.setAdParty(party);
                error = bdL_T_SFEmpresaLocal.registrarEmpresa_BDL(empresa, cidUbigeo, cDireccion, nidTire,cNombChofer,cLicenciaChofer,cMarcaFlota,cPlacaFlota,cConfFlota,cDescripcionFlota);
            }
        }catch(Exception e){
            e.getMessage().toUpperCase();
            error = "LUB-0009";
        }
        System.out.println("Msj empresa: "+error);//beanError = null;
        beanError = ln_C_SFCatalogoErroresLocal.getCatalogoErrores(error);
        System.out.println("llegue a terminar el recorrido del metodo "+error+" beanError: "+beanError+ " beanError: "+(beanError != null ? beanError.getCDescripcionError() : "NO Bean error"));
        return beanError;
    }
    
    public BeanError modificarEmpresa(BigDecimal nidParty,                                   
                                      String cPagWeb,
                                      String cRazonSocial,
                                      String cRuc,                                    
                                      String cCerins,
                                      String cDetalle,
                                      String cEmail,
                                      String cTelf,
                                      Integer[] nidTire) {
        String error = "000";
        BeanError beanError = new BeanError();
        try{
            ADEmpresa empresa = bdL_C_SFEmpresasLocal.getEmpresaById(nidParty);
            int cant = getCountEmpresaByName("cRazonSocial",cRazonSocial,true,empresa.getCRazonSocial());
            int cantcRuc = getCountEmpresaByName("cRuc",cRuc,true,empresa.getCRuc());
            if(cantcRuc > 0){
                error = "LUB-0028";
            }else if(cant > 0){
                error = "LUB-0027";
            }else if(error.equals("000")){
                ADParty party = new ADParty();            
                empresa.setCPagWeb(cPagWeb);
                empresa.setCRazonSocial(cRazonSocial.toUpperCase());
                empresa.setCRuc(cRuc);
                if(cCerins!=null){
                    empresa.setCCerins(cCerins.toUpperCase());
                }
                if(cCerins==null){
                    empresa.setCCerins(cCerins);
                }            
                party = ln_C_SFPartyLocal.modificarParty(nidParty,"", cDetalle, cEmail, cTelf);
                if(!error.equals("000")){throw new Exception("a");}
               // ln_C_SFDireccionLocal.borrarRelacion(nidParty);
                ln_C_SFRelacionEmpresaLocal.borrarRelacion(nidParty);
                for(int i = 0; i < nidTire.length; i++){
                    ln_C_SFRelacionEmpresaLocal.grabarRelacion(party.getNidParty(), nidTire[i]);
                }
                empresa.setAdParty(party);
                bdL_T_SFEmpresaLocal.mergeADEmpresa(empresa);
            }
        }catch(Exception e){
            System.out.println(error);
        }
        beanError = ln_C_SFCatalogoErroresLocal.getCatalogoErrores(error);
        return beanError;
    }
    
    
    public List<BeanEmpresa> findEmpresasByAttributes(BeanEmpresa beanEmpresa){
        return bdL_C_SFEmpresasLocal.findEmpresasByAttributes(beanEmpresa);                                                   
    }
    
    public BeanEmpresa selectedEmpresa(BigDecimal nidParty){
        return bdL_C_SFEmpresasLocal.getADEmpresasbyNid(nidParty);
    }
  }