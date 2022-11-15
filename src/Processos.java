public class Processos {
    private int idProcesso;
    private int tempoChegada;
    private int tempoCPU;

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
}
