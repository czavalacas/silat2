package backingWebMovil;

import com.bea.xbean.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.io.OutputStream;

import java.io.PrintWriter;

import java.math.BigDecimal;

import java.net.InetAddress;

import java.net.ServerSocket;
import java.net.UnknownHostException;

import java.sql.Blob;

import java.sql.ResultSet;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import net.bootsfaces.component.InputText;

import net.sf.jmimemagic.Magic;

import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;

import org.apache.commons.io.IOUtils;

import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;
import org.primefaces.event.FileUploadEvent;

import silat.servicios_negocio.Beans.BeanEmpresa;
import silat.servicios_negocio.Beans.BeanTRGuia;
import silat.servicios_negocio.Beans.BeanTRItem;
import silat.servicios_negocio.Beans.BeanTRItemsWebMovil;
import silat.servicios_negocio.LNSF.IL.LN_T_SFManifiestoRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFEmpresasRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFGuiaRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFUsuarioRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFUtilsRemote;
import silat.servicios_negocio.LNSF.IR.LN_T_SFGuiaRemote;
import silat.servicios_negocio.LNSF.SFBean.LN_C_SFEmpresasBean;

@ManagedBean
@ViewScoped
public class Frm_WebMovil extends HttpServlet{
    //CONEXCIONES
    private LN_C_SFUsuarioRemote ln_C_SFUsuarioRemote;
    private final static String LOOKUP_NAME_SFUSUA_REMOTO = "mapLNSFUsuario";
    private LN_C_SFGuiaRemote lN_C_SFGuiaRemote;
    private final static String LOOKUP_NAME_SFGUIA_REMOTO = "mapLN_C_SFGuia";
    private LN_T_SFGuiaRemote ln_T_SFGuiaRemote;
    private final static String LOOKUP_NAME_TSFGUIA_REMOTO = "mapLN_T_SFGuia";
    private LN_C_SFEmpresasRemote ln_C_SFEmpresasRemote;
    private final static String LOOKUP_NAME_SFEmpresa_REMOTO = "mapLN_C_SFEmpresas";
    private LN_T_SFManifiestoRemote ln_T_SFManifiestoRemote;
    private final static String LOOKUP_NAME_SFMANIFIESTO_REMOTO = "mapLN_T_SFManifiesto";
    private LN_C_SFUtilsRemote ln_C_SFUtilsRemote;
    private final static String LOOKUP_NAME_SFC_UTL_REMOTO        = "mapLN_C_SFUtils";
    private Base64 base;
    
    //VARIABLES LISTAS
    private List<BeanTRGuia> listaGuias = new ArrayList<BeanTRGuia>();
    private List<BeanTRGuia> listaGuiasPendientes = new ArrayList<BeanTRGuia>();
    private List<BeanTRGuia> listaGuiasOK = new ArrayList<BeanTRGuia>();
    private List<BeanTRItemsWebMovil> listaItemsAux = new ArrayList<BeanTRItemsWebMovil>();
    private List<BeanTRItem> listaItems = new ArrayList<BeanTRItem>();
    
    //OTRAS VARIABLES
    private BeanEmpresa empresa = new BeanEmpresa();
    private int exec = 0;
    private String style;
    private String style1 = "visibility:hidden;width:0px;height:0px";
    private String style2;
    private String style3 = "font-size:15px;background-color: #bfea30; width: 100%;height:50px;border-color : #16a918";
    private String nombreBoton = "Guardar";
    public String action;
    private String onbsereVacionesGuiaMod;
    private String obserVacionesGuia;
    private String estadoGuia;
    private String itemsGuia;
    private String fechaGuia;
    private String idGuia;
    private int nPaquetesGuia;
    private String bienvenida = "Bienvenido";
    private boolean estadoOK = false;
    private byte[] bytesImagen;
    private String javaScriptC = "";
    private UploadedFile file;
    private String alertaNoImage = "<<Seleccione una imagen>>";
    private InputText idGuiaGUI;
    private byte[] img;
    private String encodeImg;
    private String cconformidad;
    private String saludo = "";
    private int es = 0;
    private String direccionDestino;
    private String direccionRemitente;
    private String nidManifGuia;
    

    //PROBANDO
    private static final String systemLineSeparator = System.getProperty("line.separator");

    
    public Frm_WebMovil() {
        try {
        final Context ctx;
        ctx = new InitialContext();
        ln_C_SFUsuarioRemote = (LN_C_SFUsuarioRemote)ctx.lookup(LOOKUP_NAME_SFUSUA_REMOTO);
        lN_C_SFGuiaRemote = (LN_C_SFGuiaRemote)ctx.lookup(LOOKUP_NAME_SFGUIA_REMOTO);
        ln_T_SFGuiaRemote = (LN_T_SFGuiaRemote)ctx.lookup(LOOKUP_NAME_TSFGUIA_REMOTO);
        ln_C_SFEmpresasRemote = (LN_C_SFEmpresasRemote)ctx.lookup(LOOKUP_NAME_SFEmpresa_REMOTO);
        ln_T_SFManifiestoRemote = (LN_T_SFManifiestoRemote) ctx.lookup(LOOKUP_NAME_SFMANIFIESTO_REMOTO);
        ln_C_SFUtilsRemote = (LN_C_SFUtilsRemote) ctx.lookup(LOOKUP_NAME_SFC_UTL_REMOTO);
        }catch(Exception e){}
    }
    @PostConstruct
    public void methodInvokeOncedOnPageLoad() {
        if (getExec() == 0) {
            setExec(1);
            String usuario = ln_C_SFUsuarioRemote.getUsuario();
            String nidParty = ln_C_SFUsuarioRemote.getNidParty();
            int nid = Integer.parseInt(nidParty);
            BigDecimal BD = new BigDecimal(nid);
            setEmpresa(ln_C_SFEmpresasRemote.selectedEmpresa(BD));
            setSaludo("Bienvenido Usuario "+empresa.getCRazonSocial());
            setListaGuias(lN_C_SFGuiaRemote.guiasByNidParty(nid));
            setListaGuiasPendientes(lN_C_SFGuiaRemote.guiasByNidParty(nid));
            setListaGuiasOK(lN_C_SFGuiaRemote.guiasByNidPartyOK(nid));
        } else {
        }
    }
    public void mostrarGuiasOK(ActionEvent event){
        setListaGuias(listaGuiasOK);
        setStyle("visibility:hidden;width:0px;height:0px");
        setStyle1("visibility:visible");
        setStyle2("visibility:hidden;width:0px;height:0px");
        setStyle3("font-size:15px;background-color: #bfea30; width: 100%;height:50px;border-color : #16a918;display:none;");
        setNombreBoton("Cerrar");
        setEs(1);
    }
    public void mostrarGuiasPendientes(ActionEvent event){
        setListaGuias(listaGuiasPendientes);
        setStyle("visibility:visible");
        setStyle1("visibility:hidden;width:0px;height:0px");
        setStyle2("visibility:visible");
        setStyle3("font-size:15px;background-color: #bfea30; width: 100%;height:50px;border-color : #16a918;display:block;");
        setNombreBoton("Guardar");
        setEs(0);
    }
    /**
        * Método que sirve capturar la guia pendiente o OK seleccionada
        * @return String
        */
    public String editAction() {
                listaItemsAux.clear();
                String h = action;
                System.out.println("guia: "+h);
                traeInfo(h);
                 for(BeanTRItem bean1 : listaItems){
                    BeanTRItemsWebMovil bean2 = new BeanTRItemsWebMovil();
                    bean2.setDescripcion(bean1.getCDescItem());
                    bean2.setUMedida(bean1.getCUndMedida());
                    bean2.setPeso(bean1.getDPeso());
                    bean2.setCantidad(bean1.getNCantidad());
                    bean2.setIdItem(bean1.getIdItem());
                    listaItemsAux.add(bean2);
                } 
               return "";
            }
    /**
        * Método que sirve capturar la informacion de la guia seleccionada
        */
    public void traeInfo(String cID) {
        for (BeanTRGuia bean : listaGuias) {
            if (bean.getCidGuia().equals(cID)) {
                setIdGuia(bean.getCidGuia());
                setNPaquetesGuia(bean.getNumPaquetes());
                setNidManifGuia(""+bean.getTrManifiesto().getNidManifiesto());
                setObserVacionesGuia(bean.getCObservaciones());
                setOnbsereVacionesGuiaMod("Detalle Guia n° "+bean.getCObservaciones());
                setCconformidad(bean.getCConformidad());
                if(getEs() == 1){
                setImg(bean.getImgGuia());
                setEncodeImg(bean.getImgGuiaProv());
                }
                setDireccionDestino(bean.getCDireccionDestino());
                setDireccionRemitente(bean.getCDireccionRemitente());
                setEstadoGuia(bean.getCConformidad());
                String newstring = new SimpleDateFormat("yyyy-MM-dd").format(bean.getFechaGuia());
                setFechaGuia(newstring);
                setListaItems(bean.getItemsLista());
            }
        }
        if(getEs() == 1){
        }else{
            setEncodeImg("#");
        }
    }
    /**
        * Método Servlet para la subida de la imagen por medio de un input File
        */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String h = request.getParameter("manifg");
        int manif = Integer.parseInt(h);     
        String [] check = request.getParameterValues("check");//null=no
        
        String radio = request.getParameter("check1");  
 
        boolean chec = false;
        if(radio != null && radio != null && radio != null && radio != null && radio != null){
            chec = true;
        }
        
        String comentario = request.getParameter("comentario");
        
        String g = request.getParameter("guiag");
        
        if(request.getParameter("fileName") != null && check != null && chec == true && comentario!=""){
            
        int valoracion = 0;
        
        if(radio.equals("1")){
            valoracion = 1;
        }
        if(radio.equals("2")){
            valoracion = 2;
        }
        if(radio.equals("3")){
            valoracion = 3;
        }
        if(radio.equals("4")){
            valoracion = 4;
        }
        if(radio.equals("5")){
            valoracion = 5;
        }
           
        int cantGuias = lN_C_SFGuiaRemote.cantGuiasVigentesByManifiesto_LN(manif);
        String byte64 = request.getParameter("byte64");
        String fileName = byte64;
        byte[] restoredBytes = null;

        ln_T_SFGuiaRemote.cambiarEstadoWebMovil(g, fileName, restoredBytes,valoracion,comentario);
            if(cantGuias<=1){
                ln_T_SFManifiestoRemote.cambiarEstadoManifiesto(manif, "2");
            }
        
            FacesContext ctx = FacesContext.getCurrentInstance();
            ExternalContext extContext = ctx.getExternalContext();
            String url = extContext.encodeActionURL(ctx.getApplication().getViewHandler().getActionURL(ctx, "/faces/Prueba.xhtml"));
            try {
                extContext.redirect(url);
            } catch (IOException ioe) {
                throw new FacesException(ioe);
            }
        
        }
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
    /**
        * Método Code64 que sirve para decodificar bytes
        * @return String
        */
    public static byte[] decodeLines (String s) {
       char[] buf = new char[s.length()];
       int p = 0;
       for (int ip = 0; ip < s.length(); ip++) {
          char c = s.charAt(ip);
          if (c != ' ' && c != '\r' && c != '\n' && c != '\t')
             buf[p++] = c; }
       return decode(buf, 0, p); 
       }
    /**
        * Método Code64 que sirve para decodificar bytes
        * @return byte
        */
    public static byte[] decode (char[] in, int iOff, int iLen) {
       if (iLen%4 != 0) throw new IllegalArgumentException ("Length of Base64 encoded input string is not a multiple of 4.");
       while (iLen > 0 && in[iOff+iLen-1] == '=') iLen--;
       int oLen = (iLen*3) / 4;
       byte[] out = new byte[oLen];
       int ip = iOff;
       int iEnd = iOff + iLen;
       int op = 0;
       while (ip < iEnd) {
          int i0 = in[ip++];
          int i1 = in[ip++];
          int i2 = ip < iEnd ? in[ip++] : 'A';
          int i3 = ip < iEnd ? in[ip++] : 'A';
          if (i0 > 127 || i1 > 127 || i2 > 127 || i3 > 127)
             throw new IllegalArgumentException ("Illegal character in Base64 encoded data.");
          int b0 = map2[i0];
          int b1 = map2[i1];
          int b2 = map2[i2];
          int b3 = map2[i3];
          if (b0 < 0 || b1 < 0 || b2 < 0 || b3 < 0)
             throw new IllegalArgumentException ("Illegal character in Base64 encoded data.");
          int o0 = ( b0       <<2) | (b1>>>4);
          int o1 = ((b1 & 0xf)<<4) | (b2>>>2);
          int o2 = ((b2 &   3)<<6) |  b3;
          out[op++] = (byte)o0;
          if (op<oLen) out[op++] = (byte)o1;
          if (op<oLen) out[op++] = (byte)o2; }
       return out; }
    private static final char[] map1 = new char[64];
       static {
          int i=0;
          for (char c='A'; c<='Z'; c++) map1[i++] = c;
          for (char c='a'; c<='z'; c++) map1[i++] = c;
          for (char c='0'; c<='9'; c++) map1[i++] = c;
          map1[i++] = '+'; map1[i++] = '/'; }


    // Mapping table from Base64 characters to 6-bit nibbles.
    private static final byte[] map2 = new byte[128];
       static {
          for (int i=0; i<map2.length; i++) map2[i] = -1;
          for (int i=0; i<64; i++) map2[map1[i]] = (byte)i; }
    /**
        * Método Code64 que sirve para decodificar bytes
        * @return String
        */
       
    public static String encodeLines (byte[] in) {
       return encodeLines(in, 0, in.length, 76, systemLineSeparator); }
    public static String encodeLines (byte[] in, int iOff, int iLen, int lineLen, String lineSeparator) {
       int blockLen = (lineLen*3) / 4;
       if (blockLen <= 0) throw new IllegalArgumentException();
       int lines = (iLen+blockLen-1) / blockLen;
       int bufLen = ((iLen+2)/3)*4 + lines*lineSeparator.length();
       StringBuilder buf = new StringBuilder(bufLen);
       int ip = 0;
       while (ip < iLen) {
          int l = Math.min(iLen-ip, blockLen);
          buf.append (encode(in, iOff+ip, l));
          buf.append (lineSeparator);
          ip += l; }
       return buf.toString(); }
    /**
        * Método Code64 que sirve para decodificar bytes
        * @return char
        */
    public static char[] encode (byte[] in, int iOff, int iLen) {
       int oDataLen = (iLen*4+2)/3;       // output length without padding
       int oLen = ((iLen+2)/3)*4;         // output length including padding
       char[] out = new char[oLen];
       int ip = iOff;
       int iEnd = iOff + iLen;
       int op = 0;
       while (ip < iEnd) {
          int i0 = in[ip++] & 0xff;
          int i1 = ip < iEnd ? in[ip++] & 0xff : 0;
          int i2 = ip < iEnd ? in[ip++] & 0xff : 0;
          int o0 = i0 >>> 2;
          int o1 = ((i0 &   3) << 4) | (i1 >>> 4);
          int o2 = ((i1 & 0xf) << 2) | (i2 >>> 6);
          int o3 = i2 & 0x3F;
          out[op++] = map1[o0];
          out[op++] = map1[o1];
          out[op] = op < oDataLen ? map1[o2] : '='; op++;
          out[op] = op < oDataLen ? map1[o3] : '='; op++; }
       return out; }

    /**
        * Método Code64 que sirve para decodificar bytes
        * @return byte
        */
    public  byte[] extractBytes(String ImageName) throws IOException {
        File archivo = new File(ImageName);
        byte[] aBytes = null;
        long tamanoA = archivo.length(); 
        aBytes = new byte[(int) tamanoA];
        try{
            FileInputStream docu = new FileInputStream(archivo);
            int numBytes = docu.read(aBytes);
            docu.close();
            archivo.delete();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return aBytes;
    }   
    
    public void g(String n){
        File file = new File(n);

             byte[] b = new byte[(int) file.length()];
             try {
                   FileInputStream fileInputStream = new FileInputStream(file);
                   fileInputStream.read(b);
                   for (int i = 0; i < b.length; i++) {
                               System.out.println((char)b[i]);
                    }
              } catch (FileNotFoundException e) {
                          System.out.println("File Not Found.");
                          e.printStackTrace();
              }
              catch (IOException e1) {
                       System.out.println("Error Reading The File.");
                        e1.printStackTrace();
              }
    }
    
    public String redirectLogin() {
        ln_C_SFUtilsRemote.SystemOutPrint1nWebMovil(null, null);
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        String url = extContext.encodeActionURL(ctx.getApplication().getViewHandler().getActionURL(ctx, "/frm_login"));
        System.out.println("URL movil:"+url);
        String h = url.replaceAll("LUBAL_SIAT_APP-SILATWEBMOVIL-context-root", "silat");
        try {                      
            extContext.redirect(h);
        } catch (IOException ioe) {
            throw new FacesException(ioe);
        }
        return null;
    }
    
    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }

    public void setListaGuias(List<BeanTRGuia> listaGuias) {
        this.listaGuias = listaGuias;
    }

    public List<BeanTRGuia> getListaGuias() {
        return listaGuias;
    }

    public void setListaGuiasPendientes(List<BeanTRGuia> listaGuiasPendientes) {
        this.listaGuiasPendientes = listaGuiasPendientes;
    }

    public List<BeanTRGuia> getListaGuiasPendientes() {
        return listaGuiasPendientes;
    }

    public void setListaGuiasOK(List<BeanTRGuia> listaGuiasOK) {
        this.listaGuiasOK = listaGuiasOK;
    }

    public List<BeanTRGuia> getListaGuiasOK() {
        return listaGuiasOK;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getStyle() {
        return style;
    }

    public void setNombreBoton(String nombreBoton) {
        this.nombreBoton = nombreBoton;
    }

    public String getNombreBoton() {
        return nombreBoton;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setObserVacionesGuia(String obserVacionesGuia) {
        this.obserVacionesGuia = obserVacionesGuia;
    }

    public String getObserVacionesGuia() {
        return obserVacionesGuia;
    }

    public void setEstadoGuia(String estadoGuia) {
        this.estadoGuia = estadoGuia;
    }

    public String getEstadoGuia() {
        return estadoGuia;
    }

    public void setFechaGuia(String fechaGuia) {
        this.fechaGuia = fechaGuia;
    }

    public String getFechaGuia() {
        return fechaGuia;
    }

    public void setIdGuia(String idGuia) {
        this.idGuia = idGuia;
    }

    public String getIdGuia() {
        return idGuia;
    }

    public void setNPaquetesGuia(int nPaquetesGuia) {
        this.nPaquetesGuia = nPaquetesGuia;
    }

    public int getNPaquetesGuia() {
        return nPaquetesGuia;
    }

    public void setEstadoOK(boolean estadoOK) {
        this.estadoOK = estadoOK;
    }

    public boolean isEstadoOK() {
        return estadoOK;
    }

    public void setListaItems(List<BeanTRItem> listaItems) {
        this.listaItems = listaItems;
    }

    public List<BeanTRItem> getListaItems() {
        return listaItems;
    }

    public void setOnbsereVacionesGuiaMod(String onbsereVacionesGuiaMod) {
        this.onbsereVacionesGuiaMod = onbsereVacionesGuiaMod;
    }

    public String getOnbsereVacionesGuiaMod() {
        return onbsereVacionesGuiaMod;
    }

    public void setListaItemsAux(List<BeanTRItemsWebMovil> listaItemsAux) {
        this.listaItemsAux = listaItemsAux;
    }

    public List<BeanTRItemsWebMovil> getListaItemsAux() {
        return listaItemsAux;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setAlertaNoImage(String alertaNoImage) {
        this.alertaNoImage = alertaNoImage;
    }

    public String getAlertaNoImage() {
        return alertaNoImage;
    }

    public void setBytesImagen(byte[] bytesImagen) {
        this.bytesImagen = bytesImagen;
    }

    public byte[] getBytesImagen() {
        return bytesImagen;
    }

    public void setJavaScriptC(String javaScriptC) {
        this.javaScriptC = javaScriptC;
    }

    public String getJavaScriptC() {
        return javaScriptC;
    }

    public void setStyle1(String style1) {
        this.style1 = style1;
    }

    public String getStyle1() {
        return style1;
    }

    public void setIdGuiaGUI(InputText idGuiaGUI) {
        this.idGuiaGUI = idGuiaGUI;
    }

    public InputText getIdGuiaGUI() {
        return idGuiaGUI;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public byte[] getImg() {
        return img;
    }

    public void setEncodeImg(String encodeImg) {
        this.encodeImg = encodeImg;
    }

    public String getEncodeImg() {
        return encodeImg;
    }

    public void setCconformidad(String cconformidad) {
        this.cconformidad = cconformidad;
    }

    public String getCconformidad() {
        return cconformidad;
    }

    public void setEs(int es) {
        this.es = es;
    }

    public int getEs() {
        return es;
    }

    public void setSaludo(String saludo) {
        this.saludo = saludo;
    }

    public String getSaludo() {
        return saludo;
    }

    public void setEmpresa(BeanEmpresa empresa) {
        this.empresa = empresa;
    }

    public BeanEmpresa getEmpresa() {
        return empresa;
    }

    public void setDireccionDestino(String direccionDestino) {
        this.direccionDestino = direccionDestino;
    }

    public String getDireccionDestino() {
        return direccionDestino;
    }

    public void setDireccionRemitente(String direccionRemitente) {
        this.direccionRemitente = direccionRemitente;
    }

    public String getDireccionRemitente() {
        return direccionRemitente;
    }

    public void setNidManifGuia(String nidManifGuia) {
        this.nidManifGuia = nidManifGuia;
    }

    public String getNidManifGuia() {
        return nidManifGuia;
    }

    public void setStyle2(String style2) {
        this.style2 = style2;
    }

    public String getStyle2() {
        return style2;
    }

    public void setStyle3(String style3) {
        this.style3 = style3;
    }

    public String getStyle3() {
        return style3;
    }
}
