package siat.view.chat.bean;

import siat.view.chat.modelo.Chat;
import siat.view.chat.modelo.ChatListener;
import java.beans.PropertyChangeEvent;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.activedata.ActiveDataEventUtil;
import oracle.adf.view.rich.activedata.ActiveModelContext;
import oracle.adf.view.rich.activedata.BaseActiveDataModel;
import oracle.adf.view.rich.event.ActiveDataEntry;
import oracle.adf.view.rich.event.ActiveDataUpdateEvent;
import siat.view.backing.utiles.Utils;
import silat.servicios_negocio.Beans.BeanUsuarioAutenticado;

@ManagedBean
@ViewScoped
public class ChatListenerBean extends BaseActiveDataModel implements ChatListener{
    private final AtomicInteger counter = new AtomicInteger(0);
    private BeanUsuarioAutenticado beanUsuario = (BeanUsuarioAutenticado)Utils.getSession("USER");
    
    /** Inyectar Chat bean
     */
    @ManagedProperty(value = "#{chatBean}")
    private Chat chatBean;
    
    public ChatListenerBean(){
        beanUsuario = (BeanUsuarioAutenticado)Utils.getSession("USER");
        if(beanUsuario == null){
            finalizer();
        }
    }

    private String username = beanUsuario.getCUsuario();
    private boolean alive = true;

    @PostConstruct
    public void initializer() {
        logger.setLevel(Level.INFO);
        logger.info("Usuario logeado: "+username);

        /**
         * Registrar modelo de datos activo a la ruta de llaves para el atribtuo de 'mensaje'
         */
        ActiveModelContext context = ActiveModelContext.getActiveModelContext();
        Object[] keyPath = new String[0];
        context.addActiveModelInfo(this, keyPath, "message");
        getChatBean().login(this);
    }

    /** This method is also referenced in
     * a bounded task flow chat-task-flow-definition.xml
     * as a property "finalizer".
     */
    @PreDestroy
    public void finalizer() {
        logger.info("Usuario: LOGGED OFF: " + username);
        getChatBean().logout(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
         logger.info("____Usuario:" + username + " Evento:" + evt.getPropertyName() + " Mensaje:" + evt.getNewValue());
         counter.incrementAndGet();
         ActiveDataUpdateEvent event = ActiveDataEventUtil.buildActiveDataUpdateEvent(ActiveDataEntry.ChangeType.UPDATE, 
                                                                                      counter.get(),
                                                                                      new String[0], 
                                                                                      null, 
                                                                                      new String[] { "message" },
                                                                                      new Object[] { evt.getNewValue() });
         fireActiveDataUpdate(event);
    }

    public void setMessage(String message) {
        getChatBean().addMessage(username + " > " + message);
    }

    public String getMessage() {
        return null;
    }

    public boolean getPong() {
        alive = true;
        return alive;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username != null ? username : "An�nimo";
    }

    @Override
    protected void startActiveData(Collection<Object> collection, int i) {
        
    }

    @Override
    protected void stopActiveData(Collection<Object> collection) {
        
    }

    @Override
    public int getCurrentChangeCount() {
        return counter.get();
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    private static ADFLogger logger = ADFLogger.createADFLogger(ChatListenerBean.class);

    public void setChatBean(Chat chatBean) {
        this.chatBean = chatBean;
    }

    public Chat getChatBean() {
        return chatBean;
    }
}