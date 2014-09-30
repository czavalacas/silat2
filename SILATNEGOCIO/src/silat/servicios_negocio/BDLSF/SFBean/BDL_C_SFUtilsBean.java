package silat.servicios_negocio.BDLSF.SFBean;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import java.util.Set;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import javax.sql.DataSource;

import net.sf.dozer.util.mapping.DozerBeanMapper;
import net.sf.dozer.util.mapping.MapperIF;

import silat.servicios_negocio.BDLSF.IL.BDL_C_SFGuiaLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_C_SFUtilsLocal;
import silat.servicios_negocio.BDLSF.IL.BDL_T_SFGuiaLocal;
import silat.servicios_negocio.BDLSF.IR.BDL_C_SFUtilsRemote;
import silat.servicios_negocio.Beans.BeanADUbigeo;
import silat.servicios_negocio.Beans.BeanCodigo;
import silat.servicios_negocio.Beans.BeanCombo;
import silat.servicios_negocio.Beans.BeanConstraint;
import silat.servicios_negocio.Beans.BeanReportePrevio;
import silat.servicios_negocio.entidades.admin.ADConstraint;
import silat.servicios_negocio.entidades.admin.ADConstraintPK;
import silat.servicios_negocio.entidades.admin.ADFlota;
import silat.servicios_negocio.entidades.admin.ADTipoGasto;
import silat.servicios_negocio.entidades.admin.ADUbigeo;
import silat.servicios_negocio.entidades.admin.ADUtil;
import silat.servicios_negocio.entidades.trans.TRFactura;
import silat.servicios_negocio.entidades.trans.TRGuia;
import silat.servicios_negocio.entidades.trans.TRItem;
import silat.servicios_negocio.entidades.trans.Trmcodi;
import silat.servicios_negocio.entidades.trans.TrmcodiPK;
import silat.servicios_negocio.util_formato.Caracter.FormatoLetra;
import silat.servicios_negocio.util_formato.UtilsGeneral;

@Stateless(name = "BDL_C_SFUtils", mappedName = "mapBDL_C_SFUtils")
public class BDL_C_SFUtilsBean implements BDL_C_SFUtilsRemote, 
                                          BDL_C_SFUtilsLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName="SILATNEGOCIO")
    private EntityManager em;
    private final static String NO_ERROR = "000";
    
  //  @Resource(mappedName = "java:/app/jdbc/jdbc/ConLubalDS")
    @Resource(mappedName = "jdbc/ConLubalDS")
    private DataSource lubalDS;
    Connection conn = null;
    @EJB
    private BDL_C_SFGuiaLocal bdL_C_SFGuiaLocal;

    public BDL_C_SFUtilsBean() {
    }
    
    /** <code>select o from ADTipoGasto o</code> */
    public List<ADUtil> getAdutilFindAll() {
        return em.createNamedQuery("Admutil.findAll").getResultList();
    }
    
    public List<ADUtil> getUtilPorTipoObjeto(BigDecimal tipobj){
            String ejbQL =  "select o " +
                            "from ADUtil o " +
                            "where o.tipobj = :tipobj ";
            return em.createQuery(ejbQL)
                    .setParameter("tipobj",tipobj)
                    .getResultList();
    }

    public BigDecimal call_Function_validar_usuario(String usuario, 
                                                    String clave,
                                                    String tipoKey){
        int iUsuario = 0;
        BigDecimal nidUsuario = null;
        try {
            conn = lubalDS.getConnection();
            String query = "{call ? := PKG_Seguridad.validar_usuario(?,?,?) }";
            CallableStatement stmt = conn.prepareCall(query);
            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.setString(2, usuario);
            stmt.setString(3, clave);
            stmt.setString(4, tipoKey);
            
            stmt.execute();
            iUsuario = stmt.getInt(1);
            nidUsuario = new BigDecimal(iUsuario);
            conn.close();
            return nidUsuario;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.close();
            } catch (SQLException f) {
                f.printStackTrace();
            }
            return null;
        }
        return nidUsuario;
    }
    
    /**
     * Generador de correlativo de Strings segun
     * la entidad, atributo (id) y longitud del ID
     * @param entidad Nombre de la entidad (tabla mapeada)
     * @param atributo Nombre del atributo ID en la entidad
     * @param tamano longitud del Id
     * @return String correlativo para insertar en la tabla
     */
    public HashMap generarCorrelativo(String entidad,
                                      String tipDoc,
                                      int tamano,
                                      String cidUN){
        String error = NO_ERROR;
        HashMap output = new HashMap();
        int corr = 0;
        try{
            String sql = "SELECT COD.CODIGO " +
                         "FROM TRMCODI COD " +
                         "WHERE COD.CIDUNIN = ? " +
                         "AND COD.TIPDOC = ? ";
            Query query = em.createNativeQuery(sql).setParameter(1,cidUN).
                                                    setParameter(2,tipDoc);
            String correlativo = (String) query.getSingleResult();
            /*String ejbQL = "SELECT MAX(e."+atributo+") "+
                           "FROM "+ entidad +" e " +
                           "WHERE e.cidUnidadNegocio = '"+cidUN+"' ";
            String correlativo = (String) em.createQuery(ejbQL)
                                            .getSingleResult();
            if(correlativo == null){
                corr = 1;
                correlativo = FormatoLetra.llenaCeros("0", tamano - 1) + corr;
            }else{
                corr = Integer.parseInt(correlativo);//000001 -> 1
                corr += 1;
                correlativo = FormatoLetra.llenaCeros("0",(tamano - String.valueOf(corr).length())) + corr;
            }*/
            output.put("correlativo", correlativo);
        }catch(Exception e){
            e.printStackTrace();
            error = "LUB-0008";
        }
        output.put("error", error);
        return output;
    }
    
    public int traerSiguienteValorSequence(String sequence){//SQ_TRMORDS_01
        try{
            String sql = "select last_number " + 
                         "from user_sequences " + 
                         "where sequence_name = '"+sequence.toUpperCase()+"' ";
            Query query = em.createNativeQuery(sql);
            Object corr = query.getSingleResult();
            int correlativo = Integer.parseInt(corr.toString());
            return correlativo;
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    
    public int traerSiguienteValorCodigo(String app_seq_name){//CODI TRMORDEN
        try{
            String sql = "select o.app_seq_value + 1 " + 
                         "from codigo o " + 
                         "where o.app_seq_name = '"+app_seq_name+"' ";
            Query query = em.createNativeQuery(sql);
            Object corr = query.getSingleResult();
            int correlativo = Integer.parseInt(corr.toString());
            return correlativo;
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    
    public int traerSiguienteOrdenServicio(String sequence){//SQ_TRMORDS_01
        try{
            String sql = "select last_number " + 
                         "from user_sequences " + 
                         "where sequence_name = '"+sequence.toUpperCase()+"' ";
            Query query = em.createNativeQuery(sql);
            Object corr = query.getSingleResult();
            int correlativo = Integer.parseInt(corr.toString());
            return correlativo;
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    
    public String getMaxCodigoDocByCidUnin(String cidUnin,String atributo, String entidad){
        String ejbQL = "SELECT MAX(e."+atributo+") "+
                       "FROM "+ entidad +" e " +
                       "WHERE e.cidUnidadNegocio = :cidunin ";
        String correlativo = (String) em.createQuery(ejbQL).setParameter("cidunin",cidUnin)
                                                           .getSingleResult();
        return correlativo;
    }
    
    public int isCodigoExistente(String cidUnin,
                                 String atributo,
                                 String entidad,
                                 String codigo){
        try {
            String ejbQL = "SELECT COUNT(e." + atributo + ") " + 
                           "FROM " + entidad + " e " + 
                           "WHERE e.cidUnidadNegocio = :cidunin " +
                           "AND e." + atributo + " = :codigo ";
            List lst = em.createQuery(ejbQL).setParameter("cidunin", cidUnin).setParameter("codigo", codigo).getResultList();
            if (lst.isEmpty()) {
                return 0;
            } else {
                return Integer.parseInt(lst.get(0).toString());
            }
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            return 0;
        }
    }
    
    public List<BeanConstraint> getListConstraints(String cCampo, 
                                                   String cNombreTabla){
        try{
            String ejbQl = "SELECT c " +
                           "FROM ADConstraint c "+
                           "WHERE c.cCampo = '"+cCampo+"' "+
                           "AND c.cNombreTabla = '"+cNombreTabla+"' ";
            MapperIF mapper = new DozerBeanMapper();
            List<ADConstraint> lstConst = em.createQuery(ejbQl).getResultList();
            List<BeanConstraint> lstBeanConst = new ArrayList<BeanConstraint>();
            for(ADConstraint constr : lstConst){
                lstBeanConst.add((BeanConstraint) mapper.map(constr, BeanConstraint.class));
            }
            return lstBeanConst;
        }catch(Exception e){
            List<BeanConstraint> lstBeanConst = new ArrayList<BeanConstraint>();
            return lstBeanConst;
        }
    }
    
    /**
     * Sirve para entidades que tiene Integer su nid y String su descripcion.
     * @param nombreEntidad
     * @param nombreCampo
     * @param idCampo
     * @return
     */
    public List<BeanCombo> getListaParaCombo(String nombreEntidad, String nombreCampo, String idCampo){
        try{
            String query = "SELECT NEW silat.servicios_negocio.Beans.BeanCombo(o."+idCampo +"," +
                           " o."+nombreCampo+") "+
                           "FROM "+nombreEntidad+" o ";
            List<BeanCombo> results =  em.createQuery(query).getResultList();
            return results;
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<BeanCombo>();
        }
    }
    
    public BeanConstraint getCatalogoConstraints(String cCampo, 
                                                 String cNombreTabla, 
                                                 String cValorCampo){
        try{
            ADConstraintPK id = new ADConstraintPK(cCampo,cNombreTabla,cValorCampo);
            ADConstraint constraint = findConstraintById(id);
            if(constraint != null){
                MapperIF mapper = new DozerBeanMapper();
                BeanConstraint beanConstraint = (BeanConstraint)mapper.map(constraint, BeanConstraint.class);
                return beanConstraint;
            }else{
                System.out.println("Constraint no encontrado");
                return null;
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public ADConstraint findConstraintById(ADConstraintPK id) {
        try {
            ADConstraint instance = em.find(ADConstraint.class, id);
            return instance;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    /**
     * Metodo que trae los departamentos, provincias y distritos de Peru
     * @param tipUbigeo (Departamente = 1, Provincia = 2, Distrito = 3)
     * @param dep El cidUbigeo del departamente ejm: 150101
     * @param prov El cidUbigeo de la provincia ejm: 150103
     * @return lista de Beans BeanADUbigeo
     */
    public List<BeanADUbigeo> getUbigeos(int tipUbigeo, 
                                         String dep, 
                                         String prov) {
        String ejbQuery =   "SELECT u " +
                            "FROM ADUbigeo u " +
                            "WHERE 1 = 1 ";
        dep = (dep != null) ? dep.substring(0,2) : null;
        prov = (prov != null) ? prov.substring(0,4) : null;
        switch (tipUbigeo) {
        case 1:
            ejbQuery = ejbQuery.concat(" AND SUBSTRING(u.cidUbigeo,3,6) = '0000' ");
            break;
        case 2:
            ejbQuery = ejbQuery.concat(" AND SUBSTRING(u.cidUbigeo,1,2)= '" + dep + "' and SUBSTRING(u.cidUbigeo,3,2) <> '00' and SUBSTRING(u.cidUbigeo,5,6) = '00' ");
            break;
        case 3:
            ejbQuery = ejbQuery.concat(" AND SUBSTRING(u.cidUbigeo,1,4)= '" + prov + "' and SUBSTRING(u.cidUbigeo,5,6) <> '00' ");
            break;
        }
        ejbQuery = ejbQuery.concat(" ORDER BY u.cDescUbigeo ASC ");
        MapperIF mapper = new DozerBeanMapper();
        List<ADUbigeo> lstUbigeos = em.createQuery(ejbQuery).getResultList();
        List<BeanADUbigeo> lstBeanUbigeo = new ArrayList<BeanADUbigeo>();
        for(ADUbigeo ubigeo : lstUbigeos){
            lstBeanUbigeo.add((BeanADUbigeo) mapper.map(ubigeo, BeanADUbigeo.class));
        }
        return lstBeanUbigeo;
    }
    
    public BeanADUbigeo getUbigeoByCid(String cidUbigeo){
        String ejbQuery = "SELECT u " + 
                          "FROM ADUbigeo u " + 
                          "WHERE u.cidUbigeo = :codigoUbigeo ";
        try{
            MapperIF mapper = new DozerBeanMapper();
            ADUbigeo ubigeo = (ADUbigeo) em.createQuery(ejbQuery)
                                           .setParameter("codigoUbigeo",cidUbigeo)
                                           .getSingleResult();
            BeanADUbigeo beanUbigeo = new BeanADUbigeo();
            if(ubigeo != null){
                beanUbigeo = (BeanADUbigeo) mapper.map(ubigeo, BeanADUbigeo.class);
            }
            return beanUbigeo;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }   
 
    public List<BeanCodigo> getCodigos(){
        String query = "SELECT NEW silat.servicios_negocio.Beans.BeanCodigo(o.cidunin, o.codigo,o.tipdoc) "+
                       "FROM Trmcodi o ";
        List<BeanCodigo> codigos =  em.createQuery(query).getResultList();
        return codigos;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public int grabarItemReporte(List<BeanReportePrevio> lstReportItem){
        try{
            if(lstReportItem != null){
                if(lstReportItem.size() > 0){
                    Iterator it = lstReportItem.iterator();
                    while(it.hasNext()){
                        BeanReportePrevio beanRP = (BeanReportePrevio) it.next();
                        String cidRepo = beanRP.getCidRepo();
                        Double cantidad = beanRP.getNCantidad();
                        String undMed = beanRP.getCUndMedida();
                        String descItem = beanRP.getCDescItem();
                        BigDecimal prec = beanRP.getPrecio();
                        String ruta = beanRP.getRuta();
                        int grupo = beanRP.getGrupo();
                        String cidGuiaFull = beanRP.getCidGuiaFull();
                        em.createNativeQuery(" INSERT INTO TRMREPO (CIDREPO,CANTIDAD,UNDMED,DESCITM,PRECIO,RUTA,CIDGUIAFULL,FLG_BORRAR,GRUPO) VALUES " +
                            " ('"+cidRepo+"',"+cantidad+",'"+undMed+"','"+descItem+"',"+prec+", '"+ruta+"','"+cidGuiaFull+"','1',"+grupo+") ").executeUpdate();
                    }
                }
            }
            return 0;
        }catch(Exception e){
            return 1;
        }
    }
    
    public void removerItmsRepo(String cidRepo){
        try{
            String sql = "DELETE FROM TRMREPO  " +//MODIFICADOR POR LA NUEVA BD MYSQL
                         "WHERE CIDREPO = '"+cidRepo+"'";//UtilsGeneral.depurar("borrar: "+sql);
            em.createNativeQuery(sql).executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public List<TRGuia> actualizarGuias(String guias,
                                        TRFactura factura,
                                        String cidSerie){
        try{
            List<TRGuia> eGuias = new ArrayList<TRGuia>();
            String[] guiaArry = guias.split(",");
            Set<String> s = new HashSet<String>(Arrays.asList(guiaArry));
            Object[] arr =  s.toArray();
            for(int i = 0; i <arr.length; i++){
                String cidGuia = (String) arr[i];
                TRGuia eGuia = bdL_C_SFGuiaLocal.findGuiaByUNCid(cidSerie, cidGuia);
                eGuia.setTrFactura(factura);
                eGuias.add(eGuia);
            }
            return eGuias;
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<TRGuia>();
        }
    }
    
    public String call_Procedure_GET_COD_FACTURA_BY_UN(String cidUn){
        String codFactura = "";
        try {
            conn = lubalDS.getConnection();
            String query = "CALL GET_COD_FACTURA_BY_UN(?,?); ";
            CallableStatement stmt = conn.prepareCall(query);
            stmt.registerOutParameter(2, java.sql.Types.VARCHAR);
            stmt.setString(1, cidUn);
            stmt.execute();
            conn.close();
            codFactura = stmt.getString(2);
            System.out.println("CODIGO DE FACTURA :: "+codFactura);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.close();
            } catch (SQLException f) {
                f.printStackTrace();
            }
            return null;
        }
        return codFactura;
    }
    
    public int findCountByProperty(String propertyName, Object value, String entidad, boolean changeCase,boolean isUpdate, String oldValue) {
         try {
             String queryString = "select count(model) " +
                                  "from "+entidad+" model " +
                                  "where 1 = 1 ";
             if(changeCase){
                 queryString = queryString.concat(" and upper(model."+ propertyName +") = :propertyValue ");
             }else{
                 queryString = queryString.concat(" and model."+ propertyName +" = :propertyValue ");
             }
             if(isUpdate){
                 queryString = queryString.concat(" and upper(model."+ propertyName +") <> :propertyValue ");
             }
             List lst = em.createQuery(queryString).setParameter("propertyValue", value).getResultList();
             if(lst.isEmpty()){
                 return 0;
             }else{
                 return Integer.parseInt(lst.get(0).toString());
             }
         } catch (Exception re) {
             re.printStackTrace();
             return 0;
         }
     }
}