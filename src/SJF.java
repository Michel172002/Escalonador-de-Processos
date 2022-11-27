import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class SJF implements Algoritmos{
    @Override
    public void executar(int numProcessos, ArrayList<Processos> processos, int quantum) {
        ArrayList<Processos> filaDePronto = new ArrayList<>();
        ArrayList<Processos> filaDeProntoClone;
        ArrayList<Processos> filaConcluidos = new ArrayList<>();
        ArrayList<Processos> ordemGrafico = new ArrayList<>();

        Processos processoAux = null;
        Processos processoAtual = null;

        int tempoExecucao = 0;

        int duracao = 0;
        for(Processos p : processos){
            duracao += p.getTempoCPU();
        }

        for(int i=0; i <= duracao; i++){
            for (int j = 0; j < processos.size(); j++) {
                if (i == processos.get(j).getTempoChegada()) {
                    filaDePronto.add(processos.get(j));
                }
            }
            Collections.sort(filaDePronto);
            filaDeProntoClone = filaDePronto;
            if(processoAtual == null && !filaDePronto.isEmpty()) {
                processoAtual = filaDePronto.get(0);
            }

            if(!filaDePronto.isEmpty()) {
                ordemGrafico.add(processoAtual);
                if ((tempoExecucao + 1) >= processoAtual.getTempoCPU() && processoAux == null && filaDeProntoClone.size() > 1){
                    filaDeProntoClone.remove(processoAtual);
                    processoAux = filaDeProntoClone.get(0);
                }
                if(tempoExecucao == processoAtual.getTempoCPU()) {
                    if(!filaConcluidos.contains(processoAtual)){
                        filaConcluidos.add(processoAtual);
                    }
                    filaDePronto.remove(processoAtual);
                    processoAtual = processoAux;
                    processoAux = null;
                    tempoExecucao = 0;
                }
                for (int j = 0; j < filaDePronto.size(); j++) {
                    if(filaDePronto.get(j) != processoAtual){
                        filaDePronto.get(j).setTempoEspera(filaDePronto.get(j).getTempoEspera() + 1);
                    }
                }
                tempoExecucao++;
            }
        }
        for(Processos p : filaConcluidos){
            for (Processos p2 : processos){
                if(p.getIdProcesso() == p2.getIdProcesso()){
                    p.setTempoSistema(p2.getTempoCPU());
                }
            }
        }
        for (Processos p : filaConcluidos) {
            p.setTempoDeConclusao(p.getTempoEspera() + p.getTempoSistema());
            p.setTempoCPU(p.getTempoSistema());
        }

        criarTabela(filaConcluidos);
        criarGrafico(filaConcluidos, ordemGrafico);
    }
    public void criarTabela(ArrayList<Processos> processos) {
        try {
//            FileWriter fw = new FileWriter("C:\\Users\\DESKTOP\\Desktop\\FACULDADE\\4ºSEMESTRE\\SO\\Escalonador-de-Processos-master\\src\\saida");
            FileWriter fw = new FileWriter("/home/michel/Documentos/Faculdade/4semestre/SO/Escalonador-de-Processos/src/saida");

            PrintWriter pw = new PrintWriter(fw);

            pw.println("       |-----------------------TEMPO DE--------------------|");
            pw.println("Tarefa | Ingresso | Duração | Conclusão | Sistema | Espera |");
            pw.println("------- ---------- --------- ----------- --------- -------- ");
            for (int j = 0; j < processos.size(); j++) {
                pw.print("  T" + processos.get(j).getIdProcesso() + "   | ");
                pw.print("    " + processos.get(j).getTempoChegada() + "    | ");
                pw.print("   " + processos.get(j).getTempoCPU() + "    | ");
                pw.print("    " + processos.get(j).getTempoDeConclusao() + "     | ");
                pw.print("   " + processos.get(j).getTempoSistema() + "    | ");
                pw.println("   " + processos.get(j).getTempoEspera() + "  | ");
            }
            pw.println("------- ---------- --------- ----------- --------- -------- ");
            double tempoEspera = 0;
            double tempoSistema = 0;
            for (Processos p : processos){
                tempoSistema  += p.getTempoDeConclusao();
                tempoEspera  += p.getTempoEspera();
            }
            tempoEspera = tempoEspera / processos.size();
            tempoSistema = tempoSistema / processos.size();
            pw.println("");
            pw.println("TEMPO MEDIO DE ESPERA: " + tempoEspera);
            pw.println("TEMPO MEDIO DE SISTEMA: " + tempoSistema);
            pw.println("");
            pw.flush();
            pw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void criarGrafico(ArrayList<Processos> filaConcluidos, ArrayList<Processos> ordem) {
        try {
//            FileWriter fw = new FileWriter("C:\\Users\\DESKTOP\\Desktop\\FACULDADE\\4ºSEMESTRE\\SO\\Escalonador-de-Processos-master\\src\\saida", true);
            FileWriter fw = new FileWriter("/home/michel/Documentos/Faculdade/4semestre/SO/Escalonador-de-Processos/src/saida", true);
            PrintWriter pw = new PrintWriter(fw);
            int duracao = 0;
            for(Processos p : filaConcluidos){
                duracao += p.getTempoCPU();
            }

            for (int i = 1; i <= duracao; i++) {
                String num = String.format("%03d", i);

                if ((i - 1) == 0) {
                    pw.print("_____|");
                    for (int j = 0; j < filaConcluidos.size(); j++) {
                        pw.print("-T" + filaConcluidos.get(j).getIdProcesso() + "-|");
                    }
                    pw.println("");
                }
                pw.print(" " + num + " |");
                int numEspaco;
                numEspaco = 2 + (ordem.get(i).getIdProcesso() - 1) * 5;
                for (int x = 0; x < numEspaco; x++) {
                    pw.print(" ");
                }
                pw.println("|");
            }
            pw.flush();
            pw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}