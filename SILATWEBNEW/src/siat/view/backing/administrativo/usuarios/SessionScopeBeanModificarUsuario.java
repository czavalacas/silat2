package siat.view.backing.administrativo.usuarios;

public class SessionScopeBeanModificarUsuario {
    private String contraseñaActual;
    private String contraselaNueva;
    private String repetirContraseñaNueva;

    public void setContraseñaActual(String contraseñaActual) {
        this.contraseñaActual = contraseñaActual;
    }

    public String getContraseñaActual() {
        return contraseñaActual;
    }

    public void setContraselaNueva(String contraselaNueva) {
        this.contraselaNueva = contraselaNueva;
    }

    public String getContraselaNueva() {
        return contraselaNueva;
    }

    public void setRepetirContraseñaNueva(String repetirContraseñaNueva) {
        this.repetirContraseñaNueva = repetirContraseñaNueva;
    }

    public String getRepetirContraseñaNueva() {
        return repetirContraseñaNueva;
    }
}
