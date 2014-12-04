package siat.view.backing.estadistico;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.Dimension;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.net.MalformedURLException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.servlet.ServletContext;

import oracle.adf.view.faces.bi.component.graph.UIGraph;
import oracle.adf.view.faces.bi.event.ClickEvent;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputDate;

import oracle.adf.view.rich.component.rich.input.RichSelectManyCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectOneRadio;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelDashboard;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;

import oracle.dss.dataView.Attributes;
import oracle.dss.dataView.ComponentHandle;
import oracle.dss.dataView.DataComponentHandle;
import oracle.dss.dataView.ImageView;

import siat.view.backing.utiles.Utils;

import siat.view.backing.utiles.fecha.FechaUtiles;

import silat.servicios_negocio.Beans.BeanEstCliente;
import silat.servicios_negocio.Beans.BeanFactura;
import silat.servicios_negocio.Beans.BeanManifiesto;
import silat.servicios_negocio.Beans.BeanTRGuia;
import silat.servicios_negocio.LNSF.IR.LN_C_SFEstaClienteRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFEstaViajesRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFGuiaRemote;

public class Frm_estadisticos_viajes {
    private SessionScopeEstadisticosViajes beanSessionEstadisticosViajes;
    private final static String LOOKUP_NAME_SFESTAViajes = "map-LN_C_SFEstaViajes";
    private LN_C_SFEstaViajesRemote ln_C_SFEstaViajes;
    private final static String LOOKUP_NAME_SFGuias = "mapLN_C_SFGuia";
    private LN_C_SFGuiaRemote ln_C_SFGuiaRemote;
    private RichInputDate id1;
    private RichInputDate id2;
    private RichCommandButton cb1;
    private RichSelectOneRadio sor1;
    private RichSelectOneRadio sor2;
    private RichSelectManyCheckbox smc1;
    private RichPanelDashboard pd1;
    private RichPanelBox pb5;
    private RichPanelBox pb6;
    private UIGraph graph1;
    private UIGraph graph2;
    private UIGraph graph3;
    private UIGraph graph4;
    private RichPanelBox pb4;
    private RichPanelBox pb3;
    private RichPanelBox pb2;
    private RichPanelBox pb1;
    private UIGraph graph6;
    private UIGraph graph5;
    private RichPopup popDetBar;
    
    public Frm_estadisticos_viajes(){
        try{
            final Context ctx;
            ctx = new InitialContext();
            ln_C_SFEstaViajes = (LN_C_SFEstaViajesRemote)ctx.lookup(LOOKUP_NAME_SFESTAViajes);
            ln_C_SFGuiaRemote = (LN_C_SFGuiaRemote)ctx.lookup(LOOKUP_NAME_SFGuias);
        }
        catch(Exception e){
            e.printStackTrace();
        }   
    }
    
    public void clickListenerBarEST(ClickEvent clickEvent) {
        ComponentHandle handle = clickEvent.getComponentHandle();
        String nombre = null;
        String tipo = null;
        if (handle instanceof DataComponentHandle) {
            DataComponentHandle dhandle = (DataComponentHandle) handle;
            Attributes[] groupInfo = dhandle.getGroupAttributes();
            Attributes[] seriesInfo = dhandle.getSeriesAttributes();
            if (groupInfo != null) {
                for (Attributes attrs : groupInfo) {
                    nombre = (String) attrs.getValue(Attributes.LABEL_VALUE);
                }
                for (Attributes attrs : seriesInfo) {
                    tipo = (String) attrs.getValue(Attributes.LABEL_VALUE);
                }
            }
        }
        String fec[] = nombre.split(" ");
        Calendar calMin = Calendar.getInstance();
        calMin.set(Integer.parseInt(fec[0]), Integer.parseInt(fec[1])-1,
                calMin.getMinimum(Calendar.DAY_OF_MONTH),
                calMin.getMinimum(Calendar.HOUR_OF_DAY),
                calMin.getMinimum(Calendar.MINUTE),
                calMin.getMinimum(Calendar.SECOND));
        Calendar calMax = Calendar.getInstance();
        calMax.set(Integer.parseInt(fec[0]), Integer.parseInt(fec[1])-1,
                calMax.getMaximum(Calendar.DAY_OF_MONTH),
                calMax.getMaximum(Calendar.HOUR_OF_DAY),
                calMax.getMaximum(Calendar.MINUTE),
                calMax.getMaximum(Calendar.SECOND));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(calMin.getTime()));
        System.out.println(sdf.format(calMax.getTime()));
        BeanTRGuia bean = new BeanTRGuia();
        BeanManifiesto mani = new BeanManifiesto();
        if(tipo.contains("No Exitoso")){
            bean.setValoracion(1);
        } else {
            bean.setValoracion(3);    
        }
        bean.setManifiesto(mani);
        if(tipo.contains("Propio")){
            mani.setCTipoDoc(2+"");
        } else {
            mani.setCTipoDoc(1+"");    
        }
        beanSessionEstadisticosViajes.setGuias(Filter(ln_C_SFGuiaRemote.getGuiasEstadisticas(bean), calMin.getTime(), calMax.getTime()));
        if(mani.getCTipoDoc().equals(2)){
            beanSessionEstadisticosViajes.setTitulo("Ingresos del "+fec[0]+" al mes "+fec[1]+" para los viajes Propios");    
        } else {
            beanSessionEstadisticosViajes.setTitulo("Ingresos del "+fec[0]+" al mes "+fec[1]+" para los viajes Proveedores");
        }
        Utils.showPopUpMIDDLE(popDetBar);
    }
    
    public List<BeanTRGuia> Filter(List<BeanTRGuia> lista, Date fecMin, Date fecMax){
        List<BeanTRGuia> output = new ArrayList<BeanTRGuia>();
        for(BeanTRGuia bean : lista){
            if(bean.getFechaGuia().after(fecMin) && bean.getFechaGuia().before(fecMax)){
                output.add(bean);
            }
        }
        return output;
    }
    
    public void llenarGraficos(ActionEvent actionEvent){
        llenarListIngresos_Egresos();
    }
    
    public void setListasCompletas(){
        setListaViajesExitos();
        setListaViajesFallidos();
        setListaTotalViajes();
        setListaViajesProp();
        setListaViajesProv();
    }
    
    public void llenarListIngresos_Egresos(){
        setListasCompletas();
        List<BeanEstCliente> aux = new ArrayList<BeanEstCliente>();
        aux.addAll(beanSessionEstadisticosViajes.getListaViajesExitosos());
        aux.addAll(beanSessionEstadisticosViajes.getListaViajesFallidos());
        List<BeanEstCliente> aux2 = new ArrayList<BeanEstCliente>();
        aux2.addAll(beanSessionEstadisticosViajes.getListaProp());
        aux2.addAll(beanSessionEstadisticosViajes.getListaProv());
        this.getGraph5().setTabularData(DataGraficoLineEspecial(aux));
        this.getGraph6().setTabularData(DataGraficoLine(aux2));
        this.getGraph1().setTabularData(DataGraficoLineEspecial(aux));
        this.getGraph2().setTabularData(DataGraficoLine(beanSessionEstadisticosViajes.getListaViajesTotal()));
        this.getGraph3().setTabularData(DataGraficoLine(beanSessionEstadisticosViajes.getListaViajesTotal()));
        this.getGraph4().setTabularData(DataGraficoBARSIMPLEViajes(aux2));
    }

    public List DataGraficoLine(List<BeanEstCliente> lista){
        ArrayList data = new ArrayList();
        for(BeanEstCliente bean: lista){
            Object[] data2 = new Object[3];
            data2[0]= bean.getYear()+" "+bean.getMes();
            data2[1]= bean.getRazonSocial();
            System.out.println("Tipo:"+bean.getRazonSocial());
            data2[2] = bean.getConteo();
            data.add(data2);
        }
        return data; 
    }
    
    public List DataGraficoLineEspecial(List<BeanEstCliente> lista){
        ArrayList data = new ArrayList();
        for(BeanEstCliente bean: lista){
            Object[] data2 = new Object[3];
            data2[0]= bean.getYear()+" "+bean.getMes();
            data2[1]= bean.getTipo()+" - "+bean.getRazonSocial();
            data2[2] = bean.getConteo();
            data.add(data2);
        }
        return data; 
    }
    
    public List DataGraficoBARSIMPLEViajes(List<BeanEstCliente> lista){
        ArrayList data = new ArrayList();
        double countProp = 0;
        double countProv = 0;
        if(lista == null){return data;}
        for(BeanEstCliente bean: lista){
            if(bean.getRazonSocial().equalsIgnoreCase("Propio")){
                countProp = countProp + bean.getConteo();
            }
            else{
                countProv = countProv + bean.getConteo();
            }
        }
        Object[] dataaux = {"Viajes","Propio",countProp};
        Object[] dataaux2 = {"Viajes","Proveedor",countProv};
        data.add(dataaux);
        data.add(dataaux2);
        return data; 
    }
    
    public List DataGraficoBARSIMPLE(List<BeanEstCliente> lista,String input){
        ArrayList data = new ArrayList();
        if(lista == null){return data;}
        for(BeanEstCliente bean: lista){
            Object[] data2 = new Object[3];
            data2[0]= input;
            data2[1]= bean.getRazonSocial();
            System.out.println("Conteo: "+bean.getConteo());
            data2[2] = bean.getConteo();
            data.add(data2);
        }
        return data; 
    }
    
    public void changeDashBoardColumn(ValueChangeEvent vcl) {
        vcl.getComponent().processUpdates(FacesContext.getCurrentInstance());
        int column = Integer.parseInt(vcl.getNewValue()+"");
        beanSessionEstadisticosViajes.setColumns(column);
        Utils.addTarget(pd1);
    }
    
    public void changeDashBoardRowHeight(ValueChangeEvent vcl) {
        vcl.getComponent().processUpdates(FacesContext.getCurrentInstance());
        String rowHeight = (String)vcl.getNewValue();
        beanSessionEstadisticosViajes.setRowHeights(rowHeight);
        Utils.addTarget(pd1);
    }
    
    public void doChange(){
        Utils.addTargetMany(pb1,pb2,pb3,pb4,pb5,pb6);
    }
    
    public void PanelVisibleChanger(ValueChangeEvent vce) {
        ArrayList lista = (ArrayList)vce.getNewValue();
        pb1.setVisible(true);
        pb2.setVisible(true);
        pb3.setVisible(true);
        pb4.setVisible(true);
        pb5.setVisible(true);
        pb6.setVisible(true);
        if(lista == null){
            doChange();
            return;
        }
        for(int i = 0; i < lista.size();i++){
            Object obj = lista.get(i);
            if(obj.toString().equalsIgnoreCase("Panel 1")){
                pb5.setVisible(false);
            }
            if(obj.toString().equalsIgnoreCase("Panel 2")){
                pb6.setVisible(false);
            }
            if(obj.toString().equalsIgnoreCase("Panel 3")){
                pb1.setVisible(false);
            }
            if(obj.toString().equalsIgnoreCase("Panel 4")){
                pb2.setVisible(false);
            }
            if(obj.toString().equalsIgnoreCase("Panel 5")){
                pb3.setVisible(false);
            }
            if(obj.toString().equalsIgnoreCase("Panel 6")){
                pb4.setVisible(false);
            }
        }
        doChange();
    }

    public void setListaViajesExitos(){
        List<BeanEstCliente> lista = new ArrayList<BeanEstCliente>();
        lista.addAll(ln_C_SFEstaViajes.getViajesExitosos(beanSessionEstadisticosViajes.getFecMin(), beanSessionEstadisticosViajes.getFecMax(), 3, 5));
        beanSessionEstadisticosViajes.setListaViajesExitosos(lista);
    }
    
    public void setListaViajesFallidos(){
        List<BeanEstCliente> lista = new ArrayList<BeanEstCliente>();
        lista.addAll(ln_C_SFEstaViajes.getViajesExitosos(beanSessionEstadisticosViajes.getFecMin(), beanSessionEstadisticosViajes.getFecMax(), 1, 2));
        beanSessionEstadisticosViajes.setListaViajesFallidos(lista);
    }
    
    public void setListaTotalViajes(){
        List<BeanEstCliente> lista = new ArrayList<BeanEstCliente>();
        lista.addAll(ln_C_SFEstaViajes.getViajesMesGeneral(beanSessionEstadisticosViajes.getFecMin(), beanSessionEstadisticosViajes.getFecMax()));
        beanSessionEstadisticosViajes.setListaViajesTotal(lista);
    }
    
    public void setListaViajesProv(){
        List<BeanEstCliente> lista = new ArrayList<BeanEstCliente>();
        lista.addAll(ln_C_SFEstaViajes.getViajesProvProp(beanSessionEstadisticosViajes.getFecMin(), beanSessionEstadisticosViajes.getFecMax(),1));
        beanSessionEstadisticosViajes.setListaProv(lista);
    }
    
    public void setListaViajesProp(){
        List<BeanEstCliente> lista = new ArrayList<BeanEstCliente>();
        lista.addAll(ln_C_SFEstaViajes.getViajesProvProp(beanSessionEstadisticosViajes.getFecMin(), beanSessionEstadisticosViajes.getFecMax(),2));
        beanSessionEstadisticosViajes.setListaProp(lista);
    }
    
    public void exportPdf(FacesContext facesContext, java.io.OutputStream outputStream) {
        generarPdf(outputStream);
    }
    
    public String rutaImagenes(){
        String rutaLocal = "";
        if(File.separator.equals("/")){
            rutaLocal = File.separator+"recursos" + File.separator + "img" + File.separator;     
        }else{
            rutaLocal = "recursos" + File.separator + "img" + File.separator;   
        }
        FacesContext ctx = FacesContext.getCurrentInstance();
        ServletContext servletCtx = (ServletContext)ctx.getExternalContext().getContext();
        String imageDirPath = servletCtx.getRealPath("/");
        return imageDirPath + rutaLocal;
    }
    
    public String exportGrafPNG(UIGraph graph, String ruta){
        if(graph != null){
            UIGraph dvtgraph = graph; 
            ImageView imgView = dvtgraph.getImageView();
            imgView.setImageSize(new Dimension(600,400));
            try{
                File file = null; 
                FileOutputStream fos;
                file = new File(ruta); 
                fos = new FileOutputStream(file); 
                imgView.exportToPNG(fos); 
                fos.close(); 
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return ruta;                       
    }
    
    public void mostrarFiltro(Document document,
                              String titulo,
                              String selecionado) throws DocumentException {
        Paragraph  paragraph =new Paragraph(titulo,
                       FontFactory.getFont(FontFactory.TIMES, 11, Font.BOLD));
        paragraph.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        document.add(paragraph);
        if(selecionado != null){
            Paragraph  paragraph2 =new Paragraph(selecionado,
                           FontFactory.getFont(FontFactory.TIMES, 9, Font.NORMAL));
            paragraph2.setAlignment(Paragraph.ALIGN_JUSTIFIED);
            document.add(paragraph2);
        }        
    }
    
    public void addSelectFiltro(Document document) throws DocumentException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Paragraph  paragraph =new Paragraph("Filtro",
                       FontFactory.getFont(FontFactory.TIMES, 12, Font.BOLDITALIC));
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph); 
        mostrarFiltro(document,"Documento creado el "+FechaUtiles.fechaHoy(),null);
        String dateE = "Fecha de Resultados:\t";
        if(beanSessionEstadisticosViajes.getFecMin() != null){
            dateE = dateE.concat("Desde el "+formato.format(beanSessionEstadisticosViajes.getFecMin()));
        } else {
            dateE = dateE.concat("Desde el principio de año ");
        }
        if(beanSessionEstadisticosViajes.getFecMax() != null ){
            dateE = dateE.concat(" hasta el "+formato.format(beanSessionEstadisticosViajes.getFecMax()));
        } else {
            dateE = dateE.concat(" hasta el "+FechaUtiles.fechaHoy());
        }
        mostrarFiltro(document, dateE,null);
        String filtroUsados = "Se mostraran los graficos: ";
        mostrarFiltro(document,filtroUsados,null);
        if(pb6.isVisible()){
            filtroUsados = "- Grafico Viajes Por Tipo Mes Lineal";
            mostrarFiltro(document,filtroUsados,null);
        }
        if(pb5.isVisible()){
            filtroUsados = "- Grafico Exito por Viajes por Mes Lineal";
            mostrarFiltro(document,filtroUsados,null);
        }
        if(pb1.isVisible()){
            filtroUsados = "- Grafico Exito por Viajes por Mes Barra";
            mostrarFiltro(document,filtroUsados,null);
        }
        if(pb2.isVisible()){
            filtroUsados = "- Grafico Viajes Totales Barra";
            mostrarFiltro(document,filtroUsados,null);
        }
        if(pb3.isVisible()){
            filtroUsados = "- Grafico Viajes Totales Lineal";
            mostrarFiltro(document,filtroUsados,null);
        }
        if(pb4.isVisible()){
            filtroUsados = "- Grafico Comparativo de Viajes Vs Proveedor Pie";
            mostrarFiltro(document,filtroUsados,null);
        }
        mostrarFiltro(document,filtroUsados,null);
    }
    
    public void generarPdf(java.io.OutputStream fos) {
        String timePath = GregorianCalendar.getInstance().getTimeInMillis() + "";
        String rutaImg = rutaImagenes();
        String rutaSave = rutaImg + timePath;
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, fos);
            document.open();
            Image img = Image.getInstance(rutaImg + "cabecera.png"); //cabecera.png
            img.scalePercent(24);
            img.setAlignment(Image.ALIGN_CENTER);
            document.add(img);
            addSelectFiltro(document);
            int cont = 0;
            if (pb6.isVisible() &&
                beanSessionEstadisticosViajes.getListaViajesExitosos() != null) {
                addImagenes(document, "Grafico Viajes Por Tipo Mes Lineal",
                            exportGrafPNG(graph6, rutaSave + "GR.png"));
                cont++;
                addEspacio(cont, document);
            }
            if (pb5.isVisible() &&
                beanSessionEstadisticosViajes.getListaProp() != null) {
                addImagenes(document, "Grafico Exito por Viajes por Mes Lineal",
                            exportGrafPNG(graph5, rutaSave + "GR.png"));
                cont++;
                addEspacio(cont, document);
            }
            if (pb1.isVisible() &&
                beanSessionEstadisticosViajes.getListaViajesExitosos() != null ) {
                addImagenes(document, "Grafico Exito por Viajes por Mes Barra",
                            exportGrafPNG(graph1, rutaSave + "GR.png"));
                cont++;
                addEspacio(cont, document);
            }
            if (pb2.isVisible() &&
                beanSessionEstadisticosViajes.getListaViajesTotal() != null ) {
                addImagenes(document, "Grafico Viajes Totales Barra",
                            exportGrafPNG(graph2, rutaSave + "GR.png"));
                cont++;
                addEspacio(cont, document);
            }
            if (pb3.isVisible() &&
                beanSessionEstadisticosViajes.getListaViajesTotal() != null) {
                addImagenes(document, "Grafico Viajes Totales Lineal",
                            exportGrafPNG(graph3, rutaSave + "GR.png"));
                cont++;
                addEspacio(cont, document);
            }
            if (pb4.isVisible() &&
                beanSessionEstadisticosViajes.getListaProp() != null) {
                addImagenes(document, "Grafico Comparativo de Viajes Vs Proveedor Pie",
                            exportGrafPNG(graph4, rutaSave + "GR.png"));
                cont++;
                addEspacio(cont, document);
            }
            document.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void addImagenes(Document document, 
                            String titulo, 
                            String rutaImg) throws DocumentException, MalformedURLException, IOException {
        Paragraph  paragraph =new Paragraph("\n"+titulo+"\n",
                       FontFactory.getFont(FontFactory.HELVETICA, 13, Font.BOLDITALIC));
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);
        Image img = Image.getInstance(rutaImg);
        img.scalePercent(75);
        img.setAlignment(Image.ALIGN_CENTER);
        File archivo = new File(rutaImg);
        archivo.delete();
        document.add(img);
    }
    
    public void addEspacio(int i,Document document) throws DocumentException {
        if(i == 1 || i == 3){
            document.newPage();
        }
    }
    
    public void setId1(RichInputDate id1) {
        this.id1 = id1;
    }

    public RichInputDate getId1() {
        return id1;
    }

    public void setId2(RichInputDate id2) {
        this.id2 = id2;
    }

    public RichInputDate getId2() {
        return id2;
    }

    public void setCb1(RichCommandButton cb1) {
        this.cb1 = cb1;
    }

    public RichCommandButton getCb1() {
        return cb1;
    }

    public void setSor1(RichSelectOneRadio sor1) {
        this.sor1 = sor1;
    }

    public RichSelectOneRadio getSor1() {
        return sor1;
    }

    public void setSor2(RichSelectOneRadio sor2) {
        this.sor2 = sor2;
    }

    public RichSelectOneRadio getSor2() {
        return sor2;
    }

    public void setSmc1(RichSelectManyCheckbox smc1) {
        this.smc1 = smc1;
    }

    public RichSelectManyCheckbox getSmc1() {
        return smc1;
    }

    public void setPd1(RichPanelDashboard pd1) {
        this.pd1 = pd1;
    }

    public RichPanelDashboard getPd1() {
        return pd1;
    }

    public void setPb5(RichPanelBox pb5) {
        this.pb5 = pb5;
    }

    public RichPanelBox getPb5() {
        return pb5;
    }

    public void setPb6(RichPanelBox pb6) {
        this.pb6 = pb6;
    }

    public RichPanelBox getPb6() {
        return pb6;
    }

    public void setGraph1(UIGraph graph1) {
        this.graph1 = graph1;
    }

    public UIGraph getGraph1() {
        return graph1;
    }

    public void setGraph2(UIGraph graph2) {
        this.graph2 = graph2;
    }

    public UIGraph getGraph2() {
        return graph2;
    }

    public void setGraph3(UIGraph graph3) {
        this.graph3 = graph3;
    }

    public UIGraph getGraph3() {
        return graph3;
    }

    public void setGraph4(UIGraph graph4) {
        this.graph4 = graph4;
    }

    public UIGraph getGraph4() {
        return graph4;
    }

    public void setPb4(RichPanelBox pb4) {
        this.pb4 = pb4;
    }

    public RichPanelBox getPb4() {
        return pb4;
    }

    public void setPb3(RichPanelBox pb3) {
        this.pb3 = pb3;
    }

    public RichPanelBox getPb3() {
        return pb3;
    }

    public void setPb2(RichPanelBox pb2) {
        this.pb2 = pb2;
    }

    public RichPanelBox getPb2() {
        return pb2;
    }

    public void setPb1(RichPanelBox pb1) {
        this.pb1 = pb1;
    }

    public RichPanelBox getPb1() {
        return pb1;
    }

    public void setGraph6(UIGraph graph6) {
        this.graph6 = graph6;
    }

    public UIGraph getGraph6() {
        return graph6;
    }

    public void setGraph5(UIGraph graph5) {
        this.graph5 = graph5;
    }

    public UIGraph getGraph5() {
        return graph5;
    }

    public void setBeanSessionEstadisticosViajes(SessionScopeEstadisticosViajes beanSessionEstadisticosViajes) {
        this.beanSessionEstadisticosViajes = beanSessionEstadisticosViajes;
    }

    public SessionScopeEstadisticosViajes getBeanSessionEstadisticosViajes() {
        return beanSessionEstadisticosViajes;
    }

    public void setPopDetBar(RichPopup popDetBar) {
        this.popDetBar = popDetBar;
    }

    public RichPopup getPopDetBar() {
        return popDetBar;
    }
}
