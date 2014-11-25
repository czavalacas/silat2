package siat.view.region;

import javax.faces.context.FacesContext;

import oracle.adf.controller.TaskFlowId;

import siat.view.backing.utiles.Utils;

public class BackingBean_Region {
    private String taskFlowId = "/WEB-INF/mainTF.xml#mainTF";

    public BackingBean_Region() {

    }

    public TaskFlowId getDynamicTaskFlowId() {
        return TaskFlowId.parse(taskFlowId);
    }

    public String getMainCall() {
        if(Utils.getSession("cambiarUrl") != null && taskFlowId.equals(Utils.getSession("url"))){
            taskFlowId = "/WEB-INF/mainTF.xml#mainTF";
            Utils.removeSession("cambiarUrl");
        }else{
            taskFlowId = (String)Utils.getSession("url");
        }
        if(Utils.getSession("override") != null){
            Utils.putSession("cambiarUrl","1");
        }
        Utils.removeSession("override");
        try{
            if(taskFlowId == null){
                taskFlowId = "/WEB-INF/mainTF.xml#mainTF";
            }else if(taskFlowId.equals("")){
                taskFlowId = "/WEB-INF/mainTF.xml#mainTF";
            }else{
                Utils.removeSession("beanSessionRegistrarCliente");
                Utils.removeSession("beanSessionRegistrarGuia");
                Utils.removeSession("beanSessionConsultarCliente");
                Utils.removeSession("beanSessionConsultarGuia");
                Utils.removeSession("beanSessionScopedRegistrarGastos");  
                Utils.removeSession("beanSessionScopedConsultarGastos");
                Utils.removeSession("beanSessionScopedAdministrarPermisos");
                Utils.removeSession("beanSessionScopeRegistrarFactura");
                Utils.removeSession("beanSessionScopeConsFactura");
                Utils.removeSession("beanSessionActualizarOrdenServicio");
                Utils.removeSession("beanSessionActualizarCliente");
                Utils.removeSession("beanSessionConfigCodigos");
                Utils.removeSession("beanSessionRegistrarPreFactura");
                Utils.removeSession("beanSessionConsPreFactura");
                Utils.removeSession("beanSessionScopeModificarUsuario");
                Utils.removeSession("beanSessionRegistrarManifiesto");
                Utils.removeSession("beanSessionScopedRegistrarOS");    
                Utils.removeSession("beanSessionConsultarManifiesto");   
           }
        }catch(Exception e){
            taskFlowId = "/WEB-INF/mainTF.xml#mainTF";
        }
        return null;
    }
}