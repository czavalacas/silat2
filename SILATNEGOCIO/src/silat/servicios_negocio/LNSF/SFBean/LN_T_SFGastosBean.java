package silat.servicios_negocio.LNSF.SFBean;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.awt.image.BufferedImage;

import java.io.File;


import java.io.*;

import java.math.BigDecimal;

import java.util.Date;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.imageio.ImageIO;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import silat.servicios_negocio.BDLSF.IL.BDL_T_SFGastosLocal;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanGasto;
import silat.servicios_negocio.LNSF.IL.LN_C_SFCatalogoErroresLocal;
import silat.servicios_negocio.LNSF.IL.LN_T_SFGastosLocal;
import silat.servicios_negocio.LNSF.IR.LN_T_SFGastosRemoto;
import silat.servicios_negocio.entidades.admin.ADEmpresa;
import silat.servicios_negocio.entidades.admin.ADFlota;
import silat.servicios_negocio.entidades.admin.ADGasto;
import silat.servicios_negocio.entidades.admin.ADModalidadPago;
import silat.servicios_negocio.entidades.admin.ADTipoGasto;
import silat.servicios_negocio.entidades.admin.ADUtil;
import silat.servicios_negocio.entidades.trans.TRGuia;
import silat.servicios_negocio.util_formato.UtilsGeneral;

@Stateless(name = "LN_T_SFGastos", mappedName = "LUBAL_SIAT_APP-SILATNEGOCIO-LN_T_SFGastos")
public class LN_T_SFGastosBean implements LN_T_SFGastosRemoto, 
                                          LN_T_SFGastosLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "SILATNEGOCIO")
    private EntityManager em;
    @EJB
    private BDL_T_SFGastosLocal bdl_T_SFGastosLocal;
    @EJB
    private LN_C_SFCatalogoErroresLocal ln_C_SFCatalogoErroresLocal;

    public LN_T_SFGastosBean() {
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public BeanGasto insertarGasto(Integer nidTipoGasto, Integer nidModoPago, BigDecimal monto, Date fechaGasto,
                                   Integer nidFlota, Long nidProveedor, String cidFactura, String destino,
                                   BigDecimal nidServBasico, BigDecimal tipoCombustible, Integer cantPersonas,
                                   BigDecimal tipoMantenimiento, String numCheque, String rutaImagen,
                                   byte[] imagenFisica, String detalle, String banco, String estado) {
        ADGasto eGasto = new ADGasto();
        BeanGasto bGasto = new BeanGasto();
        BeanError beanError = new BeanError();
        String error = "000";
        try {
            if (nidFlota != null) {
                ADFlota flota = new ADFlota();
                flota.setNidFlota(nidFlota);
                eGasto.setAdFlota(flota);
            }
            //////
            ADTipoGasto tipoGasto = new ADTipoGasto();
            tipoGasto.setNidTiga(nidTipoGasto);
            //////
            ADModalidadPago mopa = new ADModalidadPago();
            mopa.setNidModalidadPago(nidModoPago);
            //////
            if (nidServBasico != null) {
                ADUtil util = new ADUtil();
                util.setNidObj(nidServBasico);
                eGasto.setUtilServicioBasico(util);
            }
            if (tipoCombustible != null) {
                ADUtil util = new ADUtil();
                util.setNidObj(tipoCombustible);
                eGasto.setUtilServicioBasico(util);
            }
            if (tipoMantenimiento != null) {
                ADUtil util = new ADUtil();
                util.setNidObj(tipoMantenimiento);
                eGasto.setUtilServicioBasico(util);
            }
            //////
            if (rutaImagen != null) {
                eGasto.setBlobImagenRecibo(UtilsGeneral.imagen(rutaImagen));
                /* if (!rutaImagen.equals("")) {
                    byte[] byt = extractBytes(rutaImagen);
                    if (byt != null) {
                        eGasto.setBlobImagenRecibo(byt);
                    }
                } */
            }
            eGasto.setDMontoGeneral(monto);
            eGasto.setFechaGasto(fechaGasto);
            eGasto.setEstadoRegistro(estado);
            eGasto.setCidFactura(cidFactura);
            eGasto.setDestino(destino);
            eGasto.setCantper(cantPersonas);
            eGasto.setNroCheque(numCheque);
            eGasto.setC_detalle(detalle);
            eGasto.setCimgrecibo(rutaImagen);
            eGasto.setTipoGasto(tipoGasto);
            eGasto.setModalidadPago(mopa);
            eGasto.setNidProtra(nidProveedor);
            eGasto.setC_banco(banco);
            eGasto = bdl_T_SFGastosLocal.persistADGasto(eGasto);
        } catch (Exception e) {
            error = "LUB-0018";
            e.printStackTrace();
            return null;
        }
        beanError = ln_C_SFCatalogoErroresLocal.getCatalogoErrores(error);
        bGasto.setBeanError(beanError);
        return bGasto;
    }

    public static byte[] extractBytes(String ImageName) throws IOException {
        File imgPath = new File(ImageName);
        String extension = ImageName.substring(ImageName.lastIndexOf(".") + 1, ImageName.length());
        BufferedImage bufferedImage = ImageIO.read(imgPath);
        ByteOutputStream bos = null;
        try {
            bos = new ByteOutputStream();
            ImageIO.write(bufferedImage, extension, bos);
        } finally {
            try {
                bos.close();
            } catch (Exception e) {
            }
        }
        return bos == null ? null : bos.getBytes();
    }
    
    public BeanGasto anularGasto(Long nidGasto, String estado){
        ADGasto eGasto = new ADGasto();
        BeanGasto bGasto = new BeanGasto();
        BeanError beanError = new BeanError();
        String error = "000";
        try {       
            eGasto=bdl_T_SFGastosLocal.findADGastoById(nidGasto); 
            eGasto.setEstadoRegistro(estado);   
            bdl_T_SFGastosLocal.mergeADGasto(eGasto);    
        }catch(Exception e){
            error = "LUB-0018";
            e.printStackTrace();
            return null;
        }
        beanError = ln_C_SFCatalogoErroresLocal.getCatalogoErrores(error);
        bGasto.setBeanError(beanError);
        return bGasto;
    }
  /*  public ADGasto anularGasto(Long nidGasto, String estado){        
        ADGasto eGasto       
        eGasto.setEstadoRegistro(estado);
        return bdl_T_SFGastosLocal.mergeADGasto(eGasto);
    }*/  

}
