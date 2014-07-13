package siat.view.chat.modelo;

import java.beans.PropertyChangeListener;

public interface ChatListener extends PropertyChangeListener{
    public void setUsername(String username);
    public String getUsername();
    public boolean isAlive();
    public void setAlive(boolean alive);
}