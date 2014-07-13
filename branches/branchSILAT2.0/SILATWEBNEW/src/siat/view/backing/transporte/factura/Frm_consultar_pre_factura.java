package siat.view.backing.transporte.factura;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.naming.Context;
import javax.naming.InitialContext;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectItem;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;

import oracle.adf.view.rich.event.DialogEvent;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import siat.view.backing.utiles.Utils;

import silat.servicios_negocio.Beans.BeanItemPreFactura;
import silat.servicios_negocio.Beans.BeanPreFactura;
import silat.servicios_negocio.Beans.BeanUsuarioAutenticado;
import silat.servicios_negocio.LNSF.IR.LN_C_SFItemPreFacturaRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFPreFacturaRemote;
//
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.net.URLEncoder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import java.util.Set;

import javax.faces.component.UISelectItems;
import javax.faces.model.SelectItem;

import jxl.Workbook;

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import oracle.adf.view.rich.component.rich.RichDialog;

import org.apache.myfaces.trinidad.event.PollEvent;
import org.apache.poi.hssf.model.Sheet;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;

import silat.servicios_negocio.BDLSF.IR.BDL_C_SFUtilsRemote;
import silat.servicios_negocio.Beans.BeanDireccion;
import silat.servicios_negocio.Beans.BeanError;
import silat.servicios_negocio.Beans.BeanFactura;
import silat.servicios_negocio.Beans.BeanReportePrevio;
import silat.servicios_negocio.Beans.BeanTRGuia;
import silat.servicios_negocio.Beans.BeanTRItem;
import silat.servicios_negocio.Beans.BeanUnidadNegocio;
import silat.servicios_negocio.LNSF.IR.LN_C_SFDireccionRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFUnidadNegocioRemote;
import silat.servicios_negocio.LNSF.IR.LN_T_SFFacturaRemote;
import silat.servicios_negocio.LNSF.IR.LN_T_SFPreFacturaRemote;

public class Frm_consultar_pre_factura {

    private RichCommandButton btnBuscar;
    private RichInputDate fecMin;
    private RichInputDate fecMax;
    private RichInputText itCodPF;
    private RichInputText itClie;
    private RichSelectOneChoice socConFac;
    private RichSelectItem si1;
    private RichSelectItem si2;
    private RichInputText itCodPed;
    private RichInputText itGuia;
    private RichTable tbPF;
    private RichCommandButton btnClear;
    private RichCommandButton btnVerItms;
    private RichPopup popItms;
    private RichTable tbItms;
    private RichCommandButton btnExportar;
    private RichCommandButton btnEditar;
    private RichCommandButton btnPrevFact;
    private RichPopup popReport;
    private RichCommandButton btnGenFact;
    private RichPopup popGenFa;
    private RichDialog diaGenFa;
    private RichSelectOneChoice socDire;
    private UISelectItems si3;
    private RichInputText itCodFact;
    private RichSelectOneChoice socUN;
    private UISelectItems si4;
    private RichCommandButton btnRegFact;
    private RichSelectOneChoice socTipFa;
    private RichSelectItem si5;
    private RichSelectItem si6;
    private RichPopup popTipPre;
    /************************************** MIS VARIABLES *************************************************************/
    private LN_C_SFPreFacturaRemote ln_C_SFPreFacturaRemote;
    private LN_C_SFItemPreFacturaRemote ln_C_SFItemPreFacturaRemote;
    private LN_C_SFDireccionRemote ln_C_SFDireccionRemote;
    private BDL_C_SFUtilsRemote bdL_C_SFUtilsRemote;
    private LN_C_SFUnidadNegocioRemote ln_C_SFUnidadNegocioRemote;
    private LN_T_SFFacturaRemote ln_T_SFFacturaRemote;
    private LN_T_SFPreFacturaRemote ln_T_SFPreFacturaRemote;
    private final static String LOOKUP_NAME_SFCPREFACT_REMOTO     = "mapLN_C_SFPreFactura";
    private final static String LOOKUP_NAME_SFCPREFACTITEM_REMOTO = "mapLN_C_SFItemPreFactura";
    private final static String LOOKUP_NAME_SFDIRECCION_REMOTO    = "LUBAL_SIAT_APP-SILATNEGOCIO-LN_C_SFDireccion";
    private final static String LOOKUP_NAME_SFUN_REMOTO           = "mapLN_C_SFUnidadNegocio";
    private final static String LOOKUP_NAME_SF_T_REPO_REMOTO      = "mapBDL_C_SFUtils";
    private final static String LOOKUP_NAME_SFFACT_REMOTO         = "mapLN_T_SFFactura";
    private final static String LOOKUP_NAME_SFTPREFACT_REMOTO     = "mapLN_T_SFPreFactura";
    private BeanUsuarioAutenticado beanUsuario = new BeanUsuarioAutenticado();
    private SessionScopeBeanConsPreFactura beanSessionConsPreFactura;
    FacesContext ctx = FacesContext.getCurrentInstance();
    private final static String SEP = "&";
    private RichCommandButton btnBorrPF;
    private RichPopup popBorrarPF;

    public Frm_consultar_pre_factura() {
        try {
            final Context ctx;
            ctx = new InitialContext();
            ln_C_SFPreFacturaRemote = (LN_C_SFPreFacturaRemote)        ctx.lookup(LOOKUP_NAME_SFCPREFACT_REMOTO);
            ln_C_SFItemPreFacturaRemote = (LN_C_SFItemPreFacturaRemote)ctx.lookup(LOOKUP_NAME_SFCPREFACTITEM_REMOTO);
            ln_C_SFDireccionRemote = (LN_C_SFDireccionRemote)          ctx.lookup(LOOKUP_NAME_SFDIRECCION_REMOTO);
            ln_C_SFUnidadNegocioRemote = (LN_C_SFUnidadNegocioRemote)  ctx.lookup(LOOKUP_NAME_SFUN_REMOTO);
            bdL_C_SFUtilsRemote = (BDL_C_SFUtilsRemote)                ctx.lookup(LOOKUP_NAME_SF_T_REPO_REMOTO);
            ln_T_SFFacturaRemote = (LN_T_SFFacturaRemote)              ctx.lookup(LOOKUP_NAME_SFFACT_REMOTO);
            ln_T_SFPreFacturaRemote = (LN_T_SFPreFacturaRemote)        ctx.lookup(LOOKUP_NAME_SFTPREFACT_REMOTO);
            beanUsuario = (BeanUsuarioAutenticado)Utils.getSession("USER");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void methodOnPostConstruct() {
        if (beanSessionConsPreFactura.getExec() == 0) {
            beanSessionConsPreFactura.setExec(1);
            beanSessionConsPreFactura.setListaUNs(this.llenarUNCombo());
        } else {
            buscarPreFacturas();
        }
    }

    public String buscarPreFacturas() {
        String codpedido = beanSessionConsPreFactura.getCodpedido();
        String flgFactura = beanSessionConsPreFactura.getFlgFactura();
        Date fecMin = beanSessionConsPreFactura.getFecMin();
        Date fecMax = beanSessionConsPreFactura.getFecMax();
        String cliente = beanSessionConsPreFactura.getCliente();
        String cidguia = beanSessionConsPreFactura.getCidguia();
        Long nidPrefact = beanSessionConsPreFactura.getNidPrefact();
        beanSessionConsPreFactura.setLstBeanPreFactura(ln_C_SFPreFacturaRemote.findBeanPreFacturaByAttr_LN(codpedido,
                                                                                                           flgFactura,
                                                                                                           fecMin,
                                                                                                           fecMax,
                                                                                                           cliente,
                                                                                                           cidguia,
                                                                                                           nidPrefact));
        if (tbPF != null) {
            tbPF.setValue(beanSessionConsPreFactura.getLstBeanPreFactura());
            Utils.addTarget(tbPF);
        }
        return null;
    }

    public void limpiar(ActionEvent actionEvent) {
        _limpiar();
    }

    public List<BeanPreFactura> _limpiar() {
        beanSessionConsPreFactura.setCodpedido(null);
        beanSessionConsPreFactura.setFlgFactura(null);
        beanSessionConsPreFactura.setFecMin(null);
        beanSessionConsPreFactura.setFecMax(null);
        beanSessionConsPreFactura.setCliente(null);
        beanSessionConsPreFactura.setCidguia(null);
        beanSessionConsPreFactura.setNidPrefact(null);
        beanSessionConsPreFactura.setLstBeanPreFactura(ln_C_SFPreFacturaRemote.findBeanPreFacturaByAttr_LN(null,null,
                                                                                                           null, null,
                                                                                                           null, null,
                                                                                                           null));
        if (tbPF != null) {
            tbPF.setValue(beanSessionConsPreFactura.getLstBeanPreFactura());
            Utils.addTarget(tbPF);
            Utils.unselectFilas(tbPF);
        }
        return beanSessionConsPreFactura.getLstBeanPreFactura();
    }

    public void selectPreFactura(SelectionEvent selectionEvent) {
        RichTable t = (RichTable)selectionEvent.getSource();
        BeanPreFactura beanPreFactura = (BeanPreFactura)t.getSelectedRowData();
        beanSessionConsPreFactura.setSelectedBeanPreFactura(beanPreFactura);
        btnEditar.setDisabled(true);
        btnPrevFact.setDisabled(true); 
        btnGenFact.setDisabled(true);
        if (beanPreFactura.getFlgFactura().equals("0")){
            btnEditar.setDisabled(false);
            btnPrevFact.setDisabled(false);   
            btnBorrPF.setDisabled(false);
        }else{
            btnBorrPF.setDisabled(true);
        }
        if(beanPreFactura.getCodpedido() != null && beanPreFactura.getFlgFactura().equals("0")){
            if(!beanPreFactura.getCodpedido().equals("")){
                btnGenFact.setDisabled(false);
                btnBorrPF.setDisabled(false);
            }
        }
        btnVerItms.setDisabled(false);
        Utils.addTargetMany(btnVerItms,btnEditar,btnPrevFact,btnGenFact,btnBorrPF);
    }

    public void verItemsAction(ActionEvent actionEvent) {
        if (beanSessionConsPreFactura.getSelectedBeanPreFactura() != null) {
            if (beanSessionConsPreFactura.getSelectedBeanPreFactura().getNidPrefact() != null) {
                beanSessionConsPreFactura.setLstBeanItemPreFactura(ln_C_SFItemPreFacturaRemote.findBeanItemPreFactura_LN(beanSessionConsPreFactura.getSelectedBeanPreFactura().getNidPrefact()));
                Utils.showPopUpMIDDLE(popItms);
            }
        }
    }
    
    public void editarPreFactura(ActionEvent actionEvent) {
        if(beanSessionConsPreFactura.getSelectedBeanPreFactura() != null){
            if (beanSessionConsPreFactura.getSelectedBeanPreFactura().getNidPrefact() != null){
                if (beanSessionConsPreFactura.getSelectedBeanPreFactura().getFlgFactura().equals("0")){
                    if(beanSessionConsPreFactura.getLstBeanItemPreFactura() != null){
                        if(!beanSessionConsPreFactura.getLstBeanItemPreFactura().isEmpty()){
                            beanSessionConsPreFactura.getSelectedBeanPreFactura().setItemsPreFacturaList(beanSessionConsPreFactura.getLstBeanItemPreFactura());
                        }else{
                            beanSessionConsPreFactura.setLstBeanItemPreFactura(ln_C_SFItemPreFacturaRemote.findBeanItemPreFactura_LN(beanSessionConsPreFactura.getSelectedBeanPreFactura().getNidPrefact()));
                            beanSessionConsPreFactura.getSelectedBeanPreFactura().setItemsPreFacturaList(beanSessionConsPreFactura.getLstBeanItemPreFactura());
                        }
                    }else{
                        beanSessionConsPreFactura.setLstBeanItemPreFactura(ln_C_SFItemPreFacturaRemote.findBeanItemPreFactura_LN(beanSessionConsPreFactura.getSelectedBeanPreFactura().getNidPrefact()));
                        beanSessionConsPreFactura.getSelectedBeanPreFactura().setItemsPreFacturaList(beanSessionConsPreFactura.getLstBeanItemPreFactura());
                    }
                    Utils.putSession("PREFAC",beanSessionConsPreFactura.getSelectedBeanPreFactura());
                    Utils._redireccionar(ctx,"/WEB-INF/registrar_prefactura.xml#registrar_prefactura");
                }
            }
        }
    }
    
    public void elegirTipPrevio(ActionEvent actionEvent) {
        if(beanSessionConsPreFactura.getSelectedBeanPreFactura() != null){
            if (beanSessionConsPreFactura.getSelectedBeanPreFactura().getNidPrefact() != null){
                Utils.showPopUpMIDDLE(popTipPre);   
            }
        }
    }
    
    public void verFactura(ActionEvent actionEvent) {//ESTO EN CONSULTAR FACTURAS
        if(beanSessionConsPreFactura.getSelectedBeanPreFactura() != null){
            if (beanSessionConsPreFactura.getSelectedBeanPreFactura().getNidPrefact() != null){
                boolean isPrevio = true;
                String nombreRepo = "vista_previa_factura.jasper";
                setearReporteRegistro(isPrevio,nombreRepo);
            }
        }
    }
    
    public void generarFactura(ActionEvent actionEvent) {
        if(beanSessionConsPreFactura.getSelectedBeanPreFactura() != null){
            if (beanSessionConsPreFactura.getSelectedBeanPreFactura().getNidPrefact() != null){
                beanSessionConsPreFactura.setLstDirecs(this.llenarDireccionCombo(beanSessionConsPreFactura.getSelectedBeanPreFactura().getNidCliente().intValue()));
                Utils.showPopUpMIDDLE(popGenFa);
                popTipPre.hide();
            }
        }
    }
    
    public void registrarFactura(ActionEvent actionEvent) {
        try{
            if(!isOK()){
                return; 
            }
            //setearReporteRegistro(false);
            Date fechaFactura = beanSessionConsPreFactura.getFecFactura();
            BigDecimal[] montos = ln_C_SFPreFacturaRemote.call_Procedure_GET_PREFACTURA_MONTOS_LN(beanSessionConsPreFactura.getSelectedBeanPreFactura().getNidPrefact());
            BigDecimal subTotal = montos[0].setScale(2, RoundingMode.DOWN);
            String cidSerie = beanSessionConsPreFactura.getCodUN();
            String cidRepo = "NO";
            String guias = beanSessionConsPreFactura.getSelectedBeanPreFactura().getGuiasToReporte();
            BigDecimal nidParty = new BigDecimal(beanSessionConsPreFactura.getSelectedBeanPreFactura().getNidCliente());
            String cliente = beanSessionConsPreFactura.getSelectedBeanPreFactura().getCliente();
            String ruc = beanSessionConsPreFactura.getSelectedBeanPreFactura().getEmpresa().getCRuc();
            String nidOServs = beanSessionConsPreFactura.getSelectedBeanPreFactura().getOrdServ();
            String direccion = beanSessionConsPreFactura.getDirec();
            String tipFactura = beanSessionConsPreFactura.getTipFactura();
            Long nidPrefactura = beanSessionConsPreFactura.getSelectedBeanPreFactura().getNidPrefact();
            String guias4Trigger = beanSessionConsPreFactura.getSelectedBeanPreFactura().getGuiasToTrigger();
            BeanFactura bFactura = ln_T_SFFacturaRemote.registrarFactura_LN("C", 
                                                                            fechaFactura,
                                                                            subTotal,
                                                                            2,
                                                                            cidSerie,
                                                                            cidRepo,
                                                                            guias4Trigger,//PARA EL TRIGGER Y ACTUALICE LAS GUIAS CON EL NID FACTURA
                                                                            nidParty,
                                                                            cliente, 
                                                                            ruc,
                                                                            nidOServs,
                                                                            direccion,
                                                                            tipFactura,
                                                                            nidPrefactura,
                                                                            guias,
                                                                            false,
                                                                            null);
            if(bFactura.getBeanError() != null){
                BeanError error = bFactura.getBeanError();
                int severidad = 0;
                if(error.getCidError().equals("000")){
                    severidad = 3;
                    Utils.depurar("Grabo la factura");
                    if(Utils.hasPermiso(beanUsuario.getLstPermisos(),new BigDecimal("38"))){
                        Utils._redireccionar(ctx,"WEB-INF/consultar_facturas.xml#consultar_facturas");
                    }else{
                        Utils._redireccionar(ctx, "WEB-INF/mainTF.xml#mainTF");
                    }
                }else{
                    severidad = 1;
                }
                Utils.throwError_Aux(ctx,error.getCDescripcionError(), severidad);
            }else{
                Utils.throwError_Aux(ctx,"Error Inesperado", 1);
            }
        }catch(Exception e){
            e.printStackTrace();
            Utils.throwError_Aux(ctx,"Error Inesperado", 1);
        }
    }
    
    public void setearReporteRegistro(boolean isReporte, String jasper){
        try {
            BigDecimal[] montos = ln_C_SFPreFacturaRemote.call_Procedure_GET_PREFACTURA_MONTOS_LN(beanSessionConsPreFactura.getSelectedBeanPreFactura().getNidPrefact());
            String monto = montos[2].toString(); //beanSessionScopeRegistrarFactura.getTotal().setScale(2, RoundingMode.DOWN).toString();
            String igv = montos[1].toString(); //beanSessionScopeRegistrarFactura.getIgv().setScale(2, RoundingMode.DOWN).toString();
            String subTotal = montos[0].toString(); //beanSessionScopeRegistrarFactura.getSub_total().setScale(2, RoundingMode.DOWN).toString();
            String cliente = beanSessionConsPreFactura.getSelectedBeanPreFactura().getCliente();
            String guias = beanSessionConsPreFactura.getSelectedBeanPreFactura().getGuiasToReporte(); //beanSessionScopeRegistrarFactura.getGuiasPDF();
            String claveRandom = Utils.generarContrasena();
            String direccion = ""; //beanSessionScopeRegistrarFactura.getDirec();
            String codFact = ""; //beanSessionScopeRegistrarFactura.getCodUN()+"-"+beanSessionScopeRegistrarFactura.getCodFactura();
            String timePath = GregorianCalendar.getInstance().getTimeInMillis() + claveRandom;
            String tipRepo = beanSessionConsPreFactura.getTipFacturaForPrevio();
            String pedido = beanSessionConsPreFactura.getSelectedBeanPreFactura().getCodpedido();
            String ruc = beanSessionConsPreFactura.getSelectedBeanPreFactura().getEmpresa().getCRuc(); //beanSessionScopeRegistrarFactura.getEmpresaSelected().getCRuc();
            direccion = direccion == null ? "" : direccion;
            cliente = cliente == null ? "" : cliente;
            pedido = pedido == null ? "" : pedido;
            String contenido = "";
            if (isReporte) {
                String path =
                    "/showpdfservlet?jasper=" + jasper + SEP + "monto=" + monto + SEP + "igv=" + igv + SEP + "subTotal=" +
                    subTotal + SEP + "estado=previo" + SEP + "cliente=" + URLEncoder.encode(cliente, "UTF-8") + SEP + "guias=" + guias + SEP +
                    "direccion=" + URLEncoder.encode(direccion, "UTF-8") + SEP + "timepath=" + timePath + SEP + "ruc=" + ruc + SEP + "codFact=" +
                    codFact + SEP + "fecha_pago=" + null + SEP + "fechaHoy=" + null + SEP + "tipFactRepo=" + tipRepo +
                    SEP + "contenido="+contenido+SEP+"nidPreFact=" + beanSessionConsPreFactura.getSelectedBeanPreFactura().getNidPrefact() + SEP +
                    "pedido=" + URLEncoder.encode(pedido, "UTF-8") + SEP + "formato_repo=PDF";
                beanSessionConsPreFactura.setSourcePrevio(path);
                Utils.showPopUpMIDDLE(popReport);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }
    
    public void dialogOK_BorrarPreFactura(DialogEvent dialogEvent) {
        try {
            if (beanSessionConsPreFactura.getSelectedBeanPreFactura() != null) {
                BeanPreFactura bPreFac = ln_T_SFPreFacturaRemote.borrarPreFactura_LN(beanSessionConsPreFactura.getSelectedBeanPreFactura().getNidPrefact());
                if (bPreFac.getBeanError() != null) {
                    BeanError error = bPreFac.getBeanError();
                    int severidad = 0;
                    if (error.getCidError().equals("000")) {
                        severidad = 3;
                        Utils.depurar("Borro la pre-factura");
                        _limpiar();
                    } else {
                        severidad = 1;
                    }
                    Utils.throwError_Aux(ctx, error.getCDescripcionError(), severidad);
                } else {
                    Utils.throwError_Aux(ctx, "Error Inesperado", 1);
                }
            }
            popBorrarPF.hide();
        } catch (Exception e) {
            Utils.throwError_Aux(ctx,"Error Inesperado", 1);
        }
    }
    
    public void borrarPreFactura(ActionEvent actionEvent) {
        if (beanSessionConsPreFactura.getSelectedBeanPreFactura() != null) {
            Utils.showPopUpMIDDLE(popBorrarPF);
        }
    }
    
    public void exportData(FacesContext facesContext, OutputStream outputStream) throws IOException, WriteException {
        try {
            List<BeanItemPreFactura> lstBeanItmsPF = beanSessionConsPreFactura.getLstBeanItemPreFactura();
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet("Pre-Factura");
            HSSFRow excelrow = null;
            HSSFRow excelrowHeader = worksheet.createRow((short)0);
            HSSFCell cellHeader = excelrowHeader.createCell(0);
            cellHeader.setCellValue("ORDEN");

            
            HSSFCellStyle style = workbook.createCellStyle(); 
            style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index); 
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 
            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_JUSTIFY); 

            cellHeader.setCellStyle(style); 
            cellHeader = excelrowHeader.createCell(1);
            cellHeader.setCellValue("GUIAS");
            cellHeader.setCellStyle(style); 
            cellHeader = excelrowHeader.createCell(2);
            cellHeader.setCellValue("DESCRIPCION");
            cellHeader.setCellStyle(style); 
            cellHeader = excelrowHeader.createCell(3);
            cellHeader.setCellValue("DESTINO");
            cellHeader.setCellStyle(style); 
            cellHeader = excelrowHeader.createCell(4);
            cellHeader.setCellValue("CLIENTE");
            cellHeader.setCellStyle(style); 
            cellHeader = excelrowHeader.createCell(5);
            cellHeader.setCellValue("SUBTOTAL");
            cellHeader.setCellStyle(style); 
            cellHeader = excelrowHeader.createCell(6);
            cellHeader.setCellValue("IGV SUBTOTAL");
            cellHeader.setCellStyle(style); 
            cellHeader = excelrowHeader.createCell(7);
            cellHeader.setCellValue("TOTAL");
            cellHeader.setCellStyle(style); 
            int i = 1;
            for (BeanItemPreFactura row : lstBeanItmsPF) {
                short j = 0;
                excelrow = worksheet.createRow((short)i);
                HSSFCell cell = excelrow.createCell(j);
                CellStyle cs = workbook.createCellStyle();
                cs.setWrapText(true);
                cs.setAlignment(cs.ALIGN_LEFT);
                cs.setVerticalAlignment(cs.VERTICAL_TOP);
                float heightPoints = row.getCantItmsTotal() * worksheet.getDefaultRowHeightInPoints();
                
                cell = excelrow.createCell(j);j++;
                cell.setCellValue(row.getOrden());
                excelrow.setHeightInPoints(heightPoints);
                cell.setCellStyle(cs);
                
                cell = excelrow.createCell(j);j++;
                cell.setCellValue(row.getGuiasConcat());
                excelrow.setHeightInPoints(heightPoints);
                cell.setCellStyle(cs);
                
                cell = excelrow.createCell(j);j++;
                cell.setCellValue(row.getGuiasItmsConcat());
                excelrow.setHeightInPoints(heightPoints);
                cell.setCellStyle(cs);
                
                cell = excelrow.createCell(j);j++;
                cell.setCellValue(row.getDestino());
                excelrow.setHeightInPoints(heightPoints);
                cell.setCellStyle(cs);
                
                cell = excelrow.createCell(j);j++;
                cell.setCellValue(row.getCliente());
                excelrow.setHeightInPoints(heightPoints);
                cell.setCellStyle(cs);
                
                cell = excelrow.createCell(j);j++;
                cell.setCellValue(row.getSubtotal().doubleValue());
                excelrow.setHeightInPoints(heightPoints);
                cell.setCellStyle(cs);
                
                cell = excelrow.createCell(j);j++;
                cell.setCellValue(row.getIgvSubtotal().doubleValue());
                excelrow.setHeightInPoints(heightPoints);
                cell.setCellStyle(cs);
                
                cell = excelrow.createCell(j);
                cell.setCellValue(row.getTotalItem().doubleValue());
                excelrow.setHeightInPoints(heightPoints);
                cell.setCellStyle(cs);
                
                worksheet.createFreezePane(0, 1, 0, 1);
                i++;
            }
            for(int columnPosition = 0; columnPosition < 8; columnPosition++) {
                worksheet.autoSizeColumn((short) (columnPosition));
            }
            workbook.write(outputStream);
            outputStream.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList llenarDireccionCombo(Integer nidParty){
        ArrayList direcsItems = new ArrayList();
        List<BeanDireccion> direcs = ln_C_SFDireccionRemote.getDireccionByProp_LN(null,nidParty,null);
        if(direcs != null){
            if(direcs.size() > 0){
                for (BeanDireccion d : direcs) {
                    direcsItems.add(new SelectItem(d.getCDireccion(), 
                                                   d.getCDireccion()));
                }
            }
        }
        return direcsItems;
    }
    
    public ArrayList llenarUNCombo() {
        ArrayList unItems = new ArrayList();
        List<BeanUnidadNegocio> roles = ln_C_SFUnidadNegocioRemote.getUnidadesNegocio_LN();
        for (BeanUnidadNegocio r : roles) {
            unItems.add(new SelectItem(r.getCidUnidadNegocio().toString(), 
                                       r.getCidUnidadNegocio().toString()));
        }
        return unItems;
    }
    
    public void exeCodFacturaPoll(PollEvent pe) {
        String cidUn = beanSessionConsPreFactura.getCodUN();
        String codFac = "";
        if(cidUn != null){
            codFac = bdL_C_SFUtilsRemote.call_Procedure_GET_COD_FACTURA_BY_UN(cidUn);
            beanSessionConsPreFactura.setCodFactura(codFac);
            Utils.addTargetMany(itCodFact);
        }
    }
    
    public boolean isOK(){
        boolean isOk = true;
        if(beanSessionConsPreFactura.getCodUN() == null){
            Utils.throwError_Aux(ctx,"Seleccionar una Serie",4);
            return false;
        }
        if(beanSessionConsPreFactura.getDirec() == null){
            Utils.throwError_Aux(ctx,"Seleccionar una direccion",4);
            return false;
        }
        if(beanSessionConsPreFactura.getDirec().equals("")){
            Utils.throwError_Aux(ctx,"Seleccionar una direccion",4);
            return false;
        }
        if(beanSessionConsPreFactura.getCodFactura() == null){
            Utils.throwError_Aux(ctx,"Espere a que se genere el codigo de Factura",4);
            return false;
        }
        return isOk;
    }
    
    public void setBeanSessionConsPreFactura(SessionScopeBeanConsPreFactura beanSessionConsPreFactura) {
        this.beanSessionConsPreFactura = beanSessionConsPreFactura;
    }

    public SessionScopeBeanConsPreFactura getBeanSessionConsPreFactura() {
        return beanSessionConsPreFactura;
    }

    public void setBtnBuscar(RichCommandButton cb1) {
        this.btnBuscar = cb1;
    }

    public RichCommandButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setFecMin(RichInputDate id1) {
        this.fecMin = id1;
    }

    public RichInputDate getFecMin() {
        return fecMin;
    }

    public void setFecMax(RichInputDate id2) {
        this.fecMax = id2;
    }

    public RichInputDate getFecMax() {
        return fecMax;
    }

    public void setItCodPF(RichInputText it1) {
        this.itCodPF = it1;
    }

    public RichInputText getItCodPF() {
        return itCodPF;
    }

    public void setItClie(RichInputText it2) {
        this.itClie = it2;
    }

    public RichInputText getItClie() {
        return itClie;
    }

    public void setSocConFac(RichSelectOneChoice soc1) {
        this.socConFac = soc1;
    }

    public RichSelectOneChoice getSocConFac() {
        return socConFac;
    }

    public void setSi1(RichSelectItem si1) {
        this.si1 = si1;
    }

    public RichSelectItem getSi1() {
        return si1;
    }

    public void setSi2(RichSelectItem si2) {
        this.si2 = si2;
    }

    public RichSelectItem getSi2() {
        return si2;
    }

    public void setItCodPed(RichInputText it1) {
        this.itCodPed = it1;
    }

    public RichInputText getItCodPed() {
        return itCodPed;
    }

    public void setItGuia(RichInputText it2) {
        this.itGuia = it2;
    }

    public RichInputText getItGuia() {
        return itGuia;
    }

    public void setTbPF(RichTable t1) {
        this.tbPF = t1;
    }

    public RichTable getTbPF() {
        return tbPF;
    }

    public void setBtnClear(RichCommandButton cb1) {
        this.btnClear = cb1;
    }

    public RichCommandButton getBtnClear() {
        return btnClear;
    }

    public void setBtnVerItms(RichCommandButton cb1) {
        this.btnVerItms = cb1;
    }

    public RichCommandButton getBtnVerItms() {
        return btnVerItms;
    }

    public void setPopItms(RichPopup p1) {
        this.popItms = p1;
    }

    public RichPopup getPopItms() {
        return popItms;
    }

    public void setTbItms(RichTable t1) {
        this.tbItms = t1;
    }

    public RichTable getTbItms() {
        return tbItms;
    }

    public void setBtnExportar(RichCommandButton cb1) {
        this.btnExportar = cb1;
    }

    public RichCommandButton getBtnExportar() {
        return btnExportar;
    }

    public void setBtnEditar(RichCommandButton cb1) {
        this.btnEditar = cb1;
    }

    public RichCommandButton getBtnEditar() {
        return btnEditar;
    }

    public void setBtnPrevFact(RichCommandButton cb1) {
        this.btnPrevFact = cb1;
    }

    public RichCommandButton getBtnPrevFact() {
        return btnPrevFact;
    }

    public void setPopReport(RichPopup p1) {
        this.popReport = p1;
    }

    public RichPopup getPopReport() {
        return popReport;
    }

    public void setBtnGenFact(RichCommandButton cb1) {
        this.btnGenFact = cb1;
    }

    public RichCommandButton getBtnGenFact() {
        return btnGenFact;
    }

    public void setPopGenFa(RichPopup p1) {
        this.popGenFa = p1;
    }

    public RichPopup getPopGenFa() {
        return popGenFa;
    }

    public void setDiaGenFa(RichDialog d1) {
        this.diaGenFa = d1;
    }

    public RichDialog getDiaGenFa() {
        return diaGenFa;
    }

    public void setSocDire(RichSelectOneChoice soc1) {
        this.socDire = soc1;
    }

    public RichSelectOneChoice getSocDire() {
        return socDire;
    }

    public void setSi3(UISelectItems si3) {
        this.si3 = si3;
    }

    public UISelectItems getSi3() {
        return si3;
    }

    public void setItCodFact(RichInputText it1) {
        this.itCodFact = it1;
    }

    public RichInputText getItCodFact() {
        return itCodFact;
    }

    public void setSocUN(RichSelectOneChoice soc1) {
        this.socUN = soc1;
    }

    public RichSelectOneChoice getSocUN() {
        return socUN;
    }

    public void setSi4(UISelectItems si4) {
        this.si4 = si4;
    }

    public UISelectItems getSi4() {
        return si4;
    }

    public void setBtnRegFact(RichCommandButton cb1) {
        this.btnRegFact = cb1;
    }

    public RichCommandButton getBtnRegFact() {
        return btnRegFact;
    }

    public void setSocTipFa(RichSelectOneChoice soc1) {
        this.socTipFa = soc1;
    }

    public RichSelectOneChoice getSocTipFa() {
        return socTipFa;
    }

    public void setSi5(RichSelectItem si5) {
        this.si5 = si5;
    }

    public RichSelectItem getSi5() {
        return si5;
    }

    public void setSi6(RichSelectItem si6) {
        this.si6 = si6;
    }

    public RichSelectItem getSi6() {
        return si6;
    }

    public void setPopTipPre(RichPopup p1) {
        this.popTipPre = p1;
    }

    public RichPopup getPopTipPre() {
        return popTipPre;
    }

    public void setBtnBorrPF(RichCommandButton cb1) {
        this.btnBorrPF = cb1;
    }

    public RichCommandButton getBtnBorrPF() {
        return btnBorrPF;
    }

    public void setPopBorrarPF(RichPopup p1) {
        this.popBorrarPF = p1;
    }

    public RichPopup getPopBorrarPF() {
        return popBorrarPF;
    }
}
