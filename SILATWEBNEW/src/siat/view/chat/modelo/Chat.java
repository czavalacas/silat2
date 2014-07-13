package siat.view.chat.modelo;

import java.util.List;

public interface Chat {
    public void login(ChatListener listener);
    public void logout(ChatListener listener);
    public void addMessage(String message);
    public List<String> getMessages();
    public List<String> getUsers();
}