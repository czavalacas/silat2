package siat.view.chat.bean;

import siat.view.chat.modelo.Chat;
import siat.view.chat.modelo.ChatListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import oracle.adf.share.logging.ADFLogger;

@ManagedBean
@ApplicationScoped
public class ChatBean implements Chat {
    private List<String> messages = Collections.synchronizedList(new ArrayList<String>());
    private List<String> users = Collections.synchronizedList(new ArrayList<String>());

    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private ScheduledExecutorService scheduler;
    
    public ChatBean(){

    }
    
    @PostConstruct
    public void initializer() {
        logger.setLevel(Level.INFO);
        scheduler = Executors.newSingleThreadScheduledExecutor();
        /**
         * Programar una tarea para hacer ping a los listeners de chat cada 10 segundos
         * */
        scheduler.scheduleWithFixedDelay(new PingTask(), 10, 10, TimeUnit.SECONDS);
        /**
         * Progamar una tarea para limpiar listeners de chat en estado muerto cada 30 segundos
         * */
        scheduler.scheduleWithFixedDelay(new CleanTask(), 30, 30, TimeUnit.SECONDS);
    }

    @PreDestroy
    public void finalizer() {
        scheduler.shutdownNow();
    }

    /** Login ChatListener subscribiendo a los mensjaes del chat
     * y agregando los nombres de usuario a la lista de usuarios
     *
     * @param listener ChatListener to login
     */
    public void login(ChatListener listener) {
        logger.info(listener.getUsername());

        propertyChangeSupport.addPropertyChangeListener(listener);
        users.add(listener.getUsername());
        addMessage("Bienvenido " + listener.getUsername());
    }

    /** Logout ChatLister quitandolos de la lista de usuarios
     *
     * @param listener ChatListener to login
     */
    public void logout(ChatListener listener) {
        logger.info(listener.getUsername());

        propertyChangeSupport.removePropertyChangeListener(listener);
        users.remove(listener.getUsername());
        addMessage("Goodbye " + listener.getUsername());
    }

    /**  Mandar Broadcast a los listeners de chat
     *
     * @param newMessage new chat message
     */
    public void addMessage(String newMessage) {
        logger.info(newMessage);

        messages.add(newMessage);
        /** Mostrar hasta 20 mensajes en una ventana de chat
         * */
        if (messages.size() > 20) {
            messages.remove(0);
        }
        propertyChangeSupport.firePropertyChange("newMessage", 
                                                 null,
                                                 newMessage + " Timestamp:" + System.currentTimeMillis());
    }

    public List<String> getMessages() {
        return messages;
    }

    public List<String> getUsers() {
        return users;
    }

    /** Recurrir a las tareas para hacer ping a los listeners de chat para chequear si aun estan
     * disponibles
     */
    protected class PingTask implements Runnable {
        public void run() {
            propertyChangeSupport.firePropertyChange("ping", 
                                                     null, 
                                                     "Ping:" + System.currentTimeMillis());
        }
    }

    /** Recurrir a las tareas para limpiar chat listeners que ya se cancelaron
     *  Resetear listeners que estan disponibles
     */
    protected class CleanTask implements Runnable {
        public void run() {
            for (PropertyChangeListener chatListener : propertyChangeSupport.getPropertyChangeListeners()) {
                ChatListener listener = (ChatListener)chatListener;
                if (!listener.isAlive()) {
                    logger.info(listener.getUsername());
                    logout(listener);
                } else {
                    //listener.setAlive(false);
                }
            }
        }
    }

    private static ADFLogger logger = ADFLogger.createADFLogger(ChatBean.class);
}
