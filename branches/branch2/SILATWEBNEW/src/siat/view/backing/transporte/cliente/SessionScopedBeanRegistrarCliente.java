package siat.view.backing.transporte.cliente;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.collections.BeanMap;

import silat.servicios_negocio.Beans.BeanChofer;
import silat.servicios_negocio.Beans.BeanDireccion;
import silat.servicios_negocio.Beans.BeanFlota;
import silat.servicios_negocio.Beans.BeanTRItem;

public class SessionScopedBeanRegistrarCliente implements Serializable {
    @SuppressWarnings("compatibility:7629328835263849124")
    private static final long serialVersionUID = 1L;

    private List<BeanDireccion> listDirec = new ArrayList<BeanDireccion>();
    private BeanDireccion beanDirec = new BeanDireccion();
    private List<BeanChofer> listChofer = new ArrayList<BeanChofer>();
    private BeanChofer beanChofer = new BeanChofer();
    private List<BeanFlota> listFlota = new ArrayList<BeanFlota>();
    private BeanFlota beanFlota = new BeanFlota();  
    private List<String> relaValue = new ArrayList<String>();
    private String certificadoInscripcion;
    private int numDirec = 0;
    private boolean isDepartamentoSeteado = false;
    private boolean isProvinciaSeteado = false;
    //dfloresgonz
    private boolean isRazonSocialOK = false;
    private boolean isDisa = true;
    private boolean isReq_Vis = false;
    private boolean panelRegistroFlota = false;
    private boolean panelRegistroChofer = false;
    private int accion = 0;//1 registrar, 2 modificar, 3 borrar

    public boolean contiene(BeanChofer beanChofer){
        for(BeanChofer chofer : getListChofer()){
            if(comparaChofer(chofer,beanChofer)){
                return true;
            }
        }
        return false;
    }
    
    public boolean contieneFlota(BeanFlota beanFlota){
        for(BeanFlota flota : getListFlota()){
            if(comparaChofer(flota,beanFlota)){
                return true;
            }
        }
        return false;
    }
    
    public boolean contieneDireccion(BeanDireccion beanDirec){
        for(BeanDireccion direccion : getListDirec()){
            if(comparaChofer(direccion,beanDirec)){
                return true;
            }
        }
        return false;
    }
    
    public boolean comparaChofer(Object oldObject, Object newObject){
        try{
            BeanMap map = new BeanMap(oldObject);
            PropertyUtilsBean propUtils = new PropertyUtilsBean();int a = 0;
            for (Object propNameObject : map.keySet()) {//System.out.println("a: "+a);a++;
                String propertyName = (String)propNameObject;
                Object property1 = propUtils.getProperty(oldObject, propertyName);
                Object property2 = propUtils.getProperty(newObject, propertyName);
                if(property1 != null && property2 != null){
                    if (property1.equals(property2)) {
                        System.out.println("  " + propertyName + " is equal");
                    } else {
                        System.out.println("> " + propertyName + " is different (oldValue=\"" + property1 +
                                           "\", newValue=\"" + property2 + "\")");
                        return false;
                    }
                }
                if((property1 != null && property2 == null) || (property1 == null && property2 != null)){
                    return false;
                }
            }
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public void setAccion(int accion) {
        this.accion = accion;
    }

    public int getAccion() {
        return accion;
    }

    public void setBeanChofer(BeanChofer beanChofer) {
        this.beanChofer = beanChofer;
    }

    public BeanChofer getBeanChofer() {
        return beanChofer;
    }

    public void setListChofer(List<BeanChofer> listChofer) {
        this.listChofer = listChofer;
    }

    public List<BeanChofer> getListChofer() {
        return listChofer;
    }


    public void setRelaValue(List<String> relaValue) {
        this.relaValue = relaValue;
    }

    public List<String> getRelaValue() {
        return relaValue;
    }

    public void setBeanFlota(BeanFlota beanFlota) {
        this.beanFlota = beanFlota;
    }

    public BeanFlota getBeanFlota() {
        return beanFlota;
    }

    public void setListFlota(List<BeanFlota> listFlota) {
        this.listFlota = listFlota;
    }

    public List<BeanFlota> getListFlota() {
        return listFlota;
    }

    public void setNumDirec(int numDirec) {
        this.numDirec = numDirec;
    }

    public int getNumDirec() {
        return numDirec;
    }

    public void setBeanDirec(BeanDireccion beanDirec) {
        this.beanDirec = beanDirec;
    }

    public BeanDireccion getBeanDirec() {
        return beanDirec;
    }

    public void setListDirec(List<BeanDireccion> listDirec) {
        this.listDirec = listDirec;
    }

    public List<BeanDireccion> getListDirec() {
        return listDirec;
    }

    public void setIsDepartamentoSeteado(boolean isDepartamentoSeteado) {
        this.isDepartamentoSeteado = isDepartamentoSeteado;
    }

    public boolean isIsDepartamentoSeteado() {
        return isDepartamentoSeteado;
    }

    public void setIsProvinciaSeteado(boolean isProvinciaSeteado) {
        this.isProvinciaSeteado = isProvinciaSeteado;
    }

    public boolean isIsProvinciaSeteado() {
        return isProvinciaSeteado;
    }

    public void setCertificadoInscripcion(String certificadoInscripcion) {
        this.certificadoInscripcion = certificadoInscripcion;
    }

    public String getCertificadoInscripcion() {
        return certificadoInscripcion;
    }

    public void setIsRazonSocialOK(boolean isRazonSocialOK) {
        this.isRazonSocialOK = isRazonSocialOK;
    }

    public boolean isIsRazonSocialOK() {
        return isRazonSocialOK;
    }

    public void setIsDisa(boolean isDisa) {
        this.isDisa = isDisa;
    }

    public boolean isIsDisa() {
        return isDisa;
    }

    public void setIsReq_Vis(boolean isReq_Vis) {
        this.isReq_Vis = isReq_Vis;
    }

    public boolean isIsReq_Vis() {
        return isReq_Vis;
    }

    public void setPanelRegistroFlota(boolean panelRegistroFlota) {
        this.panelRegistroFlota = panelRegistroFlota;
    }

    public boolean isPanelRegistroFlota() {
        return panelRegistroFlota;
    }

    public void setPanelRegistroChofer(boolean panelRegistroChofer) {
        this.panelRegistroChofer = panelRegistroChofer;
    }

    public boolean isPanelRegistroChofer() {
        return panelRegistroChofer;
    }
}
