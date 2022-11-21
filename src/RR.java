import java.util.*;

public class RR implements Algoritmos{
    @Override
    public void executar(int numProcessos, ArrayList<Processos> processos, int quantum) {

        ArrayList<Processos> filaDePronto = new ArrayList<>();
        ArrayList<Processos> filaConcluidos = new ArrayList<>();
        ArrayList<Processos> ordemGrafico = new ArrayList<>();
        int tempoExecucao = 0;

        for(int i=0; i <= 15; i++){
            for (int j = 0; j < processos.size(); j++) {
                if (i == processos.get(j).getTempoChegada()) {
                    filaDePronto.add(processos.get(j));
                }
            }
            if(!filaDePronto.isEmpty()) {
                ordemGrafico.add(filaDePronto.get(0));

                if(tempoExecucao == quantum || tempoExecucao == filaDePronto.get(0).getTempoCPU()) {
                    filaDePronto.get(0).setTempoCPU(filaDePronto.get(0).getTempoCPU() - quantum);

                    if (filaDePronto.get(0).getTempoCPU() > 0) {
                        filaDePronto.add(filaDePronto.get(0));
                    }

                    if(!filaConcluidos.contains(filaDePronto.get(0))){
                        filaConcluidos.add(filaDePronto.get(0));
                    }

                    filaDePronto.remove(0);
                    tempoExecucao = 0;
                }
                for (int j = 0; j < filaDePronto.size(); j++) {
                    if (j == 0) {
                        filaDePronto.get(j).setTempoSistema(filaDePronto.get(j).getTempoSistema() + 1);
                    }else {
                        filaDePronto.get(j).setTempoEspera(filaDePronto.get(j).getTempoEspera() + 1);
                    }
                }
                tempoExecucao++;
            }
        }
        for (Processos p : filaConcluidos) {
            p.setTempoDeConclusao(p.getTempoEspera() + p.getTempoSistema());
            p.setTempoCPU(p.getTempoSistema());
        }
        criarTabela(filaConcluidos);
        criarGrafico(filaConcluidos, ordemGrafico);
    }

    public void criarTabela(ArrayList<Processos> processos){
        System.out.println("       |-----------------------TEMPO DE--------------------|");
        System.out.println("Tarefa | Ingresso | Duração | Conclusão | Sistema | Espera |");
        System.out.println("-------+----------+---------+-----------+---------+--------+");
        for (int j=0; j < processos.size(); j++){
            System.out.print("  T" + processos.get(j).getIdProcesso() + "   | ");
            System.out.print("    " + processos.get(j).getTempoChegada() + "    | ");
            System.out.print("   " + processos.get(j).getTempoCPU() + "    | ");
            System.out.print("    " + processos.get(j).getTempoDeConclusao() + "     | ");
            System.out.print("   " + processos.get(j).getTempoSistema() + "    | ");
            System.out.println("   " + processos.get(j).getTempoEspera() + "  | ");
        }
        System.out.println("-------+----------+---------+-----------+---------+--------+");
    }

    public void criarGrafico(ArrayList<Processos> filaConcluidos, ArrayList<Processos> ordem){
        int maiorTempo = 0;
        for (Processos p : filaConcluidos) {
            if(maiorTempo < p.getTempoDeConclusao()){
                maiorTempo = p.getTempoDeConclusao();
            }
        }
        for (int i=1; i <= maiorTempo; i++){
            String num = String.format("%03d", i);

            if((i-1) == 0) {
                System.out.print("_____|");
                for (int j = 0; j < filaConcluidos.size(); j++) {
                    System.out.print("-T" + filaConcluidos.get(j).getIdProcesso() + "-|");
                }
                System.out.println("");
            }
            System.out.print(" " + num + " |");
            int numEspaco;
            numEspaco = 2 + (ordem.get(i).getIdProcesso() - 1) * 5;
            for (int x = 0; x < numEspaco; x++) {
                System.out.print(" ");
            }
            System.out.println("|");
        }
    }
}
