package siat.view.backing.estadistico;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.naming.Context;
import javax.naming.InitialContext;

import oracle.adf.view.faces.bi.component.graph.UIGraph;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichSelectManyCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectOneRadio;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelDashboard;

import oracle.adf.view.rich.component.rich.nav.RichCommandButton;

import siat.view.backing.utiles.Utils;

import silat.servicios_negocio.Beans.BeanEstCliente;
import silat.servicios_negocio.LNSF.IR.LN_C_SFEstaClienteRemote;
import silat.servicios_negocio.LNSF.SFBean.LN_C_SFEstaClienteBean;

public class Frm_estadisticos_cliente {
    private SessionScopedEstadisticosCliente beanSessionEstadisticosCliente;
    private LN_C_SFEstaClienteRemote ln_C_SFEstaCliente;
    private final static String LOOKUP_NAME_SFESTACLIENTE = "map-LN_C_SFEstaCliente";
    private RichInputDate id1;
    private RichInputDate id2;
    private RichSelectOneRadio sor1;
    private RichSelectOneRadio sor2;
    private RichSelectManyCheckbox smc1;
    private RichPanelDashboard pd1;
    private RichPanelBox pb5;
    private UIGraph graph6;
    private RichPanelBox pb6;
    private UIGraph graph5;
    private RichPanelBox pb1;
    private UIGraph graph1;
    private RichPanelBox pb2;
    private UIGraph graph2;
    private RichPanelBox pb3;
    private UIGraph graph3;
    private RichPanelBox pb4;
    private UIGraph graph4;
    private RichCommandButton cb1;


    public Frm_estadisticos_cliente(){
        try{
            final Context ctx;
            ctx = new InitialContext();
            ln_C_SFEstaCliente = (LN_C_SFEstaClienteRemote)ctx.lookup(LOOKUP_NAME_SFESTACLIENTE);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void llenarGraficos(ActionEvent actionEvent){
        llenarListIngresos_Egresos();
    }
    
    public void llenarListIngresos_Egresos(){
        //beanSessionEstadisticosCliente.setListGrafico(ln_C_SFCuadreRemote.getReporteCuadre(beanSessionEstadisticos.getFecMin(), beanSessionEstadisticos.getFecMax()));
        setListaOrdenesAprob();
        setListaOrdenesAnul();
        setListaOrdenesXMES();
        setListaGananciasCliente();
        setListaGananciasClienteMES();
        this.getGraph1().setTabularData(this.DataGraficoBARSIMPLE(beanSessionEstadisticosCliente.getLista_Ordenes_Fina(),"Ordenes"));
        this.getGraph2().setTabularData(this.DataGraficoBARSIMPLE(beanSessionEstadisticosCliente.getLista_Ordenes_Anul(),"Ordenes"));
        this.getGraph3().setTabularData(this.DataGraficoBARSIMPLE(beanSessionEstadisticosCliente.getLista_Clientes(),"Clientes"));
        this.getGraph4().setTabularData(this.DataGraficoBARSIMPLE(beanSessionEstadisticosCliente.getLista_Clientes(),"Clientes"));
        this.getGraph5().setTabularData(this.DataGraficoLine(beanSessionEstadisticosCliente.getLista_Clientes_MES()));
        this.getGraph6().setTabularData(this.DataGraficoLine(beanSessionEstadisticosCliente.getLista_Ordenes_MES()));
        Utils.addTargetMany(graph1,graph2,graph3,graph4,graph5,graph6);
    }
    
    public void setListaGananciasCliente(){
        List <BeanEstCliente> lista = new ArrayList<BeanEstCliente>();
        lista.addAll(ln_C_SFEstaCliente.getGanaciasGeneralCliente(beanSessionEstadisticosCliente.getFecMin(), beanSessionEstadisticosCliente.getFecMax(), 5));
        beanSessionEstadisticosCliente.setLista_Clientes(lista);
    }
    
    public void setListaGananciasClienteMES(){
        List <BeanEstCliente> lista = new ArrayList<BeanEstCliente>();
        lista.addAll(ln_C_SFEstaCliente.getGanaciasXMESCliente(beanSessionEstadisticosCliente.getFecMin(), beanSessionEstadisticosCliente.getFecMax()));
        beanSessionEstadisticosCliente.setLista_Clientes_MES(lista);
    }
    
    public void setListaOrdenesXMES(){
        List <BeanEstCliente> lista = new ArrayList<BeanEstCliente>();
        lista.addAll(ln_C_SFEstaCliente.getOrdenesXMeses(beanSessionEstadisticosCliente.getFecMin(), beanSessionEstadisticosCliente.getFecMax(), "F"));
        beanSessionEstadisticosCliente.setLista_Ordenes_MES(lista);
    }
    
    public void setListaOrdenesAprob(){
        List <BeanEstCliente> lista = new ArrayList<BeanEstCliente>();
        lista.addAll(ln_C_SFEstaCliente.getOrdenesServicioXCLIENTE(beanSessionEstadisticosCliente.getFecMin(), beanSessionEstadisticosCliente.getFecMax(), 5, "F"));
        beanSessionEstadisticosCliente.setLista_Ordenes_Fina(lista);
    }
    
    public void setListaOrdenesAnul(){
        List <BeanEstCliente> lista = new ArrayList<BeanEstCliente>();
        lista.addAll(ln_C_SFEstaCliente.getOrdenesServicioXCLIENTE(beanSessionEstadisticosCliente.getFecMin(), beanSessionEstadisticosCliente.getFecMax(), 5, "A"));
        beanSessionEstadisticosCliente.setLista_Ordenes_Fina(lista);
    }
    /*
    public void setListaGraficosXMES(){
        List <BeanEstCliente> lista = new ArrayList<BeanEstCliente>();
        lista.addAll(ln_C_SFCuadreRemote.getGastosXMES(beanSessionEstadisticos.getFecMin(), beanSessionEstadisticos.getFecMax()));
        lista.addAll(ln_C_SFCuadreRemote.getGastosAdelantos(beanSessionEstadisticos.getFecMin(), beanSessionEstadisticos.getFecMax()));
        lista.addAll(ln_C_SFCuadreRemote.getGastosManifiestos(beanSessionEstadisticos.getFecMin(), beanSessionEstadisticos.getFecMax()));
        lista.addAll(ln_C_SFCuadreRemote.getDiferencialNota(beanSessionEstadisticos.getFecMin(), beanSessionEstadisticos.getFecMax(), "C"));
        beanSessionEstadisticos.setListGraficoGasMES(lista);
    }*/
    
    public List DataGraficoLine(List<BeanEstCliente> lista){
        ArrayList data = new ArrayList();
        for(BeanEstCliente bean: lista){
            Object[] data2 = new Object[3];
            data2[0]= bean.getYear()+" "+bean.getMes();
            data2[1]= bean.getRazonSocial();
            data2[2] = bean.getConteo();
            data.add(data2);
        }
        return data; 
    }
    
    public List DataGraficoBARSIMPLE(List<BeanEstCliente> lista,String input){
        ArrayList data = new ArrayList();
        if(lista == null){return data;}
        for(BeanEstCliente bean: lista){
            Object[] data2 = new Object[3];
            data2[0]= input;
            data2[1]= bean.getRazonSocial();
            data2[2] = bean.getConteo();
            data.add(data2);
        }
        return data; 
    }
    /*
    public void setListaGraficosGastosGenMen(){
        List <BeanEstCliente> lista = new ArrayList<BeanEstCliente>();
        for(BeanEstCliente bean: beanSessionEstadisticos.getListGraficoGasMES()){
            BeanEstCliente aux = new BeanEstCliente();
            aux.setYear(bean.getYear());
            aux.setMes(bean.getMes());
            aux.setRazonSocial("Gasto");
            aux.setConteo(bean.getConteo());
            lista.add(aux);
        }
        beanSessionEstadisticos.setListGraficoGastosGen(lista);
    }
    
    public List DataGraficoIngresosLine(String tipo){
            ArrayList data = new ArrayList();
            for(BeanCuadre cuadre: beanSessionEstadisticos.getListGrafico()){
                List<BeanCuadre> listCuadre = cuadre.getLstSubCuadres();
                for(BeanCuadre cuadreHijo: listCuadre){
                List<BeanCuadre> listCuadreHijo = cuadreHijo.getLstSubCuadres();
                    if(cuadreHijo.getDescCuadre().equalsIgnoreCase(tipo))
                    for(BeanCuadre cuadreNieto: listCuadreHijo){
                        Object[] data2 = new Object[3];
                        data2[0]= cuadreNieto.getDescCuadre();
                        data2[1]= cuadreHijo.getDescCuadre();
                        data2[2] = cuadreNieto.getEgreso().intValue()+cuadreNieto.getIngreso().intValue();
                        data.add(data2);
                    }
                }
            }
        return data; 
    }
    
    public List DataGraficoIngresosBAR(String tipo){
            ArrayList data = new ArrayList();
            for(BeanCuadre cuadre: beanSessionEstadisticos.getListGrafico()){
                List<BeanCuadre> listCuadre = cuadre.getLstSubCuadres();
                for(BeanCuadre cuadreHijo: listCuadre){
                    if(tipo.equalsIgnoreCase(cuadreHijo.getDescCuadre())){
                        List<BeanCuadre> listCuadreHijo = cuadreHijo.getLstSubCuadres();
                        for(BeanCuadre cuadreNieto: listCuadreHijo){
                            Object[] data2 = new Object[3];
                            data2[0]= cuadreHijo.getDescCuadre();
                            data2[1]= cuadreNieto.getDescCuadre();
                            data2[2] = cuadreNieto.getEgreso().intValue()+cuadreNieto.getIngreso().intValue();
                            data.add(data2);
                        }
                    }
                }
            }
        return data; 
    }
    
    public List DataGraficoIngresosVSEgresosBAR(){
        ArrayList data = new ArrayList();
        for(BeanCuadre cuadre: beanSessionEstadisticos.getListGrafico()){
            List<BeanCuadre> listCuadre = cuadre.getLstSubCuadres();
            for(BeanCuadre cuadreHijo: listCuadre){
                Object[] data2 = new Object[3];
                data2[0]= "Valores";
                data2[1]= cuadreHijo.getDescCuadre();
                data2[2] = cuadreHijo.getEgreso().intValue()+cuadreHijo.getIngreso().intValue();
                data.add(data2);
            }
        }
        return data; 
    }
    
    public List DataGraficoIngresosVSEgresosPie() {
        ArrayList data = new ArrayList();
        for(BeanCuadre cuadre: beanSessionEstadisticos.getListGrafico()){
            List<BeanCuadre> listCuadre = cuadre.getLstSubCuadres();
            for(BeanCuadre cuadreHijo: listCuadre){
                Object[] data2 = new Object[3];
                data2[0]= "Ingresos VS Egresos";
                data2[1]= cuadreHijo.getDescCuadre();
                data2[2] = cuadreHijo.getEgreso().intValue()+cuadreHijo.getIngreso().intValue();
                data.add(data2);
            }
        }
        return data;
    }*/
    
    public void changeDashBoardColumn(ValueChangeEvent vcl) {
        vcl.getComponent().processUpdates(FacesContext.getCurrentInstance());
        int column = Integer.parseInt(vcl.getNewValue()+"");
        beanSessionEstadisticosCliente.setColumns(column);
        Utils.addTarget(pd1);
    }
    
    public void changeDashBoardRowHeight(ValueChangeEvent vcl) {
        vcl.getComponent().processUpdates(FacesContext.getCurrentInstance());
        String rowHeight = (String)vcl.getNewValue();
        beanSessionEstadisticosCliente.setRowHeights(rowHeight);
        System.out.println(beanSessionEstadisticosCliente.getRowHeights());
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

    public void setGraph6(UIGraph graph6) {
        this.graph6 = graph6;
    }

    public UIGraph getGraph6() {
        return graph6;
    }

    public void setPb6(RichPanelBox pb6) {
        this.pb6 = pb6;
    }

    public RichPanelBox getPb6() {
        return pb6;
    }

    public void setGraph5(UIGraph graph5) {
        this.graph5 = graph5;
    }

    public UIGraph getGraph5() {
        return graph5;
    }

    public void setPb1(RichPanelBox pb1) {
        this.pb1 = pb1;
    }

    public RichPanelBox getPb1() {
        return pb1;
    }

    public void setGraph1(UIGraph graph1) {
        this.graph1 = graph1;
    }

    public UIGraph getGraph1() {
        return graph1;
    }

    public void setPb2(RichPanelBox pb2) {
        this.pb2 = pb2;
    }

    public RichPanelBox getPb2() {
        return pb2;
    }

    public void setGraph2(UIGraph graph2) {
        this.graph2 = graph2;
    }

    public UIGraph getGraph2() {
        return graph2;
    }

    public void setPb3(RichPanelBox pb3) {
        this.pb3 = pb3;
    }

    public RichPanelBox getPb3() {
        return pb3;
    }

    public void setGraph3(UIGraph graph3) {
        this.graph3 = graph3;
    }

    public UIGraph getGraph3() {
        return graph3;
    }

    public void setPb4(RichPanelBox pb4) {
        this.pb4 = pb4;
    }

    public RichPanelBox getPb4() {
        return pb4;
    }

    public void setGraph4(UIGraph graph4) {
        this.graph4 = graph4;
    }

    public UIGraph getGraph4() {
        return graph4;
    }

    public void setBeanSessionEstadisticosCliente(SessionScopedEstadisticosCliente beanSessionEstadisticosCliente) {
        this.beanSessionEstadisticosCliente = beanSessionEstadisticosCliente;
    }

    public SessionScopedEstadisticosCliente getBeanSessionEstadisticosCliente() {
        return beanSessionEstadisticosCliente;
    }

    public void setCb1(RichCommandButton cb1) {
        this.cb1 = cb1;
    }

    public RichCommandButton getCb1() {
        return cb1;
    }
}
