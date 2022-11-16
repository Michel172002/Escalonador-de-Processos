public class Processos {
    private int idProcesso;
    private int tempoChegada;
    private int tempoCPU;
    private int tempoDeConclusao = 0;
    private int tempoSistema = 0;
    private int tempoEspera = 0;

    public Processos(int idProcesso, int tempoChegada, int tempoCPU){
        this.idProcesso = idProcesso;
        this.tempoChegada = tempoChegada;
        this.tempoCPU = tempoCPU;
    }

    public int getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(int idProcesso) {
        this.idProcesso = idProcesso;
    }

    public int getTempoChegada() {
        return tempoChegada;
    }

    public void setTempoChegada(int tempoChegada) {
        this.tempoChegada = tempoChegada;
    }

    public int getTempoCPU() {
        return tempoCPU;
    }

    public void setTempoCPU(int tempoCPU) {
        this.tempoCPU = tempoCPU;
    }

    public int getTempoDeConclusao() {
        return tempoDeConclusao;
    }

    public void setTempoDeConclusao(int tempoDeConclusao) {
        this.tempoDeConclusao = tempoDeConclusao;
    }

    public int getTempoSistema() {
        return tempoSistema;
    }

    public void setTempoSistema(int tempoSistema) {
        this.tempoSistema = tempoSistema;
    }

    public int getTempoEspera() {
        return tempoEspera;
    }

    public void setTempoEspera(int tempoEspera) {
        this.tempoEspera = tempoEspera;
    }
}
