package siat.view.backing.bienvenida;

public class SessionScopedBeanMain {
    
    private int exec = 0;
    private String nombMsj;
    private boolean isMostrarNotif = true;
    private boolean renderPoll = true;
    private boolean isPrimerChequeo = true;
    private int cantNuevoInicio = 0;

    public void setExec(int exec) {
        this.exec = exec;
    }

    public int getExec() {
        return exec;
    }

    public void setNombMsj(String nombMsj) {
        this.nombMsj = nombMsj;
    }

    public String getNombMsj() {
        return nombMsj;
    }

    public void setIsMostrarNotif(boolean isMostrarNotif) {
        this.isMostrarNotif = isMostrarNotif;
    }

    public boolean isIsMostrarNotif() {
        return isMostrarNotif;
    }

    public void setRenderPoll(boolean renderPoll) {
        this.renderPoll = renderPoll;
    }

    public boolean isRenderPoll() {
        return renderPoll;
    }

    public void setIsPrimerChequeo(boolean isPrimerChequeo) {
        this.isPrimerChequeo = isPrimerChequeo;
    }

    public boolean isIsPrimerChequeo() {
        return isPrimerChequeo;
    }

    public void setCantNuevoInicio(int cantNuevoInicio) {
        this.cantNuevoInicio = cantNuevoInicio;
    }

    public int getCantNuevoInicio() {
        return cantNuevoInicio;
    }
}
