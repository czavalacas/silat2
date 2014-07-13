package siat.view.backing.administrativo.cuadre;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;

import java.io.OutputStreamWriter;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import javax.faces.context.FacesContext;

import javax.naming.Context;
import javax.naming.InitialContext;

import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WriteException;

import jxl.write.biff.RowsExceededException;

import oracle.adf.view.rich.component.rich.RichSubform;
import oracle.adf.view.rich.component.rich.data.RichTree;
import oracle.adf.view.rich.component.rich.data.RichTreeTable;
import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.adfinternal.view.faces.model.binding.FacesCtrlHierNodeBinding;

import oracle.jbo.Key;

import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;

import org.apache.myfaces.trinidad.model.CollectionModel;

import siat.view.backing.utiles.Utils;

import silat.servicios_negocio.Beans.BeanCuadre;
import silat.servicios_negocio.Beans.BeanPermisos;
import silat.servicios_negocio.LNSF.IR.LN_C_SFCuadreRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFPermisosBeanRemote;
import silat.servicios_negocio.LNSF.IR.LN_C_SFUsuarioRemote;
import silat.servicios_negocio.LNSF.IR.LN_T_SFPermisosRemoto;

public class Frm_cuadre {
    private RichOutputText otTitulo;
    private RichTreeTable treeCua;
    private RichSubform sfrmCu;
    private RichPanelFormLayout pflCuad;
    private RichInputDate idFecMin;
    private RichInputDate idFecMax;
    private RichCommandButton btnBuscar;
    private RichCommandButton btnLimpiar;
    private RichCommandButton btnExp;
    /*Mis variables*/
    private LN_C_SFCuadreRemote ln_C_SFCuadreRemote;
    private final static String LOOKUP_NAME_SFCUADRE_REMOTO = "mapLN_C_SFCuadre";//#silat.servicios_negocio.LNSF.IR.LN_C_SFCuadreRemote
    private final static String SEPARADOR_CSV = ";";
    private SessionScopedBeanCuadre beanSessionScopedCuadre;
    private ChildPropertyTreeModel permisosTree;
    FacesContext fctx = FacesContext.getCurrentInstance();

    public Frm_cuadre(){
        try {
            final Context ctx;
            ctx = new InitialContext();
            ln_C_SFCuadreRemote = (LN_C_SFCuadreRemote)ctx.lookup(LOOKUP_NAME_SFCUADRE_REMOTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @PostConstruct 
    public void methodOnPostConstruct(){
        if(beanSessionScopedCuadre.getExec() == 0){
            beanSessionScopedCuadre.setExec(1);
            this.mostrarCuadre();
        }else{
            this.mostrarCuadre();
        }
    }
    
    public String mostrarCuadre() {
        List<BeanCuadre> lstBeanCuadre = ln_C_SFCuadreRemote.getReporteCuadre(beanSessionScopedCuadre.getFecMin(),
                                                                              beanSessionScopedCuadre.getFecMax());
        beanSessionScopedCuadre.setLstBeanCuadre(lstBeanCuadre);
        permisosTree = new ChildPropertyTreeModel(beanSessionScopedCuadre.getLstBeanCuadre(),"lstSubCuadres");
        beanSessionScopedCuadre.setPermisosTree(permisosTree);
        if(treeCua != null){ 
            treeCua.setValue(beanSessionScopedCuadre.getPermisosTree());
            Utils.addTarget(treeCua);
        }
        return null;
    }
    
    public String resetearBusqueda() {
        beanSessionScopedCuadre.setFecMax(null);
        beanSessionScopedCuadre.setFecMin(null);
        beanSessionScopedCuadre.setLstBeanCuadre(new ArrayList<BeanCuadre>());
        permisosTree = null;
        mostrarCuadre();
        return null;
    }
    
    public void exportData(FacesContext facesContext, OutputStream outputStream) throws IOException,
                                                                                        WriteException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
        CollectionModel treeModel = beanSessionScopedCuadre.getPermisosTree();
        List<BeanCuadre> lstBeanCuadre = (List<BeanCuadre>) treeModel.getWrappedData();
        writer.write("Modalidad"); writer.write(SEPARADOR_CSV);writer.write("Ingreso S/."); writer.write(SEPARADOR_CSV);writer.write("Egreso S/."); writer.write(SEPARADOR_CSV);writer.write("Balance S/.");
        writer.newLine();
        String espacio = " ";
        exportarFilasExcelAux(lstBeanCuadre, writer,espacio);
        writer.flush();     
        writer.close();
    }
    
    public void exportarFilasExcelAux(List<BeanCuadre> lstBeanCuadre,BufferedWriter writer,String espacio) throws IOException, 
                                                                                                                  WriteException{
        Iterator it = lstBeanCuadre.iterator();
        while(it.hasNext()){
            BeanCuadre cuadre = (BeanCuadre) it.next();
            writer.write(espacio+"> "+cuadre.getDescCuadre());writer.write(SEPARADOR_CSV);
            writer.write(cuadre.getIngreso()+"");writer.write(SEPARADOR_CSV);
            writer.write(cuadre.getEgreso()+"");writer.write(SEPARADOR_CSV);
            writer.write(cuadre.getBalance()+"");writer.write(SEPARADOR_CSV);
            writer.newLine();
            exportarFilasExcelAux(cuadre.getLstSubCuadres(), writer,espacio+"      ");
        }
    }
    
    public void setOtTitulo(RichOutputText ot1) {
        this.otTitulo = ot1;
    }

    public RichOutputText getOtTitulo() {
        return otTitulo;
    }

    public void setBeanSessionScopedCuadre(SessionScopedBeanCuadre beanSessionScopedCuadre) {
        this.beanSessionScopedCuadre = beanSessionScopedCuadre;
    }

    public SessionScopedBeanCuadre getBeanSessionScopedCuadre() {
        return beanSessionScopedCuadre;
    }

    public void setTreeCua(RichTreeTable tt1) {
        this.treeCua = tt1;
    }

    public RichTreeTable getTreeCua() {
        return treeCua;
    }

    public void setSfrmCu(RichSubform s1) {
        this.sfrmCu = s1;
    }

    public RichSubform getSfrmCu() {
        return sfrmCu;
    }

    public void setPflCuad(RichPanelFormLayout pfl1) {
        this.pflCuad = pfl1;
    }

    public RichPanelFormLayout getPflCuad() {
        return pflCuad;
    }

    public void setIdFecMin(RichInputDate id1) {
        this.idFecMin = id1;
    }

    public RichInputDate getIdFecMin() {
        return idFecMin;
    }

    public void setIdFecMax(RichInputDate id2) {
        this.idFecMax = id2;
    }

    public RichInputDate getIdFecMax() {
        return idFecMax;
    }

    public void setBtnBuscar(RichCommandButton cb1) {
        this.btnBuscar = cb1;
    }

    public RichCommandButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnLimpiar(RichCommandButton cb1) {
        this.btnLimpiar = cb1;
    }

    public RichCommandButton getBtnLimpiar() {
        return btnLimpiar;
    }


    public void setBtnExp(RichCommandButton cb1) {
        this.btnExp = cb1;
    }

    public RichCommandButton getBtnExp() {
        return btnExp;
    }


    public void exportDataToExcel(FacesContext facesContext, OutputStream outputStream) {
        // Add event code here...
    }
}
